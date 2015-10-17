(ns cljs-bootstrap.repl
  (:require-macros [cljs.env.macros :refer [with-compiler-env]])
  (:require [cljs.js :as cljs]
            [cljs.tagged-literals :as tags]
            [cljs.tools.reader :as r]
            [cljs.analyzer :as ana]
            [cljs.env :as env]
            [cljs.repl :as repl]
            [cognitect.transit :as transit]
            [cljs-bootstrap.load :as load]
            [cljs-bootstrap.doc-maps :as docs]))

(def ^:dynamic  *custom-eval-fn* "See cljs.js/*eval-fn* in ClojureScript core."
  cljs/js-eval)

(def ^:dynamic *custom-load-fn* "See cljs.js/*load-fn* in ClojureScript core."
  load/js-load)

;; This is the compiler state atom. Note that
;; cljs/eval wants exactly an atom.
(defonce st (cljs/empty-state))

(defonce app-env (atom {:current-ns 'cljs.user}))

(defn debug-prn
  [& args]
  (binding [*print-fn* *print-err-fn*]
    (apply println args)))

(defn build-error
  ([msg] (build-error msg nil))
  ([msg cause]
   (ex-info msg
            {:tag :bootstrap/repl-error}
            cause)))

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

(defn make-base-eval-opts
  []
  {:ns      (:current-ns @app-env)
   :context :expr
   :load    *custom-load-fn*
   :eval    *custom-eval-fn*
   :verbose (:verbose @app-env)})

;; (defn require [macros-ns? sym reload]
  ;; (cljs.js/require
    ;; {:*compiler*     st
     ;; :*data-readers* tags/*cljs-data-readers*
     ;; :*load-fn*      *custom-load-fn*
     ;; :*eval-fn*      *custom-eval-fn*}
    ;; sym
    ;; reload
    ;; {:macros-ns macros-ns?
     ;; :verbose   (:verbose @app-env)}
    ;; (fn [res]
      ;; (println "require result:" res))))

;; (defn require-destructure [macros-ns? args]
  ;; (let [[[_ sym] reload] args]
    ;; (require macros-ns? sym reload)))

(defn handle-eval-success!
  "Handles the case when the evaluation returned success, executing
  (side-effect!) *before* the callback is called with [true, result].

  Supports the following options (opts = option map):

  * :no-result-processing If true, avoids any processing the result and
                          just forwards it to the callback as is.

  The opts map passed here overrides the environment options."
  ([opts cb result]
   (handle-eval-success! opts cb result identity))
  ([opts cb result side-effect!]
   (side-effect!)
   (cb true (if-not (:no-result-processing opts)
              (pr-str result)
              result))))

(defn handle-eval-error!
  "Handles the case when the evaluation returned error, executing
  (side-effect!) *before* the callback is called with [false, error]."
  ([opts cb error]
   (handle-eval-error! opts cb error identity))
  ([opts cb error side-effect!]
   (side-effect!)
   (set! *e error)
   (cb false error)))

(defn handle-eval-result!
  "Handles the evaluation result, calling the callback in the right way,
  based on success or error of the evaluation and executing
  (side-effect!) *before* the callback is called. Does not care about
  other evaluation result keys.

  Supports the following options (opts = option map):
  * :verbose If true, prints the evaluation return.

  The opts map passed here overrides the environment options."
  ([opts cb ret]
   (handle-eval-result! opts cb ret identity))
  ([opts cb {:keys [value error] :as ret} side-effect!]
   (when (:verbose opts)
     (debug-prn "Evaluation returned {:value " value " :error " error "}"))
   (when-not error
     (handle-eval-success! opts cb value side-effect!)
     (handle-eval-error! opts cb error side-effect!))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Process functions - from mfikes/plank ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- process-doc
  [cb env sym]
  (handle-eval-success! {:no-result-processing true}
                        cb
                        (with-out-str
                          (cond
                            (docs/special-doc-map sym) (repl/print-doc (docs/special-doc sym))
                            (docs/repl-special-doc-map sym) (repl/print-doc (docs/repl-special-doc sym))
                            :else (repl/print-doc (get-var env sym))))))

(defn- process-pst
  [opts cb expr]
  (when (:verbose opts) (debug-prn expr))
  (if-let [expr (or expr '*e)]
    (cljs/eval st
               expr
               (merge (make-base-eval-opts) opts)
               (partial handle-eval-result! opts cb))
    (handle-eval-success! opts cb nil)))

(defn process-in-ns
  [opts cb ns-string]
  (let [eval-opts (merge (make-base-eval-opts) opts)]
    (cljs/eval
     st
     ns-string
     eval-opts
     (fn [result]
       (when (:verbose opts)
         (debug-prn "in-ns first evaluation returned " result))
       (if (and (map? result) (:error result))
         (handle-eval-error! opts cb result)
         (let [ns-symbol result]
           (when (:verbose opts)
             (debug-prn "in-ns argument is symbol? " (symbol? ns-symbol)))
           (if-not (symbol? ns-symbol)
             (handle-eval-error! opts
                                 cb
                                 (build-error "Argument to in-ns must be a symbol"))
             (if (some (partial = ns-symbol) (known-namespaces))
               (handle-eval-success! opts
                                     cb
                                     nil
                                     #(swap! app-env assoc :current-ns ns-symbol))
               (let [ns-form `(~'ns ~ns-symbol)]
                 (cljs/eval
                  st
                  ns-form
                  eval-opts
                  (fn [{e :error}]
                    (if e
                      (handle-eval-error! opts cb e)
                      (handle-eval-success! opts
                                            cb
                                            nil
                                            #(swap! app-env assoc :current-ns ns-symbol))))))))))))))

(defn process-repl-special
  [opts cb expression-form]
  (let [env (assoc (ana/empty-env) :context :expr
                   :ns {:name (:current-ns @app-env)})
        argument (second expression-form)]
    (case (first expression-form)
      in-ns (process-in-ns opts cb argument)
      require (handle-eval-error! opts cb (build-error "This keyword is not supported at the moment"))         ;; (process-require :require identity (rest expression-form))
      require-macros (handle-eval-error! opts cb (build-error "This keyword is not supported at the moment"))  ;; (process-require :require-macros identity (rest expression-form))
      import (handle-eval-error! opts cb (build-error "This keyword is not supported at the moment"))          ;; (process-require :import identity (rest expression-form))
      doc (process-doc cb env argument)
      source (handle-eval-error! opts cb (build-error "This keyword is not supported at the moment"))          ;; (println (fetch-source (get-var env argument)))
      pst (process-pst opts cb argument)
      load-file (handle-eval-error! opts cb (build-error "This keyword is not supported at the moment")))))    ;; (process-load-file argument opts)

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
        (cljs/eval-str
         st
         source
         source
         ;; opts (map)
         (merge (make-base-eval-opts)
                {:source-map false
                 :def-emits-var true}
                opts)
         (fn [{:keys [ns value error] :as ret}]
           (when (:verbose opts)
             (debug-prn "Evaluation returned " ret))
           (if-not error
             (handle-eval-success! opts
                                  cb
                                  value
                                  #(do
                                     (process-1-2-3 expression-form value)
                                     (swap! app-env assoc :current-ns ns)))
             (handle-eval-error! opts cb error))))))
    (catch :default e
      (handle-eval-error! opts cb e))))
