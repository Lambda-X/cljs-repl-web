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
    (is (common/valid-eval-result? error) "The evaluation of *e should be a valid result")
    (is (re-find #"Exception" error) "The evaluation of *e should return the correct message")
    (common/reset-errors))

  ;; This test fails in phanthomjs, but is correctly handled inside the browser
  (let [[success trace] (do (repl/read-eval-print {} common/echo-callback "(throw (ex-info \"Exception\" {:tag :exception}))")
                            (repl/read-eval-print {} common/echo-callback "(pst)"))]
    (is success "(pst) with previous error should return successfully")
    (is (common/valid-eval-result? trace) "(pst) with previous error should be a valid result")
    (is (re-find #"cljs\$core\$ExceptionInfo" trace) "(pst) with previous error should return the trace as string")
    (common/reset-errors))

  (let [[success trace] (repl/read-eval-print {} common/echo-callback "(pst)")]
    (is success "(pst) with no error should return successfully")
    (is (common/valid-eval-result? trace) "(pst) with no error should be a valid result")
    (is (= "nil" trace) "(pst) with no error should return nil")
    (common/reset-errors)))

(deftest process-doc
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(doc 'println)")]
    (is (not success) "(doc 'symbol) should have correct error")
    (common/reset-errors))
  (let [[success result] (repl/read-eval-print {} common/echo-callback "(doc println)")]
    (is success "(doc symbol) should succeed")
    (is (common/valid-eval-result? result) "(doc symbol) should be a valid result")
    ;; Cannot test #"cljs.core\/println" because of a compilation bug?
    (is (re-find #"cljs\.core.{1}println" result) "(doc symbol) should return valid docstring")
    (common/reset-errors)))

(deftest process-in-ns
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(in-ns \"first.namespace\")")]
    (is (not success) "(in-ns \"string\") should NOT succeed")
    (is (= "Argument to in-ns must be a symbol" (common/extract-message error)) "(in-ns \"string\") should have correct error")
    (common/reset-errors))
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(in-ns first.namespace)")]
    (is (not success) "(in-ns symbol) should NOT succeed")
    ;; Weird, but at least gives off an error
    (is (or (re-find #"is not defined" (common/extract-message error))
            (re-find #"Argument to in-ns must be a symbol" (common/extract-message error)) ) "(in-ns symbol) should have correct error")
    (common/reset-errors))
  (let [[success result] (repl/read-eval-print {} common/echo-callback "(in-ns 'first.namespace)")]
    (is success "(in-ns 'symbol) should succeed")
    (is (common/valid-eval-result? result) "(in-ns 'symbol) should be a valid result")
    (is (= "nil" result) "(in-ns 'symbol) should return nil")

    ;; Note that (do (in-ns 'my.namespace) (def a 3) (in-ns 'cljs) my.namespace/a)
    ;; Does not work in ClojureScript!
    (let [[success result] (do (repl/read-eval-print {} common/echo-callback "(in-ns 'first.namespace)")
                               (repl/read-eval-print {} common/echo-callback "(def a 3)")
                               (repl/read-eval-print {} common/echo-callback "(in-ns 'second.namespace)")
                               (repl/read-eval-print {} common/echo-callback "first.namespace/a"))]
      (is (= "3" result) "Defining in ns should intern persistent var"))
    (common/reset-errors)
    (common/reset-namespace)))

(deftest process-ns
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(ns 'first.namespace)")]
    (is (not success) "(ns 'something) should NOT succeed")
    (is (re-find #"Namespaces must be named by a symbol" (common/extract-message error)) "(ns 'something) should have correct error")
    (common/reset-errors))
  (let [[success result] (repl/read-eval-print {} common/echo-callback "(ns first.namespace)")]
    (is success "(ns something) should succeed")
    (is (common/valid-eval-result? result) "(ns something) should be a valid result")
    (is (= "nil" result) "(ns something) should return \"nil\"")
    (common/reset-errors)
    (common/reset-namespace)))

(deftest process-require
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(require first.namespace)")]
    (is (not success) "(require something) should NOT succeed")
    (is (re-find #"first.namespace is not ISeqable" (common/extract-message error)) "(require something) should have correct error")
    (common/reset-errors))
  (let [[success error] (repl/read-eval-print {} common/echo-callback "(require \"first.namespace\")")]
    (is (not success) "(require \"something\") should NOT succeed")
    (is (re-find #"ibrary name must be specified as a symbol" (common/extract-message error)) "(require \"something\") should have correct error")
    (common/reset-errors))
  (let [[success value] (repl/read-eval-print {} common/echo-callback "(require 'first.namespace.com)")]
    (is success "(require 'something) should succeed")
    (is (common/valid-eval-result? value) "(require 'something) should be a valid result")
    (is (= "nil" value) "(require 'something) should return nil")
    (common/reset-errors)
    (common/reset-namespace))

  (let [[success result] (do (repl/read-eval-print {} common/echo-callback "(ns first.namespace)")
                             (repl/read-eval-print {} common/echo-callback "(def a 3)")
                             (repl/read-eval-print {} common/echo-callback "(ns second.namespace)")
                             (repl/read-eval-print {} common/echo-callback "(require 'first.namespace)"))]
    (is success "(require 'first.namespace) from second.namespace should succeed")
    (is (common/valid-eval-result? result) "(require 'first.namespace) from second.namespace should be a valid result")
    (is (= 'second.namespace (repl/current-ns)) "(require 'first.namespace) from second.namespace should not change namespace")
    (common/reset-namespace))

  (let [[success result] (do (repl/read-eval-print {} common/echo-callback "(ns first.namespace)")
                             (repl/read-eval-print {} common/echo-callback "(def referred-a 3)")
                             (repl/read-eval-print {} common/echo-callback "(ns second.namespace)")
                             (repl/read-eval-print {} common/echo-callback "(require '[first.namespace :refer [referred-a]])")
                             (repl/read-eval-print {} common/echo-callback "referred-a"))]
    (is success "(require '[first.namespace :refer [referred-a]]) should succeed")
    (is (common/valid-eval-result? result) "(require '[first.namespace :refer [referred-a]]) should have a valid result")
    (is (= 'second.namespace (repl/current-ns)) "(require '[first.namespace :refer [referred-a]]) should not change namespace")
    (is (= "3" result) "(require '[first.namespace :refer [referred-a]]) should intern persistent var")
    (common/reset-namespace)))
