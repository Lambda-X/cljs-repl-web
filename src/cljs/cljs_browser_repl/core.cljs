(ns cljs-browser-repl.core
  (:require [reagent.core :as reagent]
            [cljsjs.jqconsole]
            ))

(enable-console-print!)

(def initial-state {:console {:created? false}})

(defonce app-state (reagent/atom initial-state))

(reset! app-state initial-state)

(def jq (js* "$"))

(defn jq-select
  "Selects a DOM element using jQuery. Any jQuery selector will work."
  [selector]
  (jq selector))

(defn new-jqconsole
  "Creates a new instance of JQConsole which loads on the input
  selector (any jQuery selector will work). TODO"
  [selector]
  (-> (jq-select selector) (.jqconsole "Hi there\n" ">> ")))

(defn echo-prompt
  [console]
  (.Prompt console
           true
           (fn [input]
             (.Write console (str input "\n") "jqconsole-output")
             (echo-prompt console))))

(defn echo-console-creator
  "Mimics the sample in the JQConsole wiki:
  <script>
      $(function () {
        var jqconsole = $('#console').jqconsole('Hi\n', '>>>');
        var startPrompt = function () {
          // Start the prompt with history enabled.
          jqconsole.Prompt(true, function (input) {
            // Output input with the class jqconsole-output.
            jqconsole.Write(input + '\n', 'jqconsole-output');
            // Restart the prompt.
            startPrompt();
          });
        };
        startPrompt();
      });
    </script>"
  []
  (fn []
    (jq-select #(let [jqconsole (new-jqconsole "#console")]
                  (println "JQConsole has been loaded on " jqconsole)
                  (echo-prompt jqconsole)))
    [:div.console-container
     [:div#console.console]]))

(defn console-creator []
  (fn []
    (when-not (get-in @app-state [:console :created?])
      (jq-select #(let [jqconsole (new-jqconsole "#console")]
                    ;; do your stuff here
                    ))
      [:div.console-container
       [:div#console.console]])))

(defn page []
  [:div
   [echo-console-creator]])


(defn ^:export main []
  (reagent/render [page]
                  (.getElementById js/document "app")))
