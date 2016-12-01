(def clojure-dep '[org.clojure/clojure "1.9.0-alpha4"])
(def clojurescript-dep '[org.clojure/clojurescript "1.9.36"])

(set-env!
 :source-paths #{"dev"}
 :dependencies (conj '[;; Boot deps
                       [adzerk/boot-cljs            "1.7.228-1" :scope "test"]
                       [pandeiro/boot-http          "0.7.2"     :scope "test"]
                       [adzerk/boot-reload          "0.4.8"     :scope "test"]
                       [degree9/boot-semver         "1.2.4"     :scope "test"]
                       [replumb/boot-pack-source    "0.1.2-1"   :scope "test"]
                       [confetti/confetti           "0.1.2-SNAPSHOT"     :scope "test"]
                       [adzerk/env                  "0.3.0"     :scope "test"]

                       ;; Repl
                       [adzerk/boot-cljs-repl       "0.3.0"    :scope "test"]
                       [com.cemerick/piggieback     "0.2.1"    :scope "test"]
                       [weasel                      "0.7.0"    :scope "test"]
                       [org.clojure/tools.nrepl     "0.2.12"   :scope "test"]
                       [com.cognitect/transit-clj   "0.8.285"  :scope "test"] ;; makes repl faster

                       ;; Tests
                       [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT" :scope "test"]

                       ;; App deps
                       [org.clojure/core.async      "0.2.374"]
                       [reagent                     "0.6.0-SNAPSHOT"]
                       [re-frame                    "0.7.0"]
                       [replumb/replumb             "0.2.2-SNAPSHOT"]
                       [cljsjs/highlight            "8.4-0"]
                       [re-console                  "0.1.4-SNAPSHOT"]
                       [re-com                      "0.8.1"]
                       [cljs-ajax                   "0.5.1"]
                       [hickory                     "0.5.4"]
                       [cljsjs/showdown             "0.4.0-1"]
                       [org.clojure/tools.reader    "1.0.0-alpha3"]
                       [cljsjs/enquire              "2.1.2-0"]
                       [com.cemerick/piggieback     "0.2.1"]
                       [org.clojars.stumitchell/clairvoyant "0.2.0"]
                       [binaryage/devtools          "0.6.0"]
                       [day8/re-frame-tracer        "0.1.0-SNAPSHOT"]
                       [cljsjs/codemirror           "5.10.0-0"]
                       [adzerk/cljs-console         "0.1.1"]
                       [re-complete                 "0.1.4-1-SNAPSHOT"]
                       [com.andrewmcveigh/cljs-time "0.5.0-alpha1"]]
                     clojure-dep clojurescript-dep))

(def pack-source-deps (conj '[[replumb/replumb             "0.2.2-SNAPSHOT"]
                              [org.clojure/tools.reader    "1.0.0-alpha3"]]
                            clojurescript-dep))

(def cljs-api-deps (conj '[[org.clojure/tools.reader    "1.0.0-alpha3"]
                           [endophile                   "0.1.2"]
                           [markdown-clj                "0.9.78"]]
                         clojure-dep))

