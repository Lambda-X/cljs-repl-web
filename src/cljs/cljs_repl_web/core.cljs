(ns cljs-repl-web.core
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch dispatch-sync]]
            [re-com.core :refer [p h-box v-box box gap line]]
            [devtools.core :as devtools]
            [cljs-repl-web.cache :as cache]
            [cljs-repl-web.handlers]
            [cljs-repl-web.subs]
            [cljs-repl-web.views :as views]))

;; (defonce conn (repl/connect "http://localhost:9000/repl"))

(devtools/set-pref! :install-sanity-hints true) ; this is optional
(devtools/install!)

(enable-console-print!)

(defn ^:export main []
  (println "In cljs-browser-repl.core/main")

  ;; we load the cljs core cache manually in order to reduce the app size
  ;; see https://github.com/clojure/clojurescript/wiki/Optional-Self-hosting for more info
  ;; see also related issue in replbum https://github.com/ScalaConsultants/replumb/issues/42
  (cache/get-cljs-core-cache)

  (dispatch-sync [:initialize])
  (reagent/render [views/repl-component] (.getElementById js/document "app-center"))
  (reagent/render [views/bottom-panel] (.getElementById js/document "app-bottom"))
  (reagent/render [views/footer-component] (.getElementById js/document "app-footer")))
