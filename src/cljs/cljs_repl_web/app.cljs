(ns cljs-repl-web.app
  (:require [reagent.core :as reagent]
            cljsjs.enquire
            [re-frame.core :refer [dispatch subscribe]]
            [re-complete.app :as complete-app]
            [replumb.ast :as ast]
            [clojure.string :as str]
            [replumb.ast :as ast]))

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
  [config {:keys [username]}]
  (-> (merge initial-state {:media-query-size (initial-media-query!)})
      (assoc-in [:gist-data :auth-data :username] username)))

(defn reset-state!
  "Reset the app state. Use this do"
  []
  (dispatch [:reset-db]))

;;;;;;;;;;;;;;;;;;;;;
;;; Autocomplete  ;;;
;;;;;;;;;;;;;;;;;;;;;

(defn opening-excluded-chars [word excluded-chars]
  (let [starts-with-excluded-char? ((set excluded-chars) (first word))]
    (if starts-with-excluded-char?
      (opening-excluded-chars (apply str (rest word)) excluded-chars)
      word)))

(defn closing-excluded-chars [word excluded-chars]
  (let [ends-with-excluded-char? ((set excluded-chars) (last word))]
    (if ends-with-excluded-char?
      (closing-excluded-chars (apply str (butlast word)) excluded-chars)
      word)))

(defn current-word [previous-input input]
  (->> input
       (complete-app/index previous-input)
       (complete-app/current-word input)))

(defn sort-case-insensitive-way [data]
  (sort #(compare (str/upper-case %1) (str/upper-case %2)) data))

(defn get-functions-from-ns [ns]
  (sort-case-insensitive-way (map str (keys (ast/ns-publics @replumb.repl.st ns)))))

(defn lower-case-first [items-to-sort]
  (let [lower-case-items (sort (filter #((set (map char (range 97 122))) (first %))
                                       items-to-sort))
        other-items (sort (vec (clojure.set/difference (set items-to-sort) (set lower-case-items))))]
    (apply conj other-items (reverse lower-case-items))))

(defn sort-dictionary [dictionary]
  (->> dictionary
       sort-case-insensitive-way
       lower-case-first))

(defn create-dictionary [event console-key]
  (let [previous-input (subscribe [:get-previous-input console-key])
        options (subscribe [:get-options console-key])
        trim-chars (:trim-chars @options)
        new-text (-> @previous-input
                     (current-word event)
                     (opening-excluded-chars trim-chars)
                     (closing-excluded-chars trim-chars))
        user-ns (keys (replumb.ast/ns-publics @replumb.repl.st 'cljs.user))
        requires (ast/requires @replumb.repl.st 'cljs.user)
        cljs-user (get-functions-from-ns 'cljs.user)
        cljs-core (get-functions-from-ns 'cljs.core)
        base-libs (concat cljs-user cljs-core)
        list-of-requires-vars (apply concat (map (fn [[k v]]
                                                   (map #(str k "/" %) (keys (ast/ns-publics @replumb.repl.st v))))
                                                 requires))
        created-dictionary (concat base-libs list-of-requires-vars)]
    (if (= new-text "")
      ""
      (dispatch [:dictionary console-key (sort-dictionary created-dictionary)]))))
