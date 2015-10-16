(ns ^:figwheel-load cljs-bootstrap.core-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs-bootstrap.core :as core]))

(deftest prompt
  (is (not (empty? (re-seq #"=>" (core/get-prompt))))))

(deftest real-eval-print
  (is (core/success? (core/read-eval-print "(+ 1 1)" core/echo-result)))
  (is (= "2" (core/result (core/read-eval-print "(+ 1 1)" core/echo-result))))
  (is (not (core/success? (core/read-eval-print "3(" core/echo-result)))))

(deftest core-test
  (prompt)
  (real-eval-print))