(require '[adzerk.boot-cljs            :refer [cljs]]
         '[adzerk.boot-reload          :refer [reload]]
         '[pandeiro.boot-http          :refer [serve]]
         '[crisptrutski.boot-cljs-test :refer [test-cljs exit!]]
         '[adzerk.boot-cljs-repl       :refer [cljs-repl start-repl]]
         '[boot-semver.core            :refer :all]
         '[boot.pod                    :as pod]
         '[clojure.pprint              :refer [pprint]]
         '[replumb.boot-pack-source    :refer [pack-source]]
         '[confetti.boot-confetti      :refer [create-site sync-bucket]]
         '[adzerk.env                  :as env]
         '[lambdax.boot.addons         :as addons])

(def +version+ (get-version))

;;;;;;;;;;;;;;;;;;;;;;;
;;;  Env Variables  ;;;
;;;;;;;;;;;;;;;;;;;;;;;

(env/def
  AWS_BUCKET nil
  AWS_ACCESS_KEY nil
  AWS_SECRET_KEY nil
  AWS_CLOUDFRONT_ID nil)

;;;;;;;;;;;;;;;;;;;;;;
;;;    Options     ;;;
;;;;;;;;;;;;;;;;;;;;;;

(task-options! pom {:project "cljs-repl-web"
                    :version +version+}
               test-cljs {:js-env :phantom
                          :out-file "phantom-tests.js"})

(def foreign-libs
  [{:file "resources/public/js/clojure-parinfer.js"
    :provides ["parinfer.codemirror.mode.clojure.clojure-parinfer"]}])

(def prod-compiler-options
  {:closure-defines {"goog.DEBUG" false
                     "clairvoyant.core.devmode" false}
   :optimize-constants true
   :static-fns true
   :elide-asserts true
   :pretty-print false
   :source-map-timestamp true
   :dump-core false
   :parallel-build true
   :foreign-libs foreign-libs})

(def dev-compiler-options
  (merge prod-compiler-options
         {:closure-defines {"goog.DEBUG" true
                            "clairvoyant.core.devmode" true}}))

(defmulti options
  "Return the correct option map for the build, dispatching on identity"
  identity)

(defmethod options :generators
  [selection]
  {:type :generator
   :env {:source-paths #{"dev" "src/clj"}
         :resource-paths #{"dev-resources"}}})

(defmethod options :dev
  [selection]
  {:type :dev
   :props {"CLJS_LOG_LEVEL" "DEBUG"}
   :env {:source-paths #{"src/clj" "src/cljs" "env/dev/cljs" "dev"}
         :resource-paths #{"resources/public/"}}
   :cljs {:source-map true
          :optimizations :simple
          :compiler-options dev-compiler-options}
   :test-cljs {:optimizations :simple
               :cljs-opts dev-compiler-options
               :suite-ns 'cljs-repl-web.suite}})

(defmethod options :prod
  [selection]
  {:type :prod
   :props {"CLJS_LOG_LEVEL" "INFO"}
   :env {:source-paths #{"src/clj" "src/cljs" "env/prod/cljs"}
         :resource-paths #{"resources/public/"}}
   :cljs {:optimizations :simple
          :compiler-options prod-compiler-options}
   :test-cljs {:optimizations :simple
               :cljs-opts prod-compiler-options
               :suite-ns 'cljs-repl-web.suite}})

(defn set-system-properties!
  "Set a system property for each entry in the map m."
  [m]
  (doseq [kv m]
    (System/setProperty (key kv) (val kv))))

(deftask version-file
  "A task that includes the version.properties file in the fileset."
  []
  (with-pre-wrap [fileset]
    (boot.util/info "Add version.properties...\n")
    (-> fileset
        (add-resource (java.io.File. ".") :include #{#"^version\.properties$"})
        commit!)))

(declare add-cache add-cljs-source)

;;;;;;;;;;;;;;;;;;
;;  MAIN TASKS  ;;
;;;;;;;;;;;;;;;;;;

(deftask build
  "Build the final artifact, if no type is passed in, it builds production."
  [t type VAL kw "The build type, either prod or dev"]
  (let [options (options (or type :prod))]
    (boot.util/info "Building %s profile...\n" (:type options))
    (apply set-env! (reduce #(into %2 %1) [] (:env options)))
    (set-system-properties! (:props options))
    (comp (version-file)
          (apply cljs (reduce #(into %2 %1) [] (:cljs options)))
          (sift :include #{#"main.out"}
                :invert true)
          (add-cljs-source)
          (add-cache :dir "js-cache"))))

(deftask dev
  "Start the dev interactive environment."
  []
  (boot.util/info "Starting interactive dev...\n")
  (let [options (options :dev)]
    (apply set-env! (reduce #(into %2 %1) [] (:env options)))
    (set-system-properties! (:props options))
    (comp (version-file)
          (watch)
          (cljs-repl)
          (reload :on-jsload 'cljs-repl-web.core/main)
          (apply cljs (reduce #(into %2 %1) [] (:cljs options)))
          (add-cljs-source)
          (add-cache :dir "js-cache")
          (serve))))

;;;;;;;;;;;;;;;;;;;;;
;;  TEST (please)  ;;
;;;;;;;;;;;;;;;;;;;;;

;; This prevents a name collision WARNING between the test task and
;; clojure.core/test, a function that nobody really uses or cares
;; about.
(ns-unmap 'boot.user 'test)

(defn test-cljs-opts
  [options namespaces exit?]
  (cond-> options
    namespaces (-> (update-in [:test-cljs :suite-ns] (fn [_] nil))
                   (assoc-in [:test-cljs :namespaces] namespaces))
    exit? (assoc-in [:test-cljs :exit?] exit?)))

(defn set-test-env!
  [options]
  (apply set-env! (reduce #(into %2 %1) [] (update-in (:env options) [:source-paths] conj "test/cljs"))))

(deftask test
  "Run tests once.

   If no type is passed in, it tests against the production build. It
   optionally accepts (a set of) regular expressions that are used for testing
   only some namespaces."
  [t type       VAL        kw       "The build type, either prod or dev"
   n namespace  NAMESPACE  #{regex} "Namespace regex to test against"]
  (let [options (-> (options (or type :prod))
                    (test-cljs-opts namespace true))]
    (boot.util/info "Testing options %s\n" (with-out-str (pprint options)))
    (set-test-env! options)
    (apply test-cljs (reduce #(into %2 %1) [] (:test-cljs options)))))

(deftask auto-test
  "Run tests watching for file changes.

  If no type is passed in, it tests against the production build. It optionally
  accepts (a set of) regular expressions that are used for testing only some
  namespaces."
  [t type      VAL       kw       "The build type, either prod or dev"
   n namespace NAMESPACE #{regex} "Namespace regex to test against"]
  (let [options (-> (options (or type :prod))
                    (test-cljs-opts namespace false))]
    (set-test-env! options)
    (comp (watch)
          (apply test-cljs (reduce #(into %2 %1) [] (:test-cljs options))))))

;;;;;;;;;;;;;;;;;;;
;;  OTHER TASKS  ;;
;;;;;;;;;;;;;;;;;;;

(deftask add-cljs-source
  []
  (comp (with-pre-wrap [fs]
          (boot.util/info "Pack source files...\n")
          fs)
        (pack-source :to-dir "cljs-src"
                     :deps (into #{} pack-source-deps)
                     :exclusions '#{org.clojure/clojure
                                    org.mozilla/rhino})))

(deftask deploy-s3
  [y dry-run bool "Run dryly :)"
   p prune   bool "Delete files from S3 bucket not in the current fileset"]
  (let [bucket (get (env/env) "AWS_BUCKET")]
    (boot.util/info "Deploying on bucket %s...\n" bucket)
    (sync-bucket :dry-run dry-run
                 :prune prune
                 :bucket bucket
                 :access-key (get (env/env) "AWS_ACCESS_KEY")
                 :secret-key (get (env/env) "AWS_SECRET_KEY")
                 :cloudfront-id (get (env/env) "AWS_CLOUDFRONT_ID"))))

(deftask cljs-api
  "The task generates the Clojurescript API and the cljs-repl-web.cljs-api
  namespace.

  It does NOT add it to the fileset, but calls cljs-api.generator/-main and
  dumps in src/cljs (hard coded). It should not be part of the build pipeline
  unless there is a ClojureScript version change, in which case it should be
  executed once:

  # boot cljs-api"
  []
  (let [custom-env (:env (options :generators))
        pod-env (-> (get-env)
                    (assoc :dependencies cljs-api-deps)
                    (update :directories concat
                            (:source-paths custom-env)
                            (:resource-paths custom-env)))
        pod (future (pod/make-pod pod-env))]
    (with-pass-thru fs
      (boot.util/info "Generating cljs-api...\n")
      (pod/with-eval-in @pod
        (require 'cljs-api.generator)
        (cljs-api.generator/-main)))))

(def dump-cache-deps '[[boot/core "2.6.0-SNAPSHOT"]
                       [com.cognitect/transit-clj "0.8.285"]])

(deftask transit-jsonify
  "Materializes the transit+json file resulting from the input transit file path.

  The new file will be added to the fileset root and it is up to the next tasks
  to move it to the proper place (use sift --move for this)."
  [p transit-path PATH str "The fileset path to the cache file in transit format"
   n json-name    NAME str "The name of the transit+json file"]
  (let [custom-env (:env (options :generators))
        pod-env (-> (get-env)
                    (assoc :dependencies dump-cache-deps)
                    (update :directories concat
                            (:source-paths custom-env)
                            (:resource-paths custom-env)))
        pod (future (pod/make-pod pod-env))]
    (dbug "transit-path %s - json-name %s\n" transit-path json-name)
    (with-pre-wrap fs
      (commit!
       (let [tmp-dir (tmp-dir!)
             input-path (->> transit-path (tmp-get fs) (tmp-file) (.getPath))
             out-path (str (addons/normalize-path (.getPath tmp-dir)) json-name)]
         (let [new-fs (if-let [transit-json (pod/with-eval-in @pod
                                              (require '[lambdax.boot.addons :as addons])
                                              (.getPath (addons/transit-json ~input-path ~out-path)))]
                        (do (dbug "Conversion produced %s\n" transit-json)
                            (add-resource fs tmp-dir))
                        (do (warn "Could not perform Transit/Json conversion, skipping...\n")
                            fs))]
           (pod/destroy-pod @pod) ;; AR - ugly, I need to find a better way
           new-fs))))))

(deftask add-cache
  "The task fetches the core.cljs.cache.aot file from your .m2, and materializes it on the classpath.

  It is added to the filese so it should be part of the build pipeline:

  $ boot build add-cache target"
  [d dir PATH str "The dir path where to dump the cljs.core cache file"]
  (assert dir "The dir param cannot be nil")
  (let [dir (addons/normalize-path dir)
        cache-json-name "core.cljs.cache.aot.json"
        cache-fs-path "cljs/core.cljs.cache.aot.edn"
        cache-fs-path-regex (re-pattern cache-fs-path)]
    (comp (with-pass-thru fs
            (info "Adding cljs.core cache to %s...\n" dir))
          (sift :add-jar {(first clojurescript-dep) cache-fs-path-regex})
          (transit-jsonify :transit-path cache-fs-path :json-name cache-json-name)
          (sift :include #{cache-fs-path-regex} :invert true)
          (sift :move {(re-pattern cache-json-name) (str dir cache-json-name)}))))
