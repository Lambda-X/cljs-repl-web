(ns cljs-repl-web.views
  (:require-macros [re-com.core :refer [handler-fn]])
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [re-com.core :refer [md-icon-button h-box v-box box gap button input-text
                                 popover-content-wrapper popover-anchor-wrapper hyperlink-href
                                 popover-tooltip title label scroller line modal-panel align-style
                                 make-tour start-tour make-tour-nav p]]
            [re-com.tour :refer [finish-tour]]
            [re-com.box :refer [flex-child-style]]
            [re-com.util :refer [px]]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]
            [cljs-repl-web.app :as app]
            [cljs-repl-web.cljs-api :as api]
            [cljs-api.utils :as api-utils]
            [cljs-repl-web.views.utils :as utils]
            [cljs-repl-web.markdown :as md]
            [cljs-repl-web.localstorage :as ls]
            [re-console.core :as console]
            [re-complete.core :as re-complete]
            [re-com.buttons :refer [button]]
            [goog.dom :as dom]))

;; (set! re-com.box/debug true)

;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;      Tour          ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;

(def tour
  (make-tour [:step1 :step2 :step3 :step4 :step5 :step6 :step7 :step8 :step9 :step10]))

(def current-step (reagent/atom @(:current-step tour)))

(def tour-steps
  {:welcome {:title "Welcome to clojurescript.io!"
             :body [:div [:p "Here you will find a terminal-like REPL which you can use when learning Clojure/ClojureScript or just trying new things out."]
                    [:p "It contains many features, so let’s start!"]]}
   :step1 {:title "Tour 1 of 9"
           :body [:div [:h1.tour-title "Reset button"]
                  [:p "It will reset the REPL, that is:"]
                  [:ul.tour
                   [:li "it will clear the current prompt"]
                   [:li "it will clear the REPL “screen”"]
                   [:li "it will delete the history"]]
                  [:span "By the way, there are two ways to navigate back and forth in the history: with key arrows"]
                  [:span.symbol " ↑ "]
                  [:span "and"]
                  [:span.symbol " ↓ "]
                  [:span "or just by clicking on the item you are interested in."]]}
   :step2 {:title "Tour 2 of 8"
           :body [:div [:h1.tour-title "Clear button"]
                  [:p "It will trigger the same action as before with the difference that the history will not be deleted."]]}
   :step3 {:title "Tour 3 of 9"
           :body [:div [:h1.tour-title "Creating a Gist"]
                  [:p "It is really one of the nicest features of our app."]
                  [:p "You will need to fill your username and password and a new window with the newly created Gist will be opened."]
                  [:p "The username will be saved (but not the password) so you won’t need to fill it every time."]
                  [:h5 "Note:"]
                  [:p "Only what is shown in the REPL will be sent to Gist. So even if you have some items in your history but the current “screen” is empty you won’t be able to create a Gist."]]}
   :step4 {:title "Tour 4 of 9"
           :body [:div [:h1.tour-title "Clear examples"]
                  [:p "Just under the REPL you will find many buttons, logically grouped by topic. Clicking on them will open a popup (this feature will be explained in another step of this tutorial)."]
                  [:span "One of the actions you can take is send the examples directly to the REPL: for example the"]
                  [:span.symbol " + "]
                  [:span "symbol contains 5 examples you can evaluate. If for any reason you want to interrupt the examples’ evaluation just click on this button."]
                  [:h5 "Note:"]
                  [:span "The number of current examples to evaluate is shown in the mode-line:"]
                  [:code "X form(s) in the evaluation queue"]]}
   :step5 {:title "Tour 5 of 9"
           :body [:div [:h1.tour-title "Switch input mode"]
                  [:span "We integrated"]
                  [:a.tour {:href "https://github.com/shaunlebron"} " shaunlebron"]
                  [:span "’s excellent"]
                  [:a.tour {:href "https://github.com/shaunlebron/parinfer"} " parinfer"]
                  [:span " to our REPL with the default input mode as Indent Mode (the other two being Paren Mode and none)."]
                  [:h5 "Note:"]
                  [:span "If you write for exmaple "]
                  [:span.mode "(def a"]
                  [:span " and press ENTER the cursor will be placed on a newline automatically (none mode)."]
                  [:span "This is not always the case with Indent Mode, which will try to balance parenthesis causing the previous expression to be transformed to "]
                  [:span.mode "(def a)"]
                  [:span "."]
                  [:p "In this case you need to press SHIFT+ENTER."]]}
   :step6 {:title "Tour 6 of 9"
           :body [:div [:h1.tour-title "Console"]
                  [:p "We already saw many features of the REPL like history navigation, input mode or examples evaluation (more on this in the next step)."]
                  [:p "Another feature we want to mention here is autocompletion:"]
                  [:ul.tour]
                  [:span "The suggestion list is shown immediately when the user starts typing. The word to autocomplete is selected by using"]
                  [:span.symbol " ↑ "]
                  [:span "and"]
                  [:span.symbol " ↓ "]
                  [:span "arrows, or by clicking on the selected item."]
                  [:span " When the user presses "]
                  [:span.mode "tab"]
                  [:span " the first item from suggestion list is picked for autocompletion."]]}
   :step7 {:title "Tour 7 of 9"
           :body [:div [:h1.tour-title "Symbol"]
                  [:p "As mentioned earlier there are many buttons in the lower section of the page."]
                  [:p "Just click on any of those to open a popup and see what happens."]]}
   :step8 {:title "Tour 8 of 9"
           :body [:div [:h1.tour-title "Popup"]
                  [:p "In the popup you will see a detailed explanation of a symbol:"]
                  [:ul.tour
                   [:li "the (i) icon will open the relative online doc page"]
                   [:li "signatures"]
                   [:li "description"]
                   [:li "related symbols list"]]]}
   :step9 {:title  "Tour 9 of 9"
           :body [:div [:h1.tour-title "Send to repl"]
                  [:p "Click on this button to send the examples to the REPL."]]}
   :step10 {:title  "Excellent!"
            :body [:div [:p "This is the end of the tutorial. If you want to report an issue or fork the repository click on the ribbon in the top-left corner."]
                   [:span "Check also our blog at "]
                   [:a {:href "http://lambdax.io/blog"} "lambdax.io/blog"]
                   [:span " for posts about Clojure and follow us on Twitter at"]
                   [:a {:href "https://twitter.com/lambdax_io"} " @lambdax_io"]
                   [:span "."]
                   [:p]
                   [:p "Thanks and enjoy."]]}})

