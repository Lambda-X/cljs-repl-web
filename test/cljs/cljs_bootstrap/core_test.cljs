(ns ^:figwheel-load cljs-bootstrap.core-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.core :as core]
            [cljs-bootstrap.repl :as repl]))

(deftest prompt
  (is (not (empty? (re-seq #"=>" (core/get-prompt)))) "core/get-prompt should correcly return =>")
  (is (string? (core/get-prompt)) "core/get-prompt should be a string")
  (is (re-find (re-pattern (str (repl/current-ns))) (core/get-prompt)) "core/get-prompt should contain the current namespace"))
