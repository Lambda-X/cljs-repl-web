(ns cljs-browser-repl.console.cljs
  (:require [reagent.core :as reagent]
            ;; [cljs-bootstrap.repl.web :as repl]
            [cljs-browser-repl.console :as console]))


(defn eval-write [jqconsole str]
  (console/jqconsole-write jqconsole ["==> " str "\n"]))

(defn extend-jqconsole [jqconsole chan]
  (.RegisterMatching jqconsole "{" "}" "brace")
  (.RegisterMatching jqconsole "(" ")" "paren")
  (.RegisterMatching jqconsole "[" "]" "bracket")

  ;; Abort prompt on Ctrl+C.
  (.RegisterShortcut jqconsole "C"
                     (fn []
                       (.AbortPrompt jqconsole)
                       (console/jqconsole-write jqconsole ["Cancel with Ctrl+C" "\n"])
                       (console/jqconsole-prompt jqconsole chan))))

(defn clj-console
  "A console for evaluating Clojure(Script) forms."
  []
  [(console/jqconsole-component
    {:prompt-label "Let me echo it for you: "
     :eval-write eval-write
     :extend-jqconsole extend-jqconsole
     })])
