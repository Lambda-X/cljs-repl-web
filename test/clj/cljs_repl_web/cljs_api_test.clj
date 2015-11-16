(ns cljs-repl-web.cljs-api-test
  (:require [clojure.test :refer :all]
            [cljs-browser-repl.cljs-api :as ca]))

(def edn-part
  {:release { ;; clojurescript
             :cljs-version     "0.0-3297",
             :cljs-tag         "r3297",
             :cljs-date        "2015-05-23",

             ;; clojure
             :clj-version      "1.7.0-beta2",
             :clj-tag          "clojure-1.7.0-beta2",

             ;; tools.reader
             :treader-version  "0.9.2",
             :treader-tag      "tools.reader-0.9.2",

             ;; google closure library
             :gclosure-lib     "0.0-20150505-021ed5b3"}
   :hail {:all "Clojure Coders"}})

(def single-keyseqs #{[:release :cljs-version]})
(def multi-keyseqs #{[:release :gclosure-lib] [:release :cljs-tag] [:hail :all]})

(def edn-symbols
  {:symbols {"cljs.core/keys"
             { ;; auto-parsed docs

              :full-name         "cljs.core/keys",
              :full-name-encode  "cljs.core_keys", ;; used for filenames
              :ns                "cljs.core",
              :name              "keys",
              :type              "function",
              :docstring         "Returns a sequence of the map's keys.",
              :signature         ["[hash-map]"],
              :history           [["+" "0.0-927"]],
              :source {:code     "... full source code ...",
                       :repo     "clojurescript"
                       :tag      "r3297"
                       :filename "src/main/cljs/cljs/core.cljs",
                       :lines    [7559 7563]},

              ;; equivalent symbol in Clojure (if applicable)
              :clj-symbol        "clojure.core/keys",
              ;; syntax usage strings
              ;; (see cljsdoc/syntax_inst-literal.cljsdoc for example)
              :usage []

              ;; alternate display name (if :full-name is a pseudo-symbol that shouldn't be displayed)
              ;; for example, syntax/queue-literal should be displayed "#queue [] literal"
              :display ""

              ;; syntax equivalency to clojure or edn if applicable
              :clj-doc "... external URL for clojure syntax doc ..."
              :edn-doc "... external URL for edn syntax doc ..."

              ;; manually added docs from cljsdoc/ files

              :description "... description in markdown ..."
              :examples [{:id "a34fe2"
                          :content "... example in markdown ..."}]
              :related ["cljs.core/foo" "cljs.core/bar"]}}})

(deftest generate-cljs-api-map
  (is (= {:release {:cljs-version "0.0-3297"}} (ca/filter-kv single-keyseqs edn-part)) "extracting a single key seq from cljs edn")
  (is (= {:release {:gclosure-lib "0.0-20150505-021ed5b3"
                    :cljs-tag "r3297"}
          :hail {:all "Clojure Coders"}} (ca/filter-kv multi-keyseqs edn-part)) "extracting many key seqs from cljs edn")

  ;; testing just a few
  (let [sym (first (:symbols edn-symbols))
        new-symbol-map (ca/api-symbol->new-value sym)]
    (is (= (:name (val sym)) (ca/api-symbol->new-key sym)) "the new key should be the symbol :name")
    (is (:full-name new-symbol-map) ":full-name should be in the new-value for a symbol")
    (is (:examples new-symbol-map) ":example should be in the new-value for a symbol")))
