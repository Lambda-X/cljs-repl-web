(ns cljs-repl-web.markdown
  "Core implementation of reagent-markdown. A small utility that renders
  Markdown to react/reagent components. Most of the code has been
  borrowed from devcards, courtesy of Bruce Hauman."
  (:require [clojure.string :as string]
            [cljsjs.showdown]
            [reagent.core :as reagent]))

(declare parse-out-blocks markdown-block->react)

;;;;;;;;;;;;;;;;
;;; MAIN API ;;;
;;;;;;;;;;;;;;;;

(defn md->react-component
  "Interprets the input string(s) as Markdown, yielding a react
  component. It wraps the generated HTML in <div
  class=\"<container-class>\"> or <div
  class=\"reactive-markdown-block\"> if container-class is not given as
  first parameter. In case of failure a message is logged to console and
  an error <div> is added to the DOM."
  ([& strs]
   (if (every? string? strs)
     (let [blocks (mapcat parse-out-blocks strs)]
       [:div {:class "reagent-markdown-block"}
        (map markdown-block->react blocks)])
     (let [message "Error: The input should be a seq of strings containing less-sensitive markdown."]
       (try (.error js/console message))
       [:div {:style {:color "#a94442"}} message]))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; IMPLEMENTATION by Bruce Hauman ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn react-raw [raw-html-str]
  "A React component that renders raw html."
  (.div (.-DOM js/React)
        (clj->js { :key (str (hash raw-html-str))
                  :dangerouslySetInnerHTML
                  { :__html
                   raw-html-str}})))

(defn ref->node [this ref]
  (when-let [comp (aget (.. this -refs) ref)]
    (js/React.findDOMNode comp)))

(defn get-props [this k]
  (aget (.-props this) (name k)))

(defn leading-space-count [s]
  (when-let [ws (second (re-matches #"^([\s]*).*"  s))]
    (.-length ws)))

(let [conv-class (.-converter js/Showdown)
      converter (conv-class.)]
  (defn markdown-to-html
    "render markdown"
    [markdown-txt]
    (.makeHtml converter markdown-txt)))

(defn matches-delim? [line]
  (re-matches #"^[\s]*```(\w*).*" line))

(defmulti block-parser
  (fn [{:keys [stage]} line]
    [(if (matches-delim? line) :delim :line) (:type stage)]))

(defmethod block-parser [:line :markdown] [{:keys [stage] :as st} line]
  (update-in st [:stage :content] conj (string/trim line)))

(defmethod block-parser [:line :code-block] [{:keys [stage] :as st} line]
  (update-in st [:stage :content] conj (subs line (:leading-spaces stage))))

(defmethod block-parser [:delim :markdown] [{:keys [stage accum] :as st} line];; enter block
  (let [lang (second (matches-delim? line))]
    (-> st ;; the beginning
        (assoc :accum (conj accum stage))
        (assoc :stage
               {:type :code-block
                :lang (when-not (string/blank? lang) lang)
                :leading-spaces (leading-space-count line)
                :content []}))))

(defmethod block-parser [:delim :code-block] [{:keys [stage accum] :as st} line];; enter block
  (-> st ;; the end
      (assoc :accum (conj accum stage))
      (assoc :stage {:type :markdown :content []})))

(defn parse-out-blocks* [m]
  (reduce block-parser
          {:stage {:type :markdown :content []} :accum []}
          (string/split m "\n")))

(defn parse-out-blocks [m]
  (let [{:keys [stage accum]} (parse-out-blocks* m)]
    (->> (conj accum stage)
         (filter (fn [{:keys [content]}] (not-empty content)))
         (map (fn [x] (update-in x [:content] #(string/join "\n" %)))))))

(defmulti markdown-block->react :type)

(defmethod markdown-block->react :default [{:keys [content]}]
  (-> content markdown-to-html react-raw))

(defmethod markdown-block->react :code-block [{:keys [content] :as block}]
  (reagent/create-class
   {:render
    (fn [this]
      [:pre {:className ""}
       [:code {:className (or (get-props this :lang) "")
               :ref "code-ref"}
        (get-props this :code)]])}))
