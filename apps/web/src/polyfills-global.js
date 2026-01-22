/**
 * Parche para librerías antiguas como sockjs-client que dependen de Node.js 'global'.
 */
if (typeof global === 'undefined') {
    window.global = window;
}
