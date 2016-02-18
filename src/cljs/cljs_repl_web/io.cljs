(ns cljs-repl-web.io
  (:require [cljs.js :as cljs]
            [clojure.string :as string]
            [cognitect.transit :as transit]
            [replumb.repl :as replumb-repl])
  (:import [goog.events EventType]
           [goog.net XhrIo]))

(defn fetch-file!
  "Very simple implementation of XMLHttpRequests that given a file path
  calls src-cb with the string fetched of nil in case of error.

  See doc at https://developers.google.com/closure/library/docs/xhrio"
  [file-url src-cb]
  (try
    (.send XhrIo file-url
           (fn [e]
             (if (.isSuccess (.-target e))
               (src-cb (.. e -target getResponseText))
               (src-cb nil))))
    (catch :default e
      (src-cb nil))))

(defn load-cljs-core-cache!
  "Load core.cljs.cache.aot.json given the url"
  [cache-url]
  (fetch-file! cache-url
               (fn [txt]
                 (let [rdr   (transit/reader :json)
                       cache (transit/read rdr txt)]
                   (cljs/load-analysis-cache! replumb-repl/st 'cljs.core cache)))))

(defn print-version!
  "Return the current version from the version.properties file."
  [version-path]
  (fetch-file! version-path
               (fn [content]
                 (let [version (second (string/split (->> (string/split-lines content)
                                                          (remove #(= "#" (first %)))
                                                          first)
                                                     #"=" 2))]
                   (println "[Version]" version)))))

(comment
  (def s "#Tue Feb 16 13:27:59 PST 2016\nVERSION=0.2.2-ar\nOTHER=STUFF")
  )
