(ns cljs-browser-repl.core
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch-sync]]
            [re-com.core :refer [p h-box v-box box gap line]]
            [timothypratley.reanimated.core :as anim]
            [cljs-browser-repl.handlers]
            [cljs-browser-repl.subscriptions]
            [cljs-browser-repl.views :as views]))

;; (defonce conn (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

;; (set! re-com.box/debug true)

(defn page []
  [anim/pop-when true
   [v-box
    :class "app-main"
    :size   "1 1 auto"
    :gap    "10px"
    :align  :stretch
    :children [[h-box
                :size "1 1 auto"
                :gap "10px"
                :children [[gap :size "20px"]
                           [views/cljs-buttons]
                           [box
                            :size "1"
                            :style {:overflow "hidden"}
                            :child [views/cljs-console-component]]]]
               [line :size "2px"]
               [views/api-panel]]]])

(defn ^:export main []
  (println "In cljs-browser-repl.core/main")
  (dispatch-sync [:initialize])
  (reagent/render [page] (.getElementById js/document "app")))
