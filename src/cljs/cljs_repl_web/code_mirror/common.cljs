(ns cljs-repl-web.code-mirror.common)

(defn beginning-of-source
  [exp]
  (let [idx (.indexOf exp "=> ")]
    (if (not= -1 idx)
      (+ 3 (.indexOf exp "=> "))
      0)))

(defn source-without-prompt
  [exp]
  exp
  (subs exp (beginning-of-source exp)))

(defn scroll-to-el-bottom!
  [el]
  (set! (.-scrollTop el) (.-scrollHeight el)))

