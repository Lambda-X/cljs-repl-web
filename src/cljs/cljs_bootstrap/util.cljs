(ns cljs-bootstrap.util)

(defn debug-prn
  [& args]
  (binding [*out* *err*]
    (apply println args)))

(defn build-error
  ([msg] (build-error msg nil))
  ([msg cause]
   (ex-info msg
            {:tag :bootstrap/repl-error}
            cause)))
