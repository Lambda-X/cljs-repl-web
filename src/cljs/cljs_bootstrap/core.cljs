(ns cljs-bootstrap.core
  (:require-macros [cljs.env.macros :refer [with-compiler-env]])
  (:require [cljs.js :as cljs]
            [cljs-bootstrap.repl :as repl]
            [cljs-bootstrap.common :as common]))

(defn ^:export read-eval-call
  "Reads, evaluates and calls back with the evaluation result.

  The first parameter is a map of configuration options, currently
  supporting:

  * `:verbose` will enable the the evaluation logging, defaults to false.

  The second parameter `cb`, should be a 1-arity function which receives
  the result map.

  Therefore, given callback = `(fn [result] ...)`, the main map keys are:

  ```
  { :success? ;; a boolean indicating if everything went right
    :value    ;; (if (success? result)) will contain the actual yield of the evaluation
    :error    ;; (if (not (success? result)) will contain a js/Error }
  ```

  It initializes the repl harness if necessary."
  ([callback source] (repl/read-eval-print {} callback source))
  ([opts callback source] (repl/read-eval-print opts callback source)))

(defn ^:export get-prompt
  "Retrieves the repl prompt to display, according to the current
  namespace. Returns a string."
  []
  (str (repl/current-ns) "=> "))

(defn ^:export exception->str
  "Return the message string of the input `js/Error`."
  ([error] (exception->str error false))
  ([error print-stack?]
   (str (common/extract-message ex)
        (when (and ex print-stack?)
          (str "\n" (.-stack ex))))))
