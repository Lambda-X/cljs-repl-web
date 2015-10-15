(ns cljs-bootstrap.load)

;; From mfikes/planck

(defn- skip-load?
  [{:keys [name macros]}]
  (or
    (= name 'cljs.core)
    (and (= name 'cljs.pprint) macros)
    (and (= name 'cljs.test) macros)
    (and (= name 'clojure.template) macros)))

(defn js-load
  [{:keys [name macros path file] :as full} cb]
  (cond
    (skip-load? full) (cb {:lang   :js
                           :source ""})
    ;; file (do-load-file file cb)
    ;; (re-matches #"^goog/.*" path) (do-load-goog name cb)
    ;; :else (do-load-other path macros cb)
    ))
