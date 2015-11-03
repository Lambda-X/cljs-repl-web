(ns cljs-browser-repl.console.cljs
  (:require [reagent.core :as reagent]
            [re-com.core     :refer [md-icon-button v-box] :refer-macros [handler-fn]]
            [re-com.validate :refer [string-or-hiccup? alert-type? vector-of-maps?]]
            [replumb.core :as replumb]
            [cljs-browser-repl.app :as app]
            [cljs-browser-repl.console :as console]))

(def default-matchings
  {:match-round-brackets  [\( \)]
   :match-square-brackets [\[ \]]
   :match-curly-brackets  [\{ \}]
   :match-string          [\" \"] })

(defn handle-result!
  [console result]
  (let [write-fn (if (replumb/success? result) console/write-return! console/write-exception!)]
    (write-fn console (replumb/unwrap-result result))))

(defn cljs-read-eval-print!
  [console line]
  (try
    (replumb/read-eval-call (partial handle-result! console) line)
    (catch js/Error err
      (println "Caught js/Error during read-eval-print: " err)
      (console/write-exception! console err))))

(defn cljs-console-prompt!
  [console]
  (doto console
    (.Prompt true (fn [input]
                    (cljs-read-eval-print! console input)
                    (.SetPromptLabel console (replumb/get-prompt)) ;; necessary for namespace changes
                    (cljs-console-prompt! console)))))

; Reagent components

(defn cljs-console-did-mount
  [console-opts]
  (js/$
   (fn []
     (let [jqconsole (console/new-jqconsole ".cljs-console"
                                            (merge {:prompt-label (replumb/get-prompt)
                                                    :disable-auto-focus true}
                                                   console-opts))]
       (app/add-console! :cljs-console jqconsole)
       (console/register-matchings! jqconsole default-matchings)
       (cljs-console-prompt! jqconsole)))))

(defn cljs-console-render []
  [:div.cljs-console.console])

(defn cljs-component
  "Creates a new instance of e which loads on the input
  selector (any jQuery selector will work) and configuration. The
  options are passed as named parameters and follow the jq-console ones.

  * :welcome-string is the string to be shown when the terminal is first
    rendered. Defaults to nil.
  * :prompt-label is the label to be shown before the input when using
    Prompt(). Defaults to namespace=>.
  * :continue-label is the label to be shown before the continued lines
    of the input when using Prompt(). Defaults to nil.
  * :disable-auto-focus is a boolean indicating whether we should disable
    the default auto-focus behavior. Defaults to true, the console never
    takes focus."
  []
  (fn [console-opts]
    (println "Building ClojureScript React component")
    (reagent/create-class {:display-name "cljs-console-component"
                           :reagent-render cljs-console-render
                           :component-did-mount #(cljs-console-did-mount console-opts)})))

(defn cljs-reset-console-and-prompt!
  [console]
  (console/reset-console! console)
  (cljs-console-prompt! console))

(defn cljs-button-components []
  "Return a vector of components containing the cljs console buttons.
   To place them in a layout, call the function (it does not return a
   component)."
  [v-box
   :gap "4px"
   :children [[md-icon-button
               :md-icon-name "zmdi-delete"
               :on-click #(cljs-reset-console-and-prompt! (app/console :cljs-console))
               :class "cljs-btn"
               :disabled? (not (app/console-created? :cljs-console))]
              [md-icon-button
               :md-icon-name "zmdi-format-clear-all"
               :on-click #(console/clear-console! (app/console :cljs-console))
               :class "cljs-btn"
               :disabled? (not (app/console-created? :cljs-console))]
              ;; copy to clipboard?
              [md-icon-button
               :md-icon-name "zmdi-github"
               :on-click #()
               :class "cljs-btn"
               :disabled? (not (app/console-created? :cljs-console))]]])
