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

#### Figwheel in dev mode:

```lein fig-dev```  **or** ```lein figwheel dev```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```lein minify``` **or** ```lein do clean, cljsbuild once min```

## Resources

 * [JQConsole](https://github.com/replit/jq-console)
 * [CLJSJS](https://github.com/cljsjs/packages)
