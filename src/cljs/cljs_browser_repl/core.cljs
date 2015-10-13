(ns cljs-browser-repl.core
  (:require [reagent.core :as reagent]
            [cljs-browser-repl.console.echo :as echo]))

(enable-console-print!)

;; (def initial-state {:console {:created? false}})

;; (defonce app-state (reagent/atom initial-state))

;; (reset! app-state initial-state)

(defn page []
  [:div
   [echo/echo-console]])

(defn ^:export main []
  (println "In main()")
  (reagent/render [page]
                  (.getElementById js/document "app")))
