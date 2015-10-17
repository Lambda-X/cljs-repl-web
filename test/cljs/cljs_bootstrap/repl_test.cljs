(ns ^:figwheel-load cljs-bootstrap.repl-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.repl :as repl]
            [cljs-bootstrap.common :as common]))

(deftest current-ns
  (is (symbol? (repl/current-ns)) "The current ns should be a symbol"))

(deftest process-doc
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(doc 'println)")]
    (is (not success) "In-ns but no symbol should NOT succeed") "Doc but no symbol should have correct error")
  (let [[success result] (repl/read-eval-print {} common/echo-callback "(doc println)")]
    (is success "Doc with symbol should succeed")
    ;; Cannot test #"cljs.core\/println" because of a compilation bug?
    (is (re-find #"cljs\.core.{1}println" result) "Doc with symbol should return nil")))

(deftest process-in-ns
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(in-ns \"my.first.namespace\")")]
    (is (not success) "In-ns but no symbol should NOT succeed")
    (is (= "Argument to in-ns must be a symbol" (.-message error))) "In-ns but no symbol should have correct error")
  (let [[success result] (repl/read-eval-print {} common/echo-callback "(in-ns 'my.second.namespace)")]
    (is success "In-ns with symbol should succeed")
    (is (= "nil" result)) "In-ns with symbol should return nil"

    ;; Note that (do (in-ns 'my.namespace) (def a 3) (in-ns 'cljs) my.namespace/a)
    ;; Does not work!
    (let [[success result] (do (repl/read-eval-print {} common/echo-callback "(in-ns 'first.namespace)")
                               (repl/read-eval-print {} common/echo-callback "(def a 3)")
                               (repl/read-eval-print {} common/echo-callback "(in-ns 'second.namespace)")
                               (repl/read-eval-print {} common/echo-callback "first.namespace/a"))]
      (is (= "3" result))) "Defining in ns should intern persistent var"))

(deftest repl-test
  (current-ns)
  (process-in-ns)
  (process-doc))
