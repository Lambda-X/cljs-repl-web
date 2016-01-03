(ns cljs-repl-web.console.cljs-test
  (:require [cljs.test :refer-macros [deftest is]]
            [cljs.core.async :refer [<! >! chan close! put! timeout]]
            [cljs-repl-web.console.cljs :as console :refer [cljs-read-eval-print!]])
  (:require-macros [cljs.core.async.macros :refer [go alt!]]))

(deftest require
  ;; AR - here goes the (require 'clojure.string) test
  ;; We well probably need core.async, that is why I added it in the deps.
  #_(async done
      (go ...)))
