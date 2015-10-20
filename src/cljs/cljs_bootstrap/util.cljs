(ns cljs-bootstrap.util
  (:require [cljs-bootstrap.repl :as repl]))

(defn echo-callback
  "Echoes the input success and result, returning [success,
  result]. Useful for debugging."
  [success result]
  [success result])

(def result "Returns the result of an evaluation" second)

(def success? "Returns if the evaluation was successful" first)

(def reset-errors
  "Evaluates (set! *e nil), resetting the current *e."
  #(repl/read-eval-print {} echo-callback "(set! *e nil)"))

(def reset-namespace
  "Evaluates (in-ns 'cljs.user), resetting the current namespace."
  #(repl/read-eval-print {} echo-callback "(in-ns 'cljs.user)"))
