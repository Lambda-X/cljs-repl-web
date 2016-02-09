(ns cljs-repl-web.subs
  (:require [reagent.ratom :refer [make-reaction]]
            [re-frame.core :refer [register-sub subscribe]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.app :as app]
            [cljs-repl-web.views.utils :as view-utils]))

;; (trace-forms {:tracer (tracer :color "brown")}

;;;;;;;;;;;;;;;;;;
;;     APIs    ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :api-panel-section-columns
 (fn [db [_ sections]]
   (let [mq (subscribe [:media-query-size])]
     (make-reaction (fn api-panel-section-columns []
                      (let [cols (view-utils/api-panel-column-number @mq)
                            secs (if-not (= :narrow @mq)
                                   sections
                                   (filter #(= :symbols (get-in % [:additional-info :type])) sections))]
                        (partition-all (let [secs (count sections)]
                                         (if (zero? (rem secs cols))
                                           (quot secs cols)
                                           (inc (quot secs cols))))
                                       secs)))))))

(register-sub
 :api-panel-column-number
 (fn [db [_]]
   (let [mq (subscribe [:media-query-size])]
     (make-reaction (fn api-panel-column-number []
                      (view-utils/api-panel-column-number @mq))))))

;;;;;;;;;;;;;;;;;;
;;   Footer    ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :footer-column-number
 (fn [db [_]]
   (let [mq (subscribe [:media-query-size])]
     (make-reaction (fn footer-column-number []
                      (view-utils/footer-column-number @mq))))))

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
 :gist-error-msg
 (fn [db [_]]
   (make-reaction (fn gist-error-msg []
                    (app/gist-error-msg @db)))))

(register-sub
 :can-dump-gist?
 (fn [db [_ console-key]]
   (let [items (subscribe [:get-console-items console-key])]
     (make-reaction (fn can-save-gist? [] (nil? (seq @items)))))))

;;;;;;;;;;;;;;;;;;;;;;;
;;  Media Queries   ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-sub
 :media-query-size
 (fn [db [_]]
   (make-reaction (fn media-query-size []
                    (app/media-query-size @db)))))

;; )
