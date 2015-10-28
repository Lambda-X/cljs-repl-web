# cljs-browser-repl

A reagent app designed to embed a pure ClojureScript REPL in a web page.

The JavaScript will be ```cljs-browser-repl.js``` and it is typically located in ```resources/public/js/compiled```.

In order to build the project in both ```dev``` and ```min``` you first need to have a local copy of ```cljsjs/jqconsole``` (the reason is that it has not yet merged to master).

The steps to generate it at the moment are:

* ```git clone -b cljsjs-jqconsole https://github.com/arichiardi/packages```
* ```cd packages/jqconsole```
* ```boot package build-jar```

[Boot](https://github.com/boot-clj/boot#install) must be installed on the machine.

## Development Mode

In dev mode, an ```out``` folder, containing all the compiled dependencies will be also created in the same folder of ```clojure-browser-repl.js```.

#### Figwheel:

```lein fig-dev```  **or** ```lein fig-dev*``` if you want to clean as well.

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

#### Vanilla ClojureScript REPL:

This is useful in order to be sure that Figwheel does not interfere with the classpath.

There is a problem, solved [here](https://github.com/tailrecursion/lein-simpleton/pull/7) in `lein-simpleton`, so you need either to wait for the merge and then point to the correct version or build locally from my branch (at the moment the latest is `1.4.0-SNAPSHOT`):

```
git clone -b from-fix git@github.com:arichiardi/lein-simpleton.git
cd lein-simpleton
lein install
```

After that, you should have the fixed jar in `.m2/repository`. Come back to `cljs-browser-repl` and build the project with `lein cljsbuild once dev`. Open two terminals.

In the first one, execute:
`./scripts/brepl`  (or `./scripts/brepl.bat`, untested, for Windows)

In the second one, execute:
```
lein simpleton 5042 :from resources/public
```

Then connect your browser to `http://localhost:5042` (your app will appear).

If you want a battery included alias, launch `lein serve`.

## Production Build

```lein minify``` **or** ```lein do clean, cljsbuild once min```

## Testing

Tests at the moment complete smoothly in the Firefox/Chrome Developer Console but not with PhanthomJS or SlimerJS.
The weird returned error form requiring or ns-ing a namespace is:

``` clojure
#error {:message "ERROR", :data {:tag :cljs/analysis-error}, :cause #object[Error Error: Namespace "first.namespace" already declared.]}
```

The former is easier to check: after having booted Figwheel you have to open the Developer Console and run ```luncher.test.run()```. Moreover, tests are executed every time Figwheel reloads.

For improving on the latter, you need first of all [PhantomJS](https://github.com/ariya/phantomjs/) and/or [SlimerJS](http://slimerjs.org/), after which you can: ```lein test-phantom``` and/or ```lein test-slimer``` respectively. Featuring [doo](https://github.com/bensu/doo) here.

## Docs

The documentation tool of choice is [Codox](https://github.com/weavejester/codox). You just need to execute `lein codox` and open `doc/index.html` in order to see the result.

## Resources

 * [JQConsole](https://github.com/replit/jq-console)
 * [CLJSJS](https://github.com/cljsjs/packages)
