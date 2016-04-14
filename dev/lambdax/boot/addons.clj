(ns lambdax.boot.addons
  {:boot/export-tasks true}
  (:require [boot.core :as core :refer [boot]]
            [boot.pod :as pod]
            [boot.util :as util]
            [clojure.java.io :as io]
            [cognitect.transit :as transit])
  (:import [java.io ByteArrayOutputStream]))

(defn transit-json
  "Convert a transit file to a transit json file.
  Returns the new java.io.File on completion or nil if it fails."
  [src-path out-path]
  (try
    (let [cache (read-string (slurp (io/file src-path)))
          out (ByteArrayOutputStream. 1000000)]
      (transit/write (transit/writer out :json) cache)
      (doto (io/file out-path)
        (spit (.toString out "UTF-8"))))
    (catch Exception e
      (.printStackTrace e *err*)
      nil)))

(defn normalize-path
  "Adds a / if missing at the end of the path."
  [path]
  (str path (when-not (= "/" (last path)) "/")))
