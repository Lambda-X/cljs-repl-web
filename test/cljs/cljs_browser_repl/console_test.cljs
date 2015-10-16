(ns ^:figwheel-load cljs-browser-repl.console-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-browser-repl.console :as console]))

(def empty-err {})
(def single-err (ex-info "Could not eval -)" {:tag :cljs/reader-exception}))
(def nested-err (ex-info "Could not eval -)"
                         {:tag :cljs/analysis-error}
                         (ex-info "Unmatched delimiter )" {:type :reader-exception, :line 1, :column 3, :file "-"})))

(deftest console-test
  (let [msg (console/extract-message single-err)]
    (is (= "Could not eval -)" msg))
    (is (string? msg)))
  (let [msg (console/extract-message nested-err)]
    (is (= "Could not eval -) - Unmatched delimiter )" msg))
    (is (string? msg)))
  (let [msg (console/extract-message empty-err)]
    (is (= "" msg))
    (is (string? msg))))
