(ns cljs-browser-repl.views.utils
  (:require-macros [re-com.core :refer [handler-fn]])
  (:require [cljs.pprint :as pprint]
            [clojure.string :as string]
            [reagent.core :as reagent]
            [re-com.core :refer [popover-tooltip]]))

(def clojuredocs-url "http://clojuredocs.org/")

(defn strip-namespace
  "Given a cljs symbol, strip the namespace part."
  [sym]
  (string/join (drop-while #(re-find #"\." %) (string/split (str sym) #"/" 2))))

(defn symbol->clojuredocs-url
  "Given a cljs symbol (with fully qualified ns or without, in which
  case it defaults to clojure.core), returns the url to ClojureDocs
  documentation."
  [cljs-symbol]
  (let [[ns symbol] (string/split (str cljs-symbol) #"/" 2)]
    (str clojuredocs-url (if ns
                           (string/replace ns #"cljs" "clojure")
                           "clojure.core")
         "/" symbol)))

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

(defn number->word
  "From: http://clojurescriptmadeeasy.com/blog/how-to-humanize-text-cl-format.html
  If (< 0 n 10) use the ~R directive, otherwise use ~A"
  [n]
  (pprint/cl-format nil "~:[~a~;~r~]" (< 0 n 10) n))
