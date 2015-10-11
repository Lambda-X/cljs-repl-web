/**
 * @fileoverview Google Closure Compiler externs for cljs-browser-repl.js 0.1.0
 * @externs
 * @author Andrea Richiardi
 */

/**
 * @constructor
 * @param {(jQuerySelector|Element|Object|Array.<Element>|jQuery|string|
 *     function())=} outer_container The DOM element into which the console is
 *     inserted.
 * @param {?string|undefined} header Text to print at the top of the console on
 * reset. Optional. Defaults to an empty string.
 * @param {(Element|jQuery|Document| Object.<string,
 *     (string|function(!jQuery.event=))>)=} prompt_label The label to show
 *     before the command prompt. Optional. Defaults to DEFAULT_PROMPT_LABEL.
 * @param {?string|undefined} prompt_continue_label The label to
 *     show before continuation lines of the command prompt. Optional. Defaults
 *     to DEFAULT_PROMPT_CONINUE_LABEL.
 * @return {!JQConsole}
 */
var JQConsole = function(outer_container, header, prompt_label, prompt_continue_label) {};
