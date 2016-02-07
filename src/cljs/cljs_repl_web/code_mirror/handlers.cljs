(ns cljs-repl-web.code-mirror.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.code-mirror.app :as app]
            [cljs-repl-web.code-mirror.replumb :as replumb-proxy]))

;; (trace-forms {:tracer (tracer :color "green")}

(register-handler
 :add-console
 (fn add-console [db [_ console-key inst]]
   (assoc-in db [:consoles (name console-key)]
             (assoc app/initial-console-state :cm-instance inst))))

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
   (assoc-in db [:consoles (name console-key) :items] [])))

(register-handler
 :reset-console-items
 (fn reset-console-items [db [_ console-key]]
   (dispatch [:focus-console-editor console-key])
   (update-in db
              [:consoles (name console-key)]
              (fn [current-state]
                (merge current-state
                       (select-keys app/initial-console-state [:items :hist-pos :history]))))))

(register-handler
 :add-console-item
 (fn add-console-item [db [_ console-key item]]
   (update-in db [:consoles (name console-key) :items] conj item)))

(register-handler
 :add-console-items
 (fn add-console-items [db [_ console-key items]]
   (update-in db [:consoles (name console-key) :items] concat items)))

(register-handler
 :add-console-input
 (fn add-console-input [db [_ console-key input ns]]
   (let [inum (count (app/console-history db console-key))]
     (-> db
         (assoc-in [:consoles (name console-key) :hist-pos] 0)
         (update-in [:consoles (name console-key) :history] conj "")
         (update-in [:consoles (name console-key) :items] conj {:type :input :text input :num inum :ns ns})))))

(register-handler
 :add-console-result
 (fn add-console-result [db [_ console-key error? value]]
   (update-in db [:consoles (name console-key) :items] conj {:type (if error? :error :output)
                                                             :value value})))

(register-handler
 :add-console-log
 (fn add-console-log [db [_ console-key item]]
   (update-in db [:consoles (name console-key) :items] conj {:type :log :value item})))

(register-handler
 :console-set-text
 (fn console-set-text [db [_ console-key text]]
   (let [history (app/console-history db console-key)
         pos (app/console-history-pos db console-key)
         idx (- (count history) pos 1)]
     (-> db
         (assoc-in [:consoles (name console-key) :hist-pos] 0)
         (assoc-in [:consoles (name console-key) :history]
                   (if (= pos 0)
                     (assoc history idx text)
                     (conj history text)))))))

(register-handler
 :console-go-up
 (fn console-go-up [db [_ console-key]]
   (let [pos (app/console-history-pos db console-key)
         len (count (app/console-history db console-key))
         new-pos (if (>= pos (dec len))
                   pos
                   (inc pos))]
     (assoc-in db [:consoles (name console-key) :hist-pos] new-pos))))

(register-handler
 :console-go-down
 (fn console-go-down [db [_ console-key]]
   (update-in db
              [:consoles (name console-key) :hist-pos]
              (fn [pos] (if (<= pos 0)
                          0
                          (dec pos))))))

(register-handler
 :set-console-queued-forms
 (fn set-queued-forms [db [_ console-key forms]]
   (dispatch [:console-set-text console-key (first forms)])
   (dispatch [:focus-console-editor console-key])
   (assoc-in db [:consoles (name console-key) :queued-forms] (rest forms))))

(register-handler
 :clear-console-queued-forms
 (fn clear-console-queued-forms [db [_ console-key]]
   (assoc-in db [:consoles (name console-key) :queued-forms] [])))

(register-handler
 :submit-source
 (fn submit-source [db [_  console-key execute-fn text]]
   (let [text (.trim @text)]
     (dispatch [:console-set-text console-key text])
     (dispatch [:add-console-input console-key text (replumb-proxy/current-ns)])
     (execute-fn text #(dispatch [:add-console-result console-key (not %1) %2]))

     (if-let [form (first (app/queued-forms db console-key))]
       (do
         (dispatch [:console-set-text console-key form])
         (update-in db [:consoles (name console-key) :queued-forms] (partial drop 1)))
       db))))

;; )
