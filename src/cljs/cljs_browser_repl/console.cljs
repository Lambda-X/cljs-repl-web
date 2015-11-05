(ns cljs-browser-repl.console
  (:require [clojure.string :as s :refer [join]]
            [cljsjs.jqconsole]
            [cljsjs.highlight]
            [cljsjs.highlight.langs.clojure]
            [replumb.core :as replumb]))

(defn new-jqconsole
  "Creates a new instance of JQConsole which loads on the input
  selector (any jQuery selector will work) and configuration. The
  options are passed as named parameters and follow the jq-console ones.

  * :welcome-string is the string to be shown when the terminal is first
    rendered. Defaults to nil.
  * :prompt-label is the label to be shown before the input when using
    Prompt(). Defaults to \"$ \".
  * :continue-label is the label to be shown before the continued lines
    of the input when using Prompt(). Defaults to nil.
  * :disable-auto-focus is a boolean indicating whether we should disable
    the default auto-focus behavior. Defaults to false, the console always
    focus.

  See https://github.com/replit/jq-console#instantiating"
  [selector {:keys [welcome-string prompt-label continue-label disable-auto-focus]}]
  (-> (js/$ selector) (.jqconsole welcome-string prompt-label continue-label disable-auto-focus)))

(defn write!
  "Writes a message to the input console. Type is used as class inside
  the rendered message and should be either a string or a keyword. It
  automatically separates messages with \n. Purely side effecting,
  returns nil."
  [console type & messages]
  (when-let [msgs (filter (complement nil?) messages)]
    (.Write console (str (s/join "\n" msgs) "\n") (name type))))

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
  ([console ex] (write-exception! console ex false))
  ([console ex print-stack-trace?]
   (write-error! console (replumb/error->str ex print-stack-trace?))))

(defn clear-console!
  "jqconsole wrapper, clears the console's content excluding the current
  prompt."
  [console]
  (.Clear console))

(defn reset-console!
  "jqconsole wrapper, resets the console to its initial state, cancelling
  all current and pending operations."
  [console]
  (.Reset console))

(defn dump-console!
  "jqconsole wrapper, returns the text content of the console."
  [console]
  (.Dump console))

(defn register-matching!
  "jqconsole wrapper, registers an opening and closing characters to match."
  [console matching-name opening closing]
  (.RegisterMatching console opening closing (name matching-name)))

(defn register-matchings!
  "Registers each matching in the provided map. The key is used as CSS
  class"
  [console matchings]
  (doseq [[matching-name [opening closing]] matchings]
    (register-matching! console matching-name opening closing)))

(defn focus-console!
  "jqconsole wrapper, forces the focus onto the console so events can be
  captured."
  [console]
  (.Focus console))

;TODO: developer was crying when pushing!!!
(defn ^:private add-code! [$node]
  "we need help hljs detect code so we mutate node adding code tag wrapper"
  (.addClass $node "clojure")
  (.wrapInner $node "<code></code>"))

(defn ^:private seq-children [node]
  (array-seq (.children (js/$ node))))

(defn highlight-prompt-before-after! [prompt]
  (if-let [prompt-children (seq-children prompt)]
    (doseq [prompt-child prompt-children]
      (let [node (second (seq-children prompt-child))
            $node (js/$ node)]
        (if-not (.hasClass $node "clojure")
          (do (add-code! $node)
              (js/hljs.highlightBlock node)))))))

(defn highlight-prompt-left-right! [prompt]
  (let [node (first (array-seq (.html prompt (.text prompt))))]
    (add-code! (js/$ node))
    (js/hljs.highlightBlock node)))

(defn highlight-prompt-block!
  [input-source prompt-left prompt-right prompt-before prompt-after]
  (.on input-source "change keyup paste" (fn []
                                           (highlight-prompt-before-after! prompt-before)
                                           (highlight-prompt-before-after! prompt-after)
                                           (highlight-prompt-left-right! prompt-left)
                                           (highlight-prompt-left-right! prompt-right)
                                           )))

(defn color-crap! [console]
  (highlight-prompt-block! (.-$input_source console) (.-$prompt_left console) (.-$prompt_right console) (.-$prompt_before console) (.-$prompt_after console)))
