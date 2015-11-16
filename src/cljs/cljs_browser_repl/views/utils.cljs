(ns cljs-browser-repl.views.utils
  (:require-macros [re-com.core :refer [handler-fn]])
  (:require [cljs.pprint :as pprint :refer [pprint]]
            [clojure.string :as string]
            [clojure.walk :as walk]
            [clojure.zip :as zip]
            [reagent.core :as reagent]
            [re-com.core :refer [popover-tooltip]]
            [hickory.core :as hickory]
            [hickory.zip :as hzip]
            [hickory.convert :as hconvert]
            [goog.string :as gstring]
            [cljs-browser-repl.highlight :as hl]))

(def clojuredocs-url "http://clojuredocs.org/")

(defn strip-namespace
  "Given a cljs symbol, strip the namespace part."
  [sym]
  (string/join (drop-while #(re-find #"\." %) (string/split (str sym) #"/" 2))))

(defn symbol->clojuredocs-url
  "Given a cljs symbol (with fully qualified ns or without, in which
  case it defaults to clojure.core), returns the url to ClojureDocs
  documentation."
  [cljs-symbol]
  (let [[ns symbol] (string/split (str cljs-symbol) #"/" 2)]
    (str clojuredocs-url (if ns
                           (string/replace ns #"cljs" "clojure")
                           "clojure.core")
         "/" symbol)))

(defn number->word
  "From: http://clojurescriptmadeeasy.com/blog/how-to-humanize-text-cl-format.html
  If (< 0 n 10) use the ~R directive, otherwise use ~A"
  [n]
  (pprint/cl-format nil "~:[~a~;~r~]" (< 0 n 10) n))

(defn calculate-popover-position
  "Calculates the tooltip orientation for a given symbol."
  [[x y]]
  (let [h (.-innerHeight js/window)
        w (.-innerWidth  js/window)
        v-threshold (quot h 2)
        v-position  (if (< y v-threshold) "below" "above")
        h-threshold-left (quot w 3)
        h-threshold-cent (* 2 h-threshold-left)
        h-position (cond
                    (< x h-threshold-left) "right"
                    (< x h-threshold-cent) "center"
                    :else "left")]
    (keyword (str v-position \- h-position))))

(defn unescape-html
  "Walk a given Hiccup form and remove all pure whitespace."
  [hiccup]
  (walk/prewalk
   (fn [form]
     (if (string? form)
       (goog.string/unescapeEntities form)
       form))
   hiccup))

(defn trim-strings
  "Walk a given Hiccup form and remove all pure whitespace."
  [hiccup]
  (walk/prewalk
   (fn [form]
     (if (string? form)
       (string/trim form)
       form))
   hiccup))

(defn add-node-class
  "Given a loc's node, adds a class (as either string or keyword) and
  returns the new node. Usually used like: (zip/edit loc
  add-loc-class :class-name)"
  [node class-name]
  (update-in node [:attrs :class] (fn [old-str]
                                    (if (empty? old-str)
                                      (name class-name)
                                      (str old-str " " (name class-name))))))

(def as-hickory-zip (comp hzip/hickory-zip
                          hickory/as-hickory))

(defn inject-node-highlight
  "Given a loc's node, replaces its string :content with highlighted
  nodes, returns the new node. Usually used like: (zip/edit loc
  inject-node-highlight)"
  [node]
  (update-in node [:content] (fn [old-content]
                               (if-let [s (first old-content)]
                                 (if (string? s)
                                   (let [parsed-hls (hickory/parse-fragment (hl/highlight-html s))]
                                     (mapv (comp zip/root as-hickory-zip) parsed-hls))
                                   old-content)
                                 old-content))))

(defn highlight-code-locs
  "Given a hickory zipper, highlights all the <code> locs."
  [hickory-zip-root]
  (loop [loc hickory-zip-root]
    (if-not (zip/end? loc)
      (recur (zip/next (if (= :code (:tag (zip/node loc)))
                         (-> loc
                             (zip/edit add-node-class :hljs)
                             (zip/edit inject-node-highlight))
                         loc)))
      loc)))

(defn html-string->hiccup
  "Convert a html string to hiccup vector, optionally merging class,
  style and attr if present as variadic params."
  [html-string & {:keys [class style attr]}]
  (into [:div (merge {:class class
                      :style style}
                     attr)]
        (map (comp unescape-html hickory/as-hiccup)
             (hickory/parse-fragment html-string))))

(defn html-string->highligthed-hiccup
  "Convert a html string to a hiccup vector, optionally merging class,
  style and attr if present as variadic params."
  [html-string & {:keys [class style attr]}]
  (into [:div (merge {:class class
                      :style style}
                     attr)]
        (map (comp unescape-html
                   hconvert/hickory-to-hiccup
                   zip/root
                   highlight-code-locs
                   as-hickory-zip)
             (hickory/parse-fragment html-string))))

(comment
  (require '[clairvoyant.core :as t :include-macros true])
  (require '[hickory.core :as hickory])
  (require '[hickory.render :as hrender])
  (require '[hickory.convert :as hconver])
  (require '[hickory.zip :as hzip])
  (require '[clojure.zip :as zip])
  (require '[cljs-browser-repl.highlight :as hl])
  (require '[cljs.pprint :refer [pprint]])

  (def h1 (hl/highlight-html "(def a \"b\")"))

  (def s1 "<div><pre><code class=\"clj\">(def a \"b\")</code></pre></div>")
  (def s2 "<div><pre><code class=\"clj\">(def a \"b\")</code></pre></div> <div><pre><code class=\"clj\">(defn myfun [coll] (first coll))</code></pre></div>")

  (def h (map as-hickory-zip (hickory/parse-fragment s2)))
  (map highlight-code-locs h)

  (html-string->highligthed-hiccup s2))

;; AR - Not used anywhere atm
(defn inject-attributes
  "Walk a given Hiccup form and inject the attribute map, merging (with
  precedence to attr-map) with the pre-existing one if any."
  [attr-map hiccup]
  (println hiccup)
  ;; having fun
  (assoc hiccup 1 (merge-with merge (when (map? (nth hiccup 1))
                                      (nth hiccup 1))
                              attr-map)))
