(ns cljs-browser-repl.console.cljs
  (:require [reagent.core :as reagent]
            [cljs-bootstrap.core :as bootstrap :refer [read-eval-print get-prompt]]
            [cljs-browser-repl.app :as app]
            [cljs-browser-repl.console :as console]))

(defn handle-result!
  [console success result]
  (let [write-fn (if success console/write-return! console/write-exception!)]
    (write-fn console result)))

(defn cljs-read-eval-print!
  [console line]
  (try
    (bootstrap/read-eval-print line (partial handle-result! console))
    (catch js/Error err
      (println "Caught js/Error - " err)
      (console/write-exception! console err))))

(defn cljs-console-prompt!
  [console]
  (doto console
    (.Prompt true (fn [input]
                    (cljs-read-eval-print! console input)
                    (.SetPromptLabel console (bootstrap/get-prompt)) ;; necessary for namespace changes
                    (cljs-console-prompt! console)))))

(defn cljs-console-did-mount []
  (js/$
   (fn []
     (let [jqconsole (console/new-jqconsole "#cljs-console"
                                            :prompt-label (bootstrap/get-prompt))]
       (app/add-console! :cljs-console jqconsole)
       (cljs-console-prompt! jqconsole)))))

(defn cljs-console-render []
  [:div.console-container
   [:div#cljs-console.console.cljs-console]])

(defn cljs-component
  "A console for evaluating Clojure(Script) forms."
  []
  (fn []
    (println "Initializing the ClojureScript REPL")
    (bootstrap/init-repl {:verbose true})
    (println "Building ClojureScript React component")
    (reagent/create-class {:reagent-render cljs-console-render
                           :component-did-mount cljs-console-did-mount})))
