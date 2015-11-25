(ns cljs-repl-web.cljs-api.utils)

(defrecord Section [title additional-info topics])
(defrecord Topic [title symbols])

;;; A map of sections and topics we want to display in our API panel.
;;; We can easily add or remove sections/topics and they will be automatically
;;; displayed in the API panel.
(def custom-api-map
  {:sections [(Section. "Useful Functions" ""
                        [(Topic. "math"
                                 '(+ - * / quot rem mod inc dec max min rand))
                         (Topic. "comparison"
                                 '(= == not= < > <= >=))
                         (Topic. "predicates"
                                 '(nil? identical? zero? pos? neg? even? odd? true? false?
                                        distinct? empty? every? not-every? some not-any?))
                         (Topic. "higher-order functions"
                                 '(map map-indexed mapcat reduce filter partition-by
                                       take-while keep remove merge-with iterate repeatedly))

                         (Topic. "functions as data"
                                 '(apply partial comp complement juxt))])
              (Section. "Datatypes" ""
                        [(Topic. "maps " '({:key1 val1 :key2 val2}))
                         (Topic. "vectors" '([1 2 3] [:a :b :c]))
                         (Topic. "sets" '(#{:a :b :c 1 2 3}))
                         (Topic. "scalars" '(a-symbol :a-keyword "\"a-string\""))
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
              (Section. "Sequences" ""
                        [(Topic. "creation"
                                 '(vec hash-map set for list list* sorted-map
                                       repeat range cycle seq rseq))
                         (Topic. "inspection"
                                 '(first last rest next get get-in count keys
                                         vals nth contains? find))
                         (Topic. "manipulation"
                                 '(into conj cons assoc flatten merge assoc-in
                                        dissoc zipmap partition update-in reverse
                                        take drop distinct))])
              (Section. "Useful Macros" ""
                        [(Topic. "conditionals"
                                 '(if if-not if-let cond condp and or not when when-let
                                      when-not case))
                         (Topic. "nesting, chaining, and interop"
                                 '(-> ->> doto .. .))
                         (Topic. "defining things"
                                 '(def defn fn let letfn defmulti defmethod
                                    deftype defrecord reify this-as declare ns))])


              (Section. "JavaScript Interop" ""
                        [(Topic. "method call" '("(.the-method target-object args...)"))
                         (Topic. "property access" '("(.-property target-object -property)"))
                         (Topic. "property setting" '("(set! (.-title js/document) \"Hi!\")"))
                         (Topic. "direct javascript" '("(js/alert \"Hello world!\")"))
                         (Topic. "external library use" '("(.text (js/jQuery \"#title\") \"ClojureScript Rocks!\")"))])]})
