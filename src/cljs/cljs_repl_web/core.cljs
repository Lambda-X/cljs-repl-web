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

;; https://github.com/binaryage/cljs-devtools/releases/tag/v0.5.3
(when-not (:production? config/defaults)
  (devtools/set-pref! :install-sanity-hints true)
  (devtools/install!))

(enable-console-print!)

(defn ^:export main []
  (let [{:keys [name verbose-repl? src-paths]} config/defaults ]
    (println "[Entering]" name)
    (dispatch-sync [:initialize config/defaults])
    (reagent/render [views/repl-component console-key {:eval-opts (replumb-proxy/eval-opts verbose-repl? src-paths)
                                                       :mode :indent-mode
                                                       :mode-line? true}]
                    (.getElementById js/document "app-center"))
    (reagent/render [views/bottom-panel] (.getElementById js/document "app-bottom"))
    (reagent/render [views/footer-component] (.getElementById js/document "app-footer"))))
