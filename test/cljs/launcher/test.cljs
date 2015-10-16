(ns ^:figwheel-always launcher.test
  (:require [cljs.test :refer-macros [run-tests run-all-tests]]
            [cljs-browser-repl.console-test]
            [cljs-bootstrap.core-test]
            [cljs-bootstrap.repl-test]))

(enable-console-print!)

(def success 0)

(defn ^:export run
  []
  (run-all-tests #"^cljs.*-test")
  success)
