(ns cljs-repl-web.cljs-api)

(def cljs-api-edn {:symbols
 {"butlast"
  {:description
   "Returns a sequence of all but the last item in `s`.\n\n`butlast` runs in linear time.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;butlast &#91;1 2 3&#93;&#41;\n;;=&gt; &#40;1 2&#41;\n\n&#40;butlast &#91;1 2&#93;&#41;\n;;=&gt; &#40;1&#41;\n\n&#40;butlast &#91;1&#93;&#41;\n;;=&gt; nil\n\n&#40;butlast &#91;&#93;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "butlast",
   :signature ["[s]"],
   :type "function",
   :related
   ["cljs.core/first"
    "cljs.core/rest"
    "cljs.core/last"
    "cljs.core/next"
    "cljs.core/drop-last"
    "cljs.core/take-last"],
   :examples-strings
   [["(butlast [1 2 3])"
     "(butlast [1 2])"
     "(butlast [1])"
     "(butlast [])"]],
   :examples
   [{:id "7a4676",
     :content
     "```clj\n(butlast [1 2 3])\n;;=> (1 2)\n\n(butlast [1 2])\n;;=> (1)\n\n(butlast [1])\n;;=> nil\n\n(butlast [])\n;;=> nil\n```"}],
   :full-name "cljs.core/butlast",
   :docstring
   "Return a seq of all but the last item in coll, in linear time"},
  "PersistentArrayMap.EMPTY"
  {:ns "cljs.core",
   :name "PersistentArrayMap.EMPTY",
   :type "var",
   :full-name "cljs.core/PersistentArrayMap.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "float"
  {:ns "cljs.core",
   :name "float",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/float",
   :examples-strings [],
   :examples-htmls []},
  "*print-level*"
  {:ns "cljs.core",
   :name "*print-level*",
   :type "dynamic var",
   :full-name "cljs.core/*print-level*",
   :docstring
   "*print-level* controls how many levels deep the printer will\nprint nested objects. If it is bound to logical false, there is no\nlimit. Otherwise, it must be bound to an integer indicating the maximum\nlevel to print. Each argument to print is at level 0; if an argument is a\ncollection, its items are at level 1; and so on. If an object is a\ncollection and is at a level greater than or equal to the value bound to\n*print-level*, the printer prints '#' to represent it. The root binding\nis nil indicating no limit.",
   :examples-strings [],
   :examples-htmls []},
  "re-pattern"
  {:description
   "Returns an instance of RegExp which has compiled the provided string.",
   :ns "cljs.core",
   :name "re-pattern",
   :signature ["[s]"],
   :type "function",
   :full-name "cljs.core/re-pattern",
   :docstring
   "Returns an instance of RegExp which has compiled the provided string.",
   :examples-strings [],
   :examples-htmls []},
  "gen-apply-to"
  {:ns "cljs.core",
   :name "gen-apply-to",
   :signature ["[]"],
   :type "macro",
   :full-name "cljs.core/gen-apply-to",
   :examples-strings [],
   :examples-htmls []},
  "Subvec"
  {:ns "cljs.core",
   :name "Subvec",
   :signature ["[meta v start end __hash]"],
   :type "type",
   :full-name "cljs.core/Subvec",
   :examples-strings [],
   :examples-htmls []},
  "js-mod"
  {:description
   "Returns the modulus of dividing numerator `n` by denominator `d`, with JavaScript's\noriginal behavior for negative numbers.\n\nReturns `NaN` when `d` is 0 (divide by 0 error).\n\nEquivalent to `x % y` in JavaScript.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;js-mod -5 3&#41;\n;;=&gt; -2\n\n&#40;js-mod 5 3&#41;\n;;=&gt; 2\n\n&#40;js-mod 5 0&#41;\n;;=&gt; NaN\n</code></pre>"],
   :ns "cljs.core",
   :name "js-mod",
   :signature ["[n d]"],
   :type "function/macro",
   :related ["cljs.core/mod"],
   :examples-strings [["(js-mod -5 3)" "(js-mod 5 3)" "(js-mod 5 0)"]],
   :examples
   [{:id "75fa6d",
     :content
     "```clj\n(js-mod -5 3)\n;;=> -2\n\n(js-mod 5 3)\n;;=> 2\n\n(js-mod 5 0)\n;;=> NaN\n```"}],
   :full-name "cljs.core/js-mod",
   :docstring
   "Modulus of num and div with original javascript behavior. i.e. bug for negative numbers"},
  "partial"
  {:description
   "Takes a function `f` and fewer than the normal arguments to `f`. Returns a\nfunction that takes a variable number of additional arguments. When called, the\nreturned function calls `f` with the original arguments plus the additional\narguments.\n\n`((partial f a b) c d)` => `(f a b c d)`",
   :examples-htmls [],
   :ns "cljs.core",
   :name "partial",
   :signature
   ["[f]"
    "[f arg1]"
    "[f arg1 arg2]"
    "[f arg1 arg2 arg3]"
    "[f arg1 arg2 arg3 & more]"],
   :type "function",
   :related ["cljs.core/comp" "cljs.core/juxt"],
   :examples-strings [],
   :full-name "cljs.core/partial",
   :docstring
   "Takes a function f and fewer than the normal arguments to f, and\nreturns a fn that takes a variable number of additional args. When\ncalled, the returned function calls f with args + additional args."},
  "TaggedLiteral"
  {:ns "cljs.core",
   :name "TaggedLiteral",
   :signature ["[tag form]"],
   :type "type",
   :full-name "cljs.core/TaggedLiteral",
   :examples-strings [],
   :examples-htmls []},
  "boolean"
  {:description
   "Return `false` if `x` is false or nil.  Otherwise return `true`.  This is the\ntruthiness condition used by `if` expressions.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;boolean 1&#41;\n;;=&gt; true\n\n&#40;boolean 0&#41;\n;;=&gt; true\n\n&#40;boolean nil&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "boolean",
   :signature ["[x]"],
   :type "function",
   :related ["special/if"],
   :examples-strings [["(boolean 1)" "(boolean 0)" "(boolean nil)"]],
   :examples
   [{:id "9edf3a",
     :content
     "```clj\n(boolean 1)\n;;=> true\n\n(boolean 0)\n;;=> true\n\n(boolean nil)\n;;=> false\n```"}],
   :full-name "cljs.core/boolean",
   :docstring "Coerce to boolean"},
  "remove-method"
  {:ns "cljs.core",
   :name "remove-method",
   :signature ["[multifn dispatch-val]"],
   :type "function",
   :full-name "cljs.core/remove-method",
   :docstring
   "Removes the method of multimethod associated with dispatch-value.",
   :examples-strings [],
   :examples-htmls []},
  "sorted-set"
  {:description "Returns a new sorted set with supplied `keys`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "sorted-set",
   :signature ["[& keys]"],
   :type "function",
   :related
   ["cljs.core/sorted-set-by"
    "cljs.core/subseq"
    "cljs.core/rsubseq"
    "cljs.core/sorted-map"],
   :examples-strings [],
   :full-name "cljs.core/sorted-set",
   :docstring "Returns a new sorted set with supplied keys."},
  "drop-last"
  {:description
   "Return a lazy sequence of all but the last `n` items in `s`.\n\n`n` defaults to 1.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "drop-last",
   :signature ["[s]" "[n s]"],
   :type "function",
   :related ["cljs.core/drop" "cljs.core/drop-while"],
   :examples-strings [],
   :full-name "cljs.core/drop-last",
   :docstring
   "Return a lazy sequence of all but the last n (default 1) items in coll"},
  "vector-seq"
  {:ns "cljs.core",
   :name "vector-seq",
   :signature ["[v offset]"],
   :type "function",
   :full-name "cljs.core/vector-seq",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-negate"
  {:ns "cljs.core",
   :name "unchecked-negate",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-negate",
   :examples-strings [],
   :examples-htmls []},
  "map"
  {:description
   "Returns a lazy sequence of applying function `f` to every element of `coll`.\n\nWhen more than one collection is provided, returns a lazy sequence consisting of\nthe result of applying `f` to the set of first items of each `c`, followed by\napplying `f` to the set of second items in each `c`, until any one of the `c`s\nis exhausted. Any remaining items in other `c`s are ignored. Function `f` should\naccept number-of-`c`s arguments.\n\nReturns a transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "map",
   :signature
   ["[f]"
    "[f coll]"
    "[f c1 c2]"
    "[f c1 c2 c3]"
    "[f c1 c2 c3 & colls]"],
   :type "function",
   :related
   ["cljs.core/map-indexed"
    "cljs.core/amap"
    "cljs.core/mapcat"
    "cljs.core/keep"
    "cljs.core/juxt"],
   :examples-strings [],
   :full-name "cljs.core/map",
   :docstring
   "Returns a lazy sequence consisting of the result of applying f to\nthe set of first items of each coll, followed by applying f to the\nset of second items in each coll, until any one of the colls is\nexhausted.  Any remaining items in other colls are ignored. Function\nf should accept number-of-colls arguments. Returns a transducer when\nno collection is provided."},
  "select-keys"
  {:description
   "Returns a map containing only those entries in `map` whose key is in `keys`.",
   :ns "cljs.core",
   :name "select-keys",
   :signature ["[map keys]"],
   :type "function",
   :full-name "cljs.core/select-keys",
   :docstring
   "Returns a map containing only those entries in map whose key is in keys",
   :examples-strings [],
   :examples-htmls []},
  "zipmap"
  {:description
   "Returns a map with `keys` mapped to corresponding `vals`.\n\n<pre>user=> (zipmap [:a :b :c :d] [1 2 3 4])\n{:a 1, :b 2, :c 3, :d 4}</pre>",
   :examples-htmls [],
   :ns "cljs.core",
   :name "zipmap",
   :signature ["[keys vals]"],
   :type "function",
   :related ["cljs.core/interleave"],
   :examples-strings [],
   :full-name "cljs.core/zipmap",
   :docstring
   "Returns a map with the keys mapped to the corresponding vals."},
  "rseq"
  {:description
   "Returns a sequence of the items in `coll` in reverse order in constant time.\n\nReturns nil if `coll` is empty.\n\n`coll` must be a vector or a sorted-map.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "rseq",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/reverse"],
   :examples-strings [],
   :full-name "cljs.core/rseq",
   :docstring
   "Returns, in constant time, a seq of the items in rev (which\ncan be a vector or sorted-map), in reverse order. If rev is empty returns nil"},
  "unchecked-int"
  {:ns "cljs.core",
   :name "unchecked-int",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/unchecked-int",
   :docstring "Coerce to int by stripping decimal places.",
   :examples-strings [],
   :examples-htmls []},
  "compare"
  {:description
   "Comparator.\n\nReturns a negative number, zero, or a positive number when `x` is logically\n\"less than\", \"equal to\", or \"greater than\" `y`.\n\nUses `IComparable` if available and `google.array.defaultCompare` for objects of\nthe same type. nil is treated as a special case and is always less than any\nother object.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;compare 10 12&#41;\n;;=&gt; -1\n\n&#40;compare 12 10&#41;\n;;=&gt; 1\n\n&#40;compare 10 10&#41;\n;;=&gt; 0\n\n&#40;compare 10 nil&#41;\n;;=&gt;  1\n\n&#40;compare 10 &#40;list 1 2 3&#41;&#41;\n;; Error: compare on non-nil objects of different types\n</code></pre>"],
   :ns "cljs.core",
   :name "compare",
   :signature ["[x y]"],
   :type "function",
   :related
   ["cljs.core/sort-by"
    "cljs.core/sorted-set-by"
    "cljs.core/sorted-map-by"],
   :examples-strings
   [["(compare 10 12)"
     "(compare 12 10)"
     "(compare 10 10)"
     "(compare 10 nil)"
     "(compare 10 (list 1 2 3))"]],
   :examples
   [{:id "e13fa0",
     :content
     "```clj\n(compare 10 12)\n;;=> -1\n\n(compare 12 10)\n;;=> 1\n\n(compare 10 10)\n;;=> 0\n\n(compare 10 nil)\n;;=>  1\n\n(compare 10 (list 1 2 3))\n;; Error: compare on non-nil objects of different types\n```"}],
   :full-name "cljs.core/compare",
   :docstring
   "Comparator. Returns a negative number, zero, or a positive number\n when x is logically 'less than', 'equal to', or 'greater than'\n y. Uses IComparable if available and google.array.defaultCompare for objects\nof the same type and special-cases nil to be less than any other object."},
  "IDerefWithTimeout"
  {:ns "cljs.core",
   :name "IDerefWithTimeout",
   :type "protocol",
   :full-name "cljs.core/IDerefWithTimeout",
   :examples-strings [],
   :examples-htmls []},
  "split-with"
  {:description
   "Returns a vector of `[(take-while pred coll) (drop-while pred coll)]`",
   :examples-htmls [],
   :ns "cljs.core",
   :name "split-with",
   :signature ["[pred coll]"],
   :type "function",
   :related
   ["cljs.core/split-at"
    "clojure.string/split"
    "cljs.core/take-while"
    "cljs.core/drop-while"],
   :examples-strings [],
   :full-name "cljs.core/split-with",
   :docstring
   "Returns a vector of [(take-while pred coll) (drop-while pred coll)]"},
  "Symbol"
  {:ns "cljs.core",
   :name "Symbol",
   :signature ["[ns name str _hash _meta]"],
   :type "type",
   :full-name "cljs.core/Symbol",
   :examples-strings [],
   :examples-htmls []},
  "binding"
  {:description
   "binding => var-symbol init-expr\n\nCreates new bindings for the (already-existing) vars, with the\nsupplied initial values, executes the exprs in an implicit `do`, then\nre-establishes the bindings that existed before.\n\nThe new bindings are made in parallel (unlike `let`); all init-exprs are\nevaluated before the vars are bound to their new values.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def &#94;:dynamic &#42;foo&#42; 1&#41;\n\n&#40;defn do-something &#91;&#93;\n  &#40;println &#42;foo&#42;&#41;&#41;\n\n&#40;binding &#91;&#42;foo&#42; 2&#93;\n  &#40;do-something&#41;&#41;\n;;=&gt; prints 2\n\n&#42;foo&#42;\n;;=&gt; 1\n</code></pre>"],
   :ns "cljs.core",
   :name "binding",
   :signature ["[bindings & body]"],
   :type "macro",
   :related ["cljs.core/let"],
   :examples-strings
   [["(def ^:dynamic *foo* 1)"
     "(defn do-something []\n  (println *foo*))"
     "(binding [*foo* 2]\n  (do-something))"
     "*foo*"]],
   :examples
   [{:id "7dd17f",
     :content
     "```clj\n(def ^:dynamic *foo* 1)\n\n(defn do-something []\n  (println *foo*))\n\n(binding [*foo* 2]\n  (do-something))\n;;=> prints 2\n\n*foo*\n;;=> 1\n```"}],
   :full-name "cljs.core/binding",
   :docstring
   "binding => var-symbol init-expr\n\nCreates new bindings for the (already-existing) vars, with the\nsupplied initial values, executes the exprs in an implicit do, then\nre-establishes the bindings that existed before.  The new bindings\nare made in parallel (unlike let); all init-exprs are evaluated\nbefore the vars are bound to their new values."},
  "int"
  {:description
   "Coerces `x` to an integer by stripping decimal places.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "int",
   :signature ["[x]"],
   :type "function/macro",
   :related ["cljs.core/char" "cljs.core/integer?"],
   :examples-strings [],
   :full-name "cljs.core/int",
   :docstring "Coerce to int by stripping decimal places."},
  "seq"
  {:description
   "Returns a sequence on the collection. If the collection is empty, returns nil.\n\n`(seq nil)` returns nil.\n\n`seq` also works on strings.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "seq",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/seq?" "cljs.core/empty?"],
   :examples-strings [],
   :full-name "cljs.core/seq",
   :docstring
   "Returns a seq on the collection. If the collection is\nempty, returns nil.  (seq nil) returns nil. seq also works on\nStrings."},
  "amap"
  {:description
   "For quickly creating a new JavaScript array by mapping an expression `expr`\nacross a JavaScript array `a`.  The expression can use `ret` as the current\nresult, which is initialized to `a`.  It can also use `idx` to get the current\nindex.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a #js &#91;1 2 3&#93;&#41;\n&#40;amap a i ret &#40;&#42; 10 &#40;aget a i&#41;&#41;&#41;\n;;=&gt; #js &#91;10 20 30&#93;\n</code></pre>"
    "<p>You can also use <code>ret</code> inside the mapped expression if you want to use the current result:</p><pre><code class=\"clj\">&#40;def a #js &#91;1 2 3&#93;&#41;\n&#40;amap a i ret &#40;+ &#40;if &#40;pos? i&#41;\n                   &#40;aget ret &#40;dec i&#41;&#41;\n                   0&#41;\n                 &#40;&#42; 10 &#40;aget a i&#41;&#41;&#41;&#41;\n;;=&gt; #js &#91;10 30 60&#93;\n</code></pre>"],
   :ns "cljs.core",
   :name "amap",
   :signature ["[a idx ret expr]"],
   :type "macro",
   :related ["cljs.core/map"],
   :examples-strings
   [["(def a #js [1 2 3])" "(amap a i ret (* 10 (aget a i)))"]
    ["(def a #js [1 2 3])"
     "(amap a i ret (+ (if (pos? i)\n                   (aget ret (dec i))\n                   0)\n                 (* 10 (aget a i))))"]],
   :examples
   [{:id "3a7471",
     :content
     "```clj\n(def a #js [1 2 3])\n(amap a i ret (* 10 (aget a i)))\n;;=> #js [10 20 30]\n```"}
    {:id "0f57af",
     :content
     "You can also use `ret` inside the mapped expression if you want to use the\ncurrent result:\n\n```clj\n(def a #js [1 2 3])\n(amap a i ret (+ (if (pos? i)\n                   (aget ret (dec i))\n                   0)\n                 (* 10 (aget a i))))\n;;=> #js [10 30 60]\n```"}],
   :full-name "cljs.core/amap",
   :docstring
   "Maps an expression across an array a, using an index named idx, and\nreturn value named ret, initialized to a clone of a, then setting\neach element of ret to the evaluation of expr, returning the new\narray ret."},
  "IChunkedNext"
  {:ns "cljs.core",
   :name "IChunkedNext",
   :type "protocol",
   :full-name "cljs.core/IChunkedNext",
   :docstring "Protocol for accessing the chunks of a collection.",
   :examples-strings [],
   :examples-htmls []},
  "ancestors"
  {:ns "cljs.core",
   :name "ancestors",
   :signature ["[tag]" "[h tag]"],
   :type "function",
   :related
   ["cljs.core/descendants"
    "cljs.core/isa?"
    "cljs.core/make-hierarchy"
    "cljs.core/derive"],
   :full-name "cljs.core/ancestors",
   :docstring
   "Returns the immediate and indirect parents of tag, either via a JavaScript type\ninheritance relationship or a relationship established via derive. h\nmust be a hierarchy obtained from make-hierarchy, if not supplied\ndefaults to the global hierarchy",
   :examples-strings [],
   :examples-htmls []},
  "object-array"
  {:ns "cljs.core",
   :name "object-array",
   :signature ["[size-or-seq]" "[size init-val-or-seq]"],
   :type "function",
   :full-name "cljs.core/object-array",
   :docstring
   "Creates an array of objects. Does not coerce array, provided for compatibility\nwith Clojure.",
   :examples-strings [],
   :examples-htmls []},
  "shorts"
  {:ns "cljs.core",
   :name "shorts",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/shorts",
   :examples-strings [],
   :examples-htmls []},
  "cond->"
  {:description
   "Takes an expression and a set of test/form pairs. Threads `expr` (via `->`)\nthrough each form for which the corresponding test expression is true.\n\nNote that, unlike `cond` branching, `cond->` threading does not short circuit\nafter the first true test expression.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a 12&#41;\n&#40;cond-&gt; a\n  &#40;&gt; a 10&#41; &#40;str &quot; is greater than 10&quot;&#41;\n  &#40;&lt; a 20&#41; &#40;str &quot; and less than 20&quot;&#41;&#41;\n;;=&gt; &quot;12 is greater than 10 and less than 20&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "cond->",
   :signature ["[expr & clauses]"],
   :type "macro",
   :related
   ["cljs.core/->"
    "cljs.core/->>"
    "cljs.core/cond->>"
    "cljs.core/cond"],
   :examples-strings
   [["(def a 12)"
     "(cond-> a\n  (> a 10) (str \" is greater than 10\")\n  (< a 20) (str \" and less than 20\"))"]],
   :examples
   [{:id "f08338",
     :content
     "```clj\n(def a 12)\n(cond-> a\n  (> a 10) (str \" is greater than 10\")\n  (< a 20) (str \" and less than 20\"))\n;;=> \"12 is greater than 10 and less than 20\"\n```"}],
   :full-name "cljs.core/cond->",
   :docstring
   "Takes an expression and a set of test/form pairs. Threads expr (via ->)\nthrough each form for which the corresponding test\nexpression is true. Note that, unlike cond branching, cond-> threading does\nnot short circuit after the first true test expression."},
  "IFn"
  {:ns "cljs.core",
   :name "IFn",
   :type "protocol",
   :full-name "cljs.core/IFn",
   :docstring
   "Protocol for adding the ability to invoke an object as a function.\n  For example, a vector can also be used to look up a value:\n  ([1 2 3 4] 1) => 2",
   :examples-strings [],
   :examples-htmls []},
  "range"
  {:description
   "Returns a lazy sequence of nums from `start` (inclusive) to `end` (exclusive),\nby `step`, where `start` defaults to 0, `step` to 1, and `end` to infinity.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "range",
   :signature ["[]" "[end]" "[start end]" "[start end step]"],
   :type "function",
   :related ["cljs.core/repeat"],
   :examples-strings [],
   :full-name "cljs.core/range",
   :docstring
   "Returns a lazy seq of nums from start (inclusive) to end\n(exclusive), by step, where start defaults to 0, step to 1,\nand end to infinity."},
  "unchecked-short"
  {:ns "cljs.core",
   :name "unchecked-short",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-short",
   :examples-strings [],
   :examples-htmls []},
  "js-debugger"
  {:description
   "Creates breakpoint that will stop the debugger if the browser's devtools are\nopen.  Equivalent to `debugger;` in JavaScript.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;defn foo &#91;&#93;\n  &#40;println &quot;HI&quot;&#41;\n  &#40;js-debugger&#41;\n  &#40;println &quot;WORLD&quot;&#41;&#41;\n\n&#40;foo&#41;\n;; will print &quot;HI&quot; then pause JS inside this function\n;; if browser devtools are open.\n</code></pre>"],
   :ns "cljs.core",
   :name "js-debugger",
   :signature ["[]"],
   :type "macro",
   :examples-strings
   [["(defn foo []\n  (println \"HI\")\n  (js-debugger)\n  (println \"WORLD\"))"
     "(foo)"]],
   :examples
   [{:id "87f2fa",
     :content
     "```clj\n(defn foo []\n  (println \"HI\")\n  (js-debugger)\n  (println \"WORLD\"))\n\n(foo)\n;; will print \"HI\" then pause JS inside this function\n;; if browser devtools are open.\n```"}],
   :full-name "cljs.core/js-debugger",
   :docstring "Emit JavaScript \"debugger;\" statement."},
  "es6-iterable"
  {:ns "cljs.core",
   :name "es6-iterable",
   :signature ["[ty]"],
   :type "macro",
   :full-name "cljs.core/es6-iterable",
   :examples-strings [],
   :examples-htmls []},
  "not-every?"
  {:description
   "Returns false if `(pred x)` is logical true for every `x` in `coll`, else true.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "not-every?",
   :signature ["[pred coll]"],
   :type "function",
   :related ["cljs.core/every?" "cljs.core/not-any?" "cljs.core/some"],
   :examples-strings [],
   :full-name "cljs.core/not-every?",
   :docstring
   "Returns false if (pred x) is logical true for every x in\ncoll, else true."},
  "drop-while"
  {:description
   "Returns a lazy sequence of the items in `coll` starting from the first item for\nwhich `(pred item)` returns logical false.\n\nReturns a stateful transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "drop-while",
   :signature ["[pred]" "[pred coll]"],
   :type "function",
   :related ["cljs.core/take-while" "cljs.core/split-with"],
   :examples-strings [],
   :full-name "cljs.core/drop-while",
   :docstring
   "Returns a lazy sequence of the items in coll starting from the\nfirst item for which (pred item) returns logical false.  Returns a\nstateful transducer when no collection is provided."},
  "peek"
  {:description
   "Returns the first element of a list; same as `first`.\n\nReturns the last element of a vector, and much more efficient than using `last`.\n\nReturns nil if `coll` is empty.",
   :examples-htmls
   ["<p>With vectors:</p><pre><code class=\"clj\">&#40;peek &#91;1 2 3&#93;&#41;\n;;=&gt; 3\n\n&#40;peek &#91;1 2&#93;&#41;\n;;=&gt; 2\n\n&#40;peek &#91;1&#93;&#41;\n;;=&gt; 1\n\n&#40;peek &#91;&#93;&#41;\n;;=&gt; nil\n</code></pre>"
    "<p>With lists:</p><pre><code class=\"clj\">&#40;peek '&#40;1 2 3&#41;&#41;\n;;=&gt; 1\n\n&#40;peek '&#40;1 2&#41;&#41;\n;;=&gt; 1\n\n&#40;peek '&#40;1&#41;&#41;\n;;=&gt; 1\n\n&#40;peek '&#40;&#41;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "peek",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/first" "cljs.core/pop" "cljs.core/conj"],
   :examples-strings
   [["(peek [1 2 3])" "(peek [1 2])" "(peek [1])" "(peek [])"]
    ["(peek '(1 2 3))" "(peek '(1 2))" "(peek '(1))" "(peek '())"]],
   :examples
   [{:id "4abc4c",
     :content
     "With vectors:\n\n```clj\n(peek [1 2 3])\n;;=> 3\n\n(peek [1 2])\n;;=> 2\n\n(peek [1])\n;;=> 1\n\n(peek [])\n;;=> nil\n```"}
    {:id "d50bd0",
     :content
     "With lists:\n\n```clj\n(peek '(1 2 3))\n;;=> 1\n\n(peek '(1 2))\n;;=> 1\n\n(peek '(1))\n;;=> 1\n\n(peek '())\n;;=> nil\n```"}],
   :full-name "cljs.core/peek",
   :docstring
   "For a list or queue, same as first, for a vector, same as, but much\nmore efficient than, last. If the collection is empty, returns nil."},
  "subs"
  {:description
   "Returns the substring of `s` beginning at `start` inclusive, and ending at `end`\nexclusive.\n\n`end` defaults to the length of the string.",
   :ns "cljs.core",
   :name "subs",
   :signature ["[s start]" "[s start end]"],
   :type "function",
   :full-name "cljs.core/subs",
   :docstring
   "Returns the substring of s beginning at start inclusive, and ending\nat end (defaults to length of string), exclusive.",
   :examples-strings [],
   :examples-htmls []},
  "take-nth"
  {:description
   "Returns a lazy seq of every `n`th item in `coll`.\n\nReturns a stateful transducer when no collection is provided.",
   :ns "cljs.core",
   :name "take-nth",
   :signature ["[n]" "[n coll]"],
   :type "function",
   :full-name "cljs.core/take-nth",
   :docstring
   "Returns a lazy seq of every nth item in coll.  Returns a stateful\ntransducer when no collection is provided.",
   :examples-strings [],
   :examples-htmls []},
  "enable-console-print!"
  {:ns "cljs.core",
   :name "enable-console-print!",
   :signature ["[]"],
   :type "function",
   :full-name "cljs.core/enable-console-print!",
   :docstring "Set *print-fn* to console.log",
   :examples-strings [],
   :examples-htmls []},
  "ns-name"
  {:ns "cljs.core",
   :name "ns-name",
   :signature ["[ns-obj]"],
   :type "function",
   :full-name "cljs.core/ns-name",
   :examples-strings [],
   :examples-htmls []},
  "*e"
  {:description
   "Only usable from a REPL.\n\nHolds the result of the last exception.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;defn cause-error &#91;&#93;\n  &#40;throw &quot;Error: something went wrong&quot;&#41;&#41;\n\n&#40;cause-error&#41;\n;; Error: something went wrong\n\n&#42;e\n;;=&gt; &quot;Error: something went wrong&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "*e",
   :type "var",
   :related ["cljs.core/*1" "cljs.core/*2" "cljs.core/*3"],
   :examples-strings
   [["(defn cause-error []\n  (throw \"Error: something went wrong\"))"
     "(cause-error)"
     "*e"]],
   :examples
   [{:id "bea858",
     :content
     "```clj\n(defn cause-error []\n  (throw \"Error: something went wrong\"))\n\n(cause-error)\n;; Error: something went wrong\n\n*e\n;;=> \"Error: something went wrong\"\n```"}],
   :full-name "cljs.core/*e",
   :docstring
   "bound in a repl thread to the most recent exception caught by the repl"},
  "prn-str"
  {:ns "cljs.core",
   :name "prn-str",
   :signature ["[& objs]"],
   :type "function",
   :full-name "cljs.core/prn-str",
   :docstring "Same as pr-str followed by (newline)",
   :examples-strings [],
   :examples-htmls []},
  "nthrest"
  {:description
   "Returns the `nth` rest of `coll`.\n\nReturns `coll` when `n` is 0.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "nthrest",
   :signature ["[coll n]"],
   :type "function",
   :related ["cljs.core/drop" "cljs.core/nthnext" "cljs.core/nth"],
   :examples-strings [],
   :full-name "cljs.core/nthrest",
   :docstring "Returns the nth rest of coll, coll when n is 0."},
  "VectorNode"
  {:ns "cljs.core",
   :name "VectorNode",
   :signature ["[edit arr]"],
   :type "type",
   :full-name "cljs.core/VectorNode",
   :examples-strings [],
   :examples-htmls []},
  "satisfies?"
  {:ns "cljs.core",
   :name "satisfies?",
   :signature ["[psym x]"],
   :type "macro",
   :full-name "cljs.core/satisfies?",
   :docstring "Returns true if x satisfies the protocol",
   :examples-strings [],
   :examples-htmls []},
  "ISeqable"
  {:ns "cljs.core",
   :name "ISeqable",
   :type "protocol",
   :full-name "cljs.core/ISeqable",
   :docstring
   "Protocol for adding the ability to a type to be transformed into a sequence.",
   :examples-strings [],
   :examples-htmls []},
  "vec"
  {:description
   "Creates a new vector containing the contents of `coll`",
   :examples-htmls [],
   :ns "cljs.core",
   :name "vec",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/vector" "cljs.core/vector?"],
   :examples-strings [],
   :full-name "cljs.core/vec",
   :docstring
   "Creates a new vector containing the contents of coll. JavaScript arrays\nwill be aliased and should not be modified."},
  "as->"
  {:description
   "Binds `name` to `expr`, evaluates the first form in the lexical context of that\nbinding, then binds `name` to that result, repeating for each successive form,\nreturning the result of the last form.\n\nUseful for when you want a threading macro to use different \"places\" at each\nform.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;as-&gt; &#91;1 2 3 4&#93; x\n  &#40;reduce + x&#41;\n  &#40;/ x 2&#41;&#41;\n;;=&gt; 5\n</code></pre>"],
   :ns "cljs.core",
   :name "as->",
   :signature ["[expr name & forms]"],
   :type "macro",
   :related
   ["cljs.core/->"
    "cljs.core/->>"
    "cljs.core/cond->"
    "cljs.core/cond->>"
    "cljs.core/some->"
    "cljs.core/some->>"],
   :examples-strings
   [["(as-> [1 2 3 4] x\n  (reduce + x)\n  (/ x 2))"]],
   :examples
   [{:id "5e7eef",
     :content
     "```clj\n(as-> [1 2 3 4] x\n  (reduce + x)\n  (/ x 2))\n;;=> 5\n```"}],
   :full-name "cljs.core/as->",
   :docstring
   "Binds name to expr, evaluates the first form in the lexical context\nof that binding, then binds name to that result, repeating for each\nsuccessive form, returning the result of the last form."},
  "m3-fmix"
  {:ns "cljs.core",
   :name "m3-fmix",
   :signature ["[h1 len]"],
   :type "function",
   :full-name "cljs.core/m3-fmix",
   :examples-strings [],
   :examples-htmls []},
  "integer?"
  {:description "Returns true if `n` is an integer, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "integer?",
   :signature ["[n]"],
   :type "function",
   :related ["cljs.core/int"],
   :examples-strings [],
   :full-name "cljs.core/integer?",
   :docstring "Returns true if n is an integer."},
  "js->clj"
  {:description
   "Recursively transforms JavaScript arrays into ClojureScript vectors, and\nJavaScript objects into ClojureScript maps.\n\nWith option `{:keywordize-keys true}` will convert object fields from strings to\nkeywords.\n\nNote that `js->clj` is not optimized for speed and the [transit.cljs] library is\nrecommended for parsing large amounts of JSON data.\n\n[transit.cljs]:http://swannodette.github.io/2014/07/26/transit--clojurescript/",
   :examples-htmls
   ["<p>Parse a JSON string:</p><pre><code class=\"clj\">&#40;def json &quot;{\\&quot;foo\\&quot;: 1, \\&quot;bar\\&quot;: 2, \\&quot;baz\\&quot;: &#91;1,2,3&#93;}&quot;&#41;\n&#40;def a &#40;.parse js/JSON json&#41;&#41;\n;;=&gt; #js {:foo 1, :bar 2, :baz #js &#91;1 2 3&#93;}\n</code></pre><p>Convert JSON data <code>a</code> to ClojureScript data:</p><pre><code class=\"clj\">&#40;js-&gt;clj a&#41;\n;;=&gt; {&quot;foo&quot; 1, &quot;bar&quot; 2, &quot;baz&quot; &#91;1 2 3&#93;}\n\n&#40;js-&gt;clj a :keywordize-keys true&#41;\n;;=&gt; {:foo 1, :bar 2, :baz &#91;1 2 3&#93;}\n</code></pre>"],
   :ns "cljs.core",
   :name "js->clj",
   :signature ["[x]" "[x & opts]"],
   :type "function",
   :related ["cljs.core/clj->js"],
   :examples-strings
   [["(def json \"{\\\"foo\\\": 1, \\\"bar\\\": 2, \\\"baz\\\": [1,2,3]}\")"
     "(def a (.parse js/JSON json))"
     "(js->clj a)"
     "(js->clj a :keywordize-keys true)"]],
   :examples
   [{:id "61d263",
     :content
     "Parse a JSON string:\n\n```clj\n(def json \"{\\\"foo\\\": 1, \\\"bar\\\": 2, \\\"baz\\\": [1,2,3]}\")\n(def a (.parse js/JSON json))\n;;=> #js {:foo 1, :bar 2, :baz #js [1 2 3]}\n```\n\nConvert JSON data `a` to ClojureScript data:\n\n```clj\n(js->clj a)\n;;=> {\"foo\" 1, \"bar\" 2, \"baz\" [1 2 3]}\n\n(js->clj a :keywordize-keys true)\n;;=> {:foo 1, :bar 2, :baz [1 2 3]}\n```"}],
   :full-name "cljs.core/js->clj",
   :docstring
   "Recursively transforms JavaScript arrays into ClojureScript\nvectors, and JavaScript objects into ClojureScript maps.  With\noption ':keywordize-keys true' will convert object fields from\nstrings to keywords."},
  "*print-dup*"
  {:ns "cljs.core",
   :name "*print-dup*",
   :type "dynamic var",
   :full-name "cljs.core/*print-dup*",
   :docstring
   "When set to logical true, objects will be printed in a way that preserves\ntheir type when read in later.\n\nDefaults to false.",
   :examples-strings [],
   :examples-htmls []},
  "loop"
  {:description
   "Evaluates the `body-exprs` in a lexical context in which the symbols in\nthe binding-forms are bound to their respective init-exprs, just like a `let` form.\nActs as a `recur` target, which will allow tail-call optimization.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;loop &#91;x 0&#93;\n  &#40;when &#40;&lt; x 10&#41;\n    &#40;println x&#41;\n    &#40;recur &#40;+ x 2&#41;&#41;&#41;&#41;\n;; Prints:\n;; 0\n;; 2\n;; 4\n;; 6\n;; 8\n;;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "loop",
   :signature ["[[& bindings] & body-exprs]"],
   :type "macro",
   :related ["special/recur"],
   :examples-strings
   [["(loop [x 0]\n  (when (< x 10)\n    (println x)\n    (recur (+ x 2))))"]],
   :examples
   [{:id "60291e",
     :content
     "```clj\n(loop [x 0]\n  (when (< x 10)\n    (println x)\n    (recur (+ x 2))))\n;; Prints:\n;; 0\n;; 2\n;; 4\n;; 6\n;; 8\n;;\n;;=> nil\n```"}],
   :full-name "cljs.core/loop",
   :docstring
   "Evaluates the exprs in a lexical context in which the symbols in\nthe binding-forms are bound to their respective init-exprs or parts\ntherein. Acts as a recur target."},
  "IWatchable"
  {:ns "cljs.core",
   :name "IWatchable",
   :type "protocol",
   :full-name "cljs.core/IWatchable",
   :docstring
   "Protocol for types that can be watched. Currently only implemented by Atom.",
   :examples-strings [],
   :examples-htmls []},
  "min"
  {:description "Returns the least number argument.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;min 1 2 3 4&#41;\n;; =&gt; 1\n</code></pre><p>Apply it to a collection:</p><pre><code class=\"clj\">&#40;apply min &#91;1 2 3 4&#93;&#41;\n;; =&gt; 1\n</code></pre>"],
   :ns "cljs.core",
   :name "min",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/max" "cljs.core/min-key"],
   :examples-strings [["(min 1 2 3 4)" "(apply min [1 2 3 4])"]],
   :examples
   [{:id "ab2de5",
     :content
     "```clj\n(min 1 2 3 4)\n;; => 1\n```\n\nApply it to a collection:\n\n```clj\n(apply min [1 2 3 4])\n;; => 1\n```"}],
   :full-name "cljs.core/min",
   :docstring "Returns the least of the nums."},
  "IMap"
  {:ns "cljs.core",
   :name "IMap",
   :type "protocol",
   :full-name "cljs.core/IMap",
   :docstring
   "Protocol for adding mapping functionality to collections.",
   :examples-strings [],
   :examples-htmls []},
  "bit-flip"
  {:description
   "Flip bit at index `n`.  Same as `x ^ (1 << y)` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-flip 2r1111 2&#41;\n;;=&gt; 11\n;; 11 = 2r1011\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-flip 15 2&#41;\n;;=&gt; 11\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-flip",
   :signature ["[x n]"],
   :type "function/macro",
   :related ["cljs.core/bit-set" "cljs.core/bit-clear"],
   :examples-strings [["(bit-flip 2r1111 2)" "(bit-flip 15 2)"]],
   :examples
   [{:id "5d7ee0",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-flip 2r1111 2)\n;;=> 11\n;; 11 = 2r1011\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-flip 15 2)\n;;=> 11\n```"}],
   :full-name "cljs.core/bit-flip",
   :docstring "Flip bit at index n"},
  "defn"
  {:description
   "Defines a function.\n\n`doc-string?` is an optional documentation string.\n\n`attr-map?` is an optional map of [metadata](http://clojure.org/metadata) to\nattach to the global variable name.\n\n`prepost-map?` is an optional map with optional keys `:pre` and `:post` that\ncontain collections of [pre or post conditions](http://blog.fogus.me/2009/12/21/clojures-pre-and-post/)\nfor the function.\n\n<table class=\"code-tbl-9bef6\">\n  <thead>\n    <tr>\n      <th>Code</th>\n      <th>Expands To</th></tr></thead>\n  <tbody>\n    <tr>\n      <td><pre>\n(defn foo [a b c]\n  (\\* a b c))</pre></td>\n      <td><pre>\n(def foo\n  (fn [a b c]\n    (\\* a b c)))</pre></td></tr></tbody></table>",
   :examples-htmls [],
   :ns "cljs.core",
   :name "defn",
   :signature
   ["[name doc-string? attr-map? [params*] prepost-map? body]"
    "[name doc-string? attr-map? ([params*] prepost-map? body) + attr-map?]"],
   :type "macro",
   :related
   ["special/def"
    "cljs.core/defn-"
    "cljs.core/defmacro"
    "cljs.core/fn"],
   :examples-strings [],
   :full-name "cljs.core/defn",
   :docstring
   "Same as (def name (core/fn [params* ] exprs*)) or (def\n name (core/fn ([params* ] exprs*)+)) with any doc-string or attrs added\n to the var metadata. prepost-map defines a map with optional keys\n :pre and :post that contain collections of pre or post conditions."},
  "isa?"
  {:ns "cljs.core",
   :name "isa?",
   :signature ["[child parent]" "[h child parent]"],
   :type "function",
   :related
   ["cljs.core/ancestors"
    "cljs.core/descendants"
    "cljs.core/make-hierarchy"
    "cljs.core/derive"],
   :full-name "cljs.core/isa?",
   :docstring
   "Returns true if (= child parent), or child is directly or indirectly derived from\nparent, either via a JavaScript type inheritance relationship or a\nrelationship established via derive. h must be a hierarchy obtained\nfrom make-hierarchy, if not supplied defaults to the global\nhierarchy",
   :examples-strings [],
   :examples-htmls []},
  "*flush-on-newline*"
  {:ns "cljs.core",
   :name "*flush-on-newline*",
   :type "dynamic var",
   :full-name "cljs.core/*flush-on-newline*",
   :docstring
   "When set to true, output will be flushed whenever a newline is printed.\n\nDefaults to true.",
   :examples-strings [],
   :examples-htmls []},
  "printf"
  {:ns "cljs.core",
   :name "printf",
   :signature ["[fmt & args]"],
   :type "function",
   :full-name "cljs.core/printf",
   :docstring "Prints formatted output, as per format",
   :examples-strings [],
   :examples-htmls []},
  "max-key"
  {:description
   "Returns the `x` for which `(k x)` is greatest.\n\n`(k x)` should return a number.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "max-key",
   :signature ["[k x]" "[k x y]" "[k x y & more]"],
   :type "function",
   :related ["cljs.core/max" "cljs.core/min-key"],
   :examples-strings [],
   :full-name "cljs.core/max-key",
   :docstring "Returns the x for which (k x), a number, is greatest."},
  "HashMap.fromArrays"
  {:ns "cljs.core",
   :name "HashMap.fromArrays",
   :signature ["[ks vs]"],
   :type "function",
   :full-name "cljs.core/HashMap.fromArrays",
   :examples-strings [],
   :examples-htmls []},
  "es6-iterator"
  {:ns "cljs.core",
   :name "es6-iterator",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/es6-iterator",
   :docstring
   "EXPERIMENTAL: Return a ES2015 compatible iterator for coll.",
   :examples-strings [],
   :examples-htmls []},
  "="
  {:description
   "Returns true if the value of `x` equals the value of `y`, false otherwise.\n\n`=` is a value comparison, not an identity comparison.\n\nAll collections can be tested for value, regardless of \"depth\".",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;= 1&#41;\n;;=&gt; true\n\n&#40;= 1 1&#41;\n;;=&gt; true\n\n&#40;= 1 2&#41;\n;;=&gt; false\n\n&#40;= 1 1 1&#41;\n;;=&gt; true\n\n&#40;= 1 1 2&#41;\n;;=&gt; false\n</code></pre>"
    "<p>Sequences are considered equal in value if they have the same elements:</p><pre><code class=\"clj\">&#40;= '&#40;1 2&#41; &#91;1 2&#93;&#41;\n;;=&gt; true\n</code></pre><p>But you cannot compare JavaScript arrays until you convert them to sequences:</p><pre><code class=\"clj\">&#40;def a #js &#91;1 2&#93;&#41;\n&#40;def b #js &#91;1 2&#93;&#41;\n&#40;= a b&#41;\n;;=&gt; false\n\n&#40;= &#40;seq a&#41; &#40;seq b&#41;&#41;\n;;=&gt; true\n</code></pre>"
    "<p>It is natural to compare deeply nested collections since value equality checks are cheap in ClojureScript:</p><pre><code class=\"clj\">&#40;def a {:foo {:bar &quot;baz&quot;}}&#41;\n&#40;def b {:foo {:bar &quot;baz&quot;}}&#41;\n&#40;= a b&#41;\n;;=&gt; true\n\n&#40;= &#91;a b&#93; &#91;a b&#93;&#41;\n;=&gt; true\n</code></pre><p>JavaScript objects cannot be compared in this way until they are converted to ClojureScript collections:</p><pre><code class=\"clj\">&#40;def a #js {:foo #js {:bar &quot;baz&quot;}}&#41;\n&#40;def b #js {:foo #js {:bar &quot;baz&quot;}}&#41;\n&#40;= a b&#41;\n;;=&gt; false\n\n&#40;= &#40;js-&gt;clj a&#41;\n   &#40;js-&gt;clj b&#41;&#41;\n;;=&gt; true\n</code></pre>"],
   :ns "cljs.core",
   :name "=",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function",
   :related ["cljs.core/==" "cljs.core/not=" "cljs.core/identical?"],
   :examples-strings
   [["(= 1)" "(= 1 1)" "(= 1 2)" "(= 1 1 1)" "(= 1 1 2)"]
    ["(= '(1 2) [1 2])"
     "(def a #js [1 2])"
     "(def b #js [1 2])"
     "(= a b)"
     "(= (seq a) (seq b))"]
    ["(def a {:foo {:bar \"baz\"}})"
     "(def b {:foo {:bar \"baz\"}})"
     "(= a b)"
     "(= [a b] [a b])"
     "(def a #js {:foo #js {:bar \"baz\"}})"
     "(def b #js {:foo #js {:bar \"baz\"}})"
     "(= a b)"
     "(= (js->clj a)\n   (js->clj b))"]],
   :examples
   [{:id "edffb6",
     :content
     "```clj\n(= 1)\n;;=> true\n\n(= 1 1)\n;;=> true\n\n(= 1 2)\n;;=> false\n\n(= 1 1 1)\n;;=> true\n\n(= 1 1 2)\n;;=> false\n```"}
    {:id "a2d064",
     :content
     "Sequences are considered equal in value if they have the same elements:\n\n```clj\n(= '(1 2) [1 2])\n;;=> true\n```\n\nBut you cannot compare JavaScript arrays until you convert them to sequences:\n\n```clj\n(def a #js [1 2])\n(def b #js [1 2])\n(= a b)\n;;=> false\n\n(= (seq a) (seq b))\n;;=> true\n```"}
    {:id "6c8424",
     :content
     "It is natural to compare deeply nested collections since value equality checks\nare cheap in ClojureScript:\n\n```clj\n(def a {:foo {:bar \"baz\"}})\n(def b {:foo {:bar \"baz\"}})\n(= a b)\n;;=> true\n\n(= [a b] [a b])\n;=> true\n```\n\nJavaScript objects cannot be compared in this way until they are converted to\nClojureScript collections:\n\n```clj\n(def a #js {:foo #js {:bar \"baz\"}})\n(def b #js {:foo #js {:bar \"baz\"}})\n(= a b)\n;;=> false\n\n(= (js->clj a)\n   (js->clj b))\n;;=> true\n```"}],
   :full-name "cljs.core/=",
   :docstring
   "Equality. Returns true if x equals y, false if not. Compares\nnumbers and collections in a type-independent manner.  Clojure's immutable data\nstructures define -equiv (and thus =) as a value, not an identity,\ncomparison."},
  "list"
  {:description "Creates a new list containing `items`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "list",
   :signature ["[& items]"],
   :type "function/macro",
   :related ["cljs.core/vector" "cljs.core/list?"],
   :examples-strings [],
   :full-name "cljs.core/list",
   :docstring "Creates a new list containing the items."},
  "IMeta"
  {:ns "cljs.core",
   :name "IMeta",
   :type "protocol",
   :full-name "cljs.core/IMeta",
   :docstring "Protocol for accessing the metadata of an object.",
   :examples-strings [],
   :examples-htmls []},
  "constantly"
  {:description
   "Returns a function that takes any number of arguments and always returns `x`.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def ten &#40;constantly 10&#41;&#41;\n\n&#40;ten &quot;hi&quot;&#41;\n;;=&gt; 10\n\n&#40;ten 123&#41;\n;;=&gt; 10\n\n&#40;ten :whatever&#41;\n;;=&gt; 10\n</code></pre>"],
   :ns "cljs.core",
   :name "constantly",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/repeatedly"],
   :examples-strings
   [["(def ten (constantly 10))"
     "(ten \"hi\")"
     "(ten 123)"
     "(ten :whatever)"]],
   :examples
   [{:id "9d5c25",
     :content
     "```clj\n(def ten (constantly 10))\n\n(ten \"hi\")\n;;=> 10\n\n(ten 123)\n;;=> 10\n\n(ten :whatever)\n;;=> 10\n```"}],
   :full-name "cljs.core/constantly",
   :docstring
   "Returns a function that takes any number of arguments and returns x."},
  "into-array"
  {:description
   "Returns a new JavaScript array from the elements of `aseq`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "into-array",
   :signature ["[aseq]"],
   :type "function",
   :related ["cljs.core/to-array" "cljs.core/make-array"],
   :examples-strings [],
   :full-name "cljs.core/into-array",
   :docstring
   "Returns an array with components set to the values in aseq. Optional type\nargument accepted for compatibility with Clojure."},
  "*print-readably*"
  {:ns "cljs.core",
   :name "*print-readably*",
   :type "dynamic var",
   :full-name "cljs.core/*print-readably*",
   :docstring
   "When set to logical false, strings and characters will be printed with\nnon-alphanumeric characters converted to the appropriate escape sequences.\n\nDefaults to true",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-double"
  {:ns "cljs.core",
   :name "unchecked-double",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-double",
   :examples-strings [],
   :examples-htmls []},
  "IWithMeta"
  {:ns "cljs.core",
   :name "IWithMeta",
   :type "protocol",
   :full-name "cljs.core/IWithMeta",
   :docstring "Protocol for adding metadata to an object.",
   :examples-strings [],
   :examples-htmls []},
  "pr-str-with-opts"
  {:ns "cljs.core",
   :name "pr-str-with-opts",
   :signature ["[objs opts]"],
   :type "function",
   :full-name "cljs.core/pr-str-with-opts",
   :docstring
   "Prints a sequence of objects to a string, observing all the\noptions given in opts",
   :examples-strings [],
   :examples-htmls []},
  "fnil"
  {:description
   "Takes a function `f`, and returns a function that calls `f`, replacing a nil\nfirst argument to `f` with the supplied value `x`. Higher arity versions can\nreplace arguments in the second and third positions (`y`, `z`).\n\nNote that the function `f` can take any number of arguments, not just the one(s)\nbeing nil-patched.",
   :ns "cljs.core",
   :name "fnil",
   :signature ["[f x]" "[f x y]" "[f x y z]"],
   :type "function",
   :full-name "cljs.core/fnil",
   :docstring
   "Takes a function f, and returns a function that calls f, replacing\na nil first argument to f with the supplied value x. Higher arity\nversions can replace arguments in the second and third\npositions (y, z). Note that the function f can take any number of\narguments, not just the one(s) being nil-patched.",
   :examples-strings [],
   :examples-htmls []},
  "random-sample"
  {:ns "cljs.core",
   :name "random-sample",
   :signature ["[prob]" "[prob coll]"],
   :type "function",
   :full-name "cljs.core/random-sample",
   :docstring
   "Returns items from coll with random probability of prob (0.0 -\n1.0).  Returns a transducer when no collection is provided.",
   :examples-strings [],
   :examples-htmls []},
  "bit-count"
  {:description "Counts the number of bits set in `x`.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-count 2r1011&#41;\n;;=&gt; 3\n</code></pre><p>Same number in decimal:</p><pre><code class=\"clj\">&#40;bit-count 11&#41;\n;;=&gt; 3\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-count",
   :signature ["[x]"],
   :type "function",
   :examples-strings [["(bit-count 2r1011)" "(bit-count 11)"]],
   :examples
   [{:id "35c78c",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-count 2r1011)\n;;=> 3\n```\n\nSame number in decimal:\n\n```clj\n(bit-count 11)\n;;=> 3\n```"}],
   :full-name "cljs.core/bit-count",
   :docstring "Counts the number of bits set in n"},
  "*"
  {:description "Returns the product of nums.\n\n`(*)` returns 1.",
   :examples-htmls
   ["<pre><code class=\"clj\">;; there is an implicit 1\n&#40;&#42;&#41;\n;;=&gt; 1\n\n;; the implicit 1 comes into play\n&#40;&#42; 6&#41;\n;;=&gt; 6\n\n&#40;&#42; 2 3&#41;\n;;=&gt; 6\n\n&#40;&#42; 2 3 4&#41;\n;;=&gt; 24\n</code></pre>"],
   :ns "cljs.core",
   :name "*",
   :signature ["[]" "[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/+" "cljs.core//"],
   :examples-strings [["(*)" "(* 6)" "(* 2 3)" "(* 2 3 4)"]],
   :examples
   [{:id "bc4a1f",
     :content
     "```clj\n;; there is an implicit 1\n(*)\n;;=> 1\n\n;; the implicit 1 comes into play\n(* 6)\n;;=> 6\n\n(* 2 3)\n;;=> 6\n\n(* 2 3 4)\n;;=> 24\n```"}],
   :full-name "cljs.core/*",
   :docstring "Returns the product of nums. (*) returns 1."},
  "next"
  {:description
   "Returns a sequence of the items after the first and calls `seq` on its argument.\n\nReturns nil if `coll` is empty.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;next &#91;1 2 3&#93;&#41;\n;;=&gt; &#40;2 3&#41;\n\n&#40;next &#91;1 2&#93;&#41;\n;;=&gt; &#40;2&#41;\n\n&#40;next &#91;1&#93;&#41;\n;;=&gt; nil\n\n&#40;next &#91;&#93;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "next",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/rest" "cljs.core/first" "cljs.core/fnext"],
   :examples-strings
   [["(next [1 2 3])" "(next [1 2])" "(next [1])" "(next [])"]],
   :examples
   [{:id "7db59a",
     :content
     "```clj\n(next [1 2 3])\n;;=> (2 3)\n\n(next [1 2])\n;;=> (2)\n\n(next [1])\n;;=> nil\n\n(next [])\n;;=> nil\n```"}],
   :full-name "cljs.core/next",
   :docstring
   "Returns a seq of the items after the first. Calls seq on its\nargument.  If there are no more items, returns nil"},
  "PersistentHashMap.fromArray"
  {:ns "cljs.core",
   :name "PersistentHashMap.fromArray",
   :signature ["[arr no-clone]"],
   :type "function",
   :full-name "cljs.core/PersistentHashMap.fromArray",
   :examples-strings [],
   :examples-htmls []},
  "bit-test"
  {:description
   "Test bit at index `n`. Returns `true` if 1, and `false` if 0. Same as `(x & (1 << y)) != 0` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-test 2r0100 2&#41;\n;;=&gt; true\n\n&#40;bit-test 2r0100 1&#41;\n;;=&gt; false\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-test 4 2&#41;\n;;=&gt; true\n\n&#40;bit-test 4 1&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-test",
   :signature ["[x n]"],
   :type "function/macro",
   :examples-strings
   [["(bit-test 2r0100 2)"
     "(bit-test 2r0100 1)"
     "(bit-test 4 2)"
     "(bit-test 4 1)"]],
   :examples
   [{:id "f64664",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-test 2r0100 2)\n;;=> true\n\n(bit-test 2r0100 1)\n;;=> false\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-test 4 2)\n;;=> true\n\n(bit-test 4 1)\n;;=> false\n```"}],
   :full-name "cljs.core/bit-test",
   :docstring "Test bit at index n"},
  "transduce"
  {:ns "cljs.core",
   :name "transduce",
   :signature ["[xform f coll]" "[xform f init coll]"],
   :type "function",
   :full-name "cljs.core/transduce",
   :docstring
   "reduce with a transformation of f (xf). If init is not\nsupplied, (f) will be called to produce it. f should be a reducing\nstep function that accepts both 1 and 2 arguments, if it accepts\nonly 2 you can add the arity-1 with 'completing'. Returns the result\nof applying (the transformed) xf to init and the first item in coll,\nthen applying xf to that result and the 2nd item, etc. If coll\ncontains no items, returns init and f is not called. Note that\ncertain transforms may inject or skip items.",
   :examples-strings [],
   :examples-htmls []},
  "keys"
  {:description "Returns a sequence of the keys in `hash-map`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "keys",
   :signature ["[hash-map]"],
   :type "function",
   :related ["cljs.core/vals"],
   :examples-strings [],
   :full-name "cljs.core/keys",
   :docstring "Returns a sequence of the map's keys."},
  "nil-iter"
  {:ns "cljs.core",
   :name "nil-iter",
   :signature ["[]"],
   :type "function",
   :full-name "cljs.core/nil-iter",
   :examples-strings [],
   :examples-htmls []},
  "IVolatile"
  {:ns "cljs.core",
   :name "IVolatile",
   :type "protocol",
   :full-name "cljs.core/IVolatile",
   :docstring "Protocol for adding volatile functionality.",
   :examples-strings [],
   :examples-htmls []},
  "MultiStepper"
  {:ns "cljs.core",
   :name "MultiStepper",
   :signature ["[xform iters nexts]"],
   :type "type",
   :full-name "cljs.core/MultiStepper",
   :examples-strings [],
   :examples-htmls []},
  "subvec"
  {:description
   "Returns a persistent vector of the items in `v` from `start` inclusive to `end`\nexclusive.\n\nIf `end` is not supplied, defaults to `(count v)`.\n\nThis operation is O(1) and very fast, as the resulting vector shares structure\nwith the original and no trimming is done.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "subvec",
   :signature ["[v start]" "[v start end]"],
   :type "function",
   :related ["cljs.core/vector" "cljs.core/vector?"],
   :examples-strings [],
   :full-name "cljs.core/subvec",
   :docstring
   "Returns a persistent vector of the items in vector from\nstart (inclusive) to end (exclusive).  If end is not supplied,\ndefaults to (count vector). This operation is O(1) and very fast, as\nthe resulting vector shares structure with the original and no\ntrimming is done."},
  "contains?"
  {:description
   "Returns true if the `coll` contains the lookup key `k`, otherwise returns false.\n\nNote that for numerically indexed collections like vectors and arrays, this\ntests if the numeric key is within the range of indexes.\n\n`contains?` operates in constant or logarithmic time, using `get` to perform\nthe lookup. It will not perform a linear search for a value.  `some` is\nused for this purpose:\n\n```clj\n(some #{value} coll)\n```",
   :examples-htmls
   ["<p>Sets and Maps provide key lookups, so <code>contains?</code> works as expected:</p><pre><code class=\"clj\">&#40;contains? #{:a :b} :a&#41;\n;;=&gt; true\n\n&#40;contains? {:a 1, :b 2} :a&#41;\n;;=&gt; true\n\n&#40;contains? {:a 1, :b 2} 1&#41;\n;;=&gt; false\n</code></pre><p>Vectors provide integer index lookups, so <code>contains?</code> works appropriately:</p><pre><code class=\"clj\">&#40;contains? &#91;:a :b&#93; :b&#41;\n;;=&gt; false\n\n&#40;contains? &#91;:a :b&#93; 1&#41;\n;;=&gt; true\n</code></pre><p>Lists and Sequences do not provide lookups, so <code>contains?</code> will not work:</p><pre><code class=\"clj\">&#40;contains? '&#40;:a :b&#41; :a&#41;\n;;=&gt; false\n\n&#40;contains? '&#40;:a :b&#41; 1&#41;\n;;=&gt; false\n\n&#40;contains? &#40;range 3&#41; 1&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "contains?",
   :signature ["[coll k]"],
   :type "function",
   :related ["cljs.core/some" "cljs.core/get"],
   :examples-strings
   [["(contains? #{:a :b} :a)"
     "(contains? {:a 1, :b 2} :a)"
     "(contains? {:a 1, :b 2} 1)"
     "(contains? [:a :b] :b)"
     "(contains? [:a :b] 1)"
     "(contains? '(:a :b) :a)"
     "(contains? '(:a :b) 1)"
     "(contains? (range 3) 1)"]],
   :examples
   [{:id "2991f0",
     :content
     "Sets and Maps provide key lookups, so `contains?` works as expected:\n\n```clj\n(contains? #{:a :b} :a)\n;;=> true\n\n(contains? {:a 1, :b 2} :a)\n;;=> true\n\n(contains? {:a 1, :b 2} 1)\n;;=> false\n```\n\nVectors provide integer index lookups, so `contains?` works appropriately:\n\n```clj\n(contains? [:a :b] :b)\n;;=> false\n\n(contains? [:a :b] 1)\n;;=> true\n```\n\nLists and Sequences do not provide lookups, so `contains?` will not work:\n\n```clj\n(contains? '(:a :b) :a)\n;;=> false\n\n(contains? '(:a :b) 1)\n;;=> false\n\n(contains? (range 3) 1)\n;;=> false\n```"}],
   :full-name "cljs.core/contains?",
   :docstring
   "Returns true if key is present in the given collection, otherwise\nreturns false.  Note that for numerically indexed collections like\nvectors and arrays, this tests if the numeric key is within the\nrange of indexes. 'contains?' operates constant or logarithmic time;\nit will not perform a linear search for a value.  See also 'some'."},
  "<="
  {:description
   "Returns true if each successive number argument is greater than or equal to the\nprevious one, false otherwise.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;&lt;= 1 2&#41;\n;;=&gt; true\n\n&#40;&lt;= 2 2&#41;\n;;=&gt; true\n\n&#40;&lt;= 3 2&#41;\n;;=&gt; false\n\n&#40;&lt;= 2 3 4 5 6&#41;\n;;=&gt; true\n</code></pre>"],
   :ns "cljs.core",
   :name "<=",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/<"],
   :examples-strings
   [["(<= 1 2)" "(<= 2 2)" "(<= 3 2)" "(<= 2 3 4 5 6)"]],
   :examples
   [{:id "adb3fd",
     :content
     "```clj\n(<= 1 2)\n;;=> true\n\n(<= 2 2)\n;;=> true\n\n(<= 3 2)\n;;=> false\n\n(<= 2 3 4 5 6)\n;;=> true\n```"}],
   :full-name "cljs.core/<=",
   :docstring
   "Returns non-nil if nums are in monotonically non-decreasing order,\notherwise false."},
  "some->"
  {:description
   "When `expr` is not nil, threads it into the first form (via `->`), and when that\nresult is not nil, through the next, etc.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "some->",
   :signature ["[expr & forms]"],
   :type "macro",
   :related
   ["cljs.core/->"
    "cljs.core/->>"
    "cljs.core/some->>"
    "cljs.core/some"],
   :examples-strings [],
   :full-name "cljs.core/some->",
   :docstring
   "When expr is not nil, threads it into the first form (via ->),\nand when that result is not nil, through the next etc"},
  "undefined?"
  {:ns "cljs.core",
   :name "undefined?",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/undefined?",
   :docstring
   "Returns true if x identical to the JavaScript undefined value.",
   :examples-strings [],
   :examples-htmls []},
  "PersistentVector.EMPTY-NODE"
  {:ns "cljs.core",
   :name "PersistentVector.EMPTY-NODE",
   :type "var",
   :full-name "cljs.core/PersistentVector.EMPTY-NODE",
   :examples-strings [],
   :examples-htmls []},
  "PersistentQueue"
  {:ns "cljs.core",
   :name "PersistentQueue",
   :signature ["[meta count front rear __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentQueue",
   :examples-strings [],
   :examples-htmls []},
  "if-not"
  {:description
   "If `test` is false or nil, evaluates and returns `then`. Otherwise, evaluates\nand returns `else`. `else` defaults to nil if not provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "if-not",
   :signature ["[test then]" "[test then else]"],
   :type "macro",
   :related ["special/if" "cljs.core/when-not"],
   :examples-strings [],
   :full-name "cljs.core/if-not",
   :docstring
   "Evaluates test. If logical false, evaluates and returns then expr, \notherwise else expr, if supplied, else nil."},
  "sorted-map-by"
  {:description
   "Returns a new sorted map with supplied mappings, using the supplied comparator\nfunction.\n\n`keyvals` must be an even number of forms.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "sorted-map-by",
   :signature ["[comparator & keyvals]"],
   :type "function",
   :related
   ["cljs.core/sorted-map"
    "cljs.core/subseq"
    "cljs.core/rsubseq"
    "cljs.core/sorted-set-by"],
   :examples-strings [],
   :full-name "cljs.core/sorted-map-by",
   :docstring
   "keyval => key val\nReturns a new sorted map with supplied mappings, using the supplied comparator."},
  "keep"
  {:description
   "Returns a lazy sequence of the non-nil results of `(f item)`. Note, this means\nfalse return values will be included.\n\n`f` must be free of side-effects.\n\nReturns a transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "keep",
   :signature ["[f]" "[f coll]"],
   :type "function",
   :related
   ["cljs.core/keep-indexed" "cljs.core/map" "cljs.core/filter"],
   :examples-strings [],
   :full-name "cljs.core/keep",
   :docstring
   "Returns a lazy sequence of the non-nil results of (f item). Note,\nthis means false return values will be included.  f must be free of\nside-effects.  Returns a transducer when no collection is provided."},
  "js-keys"
  {:description "Returns the keys for the JavaScript object `obj`.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;js-keys #js {:foo 1 :bar 2}&#41;\n;;=&gt; #js &#91;&quot;foo&quot; &quot;bar&quot;&#93;\n</code></pre>"],
   :ns "cljs.core",
   :name "js-keys",
   :signature ["[obj]"],
   :type "function",
   :related ["cljs.core/keys"],
   :examples-strings [["(js-keys #js {:foo 1 :bar 2})"]],
   :examples
   [{:id "5dd933",
     :content
     "```clj\n(js-keys #js {:foo 1 :bar 2})\n;;=> #js [\"foo\" \"bar\"]\n```"}],
   :full-name "cljs.core/js-keys",
   :docstring "Return the JavaScript keys for an object."},
  "unchecked-subtract"
  {:ns "cljs.core",
   :name "unchecked-subtract",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-subtract",
   :docstring
   "If no ys are supplied, returns the negation of x, else subtracts\nthe ys from x and returns the result.",
   :examples-strings [],
   :examples-htmls []},
  "declare"
  {:description
   "Uses `def` to establish symbols of `names` with no bindings.\n\nUseful for making forward declarations.",
   :examples-htmls
   ["<pre><code class=\"clj\">a\n;; WARNING: Use of undeclared Var\n\n&#40;declare a&#41;\na\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "declare",
   :signature ["[& names]"],
   :type "macro",
   :related ["special/def"],
   :examples-strings [["a" "(declare a)" "a"]],
   :examples
   [{:id "5a2dc2",
     :content
     "```clj\na\n;; WARNING: Use of undeclared Var\n\n(declare a)\na\n;;=> nil\n```"}],
   :full-name "cljs.core/declare",
   :docstring
   "defs the supplied var names with no bindings, useful for making forward declarations."},
  "when-first"
  {:description
   "With `bindings` as `x`, `xs`, roughly the same as `(when (seq xs) (let [x (first\nxs)] body))` but `xs` is evaluated only once.",
   :ns "cljs.core",
   :name "when-first",
   :signature ["[bindings & body]"],
   :type "macro",
   :full-name "cljs.core/when-first",
   :docstring
   "bindings => x xs\n\nRoughly the same as (when (seq xs) (let [x (first xs)] body)) but xs is evaluated only once",
   :examples-strings [],
   :examples-htmls []},
  "PersistentHashMap"
  {:ns "cljs.core",
   :name "PersistentHashMap",
   :signature ["[meta cnt root has-nil? nil-val __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentHashMap",
   :examples-strings [],
   :examples-htmls []},
  "IMultiFn"
  {:ns "cljs.core",
   :name "IMultiFn",
   :type "protocol",
   :full-name "cljs.core/IMultiFn",
   :examples-strings [],
   :examples-htmls []},
  "seqable?"
  {:ns "cljs.core",
   :name "seqable?",
   :signature ["[s]"],
   :type "function",
   :full-name "cljs.core/seqable?",
   :docstring "Return true if s satisfies ISeqable",
   :examples-strings [],
   :examples-htmls []},
  "nthnext"
  {:description
   "Returns the `n`th `next` of `coll`.\n\nReturns `(seq coll)` when `n` is 0.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "nthnext",
   :signature ["[coll n]"],
   :type "function",
   :related ["cljs.core/nth" "cljs.core/drop" "cljs.core/nthrest"],
   :examples-strings [],
   :full-name "cljs.core/nthnext",
   :docstring "Returns the nth next of coll, (seq coll) when n is 0."},
  "IKVReduce"
  {:ns "cljs.core",
   :name "IKVReduce",
   :type "protocol",
   :full-name "cljs.core/IKVReduce",
   :docstring
   "Protocol for associative types that can reduce themselves\n  via a function of key and val. Called by cljs.core/reduce-kv.",
   :examples-strings [],
   :examples-htmls []},
  "to-array-2d"
  {:description
   "Returns a (potentially-ragged) 2-dimensional JavaScript array containing the\ncontents of `coll`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "to-array-2d",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/to-array"],
   :examples-strings [],
   :full-name "cljs.core/to-array-2d",
   :docstring
   "Returns a (potentially-ragged) 2-dimensional array\ncontaining the contents of coll."},
  "demunge"
  {:ns "cljs.core",
   :name "demunge",
   :signature ["[name]"],
   :type "function",
   :full-name "cljs.core/demunge",
   :examples-strings [],
   :examples-htmls []},
  "PersistentTreeSet"
  {:ns "cljs.core",
   :name "PersistentTreeSet",
   :signature ["[meta tree-map __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentTreeSet",
   :examples-strings [],
   :examples-htmls []},
  "disj!"
  {:ns "cljs.core",
   :name "disj!",
   :signature ["[tcoll val]" "[tcoll val & vals]"],
   :type "function",
   :full-name "cljs.core/disj!",
   :docstring
   "disj[oin]. Returns a transient set of the same (hashed/sorted) type, that\ndoes not contain key(s).",
   :examples-strings [],
   :examples-htmls []},
  "PersistentArrayMap.fromArrays"
  {:ns "cljs.core",
   :name "PersistentArrayMap.fromArrays",
   :signature ["[ks vs]"],
   :type "function",
   :full-name "cljs.core/PersistentArrayMap.fromArrays",
   :examples-strings [],
   :examples-htmls []},
  "Reduced"
  {:ns "cljs.core",
   :name "Reduced",
   :signature ["[val]"],
   :type "type",
   :full-name "cljs.core/Reduced",
   :examples-strings [],
   :examples-htmls []},
  "aget"
  {:description
   "Returns the value at index `i` of a JavaScript array.\n\n```clj\n(def a #js [1 2 3])\n(aget a 0)\n;;=> 1\n```\n\nRetrieve nested elements with the additional `idxs` arguments.\n\n```clj\n(def a #js [1 2 #js [3 4]])\n(aget a 2 0)\n;;=> 3\n```\n\nFor JavaScript objects, use [`goog.object/get`] or the multi-arity [`goog.object/getValueByKeys`].\n\n```clj\n(require 'goog.object)\n(def obj #js {:foo #js {:bar 2}})\n\n(goog.object/get obj \"foo\")\n;;=> #js {:bar 2} \n\n(goog.object/getValueByKeys obj \"foo\" \"bar\")\n;;=> 2\n```\n\n[`goog.object/get`]:http://google.github.io/closure-library/api/namespace_goog_object.html#get\n[`goog.object/getValueByKeys`]:http://google.github.io/closure-library/api/namespace_goog_object.html#getValueByKeys",
   :examples-htmls [],
   :ns "cljs.core",
   :name "aget",
   :signature ["[array i]" "[array i & idxs]"],
   :type "function/macro",
   :related
   ["cljs.core/.." "cljs.core/aset" "cljs.core/get" "cljs.core/nth"],
   :examples-strings [],
   :full-name "cljs.core/aget",
   :docstring "Returns the value at the index."},
  "find"
  {:description
   "Returns the map entry for key `k`, or nil if `k` is not found.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "find",
   :signature ["[coll k]"],
   :type "function",
   :related ["cljs.core/get" "cljs.core/get-in"],
   :examples-strings [],
   :full-name "cljs.core/find",
   :docstring
   "Returns the map entry for key, or nil if key not present."},
  "m3-C2"
  {:ns "cljs.core",
   :name "m3-C2",
   :type "var",
   :full-name "cljs.core/m3-C2",
   :examples-strings [],
   :examples-htmls []},
  "system-time"
  {:ns "cljs.core",
   :name "system-time",
   :signature ["[]"],
   :type "function",
   :full-name "cljs.core/system-time",
   :docstring
   "Returns highest resolution time offered by host in milliseconds.",
   :examples-strings [],
   :examples-htmls []},
  "ITransientCollection"
  {:ns "cljs.core",
   :name "ITransientCollection",
   :type "protocol",
   :full-name "cljs.core/ITransientCollection",
   :docstring
   "Protocol for adding basic functionality to transient collections.",
   :examples-strings [],
   :examples-htmls []},
  "create-ns"
  {:ns "cljs.core",
   :name "create-ns",
   :signature ["[sym]" "[sym ns-obj]"],
   :type "function",
   :full-name "cljs.core/create-ns",
   :examples-strings [],
   :examples-htmls []},
  "number?"
  {:description "Returns true if `n` is a number, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "number?",
   :signature ["[n]"],
   :type "function/macro",
   :related ["cljs.core/integer?"],
   :examples-strings [],
   :full-name "cljs.core/number?",
   :docstring "Returns true if x is a JavaScript number."},
  "IRecord"
  {:ns "cljs.core",
   :name "IRecord",
   :type "protocol",
   :full-name "cljs.core/IRecord",
   :docstring "Marker interface indicating a record object",
   :examples-strings [],
   :examples-htmls []},
  "bit-shift-right-zero-fill"
  {:ns "cljs.core",
   :name "bit-shift-right-zero-fill",
   :signature ["[x n]"],
   :type "function/macro",
   :full-name "cljs.core/bit-shift-right-zero-fill",
   :docstring "DEPRECATED: Bitwise shift right with zero fill",
   :examples-strings [],
   :examples-htmls []},
  "booleans"
  {:ns "cljs.core",
   :name "booleans",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/booleans",
   :examples-strings [],
   :examples-htmls []},
  "dissoc"
  {:description
   "dissoc(iate)\n\nReturns a new map that does not contain a mapping for key(s).\n\nHas no effect on the map type (hashed/sorted).",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;dissoc {:key &quot;value&quot; :key2 &quot;value2&quot;} :key&#41;\n;;=&gt; {:key2 &quot;value2&quot;}\n</code></pre>"],
   :ns "cljs.core",
   :name "dissoc",
   :signature ["[coll]" "[coll k]" "[coll k & ks]"],
   :type "function",
   :related
   ["cljs.core/assoc" "cljs.core/disj" "cljs.core/select-keys"],
   :examples-strings
   [["(dissoc {:key \"value\" :key2 \"value2\"} :key)"]],
   :examples
   [{:id "fd6ae9",
     :content
     "```clj\n(dissoc {:key \"value\" :key2 \"value2\"} :key)\n;;=> {:key2 \"value2\"}\n```"}],
   :full-name "cljs.core/dissoc",
   :docstring
   "dissoc[iate]. Returns a new map of the same (hashed/sorted) type,\nthat does not contain a mapping for key(s)."},
  "mix-collection-hash"
  {:ns "cljs.core",
   :name "mix-collection-hash",
   :signature ["[hash-basis count]"],
   :type "function",
   :full-name "cljs.core/mix-collection-hash",
   :docstring
   "Mix final collection hash for ordered or unordered collections.\nhash-basis is the combined collection hash, count is the number\nof elements included in the basis. Note this is the hash code\nconsistent with =, different from .hashCode.\nSee http://clojure.org/data_structures#hash for full algorithms.",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-divide-int"
  {:ns "cljs.core",
   :name "unchecked-divide-int",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-divide-int",
   :docstring
   "If no denominators are supplied, returns 1/numerator,\nelse returns numerator divided by all of the denominators.",
   :examples-strings [],
   :examples-htmls []},
  "gensym"
  {:ns "cljs.core",
   :name "gensym",
   :signature ["[]" "[prefix-string]"],
   :type "function",
   :full-name "cljs.core/gensym",
   :docstring
   "Returns a new symbol with a unique name. If a prefix string is\nsupplied, the name is prefix# where # is some unique number. If\nprefix is not supplied, the prefix is 'G__'.",
   :examples-strings [],
   :examples-htmls []},
  "js-obj"
  {:description
   "Returns a new JavaScript object using the supplied mappings.\n\n`keyvals` must be an even number of forms.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;js-obj &quot;foo&quot; 1 &quot;bar&quot; 2&#41;\n;;=&gt; #js {:foo 1, :bar 2}\n</code></pre>"],
   :ns "cljs.core",
   :name "js-obj",
   :signature ["[& keyvals]"],
   :type "function/macro",
   :related
   ["syntax/js-literal" "cljs.core/array" "cljs.core/clj->js"],
   :examples-strings [["(js-obj \"foo\" 1 \"bar\" 2)"]],
   :examples
   [{:id "657cd7",
     :content
     "```clj\n(js-obj \"foo\" 1 \"bar\" 2)\n;;=> #js {:foo 1, :bar 2}\n```"}],
   :full-name "cljs.core/js-obj",
   :docstring
   "Create JavaSript object from an even number arguments representing\ninterleaved keys and values."},
  "assoc!"
  {:description
   "assoc(iate) on transient collection\n\nWhen applied to a transient map, adds mapping of key(s) to val(s).\n\nWhen applied to a transient vector, sets the val at index.  Note - index must\nbe <= (count vector).\n\nReturns coll.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def tcoll &#40;transient! {}&#41;&#41;\n&#40;assoc! tcoll :a 1&#41;\n&#40;assoc! tcoll :b 2&#41;\n\ntcoll\n;;=&gt; #&lt;&#91;object Object&#93;&gt; \n\n&#40;:a tcoll&#41;\n;;=&gt; 1\n\n&#40;:b tcoll&#41;\n;;=&gt; 2\n\n&#40;def a &#40;persistent! tcoll&#41;&#41;\n;;=&gt; {:a 1 :b 2}\n</code></pre>"],
   :ns "cljs.core",
   :name "assoc!",
   :signature ["[tcoll key val]" "[tcoll key val & kvs]"],
   :type "function",
   :related ["cljs.core/transient" "cljs.core/persistent!"],
   :examples-strings
   [["(def tcoll (transient! {}))"
     "(assoc! tcoll :a 1)"
     "(assoc! tcoll :b 2)"
     "tcoll"
     "(:a tcoll)"
     "(:b tcoll)"
     "(def a (persistent! tcoll))"]],
   :examples
   [{:id "7d1e6b",
     :content
     "```clj\n(def tcoll (transient! {}))\n(assoc! tcoll :a 1)\n(assoc! tcoll :b 2)\n\ntcoll\n;;=> #<[object Object]> \n\n(:a tcoll)\n;;=> 1\n\n(:b tcoll)\n;;=> 2\n\n(def a (persistent! tcoll))\n;;=> {:a 1 :b 2}\n```"}],
   :full-name "cljs.core/assoc!",
   :docstring
   "When applied to a transient map, adds mapping of key(s) to\nval(s). When applied to a transient vector, sets the val at index.\nNote - index must be <= (count vector). Returns coll."},
  "js-delete"
  {:description
   "Deletes property `key` in JavaScript object `obj`.\n\nEquivalent to `delete obj[key]` in JavaScript.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a #js {:foo 1 :bar 2}&#41;\n&#40;js-delete a &quot;foo&quot;&#41;\n\na\n;;=&gt; #js {:bar 2}\n</code></pre>"],
   :ns "cljs.core",
   :name "js-delete",
   :signature ["[obj key]"],
   :type "function/macro",
   :related ["cljs.core/dissoc"],
   :examples-strings
   [["(def a #js {:foo 1 :bar 2})" "(js-delete a \"foo\")" "a"]],
   :examples
   [{:id "5b24ea",
     :content
     "```clj\n(def a #js {:foo 1 :bar 2})\n(js-delete a \"foo\")\n\na\n;;=> #js {:bar 2}\n```"}],
   :full-name "cljs.core/js-delete",
   :docstring "Delete a property from a JavaScript object."},
  "object?"
  {:description
   "Returns true if `x` is a JavaScript object, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "object?",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/array?"],
   :examples-strings [],
   :full-name "cljs.core/object?",
   :docstring "Returns true if x's constructor is Object"},
  "when-some"
  {:description
   "When `test` is not nil, evaluates `body` with `x` bound to the value of `test`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "when-some",
   :signature ["[[x test] & body]"],
   :type "macro",
   :related ["cljs.core/if-some"],
   :examples-strings [],
   :full-name "cljs.core/when-some",
   :docstring
   "bindings => binding-form test\n\nWhen test is not nil, evaluates body with binding-form bound to the\nvalue of test"},
  "RecordIter"
  {:ns "cljs.core",
   :name "RecordIter",
   :signature ["[i record base-count fields ext-map-iter]"],
   :type "type",
   :full-name "cljs.core/RecordIter",
   :examples-strings [],
   :examples-htmls []},
  "vector?"
  {:description "Returns true if `x` is a vector, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "vector?",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/vector" "cljs.core/vec"],
   :examples-strings [],
   :full-name "cljs.core/vector?",
   :docstring "Return true if x satisfies IVector"},
  "remove-watch"
  {:description
   "Removes a watch function identified by `key` from atom `a`.  The function must\nhave originally been set by `add-watch`.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a &#40;atom {}&#41;&#41;\n\n&#40;add-watch a :logger\n  &#40;fn &#91;&#95;key &#95;atom old-state new-state&#93;\n    &#40;println &quot;old:&quot; old-state&#41;\n    &#40;println &quot;new:&quot; new-state&#41;&#41;&#41;\n\n&#40;swap! a assoc :foo &quot;bar&quot;&#41;\n;;=&gt; will print the following:\n;; old: {}\n;; new: {:foo &quot;bar&quot;}\n\n&#40;remove-watch a :logger&#41;\n\n&#40;swap! a assoc :foo 3&#41;\n;;=&gt; nothing will be printed...\n</code></pre>"],
   :ns "cljs.core",
   :name "remove-watch",
   :signature ["[a key]"],
   :type "function",
   :related ["cljs.core/add-watch"],
   :examples-strings
   [["(def a (atom {}))"
     "(add-watch a :logger\n  (fn [_key _atom old-state new-state]\n    (println \"old:\" old-state)\n    (println \"new:\" new-state)))"
     "(swap! a assoc :foo \"bar\")"
     "(remove-watch a :logger)"
     "(swap! a assoc :foo 3)"]],
   :examples
   [{:id "70044a",
     :content
     "```clj\n(def a (atom {}))\n\n(add-watch a :logger\n  (fn [_key _atom old-state new-state]\n    (println \"old:\" old-state)\n    (println \"new:\" new-state)))\n\n(swap! a assoc :foo \"bar\")\n;;=> will print the following:\n;; old: {}\n;; new: {:foo \"bar\"}\n\n(remove-watch a :logger)\n\n(swap! a assoc :foo 3)\n;;=> nothing will be printed...\n```"}],
   :full-name "cljs.core/remove-watch",
   :docstring
   "Alpha - subject to change.\n\nRemoves a watch (set by add-watch) from a reference"},
  "js-inline-comment"
  {:ns "cljs.core",
   :name "js-inline-comment",
   :signature ["[comment]"],
   :type "macro",
   :full-name "cljs.core/js-inline-comment",
   :docstring "Emit an inline JavaScript comment.",
   :examples-strings [],
   :examples-htmls []},
  "bit-or"
  {:description "Bitwise \"or\". Same as `x | y` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-or 2r1100 2r1010&#41;\n;;=&gt; 14\n;; 14 = 2r1110\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-or 12 10&#41;\n;;=&gt; 14\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-or",
   :signature ["[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/bit-and" "cljs.core/bit-xor"],
   :examples-strings [["(bit-or 2r1100 2r1010)" "(bit-or 12 10)"]],
   :examples
   [{:id "ecea10",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-or 2r1100 2r1010)\n;;=> 14\n;; 14 = 2r1110\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-or 12 10)\n;;=> 14\n```"}],
   :full-name "cljs.core/bit-or",
   :docstring "Bitwise or"},
  "reduced"
  {:ns "cljs.core",
   :name "reduced",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/reduced",
   :docstring
   "Wraps x in a way such that a reduce will terminate with the value x",
   :examples-strings [],
   :examples-htmls []},
  "ES6SetEntriesIterator"
  {:ns "cljs.core",
   :name "ES6SetEntriesIterator",
   :signature ["[s]"],
   :type "type",
   :full-name "cljs.core/ES6SetEntriesIterator",
   :examples-strings [],
   :examples-htmls []},
  "*print-meta*"
  {:ns "cljs.core",
   :name "*print-meta*",
   :type "dynamic var",
   :full-name "cljs.core/*print-meta*",
   :docstring
   "If set to logical true, when printing an object, its metadata will also\nbe printed in a form that can be read back by the reader.\n\nDefaults to false.",
   :examples-strings [],
   :examples-htmls []},
  "not"
  {:description
   "Returns true if `x` is logical false, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "not",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/complement" "cljs.core/false?"],
   :examples-strings [],
   :full-name "cljs.core/not",
   :docstring "Returns true if x is logical false, false otherwise."},
  "longs"
  {:ns "cljs.core",
   :name "longs",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/longs",
   :examples-strings [],
   :examples-htmls []},
  "HashMap.EMPTY"
  {:ns "cljs.core",
   :name "HashMap.EMPTY",
   :type "var",
   :full-name "cljs.core/HashMap.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "newline"
  {:ns "cljs.core",
   :name "newline",
   :signature ["[]" "[opts]"],
   :type "function",
   :full-name "cljs.core/newline",
   :examples-strings [],
   :examples-htmls []},
  "reductions"
  {:description
   "Returns a lazy sequence of the intermediate values of the reduction (as per\n`reduce`) of `coll` by `f`, starting with `init`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "reductions",
   :signature ["[f coll]" "[f init coll]"],
   :type "function",
   :related ["cljs.core/reduce"],
   :examples-strings [],
   :full-name "cljs.core/reductions",
   :docstring
   "Returns a lazy seq of the intermediate values of the reduction (as\nper reduce) of coll by f, starting with init."},
  "is_proto_"
  {:ns "cljs.core",
   :name "is_proto_",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/is_proto_",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-negate-int"
  {:ns "cljs.core",
   :name "unchecked-negate-int",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-negate-int",
   :examples-strings [],
   :examples-htmls []},
  "doall"
  {:description
   "Forces evaluation of a lazy sequence. Often used to see the effects of a\nsequence produced via functions that have side effects.\n\n`doall` walks through the successive `next`s of the sequence, returning the head\nand causing the entire sequence to reside in memory at one time.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "doall",
   :signature ["[coll]" "[n coll]"],
   :type "function",
   :related ["cljs.core/dorun" "cljs.core/doseq"],
   :examples-strings [],
   :full-name "cljs.core/doall",
   :docstring
   "When lazy sequences are produced via functions that have side\neffects, any effects other than those needed to produce the first\nelement in the seq do not occur until the seq is consumed. doall can\nbe used to force any effects. Walks through the successive nexts of\nthe seq, retains the head and returns it, thus causing the entire\nseq to reside in memory at one time."},
  "if-let"
  {:description
   "When `test` is logical true, evaluates `then` with the value of `test` bound to\n`x`. Otherwise, evaluates `else` with no bindings.\n\n`else` defaults to nil.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "if-let",
   :signature ["[[x test] then]" "[[x test] then else]"],
   :type "macro",
   :related ["cljs.core/when-let" "special/if"],
   :examples-strings [],
   :full-name "cljs.core/if-let",
   :docstring
   "bindings => binding-form test\n\nIf test is true, evaluates then with binding-form bound to the value of \ntest, if not, yields else"},
  "pop!"
  {:ns "cljs.core",
   :name "pop!",
   :signature ["[tcoll]"],
   :type "function",
   :full-name "cljs.core/pop!",
   :docstring
   "Removes the last item from a transient vector. If\nthe collection is empty, throws an exception. Returns coll",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-add"
  {:ns "cljs.core",
   :name "unchecked-add",
   :signature ["[]" "[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-add",
   :docstring "Returns the sum of nums. (+) returns 0.",
   :examples-strings [],
   :examples-htmls []},
  "IReversible"
  {:ns "cljs.core",
   :name "IReversible",
   :type "protocol",
   :full-name "cljs.core/IReversible",
   :docstring "Protocol for reversing a seq.",
   :examples-strings [],
   :examples-htmls []},
  "run!"
  {:ns "cljs.core",
   :name "run!",
   :signature ["[proc coll]"],
   :type "function",
   :full-name "cljs.core/run!",
   :docstring
   "Runs the supplied procedure (via reduce), for purposes of side\neffects, on successive items in the collection. Returns nil",
   :examples-strings [],
   :examples-htmls []},
  "PersistentArrayMapSeq"
  {:ns "cljs.core",
   :name "PersistentArrayMapSeq",
   :signature ["[arr i _meta]"],
   :type "type",
   :full-name "cljs.core/PersistentArrayMapSeq",
   :examples-strings [],
   :examples-htmls []},
  "string-hash-cache-count"
  {:ns "cljs.core",
   :name "string-hash-cache-count",
   :type "var",
   :full-name "cljs.core/string-hash-cache-count",
   :examples-strings [],
   :examples-htmls []},
  "find-ns-obj"
  {:ns "cljs.core",
   :name "find-ns-obj",
   :signature ["[ns]"],
   :type "function",
   :full-name "cljs.core/find-ns-obj",
   :examples-strings [],
   :examples-htmls []},
  "IEditableCollection"
  {:ns "cljs.core",
   :name "IEditableCollection",
   :type "protocol",
   :full-name "cljs.core/IEditableCollection",
   :docstring
   "Protocol for collections which can transformed to transients.",
   :examples-strings [],
   :examples-htmls []},
  "NS_CACHE"
  {:ns "cljs.core",
   :name "NS_CACHE",
   :type "var",
   :full-name "cljs.core/NS_CACHE",
   :examples-strings [],
   :examples-htmls []},
  "DEMUNGE_MAP"
  {:ns "cljs.core",
   :name "DEMUNGE_MAP",
   :type "var",
   :full-name "cljs.core/DEMUNGE_MAP",
   :examples-strings [],
   :examples-htmls []},
  "doseq"
  {:description
   "Repeatedly executes `body` (presumably for side-effects) with bindings and\nfiltering as provided by `for`. Does not retain the head of the sequence.\n\nReturns nil.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "doseq",
   :signature ["[seq-exprs & body]"],
   :type "macro",
   :related
   ["cljs.core/doall"
    "cljs.core/dorun"
    "cljs.core/for"
    "cljs.core/dotimes"],
   :examples-strings [],
   :full-name "cljs.core/doseq",
   :docstring
   "Repeatedly executes body (presumably for side-effects) with\nbindings and filtering as provided by \"for\".  Does not retain\nthe head of the sequence. Returns nil."},
  "sequential?"
  {:description
   "Returns true if `coll` implements the `ISequential` protocol, false otherwise.\n\nLists and vectors are sequential.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "sequential?",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/seq?" "cljs.core/coll?"],
   :examples-strings [],
   :full-name "cljs.core/sequential?",
   :docstring "Returns true if coll satisfies ISequential"},
  "get-validator"
  {:description "Returns the validator function for atom `a`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "get-validator",
   :signature ["[a]"],
   :type "function",
   :related ["cljs.core/atom" "cljs.core/set-validator!"],
   :examples-strings [],
   :full-name "cljs.core/get-validator",
   :docstring "Gets the validator-fn for a var/ref/agent/atom."},
  "pr-with-opts"
  {:ns "cljs.core",
   :name "pr-with-opts",
   :signature ["[objs opts]"],
   :type "function",
   :full-name "cljs.core/pr-with-opts",
   :docstring
   "Prints a sequence of objects using string-print, observing all\nthe options given in opts",
   :examples-strings [],
   :examples-htmls []},
  "replicate"
  {:ns "cljs.core",
   :name "replicate",
   :signature ["[n x]"],
   :type "function",
   :full-name "cljs.core/replicate",
   :docstring "Returns a lazy seq of n xs.",
   :examples-strings [],
   :examples-htmls []},
  "m3-hash-int"
  {:ns "cljs.core",
   :name "m3-hash-int",
   :signature ["[in]"],
   :type "function",
   :full-name "cljs.core/m3-hash-int",
   :examples-strings [],
   :examples-htmls []},
  "IDeref"
  {:ns "cljs.core",
   :name "IDeref",
   :type "protocol",
   :full-name "cljs.core/IDeref",
   :docstring
   "Protocol for adding dereference functionality to a reference.",
   :examples-strings [],
   :examples-htmls []},
  "long-array"
  {:ns "cljs.core",
   :name "long-array",
   :signature ["[size-or-seq]" "[size init-val-or-seq]"],
   :type "function",
   :full-name "cljs.core/long-array",
   :docstring
   "Creates an array of longs. Does not coerce array, provided for compatibility\nwith Clojure.",
   :examples-strings [],
   :examples-htmls []},
  "Delay"
  {:ns "cljs.core",
   :name "Delay",
   :signature ["[f value]"],
   :type "type",
   :full-name "cljs.core/Delay",
   :examples-strings [],
   :examples-htmls []},
  "some-fn"
  {:description
   "Takes a set of predicate functions and returns a function `f` that returns the\nfirst logical true value returned by one of its composing predicates against any\nof its arguments, else it returns logical false.\n\nNote that `f` is short-circuiting in that it will stop execution on the first\nargument that triggers a logical true result against the original predicates.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "some-fn",
   :signature ["[p]" "[p1 p2]" "[p1 p2 p3]" "[p1 p2 p3 & ps]"],
   :type "function",
   :related ["cljs.core/every-pred" "cljs.core/some" "cljs.core/or"],
   :examples-strings [],
   :full-name "cljs.core/some-fn",
   :docstring
   "Takes a set of predicates and returns a function f that returns the first logical true value\nreturned by one of its composing predicates against any of its arguments, else it returns\nlogical false. Note that f is short-circuiting in that it will stop execution on the first\nargument that triggers a logical true result against the original predicates."},
  "update-in"
  {:description
   "\"Updates\" a value in a nested associative structure, where `ks` is a sequence of\nkeys and `f` is a function that will take the old value and any supplied\narguments and return the new value. Returns a new nested structure.\n\nIf any levels do not exist, hash-maps will be created.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "update-in",
   :signature
   ["[m [k & ks] f]"
    "[m [k & ks] f a]"
    "[m [k & ks] f a b]"
    "[m [k & ks] f a b c]"
    "[m [k & ks] f a b c & args]"],
   :type "function",
   :related ["cljs.core/assoc-in" "cljs.core/get-in"],
   :examples-strings [],
   :full-name "cljs.core/update-in",
   :docstring
   "'Updates' a value in a nested associative structure, where ks is a\nsequence of keys and f is a function that will take the old value\nand any supplied args and return the new value, and returns a new\nnested structure.  If any levels do not exist, hash-maps will be\ncreated."},
  "var"
  {:ns "special",
   :name "var",
   :signature ["[symbol]"],
   :type "special form",
   :full-name "special/var",
   :docstring
   "The symbol must resolve to a var, and the Var object\nitself (not its value) is returned. The reader macro #'x expands to (var x).",
   :examples-strings [],
   :examples-htmls []},
  "RedNode"
  {:ns "cljs.core",
   :name "RedNode",
   :signature ["[key val left right __hash]"],
   :type "type",
   :full-name "cljs.core/RedNode",
   :examples-strings [],
   :examples-htmls []},
  "hash-map"
  {:description
   "Returns a new hash map with supplied mappings.\n\n`keyvals` must be an even number of forms.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "hash-map",
   :signature ["[& keyvals]"],
   :type "function/macro",
   :related ["cljs.core/array-map" "cljs.core/sorted-map"],
   :examples-strings [],
   :full-name "cljs.core/hash-map",
   :docstring
   "keyval => key val\nReturns a new hash map with supplied mappings."},
  "try"
  {:ns "cljs.core",
   :name "try",
   :signature ["[& forms]"],
   :type "macro",
   :full-name "cljs.core/try",
   :docstring
   "(try expr* catch-clause* finally-clause?)\n\n Special Form\n\n catch-clause => (catch protoname name expr*)\n finally-clause => (finally expr*)\n\nCatches and handles JavaScript exceptions.",
   :examples-strings [],
   :examples-htmls []},
  "/"
  {:description
   "If no denominators are supplied, returns 1/numerator, else returns numerator\ndivided by all of the denominators.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;/ 6 3&#41;\n;;=&gt; 2\n\n&#40;/ 6 3 2&#41;\n;;=&gt; 1\n\n&#40;/ 10&#41;\n;;=&gt; 0.1\n\n&#40;/ 1 3&#41;\n;;=&gt; 0.3333333333333333\n</code></pre>"],
   :ns "cljs.core",
   :name "/",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/*" "cljs.core/quot"],
   :examples-strings [["(/ 6 3)" "(/ 6 3 2)" "(/ 10)" "(/ 1 3)"]],
   :examples
   [{:id "824bb7",
     :content
     "```clj\n(/ 6 3)\n;;=> 2\n\n(/ 6 3 2)\n;;=> 1\n\n(/ 10)\n;;=> 0.1\n\n(/ 1 3)\n;;=> 0.3333333333333333\n```"}],
   :full-name "cljs.core//",
   :docstring
   "If no denominators are supplied, returns 1/numerator,\nelse returns numerator divided by all of the denominators."},
  "str"
  {:description
   "`(str)` and `(str nil)` return the empty string.\n\n`(str x)` returns `x.toString()`.\n\nWith more than one argument, returns the concatenation of the `str` values of\nthe arguments.",
   :ns "cljs.core",
   :name "str",
   :signature ["[]" "[x]" "[x & ys]"],
   :type "function/macro",
   :full-name "cljs.core/str",
   :docstring
   "With no args, returns the empty string. With one arg x, returns\nx.toString().  (str nil) returns the empty string. With more than\none arg, returns the concatenation of the str values of the args.",
   :examples-strings [],
   :examples-htmls []},
  "import-macros"
  {:ns "cljs.core",
   :name "import-macros",
   :signature ["[ns [& vars]]"],
   :type "macro",
   :full-name "cljs.core/import-macros",
   :examples-strings [],
   :examples-htmls []},
  "pop"
  {:description
   "For a list, returns a new list without the first item.\n\nFor a vector, returns a new vector without the last item.",
   :examples-htmls
   ["<p>With vectors:</p><pre><code class=\"clj\">&#40;pop &#91;1 2 3&#93;&#41;\n;;=&gt; &#91;1 2&#93;\n\n&#40;pop &#91;1 2&#93;&#41;\n;;=&gt; &#91;1&#93;\n\n&#40;pop &#91;1&#93;&#41;\n;;=&gt; &#91;&#93;\n\n&#40;pop &#91;&#93;&#41;\n;; Error: Can't pop empty vector\n</code></pre>"
    "<p>With lists:</p><pre><code class=\"clj\">&#40;pop '&#40;1 2 3&#41;&#41;\n;;=&gt; &#40;2 3&#41;\n\n&#40;pop '&#40;1 2&#41;&#41;\n;;=&gt; &#40;2&#41;\n\n&#40;pop '&#40;1&#41;&#41;\n;;=&gt; &#40;&#41;\n\n&#40;pop '&#40;&#41;&#41;\n;; Error: Can't pop empty list\n</code></pre>"],
   :ns "cljs.core",
   :name "pop",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/peek" "cljs.core/rest" "cljs.core/conj"],
   :examples-strings
   [["(pop [1 2 3])" "(pop [1 2])" "(pop [1])" "(pop [])"]
    ["(pop '(1 2 3))" "(pop '(1 2))" "(pop '(1))" "(pop '())"]],
   :examples
   [{:id "6bd9f7",
     :content
     "With vectors:\n\n```clj\n(pop [1 2 3])\n;;=> [1 2]\n\n(pop [1 2])\n;;=> [1]\n\n(pop [1])\n;;=> []\n\n(pop [])\n;; Error: Can't pop empty vector\n```"}
    {:id "81221f",
     :content
     "With lists:\n\n```clj\n(pop '(1 2 3))\n;;=> (2 3)\n\n(pop '(1 2))\n;;=> (2)\n\n(pop '(1))\n;;=> ()\n\n(pop '())\n;; Error: Can't pop empty list\n```"}],
   :full-name "cljs.core/pop",
   :docstring
   "For a list or queue, returns a new list/queue without the first\nitem, for a vector, returns a new vector without the last item.\nNote - not the same as next/butlast."},
  "long"
  {:ns "cljs.core",
   :name "long",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/long",
   :docstring
   "Coerce to long by stripping decimal places. Identical to `int'.",
   :examples-strings [],
   :examples-htmls []},
  "reverse"
  {:description
   "Returns a sequence of the items in `coll` in reverse order. Not lazy.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "reverse",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/rseq"],
   :examples-strings [],
   :full-name "cljs.core/reverse",
   :docstring
   "Returns a seq of the items in coll in reverse order. Not lazy."},
  "vswap!"
  {:ns "cljs.core",
   :name "vswap!",
   :signature ["[vol f & args]"],
   :type "macro",
   :related ["cljs.core/vreset!" "cljs.core/volatile!"],
   :full-name "cljs.core/vswap!",
   :docstring
   "Non-atomically swaps the value of the volatile as if:\n(apply f current-value-of-vol args). Returns the value that\nwas swapped in.",
   :examples-strings [],
   :examples-htmls []},
  "rand"
  {:description
   "Returns a random floating point number between 0 inclusive and `n` exclusive.\n\n`n` defaults to 1.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "rand",
   :signature ["[]" "[n]"],
   :type "function",
   :related ["cljs.core/rand-int" "cljs.core/rand-nth"],
   :examples-strings [],
   :full-name "cljs.core/rand",
   :docstring
   "Returns a random floating point number between 0 (inclusive) and\nn (default 1) (exclusive)."},
  "conj!"
  {:ns "cljs.core",
   :name "conj!",
   :signature ["[]" "[coll]" "[tcoll val]" "[tcoll val & vals]"],
   :type "function",
   :full-name "cljs.core/conj!",
   :docstring
   "Adds x to the transient collection, and return coll. The 'addition'\nmay happen at different 'places' depending on the concrete type.",
   :examples-strings [],
   :examples-htmls []},
  "trampoline"
  {:ns "cljs.core",
   :name "trampoline",
   :signature ["[f]" "[f & args]"],
   :type "function",
   :full-name "cljs.core/trampoline",
   :docstring
   "trampoline can be used to convert algorithms requiring mutual\nrecursion without stack consumption. Calls f with supplied args, if\nany. If f returns a fn, calls that fn with no arguments, and\ncontinues to repeat, until the return value is not a fn, then\nreturns that non-fn value. Note that if you want to return a fn as a\nfinal value, you must wrap it in some data structure and unpack it\nafter trampoline returns.",
   :examples-strings [],
   :examples-htmls []},
  "hash"
  {:ns "cljs.core",
   :name "hash",
   :signature ["[o]"],
   :type "function",
   :full-name "cljs.core/hash",
   :docstring
   "Returns the hash code of its argument. Note this is the hash code\nconsistent with =.",
   :examples-strings [],
   :examples-htmls []},
  "simple-benchmark"
  {:ns "cljs.core",
   :name "simple-benchmark",
   :signature
   ["[bindings expr iterations & {:keys [print-fn], :or {print-fn (quote println)}}]"],
   :type "macro",
   :full-name "cljs.core/simple-benchmark",
   :docstring
   "Runs expr iterations times in the context of a let expression with\nthe given bindings, then prints out the bindings and the expr\nfollowed by number of iterations and total time. The optional\nargument print-fn, defaulting to println, sets function used to\nprint the result. expr's string representation will be produced\nusing pr-str in any case.",
   :examples-strings [],
   :examples-htmls []},
  ".."
  {:description
   "For interop, the `..` macro allows method/property chaining on the given JavaScript object `o`.\n\nIt essentially combines the thread-first `->` macro with the `.` operator.",
   :examples-htmls
   ["<pre><code class=\"js\">// JavaScript\n&quot;a b c d&quot;.toUpperCase&#40;&#41;.replace&#40;&quot;A&quot;, &quot;X&quot;&#41;\n//=&gt; &quot;X B C D&quot;\n</code></pre><pre><code class=\"clj\">;; ClojureScript\n&#40;.. &quot;a b c d&quot;\n    toUpperCase\n    &#40;replace &quot;A&quot; &quot;X&quot;&#41;&#41;\n;;=&gt; &quot;X B C D&quot;\n</code></pre><p>This is expanded to:</p><pre><code class=\"clj\">&#40;. &#40;. &quot;a b c d&quot; toUpperCase&#41; &#40;replace &quot;A&quot; &quot;X&quot;&#41;&#41;\n</code></pre><p>which is equivalent to:</p><pre><code class=\"clj\">&#40;.replace &#40;.toUpperCase &quot;a b c d&quot;&#41; &quot;A&quot; &quot;X&quot;&#41;\n;;=&gt; &quot;X B C D&quot;\n</code></pre><p>Compare to the equivalent form using the thread-first <code>-&gt;</code> macro:</p><pre><code class=\"clj\">&#40;-&gt; &quot;a b c d&quot;\n    .toUpperCase\n    &#40;.replace &quot;A&quot; &quot;X&quot;&#41;&#41;\n;;=&gt; &quot;X B C D&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "..",
   :signature ["[o form]" "[o form & more]"],
   :type "macro",
   :related ["special/." "cljs.core/->" "cljs.core/doto"],
   :examples-strings
   [["(.. \"a b c d\"\n    toUpperCase\n    (replace \"A\" \"X\"))"
     "(. (. \"a b c d\" toUpperCase) (replace \"A\" \"X\"))"
     "(.replace (.toUpperCase \"a b c d\") \"A\" \"X\")"
     "(-> \"a b c d\"\n    .toUpperCase\n    (.replace \"A\" \"X\"))"]],
   :examples
   [{:id "500658",
     :content
     "```js\n// JavaScript\n\"a b c d\".toUpperCase().replace(\"A\", \"X\")\n//=> \"X B C D\"\n```\n\n```clj\n;; ClojureScript\n(.. \"a b c d\"\n    toUpperCase\n    (replace \"A\" \"X\"))\n;;=> \"X B C D\"\n```\n\nThis is expanded to:\n\n```clj\n(. (. \"a b c d\" toUpperCase) (replace \"A\" \"X\"))\n```\n\n\nwhich is equivalent to:\n\n```clj\n(.replace (.toUpperCase \"a b c d\") \"A\" \"X\")\n;;=> \"X B C D\"\n```\n\nCompare to the equivalent form using the thread-first `->` macro:\n\n```clj\n(-> \"a b c d\"\n    .toUpperCase\n    (.replace \"A\" \"X\"))\n;;=> \"X B C D\"\n```"}],
   :full-name "cljs.core/..",
   :docstring
   "form => fieldName-symbol or (instanceMethodName-symbol args*)\n\nExpands into a member access (.) of the first member on the first\nargument, followed by the next member on the result, etc. For\ninstance:\n\n(.. System (getProperties) (get \"os.name\"))\n\nexpands to:\n\n(. (. System (getProperties)) (get \"os.name\"))\n\nbut is easier to write, read, and understand."},
  "chars"
  {:ns "cljs.core",
   :name "chars",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/chars",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-dec-int"
  {:ns "cljs.core",
   :name "unchecked-dec-int",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-dec-int",
   :docstring "Returns a number one less than x, an int.",
   :examples-strings [],
   :examples-htmls []},
  "m3-seed"
  {:ns "cljs.core",
   :name "m3-seed",
   :type "var",
   :full-name "cljs.core/m3-seed",
   :examples-strings [],
   :examples-htmls []},
  "js-str"
  {:description
   "Convert `s` to string using JavaScript's coercion behavior.\n\nEquivalent to `''+s` in JavaScript.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;js-str 23&#41;\n;;=&gt; &quot;23&quot;\n\n&#40;js-str #js {:foo 1}&#41;\n;;=&gt; &quot;&#91;Object object&#93;&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "js-str",
   :signature ["[s]"],
   :type "macro",
   :related ["cljs.core/str"],
   :examples-strings [["(js-str 23)" "(js-str #js {:foo 1})"]],
   :examples
   [{:id "e92009",
     :content
     "```clj\n(js-str 23)\n;;=> \"23\"\n\n(js-str #js {:foo 1})\n;;=> \"[Object object]\"\n```"}],
   :full-name "cljs.core/js-str"},
  "RangedIterator"
  {:ns "cljs.core",
   :name "RangedIterator",
   :signature ["[i base arr v start end]"],
   :type "type",
   :full-name "cljs.core/RangedIterator",
   :examples-strings [],
   :examples-htmls []},
  "IStack"
  {:ns "cljs.core",
   :name "IStack",
   :type "protocol",
   :full-name "cljs.core/IStack",
   :docstring
   "Protocol for collections to provide access to their items as stacks. The top\n  of the stack should be accessed in the most efficient way for the different\n  data structures.",
   :examples-strings [],
   :examples-htmls []},
  "TransientArrayMap"
  {:ns "cljs.core",
   :name "TransientArrayMap",
   :signature ["[editable? len arr]"],
   :type "type",
   :full-name "cljs.core/TransientArrayMap",
   :examples-strings [],
   :examples-htmls []},
  "every?"
  {:description
   "Returns true if `(pred x)` is logical true for every `x` in `coll`, else false.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "every?",
   :signature ["[pred coll]"],
   :type "function",
   :related ["cljs.core/some" "cljs.core/not-any?"],
   :examples-strings [],
   :full-name "cljs.core/every?",
   :docstring
   "Returns true if (pred x) is logical true for every x in coll, else\nfalse."},
  "type->str"
  {:ns "cljs.core",
   :name "type->str",
   :signature ["[ty]"],
   :type "function",
   :full-name "cljs.core/type->str",
   :examples-strings [],
   :examples-htmls []},
  "print-map"
  {:ns "cljs.core",
   :name "print-map",
   :signature ["[m print-one writer opts]"],
   :type "function",
   :full-name "cljs.core/print-map",
   :examples-strings [],
   :examples-htmls []},
  "with-redefs"
  {:ns "cljs.core",
   :name "with-redefs",
   :signature ["[bindings & body]"],
   :type "macro",
   :full-name "cljs.core/with-redefs",
   :docstring
   "binding => var-symbol temp-value-expr\n\nTemporarily redefines vars while executing the body.  The\ntemp-value-exprs will be evaluated and each resulting value will\nreplace in parallel the root value of its var.  After the body is\nexecuted, the root values of all the vars will be set back to their\nold values. Useful for mocking out functions during testing.",
   :examples-strings [],
   :examples-htmls []},
  "EmptyList"
  {:ns "cljs.core",
   :name "EmptyList",
   :signature ["[meta]"],
   :type "type",
   :full-name "cljs.core/EmptyList",
   :examples-strings [],
   :examples-htmls []},
  "-"
  {:description
   "If no `y`s are supplied, returns the negation of `x`, else subtracts the `y`s\nfrom `x` and returns the result.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;- 1&#41;\n;;=&gt; -1\n\n&#40;- 6 3&#41;\n;;=&gt; 3\n\n&#40;- 10 3 2&#41;\n;;=&gt; 5\n</code></pre>"],
   :ns "cljs.core",
   :name "-",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/+"],
   :examples-strings [["(- 1)" "(- 6 3)" "(- 10 3 2)"]],
   :examples
   [{:id "0a974e",
     :content
     "```clj\n(- 1)\n;;=> -1\n\n(- 6 3)\n;;=> 3\n\n(- 10 3 2)\n;;=> 5\n```"}],
   :full-name "cljs.core/-",
   :docstring
   "If no ys are supplied, returns the negation of x, else subtracts\nthe ys from x and returns the result."},
  "ArrayNode"
  {:ns "cljs.core",
   :name "ArrayNode",
   :signature ["[edit cnt arr]"],
   :type "type",
   :full-name "cljs.core/ArrayNode",
   :examples-strings [],
   :examples-htmls []},
  "descendants"
  {:ns "cljs.core",
   :name "descendants",
   :signature ["[tag]" "[h tag]"],
   :type "function",
   :related
   ["cljs.core/ancestors"
    "cljs.core/isa?"
    "cljs.core/make-hierarchy"
    "cljs.core/derive"],
   :full-name "cljs.core/descendants",
   :docstring
   "Returns the immediate and indirect children of tag, through a\nrelationship established via derive. h must be a hierarchy obtained\nfrom make-hierarchy, if not supplied defaults to the global\nhierarchy. Note: does not work on JavaScript type inheritance\nrelationships.",
   :examples-strings [],
   :examples-htmls []},
  "key"
  {:description "Returns the key of the map entry.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "key",
   :signature ["[map-entry]"],
   :type "function",
   :related ["cljs.core/keys"],
   :examples-strings [],
   :full-name "cljs.core/key",
   :docstring "Returns the key of the map entry."},
  "hash-unordered-coll"
  {:ns "cljs.core",
   :name "hash-unordered-coll",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/hash-unordered-coll",
   :docstring
   "Returns the hash code, consistent with =, for an external unordered\ncollection implementing Iterable. For maps, the iterator should\nreturn map entries whose hash is computed as\n  (hash-ordered-coll [k v]).\nSee http://clojure.org/data_structures#hash for full algorithms.",
   :examples-strings [],
   :examples-htmls []},
  "repeat"
  {:description
   "Returns a lazy sequence of `x`s.\n\nThe length of the sequence is infinite, or `n` if provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "repeat",
   :signature ["[x]" "[n x]"],
   :type "function",
   :related
   ["cljs.core/repeatedly"
    "cljs.core/cycle"
    "cljs.core/constantly"
    "cljs.core/dotimes"],
   :examples-strings [],
   :full-name "cljs.core/repeat",
   :docstring
   "Returns a lazy (infinite!, or length n if supplied) sequence of xs."},
  "*print-fn*"
  {:ns "cljs.core",
   :name "*print-fn*",
   :type "dynamic var",
   :full-name "cljs.core/*print-fn*",
   :docstring
   "Each runtime environment provides a different way to print output.\nWhatever function *print-fn* is bound to will be passed any\nStrings which should be printed.",
   :examples-strings [],
   :examples-htmls []},
  "LazyTransformer"
  {:ns "cljs.core",
   :name "LazyTransformer",
   :signature ["[stepper first rest meta]"],
   :type "type",
   :full-name "cljs.core/LazyTransformer",
   :examples-strings [],
   :examples-htmls []},
  "set-print-err-fn!"
  {:ns "cljs.core",
   :name "set-print-err-fn!",
   :signature ["[f]"],
   :type "function",
   :full-name "cljs.core/set-print-err-fn!",
   :docstring "Set *print-err-fn* to f.",
   :examples-strings [],
   :examples-htmls []},
  "dissoc!"
  {:ns "cljs.core",
   :name "dissoc!",
   :signature ["[tcoll key]" "[tcoll key & ks]"],
   :type "function",
   :full-name "cljs.core/dissoc!",
   :docstring
   "Returns a transient map that doesn't contain a mapping for key(s).",
   :examples-strings [],
   :examples-htmls []},
  "Iterator"
  {:ns "cljs.core",
   :name "Iterator",
   :signature ["[s]"],
   :type "type",
   :full-name "cljs.core/Iterator",
   :examples-strings [],
   :examples-htmls []},
  "methods"
  {:ns "cljs.core",
   :name "methods",
   :signature ["[multifn]"],
   :type "function",
   :full-name "cljs.core/methods",
   :docstring
   "Given a multimethod, returns a map of dispatch values -> dispatch fns",
   :examples-strings [],
   :examples-htmls []},
  "dedupe"
  {:ns "cljs.core",
   :name "dedupe",
   :signature ["[]" "[coll]"],
   :type "function",
   :full-name "cljs.core/dedupe",
   :docstring
   "Returns a lazy sequence removing consecutive duplicates in coll.\nReturns a transducer when no collection is provided.",
   :examples-strings [],
   :examples-htmls []},
  "*assert*"
  {:ns "cljs.core",
   :name "*assert*",
   :type "dynamic var",
   :full-name "cljs.core/*assert*",
   :examples-strings [],
   :examples-htmls []},
  "vary-meta"
  {:description
   "Returns an object of the same type and value as `obj`, with\n`(apply f (meta obj) args)` as its metadata.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a &#94;:foo &#91;1 2 3&#93;&#41;\n&#40;def b &#40;vary-meta a assoc :bar true&#41;&#41;\n\n&#40;= a b&#41;\n;;=&gt; true\n\n&#40;meta a&#41;\n;;=&gt; {:foo true}\n\n&#40;meta b&#41;\n;;=&gt; {:foo true, :bar true}\n</code></pre>"],
   :ns "cljs.core",
   :name "vary-meta",
   :signature ["[obj f & args]"],
   :type "function",
   :related ["cljs.core/alter-meta!" "cljs.core/with-meta"],
   :examples-strings
   [["(def a ^:foo [1 2 3])"
     "(def b (vary-meta a assoc :bar true))"
     "(= a b)"
     "(meta a)"
     "(meta b)"]],
   :examples
   [{:id "8cca62",
     :content
     "```clj\n(def a ^:foo [1 2 3])\n(def b (vary-meta a assoc :bar true))\n\n(= a b)\n;;=> true\n\n(meta a)\n;;=> {:foo true}\n\n(meta b)\n;;=> {:foo true, :bar true}\n```"}],
   :full-name "cljs.core/vary-meta",
   :docstring
   "Returns an object of the same type and value as obj, with\n(apply f (meta obj) args) as its metadata."},
  "true?"
  {:description
   "Returns true if `x` is the value true, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "true?",
   :signature ["[x]"],
   :type "function/macro",
   :related ["cljs.core/false?"],
   :examples-strings [],
   :full-name "cljs.core/true?",
   :docstring "Returns true if x is the value true, false otherwise."},
  "second"
  {:description
   "Returns the second item in `coll`.\n\nSame as `(first (next coll))`",
   :examples-htmls [],
   :ns "cljs.core",
   :name "second",
   :signature ["[coll]"],
   :type "function",
   :related
   ["cljs.core/first"
    "cljs.core/nth"
    "cljs.core/fnext"
    "cljs.core/next"],
   :examples-strings [],
   :full-name "cljs.core/second",
   :docstring "Same as (first (next x))"},
  "m3-hash-unencoded-chars"
  {:ns "cljs.core",
   :name "m3-hash-unencoded-chars",
   :signature ["[in]"],
   :type "function",
   :full-name "cljs.core/m3-hash-unencoded-chars",
   :examples-strings [],
   :examples-htmls []},
  "ISwap"
  {:ns "cljs.core",
   :name "ISwap",
   :type "protocol",
   :full-name "cljs.core/ISwap",
   :docstring "Protocol for adding swapping functionality.",
   :examples-strings [],
   :examples-htmls []},
  "iterate"
  {:description
   "Returns a lazy sequence of `x`, `(f x)`, `(f (f x))` etc.\n\n`f` must be free of side-effects.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "iterate",
   :signature ["[f x]"],
   :type "function",
   :related
   ["cljs.core/cycle" "cljs.core/repeatedly" "cljs.core/repeat"],
   :examples-strings [],
   :full-name "cljs.core/iterate",
   :docstring
   "Returns a lazy sequence of x, (f x), (f (f x)) etc. f must be free of side-effects"},
  "force"
  {:ns "cljs.core",
   :name "force",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/force",
   :docstring
   "If x is a Delay, returns the (possibly cached) value of its expression, else returns x",
   :examples-strings [],
   :examples-htmls []},
  "PersistentHashMap.EMPTY"
  {:ns "cljs.core",
   :name "PersistentHashMap.EMPTY",
   :type "var",
   :full-name "cljs.core/PersistentHashMap.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "chunk-append"
  {:ns "cljs.core",
   :name "chunk-append",
   :signature ["[b x]"],
   :type "function",
   :full-name "cljs.core/chunk-append",
   :examples-strings [],
   :examples-htmls []},
  "copy-arguments"
  {:ns "cljs.core",
   :name "copy-arguments",
   :signature ["[dest]"],
   :type "macro",
   :full-name "cljs.core/copy-arguments",
   :examples-strings [],
   :examples-htmls []},
  "hash-combine"
  {:ns "cljs.core",
   :name "hash-combine",
   :signature ["[seed hash]"],
   :type "function",
   :full-name "cljs.core/hash-combine",
   :examples-strings [],
   :examples-htmls []},
  "persistent!"
  {:ns "cljs.core",
   :name "persistent!",
   :signature ["[tcoll]"],
   :type "function",
   :full-name "cljs.core/persistent!",
   :docstring
   "Returns a new, persistent version of the transient collection, in\nconstant time. The transient collection cannot be used after this\ncall, any such use will throw an exception.",
   :examples-strings [],
   :examples-htmls []},
  "ObjMap"
  {:ns "cljs.core",
   :name "ObjMap",
   :signature ["[meta keys strobj update-count __hash]"],
   :type "type",
   :full-name "cljs.core/ObjMap",
   :examples-strings [],
   :examples-htmls []},
  "ObjMap.EMPTY"
  {:ns "cljs.core",
   :name "ObjMap.EMPTY",
   :type "var",
   :full-name "cljs.core/ObjMap.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "chunk-cons"
  {:ns "cljs.core",
   :name "chunk-cons",
   :signature ["[chunk rest]"],
   :type "function",
   :full-name "cljs.core/chunk-cons",
   :examples-strings [],
   :examples-htmls []},
  "false?"
  {:description
   "Returns true if `x` is the value false, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "false?",
   :signature ["[x]"],
   :type "function/macro",
   :related ["cljs.core/true?" "cljs.core/not"],
   :examples-strings [],
   :full-name "cljs.core/false?",
   :docstring
   "Returns true if x is the value false, false otherwise."},
  "IEmptyableCollection"
  {:ns "cljs.core",
   :name "IEmptyableCollection",
   :type "protocol",
   :full-name "cljs.core/IEmptyableCollection",
   :docstring "Protocol for creating an empty collection.",
   :examples-strings [],
   :examples-htmls []},
  "max"
  {:description "Returns the greatest number argument.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "max",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/min" "cljs.core/max-key"],
   :examples-strings [],
   :full-name "cljs.core/max",
   :docstring "Returns the greatest of the nums."},
  "try*"
  {:ns "special",
   :name "try*",
   :type "special form",
   :full-name "special/try*",
   :examples-strings [],
   :examples-htmls []},
  "MetaFn"
  {:ns "cljs.core",
   :name "MetaFn",
   :signature ["[afn meta]"],
   :type "type",
   :full-name "cljs.core/MetaFn",
   :examples-strings [],
   :examples-htmls []},
  "m3-mix-K1"
  {:ns "cljs.core",
   :name "m3-mix-K1",
   :signature ["[k1]"],
   :type "function",
   :full-name "cljs.core/m3-mix-K1",
   :examples-strings [],
   :examples-htmls []},
  "ISet"
  {:ns "cljs.core",
   :name "ISet",
   :type "protocol",
   :full-name "cljs.core/ISet",
   :docstring "Protocol for adding set functionality to a collection.",
   :examples-strings [],
   :examples-htmls []},
  "cycle"
  {:description
   "Returns an infinite lazy sequence of repetitions of the items in `coll`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "cycle",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/lazy-seq" "cljs.core/repeatedly"],
   :examples-strings [],
   :full-name "cljs.core/cycle",
   :docstring
   "Returns a lazy (infinite!) sequence of repetitions of the items in coll."},
  "lazy-cat"
  {:description
   "Expands to code which yields a lazy sequence of the concatenation of the\nsupplied collections. Each collections expression is not evaluated until it is\nneeded.\n\n<table class=\"code-tbl-9bef6\">\n  <thead>\n    <tr>\n      <th>Code</th>\n      <th>Expands To</th></tr></thead>\n  <tbody>\n    <tr>\n      <td><code>(lazy-cat x y z)</code>\n      <td><pre>\n(concat (lazy-seq x)\n        (lazy-seq y)\n        (lazy-seq z))</pre></td></tr></tbody></table>",
   :examples-htmls [],
   :ns "cljs.core",
   :name "lazy-cat",
   :signature ["[& colls]"],
   :type "macro",
   :related ["cljs.core/lazy-seq" "cljs.core/concat"],
   :examples-strings [],
   :full-name "cljs.core/lazy-cat",
   :docstring
   "Expands to code which yields a lazy sequence of the concatenation\nof the supplied colls.  Each coll expr is not evaluated until it is\nneeded.\n\n(lazy-cat xs ys zs) === (concat (lazy-seq xs) (lazy-seq ys) (lazy-seq zs))"},
  "print-meta?"
  {:ns "cljs.core",
   :name "print-meta?",
   :signature ["[opts obj]"],
   :type "function",
   :full-name "cljs.core/print-meta?",
   :examples-strings [],
   :examples-htmls []},
  "get-in"
  {:description
   "Returns the value in a nested associative structure, where `ks` is a sequence of\nkeys.\n\nReturns nil if the key is not found, or `not-found` if supplied.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "get-in",
   :signature ["[m ks]" "[m ks not-found]"],
   :type "function",
   :related
   ["cljs.core/assoc-in"
    "cljs.core/update-in"
    "cljs.core/find"
    "cljs.core/get"],
   :examples-strings [],
   :full-name "cljs.core/get-in",
   :docstring
   "Returns the value in a nested associative structure,\nwhere ks is a sequence of keys. Returns nil if the key is not present,\nor the not-found value if supplied."},
  "new"
  {:ns "special",
   :name "new",
   :signature ["[Constructor. args*]" "[Constructor args*]"],
   :type "special form",
   :full-name "special/new",
   :docstring
   "The args, if any, are evaluated from left to right, and\npassed to the JavaScript constructor. The constructed object is\nreturned.",
   :examples-strings [],
   :examples-htmls []},
  "dispatch-fn"
  {:ns "cljs.core",
   :name "dispatch-fn",
   :signature ["[multifn]"],
   :type "function",
   :full-name "cljs.core/dispatch-fn",
   :docstring "Given a multimethod, return it's dispatch-fn.",
   :examples-strings [],
   :examples-htmls []},
  "LazyTransformer.create"
  {:ns "cljs.core",
   :name "LazyTransformer.create",
   :signature ["[xform coll]"],
   :type "function",
   :full-name "cljs.core/LazyTransformer.create",
   :examples-strings [],
   :examples-htmls []},
  "pr-sequential"
  {:ns "cljs.core",
   :name "pr-sequential",
   :signature ["[print-one begin sep end opts coll]"],
   :type "function",
   :full-name "cljs.core/pr-sequential",
   :docstring
   "Do not use this.  It is kept for backwards compatibility with the\nold IPrintable protocol.",
   :examples-strings [],
   :examples-htmls []},
  "nth"
  {:description
   "Returns the value at index `n` or `not-found` if the index is out of bounds.\n\n`nth` will throw an exception if `n` is out of bounds and `not-found` is not\nsupplied.\n\n`nth` works for Strings, Arrays, Regex Matchers, Lists, and Sequences. For\nSequences, `nth` takes O(n) time.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "nth",
   :signature ["[coll n]" "[coll n not-found]"],
   :type "function",
   :related
   ["cljs.core/first"
    "cljs.core/second"
    "cljs.core/nthnext"
    "cljs.core/get"],
   :examples-strings [],
   :full-name "cljs.core/nth",
   :docstring
   "Returns the value at the index. get returns nil if index out of\nbounds, nth throws an exception unless not-found is supplied.  nth\nalso works for strings, arrays, regex Matchers and Lists, and,\nin O(n) time, for sequences."},
  "ArrayChunk"
  {:ns "cljs.core",
   :name "ArrayChunk",
   :signature ["[arr off end]"],
   :type "type",
   :full-name "cljs.core/ArrayChunk",
   :examples-strings [],
   :examples-htmls []},
  "BlackNode"
  {:ns "cljs.core",
   :name "BlackNode",
   :signature ["[key val left right __hash]"],
   :type "type",
   :full-name "cljs.core/BlackNode",
   :examples-strings [],
   :examples-htmls []},
  "regexp?"
  {:ns "cljs.core",
   :name "regexp?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/regexp?",
   :docstring "Returns true if x is a JavaScript RegExp instance.",
   :examples-strings [],
   :examples-htmls []},
  "add-to-string-hash-cache"
  {:ns "cljs.core",
   :name "add-to-string-hash-cache",
   :signature ["[k]"],
   :type "function",
   :full-name "cljs.core/add-to-string-hash-cache",
   :examples-strings [],
   :examples-htmls []},
  "ICounted"
  {:ns "cljs.core",
   :name "ICounted",
   :type "protocol",
   :full-name "cljs.core/ICounted",
   :docstring
   "Protocol for adding the ability to count a collection in constant time.",
   :examples-strings [],
   :examples-htmls []},
  "*loaded-libs*"
  {:ns "cljs.core",
   :name "*loaded-libs*",
   :type "dynamic var",
   :full-name "cljs.core/*loaded-libs*",
   :examples-strings [],
   :examples-htmls []},
  "compare-and-set!"
  {:description
   "Atomically sets the value of atom `a` to `newval` if and only if the current\nvalue of the atom is identical to `oldval`.\n\nReturns true if set happened, false otherwise.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a &#40;atom &quot;abc&quot;&#41;&#41;\n\n&#40;compare-and-set! a &quot;abc&quot; &quot;def&quot;&#41;\n;;=&gt; true\n\n@a\n;;=&gt; &quot;def&quot;\n\n&#40;compare-and-set! a &quot;abc&quot; &quot;def&quot;&#41;\n;;=&gt; false\n\n@a\n;;=&gt; &quot;def&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "compare-and-set!",
   :signature ["[a oldval newval]"],
   :type "function",
   :related ["cljs.core/atom" "cljs.core/reset!" "cljs.core/swap!"],
   :examples-strings
   [["(def a (atom \"abc\"))"
     "(compare-and-set! a \"abc\" \"def\")"
     "@a"
     "(compare-and-set! a \"abc\" \"def\")"
     "@a"]],
   :examples
   [{:id "1fa306",
     :content
     "```clj\n(def a (atom \"abc\"))\n\n(compare-and-set! a \"abc\" \"def\")\n;;=> true\n\n@a\n;;=> \"def\"\n\n(compare-and-set! a \"abc\" \"def\")\n;;=> false\n\n@a\n;;=> \"def\"\n```"}],
   :full-name "cljs.core/compare-and-set!",
   :docstring
   "Atomically sets the value of atom to newval if and only if the\ncurrent value of the atom is equal to oldval. Returns true if\nset happened, else false."},
  "set!"
  {:description
   "Sets `js-var` to `val` using the JavaScript `=` operator.",
   :examples-htmls [],
   :ns "special",
   :name "set!",
   :signature ["[js-var val]"],
   :type "special form",
   :related ["cljs.core/aset" "cljs.core/reset!"],
   :examples-strings [],
   :full-name "special/set!",
   :docstring "Used to set vars and JavaScript object fields"},
  "comp"
  {:description
   "Takes a set of functions and returns a function that is the composition\nof those functions.\n\nThe returned function takes a variable number of arguments, applies the\nrightmost of `fns` to the arguments, whose result is subsequently applied to\nthe next left function, and so on.\n\n`((comp a b c) x y)` = `(a (b (c x y)))`",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def f &#40;comp str inc +&#41;&#41;\n&#40;f 1 2 3&#41;\n;;=&gt; &quot;7&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "comp",
   :signature ["[& fns]"],
   :type "function",
   :related ["cljs.core/partial" "cljs.core/juxt"],
   :examples-strings [["(def f (comp str inc +))" "(f 1 2 3)"]],
   :examples
   [{:id "5d3250",
     :content
     "```clj\n(def f (comp str inc +))\n(f 1 2 3)\n;;=> \"7\"\n```"}],
   :full-name "cljs.core/comp",
   :docstring
   "Takes a set of functions and returns a fn that is the composition\nof those fns.  The returned fn takes a variable number of args,\napplies the rightmost of fns to the args, the next\nfn (right-to-left) to the result, etc."},
  "partition"
  {:description
   "Returns a lazy sequence of lists of `n` items each, at offsets `step` apart.\n\nIf `step` is not supplied, defaults to `n`, i.e. the partitions do not overlap.\n\nIf a `pad` collection is supplied, its elements will be used as necessary to\ncomplete the last partition up to `n` items.\n\nReturns a partition with less than `n` items if there are not enough padding\nelements.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "partition",
   :signature ["[n coll]" "[n step coll]" "[n step pad coll]"],
   :type "function",
   :related
   ["cljs.core/partition-all"
    "cljs.core/split-at"
    "cljs.core/partition-by"],
   :examples-strings [],
   :full-name "cljs.core/partition",
   :docstring
   "Returns a lazy sequence of lists of n items each, at offsets step\napart. If step is not supplied, defaults to n, i.e. the partitions\ndo not overlap. If a pad collection is supplied, use its elements as\nnecessary to complete last partition up to n items. In case there are\nnot enough padding elements, return a partition with less than n items."},
  "fn"
  {:description
   "Defines a function.\n\n`name?` is an optional name of the function to be used inside `body`. This is\nuseful for recursive calls. Note that `name?` in `fn` is not the same as the\n`name` argument to `defn`, which defines a global symbol for the function.\n\n`params*` are the arguments to the function and a binding form for the symbols\nthat the arguments will take inside the body of the function. Functions can have\narity of 0-20 and there is no runtime enforcement of arity when calling a\nfunction (just like in JavaScript).\n\n`prepost-map?` is an optional map with optional keys `:pre` and `:post` that\ncontain collections of [pre or post conditions](http://blog.fogus.me/2009/12/21/clojures-pre-and-post/)\nfor the function.\n\n`body` is a series of expressions that execute when the function is called. The\narguments to the function are mapped to symbols in `params*` and are available\nin `body`. The value of the last expression in `body` is the return value of\ncalling the function.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "fn",
   :signature
   ["[name? [params*] prepost-map? body]"
    "[name? ([params*] prepost-map? body)+]"],
   :type "macro",
   :related ["cljs.core/defn" "cljs.core/defn-"],
   :examples-strings [],
   :full-name "cljs.core/fn",
   :docstring
   "params => positional-params* , or positional-params* & next-param\npositional-param => binding-form\nnext-param => binding-form\nname => symbol\n\nDefines a function"},
  "flush"
  {:ns "cljs.core",
   :name "flush",
   :signature ["[]"],
   :type "function",
   :full-name "cljs.core/flush",
   :examples-strings [],
   :examples-htmls []},
  "tagged-literal?"
  {:ns "cljs.core",
   :name "tagged-literal?",
   :signature ["[value]"],
   :type "function",
   :full-name "cljs.core/tagged-literal?",
   :docstring
   "Return true if the value is the data representation of a tagged literal",
   :examples-strings [],
   :examples-htmls []},
  "println-str"
  {:ns "cljs.core",
   :name "println-str",
   :signature ["[& objs]"],
   :type "function",
   :full-name "cljs.core/println-str",
   :docstring "println to a string, returning it",
   :examples-strings [],
   :examples-htmls []},
  "munge"
  {:ns "cljs.core",
   :name "munge",
   :signature ["[name]"],
   :type "function",
   :full-name "cljs.core/munge",
   :examples-strings [],
   :examples-htmls []},
  "PersistentTreeMap.EMPTY"
  {:ns "cljs.core",
   :name "PersistentTreeMap.EMPTY",
   :type "var",
   :full-name "cljs.core/PersistentTreeMap.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "PersistentVector"
  {:ns "cljs.core",
   :name "PersistentVector",
   :signature ["[meta cnt shift root tail __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentVector",
   :examples-strings [],
   :examples-htmls []},
  "cloneable?"
  {:ns "cljs.core",
   :name "cloneable?",
   :signature ["[value]"],
   :type "function",
   :full-name "cljs.core/cloneable?",
   :docstring "Return true if x implements ICloneable protocol.",
   :examples-strings [],
   :examples-htmls []},
  "for"
  {:description
   "List comprehension.\n\nTakes a vector of one or more binding-form/collection-expr pairs, each followed\nby zero or more modifiers, and yields a lazy sequence of evaluations of expr.\n\nCollections are iterated in a nested fashion, rightmost fastest, and nested\ncoll-exprs can refer to bindings created in prior binding-forms. Supported\nmodifiers are: `:let [binding-form expr ...]`, `:while test`, `:when test`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "for",
   :signature ["[seq-exprs body-expr]"],
   :type "macro",
   :related ["cljs.core/doseq" "cljs.core/doall" "special/recur"],
   :examples-strings [],
   :full-name "cljs.core/for",
   :docstring
   "List comprehension. Takes a vector of one or more\n binding-form/collection-expr pairs, each followed by zero or more\n modifiers, and yields a lazy sequence of evaluations of expr.\n Collections are iterated in a nested fashion, rightmost fastest,\n and nested coll-exprs can refer to bindings created in prior\n binding-forms.  Supported modifiers are: :let [binding-form expr ...],\n :while test, :when test.\n\n(take 100 (for [x (range 100000000) y (range 1000000) :while (< y x)]  [x y]))"},
  "shuffle"
  {:description "Returns a random permutation of `coll`.",
   :ns "cljs.core",
   :name "shuffle",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/shuffle",
   :docstring "Return a random permutation of coll",
   :examples-strings [],
   :examples-htmls []},
  "deftype*"
  {:ns "special",
   :name "deftype*",
   :type "special form",
   :full-name "special/deftype*",
   :examples-strings [],
   :examples-htmls []},
  "defrecord*"
  {:ns "special",
   :name "defrecord*",
   :type "special form",
   :full-name "special/defrecord*",
   :examples-strings [],
   :examples-htmls []},
  "bit-xor"
  {:description
   "Bitwise \"exclusive or\". Same as `x ^ y` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-xor 2r1100 2r1010&#41;\n;;=&gt; 6\n;; 6 = 2r0110\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-xor 12 10&#41;\n;;=&gt; 6\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-xor",
   :signature ["[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/bit-and" "cljs.core/bit-or"],
   :examples-strings [["(bit-xor 2r1100 2r1010)" "(bit-xor 12 10)"]],
   :examples
   [{:id "3ccd99",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-xor 2r1100 2r1010)\n;;=> 6\n;; 6 = 2r0110\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-xor 12 10)\n;;=> 6\n```"}],
   :full-name "cljs.core/bit-xor",
   :docstring "Bitwise exclusive or"},
  "defrecord"
  {:ns "cljs.core",
   :name "defrecord",
   :signature ["[rsym fields & impls]"],
   :type "macro",
   :full-name "cljs.core/defrecord",
   :docstring
   "(defrecord name [fields*]  options* specs*)\n\nCurrently there are no options.\n\nEach spec consists of a protocol or interface name followed by zero\nor more method bodies:\n\nprotocol-or-Object\n(methodName [args*] body)*\n\nThe record will have the (immutable) fields named by\nfields, which can have type hints. Protocols and methods\nare optional. The only methods that can be supplied are those\ndeclared in the protocols.  Note that method bodies are\nnot closures, the local environment includes only the named fields,\nand those fields can be accessed directly.\n\nMethod definitions take the form:\n\n(methodname [args*] body)\n\nThe argument and return types can be hinted on the arg and\nmethodname symbols. If not supplied, they will be inferred, so type\nhints should be reserved for disambiguation.\n\nMethods should be supplied for all methods of the desired\nprotocol(s). You can also define overrides for\nmethods of Object. Note that a parameter must be supplied to\ncorrespond to the target object ('this' in JavaScript parlance). Note also\nthat recur calls to the method head should *not* pass the target object, it\nwill be supplied automatically and can not be substituted.\n\nIn the method bodies, the (unqualified) name can be used to name the\nclass (for calls to new, instance? etc).\n\nThe type will have implementations of several ClojureScript\nprotocol generated automatically: IMeta/IWithMeta (metadata support) and\nIMap, etc.\n\nIn addition, defrecord will define type-and-value-based =,\nand will define ClojureScript IHash and IEquiv.\n\nTwo constructors will be defined, one taking the designated fields\nfollowed by a metadata map (nil for none) and an extension field\nmap (nil for none), and one taking only the fields (using nil for\nmeta and extension fields). Note that the field names __meta\nand __extmap are currently reserved and should not be used when\ndefining your own records.\n\nGiven (defrecord TypeName ...), two factory functions will be\ndefined: ->TypeName, taking positional parameters for the fields,\nand map->TypeName, taking a map of keywords to field values.",
   :examples-strings [],
   :examples-htmls []},
  "*out*"
  {:ns "cljs.core",
   :name "*out*",
   :type "dynamic var",
   :full-name "cljs.core/*out*",
   :examples-strings [],
   :examples-htmls []},
  "eduction"
  {:ns "cljs.core",
   :name "eduction",
   :signature ["[xform* coll]"],
   :type "function",
   :full-name "cljs.core/eduction",
   :docstring
   "Returns a reducible/iterable application of the transducers\nto the items in coll. Transducers are applied in order as if\ncombined with comp. Note that these applications will be\nperformed every time reduce/iterator is called.",
   :examples-strings [],
   :examples-htmls []},
  "ChunkBuffer"
  {:ns "cljs.core",
   :name "ChunkBuffer",
   :signature ["[buf end]"],
   :type "type",
   :full-name "cljs.core/ChunkBuffer",
   :examples-strings [],
   :examples-htmls []},
  "with-meta"
  {:description
   "Returns an object of the same type and value as `obj`, with map `m` as its\nmetadata.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a &#94;:foo &#91;1 2 3&#93;&#41;\n&#40;def b &#40;with-meta a {:bar true}&#41;&#41;\n\n&#40;= a b&#41;\n;;=&gt; true\n\n&#40;meta a&#41;\n;;=&gt; {:foo true}\n\n&#40;meta b&#41;\n;;=&gt; {:bar true}\n</code></pre>"],
   :ns "cljs.core",
   :name "with-meta",
   :signature ["[obj m]"],
   :type "function",
   :related ["cljs.core/alter-meta!" "cljs.core/vary-meta"],
   :examples-strings
   [["(def a ^:foo [1 2 3])"
     "(def b (with-meta a {:bar true}))"
     "(= a b)"
     "(meta a)"
     "(meta b)"]],
   :examples
   [{:id "f189d4",
     :content
     "```clj\n(def a ^:foo [1 2 3])\n(def b (with-meta a {:bar true}))\n\n(= a b)\n;;=> true\n\n(meta a)\n;;=> {:foo true}\n\n(meta b)\n;;=> {:bar true}\n```"}],
   :full-name "cljs.core/with-meta",
   :docstring
   "Returns an object of the same type and value as obj, with\nmap m as its metadata."},
  "catch"
  {:description
   "`catch` should be used inside of a `try` expression.\n\n`exception-type` should be the type of exception thrown (usually `js/Error` or\n`js/Object`). When there is a match, the thrown exception will be bound to\n`name` inside of `expr*` and `expr*` will be evaluated and returned as the value\nof the `try` expression.\n\nSince JavaScript allows you to throw anything, `exception-type` can be set to\n`:default` to catch all types of exceptions.",
   :examples-htmls [],
   :ns "special",
   :name "catch",
   :signature ["[exception-type name expr*]"],
   :type "special form",
   :related ["special/try" "special/finally" "special/throw"],
   :examples-strings [],
   :full-name "special/catch",
   :docstring
   "catch-clause => (catch classname name expr*)\nfinally-clause => (finally expr*)\nCatches and handles JavaScript exceptions."},
  "prn-str-with-opts"
  {:ns "cljs.core",
   :name "prn-str-with-opts",
   :signature ["[objs opts]"],
   :type "function",
   :full-name "cljs.core/prn-str-with-opts",
   :docstring "Same as pr-str-with-opts followed by (newline)",
   :examples-strings [],
   :examples-htmls []},
  "aclone"
  {:description
   "Creates a clone of the given JavaScript array `arr`.  The result is a new\nJavaScript array, which is a shallow copy, not a deep copy.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a #js &#91;1 2 3&#93;&#41;\n&#40;def b &#40;aclone a&#41;&#41;\n&#40;aset b 0 4&#41;\n\na\n;;=&gt; #js &#91;1 2 3&#93;\n\nb\n;;=&gt; #js &#91;4 2 3&#93;\n</code></pre>"],
   :ns "cljs.core",
   :name "aclone",
   :signature ["[arr]"],
   :type "function",
   :related ["cljs.core/array" "cljs.core/make-array"],
   :examples-strings
   [["(def a #js [1 2 3])"
     "(def b (aclone a))"
     "(aset b 0 4)"
     "a\nb"]],
   :examples
   [{:id "422c4e",
     :content
     "```clj\n(def a #js [1 2 3])\n(def b (aclone a))\n(aset b 0 4)\n\na\n;;=> #js [1 2 3]\n\nb\n;;=> #js [4 2 3]\n```"}],
   :full-name "cljs.core/aclone",
   :docstring
   "Returns a javascript array, cloned from the passed in array"},
  "take"
  {:description
   "Returns a lazy sequence of the first `n` items in `coll`. Returns all the items\nif there are fewer than `n`.\n\nReturns a stateful transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "take",
   :signature ["[n]" "[n coll]"],
   :type "function",
   :related
   ["cljs.core/drop"
    "cljs.core/take-while"
    "cljs.core/take-last"
    "cljs.core/take-nth"],
   :examples-strings [],
   :full-name "cljs.core/take",
   :docstring
   "Returns a lazy sequence of the first n items in coll, or all items if\nthere are fewer than n.  Returns a stateful transducer when\nno collection is provided."},
  "ValSeq"
  {:ns "cljs.core",
   :name "ValSeq",
   :signature ["[mseq _meta]"],
   :type "type",
   :full-name "cljs.core/ValSeq",
   :examples-strings [],
   :examples-htmls []},
  "ITransientSet"
  {:ns "cljs.core",
   :name "ITransientSet",
   :type "protocol",
   :full-name "cljs.core/ITransientSet",
   :docstring
   "Protocol for adding set functionality to a transient collection.",
   :examples-strings [],
   :examples-htmls []},
  "rest"
  {:description
   "Returns a possibly empty sequence of the items after the first item.\n\nCalls `seq` on its argument.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;rest &#91;1 2 3&#93;&#41;\n;;=&gt; &#40;2 3&#41;\n\n&#40;rest &#91;1 2&#93;&#41;\n;;=&gt; &#40;2&#41;\n\n&#40;rest &#91;1&#93;&#41;\n;;=&gt; &#40;&#41;\n\n&#40;rest &#91;&#93;&#41;\n;;=&gt; &#40;&#41;\n</code></pre>"],
   :ns "cljs.core",
   :name "rest",
   :signature ["[coll]"],
   :type "function",
   :related
   ["cljs.core/next"
    "cljs.core/first"
    "cljs.core/drop"
    "cljs.core/pop"],
   :examples-strings
   [["(rest [1 2 3])" "(rest [1 2])" "(rest [1])" "(rest [])"]],
   :examples
   [{:id "0869af",
     :content
     "```clj\n(rest [1 2 3])\n;;=> (2 3)\n\n(rest [1 2])\n;;=> (2)\n\n(rest [1])\n;;=> ()\n\n(rest [])\n;;=> ()\n```"}],
   :full-name "cljs.core/rest",
   :docstring
   "Returns a possibly empty seq of the items after the first. Calls seq on its\nargument."},
  "unsafe-cast"
  {:ns "cljs.core",
   :name "unsafe-cast",
   :signature ["[t x]"],
   :type "macro",
   :full-name "cljs.core/unsafe-cast",
   :docstring
   "EXPERIMENTAL: Subject to change. Unsafely cast a value to a different type.",
   :examples-strings [],
   :examples-htmls []},
  "identical?"
  {:description
   "Returns true if `x` and `y` are the same object, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "identical?",
   :signature ["[x y]"],
   :type "function/macro",
   :related ["cljs.core/=" "cljs.core/=="],
   :examples-strings [],
   :full-name "cljs.core/identical?",
   :docstring "Tests if 2 arguments are the same object"},
  "volatile?"
  {:ns "cljs.core",
   :name "volatile?",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/volatile!"],
   :full-name "cljs.core/volatile?",
   :docstring "Returns true if x is a volatile.",
   :examples-strings [],
   :examples-htmls []},
  "reduce-kv"
  {:description
   "Reduces an associative collection.\n\n`f` should be a function of 3 arguments. Returns the result of applying `f` to\n`init`, the first key and the first value in `coll`, then applying `f` to that\nresult and the 2nd key and value, etc.\n\nIf `coll` contains no entries, returns `init` and `f` is not called.\n\nNote that `reduce-kv` is supported on vectors, where the keys will be the\nordinals.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "reduce-kv",
   :signature ["[f init coll]"],
   :type "function",
   :related ["cljs.core/reduce"],
   :examples-strings [],
   :full-name "cljs.core/reduce-kv",
   :docstring
   "Reduces an associative collection. f should be a function of 3\narguments. Returns the result of applying f to init, the first key\nand the first value in coll, then applying f to that result and the\n2nd key and value, etc. If coll contains no entries, returns init\nand f is not called. Note that reduce-kv is supported on vectors,\nwhere the keys will be the ordinals."},
  "key->js"
  {:ns "cljs.core",
   :name "key->js",
   :signature ["[k]"],
   :type "function",
   :full-name "cljs.core/key->js",
   :examples-strings [],
   :examples-htmls []},
  "merge-with"
  {:description
   "Returns a map that consists of the rest of the maps `conj`-ed onto the first.\n\nIf a key occurs in more than one map, the mapping(s) from the latter (left-to-\nright) will be combined with the mapping in the result by calling `(f val-in-\nresult val-in-latter)`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "merge-with",
   :signature ["[f & maps]"],
   :type "function",
   :related ["cljs.core/merge"],
   :examples-strings [],
   :full-name "cljs.core/merge-with",
   :docstring
   "Returns a map that consists of the rest of the maps conj-ed onto\nthe first.  If a key occurs in more than one map, the mapping(s)\nfrom the latter (left-to-right) will be combined with the mapping in\nthe result by calling (f val-in-result val-in-latter)."},
  "count"
  {:description
   "Returns the number of items in `x`.\n\n`count` works on arrays, lists, maps, sets, strings, and vectors.\n\n`(count nil)` returns 0.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;count &#91;1 2 3&#93;&#41;\n;;=&gt; 3\n\n&#40;count &#91;&#93;&#41;\n;;=&gt; 0\n\n&#40;count nil&#41;\n;;=&gt; 0\n\n&#40;count #{:a :b}&#41;\n;;=&gt; 2\n\n&#40;count {:key &quot;value&quot; :key2 &quot;value2&quot;}&#41;\n;;=&gt; 2\n</code></pre>"],
   :ns "cljs.core",
   :name "count",
   :signature ["[x]"],
   :type "function",
   :examples-strings
   [["(count [1 2 3])"
     "(count [])"
     "(count nil)"
     "(count #{:a :b})"
     "(count {:key \"value\" :key2 \"value2\"})"]],
   :examples
   [{:id "96e470",
     :content
     "```clj\n(count [1 2 3])\n;;=> 3\n\n(count [])\n;;=> 0\n\n(count nil)\n;;=> 0\n\n(count #{:a :b})\n;;=> 2\n\n(count {:key \"value\" :key2 \"value2\"})\n;;=> 2\n```"}],
   :full-name "cljs.core/count",
   :docstring
   "Returns the number of items in the collection. (count nil) returns\n0.  Also works on strings, arrays, and Maps"},
  "find-macros-ns"
  {:ns "cljs.core",
   :name "find-macros-ns",
   :signature ["[ns]"],
   :type "function",
   :full-name "cljs.core/find-macros-ns",
   :examples-strings [],
   :examples-htmls []},
  "persistent-array-map-seq"
  {:ns "cljs.core",
   :name "persistent-array-map-seq",
   :signature ["[arr i _meta]"],
   :type "function",
   :full-name "cljs.core/persistent-array-map-seq",
   :examples-strings [],
   :examples-htmls []},
  "update"
  {:ns "cljs.core",
   :name "update",
   :signature
   ["[m k f]"
    "[m k f x]"
    "[m k f x y]"
    "[m k f x y z]"
    "[m k f x y z & more]"],
   :type "function",
   :full-name "cljs.core/update",
   :docstring
   "'Updates' a value in an associative structure, where k is a\nkey and f is a function that will take the old value\nand any supplied args and return the new value, and returns a new\nstructure.  If the key does not exist, nil is passed as the old value.",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-float"
  {:ns "cljs.core",
   :name "unchecked-float",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-float",
   :examples-strings [],
   :examples-htmls []},
  "derive"
  {:ns "cljs.core",
   :name "derive",
   :signature ["[tag parent]" "[h tag parent]"],
   :type "function",
   :related
   ["cljs.core/ancestors"
    "cljs.core/descendants"
    "cljs.core/isa?"
    "cljs.core/make-hierarchy"],
   :full-name "cljs.core/derive",
   :docstring
   "Establishes a parent/child relationship between parent and\ntag. Parent must be a namespace-qualified symbol or keyword and\nchild can be either a namespace-qualified symbol or keyword or a\nclass. h must be a hierarchy obtained from make-hierarchy, if not\nsupplied defaults to, and modifies, the global hierarchy.",
   :examples-strings [],
   :examples-htmls []},
  "dorun"
  {:description
   "Forces evaluation of a lazy sequence. Often used to see the effects of a\nsequence produced via functions that have side effects.\n\n`dorun` walks through the successive `next`s of the sequence and returns nil.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "dorun",
   :signature ["[coll]" "[n coll]"],
   :type "function",
   :related ["cljs.core/doall"],
   :examples-strings [],
   :full-name "cljs.core/dorun",
   :docstring
   "When lazy sequences are produced via functions that have side\neffects, any effects other than those needed to produce the first\nelement in the seq do not occur until the seq is consumed. dorun can\nbe used to force any effects. Walks through the successive nexts of\nthe seq, does not retain the head and returns nil."},
  "empty?"
  {:description
   "Returns true if `coll` has no items - same as `(not (seq coll))`.\n\nPlease use the idiom `(seq x)` rather than `(not (empty? x))`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "empty?",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/seq"],
   :examples-strings [],
   :full-name "cljs.core/empty?",
   :docstring
   "Returns true if coll has no items - same as (not (seq coll)).\nPlease use the idiom (seq x) rather than (not (empty? x))"},
  "short"
  {:ns "cljs.core",
   :name "short",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/short",
   :examples-strings [],
   :examples-htmls []},
  "to-array"
  {:ns "cljs.core",
   :name "to-array",
   :signature ["[s]"],
   :type "function",
   :full-name "cljs.core/to-array",
   :docstring "Naive impl of to-array as a start.",
   :examples-strings [],
   :examples-htmls []},
  "letfn*"
  {:ns "special",
   :name "letfn*",
   :type "special form",
   :full-name "special/letfn*",
   :examples-strings [],
   :examples-htmls []},
  "array?"
  {:description
   "Returns true if `x` is a JavaScript array, false otherwise.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;array? #js &#91;1 2 3&#93;&#41;\n;;=&gt; true\n\n&#40;array? &#91;1 2 3&#93;&#41;\n;;=&gt; false\n\n&#40;array? &quot;hi&quot;&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "array?",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/object?"],
   :examples-strings
   [["(array? #js [1 2 3])" "(array? [1 2 3])" "(array? \"hi\")"]],
   :examples
   [{:id "39913c",
     :content
     "```clj\n(array? #js [1 2 3])\n;;=> true\n\n(array? [1 2 3])\n;;=> false\n\n(array? \"hi\")\n;;=> false\n```"}],
   :full-name "cljs.core/array?",
   :docstring "Returns true if x is a JavaScript array."},
  "cons"
  {:description
   "Returns a new sequence where `x` is the first element and `coll` is the rest.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;cons 1 &#40;list 1 2 3&#41;&#41;\n;;=&gt; &#40;1 1 2 3&#41;\n\n&#40;cons 1 &#91;1 2 3&#93;&#41;\n;;=&gt; &#40;1 1 2 3&#41;\n\n&#40;cons 1 nil&#41;\n;;=&gt; &#40;1&#41;\n\n&#40;cons nil nil&#41;\n;;=&gt; &#40;nil&#41;\n</code></pre>"],
   :ns "cljs.core",
   :name "cons",
   :signature ["[x coll]"],
   :type "function",
   :related ["cljs.core/conj"],
   :examples-strings
   [["(cons 1 (list 1 2 3))"
     "(cons 1 [1 2 3])"
     "(cons 1 nil)"
     "(cons nil nil)"]],
   :examples
   [{:id "68c769",
     :content
     "```clj\n(cons 1 (list 1 2 3))\n;;=> (1 1 2 3)\n\n(cons 1 [1 2 3])\n;;=> (1 1 2 3)\n\n(cons 1 nil)\n;;=> (1)\n\n(cons nil nil)\n;;=> (nil)\n```"}],
   :full-name "cljs.core/cons",
   :docstring
   "Returns a new seq where x is the first element and seq is the rest."},
  "swap!"
  {:description
   "Atomically swaps the value of atom to be: `(apply f current-value-of-atom\nargs)`\n\nNote that `f` may be called multiple times, and thus should be free of side\neffects.\n\nReturns the value that was swapped in.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "swap!",
   :signature ["[a f]" "[a f x]" "[a f x y]" "[a f x y & more]"],
   :type "function",
   :related ["cljs.core/atom" "cljs.core/reset!"],
   :examples-strings [],
   :full-name "cljs.core/swap!",
   :docstring
   "Atomically swaps the value of atom to be:\n(apply f current-value-of-atom args). Note that f may be called\nmultiple times, and thus should be free of side effects.  Returns\nthe value that was swapped in."},
  "rand-int"
  {:description
   "Returns a random integer between 0 inclusive and `n` exclusive.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "rand-int",
   :signature ["[n]"],
   :type "function",
   :related ["cljs.core/rand"],
   :examples-strings [],
   :full-name "cljs.core/rand-int",
   :docstring
   "Returns a random integer between 0 (inclusive) and n (exclusive)."},
  "hash-keyword"
  {:ns "cljs.core",
   :name "hash-keyword",
   :signature ["[k]"],
   :type "function",
   :full-name "cljs.core/hash-keyword",
   :examples-strings [],
   :examples-htmls []},
  "sort"
  {:description
   "Returns a sorted sequence of the items in `coll`.\n\n`comp` can be a boolean-valued comparison funcion, or a -/0/+ valued comparator.\n\n`comp` defaults to `compare`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "sort",
   :signature ["[coll]" "[comp coll]"],
   :type "function",
   :related ["cljs.core/sort-by"],
   :examples-strings [],
   :full-name "cljs.core/sort",
   :docstring
   "Returns a sorted sequence of the items in coll. Comp can be\nboolean-valued comparison function, or a -/0/+ valued comparator.\nComp defaults to compare."},
  "IPending"
  {:ns "cljs.core",
   :name "IPending",
   :type "protocol",
   :full-name "cljs.core/IPending",
   :docstring
   "Protocol for types which can have a deferred realization. Currently only\n  implemented by Delay.",
   :examples-strings [],
   :examples-htmls []},
  "IReduce"
  {:ns "cljs.core",
   :name "IReduce",
   :type "protocol",
   :full-name "cljs.core/IReduce",
   :docstring
   "Protocol for seq types that can reduce themselves.\n  Called by cljs.core/reduce.",
   :examples-strings [],
   :examples-htmls []},
  "re-seq"
  {:description
   "Returns a lazy sequence of successive matches of regex `re` in string `s`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "re-seq",
   :signature ["[re s]"],
   :type "function",
   :related
   ["cljs.core/re-find"
    "cljs.core/re-pattern"
    "cljs.core/re-matches"
    "cljs.core/subs"
    "clojure.string/split"],
   :examples-strings [],
   :full-name "cljs.core/re-seq",
   :docstring
   "Returns a lazy sequence of successive matches of re in s."},
  "iterator"
  {:ns "cljs.core",
   :name "iterator",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/iterator",
   :examples-strings [],
   :examples-htmls []},
  "re-find"
  {:description
   "Returns the first regex match, if any, of `s` to `re`, using `re.exec(s)`.\n\nReturns a vector, containing first the matching substring, then any capturing\ngroups if the regular expression contains capturing groups.",
   :ns "cljs.core",
   :name "re-find",
   :signature ["[re s]"],
   :type "function",
   :full-name "cljs.core/re-find",
   :docstring
   "Returns the first regex match, if any, of s to re, using\nre.exec(s). Returns a vector, containing first the matching\nsubstring, then any capturing groups if the regular expression contains\ncapturing groups.",
   :examples-strings [],
   :examples-htmls []},
  "not-any?"
  {:description
   "Returns false if `(pred x)` is logical true for any `x` in `coll`, else true.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "not-any?",
   :signature ["[pred coll]"],
   :type "function",
   :related ["cljs.core/every?" "cljs.core/some"],
   :examples-strings [],
   :full-name "cljs.core/not-any?",
   :docstring
   "Returns false if (pred x) is logical true for any x in coll,\nelse true."},
  "SetEntriesIterator"
  {:ns "cljs.core",
   :name "SetEntriesIterator",
   :signature ["[s]"],
   :type "type",
   :full-name "cljs.core/SetEntriesIterator",
   :examples-strings [],
   :examples-htmls []},
  "PersistentTreeSet.EMPTY"
  {:ns "cljs.core",
   :name "PersistentTreeSet.EMPTY",
   :type "var",
   :full-name "cljs.core/PersistentTreeSet.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "ES6EntriesIterator"
  {:ns "cljs.core",
   :name "ES6EntriesIterator",
   :signature ["[s]"],
   :type "type",
   :full-name "cljs.core/ES6EntriesIterator",
   :examples-strings [],
   :examples-htmls []},
  "apply"
  {:description
   "Applies function `f` to the argument list formed by prepending intervening\narguments to `args`.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;max 1 2 3&#41;\n;;=&gt; 3\n\n&#40;apply max &#91;1 2 3&#93;&#41;\n;;=&gt; 3\n\n&#40;apply max 1 &#91;2 3&#93;&#41;\n;;=&gt; 3\n</code></pre>"],
   :ns "cljs.core",
   :name "apply",
   :signature
   ["[f args]"
    "[f x args]"
    "[f x y args]"
    "[f x y z args]"
    "[f a b c d & args]"],
   :type "function",
   :related ["cljs.core/map"],
   :examples-strings
   [["(max 1 2 3)" "(apply max [1 2 3])" "(apply max 1 [2 3])"]],
   :examples
   [{:id "174052",
     :content
     "```clj\n(max 1 2 3)\n;;=> 3\n\n(apply max [1 2 3])\n;;=> 3\n\n(apply max 1 [2 3])\n;;=> 3\n```"}],
   :full-name "cljs.core/apply",
   :docstring
   "Applies fn f to the argument list formed by prepending intervening arguments to args."},
  "frequencies"
  {:description
   "Returns a map from distinct items in `coll` to the number of times they appear.\n\n`(frequencies [:a :a :b])` => `{:a 2, :b 1}`",
   :examples-htmls [],
   :ns "cljs.core",
   :name "frequencies",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/group-by" "cljs.core/distinct"],
   :examples-strings [],
   :full-name "cljs.core/frequencies",
   :docstring
   "Returns a map from distinct items in coll to the number of times\nthey appear."},
  "memfn"
  {:ns "cljs.core",
   :name "memfn",
   :signature ["[name & args]"],
   :type "macro",
   :full-name "cljs.core/memfn",
   :docstring
   "Expands into code that creates a fn that expects to be passed an\nobject and any args and calls the named instance method on the\nobject passing the args. Use when you want to treat a Java method as\na first-class fn. name may be type-hinted with the method receiver's\ntype in order to avoid reflective calls.",
   :examples-strings [],
   :examples-htmls []},
  "array-index-of"
  {:ns "cljs.core",
   :name "array-index-of",
   :signature ["[arr k]"],
   :type "function",
   :full-name "cljs.core/array-index-of",
   :examples-strings [],
   :examples-htmls []},
  "ifn?"
  {:description
   "Returns true if `f` implements the `IFn` protocol, false otherwise.\n\nFunctions, keywords, map, sets, and vectors can be called as functions.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "ifn?",
   :signature ["[f]"],
   :type "function",
   :related ["cljs.core/fn?"],
   :examples-strings [],
   :full-name "cljs.core/ifn?",
   :docstring
   "Returns true if f returns true for fn? or satisfies IFn."},
  "ILookup"
  {:ns "cljs.core",
   :name "ILookup",
   :type "protocol",
   :full-name "cljs.core/ILookup",
   :docstring "Protocol for looking up a value in a data structure.",
   :examples-strings [],
   :examples-htmls []},
  "ArrayIter"
  {:ns "cljs.core",
   :name "ArrayIter",
   :signature ["[arr i]"],
   :type "type",
   :full-name "cljs.core/ArrayIter",
   :examples-strings [],
   :examples-htmls []},
  "partition-by"
  {:description
   "Applies `f` to each value in `coll`, splitting it each time `f` returns a new\nvalue. Returns a lazy sequence of partitions.\n\nReturns a stateful transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "partition-by",
   :signature ["[f]" "[f coll]"],
   :type "function",
   :related
   ["cljs.core/partition"
    "cljs.core/partition-all"
    "cljs.core/group-by"],
   :examples-strings [],
   :full-name "cljs.core/partition-by",
   :docstring
   "Applies f to each value in coll, splitting it each time f returns a\nnew value.  Returns a lazy seq of partitions.  Returns a stateful\ntransducer when no collection is provided."},
  "ChunkedCons"
  {:ns "cljs.core",
   :name "ChunkedCons",
   :signature ["[chunk more meta __hash]"],
   :type "type",
   :full-name "cljs.core/ChunkedCons",
   :examples-strings [],
   :examples-htmls []},
  "some?"
  {:description "Returns true if `x` is not nil, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "some?",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/true?" "cljs.core/nil?"],
   :examples-strings [],
   :full-name "cljs.core/some?",
   :docstring "Returns true if x is not nil, false otherwise."},
  "rem"
  {:description
   "Returns the remainder of dividing numerator `n` by denominator `d`.\n\nReturns `NaN` when `d` is 0 (divide by 0 error).",
   :examples-htmls [],
   :ns "cljs.core",
   :name "rem",
   :signature ["[n d]"],
   :type "function",
   :related ["cljs.core/quot" "cljs.core/mod"],
   :examples-strings [],
   :full-name "cljs.core/rem",
   :docstring "remainder of dividing numerator by denominator."},
  "*print-err-fn*"
  {:ns "cljs.core",
   :name "*print-err-fn*",
   :type "dynamic var",
   :full-name "cljs.core/*print-err-fn*",
   :docstring
   "Each runtime environment provides a different way to print error output.\nWhatever function *print-fn* is bound to will be passed any\nStrings which should be printed.",
   :examples-strings [],
   :examples-htmls []},
  "areduce"
  {:description
   "For quickly reducing an expression `expr` across a JavaScript array `a`.  The\nexpression can use `ret` as the current result, which is initialized to `init`.\nIt can also use `idx` to get the current index.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a #js &#91;1 2 3&#93;&#41;\n&#40;areduce a i ret 0 &#40;+ ret &#40;aget a i&#41;&#41;&#41;\n;;=&gt; 6\n</code></pre>"],
   :ns "cljs.core",
   :name "areduce",
   :signature ["[a idx ret init expr]"],
   :type "macro",
   :related ["cljs.core/reduce"],
   :examples-strings
   [["(def a #js [1 2 3])" "(areduce a i ret 0 (+ ret (aget a i)))"]],
   :examples
   [{:id "20a389",
     :content
     "```clj\n(def a #js [1 2 3])\n(areduce a i ret 0 (+ ret (aget a i)))\n;;=> 6\n```"}],
   :full-name "cljs.core/areduce",
   :docstring
   "Reduces an expression across an array a, using an index named idx,\nand return value named ret, initialized to init, setting ret to the\nevaluation of expr at each step, returning ret."},
  "transient"
  {:ns "cljs.core",
   :name "transient",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/transient",
   :docstring
   "Returns a new, transient version of the collection, in constant time.",
   :examples-strings [],
   :examples-htmls []},
  "*1"
  {:description
   "Only usable from a REPL.\n\nHolds the result of the last expression.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;+ 1 2 3 4&#41;\n;;=&gt; 10\n\n&#42;1\n;;=&gt; 10\n\n&#40;inc &#42;1&#41;\n;;=&gt; 11\n</code></pre><p>Note that a standalone evaluation of <code>&#42;1</code>, <code>&#42;2</code>, <code>&#42;3</code>, or <code>&#42;e</code> is not a part of remembered history:</p><pre><code class=\"clj\">:first\n;;=&gt; :first\n\n:second\n;;=&gt; :second\n\n:third\n;;=&gt; :third\n\n&#42;3\n;;=&gt; :first\n\n&#42;2\n;;=&gt; :second\n\n&#42;1\n;;=&gt; :third\n</code></pre>"],
   :ns "cljs.core",
   :name "*1",
   :type "var",
   :related ["cljs.core/*2" "cljs.core/*3" "cljs.core/*e"],
   :examples-strings
   [["(+ 1 2 3 4)"
     "*1"
     "(inc *1)"
     ":first\n:second\n:third\n*3\n*2\n*1"]],
   :examples
   [{:id "30a861",
     :content
     "```clj\n(+ 1 2 3 4)\n;;=> 10\n\n*1\n;;=> 10\n\n(inc *1)\n;;=> 11\n```\n\nNote that a standalone evaluation of `*1`, `*2`, `*3`, or `*e` is not a part of\nremembered history:\n\n```clj\n:first\n;;=> :first\n\n:second\n;;=> :second\n\n:third\n;;=> :third\n\n*3\n;;=> :first\n\n*2\n;;=> :second\n\n*1\n;;=> :third\n```"}],
   :full-name "cljs.core/*1",
   :docstring
   "bound in a repl thread to the most recent value printed"},
  "sorted-map"
  {:description
   "Returns a new sorted map with supplied mappings.\n\n`keyvals` must be an even number of forms.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "sorted-map",
   :signature ["[& keyvals]"],
   :type "function",
   :related
   ["cljs.core/sorted-map-by"
    "cljs.core/subseq"
    "cljs.core/rsubseq"
    "cljs.core/sorted-set"
    "cljs.core/array-map"
    "cljs.core/hash-map"],
   :examples-strings [],
   :full-name "cljs.core/sorted-map",
   :docstring
   "keyval => key val\nReturns a new sorted map with supplied mappings."},
  "ES6Iterator"
  {:ns "cljs.core",
   :name "ES6Iterator",
   :signature ["[s]"],
   :type "type",
   :full-name "cljs.core/ES6Iterator",
   :examples-strings [],
   :examples-htmls []},
  "Range"
  {:ns "cljs.core",
   :name "Range",
   :signature ["[meta start end step __hash]"],
   :type "type",
   :full-name "cljs.core/Range",
   :examples-strings [],
   :examples-htmls []},
  "PersistentQueueSeq"
  {:ns "cljs.core",
   :name "PersistentQueueSeq",
   :signature ["[meta front rear __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentQueueSeq",
   :examples-strings [],
   :examples-htmls []},
  "completing"
  {:ns "cljs.core",
   :name "completing",
   :signature ["[f]" "[f cf]"],
   :type "function",
   :full-name "cljs.core/completing",
   :docstring
   "Takes a reducing function f of 2 args and returns a fn suitable for\ntransduce by adding an arity-1 signature that calls cf (default -\nidentity) on the result argument.",
   :examples-strings [],
   :examples-htmls []},
  "Box"
  {:ns "cljs.core",
   :name "Box",
   :signature ["[val]"],
   :type "type",
   :full-name "cljs.core/Box",
   :examples-strings [],
   :examples-htmls []},
  "distinct"
  {:description
   "Returns a lazy sequence of the elements of `coll` with duplicates removed.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "distinct",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/distinct?"],
   :examples-strings [],
   :full-name "cljs.core/distinct",
   :docstring
   "Returns a lazy sequence of the elements of coll with duplicates removed.\nReturns a stateful transducer when no collection is provided."},
  "chunk"
  {:ns "cljs.core",
   :name "chunk",
   :signature ["[b]"],
   :type "function",
   :full-name "cljs.core/chunk",
   :examples-strings [],
   :examples-htmls []},
  "symbol-identical?"
  {:ns "cljs.core",
   :name "symbol-identical?",
   :signature ["[x y]"],
   :type "function",
   :full-name "cljs.core/symbol-identical?",
   :docstring
   "Efficient test to determine that two symbol are identical.",
   :examples-strings [],
   :examples-htmls []},
  "ISequential"
  {:ns "cljs.core",
   :name "ISequential",
   :type "protocol",
   :full-name "cljs.core/ISequential",
   :docstring
   "Marker interface indicating a persistent collection of sequential items",
   :examples-strings [],
   :examples-htmls []},
  "es6-iterator-seq"
  {:ns "cljs.core",
   :name "es6-iterator-seq",
   :signature ["[iter]"],
   :type "function",
   :full-name "cljs.core/es6-iterator-seq",
   :docstring
   "EXPERIMENTAL: Given an ES2015 compatible iterator return a seq.",
   :examples-strings [],
   :examples-htmls []},
  "PersistentQueue.EMPTY"
  {:ns "cljs.core",
   :name "PersistentQueue.EMPTY",
   :type "var",
   :full-name "cljs.core/PersistentQueue.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "parents"
  {:ns "cljs.core",
   :name "parents",
   :signature ["[tag]" "[h tag]"],
   :type "function",
   :full-name "cljs.core/parents",
   :docstring
   "Returns the immediate parents of tag, either via a JavaScript type\ninheritance relationship or a relationship established via derive. h\nmust be a hierarchy obtained from make-hierarchy, if not supplied\ndefaults to the global hierarchy",
   :examples-strings [],
   :examples-htmls []},
  "PersistentArrayMap.fromArray"
  {:ns "cljs.core",
   :name "PersistentArrayMap.fromArray",
   :signature ["[arr no-clone no-check]"],
   :type "function",
   :full-name "cljs.core/PersistentArrayMap.fromArray",
   :examples-strings [],
   :examples-htmls []},
  "bit-and"
  {:description "Bitwise \"and\".  Same as `x & y` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-and 2r1100 2r1010&#41;\n;;=&gt; 8\n;; 8 = 2r1000\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-and 12 10&#41;\n;;=&gt; 8\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-and",
   :signature ["[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/bit-or"],
   :examples-strings [["(bit-and 2r1100 2r1010)" "(bit-and 12 10)"]],
   :examples
   [{:id "3c0470",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-and 2r1100 2r1010)\n;;=> 8\n;; 8 = 2r1000\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-and 12 10)\n;;=> 8\n```"}],
   :full-name "cljs.core/bit-and",
   :docstring "Bitwise and"},
  "name"
  {:description
   "Returns the name string of a possibly namespace-qualified keyword or symbol.\n\nEquivalent to [doc:cljs.core/identity] for strings.",
   :examples-htmls
   ["<p>With namespaces:</p><pre><code class=\"clj\">&#40;name :foo/bar&#41;\n;;=&gt; &quot;bar&quot;\n\n&#40;name 'foo/bar&#41;\n;;=&gt; &quot;bar&quot;\n</code></pre><p>Without namespaces:</p><pre><code class=\"clj\">&#40;name :foo&#41;\n;;=&gt; &quot;foo&quot;\n\n&#40;name 'foo&#41;\n;;=&gt; &quot;foo&quot;\n</code></pre><p>Strings have no concept of a namespace:</p><pre><code class=\"clj\">&#40;name &quot;foo/bar&quot;&#41;\n;;=&gt; &quot;foo/bar&quot;\n\n&#40;name &quot;foo&quot;&#41;\n;;=&gt; &quot;foo&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "name",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/namespace"],
   :examples-strings
   [["(name :foo/bar)"
     "(name 'foo/bar)"
     "(name :foo)"
     "(name 'foo)"
     "(name \"foo/bar\")"
     "(name \"foo\")"]],
   :examples
   [{:id "363fb7",
     :content
     "With namespaces:\n\n```clj\n(name :foo/bar)\n;;=> \"bar\"\n\n(name 'foo/bar)\n;;=> \"bar\"\n```\n\nWithout namespaces:\n\n```clj\n(name :foo)\n;;=> \"foo\"\n\n(name 'foo)\n;;=> \"foo\"\n```\n\nStrings have no concept of a namespace:\n\n```clj\n(name \"foo/bar\")\n;;=> \"foo/bar\"\n\n(name \"foo\")\n;;=> \"foo\"\n```"}],
   :full-name "cljs.core/name",
   :docstring
   "Returns the name String of a string, symbol or keyword."},
  "nil?"
  {:description "Returns true if `x` is nil, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "nil?",
   :signature ["[x]"],
   :type "function/macro",
   :related
   ["cljs.core/true?" "cljs.core/false?" "cljs.core/identity"],
   :examples-strings [],
   :full-name "cljs.core/nil?",
   :docstring "Returns true if x is nil, false otherwise."},
  "unchecked-add-int"
  {:ns "cljs.core",
   :name "unchecked-add-int",
   :signature ["[]" "[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-add-int",
   :docstring "Returns the sum of nums. (+) returns 0.",
   :examples-strings [],
   :examples-htmls []},
  "chunk-buffer"
  {:ns "cljs.core",
   :name "chunk-buffer",
   :signature ["[capacity]"],
   :type "function",
   :full-name "cljs.core/chunk-buffer",
   :examples-strings [],
   :examples-htmls []},
  "record?"
  {:ns "cljs.core",
   :name "record?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/record?",
   :docstring "Return true if x satisfies IRecord",
   :examples-strings [],
   :examples-htmls []},
  "underive"
  {:ns "cljs.core",
   :name "underive",
   :signature ["[tag parent]" "[h tag parent]"],
   :type "function",
   :full-name "cljs.core/underive",
   :docstring
   "Removes a parent/child relationship between parent and\ntag. h must be a hierarchy obtained from make-hierarchy, if not\nsupplied defaults to, and modifies, the global hierarchy.",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-dec"
  {:ns "cljs.core",
   :name "unchecked-dec",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-dec",
   :docstring "Returns a number one less than x, an int.",
   :examples-strings [],
   :examples-htmls []},
  "bit-and-not"
  {:description
   "Bitwise \"and\" `x` with bitwise \"not\" `y`.  Same as `x & ~y` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-and-not 2r1100 2r1010&#41;\n;;=&gt; 4\n;; 4 = 2r0100\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-and-not 12 10&#41;\n;;=&gt; 4\n</code></pre><p>Same result using <code>bit-and</code> and <code>bit-not</code>:</p><pre><code class=\"clj\">&#40;bit-and 12 &#40;bit-not 10&#41;&#41;\n;;=&gt; 4\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-and-not",
   :signature ["[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/bit-and" "cljs.core/bit-not"],
   :examples-strings
   [["(bit-and-not 2r1100 2r1010)"
     "(bit-and-not 12 10)"
     "(bit-and 12 (bit-not 10))"]],
   :examples
   [{:id "16f35d",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-and-not 2r1100 2r1010)\n;;=> 4\n;; 4 = 2r0100\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-and-not 12 10)\n;;=> 4\n```\n\nSame result using `bit-and` and `bit-not`:\n\n```clj\n(bit-and 12 (bit-not 10))\n;;=> 4\n```"}],
   :full-name "cljs.core/bit-and-not",
   :docstring "Bitwise and"},
  "map-indexed"
  {:description
   "Returns a lazy sequence consisting of the result of applying `f` to 0 and the\nfirst item of `coll`, followed by applying `f` to 1 and the second item in\n`coll`, etc, until `coll` is exhausted.\n\nFunction `f` should accept 2 arguments, index and item.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "map-indexed",
   :signature ["[f coll]"],
   :type "function",
   :related ["cljs.core/map" "cljs.core/keep-indexed"],
   :examples-strings [],
   :full-name "cljs.core/map-indexed",
   :docstring
   "Returns a lazy sequence consisting of the result of applying f to 0\nand the first item of coll, followed by applying f to 1 and the second\nitem in coll, etc, until coll is exhausted. Thus function f should\naccept 2 arguments, index and item. Returns a stateful transducer when\nno collection is provided."},
  "macroexpand-1"
  {:description
   "(only intended as a REPL utility)\n\nIf the given quoted form is a macro call, expand it once. NOTE: subforms are\n_not_ expanded.\n\nSee [doc:cljs.core/macroexpand] if you wish to repeatedly expand a form.",
   :examples-htmls
   ["<p>See how <code>&#40;-&gt; 2 inc&#41;</code> is progressively expanded:</p><pre><code class=\"clj\">&#40;macroexpand-1 '&#40;-&gt; 2 inc&#41;&#41;\n;;=&gt; &#40;inc 2&#41;\n\n&#40;macroexpand-1 '&#40;inc 2&#41;&#41;\n;;=&gt; &#40;cljs.core/+ 2 1&#41;\n\n&#40;macroexpand-1 '&#40;cljs.core/+ 2 1&#41;&#41;\n;;=&gt; &#40;js&#42; &quot;&#40;&#126;{} + &#126;{}&#41;&quot; 2 1&#41;\n</code></pre><p>Notice how the nested <code>inc</code> form is not expanded:</p><pre><code class=\"clj\">&#40;macroexpand-1 '&#40;inc &#40;inc 2&#41;&#41;&#41;\n;;=&gt; &#40;cljs.core/+ &#40;inc 2&#41; 1&#41;\n</code></pre>"],
   :ns "cljs.core",
   :name "macroexpand-1",
   :signature ["[quoted]"],
   :type "macro",
   :related ["cljs.core/macroexpand" "cljs.core/defmacro"],
   :examples-strings
   [["(macroexpand-1 '(-> 2 inc))"
     "(macroexpand-1 '(inc 2))"
     "(macroexpand-1 '(cljs.core/+ 2 1))"
     "(macroexpand-1 '(inc (inc 2)))"]],
   :examples
   [{:id "1bc6af",
     :content
     "See how `(-> 2 inc)` is progressively expanded:\n\n```clj\n(macroexpand-1 '(-> 2 inc))\n;;=> (inc 2)\n\n(macroexpand-1 '(inc 2))\n;;=> (cljs.core/+ 2 1)\n\n(macroexpand-1 '(cljs.core/+ 2 1))\n;;=> (js* \"(~{} + ~{})\" 2 1)\n```\n\nNotice how the nested `inc` form is not expanded:\n\n```clj\n(macroexpand-1 '(inc (inc 2)))\n;;=> (cljs.core/+ (inc 2) 1)\n```"}],
   :full-name "cljs.core/macroexpand-1",
   :docstring
   "If form represents a macro form, returns its expansion,\nelse returns form."},
  "ChunkedSeq"
  {:ns "cljs.core",
   :name "ChunkedSeq",
   :signature ["[vec node i off meta __hash]"],
   :type "type",
   :full-name "cljs.core/ChunkedSeq",
   :examples-strings [],
   :examples-htmls []},
  "unreduced"
  {:ns "cljs.core",
   :name "unreduced",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/unreduced",
   :docstring "If x is reduced?, returns (deref x), else returns x",
   :examples-strings [],
   :examples-htmls []},
  "split-at"
  {:description "Returns a vector of `[(take n coll) (drop n coll)]`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "split-at",
   :signature ["[n coll]"],
   :type "function",
   :related ["cljs.core/split-with" "clojure.string/split"],
   :examples-strings [],
   :full-name "cljs.core/split-at",
   :docstring "Returns a vector of [(take n coll) (drop n coll)]"},
  "counted?"
  {:description
   "Returns true if `x` executes `count` in constant time, false otherwise.\n\nLists, maps, sets, strings, and vectors can be counted in constant time.",
   :ns "cljs.core",
   :name "counted?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/counted?",
   :docstring "Returns true if coll implements count in constant time",
   :examples-strings [],
   :examples-htmls []},
  "replace"
  {:description
   "Given a map of replacement pairs `smap` and a vector/collection `coll`, returns\na vector/seq with any elements `=` to a key in `smap` replaced with the\ncorresponding val in `smap`.\n\nReturns a transducer when `coll` is not provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "replace",
   :signature ["[smap]" "[smap coll]"],
   :type "function",
   :related
   ["cljs.core/map"
    "clojure.walk/prewalk-replace"
    "clojure.walk/postwalk-replace"],
   :examples-strings [],
   :full-name "cljs.core/replace",
   :docstring
   "Given a map of replacement pairs and a vector/collection, returns a\nvector/seq with any elements = a key in smap replaced with the\ncorresponding val in smap.  Returns a transducer when no collection\nis provided."},
  "associative?"
  {:description
   "Returns true if `coll` implements the `IAssociative` protocol, false otherwise.\n\nMaps and vectors are associative.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;associative? &#91;1 2 3&#93;&#41;\n;;=&gt; true\n\n&#40;associative? {:a 1 :b 2}&#41;\n;;=&gt; true\n\n&#40;associative? #{1 2 3}&#41;\n;;=&gt; false\n\n&#40;associative? '&#40;1 2 3&#41;&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "associative?",
   :signature ["[coll]"],
   :type "function",
   :examples-strings
   [["(associative? [1 2 3])"
     "(associative? {:a 1 :b 2})"
     "(associative? #{1 2 3})"
     "(associative? '(1 2 3))"]],
   :examples
   [{:id "29a37f",
     :content
     "```clj\n(associative? [1 2 3])\n;;=> true\n\n(associative? {:a 1 :b 2})\n;;=> true\n\n(associative? #{1 2 3})\n;;=> false\n\n(associative? '(1 2 3))\n;;=> false\n```"}],
   :full-name "cljs.core/associative?",
   :docstring "Returns true if coll implements Associative"},
  "doubles"
  {:ns "cljs.core",
   :name "doubles",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/doubles",
   :examples-strings [],
   :examples-htmls []},
  "uuid"
  {:description
   "Creates a universally unique identifier (UUID) from the given string, using the\n[doc:cljs.core/UUID] type.\n\nThe string has an expected format `8-4-4-12` where the numbers represent the\nnumber of hex digits.  No validation is performed.\n\nTo create a UUID literal (parsed and validated at compile time), use [doc:syntax/uuid-literal].",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;uuid &quot;00000000-0000-0000-0000-000000000000&quot;&#41;\n;;=&gt; #uuid &quot;00000000-0000-0000-0000-000000000000&quot;\n\n&#40;uuid &quot;97bda55b-6175-4c39-9e04-7c0205c709dc&quot;&#41;\n;;=&gt; #uuid &quot;97bda55b-6175-4c39-9e04-7c0205c709dc&quot;\n</code></pre><p>No validation is performed:</p><pre><code class=\"clj\">&#40;uuid &quot;&quot;&#41;\n;;=&gt; #uuid &quot;&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "uuid",
   :signature ["[s]"],
   :type "function",
   :related ["cljs.core/random-uuid" "syntax/uuid-literal"],
   :examples-strings
   [["(uuid \"00000000-0000-0000-0000-000000000000\")"
     "(uuid \"97bda55b-6175-4c39-9e04-7c0205c709dc\")"
     "(uuid \"\")"]],
   :examples
   [{:id "d6491d",
     :content
     "```clj\n(uuid \"00000000-0000-0000-0000-000000000000\")\n;;=> #uuid \"00000000-0000-0000-0000-000000000000\"\n\n(uuid \"97bda55b-6175-4c39-9e04-7c0205c709dc\")\n;;=> #uuid \"97bda55b-6175-4c39-9e04-7c0205c709dc\"\n```\n\nNo validation is performed:\n\n```clj\n(uuid \"\")\n;;=> #uuid \"\"\n```"}],
   :full-name "cljs.core/uuid"},
  "cond"
  {:description
   "`clauses` must be an even number of forms, ie: `(cond t1 e1, t2 e2, t3 e3)`.\nEach test `t` is evaluated one at a time. If a test returns logical true, `cond`\nevaluates and returns the corresponding expression `e` and does not evaluate any\nof the other tests or expressions.\n\nIt is idiomatic to provide a default case as the last test pair using the\nkeyword `:else` (a keyword always evaluates to logical true).\n\n`(cond)` returns nil.",
   :examples-htmls
   ["<pre><code>&#40;def a 42&#41;\n&#40;cond\n  &#40;&lt; a 10&#41; &quot;a is less than 10&quot;\n  &#40;= a 10&#41; &quot;a is 10&quot;\n  &#40;&gt; a 10&#41; &quot;a is bigger than 10&quot;\n  :else &quot;a is not a number!&quot;&#41;\n;;=&gt; &quot;a is bigger than 10&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "cond",
   :signature ["[& clauses]"],
   :type "macro",
   :related ["cljs.core/condp" "cljs.core/case" "special/if"],
   :examples-strings
   [["(def a 42)"
     "(cond\n  (< a 10) \"a is less than 10\"\n  (= a 10) \"a is 10\"\n  (> a 10) \"a is bigger than 10\"\n  :else \"a is not a number!\")"]],
   :examples
   [{:id "0cc9ac",
     :content
     "```\n(def a 42)\n(cond\n  (< a 10) \"a is less than 10\"\n  (= a 10) \"a is 10\"\n  (> a 10) \"a is bigger than 10\"\n  :else \"a is not a number!\")\n;;=> \"a is bigger than 10\"\n```"}],
   :full-name "cljs.core/cond",
   :docstring
   "Takes a set of test/expr pairs. It evaluates each test one at a\ntime.  If a test returns logical true, cond evaluates and returns\nthe value of the corresponding expr and doesn't evaluate any of the\nother tests or exprs. (cond) returns nil."},
  "realized?"
  {:description
   "Returns true if a value has been produced for a lazy sequence.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "realized?",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/lazy-seq"],
   :examples-strings [],
   :full-name "cljs.core/realized?",
   :docstring
   "Returns true if a value has been produced for a delay or lazy sequence."},
  "Fn"
  {:ns "cljs.core",
   :name "Fn",
   :type "protocol",
   :full-name "cljs.core/Fn",
   :docstring "Marker protocol",
   :examples-strings [],
   :examples-htmls []},
  "PersistentVector.EMPTY"
  {:ns "cljs.core",
   :name "PersistentVector.EMPTY",
   :type "var",
   :full-name "cljs.core/PersistentVector.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "es6-entries-iterator"
  {:ns "cljs.core",
   :name "es6-entries-iterator",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/es6-entries-iterator",
   :examples-strings [],
   :examples-htmls []},
  "ISorted"
  {:ns "cljs.core",
   :name "ISorted",
   :type "protocol",
   :full-name "cljs.core/ISorted",
   :docstring
   "Protocol for a collection which can represent their items\n  in a sorted manner. ",
   :examples-strings [],
   :examples-htmls []},
  "sequence"
  {:ns "cljs.core",
   :name "sequence",
   :signature ["[coll]" "[xform coll]" "[xform coll & colls]"],
   :type "function",
   :full-name "cljs.core/sequence",
   :docstring
   "Coerces coll to a (possibly empty) sequence, if it is not already\none. Will not force a lazy seq. (sequence nil) yields (), When a\ntransducer is supplied, returns a lazy sequence of applications of\nthe transform to the items in coll(s), i.e. to the set of first\nitems of each coll, followed by the set of second\nitems in each coll, until any one of the colls is exhausted.  Any\nremaining items in other colls are ignored. The transform should accept\nnumber-of-colls arguments",
   :examples-strings [],
   :examples-htmls []},
  "ISeq"
  {:ns "cljs.core",
   :name "ISeq",
   :type "protocol",
   :full-name "cljs.core/ISeq",
   :docstring
   "Protocol for collections to provide access to their items as sequences.",
   :examples-strings [],
   :examples-htmls []},
  "obj-map"
  {:ns "cljs.core",
   :name "obj-map",
   :signature ["[& keyvals]"],
   :type "function",
   :full-name "cljs.core/obj-map",
   :docstring
   "keyval => key val\nReturns a new object map with supplied mappings.",
   :examples-strings [],
   :examples-htmls []},
  "if"
  {:description
   "If `test` is not false or nil, `then` is evaluated and returned. Otherwise,\n`else?` is evaluated and returned. `else?` defaults to nil if not provided.\n\n`if` is one of ClojureScript's [special forms](http://clojure.org/special_forms)\nand is a fundamental building block of the language. All other conditionals in\nClojureScript are based on `if`s notion of truthiness (ie: anything other than\nfalse or nil).",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def v &#91;1 2&#93;&#41;\n\n&#40;if &#40;empty? v&#41; &quot;empty!&quot; &quot;filled!&quot;&#41;\n;;=&gt; &quot;filled!&quot;\n\n&#40;str &quot;This vector is &quot;\n  &#40;if &#40;empty? v&#41; &quot;empty!&quot; &quot;filled!&quot;&#41;&#41;\n;;=&gt; &quot;This vector is filled!&quot;\n</code></pre>"],
   :ns "special",
   :name "if",
   :signature ["[test then else?]"],
   :type "special form",
   :related
   ["cljs.core/cond"
    "cljs.core/when"
    "cljs.core/if-let"
    "cljs.core/if-not"],
   :examples-strings
   [["(def v [1 2])"
     "(if (empty? v) \"empty!\" \"filled!\")"
     "(str \"This vector is \"\n  (if (empty? v) \"empty!\" \"filled!\"))"]],
   :examples
   [{:id "e591ff",
     :content
     "```clj\n(def v [1 2])\n\n(if (empty? v) \"empty!\" \"filled!\")\n;;=> \"filled!\"\n\n(str \"This vector is \"\n  (if (empty? v) \"empty!\" \"filled!\"))\n;;=> \"This vector is filled!\"\n```"}],
   :full-name "special/if",
   :docstring
   "Evaluates test. If not the singular values nil or false,\nevaluates and yields then, otherwise, evaluates and yields else. If\nelse is not supplied it defaults to nil."},
  "let"
  {:description
   "Binds expressions to symbols and makes those symbols available only within\n`body`.\n\n`bindings` should be a vector with an even number of forms, ie: `[a1 b1, a2 b2,\na3 b3]`. The first item in a pair (the `a`s) should be a symbol that is assigned\nthe evaluation of the second item (the `b`s). These symbols (the `a`s) are then\navailable within `body` (and not outside of `body`).\n\nAnother way to think about this is that the binding symbols in `let` are like\nlocal `def`s that are only available within `let`'s scope.\n\nIn addition to direct symbol binding, `let` supports a destructuring syntax to\n\"break apart\" collections into multiple symbols. This destructuring syntax is\nlike it's own [mini-language] and allows for succinct code.\n\n`let` is a wrapper over one of ClojureScript's [special forms] and is a\nfundamental building block of the language. Many macros rely on `let`s binding\nsyntax and scope rules.\n\n[mini-language]: http://blog.jayfields.com/2010/07/clojure-destructuring.html\n[special forms]: http://clojure.org/special_forms",
   :examples-htmls [],
   :ns "cljs.core",
   :name "let",
   :signature ["[bindings & body]"],
   :type "macro",
   :related ["cljs.core/letfn" "cljs.core/if-let"],
   :examples-strings [],
   :full-name "cljs.core/let",
   :docstring
   "binding => binding-form init-expr\n\nEvaluates the exprs in a lexical context in which the symbols in\nthe binding-forms are bound to their respective init-exprs or parts\ntherein."},
  "ArrayNodeSeq"
  {:ns "cljs.core",
   :name "ArrayNodeSeq",
   :signature ["[meta nodes i s __hash]"],
   :type "type",
   :full-name "cljs.core/ArrayNodeSeq",
   :examples-strings [],
   :examples-htmls []},
  "chunked-seq?"
  {:ns "cljs.core",
   :name "chunked-seq?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/chunked-seq?",
   :docstring "Return true if x is satisfies IChunkedSeq.",
   :examples-strings [],
   :examples-htmls []},
  "keyword-identical?"
  {:ns "cljs.core",
   :name "keyword-identical?",
   :signature ["[x y]"],
   :type "function",
   :full-name "cljs.core/keyword-identical?",
   :docstring
   "Efficient test to determine that two keywords are identical.",
   :examples-strings [],
   :examples-htmls []},
  "drop"
  {:description
   "Returns a lazy sequence of all but the first `n` items in `coll`.\n\nReturns a stateful transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "drop",
   :signature ["[n]" "[n coll]"],
   :type "function",
   :related
   ["cljs.core/take"
    "cljs.core/drop-last"
    "cljs.core/drop-while"
    "cljs.core/nthnext"
    "cljs.core/nthrest"],
   :examples-strings [],
   :full-name "cljs.core/drop",
   :docstring
   "Returns a lazy sequence of all but the first n items in coll.\nReturns a stateful transducer when no collection is provided."},
  "js-invoke"
  {:description
   "Invoke JavaScript object `obj` method via string `s`. Needed when the string is\nnot a valid unquoted property name.",
   :examples-htmls
   ["<p>If we have a JavaScript object with an unusual property name:</p><pre><code class=\"js\">// JavaScript\nvar obj = {\n  &quot;my sum&quot;: function&#40;a,b&#41; { return a+b; }\n};\n</code></pre><p>We can invoke it from ClojureScript:</p><pre><code class=\"clj\">&#40;js-invoke js/obj &quot;my sum&quot; 1 2&#41;\n;=&gt; 3\n</code></pre>"],
   :ns "cljs.core",
   :name "js-invoke",
   :signature ["[obj s & args]"],
   :type "function",
   :examples-strings [["(js-invoke js/obj \"my sum\" 1 2)"]],
   :examples
   [{:id "373cce",
     :content
     "If we have a JavaScript object with an unusual property name:\n\n```js\n// JavaScript\nvar obj = {\n  \"my sum\": function(a,b) { return a+b; }\n};\n```\n\nWe can invoke it from ClojureScript:\n\n```clj\n(js-invoke js/obj \"my sum\" 1 2)\n;=> 3\n```"}],
   :full-name "cljs.core/js-invoke",
   :docstring
   "Invoke JavaScript object method via string. Needed when the\nstring is not a valid unquoted property name."},
  "KeySeq"
  {:ns "cljs.core",
   :name "KeySeq",
   :signature ["[mseq _meta]"],
   :type "type",
   :full-name "cljs.core/KeySeq",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-inc"
  {:ns "cljs.core",
   :name "unchecked-inc",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-inc",
   :examples-strings [],
   :examples-htmls []},
  "List.EMPTY"
  {:ns "cljs.core",
   :name "List.EMPTY",
   :type "var",
   :full-name "cljs.core/List.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "reify"
  {:ns "cljs.core",
   :name "reify",
   :signature ["[& impls]"],
   :type "macro",
   :full-name "cljs.core/reify",
   :docstring
   "reify is a macro with the following structure:\n\n(reify options* specs*)\n\n Currently there are no options.\n\n Each spec consists of the protocol name followed by zero\n or more method bodies:\n\n protocol\n (methodName [args+] body)*\n\n Methods should be supplied for all methods of the desired\n protocol(s). You can also define overrides for Object methods. Note that\n the first parameter must be supplied to correspond to the target object\n ('this' in JavaScript parlance). Note also that recur calls\n to the method head should *not* pass the target object, it will be supplied\n automatically and can not be substituted.\n\n recur works to method heads The method bodies of reify are lexical\n closures, and can refer to the surrounding local scope:\n\n (str (let [f \"foo\"]\n      (reify Object\n        (toString [this] f))))\n == \"foo\"\n\n (seq (let [f \"foo\"]\n      (reify ISeqable\n        (-seq [this] (-seq f)))))\n == (\\f \\o \\o))\n\n reify always implements IMeta and IWithMeta and transfers meta\n data of the form to the created object.\n\n (meta ^{:k :v} (reify Object (toString [this] \"foo\")))\n == {:k :v}",
   :examples-strings [],
   :examples-htmls []},
  "vals"
  {:description "Returns a sequence of the values in `hash-map`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "vals",
   :signature ["[hash-map]"],
   :type "function",
   :related ["cljs.core/keys"],
   :examples-strings [],
   :full-name "cljs.core/vals",
   :docstring "Returns a sequence of the map's values."},
  "seq-iter"
  {:ns "cljs.core",
   :name "seq-iter",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/seq-iter",
   :examples-strings [],
   :examples-htmls []},
  "inc"
  {:description "Returns a number one greater than `x`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "inc",
   :signature ["[x]"],
   :type "function/macro",
   :related ["cljs.core/dec"],
   :examples-strings [],
   :full-name "cljs.core/inc",
   :docstring "Returns a number one greater than num."},
  "TransientVector"
  {:ns "cljs.core",
   :name "TransientVector",
   :signature ["[cnt shift root tail]"],
   :type "type",
   :full-name "cljs.core/TransientVector",
   :examples-strings [],
   :examples-htmls []},
  "Stepper"
  {:ns "cljs.core",
   :name "Stepper",
   :signature ["[xform iter]"],
   :type "type",
   :full-name "cljs.core/Stepper",
   :examples-strings [],
   :examples-htmls []},
  "sort-by"
  {:description
   "Returns a sorted sequence of the items in `coll`, where the sort order is\ndetermined by comparing `(keyfn item)`.\n\n`comp` can be boolean-valued comparison function, or a -/0/+ valued comparator.\n\n`comp` defaults to `compare`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "sort-by",
   :signature ["[keyfn coll]" "[keyfn comp coll]"],
   :type "function",
   :related ["cljs.core/sort" "cljs.core/compare"],
   :examples-strings [],
   :full-name "cljs.core/sort-by",
   :docstring
   "Returns a sorted sequence of the items in coll, where the sort\norder is determined by comparing (keyfn item).  Comp can be\nboolean-valued comparison funcion, or a -/0/+ valued comparator.\nComp defaults to compare."},
  "defmulti"
  {:ns "cljs.core",
   :name "defmulti",
   :signature ["[mm-name & options]"],
   :type "macro",
   :full-name "cljs.core/defmulti",
   :docstring
   "Creates a new multimethod with the associated dispatch function.\nThe docstring and attribute-map are optional.\n\nOptions are key-value pairs and may be one of:\n  :default    the default dispatch value, defaults to :default\n  :hierarchy  the isa? hierarchy to use for dispatching\n              defaults to the global hierarchy",
   :examples-strings [],
   :examples-htmls []},
  "chunk-first"
  {:ns "cljs.core",
   :name "chunk-first",
   :signature ["[s]"],
   :type "function",
   :full-name "cljs.core/chunk-first",
   :examples-strings [],
   :examples-htmls []},
  "when-let"
  {:description
   "When `test` is logical true, evaluates `body` with the value of `test` bound to\n`x`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "when-let",
   :signature ["[[x test] & body]"],
   :type "macro",
   :related
   ["cljs.core/if-let"
    "cljs.core/when"
    "cljs.core/when-not"
    "special/if"
    "cljs.core/when-first"],
   :examples-strings [],
   :full-name "cljs.core/when-let",
   :docstring
   "bindings => binding-form test\n\nWhen test is true, evaluates body with binding-form bound to the value of test"},
  "m3-C1"
  {:ns "cljs.core",
   :name "m3-C1",
   :type "var",
   :full-name "cljs.core/m3-C1",
   :examples-strings [],
   :examples-htmls []},
  "println"
  {:ns "cljs.core",
   :name "println",
   :signature ["[& objs]"],
   :type "function",
   :full-name "cljs.core/println",
   :docstring "Same as print followed by (newline)",
   :examples-strings [],
   :examples-htmls []},
  "ex-info"
  {:ns "cljs.core",
   :name "ex-info",
   :signature ["[msg data]" "[msg data cause]"],
   :type "function",
   :full-name "cljs.core/ex-info",
   :docstring
   "Alpha - subject to change.\nCreate an instance of ExceptionInfo, an Error type that carries a\nmap of additional data.",
   :examples-strings [],
   :examples-htmls []},
  "ns-unmap"
  {:ns "cljs.core",
   :name "ns-unmap",
   :signature ["[[quote0 ns] [quote1 sym]]"],
   :type "macro",
   :full-name "cljs.core/ns-unmap",
   :docstring
   "Removes the mappings for the symbol from the namespace.",
   :examples-strings [],
   :examples-htmls []},
  "char"
  {:description
   "Converts a number `x` to a character using `String.fromCharCode(x)` from\nJavaScript.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;char 81&#41;\n;;=&gt; &quot;Q&quot;\n\n&#40;char &quot;Q&quot;&#41;\n;;=&gt; &quot;Q&quot;\n\n&#40;char &quot;foo&quot;&#41;\n;; Error: Argument to char must be a character or number\n</code></pre>"],
   :ns "cljs.core",
   :name "char",
   :signature ["[x]"],
   :type "function",
   :examples-strings [["(char 81)" "(char \"Q\")" "(char \"foo\")"]],
   :examples
   [{:id "4e1a56",
     :content
     "```clj\n(char 81)\n;;=> \"Q\"\n\n(char \"Q\")\n;;=> \"Q\"\n\n(char \"foo\")\n;; Error: Argument to char must be a character or number\n```"}],
   :full-name "cljs.core/char",
   :docstring "Coerce to char"},
  "not="
  {:description
   "Returns the opposite of `=`.\n\nSame as `(not (= x y))`",
   :examples-htmls [],
   :ns "cljs.core",
   :name "not=",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function",
   :related ["cljs.core/=" "cljs.core/not"],
   :examples-strings [],
   :full-name "cljs.core/not=",
   :docstring "Same as (not (= obj1 obj2))"},
  "doto"
  {:ns "cljs.core",
   :name "doto",
   :signature ["[x & forms]"],
   :type "macro",
   :full-name "cljs.core/doto",
   :docstring
   "Evaluates x then calls all of the methods and functions with the\nvalue of x supplied at the front of the given arguments.  The forms\nare evaluated in order.  Returns x.\n\n(doto (new java.util.HashMap) (.put \"a\" 1) (.put \"b\" 2))",
   :examples-strings [],
   :examples-htmls []},
  "random-uuid"
  {:ns "cljs.core",
   :name "random-uuid",
   :signature ["[]"],
   :type "function",
   :full-name "cljs.core/random-uuid",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-char"
  {:ns "cljs.core",
   :name "unchecked-char",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-char",
   :examples-strings [],
   :examples-htmls []},
  "IndexedSeqIterator"
  {:ns "cljs.core",
   :name "IndexedSeqIterator",
   :signature ["[arr i]"],
   :type "type",
   :full-name "cljs.core/IndexedSeqIterator",
   :examples-strings [],
   :examples-htmls []},
  "ICollection"
  {:ns "cljs.core",
   :name "ICollection",
   :type "protocol",
   :full-name "cljs.core/ICollection",
   :docstring "Protocol for adding to a collection.",
   :examples-strings [],
   :examples-htmls []},
  "*target*"
  {:ns "cljs.core",
   :name "*target*",
   :type "dynamic var",
   :full-name "cljs.core/*target*",
   :docstring
   "Var bound to the name value of the compiler build :target option.\nFor example, if the compiler build :target is :nodejs, *target* will be bound\nto \"nodejs\". *target* is a Google Closure define and can be set by compiler\n:closure-defines option.",
   :examples-strings [],
   :examples-htmls []},
  "*ns*"
  {:ns "cljs.core",
   :name "*ns*",
   :type "dynamic var",
   :full-name "cljs.core/*ns*",
   :docstring
   "Var bound to the current namespace. Only used for bootstrapping.",
   :examples-strings [],
   :examples-htmls []},
  "atom"
  {:description
   "Creates and returns an atom with an initial value of `x`.\n\n`opts` is an optional map with optional keys `:meta` and `:validator`.\n\n`:meta` should be a [metadata-map](http://clojure.org/metadata) for the atom.\n\n`:validator` should be a validator function for the atom. See `set-validator!`\nfor more information.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a &#40;atom 1&#41;&#41;\n\n@a\n;;=&gt; 1\n\n&#40;reset! a 2&#41;\n@a\n;;=&gt; 2\n\n&#40;swap! a inc&#41;\n@a\n;;=&gt; 3\n</code></pre>"],
   :ns "cljs.core",
   :name "atom",
   :signature ["[x]" "[x opts]"],
   :type "function",
   :related
   ["cljs.core/atom"
    "cljs.core/swap!"
    "cljs.core/reset!"
    "cljs.core/set-validator!"
    "cljs.core/get-validator"],
   :examples-strings
   [["(def a (atom 1))"
     "@a"
     "(reset! a 2)"
     "@a"
     "(swap! a inc)"
     "@a"]],
   :examples
   [{:id "e6a38a",
     :content
     "```clj\n(def a (atom 1))\n\n@a\n;;=> 1\n\n(reset! a 2)\n@a\n;;=> 2\n\n(swap! a inc)\n@a\n;;=> 3\n```"}],
   :full-name "cljs.core/atom",
   :docstring
   "Creates and returns an Atom with an initial value of x and zero or\nmore options (in any order):\n\n:meta metadata-map\n\n:validator validate-fn\n\nIf metadata-map is supplied, it will be come the metadata on the\natom. validate-fn must be nil or a side-effect-free fn of one\nargument, which will be passed the intended new state on any state\nchange. If the new state is unacceptable, the validate-fn should\nreturn false or throw an Error.  If either of these error conditions\noccur, then the value of the atom will not change."},
  "Set.EMPTY"
  {:ns "cljs.core",
   :name "Set.EMPTY",
   :type "var",
   :full-name "cljs.core/Set.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "loop*"
  {:ns "special",
   :name "loop*",
   :type "special form",
   :full-name "special/loop*",
   :examples-strings [],
   :examples-htmls []},
  "this-as"
  {:ns "cljs.core",
   :name "this-as",
   :signature ["[name & body]"],
   :type "macro",
   :full-name "cljs.core/this-as",
   :docstring
   "Defines a scope where JavaScript's implicit \"this\" is bound to the name provided.",
   :examples-strings [],
   :examples-htmls []},
  "vector"
  {:description "Creates a new vector containing `args`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "vector",
   :signature ["[& args]"],
   :type "function/macro",
   :related
   ["cljs.core/vec"
    "cljs.core/vector?"
    "cljs.core/pop"
    "cljs.core/into"],
   :examples-strings [],
   :full-name "cljs.core/vector",
   :docstring "Creates a new vector containing the args."},
  "specify"
  {:ns "cljs.core",
   :name "specify",
   :signature ["[expr & impls]"],
   :type "macro",
   :full-name "cljs.core/specify",
   :docstring
   "Identical to specify but does not mutate its first argument. The first\nargument must be an ICloneable instance.",
   :examples-strings [],
   :examples-htmls []},
  "if-some"
  {:description
   "If `test` is not nil, evaluates `then` with `x` bound to the value of `test`. If\nnot, yields `else`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "if-some",
   :signature ["[[x test] then]" "[[x test] then else]"],
   :type "macro",
   :related ["cljs.core/when-some"],
   :examples-strings [],
   :full-name "cljs.core/if-some",
   :docstring
   "bindings => binding-form test\n\nIf test is not nil, evaluates then with binding-form bound to the\nvalue of test, if not, yields else"},
  "take-while"
  {:description
   "Returns a lazy sequence of successive items from `coll` while `(pred item)`\nreturns true. `pred` must be free of side-effects.\n\nReturns a transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "take-while",
   :signature ["[pred]" "[pred coll]"],
   :type "function",
   :related ["cljs.core/drop-while" "cljs.core/split-with"],
   :examples-strings [],
   :full-name "cljs.core/take-while",
   :docstring
   "Returns a lazy sequence of successive items from coll while\n(pred item) returns true. pred must be free of side-effects.\nReturns a transducer when no collection is provided."},
  "indexed?"
  {:ns "cljs.core",
   :name "indexed?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/indexed?",
   :docstring "Returns true if coll implements nth in constant time",
   :examples-strings [],
   :examples-htmls []},
  "IHash"
  {:ns "cljs.core",
   :name "IHash",
   :type "protocol",
   :full-name "cljs.core/IHash",
   :docstring "Protocol for adding hashing functionality to a type.",
   :examples-strings [],
   :examples-htmls []},
  "IndexedSeq"
  {:ns "cljs.core",
   :name "IndexedSeq",
   :signature ["[arr i]"],
   :type "type",
   :full-name "cljs.core/IndexedSeq",
   :examples-strings [],
   :examples-htmls []},
  "extend-protocol"
  {:ns "cljs.core",
   :name "extend-protocol",
   :signature ["[p & specs]"],
   :type "macro",
   :related ["cljs.core/extend-type"],
   :full-name "cljs.core/extend-protocol",
   :docstring
   "Useful when you want to provide several implementations of the same\nprotocol all at once. Takes a single protocol and the implementation\nof that protocol for one or more types. Expands into calls to\nextend-type:\n\n(extend-protocol Protocol\n  AType\n    (foo [x] ...)\n    (bar [x y] ...)\n  BType\n    (foo [x] ...)\n    (bar [x y] ...)\n  AClass\n    (foo [x] ...)\n    (bar [x y] ...)\n  nil\n    (foo [x] ...)\n    (bar [x y] ...))\n\nexpands into:\n\n(do\n (clojure.core/extend-type AType Protocol \n   (foo [x] ...) \n   (bar [x y] ...))\n (clojure.core/extend-type BType Protocol \n   (foo [x] ...) \n   (bar [x y] ...))\n (clojure.core/extend-type AClass Protocol \n   (foo [x] ...) \n   (bar [x y] ...))\n (clojure.core/extend-type nil Protocol \n   (foo [x] ...) \n   (bar [x y] ...)))",
   :examples-strings [],
   :examples-htmls []},
  "chunk-next"
  {:ns "cljs.core",
   :name "chunk-next",
   :signature ["[s]"],
   :type "function",
   :full-name "cljs.core/chunk-next",
   :examples-strings [],
   :examples-htmls []},
  "or"
  {:description
   "Evaluates arguments one at a time from left to right. If an argument returns\nlogical true, `or` returns that value and doesn't evaluate any of the other\narguments, otherwise it returns the value of the last argument.\n\n`(or)` returns nil.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;or&#41;\n;;=&gt; nil\n\n&#40;or false&#41;\n;;=&gt; false\n\n&#40;or true&#41;\n;;=&gt; true\n\n&#40;or true true&#41;\n;;=&gt; true\n\n&#40;or true false&#41;\n;;=&gt; true\n\n&#40;or false false&#41;\n;;=&gt; false\n</code></pre>"
    "<p><code>nil</code> and <code>false</code> are the only falsy values and everything else is truthy:</p><pre><code class=\"clj\">&#40;or &quot;foo&quot; &quot;bar&quot;&#41;\n;;=&gt; &quot;bar&quot;\n\n&#40;or &quot;foo&quot; nil&#41;\n;;=&gt; &quot;foo&quot;\n\n&#40;or &quot;foo&quot; false&#41;\n;;=&gt; &quot;foo&quot;\n\n&#40;or nil &quot;foo&quot;&#41;\n;;=&gt; &quot;foo&quot;\n\n&#40;or false &quot;foo&quot;&#41;\n;;=&gt; &quot;foo&quot;\n</code></pre>"],
   :ns "cljs.core",
   :name "or",
   :signature ["[]" "[x]" "[x & next]"],
   :type "macro",
   :related ["cljs.core/and" "special/if"],
   :examples-strings
   [["(or)"
     "(or false)"
     "(or true)"
     "(or true true)"
     "(or true false)"
     "(or false false)"]
    ["(or \"foo\" \"bar\")"
     "(or \"foo\" nil)"
     "(or \"foo\" false)"
     "(or nil \"foo\")"
     "(or false \"foo\")"]],
   :examples
   [{:id "d50433",
     :content
     "```clj\n(or)\n;;=> nil\n\n(or false)\n;;=> false\n\n(or true)\n;;=> true\n\n(or true true)\n;;=> true\n\n(or true false)\n;;=> true\n\n(or false false)\n;;=> false\n```"}
    {:id "62f291",
     :content
     "`nil` and `false` are the only falsy values and everything else is truthy:\n\n```clj\n(or \"foo\" \"bar\")\n;;=> \"bar\"\n\n(or \"foo\" nil)\n;;=> \"foo\"\n\n(or \"foo\" false)\n;;=> \"foo\"\n\n(or nil \"foo\")\n;;=> \"foo\"\n\n(or false \"foo\")\n;;=> \"foo\"\n```"}],
   :full-name "cljs.core/or",
   :docstring
   "Evaluates exprs one at a time, from left to right. If a form\nreturns a logical true value, or returns that value and doesn't\nevaluate any of the other expressions, otherwise it returns the\nvalue of the last expression. (or) returns nil."},
  "identity"
  {:description "Returns its argument.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "identity",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/nil?"],
   :examples-strings [],
   :full-name "cljs.core/identity",
   :docstring "Returns its argument."},
  "list?"
  {:description "Returns true if `x` is a list, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "list?",
   :signature ["[x]"],
   :type "function",
   :related
   ["cljs.core/seq?" "cljs.core/sequential?" "cljs.core/coll?"],
   :examples-strings [],
   :full-name "cljs.core/list?",
   :docstring "Returns true if x implements IList"},
  "implements?"
  {:ns "cljs.core",
   :name "implements?",
   :signature ["[psym x]"],
   :type "macro",
   :full-name "cljs.core/implements?",
   :docstring "EXPERIMENTAL",
   :examples-strings [],
   :examples-htmls []},
  "odd?"
  {:description
   "Returns true if `n` is an odd number.\n\nThrows an exception if `n` is not an integer.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "odd?",
   :signature ["[n]"],
   :type "function",
   :related ["cljs.core/even?"],
   :examples-strings [],
   :full-name "cljs.core/odd?",
   :docstring
   "Returns true if n is odd, throws an exception if n is not an integer"},
  "Vector.EMPTY"
  {:ns "cljs.core",
   :name "Vector.EMPTY",
   :type "var",
   :full-name "cljs.core/Vector.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "case"
  {:description
   "Takes an expression and a set of clauses. Each clause can take the form of\neither:\n\n`test-constant result-expr`\n\n`(test-constant1 ... test-constantN)  result-expr`\n\nThe test-constants are not evaluated. They must be compile-time literals, and\nneed not be quoted. If the expression is equal to a test-constant, the\ncorresponding `result-expr` is returned. A single default expression can follow\nthe clauses, and its value will be returned if no clause matches. If no default\nexpression is provided and no clause matches, an Error is thrown.\n\nUnlike `cond` and `condp`, `case` does a constant-time dispatch, the clauses are\nnot considered sequentially. All manner of constant expressions are acceptable\nin `case`, including numbers, strings, symbols, keywords, and ClojureScript\ncomposites thereof. Note that since lists are used to group multiple constants\nthat map to the same expression, a vector can be used to match a list if needed.\nThe test-constants need not be all of the same type.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a 1&#41;\n&#40;def b 2&#41;\n\n&#40;case a\n  0 &quot;zero&quot;\n  1 &quot;one&quot;\n  &quot;default&quot;&#41;\n;;=&gt; &quot;one&quot;\n\n&#40;case b\n  0 &quot;zero&quot;\n  1 &quot;one&quot;\n  &quot;default&quot;&#41;\n;;=&gt; &quot;default&quot;\n\n&#40;case b\n  0 &quot;zero&quot;\n  1 &quot;one&quot;&#41;\n;; Error: No matching clause: 2\n</code></pre>"],
   :ns "cljs.core",
   :name "case",
   :signature ["[e & clauses]"],
   :type "macro",
   :related ["cljs.core/cond" "cljs.core/condp"],
   :examples-strings
   [["(def a 1)"
     "(def b 2)"
     "(case a\n  0 \"zero\"\n  1 \"one\"\n  \"default\")"
     "(case b\n  0 \"zero\"\n  1 \"one\"\n  \"default\")"
     "(case b\n  0 \"zero\"\n  1 \"one\")"]],
   :examples
   [{:id "09a90c",
     :content
     "```clj\n(def a 1)\n(def b 2)\n\n(case a\n  0 \"zero\"\n  1 \"one\"\n  \"default\")\n;;=> \"one\"\n\n(case b\n  0 \"zero\"\n  1 \"one\"\n  \"default\")\n;;=> \"default\"\n\n(case b\n  0 \"zero\"\n  1 \"one\")\n;; Error: No matching clause: 2\n```"}],
   :full-name "cljs.core/case",
   :docstring
   "Takes an expression, and a set of clauses.\n\nEach clause can take the form of either:\n\ntest-constant result-expr\n\n(test-constant1 ... test-constantN)  result-expr\n\nThe test-constants are not evaluated. They must be compile-time\nliterals, and need not be quoted.  If the expression is equal to a\ntest-constant, the corresponding result-expr is returned. A single\ndefault expression can follow the clauses, and its value will be\nreturned if no clause matches. If no default expression is provided\nand no clause matches, an Error is thrown.\n\nUnlike cond and condp, case does a constant-time dispatch, the\nclauses are not considered sequentially.  All manner of constant\nexpressions are acceptable in case, including numbers, strings,\nsymbols, keywords, and (ClojureScript) composites thereof. Note that since\nlists are used to group multiple constants that map to the same\nexpression, a vector can be used to match a list if needed. The\ntest-constants need not be all of the same type."},
  "int-array"
  {:ns "cljs.core",
   :name "int-array",
   :signature ["[size-or-seq]" "[size init-val-or-seq]"],
   :type "function",
   :full-name "cljs.core/int-array",
   :docstring
   "Creates an array of ints. Does not coerce array, provided for compatibility\nwith Clojure.",
   :examples-strings [],
   :examples-htmls []},
  "HashSetIter"
  {:ns "cljs.core",
   :name "HashSetIter",
   :signature ["[iter]"],
   :type "type",
   :full-name "cljs.core/HashSetIter",
   :examples-strings [],
   :examples-htmls []},
  "keyword?"
  {:ns "cljs.core",
   :name "keyword?",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/keyword?",
   :docstring "Return true if x is a Keyword",
   :examples-strings [],
   :examples-htmls []},
  "PersistentArrayMapIterator"
  {:ns "cljs.core",
   :name "PersistentArrayMapIterator",
   :signature ["[arr i cnt]"],
   :type "type",
   :full-name "cljs.core/PersistentArrayMapIterator",
   :examples-strings [],
   :examples-htmls []},
  "ExceptionInfo"
  {:ns "cljs.core",
   :name "ExceptionInfo",
   :signature ["[message data cause]"],
   :type "type",
   :full-name "cljs.core/ExceptionInfo",
   :examples-strings [],
   :examples-htmls []},
  "keyword"
  {:ns "cljs.core",
   :name "keyword",
   :signature ["[name]" "[ns name]"],
   :type "function",
   :full-name "cljs.core/keyword",
   :docstring
   "Returns a Keyword with the given namespace and name.  Do not use :\nin the keyword strings, it will be added automatically.",
   :examples-strings [],
   :examples-htmls []},
  "pr-str"
  {:ns "cljs.core",
   :name "pr-str",
   :signature ["[& objs]"],
   :type "function",
   :full-name "cljs.core/pr-str",
   :docstring
   "pr to a string, returning it. Fundamental entrypoint to IPrintWithWriter.",
   :examples-strings [],
   :examples-htmls []},
  "remove"
  {:description
   "Returns a lazy sequence of the items in `coll` for which `(pred item)` returns\nfalse.\n\n`pred` must be free of side-effects.\n\nReturns a transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "remove",
   :signature ["[pred]" "[pred coll]"],
   :type "function",
   :related ["cljs.core/filter"],
   :examples-strings [],
   :full-name "cljs.core/remove",
   :docstring
   "Returns a lazy sequence of the items in coll for which\n(pred item) returns false. pred must be free of side-effects.\nReturns a transducer when no collection is provided."},
  "delay?"
  {:ns "cljs.core",
   :name "delay?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/delay?",
   :docstring "returns true if x is a Delay created with delay",
   :examples-strings [],
   :examples-htmls []},
  "iteration"
  {:ns "cljs.core",
   :name "iteration",
   :signature ["[xform coll]"],
   :type "function",
   :full-name "cljs.core/iteration",
   :docstring
   "Returns an iterable/seqable/reducible sequence of applications of\nthe transducer to the items in coll. Note that these applications\nwill be performed every time iterator/seq/reduce is called.",
   :examples-strings [],
   :examples-htmls []},
  "Namespace"
  {:ns "cljs.core",
   :name "Namespace",
   :signature ["[obj name]"],
   :type "type",
   :full-name "cljs.core/Namespace",
   :examples-strings [],
   :examples-htmls []},
  "ranged-iterator"
  {:ns "cljs.core",
   :name "ranged-iterator",
   :signature ["[v start end]"],
   :type "function",
   :full-name "cljs.core/ranged-iterator",
   :examples-strings [],
   :examples-htmls []},
  "interleave"
  {:description
   "Returns a lazy seq of the first item in each collection, then the second items,\nthen the third, etc.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "interleave",
   :signature ["[c1 c2]" "[c1 c2 & colls]"],
   :type "function",
   :related ["cljs.core/interpose" "cljs.core/zipmap"],
   :examples-strings [],
   :full-name "cljs.core/interleave",
   :docstring
   "Returns a lazy seq of the first item in each coll, then the second etc."},
  "map?"
  {:description "Returns true if `x` is a map, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "map?",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/hash-map" "cljs.core/sorted-map"],
   :examples-strings [],
   :full-name "cljs.core/map?",
   :docstring "Return true if x satisfies IMap"},
  "bit-not"
  {:description "Bitwise complement.  Same as `~x` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-not 2r1100&#41;\n;;=&gt; -13\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-not 12&#41;\n;;=&gt; -13\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-not",
   :signature ["[x]"],
   :type "function/macro",
   :examples-strings [["(bit-not 2r1100)" "(bit-not 12)"]],
   :examples
   [{:id "d4c5e3",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-not 2r1100)\n;;=> -13\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-not 12)\n;;=> -13\n```"}],
   :full-name "cljs.core/bit-not",
   :docstring "Bitwise complement"},
  "rand-nth"
  {:description
   "Returns a random element from a sequential collection `coll`.\n\nHas the same performance characteristics as `nth`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "rand-nth",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/rand"],
   :examples-strings [],
   :full-name "cljs.core/rand-nth",
   :docstring
   "Return a random element of the (sequential) collection. Will have\nthe same performance characteristics as nth for the given\ncollection."},
  "PersistentTreeMap"
  {:ns "cljs.core",
   :name "PersistentTreeMap",
   :signature ["[comp tree cnt meta __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentTreeMap",
   :examples-strings [],
   :examples-htmls []},
  "PersistentTreeMapSeq"
  {:ns "cljs.core",
   :name "PersistentTreeMapSeq",
   :signature ["[meta stack ascending? cnt __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentTreeMapSeq",
   :examples-strings [],
   :examples-htmls []},
  "ints"
  {:ns "cljs.core",
   :name "ints",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/ints",
   :examples-strings [],
   :examples-htmls []},
  "BitmapIndexedNode.EMPTY"
  {:ns "cljs.core",
   :name "BitmapIndexedNode.EMPTY",
   :type "var",
   :full-name "cljs.core/BitmapIndexedNode.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "ES6IteratorSeq"
  {:ns "cljs.core",
   :name "ES6IteratorSeq",
   :signature ["[value iter _rest]"],
   :type "type",
   :full-name "cljs.core/ES6IteratorSeq",
   :examples-strings [],
   :examples-htmls []},
  "PersistentHashSet.EMPTY"
  {:ns "cljs.core",
   :name "PersistentHashSet.EMPTY",
   :type "var",
   :full-name "cljs.core/PersistentHashSet.EMPTY",
   :examples-strings [],
   :examples-htmls []},
  "entries-iterator"
  {:ns "cljs.core",
   :name "entries-iterator",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/entries-iterator",
   :examples-strings [],
   :examples-htmls []},
  "lazy-seq"
  {:description "Returns a new lazy sequence.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "lazy-seq",
   :signature ["[& body]"],
   :type "macro",
   :related
   ["cljs.core/lazy-cat"
    "cljs.core/realized?"
    "cljs.core/doall"
    "cljs.core/iterate"],
   :examples-strings [],
   :full-name "cljs.core/lazy-seq",
   :docstring
   "Takes a body of expressions that returns an ISeq or nil, and yields\na ISeqable object that will invoke the body only the first time seq\nis called, and will cache the result and return it on all subsequent\nseq calls."},
  "partition-all"
  {:description
   "Returns a lazy sequence of lists like `partition`, but may include partitions\nwith fewer than `n` items at the end.\n\nReturns a stateful transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "partition-all",
   :signature ["[n]" "[n coll]" "[n step coll]"],
   :type "function",
   :related ["cljs.core/partition" "cljs.core/partition-by"],
   :examples-strings [],
   :full-name "cljs.core/partition-all",
   :docstring
   "Returns a lazy sequence of lists like partition, but may include\npartitions with fewer than n items at the end.  Returns a stateful\ntransducer when no collection is provided."},
  "mapcat"
  {:description
   "Returns the result of applying `concat` to the result of applying `map` to `f`\nand `colls`.\n\nFunction `f` should return a collection.\n\nReturns a transducer when no collections are provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "mapcat",
   :signature ["[f]" "[f & colls]"],
   :type "function",
   :related ["cljs.core/map" "cljs.core/concat"],
   :examples-strings [],
   :full-name "cljs.core/mapcat",
   :docstring
   "Returns the result of applying concat to the result of applying map\nto f and colls.  Thus function f should return a collection. Returns\na transducer when no collections are provided"},
  "*unchecked-if*"
  {:ns "cljs.core",
   :name "*unchecked-if*",
   :type "var",
   :full-name "cljs.core/*unchecked-if*",
   :examples-strings [],
   :examples-htmls []},
  "get-method"
  {:ns "cljs.core",
   :name "get-method",
   :signature ["[multifn dispatch-val]"],
   :type "function",
   :full-name "cljs.core/get-method",
   :docstring
   "Given a multimethod and a dispatch value, returns the dispatch fn\nthat would apply to that value, or nil if none apply and no default",
   :examples-strings [],
   :examples-htmls []},
  ">="
  {:description
   "Returns true if each successive number argument is less than or equal to the\nprevious one, false otherwise.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;&gt;= 2 1&#41;\n;;=&gt; true\n\n&#40;&gt;= 2 2&#41;\n;;=&gt; true\n\n&#40;&gt;= 1 2&#41;\n;;=&gt; false\n\n&#40;&gt;= 6 5 4 3 2&#41;\n;;=&gt; true\n</code></pre>"],
   :ns "cljs.core",
   :name ">=",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/>"],
   :examples-strings
   [["(>= 2 1)" "(>= 2 2)" "(>= 1 2)" "(>= 6 5 4 3 2)"]],
   :examples
   [{:id "de73d7",
     :content
     "```clj\n(>= 2 1)\n;;=> true\n\n(>= 2 2)\n;;=> true\n\n(>= 1 2)\n;;=> false\n\n(>= 6 5 4 3 2)\n;;=> true\n```"}],
   :full-name "cljs.core/>=",
   :docstring
   "Returns non-nil if nums are in monotonically non-increasing order,\notherwise false."},
  "int-rotate-left"
  {:ns "cljs.core",
   :name "int-rotate-left",
   :signature ["[x n]"],
   :type "function",
   :full-name "cljs.core/int-rotate-left",
   :examples-strings [],
   :examples-htmls []},
  "exists?"
  {:ns "cljs.core",
   :name "exists?",
   :signature ["[x]"],
   :type "macro",
   :full-name "cljs.core/exists?",
   :docstring
   "Return true if argument exists, analogous to usage of typeof operator\nin JavaScript.",
   :examples-strings [],
   :examples-htmls []},
  "goog-define"
  {:ns "cljs.core",
   :name "goog-define",
   :signature ["[sym default]"],
   :type "macro",
   :full-name "cljs.core/goog-define",
   :docstring
   "Defines a var using `goog.define`. Passed default value must be\nstring, number or boolean.\n\nDefault value can be overridden at compile time using the\ncompiler option `:closure-defines`.\n\nExample:\n  (ns your-app.core)\n  (goog-define DEBUG! false)\n  ;; can be overridden with\n  :closure-defines {\"your_app.core.DEBUG_BANG_\" true}\n  or\n  :closure-defines {'your-app.core/DEBUG! true}",
   :examples-strings [],
   :examples-htmls []},
  "bytes"
  {:ns "cljs.core",
   :name "bytes",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/bytes",
   :examples-strings [],
   :examples-htmls []},
  "missing-protocol"
  {:ns "cljs.core",
   :name "missing-protocol",
   :signature ["[proto obj]"],
   :type "function",
   :full-name "cljs.core/missing-protocol",
   :examples-strings [],
   :examples-htmls []},
  "IChunk"
  {:ns "cljs.core",
   :name "IChunk",
   :type "protocol",
   :full-name "cljs.core/IChunk",
   :docstring "Protocol for accessing the items of a chunk.",
   :examples-strings [],
   :examples-htmls []},
  "prn"
  {:ns "cljs.core",
   :name "prn",
   :signature ["[& objs]"],
   :type "function",
   :full-name "cljs.core/prn",
   :docstring "Same as pr followed by (newline).",
   :examples-strings [],
   :examples-htmls []},
  "concat"
  {:description
   "Returns a lazy sequence representing the concatenation of the elements in the\nsupplied collections.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;concat &#40;list 1 2 3&#41; &#40;list 4 5 6&#41;&#41;\n;;=&gt; &#40;1 2 3 4 5 6&#41;\n\n&#40;concat &#91;1 2 3&#93; &#40;list 4 5 6&#41;&#41;\n;; =&gt; &#40;1 2 3 4 5 6&#41;\n\n&#40;concat &#91;1&#93; &#91;2&#93; &#91;3&#93;&#41;\n;; =&gt; &#40;1 2 3&#41;\n</code></pre>"],
   :ns "cljs.core",
   :name "concat",
   :signature ["[]" "[x]" "[x y]" "[x y & zs]"],
   :type "function",
   :related ["cljs.core/conj" "cljs.core/into"],
   :examples-strings
   [["(concat (list 1 2 3) (list 4 5 6))"
     "(concat [1 2 3] (list 4 5 6))"
     "(concat [1] [2] [3])"]],
   :examples
   [{:id "dcc019",
     :content
     "```clj\n(concat (list 1 2 3) (list 4 5 6))\n;;=> (1 2 3 4 5 6)\n\n(concat [1 2 3] (list 4 5 6))\n;; => (1 2 3 4 5 6)\n\n(concat [1] [2] [3])\n;; => (1 2 3)\n```"}],
   :full-name "cljs.core/concat",
   :docstring
   "Returns a lazy seq representing the concatenation of the elements in the supplied colls."},
  "chunk-rest"
  {:ns "cljs.core",
   :name "chunk-rest",
   :signature ["[s]"],
   :type "function",
   :full-name "cljs.core/chunk-rest",
   :examples-strings [],
   :examples-htmls []},
  "pr-sequential-writer"
  {:ns "cljs.core",
   :name "pr-sequential-writer",
   :signature ["[writer print-one begin sep end opts coll]"],
   :type "function",
   :full-name "cljs.core/pr-sequential-writer",
   :examples-strings [],
   :examples-htmls []},
  "filter"
  {:description
   "Returns a lazy sequence of the non-nil results of `(f item)`. Note, this means\nfalse return values will be included.\n\n`f` must be free of side-effects.\n\nReturns a transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "filter",
   :signature ["[f]" "[f coll]"],
   :type "function",
   :related ["cljs.core/remove" "cljs.core/keep"],
   :examples-strings [],
   :full-name "cljs.core/filter",
   :docstring
   "Returns a lazy sequence of the items in coll for which\n(pred item) returns true. pred must be free of side-effects.\nReturns a transducer when no collection is provided."},
  "take-last"
  {:description
   "Returns a sequence of the last `n` items in `coll`.\n\nDepending on the type of collection, `take-last` may be no faster than linear\ntime. For vectors, please use `subvec`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "take-last",
   :signature ["[n coll]"],
   :type "function",
   :related
   ["cljs.core/last" "cljs.core/butlast" "cljs.core/drop-last"],
   :examples-strings [],
   :full-name "cljs.core/take-last",
   :docstring
   "Returns a seq of the last n items in coll.  Depending on the type\nof coll may be no better than linear time.  For vectors, see also subvec."},
  "sorted-set-by"
  {:description
   "Returns a new sorted set with supplied `keys`, using the supplied `comparator`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "sorted-set-by",
   :signature ["[comparator & keys]"],
   :type "function",
   :related
   ["cljs.core/sorted-set"
    "cljs.core/sorted-map-by"
    "cljs.core/compare"],
   :examples-strings [],
   :full-name "cljs.core/sorted-set-by",
   :docstring
   "Returns a new sorted set with supplied keys, using the supplied comparator."},
  "ns-interns*"
  {:ns "cljs.core",
   :name "ns-interns*",
   :signature ["[sym]"],
   :type "function",
   :full-name "cljs.core/ns-interns*",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-remainder-int"
  {:ns "cljs.core",
   :name "unchecked-remainder-int",
   :signature ["[x n]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-remainder-int",
   :examples-strings [],
   :examples-htmls []},
  "symbol"
  {:ns "cljs.core",
   :name "symbol",
   :signature ["[name]" "[ns name]"],
   :type "function",
   :full-name "cljs.core/symbol",
   :examples-strings [],
   :examples-htmls []},
  "time"
  {:ns "cljs.core",
   :name "time",
   :signature ["[expr]"],
   :type "macro",
   :full-name "cljs.core/time",
   :docstring
   "Evaluates expr and prints the time it took. Returns the value of expr.",
   :examples-strings [],
   :examples-htmls []},
  "js-in"
  {:description
   "Determines if property `key` is in JavaScript object `obj`.\n\nEquivalent to `key in obj` in JavaScript.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a #js {:foo 1 :bar 2}&#41;\n\n&#40;js-in &quot;foo&quot; a&#41;\n;;=&gt; true\n\n&#40;js-in &quot;hello&quot; a&#41;\n;;=&gt; false\n</code></pre><p>Properties inherited from prototype chain are also detected:</p><pre><code class=\"clj\">&#40;js-in &quot;toString&quot; a&#41;\n;;=&gt; true\n</code></pre>"],
   :ns "cljs.core",
   :name "js-in",
   :signature ["[key obj]"],
   :type "macro",
   :related ["cljs.core/contains?"],
   :examples-strings
   [["(def a #js {:foo 1 :bar 2})"
     "(js-in \"foo\" a)"
     "(js-in \"hello\" a)"
     "(js-in \"toString\" a)"]],
   :examples
   [{:id "a45b18",
     :content
     "```clj\n(def a #js {:foo 1 :bar 2})\n\n(js-in \"foo\" a)\n;;=> true\n\n(js-in \"hello\" a)\n;;=> false\n```\n\nProperties inherited from prototype chain are also detected:\n\n```clj\n(js-in \"toString\" a)\n;;=> true\n```"}],
   :full-name "cljs.core/js-in"},
  "empty"
  {:description
   "Returns an empty collection of the same category as `coll`.\n\nReturns nil if `coll` is nil.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "empty",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/not-empty"],
   :examples-strings [],
   :full-name "cljs.core/empty",
   :docstring
   "Returns an empty collection of the same category as coll, or nil"},
  "PersistentQueueIter"
  {:ns "cljs.core",
   :name "PersistentQueueIter",
   :signature ["[fseq riter]"],
   :type "type",
   :full-name "cljs.core/PersistentQueueIter",
   :examples-strings [],
   :examples-htmls []},
  "condp"
  {:description
   "Takes a binary predicate, an expression, and a set of clauses. There are two\nkinds of clauses:\n\nBinary clause: `test-expr` `result-expr`\n\nTernary clause: `test-expr` `:>>` `result-fn`<br />\n(Note: `:>>` is an ordinary keyword)\n\nFor each clause, `(pred test-expr expr)` is evaluated. If it returns logical\ntrue, the clause is a match.\n\nIf a binary clause matches, its `result-expr` is returned.\n\nIf a ternary clause matches, its `result-fn` is called with the result of the\npredicate and returned by `condp`. `result-fn` should take one argument.\n\nA single default expression can follow the clauses, and its value will be\nreturned if no clause matches.\n\nIf no default expression is provided and no clause matches, an Error is thrown.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "condp",
   :signature ["[pred expr & clauses]"],
   :type "macro",
   :related ["cljs.core/cond" "special/if"],
   :examples-strings [],
   :full-name "cljs.core/condp",
   :docstring
   "Takes a binary predicate, an expression, and a set of clauses.\nEach clause can take the form of either:\n\ntest-expr result-expr\n\ntest-expr :>> result-fn\n\nNote :>> is an ordinary keyword.\n\nFor each clause, (pred test-expr expr) is evaluated. If it returns\nlogical true, the clause is a match. If a binary clause matches, the\nresult-expr is returned, if a ternary clause matches, its result-fn,\nwhich must be a unary function, is called with the result of the\npredicate as its argument, the result of that call being the return\nvalue of condp. A single default expression can follow the clauses,\nand its value will be returned if no clause matches. If no default\nexpression is provided and no clause matches, an\nIllegalArgumentException is thrown."},
  "hash-ordered-coll"
  {:ns "cljs.core",
   :name "hash-ordered-coll",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/hash-ordered-coll",
   :docstring
   "Returns the hash code, consistent with =, for an external ordered\ncollection implementing Iterable.\nSee http://clojure.org/data_structures#hash for full algorithms.",
   :examples-strings [],
   :examples-htmls []},
  "unsigned-bit-shift-right"
  {:description "Bitwise shift right with zero fill",
   :examples-htmls [],
   :ns "cljs.core",
   :name "unsigned-bit-shift-right",
   :signature ["[x n]"],
   :type "function/macro",
   :related ["cljs.core/bit-shift-right"],
   :examples-strings [],
   :full-name "cljs.core/unsigned-bit-shift-right",
   :docstring "Bitwise shift right with zero fill"},
  "RSeq"
  {:ns "cljs.core",
   :name "RSeq",
   :signature ["[ci i meta]"],
   :type "type",
   :full-name "cljs.core/RSeq",
   :examples-strings [],
   :examples-htmls []},
  "comparator"
  {:ns "cljs.core",
   :name "comparator",
   :signature ["[pred]"],
   :type "function",
   :full-name "cljs.core/comparator",
   :docstring
   "Returns an JavaScript compatible comparator based upon pred.",
   :examples-strings [],
   :examples-htmls []},
  "INamed"
  {:ns "cljs.core",
   :name "INamed",
   :type "protocol",
   :full-name "cljs.core/INamed",
   :docstring "Protocol for adding a name.",
   :examples-strings [],
   :examples-htmls []},
  "complement"
  {:description
   "Takes a function `f` and returns a function that takes the same arguments as\n`f`, has the same effects, if any, and returns the opposite truth value.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a 10&#41;\n&#40;&#40;complement #&#40;= a %&#41;&#41; 12&#41;\n;;=&gt; true\n</code></pre>"],
   :ns "cljs.core",
   :name "complement",
   :signature ["[f]"],
   :type "function",
   :related ["cljs.core/not"],
   :examples-strings [["(def a 10)" "((complement #(= a %)) 12)"]],
   :examples
   [{:id "69e359",
     :content
     "```clj\n(def a 10)\n((complement #(= a %)) 12)\n;;=> true\n```"}],
   :full-name "cljs.core/complement",
   :docstring
   "Takes a fn f and returns a fn that takes the same arguments as f,\nhas the same effects, if any, and returns the opposite truth value."},
  "Vector"
  {:ns "cljs.core",
   :name "Vector",
   :signature ["[meta array __hash]"],
   :type "type",
   :full-name "cljs.core/Vector",
   :examples-strings [],
   :examples-htmls []},
  "when"
  {:description
   "Evaluates `test`. If logical true, evaluates `body` in an implicit `do`.\n\n`when` is often used instead of `if` for conditions that do not have an \"else\".",
   :examples-htmls [],
   :ns "cljs.core",
   :name "when",
   :signature ["[test & body]"],
   :type "macro",
   :related ["cljs.core/when-not" "cljs.core/when-let" "special/if"],
   :examples-strings [],
   :full-name "cljs.core/when",
   :docstring
   "Evaluates test. If logical true, evaluates body in an implicit do."},
  "let*"
  {:ns "special",
   :name "let*",
   :type "special form",
   :full-name "special/let*",
   :examples-strings [],
   :examples-htmls []},
  "lazy-transformer"
  {:ns "cljs.core",
   :name "lazy-transformer",
   :signature ["[stepper]"],
   :type "function",
   :full-name "cljs.core/lazy-transformer",
   :examples-strings [],
   :examples-htmls []},
  "chunked-seq"
  {:ns "cljs.core",
   :name "chunked-seq",
   :signature
   ["[vec i off]" "[vec node i off]" "[vec node i off meta]"],
   :type "function",
   :full-name "cljs.core/chunked-seq",
   :examples-strings [],
   :examples-htmls []},
  "symbol?"
  {:ns "cljs.core",
   :name "symbol?",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/symbol?",
   :docstring "Return true if x is a Symbol",
   :examples-strings [],
   :examples-htmls []},
  "every-pred"
  {:description
   "Takes a set of predicate functions and returns a function `f` that returns true\nif all of its composing predicates return a logical true value against all of\nits arguments, else it returns false.\n\nNote that `f` is short-circuiting in that it will stop execution on the first\nargument that triggers a logical false result against the original predicates.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "every-pred",
   :signature ["[p]" "[p1 p2]" "[p1 p2 p3]" "[p1 p2 p3 & ps]"],
   :type "function",
   :related ["cljs.core/some-fn" "cljs.core/and"],
   :examples-strings [],
   :full-name "cljs.core/every-pred",
   :docstring
   "Takes a set of predicates and returns a function f that returns true if all of its\ncomposing predicates return a logical true value against all of its arguments, else it returns\nfalse. Note that f is short-circuiting in that it will stop execution on the first\nargument that triggers a logical false result against the original predicates."},
  "subseq"
  {:description
   "`sc` must be a sorted collection.\n\n`test`, `start-test`, `end-test` must be `<`, `<=`, `>` or `>=`.\n\nReturns a sequence of those entries with keys `ek` for which\n`(test (.. sc comparator (compare ek key)) 0)` is true.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "subseq",
   :signature
   ["[sc test key]" "[sc start-test start-key end-test end-key]"],
   :type "function",
   :related
   ["cljs.core/rsubseq"
    "cljs.core/sorted-map"
    "cljs.core/sorted-set"
    "cljs.core/sorted-map-by"
    "cljs.core/sorted-set-by"],
   :examples-strings [],
   :full-name "cljs.core/subseq",
   :docstring
   "sc must be a sorted collection, test(s) one of <, <=, > or\n>=. Returns a seq of those entries with keys ek for\nwhich (test (.. sc comparator (compare ek key)) 0) is true"},
  "reset!"
  {:description
   "Sets the value of atom `a` to `new-value` without regard for the current value.\n\nReturns `new-value`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "reset!",
   :signature ["[a new-value]"],
   :type "function",
   :related
   ["cljs.core/swap!" "cljs.core/compare-and-set!" "cljs.core/atom"],
   :examples-strings [],
   :full-name "cljs.core/reset!",
   :docstring
   "Sets the value of atom to newval without regard for the\ncurrent value. Returns newval."},
  "pr-seq-writer"
  {:ns "cljs.core",
   :name "pr-seq-writer",
   :signature ["[objs writer opts]"],
   :type "function",
   :full-name "cljs.core/pr-seq-writer",
   :examples-strings [],
   :examples-htmls []},
  "PersistentArrayMap"
  {:ns "cljs.core",
   :name "PersistentArrayMap",
   :signature ["[meta cnt arr __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentArrayMap",
   :examples-strings [],
   :examples-htmls []},
  "StringIter"
  {:ns "cljs.core",
   :name "StringIter",
   :signature ["[s i]"],
   :type "type",
   :full-name "cljs.core/StringIter",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-substract"
  {:ns "cljs.core",
   :name "unchecked-substract",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function",
   :full-name "cljs.core/unchecked-substract",
   :docstring
   "If no ys are supplied, returns the negation of x, else subtracts\nthe ys from x and returns the result.",
   :examples-strings [],
   :examples-htmls []},
  "Keyword"
  {:ns "cljs.core",
   :name "Keyword",
   :signature ["[ns name fqn _hash]"],
   :type "type",
   :full-name "cljs.core/Keyword",
   :examples-strings [],
   :examples-htmls []},
  "neg?"
  {:description "Returns true if `n` is less than 0, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "neg?",
   :signature ["[n]"],
   :type "function/macro",
   :related ["cljs.core/pos?" "cljs.core/zero?"],
   :examples-strings [],
   :full-name "cljs.core/neg?",
   :docstring "Returns true if num is less than zero, else false"},
  "PersistentArrayMap.HASHMAP_THRESHOLD"
  {:ns "cljs.core",
   :name "PersistentArrayMap.HASHMAP_THRESHOLD",
   :type "var",
   :full-name "cljs.core/PersistentArrayMap.HASHMAP_THRESHOLD",
   :examples-strings [],
   :examples-htmls []},
  "PersistentHashSet"
  {:ns "cljs.core",
   :name "PersistentHashSet",
   :signature ["[meta hash-map __hash]"],
   :type "type",
   :full-name "cljs.core/PersistentHashSet",
   :examples-strings [],
   :examples-htmls []},
  "string?"
  {:description "Returns true if `x` is a string, false otherwise.",
   :ns "cljs.core",
   :name "string?",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/string?",
   :docstring "Returns true if x is a JavaScript string.",
   :examples-strings [],
   :examples-htmls []},
  "m3-mix-H1"
  {:ns "cljs.core",
   :name "m3-mix-H1",
   :signature ["[h1 k1]"],
   :type "function",
   :full-name "cljs.core/m3-mix-H1",
   :examples-strings [],
   :examples-htmls []},
  "sorted?"
  {:description
   "Returns true if `coll` implements the `ISorted` protocol, false otherwise.\n\nSorted maps and sorted sets implement `ISorted`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "sorted?",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/sorted-map" "cljs.core/sorted-set"],
   :examples-strings [],
   :full-name "cljs.core/sorted?",
   :docstring "Returns true if coll satisfies ISorted"},
  "->"
  {:description
   "The thread-first macro \"threads\" an expression through several forms as the\nsecond item in a list.\n\nInserts `x` as the second item in the first form, making a list of it if it is\nnot a list already. If there are more forms, inserts the first form as the\nsecond item in second form, etc.\n\n<table class=\"code-tbl-9bef6\">\n  <thead>\n    <tr>\n      <th>Code</th>\n      <th>Expands To</th></tr></thead>\n  <tbody>\n    <tr>\n      <td><pre>\n(-> x\n  (a b c)\n  d\n  (x y z))</pre></td>\n      <td><pre>\n(x (d (a x b c)) y z)</pre></td></tr></tbody></table>",
   :examples-htmls
   ["<p>The first is arguably a bit more cumbersome to read than the second:</p><pre><code class=\"clj\">&#40;first &#40;.split &#40;.replace &#40;.toUpperCase &quot;a b c d&quot;&#41; &quot;A&quot; &quot;X&quot;&#41; &quot; &quot;&#41;&#41;\n;;=&gt; &quot;X&quot;\n\n&#40;-&gt; &quot;a b c d&quot;\n    .toUpperCase\n    &#40;.replace &quot;A&quot; &quot;X&quot;&#41;\n    &#40;.split &quot; &quot;&#41;\n    first&#41;\n;;=&gt; &quot;X&quot;\n</code></pre>"
    "<p>It can also be useful for pulling values out of deeply-nested data structures:</p><pre><code class=\"clj\">&#40;def person\n  {:name &quot;Mark Volkmann&quot;\n   :address {:street &quot;644 Glen Summit&quot;\n             :city &quot;St. Charles&quot;\n             :state &quot;Missouri&quot;\n             :zip 63304}\n   :employer {:name &quot;Object Computing, Inc.&quot;\n              :address {:street &quot;12140 Woodcrest Dr.&quot;\n                        :city &quot;Creve Coeur&quot;\n                        :state &quot;Missouri&quot;\n                        :zip 63141}}}&#41;\n\n&#40;-&gt; person :employer :address :city&#41;\n;;=&gt; &quot;Creve Coeur&quot;\n</code></pre><p>Same as above, but with more nesting:</p><pre><code class=\"clj\">&#40;:city &#40;:address &#40;:employer person&#41;&#41;&#41;\n;;=&gt; &quot;Creve Coeur&quot;\n</code></pre>"
    "<p>It can also help with arithmetic:</p><pre><code class=\"clj\">&#40;def c 5&#41;\n&#40;-&gt; c &#40;+ 3&#41; &#40;/ 2&#41; &#40;- 1&#41;&#41;\n;;=&gt; 3\n</code></pre><p>Same as above, but with more nesting:</p><pre><code class=\"clj\">&#40;- &#40;/ &#40;+ c 3&#41; 2&#41; 1&#41;\n;;=&gt; 3\n</code></pre>"],
   :ns "cljs.core",
   :name "->",
   :signature ["[x & forms]"],
   :type "macro",
   :related ["cljs.core/->>"],
   :examples-strings
   [["(first (.split (.replace (.toUpperCase \"a b c d\") \"A\" \"X\") \" \"))"
     "(-> \"a b c d\"\n    .toUpperCase\n    (.replace \"A\" \"X\")\n    (.split \" \")\n    first)"]
    ["(def person\n  {:name \"Mark Volkmann\"\n   :address {:street \"644 Glen Summit\"\n             :city \"St. Charles\"\n             :state \"Missouri\"\n             :zip 63304}\n   :employer {:name \"Object Computing, Inc.\"\n              :address {:street \"12140 Woodcrest Dr.\"\n                        :city \"Creve Coeur\"\n                        :state \"Missouri\"\n                        :zip 63141}}})"
     "(-> person :employer :address :city)"
     "(:city (:address (:employer person)))"]
    ["(def c 5)" "(-> c (+ 3) (/ 2) (- 1))" "(- (/ (+ c 3) 2) 1)"]],
   :examples
   [{:id "19b460",
     :content
     "The first is arguably a bit more cumbersome to read than the second:\n\n```clj\n(first (.split (.replace (.toUpperCase \"a b c d\") \"A\" \"X\") \" \"))\n;;=> \"X\"\n\n(-> \"a b c d\"\n    .toUpperCase\n    (.replace \"A\" \"X\")\n    (.split \" \")\n    first)\n;;=> \"X\"\n```"}
    {:id "78ad8f",
     :content
     "It can also be useful for pulling values out of deeply-nested\ndata structures:\n\n```clj\n(def person\n  {:name \"Mark Volkmann\"\n   :address {:street \"644 Glen Summit\"\n             :city \"St. Charles\"\n             :state \"Missouri\"\n             :zip 63304}\n   :employer {:name \"Object Computing, Inc.\"\n              :address {:street \"12140 Woodcrest Dr.\"\n                        :city \"Creve Coeur\"\n                        :state \"Missouri\"\n                        :zip 63141}}})\n\n(-> person :employer :address :city)\n;;=> \"Creve Coeur\"\n```\n\nSame as above, but with more nesting:\n\n```clj\n(:city (:address (:employer person)))\n;;=> \"Creve Coeur\"\n```"}
    {:id "5fe621",
     :content
     "It can also help with arithmetic:\n\n```clj\n(def c 5)\n(-> c (+ 3) (/ 2) (- 1))\n;;=> 3\n```\n\nSame as above, but with more nesting:\n\n```clj\n(- (/ (+ c 3) 2) 1)\n;;=> 3\n```"}],
   :full-name "cljs.core/->",
   :docstring
   "Threads the expr through the forms. Inserts x as the\nsecond item in the first form, making a list of it if it is not a\nlist already. If there are more forms, inserts the first form as the\nsecond item in second form, etc."},
  "set-validator!"
  {:description
   "Sets a validator function for atom `a`.\n\n`fn` must be nil or a side-effect-free function of one argument, which will be\npassed the intended new state on any state change. `fn` should return false or\nthrow an Error if the new state is unacceptable.\n\nIf the current value of `a` is unacceptable to `fn` when `set-validator!` is\ncalled, an Error will be thrown and the validator will not be set.\n\n`(set-validator! my-atom nil)` will remove the validator from `my-atom`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "set-validator!",
   :signature ["[a fn]"],
   :type "function",
   :related ["cljs.core/atom" "cljs.core/get-validator"],
   :examples-strings [],
   :full-name "cljs.core/set-validator!",
   :docstring
   "Sets the validator-fn for an atom. validator-fn must be nil or a\nside-effect-free fn of one argument, which will be passed the intended\nnew state on any state change. If the new state is unacceptable, the\nvalidator-fn should return false or throw an Error. If the current state\nis not acceptable to the new validator, an Error will be thrown and the\nvalidator will not be changed."},
  "and"
  {:description
   "Evaluates arguments one at a time from left to right. If an argument returns\nlogical false (nil or false), `and` returns that value and doesn't evaluate any\nof the other arguments, otherwise it returns the value of the last argument.\n\n`(and)` returns true.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;and&#41;\n;;=&gt; true\n\n&#40;and false&#41;\n;;=&gt; false\n\n&#40;and true&#41;\n;;=&gt; true\n\n&#40;and true true&#41;\n;;=&gt; true\n\n&#40;and true false&#41;\n;;=&gt; false\n\n&#40;and false false&#41;\n;;=&gt; false\n</code></pre>"
    "<p><code>nil</code> and <code>false</code> are the only falsy values and everything else is truthy:</p><pre><code class=\"clj\">&#40;and &quot;foo&quot; &quot;bar&quot;&#41;\n;;=&gt; &quot;bar&quot;\n\n&#40;and &quot;foo&quot; nil&#41;\n;;=&gt; nil\n\n&#40;and &quot;foo&quot; false&#41;\n;;=&gt; false\n\n&#40;and nil &quot;foo&quot;&#41;\n;;=&gt; nil\n\n&#40;and false &quot;foo&quot;&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "and",
   :signature ["[]" "[x]" "[x & next]"],
   :type "macro",
   :related ["cljs.core/or" "special/if"],
   :examples-strings
   [["(and)"
     "(and false)"
     "(and true)"
     "(and true true)"
     "(and true false)"
     "(and false false)"]
    ["(and \"foo\" \"bar\")"
     "(and \"foo\" nil)"
     "(and \"foo\" false)"
     "(and nil \"foo\")"
     "(and false \"foo\")"]],
   :examples
   [{:id "a39a73",
     :content
     "```clj\n(and)\n;;=> true\n\n(and false)\n;;=> false\n\n(and true)\n;;=> true\n\n(and true true)\n;;=> true\n\n(and true false)\n;;=> false\n\n(and false false)\n;;=> false\n```"}
    {:id "766638",
     :content
     "`nil` and `false` are the only falsy values and everything else is truthy:\n\n```clj\n(and \"foo\" \"bar\")\n;;=> \"bar\"\n\n(and \"foo\" nil)\n;;=> nil\n\n(and \"foo\" false)\n;;=> false\n\n(and nil \"foo\")\n;;=> nil\n\n(and false \"foo\")\n;;=> false\n```"}],
   :full-name "cljs.core/and",
   :docstring
   "Evaluates exprs one at a time, from left to right. If a form\nreturns logical false (nil or false), and returns that value and\ndoesn't evaluate any of the other expressions, otherwise it returns\nthe value of the last expr. (and) returns true."},
  "hash-set"
  {:description
   "Returns a new hash set with supplied `keys`.\n\nAny equal keys are handled as if by repeated uses of `conj`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "hash-set",
   :signature ["[]" "[& keys]"],
   :type "function/macro",
   :related ["cljs.core/set" "cljs.core/sorted-set"],
   :examples-strings [],
   :full-name "cljs.core/hash-set",
   :docstring
   "Returns a new hash set with supplied keys.  Any equal keys are\nhandled as if by repeated uses of conj."},
  "set?"
  {:description "Returns true if `x` is a set, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "set?",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/set"],
   :examples-strings [],
   :full-name "cljs.core/set?",
   :docstring "Returns true if x satisfies ISet"},
  "defmethod"
  {:ns "cljs.core",
   :name "defmethod",
   :signature ["[multifn dispatch-val & fn-tail]"],
   :type "macro",
   :full-name "cljs.core/defmethod",
   :docstring
   "Creates and installs a new method of multimethod associated with dispatch-value. ",
   :examples-strings [],
   :examples-htmls []},
  "group-by"
  {:description
   "Returns a map of the elements of `coll` keyed by the result of running `f` on\neach element.\n\nThe value at each key will be a vector of the corresponding elements in the\norder they appeared in `coll`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "group-by",
   :signature ["[f coll]"],
   :type "function",
   :related ["cljs.core/partition-by" "cljs.core/frequencies"],
   :examples-strings [],
   :full-name "cljs.core/group-by",
   :docstring
   "Returns a map of the elements of coll keyed by the result of\nf on each element. The value at each key will be a vector of the\ncorresponding elements, in the order they appeared in coll."},
  "deref"
  {:description
   "Returns the current value of atom `x`.\n\nThe `@` reader macro is often used instead of `deref`. `@foo` is the same thing\nas `(deref foo)`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "deref",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/atom"],
   :examples-strings [],
   :full-name "cljs.core/deref",
   :docstring
   "Also reader macro: @var/@atom/@delay. Returns the\nmost-recently-committed value of ref. When applied to a var\nor atom, returns its current state. When applied to a delay, forces\nit if not already forced. See also - realized?."},
  "HashMapIter"
  {:ns "cljs.core",
   :name "HashMapIter",
   :signature ["[nil-val root-iter seen]"],
   :type "type",
   :full-name "cljs.core/HashMapIter",
   :examples-strings [],
   :examples-htmls []},
  "mod"
  {:description
   "Returns the modulus of dividing numerator `n` by denominator `d`.\n\nReturns `NaN` when `d` is 0 (divide by 0 error).\n\nTruncates toward negative infinity.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;mod -5 3&#41;\n;;=&gt; 1\n\n&#40;mod 5 3&#41;\n;;=&gt; 2\n\n&#40;mod 5 0&#41;\n;;=&gt; NaN\n</code></pre>"],
   :ns "cljs.core",
   :name "mod",
   :signature ["[n d]"],
   :type "function",
   :related ["cljs.core/rem"],
   :examples-strings [["(mod -5 3)" "(mod 5 3)" "(mod 5 0)"]],
   :examples
   [{:id "8165e8",
     :content
     "```clj\n(mod -5 3)\n;;=> 1\n\n(mod 5 3)\n;;=> 2\n\n(mod 5 0)\n;;=> NaN\n```"}],
   :full-name "cljs.core/mod",
   :docstring
   "Modulus of num and div. Truncates toward negative infinity."},
  "do"
  {:ns "special",
   :name "do",
   :signature ["[exprs*]"],
   :type "special form",
   :full-name "special/do",
   :docstring
   "Evaluates the expressions in order and returns the value of\nthe last. If no expressions are supplied, returns nil.",
   :examples-strings [],
   :examples-htmls []},
  "some->>"
  {:description
   "When `expr` is not nil, threads it into the first form (via `->>`), and when\nthat result is not nil, through the next, etc.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "some->>",
   :signature ["[expr & forms]"],
   :type "macro",
   :related
   ["cljs.core/->"
    "cljs.core/->>"
    "cljs.core/some->"
    "cljs.core/some"],
   :examples-strings [],
   :full-name "cljs.core/some->>",
   :docstring
   "When expr is not nil, threads it into the first form (via ->>),\nand when that result is not nil, through the next etc"},
  "not-empty"
  {:description "Returns nil if `coll` is empty, else returns `coll`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "not-empty",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/empty"],
   :examples-strings [],
   :full-name "cljs.core/not-empty",
   :docstring "If coll is empty, returns nil, else coll"},
  "fn*"
  {:ns "special",
   :name "fn*",
   :type "special form",
   :full-name "special/fn*",
   :examples-strings [],
   :examples-htmls []},
  "last"
  {:description
   "Returns the last item in `coll` in linear time.\n\n`peek` is much faster than `last` for a vector.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;last &#91;1 2 3&#93;&#41;\n;;=&gt; 3\n\n&#40;last &#91;1 2&#93;&#41;\n;;=&gt; 2\n\n&#40;last &#91;1&#93;&#41;\n;;=&gt; 1\n\n&#40;last &#91;&#93;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "last",
   :signature ["[coll]"],
   :type "function",
   :related
   ["cljs.core/first"
    "cljs.core/next"
    "cljs.core/rest"
    "cljs.core/butlast"
    "cljs.core/take-last"],
   :examples-strings
   [["(last [1 2 3])" "(last [1 2])" "(last [1])" "(last [])"]],
   :examples
   [{:id "eb0836",
     :content
     "```clj\n(last [1 2 3])\n;;=> 3\n\n(last [1 2])\n;;=> 2\n\n(last [1])\n;;=> 1\n\n(last [])\n;;=> nil\n```"}],
   :full-name "cljs.core/last",
   :docstring "Return the last item in coll, in linear time"},
  "IComparable"
  {:ns "cljs.core",
   :name "IComparable",
   :type "protocol",
   :full-name "cljs.core/IComparable",
   :docstring "Protocol for values that can be compared.",
   :examples-strings [],
   :examples-htmls []},
  "delay"
  {:ns "cljs.core",
   :name "delay",
   :signature ["[& body]"],
   :type "macro",
   :full-name "cljs.core/delay",
   :docstring
   "Takes a body of expressions and yields a Delay object that will\ninvoke the body only the first time it is forced (with force or deref/@), and\nwill cache the result and return it on all subsequent force\ncalls.",
   :examples-strings [],
   :examples-htmls []},
  "hash-string*"
  {:ns "cljs.core",
   :name "hash-string*",
   :signature ["[s]"],
   :type "function",
   :full-name "cljs.core/hash-string*",
   :examples-strings [],
   :examples-htmls []},
  "interpose"
  {:description
   "Returns a lazy seq of the elements of `coll` separated by `sep`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "interpose",
   :signature ["[sep coll]"],
   :type "function",
   :related ["cljs.core/interleave" "clojure.string/join"],
   :examples-strings [],
   :full-name "cljs.core/interpose",
   :docstring
   "Returns a lazy seq of the elements of coll separated by sep.\nReturns a stateful transducer when no collection is provided."},
  "reduceable?"
  {:ns "cljs.core",
   :name "reduceable?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/reduceable?",
   :docstring "Returns true if coll satisfies IReduce",
   :examples-strings [],
   :examples-htmls []},
  "HashMap"
  {:ns "cljs.core",
   :name "HashMap",
   :signature ["[meta count hashobj __hash]"],
   :type "type",
   :full-name "cljs.core/HashMap",
   :examples-strings [],
   :examples-htmls []},
  "<"
  {:description
   "Returns true if each successive number argument is greater than the previous\none, false otherwise.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;&lt; 1 2&#41;\n;;=&gt; true\n\n&#40;&lt; 2 1&#41;\n;;=&gt; false\n\n&#40;&lt; 1 1&#41;\n;;=&gt; false\n\n&#40;&lt; 2 3 4 5 6&#41;\n;;=&gt; true\n</code></pre>"],
   :ns "cljs.core",
   :name "<",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/<="],
   :examples-strings [["(< 1 2)" "(< 2 1)" "(< 1 1)" "(< 2 3 4 5 6)"]],
   :examples
   [{:id "02e6d3",
     :content
     "```clj\n(< 1 2)\n;;=> true\n\n(< 2 1)\n;;=> false\n\n(< 1 1)\n;;=> false\n\n(< 2 3 4 5 6)\n;;=> true\n```"}],
   :full-name "cljs.core/<",
   :docstring
   "Returns non-nil if nums are in monotonically increasing order,\notherwise false."},
  "pos?"
  {:description
   "Returns true if `n` is greater than 0, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "pos?",
   :signature ["[n]"],
   :type "function/macro",
   :related ["cljs.core/neg?" "cljs.core/zero?"],
   :examples-strings [],
   :full-name "cljs.core/pos?",
   :docstring "Returns true if num is greater than zero, else false"},
  "type"
  {:ns "cljs.core",
   :name "type",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/type",
   :docstring "Return x's constructor.",
   :examples-strings [],
   :examples-htmls []},
  "HashCollisionNode"
  {:ns "cljs.core",
   :name "HashCollisionNode",
   :signature ["[edit collision-hash cnt arr]"],
   :type "type",
   :full-name "cljs.core/HashCollisionNode",
   :examples-strings [],
   :examples-htmls []},
  "NodeSeq"
  {:ns "cljs.core",
   :name "NodeSeq",
   :signature ["[meta nodes i s __hash]"],
   :type "type",
   :full-name "cljs.core/NodeSeq",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-substract-int"
  {:ns "cljs.core",
   :name "unchecked-substract-int",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function",
   :full-name "cljs.core/unchecked-substract-int",
   :docstring
   "If no ys are supplied, returns the negation of x, else subtracts\nthe ys from x and returns the result.",
   :examples-strings [],
   :examples-htmls []},
  "print-str"
  {:ns "cljs.core",
   :name "print-str",
   :signature ["[& objs]"],
   :type "function",
   :full-name "cljs.core/print-str",
   :docstring "print to a string, returning it",
   :examples-strings [],
   :examples-htmls []},
  "min-key"
  {:description
   "Returns the `x` for which `(k x)` is least.\n\n`(k x)` should return a number.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "min-key",
   :signature ["[k x]" "[k x y]" "[k x y & more]"],
   :type "function",
   :related ["cljs.core/min" "cljs.core/max-key"],
   :examples-strings [],
   :full-name "cljs.core/min-key",
   :docstring "Returns the x for which (k x), a number, is least."},
  "alength"
  {:description
   "For interop, it returns the length of a JavaScript array or string.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a #js &#91;1 2 3&#93;&#41;\n\n&#40;alength a&#41;\n;;=&gt; 3\n\n&#40;.-length a&#41;\n;;=&gt; 3\n\n&#40;aget a &quot;length&quot;&#41;\n;;=&gt; 3\n\n&#40;count a&#41;\n;;=&gt; 3\n</code></pre>"],
   :ns "cljs.core",
   :name "alength",
   :signature ["[a]"],
   :type "function/macro",
   :related ["cljs.core/count"],
   :examples-strings
   [["(def a #js [1 2 3])"
     "(alength a)"
     "(.-length a)"
     "(aget a \"length\")"
     "(count a)"]],
   :examples
   [{:id "26f79f",
     :content
     "```clj\n(def a #js [1 2 3])\n\n(alength a)\n;;=> 3\n\n(.-length a)\n;;=> 3\n\n(aget a \"length\")\n;;=> 3\n\n(count a)\n;;=> 3\n```"}],
   :full-name "cljs.core/alength",
   :docstring
   "Returns the length of the array. Works on arrays of all types."},
  "."
  {:description
   "For interop, the `.` special form allows access to member properties of the\ngiven JavaScript object `o`.\n\nIf the second operand is a symbol preceded with a hyphen as in `-p`, the\nexpression will result in the value of the property named `p`.\n\nIf the second operand is a symbol that is not preceded with a hyphen as in `m`,\nthe expression will evaluate to a call of the method named `m`.  Any additional\noperands will be passed as arguments to the method.\n\nThe __preferred, idiomatic__ way to access members of a JavaScript object is to\nuse the following sugar:\n\n<table class=\"code-tbl-9bef6\">\n  <thead>\n    <tr>\n      <th>Sugar</th>\n      <th>Expands To</th></tr>\n  </thead>\n  <tbody>\n    <tr>\n      <td><pre>(.-p o)</pre></td>\n      <td><pre>(. o -p)</pre></td>\n    </tr>\n    <tr>\n      <td><pre>(.m o)</pre></td>\n      <td><pre>(. o m)</pre></td>\n    </tr>\n    <tr>\n      <td><pre>(.m o 1 2)</pre></td>\n      <td><pre>(. o m 1 2)</pre></td>\n    </tr>\n  </tbody>\n</table>",
   :examples-htmls
   ["<p>We can access the JavaScript properties of a string:</p><pre><code class=\"js\">// JavaScript\nvar m = &quot;Hello World&quot;;\nm.length;\n//=&gt; 11\n</code></pre><pre><code class=\"clj\">;; ClojureScript\n&#40;def m &quot;Hello World&quot;&#41;\n&#40;.-length m&#41;\n;;=&gt; 11\n</code></pre><p>We can also call member functions on the string:</p><pre><code class=\"js\">// JavaScript\nm.toUpperCase&#40;&#41;;\n//=&gt; &quot;HELLO WORLD&quot;\n\nm.replace&#40;&quot;H&quot;, &quot;&quot;&#41;;\n//=&gt; &quot;ello World&quot;;\n</code></pre><pre><code class=\"clj\">;; ClojureScript\n&#40;.toUpperCase m&#41;\n;;=&gt; &quot;HELLO WORLD&quot;\n\n&#40;.replace m &quot;H&quot; &quot;&quot;&#41;\n;;=&gt; &quot;ello World&quot;\n</code></pre>"
    "<p>Create a JavaScript object <code>o</code>:</p><pre><code class=\"clj\">&#40;def o #js {:foo &quot;bar&quot;}&#41;\n</code></pre><p>You can get the value at property <code>&quot;foo&quot;</code> with any of the following:</p><pre><code class=\"clj\">&#40;. o -foo&#41;\n;;=&gt; &quot;bar&quot;\n\n&#40;.-foo o&#41;\n;;=&gt; &quot;bar&quot;\n\n&#40;aget o &quot;foo&quot;&#41;\n;;=&gt; &quot;bar&quot;\n</code></pre>"],
   :ns "special",
   :name ".",
   :signature ["[o -p]" "[o m]" "[o m 1 2]" "[o (m 1 2)]"],
   :type "special form",
   :related ["cljs.core/.." "cljs.core/aget"],
   :examples-strings
   [["(def m \"Hello World\")"
     "(.-length m)"
     "(.toUpperCase m)"
     "(.replace m \"H\" \"\")"]
    ["(def o #js {:foo \"bar\"})"
     "(. o -foo)"
     "(.-foo o)"
     "(aget o \"foo\")"]],
   :examples
   [{:id "22ccbb",
     :content
     "We can access the JavaScript properties of a string:\n\n```js\n// JavaScript\nvar m = \"Hello World\";\nm.length;\n//=> 11\n```\n\n```clj\n;; ClojureScript\n(def m \"Hello World\")\n(.-length m)\n;;=> 11\n```\n\nWe can also call member functions on the string:\n\n```js\n// JavaScript\nm.toUpperCase();\n//=> \"HELLO WORLD\"\n\nm.replace(\"H\", \"\");\n//=> \"ello World\";\n```\n\n```clj\n;; ClojureScript\n(.toUpperCase m)\n;;=> \"HELLO WORLD\"\n\n(.replace m \"H\" \"\")\n;;=> \"ello World\"\n```"}
    {:id "7c5e58",
     :content
     "Create a JavaScript object `o`:\n\n```clj\n(def o #js {:foo \"bar\"})\n```\n\nYou can get the value at property `\"foo\"` with any of the following:\n\n```clj\n(. o -foo)\n;;=> \"bar\"\n\n(.-foo o)\n;;=> \"bar\"\n\n(aget o \"foo\")\n;;=> \"bar\"\n```"}],
   :full-name "special/.",
   :docstring
   "The instance member form works for methods and fields.\nThey all expand into calls to the dot operator at macroexpansion time."},
  "make-array"
  {:description "Creates an empty JavaScript array of size `size`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "make-array",
   :signature ["[size]"],
   :type "function/macro",
   :related ["cljs.core/aclone" "cljs.core/array"],
   :examples-strings [],
   :full-name "cljs.core/make-array",
   :docstring
   "Construct a JavaScript array of specified size. Accepts ignored type\nargument for compatibility with Clojure."},
  "alter-meta!"
  {:description
   "Alter the metadata of `data` to be `(apply f its-current-meta args)`.\n\nMetadata of vars cannot be altered since they are statically determined at compile-time.",
   :examples-htmls
   ["<p>Metadata of symbols and collections can be altered:</p><pre><code class=\"clj\">&#40;def a &#94;:foo &#91;1 2 3&#93;&#41;\n&#40;meta a&#41;\n;;=&gt; {:foo true}\n\n&#40;alter-meta! a assoc :bar true&#41;\n&#40;meta a&#41;\n;;=&gt; {:foo true, :bar true}\n</code></pre><p>Metadata of vars cannot be altered:</p><pre><code class=\"clj\">&#40;def a &#91;1 2 3&#93;&#41;\n&#40;meta #'a&#41;\n;;=&gt; {:arglists &#40;&#41;, :test nil, :name a, :column 1, :line 1, :file &quot;&lt;cljs repl&gt;&quot;, :doc nil, :ns cljs.user}\n\n&#40;alter-meta! #'a assoc :bar true&#41;\n&#40;:bar &#40;meta #'a&#41;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "alter-meta!",
   :signature ["[data f & args]"],
   :type "function",
   :related ["cljs.core/with-meta" "cljs.core/vary-meta"],
   :examples-strings
   [["(def a ^:foo [1 2 3])"
     "(meta a)"
     "(alter-meta! a assoc :bar true)"
     "(meta a)"
     "(def a [1 2 3])"
     "(meta #'a)"
     "(alter-meta! #'a assoc :bar true)"
     "(:bar (meta #'a))"]],
   :examples
   [{:id "8378a0",
     :content
     "Metadata of symbols and collections can be altered:\n\n```clj\n(def a ^:foo [1 2 3])\n(meta a)\n;;=> {:foo true}\n\n(alter-meta! a assoc :bar true)\n(meta a)\n;;=> {:foo true, :bar true}\n```\n\nMetadata of vars cannot be altered:\n\n```clj\n(def a [1 2 3])\n(meta #'a)\n;;=> {:arglists (), :test nil, :name a, :column 1, :line 1, :file \"<cljs repl>\", :doc nil, :ns cljs.user}\n\n(alter-meta! #'a assoc :bar true)\n(:bar (meta #'a))\n;;=> nil\n```"}],
   :full-name "cljs.core/alter-meta!",
   :docstring
   "Atomically sets the metadata for a namespace/var/ref/agent/atom to be:\n\n(apply f its-current-meta args)\n\nf must be free of side-effects"},
  "es6-set-entries-iterator"
  {:ns "cljs.core",
   :name "es6-set-entries-iterator",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/es6-set-entries-iterator",
   :examples-strings [],
   :examples-htmls []},
  "conj"
  {:description
   "conj(oin)\n\nReturns a new collection with the `x`s \"added\" to `coll`.\n\nThe \"addition\" may happen at different \"places\" depending on the collection\ntype.\n\n`(conj nil item)` returns `(item)`.",
   :examples-htmls
   ["<p>Append a vector:</p><pre><code class=\"clj\">&#40;conj &#91;1 2 3&#93; 4&#41;\n;;=&gt; &#91;1 2 3 4&#93;\n</code></pre><p>Prepend a list:</p><pre><code class=\"clj\">&#40;conj &#40;list 1 2 3&#41; 0&#41;\n;;=&gt; &#40;0 1 2 3&#41;\n</code></pre><p>Prepend a sequence:</p><pre><code class=\"clj\">&#40;def x &#40;range 1 4&#41;&#41;\n;;=&gt; &#40;1 2 3&#41;\n\n&#40;conj x 0&#41;\n;;=&gt; &#40;0 1 2 3&#41;\n</code></pre><p>Add to set:</p><pre><code class=\"clj\">&#40;conj #{&quot;a&quot; &quot;b&quot; &quot;c&quot;} &quot;d&quot;&#41;\n;;=&gt; #{&quot;a&quot; &quot;b&quot; &quot;c&quot; &quot;d&quot;}\n</code></pre>"],
   :ns "cljs.core",
   :name "conj",
   :signature ["[]" "[coll]" "[coll x]" "[coll x & xs]"],
   :type "function",
   :related
   ["cljs.core/cons"
    "cljs.core/into"
    "cljs.core/peek"
    "cljs.core/pop"],
   :examples-strings
   [["(conj [1 2 3] 4)"
     "(conj (list 1 2 3) 0)"
     "(def x (range 1 4))"
     "(conj x 0)"
     "(conj #{\"a\" \"b\" \"c\"} \"d\")"]],
   :examples
   [{:id "8c2a84",
     :content
     "Append a vector:\n\n```clj\n(conj [1 2 3] 4)\n;;=> [1 2 3 4]\n```\n\nPrepend a list:\n\n```clj\n(conj (list 1 2 3) 0)\n;;=> (0 1 2 3)\n```\n\nPrepend a sequence:\n\n```clj\n(def x (range 1 4))\n;;=> (1 2 3)\n\n(conj x 0)\n;;=> (0 1 2 3)\n```\n\nAdd to set:\n\n```clj\n(conj #{\"a\" \"b\" \"c\"} \"d\")\n;;=> #{\"a\" \"b\" \"c\" \"d\"}\n```"}],
   :full-name "cljs.core/conj",
   :docstring
   "conj[oin]. Returns a new collection with the xs\n'added'. (conj nil item) returns (item).  The 'addition' may\nhappen at different 'places' depending on the concrete type."},
  "ASeq"
  {:ns "cljs.core",
   :name "ASeq",
   :type "protocol",
   :full-name "cljs.core/ASeq",
   :docstring "Marker protocol indicating an array sequence.",
   :examples-strings [],
   :examples-htmls []},
  "List"
  {:ns "cljs.core",
   :name "List",
   :signature ["[meta first rest count __hash]"],
   :type "type",
   :full-name "cljs.core/List",
   :examples-strings [],
   :examples-htmls []},
  "ObjMap.fromObject"
  {:ns "cljs.core",
   :name "ObjMap.fromObject",
   :signature ["[ks obj]"],
   :type "function",
   :full-name "cljs.core/ObjMap.fromObject",
   :examples-strings [],
   :examples-htmls []},
  "check-string-hash-cache"
  {:ns "cljs.core",
   :name "check-string-hash-cache",
   :signature ["[k]"],
   :type "function",
   :full-name "cljs.core/check-string-hash-cache",
   :examples-strings [],
   :examples-htmls []},
  "clj->js"
  {:description
   "Recursively transforms ClojureScript values to JavaScript.\n\n| ClojureScript |        | JavaScript |         |\n|---------------|--------|------------|---------|\n| Set           | `#{}`  | Array      | `[]`    |\n| Vector        | `[]`   | Array      | `[]`    |\n| List          | `()`   | Array      | `[]`    |\n| Keyword       | `:foo` | String     | `\"foo\"` |\n| Symbol        | `bar`  | String     | `\"bar\"` |\n| Map           | `{}`   | Object     | `{}`    |",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;clj-&gt;js {:foo 1 :bar 2}&#41;\n;;=&gt; #js {:foo 1, :bar 2}\n\n&#40;clj-&gt;js &#91;:foo &quot;bar&quot; 'baz&#93;&#41;\n;;=&gt; #js &#91;&quot;foo&quot; &quot;bar&quot; &quot;baz&quot;&#93;\n\n&#40;clj-&gt;js &#91;1 {:foo &quot;bar&quot;} 4&#93;&#41;\n;;=&gt; #js &#91;1 #js {:foo &quot;bar&quot;} 4&#93;\n</code></pre>"],
   :ns "cljs.core",
   :name "clj->js",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/js->clj"],
   :examples-strings
   [["(clj->js {:foo 1 :bar 2})"
     "(clj->js [:foo \"bar\" 'baz])"
     "(clj->js [1 {:foo \"bar\"} 4])"]],
   :examples
   [{:id "2b1057",
     :content
     "```clj\n(clj->js {:foo 1 :bar 2})\n;;=> #js {:foo 1, :bar 2}\n\n(clj->js [:foo \"bar\" 'baz])\n;;=> #js [\"foo\" \"bar\" \"baz\"]\n\n(clj->js [1 {:foo \"bar\"} 4])\n;;=> #js [1 #js {:foo \"bar\"} 4]\n```"}],
   :full-name "cljs.core/clj->js",
   :docstring
   "Recursively transforms ClojureScript values to JavaScript.\nsets/vectors/lists become Arrays, Keywords and Symbol become Strings,\nMaps become Objects. Arbitrary keys are encoded to by key->js."},
  "double"
  {:ns "cljs.core",
   :name "double",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/double",
   :examples-strings [],
   :examples-htmls []},
  "IPrintWithWriter"
  {:ns "cljs.core",
   :name "IPrintWithWriter",
   :type "protocol",
   :full-name "cljs.core/IPrintWithWriter",
   :docstring
   "The old IPrintable protocol's implementation consisted of building a giant\n   list of strings to concatenate.  This involved lots of concat calls,\n   intermediate vectors, and lazy-seqs, and was very slow in some older JS\n   engines.  IPrintWithWriter implements printing via the IWriter protocol, so it\n   be implemented efficiently in terms of e.g. a StringBuffer append.",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-multiply"
  {:ns "cljs.core",
   :name "unchecked-multiply",
   :signature ["[]" "[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-multiply",
   :docstring "Returns the product of nums. (*) returns 1.",
   :examples-strings [],
   :examples-htmls []},
  "DEMUNGE_PATTERN"
  {:ns "cljs.core",
   :name "DEMUNGE_PATTERN",
   :type "var",
   :full-name "cljs.core/DEMUNGE_PATTERN",
   :examples-strings [],
   :examples-htmls []},
  "IChunkedSeq"
  {:ns "cljs.core",
   :name "IChunkedSeq",
   :type "protocol",
   :full-name "cljs.core/IChunkedSeq",
   :docstring
   "Protocol for accessing a collection as sequential chunks.",
   :examples-strings [],
   :examples-htmls []},
  "tree-seq"
  {:description
   "Returns a lazy sequence of the nodes in a tree, via a depth-first walk.\n\n`branch?` must be a function of one argument that returns true if passed a node\nthat can have children (but may not).\n\n`children` must be a function of one argument that returns a sequence of the\nchildren. `children` will only be called on nodes for which `branch?` returns\ntrue.\n\n`root` is the root node of the tree.",
   :ns "cljs.core",
   :name "tree-seq",
   :signature ["[branch? children root]"],
   :type "function",
   :full-name "cljs.core/tree-seq",
   :docstring
   "Returns a lazy sequence of the nodes in a tree, via a depth-first walk.\n branch? must be a fn of one arg that returns true if passed a node\n that can have children (but may not).  children must be a fn of one\n arg that returns a sequence of the children. Will only be called on\n nodes for which branch? returns true. Root is the root node of the\ntree.",
   :examples-strings [],
   :examples-htmls []},
  "meta"
  {:ns "cljs.core",
   :name "meta",
   :signature ["[o]"],
   :type "function",
   :full-name "cljs.core/meta",
   :docstring
   "Returns the metadata of obj, returns nil if there is no metadata.",
   :examples-strings [],
   :examples-htmls []},
  "defonce"
  {:ns "cljs.core",
   :name "defonce",
   :signature ["[x init]"],
   :type "macro",
   :full-name "cljs.core/defonce",
   :examples-strings [],
   :examples-htmls []},
  "array-list"
  {:ns "cljs.core",
   :name "array-list",
   :signature ["[]"],
   :type "function",
   :full-name "cljs.core/array-list",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-subtract-int"
  {:ns "cljs.core",
   :name "unchecked-subtract-int",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-subtract-int",
   :docstring
   "If no ys are supplied, returns the negation of x, else subtracts\nthe ys from x and returns the result.",
   :examples-strings [],
   :examples-htmls []},
  "IIndexed"
  {:ns "cljs.core",
   :name "IIndexed",
   :type "protocol",
   :full-name "cljs.core/IIndexed",
   :docstring
   "Protocol for collections to provide idexed-based access to their items.",
   :examples-strings [],
   :examples-htmls []},
  "RangeIterator"
  {:ns "cljs.core",
   :name "RangeIterator",
   :signature ["[i end step]"],
   :type "type",
   :full-name "cljs.core/RangeIterator",
   :examples-strings [],
   :examples-htmls []},
  "TransientHashMap"
  {:ns "cljs.core",
   :name "TransientHashMap",
   :signature ["[edit root count has-nil? nil-val]"],
   :type "type",
   :full-name "cljs.core/TransientHashMap",
   :examples-strings [],
   :examples-htmls []},
  "divide"
  {:ns "cljs.core",
   :name "divide",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "macro",
   :full-name "cljs.core/divide",
   :examples-strings [],
   :examples-htmls []},
  "clone"
  {:ns "cljs.core",
   :name "clone",
   :signature ["[value]"],
   :type "function",
   :full-name "cljs.core/clone",
   :docstring
   "Clone the supplied value which must implement ICloneable.",
   :examples-strings [],
   :examples-htmls []},
  "*main-cli-fn*"
  {:ns "cljs.core",
   :name "*main-cli-fn*",
   :type "var",
   :full-name "cljs.core/*main-cli-fn*",
   :docstring
   "When compiled for a command-line target, whatever function\n*main-cli-fn* is set to will be called with the command-line\nargv as arguments",
   :examples-strings [],
   :examples-htmls []},
  "PersistentVector.EMPTY_NODE"
  {:ns "cljs.core",
   :name "PersistentVector.EMPTY_NODE",
   :type "var",
   :full-name "cljs.core/PersistentVector.EMPTY_NODE",
   :examples-strings [],
   :examples-htmls []},
  "write-all"
  {:ns "cljs.core",
   :name "write-all",
   :signature ["[writer & ss]"],
   :type "function",
   :full-name "cljs.core/write-all",
   :examples-strings [],
   :examples-htmls []},
  "IIterable"
  {:ns "cljs.core",
   :name "IIterable",
   :type "protocol",
   :full-name "cljs.core/IIterable",
   :docstring "Protocol for iterating over a collection.",
   :examples-strings [],
   :examples-htmls []},
  "hash-string"
  {:ns "cljs.core",
   :name "hash-string",
   :signature ["[k]"],
   :type "function",
   :full-name "cljs.core/hash-string",
   :examples-strings [],
   :examples-htmls []},
  "StringBufferWriter"
  {:ns "cljs.core",
   :name "StringBufferWriter",
   :signature ["[sb]"],
   :type "type",
   :full-name "cljs.core/StringBufferWriter",
   :examples-strings [],
   :examples-htmls []},
  "while"
  {:description
   "Repeatedly executes `body` while `test` expression is true. Presumes some\nside-effect will cause `test` to become false or nil.\n\nReturns nil.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "while",
   :signature ["[test & body]"],
   :type "macro",
   :related ["cljs.core/loop"],
   :examples-strings [],
   :full-name "cljs.core/while",
   :docstring
   "Repeatedly executes body while test expression is true. Presumes\nsome side-effect will cause test to become false/nil. Returns nil"},
  "PersistentVector.fromArray"
  {:ns "cljs.core",
   :name "PersistentVector.fromArray",
   :signature ["[xs no-clone]"],
   :type "function",
   :full-name "cljs.core/PersistentVector.fromArray",
   :examples-strings [],
   :examples-htmls []},
  "set-print-fn!"
  {:ns "cljs.core",
   :name "set-print-fn!",
   :signature ["[f]"],
   :type "function",
   :full-name "cljs.core/set-print-fn!",
   :docstring "Set *print-fn* to f.",
   :examples-strings [],
   :examples-htmls []},
  "floats"
  {:ns "cljs.core",
   :name "floats",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/floats",
   :examples-strings [],
   :examples-htmls []},
  "reset-meta!"
  {:ns "cljs.core",
   :name "reset-meta!",
   :signature ["[iref m]"],
   :type "function",
   :full-name "cljs.core/reset-meta!",
   :docstring "Atomically resets the metadata for an atom",
   :examples-strings [],
   :examples-htmls []},
  "bit-shift-right"
  {:description
   "Bitwise shift right `n` bits.  Same as `x >> n` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-shift-right 2r1010 1&#41;\n;;=&gt; 5\n;; 5 = 2r0101\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-shift-right 10 1&#41;\n;;=&gt; 5\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-shift-right",
   :signature ["[x n]"],
   :type "function/macro",
   :related
   ["cljs.core/bit-shift-left" "cljs.core/unsigned-bit-shift-right"],
   :examples-strings
   [["(bit-shift-right 2r1010 1)" "(bit-shift-right 10 1)"]],
   :examples
   [{:id "5b75af",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-shift-right 2r1010 1)\n;;=> 5\n;; 5 = 2r0101\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-shift-right 10 1)\n;;=> 5\n```"}],
   :full-name "cljs.core/bit-shift-right",
   :docstring "Bitwise shift right"},
  "->>"
  {:description
   "The thread-last macro \"threads\" an expression through several forms as the last\nitem in a list.\n\nInserts `x` as the last item in the first form, making a list of it if it is not\na list already. If there are more forms, inserts the first form as the last item\nin second form, etc.\n\n<table class=\"code-tbl-9bef6\">\n  <thead>\n    <tr>\n      <th>Code</th>\n      <th>Expands To</th></tr></thead>\n  <tbody>\n    <tr>\n      <td><pre>\n(->> x\n  (a b c)\n  d\n  (x y z))</pre></td>\n      <td><pre>\n(x y z (d (a b c x)))</pre></td></tr></tbody></table>",
   :examples-htmls
   ["<p>Sequence transformation functions often take a sequence as the last argument, thus the thread-last macro is commonly used with them.  Here we compute the sum of the first 10 even squares:</p><pre><code class=\"clj\">&#40;-&gt;&gt; &#40;range&#41;\n     &#40;map #&#40;&#42; % %&#41;&#41;\n     &#40;filter even?&#41;\n     &#40;take 10&#41;\n     &#40;reduce +&#41;&#41;\n;;=&gt; 1140\n</code></pre><p>This expands to:</p><pre><code class=\"clj\">&#40;reduce +\n  &#40;take 10\n    &#40;filter even?\n      &#40;map #&#40;&#42; % %&#41;\n        &#40;range&#41;&#41;&#41;&#41;&#41;\n;;=&gt; 1140\n</code></pre>"],
   :ns "cljs.core",
   :name "->>",
   :signature ["[x & forms]"],
   :type "macro",
   :related ["cljs.core/->"],
   :examples-strings
   [["(->> (range)\n     (map #(* % %))\n     (filter even?)\n     (take 10)\n     (reduce +))"
     "(reduce +\n  (take 10\n    (filter even?\n      (map #(* % %)\n        (range)))))"]],
   :examples
   [{:id "1dc72c",
     :content
     "Sequence transformation functions often take a sequence as the last argument,\nthus the thread-last macro is commonly used with them.  Here we compute the sum\nof the first 10 even squares:\n\n```clj\n(->> (range)\n     (map #(* % %))\n     (filter even?)\n     (take 10)\n     (reduce +))\n;;=> 1140\n```\n\nThis expands to:\n\n```clj\n(reduce +\n  (take 10\n    (filter even?\n      (map #(* % %)\n        (range)))))\n;;=> 1140\n```"}],
   :full-name "cljs.core/->>",
   :docstring
   "Threads the expr through the forms. Inserts x as the\nlast item in the first form, making a list of it if it is not a\nlist already. If there are more forms, inserts the first form as the\nlast item in second form, etc."},
  "print"
  {:ns "cljs.core",
   :name "print",
   :type "function",
   :full-name "cljs.core/print",
   :docstring
   "Prints the object(s) using string-print.\nprint and println produce output for human consumption.",
   :examples-strings [],
   :examples-htmls []},
  "*print-length*"
  {:ns "cljs.core",
   :name "*print-length*",
   :type "dynamic var",
   :full-name "cljs.core/*print-length*",
   :docstring
   "When set to logical true, objects will be printed in a way that preserves\ntheir type when read in later.\n\nDefaults to false.",
   :examples-strings [],
   :examples-htmls []},
  "IAssociative"
  {:ns "cljs.core",
   :name "IAssociative",
   :type "protocol",
   :full-name "cljs.core/IAssociative",
   :docstring "Protocol for adding associativity to collections.",
   :examples-strings [],
   :examples-htmls []},
  "instance?"
  {:description
   "Returns true if `o` is an instance of type `t`, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "instance?",
   :signature ["[t o]"],
   :type "function/macro",
   :related ["cljs.core/type"],
   :examples-strings [],
   :full-name "cljs.core/instance?",
   :docstring
   "Evaluates x and tests if it is an instance of the type\nc. Returns true or false"},
  "volatile!"
  {:ns "cljs.core",
   :name "volatile!",
   :signature ["[val]"],
   :type "function",
   :related
   ["cljs.core/volatile?"
    "cljs.core/vswap!"
    "cljs.core/vreset!"
    "cljs.core/Volatile"],
   :full-name "cljs.core/volatile!",
   :docstring
   "Creates and returns a Volatile with an initial value of val.",
   :examples-strings [],
   :examples-htmls []},
  "IAtom"
  {:ns "cljs.core",
   :name "IAtom",
   :type "protocol",
   :full-name "cljs.core/IAtom",
   :docstring "Marker protocol indicating an atom.",
   :examples-strings [],
   :examples-htmls []},
  "stepper"
  {:ns "cljs.core",
   :name "stepper",
   :signature ["[xform iter]"],
   :type "function",
   :full-name "cljs.core/stepper",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-multiply-int"
  {:ns "cljs.core",
   :name "unchecked-multiply-int",
   :signature ["[]" "[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-multiply-int",
   :docstring "Returns the product of nums. (*) returns 1.",
   :examples-strings [],
   :examples-htmls []},
  "quote"
  {:ns "special",
   :name "quote",
   :signature ["[form]"],
   :type "special form",
   :full-name "special/quote",
   :docstring "Yields the unevaluated form.",
   :examples-strings [],
   :examples-htmls []},
  "array-seq"
  {:description
   "Creates a `seq` from a JavaScript array, starting at index `i` if given.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;array-seq #js &#91;1 2 3&#93;&#41;\n;;=&gt; &#40;1 2 3&#41;\n\n&#40;array-seq #js &#91;1 2 3&#93; 1&#41;\n;;=&gt; &#40;2 3&#41;\n</code></pre>"],
   :ns "cljs.core",
   :name "array-seq",
   :signature ["[array]" "[array i]"],
   :type "function",
   :examples-strings
   [["(array-seq #js [1 2 3])" "(array-seq #js [1 2 3] 1)"]],
   :examples
   [{:id "9ef6de",
     :content
     "```clj\n(array-seq #js [1 2 3])\n;;=> (1 2 3)\n\n(array-seq #js [1 2 3] 1)\n;;=> (2 3)\n```"}],
   :full-name "cljs.core/array-seq",
   :docstring "Create a seq from a JavaScript array."},
  "memoize"
  {:description
   "Returns a memoized version of a referentially transparent function.\n\nA memoized version of a function keeps a cache of the mappings from arguments to\nresults in memory. When calls with the same arguments are repeated often, a\nmemoized function has higher performance at the expense of higher memory usage.",
   :ns "cljs.core",
   :name "memoize",
   :signature ["[f]"],
   :type "function",
   :full-name "cljs.core/memoize",
   :docstring
   "Returns a memoized version of a referentially transparent function. The\nmemoized version of the function keeps a cache of the mapping from arguments\nto results and, when calls with the same arguments are repeated often, has\nhigher performance at the expense of higher memory use.",
   :examples-strings [],
   :examples-htmls []},
  "array-map"
  {:description
   "Returns a new array map (a map implemented with arrays) with the supplied mappings.\n\n`keyvals` must be an even number of forms.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;array-map :a 10&#41;\n;;=&gt; {:a 10}\n\n&#40;array-map :a 10 :b 20&#41;\n;;=&gt; {:a 10 :b 20}\n</code></pre>"],
   :ns "cljs.core",
   :name "array-map",
   :signature ["[& keyvals]"],
   :type "function/macro",
   :related
   ["cljs.core/assoc" "cljs.core/hash-map" "cljs.core/sorted-map"],
   :examples-strings [["(array-map :a 10)" "(array-map :a 10 :b 20)"]],
   :examples
   [{:id "198026",
     :content
     "```clj\n(array-map :a 10)\n;;=> {:a 10}\n\n(array-map :a 10 :b 20)\n;;=> {:a 10 :b 20}\n```"}],
   :full-name "cljs.core/array-map",
   :docstring
   "keyval => key val\nReturns a new array map with supplied mappings."},
  "defprotocol"
  {:ns "cljs.core",
   :name "defprotocol",
   :signature ["[psym & doc+methods]"],
   :type "macro",
   :full-name "cljs.core/defprotocol",
   :docstring
   "A protocol is a named set of named methods and their signatures:\n\n(defprotocol AProtocolName\n  ;optional doc string\n  \"A doc string for AProtocol abstraction\"\n\n;method signatures\n  (bar [this a b] \"bar docs\")\n  (baz [this a] [this a b] [this a b c] \"baz docs\"))\n\nNo implementations are provided. Docs can be specified for the\nprotocol overall and for each method. The above yields a set of\npolymorphic functions and a protocol object. All are\nnamespace-qualified by the ns enclosing the definition The resulting\nfunctions dispatch on the type of their first argument, which is\nrequired and corresponds to the implicit target object ('this' in\nJavaScript parlance). defprotocol is dynamic, has no special compile-time\neffect, and defines no new types.\n\n(defprotocol P\n  (foo [this])\n  (bar-me [this] [this y]))\n\n(deftype Foo [a b c]\n  P\n  (foo [this] a)\n  (bar-me [this] b)\n  (bar-me [this y] (+ c y)))\n\n(bar-me (Foo. 1 2 3) 42)\n=> 45\n\n(foo\n  (let [x 42]\n    (reify P\n      (foo [this] 17)\n      (bar-me [this] x)\n      (bar-me [this y] x))))\n=> 17",
   :examples-strings [],
   :examples-htmls []},
  "add-watch"
  {:description
   "Adds a watch function `f` to atom `a` that will execute when the value of `a`\nchanges.\n\nThe watch function takes 4 arguments: a key, the atom, its old state, and its\nnew state.\n\n`key` should be a keyword and can be used with `remove-watch` to remove the\nwatch function.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a &#40;atom {}&#41;&#41;\n\n&#40;add-watch a :logger\n  &#40;fn &#91;&#95;key &#95;atom old-state new-state&#93;\n    &#40;println &quot;old:&quot; old-state&#41;\n    &#40;println &quot;new:&quot; new-state&#41;&#41;&#41;\n\n&#40;swap! a assoc :foo &quot;bar&quot;&#41;\n;;=&gt; will print the following:\n;; old: {}\n;; new: {:foo &quot;bar&quot;}\n</code></pre>"],
   :ns "cljs.core",
   :name "add-watch",
   :signature ["[a key f]"],
   :type "function",
   :related ["cljs.core/remove-watch"],
   :examples-strings
   [["(def a (atom {}))"
     "(add-watch a :logger\n  (fn [_key _atom old-state new-state]\n    (println \"old:\" old-state)\n    (println \"new:\" new-state)))"
     "(swap! a assoc :foo \"bar\")"]],
   :examples
   [{:id "2f2fe0",
     :content
     "```clj\n(def a (atom {}))\n\n(add-watch a :logger\n  (fn [_key _atom old-state new-state]\n    (println \"old:\" old-state)\n    (println \"new:\" new-state)))\n\n(swap! a assoc :foo \"bar\")\n;;=> will print the following:\n;; old: {}\n;; new: {:foo \"bar\"}\n```"}],
   :full-name "cljs.core/add-watch",
   :docstring
   "Alpha - subject to change.\n\nAdds a watch function to an atom reference. The watch fn must be a\nfn of 4 args: a key, the reference, its old-state, its\nnew-state. Whenever the reference's state might have been changed,\nany registered watches will have their functions called. The watch\nfn will be called synchronously. Note that an atom's state\nmay have changed again prior to the fn call, so use old/new-state\nrather than derefing the reference. Keys must be unique per\nreference, and can be used to remove the watch with remove-watch,\nbut are otherwise considered opaque by the watch mechanism.  Bear in\nmind that regardless of the result or action of the watch fns the\natom's value will change.  Example:\n\n    (def a (atom 0))\n    (add-watch a :inc (fn [k r o n] (assert (== 0 n))))\n    (swap! a inc)\n    ;; Assertion Error\n    (deref a)\n    ;=> 1"},
  "zero?"
  {:description "Returns true if `n` is 0, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "zero?",
   :signature ["[n]"],
   :type "function/macro",
   :related ["cljs.core/pos?" "cljs.core/neg?"],
   :examples-strings [],
   :full-name "cljs.core/zero?",
   :docstring "Returns true if num is zero, else false"},
  "ITransientAssociative"
  {:ns "cljs.core",
   :name "ITransientAssociative",
   :type "protocol",
   :full-name "cljs.core/ITransientAssociative",
   :docstring
   "Protocol for adding associativity to transient collections.",
   :examples-strings [],
   :examples-htmls []},
  "extend-type"
  {:description
   "Extend a [type] to implement one or more [protocols].\n\n`type-sym` can be the result of a [doc:cljs.core/deftype] or any JS constructor\nfunction (e.g. `js/Date`).  But when targetting JS base types (e.g.\n`js/Number`, `js/String`), you must use special _type symbols_ instead.  These\ntype symbols are associated with type strings deduced by [`goog/typeOf`]:\n\n| type symbol  | corresponding `goog/typeOf` value |\n|--------------|-------------|\n| `nil`        | `\"null\"` |\n| `object`     | `\"object\"` |\n| `string`     | `\"string\"` |\n| `number`     | `\"number\"` |\n| `array`      | `\"array\"` |\n| `function`   | `\"function\"` |\n| `boolean`    | `\"boolean\"` |\n\n`type-sym` can also be specified as `default` in order to provide default\nimplementations for protocols.\n\n[`goog/typeOf`]:http://google.github.io/closure-library/api/namespace_goog.html#typeOf",
   :examples-htmls [],
   :ns "cljs.core",
   :name "extend-type",
   :signature ["[type-sym & impls]"],
   :type "macro",
   :related ["cljs.core/extend-protocol"],
   :examples-strings [],
   :full-name "cljs.core/extend-type",
   :docstring
   "Extend a type to a series of protocols. Useful when you are\n supplying the definitions explicitly inline. Propagates the\n type as a type hint on the first argument of all fns.\n\n(extend-type MyType\n  ICounted\n  (-count [c] ...)\n  Foo\n  (bar [x y] ...)\n  (baz ([x] ...) ([x y & zs] ...))"},
  "make-hierarchy"
  {:ns "cljs.core",
   :name "make-hierarchy",
   :signature ["[]"],
   :type "function",
   :related
   ["cljs.core/ancestors"
    "cljs.core/descendants"
    "cljs.core/isa?"
    "cljs.core/derive"],
   :full-name "cljs.core/make-hierarchy",
   :docstring
   "Creates a hierarchy object for use with derive, isa? etc.",
   :examples-strings [],
   :examples-htmls []},
  "array-iter"
  {:ns "cljs.core",
   :name "array-iter",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/array-iter",
   :examples-strings [],
   :examples-htmls []},
  "defn-"
  {:description
   "Same as `defn`, but adds `{:private true}` metadata to the definition.\n\nNote: `:private` metadata is not currently enforced by the ClojureScript\ncompiler.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "defn-",
   :signature ["[name & decls]"],
   :type "macro",
   :related ["cljs.core/defn"],
   :examples-strings [],
   :full-name "cljs.core/defn-",
   :docstring "same as defn, yielding non-public def"},
  "IVector"
  {:ns "cljs.core",
   :name "IVector",
   :type "protocol",
   :full-name "cljs.core/IVector",
   :docstring
   "Protocol for adding vector functionality to collections.",
   :examples-strings [],
   :examples-htmls []},
  "set"
  {:description "Returns a set of the distinct elements of `coll`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "set",
   :signature ["[coll]"],
   :type "function",
   :related
   ["cljs.core/hash-set"
    "cljs.core/sorted-set"
    "cljs.core/conj"
    "cljs.core/disj"
    "cljs.core/distinct"
    "clojure.set/join"
    "clojure.set/select"
    "clojure.set/difference"
    "clojure.set/intersection"
    "clojure.set/union"
    "clojure.set/index"
    "clojure.set/project"
    "clojure.set/rename"
    "clojure.set/rename-keys"
    "clojure.set/map-invert"],
   :examples-strings [],
   :full-name "cljs.core/set",
   :docstring "Returns a set of the distinct elements of coll."},
  "double-array"
  {:ns "cljs.core",
   :name "double-array",
   :signature ["[size-or-seq]" "[size init-val-or-seq]"],
   :type "function",
   :full-name "cljs.core/double-array",
   :docstring
   "Creates an array of doubles. Does not coerce array, provided for compatibility\nwith Clojure.",
   :examples-strings [],
   :examples-htmls []},
  "*3"
  {:description
   "Only usable from a REPL.\n\nHolds the result of the third to last expression.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;+ 1 2 3 4&#41;\n;;=&gt; 10\n\n&#40;+ 4 8&#41;\n;;=&gt; 12\n\n&#40;+ 1 2&#41;\n;;=&gt; 3\n\n&#42;3\n;;=&gt; 10\n\n&#40;inc &#42;3&#41;\n;;=&gt; 11\n</code></pre><p>Note that a standalone evaluation of <code>&#42;1</code>, <code>&#42;2</code>, <code>&#42;3</code>, or <code>&#42;e</code> is not a part of remembered history:</p><pre><code class=\"clj\">:first\n;;=&gt; :first\n\n:second\n;;=&gt; :second\n\n:third\n;;=&gt; :third\n\n&#42;3\n;;=&gt; :first\n\n&#42;2\n;;=&gt; :second\n\n&#42;1\n;;=&gt; :third\n</code></pre>"],
   :ns "cljs.core",
   :name "*3",
   :type "var",
   :related ["cljs.core/*1" "cljs.core/*2" "cljs.core/*e"],
   :examples-strings
   [["(+ 1 2 3 4)"
     "(+ 4 8)"
     "(+ 1 2)"
     "*3"
     "(inc *3)"
     ":first\n:second\n:third\n*3\n*2\n*1"]],
   :examples
   [{:id "d7a6e9",
     :content
     "```clj\n(+ 1 2 3 4)\n;;=> 10\n\n(+ 4 8)\n;;=> 12\n\n(+ 1 2)\n;;=> 3\n\n*3\n;;=> 10\n\n(inc *3)\n;;=> 11\n```\n\nNote that a standalone evaluation of `*1`, `*2`, `*3`, or `*e` is not a part of\nremembered history:\n\n```clj\n:first\n;;=> :first\n\n:second\n;;=> :second\n\n:third\n;;=> :third\n\n*3\n;;=> :first\n\n*2\n;;=> :second\n\n*1\n;;=> :third\n```"}],
   :full-name "cljs.core/*3",
   :docstring
   "bound in a repl thread to the third most recent value printed"},
  "string-hash-cache"
  {:ns "cljs.core",
   :name "string-hash-cache",
   :type "var",
   :full-name "cljs.core/string-hash-cache",
   :examples-strings [],
   :examples-htmls []},
  "unchecked-inc-int"
  {:ns "cljs.core",
   :name "unchecked-inc-int",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-inc-int",
   :examples-strings [],
   :examples-htmls []},
  "some"
  {:description
   "Returns the first logical true value of `(pred x)` for any `x` in `coll`, else\nnil.\n\nA common idiom is to use a set as pred, for example this will return `:fred` if\n`:fred` is in the sequence, otherwise nil: `(some #{:fred} coll)`",
   :examples-htmls [],
   :ns "cljs.core",
   :name "some",
   :signature ["[pred coll]"],
   :type "function",
   :related
   ["cljs.core/every?"
    "cljs.core/not-any?"
    "cljs.core/keep"
    "cljs.core/keep-indexed"
    "cljs.core/some-fn"],
   :examples-strings [],
   :full-name "cljs.core/some",
   :docstring
   "Returns the first logical true value of (pred x) for any x in coll,\nelse nil.  One common idiom is to use a set as pred, for example\nthis will return :fred if :fred is in the sequence, otherwise nil:\n(some #{:fred} coll)"},
  "PersistentHashSet.fromArray"
  {:ns "cljs.core",
   :name "PersistentHashSet.fromArray",
   :signature ["[items no-clone]"],
   :type "function",
   :full-name "cljs.core/PersistentHashSet.fromArray",
   :examples-strings [],
   :examples-htmls []},
  "var?"
  {:ns "cljs.core",
   :name "var?",
   :signature ["[v]"],
   :type "function",
   :full-name "cljs.core/var?",
   :docstring "Returns true if v is of type cljs.core.Var",
   :examples-strings [],
   :examples-htmls []},
  "INext"
  {:ns "cljs.core",
   :name "INext",
   :type "protocol",
   :full-name "cljs.core/INext",
   :docstring "Protocol for accessing the next items of a collection.",
   :examples-strings [],
   :examples-htmls []},
  "fn?"
  {:description "Returns true if `f` is a function, false otherwise.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "fn?",
   :signature ["[f]"],
   :type "function",
   :related ["cljs.core/ifn?"],
   :examples-strings [],
   :full-name "cljs.core/fn?",
   :docstring
   "Return true if f is a JavaScript function or satisfies the Fn protocol."},
  "IReset"
  {:ns "cljs.core",
   :name "IReset",
   :type "protocol",
   :full-name "cljs.core/IReset",
   :docstring "Protocol for adding resetting functionality.",
   :examples-strings [],
   :examples-htmls []},
  "string-print"
  {:ns "cljs.core",
   :name "string-print",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/string-print",
   :examples-strings [],
   :examples-htmls []},
  "macroexpand"
  {:description
   "(only intended as a REPL utility)\n\nIf the given quoted form is a macro call, expand it once, then repeat until a\nsubsequent result is _not_ a macro call.  NOTE: nested forms are _not_ expanded.\n\nSee [doc:cljs.core/macroexpand-1] if you only wish to expand a form once.",
   :examples-htmls
   ["<p>See how [doc:cljs.core/when] expands to [doc:special/if]:</p><pre><code class=\"clj\">&#40;macroexpand '&#40;when true :foo&#41;&#41;\n;;=&gt; &#40;if true &#40;do :foo&#41;&#41;\n</code></pre><p>The following goes through three expansion steps, but you can use [doc:cljs.core/macroexpand-1] to do one at a time instead.</p><pre><code class=\"clj\">&#40;macroexpand '&#40;-&gt; 2 inc&#41;&#41;\n;;=&gt; &#40;js&#42; &quot;&#40;&#126;{} + &#126;{}&#41;&quot; 2 1&#41;\n</code></pre><p>Notice how the nested <code>inc</code> form is not expanded:</p><pre><code class=\"clj\">&#40;macroexpand '&#40;inc &#40;inc 2&#41;&#41;&#41;\n;;=&gt; &#40;js&#42; &quot;&#40;&#126;{} + &#126;{}&#41;&quot; &#40;inc 2&#41; 1&#41;\n</code></pre>"],
   :ns "cljs.core",
   :name "macroexpand",
   :signature ["[quoted]"],
   :type "macro",
   :related ["cljs.core/macroexpand-1" "cljs.core/defmacro"],
   :examples-strings
   [["(macroexpand '(when true :foo))"
     "(macroexpand '(-> 2 inc))"
     "(macroexpand '(inc (inc 2)))"]],
   :examples
   [{:id "b773af",
     :content
     "See how [doc:cljs.core/when] expands to [doc:special/if]:\n\n```clj\n(macroexpand '(when true :foo))\n;;=> (if true (do :foo))\n```\n\nThe following goes through three expansion steps, but you can use\n[doc:cljs.core/macroexpand-1] to do one at a time instead.\n\n```clj\n(macroexpand '(-> 2 inc))\n;;=> (js* \"(~{} + ~{})\" 2 1)\n```\n\nNotice how the nested `inc` form is not expanded:\n\n```clj\n(macroexpand '(inc (inc 2)))\n;;=> (js* \"(~{} + ~{})\" (inc 2) 1)\n```"}],
   :full-name "cljs.core/macroexpand",
   :docstring
   "Repeatedly calls macroexpand-1 on form until it no longer\nrepresents a macro form, then returns it.  Note neither\nmacroexpand-1 nor macroexpand expand macros in subforms."},
  "ns-interns"
  {:ns "cljs.core",
   :name "ns-interns",
   :signature ["[[quote ns]]"],
   :type "macro",
   :full-name "cljs.core/ns-interns",
   :docstring
   "Returns a map of the intern mappings for the namespace.",
   :examples-strings [],
   :examples-htmls []},
  "Atom"
  {:ns "cljs.core",
   :name "Atom",
   :signature ["[state meta validator watches]"],
   :type "type",
   :full-name "cljs.core/Atom",
   :examples-strings [],
   :examples-htmls []},
  "aset"
  {:description
   "Sets `val` at index `i` in a JavaScript array.\n\n```clj\n(def a #js [1 2 3])\n(aset a 0 \"foo\")\na\n;;=> #js [\"foo\" 2 3]\n```\n\nSet nested elements with the additional `idxs` arguments.\n\n```clj\n(def a #js [1 2 #js [3 4]])\n(aset a 2 0 \"foo\")\na\n;;=> #js [1 2 #js [\"foo\" 4]]\n```\n\nFor JavaScript objects, use [`goog.object/set`].\n\n[`goog.object/set`]:http://google.github.io/closure-library/api/namespace_goog_object.html#set\n\n```clj\n(require 'goog.object)\n(def obj #js {:foo 1})\n\n(goog.object/set obj \"foo\" \"bar\")\nobj\n;;=> #js {:foo \"bar\"}\n```",
   :examples-htmls [],
   :ns "cljs.core",
   :name "aset",
   :signature ["[array i val]" "[array idx idx2 & idxv]"],
   :type "function/macro",
   :related ["cljs.core/aget" "special/set!" "cljs.core/assoc-in"],
   :examples-strings [],
   :full-name "cljs.core/aset",
   :docstring "Sets the value at the index."},
  "*print-newline*"
  {:ns "cljs.core",
   :name "*print-newline*",
   :type "dynamic var",
   :full-name "cljs.core/*print-newline*",
   :docstring
   "When set to logical false will drop newlines from printing calls.\nThis is to work around the implicit newlines emitted by standard JavaScript\nconsole objects.",
   :examples-strings [],
   :examples-htmls []},
  "juxt"
  {:description
   "Takes a set of functions and returns a function that is the juxtaposition of\nthose functions.\n\nThe returned function takes a variable number of arguments, and returns a vector\ncontaining the result of applying each function to the arguments (left-to-\nright).\n\n`((juxt a b c) x)` => `[(a x) (b x) (c x)]`",
   :examples-htmls [],
   :ns "cljs.core",
   :name "juxt",
   :signature ["[f]" "[f g]" "[f g h]" "[f g h & fs]"],
   :type "function",
   :related ["cljs.core/partial" "cljs.core/comp"],
   :examples-strings [],
   :full-name "cljs.core/juxt",
   :docstring
   "Takes a set of functions and returns a fn that is the juxtaposition\nof those fns.  The returned fn takes a variable number of args, and\nreturns a vector containing the result of applying each fn to the\nargs (left-to-right).\n((juxt a b c) x) => [(a x) (b x) (c x)]"},
  "seq?"
  {:description
   "Returns true if `x` is a sequence, false otherwise.\n\nAll collections can be converted into a sequence using `seq`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "seq?",
   :signature ["[x]"],
   :type "function",
   :related
   ["cljs.core/seq"
    "cljs.core/sequential?"
    "cljs.core/vector?"
    "cljs.core/coll?"
    "cljs.core/list?"
    "cljs.core/map?"
    "cljs.core/set?"],
   :examples-strings [],
   :full-name "cljs.core/seq?",
   :docstring "Return true if s satisfies ISeq"},
  "filterv"
  {:description
   "Returns a vector of the items in `coll` for which `(pred item)` returns true.\n\n`pred` must be free of side-effects.",
   :ns "cljs.core",
   :name "filterv",
   :signature ["[pred coll]"],
   :type "function",
   :full-name "cljs.core/filterv",
   :docstring
   "Returns a vector of the items in coll for which\n(pred item) returns true. pred must be free of side-effects.",
   :examples-strings [],
   :examples-htmls []},
  "bit-set"
  {:description
   "Set bit at index `n`.  Same as `x | (1 << y)` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-set 2r1100 1&#41;\n;;=&gt; 14\n;; 14 = 2r1110\n</code></pre><p>Same number in decimal:</p><pre><code class=\"clj\">&#40;bit-set 12 1&#41;\n;;=&gt; 14\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-set",
   :signature ["[x n]"],
   :type "function/macro",
   :related ["cljs.core/bit-clear"],
   :examples-strings [["(bit-set 2r1100 1)" "(bit-set 12 1)"]],
   :examples
   [{:id "6a8a49",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-set 2r1100 1)\n;;=> 14\n;; 14 = 2r1110\n```\n\nSame number in decimal:\n\n```clj\n(bit-set 12 1)\n;;=> 14\n```"}],
   :full-name "cljs.core/bit-set",
   :docstring "Set bit at index n"},
  "quot"
  {:description
   "Returns the quotient of dividing numerator `n` by denominator `d`.\n\nReturns `NaN` when `d` is 0 (divide by 0 error).",
   :examples-htmls [],
   :ns "cljs.core",
   :name "quot",
   :signature ["[n d]"],
   :type "function",
   :related ["cljs.core/rem" "cljs.core/mod"],
   :examples-strings [],
   :full-name "cljs.core/quot",
   :docstring "quot[ient] of dividing numerator by denominator."},
  "def"
  {:description
   "Creates a global variable with the name of `symbol` and a namespace of the\ncurrent namespace.\n\nIf `init` is supplied, it is evaluated and the result is assigned to `symbol`.\n\n`doc-string` is an optional documentation string.\n\n`def` is one of ClojureScript's [special forms](http://clojure.org/special_forms)\nand is used by many macros to define common elements (ie: `defn`, `defmacro`,\netc).\n\nSupported metadata:\n\n- `^:private boolean` - make non-accessible from other namespaces\n- `^:dynamic boolean` - make [dynamically bindable][doc:cljs.core/binding] (usually named with [doc:syntax/earmuffs])\n- `^:const boolean` - prevents redef and allows it to be used in [doc:cljs.core/case].\n- `^:jsdoc [\"\"]` - vector of JSDoc Tags for [Google Closure][closure-jsdoc] or [standard][other-jsdoc].\n- `^:test (fn [] (assert ...))` - allows function to be tested with [doc:cljs.core/test].\n- `^:doc \"\"` - doc-string (prefer the use of the `(def symbol doc-string init)`)\n\n[closure-jsdoc]:https://developers.google.com/closure/compiler/docs/js-for-compiler?hl=en#tags\n[other-jsdoc]:http://usejsdoc.org/#block-tags\n\nCompiler will also add metadata:\n\n- `:ns`\n- `:name`\n- `:file`\n- `:line`, `:end-line`\n- `:column`, `:end-column`\n- `:source`\n- `:arglists`",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def a&#41;\na\n;;=&gt; nil\n\n&#40;def b 42&#41;\nb\n;;=&gt; 42\n\n&#40;def c &quot;an optional docstring&quot; 42&#41;\nc\n;;=&gt; 42\n</code></pre>"],
   :ns "special",
   :name "def",
   :signature ["[symbol]" "[symbol init]" "[symbol doc-string init]"],
   :type "special form",
   :related
   ["cljs.core/defn"
    "cljs.core/fn"
    "cljs.core/defmacro"
    "cljs.core/defmulti"],
   :examples-strings
   [["(def a)"
     "a"
     "(def b 42)"
     "b"
     "(def c \"an optional docstring\" 42)"
     "c"]],
   :examples
   [{:id "a5f898",
     :content
     "```clj\n(def a)\na\n;;=> nil\n\n(def b 42)\nb\n;;=> 42\n\n(def c \"an optional docstring\" 42)\nc\n;;=> 42\n```"}],
   :full-name "special/def",
   :docstring
   "Creates and interns a global var with the name\nof symbol in the current namespace (*ns*) or locates such a var if\nit already exists.  If init is supplied, it is evaluated, and the\nroot binding of the var is set to the resulting value.  If init is\nnot supplied, the root binding of the var is unaffected."},
  "unchecked-byte"
  {:ns "cljs.core",
   :name "unchecked-byte",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/unchecked-byte",
   :examples-strings [],
   :examples-htmls []},
  "coll?"
  {:description
   "Returns true if `x` is a collection, false otherwise.\n\nLists, maps, sets, and vectors are collections.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;coll? &#91;1 2 3&#93;&#41;\n;;=&gt; true\n\n&#40;coll? '&#40;1 2 3&#41;&#41;\n;;=&gt; true\n\n&#40;coll? #{1 2 3}&#41;\n;;=&gt; true\n\n&#40;coll? {:foo 1 :bar 2}&#41;\n;;=&gt; true\n</code></pre><p>Not collections:</p><pre><code class=\"clj\">&#40;coll? &quot;foo&quot;&#41;\n;;=&gt; false\n\n&#40;coll? 123&#41;\n;;=&gt; false\n\n&#40;coll? nil&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "coll?",
   :signature ["[x]"],
   :type "function",
   :related
   ["cljs.core/seq?" "cljs.core/list?" "cljs.core/sequential?"],
   :examples-strings
   [["(coll? [1 2 3])"
     "(coll? '(1 2 3))"
     "(coll? #{1 2 3})"
     "(coll? {:foo 1 :bar 2})"
     "(coll? \"foo\")"
     "(coll? 123)"
     "(coll? nil)"]],
   :examples
   [{:id "d30884",
     :content
     "```clj\n(coll? [1 2 3])\n;;=> true\n\n(coll? '(1 2 3))\n;;=> true\n\n(coll? #{1 2 3})\n;;=> true\n\n(coll? {:foo 1 :bar 2})\n;;=> true\n```\n\nNot collections:\n\n```clj\n(coll? \"foo\")\n;;=> false\n\n(coll? 123)\n;;=> false\n\n(coll? nil)\n;;=> false\n```"}],
   :full-name "cljs.core/coll?",
   :docstring "Returns true if x satisfies ICollection"},
  "reduce"
  {:description
   "`f` should be a function of 2 arguments. If `val` is not supplied, returns the\nresult of applying `f` to the first 2 items in `coll`, then applying `f` to that\nresult and the 3rd item, etc.\n\nIf `coll` contains no items, `f` must accept no arguments as well, and `reduce`\nreturns the result of calling `f` with no arguments.\n\nIf `coll` has only 1 item, it is returned and `f` is not called.\n\nIf `val` is supplied, returns the result of applying `f` to `val` and the first\nitem in `coll`, then applying `f` to that result and the 2nd item, etc.\n\nIf `coll` contains no items, returns `val` and `f` is not called.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "reduce",
   :signature ["[f coll]" "[f val coll]"],
   :type "function",
   :related
   ["cljs.core/reductions" "cljs.core/apply" "cljs.core/frequencies"],
   :examples-strings [],
   :full-name "cljs.core/reduce",
   :docstring
   "f should be a function of 2 arguments. If val is not supplied,\nreturns the result of applying f to the first 2 items in coll, then\napplying f to that result and the 3rd item, etc. If coll contains no\nitems, f must accept no arguments as well, and reduce returns the\nresult of calling f with no arguments.  If coll has only 1 item, it\nis returned and f is not called.  If val is supplied, returns the\nresult of applying f to val and the first item in coll, then\napplying f to that result and the 2nd item, etc. If coll contains no\nitems, returns val and f is not called."},
  "not-native"
  {:ns "cljs.core",
   :name "not-native",
   :type "var",
   :full-name "cljs.core/not-native",
   :examples-strings [],
   :examples-htmls []},
  "bit-shift-left"
  {:description
   "Bitwise shift left `n` bits.  Same as `x << n` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-shift-left 2r0101 1&#41;\n;;=&gt; 10\n;; 10 = 2r1010\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-shift-left 5 1&#41;\n;;=&gt; 10\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-shift-left",
   :signature ["[x n]"],
   :type "function/macro",
   :related ["cljs.core/bit-shift-right"],
   :examples-strings
   [["(bit-shift-left 2r0101 1)" "(bit-shift-left 5 1)"]],
   :examples
   [{:id "67c34a",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-shift-left 2r0101 1)\n;;=> 10\n;; 10 = 2r1010\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-shift-left 5 1)\n;;=> 10\n```"}],
   :full-name "cljs.core/bit-shift-left",
   :docstring "Bitwise shift left"},
  "*2"
  {:description
   "Only usable from a REPL.\n\nHolds the result of the second to last expression.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;+ 1 2 3 4&#41;\n;;=&gt; 10\n\n&#40;+ 4 8&#41;\n;;=&gt; 12\n\n&#42;2\n;;=&gt; 10\n\n&#40;inc &#42;2&#41;\n;;=&gt; 11\n</code></pre><p>Note that a standalone evaluation of <code>&#42;1</code>, <code>&#42;2</code>, <code>&#42;3</code>, or <code>&#42;e</code> is not a part of remembered history:</p><pre><code class=\"clj\">:first\n;;=&gt; :first\n\n:second\n;;=&gt; :second\n\n:third\n;;=&gt; :third\n\n&#42;3\n;;=&gt; :first\n\n&#42;2\n;;=&gt; :second\n\n&#42;1\n;;=&gt; :third\n</code></pre>"],
   :ns "cljs.core",
   :name "*2",
   :type "var",
   :related ["cljs.core/*1" "cljs.core/*3" "cljs.core/*e"],
   :examples-strings
   [["(+ 1 2 3 4)"
     "(+ 4 8)"
     "*2"
     "(inc *2)"
     ":first\n:second\n:third\n*3\n*2\n*1"]],
   :examples
   [{:id "208d41",
     :content
     "```clj\n(+ 1 2 3 4)\n;;=> 10\n\n(+ 4 8)\n;;=> 12\n\n*2\n;;=> 10\n\n(inc *2)\n;;=> 11\n```\n\nNote that a standalone evaluation of `*1`, `*2`, `*3`, or `*e` is not a part of\nremembered history:\n\n```clj\n:first\n;;=> :first\n\n:second\n;;=> :second\n\n:third\n;;=> :third\n\n*3\n;;=> :first\n\n*2\n;;=> :second\n\n*1\n;;=> :third\n```"}],
   :full-name "cljs.core/*2",
   :docstring
   "bound in a repl thread to the second most recent value printed"},
  "re-matches"
  {:description
   "Returns the result of `(re-find re s)` if `re` fully matches `s`.",
   :ns "cljs.core",
   :name "re-matches",
   :signature ["[re s]"],
   :type "function",
   :full-name "cljs.core/re-matches",
   :docstring
   "Returns the result of (re-find re s) if re fully matches s.",
   :examples-strings [],
   :examples-htmls []},
  "case*"
  {:ns "special",
   :name "case*",
   :type "special form",
   :full-name "special/case*",
   :examples-strings [],
   :examples-htmls []},
  "flatten"
  {:description
   "Takes any nested combination of sequential things (lists, vectors, etc.) and\nreturns their contents as a single, flat sequence.\n\n`(flatten nil)` returns nil.",
   :ns "cljs.core",
   :name "flatten",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/flatten",
   :docstring
   "Takes any nested combination of sequential things (lists, vectors,\netc.) and returns their contents as a single, flat sequence.\n(flatten nil) returns nil.",
   :examples-strings [],
   :examples-htmls []},
  "nnext"
  {:description "Same as `(next (next coll))`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "nnext",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/next"],
   :examples-strings [],
   :full-name "cljs.core/nnext",
   :docstring "Same as (next (next x))"},
  "ITransientVector"
  {:ns "cljs.core",
   :name "ITransientVector",
   :type "protocol",
   :full-name "cljs.core/ITransientVector",
   :docstring
   "Protocol for adding vector functionality to transient collections.",
   :examples-strings [],
   :examples-htmls []},
  "LazyTransformer.createMulti"
  {:ns "cljs.core",
   :name "LazyTransformer.createMulti",
   :signature ["[xform colls]"],
   :type "function",
   :full-name "cljs.core/LazyTransformer.createMulti",
   :examples-strings [],
   :examples-htmls []},
  "multi-stepper"
  {:ns "cljs.core",
   :name "multi-stepper",
   :signature ["[xform iters]" "[xform iters nexts]"],
   :type "function",
   :full-name "cljs.core/multi-stepper",
   :examples-strings [],
   :examples-htmls []},
  "bit-clear"
  {:description
   "Clear bit at index `n`.  Same as `x & ~(1 << y)` in JavaScript.",
   :examples-htmls
   ["<p>Bits can be entered using radix notation:</p><pre><code class=\"clj\">&#40;bit-clear 2r1111 2&#41;\n;;=&gt; 11\n;; 11 = 2r1011\n</code></pre><p>Same numbers in decimal:</p><pre><code class=\"clj\">&#40;bit-clear 15 2&#41;\n;;=&gt; 11\n</code></pre>"],
   :ns "cljs.core",
   :name "bit-clear",
   :signature ["[x n]"],
   :type "function/macro",
   :related ["cljs.core/bit-set" "cljs.core/bit-flip"],
   :examples-strings [["(bit-clear 2r1111 2)" "(bit-clear 15 2)"]],
   :examples
   [{:id "0f6748",
     :content
     "Bits can be entered using radix notation:\n\n```clj\n(bit-clear 2r1111 2)\n;;=> 11\n;; 11 = 2r1011\n```\n\nSame numbers in decimal:\n\n```clj\n(bit-clear 15 2)\n;;=> 11\n```"}],
   :full-name "cljs.core/bit-clear",
   :docstring "Clear bit at index n"},
  "MultiFn"
  {:ns "cljs.core",
   :name "MultiFn",
   :signature
   ["[name dispatch-fn default-dispatch-val hierarchy method-table prefer-table method-cache cached-hierarchy]"],
   :type "type",
   :full-name "cljs.core/MultiFn",
   :examples-strings [],
   :examples-htmls []},
  "ensure-reduced"
  {:ns "cljs.core",
   :name "ensure-reduced",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/ensure-reduced",
   :docstring
   "If x is already reduced?, returns it, else returns (reduced x)",
   :examples-strings [],
   :examples-htmls []},
  "namespace"
  {:description
   "Returns the namespace string of a possibly namespace-qualified keyword or symbol.\n\nReturns [doc:syntax/nil] if not present.",
   :examples-htmls
   ["<p>With namespaces:</p><pre><code class=\"clj\">&#40;namespace :foo/bar&#41;\n;;=&gt; &quot;foo&quot;\n\n&#40;namespace 'foo/bar&#41;\n;;=&gt; &quot;foo&quot;\n</code></pre><p>Without namespaces:</p><pre><code class=\"clj\">&#40;namespace :foo&#41;\n;;=&gt; nil\n\n&#40;namespace 'foo&#41;\n;;=&gt; nil\n</code></pre><p>Strings have no concept of a namespace:</p><pre><code class=\"clj\">&#40;name &quot;foo/bar&quot;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "namespace",
   :signature ["[x]"],
   :type "function",
   :related ["cljs.core/name"],
   :examples-strings
   [["(namespace :foo/bar)"
     "(namespace 'foo/bar)"
     "(namespace :foo)"
     "(namespace 'foo)"
     "(name \"foo/bar\")"]],
   :examples
   [{:id "5bd3b4",
     :content
     "With namespaces:\n\n```clj\n(namespace :foo/bar)\n;;=> \"foo\"\n\n(namespace 'foo/bar)\n;;=> \"foo\"\n```\n\nWithout namespaces:\n\n```clj\n(namespace :foo)\n;;=> nil\n\n(namespace 'foo)\n;;=> nil\n```\n\nStrings have no concept of a namespace:\n\n```clj\n(name \"foo/bar\")\n;;=> nil\n```"}],
   :full-name "cljs.core/namespace",
   :docstring
   "Returns the namespace String of a symbol or keyword, or nil if not present."},
  "repeatedly"
  {:description
   "Takes a function `f` of no args, presumably with side effects, and returns an\ninfinite (or length `n` if supplied) lazy sequence of calls to it.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "repeatedly",
   :signature ["[f]" "[n f]"],
   :type "function",
   :related
   ["cljs.core/repeat"
    "cljs.core/iterate"
    "cljs.core/lazy-seq"
    "cljs.core/dotimes"
    "cljs.core/constantly"],
   :examples-strings [],
   :full-name "cljs.core/repeatedly",
   :docstring
   "Takes a function of no args, presumably with side effects, and\nreturns an infinite (or length n if supplied) lazy sequence of calls\nto it"},
  "iter"
  {:ns "cljs.core",
   :name "iter",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/iter",
   :examples-strings [],
   :examples-htmls []},
  "comment"
  {:description
   "Ignores all `body` forms (i.e. \"commenting out\"). Returns nil.\n\nThis is often used near the bottom of a file to hold expressions that test\ndifferent functions during development.  Specific expressions within the\n`comment` can then be selected and evaluated from some editors.\n\nYou can also use `;` to \"comment out\" code until the end of a line.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;comment 123&#41;\n;;=&gt; nil\n\n&#40;comment\n  &#40;foo 1 2 3&#41;\n  &#40;bar &quot;hello&quot;&#41;&#41;\n;;=&gt; nil\n</code></pre><p>Inner forms must still be syntactically correct:</p><pre><code class=\"clj\">&#40;comment &#91;1 2 3&#93;&#93;&#41;\n;; Error: Unmatched delimiter &#93;\n\n&#40;comment a : b&#41;\n;; Error: Invalid token :\n</code></pre>"],
   :ns "cljs.core",
   :name "comment",
   :signature ["[& body]"],
   :type "macro",
   :examples-strings
   [["(comment 123)"
     "(comment\n  (foo 1 2 3)\n  (bar \"hello\"))"
     "(comment [1 2 3]])"
     "(comment a : b)"]],
   :examples
   [{:id "482fd7",
     :content
     "```clj\n(comment 123)\n;;=> nil\n\n(comment\n  (foo 1 2 3)\n  (bar \"hello\"))\n;;=> nil\n```\n\nInner forms must still be syntactically correct:\n\n```clj\n(comment [1 2 3]])\n;; Error: Unmatched delimiter ]\n\n(comment a : b)\n;; Error: Invalid token :\n```"}],
   :full-name "cljs.core/comment",
   :docstring "Ignores body, yields nil"},
  "recur"
  {:ns "special",
   :name "recur",
   :signature ["[exprs*]"],
   :type "special form",
   :full-name "special/recur",
   :docstring
   "Evaluates the exprs in order, then, in parallel, rebinds\nthe bindings of the recursion point to the values of the exprs.\nExecution then jumps back to the recursion point, a loop or fn method.",
   :examples-strings [],
   :examples-htmls []},
  "Vector.fromArray"
  {:ns "cljs.core",
   :name "Vector.fromArray",
   :signature ["[xs]"],
   :type "function",
   :full-name "cljs.core/Vector.fromArray",
   :examples-strings [],
   :examples-htmls []},
  "IPrintable"
  {:ns "cljs.core",
   :name "IPrintable",
   :type "protocol",
   :full-name "cljs.core/IPrintable",
   :docstring
   "Do not use this.  It is kept for backwards compatibility with existing\n   user code that depends on it, but it has been superceded by IPrintWithWriter\n   User code that depends on this should be changed to use -pr-writer instead.",
   :examples-strings [],
   :examples-htmls []},
  "find-ns"
  {:ns "cljs.core",
   :name "find-ns",
   :signature ["[ns]"],
   :type "function",
   :full-name "cljs.core/find-ns",
   :examples-strings [],
   :examples-htmls []},
  "ObjMap.HASHMAP_THRESHOLD"
  {:ns "cljs.core",
   :name "ObjMap.HASHMAP_THRESHOLD",
   :type "var",
   :full-name "cljs.core/ObjMap.HASHMAP_THRESHOLD",
   :examples-strings [],
   :examples-htmls []},
  "dotimes"
  {:description
   "Repeatedly executes `body` (presumably for side-effects) with `name` bound to\nintegers from 0 through `n`-1.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "dotimes",
   :signature ["[[name n] & body]"],
   :type "macro",
   :related ["cljs.core/repeat" "cljs.core/for" "cljs.core/doseq"],
   :examples-strings [],
   :full-name "cljs.core/dotimes",
   :docstring
   "bindings => name n\n\nRepeatedly executes body (presumably for side-effects) with name\nbound to integers from 0 through n-1."},
  "CHAR_MAP"
  {:ns "cljs.core",
   :name "CHAR_MAP",
   :type "var",
   :full-name "cljs.core/CHAR_MAP",
   :examples-strings [],
   :examples-htmls []},
  "set-entries-iterator"
  {:ns "cljs.core",
   :name "set-entries-iterator",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/set-entries-iterator",
   :examples-strings [],
   :examples-htmls []},
  "iterable?"
  {:ns "cljs.core",
   :name "iterable?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/iterable?",
   :docstring "Return true if x implements IIterable protocol.",
   :examples-strings [],
   :examples-htmls []},
  "js-comment"
  {:ns "cljs.core",
   :name "js-comment",
   :signature ["[comment]"],
   :type "macro",
   :full-name "cljs.core/js-comment",
   :docstring
   "Emit a top-level JavaScript multi-line comment. New lines will create a\nnew comment line. Comment block will be preceded and followed by a newline",
   :examples-strings [],
   :examples-htmls []},
  "nfirst"
  {:description "Same as `(next (first coll))`.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;nfirst &#91;&#91;1 2 3&#93; &#91;4 5&#93;&#93;&#41;\n;;=&gt; &#40;2 3&#41;\n\n&#40;nfirst &#91;&#91;1 2&#93; &#91;3 4&#93;&#93;&#41;\n;;=&gt; &#40;2&#41;\n\n&#40;nfirst &#91;&#91;1&#93; &#91;2 3&#93;&#93;&#41;\n;;=&gt; nil\n\n&#40;nfirst &#91;&#91;&#93; &#91;1 2&#93;&#93;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "nfirst",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/next"],
   :examples-strings
   [["(nfirst [[1 2 3] [4 5]])"
     "(nfirst [[1 2] [3 4]])"
     "(nfirst [[1] [2 3]])"
     "(nfirst [[] [1 2]])"]],
   :examples
   [{:id "60b8a4",
     :content
     "```clj\n(nfirst [[1 2 3] [4 5]])\n;;=> (2 3)\n\n(nfirst [[1 2] [3 4]])\n;;=> (2)\n\n(nfirst [[1] [2 3]])\n;;=> nil\n\n(nfirst [[] [1 2]])\n;;=> nil\n```"}],
   :full-name "cljs.core/nfirst",
   :docstring "Same as (next (first x))"},
  "prefer-method"
  {:ns "cljs.core",
   :name "prefer-method",
   :signature ["[multifn dispatch-val-x dispatch-val-y]"],
   :type "function",
   :full-name "cljs.core/prefer-method",
   :docstring
   "Causes the multimethod to prefer matches of dispatch-val-x over dispatch-val-y\nwhen there is a conflict",
   :examples-strings [],
   :examples-htmls []},
  "assoc-in"
  {:description
   "Associates a value in a nested associative structure, where `ks` is a sequence\nof keys and `v` is the new value. Returns a new nested structure.\n\nIf any levels do not exist, hash-maps will be created.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def users &#91;{:name &quot;James&quot; :age 26}\n            {:name &quot;John&quot; :age 43}&#93;&#41;\n</code></pre><p>Update the age of the second (index 1) user:</p><pre><code class=\"clj\">&#40;assoc-in users &#91;1 :age&#93; 44&#41;\n;;=&gt; &#91;{:name &quot;James&quot;, :age 26}\n;;    {:name &quot;John&quot;, :age 44}&#93;\n</code></pre><p>Insert the password of the second (index 1) user:</p><pre><code class=\"clj\">&#40;assoc-in users &#91;1 :password&#93; &quot;nhoJ&quot;&#41;\n;;=&gt; &#91;{:name &quot;James&quot;, :age 26}\n;;    {:password &quot;nhoJ&quot;, :name &quot;John&quot;, :age 43}&#93;\n</code></pre>"],
   :ns "cljs.core",
   :name "assoc-in",
   :signature ["[m [k & ks] v]"],
   :type "function",
   :related
   ["cljs.core/assoc" "cljs.core/update-in" "cljs.core/get-in"],
   :examples-strings
   [["(def users [{:name \"James\" :age 26}\n            {:name \"John\" :age 43}])"
     "(assoc-in users [1 :age] 44)"
     "(assoc-in users [1 :password] \"nhoJ\")"]],
   :examples
   [{:id "e76f20",
     :content
     "```clj\n(def users [{:name \"James\" :age 26}\n            {:name \"John\" :age 43}])\n```\n\nUpdate the age of the second (index 1) user:\n\n```clj\n(assoc-in users [1 :age] 44)\n;;=> [{:name \"James\", :age 26}\n;;    {:name \"John\", :age 44}]\n```\n\nInsert the password of the second (index 1) user:\n\n```clj\n(assoc-in users [1 :password] \"nhoJ\")\n;;=> [{:name \"James\", :age 26}\n;;    {:password \"nhoJ\", :name \"John\", :age 43}]\n```"}],
   :full-name "cljs.core/assoc-in",
   :docstring
   "Associates a value in a nested associative structure, where ks is a\nsequence of keys and v is the new value and returns a new nested structure.\nIf any levels do not exist, hash-maps will be created."},
  "js-reserved"
  {:ns "cljs.core",
   :name "js-reserved",
   :type "var",
   :full-name "cljs.core/js-reserved",
   :examples-strings [],
   :examples-htmls []},
  "assoc"
  {:description
   "assoc(iate)\n\nWhen applied to a map, returns a new map that contains the mapping of key(s) to\nval(s).\n\nHas no effect on the map type (hashed/sorted).\n\nWhen applied to a vector, returns a new vector that contains value `v` at index\n`k`.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def my-map {:foo 1}&#41;\n\n&#40;assoc my-map :foo 2&#41;\n;;=&gt; {:foo 2}\n\n&#40;assoc my-map :bar 2&#41;\n;;=&gt; {:foo 1 :bar 2}\n\n&#40;assoc my-map :a 3 :b 4 :c 5 :d 6&#41;\n;;=&gt; {:foo 1 :a 3 :b 4 :c 5 :d 6}\n\n;; you must pass a value for every key\n&#40;assoc my-map :foo&#41;\n;;=&gt; WARNING: Wrong number of args &#40;2&#41; passed to cljs.core/assoc\n</code></pre>"
    "<pre><code class=\"clj\">&#40;def my-vec &#91;1 2 3&#93;&#41;\n\n&#40;assoc my-vec 0 &quot;foo&quot;&#41;\n;;=&gt; &#91;&quot;foo&quot; 2 3&#93;\n\n&#40;assoc my-vec 3 &quot;foo&quot;&#41;\n;;=&gt; Error: Index 3 out of bounds  &#91;0,0&#93;\n</code></pre>"],
   :ns "cljs.core",
   :name "assoc",
   :signature ["[coll k v]" "[coll k v & kvs]"],
   :type "function",
   :related
   ["cljs.core/assoc-in" "cljs.core/dissoc" "cljs.core/merge"],
   :examples-strings
   [["(def my-map {:foo 1})"
     "(assoc my-map :foo 2)"
     "(assoc my-map :bar 2)"
     "(assoc my-map :a 3 :b 4 :c 5 :d 6)"
     "(assoc my-map :foo)"]
    ["(def my-vec [1 2 3])"
     "(assoc my-vec 0 \"foo\")"
     "(assoc my-vec 3 \"foo\")"]],
   :examples
   [{:id "2fa7e0",
     :content
     "```clj\n(def my-map {:foo 1})\n\n(assoc my-map :foo 2)\n;;=> {:foo 2}\n\n(assoc my-map :bar 2)\n;;=> {:foo 1 :bar 2}\n\n(assoc my-map :a 3 :b 4 :c 5 :d 6)\n;;=> {:foo 1 :a 3 :b 4 :c 5 :d 6}\n\n;; you must pass a value for every key\n(assoc my-map :foo)\n;;=> WARNING: Wrong number of args (2) passed to cljs.core/assoc\n```"}
    {:id "c06eac",
     :content
     "```clj\n(def my-vec [1 2 3])\n\n(assoc my-vec 0 \"foo\")\n;;=> [\"foo\" 2 3]\n\n(assoc my-vec 3 \"foo\")\n;;=> Error: Index 3 out of bounds  [0,0]\n```"}],
   :full-name "cljs.core/assoc",
   :docstring
   "assoc[iate]. When applied to a map, returns a new map of the\nsame (hashed/sorted) type, that contains the mapping of key(s) to\nval(s). When applied to a vector, returns a new vector that\ncontains val at index."},
  "unchecked-long"
  {:ns "cljs.core",
   :name "unchecked-long",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/unchecked-long",
   :docstring
   "Coerce to long by stripping decimal places. Identical to `int'.",
   :examples-strings [],
   :examples-htmls []},
  "mapv"
  {:description
   "Returns a vector consisting of the result of applying `f` to the set of first\nitems of each coll, followed by applying `f` to the set of second items in each\ncoll, until any one of the colls is exhausted. Any remaining items in other\ncolls are ignored.\n\nFunction `f` should accept number-of-colls arguments.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "mapv",
   :signature
   ["[f coll]" "[f c1 c2]" "[f c1 c2 c3]" "[f c1 c2 c3 & colls]"],
   :type "function",
   :related ["cljs.core/map"],
   :examples-strings [],
   :full-name "cljs.core/mapv",
   :docstring
   "Returns a vector consisting of the result of applying f to the\nset of first items of each coll, followed by applying f to the set\nof second items in each coll, until any one of the colls is\nexhausted.  Any remaining items in other colls are ignored. Function\nf should accept number-of-colls arguments."},
  "into"
  {:description
   "Returns a new collection consisting of `to` with all of the items of `from`\n\"added\" using `conj`.\n\nA transducer may be supplied as `xform`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "into",
   :signature ["[to from]" "[to xform from]"],
   :type "function",
   :related ["cljs.core/conj"],
   :examples-strings [],
   :full-name "cljs.core/into",
   :docstring
   "Returns a new coll consisting of to-coll with all of the items of\nfrom-coll conjoined. A transducer may be supplied."},
  "*clojurescript-version*"
  {:ns "cljs.core",
   :name "*clojurescript-version*",
   :type "var",
   :full-name "cljs.core/*clojurescript-version*",
   :examples-strings [],
   :examples-htmls []},
  "IEncodeClojure"
  {:ns "cljs.core",
   :name "IEncodeClojure",
   :type "protocol",
   :full-name "cljs.core/IEncodeClojure",
   :examples-strings [],
   :examples-htmls []},
  "special-symbol?"
  {:ns "cljs.core",
   :name "special-symbol?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/special-symbol?",
   :examples-strings [],
   :examples-htmls []},
  "when-not"
  {:description
   "Evaluates `test`. If logical false, evaluates `body` in an implicit `do`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "when-not",
   :signature ["[test & body]"],
   :type "macro",
   :related ["cljs.core/when" "cljs.core/when-let" "special/if"],
   :examples-strings [],
   :full-name "cljs.core/when-not",
   :docstring
   "Evaluates test. If logical false, evaluates body in an implicit do."},
  "dec"
  {:description "Returns a number one less than `x`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "dec",
   :signature ["[x]"],
   :type "function/macro",
   :related ["cljs.core/inc"],
   :examples-strings [],
   :full-name "cljs.core/dec",
   :docstring "Returns a number one less than num."},
  "disj"
  {:description
   "disj(oin). Returns a new set of the same (hashed/sorted) type, that does not\ncontain key(s).",
   :examples-htmls [],
   :ns "cljs.core",
   :name "disj",
   :signature ["[coll]" "[coll k]" "[coll k & ks]"],
   :type "function",
   :related
   ["cljs.core/dissoc" "cljs.core/disj!" "clojure.set/difference"],
   :examples-strings [],
   :full-name "cljs.core/disj",
   :docstring
   "disj[oin]. Returns a new set of the same (hashed/sorted) type, that\ndoes not contain key(s)."},
  "default-dispatch-val"
  {:ns "cljs.core",
   :name "default-dispatch-val",
   :signature ["[multifn]"],
   :type "function",
   :full-name "cljs.core/default-dispatch-val",
   :docstring "Given a multimethod, return it's default-dispatch-val.",
   :examples-strings [],
   :examples-htmls []},
  "=="
  {:description
   "This is an equality check for numbers of different types that was carried over from Clojure,\nto allow compatibility when converting code to ClojureScript.\n\nSince there is only a single number type in JavaScript, 64-bit floating point, there is no\nreason to use the `==` operator in ClojureScript.\n\nBehavior on non-number arguments is undefined.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;== 1 1&#41;\n;;=&gt; true\n\n&#40;== 1 2&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "==",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/=" "cljs.core/identical?"],
   :examples-strings [["(== 1 1)" "(== 1 2)"]],
   :examples
   [{:id "5ac342",
     :content
     "```clj\n(== 1 1)\n;;=> true\n\n(== 1 2)\n;;=> false\n```"}],
   :full-name "cljs.core/==",
   :docstring
   "Returns non-nil if nums all have the equivalent\nvalue, otherwise false. Behavior on non nums is\nundefined."},
  "get"
  {:description
   "Returns the value mapped to key `k`.\n\nReturns `not-found` or nil if `k` is not present in `o`.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "get",
   :signature ["[o k]" "[o k not-found]"],
   :type "function",
   :related ["cljs.core/get-in"],
   :examples-strings [],
   :full-name "cljs.core/get",
   :docstring
   "Returns the value mapped to key, not-found or nil if key not present."},
  "ex-cause"
  {:ns "cljs.core",
   :name "ex-cause",
   :signature ["[ex]"],
   :type "function",
   :full-name "cljs.core/ex-cause",
   :docstring
   "Alpha - subject to change.\nReturns exception cause (an Error / ExceptionInfo) if ex is an\nExceptionInfo.\nOtherwise returns nil.",
   :examples-strings [],
   :examples-htmls []},
  "Cons"
  {:ns "cljs.core",
   :name "Cons",
   :signature ["[meta first rest __hash]"],
   :type "type",
   :full-name "cljs.core/Cons",
   :examples-strings [],
   :examples-htmls []},
  "distinct?"
  {:description "Returns true if no two of the arguments are `=`",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;distinct? 1&#41;\n;;=&gt; true\n\n&#40;distinct? 1 2&#41;\n;;=&gt; true\n\n&#40;distinct? 1 1&#41;\n;;=&gt; false\n\n&#40;distinct? 1 2 3&#41;\n;;=&gt; true\n\n&#40;distinct? 1 2 1&#41;\n;;=&gt; false\n</code></pre><p>Apply it a collection:</p><pre><code class=\"clj\">&#40;apply distinct? &#91;1 2 3&#93;&#41;\n;;=&gt; true\n\n&#40;apply distinct? &#91;1 2 1&#93;&#41;\n;;=&gt; false\n</code></pre>"],
   :ns "cljs.core",
   :name "distinct?",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function",
   :related ["cljs.core/distinct"],
   :examples-strings
   [["(distinct? 1)"
     "(distinct? 1 2)"
     "(distinct? 1 1)"
     "(distinct? 1 2 3)"
     "(distinct? 1 2 1)"
     "(apply distinct? [1 2 3])"
     "(apply distinct? [1 2 1])"]],
   :examples
   [{:id "b32799",
     :content
     "```clj\n(distinct? 1)\n;;=> true\n\n(distinct? 1 2)\n;;=> true\n\n(distinct? 1 1)\n;;=> false\n\n(distinct? 1 2 3)\n;;=> true\n\n(distinct? 1 2 1)\n;;=> false\n```\n\nApply it a collection:\n\n```clj\n(apply distinct? [1 2 3])\n;;=> true\n\n(apply distinct? [1 2 1])\n;;=> false\n```"}],
   :full-name "cljs.core/distinct?",
   :docstring "Returns true if no two of the arguments are ="},
  "merge"
  {:description
   "Returns a map that consists of the rest of the maps `conj`-ed onto the first.\n\nIf a key occurs in more than one map, the mapping from the rightmost map will\n\"win\".",
   :examples-htmls [],
   :ns "cljs.core",
   :name "merge",
   :signature ["[& maps]"],
   :type "function",
   :related ["cljs.core/merge-with" "cljs.core/hash-map"],
   :examples-strings [],
   :full-name "cljs.core/merge",
   :docstring
   "Returns a map that consists of the rest of the maps conj-ed onto\nthe first.  If a key occurs in more than one map, the mapping from\nthe latter (left-to-right) will be the mapping in the result."},
  "Var"
  {:ns "cljs.core",
   :name "Var",
   :signature ["[val sym _meta]"],
   :type "type",
   :full-name "cljs.core/Var",
   :examples-strings [],
   :examples-htmls []},
  "BitmapIndexedNode"
  {:ns "cljs.core",
   :name "BitmapIndexedNode",
   :signature ["[edit bitmap arr]"],
   :type "type",
   :full-name "cljs.core/BitmapIndexedNode",
   :examples-strings [],
   :examples-htmls []},
  "+"
  {:description "Returns the sum of nums.\n\n`(+)` returns 0.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;+&#41;\n;;=&gt; 0\n\n&#40;+ 1&#41;\n;;=&gt; 1\n\n&#40;+ -10&#41;\n;;=&gt; -10\n\n&#40;+ 1 2&#41;\n;;=&gt; 3\n\n&#40;+ 1 2 3&#41;\n;;=&gt; 6\n</code></pre>"],
   :ns "cljs.core",
   :name "+",
   :signature ["[]" "[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/*" "cljs.core/-"],
   :examples-strings [["(+)" "(+ 1)" "(+ -10)" "(+ 1 2)" "(+ 1 2 3)"]],
   :examples
   [{:id "650668",
     :content
     "```clj\n(+)\n;;=> 0\n\n(+ 1)\n;;=> 1\n\n(+ -10)\n;;=> -10\n\n(+ 1 2)\n;;=> 3\n\n(+ 1 2 3)\n;;=> 6\n```"}],
   :full-name "cljs.core/+",
   :docstring "Returns the sum of nums. (+) returns 0."},
  "list*"
  {:description
   "Creates a new list containing the items prepended to the rest, the last of which\nwill be treated as a sequence.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "list*",
   :signature
   ["[args]"
    "[a args]"
    "[a b args]"
    "[a b c args]"
    "[a b c d & more]"],
   :type "function",
   :related ["cljs.core/list"],
   :examples-strings [],
   :full-name "cljs.core/list*",
   :docstring
   "Creates a new list containing the items prepended to the rest, the\nlast of which will be treated as a sequence."},
  "IEquiv"
  {:ns "cljs.core",
   :name "IEquiv",
   :type "protocol",
   :full-name "cljs.core/IEquiv",
   :docstring
   "Protocol for adding value comparison functionality to a type.",
   :examples-strings [],
   :examples-htmls []},
  "LazySeq"
  {:ns "cljs.core",
   :name "LazySeq",
   :signature ["[meta fn s __hash]"],
   :type "type",
   :full-name "cljs.core/LazySeq",
   :examples-strings [],
   :examples-htmls []},
  "even?"
  {:description
   "Returns true if `n` is an even number.\n\nThrows an exception if `n` is not an integer.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "even?",
   :signature ["[n]"],
   :type "function",
   :related ["cljs.core/odd?"],
   :examples-strings [],
   :full-name "cljs.core/even?",
   :docstring
   "Returns true if n is even, throws an exception if n is not an integer"},
  "spread"
  {:ns "cljs.core",
   :name "spread",
   :signature ["[arglist]"],
   :type "function",
   :full-name "cljs.core/spread",
   :examples-strings [],
   :examples-htmls []},
  "IWriter"
  {:ns "cljs.core",
   :name "IWriter",
   :type "protocol",
   :full-name "cljs.core/IWriter",
   :docstring
   "Protocol for writing. Currently only implemented by StringBufferWriter.",
   :examples-strings [],
   :examples-htmls []},
  "NeverEquiv"
  {:ns "cljs.core",
   :name "NeverEquiv",
   :signature ["[]"],
   :type "type",
   :full-name "cljs.core/NeverEquiv",
   :examples-strings [],
   :examples-htmls []},
  "array"
  {:description
   "Creates a JavaScript array containing `args`.\n\nThe tagged literal `#js [1 2 3]` is equivalent to `(array 1 2 3)`",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;array 1 2 3&#41;\n;;=&gt; #js &#91;1 2 3&#93;\n\n&#40;apply array &#91;1 2 3&#93;&#41;\n;;=&gt; #js &#91;1 2 3&#93;\n\n#js &#91;1 2 3&#93;\n;;=&gt; #js &#91;1 2 3&#93;\n</code></pre>"
    "<p>When creating nested JavaScript arrays, you can opt to use <code>clj-&gt;js</code> instead:</p><pre><code class=\"clj\">&#40;array 1 2 &#40;array 3 4&#41;&#41;\n;;=&gt; #js &#91;1 2 #js &#91;3 4&#93;&#93;\n\n&#40;clj-&gt;js &#91;1 2 &#91;3 4&#93;&#93;&#41;\n;;=&gt; #js &#91;1 2 #js &#91;3 4&#93;&#93;\n</code></pre>"],
   :ns "cljs.core",
   :name "array",
   :signature ["[& args]"],
   :type "function/macro",
   :related
   ["cljs.core/aclone" "cljs.core/make-array" "cljs.core/clj->js"],
   :examples-strings
   [["(array 1 2 3)" "(apply array [1 2 3])" "#js [1 2 3]"]
    ["(array 1 2 (array 3 4))" "(clj->js [1 2 [3 4]])"]],
   :examples
   [{:id "3a546d",
     :content
     "```clj\n(array 1 2 3)\n;;=> #js [1 2 3]\n\n(apply array [1 2 3])\n;;=> #js [1 2 3]\n\n#js [1 2 3]\n;;=> #js [1 2 3]\n```"}
    {:id "cca945",
     :content
     "When creating nested JavaScript arrays, you can opt to use `clj->js` instead:\n\n```clj\n(array 1 2 (array 3 4))\n;;=> #js [1 2 #js [3 4]]\n\n(clj->js [1 2 [3 4]])\n;;=> #js [1 2 #js [3 4]]\n```"}],
   :full-name "cljs.core/array",
   :docstring
   "Creates a new javascript array.\n@param {...*} var_args"},
  "ICloneable"
  {:ns "cljs.core",
   :name "ICloneable",
   :type "protocol",
   :full-name "cljs.core/ICloneable",
   :docstring "Protocol for cloning a value.",
   :examples-strings [],
   :examples-htmls []},
  "cond->>"
  {:description
   "Takes an expression and a set of test/form pairs. Threads `expr` (via `->>`)\nthrough each form for which the corresponding test expression is true.\n\nNote that, unlike `cond` branching, `cond->>` threading does not short circuit\nafter the first true test expression.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;def filter? true&#41;\n&#40;def sum? true&#41;\n\n&#40;cond-&gt;&gt; &#91;1 2 3 4&#93;\n  filter? &#40;filter even?&#41;\n  sum?    &#40;reduce +&#41;&#41;\n;;=&gt; 6\n</code></pre>"],
   :ns "cljs.core",
   :name "cond->>",
   :signature ["[expr & clauses]"],
   :type "macro",
   :related
   ["cljs.core/->"
    "cljs.core/->>"
    "cljs.core/cond->"
    "cljs.core/cond"],
   :examples-strings
   [["(def filter? true)"
     "(def sum? true)"
     "(cond->> [1 2 3 4]\n  filter? (filter even?)\n  sum?    (reduce +))"]],
   :examples
   [{:id "e07a05",
     :content
     "```clj\n(def filter? true)\n(def sum? true)\n\n(cond->> [1 2 3 4]\n  filter? (filter even?)\n  sum?    (reduce +))\n;;=> 6\n```"}],
   :full-name "cljs.core/cond->>",
   :docstring
   "Takes an expression and a set of test/form pairs. Threads expr (via ->>)\nthrough each form for which the corresponding test expression\nis true.  Note that, unlike cond branching, cond->> threading does not short circuit\nafter the first true test expression."},
  "rsubseq"
  {:description
   "`sc` must be a sorted collection.\n\n`test`, `start-test`, `end-test` must be `<`, `<=`, `>` or `>=`.\n\nReturns a reverse sequence of those entries with keys `ek` for which\n`(test (.. sc comparator (compare ek key)) 0)` is true.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "rsubseq",
   :signature
   ["[sc test key]" "[sc start-test start-key end-test end-key]"],
   :type "function",
   :related ["cljs.core/subseq"],
   :examples-strings [],
   :full-name "cljs.core/rsubseq",
   :docstring
   "sc must be a sorted collection, test(s) one of <, <=, > or\n>=. Returns a reverse seq of those entries with keys ek for\nwhich (test (.. sc comparator (compare ek key)) 0) is true"},
  "IList"
  {:ns "cljs.core",
   :name "IList",
   :type "protocol",
   :full-name "cljs.core/IList",
   :docstring "Marker interface indicating a persistent list",
   :examples-strings [],
   :examples-htmls []},
  "throw"
  {:description
   "`expr` is evaluated and thrown, hopefully to be caught by a `try` expression.\n\n`(throw (js/Error. \"Oops!\"))`",
   :examples-htmls [],
   :ns "special",
   :name "throw",
   :signature ["[expr]"],
   :type "special form",
   :related ["special/try" "special/catch" "special/finally"],
   :examples-strings [],
   :full-name "special/throw",
   :docstring "The expr is evaluated and thrown."},
  "ITransientMap"
  {:ns "cljs.core",
   :name "ITransientMap",
   :type "protocol",
   :full-name "cljs.core/ITransientMap",
   :docstring
   "Protocol for adding mapping functionality to transient collections.",
   :examples-strings [],
   :examples-htmls []},
  "cat"
  {:ns "cljs.core",
   :name "cat",
   :signature ["[rf]"],
   :type "function",
   :full-name "cljs.core/cat",
   :docstring
   "A transducer which concatenates the contents of each input, which must be a\ncollection, into the reduction.",
   :examples-strings [],
   :examples-htmls []},
  "mk-bound-fn"
  {:ns "cljs.core",
   :name "mk-bound-fn",
   :signature ["[sc test key]"],
   :type "function",
   :full-name "cljs.core/mk-bound-fn",
   :examples-strings [],
   :examples-htmls []},
  "char?"
  {:ns "cljs.core",
   :name "char?",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/char?",
   :docstring "Returns true if x is a JavaScript char.",
   :examples-strings [],
   :examples-htmls []},
  "pr"
  {:ns "cljs.core",
   :name "pr",
   :signature ["[& objs]"],
   :type "function",
   :full-name "cljs.core/pr",
   :docstring
   "Prints the object(s) using string-print.  Prints the\nobject(s), separated by spaces if there is more than one.\nBy default, pr and prn print in a way that objects can be\nread by the reader",
   :examples-strings [],
   :examples-htmls []},
  "js*"
  {:ns "special",
   :name "js*",
   :type "special form",
   :full-name "special/js*",
   :examples-strings [],
   :examples-htmls []},
  "Eduction"
  {:ns "cljs.core",
   :name "Eduction",
   :signature ["[xform coll]"],
   :type "type",
   :full-name "cljs.core/Eduction",
   :examples-strings [],
   :examples-htmls []},
  "SeqIter"
  {:ns "cljs.core",
   :name "SeqIter",
   :signature ["[_seq _next]"],
   :type "type",
   :full-name "cljs.core/SeqIter",
   :examples-strings [],
   :examples-htmls []},
  "Iteration"
  {:ns "cljs.core",
   :name "Iteration",
   :signature ["[xform coll]"],
   :type "type",
   :full-name "cljs.core/Iteration",
   :examples-strings [],
   :examples-htmls []},
  "tagged-literal"
  {:description
   "Internal use only.  Create tagged literals with their [syntax form][doc:syntax/tagged-literal] instead.",
   :ns "cljs.core",
   :name "tagged-literal",
   :signature ["[tag form]"],
   :type "function",
   :full-name "cljs.core/tagged-literal",
   :docstring
   "Construct a data representation of a tagged literal from a\ntag symbol and a form.",
   :examples-strings [],
   :examples-htmls []},
  "load-file*"
  {:ns "cljs.core",
   :name "load-file*",
   :signature ["[f]"],
   :type "macro",
   :full-name "cljs.core/load-file*",
   :examples-strings [],
   :examples-htmls []},
  "vreset!"
  {:ns "cljs.core",
   :name "vreset!",
   :signature ["[vol newval]"],
   :type "function",
   :related ["cljs.core/vswap!" "cljs.core/volatile!"],
   :full-name "cljs.core/vreset!",
   :docstring
   "Sets the value of volatile to newval without regard for the\ncurrent value. Returns newval.",
   :examples-strings [],
   :examples-htmls []},
  "EntriesIterator"
  {:ns "cljs.core",
   :name "EntriesIterator",
   :signature ["[s]"],
   :type "type",
   :full-name "cljs.core/EntriesIterator",
   :examples-strings [],
   :examples-htmls []},
  "reversible?"
  {:description
   "Returns true if `coll` implements the `IReversible` protocol, false otherwise.\n\nVectors, sorted maps, and sorted sets implement `IReversible`.",
   :ns "cljs.core",
   :name "reversible?",
   :signature ["[coll]"],
   :type "function",
   :full-name "cljs.core/reversible?",
   :docstring "Returns true if coll satisfies? IReversible.",
   :examples-strings [],
   :examples-htmls []},
  "assert"
  {:description
   "Evaluates expression `expr` and throws an exception if it does not evaluate to\nlogical true.  Exception will include `message` if given.\n\nReturns `nil`.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;assert true&#41;\n;;=&gt; nil\n\n&#40;assert false&#41;\n;;=&gt; Uncaught Error: Assert failed: false\n\n&#40;assert &#40;= 1 2&#41; &quot;1 is not 2&quot;&#41;\n;;=&gt; Uncaught Error: Assert failed: 1 is not 2\n;;   &#40;= 1 2&#41;\n</code></pre>"],
   :ns "cljs.core",
   :name "assert",
   :signature ["[expr]" "[expr message]"],
   :type "macro",
   :examples-strings
   [["(assert true)"
     "(assert false)"
     "(assert (= 1 2) \"1 is not 2\")"]],
   :examples
   [{:id "1dc16f",
     :content
     "```clj\n(assert true)\n;;=> nil\n\n(assert false)\n;;=> Uncaught Error: Assert failed: false\n\n(assert (= 1 2) \"1 is not 2\")\n;;=> Uncaught Error: Assert failed: 1 is not 2\n;;   (= 1 2)\n```"}],
   :full-name "cljs.core/assert",
   :docstring
   "Evaluates expr and throws an exception if it does not evaluate to\nlogical true."},
  "finally"
  {:description
   "`finally` should be the last form inside of a `try` expression. It is optional.\n\n`finally` clauses are always evaluated for their side effects whether there was\nan error or not, but they are never the return value of a `try` expression.",
   :examples-htmls [],
   :ns "special",
   :name "finally",
   :signature ["[expr*]"],
   :type "special form",
   :related ["special/try" "special/catch" "special/throw"],
   :examples-strings [],
   :full-name "special/finally",
   :docstring
   "catch-clause => (catch classname name expr*)\nfinally-clause => (finally expr*)\nCatches and handles JavaScript exceptions."},
  "byte"
  {:ns "cljs.core",
   :name "byte",
   :signature ["[x]"],
   :type "function/macro",
   :full-name "cljs.core/byte",
   :examples-strings [],
   :examples-htmls []},
  "prefers"
  {:ns "cljs.core",
   :name "prefers",
   :signature ["[multifn]"],
   :type "function",
   :full-name "cljs.core/prefers",
   :docstring
   "Given a multimethod, returns a map of preferred value -> set of other values",
   :examples-strings [],
   :examples-htmls []},
  "array-chunk"
  {:ns "cljs.core",
   :name "array-chunk",
   :signature ["[arr]" "[arr off]" "[arr off end]"],
   :type "function",
   :full-name "cljs.core/array-chunk",
   :examples-strings [],
   :examples-htmls []},
  "keep-indexed"
  {:description
   "Returns a lazy sequence of the non-nil results of `(f index item)`. Note, this\nmeans false return values will be included.\n\n`f` must be free of side-effects.\n\nReturns a stateful transducer when no collection is provided.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "keep-indexed",
   :signature ["[f]" "[f coll]"],
   :type "function",
   :related ["cljs.core/map-indexed" "cljs.core/keep"],
   :examples-strings [],
   :full-name "cljs.core/keep-indexed",
   :docstring
   "Returns a lazy sequence of the non-nil results of (f index item). Note,\nthis means false return values will be included.  f must be free of\nside-effects.  Returns a stateful transducer when no collection is\nprovided."},
  "letfn"
  {:description
   "Takes a vector of function definitions `fnspecs` and binds the functions to\ntheir names. All of the names are available in all of the definitions of the\nfunctions as well as `body`.\n\n`fnspecs` must be a vector with an even number of forms. See `let`.\n\n`letfn` is a wrapper over one of ClojureScript's [special forms].\n\n[special forms]:http://clojure.org/special_forms",
   :examples-htmls [],
   :ns "cljs.core",
   :name "letfn",
   :signature ["[fnspecs & body]"],
   :type "macro",
   :related ["cljs.core/let"],
   :examples-strings [],
   :full-name "cljs.core/letfn",
   :docstring
   "fnspec ==> (fname [params*] exprs) or (fname ([params*] exprs)+)\n\nTakes a vector of function specs and a body, and generates a set of\nbindings of functions to their names. All of the names are available\nin all of the definitions of the functions, as well as the body."},
  "string-iter"
  {:ns "cljs.core",
   :name "string-iter",
   :signature ["[x]"],
   :type "function",
   :full-name "cljs.core/string-iter",
   :examples-strings [],
   :examples-htmls []},
  "ArrayNodeIterator"
  {:ns "cljs.core",
   :name "ArrayNodeIterator",
   :signature ["[arr i next-iter]"],
   :type "type",
   :full-name "cljs.core/ArrayNodeIterator",
   :examples-strings [],
   :examples-htmls []},
  "TransientHashSet"
  {:ns "cljs.core",
   :name "TransientHashSet",
   :signature ["[transient-map]"],
   :type "type",
   :full-name "cljs.core/TransientHashSet",
   :examples-strings [],
   :examples-htmls []},
  "ArrayList"
  {:ns "cljs.core",
   :name "ArrayList",
   :signature ["[arr]"],
   :type "type",
   :full-name "cljs.core/ArrayList",
   :examples-strings [],
   :examples-htmls []},
  "test"
  {:ns "cljs.core",
   :name "test",
   :signature ["[v]"],
   :type "function",
   :full-name "cljs.core/test",
   :docstring
   "test [v] finds fn at key :test in var metadata and calls it,\npresuming failure will throw exception",
   :examples-strings [],
   :examples-htmls []},
  "IMapEntry"
  {:ns "cljs.core",
   :name "IMapEntry",
   :type "protocol",
   :full-name "cljs.core/IMapEntry",
   :docstring "Protocol for examining a map entry.",
   :examples-strings [],
   :examples-htmls []},
  "UUID"
  {:description
   "A type representing a universally unique identifier ([UUID]).\n\nUse [doc:cljs.core/uuid] or [doc:syntax/uuid-literal] to create one.\n\n[UUID]:https://en.wikipedia.org/wiki/Universally_unique_identifier",
   :ns "cljs.core",
   :name "UUID",
   :signature ["[uuid __hash]"],
   :type "type",
   :related
   ["syntax/uuid-literal" "cljs.core/random-uuid" "cljs.core/uuid"],
   :full-name "cljs.core/UUID",
   :examples-strings [],
   :examples-htmls []},
  "format"
  {:ns "cljs.core",
   :name "format",
   :signature ["[fmt & args]"],
   :type "function",
   :full-name "cljs.core/format",
   :docstring "Formats a string using goog.string.format.",
   :examples-strings [],
   :examples-htmls []},
  "key-test"
  {:ns "cljs.core",
   :name "key-test",
   :signature ["[key other]"],
   :type "function",
   :full-name "cljs.core/key-test",
   :examples-strings [],
   :examples-htmls []},
  "ns"
  {:description
   "Sets the namespace of the file.\n\n`ns` must be the first form in a `.cljs` file and there can only be one `ns`\ndeclaration per file. Namespaces must match the file name of their respective\n`.cljs` files, with the exception that dashes in namespaces become underscores\nin filenames. Thus, `(ns foo.bar-biz.baz)` should be the first form in file\n`foo/bar_biz/baz.cljs`.\n\n`references` can be zero or more forms used to import other namespaces, symbols,\nand libraries into the current namespace.\n\n```clj\n(ns example.core\n\n  ;; for excluding clojure symbols\n  (:refer-clojure :exclude [])\n\n  ;; for importing goog classes and enums\n  (:import\n    lib.ns\n    [lib.ns Ctor*])\n\n  (:require-macros\n    [lib.ns :refer []\n            :as alias\n            :reload\n            :reload-all])\n\n  (:require\n    [lib.ns :refer []\n            :refer-macros []\n            :include-macros true|false\n            :as alias\n            :reload\n            :reload-all])\n\n  (:use\n    [lib.ns :only []\n            :reload\n            :reload-all])\n\n  (:use-macros\n    [lib.ns :only []\n            :reload\n            :reload-all]))\n```",
   :examples-htmls [],
   :ns "special",
   :name "ns",
   :signature ["[name & references]"],
   :type "special form",
   :related
   ["specialrepl/in-ns"
    "specialrepl/load-namespace"
    "specialrepl/import"
    "specialrepl/require"
    "specialrepl/require-macros"],
   :examples-strings [],
   :full-name "special/ns",
   :docstring
   "You must currently use the ns form only with the following caveats\n\n  * You must use the :only form of :use\n  * :require supports :as and :refer\n    - both options can be skipped\n    - in this case a symbol can be used as a libspec directly\n      - that is, (:require lib.foo) and (:require [lib.foo]) are both\n        supported and mean the same thing\n    - prefix lists are not supported\n  * The only option for :refer-clojure is :exclude\n  * :import is available for importing Google Closure classes\n    - ClojureScript types and records should be brought in with :use\n      or :require :refer, not :import ed\n  * Macros are written in Clojure, and are referenced via the new\n    :require-macros / :use-macros options to ns\n    - :require-macros and :use-macros support the same forms that\n      :require and :use do\n\nImplicit macro loading: If a namespace is required or used, and that\nnamespace itself requires or uses macros from its own namespace, then\nthe macros will be implicitly required or used using the same\nspecifications. This oftentimes leads to simplified library usage,\nsuch that the consuming namespace need not be concerned about\nexplicitly distinguishing between whether certain vars are functions\nor macros.\n\nInline macro specification: As a convenience, :require can be given\neither :include-macros true or :refer-macros [syms...]. Both desugar\ninto forms which explicitly load the matching Clojure file containing\nmacros. (This works independently of whether the namespace being\nrequired internally requires or uses its own macros.) For example:\n\n(ns testme.core\n(:require [foo.core :as foo :refer [foo-fn] :include-macros true]\n          [woz.core :as woz :refer [woz-fn] :refer-macros [app jx]]))\n\nis sugar for\n\n(ns testme.core\n(:require [foo.core :as foo :refer [foo-fn]]\n          [woz.core :as woz :refer [woz-fn]])\n(:require-macros [foo.core :as foo]\n                 [woz.core :as woz :refer [app jx]]))"},
  "PersistentArrayMap.HASHMAP-THRESHOLD"
  {:ns "cljs.core",
   :name "PersistentArrayMap.HASHMAP-THRESHOLD",
   :type "var",
   :full-name "cljs.core/PersistentArrayMap.HASHMAP-THRESHOLD",
   :examples-strings [],
   :examples-htmls []},
  "reduced?"
  {:ns "cljs.core",
   :name "reduced?",
   :signature ["[r]"],
   :type "function",
   :full-name "cljs.core/reduced?",
   :docstring "Returns true if x is the result of a call to reduced",
   :examples-strings [],
   :examples-htmls []},
  "ffirst"
  {:description "Same as `(first (first coll))`.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;ffirst &#91;&#91;1 2&#93; &#91;3 4&#93; &#91;5 6&#93;&#93;&#41;\n;;=&gt; 1\n</code></pre>"],
   :ns "cljs.core",
   :name "ffirst",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/first" "cljs.core/fnext" "cljs.core/nfirst"],
   :examples-strings [["(ffirst [[1 2] [3 4] [5 6]])"]],
   :examples
   [{:id "575ba2",
     :content "```clj\n(ffirst [[1 2] [3 4] [5 6]])\n;;=> 1\n```"}],
   :full-name "cljs.core/ffirst",
   :docstring "Same as (first (first x))"},
  "remove-all-methods"
  {:ns "cljs.core",
   :name "remove-all-methods",
   :signature ["[multifn]"],
   :type "function",
   :full-name "cljs.core/remove-all-methods",
   :docstring "Removes all of the methods of multimethod.",
   :examples-strings [],
   :examples-htmls []},
  "flatmap"
  {:ns "cljs.core",
   :name "flatmap",
   :signature ["[f]" "[f coll]"],
   :type "function",
   :full-name "cljs.core/flatmap",
   :docstring
   "maps f over coll and concatenates the results.  Thus function f\nshould return a collection.  Returns a transducer when no collection\nis provided.",
   :examples-strings [],
   :examples-htmls []},
  "pr-str*"
  {:ns "cljs.core",
   :name "pr-str*",
   :signature ["[obj]"],
   :type "function",
   :full-name "cljs.core/pr-str*",
   :docstring
   "Support so that collections can implement toString without\nloading all the printing machinery.",
   :examples-strings [],
   :examples-htmls []},
  "first"
  {:description
   "Returns the first item in `coll` and calls `seq` on its argument.\n\nReturns nil when `coll` is nil.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;first &#91;1 2 3&#93;&#41;\n;;=&gt; 1\n\n&#40;first &#91;&#93;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "first",
   :signature ["[coll]"],
   :type "function",
   :related
   ["cljs.core/rest"
    "cljs.core/next"
    "cljs.core/nth"
    "cljs.core/second"
    "cljs.core/take"
    "cljs.core/ffirst"],
   :examples-strings [["(first [1 2 3])" "(first [])"]],
   :examples
   [{:id "40e413",
     :content
     "```clj\n(first [1 2 3])\n;;=> 1\n\n(first [])\n;;=> nil\n```"}],
   :full-name "cljs.core/first",
   :docstring
   "Returns the first item in the collection. Calls seq on its\nargument. If coll is nil, returns nil."},
  "prim-seq"
  {:ns "cljs.core",
   :name "prim-seq",
   :signature ["[prim]" "[prim i]"],
   :type "function",
   :full-name "cljs.core/prim-seq",
   :docstring "Create seq from a primitive JavaScript Array-like.",
   :examples-strings [],
   :examples-htmls []},
  "Volatile"
  {:ns "cljs.core",
   :name "Volatile",
   :signature ["[state]"],
   :type "type",
   :related ["cljs.core/volatile!" "cljs.core/volatile?"],
   :full-name "cljs.core/Volatile",
   :examples-strings [],
   :examples-htmls []},
  "IEncodeJS"
  {:ns "cljs.core",
   :name "IEncodeJS",
   :type "protocol",
   :full-name "cljs.core/IEncodeJS",
   :examples-strings [],
   :examples-htmls []},
  "Set"
  {:ns "cljs.core",
   :name "Set",
   :signature ["[meta hash-map]"],
   :type "type",
   :full-name "cljs.core/Set",
   :examples-strings [],
   :examples-htmls []},
  "val"
  {:description "Returns the value in the map entry.",
   :examples-htmls [],
   :ns "cljs.core",
   :name "val",
   :signature ["[map-entry]"],
   :type "function",
   :related ["cljs.core/vals"],
   :examples-strings [],
   :full-name "cljs.core/val",
   :docstring "Returns the value in the map entry."},
  "ex-message"
  {:ns "cljs.core",
   :name "ex-message",
   :signature ["[ex]"],
   :type "function",
   :full-name "cljs.core/ex-message",
   :docstring
   "Alpha - subject to change.\nReturns the message attached to the given Error / ExceptionInfo object.\nFor non-Errors returns nil.",
   :examples-strings [],
   :examples-htmls []},
  "set-from-indexed-seq"
  {:ns "cljs.core",
   :name "set-from-indexed-seq",
   :signature ["[iseq]"],
   :type "function",
   :full-name "cljs.core/set-from-indexed-seq",
   :examples-strings [],
   :examples-htmls []},
  "deftype"
  {:ns "cljs.core",
   :name "deftype",
   :signature ["[t fields & impls]"],
   :type "macro",
   :full-name "cljs.core/deftype",
   :docstring
   "(deftype name [fields*]  options* specs*)\n\nCurrently there are no options.\n\nEach spec consists of a protocol or interface name followed by zero\nor more method bodies:\n\nprotocol-or-Object\n(methodName [args*] body)*\n\nThe type will have the (by default, immutable) fields named by\nfields, which can have type hints. Protocols and methods\nare optional. The only methods that can be supplied are those\ndeclared in the protocols/interfaces.  Note that method bodies are\nnot closures, the local environment includes only the named fields,\nand those fields can be accessed directly. Fields can be qualified\nwith the metadata :mutable true at which point (set! afield aval) will be\nsupported in method bodies. Note well that mutable fields are extremely\ndifficult to use correctly, and are present only to facilitate the building\nof higherlevel constructs, such as ClojureScript's reference types, in\nClojureScript itself. They are for experts only - if the semantics and\nimplications of :mutable are not immediately apparent to you, you should not\nbe using them.\n\nMethod definitions take the form:\n\n(methodname [args*] body)\n\nThe argument and return types can be hinted on the arg and\nmethodname symbols. If not supplied, they will be inferred, so type\nhints should be reserved for disambiguation.\n\nMethods should be supplied for all methods of the desired\nprotocol(s). You can also define overrides for methods of Object. Note that\na parameter must be supplied to correspond to the target object\n('this' in JavaScript parlance). Note also that recur calls to the method\nhead should *not* pass the target object, it will be supplied\nautomatically and can not be substituted.\n\nIn the method bodies, the (unqualified) name can be used to name the\nclass (for calls to new, instance? etc).\n\nOne constructor will be defined, taking the designated fields.  Note\nthat the field names __meta and __extmap are currently reserved and\nshould not be used when defining your own types.\n\nGiven (deftype TypeName ...), a factory function called ->TypeName\nwill be defined, taking positional parameters for the fields",
   :examples-strings [],
   :examples-htmls []},
  "defmacro"
  {:description
   "Defines a macro, which is essentially a function that runs at compile time.\nMacros can be used to define syntactic constructs which would require\nprimitives or built-in support in other languages.\n\nUsing macros is as easy as using functions, but writing them is a little more\ndifficult.  Also, creating macros is generally discouraged if you can\naccomplish the same goal with a function.\n\n## Rules and Details\n\nThere is a strict rule for when you can use `defmacro` -- you can only use it\nin what we call a _macro namespace_, effectively forcing you to separate your\ncompile time and runtime code.\n\nA side effect of this is that you cannot use `defmacro` from a REPL.  Sorry!\n\nThis strict rule is due to the nature of differing compile time environments\nfor the optimized \"ClojureScript JVM\" compiler and the newer bootstrapped\n\"ClojureScript JS\" compiler.\n\nIn order to create macros that are portable between either compiler version,\nyou must place macros in a `.cljc` file, but a `.clj` file is sufficient if no\n[reader conditionals][doc:syntax/cond] are needed.  Why would they be needed?\nBecause ClojureScript macro namespaces may be handed off to Clojure for\nevaluation, depending on the compiler version:\n\n| compiler version  | macro namespaces evaluated by |\n|-------------------|-------------------------------|\n| ClojureScript JVM | Clojure                       |\n| ClojureScript JS  | ClojureScript                 |\n\nPlease see the examples section below for a more concrete look.",
   :examples-htmls
   ["<p>Here is a <code>str-&gt;int</code> macro that works for either ClojureScript compiler version.  It simply expands to a <code>js/parseInt</code> call:</p><pre><code class=\"clj\">;; in macros.clj\n&#40;ns foo.macros&#41;\n\n;; expands to a runtime call\n&#40;defmacro str-&gt;int &#91;s&#93;\n  `&#40;js/parseInt s&#41;&#41;\n</code></pre><p>If we want to evaluate the conversion at <i>compile time</i> instead of expanding it to a runtime call, we must use reader conditionals (in a <code>.cljc</code> file) to choose the function appropriate for each compiler's evaluation environment.</p><pre><code class=\"clj\">;; in macros.cljc\n&#40;ns foo.macros&#41;\n\n;; expands to the result of the conversion\n&#40;defmacro str-&gt;int &#91;s&#93;\n  #?&#40;:clj  &#40;Integer/parseInt s&#41;\n     :cljs &#40;js/parseInt s&#41;&#41;&#41;\n</code></pre>"],
   :ns "cljs.core",
   :name "defmacro",
   :signature
   ["[name doc-string? attr-map? [params*] body]"
    "[name doc-string? attr-map? ([params*] body) + attr-map?]"],
   :type "macro",
   :related
   ["syntax/syntax-quote"
    "syntax/unquote"
    "cljs.core/macroexpand"
    "cljs.core/macroexpand-1"],
   :examples-strings
   [["(ns foo.macros)"
     "(defmacro str->int [s]\n  `(js/parseInt s))"
     "(ns foo.macros)"
     "(defmacro str->int [s]\n  #?(:clj  (Integer/parseInt s)\n     :cljs (js/parseInt s)))"]],
   :examples
   [{:id "8040c8",
     :content
     "Here is a `str->int` macro that works for either ClojureScript compiler\nversion.  It simply expands to a `js/parseInt` call:\n\n```clj\n;; in macros.clj\n(ns foo.macros)\n\n;; expands to a runtime call\n(defmacro str->int [s]\n  `(js/parseInt s))\n```\n\nIf we want to evaluate the conversion at _compile time_ instead of expanding it\nto a runtime call, we must use reader conditionals (in a `.cljc` file) to\nchoose the function appropriate for each compiler's evaluation environment.\n\n```clj\n;; in macros.cljc\n(ns foo.macros)\n\n;; expands to the result of the conversion\n(defmacro str->int [s]\n  #?(:clj  (Integer/parseInt s)\n     :cljs (js/parseInt s)))\n```"}],
   :full-name "cljs.core/defmacro",
   :docstring
   "Like defn, but the resulting function name is declared as a\nmacro and will be used as a macro by the compiler when it is\ncalled."},
  "NodeIterator"
  {:ns "cljs.core",
   :name "NodeIterator",
   :signature ["[arr i next-entry next-iter]"],
   :type "type",
   :full-name "cljs.core/NodeIterator",
   :examples-strings [],
   :examples-htmls []},
  "ex-data"
  {:ns "cljs.core",
   :name "ex-data",
   :signature ["[ex]"],
   :type "function",
   :full-name "cljs.core/ex-data",
   :docstring
   "Alpha - subject to change.\nReturns exception data (a map) if ex is an ExceptionInfo.\nOtherwise returns nil.",
   :examples-strings [],
   :examples-htmls []},
  ">"
  {:description
   "Returns true if each successive number argument is less than the previous\none, false otherwise.",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;&gt; 1 2&#41;\n;;=&gt; false\n\n&#40;&gt; 2 1&#41;\n;;=&gt; true\n\n&#40;&gt; 2 2&#41;\n;;=&gt; false\n\n&#40;&gt; 6 5 4 3 2&#41;\n;;=&gt; true\n</code></pre>"],
   :ns "cljs.core",
   :name ">",
   :signature ["[x]" "[x y]" "[x y & more]"],
   :type "function/macro",
   :related ["cljs.core/>="],
   :examples-strings [["(> 1 2)" "(> 2 1)" "(> 2 2)" "(> 6 5 4 3 2)"]],
   :examples
   [{:id "67180c",
     :content
     "```clj\n(> 1 2)\n;;=> false\n\n(> 2 1)\n;;=> true\n\n(> 2 2)\n;;=> false\n\n(> 6 5 4 3 2)\n;;=> true\n```"}],
   :full-name "cljs.core/>",
   :docstring
   "Returns non-nil if nums are in monotonically decreasing order,\notherwise false."},
  "specify!"
  {:ns "cljs.core",
   :name "specify!",
   :signature ["[expr & impls]"],
   :type "macro",
   :full-name "cljs.core/specify!",
   :docstring "Identical to reify but mutates its first argument.",
   :examples-strings [],
   :examples-htmls []},
  "PersistentHashMap.fromArrays"
  {:ns "cljs.core",
   :name "PersistentHashMap.fromArrays",
   :signature ["[ks vs]"],
   :type "function",
   :full-name "cljs.core/PersistentHashMap.fromArrays",
   :examples-strings [],
   :examples-htmls []},
  "fnext"
  {:description "Same as `(first (next coll))`",
   :examples-htmls
   ["<pre><code class=\"clj\">&#40;fnext &#91;1 2 3&#93;&#41;\n;;=&gt; 2\n\n&#40;fnext &#91;1 2&#93;&#41;\n;;=&gt; 2\n\n&#40;fnext &#91;1&#93;&#41;\n;;=&gt; nil\n\n&#40;fnext &#91;&#93;&#41;\n;;=&gt; nil\n</code></pre>"],
   :ns "cljs.core",
   :name "fnext",
   :signature ["[coll]"],
   :type "function",
   :related ["cljs.core/ffirst" "cljs.core/second"],
   :examples-strings
   [["(fnext [1 2 3])" "(fnext [1 2])" "(fnext [1])" "(fnext [])"]],
   :examples
   [{:id "92383f",
     :content
     "```clj\n(fnext [1 2 3])\n;;=> 2\n\n(fnext [1 2])\n;;=> 2\n\n(fnext [1])\n;;=> nil\n\n(fnext [])\n;;=> nil\n```"}],
   :full-name "cljs.core/fnext",
   :docstring "Same as (first (next x))"},
  "with-out-str"
  {:ns "cljs.core",
   :name "with-out-str",
   :signature ["[& body]"],
   :type "macro",
   :full-name "cljs.core/with-out-str",
   :docstring
   "Evaluates exprs in a context in which *print-fn* is bound to .append\non a fresh StringBuffer.  Returns the string created by any nested\nprinting calls.",
   :examples-strings [],
   :examples-htmls []}},
 :release
 {:gclosure-lib "0.0-20151016-61277aea",
  :cljs-version "1.7.170",
  :treader-version "0.10.0-alpha3",
  :cljs-date "2015-11-06",
  :clj-version "1.7.0"}}
)