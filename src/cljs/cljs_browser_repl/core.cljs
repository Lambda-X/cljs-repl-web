(ns cljs-browser-repl.core
  (:require [reagent.core :as reagent]
            [clojure.browser.repl :as repl]
            [cljs-browser-repl.console.cljs :as cljs]))

(defonce conn (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(defn page []
  [:div
   [cljs/cljs-component]
   [cljs/cljs-buttons-component]])

(defn ^:export main []
  (println "In cljs-browser-repl.core/main")
  (reagent/render [page]
                  (.getElementById js/document "app")))
