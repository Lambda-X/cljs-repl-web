(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'cljs-browser-repl.core
   :output-to "target/cljs-browser-repl.js"
   :output-dir "target"})
