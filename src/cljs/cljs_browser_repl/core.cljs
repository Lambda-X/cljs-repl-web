(ns cljs-browser-repl.core
  (:require [reagent.core :as reagent]
            [cljs-browser-repl.console.echo :as echo]))

(enable-console-print!)

(defn page []
  [:div
   [echo/echo-component]])

(defn ^:export main []
  (println "In main()")
  (reagent/render [page]
                  (.getElementById js/document "app")))
