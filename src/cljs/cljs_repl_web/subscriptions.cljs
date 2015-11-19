(ns cljs-repl-web.subscriptions
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]
            [cljs-repl-web.app :as app]))

;;;;;;;;;;;;;;;;;;
;;   Console   ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
  :console-created?
  (fn [db [_ console-key]]
    (reaction (app/console-created? @db console-key))))

(register-sub
  :get-console
  (fn [db [_ console-key]]
    (reaction (app/console @db console-key))))

(register-sub
  :is-console-empty?
  (fn [db [_ console-key]]
    (reaction (app/is-console-empty? @db console-key))))

;;;;;;;;;;;;;;;;;;
;;   Examples  ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :get-next-example
 (fn [db [_ console-key]]
   (reaction (first (app/interactive-examples @db console-key)))))

(register-sub
 :example-mode?
 (fn [db [_ console-key]]
   (reaction (not (empty? (app/interactive-examples @db console-key))))))

;;;;;;;;;;;;;;;;;;
;;     Gist    ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :gist-showing?
 (fn [db [_]]
   (reaction (app/gist-showing? @db))))

(register-sub
 :gist-auth-data
 (fn [db [_]]
   (reaction (app/gist-auth-data @db))))

(register-sub
 :gist-save-auth-data
 (fn [db [_]]
   (reaction (app/gist-save-auth-data @db))))

(register-sub
 :gist-error-msg
 (fn [db [_]]
   (reaction (app/gist-error-msg @db))))
