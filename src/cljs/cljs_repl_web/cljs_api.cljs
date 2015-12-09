(ns cljs-repl-web.cljs-api)

(def cljs-api-edn {:symbols
 {"partial"
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
   "Returns non-nil if nums are in monotonically decreasing order,\notherwise false."}},
 :release
 {:gclosure-lib "0.0-20151016-61277aea",
  :cljs-version "1.7.170",
  :treader-version "0.10.0-alpha3",
  :cljs-date "2015-11-06",
  :clj-version "1.7.0"}}
)