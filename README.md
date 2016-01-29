# cljs-repl-web (aka http://www.clojurescript.io)

A reagent app designed to embed a pure ClojureScript REPL in a web page.

The JavaScript will be ```cljs-repl-web.js``` and it is typically located in ```resources/public/js/compiled```.


### Generate the ClojureScript API 

The project needs to create the ClojureScript `cljs-repl-web.cljs-api` namespace and relative custom data taken from the amazing work at [cljs-api-info](https://github.com/cljsinfo/cljs-api-docs).

In order to generate the namespace inside `src`,  run:

`lein run -m cljs-api.generator/-main`

If you need to update the `edn` with the latest version from `cljs-api-docs` follow the instructions on their project page. This will generate a `cljs-api.edn` that you will need to copy from `cljs-api-docs/catalog` to `resources`.


## Development Mode

In dev mode, an ```out``` folder, containing all the compiled dependencies will be also created in the same folder of ```cljs-repl-web.js```.

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

After that, you should have the fixed jar in `.m2/repository`. Come back to `cljs-repl-web` and build the project with `lein cljsbuild once dev`. Open two terminals.

In the first one, execute:
`./scripts/brepl`  (or `./scripts/brepl.bat`, untested, for Windows)

In the second one, execute:
```
lein simpleton 5042 :from resources/public
```

Then connect your browser to `http://localhost:5042` (your app will appear).

If you want a battery included alias, launch `lein serve`.

## Production Build

```lein minify```

## Testing

Tests at the moment complete smoothly in the Firefox/Chrome Developer Console, PhantomJS and SlimerJS.

You can test inside the browser's Developer Console after having booted Figwheel using ```luncher.test.run()```. Moreover, tests are executed every time Figwheel reloads.

For headless tests you need first of all [PhantomJS](https://github.com/ariya/phantomjs/) and/or [SlimerJS](http://slimerjs.org/), after which you can: ```lein test-phantom``` and/or ```lein test-slimer``` respectively. Featuring [doo](https://github.com/bensu/doo) here.

## Docs

The documentation tool of choice is [Codox](https://github.com/weavejester/codox). You just need to execute `lein codox` and open `doc/index.html` in order to see the result.

## Resources

 * [JQConsole](https://github.com/replit/jq-console)
 * [CLJSJS](https://github.com/cljsjs/packages)
