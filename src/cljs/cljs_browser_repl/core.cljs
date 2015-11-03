(ns cljs-browser-repl.core
  (:require [reagent.core :as reagent]
            [re-com.core     :refer [p h-box v-box box gap line scroller border label title button checkbox hyperlink-href
                                     slider horizontal-bar-tabs info-button input-text input-textarea
                                     popover-anchor-wrapper popover-content-wrapper popover-tooltip px] :refer-macros [handler-fn]]
            [re-com.box      :refer [h-box-args-desc v-box-args-desc box-args-desc gap-args-desc line-args-desc scroller-args-desc border-args-desc flex-child-style]]
            [re-com.util     :refer [px]]
            [re-com.validate :refer [string-or-hiccup? alert-type? vector-of-maps?]]
            [clojure.browser.repl :as repl]
            [cljs-browser-repl.console.cljs :as cljs]))

;; (defonce conn (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(defn page []
  [v-box
   :size   "1 1 auto"
   :margin "2px"
   :gap    "20px"
   :align  :stretch
   :children [[title
               :label "ClojureScript REPL"
               :underline? true
               :level :level2
               :style {:align-self :center}]
              [h-box
               :size "1 1 auto"
               :gap "10px"
               :children [[gap :size "0 1 70px"]
                          [cljs/cljs-button-components]
                          [box
                           :size "1"
                           :style {:overflow "hidden"}
                           :child [cljs/cljs-component]]
                          [gap :size "0 1 80px"]]]
              [line
               :size "2px"]]])

(defn ^:export main []
  (println "In cljs-browser-repl.core/main")
  (reagent/render [page] (.getElementById js/document "app")))
