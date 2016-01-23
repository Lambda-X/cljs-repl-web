(ns cljs-repl-web.core-test
  (:require [cljs.test :refer-macros [deftest is async]]
            [cljs.core.async :as async :refer [chan put! <!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(deftest sanity-check
  (is (= 1 1)))

;; AR - Straight from doo's examples
(deftest async-test
  (async done
    (let [a 1]
      (js/setTimeout (fn []
                       (is (= 1 a))
                       (done))
                     100)
      (is (= 1 a)))))

(deftest csp-test
  (async done
    (let [val 1
          ch (chan)]
      (go (let [a (<! ch)]
            (is (= a val))
            (done)))
      (put! ch val))))
