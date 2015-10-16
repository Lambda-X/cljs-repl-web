(ns ^:figwheel-load cljs-bootstrap.repl-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.repl :as repl]))

(deftest current-ns
  (is (symbol? (repl/current-ns)) "The current ns should be a symbol"))

(deftest read-eval-print

  )

(deftest repl-test
  (current-ns))
