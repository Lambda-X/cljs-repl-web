(ns cljs-utils.caching
  (:require [clojure.java.io :as io]
            [cognitect.transit :as transit])
  (:import [java.io ByteArrayOutputStream]))

(defn ->transit-json
  "Convert a transit file to a transit json file.
  Returns the out-path on completion or nil if it fails."
  [src-path out-path]
  (try
    (let [resource (io/file src-path)
          cache (read-string (slurp resource))
          out (ByteArrayOutputStream. 1000000)
          writer (transit/writer out :json)
          out-file (io/file out-path)]
      (transit/write writer cache)
      (spit out-file (.toString out "UTF-8"))
      out-file)
    (catch Exception e
      (.printStackTrace e *err*)
      nil)))
