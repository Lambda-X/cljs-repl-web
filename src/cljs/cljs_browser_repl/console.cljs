(ns cljs-browser-repl.console
  (:require [cljsjs.jqconsole]))

(defn new-jqconsole
  "Creates a new instance of JQConsole which loads on the input
  selector (any jQuery selector will work) and configuration. The
  options are passed as named parameters and follow the jq-console ones.

  * welcome-string is the string to be shown when the terminal is first
    rendered.
  * prompt-label is the label to be shown before the input when using
    Prompt().
  * continue-label is the label to be shown before the continued lines
    of the input when using Prompt().
  * disable-auto-focus is a boolean indicating whether we should disable
    the default auto-focus behavior.

  See https://github.com/replit/jq-console#instantiating"

  [selector & {:keys [welcome-string prompt-label continue-label disable-auto-focus]
               :or {welcome-string nil prompt-label ">> " continue-label nil disable-auto-focus nil}}]
  (-> (js/$ selector) (.jqconsole welcome-string prompt-label continue-label disable-auto-focus)))
