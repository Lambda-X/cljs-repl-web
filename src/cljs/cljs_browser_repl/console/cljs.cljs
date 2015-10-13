(ns cljs-browser-repl.console.cljs
  (:require [reagent.core :as reagent]
            [cljs-browser-repl.console :as console]))

(defn cljs-console-did-mount []
  (js/$
   (fn []
     (let [jqconsole (console/new-jqconsole "#cljs-console"
                                            :prompt-label "Let me echo it for you: ")]
       #_(echo-prompt jqconsole)))))

(defn cljs-console-render []
  [:div.console-container
   [:div#cljs-console.console.cljs-console]])

(defn cljs-console
  "A console for evaluating Clojure(Script) forms."
  []
  (reagent/create-class {:reagent-render cljs-console-render
                         :component-did-mount cljs-console-did-mount}))
