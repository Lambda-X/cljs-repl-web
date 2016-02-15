(set-env!
 :source-paths #{"src/cljs" "src/clj" "test/clj" "test/cljs"}
 :resource-paths #{"resouces/public/"}
 :dependencies '[;; Boot deps
                 [adzerk/boot-cljs            "1.7.228-1" :scope "test"]
                 [pandeiro/boot-http          "0.7.1-SNAPSHOT" :scope "test"]
                 [adzerk/boot-reload          "0.4.4" :scope "test"]
                 [degree9/boot-semver "1.2.0"]

                 ;; Repl
                 [adzerk/boot-cljs-repl       "0.3.0"]
                 [com.cemerick/piggieback     "0.2.1"  :scope "test"]
                 [weasel                      "0.7.0"  :scope "test"]
                 [org.clojure/tools.nrepl     "0.2.12" :scope "test"]

                 ;; Tests
                 [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT"  :scope "test"]
                 [adzerk/boot-test            "1.0.7"      :scope "test"]

                 ;; App deps
                 [org.clojure/clojure         "1.7.0"]
                 [org.clojure/clojurescript   "1.7.170"]
                 [org.clojure/core.async      "0.2.374"]
                 [reagent                     "0.5.0"]
                 [re-frame                    "0.5.0"]
                 [replumb/replumb             "0.1.3-SNAPSHOT"]
                 [cljsjs/jqconsole            "2.13.1-0"]
                 [cljsjs/highlight            "8.4-0"]
                 [re-com                      "0.7.0-alpha2"]
                 [cljs-ajax                   "0.5.1"]
                 [endophile                   "0.1.2"]
                 [markdown-clj                "0.9.78"]
                 [hickory                     "0.5.4"]
                 [cljsjs/showdown             "0.4.0-1"]
                 [org.clojure/tools.reader    "1.0.0-alpha1"]
                 [cljsjs/enquire              "2.1.2-0"]
                 [com.cemerick/piggieback     "0.2.1"]
                 [binaryage/devtools          "0.4.1"]
                 [day8/re-frame-tracer        "0.1.0-SNAPSHOT"]
                 [cljsjs/codemirror           "5.10.0-0"]])

(require '[adzerk.boot-cljs             :refer [cljs]]
         '[adzerk.boot-reload           :refer [reload]]
         '[adzerk.boot-test             :as boot-test]
         '[pandeiro.boot-http           :refer [serve]]
         '[crisptrutski.boot-cljs-test  :refer [test-cljs]]
         '[adzerk.boot-cljs-repl        :refer [cljs-repl start-repl]]
         '[boot-semver.core :refer :all])

(def +version+ (get-version))

(task-options!
 pom {:project "cljs-repl-web"
      :version +version+}
 test-cljs {:js-env :phantom})

(def compiler-options
  {:closure-defines {"goog.DEBUG" false}
   :source-map :true
   :optimizations :none
   :source-map-timestamp true})

(deftask build []
  (set-env! :source-paths #{"src/clj" "src/cljs" "env/prod/cljs"})
  (comp (cljs :compiler-options compiler-options)))

(deftask dev
  "Start the dev env..."
  []
  (set-env! :source-paths #{"src/clj" "src/cljs" "env/dev/cljs"})
  (comp (serve :dir "resources/public")
        (watch)
        (cljs-repl)
        (cljs :compiler-options compiler-options)
        (reload :on-jsload 'cljs-repl-web.core/main)))

(deftask test
  "Run tests.."
  []
  (set-env! :source-paths #{"test/clj" "test/cljs"})
  (comp
   (speak)
   (test-cljs :namespaces #{"cljs-repl-web.core-test"})
   (boot-test/test)))

(deftask auto-test []
  (comp (watch) (test)))
