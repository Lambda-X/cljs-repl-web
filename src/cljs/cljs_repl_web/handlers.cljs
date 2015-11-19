(ns cljs-repl-web.handlers
  (:require [re-frame.core :refer [register-handler]]
            [replumb.core :as replumb]
            [cljs-repl-web.console.cljs :as cljs]
            [cljs-repl-web.console :as console]
            [cljs-repl-web.app :as app]
            [cljs-repl-web.views.utils :as utils]
            [cljs-repl-web.gist :as gist]))

(def initial-state {:consoles {}
                    :gist-data {:gist-showing? false
                                :auth-data {:username "" :password ""}}})

;; TODO Middleware


;;;;;;;;;;;;;;;;;;;;;;
;;;    Console     ;;;
;;;;;;;;;;;;;;;;;;;;;;

(register-handler
 :reset-db
 (fn [_ _]
   initial-state))

;; On app startup, create initial state
(register-handler
 :initialize
 (fn [_ _]
   (println "Initializing app...")
   initial-state))

(register-handler
 :add-console
 (fn [db [_ console-key console]]
   (assoc-in db [:consoles (name console-key)] {:console console
                                                :empty?  true})))

(register-handler
 :text-added-to-console
 (fn [db [_ console-key console]]
   (assoc-in db [:consoles (name console-key) :empty?] false)))


;;;;;;;;;;;;;;;;;;;;;;;
;;; Console buttons ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-handler
 :clear-console
 (fn [db [_ console-key]]
   (let [console (app/console db console-key)]
     (cljs/cljs-clear-console! console))
   (assoc-in db [:consoles (name console-key) :empty?] true)))

(register-handler
 :reset-console
 (fn [db [_ console-key]]
   (let [console (app/console db console-key)]
     (cljs/cljs-reset-console-and-prompt! console))
   (assoc-in db [:consoles (name console-key) :empty?] true)))

;;;;;;;;;;;;;;;;;;
;;     Gist    ;;;
;;;;;;;;;;;;;;;;;;

(register-handler
 :show-gist-login
 (fn [db [_]]
   (let [auth-data (app/gist-auth-data db)
         save-auth-data (app/gist-save-auth-data db)]
     (assoc db :gist-data {:gist-showing? true
                           :auth-data (assoc auth-data :password "")
                           :save-auth-data auth-data}))))

(register-handler
 :hide-gist-login
 (fn [db [_]]
   (let [save-auth-data (app/gist-save-auth-data db)]
         (assoc db :gist-data {:gist-showing? false
                               :auth-data save-auth-data}))))

(register-handler
 :create-gist
 (fn [db [_ console-key auth-data success-handler error-handler]]
   (let [{:keys [username password]} @auth-data
         console (app/console db console-key)
         text (console/dump-console! console)
         empty-usr? (empty? username)
         empty-pwd? (empty? password)
         empty-txt? (empty? text)
         valid (not (or empty-usr? empty-pwd? empty-txt?))]
     
     (if valid
       (gist/create-gist username password text success-handler error-handler))

     (assoc db :gist-data {:gist-showing? false
                           :auth-data @auth-data
                           :save-auth-data @auth-data}))))

(register-handler
 :reset-gist-error-msg
 (fn [db [_]]
   (assoc-in db [:gist-data :error-msg] "")))

(register-handler
 :set-gist-error-msg
 (fn [db [_ msg]]
   (assoc-in db [:gist-data :error-msg] msg)))

;;;;;;;;;;;;;;;;;;;;;;;
;;;     Popover     ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-handler
 :send-to-console
 (fn [db [_ console-key lines]]
   (let [console (app/console db console-key)
         lines   (filter #(re-seq #"^[^;]" (clojure.string/trim %)) lines)]
     (utils/scroll-to-top) ; in case we are at the bottom of the page
     (console/set-prompt-text! console (first lines))
     (console/focus-console! console)
     (assoc-in db [:consoles (name console-key) :interactive-examples] (rest lines)))))

(register-handler
 :delete-first-example
 (fn [db [_ console-key]]
   (let [examples (app/interactive-examples db console-key)]
     (assoc-in db [:consoles (name console-key) :interactive-examples] (drop 1 examples)))))

(register-handler
 :exit-interactive-examples
 (fn [db [_ console-key]]
   (let [console (app/console db console-key)]
     (console/set-prompt-text! console "")
     (console/focus-console! console)
     (assoc-in db [:consoles (name console-key) :interactive-examples] []))))
