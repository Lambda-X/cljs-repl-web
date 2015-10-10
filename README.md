************************************************************
* The project still does not work because cljsjs/jqconsole *
* needs to be pushed. I have it in my local repo and I will*
* open a PR this weekend                                   *
************************************************************

# cljs-browser-repl

A reagent app designed to embed a pure ClojureScript REPL.

The JavaScript will be ```clojure-browser-repl.js``` and it is typically located in ```resources/public/js/compiled```.

## Development Mode

In dev mode, an ```out``` folder, containing all the compiled dependencies will be also created in the same folder of ```clojure-browser-repl.js```.

#### Figwheel in dev mode:

```
lein do clean, figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```
lein clean
lein cljsbuild once min
```

## Resources

 * [JQConsole](https://github.com/replit/jq-console)
 * [CLJSJS](https://github.com/cljsjs/packages)
