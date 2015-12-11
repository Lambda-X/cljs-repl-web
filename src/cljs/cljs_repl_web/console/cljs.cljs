(ns cljs-repl-web.console.cljs
  (:require [clojure.string :as string]
            [replumb.core :as replumb]
            [replumb.repl :as replumb-repl]
            [re-frame.core :refer [subscribe dispatch]]
            [cljs-repl-web.console :as console]
            [cljs-repl-web.io :as io]
            [cljs-repl-web.highlight :as highlight]))

;; ;TODO: commented out for now, because of hljs
;; (def default-matchings {})
;; {:match-round-brackets  [\( \)]
;;  :match-square-brackets [\[ \]]
;;  :match-curly-brackets  [\{ \}]
;;  :match-string          [\" \"] })

(defn handle-result!
  [console result]
  (let [write-fn (if (replumb/success? result) console/write-return! console/write-exception!)]
    (write-fn console (replumb/unwrap-result result))))

(defn cljs-read-eval-print!
  [console repl-opts line]
  (try
    (replumb/read-eval-call repl-opts (partial handle-result! console) line)
    (catch js/Error err
      (println "Caught js/Error during read-eval-print: " err)
      (console/write-exception! console err))))

(defn cljs-console-set-prompt-text!
  [console text]
  (console/set-prompt-text! console text)
  (dispatch [:set-console-text :cljs-console text]))

(defn cljs-console-get-prompt-text!
  "Get the current text, prompt not included, unlike jqconsole's."
  [console]
  (let [prompt-n-text (.GetPromptText console)]
    (nth (remove empty? (map string/trim (string/split prompt-n-text
                                                       (re-pattern (replumb/get-prompt)) 2)))
         0 "")))

(defn cljs-console-multiline?
  "Following jq-console specs:

  multiline_callback: If specified, this function is called when the
  user presses Enter to check whether the input should continue to the
  next line. The function must return one of the following values:

    false: the input operation is completed.

    0: the input continues to the next line with the current indent.

    N (int): the input continues to the next line, and the current
    indent is adjusted by N, e.g. -2 to unindent two levels."
  [input]
  (try
    (replumb-repl/repl-read-string input)
    false
    (catch :default _
      0)))

(def repl-options "Static set of options for replumb.core/read-eval-call"
  (merge (replumb/browser-options ["/js/compiled/out/min"]
                                  io/fetch-file!)
         {:warning-as-error true
          :verbose false}))

(defn cljs-console-prompt!
  [console repl-opts]
  (.Prompt console true
           (fn [input]
             (cljs-read-eval-print! console repl-opts input)
             (.SetPromptLabel console (replumb/get-prompt)) ;; necessary for namespace changes
             (cljs-console-prompt! console repl-opts)
             (dispatch [:set-console-text :cljs-console input]))
           cljs-console-multiline?)
  (when-let [example @(subscribe [:get-next-example :cljs-console])]
    (cljs-console-set-prompt-text! console example)
    (dispatch [:delete-first-example :cljs-console])))

(defn cljs-console!
  "Create a console for ClojureScript."
  [console-opts]
  (doto (console/new-jqconsole ".cljs-console"
                               (merge {:prompt-label (replumb/get-prompt)
                                       :disable-auto-focus false
                                       :continue-label "  "}
                                      console-opts))
    ;; (console/register-matchings! default-matchings)
    (console/register-shortcut "A" console/move-to-start!)
    (console/register-shortcut "E" console/move-to-end!)
    (highlight/highlight-hack!)))

(defn cljs-reset-console-and-prompt!
  "Resets the console and forces the focus onto it."
  [console repl-opts]
  (console/reset-console! console)
  (console/focus-console! console)
  (cljs-console-prompt! console repl-opts))

(defn cljs-clear-console!
  "Clears the console and put forces the focus onto it."
  [console]
  (console/clear-console! console)
  (console/focus-console! console))
