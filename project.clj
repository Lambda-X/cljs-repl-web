(defproject cljs-repl-web "0.1.2-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [replumb/replumb "0.1.2"]
                 [cljsjs/jqconsole "2.13.1-0"]
                 [cljsjs/highlight "8.4-0"]
                 [reagent "0.5.1"]
                 [re-frame "0.5.0"]
                 [re-com "0.7.0-alpha2"]
                 [cljs-ajax "0.5.1"]
                 [endophile "0.1.2"]
                 [markdown-clj "0.9.78"]
                 [hickory "0.5.4"]
                 [cljsjs/showdown "0.4.0-1"]
                 [org.clojure/tools.reader "1.0.0-alpha1"]
                 [cljsjs/enquire "2.1.2-0"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-codox "0.9.0"]
            [lein-simpleton "1.4.0-SNAPSHOT"]]

  ;; from https://github.com/technomancy/leiningen/wiki/Faster
  ;; :eval-in :nrepl ;; enable this only if you know what you are doing
  :jvm-opts ^:replace []

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "resources/private/test" "target" "out"]
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs" "test/cljs"]
                        :figwheel {:on-jsload "cljs-repl-web.core/main"}
                        :compiler {:main cljs-repl-web.core
                                   :output-to "resources/public/js/compiled/cljs-repl-web.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :optimizations :none
                                   :source-map-timestamp true}}
                       {:id "test"
                        :source-paths ["src/cljs" "test/doo"]
                        :compiler {:main launcher.runner
                                   :output-to "resources/private/test/compiled/cljs-repl-web.js"
                                   :pretty-print false}}
                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler { ;; :main cljs-repl-web.core ;; AR - No main! https://github.com/emezeske/lein-cljsbuild/issues/420
                                   :closure-defines {:goog.DEBUG false}
                                   :output-to "resources/public/js/compiled/cljs-repl-web.js"
                                   :output-dir "resources/public/js/compiled/out/min"
                                   :source-map "resources/public/js/compiled/cljs-repl-web.js.map"
                                   :source-map-path "public/js/compiled"
                                   :source-map-timestamp true
                                   :optimizations :simple
                                   :pretty-print false}}]}

  ;; :prep-tasks ["run" "-m cljs-api.generator/-main"]

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
            "serve" ^{:doc "Compile minified and start a server on port 9090 at resources/public"} ["do" "cljsbuild" "once" "min" ["simpleton" "9090" ":from" "resources/public"]]
            "serve*" ^{:doc "Clean, compile minified and start a server on port 9090 at resources/public"} ["do" "clean" ["cljsbuild" "once" "min"] ["simpleton" "9090" ":from" "resources/public"]]
            ;; AR - does not work as expected
            ;; "bump" ^{:doc "Bump version and tags it, without any deployment on Clojars or website" } ["do" ["vcs" "assert-committed"]
            ;; ["change" "version" "leiningen.release/bump-version" "release"]
            ;; ["vcs" "commit"]
            ;; ["vcs" "tag"]
            ;; ["change" "version" "leiningen.release/bump-version"]
            ;; ["vcs" "commit"]]
            }

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.11"]
                                  [binaryage/devtools "0.4.1"]
                                  [org.clojars.stumitchell/clairvoyant "0.1.0-SNAPSHOT"]
                                  [day8/re-frame-tracer "0.1.0-SNAPSHOT"]]
                   :plugins [[lein-doo "0.1.6-SNAPSHOT"]
                             [lein-figwheel "0.5.0-1" :exclusions [cider/cider-nrepl]]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :figwheel {:nrepl-port 5088
                              :repl true
                              :css-dirs ["resources/public/styles/css/"]
                              ;; Load CIDER, refactor-nrepl and piggieback middleware
                              :nrepl-middleware ["cider.nrepl/cider-middleware"
                                                 "refactor-nrepl.middleware/wrap-refactor"
                                                 "cemerick.piggieback/wrap-cljs-repl"]}}})
