(ns ^:figwheel-load cljs-bootstrap.repl-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.repl :as repl]
            [cljs-bootstrap.common :as common]))

(deftest current-ns
  (is (symbol? (repl/current-ns)) "The current ns should be a symbol"))

(deftest process-pst
  (let [[success error] (do (repl/read-eval-print {} common/echo-callback "(throw (ex-info \"Exception\" {:tag :exception}))")
                            (repl/read-eval-print {} common/echo-callback "*e"))]
    (is success "The evaluation of *e should return successfully")
    (is (string? error) "The evaluation of *e should return a string")
    (is (not (common/inline-newline? error)) "The evaluation of *e should not be a pr-str of a string")
    (is (re-find #"Exception" error) "The evaluation of *e should return the correct message")
    (common/reset-errors))

  ;; This test fails in phanthomjs, but is correctly handled inside the browser
  (let [[success trace] (do (repl/read-eval-print {} common/echo-callback "(throw (ex-info \"Exception\" {:tag :exception}))")
                            (repl/read-eval-print {} common/echo-callback "(pst)"))]
    (is success "(pst) with previous error should return successfully")
    (is (string? trace) "(pst) with previous error should return a string")
    (is (not (common/inline-newline? trace)) "(pst) with previous error should not be a pr-str of a string")
    (is (re-find #"Exception" trace) "(pst) with previous error should return the trace as string")
    (is (> (count (clojure.string/split trace #"\s*at")) 1) "(pst) with previous error should more than one \"at ...\", aka the stacktrace")
    (common/reset-errors))

  (let [[success trace] (repl/read-eval-print {} common/echo-callback "(pst)")]
    (is success "(pst) with no error should return successfully")
    (is (string? trace) "(pst) with no error should return a string")
    (is (not (common/inline-newline? trace)) "(pst) with no error should not be a pr-str of a string")
    (is (= "nil" trace) "(pst) with no error should return nil")
    (common/reset-errors)))

(deftest process-doc
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(doc 'println)")]
    (is (not success) "In-ns but no symbol should NOT succeed") "Doc but no symbol should have correct error")
  (let [[success result] (repl/read-eval-print {} common/echo-callback "(doc println)")]
    (is success "(doc symbol) should succeed")
    (is (string? result) "(doc symbol) should return a string")
    (is (not (common/inline-newline? result)) "(doc symbol) should not be a pr-str of a string")
    ;; Cannot test #"cljs.core\/println" because of a compilation bug?
    (is (re-find #"cljs\.core.{1}println" result) "Doc with symbol should return nil"))
  (common/reset-errors))

(deftest process-in-ns
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(in-ns \"my.first.namespace\")")]
    (is (not success) "In-ns but no symbol should NOT succeed")
    (is (= "Argument to in-ns must be a symbol" (.-message error))) "In-ns but no symbol should have correct error")
  (let [[success result] (repl/read-eval-print {} common/echo-callback "(in-ns 'my.second.namespace)")]
    (is success "(in-ns 'symbol) should succeed")
    (is (string? result) "(in-ns 'symbol) should return a string")
    (is (not (common/inline-newline? result)) "(in-ns 'symbol) should not be a pr-str of a string")
    (is (= "nil" result)) "(in-ns 'symbol) should return nil"

    ;; Note that (do (in-ns 'my.namespace) (def a 3) (in-ns 'cljs) my.namespace/a)
    ;; Does not work in ClojureScript!
    (let [[success result] (do (repl/read-eval-print {} common/echo-callback "(in-ns 'first.namespace)")
                               (repl/read-eval-print {} common/echo-callback "(def a 3)")
                               (repl/read-eval-print {} common/echo-callback "(in-ns 'second.namespace)")
                               (repl/read-eval-print {} common/echo-callback "first.namespace/a"))]
      (is (= "3" result))) "Defining in ns should intern persistent var")
  (common/reset-errors)
  (common/reset-namespace))
