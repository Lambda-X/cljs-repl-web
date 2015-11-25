(ns cljs-repl-web.subs
  (:require [reagent.ratom :refer [make-reaction]]
            [re-frame.core :refer [register-sub]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.app :as app]))

(trace-forms {:tracer (tracer :color "brown")}

;;;;;;;;;;;;;;;;;;
;;   Console   ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :console-created?
 (fn [db [_ console-key]]
   (make-reaction (fn console-created? []
                    (app/console-created? @db console-key)))))

(register-sub
 :get-console
 (fn [db [_ console-key]]
   (make-reaction (fn get-console []
                    (app/console @db console-key)))))

(register-sub
 :is-console-empty?
 (fn [db [_ console-key]]
   (make-reaction (fn is-console-empty? []
                    (app/is-console-empty? @db console-key)))))

;;;;;;;;;;;;;;;;;;
;;   Examples  ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :get-next-example
 (fn [db [_ console-key]]
   (make-reaction (fn get-next-example []
                    (first (app/interactive-examples @db console-key))))))

(register-sub
 :example-mode?
 (fn [db [_ console-key]]
   (make-reaction (fn example-mode? []
                    (not (empty? (app/interactive-examples @db console-key)))))))

;;;;;;;;;;;;;;;;;;
;;     Gist    ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :gist-showing?
 (fn [db [_]]
   (make-reaction (fn gist-showing? []
                    (app/gist-showing? @db)))))

(register-sub
 :gist-auth-data
 (fn [db [_]]
   (make-reaction (fn gist-auth-data []
                    (app/gist-auth-data @db)))))

(register-sub
 :gist-save-auth-data
 (fn [db [_]]
   (make-reaction (fn gist-save-auth-data []
                    (app/gist-save-auth-data @db)))))

(register-sub
 :gist-error-msg
 (fn [db [_]]
   (make-reaction (fn gist-error-msg []
                    (app/gist-error-msg @db)))))

;;;;;;;;;;;;;;;;;;;;;;;
;;  Media Queries   ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-sub
 :api-panel-columns
 (fn [db [_]]
   (make-reaction (fn api-panel-columns []
                    (app/api-panel-columns @db)))))

)
