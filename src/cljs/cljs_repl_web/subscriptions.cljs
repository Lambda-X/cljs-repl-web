(ns cljs-repl-web.subscriptions
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]
            [cljs-repl-web.app :as app]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Subscription handlers ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(register-sub
  :console-created?
  (fn [db [_ console-key]]
    (reaction (app/console-created? @db console-key))))

(register-sub
  :get-console
  (fn [db [_ console-key]]
    (reaction (app/console @db console-key))))

(register-sub
 :get-next-example
 (fn [db [_ console-key]]
   (reaction (first (app/interactive-examples @db console-key)))))

(register-sub
 :example-mode?
 (fn [db [_ console-key]]
   (reaction (not (empty? (app/interactive-examples @db console-key))))))
