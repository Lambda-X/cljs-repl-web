(ns cljs-utils.caching  
  (:require [clojure.java.io :as io]
            [cognitect.transit :as transit])
  (:import [java.io ByteArrayOutputStream]))

(def out-dir  "resources/public/js-cache/")
(def filename "core.cljs.cache.aot.json")

(defn dump-core-analysis-cache
  [out-path]
  (let [cache (read-string
               (slurp (io/resource "cljs/core.cljs.cache.aot.edn")))
        out (ByteArrayOutputStream. 1000000)
        writer (transit/writer out :json)]
    (transit/write writer cache)
    (spit (io/file out-path) (.toString out))))

(defn -main
  [& args]
  (println "Starting dump...")
  (dump-core-analysis-cache (str out-dir filename))
  (println "End."))
