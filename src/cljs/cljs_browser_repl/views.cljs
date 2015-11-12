(ns cljs-browser-repl.views
  (:require-macros [re-com.core :refer [handler-fn]])
  (:require [reagent.core :as reagent]
            [re-com.core :refer [md-icon-button h-box v-box box gap button input-text
                                 popover-content-wrapper popover-anchor-wrapper hyperlink-href
                                 popover-tooltip title label scroller]]
            [re-com.util :refer [px]]
            [hickory.core :as hickory]
            [cljs-browser-repl.app :as app]
            [cljs-browser-repl.gist :as gist]
            [cljs-browser-repl.console :as console]
            [cljs-browser-repl.console.cljs :as cljs]
            [cljs-browser-repl.cljs-api :as api]
            [cljs-browser-repl.cljs-api.utils :as api-utils]
            [cljs-browser-repl.views.utils :as utils]))

;; (set! re-com.box/debug true)

;;;;;;;;;;;;;;;;;;;;;;;
;;; Reagent helpers ;;;
;;;;;;;;;;;;;;;;;;;;;;;

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

;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;      Buttons       ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;

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

(defn cljs-buttons
  "Return a vector of components containing the cljs console buttons.
   To place them in a layout, call the function (it does not return a
   component)."
  []
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
   :children [[title
               :label "Signatures"
               :level :level4
               :class "api-panel-popup-section-title"]
              (for [s signatures]
                [label
                 :label s
                 :class "api-panel-signature"])]])

(defn build-symbol-description-ui
  "Builds the UI for the symbol's description in the popover."
  [sym desc]
  [v-box
   :size "0 0 auto"
   :gap "4px"
   :children [[h-box
               :gap      "4px"
               :children [[title
                           :label "Docs"
                           :level :level4
                           :class "api-panel-popup-section-title"]
                          [md-icon-button
                           :md-icon-name "zmdi-info"
                           :tooltip "See docs online"
                           :tooltip-position :right-center
                           :size :smaller
                           :style {:justify-content :center}
                           :on-click #(.open js/window (utils/symbol->clojuredocs-url sym) "_blank")]]]
              ;; we can use `dangerouslySetInnerHTML` or construct the edn from
              ;; the html string (using eg. hickory)
              ;; [:div (map hickory/as-hiccup (hickory/parse-fragment desc))]
              ;; AR - hickory performs better in flexbox container calculation)
              [label :label (map hickory/as-hiccup (hickory/parse-fragment desc))]]])

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
               :children (for [rel related]
                           [hyperlink-href
                            :label (utils/strip-namespace rel)
                            :href (utils/symbol->clojuredocs-url rel)
                            :target "_blank"])]]])

(defn build-example-ui
  "Builds the UI for a single example"
  [example]
  [v-box
   :size "none"
   :gap "2px"
   :children [[:pre {:style {:margin "0"}}
               [:code
                example]]]])

(defn example-panel
  "Build the example panel, accepts a list of {:html ... :string ...}
  maps."
  [example-index example-string]
  ;{:pre [(:string example-map) (:html example-map)]}
  [v-box
   :size "none"
   :gap "2px"
   :justify :center
   :children [[h-box
               :size "1 1 auto"
               :gap "2px"
               :align :center
               :children [[:i.material-icons (str "looks_" (if (< example-index 2)
                                                             (utils/number->word (inc example-index)) ; looks_one, looks_two
                                                             (inc example-index)))] ; looks_3, looks_4, ...
                          ;; <img src="kiwi.svg" alt="Kiwi standing on oval">
                          [v-box
                           :size "none"
                           :width "70%"
                           :children [(build-example-ui example-string)]]
                          [button
                           :label [:img {:class "api-panel-button-send-repl"
                                         :src "styles/images/cljs.svg"
                                         :alt "Send to the REPL!"}]
                           :tooltip "Load the example in the REPL"
                           :tooltip-position :above-left
                           :disabled? (not (app/console-created? :cljs-console))]]]]])

