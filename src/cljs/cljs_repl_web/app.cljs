(ns cljs-repl-web.app
  (:require [reagent.core :as reagent]
            cljsjs.enquire
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

(defn is-console-empty?
  "Given a db and console key, returns whether the console
  contains text or is empty."
  [db k]
  (get-in db [:consoles (name k) :empty?]))

(defn interactive-examples
  "Given a db and console key, returns its examples or nil if not
  found."
  [db k]
  (get-in db [:consoles (name k) :interactive-examples]))

(def console-created? "Was the console created? Returns a truey or falsey value."
  console)

(defn gist-showing?
  "Given a db, indicates if the gist login dialog is shown.
  It is not bound to any specific console."
  [db]
  (get-in db [:gist-data :gist-showing?]))

(defn gist-auth-data
  "Given a db, returns the temp authorization data for gist.
  It is not bound to any specific console. It is used to
  retrieve the data from the form."
  [db]
  (get-in db [:gist-data :auth-data]))

(defn gist-save-auth-data
  "Given a db, returns the saved authorization data for gist.
  It is not bound to any specific console. It is used to keep
  info for the next login."
  [db]
  (get-in db [:gist-data :save-auth-data]))

(defn gist-error-msg
  "Given a db, returns the error after an unsuccessful attempt
  to create a gist. It is not bound to any specific console."
  [db]
  (get-in db [:gist-data :error-msg]))

(defn register-media-queries!
  []
  (js/enquire.register "only screen and (max-width: 480px)"
                       (clj->js {:match #(dispatch [:media-match :narrow])}))
  (js/enquire.register "only screen and (min-width: 481px) and (max-width: 960px)"
                       (clj->js {:match #(dispatch [:media-match :medium])}))
  (js/enquire.register "only screen and (min-width: 961px)"
                       (clj->js {:match #(dispatch [:media-match :wide])})
                       true
                       ;; AR - degrade gracefully to this if browser does
                       ;; not support CSS3 Media queries
                       ))

(defn api-panel-columns
  "Given a db, returns the number of columns necessary for the api
  panel. Defaults to 2 if no media query matches."
  [db]
  (let [mq (or (get db :media-query-size) :wide)]
    (case mq
      :narrow 1
      :medium 1
      :wide 2)))
