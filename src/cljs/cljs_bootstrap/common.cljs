(ns cljs-bootstrap.common
  (:require [clojure.string :as string]))

(defn extract-message
  "Iteratively extracts messages inside (nested #error objects), returns
  a string. Be sure to pass #error object here."
  ([err]
   (extract-message err false))
  ([err print-stack?]
   (str (loop [e err msgs [(.-message err)]]
          (if-let [next-err (.-cause e)]
            (recur next-err (conj msgs (.-message next-err)))
            (if (seq msgs)
              (string/join " - " msgs)
              "")))
        (when (and err print-stack?)
          (str "\n" (.-stack err))))))

(defn echo-callback
  "Callback that just echoes the result map. It also asserts the correct
  result format in its post condition. Useful for debugging and
  testing."
  {:post [(map? %) (find % :success?) (or (find % :error) (find % :value))]} ;; TODO, use dire or schema
  [result-map]
  result-map)

(defn unwrap-result
  "Unwraps the result of an evaluation.

  It returns the content of `:value` in case of success and the content
  of `:error` (a `js/Error`) in case of failure."
  [result-map]
  (if (:success? result-map)
    (:value result-map)
    (:error result-map)))

(defn success?
  "Given a `result-map`, tells whether the evaluation was successful."
  [result-map]
  (:success? result-map))

(defn result-map->string
  "Given a `result-map`, returns the result of an evaluation as string."
  ([result]
   (result-map->string result false))
  ([result-map print-stack?]
   (if (:success result-map)
     (:value result-map)
     (extract-message (:error result-map) print-stack?))))

(defn wrap-success
  "Wraps the message in a success map."
  [message]
  {:value message})

(defn wrap-error
  "Wraps the message in a error map."
  [message]
  {:error message})

(defn inline-newline?
  "Returns true if the string contains the newline \\\\n or \\\\r as
  characters."
  [s]
  (re-matches #"\\{2,}n|\\{2,}r" s))

(defn valid-eval-result?
  "Is the string returned from an evaluation valid?"
  [result]
  (and (string? result) (not (inline-newline? result))))

(defn valid-eval-error?
  "Is the string returned from an evaluation valid?"
  [error]
  (instance? js/Error error))

(defn error-keyword-not-supported
  "Yields a \"keyword not supported\" error map. Receives the
  symbol/keyword printed in the message and ex-info data."
  [keyword ex-info-data]
  (wrap-error (ex-info (str "The " keyword " keyword is not supported at the moment")
                       ex-info-data)))

(defn error-argument-must-be-symbol
  "Yields a \"Argument must a be a symbol\" error map. Receives the
  symbol/fn name printed in the message and ex-info data."
  [symbol ex-info-data]
  (wrap-error (ex-info (str "Argument to " symbol " must be a symbol") ex-info-data)))
