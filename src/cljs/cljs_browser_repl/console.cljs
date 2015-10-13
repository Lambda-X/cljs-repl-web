(ns cljs-browser-repl.console
  (:require
   [cljsjs.jqconsole
   [reagent.core :as reagent :refer [atom]]
   [cljs.core.async :refer [put! chan <!]])
  (:require-macros [cljs.core.async.macros :refer [go go-loop]]))


(defn jqconsole-prompt [jqconsole chan]
  (.Prompt jqconsole true #(put! chan %)))

(defn jqconsole-write [jqconsole args]
  (.Write jqconsole (clojure.string/join "" args)))

(defn jqconsole-eval-write
  "default eval validate and write to jqconsole result if there is no fn provided on options"
  [jqconsole str]
  (try
    (jqconsole-write jqconsole ["==> " (.eval js/window str) "\n"])
    (catch js/Error err
      (jqconsole-write jqconsole [err "\n"]))))

(defn loop-prompt [jqconsole chan eval-write]
  (go-loop []
    (let [str (<! chan)]
      (if (nil? eval-write)
        (jqconsole-eval-write jqconsole str)
        (eval-write jqconsole str))
      (jqconsole-prompt jqconsole chan)
    (recur))))

(defn jqconsole-init
  "init jqconsole object set all options also all actions that we want"
  [node chan options]
  (let [extend-jqconsole (:extend-jqconsole options)
        jqconsole (-> (js/$ node)
                      (.jqconsole (:welcome-string options) (:prompt-label options) (:continue-label options) (:disable-auto-focus options)))]
    (do
      (if (not (nil? extend-jqconsole))
        (extend-jqconsole jqconsole chan))
      jqconsole)))

(defn jqconsole-did-mount [this options]
  (let [command-chan (chan)
        jqconsole (jqconsole-init (reagent/dom-node this) command-chan options)]
    (jqconsole-prompt jqconsole command-chan)
    (loop-prompt jqconsole command-chan (:eval-write options))))

(defn jqconsole-render [class-name]
  [:div {:class-name (or class-name "console-wrapper")}])

(defn jqconsole-component
  "Creates a new reagent component that wrapps JQConsole which loads on the node and configuration.
  The options are passed as map:

  welcome-string        - is the string to be shown when the terminal is first rendered.
  prompt-label          - is the label to be shown before the continued lines of the input when using Prompt().
  continue-label        - is the label to be shown before the continued lines of the input when using Prompt().
  disable-auto-focus    - is a boolean indicating whether we should disable the default auto-focus behavior.
  class-name            - this will add class name for main console div wrapper
  extend-jqconsole      - this will extand instance jqconsole add here for example RegisterShortcut
  eval-write            - function call on each eval of line
  "
  [options]
  (reagent/create-class {:reagent-render #(jqconsole-render (:class-name options))
                         :component-did-mount #(jqconsole-did-mount % options)}))
