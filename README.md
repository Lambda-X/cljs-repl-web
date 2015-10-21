# cljs-browser-repl

A reagent app designed to embed a pure ClojureScript REPL in a web page.

The JavaScript will be ```cljs-browser-repl.js``` and it is typically located in ```resources/public/js/compiled```.

In order to build the project in both ```dev``` and ```min``` you first need to have a local copy of ```cljsjs/jqconsole``` (the reason is that it has not yet merged to master).

The steps to generate it at the moment are:

* ```git clone https://github.com/arichiardi/packages```
* ```cd packages/jqconsole```
* ```boot package build-jar```


## Development Mode

In dev mode, an ```out``` folder, containing all the compiled dependencies will be also created in the same folder of ```clojure-browser-repl.js```.

#### Figwheel:

```lein fig-dev```  **or** ```lein fig-dev*``` if you want to clean as well.

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```lein minify``` **or** ```lein do clean, cljsbuild once min```

## Testing

Tests at the moment complete smoothly in the Firefox/Chrome Developer Console but not with PhanthomJS.

The former is easier to check: after having booted Figwheel you have to open the Developer Console and run ```luncher.test.run()```. Moreover, tests are executed every time Figwheel reloads.

For improving on the latter, you need first of all [PhantomJS](https://github.com/ariya/phantomjs/), after which you can: ```lein unit-test```.

## Resources

 * [JQConsole](https://github.com/replit/jq-console)
 * [CLJSJS](https://github.com/cljsjs/packages)
