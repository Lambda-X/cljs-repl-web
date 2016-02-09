(ns cljs-repl-web.config)

(def defaults
  (let [base-path ""]
    {:name "Clojurescript.io Website"
     :production? true
     :base-path base-path
     :core-cache-url (str base-path "/js-cache/core.cljs.cache.aot.json")
     :src-paths [(str base-path "/cljs-src")]
     :verbose-repl? true}))
