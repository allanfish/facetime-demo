define(function (require) {

    window.util = window.util || {};

    util.isNet = function () {
        return "app.oatos.net" == (window.location.hostname + "");
    };

    /**
     * Get cookie by name, if name not exist, return null
     */
    util.getCookie = function (name) {
        var arr = document.cookie
            .match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null)
            return unescape(arr[2]);
        return null;
    };
    /**
     * Get locale
     */
    util.getLocale = function () {
        var locale = util.getCookie('lang');
        locale = locale || util.getUrlEncodedKey('locale');
        locale = locale ? locale : (navigator.language || navigator.browserLanguage
            || navigator.systemLanguage || navigator.userLanguage).replace(
                /-/g, '_');
        var index = locale.indexOf('_');
        if (index != -1) {
            var lang, region;
            lang = locale.substring(0, index);
            region = locale.substring(index, locale.length).toUpperCase();
            locale = lang + region;
        }
        return locale;
    };
    /**
     * Example:<br>
     * <code>
     * loadExternalFiles({
 * 		prefix:"<script type='text\/javascript' src='",
 * 		suffix:"><\/script>", 
 * 		fileUrls:["js/util.js","js/md5.js"], 
 * 		version: "20120101" 
 * });
     * </code>
     *
     * @param settings
     */
    util.loadExternalFiles = function (settings) {
        var fileUrlStrs = [];
        var i;
        for (i in settings.fileUrls) {
            fileUrlStrs.push(settings.prefix + settings.fileUrls[i] + "?t="
                + settings.version + settings.suffix);
        }
        document.write(fileUrlStrs.join(""));
    };

    util.escapeRegExp = function (str) {
        if (str)
            return str.replace(/[.*+?^${}()|[\]\/\\]/g, "\\$0");
        return null;
    };
    util.trimEnd = function (str, c) {
        if (str && c)
            return str.replace(new RegExp(util.escapeRegExp(c) + "*$"), '');
        return str.replace(/\s+$/, '');
    };
    util.trimStart = function (str, c) {
        if (str && c)
            return str.replace(new RegExp("^" + util.escapeRegExp(c) + "*"), '');
        return str.replace(/^\s+/, '');
    };
    /**
     * set
     */
    util.setUrlEncodedKey = function (key, value, query) {
        query = query || window.location.search;
        var q = query + "&";
        var re = new RegExp("[?|&]" + key + "=.*?&");
        if (!re.test(q))
            q += key + "=" + encodeURI(value);
        else
            q = q.replace(re, "&" + key + "=" + encodeURIComponent(value) + "&");
        q = util.trimEnd(util.trimStart(q, "&"), "&");
        return q.charAt(0) == "?" ? q : q = "?" + q;
    };

    util.getUrlEncodedKey = function (key, query) {
        if (!query)
            query = window.location.search;
        var re = new RegExp("[?|&]" + key + "=(.*?)&");
        var matches = re.exec(query + "&");
        if (!matches || matches.length < 2)
            return null;
        return decodeURIComponent(matches[1].replace("+", " "));
    };
    util.isSupportBrowser = function () {
        var ua = navigator.userAgent.toLowerCase();
        if (function () {
            return ua.indexOf('msie 9') != -1;
        }())
            return true;
        if (function () {
            return ua.indexOf('msie 8') != -1;
        }())
            return true;
        if (function () {
            return ua.indexOf('msie 6') != -1 || ua.indexOf('msie 7') != -1;
        }()) {
            return false;
        }
        return true;
    };

    util.isCompatiableMode = function () {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.indexOf('msie')) {
            var iereg = /msie\s*(\d)/i;
            ua.match(iereg);
            var ieversion = RegExp.$1;
            var mode = document.documentMode;
            if (ieversion != mode && (ieversion >= 8 || mode >= 8) && !(ieversion >= 8 && mode >= 8)) {
                return true;
            }
            // ie9 兼容视图
            if (ieversion == 7 && ua.indexOf('trident/5.0') != -1) {
                return true;
            }
        }
        return false;
    };

    util.isIE = function () {
        var ua = navigator.userAgent.toLowerCase();
        return ua.indexOf('msie') != -1;
    };

    util.isIE9 = function () {
        var ua = navigator.userAgent.toLowerCase();
        var iereg = /msie\s*(\d)/i;
        ua.match(iereg);
        var ieversion = RegExp.$1;
        return ua.indexOf('msie') != -1 && ieversion == 9;
    };


    util.isMobile = function () {
        return util.isIPhone() || util.isAndroidPhone() || util.isIPad()
            || util.isAndroidPad();
    };
    util.isIPad = function () {
        var platForm = navigator.platform.toLowerCase();
        if (platForm.indexOf("ipad") != -1) {
            return true;
        }
        return false;
    };
    util.isIPhone = function () {
        var platForm = navigator.platform.toLowerCase();
        if (platForm.indexOf("iphone") != -1 || platForm.indexOf("ipod") != -1) {
            return true;
        }
        return false;
    };
    util.isAndroidPhone = function () {
        var platForm = navigator.platform.toLowerCase();
        if (platForm.indexOf("android") != -1
            || platForm.indexOf("linux armv7l") != -1) {
            return true;
        }
        return false;
    };
    util.isAndroidPad = function () {
        var platForm = navigator.platform.toLowerCase();
        if (platForm.indexOf("android") != -1
            || platForm.indexOf("linux armv7l") != -1) {
            return true;
        }
        return false;
    };

    util.S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    };

    util.guid = function () {
        var S4 = util.S4;
        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4()
            + S4() + S4());
    };

    util.getAgent = function () {
        var agent = "pc";
        if (util.isIPad() || util.isIPhone()) {
            agent = "pad";
        }
        return agent;
    };


    util.convertSize = function (size) {
        var unit = 'K';
        if (size > 1024) {
            size = size / 1024;
            unit = 'M';
        }
        if (size > 1024) {
            size = size / 1024;
            unit = 'G';
        }
        if (size > 1024) {
            size = size / 1024;
            unit = 'T';
        }
        return size + unit;
    };
});
