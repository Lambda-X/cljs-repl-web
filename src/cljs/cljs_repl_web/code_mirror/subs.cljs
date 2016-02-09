(ns cljs-repl-web.code-mirror.subs
  (:require
   [re-frame.core :refer [register-sub]]
   [clairvoyant.core :refer-macros [trace-forms]]
   [re-frame-tracer.core :refer [tracer]]
   [cljs-repl-web.code-mirror.app :as app])
  (:require-macros
   [reagent.ratom :refer [reaction]]))

;; (trace-forms {:tracer (tracer :color "brown")}

(register-sub
 :console-created?
 (fn [db [_ console-key]]
   (reaction (app/console-created? @db console-key))))

(register-sub
 :get-console-items
 (fn [db [_ console-key]]
   (reaction (app/console-items @db console-key))))

(register-sub
 :get-console-current-text
 (fn [db [_ console-key]]
   (let [idx (reaction (get-in @db [:consoles (name console-key) :hist-pos]))
         history (reaction (get-in @db [:consoles (name console-key) :history]))]
     (reaction (let [items @history
                     pos (- (count items) @idx 1)]
                 (get items pos))))))

(register-sub
 :get-console
 (fn [db [_ console-key]]
   (reaction (app/console @db console-key))))

(register-sub
 :queued-forms-empty?
 (fn [db [_ console-key]]
   (reaction (not (empty? (app/queued-forms @db console-key))))))


;; )
