(ns cljs-bootstrap.core
  (:require-macros [cljs.env.macros :refer [with-compiler-env]])
  (:require [cljs.js :as cljs]
            [cljs-bootstrap.repl :as repl]))

(defn echo-result
  "Echoes the input success and result, returning [success,
  result]. Useful for debugging."
  [success result]
  [success result])

(def result "Returns the result of an evaluation" second)

(def success? "Returns if the evaluation was successful" first)

(defn ^:extern init-repl
  "The init-repl function accepts a map of options. Currently supported:

   * :verbose Switches verbose mode when true
  "
  ([] (init-repl {}))
  ([opts]
   (set! *target* "default")
   ;; Options
   (swap! repl/app-env assoc :verbose (:verbose opts))
   ;; Create cljs.user
   (if (= *target* "nodejs")
     (set! (.. js/global -cljs -user) #js {})
     (set! (.. js/window -cljs -user) #js {}))))

(defn ^:export read-eval-print
  "Reads evaluates and prints the input source. The second parameter,
  eval-callback, is a function (fn [success, result] ...) where success
  is a boolean indicating if everything went right and result will
  contain the actual result of the evaluation or an error map.

  The last parameter is a map of configuration options, currently
  supporting:
  * :redirect-out-to-string does what it says if true
  "
  ([source callback] (repl/read-eval-print source {} callback))
  ([source callback opts] (repl/read-eval-print source opts callback)))

(defn ^:export get-prompt []
  (str (repl/current-ns) "=> "))
