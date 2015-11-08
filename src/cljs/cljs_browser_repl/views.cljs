(ns cljs-browser-repl.views
  (:require [reagent.core :as reagent]
            [re-com.core :refer [md-icon-button h-box v-box box gap button input-text
                                 popover-content-wrapper popover-anchor-wrapper hyperlink-href]]
            [re-com.util :refer [px]]
            [cljs-browser-repl.app :as app]
            [cljs-browser-repl.gist :as gist]
            [cljs-browser-repl.console :as console]
            [cljs-browser-repl.console.cljs :as cljs]
            [cljs-browser-repl.cljs-api :as api]))

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

(defrecord Section [title additional-info topics])
(defrecord Topic [title symbols])

;;; A map of sections and topics we want to display in our API panel.
;;; We can easily add or remove sections/topics and they will be automatically
;;; displayed in the API panel.
(def custom-api-map
  {:sections [(Section. "Datatypes" ""
                        [(Topic. "maps " '({:key1 val1 :key2 val2}))
                         (Topic. "vectors" '([1 2 3] [:a :b :c]))
                         (Topic. "sets" '(#{:a :b :c 1 2 3}))
                         (Topic. "scalars" '(a-symbol :a-keyword "a-string"))
                         (Topic. "arrays" '((array 1 2 3)))])
              (Section. "Functions" ""
                        [(Topic. "calling"
                                 '("(<FUNCTION> <ARGS*>)"))
                         (Topic. "defining named functions"
                                 '("(defn <NAME> [<ARGS*>] |CONSTRAINTS| <ACTIONS*>)"))
                         (Topic. "anonymous function"
                                 '("(fn |NAME| [<ARGS*>] |CONSTRAINTS| <ACTIONS*>)"))
                         (Topic. "anonymous inline funcion"
                                 '("#(<ACTION> |% %2 %3 OR %&|)"))])
              (Section. "Useful Macros" ""
                        [(Topic. "conditionals"
                                 '(if if-let cond condp and or when when-let))
                         (Topic. "nesting, chaining, and interop"
                                 '(-> ->> doto .. .))
                         (Topic. "defining things"
                                 '(def defn fn let binding defmulti defmethod
                                   deftype defrecord reify this-as))])
              (Section. "Useful Functions" ""
                        [(Topic. "math"
                                 '(+ - * / quot rem mod inc dec max min))
                         (Topic. "comparison"
                                 '(= == not= < > <= >=))
                         (Topic. "predicates"
                                 '(nil? identical? zero? pos? neg? even? odd? true? false?))
                         (Topic. "data processing"
                                 '(map reduce filter partition split-at split-with))
                         (Topic. "data create"
                                 '(vector vec hash-map set for list list*))
                         (Topic. "data inspection"
                                 '(first rest get get-in keys vals count get nth contains? find))
                         (Topic. "data manipulation"
                                 '(seq into conj cons assoc assoc-in dissoc zipmap
                                   merge merge-with select-keys update-in))
                         (Topic. "arrays"
                                 '(first rest get get-in keys vals count get nth contains? find))])
              (Section. "JavaScript Interop" ""
                        [(Topic. "method call" '("(.the-method target-object args...)"))
                         (Topic. "property access" '("(.-property target-object -property)"))
                         (Topic. "property setting" '("(set! (.-title js/document) \"Hi!\")"))
                         (Topic. "direct javascript" '("(js/alert \"Hello scalac!\")"))
                         (Topic. "external library use" '("(.text (js/jQuery \"#title\") \"ClojureScript Rocks!\")"))])]
})

(defn get-symbol-doc-map
  "Returns the doc map for the symbol from the `cljs-api-edn` parsed  map."
  [symbol]
  (get-in api/cljs-api-edn [:symbols symbol]))

(defn build-symbol-ui
  "Builds the UI for a single symbol. Will be either a link with popup or
  a simple `<span>`."
  [symbol]
  (if-let [symbol (get-symbol-doc-map (str symbol))]
    [hyperlink-href                     ; add popup
     :label (:name symbol)
     :class "api-panel-symbol"
     :style {:display "inline-flex"}
     :href ""                         ; add url to documentation
     :target "_blank"]
    [:span.api-panel-symbol (str symbol)]))

(defn build-topics-ui
  "Builds the UI for the provided topics in the form of a table."
  [topics]
  [:table.api-panel-table
   (for [topic topics]
     [:tr.api-panel-table-tr
      [:td.api-panel-topic (:title topic)]
      [:td.api-panel-table-td-symbols
       (map build-symbol-ui (:symbols topic))]])])

(defn build-section-ui
  "Builds the UI for a section."
  [section]
  [:div
   [:h1.api-panel-section-title (:title section)]
   (build-topics-ui (:topics section))])

(defn build-api-panel-ui
  "Builds the UI for the api panel. Expects the numer of columns in which place the sections 
  of the tutorial and the sections themselves. `cols` must be a divisor of 12."
  [cols sections]
  (let [bootstrap-class-nr (quot 12 cols)
        secs (count sections)
        secs-per-col (quot secs cols)
        partitioned-sections (partition-all (if (zero? (rem secs cols))
                                              secs-per-col
                                              (inc secs-per-col)) sections)]
    [:div.row
     (for [sections partitioned-sections]
       [:div.api-panel-column {:class (str "col-md-" bootstrap-class-nr) }
        (for [section sections]
          (build-section-ui section))])]))

(defn api-panel []
  [build-api-panel-ui 2 (custom-api-map :sections)])

