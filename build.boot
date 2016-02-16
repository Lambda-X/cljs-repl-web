(set-env!
 :dependencies '[;; Boot deps
                 [adzerk/boot-cljs            "1.7.228-1" :scope "test"]
                 [pandeiro/boot-http          "0.7.1-SNAPSHOT" :scope "test"]
                 [adzerk/boot-reload          "0.4.4" :scope "test"]
                 [degree9/boot-semver         "1.2.1" :scope "test"]

                 ;; Repl
                 [adzerk/boot-cljs-repl       "0.3.0"  :scope "test"]
                 [com.cemerick/piggieback     "0.2.1"  :scope "test"]
                 [weasel                      "0.7.0"  :scope "test"]
                 [org.clojure/tools.nrepl     "0.2.12" :scope "test"]

                 ;; Tests
                 [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT"  :scope "test"]
                 [adzerk/boot-test            "1.0.7"      :scope "test"]

                 ;; App deps
                 [org.clojure/clojure         "1.7.0"]
                 [org.clojure/clojurescript   "1.7.228"]
                 [org.clojure/core.async      "0.2.374"]
                 [reagent                     "0.5.1"]
                 [re-frame                    "0.5.0"]
                 [replumb/replumb             "0.1.5-SNAPSHOT"]
                 [cljsjs/jqconsole            "2.13.1-0"]
                 [cljsjs/highlight            "8.4-0"]
                 [re-com                      "0.7.0-alpha2"]
                 [cljs-ajax                   "0.5.1"]
                 [endophile                   "0.1.2"]
                 [markdown-clj                "0.9.78"]
                 [hickory                     "0.5.4"]
                 [cljsjs/showdown             "0.4.0-1"]
                 [org.clojure/tools.reader    "1.0.0-alpha3"]
                 [cljsjs/enquire              "2.1.2-0"]
                 [com.cemerick/piggieback     "0.2.1"]
                 [org.clojars.stumitchell/clairvoyant "0.2.0"]
                 [binaryage/devtools          "0.5.2"]
                 [day8/re-frame-tracer        "0.1.0-SNAPSHOT"]
                 [cljsjs/codemirror           "5.10.0-0"]])

(require '[adzerk.boot-cljs             :refer [cljs]]
         '[adzerk.boot-reload           :refer [reload]]
         '[pandeiro.boot-http           :refer [serve]]
         '[crisptrutski.boot-cljs-test  :refer [test-cljs]]
         '[adzerk.boot-cljs-repl        :refer [cljs-repl start-repl]]
         '[boot-semver.core :refer :all])

(def +version+ (get-version))

(task-options! pom {:project "cljs-repl-web"
                    :version +version+}
               test-cljs {:js-env :phantom
                          :out-file "phantom-tests.js"})

;;;;;;;;;;;;;;;;;;;;;;
;;;    Options     ;;;
;;;;;;;;;;;;;;;;;;;;;;

(def dev-compiler-options
  {:source-map-timestamp true})

(def prod-compiler-options
  {:closure-defines {"goog.DEBUG" false}
   :optimize-constants true
   :static-fns true
   :elide-asserts true
   :pretty-print false
   :source-map-timestamp true})

(def test-namespaces
  #{"cljs-repl-web.core-test"
    "cljs-repl-web.code-mirror.app-test"})

(defmulti options
  "Return the correct option map for the build, dispatching on identity"
  identity)

(defmethod options :dev
  [selection]
  {:type :dev
   :env {:source-paths #{"src/clj" "src/cljs" "env/dev/cljs"}
         :resource-paths #{"resources/public/"}
         :cljs {:source-map true
                :optimizations :none
                :compiler-options dev-compiler-options}}
   :test-cljs {:optimizations :none
               :cljs-opts dev-compiler-options
               :namespaces test-namespaces}})

(defmethod options :prod
  [selection]
  {:type :prod
   :env {:source-paths #{"src/clj" "src/cljs" "env/prod/cljs"}
         :resource-paths #{"resources/public/"}}
   :cljs {:source-map true
          :optimizations :simple
          :compiler-options prod-compiler-options}
   :test-cljs {:optimizations :simple
               :cljs-opts prod-compiler-options
               :namespaces test-namespaces}})

(deftask version-file
  "A task that includes the version.properties file in the fileset."
  []
  (boot.util/info "Add version.properties...\n")
  (with-pre-wrap [fileset]
    (-> fileset
        (add-resource (java.io.File. ".") :include #{#"^version\.properties$"})
        commit!)))

(deftask build
  "Build the final artifact, if no type is passed in, it builds production."
  [t type VAL kw "The build type, either prod or dev"]

  (let [options (options (or type :prod))]
    (boot.util/info "Building %s profile...\n" (:type options))
    (apply set-env! (reduce #(into %2 %1) [] (:env options)))
    (comp (apply cljs (reduce #(into %2 %1) [] (:cljs options)))
          (version-file)
          (target))))

(deftask dev
  "Start the dev interactive environment."
  []
  (boot.util/info "Starting interactive dev...\n")
  (let [options (options :dev)]
    (apply set-env! (reduce #(into %2 %1) [] (:env options)))
    (comp (serve)
          (watch)
          (cljs-repl)
          (build :type :dev)
          (reload :on-jsload 'cljs-repl-web.core/main))))

;; This prevents a name collision WARNING between the test task and
;; clojure.core/test, a function that nobody really uses or cares
;; about.
(ns-unmap 'boot.user 'test)

(deftask test
  "Run tests, if no type is passed in, it tests against production."
  [t type VAL kw "The build type, either prod or dev"]
  (let [options (options (or type :prod))]
    (boot.util/info "Testing %s profile...\n" (:type options))
    (set-env! :source-paths (conj (get-in options [:env :source-paths]) "test/cljs" ))
    (apply test-cljs (reduce #(into %2 %1) [] (:test-cljs options)))))

(deftask auto-test
  "Run tests while updating on file change.

  Always runs against :dev.

  It automatically enables test sound notifications, use the -n parameter for
  switching them off."
  [n no-sounds bool "Enable notifications during tests"]
  (comp (watch)
        (if no-sounds identity (speak))
        (test :type :dev)))
