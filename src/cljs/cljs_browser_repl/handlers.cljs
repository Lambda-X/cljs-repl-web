(ns cljs-browser-repl.handlers
  (:require [re-frame.core :refer [register-handler]]))

(def initial-state {:consoles {}})

;; TODO Middleware


;;;;;;;;;;;;;;;;;;;;;;
;;; Event Handlers ;;;
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
   (assoc-in db [:consoles (name console-key)] console)))
