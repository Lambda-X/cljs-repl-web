(ns cljs-repl-web.core
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch dispatch-sync]]
            [re-com.core :refer [p h-box v-box box gap line]]
            [devtools.core :as devtools]
            [cljs-repl-web.handlers]
            [cljs-repl-web.subs]
            [cljs-repl-web.views :as views]
            [cljs-repl-web.replumb-proxy :as replumb-proxy]
            [cljs-repl-web.config :as config]))

(defonce console-key :cljs-console)

(devtools/set-pref! :install-sanity-hints true) ; this is optional
(devtools/install!)

(enable-console-print!)

(defn ^:export main []
  (let [{:keys [name verbose-repl? src-paths]} config/defaults ]
    (println "[Entering]" name)
    (dispatch-sync [:initialize config/defaults])
    (reagent/render [views/repl-component console-key (replumb-proxy/eval-opts verbose-repl? src-paths)]
                    (.getElementById js/document "app-center"))
    (reagent/render [views/bottom-panel] (.getElementById js/document "app-bottom"))
    (reagent/render [views/footer-component] (.getElementById js/document "app-footer"))))
