(ns ^:figwheel-load cljs-bootstrap.common-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.common :as common :refer [extract-message]]))

(def empty-err {})
(def single-err #(ex-info "Could not eval -)" {:tag :cljs/reader-exception}))
(def nested-err #(ex-info "Could not eval -)"
                         {:tag :cljs/analysis-error}
                         (ex-info "Unmatched delimiter )" {:type :reader-exception, :line 1, :column 3, :file "-"})))

(deftest error-message
  (let [msg (extract-message (single-err))]
    (is (= "Could not eval -)" msg))
    (is (string? msg)))
  (let [msg (extract-message (nested-err))]
    (is (= "Could not eval -) - Unmatched delimiter )" msg))
    (is (string? msg)))
  (let [msg (extract-message empty-err)]
    (is (= "" msg))
    (is (string? msg))))
