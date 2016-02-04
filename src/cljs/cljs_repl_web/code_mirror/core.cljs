(ns cljs-repl-web.code-mirror.core
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe dispatch]]
            [cljs-repl-web.code-mirror.handlers :as handlers]
            [cljs-repl-web.code-mirror.subs :as subs]
            [cljs-repl-web.code-mirror.editor :as editor]
            [cljs-repl-web.code-mirror.common :as common]
            [cljs-repl-web.code-mirror.replumb :as replumb]
            [cljs-repl-web.code-mirror.utils :as utils]))

;;; many parts are taken from jaredly's reepl
;;; https://github.com/jaredly/reepl

(defn make-handlers []
  {:add-input    #(dispatch [:add-console-input :cljs-console %1 %2])
   :add-result   #(dispatch [:add-console-result :cljs-console %1 %2])
   :go-up        #(dispatch [:console-go-up :cljs-console %])
   :go-down      #(dispatch [:console-go-down :cljs-console %])
   :clear-items  #(dispatch [:clear-console-items :cljs-console %])
   :set-text     #(dispatch [:console-set-text :cljs-console %1])
   :add-log      #(dispatch [:add-console-log :cljs-console %])})

(defn display-output-item
  ([value]
   (display-output-item value false))
  ([value error?]
   (println value ", " error?)
   [:div
    {:on-click #(dispatch [:focus-console-editor :cljs-console])
     :class (str "cm-console-item" (when error? " error-cm-console-item"))}
    value]))

(defn display-repl-item
  [item]
  (if-let [text (:text item)]
    [:div.cm-console-item
     {:on-click #(do (dispatch [:console-set-text :cljs-console text])
                     (dispatch [:focus-console-editor :cljs-console]))}
     [utils/colored-text (str (:ns item) "=> " text)]]

    (if (= :error (:type item))
      (display-output-item (.-message (:value item)) true)
      (display-output-item (:value item)))))

(defn repl-items [items]
  (into [:div] (map display-repl-item items)))

(defn console []
  (let [execute #(replumb/run-repl %1 {} %2)
        {:keys [add-input
                add-result
                go-up
                go-down
                clear-items
                set-text
                add-log]} (make-handlers)

        items (subscribe [:get-console-items :cljs-console])
        text  (subscribe [:get-console-current-text :cljs-console])

        submit (fn [text]
                 (let [text (.trim text)]
                   (when (< 0 (count text))
                     (set-text text)
                     (add-input text (replumb/current-ns))
                     (execute text #(add-result (not %1) %2))
                     ;; todo - rethink better?
                     (when-let [example @(subscribe [:get-next-example :cljs-console])]
                       (set-text example)
                       (dispatch [:delete-first-example :cljs-console])))))]

    (reagent/create-class
     {:reagent-render
      (fn []
        [:div.cm-console
         {:on-click #(dispatch [:focus-console-editor :cljs-console])}
         [repl-items @items]
         [editor/editor
          text
          (merge
           editor/default-cm-opts
           {:on-up go-up
            :on-down go-down
            :on-change set-text
            :on-eval submit
            :get-prompt replumb/get-prompt
            :should-eval (fn [source _ _]
                           (not (replumb/multiline? source)))})]])
      :component-did-update
      (fn [this]
        (common/scroll-to-el-bottom (.-parentElement (reagent/dom-node this))))})))
