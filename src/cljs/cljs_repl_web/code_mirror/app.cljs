(ns cljs-repl-web.code-mirror.app)

(def initial-console-state {:items []
                            :hist-pos 0
                            :queued-forms []
                            :history [""]
                            :cm-instance nil})
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;           Getters           ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn console
  [db k]
  (get-in db [:consoles (name k) :cm-instance]))

(def console-created?
  "Was the console created? Returns a truey or falsey value."
  (comp not nil? console))

(defn console-items
  [db k]
  (get-in db [:consoles (name k) :items]))

(defn console-history
  [db k]
  (get-in db [:consoles (name k) :history]))

(defn console-history-pos
  [db k]
  (get-in db [:consoles (name k) :hist-pos]))

(defn queued-forms
  [db k]
  (get-in db [:consoles (name k) :queued-forms]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;       State modifiers       ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn add-console
  [db k instance]
  (assoc-in db [:consoles (name k)] (assoc initial-console-state :cm-instance instance)))

(defn clear-console-items
  [db k]
  (assoc-in db [:consoles (name k) :items] []))

(defn reset-console-items
  [db k]
  (update-in db [:consoles (name k)]
             (fn [current-state]
               (merge current-state
                      (select-keys initial-console-state [:items :hist-pos :history])))))

(defn add-console-item
  [db k item]
  (update-in db [:consoles (name k) :items] conj item))


(defn add-console-items
  [db k items]
  (update-in db [:consoles (name k) :items] concat items))

(defn set-console-history-position
  [db k new-pos]
  (assoc-in db [:consoles (name k) :hist-pos] new-pos))

(defn add-console-history-item
  [db k item]
  (update-in db [:consoles (name k) :history] conj item))

(defn add-console-input-item
  [db k inum input ns]
  (update-in db [:consoles (name k) :items] conj {:type :input
                                                  :text input
                                                  :num inum
                                                  :ns ns}))

(defn add-console-input
  [db k input ns]
  (let [inum (count (console-history db k))]
    (-> db
        (set-console-history-position k 0)
        (add-console-history-item k "")
        (add-console-input-item k inum input ns))))

(defn add-console-result
  [db k error? value]
  (update-in db [:consoles (name k) :items] conj {:type (if error? :error :output)
                                                  :value value}))

(defn add-console-log
  [db k item]
  (update-in db [:consoles (name k) :items] conj {:type :log
                                                  :value item}))

(defn update-console-history
  [db k idx pos text]
  (update-in db [:consoles (name k) :history]
             (fn [current-history]
               (if (= pos 0)
                 (assoc current-history idx text)
                 (conj current-history text)))))

(defn set-console-text
  [db k text]
  (let [history (console-history db k)
        pos (console-history-pos db k)
        idx (- (count history) pos 1)]
    (-> db
        (set-console-history-position k 0)
        (update-console-history k idx pos text))))

(defn console-go-up
  [db k]
  (let [pos (console-history-pos db k)
        len (count (console-history db k))
        new-pos (if (>= pos (dec len))
                  pos
                  (inc pos))]
    (set-console-history-position db k new-pos)))

(defn console-go-down
  [db k]
  (update-in db [:consoles (name k) :hist-pos]
             (fn [pos] (if (<= pos 0)
                        0
                        (dec pos)))))

(defn clear-console-queued-forms
  [db k]
  (assoc-in db [:consoles (name k) :queued-forms] []))

(defn set-console-queued-forms
  [db k forms]
  (-> db
      (set-console-text k (first forms))
      (assoc-in [:consoles (name k) :queued-forms] (rest forms))))

(defn drop-first-queued-form
  [db k]
  (update-in db [:consoles (name k) :queued-forms] (partial drop 1)))

(defn set-next-queued-form-if-any
  [db k]
  (if-let [form (first (queued-forms db k))]
       (-> db
           (set-console-text k form)
           (drop-first-queued-form k))
       db))

(defn on-eval-complete
  [db k {:keys [prev-ns source success? result]}]
  (-> db
       (set-console-text k source)
       (add-console-input k source prev-ns)
       (add-console-result k (not success?) result)
       (set-next-queued-form-if-any k)))
