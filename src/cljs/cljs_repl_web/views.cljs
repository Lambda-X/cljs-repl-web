(ns cljs-repl-web.views
  (:require-macros [re-com.core :refer [handler-fn]])
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [re-com.core :refer [md-icon-button h-box v-box box gap button input-text
                                 popover-content-wrapper popover-anchor-wrapper hyperlink-href
                                 popover-tooltip title label scroller line modal-panel]]
            [re-com.box :refer [flex-child-style]]
            [re-com.util :refer [px]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.app :as app]
            [cljs-repl-web.console :as console]
            [cljs-repl-web.console.cljs :as cljs]
            [cljs-repl-web.cljs-api :as api]
            [cljs-repl-web.cljs-api.utils :as api-utils]
            [cljs-repl-web.views.utils :as utils]
            [cljs-repl-web.markdown :as md]))

(trace-forms {:tracer (tracer :color "orange")}

;; (set! re-com.box/debug true)

;;;;;;;;;;;;;;;;;;;;;;;
;;; Reagent helpers ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(defn cljs-console-did-mount
  [console-opts]
  (js/$
   (fn []
     (let [jqconsole (cljs/cljs-console! console-opts)]
       (dispatch [:add-console :cljs-console jqconsole])
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

;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;      Buttons       ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;

(def login-focus-wrapper
  ;; see http://stackoverflow.com/questions/27602592/reagent-component-did-mount?rq=1
  (with-meta identity
    {:component-did-mount (fn []
                            (let [usr (.getElementById js/document "pf-username")
                                  pwd (.getElementById js/document "pf-password")]
                              (if (empty? (.-value usr))
                                (.focus usr)
                                (.focus pwd))))}))

(defn login-key-down-handler
  [event ok-fn cancel-fn]
  (case (.-which event)
    13 (ok-fn)
    27 (cancel-fn)
    nil))

(defn gist-error-modal-dialog
  []
  (let [message (subscribe [:gist-error-msg])]
    (fn []
      (when (not-empty @message)
        [modal-panel
         :backdrop-opacity 0.5
         :child [v-box
                 :padding  "10px"
                 :align :center
                 :children [[title :label @message :level :level2]
                            [button
                             :label    "OK"
                             :class    "btn-danger"
                             :on-click #(dispatch [:reset-gist-error-msg])]]]]))))

(defn gist-error-handler [{:keys [status status-text]}]
  (dispatch [:set-gist-error-msg (str "An error occurred: " status " " status-text)]))

(defn on-gist-created [[ok response]]
  "Handles the response after a gist has been created (or not)."
  (if ok
    (utils/open-new-window (:html_url response))
    (dispatch [:set-gist-error-msg "An error occured: unable to create gist."])))

(defn gist-login-dialog-body
  []
  (let [showing? (subscribe [:gist-showing?])
        auth-data (subscribe [:gist-auth-data])
        ok-fn #(dispatch [:create-gist :cljs-console auth-data on-gist-created gist-error-handler])
        cancel-fn #(dispatch [:hide-gist-login])]
    (fn []
      [login-focus-wrapper
       [popover-content-wrapper
        :showing? showing?
        :on-cancel cancel-fn
        :position :right-center
        :width "280"
        :backdrop-opacity 0.4
        :title "Github login"
        :body [(fn []
                 [v-box
                  :children [[gist-error-modal-dialog]
                             [v-box
                              :size "auto"
                              :children [[:label {:for "pf-username"} "Username"]
                                         [input-text
                                          :model (:username @auth-data)
                                          :change-on-blur? false
                                          :on-change #(swap! auth-data assoc :username %)
                                          :placeholder "Enter username"
                                          :class "form-control"
                                          :status (when (empty? (:username @auth-data)) :error)
                                          :attr {:id "pf-username"
                                                 :on-key-down #(login-key-down-handler % ok-fn cancel-fn)}]
                                         [:label {:for "pf-password"} "Password"]
                                         [input-text
                                          :model (:password @auth-data)
                                          :change-on-blur? false
                                          :on-change #(swap! auth-data assoc :password %)
                                          :placeholder "Enter password"
                                          :class "form-control"
                                          :status (when (empty? (:password @auth-data)) :error)
                                          :attr {:id "pf-password" :type "password"
                                                 :on-key-down #(login-key-down-handler % ok-fn cancel-fn)}]]]
                             [gap :size "20px"]
                             [h-box
                              :gap      "10px"
                              :children [[button
                                          :label [:span [:i {:class "zmdi zmdi-check" }] " Login"]
                                          :on-click ok-fn
                                          :class "btn-primary"]
                                         [button
                                          :label [:span [:i {:class "zmdi zmdi-close" }] " Cancel"]
                                          :on-click cancel-fn]]]]])]]])))

(defn gist-login-dialog
  []
  (let [is-console-empty? (subscribe [:is-console-empty? :cljs-console])
        showing? (subscribe [:gist-showing?])]
    (fn []
     [popover-anchor-wrapper
      :showing? showing?
      :position :right-center
      :anchor   [md-icon-button
                 :md-icon-name "zmdi-github"
                 :on-click #(dispatch [:show-gist-login])
                 :class "cljs-btn"
                 :tooltip "Create Gist"
                 :tooltip-position :left-center
                 :disabled? @is-console-empty?]
      :popover  [gist-login-dialog-body]])))

(defn cljs-buttons
  "Return a vector of components containing the cljs console buttons.
   To place them in a layout, call the function (it does not return a
   component)."
  []
  (let [console-created? (subscribe [:console-created? :cljs-console])
        example-mode? (subscribe [:example-mode? :cljs-console])]
    (fn cljs-buttons-form2 []
      [v-box
       :gap "8px"
       :children [[md-icon-button
                   :md-icon-name "zmdi-delete"
                   :on-click #(dispatch [:reset-console :cljs-console])
                   :class "cljs-btn"
                   :tooltip "Reset"
                   :tooltip-position :left-center
                   :disabled? (not @console-created?)]
                  [md-icon-button
                   :md-icon-name "zmdi-format-clear-all"
                   :on-click #(dispatch [:clear-console :cljs-console])
                   :class "cljs-btn"
                   :tooltip "Clear"
                   :tooltip-position :left-center
                   :disabled? (not @console-created?)]
                  [gist-login-dialog]
                  [md-icon-button
                   :md-icon-name "zmdi-stop"
                   :on-click #(dispatch [:exit-interactive-examples :cljs-console])
                   :class "cljs-btn"
                   :tooltip "Stop interactive example mode"
                   :tooltip-position :below-center
                   :disabled? (not @example-mode?)]]])))

;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;  API panel section  ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn get-symbol-doc-map
  "Returns the doc map for the symbol from the `cljs-api-edn` parsed  map."
  [symbol]
  (get-in api/cljs-api-edn [:symbols symbol]))

(defn build-signatures-ui
  "Builds a table for the provided signatures of a symbol."
  [signatures]
  [v-box
   :size "0 0 auto"
   :gap "2px"
   :children [(for [s signatures]
                ^{:key s} [label
                           :label s
                           :class "api-panel-signature"])]])

(defn build-symbol-description-ui
  "Builds the UI for the symbol's description in the popover. Desc needs
  to be markdown."
  [desc]
  [v-box
   :size "0 0 auto"
   :gap "4px"
   :children [[title
               :label "Description"
               :level :level4
               :class "api-panel-popup-section-title"]
              ;; we can use `dangerouslySetInnerHTML` or construct the edn from
              ;; the html string (using eg. hickory)
              ;; [:div (map hickory/as-hiccup (hickory/parse-fragment desc))]
              ;; AR - hickory performs better in flexbox container calculation)
              ;; AR - markdown->react component solves issue #
              [label :label [md/md->react-component desc]]]])

(defn build-related-symbols-ui
  "Builds the UI for related symbols in the popover."
  [related]
  [v-box
   :size "0 0 auto"
   :gap "4px"
   :children [[title
               :label "Related"
               :level :level4
               :class "api-panel-popup-section-title"]
              [h-box
               :size "0 0 auto"
               :gap "4px"
               :style {:flex-flow "wrap"}
               :children (for [rel related]
                           [hyperlink-href
                            :label (utils/strip-namespace rel)
                            :href (utils/symbol->clojuredocs-url rel)
                            :target "_blank"])]]])

(defn example-ui
  "The (single) example, usually monospaced, html UI. Wants a map {:html ... :strings}."
  [example-map]
  {:pre [(:strings example-map) (:html example-map)]}
  [v-box
   :size "1 0 auto"
   :gap "2px"
   :children [[label
               :style {:width "100%"}
               :label (utils/html-string->highligthed-hiccup (:html example-map)
                                                             :style {:margin "0"})]]])

(defn example-number-icon
  "From https://github.com/Day8/re-com/blob/master/src/re_com/buttons.cljs"
  [index]
  [:div {:class "api-panel-number-icon noselect"}
   [:i {:class (str "zmdi zmdi-hc-fw-rc zmdi-hc-2x " "zmdi-n-" (inc index) "-square")}]])

(defn example-send-to-repl-button-label
  "https://github.com/Day8/re-com/blob/master/src/re_demo/button.cljs#L80"
  [example-index example-map]
  [:span "Send to REPL"
   [:img {:class "api-panel-send-repl-img zmdi-hc-fw-rc"
          :src "styles/images/cljs.svg"
          :alt "Load the example in the REPL"}]])

(defn example-panel
  "UI for a single example. Wants a map {:html ... :strings}."
  [example-index example-map showing?]
  {:pre [(:strings example-map) (:html example-map)]}
  [v-box
   :size "none"
   :gap "4px"
   :justify :center
   ;; Why the box?
   ;; See problem here, see https://github.com/Day8/re-com/issues/76
   :children [[box
               :class "api-panel-button-send-repl-box"
               :size "1 0 auto"
               :child [button
                       :class "btn btn-default api-panel-button-send-repl"
                       :style (merge (flex-child-style "1 0 auto")
                                     {:width "100%"})
                       :disabled? (not @(subscribe [:console-created? :cljs-console]))
                       :label [h-box
                               :size "1 1 auto"
                               :justify :between
                               :align :center
                               :children [[example-number-icon example-index]
                                          [example-send-to-repl-button-label example-index example-map]]]
                       :on-click (handler-fn
                                  (reset! showing? false)
                                  (dispatch [:send-to-console :cljs-console (:strings example-map)]))]]
              [example-ui example-map]]])

(defn build-examples-ui
  "Builds the UI for the symbol's examples. Wants a list of {:html ... :strings}."
  [examples-map showing?]
  [v-box
   :size "0 1 auto"
   :children [
              [gap :size "4px"]
              [title
               :label "Examples"
               :level :level4
               :class "api-panel-popup-section-title"]
              [v-box
               :size "0 0 auto"
               :gap "2px"
               :children (map-indexed #(example-panel %1 %2 showing?) examples-map)]]])

(defn symbol-popover
  "A popover's body in which details of the given symbol will be shown."
  [showing? popover-position sym-doc-map]
  (let [{name :name
         full-name :full-name
         desc :description
         docstring :docstring
         examples-htmls :examples-htmls
         examples-strings :examples-strings
         sign :signature
         related :related} sym-doc-map
         desc (or desc docstring)       ; some symbols don't have a description so we use
                                        ; the docstring instead; docstring is a regular string,
                                        ; without markdown. Nonetheless, it will be passed to
                                        ; md->react->component function to gain some basic html
                                        ; formatting (like paragraphs)
         examples (map (fn [html string] {:html html :strings string}) examples-htmls examples-strings)
         popover-width  400
         popover-height 400
         popover-content-width (- popover-width (* 2 14) 15)] ; bootstrap padding + scrollbar width
    [popover-content-wrapper
     :showing? showing?
     :position @popover-position
     :on-cancel (handler-fn (reset! showing? false))
     :style {:max-height (str popover-height)
             :max-width (str popover-width)}
     :backdrop-opacity 0.1
     :close-button? false
     :title [h-box
             :gap "6px"
             :align :center
             :children [[title
                         :label name
                         :level :level4]
                        [md-icon-button
                         :md-icon-name "zmdi-info"
                         :tooltip "See online documentation"
                         :tooltip-position :right-center
                         :size :smaller
                         :style {:justify-content :center}
                         :on-click #(utils/open-new-window (utils/symbol->clojuredocs-url full-name))]]]
     :body [(fn []
              [scroller
               :size "1 1 auto"
               :max-width (str popover-width)
               :max-height (str (- popover-height 50))
               :scroll :auto
               :child [v-box
                       :size "1 1 auto"
                       :gap "8px"
                       :width (str popover-content-width)
                       :style {:padding "4px"}
                       :children [(when (not-empty sign)
                                    [build-signatures-ui sign])
                                  (when (not-empty desc)
                                    [build-symbol-description-ui desc])
                                  (when (not-empty related)
                                    [build-related-symbols-ui related])
                                  (when (not-empty examples)
                                    [build-examples-ui examples showing?])]]])]]))
(defn build-symbol-ui
  "Builds the UI for a single symbol. Will be a button."
  [symbol]
  (let [showing? (reagent/atom false)
        popover-position (reagent/atom :below-center)]
    (fn []
     [box
      :size "0 1 auto"
      :align :center
      :class "api-panel-symbol-label-box"
      :child (if-let [symbol (get-symbol-doc-map (str symbol))]
               [popover-anchor-wrapper
                :showing? showing?
                :position @popover-position
                :anchor [button
                         :class "btn btn-default api-panel-symbol"
                         :label (:name symbol)
                         ;; we use :attr's `:on-click` because button's `on-click` accepts
                         ;; a parametless function and we need the mouse click coordinates
                         :attr { :on-click
                                (handler-fn
                                 ;; later we can refactor it into re-frame
                                 ;; see also https://github.com/Day8/re-frame/wiki/Beware-Returning-False#user-content-usage-examples
                                 (reset! popover-position
                                         (utils/calculate-popover-position [(.-clientX event) (.-clientY event)]))
                                 (reset! showing? true))}]
                :popover [symbol-popover showing? popover-position symbol]]
               [label
                :label (str symbol)
                :class "api-panel-symbol api-panel-symbol-label"
                :style (flex-child-style "80 1 auto")])])))

(defn section-title-component
  [section-title]
  [box
   :size "0 0 120px"
   :min-width "120px"
   :class "api-panel-topic-box"
   :child [title
           :label section-title
           :level :level4
           :class "api-panel-topic"]])

(defn build-section-ui
  "Builds the UI for a section."
  [section]
  [v-box
   :size "1 1 auto"
   :gap "4px"
   :children [[title
               :label (:title section)
               :level :level3
               :class "api-panel-section-title"]
              [h-box
               :size "0 1 auto"
               :gap "4px"
               :children [[v-box
                           :size "1 1 auto"
                           :gap "2px"
                           :children (for [topic (:topics section)]
                                       [h-box
                                        :size "1 1 auto"
                                        :gap "2px"
                                        :children [[section-title-component (:title topic)]
                                                   [h-box
                                                    :size "1 1 auto"
                                                    :gap "2px"
                                                    :justify :start
                                                    :style {:flex-flow "wrap"}
                                                    :children (for [symbol (:symbols topic)]
                                                                [build-symbol-ui symbol])]]])]]]]])

(defn build-api-panel-ui
  "Builds the UI for the api panel."
  [sections]
  (let [cols (subscribe [:api-panel-columns]) ;; must be a divisor of 12
        secs (count sections)]
    (fn [sections]
      [h-box
       :size "1 1 auto"
       :gap "10px"
       :children [(for [sections (partition-all (if (zero? (rem secs @cols))
                                                  (quot secs @cols)
                                                  (inc (quot secs @cols))) sections)]
                    ^{:key sections} [v-box
                                      :size (str "0 1 " (quot 100 @cols) "%")
                                      :gap "10px"
                                      :children (for [section sections]
                                                  [build-section-ui section])])]])))

(defn api-panel []
  [build-api-panel-ui (:sections api-utils/custom-api-map)])

(defn footer-component []
  [h-box
   :size "1 1 auto"
   :justify :between
   :align :center
   :class "page-footer"
   :children [[box
               :size "0 1 50%"
               :child [:strong "© Scalac Sp. z o.o. 2015"]]
              [h-box
               :size "0 1 50%"
               :justify :end
               :align :center
               :gap "4px"
               :children [[:span "Connect with us at"]
                          [hyperlink-href
                           :href "https://www.facebook.com/scalac.io"
                           :target "_blank"
                           :class "btn app-footer-btn"
                           :label [md-icon-button
                                   :md-icon-name "zmdi-facebook"
                                   :class "app-footer-btn-icon"]]
                          [hyperlink-href
                           :href "https://twitter.com/scalac_io"
                           :target "_blank"
                           :class "btn app-footer-btn"
                           :label [md-icon-button
                                   :md-icon-name "zmdi-twitter"
                                   :class "app-footer-btn-icon"]]
                          [hyperlink-href
                           :href "https://www.linkedin.com/company/scalac"
                           :target "_blank"
                           :class "btn app-footer-btn"
                           :label [md-icon-button
                                   :md-icon-name "zmdi-linkedin"
                                   :class "app-footer-btn-icon"]]
                          [:span "and check out our"]
                          [hyperlink-href
                           :href "http://blog.scalac.io"
                           :target "_blank"
                           :class "btn app-footer-btn"
                           :label "BLOG"]]]]])

(defn bottom-panel []
  []
  [v-box
   :class "app-main"
   :size "1 1 auto"
   :gap "4px"
   :align :stretch
   :children [[api-panel]]])

(defn repl-component []
  [h-box
   :class "app-main"
   :size "1 1 auto"
   :justify :center
   :gap "10px"
   :children [[cljs-buttons]
              [box
               :size "1"
               :style {:overflow "hidden"}
               :child [cljs-console-component]]]])

)
