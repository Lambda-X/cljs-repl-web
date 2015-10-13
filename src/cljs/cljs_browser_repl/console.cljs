(ns cljs-browser-repl.console
  (:require [clojure.string :as s :refer [join]]
            [cljsjs.jqconsole]))

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

(defn write!
  "Writes a message to the input console. Type is used as class inside
  the rendered message and should be either a string or a keyword. It
  automatically separates messages with \n. Purely side effecting,
  returns nil."
  [console type & messages]
  (.Write console (str (s/join "\n" messages) "\n") (name type)))

(defn write-error!
  "Writes a jqconsole-error message to the input console. It
  automatically separates messages with \n. Purely side effecting,
  returns nil."
  [console & messages]
  (apply write! console :jqconsole-error messages))

(defn write-return!
  "Writes a jqconsole-return message to the input console. It
  automatically separates messages with \n. Purely side effecting,
  returns nil."
  [console & messages]
  (apply write! console :jqconsole-return messages))

(defn write-output!
  "Writes a jqconsole-return message to the input console. It
  automatically separates messages with \n. Purely side effecting,
  returns nil."
  [console & messages]
  (apply write! console :jqconsole-output messages))

(defn write-exception!
  [console ex]
  (if-let [cause (.-cause ex)]
    (apply write-error! console (filter (complement nil?) [cause
                                                           (.-message cause)
                                                           (.-stack cause)]))
    (write-error! console (str "Unhandled exception: " ex))))
