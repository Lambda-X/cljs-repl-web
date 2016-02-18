(ns cljs-repl-web.app
  (:require [reagent.core :as reagent]
            cljsjs.enquire
            [re-frame.core :refer [dispatch]]))

(defn gist-showing?
  "Given a db, indicates if the gist login dialog is shown.
  It is not bound to any specific console."
  [db]
  (get-in db [:gist-data :gist-showing?]))

(defn gist-auth-data
  "Given a db, returns the authorization data for gist.
  It is not bound to any specific console. It is used to
  retrieve the data from the form."
  [db]
  (get-in db [:gist-data :auth-data]))

(defn gist-saved-username
  "Given a db, returns the saved username (gist login). It is not bound
  to any specific console. It is used to keep info for the next login."
  [db]
  (get-in db [:gist-data :saved-username]))

(defn gist-error-msg
  "Given a db, returns the error after an unsuccessful attempt
  to create a gist. It is not bound to any specific console."
  [db]
  (get-in db [:gist-data :error-msg]))


;;;;;;;;;;;;;;;;;;;;;
;;  Media Queries  ;;
;;;;;;;;;;;;;;;;;;;;;

(def mq-string-narrow "only screen and (max-width: 480px)")
(def mq-string-medium "only screen and (min-width: 481px) and (max-width: 960px)")
(def mq-string-wide "only screen and (min-width: 961px)")

(defn initial-media-query!
  "Returns the initial media query state among :narrow, :medium
  and :wide."
  []
  (let [match-media! #( %)]
    (cond
      (.-matches (js/window.matchMedia mq-string-narrow)) :narrow
      (.-matches (js/window.matchMedia mq-string-medium )) :medium
      (.-matches (js/window.matchMedia mq-string-wide)) :wide
      :else :wide)))

(defn register-media-queries!
  []
  (js/enquire.register mq-string-narrow
                       (clj->js {:match #(dispatch [:media-match :narrow])}))
  (js/enquire.register mq-string-medium
                       (clj->js {:match #(dispatch [:media-match :medium])}))
  (js/enquire.register mq-string-wide
                       (clj->js {:match #(dispatch [:media-match :wide])})
                       true
                       ;; AR - degrade gracefully to this if browser does
                       ;; not support CSS3 Media queries
                       ))

(defn media-query-size
  "Given a db, returns one in :wide :medium or :narrow, defaults
  to :wide."
  [db]
  (or (get db :media-query-size) :wide))

;;;;;;;;;;;;;;;;;;;;;
;;  Initial State  ;;
;;;;;;;;;;;;;;;;;;;;;

(def initial-state
  {:consoles {}
   :gist-data {:gist-showing? false
               :auth-data {:username "" :password ""}}
   :media-query-size :wide})

(defn make-init-state!
  "Create the initial state, can be side effecting."
  [config]
  (-> (merge initial-state {:media-query-size (initial-media-query!)})))

(defn reset-state!
  "Reset the app state. Use this do"
  []
  (dispatch [:reset-db]))
