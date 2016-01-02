(ns cljs-repl-web.subs
  (:require [reagent.ratom :refer [make-reaction]]
            [re-frame.core :refer [register-sub subscribe]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.app :as app]
            [cljs-repl-web.console :as console]
            [cljs-repl-web.views.utils :as view-utils]))

;; (trace-forms {:tracer (tracer :color "brown")}

;;;;;;;;;;;;;;;;;;
;;   Console   ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :console-created?
 (fn [db [_ console-id]]
   (make-reaction (fn console-created? []
                    (app/console-created? @db console-id)))))

(register-sub
 :get-console
 (fn [db [_ console-id]]
   (make-reaction (fn get-console []
                    (app/console @db console-id)))))

(register-sub
 :get-consoles
 (fn [db _]
   (make-reaction (fn []
                    (:consoles @db)))))

(register-sub
 :console-text
 (fn [db [_ console-id]]
   (make-reaction (fn console-text []
                    (app/console-text @db console-id)))))

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
;;   Examples  ;;;
;;;;;;;;;;;;;;;;;;

(register-sub
 :get-next-example
 (fn [db [_ console-key]]
   (make-reaction (fn get-next-example []
                    (first (app/interactive-examples @db console-key))))))

(register-sub
 :example-mode?
 (fn [db [_ console-id]]
   (make-reaction (fn example-mode? []
                    (not (empty? (app/interactive-examples @db console-id)))))))

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
 (fn [db [_ console-id]]
   (let [console (subscribe [:get-console console-id])
         console-text (subscribe [:console-text console-id])]
     (make-reaction (fn can-save-gist? []
                      (let [_ @console-text] ;; using this just as trigger
                        (some-> @console (console/dump-console!) empty?)))))))

;;;;;;;;;;;;;;;;;;;;;;;
;;  Media Queries   ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(register-sub
 :media-query-size
 (fn [db [_]]
   (make-reaction (fn media-query-size []
                    (app/media-query-size @db)))))

;; )
