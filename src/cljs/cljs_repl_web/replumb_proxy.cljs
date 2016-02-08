(ns cljs-repl-web.replumb-proxy
  (:require [replumb.core :as replumb]
            [replumb.repl :as replumb-repl]
            [cljs-repl-web.io :as io]))

(def repl-options
  "Static set of options for replumb.core/read-eval-call"
  (merge (replumb/browser-options [(str io/base-path "/js/compiled/out/min")]
                                  io/fetch-file!)
         {:warning-as-error true
          :verbose false}))

(defn read-eval-call [opts cb source]
  (let [ns (replumb-repl/current-ns)]
    (replumb/read-eval-call (merge repl-options opts)
                            #(cb {:success? (replumb/success? %)
                                  :result   (replumb/unwrap-result %)
                                  :prev-ns  ns
                                  :source   source})
                            source)))

(defn multiline?
  [input]
  (try
    (replumb-repl/repl-read-string input)
    false
    (catch :default _
      true)))

(def eval-opts {:get-prompt  replumb/get-prompt
                :should-eval (complement multiline?)
                :evaluate    read-eval-call})
