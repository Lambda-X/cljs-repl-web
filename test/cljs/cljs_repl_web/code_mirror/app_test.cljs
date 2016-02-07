(ns cljs-repl-web.code-mirror.app-test
  (:require [cljs-repl-web.code-mirror.app :as app]
            [cljs.test :refer-macros [deftest is async]]))

(def db (atom {:consoles {}}))

(def console-key :cljs-console)

(deftest initial-configuration
  (let [created? (app/console-created? @db console-key)]
    (is (= false created?) "The console should be nil"))

  (let [db (app/add-console @db console-key {:placeholder "js-object"})
        created? (app/console-created? db console-key)
        items (app/console-items db console-key)
        queued-forms (app/queued-forms db console-key)
        history (app/console-history db console-key)
        hist-pos (app/console-history-pos db console-key)]
    (is (= true created?) "The console should be created")
    (is (empty? items) "The items should be empty")
    (is (empty? queued-forms) "The queued forms should be empty")
    (is (= (-> history count) 1) "The history should contain an empty string")
    (is (= (-> history first) "") "The first and only item should be an empty string")
    (is (= 0 hist-pos) "The history position should be 0")))
