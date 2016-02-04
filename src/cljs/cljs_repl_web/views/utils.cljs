(ns cljs-repl-web.views.utils
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
            [cljs-repl-web.code-mirror.utils :as utils]))

(def clojuredocs-url "http://clojuredocs.org/")

;; Some namespaces in cljs don't have a match in clj
;; we could also use :clj-symbol in the generator, but
;; for now it's just one namepsace
(def cljs-special-ns->clj-ns {"special" "clojure.core"})

;; We need to convert some special characters to generate the url
(def url-special-characters { #"/"    "_fs"
                              #"\?$"  "_q"
                              #"^\."  "_." })

(defn strip-namespace
  "Given a cljs symbol, strip the namespace part. `sym` must be
  fully qualified."
  [sym]
  (second (string/split (str sym) #"/" 2)))

(defn symbol->clojuredocs-url
  "Given a cljs symbol (with fully qualified ns or without, in which
  case it defaults to clojure.core), returns the url to ClojureDocs
  documentation."
  [cljs-symbol]
  (let [[ns symbol] (string/split (str cljs-symbol) #"/" 2)
        url-ns (or (cljs-special-ns->clj-ns ns) ns)
        url-symbol (reduce #(apply string/replace %1 %2) symbol url-special-characters)]
    (str clojuredocs-url (if url-ns
                           (string/replace url-ns #"cljs" "clojure")
                           "clojure.core")
         "/" url-symbol)))

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

(defn message-box
  "Displays a popup with a message."
  [message]
  ;; we can later modify it to use re-com
  (js/alert message))

(defn open-new-window
  "Opens a new window in the browser (or tab - depends on browser)."
  ([url target] (.open js/window url target))
  ([url] (open-new-window url "_blank")))

(defn scroll-to-top
  []
  (.scrollTo js/window 0 0))

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
                                   (let [parsed-hls (hickory/parse-fragment s)]
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
        (->> html-string
             hickory/parse-fragment
             (map hickory/as-hiccup)
             (mapcat identity)
             (filter #(or (string? %) (vector? %)))
             (map (fn [item]
                    (if (string? item)
                      [:p (goog.string/unescapeEntities item)]
                      [utils/colored-text (goog.string/unescapeEntities (last item)) {:padding "10"}]))))))

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

(defn api-panel-column-number
  "Given a media-query, returns the number of columns for the
  api-panel. Throws if media query is not one of the handled ones."
  [media-query]
  (case media-query
    (:narrow :medium) 1
    :wide 2))

(defn footer-column-number
  "Given a media-query, returns the number of columns for the
  footer. Throws if media query is not one of the handled ones."
  [media-query]
  (case media-query
    (:narrow :medium) 1
    :wide 2))
