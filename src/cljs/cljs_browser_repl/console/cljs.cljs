(ns cljs-browser-repl.console.cljs
  (:require [replumb.core :as replumb]
            [jayq.core :refer [$ css html]]
            [cljs-browser-repl.console :as console]))

(def default-matchings
  {:match-round-brackets  [\( \)]
   :match-square-brackets [\[ \]]
   :match-curly-brackets  [\{ \}]
   :match-string          [\" \"]})

(defn handle-result!
  [console result]
  (let [write-fn (if (replumb/success? result) console/write-return! console/write-exception!)]
    (write-fn console (replumb/unwrap-result result))))

(defn cljs-read-eval-print!
  [console line]
  (try
    (replumb/read-eval-call (partial handle-result! console) line)
    (catch js/Error err
      (println "Caught js/Error during read-eval-print: " err)
      (console/write-exception! console err))))

(defn cljs-console-prompt!
  [console]
  (doto console
    (.Prompt true (fn [input]
                    (cljs-read-eval-print! console input)
                    (.SetPromptLabel console (replumb/get-prompt)) ;; necessary for namespace changes
                    (cljs-console-prompt! console)))))

(defn element-text
  [element]
  (or (.-textContent element) (.-innerText element)))

(defn hljs-html-string
  "Given a language and a string, returns the html string representing colors."
  [language s]
  (.-value (js/hljs.highlight language s)))

(defn cljs-console!
  "Create a console for ClojureScript."
  [console-opts]
  (let [c (console/new-jqconsole ".cljs-console"
                                 (merge {:prompt-label (replumb/get-prompt)
                                         :disable-auto-focus true
                                         :continue-label "  "}
                                        console-opts))]
    ;; (console/register-matchings! default-matchings)
    (js/hljs.initHighlighting)
    (.SetKeyPressHandler c (fn [event]
                             (let [previous-text (.GetPromptText c)
                                   current-char (.fromCharCode js/String (.-which event))
                                   prompt-texts ($ ".jqconsole-prompt-text")]
                               (doseq [pt prompt-texts]
                                 (let [jpt ($ pt)]
                                   (.html jpt (hljs-html-string "clojure" (.text jpt)))))

                               (let [last ($ (last prompt-texts))
                                     text (.GetPromptText c)]
                                 (println text)
                                 (if last
                                   (.html last (hljs-html-string "clojure" (str (.text last) current-char))))))
                             false))
    c))

(defn cljs-reset-console-and-prompt!
  "Resets the console and forces the focus onto it."
  [console]
  (console/reset-console! console)
  (console/focus-console! console)
  (cljs-console-prompt! console))

(defn cljs-clear-console!
  "Clears the console and put forces the focus onto it."
  [console]
  (console/clear-console! console)
  (console/focus-console! console))
