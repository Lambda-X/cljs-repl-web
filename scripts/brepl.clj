(require
  '[cljs.build.api :as b]
  '[cljs.repl :as repl]
  '[cljs.repl.browser :as browser])

(b/build "src"
  {:main 'cljs-browser-repl.core
   :output-to "target/cljs-browser-repl.js"
   :output-dir "target"
   :verbose true})

(repl/repl (browser/repl-env)
  :output-dir "target")
