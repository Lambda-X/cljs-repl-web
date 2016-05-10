(ns cljs-repl-web.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [replumb.core :as replumb]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [re-console.app :as rc-app]
            [cljs-repl-web.io :as io]
            [cljs-repl-web.app :as app]
            [cljs-repl-web.views.utils :as utils]
            [cljs-repl-web.gist :as gist]
            [cljs-repl-web.config :as config]
            [cljs-repl-web.localstorage :as ls]
            [adzerk.cljs-console :as log :include-macros true]))

;; (trace-forms {:tracer (tracer :color "green")}

;; TODO Middleware

;;;;;;;;;;;;;;;;;;
;;;    DB      ;;;
;;;;;;;;;;;;;;;;;;

(register-handler
 :reset-db
 (fn reset-db [_ [_ config local-storage-vals]]
   (app/make-init-state! config local-storage-vals)))

;; On app startup, create initial state
(register-handler
 :initialize
 (fn initialize [_ [_ config local-storage-vals]]
   (log/debug "Initializing app...")
   ;; we load the cljs core cache manually in order to reduce the app size
   ;; see https://github.com/clojure/clojurescript/wiki/Optional-Self-hosting for more info
   ;; see also related issue in replbum https://github.com/ScalaConsultants/replumb/issues/42
   (io/load-cljs-core-cache! (:core-cache-url config))
   (io/print-version! (:version-path config))
   (app/register-media-queries!)
   (app/make-init-state! config local-storage-vals)))

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
         items (get-in db [:consoles (name console-key) :items])
         text (rc-app/console-full-text db console-key)
         empty-usr? (empty? username)
         empty-pwd? (empty? password)
         empty-txt? (empty? text)
         valid (not (or empty-usr? empty-pwd? empty-txt?))]

     (when valid
       (ls/save-username! username)
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
;;  Media Queries   ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-handler
 :media-match
 (fn media-match [db [_ media-matched]]
   (assoc db :media-query-size media-matched)))

;;;;;;;;;;;;;;;;;;;;;;;
;;   Console mode   ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-handler
 :switch-console-mode
 (fn switch-console-mode [db [_ new-mode console-id]]
   (dispatch [:set-console-mode console-id new-mode])
   (ls/save-mode! new-mode)
   db))


;; )

;;;;;;;;;;;;;;;;;;;;;;;
;;  Re-complete   ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-handler
 :console-set-autocompleted-text
 (fn console-set-text [db [_ console-key]]
   (rc-app/set-console-text db console-key (get-in db [:re-complete :linked-components (keyword console-key) :text]))))

;;;;;;;;;;;;;;;;;;
;;;  Console   ;;;
;;;;;;;;;;;;;;;;;;

(register-handler
 :switch-console
 (fn switch-cosnole [db [_ console-key]]
   (assoc db :current-console (name console-key))))
