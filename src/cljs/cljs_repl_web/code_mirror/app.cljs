(ns cljs-repl-web.code-mirror.app)

(def initial-console-state {:items []
                            :hist-pos 0
                            :queued-forms []
                            :history [""]
                            :cm-instance nil})

(defn console
  [db k]
  (get-in db [:consoles (name k) :cm-instance]))

(def console-created?
  "Was the console created? Returns a truey or falsey value."
  console)

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
