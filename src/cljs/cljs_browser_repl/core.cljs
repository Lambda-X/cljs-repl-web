(ns cljs-browser-repl.core
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch-sync]]
            [re-com.core :refer [p h-box v-box box gap line]]
            [cljs-browser-repl.handlers]
            [cljs-browser-repl.subscriptions]
            [cljs-browser-repl.views :as views]))

;; (defonce conn (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

;; (set! re-com.box/debug true)

(defn ^:export main []
  (println "In cljs-browser-repl.core/main")
  (dispatch-sync [:initialize])
  (reagent/render [views/repl-component] (.getElementById js/document "app-center"))
  (reagent/render [views/bottom-panel] (.getElementById js/document "app-bottom")))
