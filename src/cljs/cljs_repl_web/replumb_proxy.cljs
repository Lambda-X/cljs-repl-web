(ns cljs-repl-web.replumb-proxy
  (:require [replumb.core :as replumb]
            [replumb.repl :as replumb-repl]
            [cljs-repl-web.io :as io]
            [cljs-repl-web.config :as config]
            [adzerk.cljs-console :as log :include-macros true]))

(defn replumb-options
  "Options for replumb.core/read-eval-call.

  Read the docs at https://github.com/ScalaConsultants/replumb"
  [verbose? src-paths]
  (merge (replumb/options :browser src-paths io/fetch-file!)
         {:warning-as-error true
          :verbose verbose?}))

(defn read-eval-call [opts cb source]
  (let [ns (replumb-repl/current-ns)]

    (replumb/read-eval-call opts
                            (fn [result]
                              (log/debug "Top of read-eval-call cb: ~{result}")
                              (cb {:success? (replumb/success? result)
                                   :result   result
                                   :prev-ns  ns
                                   :source   source}))
                            source)))

(defn multiline?
  [input]
  (try
    (replumb-repl/read-string {:features #{:cljs}} input)
    false
    (catch :default e
      (log/warn "multiline? caught @{e}")
      (= "EOF" (subs (.-message e) 0 3)))))

(defn eval-opts
  [verbose src-path]
  {:get-prompt  replumb/get-prompt
   :should-eval (complement multiline?)
   :to-str-fn   (partial replumb/result->string false true)
   :evaluate    (partial read-eval-call
                         (replumb-options verbose src-path))})

