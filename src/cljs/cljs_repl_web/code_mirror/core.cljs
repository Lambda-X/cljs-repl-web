(ns cljs-repl-web.code-mirror.core
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe dispatch]]
            [cljs-repl-web.code-mirror.handlers :as handlers]
            [cljs-repl-web.code-mirror.subs :as subs]
            [cljs-repl-web.code-mirror.editor :as editor]
            [cljs-repl-web.code-mirror.common :as common]
            [cljs-repl-web.code-mirror.utils :as utils]))

;;; many parts are taken from jaredly's reepl
;;; https://github.com/jaredly/reepl

(defn make-handlers [console-key]
  {:add-input    #(dispatch [:add-console-input console-key %1 %2])
   :add-result   #(dispatch [:add-console-result console-key %1 %2])
   :go-up        #(dispatch [:console-go-up console-key %])
   :go-down      #(dispatch [:console-go-down console-key %])
   :clear-items  #(dispatch [:clear-console-items console-key %])
   :set-text     #(dispatch [:console-set-text console-key %1])
   :add-log      #(dispatch [:add-console-log console-key %])})

(defn display-output-item
  ([console-key value]
   (display-output-item console-key value false))
  ([console-key value error?]
   [:div
    {:on-click #(dispatch [:focus-console-editor console-key])
     :class (str "cm-console-item" (when error? " error-cm-console-item"))}
    value]))

(defn display-repl-item
  [console-key item]
  (if-let [text (:text item)]
    [:div.cm-console-item
     {:on-click #(do (dispatch [:console-set-text console-key text])
                     (dispatch [:focus-console-editor console-key]))}
     [utils/colored-text (str (:ns item) "=> " text)]]

    (if (= :error (:type item))
      (display-output-item console-key (.-message (:value item)) true)
      (display-output-item console-key (:value item)))))

(defn repl-items [console-key items]
  (into [:div] (map (partial display-repl-item console-key) items)))

(defn console [console-key eval-opts]
  (let [{:keys [add-input
                add-result
                go-up
                go-down
                clear-items
                set-text
                add-log]} (make-handlers console-key)

        {:keys [get-prompt
                should-eval
                evaluate]} eval-opts

        items (subscribe [:get-console-items console-key])
        text  (subscribe [:get-console-current-text console-key])
        submit (fn [source]
                 (evaluate #(dispatch [:on-eval-complete console-key %])
                           source))]

    (reagent/create-class
     {:reagent-render
      (fn []
        [:div.cm-console
         {:on-click #(dispatch [:focus-console-editor console-key])}
         [repl-items console-key @items]
         [editor/editor
          text
          (merge
           editor/default-cm-opts
           {:on-up go-up
            :on-down go-down
            :on-change set-text
            :on-eval submit
            :get-prompt get-prompt
            :should-eval should-eval})]])
      :component-did-update
      (fn [this]
        (common/scroll-to-el-bottom! (.-parentElement (reagent/dom-node this))))})))
