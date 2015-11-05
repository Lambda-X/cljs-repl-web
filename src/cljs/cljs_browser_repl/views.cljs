(ns cljs-browser-repl.views
  (:require [reagent.core :as reagent]
            [re-com.core :refer [md-icon-button h-box v-box box gap button input-text
                                 popover-content-wrapper popover-anchor-wrapper]]
            [re-com.util :refer [px]]
            [cljs-browser-repl.app :as app]
            [cljs-browser-repl.gist :as gist]
            [cljs-browser-repl.console :as console]
            [cljs-browser-repl.console.cljs :as cljs]))

;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Reagent helpers  s ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn cljs-console-did-mount
  [console-opts]
  (js/$
   (fn []
     (let [jqconsole (cljs/cljs-console! console-opts)]
       (app/add-console! :cljs-console jqconsole)
       (cljs/cljs-console-prompt! jqconsole)))))

(defn cljs-console-render []
  [:div.cljs-console.console])

;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Reagent components ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn cljs-console-component
  "Creates a new instance of e which loads on the input
  selector (any jQuery selector will work) and configuration. The
  options are passed as named parameters and follow the jq-console ones.

  * :welcome-string is the string to be shown when the terminal is first
    rendered. Defaults to nil.
  * :prompt-label is the label to be shown before the input when using
    Prompt(). Defaults to namespace=>.
  * :continue-label is the label to be shown before the continued lines
    of the input when using Prompt(). Defaults to nil.
  * :disable-auto-focus is a boolean indicating whether we should disable
    the default auto-focus behavior. Defaults to true, the console never
    takes focus."
  []
  (fn [console-opts]
    (println "Building ClojureScript React component")
    (reagent/create-class {:display-name "cljs-console-component"
                           :reagent-render cljs-console-render
                           :component-did-mount #(cljs-console-did-mount console-opts)})))

(defn gist-login-popover-dialog-body
  [showing? auth-data ok-fn cancel-fn]
  [popover-content-wrapper
   :showing? showing?
   :on-cancel cancel-fn
   :position :right-center
   :width "280"
   :backdrop-opacity 0.4
   :title "Github login"
   :body [(fn []
            [v-box
             :children [[v-box
                         :size "auto"
                         :children [[:label {:for "pf-username"} "Username"]
                                    [input-text
                                     :model (:username @auth-data)
                                     :on-change #(swap! auth-data assoc :username %)
                                     :placeholder "Enter username"
                                     :class "form-control"
                                     :attr {:id "pf-username"}]
                                    [:label {:for "pf-password"} "Password"]
                                    [input-text
                                     :model (:password @auth-data)
                                     :on-change #(swap! auth-data assoc :password %)
                                     :placeholder "Enter password"
                                     :class "form-control"
                                     :attr {:id "pf-password" :type "password"}]]]
                        [gap :size "20px"]
                        [h-box
                         :gap      "10px"
                         :children [[button
                                     :label [:span [:i {:class "zmdi zmdi-check" }] " Login"]
                                     :on-click ok-fn
                                     :class "btn-primary"]
                                    [button
                                     :label [:span [:i {:class "zmdi zmdi-close" }] " Cancel"]
                                     :on-click cancel-fn]]]]])]])

(defn gist-error-handler [{:keys [status status-text]}]
  (js/alert (str "An error occurred: " status " " status-text)) )

(defn on-gist-created [[ok response]]
  "Handles the response after a gist has been created (or not)."
  (if ok
    (.open js/window (:html_url response) "_blank")
    (js/alert "An error occured: unable to create gist.")))

(defn gist-login-popover-dialog
  []
  (let [showing? (reagent/atom false)
        auth-data (reagent/atom {:username "" :password ""})
        save-auth-data (reagent/atom nil)
        ok-fn #(do (reset! showing? false)
                   (let [{:keys [username password]} @auth-data
                         text (console/dump-console! (app/console :cljs-console))]
                     (gist/create-gist username password text on-gist-created gist-error-handler)))
        cancel-fn #(do
                     (reset! auth-data @save-auth-data)
                     (reset! showing? false))]
    [popover-anchor-wrapper
     :showing? showing?
     :position :right-center
     :anchor   [md-icon-button
                :md-icon-name "zmdi-github"
                :on-click #(do (reset! showing? true)
                               (swap! auth-data  merge {:password "" })
                               (reset! save-auth-data @auth-data))
                :class "cljs-btn"
                :tooltip "Create Gist"
                :tooltip-position :below-center
                :disabled? (not (app/console-created? :cljs-console))]
     :popover  [gist-login-popover-dialog-body showing? auth-data ok-fn cancel-fn]]))

(defn cljs-button-components []
  "Return a vector of components containing the cljs console buttons.
   To place them in a layout, call the function (it does not return a
   component)."
  [v-box
   :gap "4px"
   :children [[md-icon-button
               :md-icon-name "zmdi-delete"
               :on-click #(cljs/cljs-reset-console-and-prompt! (app/console :cljs-console))
               :class "cljs-btn"
               :tooltip "Reset"
               :tooltip-position :left-center
               :disabled? (not (app/console-created? :cljs-console))]
              [md-icon-button
               :md-icon-name "zmdi-format-clear-all"
               :on-click #(cljs/cljs-clear-console! (app/console :cljs-console))
               :class "cljs-btn"
               :tooltip "Clear"
               :tooltip-position :left-center
               :disabled? (not (app/console-created? :cljs-console))]
              [gist-login-popover-dialog]]])


