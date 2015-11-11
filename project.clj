(defproject cljs-browser-repl "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.145"]
                 [replumb/replumb "0.1.0"]
                 [cljsjs/jqconsole "2.13.1-0"]
                 [cljsjs/highlight "8.4-0"]
                 [reagent "0.5.1"]
                 [re-com "0.7.0-alpha1"]
                 [timothypratley/reanimated "0.1.1"]
                 [cljs-ajax "0.5.1"]
                 [endophile "0.1.2"]
                 [markdown-clj "0.9.78"]
                 [hickory "0.5.4"]]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-codox "0.9.0"]
            ;; [lein-simpleton "1.4.0-SNAPSHOT"]
            ]

  ;; from https://github.com/technomancy/leiningen/wiki/Faster
  ;; :eval-in :nrepl ;; enable this only if you know what you are doing
  :jvm-opts ^:replace []

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "resources/private/test" "target" "out"]
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs" "test/cljs"]
                        :figwheel {:on-jsload "launcher.test/run"}
                        :compiler {:main cljs-browser-repl.core
                                   :output-to "resources/public/js/compiled/cljs-browser-repl.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :optimizations :none
                                   :source-map-timestamp true}}
                       {:id "test"
                        :source-paths ["src/cljs" "test/doo"]
                        :compiler {:main launcher.runner
                                   :output-to "resources/private/test/compiled/cljs-browser-repl.js"
                                   :pretty-print false}}
                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler { ;; :main cljs-browser-repl.core ;; https://github.com/emezeske/lein-cljsbuild/issues/420
                                   :output-to "resources/public/js/compiled/cljs-browser-repl.js"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :externs ["resources/cljs-browser-repl.ext.js"]}}]}

  ;; :prep-tasks ["run" "-m cljs-api.generator/-main" "src/cljs/cljs_browser_repl/cljs_api.cljs"]

  :aliases {"fig-dev" ^{:doc "Start figwheel with dev profile."} ["figwheel" "dev"]
            "fig-dev*" ^{:doc "Clean and start figwheel with dev profile"} ["do" "clean" ["figwheel" "dev"]]
            "minify" ^{:doc "Clean and compile sources minified for production."} ["do" "clean" ["cljsbuild" "once" "min"]]
            "deploy" ^{:doc "Clean, compile (minified) sources, test and then deploy."} ["do" "clean" ["test" ":integration"] ["deploy" "clojars"]]
            "test-phantom" ^{:doc "Execute once unit tests with PhantomJS (must be installed)."} ["doo" "phantom" "test" "once"]
            "test-phantom*" ^{:doc "Clean and execute once unit tests with PhantomJS (must be installed)."} ["do" "clean" ["doo" "phantom" "test" "once"]]
            "auto-phantom" ^{:doc "Clean and execute automatic unit tests with PhantomJS (must be installed)."} ["do" "clean" ["doo" "phantom" "test" "auto"]]
            "test-slimer" ^{:doc "Execute once unit tests with SlimerJS (must be installed)."} ["doo" "slimer" "test" "once"]
            "test-slimer*" ^{:doc "Clean and execute once unit tests with SlimerJS (must be installed)."} ["do" "clean" ["doo" "slimer" "test" "once"]]
            "auto-slimer" ^{:doc "Clean and execute automatic unit tests with SlimerJS (must be installed)."} ["do" "clean" ["doo" "slimer" "test" "auto"]]
            "tests" ^{:doc "Execute once unit tests with PhantomJS and SlimerJS (must be installed)."} ["doo" "headless" "test" "once"]
            "tests*" ^{:doc "Clean and execute once unit tests with PhantomJS and SlimerJS (must be installed)."} ["do" "clean" ["doo" "headless" "test" "once"]]
            "serve" ^{:doc "Compile and start a server on port 5042 at resources/public"} ["do" "cljsbuild" "once" "dev" ["simpleton" "5042" ":from" "resources/public"]]
            "serve*" ^{:doc "Clean, compile and start a server on port 5042 at resources/public"} ["do" "clean" ["cljsbuild" "once" "dev"] ["simpleton" "5042" ":from" "resources/public"]]}

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.5"]
                                  [org.clojure/tools.nrepl "0.2.11"]]
                   :plugins [[lein-doo "0.1.6-SNAPSHOT"]
                             [lein-figwheel "0.4.1" :exclusions [cider/cider-nrepl]]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :figwheel {:nrepl-port 5088
                              :repl true
                              :css-dirs ["resources/public/styles/css/"]
                              ;; Load CIDER, refactor-nrepl and piggieback middleware
                              :nrepl-middleware ["cider.nrepl/cider-middleware"
                                                 "refactor-nrepl.middleware/wrap-refactor"
                                                 "cemerick.piggieback/wrap-cljs-repl"]}}})
