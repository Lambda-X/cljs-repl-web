(defproject cljs-browser-repl "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]
                 [cljsjs/jqconsole "2.11.0-1"]
                 [reagent "0.5.1"]]
  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.0" :exclusions [cider/cider-nrepl]]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"  ]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :figwheel {:on-jsload "cljs-browser-repl.core/main"
                                   :css-dirs ["resources/public/styles"]}
                        :compiler {:main cljs-browser-repl.core
                                   :output-to "resources/public/js/compiled/cljs-browser-repl.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true}}

                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler {
                                   ;; :main cljs-browser-repl.core ;; https://github.com/emezeske/lein-cljsbuild/issues/420
                                   :output-to "resources/public/js/compiled/cljs-browser-repl.js"
                                   :optimizations :advanced
                                   :externs ["resources/cljs-browser-repl.ext.js"]
                                   :pretty-print false}}]}

  :aliases {"fig-dev" ["figwheel" "dev"]
            "minify" ^{:doc "Clean and compile sources minified for production."} ["do" "clean" ["cljsbuild" "once" "min"]]
            ;; Nested vectors are supported for the "do" task
            "deploy" ^{:doc "Clean, compile (minified) sources, test and then deploy."} ["do" "clean" ["test" ":integration"] ["deploy" "clojars"]]}

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.5"]
                                  [org.clojure/tools.nrepl "0.2.11"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :figwheel {:nrepl-port 5088
                              ;; Load CIDER, refactor-nrepl and piggieback middleware
                              :nrepl-middleware ["cider.nrepl/cider-middleware"
                                                 "refactor-nrepl.middleware/wrap-refactor"
                                                 "cemerick.piggieback/wrap-cljs-repl"]}}})
