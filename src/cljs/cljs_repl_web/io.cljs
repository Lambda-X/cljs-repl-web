(ns cljs-repl-web.io
  (:require [cljs.js :as cljs]
            [cognitect.transit :as transit]
            [replumb.repl :as replumb-repl])
  (:import [goog.events EventType]
           [goog.net XhrIo]))

;;;;;;;;;;
;;  IO  ;;
;;;;;;;;;;

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
