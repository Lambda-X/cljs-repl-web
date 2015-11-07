(ns cljs-browser-repl.subscriptions
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]
            [cljs-browser-repl.app :as app]))

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
