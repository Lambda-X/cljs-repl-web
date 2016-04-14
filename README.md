# Clojure Web Repl ([clojurescript.io](http://www.clojurescript.io))

A re-frame app designed to embed a pure ClojureScript REPL in a web
page.
Plumbing kindly provided by
<a href="https://github.com/ScalaConsultants/replumb">
 <img width="103px" height="24px" border="0" src="https://raw.githubusercontent.com/ScalaConsultants/replumb/master/images/replumb_logo_bg.jpg" alt="Replumb Logo"/></a>
.

This project also employs the following libraries, whose authors we thank:

* [regent](https://github.com/reagent-project/reagent)
* [re-frame](https://github.com/Day8/re-frame)
* [re-com](https://github.com/Day8/re-com)
* [markdown-clj](https://github.com/yogthos/markdown-clj)
* [hickory](https://github.com/davidsantiago/hickory)
* [CodeMirror](https://github.com/codemirror/CodeMirror)
* [showdown](https://github.com/showdownjs/showdown)
* [enquire.js](https://github.com/WickyNilliams/enquire.js)
* ...and more

Last but not least, many kudos go to [@jaredly](https://github.com/jaredly) and
his [Reepl](https://github.com/jaredly/reepl) with which we share the
CodeMirror implementation (and yes, we released a separate component
[library](https://github.com/Lambda-X/re-console) with it).

## Tooling

We recently switched this project to [boot](http://boot-clj.com/)
<img width="24px" height="24px" src="https://github.com/boot-clj/boot-clj.github.io/blob/master/assets/images/logos/boot-logo-3.png" alt="Boot Logo"/>
and we could not be more happy.

### Interactive workflow

Type `boot dev` and then browse to [http://localhost:3000](http://localhost:3000).

### Build

The same easy command is used to build the content directly, which will be
materialized in the `target` folder:

`boot build -t prod|dev target`

The `build` command defaults to `prod` when called with no arguments.

#### Config

The `cljs-repl-web.config/defaults` var contains the configuration map:

```
{:name "Clojurescript.io Website"
 :production? true|false
 :base-path "root/"
 :core-cache-url "/my-cache/core.cljs.cache.aot.json")
 :src-paths ["/some/path1" "/some/path2")]
 :verbose-repl? true|false}
```

#### Serve files

Sometimes it is useful to serve files from `target`, for instance to check if
everything works fine, kind of simulating the deployment. With `boot` you don't
need no more `python -m SimpleHTTPServer`, only:

`boot serve -d target wait`

Check `boot serve -h` for the other options.

## Testing

Tests at the moment use PhantomJS but there are just a few.

For headless tests you need first of all
[PhantomJS](https://github.com/ariya/phantomjs/). Then you can:

`boot test -t prod|dev`

The `auto-test` tasks also provides automatic test execution on file change.

Featuring [doo](https://github.com/bensu/doo).

## Other Resources

 * [CLJSJS](https://github.com/cljsjs/packages)
 * [Boot built-in tasks](https://github.com/boot-clj/boot/wiki/Built-in-Tasks)

### License

Distributed under the Eclipse Public License, the same as Clojure.

Copyright (C) 2015-16 Scalac Sp. z o.o.

Scalac [scalac.io](http://scalac.io/?utm_source=scalac_github&utm_campaign=scalac1&utm_medium=web) is a full-stack team of great functional developers focused around Scala/Clojure backed by great frontend and mobile developers.

On our [blog](http://blog.scalac.io/?utm_source=scalac_github&utm_campaign=scalac1&utm_medium=web) we share our knowledge with community on how to write great, clean code, how to work remotely and about our culture.
