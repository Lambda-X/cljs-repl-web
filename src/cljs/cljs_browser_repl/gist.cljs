(ns cljs-browser-repl.gist
  (:require [ajax.core :as ajax]
            [goog.crypt.base64 :as b64]))

;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;  Gist integration  ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;

(def gist-token (atom ""))
(def gist-base-url "https://api.github.com")

(defn gist-error-handler [{:keys [status status-text]}]
  (.log js/console (str "An error occurred: " status " " status-text)))

(defn generate-gist-filename
  "Generates a file name for a gist."
  [prefix ext]
  (str prefix \- (.getTime (js/Date.)) \. ext))

(defn generate-auth-note
  "Generates a unique authentication note in order to retrieve a new token."
  []
  (str "gist-basic-auth" (.getTime (js/Date.))))

(defn on-gist-uploaded [[ok response]]
  "Handles the response after a gist has been created (or not)."
  (if ok
    (.open js/window (:html_url response) "_blank")
    (js/alert "An error occured: unable to create gist.")))

(defn encode64
  "Encodes the username and password."
  [username password]
  (b64/encodeString (str username ":" password)))

(defn upload-gist
  "Creates a gist via an ajax call."
  [text]
  (ajax/ajax-request
   {:uri (str gist-base-url "/gists")
    :method :post
    :headers {:Authorization (str "token " @gist-token)}
    :params  {:description "ClojureScript REPL dump"
              :public true
              :files { (generate-gist-filename "dump" "cljs") {:content text}} }
    :handler on-gist-uploaded
    :error-handler gist-error-handler
    :format (ajax/json-request-format)
    :response-format (ajax/json-response-format {:keywords? true})} ))

(defn authenticate-gist
  "Given a username and password, request an authentication token
  in order to access the gist API. When the authentication succeeds
  a parametless `cb` is called. "
  [username password cb]
  (ajax/ajax-request
   {:uri (str gist-base-url "/authorizations")
    :method :post
    :headers {:Authorization (str "Basic " (encode64 username password))}
    :params  {:scopes ["gist"]
              :note (generate-auth-note)}
    :handler (fn [[ok response]]
               (if ok
                 (do
                   (reset! gist-token (response :token))
                   (when (fn? cb) (cb)))
                 (js/alert "An error occured: unable to authenticate.")))
    :error-handler gist-error-handler
    :format (ajax/json-request-format)
    :response-format (ajax/json-response-format {:keywords? true})} ))

(defn create-gist
  [text]
  (if (empty? @gist-token)
    (let [user (.prompt js/window "User")
          pwd (.prompt js/window "Password")]
      (authenticate-gist user pwd #(upload-gist text)))
    (upload-gist text)))

