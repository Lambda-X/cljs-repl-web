(ns cljs-browser-repl.console.echo
  (:require [reagent.core :as reagent]
            [cljs-browser-repl.console :as console]))

(defn echo-prompt
  [console]
  (.Prompt console
           true
           (fn [input]
             (console/write-output! console (str input "\n"))
             (echo-prompt console))))

(defn echo-console-did-mount []
  (js/$
   (fn []
     (let [jqconsole (console/new-jqconsole "#echo-console" :prompt-label "Let me echo it for you: ")]
       (echo-prompt jqconsole)))))

(defn echo-console-render []
  [:div.console-container
   [:div#echo-console.console.echo-console]])

(defn echo-console
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
  </script>

  And the cookbook entry at https://github.com/reagent-project/reagent-cookbook/tree/master/recipes/data-tables"
  []
  (reagent/create-class {:reagent-render echo-console-render
                         :component-did-mount echo-console-did-mount}))
