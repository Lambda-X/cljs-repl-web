(ns launcher.runner
  (:require [doo.runner :as doo :refer-macros [doo-all-tests]]
            cljs-repl-web.core-test
            cljs-repl-web.code-mirror.app-test))

(enable-console-print!)

(doo-all-tests #"^cljs-repl-web.*-test")
