(ns cljs-repl-web.code-mirror.app-test
  (:require [cljs-repl-web.code-mirror.app :as app]
            [cljs.test :refer-macros [deftest is async]]
            [cljs-repl-web.replumb-proxy :as replumb-proxy]))

;; Or doo will exit with an error, see:
;; https://github.com/bensu/doo/issues/83#issuecomment-165498172
(set! (.-error js/console) (fn [x] (.log js/console x)))

(def db (atom {:consoles {}}))

(def console-key :cljs-console)

(def eval-opts replumb-proxy/eval-opts)

(defn submit
  [db k source]
  (let [evaluate (:evaluate eval-opts)]
    (evaluate (partial app/on-eval-complete db k)
              source)))

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


(deftest evaluate
  (let [db (-> @db
               (app/add-console console-key {:placeholder "js-object"})
               (submit console-key "123")
               (submit console-key "(inc 10)"))
        items (app/console-items db console-key)
        history (app/console-history db console-key)
        hist-pos (app/console-history-pos db console-key)]
    (is (= 4 (-> items count)) "The items should contain 4 elements (2 input, 2 output)")
    (is (= 3 (-> history count)) "The history should contain 3 items (2 previous, 1 current)")
    (is (= (-> items first :type) :input) "The first item should be of type :input")
    (is (= (-> items second :type) :output) "The second item should be of type :output")))
