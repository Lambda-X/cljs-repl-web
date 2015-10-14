(ns cljs-browser-repl.core
  (:require [reagent.core :as reagent]
            [cljs-browser-repl.console.cljs :as cljs]))

(enable-console-print!)

(defn page []
  [:div
   [cljs/cljs-component]])

(defn ^:export main []
  (println "In main()")
  (reagent/render [page]
                  (.getElementById js/document "app")))
