(ns cljs-repl-web.code-mirror.subs
  (:require
   [re-frame.core :refer [register-sub]]
   [clairvoyant.core :refer-macros [trace-forms]]
   [re-frame-tracer.core :refer [tracer]])
  (:require-macros
   [reagent.ratom :refer [reaction]]))

;; (trace-forms {:tracer (tracer :color "brown")}

(register-sub
 :get-console-items
 (fn [db [_ console-key]]
   (reaction (get-in @db [:consoles (name console-key) :items]))))

(register-sub
 :get-console-current-text
 (fn [db [_ console-key]]
   (let [idx (reaction (get-in @db [:consoles (name console-key) :hist-pos]))
         history (reaction (get-in @db [:consoles (name console-key) :history]))]
     (reaction (let [items @history
                     pos (- (count items) @idx 1)]
                 (get items pos))))))

(register-sub
 :get-console-cm-instance
 (fn [db [_ console-key]]
   (reaction (get-in @db [:consoles (name console-key) :cm-instance]))))

;; )
