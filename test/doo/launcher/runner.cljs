(ns  ^:figwheel-always launcher.runner
  (:require [doo.runner :refer-macros [doo-all-tests]]
            [cljs-browser-repl.console-test]))

;; Add COMPILED flag to cljs eval to turn off namespace already declared errors
(set! js/COMPILED true)

(enable-console-print!)

(doo-all-tests) ;; #"^cljs.*-test" not supported yet
