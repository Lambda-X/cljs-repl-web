(ns ^:figwheel-load cljs-bootstrap.common-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.common :as common]))

(def empty-err {})
(def single-err #(ex-info "Could not eval -)" {:tag :cljs/reader-exception}))
(def nested-err #(ex-info "Could not eval -)"
                         {:tag :cljs/analysis-error}
                         (ex-info "Unmatched delimiter )" {:type :reader-exception, :line 1, :column 3, :file "-"})))

(deftest extract-message
  (let [msg (common/extract-message (single-err))]
    (is (= "Could not eval -)" msg))
    (is (string? msg)))
  (let [msg (common/extract-message (nested-err))]
    (is (= "Could not eval -) - Unmatched delimiter )" msg))
    (is (string? msg)))
  (let [msg (common/extract-message empty-err)]
    (is (= "" msg))
    (is (string? msg))))

(def successful-map {:success? true :value "This is a result"})
(def unsuccessful-map {:success? false :error (js/Error "This is an error")})

(deftest result-getters
  (is (common/success? successful-map))
  (is (common/valid-eval-result? (common/unwrap-result successful-map)))
  (is (not (common/valid-eval-error? (common/unwrap-result successful-map))))
  (is (= "This is a result" (common/unwrap-result successful-map)))
  (is (not (common/success? unsuccessful-map)))
  (is (not (common/valid-eval-result? (common/unwrap-result unsuccessful-map))))
  (is (common/valid-eval-error? (common/unwrap-result unsuccessful-map)))
  (is (= "This is an error" (common/extract-message (common/unwrap-result unsuccessful-map)))))
