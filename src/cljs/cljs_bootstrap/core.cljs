(ns cljs-bootstrap.core
  (:require-macros [cljs.env.macros :refer [with-compiler-env]])
  (:require [cljs.js :as cljs]
            [cljs-bootstrap.repl :as repl]
            [cljs-bootstrap.common :as common]))

(defn ^:extern init-repl
  "The init-repl function accepts a map of options. Currently supported:

   * :verbose Switches to verbose mode when true
  "
  ([] (init-repl {}))
  ([opts]
   (set! *target* "default")
   ;; Options
   (swap! repl/app-env assoc (repl/valid-opts opts))
   ;; Create cljs.user
   (set! (.. js/window -cljs -user) #js {})))

(defn ^:export read-eval-print
  "Reads evaluates and prints the input source. The second parameter,
  callback, is a function (fn [success, result] ...) where success is a
  boolean indicating if everything went right and result will contain
  the actual result of the evaluation or an error map.

  The first parameter is a map of configuration options that override
  the ones given in init-repl. The supported options are the same."
  ([callback source] (repl/read-eval-print {} callback source))
  ([opts callback source] (repl/read-eval-print opts callback source)))

(defn ^:export get-prompt []
  (str (repl/current-ns) "=> "))

(defn ^:export exception->str
  "Return the message string of the input exception."
  ([ex] (exception->str ex false))
  ([ex print-stack?]
   (str (common/extract-message ex) (when (and print-stack?
                                        (not (nil? (.-stack ex))))
                               (str "\n" (.-stack ex))))))
