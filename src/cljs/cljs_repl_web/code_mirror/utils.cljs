(ns cljs-repl-web.code-mirror.utils
  (:require [reagent.core :as reagent]))

(defn colored-text [text style]
  (reagent/create-class
   {:component-did-mount
    (fn [this]
      (let [node (reagent/dom-node this)]
        ((aget js/CodeMirror "colorize") #js[node] "clojure")))
    :reagent-render
    (fn [_]
      [:pre {:style (merge {:padding 0 :margin 0} style)}
       text])}))
