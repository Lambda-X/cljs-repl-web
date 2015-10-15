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
            [cljs-bootstrap.doc-maps :as doc-maps]))

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

(defn error
  ([msg] (error msg nil))
  ([msg cause] (ex-info msg
                        (assoc @app-env :tag :bootstrap/repl-error)
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

(defn repl-special?
  [form]
  (and (seq? form) (doc-maps/repl-special-doc-map (first form))))

(defn special-doc
  [name-symbol]
  (assoc (doc-maps/special-doc-map name-symbol)
    :name name-symbol
    :special-form true))

(defn repl-special-doc
  [name-symbol]
  (assoc (doc-maps/repl-special-doc-map name-symbol)
    :name name-symbol
    :repl-special-function true))

(defn make-base-eval-opts
  []
  {:ns      (:current-ns @app-env)
   :context :expr
   :load    *custom-load-fn*
   :eval    *custom-eval-fn*
   :verbose (:verbose @app-env)})

(defn resolve
  "Given an analysis environment resolve a var. Analogous to
   clojure.core/resolve"
  [env sym]
  {:pre [(map? env) (symbol? sym)]}
  (try
    (ana/resolve-var env sym
      (ana/confirm-var-exists-throw))
    (catch :default _
      (ana/resolve-macro-var env sym))))


(defn require [macros-ns? sym reload]
  (cljs.js/require
    {:*compiler*     st
     :*data-readers* tags/*cljs-data-readers*
     :*load-fn*      *custom-load-fn*
     :*eval-fn*      *custom-eval-fn*}
    sym
    reload
    {:macros-ns macros-ns?
     :verbose   (:verbose @app-env)}
    (fn [res]
      (println "require result:" res))))

(defn require-destructure [macros-ns? args]
  (let [[[_ sym] reload] args]
    (require macros-ns? sym reload)))

;; (defn get-namespace
;;   [key]
;;   (let [ns (get-in @current-ns [::namespaces key])]
;;     (if-not (nil? ns)
;;       ns
;;       (when (= 'cljs.user key)
;;         {:name 'cljs.user}))))

;; (let [env (assoc (ana/empty-env) :context :expr
;;                             :ns {:name @current-ns})]
;;              (let [s (case (first expression-form)
;;                        in-ns (pr-str (in-ns-fn expression-form))
;;                        require (pr-str (require-destructure false (rest expression-form)))
;;                        require-macros (pr-str (require-destructure true (rest expression-form)))
;;                        doc (with-out-str
;;                              (if (repl-specials (second expression-form))
;;                                (repl/print-doc (repl-special-doc (second expression-form)))
;;                                (repl/print-doc
;;                                 (let [sym (second expression-form)
;;                                       var (with-compiler-env st
;;                                             (resolve env sym))]
;;                                   (:meta var))))))]
;;                (cb true s)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Process functions - from mfikes/plank ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn process-in-ns
  [ns-string cb]
  (let [opts (make-base-eval-opts)]
    (cljs/eval
     st
     ns-string
     opts
     (fn [result]
       (when (:verbose opts) (debug-prn "in-ns first evaluation returned " result))
       (if (and (map? result) (:error result))
         (cb false result)
         (let [ns-symbol result]
           (when (:verbose opts) (debug-prn "in-ns argument is symbol? " (symbol? ns-symbol)))
           (if-not (symbol? ns-symbol)
             (cb false (error "Argument to in-ns must be a symbol."))
             (if (some (partial = ns-symbol) (known-namespaces))
               (do (swap! app-env assoc :current-ns ns-symbol)
                   (cb true (pr-str nil)))
               (let [ns-form `(~'ns ~ns-symbol)]
                 (cljs/eval
                  st
                  ns-form
                  opts
                  (fn [{e :error}]
                    (if e
                      (cb false e)
                      (do (when (:verbose opts) (debug-prn "in-ns second evaluation returned correctly"))
                          (swap! app-env assoc :current-ns ns-symbol)
                          (cb true (pr-str nil)))))))))))))))

(defn process-repl-special
  [expression-form {:keys [redirect-out-to-string?] :as opts} cb]
  (let [env (assoc (ana/empty-env) :context :expr
                   :ns {:name (:current-ns @app-env)})
        argument (second expression-form)]
    (case (first expression-form)
      in-ns (process-in-ns argument cb)
      ;; require (process-require :require identity (rest expression-form))
      ;; require-macros (process-require :require-macros identity (rest expression-form))
      ;; import (process-require :import identity (rest expression-form))
      ;; doc (process-doc env argument)
      ;; source (println (fetch-source (get-var env argument)))
      ;; pst (process-pst argument)
      ;; load-file (process-load-file argument opts)
      )))

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

  The last parameter is a map of configuration options, currently
  supporting:
  * :redirect-out-to-string does what it says if true
  "
  [source opts cb]
  (try
    (let [expression-form (repl-read-string source)]
      (if (repl-special? expression-form)
        (process-repl-special expression-form opts cb)
        (cljs/eval-str
         st
         source
         source
         ;; opts (map)
         (merge (make-base-eval-opts)
                {:source-map false
                 :def-emits-var true})
         (fn [{:keys [ns value error] :as ret}]
           (when (:verbose opts) (debug-prn "Normal evaluation returned " ret))
           (if-not error
             (do
               (process-1-2-3 expression-form value)
               (swap! app-env assoc :current-ns ns)
               (cb true (pr-str value)))
             (do
               (set! *e error)
               (cb false error)))))))
    (catch :default e
      (cb false e))))
