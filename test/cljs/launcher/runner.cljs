(ns launcher.runner
  (:require [doo.runner :refer-macros [doo-all-tests]]
            [cljs-browser-repl.console-test]
            [cljs-bootstrap.core-test]
            [cljs-bootstrap.repl-test]
            [cljs-bootstrap.common-test]))

(enable-console-print!)

;; (defmethod cljs.test/report [:cljs.test/default :end-run-tests] [m]
;;   (successful? m))

(doo-all-tests #"^cljs.*-test")
