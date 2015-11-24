(ns cljs-repl-web.cljs-api.utils)

(defrecord Section [title additional-info topics])
(defrecord Topic [title symbols])

;;; A map of sections and topics we want to display in our API panel.
;;; We can easily add or remove sections/topics and they will be automatically
;;; displayed in the API panel.
(def custom-api-map
  {:sections [(Section. "Useful Macros" ""
                        [(Topic. "conditionals"
                                 '(if if-not if-let cond condp and or not when when-let
                                   when-not case))
                         (Topic. "nesting, chaining, and interop"
                                 '(-> ->> doto .. .))
                         (Topic. "defining things"
                                 '(def defn fn let letfn binding defmulti defmethod
                                    deftype defrecord reify this-as declare ns))])
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
              (Section. "Useful Functions" ""
                        [(Topic. "math"
                                 '(+ - * / quot rem mod inc dec max min))
                         (Topic. "comparison"
                                 '(= == not= < > <= >=))
                         (Topic. "predicates"
                                 '(nil? identical? zero? pos? neg? even? odd? true? false?
                                   distinct? empty? every? not-every? some not-any?))
                         (Topic. "data processing"
                                 '(map map-indexed mapcat reduce filter partition split-at
                                   split-with))
                         (Topic. "data create"
                                 '(vector vec hash-map set for list list* repeat range iterate cycle))
                         (Topic. "data inspection"
                                 '(first second last rest get get-in keys vals count nth contains? find
                                   take take-while ))
                         (Topic. "data manipulation"
                                 '(seq into conj cons assoc assoc-in dissoc zipmap
                                   merge merge-with select-keys update-in reverse))
                         (Topic. "function creation"
                                 '(apply partial comp complement juxt))])
              (Section. "JavaScript Interop" ""
                        [(Topic. "method call" '("(.the-method target-object args...)"))
                         (Topic. "property access" '("(.-property target-object -property)"))
                         (Topic. "property setting" '("(set! (.-title js/document) \"Hi!\")"))
                         (Topic. "direct javascript" '("(js/alert \"Hello world!\")"))
                         (Topic. "external library use" '("(.text (js/jQuery \"#title\") \"ClojureScript Rocks!\")"))])]
})
