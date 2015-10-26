(ns cljs-bootstrap.core
  (:require-macros [cljs.env.macros :refer [with-compiler-env]])
  (:require [cljs.js :as cljs]
            [cljs-bootstrap.repl :as repl]
            [cljs-bootstrap.common :as common]))

(defn ^:export read-eval-print
  "Reads evaluates and prints the input source.

  The first parameter is a map of configuration options that override
  the ones given in init-repl. The supported options are the same.

  The second parameter, callback, is a function (fn [success, result]
  ...) where success is a boolean indicating if everything went right
  and result will contain the actual result of the evaluation as string
  or the error as js/Error.

  It initializes the repl harness if necessary."
  ([callback source] (repl/read-eval-print {} callback source))
  ([opts callback source] (repl/read-eval-print opts callback source)))

(def ^:export rep
  "Reads evaluates and prints the input source.

  The first parameter is a map of configuration options that override
  the ones given in init-repl. The supported options are the same.

  The second parameter, callback, is a function (fn [success, result]
  ...) where success is a boolean indicating if everything went right
  and result will contain the actual result of the evaluation as string
  or the error as js/Error.

  It initializes the repl harness if necessary."
  read-eval-print)

(defn ^:export get-prompt []
  (str (repl/current-ns) "=> "))

(defn ^:export exception->str
  "Return the message string of the input exception."
  ([ex] (exception->str ex false))
  ([ex print-stack?]
   (str (common/extract-message ex)
        (when (and ex print-stack?)
          (str "\n" (.-stack ex))))))
