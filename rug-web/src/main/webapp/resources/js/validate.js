!function (e, t) {
    "object" == typeof exports && "undefined" != typeof module ? module.exports = t() : "function" == typeof define && define.amd ? define(t) : e.validator = t()
}(this, function () {
    "use strict";

    function f(e) {
        if (!("string" == typeof e || e instanceof String)) throw new TypeError("This library (validator.js) validates strings only")
    }

    function i(e) {
        return f(e), e = Date.parse(e), isNaN(e) ? null : new Date(e)
    }

    function r(e) {
        return f(e), parseFloat(e)
    }
    var a = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
        return typeof e
    } : function (e) {
        return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
    };

    function n(e) {
        return "object" === (void 0 === e ? "undefined" : a(e)) && null !== e ? e = "function" == typeof e.toString ? e.toString() : "[object Object]" : (null == e || isNaN(e) && !e.length) && (e = ""), String(e)
    }

    function c() {
        var e = 0 < arguments.length && void 0 !== arguments[0] ? arguments[0] : {},
            t = arguments[1];
        for (var r in t) void 0 === e[r] && (e[r] = t[r]);
        return e
    }

    function d(e, t) {
        f(e);
        var r = void 0,
            o = void 0;
        "object" === (void 0 === t ? "undefined" : a(t)) ? (r = t.min || 0, o = t.max) : (r = t, o = arguments[2]);
        var i = encodeURI(e).split(/%..|./).length - 1;
        return r <= i && (void 0 === o || i <= o)
    }
    var l = {
        require_tld: !0,
        allow_underscores: !1,
        allow_trailing_dot: !1
    };

    function p(e, t) {
        f(e), (t = c(t, l)).allow_trailing_dot && "." === e[e.length - 1] && (e = e.substring(0, e.length - 1));
        for (var r = e.split("."), o = 0; o < r.length; o++)
            if (63 < r[o].length) return !1;
        if (t.require_tld) {
            var i = r.pop();
            if (!r.length || !/^([a-z\u00a1-\uffff]{2,}|xn[a-z0-9-]{2,})$/i.test(i)) return !1;
            if (/[\s\u2002-\u200B\u202F\u205F\u3000\uFEFF\uDB40\uDC20]/.test(i)) return !1
        }
        for (var n, a = 0; a < r.length; a++) {
            if (n = r[a], t.allow_underscores && (n = n.replace(/_/g, "")), !/^[a-z\u00a1-\uffff0-9-]+$/i.test(n)) return !1;
            if (/[\uff01-\uff5e]/.test(n)) return !1;
            if ("-" === n[0] || "-" === n[n.length - 1]) return !1
        }
        return !0
    }
    var g = {
            allow_display_name: !1,
            require_display_name: !1,
            allow_utf8_local_part: !0,
            require_tld: !0
        },
        A = /^[a-z\d!#\$%&'\*\+\-\/=\?\^_`{\|}~\.\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+[a-z\d!#\$%&'\*\+\-\/=\?\^_`{\|}~\,\.\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF\s]*<(.+)>$/i,
        h = /^[a-z\d!#\$%&'\*\+\-\/=\?\^_`{\|}~]+$/i,
        v = /^([\s\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e]|(\\[\x01-\x09\x0b\x0c\x0d-\x7f]))*$/i,
        m = /^[a-z\d!#\$%&'\*\+\-\/=\?\^_`{\|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+$/i,
        _ = /^([\s\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|(\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*$/i;
    var s = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/,
        u = /^[0-9A-F]{1,4}$/i;

    function F(e) {
        var t = 1 < arguments.length && void 0 !== arguments[1] ? arguments[1] : "";
        if (f(e), !(t = String(t))) return F(e, 4) || F(e, 6);
        if ("4" === t) return !!s.test(e) && e.split(".").sort(function (e, t) {
            return e - t
        })[3] <= 255;
        if ("6" === t) {
            var r = e.split(":"),
                o = !1,
                i = F(r[r.length - 1], 4),
                n = i ? 7 : 8;
            if (r.length > n) return !1;
            if ("::" === e) return !0;
            "::" === e.substr(0, 2) ? (r.shift(), r.shift(), o = !0) : "::" === e.substr(e.length - 2) && (r.pop(), r.pop(), o = !0);
            for (var a = 0; a < r.length; ++a)
                if ("" === r[a] && 0 < a && a < r.length - 1) {
                    if (o) return !1;
                    o = !0
                } else if (i && a === r.length - 1);
            else if (!u.test(r[a])) return !1;
            return o ? 1 <= r.length : r.length === n
        }
        return !1
    }
    var $ = {
            protocols: ["http", "https", "ftp"],
            require_tld: !0,
            require_protocol: !1,
            require_host: !0,
            require_valid_protocol: !0,
            allow_underscores: !1,
            allow_trailing_dot: !1,
            allow_protocol_relative_urls: !1
        },
        S = /^\[([^\]]+)\](?::([0-9]+))?$/;

    function R(e, t) {
        for (var r = 0; r < t.length; r++) {
            var o = t[r];
            if (e === o || (i = o, "[object RegExp]" === Object.prototype.toString.call(i) && o.test(e))) return !0
        }
        var i;
        return !1
    }
    var t = /^([0-9a-fA-F][0-9a-fA-F]:){5}([0-9a-fA-F][0-9a-fA-F])$/;
    for (var e, o = {
        "en-US": /^[A-Z]+$/i,
        "bg-BG": /^[?-?]+$/i,
        "cs-CZ": /^[A-Z???????????????]+$/i,
        "da-DK": /^[A-Z???]+$/i,
        "de-DE": /^[A-Z????]+$/i,
        "el-GR": /^[?-?]+$/i,
        "es-ES": /^[A-Z???????]+$/i,
        "fr-FR": /^[A-Z????????????????]+$/i,
        "it-IT": /^[A-Z????????]+$/i,
        "nb-NO": /^[A-Z???]+$/i,
        "nl-NL": /^[A-Z????????]+$/i,
        "nn-NO": /^[A-Z???]+$/i,
        "hu-HU": /^[A-Z?????????]+$/i,
        "pl-PL": /^[A-Z?????????]+$/i,
        "pt-PT": /^[A-Z?????????????]+$/i,
        "ru-RU": /^[?-??]+$/i,
        "sk-SK": /^[A-Z?????????????????]+$/i,
        "sr-RS@latin": /^[A-Z?????]+$/i,
        "sr-RS": /^[?-???????]+$/i,
        "sv-SE": /^[A-Z???]+$/i,
        "tr-TR": /^[A-Z???????]+$/i,
        "uk-UA": /^[?-?????I???]+$/i,
        ar: /^[?????????????????????????????????????????????]+$/
        }, E = {
            "en-US": /^[0-9A-Z]+$/i,
            "bg-BG": /^[0-9??-?]+$/i,
            "cs-CZ": /^[0-9A-Z??????????????????]+$/i,
            "da-DK": /^[0-9A-Z???]+$/i,
            "de-DE": /^[0-9A-Z????]+$/i,
            "el-GR": /^[0-9?-?]+$/i,
            "es-ES": /^[0-9A-Z?????????]+$/i,
            "fr-FR": /^[0-9A-Z?????????????????]+$/i,
            "it-IT": /^[0-9A-Z????????]+$/i,
            "hu-HU": /^[0-9A-Z????????????]+$/i,
            "nb-NO": /^[0-9A-Z???]+$/i,
            "nl-NL": /^[0-9A-Z??????????]+$/i,
            "nn-NO": /^[0-9A-Z???]+$/i,
            "pl-PL": /^[0-9A-Z??????????]+$/i,
            "pt-PT": /^[0-9A-Z???????????????]+$/i,
            "ru-RU": /^[0-9??-???]+$/i,
            "sk-SK": /^[0-9A-Z????????????????????]+$/i,
            "sr-RS@latin": /^[0-9A-Z??????]+$/i,
            "sr-RS": /^[0-9??-????????]+$/i,
            "sv-SE": /^[0-9A-Z???]+$/i,
            "tr-TR": /^[0-9A-Z???????]+$/i,
            "uk-UA": /^[0-9??-?????I????]+$/i,
            ar: /^[??????????0-9?????????????????????????????????????????????????]+$/
        }, x = {
            "en-US": ".",
            ar: "????"
        }, M = ["AU", "GB", "HK", "IN", "NZ", "ZA", "ZM"], C = 0; C < M.length; C++) o[e = "en-" + M[C]] = o["en-US"], E[e] = E["en-US"], x[e] = x["en-US"];
    for (var N, w = ["AE", "BH", "DZ", "EG", "IQ", "JO", "KW", "LB", "LY", "MA", "QM", "QA", "SA", "SD", "SY", "TN", "YE"], T = 0; T < w.length; T++) o[N = "ar-" + w[T]] = o.ar, E[N] = E.ar, x[N] = x.ar;
    for (var Z = [], B = ["bg-BG", "cs-CZ", "da-DK", "de-DE", "el-GR", "es-ES", "fr-FR", "it-IT", "hu-HU", "nb-NO", "nn-NO", "nl-NL", "pl-Pl", "pt-PT", "ru-RU", "sr-RS@latin", "sr-RS", "sv-SE", "tr-TR", "uk-UA"], I = 0; I < Z.length; I++) x[Z[I]] = x["en-US"];
    for (var L = 0; L < B.length; L++) x[B[L]] = ",";
    o["pt-BR"] = o["pt-PT"], E["pt-BR"] = E["pt-PT"], x["pt-BR"] = x["pt-PT"];
    var y = /^[+-]?([0-9]*[.])?[0-9]+$/;
    var G = /^(?:[-+]?(?:0|[1-9][0-9]*))$/,
        D = /^[-+]?[0-9]+$/;

    function O(e, t) {
        f(e);
        var r = (t = t || {}).hasOwnProperty("allow_leading_zeroes") && !t.allow_leading_zeroes ? G : D,
            o = !t.hasOwnProperty("min") || e >= t.min,
            i = !t.hasOwnProperty("max") || e <= t.max,
            n = !t.hasOwnProperty("lt") || e < t.lt,
            a = !t.hasOwnProperty("gt") || e > t.gt;
        return r.test(e) && o && i && n && a
    }
    var U = /^[\x00-\x7F]+$/;
    var b = /[^\u0020-\u007E\uFF61-\uFF9F\uFFA0-\uFFDC\uFFE8-\uFFEE0-9a-zA-Z]/;
    var P = /[\u0020-\u007E\uFF61-\uFF9F\uFFA0-\uFFDC\uFFE8-\uFFEE0-9a-zA-Z]/;
    var K = /[^\x00-\x7F]/;
    var k = /[\uD800-\uDBFF][\uDC00-\uDFFF]/;
    var H = {
            force_decimal: !1,
            decimal_digits: "1,",
            locale: "en-US"
        },
        z = ["", "-", "+"];
    var V = /^[0-9A-F]+$/i;

    function W(e) {
        return f(e), V.test(e)
    }
    var Y = /^#?([0-9A-F]{3}|[0-9A-F]{6})$/i;
    var j = /^[A-Z]{2}[0-9A-Z]{3}\d{2}\d{5}$/;
    var J = /^[a-f0-9]{32}$/;
    var q = {
        md5: 32,
        md4: 32,
        sha1: 40,
        sha256: 64,
        sha384: 96,
        sha512: 128,
        ripemd128: 32,
        ripemd160: 40,
        tiger128: 32,
        tiger160: 40,
        tiger192: 48,
        crc32: 8,
        crc32b: 8
    };
    var Q = {
        3: /^[0-9A-F]{8}-[0-9A-F]{4}-3[0-9A-F]{3}-[0-9A-F]{4}-[0-9A-F]{12}$/i,
        4: /^[0-9A-F]{8}-[0-9A-F]{4}-4[0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$/i,
        5: /^[0-9A-F]{8}-[0-9A-F]{4}-5[0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$/i,
        all: /^[0-9A-F]{8}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{12}$/i
    };
    var X = /^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|(222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11}|6[27][0-9]{14})$/;
    var ee = /^[A-Z]{2}[0-9A-Z]{9}[0-9]$/;
    var te = /^(?:[0-9]{9}X|[0-9]{10})$/,
        re = /^(?:[0-9]{13})$/,
        oe = [1, 3];
    var ie = "^\\d{4}-?\\d{3}[\\dX]$";
    var ne = {
        "ar-AE": /^((\+?971)|0)?5[024568]\d{7}$/,
        "ar-DZ": /^(\+?213|0)(5|6|7)\d{8}$/,
        "ar-EG": /^((\+?20)|0)?1[012]\d{8}$/,
        "ar-JO": /^(\+?962|0)?7[789]\d{7}$/,
        "ar-SA": /^(!?(\+?966)|0)?5\d{8}$/,
        "ar-SY": /^(!?(\+?963)|0)?9\d{8}$/,
        "be-BY": /^(\+?375)?(24|25|29|33|44)\d{7}$/,
        "bg-BG": /^(\+?359|0)?8[789]\d{7}$/,
        "cs-CZ": /^(\+?420)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$/,
        "da-DK": /^(\+?45)?\s?\d{2}\s?\d{2}\s?\d{2}\s?\d{2}$/,
        "de-DE": /^(\+?49[ \.\-])?([\(]{1}[0-9]{1,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
        "el-GR": /^(\+?30|0)?(69\d{8})$/,
        "en-AU": /^(\+?61|0)4\d{8}$/,
        "en-GB": /^(\+?44|0)7\d{9}$/,
        "en-HK": /^(\+?852\-?)?[456789]\d{3}\-?\d{4}$/,
        "en-IN": /^(\+?91|0)?[6789]\d{9}$/,
        "en-KE": /^(\+?254|0)?[7]\d{8}$/,
        "en-NG": /^(\+?234|0)?[789]\d{9}$/,
        "en-NZ": /^(\+?64|0)2\d{7,9}$/,
        "en-PK": /^((\+92)|(0092))-{0,1}\d{3}-{0,1}\d{7}$|^\d{11}$|^\d{4}-\d{7}$/,
        "en-RW": /^(\+?250|0)?[7]\d{8}$/,
        "en-SG": /^(\+65)?[89]\d{7}$/,
        "en-TZ": /^(\+?255|0)?[67]\d{8}$/,
        "en-UG": /^(\+?256|0)?[7]\d{8}$/,
        "en-US": /^(\+?1)?[2-9]\d{2}[2-9](?!11)\d{6}$/,
        "en-ZA": /^(\+?27|0)\d{9}$/,
        "en-ZM": /^(\+?26)?09[567]\d{7}$/,
        "es-ES": /^(\+?34)?(6\d{1}|7[1234])\d{7}$/,
        "et-EE": /^(\+?372)?\s?(5|8[1-4])\s?([0-9]\s?){6,7}$/,
        "fa-IR": /^(\+?98[\-\s]?|0)9[0-39]\d[\-\s]?\d{3}[\-\s]?\d{4}$/,
        "fi-FI": /^(\+?358|0)\s?(4(0|1|2|4|5|6)?|50)\s?(\d\s?){4,8}\d$/,
        "fo-FO": /^(\+?298)?\s?\d{2}\s?\d{2}\s?\d{2}$/,
        "fr-FR": /^(\+?33|0)[67]\d{8}$/,
        "he-IL": /^(\+972|0)([23489]|5[012345689]|77)[1-9]\d{6}/,
        "hu-HU": /^(\+?36)(20|30|70)\d{7}$/,
        "id-ID": /^(\+?62|0[1-9])[\s|\d]+$/,
        "it-IT": /^(\+?39)?\s?3\d{2} ?\d{6,7}$/,
        "ja-JP": /^(\+?81|0)[789]0[ \-]?[1-9]\d{2}[ \-]?\d{5}$/,
        "kk-KZ": /^(\+?7|8)?7\d{9}$/,
        "kl-GL": /^(\+?299)?\s?\d{2}\s?\d{2}\s?\d{2}$/,
        "ko-KR": /^((\+?82)[ \-]?)?0?1([0|1|6|7|8|9]{1})[ \-]?\d{3,4}[ \-]?\d{4}$/,
        "lt-LT": /^(\+370|8)\d{8}$/,
        "ms-MY": /^(\+?6?01){1}(([145]{1}(\-|\s)?\d{7,8})|([236789]{1}(\s|\-)?\d{7}))$/,
        "nb-NO": /^(\+?47)?[49]\d{7}$/,
        "nl-BE": /^(\+?32|0)4?\d{8}$/,
        "nn-NO": /^(\+?47)?[49]\d{7}$/,
        "pl-PL": /^(\+?48)? ?[5-8]\d ?\d{3} ?\d{2} ?\d{2}$/,
        "pt-BR": /^(\+?55|0)\-?[1-9]{2}\-?[2-9]{1}\d{3,4}\-?\d{4}$/,
        "pt-PT": /^(\+?351)?9[1236]\d{7}$/,
        "ro-RO": /^(\+?4?0)\s?7\d{2}(\/|\s|\.|\-)?\d{3}(\s|\.|\-)?\d{3}$/,
        "ru-RU": /^(\+?7|8)?9\d{9}$/,
        "sk-SK": /^(\+?421)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$/,
        "sr-RS": /^(\+3816|06)[- \d]{5,9}$/,
        "th-TH": /^(\+66|66|0)\d{9}$/,
        "tr-TR": /^(\+?90|0)?5\d{9}$/,
        "uk-UA": /^(\+?38|8)?0\d{9}$/,
        "vi-VN": /^(\+?84|0)?((1(2([0-9])|6([2-9])|88|99))|(9((?!5)[0-9])))([0-9]{7})$/,
        "zh-CN": /^(\+?0?86\-?)?1[3456789]\d{9}$/,
        "zh-TW": /^(\+?886\-?|0)?9\d{8}$/
    };
    ne["en-CA"] = ne["en-US"], ne["fr-BE"] = ne["nl-BE"], ne["zh-HK"] = ne["en-HK"];
    var ae = {
        symbol: "$",
        require_symbol: !1,
        allow_space_after_symbol: !1,
        symbol_after_digits: !1,
        allow_negatives: !0,
        parens_for_negatives: !1,
        negative_sign_before_digits: !1,
        negative_sign_after_digits: !1,
        allow_negative_sign_placeholder: !1,
        thousands_separator: ",",
        decimal_separator: ".",
        allow_decimal: !0,
        require_decimal: !1,
        digits_after_decimal: [2],
        allow_space_after_digits: !1
    };
    var le = /^([\+-]?\d{4}(?!\d{2}\b))((-?)((0[1-9]|1[0-2])(\3([12]\d|0[1-9]|3[01]))?|W([0-4]\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\d|[12]\d{2}|3([0-5]\d|6[1-6])))([T\s]((([01]\d|2[0-3])((:?)[0-5]\d)?|24:?00)([\.,]\d+(?!:))?)?(\17[0-5]\d([\.,]\d+)?)?([zZ]|([\+-])([01]\d|2[0-3]):?([0-5]\d)?)?)?)?$/;
    var se = /([01][0-9]|2[0-3])/,
        ue = /[0-5][0-9]/,
        de = new RegExp("[-+]" + se.source + ":" + ue.source),
        ce = new RegExp("([zZ]|" + de.source + ")"),
        fe = new RegExp(se.source + ":" + ue.source + ":" + /([0-5][0-9]|60)/.source + /(\.[0-9]+)?/.source),
        pe = new RegExp(/[0-9]{4}/.source + "-" + /(0[1-9]|1[0-2])/.source + "-" + /([12]\d|0[1-9]|3[01])/.source),
        ge = new RegExp("" + fe.source + ce.source),
        Ae = new RegExp(pe.source + "[ tT]" + ge.source);
    var he = ["AD", "AE", "AF", "AG", "AI", "AL", "AM", "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BL", "BM", "BN", "BO", "BQ", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CU", "CV", "CW", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE", "EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO", "FR", "GA", "GB", "GD", "GE", "GF", "GG", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IM", "IN", "IO", "IQ", "IR", "IS", "IT", "JE", "JM", "JO", "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MF", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS", "RU", "RW", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "SS", "ST", "SV", "SX", "SY", "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU", "WF", "WS", "YE", "YT", "ZA", "ZM", "ZW"];
    var ve = ["AFG", "ALA", "ALB", "DZA", "ASM", "AND", "AGO", "AIA", "ATA", "ATG", "ARG", "ARM", "ABW", "AUS", "AUT", "AZE", "BHS", "BHR", "BGD", "BRB", "BLR", "BEL", "BLZ", "BEN", "BMU", "BTN", "BOL", "BES", "BIH", "BWA", "BVT", "BRA", "IOT", "BRN", "BGR", "BFA", "BDI", "KHM", "CMR", "CAN", "CPV", "CYM", "CAF", "TCD", "CHL", "CHN", "CXR", "CCK", "COL", "COM", "COG", "COD", "COK", "CRI", "CIV", "HRV", "CUB", "CUW", "CYP", "CZE", "DNK", "DJI", "DMA", "DOM", "ECU", "EGY", "SLV", "GNQ", "ERI", "EST", "ETH", "FLK", "FRO", "FJI", "FIN", "FRA", "GUF", "PYF", "ATF", "GAB", "GMB", "GEO", "DEU", "GHA", "GIB", "GRC", "GRL", "GRD", "GLP", "GUM", "GTM", "GGY", "GIN", "GNB", "GUY", "HTI", "HMD", "VAT", "HND", "HKG", "HUN", "ISL", "IND", "IDN", "IRN", "IRQ", "IRL", "IMN", "ISR", "ITA", "JAM", "JPN", "JEY", "JOR", "KAZ", "KEN", "KIR", "PRK", "KOR", "KWT", "KGZ", "LAO", "LVA", "LBN", "LSO", "LBR", "LBY", "LIE", "LTU", "LUX", "MAC", "MKD", "MDG", "MWI", "MYS", "MDV", "MLI", "MLT", "MHL", "MTQ", "MRT", "MUS", "MYT", "MEX", "FSM", "MDA", "MCO", "MNG", "MNE", "MSR", "MAR", "MOZ", "MMR", "NAM", "NRU", "NPL", "NLD", "NCL", "NZL", "NIC", "NER", "NGA", "NIU", "NFK", "MNP", "NOR", "OMN", "PAK", "PLW", "PSE", "PAN", "PNG", "PRY", "PER", "PHL", "PCN", "POL", "PRT", "PRI", "QAT", "REU", "ROU", "RUS", "RWA", "BLM", "SHN", "KNA", "LCA", "MAF", "SPM", "VCT", "WSM", "SMR", "STP", "SAU", "SEN", "SRB", "SYC", "SLE", "SGP", "SXM", "SVK", "SVN", "SLB", "SOM", "ZAF", "SGS", "SSD", "ESP", "LKA", "SDN", "SUR", "SJM", "SWZ", "SWE", "CHE", "SYR", "TWN", "TJK", "TZA", "THA", "TLS", "TGO", "TKL", "TON", "TTO", "TUN", "TUR", "TKM", "TCA", "TUV", "UGA", "UKR", "ARE", "GBR", "USA", "UMI", "URY", "UZB", "VUT", "VEN", "VNM", "VGB", "VIR", "WLF", "ESH", "YEM", "ZMB", "ZWE"];
    var me = /[^A-Z0-9+\/=]/i;
    var _e = /^[a-z]+\/[a-z0-9\-\+]+$/i,
        Fe = /^[a-z\-]+=[a-z0-9\-]+$/i,
        $e = /^[a-z0-9!\$&'\(\)\*\+,;=\-\._~:@\/\?%\s]*$/i;
    var Se = /^(application|audio|font|image|message|model|multipart|text|video)\/[a-zA-Z0-9\.\-\+]{1,100}$/i,
        Re = /^text\/[a-zA-Z0-9\.\-\+]{1,100};\s?charset=("[a-zA-Z0-9\.\-\+\s]{0,70}"|[a-zA-Z0-9\.\-\+]{0,70})(\s?\([a-zA-Z0-9\.\-\+\s]{1,20}\))?$/i,
        Ee = /^multipart\/[a-zA-Z0-9\.\-\+]{1,100}(;\s?(boundary|charset)=("[a-zA-Z0-9\.\-\+\s]{0,70}"|[a-zA-Z0-9\.\-\+]{0,70})(\s?\([a-zA-Z0-9\.\-\+\s]{1,20}\))?){0,2}$/i;
    var xe = /^\(?[+-]?(90(\.0+)?|[1-8]?\d(\.\d+)?)$/,
        Me = /^\s?[+-]?(180(\.0+)?|1[0-7]\d(\.\d+)?|\d{1,2}(\.\d+)?)\)?$/,
        Ce = /^\d{4}$/,
        Ne = /^\d{5}$/,
        we = /^\d{6}$/,
        Te = {
            AT: Ce,
            AU: Ce,
            BE: Ce,
            BG: Ce,
            CA: /^[ABCEGHJKLMNPRSTVXY]\d[ABCEGHJ-NPRSTV-Z][\s\-]?\d[ABCEGHJ-NPRSTV-Z]\d$/i,
            CH: Ce,
            CZ: /^\d{3}\s?\d{2}$/,
            DE: Ne,
            DK: Ce,
            DZ: Ne,
            ES: Ne,
            FI: Ne,
            FR: /^\d{2}\s?\d{3}$/,
            GB: /^(gir\s?0aa|[a-z]{1,2}\d[\da-z]?\s?(\d[a-z]{2})?)$/i,
            GR: /^\d{3}\s?\d{2}$/,
            IL: Ne,
            IN: we,
            IS: /^\d{3}$/,
            IT: Ne,
            JP: /^\d{3}\-\d{4}$/,
            KE: Ne,
            LI: /^(948[5-9]|949[0-7])$/,
            MX: Ne,
            NL: /^\d{4}\s?[a-z]{2}$/i,
            NO: Ce,
            PL: /^\d{2}\-\d{3}$/,
            PT: /^\d{4}\-\d{3}?$/,
            RO: we,
            RU: we,
            SA: Ne,
            SE: /^\d{3}\s?\d{2}$/,
            SK: /^\d{3}\s?\d{2}$/,
            TW: /^\d{3}(\d{2})?$/,
            US: /^\d{5}(-\d{4})?$/,
            ZA: Ce,
            ZM: Ne
        };

    function Ze(e, t) {
        f(e);
        var r = t ? new RegExp("^[" + t + "]+", "g") : /^\s+/g;
        return e.replace(r, "")
    }

    function Be(e, t) {
        f(e);
        for (var r = t ? new RegExp("[" + t + "]") : /\s/, o = e.length - 1; 0 <= o && r.test(e[o]);) o--;
        return o < e.length ? e.substr(0, o + 1) : e
    }

    function Ie(e, t) {
        return f(e), e.replace(new RegExp("[" + t + "]+", "g"), "")
    }
    var Le = {
            all_lowercase: !0,
            gmail_lowercase: !0,
            gmail_remove_dots: !0,
            gmail_remove_subaddress: !0,
            gmail_convert_googlemaildotcom: !0,
            outlookdotcom_lowercase: !0,
            outlookdotcom_remove_subaddress: !0,
            yahoo_lowercase: !0,
            yahoo_remove_subaddress: !0,
            yandex_lowercase: !0,
            icloud_lowercase: !0,
            icloud_remove_subaddress: !0
        },
        ye = ["icloud.com", "me.com"],
        Ge = ["hotmail.at", "hotmail.be", "hotmail.ca", "hotmail.cl", "hotmail.co.il", "hotmail.co.nz", "hotmail.co.th", "hotmail.co.uk", "hotmail.com", "hotmail.com.ar", "hotmail.com.au", "hotmail.com.br", "hotmail.com.gr", "hotmail.com.mx", "hotmail.com.pe", "hotmail.com.tr", "hotmail.com.vn", "hotmail.cz", "hotmail.de", "hotmail.dk", "hotmail.es", "hotmail.fr", "hotmail.hu", "hotmail.id", "hotmail.ie", "hotmail.in", "hotmail.it", "hotmail.jp", "hotmail.kr", "hotmail.lv", "hotmail.my", "hotmail.ph", "hotmail.pt", "hotmail.sa", "hotmail.sg", "hotmail.sk", "live.be", "live.co.uk", "live.com", "live.com.ar", "live.com.mx", "live.de", "live.es", "live.eu", "live.fr", "live.it", "live.nl", "msn.com", "outlook.at", "outlook.be", "outlook.cl", "outlook.co.il", "outlook.co.nz", "outlook.co.th", "outlook.com", "outlook.com.ar", "outlook.com.au", "outlook.com.br", "outlook.com.gr", "outlook.com.pe", "outlook.com.tr", "outlook.com.vn", "outlook.cz", "outlook.de", "outlook.dk", "outlook.es", "outlook.fr", "outlook.hu", "outlook.id", "outlook.ie", "outlook.in", "outlook.it", "outlook.jp", "outlook.kr", "outlook.lv", "outlook.my", "outlook.ph", "outlook.pt", "outlook.sa", "outlook.sg", "outlook.sk", "passport.com"],
        De = ["rocketmail.com", "yahoo.ca", "yahoo.co.uk", "yahoo.com", "yahoo.de", "yahoo.fr", "yahoo.in", "yahoo.it", "ymail.com"],
        Oe = ["yandex.ru", "yandex.ua", "yandex.kz", "yandex.com", "yandex.by", "ya.ru"];

    function Ue(e) {
        return 1 < e.length ? e : ""
    }
    return {
        version: "10.1.0",
        toDate: i,
        toFloat: r,
        toInt: function (e, t) {
            return f(e), parseInt(e, t || 10)
        },
        toBoolean: function (e, t) {
            return f(e), t ? "1" === e || "true" === e : "0" !== e && "false" !== e && "" !== e
        },
        equals: function (e, t) {
            return f(e), e === t
        },
        contains: function (e, t) {
            return f(e), 0 <= e.indexOf(n(t))
        },
        matches: function (e, t, r) {
            return f(e), "[object RegExp]" !== Object.prototype.toString.call(t) && (t = new RegExp(t, r)), t.test(e)
        },
        isEmail: function (e, t) {
            if (f(e), (t = c(t, g)).require_display_name || t.allow_display_name) {
                var r = e.match(A);
                if (r) e = r[1];
                else if (t.require_display_name) return !1
            }
            var o = e.split("@"),
                i = o.pop(),
                n = o.join("@"),
                a = i.toLowerCase();
            if ("gmail.com" !== a && "googlemail.com" !== a || (n = n.toLowerCase()), !d(n, {
                    max: 64
                }) || !d(i, {
                    max: 254
                })) return !1;
            if (!p(i, {
                    require_tld: t.require_tld
                })) return !1;
            if ('"' === n[0]) return n = n.slice(1, n.length - 1), t.allow_utf8_local_part ? _.test(n) : v.test(n);
            for (var l = t.allow_utf8_local_part ? m : h, s = n.split("."), u = 0; u < s.length; u++)
                if (!l.test(s[u])) return !1;
            return !0
        },
        isURL: function (e, t) {
            if (f(e), !e || 2083 <= e.length || /[\s<>]/.test(e)) return !1;
            if (0 === e.indexOf("mailto:")) return !1;
            t = c(t, $);
            var r = void 0,
                o = void 0,
                i = void 0,
                n = void 0,
                a = void 0,
                l = void 0,
                s = void 0,
                u = void 0;
            if (1 < (s = (e = (s = (e = (s = e.split("#")).shift()).split("?")).shift()).split("://")).length) {
                if (r = s.shift(), t.require_valid_protocol && -1 === t.protocols.indexOf(r)) return !1
            } else {
                if (t.require_protocol) return !1;
                t.allow_protocol_relative_urls && "//" === e.substr(0, 2) && (s[0] = e.substr(2))
            }
            if ("" === (e = s.join("://"))) return !1;
            if ("" === (e = (s = e.split("/")).shift()) && !t.require_host) return !0;
            if (1 < (s = e.split("@")).length && 0 <= (o = s.shift()).indexOf(":") && 2 < o.split(":").length) return !1;
            u = l = null;
            var d = (n = s.join("@")).match(S);
            return d ? (i = "", u = d[1], l = d[2] || null) : (i = (s = n.split(":")).shift(), s.length && (l = s.join(":"))), !(null !== l && (a = parseInt(l, 10), !/^[0-9]+$/.test(l) || a <= 0 || 65535 < a) || !(F(i) || p(i, t) || u && F(u, 6)) || (i = i || u, t.host_whitelist && !R(i, t.host_whitelist) || t.host_blacklist && R(i, t.host_blacklist)))
        },
        isMACAddress: function (e) {
            return f(e), t.test(e)
        },
        isIP: F,
        isFQDN: p,
        isBoolean: function (e) {
            return f(e), 0 <= ["true", "false", "1", "0"].indexOf(e)
        },
        isAlpha: function (e) {
            var t = 1 < arguments.length && void 0 !== arguments[1] ? arguments[1] : "en-US";
            if (f(e), t in o) return o[t].test(e);
            throw new Error("Invalid locale '" + t + "'")
        },
        isAlphanumeric: function (e) {
            var t = 1 < arguments.length && void 0 !== arguments[1] ? arguments[1] : "en-US";
            if (f(e), t in E) return E[t].test(e);
            throw new Error("Invalid locale '" + t + "'")
        },
        isNumeric: function (e) {
            return f(e), y.test(e)
        },
        isPort: function (e) {
            return O(e, {
                min: 0,
                max: 65535
            })
        },
        isLowercase: function (e) {
            return f(e), e === e.toLowerCase()
        },
        isUppercase: function (e) {
            return f(e), e === e.toUpperCase()
        },
        isAscii: function (e) {
            return f(e), U.test(e)
        },
        isFullWidth: function (e) {
            return f(e), b.test(e)
        },
        isHalfWidth: function (e) {
            return f(e), P.test(e)
        },
        isVariableWidth: function (e) {
            return f(e), b.test(e) && P.test(e)
        },
        isMultibyte: function (e) {
            return f(e), K.test(e)
        },
        isSurrogatePair: function (e) {
            return f(e), k.test(e)
        },
        isInt: O,
        isFloat: function (e, t) {
            f(e), t = t || {};
            var r = new RegExp("^(?:[-+])?(?:[0-9]+)?(?:\\" + (t.locale ? x[t.locale] : ".") + "[0-9]*)?(?:[eE][\\+\\-]?(?:[0-9]+))?$");
            if ("" === e || "." === e || "-" === e || "+" === e) return !1;
            var o = parseFloat(e.replace(",", "."));
            return r.test(e) && (!t.hasOwnProperty("min") || o >= t.min) && (!t.hasOwnProperty("max") || o <= t.max) && (!t.hasOwnProperty("lt") || o < t.lt) && (!t.hasOwnProperty("gt") || o > t.gt)
        },
        isDecimal: function (e, t) {
            if (f(e), (t = c(t, H)).locale in x) return !z.includes(e.replace(/ /g, "")) && (r = t, new RegExp("^[-+]?([0-9]+)?(\\" + x[r.locale] + "[0-9]{" + r.decimal_digits + "})" + (r.force_decimal ? "" : "?") + "$")).test(e);
            var r;
            throw new Error("Invalid locale '" + t.locale + "'")
        },
        isHexadecimal: W,
        isDivisibleBy: function (e, t) {
            return f(e), r(e) % parseInt(t, 10) == 0
        },
        isHexColor: function (e) {
            return f(e), Y.test(e)
        },
        isISRC: function (e) {
            return f(e), j.test(e)
        },
        isMD5: function (e) {
            return f(e), J.test(e)
        },
        isHash: function (e, t) {
            return f(e), new RegExp("^[a-f0-9]{" + q[t] + "}$").test(e)
        },
        isJSON: function (e) {
            f(e);
            try {
                var t = JSON.parse(e);
                return !!t && "object" === (void 0 === t ? "undefined" : a(t))
            } catch (e) {}
            return !1
        },
        isEmpty: function (e) {
            return f(e), 0 === e.length
        },
        isLength: function (e, t) {
            f(e);
            var r = void 0,
                o = void 0;
            "object" === (void 0 === t ? "undefined" : a(t)) ? (r = t.min || 0, o = t.max) : (r = t, o = arguments[2]);
            var i = e.match(/[\uD800-\uDBFF][\uDC00-\uDFFF]/g) || [],
                n = e.length - i.length;
            return r <= n && (void 0 === o || n <= o)
        },
        isByteLength: d,
        isUUID: function (e) {
            var t = 1 < arguments.length && void 0 !== arguments[1] ? arguments[1] : "all";
            f(e);
            var r = Q[t];
            return r && r.test(e)
        },
        isMongoId: function (e) {
            return f(e), W(e) && 24 === e.length
        },
        isAfter: function (e) {
            var t = 1 < arguments.length && void 0 !== arguments[1] ? arguments[1] : String(new Date);
            f(e);
            var r = i(t),
                o = i(e);
            return !!(o && r && r < o)
        },
        isBefore: function (e) {
            var t = 1 < arguments.length && void 0 !== arguments[1] ? arguments[1] : String(new Date);
            f(e);
            var r = i(t),
                o = i(e);
            return !!(o && r && o < r)
        },
        isIn: function (e, t) {
            f(e);
            var r = void 0;
            if ("[object Array]" === Object.prototype.toString.call(t)) {
                var o = [];
                for (r in t)({}).hasOwnProperty.call(t, r) && (o[r] = n(t[r]));
                return 0 <= o.indexOf(e)
            }
            return "object" === (void 0 === t ? "undefined" : a(t)) ? t.hasOwnProperty(e) : !(!t || "function" != typeof t.indexOf) && 0 <= t.indexOf(e)
        },
        isCreditCard: function (e) {
            f(e);
            var t = e.replace(/[- ]+/g, "");
            if (!X.test(t)) return !1;
            for (var r = 0, o = void 0, i = void 0, n = void 0, a = t.length - 1; 0 <= a; a--) o = t.substring(a, a + 1), i = parseInt(o, 10), r += n && 10 <= (i *= 2) ? i % 10 + 1 : i, n = !n;
            return !(r % 10 != 0 || !t)
        },
        isISIN: function (e) {
            if (f(e), !ee.test(e)) return !1;
            for (var t = e.replace(/[A-Z]/g, function (e) {
                    return parseInt(e, 36)
                }), r = 0, o = void 0, i = void 0, n = !0, a = t.length - 2; 0 <= a; a--) o = t.substring(a, a + 1), i = parseInt(o, 10), r += n && 10 <= (i *= 2) ? i + 1 : i, n = !n;
            return parseInt(e.substr(e.length - 1), 10) === (1e4 - r) % 10
        },
        isISBN: function e(t) {
            var r = 1 < arguments.length && void 0 !== arguments[1] ? arguments[1] : "";
            if (f(t), !(r = String(r))) return e(t, 10) || e(t, 13);
            var o = t.replace(/[\s-]+/g, ""),
                i = 0,
                n = void 0;
            if ("10" === r) {
                if (!te.test(o)) return !1;
                for (n = 0; n < 9; n++) i += (n + 1) * o.charAt(n);
                if ("X" === o.charAt(9) ? i += 100 : i += 10 * o.charAt(9), i % 11 == 0) return !!o
            } else if ("13" === r) {
                if (!re.test(o)) return !1;
                for (n = 0; n < 12; n++) i += oe[n % 2] * o.charAt(n);
                if (o.charAt(12) - (10 - i % 10) % 10 == 0) return !!o
            }
            return !1
        },
        isISSN: function (e) {
            var t = 1 < arguments.length && void 0 !== arguments[1] ? arguments[1] : {};
            f(e);
            var r = ie;
            if (r = t.require_hyphen ? r.replace("?", "") : r, !(r = t.case_sensitive ? new RegExp(r) : new RegExp(r, "i")).test(e)) return !1;
            var o = e.replace("-", ""),
                i = 8,
                n = 0,
                a = !0,
                l = !1,
                s = void 0;
            try {
                for (var u, d = o[Symbol.iterator](); !(a = (u = d.next()).done); a = !0) {
                    var c = u.value;
                    n += ("X" === c.toUpperCase() ? 10 : +c) * i, --i
                }
            } catch (e) {
                l = !0, s = e
            } finally {
                try {
                    !a && d.return && d.return()
                } finally {
                    if (l) throw s
                }
            }
            return n % 11 == 0
        },
        isMobilePhone: function (e, t, r) {
            if (f(e), r && r.strictMode && !e.startsWith("+")) return !1;
            if (t in ne) return ne[t].test(e);
            if ("any" === t) {
                for (var o in ne)
                    if (ne.hasOwnProperty(o) && ne[o].test(e)) return !0;
                return !1
            }
            throw new Error("Invalid locale '" + t + "'")
        },
        isPostalCode: function (e, t) {
            if (f(e), t in Te) return Te[t].test(e);
            if ("any" === t) {
                for (var r in Te)
                    if (Te.hasOwnProperty(r) && Te[r].test(e)) return !0;
                return !1
            }
            throw new Error("Invalid locale '" + t + "'")
        },
        isCurrency: function (e, t) {
            return f(e),
                function (e) {
                    var r = "\\d{" + e.digits_after_decimal[0] + "}";
                    e.digits_after_decimal.forEach(function (e, t) {
                        0 !== t && (r = r + "|\\d{" + e + "}")
                    });
                    var t = "(\\" + e.symbol.replace(/\./g, "\\.") + ")" + (e.require_symbol ? "" : "?"),
                        o = "(" + ["0", "[1-9]\\d*", "[1-9]\\d{0,2}(\\" + e.thousands_separator + "\\d{3})*"].join("|") + ")?",
                        i = "(\\" + e.decimal_separator + "(" + r + "))" + (e.require_decimal ? "" : "?"),
                        n = o + (e.allow_decimal || e.require_decimal ? i : "");
                    return e.allow_negatives && !e.parens_for_negatives && (e.negative_sign_after_digits ? n += "-?" : e.negative_sign_before_digits && (n = "-?" + n)), e.allow_negative_sign_placeholder ? n = "( (?!\\-))?" + n : e.allow_space_after_symbol ? n = " ?" + n : e.allow_space_after_digits && (n += "( (?!$))?"), e.symbol_after_digits ? n += t : n = t + n, e.allow_negatives && (e.parens_for_negatives ? n = "(\\(" + n + "\\)|" + n + ")" : e.negative_sign_before_digits || e.negative_sign_after_digits || (n = "-?" + n)), new RegExp("^(?!-? )(?=.*\\d)" + n + "$")
                }(t = c(t, ae)).test(e)
        },
        isISO8601: function (e) {
            return f(e), le.test(e)
        },
        isRFC3339: function (e) {
            return f(e), Ae.test(e)
        },
        isISO31661Alpha2: function (e) {
            return f(e), he.includes(e.toUpperCase())
        },
        isISO31661Alpha3: function (e) {
            return f(e), ve.includes(e.toUpperCase())
        },
        isBase64: function (e) {
            f(e);
            var t = e.length;
            if (!t || t % 4 != 0 || me.test(e)) return !1;
            var r = e.indexOf("=");
            return -1 === r || r === t - 1 || r === t - 2 && "=" === e[t - 1]
        },
        isDataURI: function (e) {
            f(e);
            var t = e.split(",");
            if (t.length < 2) return !1;
            var r = t.shift().trim().split(";"),
                o = r.shift();
            if ("data:" !== o.substr(0, 5)) return !1;
            var i = o.substr(5);
            if ("" !== i && !_e.test(i)) return !1;
            for (var n = 0; n < r.length; n++)
                if (n === r.length - 1 && "base64" === r[n].toLowerCase());
                else if (!Fe.test(r[n])) return !1;
            for (var a = 0; a < t.length; a++)
                if (!$e.test(t[a])) return !1;
            return !0
        },
        isMimeType: function (e) {
            return f(e), Se.test(e) || Re.test(e) || Ee.test(e)
        },
        isLatLong: function (e) {
            if (f(e), !e.includes(",")) return !1;
            var t = e.split(",");
            return xe.test(t[0]) && Me.test(t[1])
        },
        ltrim: Ze,
        rtrim: Be,
        trim: function (e, t) {
            return Be(Ze(e, t), t)
        },
        escape: function (e) {
            return f(e), e.replace(/&/g, "&amp;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\//g, "&#x2F;").replace(/\\/g, "&#x5C;").replace(/`/g, "&#96;")
        },
        unescape: function (e) {
            return f(e), e.replace(/&amp;/g, "&").replace(/&quot;/g, '"').replace(/&#x27;/g, "'").replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&#x2F;/g, "/").replace(/&#x5C;/g, "\\").replace(/&#96;/g, "`")
        },
        stripLow: function (e, t) {
            return f(e), Ie(e, t ? "\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F" : "\\x00-\\x1F\\x7F")
        },
        whitelist: function (e, t) {
            return f(e), e.replace(new RegExp("[^" + t + "]+", "g"), "")
        },
        blacklist: Ie,
        isWhitelisted: function (e, t) {
            f(e);
            for (var r = e.length - 1; 0 <= r; r--)
                if (-1 === t.indexOf(e[r])) return !1;
            return !0
        },
        normalizeEmail: function (e, t) {
            t = c(t, Le);
            var r = e.split("@"),
                o = r.pop(),
                i = [r.join("@"), o];
            if (i[1] = i[1].toLowerCase(), "gmail.com" === i[1] || "googlemail.com" === i[1]) {
                if (t.gmail_remove_subaddress && (i[0] = i[0].split("+")[0]), t.gmail_remove_dots && (i[0] = i[0].replace(/\.+/g, Ue)), !i[0].length) return !1;
                (t.all_lowercase || t.gmail_lowercase) && (i[0] = i[0].toLowerCase()), i[1] = t.gmail_convert_googlemaildotcom ? "gmail.com" : i[1]
            } else if (~ye.indexOf(i[1])) {
                if (t.icloud_remove_subaddress && (i[0] = i[0].split("+")[0]), !i[0].length) return !1;
                (t.all_lowercase || t.icloud_lowercase) && (i[0] = i[0].toLowerCase())
            } else if (~Ge.indexOf(i[1])) {
                if (t.outlookdotcom_remove_subaddress && (i[0] = i[0].split("+")[0]), !i[0].length) return !1;
                (t.all_lowercase || t.outlookdotcom_lowercase) && (i[0] = i[0].toLowerCase())
            } else if (~De.indexOf(i[1])) {
                if (t.yahoo_remove_subaddress) {
                    var n = i[0].split("-");
                    i[0] = 1 < n.length ? n.slice(0, -1).join("-") : n[0]
                }
                if (!i[0].length) return !1;
                (t.all_lowercase || t.yahoo_lowercase) && (i[0] = i[0].toLowerCase())
            } else ~Oe.indexOf(i[1]) ? ((t.all_lowercase || t.yandex_lowercase) && (i[0] = i[0].toLowerCase()), i[1] = "yandex.ru") : t.all_lowercase && (i[0] = i[0].toLowerCase());
            return i.join("@")
        },
        toString: n
    }
});