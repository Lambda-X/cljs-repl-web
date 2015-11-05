(ns cljs-browser-repl.gist
  (:require [ajax.core :as ajax]
            [goog.crypt.base64 :as b64]))

;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;  Gist integration  ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce gist-api-url "https://api.github.com")

(defn generate-gist-filename
  "Generates a file name for a gist."
  [prefix ext]
  (str prefix \- (.getTime (js/Date.)) \. ext))

(defn encode64
  "Encodes the username and password."
  [username password]
  (b64/encodeString (str username ":" password)))

(defn create-gist
  "Given a username and password, creates a gist via an ajax call.
  On success `handler` will be called, otherwise `error-handler`"
  [username password text handler error-handler]
  (ajax/ajax-request
   {:uri (str gist-api-url "/gists")
    :method :post
    :headers {:Authorization  (str "Basic " (encode64 username password))}
    :params  {:description "ClojureScript REPL dump"
              :public true
              :files { (generate-gist-filename "dump" "cljs") {:content text}} }
    :handler handler
    :error-handler error-handler
    :format (ajax/json-request-format)
    :response-format (ajax/json-response-format {:keywords? true})} ))

