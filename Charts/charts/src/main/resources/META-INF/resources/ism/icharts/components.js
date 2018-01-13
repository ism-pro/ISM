IChartsFaces.widget.OverlayFiltersPanel = IChartsFaces.widget.BaseWidget.extend({
    init: function (a) {
        this._super(a);

        this.content = this.jq.children("div.ui-overlaypanel-content");
        this.cfg.my = this.cfg.my || "left top";
        this.cfg.at = this.cfg.at || "left bottom";
        this.cfg.showEvent = this.cfg.showEvent || "click.ui-overlaypanel";
        this.cfg.hideEvent = this.cfg.hideEvent || "click.ui-overlaypanel";
        this.cfg.dismissable = (this.cfg.dismissable === false) ? false : true;
        if (this.cfg.showCloseIcon) {
            this.closerIcon = $('<a href="#" class="ui-overlaypanel-close ui-state-default" href="#"><span class="ui-icon ui-icon-closethick"></span></a>').appendTo(this.jq);
        }
        if (this.jq.length > 1) {
            $(document.body).children(this.jqId).remove();
            this.jq = $(this.jqId);
        }
        if (this.cfg.appendToBody) {
            this.jq.appendTo(document.body);
        }
        this.bindCommonEvents();
        if (this.cfg.target) {
            this.target = IChartsFaces.expressions.SearchExpressionFacade.resolveComponentsAsSelector(this.cfg.target);
            this.bindTargetEvents();
            this.setupDialogSupport();
        }
    },
    bindTargetEvents: function () {
        var d = this;
        this.target.data("ichartsfaces-overlay-target", this.id).find("*").data("ichartsfaces-overlay-target", this.id);
        if (this.cfg.showEvent === this.cfg.hideEvent) {
            var b = this.cfg.showEvent;
            this.target.on(b, function (f) {
                d.toggle();
            });
        } else {
            var a = this.cfg.showEvent + ".ui-overlaypanel", c = this.cfg.hideEvent + ".ui-overlaypanel";
            this.target.off(a + " " + c).on(a, function (f) {
                if (!d.isVisible()) {
                    d.show();
                    if (a === "contextmenu.ui-overlaypanel") {
                        f.preventDefault();
                    }
                }
            }).on(c, function (f) {
                if (d.isVisible()) {
                    d.hide();
                }
            });
        }
        d.target.off("keydown.ui-overlaypanel keyup.ui-overlaypanel").on("keydown.ui-overlaypanel", function (h) {
            var g = $.ui.keyCode, f = h.which;
            if (f === g.ENTER || f === g.NUMPAD_ENTER) {
                h.preventDefault();
            }
        })
                .on("keyup.ui-overlaypanel", function (h) {
                    var g = $.ui.keyCode, f = h.which;
                    if (f === g.ENTER || f === g.NUMPAD_ENTER) {
                        d.toggle();
                        h.preventDefault();
                    }
                });
    },
    bindCommonEvents: function () {
        var c = this;
        if (this.cfg.showCloseIcon) {
            this.closerIcon.on("mouseover.ui-overlaypanel", function () {
                $(this).addClass("ui-state-hover");
            }).on("mouseout.ui-overlaypanel", function () {
                $(this).removeClass("ui-state-hover");
            }).on("click.ui-overlaypanel", function (d) {
                c.hide();
                d.preventDefault();
            });
        }
        if (this.cfg.dismissable) {
            var b = "mousedown." + this.id;
            $(document.body).off(b).on(b, function (f) {
                if (c.jq.hasClass("ui-overlay-hidden")) {
                    return;
                }
                if (c.target) {
                    var d = $(f.target);
                    if (c.target.is(d) || c.target.has(d).length > 0) {
                        return;
                    }
                }
                var g = c.jq.offset();
                if (f.pageX < g.left || f.pageX > g.left + c.jq.outerWidth() || f.pageY < g.top || f.pageY > g.top + c.jq.outerHeight()) {
                    c.hide();
                }
            });
        }
        var a = "resize." + this.id;
        $(window).off(a).on(a, function () {
            if (c.jq.hasClass("ui-overlay-visible")) {
                c.align();
            }
        });
    },
    toggle: function () {
        if (!this.isVisible()) {
            this.show();
        } else {
            this.hide();
        }
    },
    show: function (a) {
        if (!this.loaded && this.cfg.dynamic) {
            this.loadContents(a);
        } else {
            this._show(a);
        }
    },
    _show: function (b) {
        var a = this;
        this.align(b);
        this.jq.removeClass("ui-overlay-hidden").addClass("ui-overlay-visible").css({display: "none", visibility: "visible"});
        if (this.cfg.showEffect) {
            this.jq.show(this.cfg.showEffect, {}, 200, function () {
                a.postShow();
            });
        } else {
            this.jq.show();
            this.postShow();
        }
    },
    align: function (e) {
        var c = this.jq.css("position") === "fixed", d = $(window), a = c ? "-" + d.scrollLeft() + " -" + d.scrollTop() : null, b = e || this.cfg.target;
        this.jq.css({
            left: "",
            top: "",
            "z-index": ++IChartsFaces.zindex
        }).position({
            my: this.cfg.my,
            at: this.cfg.at,
            of: document.getElementById(b),
            offset: a
        });
    },
    hide: function () {
        var a = this;
        if (this.cfg.hideEffect) {
            this.jq.hide(this.cfg.hideEffect, {}, 200, function () {
                a.postHide();
            });
        } else {
            this.jq.hide();
            this.postHide();
        }
    },
    postShow: function () {
        if (this.cfg.onShow) {
            this.cfg.onShow.call(this);
        }
        this.applyFocus();
    },
    postHide: function () {
        this.jq.removeClass("ui-overlay-visible").addClass("ui-overlay-hidden").css({display: "block", visibility: "hidden"});
        if (this.cfg.onHide) {
            this.cfg.onHide.call(this);
        }
    },
    setupDialogSupport: function () {
        var a = this.target.closest(".ui-dialog");
        if (a.length === 1) {
            this.jq.css("position", "fixed");
            if (!this.cfg.appendToBody) {
                this.jq.appendTo(document.body);
            }
        }
    },
    loadContents: function (c) {
        var b = this, a = {source: this.id, process: this.id, update: this.id, params: [{name: this.id + "_contentLoad", value: true}], onsuccess: function (f, d, e) {
                IChartsFaces.ajax.Response.handle(f, d, e, {widget: b, handle: function (g) {
                        this.content.html(g);
                        this.loaded = true;
                    }});
                return true;
            }, oncomplete: function () {
                b._show(c);
            }};
        IChartsFaces.ajax.Request.handle(a);
    },
    isVisible: function () {
        return this.jq.hasClass("ui-overlay-visible");
    },
    applyFocus: function () {
        this.jq.find(":not(:submit):not(:button):input:visible:enabled:first").focus();
    }
});
IChartsFaces.widget.Serie = IChartsFaces.widget.BaseWidget.extend({
    init: function (a) {
        //this._super(a);
        this.serie = a;
    },
    toString: function () {
        //console.log(simpleStringify(this.serie));
        /*
         var obj = "{"
         + "animate:" + this.serie.animate + ","
         + "animationTimeout:" + this.serie.animationTimeout + ","
         + "barW:" + this.serie.barW + ","
         + "borderWidth:" + this.serie.borderWidth + ","
         //Skip chart
         "checkboxOffset:" + this.serie.checkboxOffset + ","
         + "closestPointRange:" + this.serie.closestPointRange + ","
         + "closestPointRangePx:" + this.serie.closestPointRangePx + ","
         + "color:" + this.serie.color + ","
         + "colorCounter:" + this.serie.colorCounter + ","
         + "colorIndex:" + this.serie.colorIndex + ","
         + "columnIndex:" + this.serie.columnIndex + ","
         //+ "columnMetrics:{offset:" + this.serie.columnMetrics.offset + ",width:" + this.serie.columnMetrics.width + "},"
         + "cropStart:" + this.serie.cropStart + ","
         + "cropped:" + this.serie.cropped + ","
         + "data:[";
         var i = 0;
         for (; this.serie.data[i]; ) {
         if (i > 0) {
         obj += ",";
         }
         obj += simpleStringify(this.serie.data[i]);
         i++;
         }
         obj += "],"
         + "dataLabelsGroup:{" + simpleStringify(this.serie.dataLabelsGroup) + "},"
         + "dataMax:" + this.serie.dataMax + ","
         + "dataMin:" + this.serie.dataMin + ","
         + "dense:" + this.serie.dense + ","
         // Skip group
         + "hasRendered:" + this.serie.hasRendered + ","
         + "dataMin:" + this.serie.colorIndex + ","
         + "dense:" + this.serie.columnIndex + ","
         // skip hcEvents,
         + "index:" + this.serie.index + ","
         // skip invertGroups
         + "isDirty:" + this.serie.isDirty + ","
         + "isDirtyData:" + this.serie.isDirtyData + ","
         // Skip legendGroup
         + "legendItem:{" + simpleStringify(this.serie.legendItem) + "},"
         + "legendSymbol:{" + simpleStringify(this.serie.legendSymbol) + "},"
         // Skip linkedSerie
         + "markerGroup:{" + simpleStringify(this.serie.markerGroup) + "},"
         + "name:" + this.serie.name + ","
         + "options:{" + simpleStringify(this.serie.options) + "},"
         + "pointInterval:" + this.serie.pointInterval + ","
         + "pointXOffset:" + this.serie.pointXOffset + ","
         // Skip poiints
         + "processedXData:{" + simpleStringify(this.serie.processedXData) + "},"
         + "processedYData:{" + simpleStringify(this.serie.processedYData) + "},"
         + "selected:" + this.serie.selected + ","
         + "selectedSerieId:" + this.serie.selectedSerieId + ","
         + "sharedClipKey:" + this.serie.sharedClipKey + ","
         + "state:" + this.serie.state + ","
         + "tooltipOptions:{" + simpleStringify(this.serie.tooltipOptions) + "},"
         + "translatedThreshold:" + this.serie.translatedThreshold + ","
         + "userOptions:{" + simpleStringify(this.serie.userOptions) + "},"
         + "visible:" + this.serie.visible + ","
         // Skip xAxis
         + "xData:{" + simpleStringify(this.serie.xData) + "},"
         + "xIncrement:" + this.serie.xIncrement + ","
         // Skip yAxis
         + "yData:{" + simpleStringify(this.serie.yData) + "},"
         // Skip zoneAxis
         // Skip zones
         + "_hasTracking:" + this.serie._hasTracking + ","
         + "_i:" + this.serie._i + ","
         + "_legendItemPos:{" + simpleStringify(this.serie._legendItemPos) + "},"
         + "__proto__:{" + simpleStringify(this.serie.__proto__) + "}"
         + "}";*/
        //console.log(this.serie.options);
        //var obj = simpleStringify(this.serie.options);
        var obj = JSON.stringify(this.serie.options);
        //console.log(obj);
        return obj;
    }
});
