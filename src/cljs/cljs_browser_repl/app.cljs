(ns cljs-browser-repl.app
  (:require [reagent.core :as reagent]))

(def initial-state {:consoles {}
                    :app-ready? false})

(defonce state (reagent/atom initial-state))

(defn reset-state!
  "Reset the app state."
  []
  (reset! state initial-state))

(defn add-console!
  "Add a new console instance to the app state."
  [key instance]
  (swap! state assoc-in [:consoles (name key)] instance))

(defn console
  "Given a console key, returns its instance or nil if not found."
  [key]
  (get-in @state [:consoles (name key)]))

(def console-created? "Was the console created? Returns a truey or falsey value."
  console)

(defn ready?
  "Is the app ready to be shown?."
  []
  false
  #_(console-created? :cljs-console))
