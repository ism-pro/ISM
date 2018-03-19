(function (a) {
    if (a.IChartsFaces) {
        a.IChartsFaces.debug("IChartsFaces already loaded, ignoring duplicate execution.");
        return;
    }

    var b = {
        escapeClientId: function (c) {
            return"#" + c.replace(/:/g, "\\:");
        },
        cleanWatermarks: function () {
            $.watermark.hideAll();
        },
        showWatermarks: function () {
            $.watermark.showAll();
        },
        getWidgetById: function (e) {
            for (var d in b.widgets) {
                var c = b.widgets[d];
                if (c && c.id === e) {
                    return c;
                }
            }
            return null;
        },
        addSubmitParam: function (d, f) {
            var e = $(this.escapeClientId(d));
            for (var c in f) {
                e.append('<input type="hidden" name="' + c + '" value="' + f[c] + '" class="ui-submit-param"/>');
            }
            return this;
        },
        submit: function (f, e) {
            var c = $(this.escapeClientId(f));
            var d;
            if (e) {
                d = c.attr("target");
                c.attr("target", e);
            }
            c.submit();
            c.children("input.ui-submit-param").remove();
            if (e) {
                if (d !== undefined) {
                    c.attr("target", d);
                } else {
                    c.removeAttr("target");
                }
            }
        },
        onPost: function () {
            this.nonAjaxPosted = true;
            this.abortXHRs();
        },
        abortXHRs: function () {
            b.ajax.Queue.abortAll();
        },
        attachBehaviors: function (d, c) {
            $.each(c, function (f, e) {
                d.bind(f, function (g) {
                    e.call(d, g);
                });
            });
        },
        getCookie: function (c) {
            return $.cookie(c);
        },
        setCookie: function (d, e, c) {
            $.cookie(d, e, c);
        },
        deleteCookie: function (d, c) {
            $.removeCookie(d, c);
        },
        cookiesEnabled: function () {
            var c = (navigator.cookieEnabled) ? true : false;
            if (typeof navigator.cookieEnabled === "undefined" && !c) {
                document.cookie = "testcookie";
                c = (document.cookie.indexOf("testcookie") !== -1) ? true : false;
            }
            return(c);
        },
        skinInput: function (c) {
            c.hover(function () {
                $(this).addClass("ui-state-hover");
            }, function () {
                $(this).removeClass("ui-state-hover");
            }).focus(function () {
                $(this).addClass("ui-state-focus");
            }).blur(function () {
                $(this).removeClass("ui-state-focus");
            });
            c.attr("role", "textbox").attr("aria-disabled", c.is(":disabled")).attr("aria-readonly", c.prop("readonly"));
            if (c.is("textarea")) {
                c.attr("aria-multiline", true);
            }
            return this;
        },
        skinButton: function (c) {
            c.mouseover(function () {
                var e = $(this);
                if (!c.prop("disabled")) {
                    e.addClass("ui-state-hover");
                }
            }).mouseout(function () {
                $(this).removeClass("ui-state-active ui-state-hover");
            }).mousedown(function () {
                var e = $(this);
                if (!c.prop("disabled")) {
                    e.addClass("ui-state-active").removeClass("ui-state-hover");
                }
            }).mouseup(function () {
                $(this).removeClass("ui-state-active").addClass("ui-state-hover");
            }).focus(function () {
                $(this).addClass("ui-state-focus");
            }).blur(function () {
                $(this).removeClass("ui-state-focus ui-state-active");
            }).keydown(function (f) {
                if (f.which === $.ui.keyCode.SPACE || f.which === $.ui.keyCode.ENTER || f.which === $.ui.keyCode.NUMPAD_ENTER) {
                    $(this).addClass("ui-state-active");
                }
            }).keyup(function () {
                $(this).removeClass("ui-state-active");
            });
            var d = c.attr("role");
            if (!d) {
                c.attr("role", "button");
            }
            c.attr("aria-disabled", c.prop("disabled"));
            return this;
        },
        skinSelect: function (c) {
            c.mouseover(function () {
                var d = $(this);
                if (!d.hasClass("ui-state-focus")) {
                    d.addClass("ui-state-hover");
                }
            }).mouseout(function () {
                $(this).removeClass("ui-state-hover");
            }).focus(function () {
                $(this).addClass("ui-state-focus").removeClass("ui-state-hover");
            }).blur(function () {
                $(this).removeClass("ui-state-focus ui-state-hover");
            });
            return this;
        },
        isIE: function (c) {
            return b.env.isIE(c);
        },
        info: function (c) {
            if (this.logger) {
                this.logger.info(c);
            }
        },
        debug: function (c) {
            if (this.logger) {
                this.logger.debug(c);
            }
        },
        warn: function (c) {
            if (this.logger) {
                this.logger.warn(c);
            }
            if (b.isDevelopmentProjectStage() && a.console) {
                console.log(c);
            }
        },
        error: function (c) {
            if (this.logger) {
                this.logger.error(c);
            }
            if (b.isDevelopmentProjectStage() && a.console) {
                console.log(c);
            }
        },
        isDevelopmentProjectStage: function () {
            return b.settings.projectStage === "Development";
        },
        setCaretToEnd: function (d) {
            if (d) {
                d.focus();
                var e = d.value.length;
                if (e > 0) {
                    if (d.setSelectionRange) {
                        d.setSelectionRange(0, e);
                    } else {
                        if (d.createTextRange) {
                            var c = d.createTextRange();
                            c.collapse(true);
                            c.moveEnd("character", 1);
                            c.moveStart("character", 1);
                            c.select();
                        }
                    }
                }
            }
        },
        changeTheme: function (g) {
            if (g && g !== "") {
                var h = $('link[href*="' + b.RESOURCE_IDENTIFIER + '/theme.css"]');
                if (h.length === 0) {
                    h = $('link[href*="' + b.RESOURCE_IDENTIFIER + '=theme.css"]');
                }
                var f = h.attr("href"), e = f.split("&")[0], d = e.split("ln=")[1], c = f.replace(d, "ichartsfaces-" + g);
                h.attr("href", c);
            }
        },
        escapeRegExp: function (c) {
            return this.escapeHTML(c.replace(/([.?*+^$[\]\\(){}|-])/g, "\\$1"));
        },
        escapeHTML: function (c) {
            return c.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
        },
        clearSelection: function () {
            if (a.getSelection) {
                if (a.getSelection().empty) {
                    a.getSelection().empty();
                } else {
                    if (a.getSelection().removeAllRanges && a.getSelection().rangeCount > 0 && a.getSelection().getRangeAt(0).getClientRects().length > 0) {
                        a.getSelection().removeAllRanges();
                    }
                }
            } else {
                if (document.selection && document.selection.empty) {
                    try {
                        document.selection.empty();
                    } catch (c) {
                    }
                }
            }
        },
        getSelection: function () {
            var c = "";
            if (a.getSelection) {
                c = a.getSelection();
            } else {
                if (document.getSelection) {
                    c = document.getSelection();
                } else {
                    if (document.selection) {
                        c = document.selection.createRange().text;
                    }
                }
            }
            return c;
        },
        hasSelection: function () {
            return this.getSelection().length > 0;
        },
        cw: function (d, e, c) {
            this.createWidget(d, e, c);
        },
        createWidget: function (d, f, c) {
            c.widgetVar = f;
            if (this.widget[d]) {
                var e = this.widgets[f];
                if (e && (e.constructor === this.widget[d])) {
                    e.refresh(c);
                } else {
                    this.widgets[f] = new this.widget[d](c);
                    if (this.settings.legacyWidgetNamespace) {
                        a[f] = this.widgets[f];
                    }
                }
            } else {
                b.error("Widget not available: " + d);
            }
        },
        getFacesResource: function (f, e, c) {
            if (f.indexOf("/") === 0) {
                f = f.substring(1, f.length);
            }
            var d = $('script[src*="/' + b.RESOURCE_IDENTIFIER + '/core.js"]').attr("src");
            if (!d) {
                d = $('script[src*="' + b.RESOURCE_IDENTIFIER + '=core.js"]').attr("src");
            }
            d = d.replace("core.js", f);
            d = d.replace("ln=ichartsfaces", "ln=" + e);
            if (c) {
                var h = new RegExp("[?&]v=([^&]*)").exec(d)[1];
                d = d.replace("v=" + h, "v=" + c);
            }
            var g = a.location.protocol + "//" + a.location.host;
            return d.indexOf(g) >= 0 ? d : g + d;
        },
        inArray: function (c, e) {
            for (var d = 0; d < c.length; d++) {
                if (c[d] === e) {
                    return true;
                }
            }
            return false;
        },
        isNumber: function (c) {
            return typeof c === "number" && isFinite(c);
        },
        getScript: function (c, d) {
            $.ajax({type: "GET", url: c, success: d, dataType: "script", cache: true, async: false});
        },
        focus: function (e, d) {
            var c = ":not(:submit):not(:button):input:visible:enabled[name]";
            setTimeout(function () {
                if (e) {
                    var i = $(b.escapeClientId(e));
                    if (i.is(c)) {
                        i.focus();
                    } else {
                        i.find(c).eq(0).focus();
                    }
                } else {
                    if (d) {
                        $(b.escapeClientId(d)).find(c).eq(0).focus();
                    } else {
                        var h = $(c), g = h.eq(0);
                        if (g.is(":radio")) {
                            var f = $(':radio[name="' + g.attr("name") + '"]').filter(":checked");
                            if (f.length) {
                                f.focus();
                            } else {
                                g.focus();
                            }
                        } else {
                            g.focus();
                        }
                    }
                }
            }, 50);
            b.customFocus = true;
        },
        monitorDownload: function (f, c, d) {
            if (this.cookiesEnabled()) {
                if (f) {
                    f();
                }
                var e = d ? "ichartsfaces.download_" + d : "ichartsfaces.download";
                a.downloadMonitor = setInterval(function () {
                    var g = b.getCookie(e);
                    if (g === "true") {
                        if (c) {
                            c();
                        }
                        clearInterval(a.downloadMonitor);
                        b.setCookie(e, null);
                    }
                }, 1000);
            }
        },
        scrollTo: function (d) {
            var c = $(b.escapeClientId(d)).offset();
            $("html,body").animate({scrollTop: c.top, scrollLeft: c.left}, {easing: "easeInCirc"}, 1000);
        },
        scrollInView: function (d, g) {
            if (g.length === 0) {
                return;
            }
            var j = parseFloat(d.css("borderTopWidth")) || 0, f = parseFloat(d.css("paddingTop")) || 0, h = g.offset().top - d.offset().top - j - f, c = d.scrollTop(), e = d.height(), i = g.outerHeight(true);
            if (h < 0) {
                d.scrollTop(c + h);
            } else {
                if ((h + i) > e) {
                    d.scrollTop(c + h - e + i);
                }
            }
        },
        calculateScrollbarWidth: function () {
            if (!this.scrollbarWidth) {
                if (b.env.browser.msie) {
                    var e = $('<textarea cols="10" rows="2"></textarea>').css({position: "absolute", top: -1000, left: -1000}).appendTo("body"), d = $('<textarea cols="10" rows="2" style="overflow: hidden;"></textarea>').css({position: "absolute", top: -1000, left: -1000}).appendTo("body");
                    this.scrollbarWidth = e.width() - d.width();
                    e.add(d).remove()
                } else {
                    var c = $("<div />").css({width: 100, height: 100, overflow: "auto", position: "absolute", top: -1000, left: -1000}).prependTo("body").append("<div />").find("div").css({width: "100%", height: 200});
                    this.scrollbarWidth = 100 - c.width();
                    c.parent().remove()
                }
            }
            return this.scrollbarWidth
        },
        bcn: function (d, e, g) {
            if (g) {
                for (var c = 0; c < g.length; c++) {
                    var f = g[c].call(d, e);
                    if (f === false) {
                        if (e.preventDefault) {
                            e.preventDefault()
                        } else {
                            e.returnValue = false
                        }
                        break
                    }
                }
            }
        },
        bcnu: function (e, f, d) {
            if (d) {
                for (var c = 0; c < d.length; c++) {
                    var g = d[c].call(this, e, f);
                    if (g === false) {
                        break
                    }
                }
            }
        },
        openDialog: function (c) {
            b.dialog.DialogHandler.openDialog(c)
        },
        closeDialog: function (c) {
            b.dialog.DialogHandler.closeDialog(c)
        },
        showMessageInDialog: function (c) {
            b.dialog.DialogHandler.showMessageInDialog(c)
        },
        confirm: function (c) {
            b.dialog.DialogHandler.confirm(c)
        },
        deferredRenders: [],
        addDeferredRender: function (e, c, d) {
            this.deferredRenders.push({widget: e, container: c, callback: d})
        },
        removeDeferredRenders: function (e) {
            for (var d = (this.deferredRenders.length - 1); d >= 0; d--) {
                var c = this.deferredRenders[d];
                if (c.widget === e) {
                    this.deferredRenders.splice(d, 1)
                }
            }
        },
        invokeDeferredRenders: function (c) {
            var g = [];
            for (var f = 0; f < this.deferredRenders.length; f++) {
                var d = this.deferredRenders[f];
                if (d.container === c) {
                    var h = d.callback.call();
                    if (h) {
                        g.push(d.widget)
                    }
                }
            }
            for (var e = 0; e < g.length; e++) {
                this.removeDeferredRenders(g[e])
            }
        },
        getLocaleSettings: function () {
            if (!this.localeSettings) {
                var c = b.settings.locale;
                this.localeSettings = b.locales[c];
                if (!this.localeSettings) {
                    this.localeSettings = b.locales[c.split("_")[0]];
                    if (!this.localeSettings) {
                        this.localeSettings = b.locales.en_US
                    }
                }
            }
            return this.localeSettings
        },
        getAriaLabel: function (d) {
            var c = this.getLocaleSettings()["aria"];
            return(c && c[d]) ? c[d] : b.locales.en_US["aria"][d]
        },
        zindex: 1000,
        customFocus: false,
        detachedWidgets: [],
        PARTIAL_REQUEST_PARAM: "javax.faces.partial.ajax",
        PARTIAL_UPDATE_PARAM: "javax.faces.partial.render",
        PARTIAL_PROCESS_PARAM: "javax.faces.partial.execute",
        PARTIAL_SOURCE_PARAM: "javax.faces.source",
        BEHAVIOR_EVENT_PARAM: "javax.faces.behavior.event",
        PARTIAL_EVENT_PARAM: "javax.faces.partial.event",
        RESET_VALUES_PARAM: "ichartsfaces.resetvalues",
        IGNORE_AUTO_UPDATE_PARAM: "ichartsfaces.ignoreautoupdate",
        SKIP_CHILDREN_PARAM: "ichartsfaces.skipchildren",
        VIEW_STATE: "javax.faces.ViewState",
        CLIENT_WINDOW: "javax.faces.ClientWindow",
        VIEW_ROOT: "javax.faces.ViewRoot",
        CLIENT_ID_DATA: "ichartsfaces.clientid",
        RESOURCE_IDENTIFIER: "javax.faces.resource",
        VERSION: "${project.version}"
    };
    b.settings = {};
    b.util = {};
    b.widgets = {};
    b.locales = {
        en_US: {
            closeText: "Close",
            prevText: "Previous",
            nextText: "Next",
            monthNames: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            monthNamesShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            dayNames: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
            dayNamesShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
            dayNamesMin: ["S", "M", "T", "W ", "T", "F ", "S"],
            weekHeader: "Week",
            firstDay: 0,
            isRTL: false,
            showMonthAfterYear: false,
            yearSuffix: "",
            timeOnlyTitle: "Only Time",
            timeText: "Time",
            hourText: "Hour",
            minuteText: "Minute",
            secondText: "Second",
            currentText: "Current Date",
            ampm: false,
            month: "Month",
            week: "Week",
            day: "Day",
            allDayText: "All Day",
            aria: {
                "paginator.PAGE": "Page {0}",
                "calendar.BUTTON": "Show Calendar",
                "datatable.sort.ASC": "activate to sort column ascending",
                "datatable.sort.DESC": "activate to sort column descending",
                "columntoggler.CLOSE": "Close"
            }
        },
        fr_FR: {
            closeText: "Fermer",
            prevText: "Précédent",
            nextText: "Suivant",
            monthNames: ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"],
            monthNamesShort: ["Jan", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil", "Aoû", "Sep", "Oct", "Nov", "Déc"],
            dayNames: ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"],
            dayNamesShort: ["Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"],
            dayNamesMin: ["D", "L", "Ma", "Me", "J", "V ", "S"],
            weekHeader: "Sem",
            firstDay: 1,
            isRTL: false,
            showMonthAfterYear: false,
            yearSuffix: "",
            timeOnlyTitle: "Temps Seul",
            timeText: "Temps",
            hourText: "Heure",
            minuteText: "Minute",
            secondText: "Seconde",
            currentText: "Date Courente",
            ampm: false,
            month: "Mois",
            week: "Semaine",
            day: "Jour",
            allDayText: "Journée Entière",
            aria: {
                "paginator.PAGE": "Page {0}",
                "calendar.BUTTON": "Calendrier",
                "datatable.sort.ASC": "activation du classement ascendant de la colonne",
                "datatable.sort.DESC": "activation du classement descendant de la colonne",
                "columntoggler.CLOSE": "Fermer"
            }
        }
    };

    b.locales.en = b.locales.fr_FR;
    PF = function (d) {
        var c = b.widgets[d];
        if (!c) {
            b.error("Widget for var '" + d + "' not available!");
        }
        return c;
    };
    a.IChartsFaces = b;
})(window);


if (!IChartsFaces.env) {
    IChartsFaces.env = {
        mobile: false, touch: false, ios: false, browser: null, init: function () {
            this.mobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(window.navigator.userAgent);
            this.touch = "ontouchstart" in window || window.navigator.msMaxTouchPoints || IChartsFaces.env.mobile;
            this.ios = /iPhone|iPad|iPod/i.test(window.navigator.userAgent);
            this.resolveUserAgent();
        },

        resolveUserAgent: function () {
            if ($.browser) {
                this.browser = $.browser
            } else {
                var a, d;
                jQuery.uaMatch = function (h) {
                    h = h.toLowerCase();
                    var g = /(opr)[\/]([\w.]+)/.exec(h) || /(chrome)[ \/]([\w.]+)/.exec(h) || /(version)[ \/]([\w.]+).*(safari)[ \/]([\w.]+)/.exec(h) || /(webkit)[ \/]([\w.]+)/.exec(h) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(h) || /(msie) ([\w.]+)/.exec(h) || h.indexOf("trident") >= 0 && /(rv)(?::| )([\w.]+)/.exec(h) || h.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(h) || [];
                    var f = /(ipad)/.exec(h) || /(iphone)/.exec(h) || /(android)/.exec(h) || /(windows phone)/.exec(h) || /(win)/.exec(h) || /(mac)/.exec(h) || /(linux)/.exec(h) || /(cros)/i.exec(h) || [];
                    return{browser: g[3] || g[1] || "", version: g[2] || "0", platform: f[0] || ""}
                };
                a = jQuery.uaMatch(window.navigator.userAgent);
                d = {};
                if (a.browser) {
                    d[a.browser] = true;
                    d.version = a.version;
                    d.versionNumber = parseInt(a.version)
                }
                if (a.platform) {
                    d[a.platform] = true
                }
                if (d.android || d.ipad || d.iphone || d["windows phone"]) {
                    d.mobile = true
                }
                if (d.cros || d.mac || d.linux || d.win) {
                    d.desktop = true
                }
                if (d.chrome || d.opr || d.safari) {
                    d.webkit = true
                }
                if (d.rv) {
                    var e = "msie";
                    a.browser = e;
                    d[e] = true
                }
                if (d.opr) {
                    var c = "opera";
                    a.browser = c;
                    d[c] = true
                }
                if (d.safari && d.android) {
                    var b = "android";
                    a.browser = b;
                    d[b] = true
                }
                d.name = a.browser;
                d.platform = a.platform;
                this.browser = d;
                $.browser = d
            }
        },
        isIE: function (a) {
            return(a === undefined) ? this.browser.msie : (this.browser.msie && parseInt(this.browser.version, 10) === a)
        },

        isLtIE: function (a) {
            return(this.browser.msie) ? parseInt(this.browser.version, 10) < a : false
        },
        isCanvasSupported: function () {
            var a = document.createElement("canvas");
            return !!(a.getContext && a.getContext("2d"))
        }};
    IChartsFaces.env.init();
}
;

if (!IChartsFaces.ajax) {
    IChartsFaces.AB_MAPPING = {s: "source", f: "formId", p: "process", u: "update", e: "event", a: "async", g: "global", d: "delay", t: "timeout", sc: "skipChildren", iau: "ignoreAutoUpdate", ps: "partialSubmit", psf: "partialSubmitFilter", rv: "resetValues", fi: "fragmentId", fu: "fragmentUpdate", pa: "params", onst: "onstart", oner: "onerror", onsu: "onsuccess", onco: "oncomplete"};
    IChartsFaces.ab = function (a, c) {
        for (var b in a) {
            if (!a.hasOwnProperty(b)) {
                continue
            }
            if (this.AB_MAPPING[b]) {
                a[this.AB_MAPPING[b]] = a[b];
                delete a[b];
            }
        }

        IChartsFaces.ajax.Request.handle(a, c);
    };

    IChartsFaces.ajax = {
        VIEW_HEAD: "javax.faces.ViewHead", VIEW_BODY: "javax.faces.ViewBody", RESOURCE: "javax.faces.Resource", Utils: {loadStylesheets: function (b) {
                for (var a = 0; a < b.length; a++) {
                    $("head").append('<link type="text/css" rel="stylesheet" href="' + b[a] + '" />');
                }
            },
            loadScripts: function (b) {
                var a = function () {
                    var c = b.shift();
                    if (c) {
                        IChartsFaces.getScript(c, a);
                    }
                };
                a();
            },
            getContent: function (c) {
                var b = "";
                for (var a = 0; a < c.childNodes.length; a++) {
                    b += c.childNodes[a].nodeValue;
                }
                return b
            },
            updateFormStateInput: function (b, g, j) {
                var e = $.trim(g);
                var a = null;
                if (j && j.pfSettings && j.pfSettings.portletForms) {
                    a = $(j.pfSettings.portletForms)
                } else {
                    a = $("form")
                }
                var h = "";
                if (j && j.pfArgs && j.pfArgs.parameterPrefix) {
                    h = j.pfArgs.parameterPrefix
                }
                for (var d = 0; d < a.length; d++) {
                    var c = a.eq(d);
                    if (c.attr("method") === "post") {
                        var f = c.children("input[name='" + h + b + "']");
                        if (f.length > 0) {
                            f.val(e)
                        } else {
                            c.append('<input type="hidden" name="' + h + b + '" value="' + e + '" autocomplete="off" />')
                        }
                    }
                }
            },
            updateHead: function (d) {
                var b = $.ajaxSetup()["cache"];
                $.ajaxSetup()["cache"] = true;
                var a = new RegExp("<head[^>]*>", "gi").exec(d)[0];
                var c = d.indexOf(a) + a.length;
                $("head").html(d.substring(c, d.lastIndexOf("</head>")));
                $.ajaxSetup()["cache"] = b
            },
            updateBody: function (b) {
                var c = new RegExp("<body[^>]*>", "gi").exec(b)[0];
                var a = b.indexOf(c) + c.length;
                $("body").html(b.substring(a, b.lastIndexOf("</body>")))
            },
            updateElement: function (d, b, c) {
                if (d.indexOf(IChartsFaces.VIEW_STATE) !== -1) {
                    IChartsFaces.ajax.Utils.updateFormStateInput(IChartsFaces.VIEW_STATE, b, c)
                } else {
                    if (d.indexOf(IChartsFaces.CLIENT_WINDOW) !== -1) {
                        IChartsFaces.ajax.Utils.updateFormStateInput(IChartsFaces.CLIENT_WINDOW, b, c)
                    } else {
                        if (d === IChartsFaces.VIEW_ROOT) {
                            var a = IChartsFaces.ajax.Utils;
                            window.IChartsFaces = null;
                            a.updateHead(b);
                            a.updateBody(b)
                        } else {
                            if (d === IChartsFaces.ajax.VIEW_HEAD) {
                                IChartsFaces.ajax.Utils.updateHead(b)
                            } else {
                                if (d === IChartsFaces.ajax.VIEW_BODY) {
                                    IChartsFaces.ajax.Utils.updateBody(b)
                                } else {
                                    if (d === IChartsFaces.ajax.RESOURCE) {
                                        $("head").append(b)
                                    } else {
                                        if (d === $("head")[0].id) {
                                            IChartsFaces.ajax.Utils.updateHead(b)
                                        } else {
                                            $(IChartsFaces.escapeClientId(d)).replaceWith(b)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        Queue: {
            delays: {},
            requests: new Array(),
            xhrs: new Array(),
            offer: function (a) {
                if (a.delay) {
                    var b = null;
                    var d = this;
                    var b = (typeof (a.source) === "string") ? a.source : $(a.source).attr("id");
                    var c = function () {
                        return setTimeout(function () {
                            d.requests.push(a);
                            if (d.requests.length === 1) {
                                IChartsFaces.ajax.Request.send(a)
                            }
                        }, a.delay)
                    };
                    if (this.delays[b]) {
                        clearTimeout(this.delays[b].timeout);
                        this.delays[b].timeout = c()
                    } else {
                        this.delays[b] = {timeout: c()}
                    }
                } else {
                    this.requests.push(a);
                    if (this.requests.length === 1) {
                        IChartsFaces.ajax.Request.send(a)
                    }
                }
            },
            poll: function () {
                if (this.isEmpty()) {
                    return null
                }
                var b = this.requests.shift(), a = this.peek();
                if (a) {
                    IChartsFaces.ajax.Request.send(a)
                }
                return b
            },
            peek: function () {
                if (this.isEmpty()) {
                    return null
                }
                return this.requests[0]
            },
            isEmpty: function () {
                return this.requests.length === 0
            },
            addXHR: function (a) {
                this.xhrs.push(a)
            },
            removeXHR: function (b) {
                var a = $.inArray(b, this.xhrs);
                if (a > -1) {
                    this.xhrs.splice(a, 1)
                }
            },
            abortAll: function () {
                for (var a = 0; a < this.xhrs.length; a++) {
                    this.xhrs[a].abort()
                }
                this.xhrs = new Array();
                this.requests = new Array()
            }
        },
        Request: {
            handle: function (a, b) {
                a.ext = b;
                if (IChartsFaces.settings.earlyPostParamEvaluation) {
                    a.earlyPostParams = IChartsFaces.ajax.Request.collectEarlyPostParams(a)
                }
                if (a.async) {
                    IChartsFaces.ajax.Request.send(a)
                } else {
                    IChartsFaces.ajax.Queue.offer(a)
                }
            },
            collectEarlyPostParams: function (a) {
                var b;
                var c;
                if (typeof (a.source) === "string") {
                    c = $(IChartsFaces.escapeClientId(a.source))
                } else {
                    c = $(a.source)
                }
                if (c.is(":input") && c.is(":not(:button)")) {
                    b = [];
                    b.push({name: c.attr("name"), value: c.is(":checkbox") ? c.is(":checked") : c.val()})
                } else {
                    b = c.find(":input").serializeArray();
                    b = b.concat(c.find("input[type=checkbox]:not(:checked)").map(function () {
                        var d = $(this);
                        return{name: d.attr("name"), value: d.is(":checked")}
                    }).get())
                }
                return b
            },
            send: function (e) {
                IChartsFaces.debug("Initiating ajax request.");
                IChartsFaces.customFocus = false;
                var m = (e.global === true || e.global === undefined) ? true : false, b = null, f = null, t = null;
                if (e.onstart) {
                    t = e.onstart.call(this, e)
                }
                if (e.ext && e.ext.onstart) {
                    t = e.ext.onstart.call(this, e)
                }
                if (t === false) {
                    IChartsFaces.debug("Ajax request cancelled by onstart callback.");
                    if (!e.async) {
                        IChartsFaces.ajax.Queue.poll()
                    }
                    return false
                }
                if (m) {
                    $(document).trigger("pfAjaxStart")
                }
                if (typeof (e.source) === "string") {
                    f = e.source
                } else {
                    f = $(e.source).attr("id")
                }
                if (e.formId) {
                    b = IChartsFaces.expressions.SearchExpressionFacade.resolveComponentsAsSelector(e.formId)
                } else {
                    var q = $(IChartsFaces.escapeClientId(f));
                    b = q.closest("form");
                    if (b.length === 0) {
                        b = $("form").eq(0)
                    }
                }
                IChartsFaces.debug("Form to post " + b.attr("id") + ".");
                var w = b.attr("action"), s = b.children("input[name*='javax.faces.encodedURL']"), g = [];
                var u = IChartsFaces.ajax.Request.extractParameterNamespace(b);
                var v = null;
                if (s.length > 0) {
                    v = 'form[id*="' + u + '"]';
                    w = s.val();
                }
                IChartsFaces.debug("URL to post " + w + ".");
                IChartsFaces.ajax.Request.addParam(g, IChartsFaces.PARTIAL_REQUEST_PARAM, true, u);
                IChartsFaces.ajax.Request.addParam(g, IChartsFaces.PARTIAL_SOURCE_PARAM, f, u);
                if (e.resetValues) {
                    IChartsFaces.ajax.Request.addParam(g, IChartsFaces.RESET_VALUES_PARAM, true, u)
                }
                if (e.ignoreAutoUpdate) {
                    IChartsFaces.ajax.Request.addParam(g, IChartsFaces.IGNORE_AUTO_UPDATE_PARAM, true, u)
                }
                if (e.skipChildren === false) {
                    IChartsFaces.ajax.Request.addParam(g, IChartsFaces.SKIP_CHILDREN_PARAM, false, u)
                }
                var r = IChartsFaces.ajax.Request.resolveComponentsForAjaxCall(e, "process");
                if (e.fragmentId) {
                    r.push(e.fragmentId)
                }
                var a = "@none";
                if (r.length > 0) {
                    a = r.join(" ")
                } else {
                    var j = IChartsFaces.ajax.Request.resolveComponentsForAjaxCall(e, "process");
                    j = $.trim(j);
                    if (j === "") {
                        a = "@all"
                    }
                }
                if (a !== "@none") {
                    IChartsFaces.ajax.Request.addParam(g, IChartsFaces.PARTIAL_PROCESS_PARAM, a, u)
                }
                var d = IChartsFaces.ajax.Request.resolveComponentsForAjaxCall(e, "update");
                if (e.fragmentId && e.fragmentUpdate) {
                    d.push(e.fragmentId)
                }
                if (d.length > 0) {
                    IChartsFaces.ajax.Request.addParam(g, IChartsFaces.PARTIAL_UPDATE_PARAM, d.join(" "), u)
                }
                if (e.event) {
                    IChartsFaces.ajax.Request.addParam(g, IChartsFaces.BEHAVIOR_EVENT_PARAM, e.event, u);
                    var l = e.event;
                    if (e.event === "valueChange") {
                        l = "change"
                    } else {
                        if (e.event === "action") {
                            l = "click"
                        }
                    }
                    IChartsFaces.ajax.Request.addParam(g, IChartsFaces.PARTIAL_EVENT_PARAM, l, u)
                } else {
                    IChartsFaces.ajax.Request.addParam(g, f, f, u)
                }
                if (e.params) {
                    IChartsFaces.ajax.Request.addParams(g, e.params, u)
                }
                if (e.ext && e.ext.params) {
                    IChartsFaces.ajax.Request.addParams(g, e.ext.params, u)
                }
                if (e.partialSubmit && a.indexOf("@all") === -1) {
                    var n = false, h = e.partialSubmitFilter || ":input";
                    if (a.indexOf("@none") === -1) {
                        for (var o = 0; o < r.length; o++) {
                            var k = $(IChartsFaces.escapeClientId(r[o]));
                            var x = null;
                            if (k.is("form")) {
                                x = k.serializeArray();
                                n = true
                            } else {
                                if (k.is(":input")) {
                                    x = k.serializeArray()
                                } else {
                                    x = k.find(h).serializeArray()
                                }
                            }
                            $.merge(g, x)
                        }
                    }
                    if (!n) {
                        IChartsFaces.ajax.Request.addParamFromInput(g, IChartsFaces.VIEW_STATE, b, u);
                        IChartsFaces.ajax.Request.addParamFromInput(g, IChartsFaces.CLIENT_WINDOW, b, u);
                        IChartsFaces.ajax.Request.addParamFromInput(g, "dsPostWindowId", b, u);
                        IChartsFaces.ajax.Request.addParamFromInput(g, "dspwid", b, u)
                    }
                } else {
                    $.merge(g, b.serializeArray())
                }
                if (IChartsFaces.settings.earlyPostParamEvaluation && e.earlyPostParams) {
                    $.each(e.earlyPostParams, function (i, y) {
                        g = $.grep(g, function (A, z) {
                            if (A.name === y.name) {
                                return false
                            }
                            return true
                        })
                    });
                    $.merge(g, e.earlyPostParams)
                }
                var c = $.param(g);
                IChartsFaces.debug("Post Data:" + c);
                var p = {url: w, type: "POST", cache: false, dataType: "xml", data: c, portletForms: v, source: e.source, global: false, beforeSend: function (y, i) {
                        y.setRequestHeader("Faces-Request", "partial/ajax");
                        y.pfSettings = i;
                        y.pfArgs = {};
                        IChartsFaces.nonAjaxPosted = false;
                        if (m) {
                            $(document).trigger("pfAjaxSend", [y, this])
                        }
                    },
                    error: function (z, i, y) {
                        if (e.onerror) {
                            e.onerror.call(this, z, i, y)
                        }
                        if (e.ext && e.ext.onerror) {
                            e.ext.onerror.call(this, z, i, y)
                        }
                        if (m) {
                            $(document).trigger("pfAjaxError", [z, this, y])
                        }
                        IChartsFaces.error("Request return with error:" + i + ".")
                    },
                    success: function (z, i, A) {
                        IChartsFaces.debug("Response received succesfully.");
                        var y;
                        if (e.onsuccess) {
                            y = e.onsuccess.call(this, z, i, A)
                        }
                        if (e.ext && e.ext.onsuccess && !y) {
                            y = e.ext.onsuccess.call(this, z, i, A)
                        }
                        if (m) {
                            $(document).trigger("pfAjaxSuccess", [A, this])
                        }
                        if (y) {
                            return
                        } else {
                            IChartsFaces.ajax.Response.handle(z, i, A)
                        }
                        IChartsFaces.debug("DOM is updated.")
                    },
                    complete: function (y, i) {
                        if (e.oncomplete) {
                            e.oncomplete.call(this, y, i, y.pfArgs)
                        }
                        if (e.ext && e.ext.oncomplete) {
                            e.ext.oncomplete.call(this, y, i, y.pfArgs)
                        }
                        if (m) {
                            $(document).trigger("pfAjaxComplete", [y, this])
                        }
                        IChartsFaces.debug("Response completed.");
                        IChartsFaces.ajax.Queue.removeXHR(y);
                        if (!e.async && !IChartsFaces.nonAjaxPosted) {
                            IChartsFaces.ajax.Queue.poll()
                        }
                    }
                };
                if (e.timeout) {
                    p.timeout = e.timeout;
                }
                IChartsFaces.ajax.Queue.addXHR($.ajax(p));
            },
            resolveExpressionsForAjaxCall: function (a, b) {
                var c = "";
                if (a[b]) {
                    c += a[b]
                }
                if (a.ext && a.ext[b]) {
                    c += " " + a.ext[b]
                }
                return c
            },
            resolveComponentsForAjaxCall: function (a, b) {
                var c = IChartsFaces.ajax.Request.resolveExpressionsForAjaxCall(a, b);
                return IChartsFaces.expressions.SearchExpressionFacade.resolveComponents(c)
            },
            addParam: function (c, a, b, d) {
                if (d || !a.indexOf(d) === 0) {
                    c.push({name: d + a, value: b})
                } else {
                    c.push({name: a, value: b})
                }
            },
            addParams: function (d, a, e) {
                for (var b = 0; b < a.length; b++) {
                    var c = a[b];
                    if (e && !c.name.indexOf(e) === 0) {
                        c.name = e + c.name
                    }
                    d.push(c)
                }
            },
            addParamFromInput: function (e, b, c, f) {
                var a = null;
                if (f) {
                    a = c.children("input[name*='" + b + "']")
                } else {
                    a = c.children("input[name='" + b + "']")
                }
                if (a && a.length > 0) {
                    var d = a.val();
                    IChartsFaces.ajax.Request.addParam(e, b, d, f)
                }
            },
            extractParameterNamespace: function (c) {
                var a = c.children("input[name*='" + IChartsFaces.VIEW_STATE + "']");
                if (a && a.length > 0) {
                    var b = a[0].name;
                    if (b.length > IChartsFaces.VIEW_STATE.length) {
                        return b.substring(0, b.indexOf(IChartsFaces.VIEW_STATE))
                    }
                }
                return null
            }
        },
        Response: {
            handle: function (h, e, m, b) {
                var n = h.getElementsByTagName("partial-response")[0];
                for (var g = 0; g < n.childNodes.length; g++) {
                    var a = n.childNodes[g];
                    switch (a.nodeName) {
                        case"redirect":
                            IChartsFaces.ajax.ResponseProcessor.doRedirect(a);
                            break;
                        case"changes":
                            var c = $(document.activeElement);
                            var k = c.attr("id");
                            var f;
                            if (c.length > 0 && c.is("input") && $.isFunction($.fn.getSelection)) {
                                f = c.getSelection()
                            }
                            for (var d = 0; d < a.childNodes.length; d++) {
                                var l = a.childNodes[d];
                                switch (l.nodeName) {
                                    case"update":
                                        IChartsFaces.ajax.ResponseProcessor.doUpdate(l, m, b);
                                        break;
                                    case"delete":
                                        IChartsFaces.ajax.ResponseProcessor.doDelete(l);
                                        break;
                                    case"insert":
                                        IChartsFaces.ajax.ResponseProcessor.doInsert(l);
                                        break;
                                    case"attributes":
                                        IChartsFaces.ajax.ResponseProcessor.doAttributes(l);
                                        break;
                                    case"eval":
                                        IChartsFaces.ajax.ResponseProcessor.doEval(l);
                                        break;
                                    case"extension":
                                        IChartsFaces.ajax.ResponseProcessor.doExtension(l, m);
                                        break
                                }
                            }
                            IChartsFaces.ajax.Response.handleReFocus(k, f);
                            IChartsFaces.ajax.Response.destroyDetachedWidgets();
                            break;
                        case"eval":
                            IChartsFaces.ajax.ResponseProcessor.doEval(a);
                            break;
                        case"extension":
                            IChartsFaces.ajax.ResponseProcessor.doExtension(a, m);
                            break;
                        case"error":
                            IChartsFaces.ajax.ResponseProcessor.doError(a, m);
                            break
                    }
                }
            },
            handleReFocus: function (d, b) {
                if (IChartsFaces.customFocus === false && d && d !== $(document.activeElement).attr("id")) {
                    var c = $(IChartsFaces.escapeClientId(d));
                    var a = function () {
                        c.focus();
                        if (b && b.start) {
                            c.setSelection(b.start, b.end)
                        }
                    };
                    if (c.length) {
                        a();
                        setTimeout(function () {
                            if (!c.is(":focus")) {
                                a()
                            }
                        }, 50)
                    }
                }
                IChartsFaces.customFocus = false
            },
            destroyDetachedWidgets: function () {
                for (var a = 0; a < IChartsFaces.detachedWidgets.length; a++) {
                    var d = IChartsFaces.detachedWidgets[a];
                    var b = PF(d);
                    if (b) {
                        if (b.isDetached()) {
                            IChartsFaces.widgets[d] = null;
                            b.destroy();
                            try {
                                delete b
                            } catch (c) {
                            }
                        }
                    }
                }
                IChartsFaces.detachedWidgets = []
            }
        },
        ResponseProcessor: {
            doRedirect: function (b) {
                try {
                    window.location.assign(b.getAttribute("url"))
                } catch (a) {
                    IChartsFaces.warn("Error redirecting to URL: " + b.getAttribute("url"))
                }
            },
            doUpdate: function (c, d, a) {
                var e = c.getAttribute("id"), b = IChartsFaces.ajax.Utils.getContent(c);
                if (a && a.widget && a.widget.id === e) {
                    a.handle.call(a.widget, b)
                } else {
                    IChartsFaces.ajax.Utils.updateElement(e, b, d)
                }
            },
            doEval: function (b) {
                var a = b.textContent || b.innerText || b.text;
                $.globalEval(a)
            },
            doExtension: function (d, e) {
                if (e) {
                    if (d.getAttribute("ln") === "ichartsfaces" && d.getAttribute("type") === "args") {
                        var c = d.textContent || d.innerText || d.text;
                        if (e.pfArgs) {
                            var b = $.parseJSON(c);
                            for (var a in b) {
                                e.pfArgs[a] = b[a]
                            }
                        } else {
                            e.pfArgs = $.parseJSON(c)
                        }
                    }
                }
            },
            doError: function (a, b) {},
            doDelete: function (a) {
                var b = a.getAttribute("id");
                $(IChartsFaces.escapeClientId(b)).remove()
            },
            doInsert: function (d) {
                if (!d.childNodes) {
                    return false
                }
                for (var b = 0; b < d.childNodes.length; b++) {
                    var a = d.childNodes[b];
                    var f = a.getAttribute("id");
                    var e = $(IChartsFaces.escapeClientId(f));
                    var c = IChartsFaces.ajax.Utils.getContent(a);
                    if (a.nodeName === "after") {
                        $(c).insertAfter(e)
                    } else {
                        if (a.nodeName === "before") {
                            $(c).insertBefore(e)
                        }
                    }
                }
            },
            doAttributes: function (c) {
                if (!c.childNodes) {
                    return false
                }
                var g = c.getAttribute("id");
                var f = $(IChartsFaces.escapeClientId(g));
                for (var b = 0; b < c.childNodes.length; b++) {
                    var d = c.childNodes[b];
                    var a = d.getAttribute("name");
                    var e = d.getAttribute("value");
                    if (!a) {
                        return
                    }
                    if (!e || e === null) {
                        e = ""
                    }
                    f.attr(a, e)
                }
            }
        },
        AjaxRequest: function (a, b) {
            return IChartsFaces.ajax.Request.handle(a, b);
        }
    };

    $(window).unload(function () {
        IChartsFaces.ajax.Queue.abortAll();
    });
}
;

if (!IChartsFaces.expressions) {
    IChartsFaces.expressions = {};
    IChartsFaces.expressions.SearchExpressionFacade = {
        resolveComponentsAsSelector: function (c) {
            var a = IChartsFaces.expressions.SearchExpressionFacade.splitExpressions(c);
            var e = $();
            if (a) {
                for (var b = 0; b < a.length; ++b) {
                    var g = $.trim(a[b]);
                    if (g.length > 0) {
                        if (g == "@none" || g == "@all") {
                            continue
                        }
                        if (g.indexOf("@") == -1) {
                            e = e.add($(document.getElementById(g)))
                        } else {
                            if (g.indexOf("@widgetVar(") == 0) {
                                var f = g.substring(11, g.length - 1);
                                var d = IChartsFaces.widgets[f];
                                if (d) {
                                    e = e.add($(document.getElementById(d.id)))
                                } else {
                                    IChartsFaces.error('Widget for widgetVar "' + f + '" not avaiable')
                                }
                            } else {
                                if (g.indexOf("@(") == 0) {
                                    e = e.add($(g.substring(2, g.length - 1)))
                                }
                            }
                        }
                    }
                }
            }
            return e
        },
        resolveComponents: function (l) {
            var k = IChartsFaces.expressions.SearchExpressionFacade.splitExpressions(l), c = [];
            if (k) {
                for (var g = 0; g < k.length; ++g) {
                    var m = $.trim(k[g]);
                    if (m.length > 0) {
                        if (m.indexOf("@") == -1 || m == "@none" || m == "@all") {
                            if (!IChartsFaces.inArray(c, m)) {
                                c.push(m)
                            }
                        } else {
                            if (m.indexOf("@widgetVar(") == 0) {
                                var d = m.substring(11, m.length - 1), h = IChartsFaces.widgets[d];
                                if (h) {
                                    if (!IChartsFaces.inArray(c, h.id)) {
                                        c.push(h.id)
                                    }
                                } else {
                                    IChartsFaces.error('Widget for widgetVar "' + d + '" not avaiable')
                                }
                            } else {
                                if (m.indexOf("@(") == 0) {
                                    var b = $(m.substring(2, m.length - 1));
                                    for (var e = 0; e < b.length; e++) {
                                        var f = $(b[e]), a = f.data(IChartsFaces.CLIENT_ID_DATA) || f.attr("id");
                                        if (!IChartsFaces.inArray(c, a)) {
                                            c.push(a)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return c
        },
        splitExpressions: function (f) {
            if (IChartsFaces.isIE(7)) {
                f = f.split("")
            }
            var e = [];
            var b = "";
            var a = 0;
            for (var d = 0; d < f.length; d++) {
                var g = f[d];
                if (g === "(") {
                    a++
                }
                if (g === ")") {
                    a--
                }
                if ((g === " " || g === ",") && a === 0) {
                    e.push(b);
                    b = ""
                } else {
                    b += g
                }
            }
            e.push(b);
            return e
        }
    }
}
;

(function () {
    var a = false, b = /xyz/.test(function () {
        xyz
    }) ? /\b_super\b/ : /.*/;
    this.Class = function () {};
    Class.extend = function (g) {
        var f = this.prototype;
        a = true;
        var e = new this();
        a = false;
        for (var d in g) {
            e[d] = typeof g[d] == "function" && typeof f[d] == "function" && b.test(g[d]) ? (function (h, i) {
                return function () {
                    var k = this._super;
                    this._super = f[h];
                    var j = i.apply(this, arguments);
                    this._super = k;
                    return j
                }
            })(d, g[d]) : g[d]
        }
        function c() {
            if (!a && this.init) {
                this.init.apply(this, arguments)
            }
        }
        c.prototype = e;
        c.prototype.constructor = c;
        c.extend = arguments.callee;
        return c
    }
})();

if (!IChartsFaces.widget) {
    IChartsFaces.widget = {};
    IChartsFaces.widget.BaseWidget = Class.extend({
        init: function (a) {
            this.cfg = a;
            this.id = a.id;
            this.jqId = IChartsFaces.escapeClientId(this.id);
            this.jq = $(this.jqId);
            this.widgetVar = a.widgetVar;
            $(this.jqId + "_s").remove();
            if (this.widgetVar) {
                var b = this;
                this.jq.on("remove", function () {
                    if (b.isDetached()) {
                        IChartsFaces.detachedWidgets.push(b.widgetVar)
                    }
                })
            }
        },
        refresh: function (a) {
            return this.init(a)
        },
        destroy: function () {
            IChartsFaces.debug("Destroyed detached widget: " + this.widgetVar)
        },
        isDetached: function () {
            return document.getElementById(this.id) === null
        },
        getJQ: function () {
            return this.jq
        },
        removeScriptElement: function (a) {
            $(IChartsFaces.escapeClientId(a) + "_s").remove()
        }
    });
    IChartsFaces.widget.DeferredWidget = IChartsFaces.widget.BaseWidget.extend({
        renderDeferred: function () {
            if (this.jq.is(":visible")) {
                this._render();
                this.postRender()
            } else {
                var a = this.jq.closest(".ui-hidden-container"), b = this;
                if (a.length) {
                    this.addDeferredRender(this.id, a, function () {
                        return b.render()
                    })
                }
            }
        },
        render: function () {
            if (this.jq.is(":visible")) {
                this._render();
                this.postRender();
                return true
            } else {
                return false
            }
        },
        _render: function () {
            throw"Unsupported Operation"
        },
        postRender: function () {},
        destroy: function () {
            this._super();
            IChartsFaces.removeDeferredRenders(this.id)
        },
        addDeferredRender: function (b, a, d) {
            IChartsFaces.addDeferredRender(b, a.attr("id"), d);
            if (a.is(":hidden")) {
                var c = this.jq.closest(".ui-hidden-container");
                if (c.length) {
                    this.addDeferredRender(b, a.parent().closest(".ui-hidden-container"), d)
                }
            }
        }
    });
}
;

IChartsFaces.widget.AjaxStatus = IChartsFaces.widget.BaseWidget.extend({
    init: function (a) {
        this._super(a);
        this.bind();
    },
    bind: function () {
        var b = $(document), a = this;
        b.on("pfAjaxStart", function () {
            a.trigger("start", arguments);
        }).on("pfAjaxError", function () {
            a.trigger("error", arguments);
        }).on("pfAjaxSuccess", function () {
            a.trigger("success", arguments);
        }).on("pfAjaxComplete", function () {
            a.trigger("complete", arguments);
        });
        this.bindToStandard();
    },
    trigger: function (b, a) {
        var c = this.cfg[b];
        if (c) {
            c.apply(document, a);
        }
        this.jq.children().hide().filter(this.jqId + "_" + b).show();
    },
    bindToStandard: function () {
        if (window.jsf && window.jsf.ajax) {
            var a = $(document);
            jsf.ajax.addOnEvent(function (b) {
                if (b.status === "begin") {
                    a.trigger("pfAjaxStart", arguments);
                } else {
                    if (b.status === "complete") {
                        a.trigger("pfAjaxSuccess", arguments);
                    } else {
                        if (b.status === "success") {
                            a.trigger("pfAjaxComplete", arguments);
                        }
                    }
                }
            });
            jsf.ajax.addOnError(function (b) {
                a.trigger("pfAjaxError", arguments);
            });
        }
    }
});

IChartsFaces.widget.Poll = IChartsFaces.widget.BaseWidget.extend({
    init: function (a) {
        this.cfg = a;
        this.id = this.cfg.id;
        this.active = false;
        if (this.cfg.autoStart) {
            this.start();
        }
    },
    refresh: function (a) {
        if (this.isActive()) {
            this.stop();
        }
        this.init(a);
    },
    start: function () {
        this.timer = setInterval(this.cfg.fn, (this.cfg.frequency * 1000));
        this.active = true;
    },
    stop: function () {
        clearInterval(this.timer);
        this.active = false;
    },
    isActive: function () {
        return this.active;
    }
});
