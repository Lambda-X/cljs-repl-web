(ns cljs-repl-web.code-mirror.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.code-mirror.app :as app]))

;; (trace-forms {:tracer (tracer :color "green")}

(register-handler
 :add-console
 (fn add-console [db [_ console-key instance]]
   (app/add-console db console-key instance)))

(register-handler
 :focus-console-editor
 (fn focus-console-editor [db [_ console-key]]
   (when-let [instance (app/console db console-key)]
     (.focus instance))
   db))

(register-handler
 :clear-console-items
 (fn clear-console-items [db [_ console-key]]
   (dispatch [:focus-console-editor console-key])
   (app/clear-console-items db console-key)))

(register-handler
 :reset-console-items
 (fn reset-console-items [db [_ console-key]]
   (dispatch [:focus-console-editor console-key])
   (app/reset-console-items db console-key)))

(register-handler
 :add-console-item
 (fn add-console-item [db [_ console-key item]]
   (app/add-console-item db console-key item)))

(register-handler
 :add-console-items
 (fn add-console-items [db [_ console-key items]]
   (app/add-console-item db console-key items)))

(register-handler
 :add-console-input
 (fn add-console-input [db [_ console-key input ns]]
   (app/add-console-input db console-key input ns)))

(register-handler
 :add-console-result
 (fn add-console-result [db [_ console-key error? value]]
   (app/add-console-result db console-key error? value)))

(register-handler
 :add-console-log
 (fn add-console-log [db [_ console-key item]]
   (app/add-console-log db console-key item)))

(register-handler
 :console-set-text
 (fn console-set-text [db [_ console-key text]]
   (app/set-console-text db console-key text)))

(register-handler
 :console-go-up
 (fn console-go-up [db [_ console-key]]
   (app/console-go-up db console-key)))

(register-handler
 :console-go-down
 (fn console-go-down [db [_ console-key]]
   (app/console-go-down db console-key)))

(register-handler
 :set-console-queued-forms
 (fn set-queued-forms [db [_ console-key forms]]
   (dispatch [:focus-console-editor console-key])
   (app/set-console-queued-forms db console-key forms)))

(register-handler
 :clear-console-queued-forms
 (fn clear-console-queued-forms [db [_ console-key]]
   (app/clear-console-queued-forms db console-key)))

(register-handler
 :on-eval-complete
 (fn on-eval-complete [db [_  console-key result-map]]
   (app/on-eval-complete db console-key result-map)))

;; )
