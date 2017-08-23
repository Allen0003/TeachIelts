/**
 * extends string prototype object to get a string with a number of characters from a string.
 *
 * @type {Function|*}
 */
String.prototype.trunc = String.prototype.trunc ||
    function(n) {

        // this will return a substring and
        // if its larger than 'n' then truncate and append '...' to the string and return it.
        // if its less than 'n' then return the 'string'
        return this.length > n ? this.substr(0, n - 1) : this.toString();
    };
