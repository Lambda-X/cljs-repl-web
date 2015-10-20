(ns cljs-bootstrap.repl
  (:refer-clojure :exclude [load-file])
  (:require-macros [cljs.env.macros :refer [with-compiler-env]]
                   [cljs.repl :refer [pst]])
  (:require [cljs.js :as cljs]
            [cljs.tagged-literals :as tags]
            [cljs.tools.reader :as r]
            [cljs.analyzer :as ana]
            [cljs.env :as env]
            [cljs.repl :as repl]
            [cljs-bootstrap.load :as load]
            [cljs-bootstrap.doc-maps :as docs]))

(def ^:dynamic  *custom-eval-fn* "See cljs.js/*eval-fn* in ClojureScript core."
  cljs/js-eval)

(def ^:dynamic *custom-load-fn* "See cljs.js/*load-fn* in ClojureScript core."
  load/js-load)

;;;;;;;;;;;;;
;;; State ;;;
;;;;;;;;;;;;;

;; This is the compiler state atom. Note that
;; cljs/eval wants exactly an atom.
(defonce st (cljs/empty-state))

(defonce app-env (atom {:current-ns 'cljs.user}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Util fns - many from mfikes/plank ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn debug-prn
  [& args]
  (binding [cljs.core/*print-fn* cljs.core/*print-err-fn*]
    (apply println args)))

(defn current-ns
  "Return the current namespace, as a symbol."
  []
  (:current-ns @app-env))

(defn known-namespaces
  []
  (keys (:cljs.analyzer/namespaces @st)))

(defn map-keys
  [f m]
  (reduce-kv (fn [r k v] (assoc r (f k) v)) {} m))

(defn repl-read-string
  [line]
  (r/read-string {:read-cond :allow :features #{:cljs}} line))

(defn is-readable?
  [line]
  (binding [r/*data-readers* tags/*cljs-data-readers*]
    (try
      (repl-read-string line)
      true
      (catch :default _
        false))))

(defn ns-form?
  [form]
  (and (seq? form) (= 'ns (first form))))

(defn extract-namespace
  [source]
  (let [first-form (repl-read-string source)]
    (when (ns-form? first-form)
      (second first-form))))

(defn resolve
  "From cljs.analizer.api.clj. Given an analysis environment resolve a
  var. Analogous to clojure.core/resolve"
  [env sym]
  {:pre [(map? env) (symbol? sym)]}
  (try
    (ana/resolve-var env sym
      (ana/confirm-var-exists-throw))
    (catch :default _
      (ana/resolve-macro-var env sym))))

(defn get-var
  [env sym]
  (let [var (with-compiler-env st (resolve env sym))
        var (or var
              (if-let [macro-var (with-compiler-env st
                                   (resolve env (symbol "cljs.core$macros" (name sym))))]
                (update (assoc macro-var :ns 'cljs.core)
                  :name #(symbol "cljs.core" (name %)))))]
    (if (= (namespace (:name var)) (str (:ns var)))
      (update var :name #(symbol (name %)))
      var)))

(def valid-opts-set
  "Set of valid option for external input validation:

  * :verbose If true, enables more traces."
  #{:verbose})

(defn valid-opts
  "Extract options according to the valid-opts-set."
  [opts]
  (into {} (filter (comp valid-opts-set first) @app-env)))

(defn env-opts!
  "Reads the map of environment options. Usually these are set when the
  repl is initialized. The function works like merge, the mapping from
  the latter (left-to-right) will be the mapping in the result. Extracts
  the options in the valid-options set."
  [& maps] (apply merge @app-env maps))

(defn make-base-eval-opts!
  "Gets the base set of evaluation options. The variadic arity function
  works like merge, the mapping from the latter (left-to-right) will be
  the mapping in the result. Extracts the options in the valid-options
  set."
  ([]
   (env-opts! {:ns      (:current-ns @app-env)
               :context :expr
               :load    *custom-load-fn*
               :eval    *custom-eval-fn*}))
  ([& maps]
   (apply merge (make-base-eval-opts!) maps)))

(defn self-require?
  [specs]
  (some
    (fn [quoted-spec-or-kw]
      (and (not (keyword? quoted-spec-or-kw))
        (let [spec (second quoted-spec-or-kw)
              ns (if (sequential? spec)
                   (first spec)
                   spec)]
          (= ns @current-ns))))
    specs))

(defn canonicalize-specs
  [specs]
  (letfn [(canonicalize [quoted-spec-or-kw]
            (if (keyword? quoted-spec-or-kw)
              quoted-spec-or-kw
              (as-> (second quoted-spec-or-kw) spec
                (if (vector? spec) spec [spec]))))]
    (map canonicalize specs)))

(defn process-reloads!
  [specs]
  (if-let [k (some #{:reload :reload-all} specs)]
    (let [specs (->> specs (remove #{k}))]
      (if (= k :reload-all)
        (reset! cljs.js/*loaded* #{})
        (apply swap! cljs.js/*loaded* disj (map first specs)))
      specs)
    specs))

(defn make-ns-form
  [kind specs target-ns]
  (if (= kind :import)
    (with-meta `(~'ns ~target-ns
                  (~kind
                    ~@(map (fn [quoted-spec-or-kw]
                             (if (keyword? quoted-spec-or-kw)
                               quoted-spec-or-kw
                               (second quoted-spec-or-kw)))
                        specs)))
      {:merge true :line 1 :column 1})
    (with-meta `(~'ns ~target-ns
                  (~kind
                    ~@(-> specs canonicalize-specs process-reloads!)))
      {:merge true :line 1 :column 1})))

;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Eval handling fns ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;

(defn handle-eval-success!
  "Handles the case when the evaluation returned success, executing
  (side-effect!) *before* the callback is called with [true, result].

  Supports the following options (opts = option map):

  * :no-pr-str-on-value If true, avoids wrapping value in pr-str.

  The opts map passed here overrides the environment options.
  Note that value is always the last parameter."
  ([opts cb value]
   (handle-eval-success! opts cb identity value))
  ([opts cb side-effect! value]
   (side-effect!)
   (cb true (if-not (:no-pr-str-on-value opts)
              (pr-str value)
              value))))

(defn handle-eval-error!
  "Handles the case when the evaluation returned error, executing
  (side-effect!) *before* the callback is called with [false, error].
  Note that error is always the last parameter."
  ([opts cb error]
   (handle-eval-error! opts cb identity error))
  ([opts cb side-effect! error]
   (side-effect!)
   (set! *e error)
   (cb false error)))

(defn handle-eval-result!
  "Handles the evaluation result, calling the callback in the right way,
  based on success or error of the evaluation and executing
  (side-effect!) *before* the callback is called. There is also a arity
  for differentiating the side effect based on success or error.

  Supports the following options (opts = option map):
  * :verbose If true, prints the evaluation return.

  The opts map passed here overrides the environment options."
  ([opts cb ret]
   (handle-eval-result! opts cb identity ret))
  ([opts cb side-effect! {:keys [value error] :as ret}]
   (if-not error
     (handle-eval-success! opts cb side-effect! value)
     (handle-eval-error! opts cb side-effect! error)))
  ([opts cb side-effect-on-success! side-effect-on-error! {:keys [value error] :as ret}]
   (if-not error
     (handle-eval-success! opts cb side-effect-on-success! value)
     (handle-eval-error! opts cb side-effect-on-error! error))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Processing fns - from mfikes/plank ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn process-require
  [opts cb kind specs]
  (let [is-self-require? (and (= :kind :require) (self-require? specs))
        [target-ns restore-ns] (if-not is-self-require?
                                 [(:current-ns @app-env) nil]
                                 ['cljs.user (:current-ns @app-env)])
        ns-form (make-ns-form kind specs target-ns)]
    (when (:verbose opts)
      (debug-prn "Processing " (name kind) " via ns: " (pr-str ns-form)))
    (cljs/eval st
               ns-form
               (make-base-eval-opts! opts)
               (partial handle-eval-result!
                        opts
                        cb
                        #(when is-self-require?
                           (swap! app-env assoc :current-ns restore-ns))))))

(defn process-doc
  [cb env sym]
  (handle-eval-success! {:no-pr-str-on-value true}
                        cb
                        (with-out-str
                          (cond
                            (docs/special-doc-map sym) (repl/print-doc (docs/special-doc sym))
                            (docs/repl-special-doc-map sym) (repl/print-doc (docs/repl-special-doc sym))
                            :else (repl/print-doc (get-var env sym))))))

(defn process-pst
  [opts cb expr]
  (if-let [expr (or expr '*e)]
    (cljs/eval st
               expr
               (make-base-eval-opts! opts)
               (fn [res]
                 (if res
                   (handle-eval-success! (assoc opts :no-pr-str-on-value true) cb (.-stack res))
                   (handle-eval-success! opts cb res))))
    (handle-eval-success! opts cb nil)))

(defn process-in-ns
  [opts cb ns-string]
  (cljs/eval
   st
   ns-string
   (make-base-eval-opts! opts)
   (fn [result]
     (if (and (map? result) (:error result))
       (handle-eval-error! opts cb result)
       (let [ns-symbol result]
         (when (:verbose opts)
           (debug-prn "in-ns argument is symbol? " (symbol? ns-symbol)))
         (if-not (symbol? ns-symbol)
           (handle-eval-error! opts
                               cb
                               (ex-info "Argument to in-ns must be a symbol" {:tag ::error}))
           (if (some (partial = ns-symbol) (known-namespaces))
             (handle-eval-success! opts
                                   cb
                                   #(swap! app-env assoc :current-ns ns-symbol)
                                   nil)
             (let [ns-form `(~'ns ~ns-symbol)]
               (cljs/eval
                st
                ns-form
                (make-base-eval-opts! opts)
                (fn [{e :error}]
                  (if e
                    (handle-eval-error! opts cb e)
                    (handle-eval-success! opts
                                          cb
                                          #(swap! app-env assoc :current-ns ns-symbol)
                                          nil))))))))))))

(defn process-repl-special
  [opts cb expression-form]
  (let [env (assoc (ana/empty-env) :context :expr
                   :ns {:name (:current-ns @app-env)})
        argument (second expression-form)]
    (case (first expression-form)
      in-ns (process-in-ns opts cb argument)
      require (process-require opts cb :require (rest expression-form))
      require-macros (handle-eval-error! opts cb (ex-info "This keyword is not supported at the moment" {:tag ::error}))  ;; (process-require :require-macros identity (rest expression-form))
      import (process-require  opts cb :import (rest expression-form))
      doc (process-doc cb env argument)
      source (handle-eval-error! opts cb (ex-info "This keyword is not supported at the moment" {:tag ::error}))          ;; (println (fetch-source (get-var env argument)))
      pst (process-pst opts cb argument)
      load-file (handle-eval-error! opts cb (ex-info "This keyword is not supported at the moment" {:tag ::error})))))    ;; (process-load-file argument opts)

(defn process-1-2-3
  [expression-form value]
  (when-not (or ('#{*1 *2 *3 *e} expression-form)
                (ns-form? expression-form))
    (set! *3 *2)
    (set! *2 *1)
    (set! *1 value)))

(defn read-eval-print
  "Reads evaluates and prints the input source. The second parameter,
  eval-callback, is a function (fn [success, result] ...) where success
  is a boolean indicating if everything went right and result will
  contain the actual result of the evaluation or an error map.

  The first parameter is a map of configuration options, currently
  supporting:
  * :verbose Prints the evaluation return

  The opts map passed here overrides the environment options."
  [opts cb source]
  (try
    (let [expression-form (repl-read-string source)]
      (if (docs/repl-special? expression-form)
        (process-repl-special opts cb expression-form)
        (cljs/eval-str st
                       source
                       source
                       ;; opts (map)
                       (merge (make-base-eval-opts!)
                              {:source-map false
                               :def-emits-var true}
                              opts)
                       (fn [{:keys [ns value error] :as ret}]
                         (when (:verbose opts)
                           (debug-prn "Evaluation returned: " ret))
                         (if-not error
                           (handle-eval-success! opts
                                                 cb
                                                 #(do
                                                    (process-1-2-3 expression-form value)
                                                    (swap! app-env assoc :current-ns ns))
                                                 value)
                           (handle-eval-error! opts cb error))))))
    (catch :default e
      (handle-eval-error! opts cb e))))
