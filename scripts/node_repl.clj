(require 'cljs.repl)
(require 'cljs.build.api)
(require 'cljs.repl.node)

(cljs.build.api/build "src"
  {:main 'cljs-browser-repl.core
   :output-to "target/cljs-browser-repl.js"
   :verbose true})

;; (println (cljs.build.api/ns->location 'hello-world.core))

(cljs.repl/repl (cljs.repl.node/repl-env)
  :watch "src"
  :output-dir "target")
