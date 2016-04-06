(ns cljs-repl-web.core
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch dispatch-sync subscribe]]
            [re-com.core :refer [p h-box v-box box gap line]]
            [devtools.core :as devtools]
            [cljs-repl-web.handlers]
            [cljs-repl-web.subs]
            [cljs-repl-web.views :as views]
            [cljs-repl-web.views.utils :as utils]
            [cljs-repl-web.replumb-proxy :as replumb-proxy]
            [cljs-repl-web.config :as config]
            [cljs-repl-web.localstorage :as ls]
            [cljs-repl-web.app :as app]
            [re-console.common :as common]
            [re-complete.core :as re-complete]))

(defonce console-key :cljs-console)

;; https://github.com/binaryage/cljs-devtools/releases/tag/v0.5.3
(when-not (:production? config/defaults)
  (devtools/set-pref! :install-sanity-hints true)
  (devtools/install!))

(enable-console-print!)

(defn ^:export main []
  (let [{:keys [name verbose-repl? src-paths]} config/defaults
        local-storage-values (ls/get-local-storage-values)]
    (dispatch [:options console-key {:trim-chars "[](){}"}])
    (println "[Entering]" name)
    (dispatch-sync [:initialize config/defaults local-storage-values])
    (reagent/render [views/repl-component console-key {:eval-opts (replumb-proxy/eval-opts verbose-repl? src-paths)
                                                       :mode (:mode local-storage-values)
                                                       :mode-line? true
                                                       :on-after-change #(do (dispatch [:input console-key (common/source-without-prompt (.getValue %))])
                                                                             (app/create-dictionary (common/source-without-prompt (.getValue %)) console-key)
                                                                             (utils/align-suggestions-list))}]
                    (.getElementById js/document "app-center"))
    (reagent/render [views/bottom-panel] (.getElementById js/document "app-bottom"))
    (reagent/render [views/footer-component] (.getElementById js/document "app-footer"))))
