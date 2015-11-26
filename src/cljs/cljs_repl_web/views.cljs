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
  (let [media-query (subscribe [:media-query-size])
        console-created? (subscribe [:console-created? :cljs-console])
        example-mode? (subscribe [:example-mode? :cljs-console])]
    (fn cljs-buttons-form2 []
      (let [children [[md-icon-button
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
                       :disabled? (not @example-mode?)]]]
        (if-not (= :narrow @media-query)
          [v-box
           :gap "8px"
           :children children]
          [h-box
           :gap "8px"
           :children children])))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;  API panel section  ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn get-symbol-doc-map
  "Returns the doc map for the symbol from the `cljs-api-edn` parsed  map."
  [symbol]
  (get-in api/cljs-api-edn [:symbols symbol]))

(defn api-signatures
  "Builds a table for the provided signatures of a symbol."
  [signatures]
  [v-box
   :size "0 0 auto"
   :gap "2px"
   :children [(for [s signatures]
                ^{:key s} [label
                           :label s
                           :class "api-panel-signature"])]])

(defn api-symbol-description
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

(defn api-related-symbols
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

(defn api-example
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

(defn api-example-panel
  "UI for a single example. Wants a map {:html ... :strings}."
  [showing-atom example-index example-map]
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
                                  (reset! showing-atom false)
                                  (dispatch [:send-to-console :cljs-console (:strings example-map)]))]]
              [api-example example-map]]])

(defn api-examples
  "Builds the UI for the symbol's examples. Wants a list of {:html ... :strings}."
  [showing-atom examples-map]
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
               :children (map-indexed (partial api-example-panel showing-atom) examples-map)]]])

(defn symbol-popover
  "A popover's body in which details of the given symbol will be shown."
  [showing-atom position-atom sym-doc-map]
  (let [{name :name
         full-name :full-name
         desc :description
         docstring :docstring
         examples-htmls :examples-htmls
         examples-strings :examples-strings
         sign :signature
         related :related} sym-doc-map
        desc (or desc docstring) ; some symbols don't have a description so we use
                                        ; the docstring instead; docstring is a regular string,
                                        ; without markdown. Nonetheless, it will be passed to
                                        ; md->react->component function to gain some basic html
                                        ; formatting (like paragraphs)
        examples (map (fn [html string] {:html html :strings string}) examples-htmls examples-strings)
        popover-width  400
        popover-height 400
        popover-content-width (- popover-width (* 2 14) 15)] ; bootstrap padding + scrollbar width
    (fn symbol-popover-form2 [showing-atom position-atom sym-doc-map]
      [popover-content-wrapper
       :showing? showing-atom
       :position @position-atom
       :on-cancel (handler-fn (reset! showing-atom false))
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
                                      [api-signatures sign])
                                    (when (not-empty desc)
                                      [api-symbol-description desc])
                                    (when (not-empty related)
                                      [api-related-symbols related])
                                    (when (not-empty examples)
                                      [api-examples showing-atom examples])]]])]])))

(defn api-symbol
  "Builds the UI for a single symbol. Will be a button."
  [symbol]
  (let [showing? (reagent/atom false)
        popover-position (reagent/atom :below-center)]
    (fn api-symbol-form2 [symbol]
     [box
      :size "0 1 auto"
      :align :center
      :class "api-panel-symbol-label-box"
      :child (if-let [symbol (get-symbol-doc-map (str symbol))]
               [popover-anchor-wrapper
                :showing? showing?
                :position @popover-position
                :anchor [button
                         :class "btn btn-default api-panel-symbol-button"
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
                :class "api-panel-symbol-label"
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

;; (trace-forms {:tracer (tracer :color "orange")}

(defn api-section
  "Builds the UI for a section."
  [section]
  (let [media-query (subscribe [:media-query-size])]
    (fn api-section-form2 []
      [v-box
       :size "1 1 auto"
       :gap "4px"
       :class "api-panel-section"
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
                                            :children [(when (= :wide @media-query)
                                                         [section-title-component (:title topic)])
                                                       [v-box
                                                        :size "1 1 auto"
                                                        :gap "2px"
                                                        :justify (if (= :wide @media-query) :start :center)
                                                        :style {:flex-flow "wrap"}
                                                        :children (for [symbol (:symbols topic)]
                                                                    [api-symbol symbol])]]])]]]]])))

(defn api-panel
  "Builds the UI for the api panel."
  [sections]
  (let [column-number (subscribe [:api-panel-column-number])
        sections-by-column (subscribe [:api-panel-section-columns sections])]
    (fn api-panel-form2 []
      [h-box
       :size "1 1 auto"
       :gap "10px"
       :children (for [sections @sections-by-column]
                   ^{:key sections}
                   [v-box
                    :size (str "0 1 " (quot 100 @column-number) "%")
                    :gap "10px"
                    :children (for [section sections]
                                [api-section section])])])))

;;;;;;;;;;;;;;;;;;
;;   Footer    ;;;
;;;;;;;;;;;;;;;;;;

(defn footer-links
  [media-query-atom]
  (let [links [[:span "Connect with us at"]
               [h-box
                :size "0 0 auto"
                :gap "4px"
                :children [[hyperlink-href
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
                                    :class "app-footer-btn-icon"]]]]
               [:span "and check out our"]
               [hyperlink-href
                :href "http://blog.scalac.io"
                :target "_blank"
                :class "btn app-footer-btn"
                :label "BLOG"]]]
    (if (= :wide @media-query-atom)
      [h-box
       :size "1 1 auto"
       :justify :end
       :align :center
       :gap "4px"
       :children links]
      [v-box
       :size "1 1 auto"
       :justify :center
       :align :center
       :gap "4px"
       :children links])))

(defn footer-component []
  (let [media-query (subscribe [:media-query-size])
        children [[box
                   :size "1 1 auto"
                   :child [:strong "© Scalac Sp. z o.o. 2015"]]
                  [footer-links media-query]]]
    (fn footer-component-form2 []
      (if (= :wide @media-query)
        [h-box
         :size "1 1 auto"
         :justify :between
         :align :center
         :class "page-footer"
         :children children]
        [v-box
         :size "1 1 auto"
         :justify :between
         :align :center
         :class "page-footer"
         :children children]))))

;; )

(defn bottom-panel []
  []
  [v-box
   :class "app-main"
   :size "1 1 auto"
   :gap "4px"
   :align :stretch
   :children [[api-panel (:sections api-utils/custom-api-map)]]])

(defn repl-component []
  (let [media-query (subscribe [:media-query-size])]
    (fn repl-component-form2 []
      (let [children [[cljs-buttons]
                      [box
                       :size "0 0 auto"
                       :style {:overflow "hidden"}
                       :child [cljs-console-component]]]]
        (if (= :narrow @media-query)
          [v-box
           :size "1 1 auto"
           :align :center
           :gap "10px"
           :children children]
          [h-box
           :size "1 1 auto"
           :justify :center
           :gap "10px"
           :children children])) ))
  )
