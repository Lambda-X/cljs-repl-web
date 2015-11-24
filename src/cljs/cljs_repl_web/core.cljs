(ns cljs-repl-web.core
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch dispatch-sync]]
            [re-com.core :refer [p h-box v-box box gap line]]
            [cljs-repl-web.handlers]
            [cljs-repl-web.subscriptions]
            [cljs-repl-web.views :as views]))

;; (defonce conn (repl/connect "http://localhost:9000/repl"))

;; (set! re-com.box/debug true)

(enable-console-print!)

(defn ^:export main []
  (println "In cljs-browser-repl.core/main")
  (dispatch-sync [:initialize])
  (reagent/render [views/repl-component] (.getElementById js/document "app-center"))
  (reagent/render [views/bottom-panel] (.getElementById js/document "app-bottom"))
  (reagent/render [views/footer-component] (.getElementById js/document "app-footer")))
