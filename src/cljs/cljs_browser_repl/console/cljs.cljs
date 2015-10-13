(ns cljs-browser-repl.console.cljs
  (:require [reagent.core :as reagent]
            [cljs-bootstrap.repl.web :as repl]
            [cljs-browser-repl.console :as console]))

(defn clj-console-did-mount []
  (js/$
   (fn []
     (let [jqconsole (console/new-jqconsole "#clj-console"
                                            :prompt-label "Let me echo it for you: ")]
       #_(echo-prompt jqconsole)))))

(defn clj-console-render []
  [:div.console-container
   [:div#clj-console.console.clj-console]])

(defn clj-console
  "A console for evaluating Clojure(Script) forms."
  []
  (reagent/create-class {:reagent-render clj-console-render
                         :component-did-mount clj-console-did-mount}))