(defn welcome-modal-dialog
  []
  (let [showing? (reagent/atom true)
        closed-tour? (ls/get-item :closed-tour?)]
    (fn []
      (when (and @showing? (not closed-tour?))
        [modal-panel
         :backdrop-opacity 0.4
         :child [v-box
                 :align :center
                 :max-width "400px"
                 :class "modal-tour-popup"
                 :children [[title
                             :label (get-in tour-steps [:welcome :title])
                             :style {:font-weight "bold"
                                     :font-size "20px"
                                     :text-align "center"
                                     :color "rgba(68, 68, 68, 0.6)"}]
                            [p (get-in tour-steps [:welcome :body])]
                            [:hr {:style {:margin "10px 0 10px"}}]
                            [button
                             :label "Skip"
                             :on-click #(do (finish-tour tour)
                                            (ls/set-item! :closed-tour? true)
                                            (reset! showing? false))
                             :class "btn-default"
                             :style {:margin-right "15px"}]
                            [button
                             :label "Next"
                             :on-click #(do (start-tour tour)
                                            (reset! showing? false))
                             :class "btn-default"]]
                 :style {:display "inline-block"}]
         :style {:padding-bottom "150px"}]))))

(defn finished-tour-modal-dialog
  []
  (let [showing? (reagent/atom true)]
    (fn []
      (when (and @showing? (= @current-step (dec (count (:steps tour)))))
        [modal-panel
         :backdrop-opacity 0.4
         :child [v-box
                 :align :center
                 :max-width "400px"
                 :class "modal-tour-popup"
                 :children [[title
                             :label (get-in tour-steps [:step10 :title])
                             :style {:font-weight "bold"
                                     :font-size "20px"
                                     :text-align "center"
                                     :color "rgba(68, 68, 68, 0.6)"}]
                            [p (get-in tour-steps [:step10 :body])]
                            [:hr {:style {:margin "10px 0 10px"}}]
                            [button
                             :label "Finish"
                             :on-click #(do (finish-tour tour)
                                            (ls/set-item! :closed-tour? true)
                                            (reset! showing? false))
                             :class "btn-default"
                             :style {:margin-right "15px"}]]
                 :style {:display "inline-block"}]
         :style {:padding-bottom "150px"}]))))

