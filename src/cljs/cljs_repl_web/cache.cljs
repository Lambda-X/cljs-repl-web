(ns cljs-repl-web.cache
  (:require [cljs.js :as cljs]
            [cognitect.transit :as transit]
            [replumb.repl :as replumb-repl])
  (:import [goog.events EventType]
           [goog.net XhrIo]))

(def cache-url "/js-cache/core.cljs.cache.aot.json")

(defn get-file [url cb]
  (.send XhrIo url
    (fn [e]
      (cb (.. e -target getResponseText)))))

(defn get-cljs-core-cache []
  (get-file cache-url
    (fn [txt]
      (let [rdr   (transit/reader :json)
            cache (transit/read rdr txt)]
        (cljs/load-analysis-cache! replumb-repl/st 'cljs.core cache)))))
