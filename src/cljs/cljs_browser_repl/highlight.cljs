(ns cljs-browser-repl.highlight
  (:require [clojure.string :as string]
            [cljsjs.highlight]
            [cljsjs.highlight.langs.clojure]))

;; -Damian- developer was crying when pushing!!!
(defn ^:private seq-children [node]
  (array-seq (.children (js/$ node))))

(def ^:private node-el
  (let [node (.createElement js/document "code")]
    (set! (.-className node) "clojure")
    node))

(defn ^:private highlight-new-code-el! [str]
  (let [node (.cloneNode node-el false)]
    (.appendChild node (.createTextNode js/document str))
    (js/hljs.highlightBlock node)
    node))

(defn ^:private match-closing-char [line]
  (case (last (string/split line #""))
    "(" ")"
    "{" "}"
    "\"" "\""
    "'" "'"
    nil))

(defn highlight-prompt-line! [prompt previos-prompt-text]
  (let [text (.text prompt)]
    (if-not (= @previos-prompt-text text)
      (do
        (reset! previos-prompt-text text)
        (.html prompt (highlight-new-code-el! text))))))

(defn highlight-prompt-lines! [prompt]
  (if-let [prompt-children (seq-children prompt)]
    (doseq [prompt-child prompt-children]
      (let [node (second (seq-children prompt-child))]
        (if (= nil (.querySelector node ".clojure"))
          (highlight-prompt-line! (js/$ node) (atom "")))))))

(defn highlight-prompt-block!
  [input-source prompt-left prompt-right prompt-before prompt-after console]
  (let [previos-prompt-text-left (atom "")
        previos-prompt-text-right (atom "")]
    (.on input-source "change keydown keyup keypress paste"
         (fn [event]

           ;; hljs last old-prompt
           (if (and (= (.-which event) 13) (= (.-type event) "keyup"))
             (let [old-prompt (last (array-seq (.querySelectorAll (first (array-seq console)) ".jqconsole-old-prompt")))]
               (highlight-prompt-line! (js/$ old-prompt) (atom ""))))

           ;; TODO: make clojure dev happy man, add closing elements, wip
           ;; (let [closing-char (match-closing-char (.text prompt-left))]
           ;; (if (= (.-which event) 57)
           ;; (.log js/console closing-char)
           ;; ))

           (highlight-prompt-line! prompt-left previos-prompt-text-left)
           (highlight-prompt-line! prompt-right previos-prompt-text-right)
           (highlight-prompt-lines! prompt-before)
           (highlight-prompt-lines! prompt-after)))))

(defn highlight-hack! [console]
  (highlight-prompt-block! (.-$input_source console)
                           (.-$prompt_left console)
                           (.-$prompt_right console)
                           (.-$prompt_before console)
                           (.-$prompt_after console)
                           (.-$console console)))
