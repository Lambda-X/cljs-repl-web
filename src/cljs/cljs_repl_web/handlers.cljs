(ns cljs-repl-web.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [replumb.core :as replumb]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.io :as io]
            [cljs-repl-web.app :as app]
            [cljs-repl-web.views.utils :as utils]
            [cljs-repl-web.gist :as gist]
            [cljs-repl-web.code-mirror.handlers :as cm-handlers]))

;; (trace-forms {:tracer (tracer :color "green")}

(def initial-state {:consoles {}
                    :gist-data {:gist-showing? false
                                :auth-data {:username "" :password ""}}
                    :media-query-size :wide})

;; TODO Middleware


;;;;;;;;;;;;;;;;;;
;;;    DB      ;;;
;;;;;;;;;;;;;;;;;;

(register-handler
 :reset-db
 (fn reset-db [_ _]
   initial-state))

;; On app startup, create initial state
(register-handler
 :initialize
 (fn initialize [_ _]
   (println "Initializing app...")
   ;; we load the cljs core cache manually in order to reduce the app size
   ;; see https://github.com/clojure/clojurescript/wiki/Optional-Self-hosting for more info
   ;; see also related issue in replbum https://github.com/ScalaConsultants/replumb/issues/42
   (io/get-cljs-core-cache!)
   (app/register-media-queries!)
   (assoc initial-state
     :media-query-size (app/initial-media-query!))))

;;;;;;;;;;;;;;;;;;
;;     Gist    ;;;
;;;;;;;;;;;;;;;;;;

(register-handler
 :show-gist-login
 (fn show-gist-login [db [_]]
   (let [auth-data (app/gist-auth-data db)
         saved-username (app/gist-saved-username db)]
     (assoc db :gist-data {:gist-showing? true
                           :auth-data (assoc auth-data :password "")
                           :saved-username (:username auth-data)}))))

(register-handler
 :hide-gist-login
 (fn hide-gist-login [db [_]]
   (let [saved-username (app/gist-saved-username db)]
         (assoc db :gist-data {:gist-showing? false
                               :auth-data {:username saved-username
                                           :password ""}}))))

(register-handler
 :create-gist
 (fn create-gist [db [_ console-key auth-data success-handler error-handler]]
   (let [{:keys [username password]} @auth-data
         console (app/console db console-key)
         items (get-in db [:consoles (name console-key) :items])
         text (apply str (interpose \newline (map (fn [item]
                                                    (if-let [text (:text item)]
                                                      (str (:ns item) "=> " text)
                                                      (if (= :error (:type item))
                                                        (.-message (:value item))
                                                         (:value item))))
                                                  items)))
         empty-usr? (empty? username)
         empty-pwd? (empty? password)
         empty-txt? (empty? text)
         valid (not (or empty-usr? empty-pwd? empty-txt?))]

     (if valid
       (gist/create-gist username password text success-handler error-handler))

     (assoc db :gist-data {:gist-showing? false
                           :auth-data (dissoc @auth-data :password)
                           :saved-username (:username @auth-data)}))))

(register-handler
 :reset-gist-error-msg
 (fn reset-gist-error-msg [db [_]]
   (assoc-in db [:gist-data :error-msg] "")))

(register-handler
 :set-gist-error-msg
 (fn set-gist-error-msg [db [_ msg]]
   (assoc-in db [:gist-data :error-msg] msg)))

;;;;;;;;;;;;;;;;;;;;;;;
;;;     Popover     ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-handler
 :send-to-console
 (fn send-to-console [db [_ console-key lines]]
   (let [console (app/console db console-key)]
     (utils/scroll-to-top) ; in case we are at the bottom of the page
     (dispatch [:console-set-text console-key (first lines)])
     (dispatch [:focus-console-editor console-key])
     (assoc-in db [:consoles (name console-key) :interactive-examples] (rest lines)))))

(register-handler
 :delete-first-example
 (fn delete-first-example [db [_ console-key]]
   (let [examples (app/interactive-examples db console-key)]
     (assoc-in db [:consoles (name console-key) :interactive-examples] (drop 1 examples)))))

(register-handler
 :exit-interactive-examples
 (fn exit-interactive-examples [db [_ console-key]]
   (let [console (app/console db console-key)]
     (assoc-in db [:consoles (name console-key) :interactive-examples] []))))

;;;;;;;;;;;;;;;;;;;;;;;
;;  Media Queries   ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-handler
 :media-match
 (fn media-match [db [_ media-matched]]
   (assoc db :media-query-size media-matched)))

