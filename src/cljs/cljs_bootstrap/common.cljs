(ns cljs-bootstrap.common)

(defn echo-callback
  "Echoes the input success and result, returning [success,
  result]. Useful for debugging."
  [success result]
  [success result])

(def result "Returns the result of an evaluation" second)

(def success? "Returns if the evaluation was successful" first)



(defn inline-newline?
  "Returns true if the string contains the newline \\\\n or \\\\r as
  characters."
  [s]
  (re-matches #"\\{2,}n|\\{2,}r" s))

(defn valid-eval-result?
  "Is the string returned from an evaluation valid?"
  [result]
  (and (string? result) (not (inline-newline? result))))

(defn extract-message
  "Iteratively extracts messages inside (nested #error objects), returns
  a string. Be sure to pass #error object here."
  [err]
  (loop [e err msgs [(.-message err)]]
    (if-let [next-err (.-cause e)]
      (recur next-err (conj msgs (.-message next-err)))
      (if (seq msgs)
        (clojure.string/join " - " msgs)
        ""))))
