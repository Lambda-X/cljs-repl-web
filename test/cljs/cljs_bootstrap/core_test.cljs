(ns ^:figwheel-load cljs-bootstrap.core-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.core :as core]
            [cljs-bootstrap.common :as common]))

(deftest prompt
  (is (not (empty? (re-seq #"=>" (core/get-prompt))))))

(deftest real-eval-print
  (is (common/success? (core/read-eval-print common/echo-callback "(+ 1 1)")))
  (is (= "2" (common/result (core/read-eval-print common/echo-callback "(+ 1 1)"))))
  (is (not (common/success? (core/read-eval-print common/echo-callback "3("))))
  (common/reset-errors))
