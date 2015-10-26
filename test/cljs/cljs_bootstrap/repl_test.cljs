(ns ^:figwheel-load cljs-bootstrap.repl-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.repl :as repl]
            [cljs-bootstrap.core :as core :refer [success? unwrap-result]]
            [cljs-bootstrap.common :as common :refer [echo-callback valid-eval-result?
                                                      extract-message valid-eval-error?]]))
(defn reset-env
  []
  (repl/read-eval-call {} identity "(set! *e nil)")
  (repl/read-eval-call {} identity "(in-ns 'cljs.user)"))


(deftest init
  ;; This test heavily relies on repl execution order. If the repl is already
  ;; initialized before this point this test will fail. It is a good idea not
  ;; to put repl tests in other places other then this file or force test execution
  ;; order if this happens. At the moment it is disabled
  ;; (is (not (:initializing? @repl/app-env)) "Flag :initializing? should be false before init")
  ;; (is (:needs-init? @repl/app-env) "Flag :needs-init? should be true before init")
  (let [res (repl/read-eval-call {} echo-callback "(def c 4)")]
    (is (success? res) "Init should return successfully")
    (is (not (:initializing? @repl/app-env)) "Flag :initializing? should be false when the init exits")
    (is (not (:needs-init? @repl/app-env)) "Flag :needs-init? should be false when the init exits")
    (reset-env)))

(deftest current-ns
  (is (symbol? (repl/current-ns)) "The current ns should be a symbol"))

