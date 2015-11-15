(ns cljs-browser-repl.handlers
  (:require [re-frame.core :refer [register-handler]]
            [replumb.core :as replumb]
            [cljs-browser-repl.console.cljs :as cljs]
            [cljs-browser-repl.console :as console]
            [cljs-browser-repl.app :as app]))

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
   (assoc-in db [:consoles (name console-key) :console] console)))

(register-handler
 :send-to-console
 (fn [db [_ console-key lines]]
   (let [console (app/console db console-key)
         lines   (filter #(re-seq #"^[^;]" (clojure.string/trim %)) lines)]
     (console/set-prompt-text! console (first lines))
     (console/focus-console! console)
     (assoc-in db [:consoles (name console-key) :interactive-examples] (rest lines)))))

(register-handler
 :delete-first-example
 (fn [db [_ console-key]]
   (let [examples (app/interactive-examples db console-key)]
     (assoc-in db [:consoles (name console-key) :interactive-examples] (drop 1 examples)))))