(defn- next-tour-step
  [tour]
  (let [steps     (:steps tour)
        old-step  @(:current-step tour)
        new-step  (inc old-step)]
    (when (< new-step (count (:steps tour)))
      (reset! (:current-step tour) new-step)
      (reset! current-step new-step)
      (reset! ((nth steps old-step) tour) false)
      (reset! ((nth steps new-step) tour) true))))

(defn- prev-tour-step [tour]
  (let [steps    (:steps tour)
        old-step @(:current-step tour)
        new-step (dec old-step)]
    (when (>= new-step 0)
      (reset! (:current-step tour) new-step)
      (reset! current-step new-step)
      (reset! ((nth steps old-step) tour) false)
      (reset! ((nth steps new-step) tour) true))))

(defn tour-buttons
  [tour another-steps close-fn]
  (let [next-step (:next another-steps)
        previous-step (:prev another-steps)
        on-first-button (= @(:current-step tour) 0)
        on-last-button  (= @(:current-step tour) (dec (count (:steps tour))))
        showing? (reagent/atom true)]
    [:div
     [:hr {:style (merge (flex-child-style "none")
                         {:margin "10px 0 10px"})}]
     (when-not on-first-button
       [button
        :label    "Previous"
        :on-click (handler-fn
                   (if previous-step
                     ((previous-step)
                      (prev-tour-step tour))
                     (prev-tour-step tour)))
        :style    {:margin-right "15px"}
        :class     "btn-default"])
     [button
      :label    "Next"
      :on-click (handler-fn
                 (if next-step
                   ((next-step)
                    (next-tour-step tour))
                   (next-tour-step tour)))
      :style    {:z-index 5000}
      :class     "btn-default"]]))

(defn create-tour-step
  ([step position width anchor close-fn]
   (let [media-query (subscribe [:media-query-size])]
     (create-tour-step step position width anchor close-fn nil)))
  ([step position width anchor close-fn another-step]
   (let [step-keyword (keyword (str "step" step))
         media-query  (subscribe [:media-query-size])]
     [popover-anchor-wrapper
      :showing? (step-keyword tour)
      :position position
      :anchor anchor
      :popover [popover-content-wrapper
                :showing? (step-keyword tour)
                :position position
                :width    width
                :title    [:strong (get-in tour-steps [step-keyword :title])]
                :body     [:div (get-in tour-steps [step-keyword :body])
                           [tour-buttons tour another-step close-fn]]
                :on-cancel #(do (close-fn)
                                (ls/set-item! :closed-tour? true))
                :backdrop-opacity 0.5]
      :style (align-style :align-items :stretch)])))


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

(defn gist-position
  "Calulates the gist position depending on the media query"
  [media-query]
  (if (= :narrow @media-query) :below-center :right-center))

