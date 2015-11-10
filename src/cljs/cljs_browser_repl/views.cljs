(ns cljs-browser-repl.views
  (:require-macros [re-com.core :refer [handler-fn]])
  (:require [cljs.pprint :refer [cl-format]]
            [reagent.core :as reagent]
            [re-com.core :refer [md-icon-button h-box v-box box gap button input-text
                                 popover-content-wrapper popover-anchor-wrapper hyperlink-href
                                 popover-tooltip title label scroller]]
            [re-com.util :refer [px]]
            [cljs-browser-repl.app :as app]
            [cljs-browser-repl.gist :as gist]
            [cljs-browser-repl.console :as console]
            [cljs-browser-repl.console.cljs :as cljs]
            [cljs-browser-repl.cljs-api :as api]
            [cljs-browser-repl.api-utils :as api-utils]))

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
   :size "1 1 auto"
   :gap "2px"
   :children [(for [s signatures]
                [label
                 :label s
                 :class "api-panel-signature"])]])

(defn number->word
  "From: http://clojurescriptmadeeasy.com/blog/how-to-humanize-text-cl-format.html
  If (< 0 n 10) use the ~R directive, otherwise use ~A"
  [n]
  (cl-format nil "~:[~a~;~r~]" (< 0 n 10) n))

(defn material-icon-button
  "a square button containing a material design icon, but material-icon-name"
  []
  (let [showing? (reagent/atom false)]
    (fn
      [& {:keys [material-icon-name on-click size tooltip tooltip-position emphasise? disabled? class style attr]
          :or   {md-icon-name "add"}
          :as   args}]
      (let [the-button [:div
                        (merge
                         {:class    (str
                                     "rc-md-icon-button noselect "
                                     (case size
                                       :smaller "rc-icon-smaller "
                                       :larger "rc-icon-larger "
                                       " ")
                                     (when emphasise? "rc-icon-emphasis ")
                                     (when disabled? "rc-icon-disabled ")
                                     class)
                          :style    (merge
                                     {:cursor (when-not disabled? "pointer")}
                                     style)
                          :on-click (handler-fn
                                     (when (and on-click (not disabled?))
                                       (on-click)))}
                         (when tooltip
                           {:on-mouse-over (handler-fn (reset! showing? true))
                            :on-mouse-out  (handler-fn (reset! showing? false))})
                         attr)
                        [:i.material-icons material-icon-name]]]
        (if tooltip
          [popover-tooltip
           :label    tooltip
           :position (if tooltip-position tooltip-position :below-center)
           :showing? showing?
           :anchor   the-button]
          the-button)))))

(defn example-panel
  "Build the example panel, accepts a list of {:html ... :string ...}
  maps."
  [example-index example-map]
  {:pre [(:string example-map) (:html example-map)]}
  [v-box
   :size "none"
   :gap "2px"
   :justify :center
   :children [[box
               :size "0 1 auto"
               :min-width "26px"
               :child [material-icon-button
                       :material-icon-name (str "looks_" (number->word (inc example-index)))
                       :on-click #()
                       :tooltip "Load the example in the REPL"
                       :tooltip-position :below-center
                       :disabled? (not (app/console-created? :cljs-console))]]
              [box
               :size "none"
               :width "100%"
               :child [:div {:dangerouslySetInnerHTML {:__html (:html example-map)}}]]]])

(defn symbol-popover
  "A popover's body in which details of the given symbol will be shown."
  [showing? popover-position sym-doc-map]
  (let [{name :name
         desc :description-html
         examples-htmls :examples-htmls
         examples-strings :examples-strings
         sign :signature
         related :related} sym-doc-map
        examples (map (fn [html string] {:html html :string string}) examples-htmls examples-strings)]
    [popover-content-wrapper
     :showing? showing?
     :position @popover-position
     :on-cancel (handler-fn (reset! showing? false))
     :width "300"
     :height "300"
     :backdrop-opacity 0.1
     :close-button? false
     :title name
     :body [(fn []
              [v-box
               :size "none"
               :width "300px"
               :gap "4px"
               :children [(when (not-empty sign)
                            [build-signatures-ui sign])
                          (when (not-empty desc)
                            ;; we can use `dangerouslySetInnerHTML` or construct the edn from
                            ;; the html string (using eg. hickory)
                            ;; [:div (map hickory/as-hiccup (hickory/parse-fragment desc))]
                            [box
                             :size "0 1 auto"
                             :child [:div {:dangerouslySetInnerHTML {:__html desc}}]])
                          (when (not-empty examples)
                            [v-box
                             :size "0 0 auto"
                             :children [[title
                                         :label "Examples"
                                         :level :level4
                                         :class "api-panel-popup-section-title"]
                                        [scroller
                                         :h-scroll :auto
                                         :width "300px"
                                         :child [h-box
                                                 :size "none"
                                                 :gap "2px"
                                                 :children (map-indexed example-panel examples)]]]])]])]]))

(defn calculate-popover-position
  "Calculates the tooltip orientation for a given symbol."
  [[x y]]
  (let [h (.-innerHeight js/window)
        w (.-innerWidth  js/window)
        v-threshold (quot h 2)
        v-position  (if (< y v-threshold) "below" "above")
        h-threshold-left (quot w 3)
        h-threshold-cent (* 2 h-threshold-left)
        h-position (cond
                    (< x h-threshold-left) "right"
                    (< x h-threshold-cent) "center"
                    :else "left")]
    (keyword (str v-position \- h-position))))

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
                                             (calculate-popover-position [(.-clientX %) (.-clientY %)]))
                                     (reset! showing? true))}]
       :popover [symbol-popover showing? popover-position symbol]])
    [button
     :label (str symbol)
     :class "btn-default"]))

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
                           :children (for [topic (:topics section)]
                                       [title
                                        :label (:title topic)
                                        :level :level4
                                        :class "api-panel-topic"])]
                          [v-box
                           :size "1 1 auto"
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
    [scroller
     :scroll :auto
     :child [h-box
             :size "0 1 50%"
             :gap "10px"
             :children (for [sections partitioned-sections]
                         [v-box
                          :size "1 1 auto"
                          :gap "10px"
                          :children (for [section sections]
                                      [build-section-ui section])])]]))

(defn api-panel []
  [build-api-panel-ui 2 (:sections api-utils/custom-api-map)])
