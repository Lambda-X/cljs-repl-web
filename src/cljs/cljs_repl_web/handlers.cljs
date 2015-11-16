(ns cljs-repl-web.handlers
  (:require [re-frame.core :refer [register-handler]]
            [replumb.core :as replumb]
            [cljs-repl-web.console.cljs :as cljs]
            [cljs-repl-web.console :as console]
            [cljs-repl-web.app :as app]))

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
     (.scrollTo js/window 0 0) ; in case we are at the bottom of the page
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
