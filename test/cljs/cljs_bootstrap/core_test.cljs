(ns ^:figwheel-load cljs-bootstrap.core-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.core :as core]
            [cljs-bootstrap.common :as common]))

(def empty-err {})
(def single-err (ex-info "Could not eval -)" {:tag :cljs/reader-exception}))
(def nested-err (ex-info "Could not eval -)"
                         {:tag :cljs/analysis-error}
                         (ex-info "Unmatched delimiter )" {:type :reader-exception, :line 1, :column 3, :file "-"})))

(deftest prompt
  (is (not (empty? (re-seq #"=>" (core/get-prompt))))))

(deftest extract-message
  (let [msg (core/extract-message single-err)]
    (is (= "Could not eval -)" msg))
    (is (string? msg)))
  (let [msg (core/extract-message nested-err)]
    (is (= "Could not eval -) - Unmatched delimiter )" msg))
    (is (string? msg)))
  (let [msg (core/extract-message empty-err)]
    (is (= "" msg))
    (is (string? msg))))

(deftest real-eval-print
  (is (common/success? (core/read-eval-print common/echo-callback "(+ 1 1)")))
  (is (= "2" (common/result (core/read-eval-print common/echo-callback "(+ 1 1)"))))
  (is (not (common/success? (core/read-eval-print common/echo-callback "3("))))
  (common/reset-errors))