(defn gist-login-dialog-body
  []
  (let [showing? (subscribe [:gist-showing?])
        media-query (subscribe [:media-query-size])
        auth-data (subscribe [:gist-auth-data])
        ok-fn #(dispatch [:create-gist :cljs-console auth-data on-gist-created gist-error-handler])
        cancel-fn #(dispatch [:hide-gist-login])]
    (fn []
      [login-focus-wrapper
       [popover-content-wrapper
        :showing? showing?
        :on-cancel cancel-fn
        :position (gist-position media-query)
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
  (let [can-dump-gist? (subscribe [:can-dump-gist? :cljs-console])
        showing? (subscribe [:gist-showing?])
        media-query (subscribe [:media-query-size])]
    (fn []
      (if @showing?
        [popover-anchor-wrapper
         :showing? showing?
         :position (gist-position media-query)
         :anchor   [md-icon-button
                    :md-icon-name "zmdi-github"
                    :on-click #(dispatch [:show-gist-login])
                    :class "cljs-btn"
                    :size (if-not (= :medium @media-query) :regular :smaller)
                    :tooltip "Create Gist"
                    :tooltip-position (if-not (= :narrow @media-query) :left-center :above-center)
                    :disabled? @can-dump-gist?]
         :popover  [gist-login-dialog-body]]
        [create-tour-step 3
         (if (= @media-query :narrow) :below-center :right-center)
         (if (= @media-query :narrow) "300px" "400px")
         [md-icon-button
          :md-icon-name "zmdi-github"
          :on-click #(dispatch [:show-gist-login])
          :class "cljs-btn"
          :size (if-not (= :medium @media-query) :regular :smaller)
          :tooltip "Create Gist"
          :tooltip-position (if-not (= :narrow @media-query) :left-center :above-center)
          :disabled? @can-dump-gist?]
         #(finish-tour tour)]))))

(defn cljs-buttons
  "Return a vector of components containing the cljs console buttons.
   To place them in a layout, call the function (it does not return a
   component)."
  []
  (let [media-query (subscribe [:media-query-size])
        console-created? (subscribe [:console-created? :cljs-console])
        example-mode? (subscribe [:queued-forms-empty? :cljs-console])
        mode (subscribe [:get-console-mode :cljs-console])
        modes (atom (->> (cycle '(:indent-mode :paren-mode :none))
                         (drop-while #(not= @mode %))
                         (drop 1)))
        get-next-mode (fn []
                        (let [next-mode (first @modes)]
                          (swap! modes rest)
                          next-mode))]
    (fn cljs-buttons-form2 []
      (let [children [[create-tour-step 1
                       (if (= @media-query :narrow) :below-right :right-center)
                       (if (= @media-query :narrow) "230px" "400px")
                       [md-icon-button
                        :md-icon-name "zmdi-delete"
                        :on-click #(dispatch [:reset-console-items :cljs-console])
                        :class "cljs-btn"
                        :size (if-not (= :medium @media-query) :regular :smaller)
                        :tooltip "Reset"
                        :tooltip-position (if-not (= :narrow @media-query) :left-center :above-center)
                        :disabled? (not @console-created?)]
                       #(finish-tour tour)]
                      [create-tour-step 2
                       (if (= @media-query :narrow) :below-center :right-center)
                       (if (= @media-query :narrow) "240px" "400px")
                       [md-icon-button
                        :md-icon-name "zmdi-format-clear-all"
                        :on-click #(dispatch [:clear-console-items :cljs-console])
                        :class "cljs-btn"
                        :size (if-not (= :medium @media-query) :regular :smaller)
                        :tooltip "Clear"
                        :tooltip-position (if-not (= :narrow @media-query) :left-center :above-center)
                        :disabled? (not @console-created?)]
                       #(finish-tour tour)]
                      [gist-login-dialog]
                      [create-tour-step 4
                       (if (= @media-query :narrow) :below-center :right-center)
                       (if (= @media-query :narrow) "240px" "400px")
                       [md-icon-button
                        :md-icon-name "zmdi-stop"
                        :on-click #(dispatch [:clear-console-queued-forms :cljs-console])
                        :class "cljs-btn"
                        :size (if-not (= :medium @media-query) :regular :smaller)
                        :tooltip "Clear examples"
                        :tooltip-position (if-not (= :narrow @media-query) :left-center :above-center)
                        :disabled? (not @example-mode?)]
                       #(finish-tour tour)]
                      [create-tour-step 5
                       (if (= @media-query :narrow) :below-left :right-center)
                       (if (= @media-query :narrow) "240px" "400px")
                       [md-icon-button
                        :md-icon-name "zmdi-keyboard"
                        :on-click #(dispatch [:switch-console-mode (get-next-mode)])
                        :class "cljs-btn"
                        :size (if-not (= :medium @media-query) :regular :smaller)
                        :tooltip "Switch input mode"
                        :tooltip-position (if-not (= :narrow @media-query) :left-center :above-center)]
                       #(finish-tour tour)]]]
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
   :children [(for [[index s] (map-indexed vector signatures)]
                ^{:key index}
                [label
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
  []
  (let [media-query (subscribe [:media-query-size])]
    (fn
      [showing-atom example-index example-map]
      {:pre [(:strings example-map) (:html example-map)]}
      [v-box
       :size "none"
       :gap "4px"
       :justify :center
       ;; Why the box?
       ;; See problem here, see https://github.com/Day8/re-com/issues/76
       :children [[create-tour-step 9
                   :above-center
                   (if (= @media-query :narrow) "200px" "300px")
                   [box
                    :class "api-panel-button-send-repl-box"
                    :size "1 0 auto"
                    :style (align-style :align-items :stretch)
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
                                       (utils/scroll-to-top) ; in case we are at the bottom of the page
                                       (dispatch [:set-console-queued-forms :cljs-console (:strings example-map)]))]]
                   #(do (finish-tour tour)
                        (reset! showing-atom false)
                        (utils/scroll-to-top))
                   {:next #(reset! showing-atom false)}]
                  [api-example example-map]]])))

(defn api-examples
  "Builds the UI for the symbol's examples. Wants a list of {:html ... :strings}."
  [showing-atom examples-map]
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
               :children (map-indexed (partial (api-example-panel) showing-atom) examples-map)]]])

(defn symbol-popover
  "A popover's body in which details of the given symbol will be shown."
  ([showing-atom position-atom sym-doc-map]
   (symbol-popover showing-atom position-atom sym-doc-map nil))
  ([showing-atom position-atom sym-doc-map tour?]
   (let [media-query (subscribe [:media-query-size])]
     (fn symbol-popover-form2 [showing-atom position-atom sym-doc-map]
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
             popover-width  (if (= :narrow @media-query) 280 400)
             popover-height (if (= :narrow @media-query) 300 400)
             popover-content-width (- popover-width (* 2 14) 15)] ; bootstrap padding + scrollbar width
         [popover-content-wrapper
          :showing? showing-atom
          :position @position-atom
          :on-cancel (handler-fn (reset! showing-atom false))
          :style {:max-height (str popover-height)
                  :max-width (str popover-width)}
          :backdrop-opacity 0.1
          :close-button? (= :narrow @media-query)
          :title [h-box
                  :gap "6px"
                  :align :center
                  :children [[title
                              :label name
                              :level :level4]
                             [create-tour-step 8
                              (if (= @media-query :narrow) :below-center :right-below)
                              (if (= @media-query :narrow) "220px" "400px")
                              [md-icon-button
                               :md-icon-name "zmdi-info"
                               :tooltip "See online documentation"
                               :tooltip-position :right-center
                               :size :smaller
                               :style {:justify-content :center}
                               :on-click #(utils/open-new-window (utils/symbol->clojuredocs-url full-name))]
                              #(do (finish-tour tour)
                                   (reset! showing-atom false)
                                   (utils/scroll-to-top))
                              {:prev #(reset! showing-atom false)}]]]
          :body [reagent/create-class
                 {:component-did-mount (fn [this]
                                         (let [node (reagent/dom-node this)]
                                           (when tour?
                                             (set! (.-scrollTop node) 80))))
                  :reagent-render (fn []
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
                                                          [api-examples showing-atom examples])]]])}]])))))

(defn api-symbol
  "Builds the UI for a single symbol. Will be a button."
  [symbol]
  (let [showing? (reagent/atom false)
        popover-position (reagent/atom :below-center)
        tour? (reagent/atom false)
        media-query (subscribe [:media-query-size])]
    (fn api-symbol-form2 [symbol]
      [box
       :size "0 1 auto"
       :align :center
       :class "api-panel-symbol-label-box"
       :child (if-let [symbol' (get-symbol-doc-map (str symbol))]
                (if (and (= (str symbol) "/") (not @showing?))
                  [create-tour-step 7
                   :above-left
                   (if (= @media-query :narrow) "200px" "280px")
                   [button
                    :class "btn btn-default api-panel-symbol-button"
                    :label (:name symbol')
                    ;; we use :attr's `:on-click` because button's `on-click` accepts
                    ;; a parametless function and we need the mouse click coordinates
                    :attr {:on-click
                           (handler-fn
                            ;; later we can refactor it into re-frame
                            ;; see also https://github.com/Day8/re-frame/wiki/Beware-Returning-False#user-content-usage-examples
                            (reset! popover-position
                                    (utils/calculate-popover-position [(.-clientX event) (.-clientY event)]))
                            (reset! showing? true))}]
                   #(do (finish-tour tour)
                        (utils/scroll-to-top)
                        (reset! tour? false))
                   {:next #(do (reset! popover-position :above-center)
                               (reset! tour? true)
                               (reset! showing? true)
                               [popover-anchor-wrapper
                                :showing? showing?
                                :position @popover-position
                                :anchor [button
                                         :class "btn btn-default api-panel-symbol-button"
                                         :label (:name symbol)]])}]
                  (do [popover-anchor-wrapper
                       :showing? showing?
                       :position @popover-position
                       :anchor [button
                                :class "btn btn-default api-panel-symbol-button"
                                :label (:name symbol')
                                ;; we use :attr's `:on-click` because button's `on-click` accepts
                                ;; a parametless function and we need the mouse click coordinates
                                :attr {:on-click
                                       (handler-fn
                                        ;; later we can refactor it into re-frame
                                        ;; see also https://github.com/Day8/re-frame/wiki/Beware-Returning-False#user-content-usage-examples
                                        (reset! popover-position
                                                (utils/calculate-popover-position [(.-clientX event) (.-clientY event)]))
                                        (reset! showing? true))}]
                       :popover (if @tour?
                                  [symbol-popover showing? popover-position symbol' true]
                                  [symbol-popover showing? popover-position symbol'])]))
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
       :children (for [[index sections] (map-indexed vector @sections-by-column)]
                   ^{:key index}
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
                            :href "https://twitter.com/lambdax_io"
                            :target "_blank"
                            :class "btn app-footer-btn"
                            :label [md-icon-button
                                    :md-icon-name "zmdi-twitter"
                                    :class "app-footer-btn-icon"]]
                           [hyperlink-href
                            :href "https://github.com/Lambda-X"
                            :target "_blank"
                            :class "btn app-footer-btn"
                            :label [md-icon-button
                                    :md-icon-name "zmdi-github"
                                    :class "app-footer-btn-icon"]]]]
               [:span "and check out our"]
               [hyperlink-href
                :href "http://lambdax.io/blog"
                :target "_blank"
                :class "btn app-footer-btn"
                :label "BLOG"]]]
    (if (= :narrow @media-query-atom)
      [v-box
       :size "1 1 auto"
       :justify :center
       :align :center
       :gap "4px"
       :children links]
      [h-box
       :size "1 1 auto"
       :justify :end
       :align :center
       :gap "4px"
       :children links])))

(defn footer-component []
  (let [media-query (subscribe [:media-query-size])
        children [[box
                   :size "1 1 auto"
                   :child [:strong "LambdaX © 2016"]]
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

(defn repl-component [console-key opts]
  (let [media-query (subscribe [:media-query-size])
        console (console/console console-key opts)]
    (fn repl-component-form2 []
      (let [children [[cljs-buttons]
                      [v-box
                       :size "0 0 auto"
                       :children
                       [[create-tour-step 6
                         :below-center
                         (if (= @media-query :narrow) "300px" "400px")
                         [:div.popup-tour-console-anchor]
                         #(do (finish-tour tour)
                              (utils/scroll-to-top))]
                        [console]]]
                      [re-complete/completions console-key #(do (dispatch [:console-set-autocompleted-text console-key])
                                                                (dispatch [:focus-console-editor console-key]))]
                      [welcome-modal-dialog]
                      [finished-tour-modal-dialog]]]
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
           :children children])))))
