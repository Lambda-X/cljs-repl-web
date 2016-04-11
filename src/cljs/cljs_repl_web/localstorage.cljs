(ns cljs-repl-web.localstorage)

;;; see https://gist.github.com/daveliepmann/cf923140702c8b1de301

;;; Utils functions

(defn set-item!
  "Set `key' in browser's localStorage to `val`."
  [key val]
  (.setItem (.-localStorage js/window) key val))

(defn get-item
  "Returns value of `key' from browser's localStorage."
  [key]
  (.getItem (.-localStorage js/window) key))

(defn remove-item!
  "Remove the browser's localStorage value for the given `key`"
  [key]
  (.removeItem (.-localStorage js/window) key))

;;; getters

(defn get-local-storage-values
  []
  {:mode  (if-let [mode (get-item "cljs-repl-web-mode")]
            (keyword mode)
            :indent-mode)
   :username (or (get-item "cljs-repl-web-username") "")})

(defn save-mode!
  [mode]
  (set-item! "cljs-repl-web-mode" (name mode)))

(defn save-username!
  [mode]
  (set-item! "cljs-repl-web-username" (name mode)))

