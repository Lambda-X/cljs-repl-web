(ns launcher.runner
  (:require [doo.runner :refer-macros [doo-all-tests]]
            [cljs-browser-repl.console-test]
            [cljs-bootstrap.core-test]
            [cljs-bootstrap.repl-test]
            [cljs-bootstrap.common-test]))

(enable-console-print!)

(doo-all-tests) ;; #"^cljs.*-test" not supported yet
