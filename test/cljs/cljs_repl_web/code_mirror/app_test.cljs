(ns cljs-repl-web.code-mirror.app-test
  (:require [cljs-repl-web.code-mirror.app :as app]
            [cljs.test :refer-macros [deftest is async]]))

(def db (atom {:consoles {}}))

(def console-key :cljs-console)

(deftest initial-configuration
  (let [created? (app/console-created? @db console-key)]
    (is (= false created?) "The console should be nil"))

  (reset! db (app/add-console @db console-key {}))

  (let [created? (app/console-created? @db console-key)]
    (println @db)
    (is (= true created?) "The console should be created")))
