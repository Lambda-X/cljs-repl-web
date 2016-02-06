(ns cljs-repl-web.code-mirror.app)

(def initial-console-state {:items []
                            :hist-pos 0
                            :history [""]
                            :cm-instance nil})

(defn console-instance
  [db k]
  (get-in db [:consoles (name k) :cm-instance]))

(defn console-items
  [db k]
  (get-in db [:consoles (name k) :items]))

(defn console-history
  [db k]
  (get-in db [:consoles (name k) :history]))

(defn console-history-pos
  [db k]
  (get-in db [:consoles (name k) :hist-pos]))
