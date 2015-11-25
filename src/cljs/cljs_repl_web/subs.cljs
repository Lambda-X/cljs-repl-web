(ns cljs-repl-web.subs
  (:require [reagent.ratom :refer [make-reaction]]
            [re-frame.core :refer [register-sub subscribe]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.app :as app]))

;; (trace-forms {:tracer (tracer :color "brown")}

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
;;     APIs    ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :api-panel-section-columns
 (fn [db [_ sections]]
   (let [cols (subscribe [:api-panel-column-number])]
     (make-reaction (fn api-panel-section-columns []
                      (into [] (partition-all (let [secs (count sections)]
                                        (if (zero? (rem secs @cols))
                                          (quot secs @cols)
                                          (inc (quot secs @cols)))) sections)))))))

(register-sub
 :api-panel-column-number
 (fn [db [_]]
   (let [mq (subscribe [:media-query-size])]
     (make-reaction (fn api-panel-column-number []
                      (case @mq
                        :narrow 1
                        :medium 1
                        :wide 2))))))

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
;;   Footer    ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :footer-column-number
 (fn [db [_]]
   (let [mq (subscribe [:media-query-size])]
     (make-reaction (fn footer-column-number []
                      (case @mq
                        :narrow 1
                        :medium 1
                        :wide 2))))))
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
 :media-query-size
 (fn [db [_]]
   (make-reaction (fn media-query-size []
                    (app/media-query-size @db)))))

;; )
