(ns cljs-repl-web.require-test
  (:use clojure.test)
  (:require [clojure.string :as string]))

(deftest clojure-upper-case
  (is (= (string/upper-case "MiXeD cAsE") "MIXED CASE")))
