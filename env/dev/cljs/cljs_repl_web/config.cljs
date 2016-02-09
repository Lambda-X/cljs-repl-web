(ns cljs-repl-web.config)

(def defaults
  (let [base-path ""]
    {:name "Clojurescript.io Website (DEV)"
     :production? false
     :base-path base-path
     :core-cache-url (str base-path "/js-cache/core.cljs.cache.aot.json")
     :src-paths [(str base-path "/js/compiled/out")]
     :verbose-repl? true}))
