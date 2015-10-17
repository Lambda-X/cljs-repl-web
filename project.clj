(defproject cljs-browser-repl "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.145"]
                 [cljsjs/jqconsole "2.11.0-1"]
                 [reagent "0.5.1"]
                 [com.cognitect/transit-clj "0.8.283"]
                 [com.cognitect/transit-cljs "0.8.225"]]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.0" :exclusions [cider/cider-nrepl]]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target" ]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs" "test/cljs"]
                        :figwheel {:on-jsload "launcher.test/run"
                                   :css-dirs ["resources/public/styles"]}
                        :compiler {:main cljs-browser-repl.core
                                   :output-to "resources/public/js/compiled/cljs-browser-repl.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :optimizations :none
                                   :source-map-timestamp true}}
                       {:id "test"
                        :source-paths ["src/cljs" "test/cljs"]
                        :compiler {:output-to "resources/phantomjs/js/compiled/cljs-browser-repl.js"
                                   :pretty-print false
                                   :optimizations :whitespace
                                   :source-map-timestamp true}}
                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler { ;; :main cljs-browser-repl.core ;; https://github.com/emezeske/lein-cljsbuild/issues/420
                                   :output-to "resources/public/js/compiled/cljs-browser-repl.js"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :externs ["resources/cljs-browser-repl.ext.js"]}}
                       ]
              :test-commands {"unit" ["phantomjs"
                                      "resources/phantomjs/test.js"
                                      "resources/phantomjs/test.html"]}}

  :aliases {"fig-dev" ["do" "clean" ["figwheel" "dev"]]
            "minify" ^{:doc "Clean and compile sources minified for production."} ["do" "clean" ["cljsbuild" "once" "min"]]
            ;; Nested vectors are supported for the "do" task
            "deploy" ^{:doc "Clean, compile (minified) sources, test and then deploy."} ["do" "clean" ["test" ":integration"] ["deploy" "clojars"]]
            "unit-test" ^{:doc "Executes unit tests."} ["do" "clean" ["cljsbuild" "test" "unit"]]}

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.5"]
                                  [org.clojure/tools.nrepl "0.2.11"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :figwheel {:nrepl-port 5088
                              ;; Load CIDER, refactor-nrepl and piggieback middleware
                              :nrepl-middleware ["cider.nrepl/cider-middleware"
                                                 "refactor-nrepl.middleware/wrap-refactor"
                                                 "cemerick.piggieback/wrap-cljs-repl"]}}})
