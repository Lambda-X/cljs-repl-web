(ns cljs-utils.caching  
  (:require [clojure.java.io :as io]
            [cognitect.transit :as transit])
  (:import [java.io ByteArrayOutputStream]))

(def out-dir  "resources/public/js-cache/")
(def filename "core.cljs.cache.aot.json")

(defn mkdirp [path]
  (let [dir (java.io.File. path)]
    (if (.exists dir)
      true
      (.mkdirs dir))))

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
  (mkdirp out-dir)
  (dump-core-analysis-cache (str out-dir filename))
  (println "End."))