(defn build-examples-ui
  "Builds the UI for the symbol's examples."
  [examples]
  [v-box
   :size "0 1 auto"
   :children [[gap :size "4px"]
              [title
               :label "Examples"
               :level :level4
               :class "api-panel-popup-section-title"]
              [v-box
               :size "0 0 auto"
               :gap "2px"
               :children (map-indexed example-panel examples)]]])

(defn symbol-popover
  "A popover's body in which details of the given symbol will be shown."
  [showing? popover-position sym-doc-map]
  (let [{name :name
         full-name :full-name
         desc :description-html
         examples-htmls :examples-htmls
         examples-strings :examples-strings
         sign :signature
         related :related} sym-doc-map
         examples (flatten examples-strings)
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
     :title name
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
                       :children [(when (not-empty sign)
                                    [build-signatures-ui sign])
                                  (when (not-empty desc)
                                    [build-symbol-description-ui full-name desc])
                                  (when (not-empty related)
                                    [build-related-symbols-ui related])
                                  (when (not-empty examples)
                                    [build-examples-ui examples])]]])]]))
(defn build-symbol-ui
  "Builds the UI for a single symbol. Will be a button."
  [symbol]
  (if-let [symbol (get-symbol-doc-map (str symbol))]
    (let [showing? (reagent/atom false)
          popover-position (reagent/atom :below-center)]
      [popover-anchor-wrapper
       :showing? showing?
       ;; we initialize the position but it does not matter because we will
       ;; recalculate it, but we have to specify an initial value
       :position :below-center
       ;; we use `:input` instead of `button` because button's `on-click` accepts
       ;; a parametless function and we need the mouse click coordinates
       :anchor [:input {:type "button"
                        :class "btn btn-default"
                        :value (:name symbol)
                        :on-click #(do
                                     (reset! popover-position
                                             (utils/calculate-popover-position [(.-clientX %) (.-clientY %)]))
                                     (reset! showing? true))}]
       :popover [symbol-popover showing? popover-position symbol]])
    [button
     :label (str symbol)
     :class "btn-default"
     :disabled? true]))

(defn build-section-ui
  "Builds the UI for a section."
  [section]
  [v-box
   :size "1 1 auto"
   :gap "10px"
   :children [[title
               :label (:title section)
               :level :level3
               :class "api-panel-section-title"]
              [h-box
               :size "0 1 auto"
               :gap "10px"
               :children [[v-box
                           :size "1 0 auto"
                           :gap "2px"
                           :children (for [topic (:topics section)]
                                       [title
                                        :label (:title topic)
                                        :level :level4
                                        :class "api-panel-topic"])]
                          [v-box
                           :size "1 1 auto"
                           :gap "2px"
                           :children [(for [topic (:topics section)]
                                        [h-box
                                         :size "none"
                                         :gap "2px"
                                         :style {:flex-flow "wrap"}
                                         :class "wrap"
                                         :children (for [symbol (:symbols topic)]
                                                     [build-symbol-ui symbol])])]]]]]])

(defn build-api-panel-ui
  "Builds the UI for the api panel. Expects the numer of columns in which place the sections
  of the tutorial and the sections themselves. `cols` must be a divisor of 12."
  [cols sections]
  (let [secs (count sections)
        secs-per-col (quot secs cols)
        partitioned-sections (partition-all (if (zero? (rem secs cols))
                                              secs-per-col
                                              (inc secs-per-col)) sections)]
    [h-box
     :size "0 1 50%"
     :gap "10px"
     :children (for [sections partitioned-sections]
                 [v-box
                  :size "1 1 auto"
                  :gap "10px"
                  :children (for [section sections]
                              [build-section-ui section])])]))

(defn api-panel []
  [build-api-panel-ui 2 (:sections api-utils/custom-api-map)])
