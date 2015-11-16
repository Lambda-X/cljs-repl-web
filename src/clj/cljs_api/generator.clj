(ns cljs-api.generator
  (:require [clojure.pprint :as pprint :refer [pprint]]
            [clojure.string :as s]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [endophile.core :as e]
            [markdown.core :as md]
            [markdown.transformers :as mdt]))

(def api-edn-keyseqs #{[:release :cljs-version]
                       [:release :cljs-date]
                       [:release :clj-version]
                       [:release :gclosure-lib]
                       [:release :treader-version]
                       [:symbols]})

(def api-symbol-keywords #{:full-name :ns :name :type :docstring
                           :signature :description :examples :related})

(def selected-namespaces #{"cljs.core" "special"})

(defn filter-kv
  "Given an associative collection, return a map that contains the key
  sequences in input, filtering out the rest"
  [keyseqs coll]
  (reduce (fn [m kv] (assoc-in m kv (get-in coll kv))) {} keyseqs))

(defn api-symbol->new-value
  [api-symbol]
  (select-keys (second api-symbol) api-symbol-keywords))

(defn api-symbol->new-key
  [api-symbol]
  (:name (second api-symbol)))

(defn api-symbols->name-map
  [api-symbols]
  (into {} (map (juxt api-symbol->new-key api-symbol->new-value) api-symbols)))

;;;;;;;;;;;;;;;;
;; :examples ;;;
;;;;;;;;;;;;;;;;

(defn assoc-example-htmls
  "Note, needs the value of the symbol map, aka the map containing
  {:examples ...}. It adds a seq of examples in (escaped) html format
  under :examples-htmls."
  [api-symbol-content]
  (let [examples (->> api-symbol-content :examples (map :content))
        html (mapv #(md/md-to-html-string %) examples)]
    (assoc api-symbol-content :examples-htmls html)))

(defn lift-up-comments
  "Given lines of code, merges each commented line (;) with the
  previous. Tipically [\"(pop [1 2 3])\" \";;=> [1 2]\" ...] becomes
  [\"(pop [1 2 3]) ;;=> [1 2]\" ...]."
  [lines]
  ;; AR - Like this trick?
  ;; (doall (map pprint (reductions (fn [merged-lines line]
                                   ;; (if (re-find #"^;" line)
                                     ;; (cons (str (first merged-lines) " " line)
                                           ;; (drop 1 merged-lines))
                                     ;; (cons line merged-lines))) [] lines)))

  ;; AR - conj does not fit because drop returns lazy seqs,
  ;; reverse trasducer would be nice
  (apply vector
         (reverse (reduce (fn [merged-lines line]
                            (if (re-find #"^;" line)
                              (cons (str (first merged-lines) " " line)
                                    (drop 1 merged-lines))
                              (cons line merged-lines)))
                          () lines))))

(defn clj-node-seq->strings
  "Nodes as used in clojure.xml and in the enlive HTML library.
  Nodes can be lists of nodes."
  [clj-node-seq]
  (let [code-content #(-> % :content first :content first)
        code-nodes (filter #(and (= :pre (:tag %)) (= :code (-> % :content first :tag))) clj-node-seq)]
    ;; Thanks Thomas for spotting this!
    (into [] (flatten
              (mapv (comp lift-up-comments      ;; see docstring
                          (partial filterv seq) ;; filter out empty lines
                          s/split-lines         ;; split lines
                          code-content)         ;; fetch content
                    code-nodes)))))

(defn assoc-example-strings
  "Note, needs the value of the symbol map, aka the map containing
  {:examples ...}. It adds a seq of examples in (escaped) html format
  under :examples-strings"
  [api-symbol-content]
  (let [examples (->> api-symbol-content :examples (map :content))
        seq-of-clj-node-seq (map #(e/to-clj (e/mp %)) examples) ;; this is a seq of {:tag .. :content ...}
        examples-strings (mapv clj-node-seq->strings seq-of-clj-node-seq)]
    (assoc api-symbol-content :examples-strings examples-strings)))

;;;;;;;;;;;;;;;;;;;
;; :description ;;;
;;;;;;;;;;;;;;;;;;;

(defn assoc-description-html
  "Note, needs the value of the symbol map, aka the map containing
  {:description ...}. It adds a seq of examples in (escaped) html format
  under :description-html."
  [api-symbol-content]
  (let [description (->> api-symbol-content :description)
        html (md/md-to-html-string description)]
    (assoc api-symbol-content :description-html html)))

;;;;;;;;;;;;;;;;;;;
;;; API parsing ;;;
;;;;;;;;;;;;;;;;;;;

(defn load-cljs-api-edn
  []
  (edn/read-string (slurp (io/file (io/resource "cljs-api.edn")))))

(defn api-edn->api-map
  "Given the cljs edn map, generate a new map with only interesting
  keys (in the form of a req of keyseq)"
  [cljs-api-edn]
  (let [filtered-map (filter-kv api-edn-keyseqs cljs-api-edn)
        selected-symbols (filter #(selected-namespaces (-> % second :ns)) (:symbols cljs-api-edn))
        symbol-name-map (into {} (api-symbols->name-map selected-symbols))]
    (assoc filtered-map
           :symbols (reduce (fn [symbol-map [symbol-k symbol-v]]
                              (assoc symbol-map symbol-k (-> symbol-v
                                                             assoc-description-html
                                                             assoc-example-strings
                                                             assoc-example-htmls)))
                            {} symbol-name-map))))

(defn exit
  [code]
  (flush)
  (shutdown-agents)
  (System/exit code))

(defn -main
  [& args]
  (when (seq args)
    (do (.println *err* "This script does not want params.\n\nUsage: lein run -m cljs-api.generator/-main.")
        (exit 255)))
  (let [file-name "src/cljs/cljs_repl_web/cljs_api.cljs"
        api-edn (load-cljs-api-edn)
        new-edn-map (api-edn->api-map api-edn)
        file-content (str "(ns cljs-repl-web.cljs-api)\n\n"
                          "(def cljs-api-edn " (with-out-str (pprint new-edn-map)) ")")]
    (try
      (spit file-name file-content :encoding "UTF-8")
      (catch Exception e
        (.printStackTrace e *err*)
        (exit 254)))
    (println "Cljs api dumped in" file-name))
  (exit 0))
