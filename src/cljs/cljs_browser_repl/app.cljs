(ns cljs-browser-repl.app
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch]]))

;; AR - we are going to use re-frame
;; (defonce state (reagent/atom initial-state))

(defn reset-state!
  "Reset the app state. Use this do"
  []
  (dispatch [:reset-db]))

(defn console
  "Given a db and console key, returns its instance or nil if not
  found."
  [db k]
  (get-in db [:consoles (name k) :console]))

(defn interactive-examples
  "Given a db and console key, returns its examples or nil if not
  found."
  [db k]
  (get-in db [:consoles (name k) :interactive-examples]))

(def console-created? "Was the console created? Returns a truey or falsey value."
  console)