(deftest process-pst
  (let [res (do (repl/read-eval-call {} echo-callback "(throw (ex-info \"Exception\" {:tag :exception}))")
                (repl/read-eval-call {} echo-callback "*e"))
        error (unwrap-result res)]
    (is (success? res) "Eval of *e with error should return successfully")
    (is (valid-eval-result? error) "Eval of *e with error should be a valid error")
    (is (re-find #"xception" error) "Eval of *e with error should return the correct message")
    (reset-env))
  ;; This test fails in phanthomjs, but is correctly handled inside the browser
  (let [res (do (repl/read-eval-call {} echo-callback "(throw (ex-info \"Exception\" {:tag :exception}))")
                (repl/read-eval-call {} echo-callback "(pst)"))
        trace (unwrap-result res)]
    (is (success? res) "(pst) with previous error should return successfully")
    (is (valid-eval-result? trace) "(pst) with previous error should be a valid result")
    (is (re-find #"cljs\$core\$ExceptionInfo" trace) "(pst) with previous error should return the trace as string")
    (reset-env))
  (let [res (repl/read-eval-call {} echo-callback "(pst)")
        trace (unwrap-result res)]
    (is (success? res) "(pst) with no error should return successfully")
    (is (valid-eval-result? trace) "(pst) with no error should be a valid result")
    (is (= "nil" trace) "(pst) with no error should return nil")
    (reset-env)))

(deftest process-doc
  (let [res (repl/read-eval-call {} echo-callback "(doc 'println)")
        error (unwrap-result res)]
    (is (not (success? res)) "(doc 'symbol) should have correct error")
    (is (valid-eval-error? error) "(doc 'symbol) should result in an js/Error")
    (reset-env))
  (let [res (repl/read-eval-call {} echo-callback "(doc println)")
        docstring (unwrap-result res)]
    (is (success? res) "(doc symbol) should succeed")
    (is (valid-eval-result? docstring) "(doc symbol) should be a valid result")
    ;; Cannot test #"cljs.core\/println" because of a compilation bug?
    (is (re-find #"cljs\.core.{1}println" docstring) "(doc symbol) should return valid docstring")
    (reset-env)))

(deftest process-in-ns
  (let [res (repl/read-eval-call {} echo-callback "(in-ns \"first.namespace\")")
        error (unwrap-result res)]
    (is (not (success? res)) "(in-ns \"string\") should NOT succeed")
    (is (valid-eval-error? error) "(in-ns \"string\")  should result in an js/Error")
    (is (= "Argument to in-ns must be a symbol" (extract-message error)) "(in-ns \"string\") should have correct error")
    (reset-env))
  (let [res (repl/read-eval-call {} echo-callback "(in-ns first.namespace)")
        error (unwrap-result res)]
    (is (not (success? res)) "(in-ns symbol) should NOT succeed")
    (is (valid-eval-error? error) "(in-ns symbol)  should result in an js/Error")
    ;; Weird, but at least gives off an error
    (is (or (re-find #"is not defined" (extract-message error))
            (re-find #"Argument to in-ns must be a symbol" (extract-message error)) ) "(in-ns symbol) should have correct error")
    (reset-env))
  (let [res (repl/read-eval-call {} echo-callback "(in-ns 'first.namespace)")
        out (unwrap-result res)]
    (is (success? res) "(in-ns 'symbol) should succeed")
    (is (valid-eval-result? out) "(in-ns 'symbol) should be a valid result")
    (is (= "nil" out) "(in-ns 'symbol) should return nil")
    (reset-env))

  ;; Note that (do (in-ns 'my.namespace) (def a 3) (in-ns 'cljs) my.namespace/a)
  ;; Does not work in ClojureScript!
  (let [res (do (repl/read-eval-call {} echo-callback "(in-ns 'first.namespace)")
                (repl/read-eval-call {} echo-callback "(def a 3)")
                (repl/read-eval-call {} echo-callback "(in-ns 'second.namespace)")
                (repl/read-eval-call {} echo-callback "first.namespace/a"))
        out (unwrap-result res)]
    (is (success? res) "Defining variable in namespace and querying it should succeed")
    (is (= "3" out) "Defining variable in namespace and querying should intern persistent var")
    (reset-env)))

(deftest process-ns
  (let [res (repl/read-eval-call {} echo-callback "(ns 'first.namespace)")
        error (unwrap-result res)]
    (is (not (success? res)) "(ns 'something) should NOT succeed")
    (is (valid-eval-error? error) "(ns 'something) should result in an js/Error")
    (is (re-find #"Namespaces must be named by a symbol" (extract-message error)) "(ns 'something) should have correct error")
    (reset-env))
  (let [res (repl/read-eval-call {} echo-callback "(ns first.namespace)")
        out (unwrap-result res)]
    (is (success? res) "(ns something) should succeed")
    (is (valid-eval-result? out) "(ns something) should be a valid result")
    (is (= "nil" out) "(ns something) should return \"nil\"")
    (reset-env)))

(deftest process-require
  (let [res (repl/read-eval-call {} echo-callback "(require something)")
        error (unwrap-result res)]
    (is (not (success? res)) "(require something) should NOT succeed")
    (is (valid-eval-error? error) "(require something) should result in an js/Error")
    (is (re-find #"is not ISeqable" (extract-message error)) "(require something) should have correct error")
    (reset-env))
  (let [res (repl/read-eval-call {} echo-callback "(require \"something\")")
        error (unwrap-result res)]
    (is (not (success? res)) "(require \"something\") should NOT succeed")
    (is (valid-eval-error? error) "(require \"something\") should result in an js/Error")
    (is (re-find #"Argument to require must be a symbol" (extract-message error)) "(require \"something\") should have correct error")
    (reset-env))
  (let [res (repl/read-eval-call {} echo-callback "(require 'something.ns)")
        out (unwrap-result res)]
    (is (success? res) "(require 'something.ns) should succeed")
    (is (valid-eval-result? out) "(require 'something.ns) should be a valid result")
    (is (= "nil" out) "(require 'something.ns) should return nil")
    (reset-env))

  (let [res (do (repl/read-eval-call {} echo-callback "(ns a.ns)")
                (repl/read-eval-call {} echo-callback "(def a 3)")
                (repl/read-eval-call {} echo-callback "(ns b.ns)")
                (repl/read-eval-call {} echo-callback "(require 'a.ns)"))
        out (unwrap-result res)]
    (is (success? res) "(require 'a.ns) from b.ns should succeed")
    (is (valid-eval-result? out) "(require 'a.ns) from b.ns should be a valid result")
    (is (= 'b.ns (repl/current-ns)) "(require 'a.ns) from b.ns should not change namespace")
    (reset-env))

  (let [res (do (repl/read-eval-call {} echo-callback "(ns c.ns)")
                (repl/read-eval-call {} echo-callback "(def referred-a 3)")
                (repl/read-eval-call {} echo-callback "(ns d.ns)")
                (repl/read-eval-call {} echo-callback "(require '[c.ns :refer [referred-a]])")
                (repl/read-eval-call {} echo-callback "referred-a"))
        out (unwrap-result res)]
    (is (success? res) "(require '[c.ns :refer [referred-a]]) should succeed")
    (is (valid-eval-result? out) "(require '[c.ns :refer [referred-a]]) should have a valid result")
    (is (= 'd.ns (repl/current-ns)) "(require '[c.ns :refer [referred-a]]) should not change namespace")
    (is (= "3" out) "(require '[c.ns :refer [referred-a]]) should intern persistent var")
    (reset-env)))

(deftest warnings
  (let [results (atom [])
        swapping-callback (fn [r] (swap! results conj r))]
    (let [rs (repl/read-eval-call {} swapping-callback "_arsenununpa42")]
      (is (= 1 (count @results)) "Evaluating an undefined symbol should return one message only")
      (is (not (success? (first @results))) "Evaluating an undefined symbol should not succeed")
      (is (valid-eval-error? (unwrap-result (first @results))) "Evaluating an undefined symbol should result in an js/Error")
      (is #(re-find #"undeclared Var.*_arsenununpa42" (unwrap-result (first @results))) "Evaluating an undefined symbol should")
      (reset! results [])
      (reset-env))))
