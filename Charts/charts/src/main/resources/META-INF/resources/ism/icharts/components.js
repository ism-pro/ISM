//
//IChartsFaces.widget.Panel = IChartsFaces.widget.BaseWidget.extend({
//    init: function (a) {
//        this._super(a);
//        this.panel = $(this.jqId + "-parent");
//        this.container = $(this.jqId);
//        console.log("Chart was call on init !");
//        this.bindEvents()
//    }, bindEvents: function () {
//        if (this.cfg.toggleable) {
//            this.bindToggler()
//        }
//        if (this.cfg.closable) {
//            this.bindCloser()
//        }
//        if (this.cfg.hasMenu) {
//            $(this.jqId + "_menu").on("click.panel", function (a) {
//                a.preventDefault()
//            })
//        }
//        this.header.find(".ui-panel-titlebar-icon").on("mouseover.panel", function () {
//            $(this).addClass("ui-state-hover")
//        }).on("mouseout.panel", function () {
//            $(this).removeClass("ui-state-hover")
//        }).on("click.panel", function (b) {
//            var a = $(this).attr("href");
//            if (!a || a == "#") {
//                b.preventDefault()
//            }
//        })
//    }, toggle: function () {
//        if (this.cfg.collapsed) {
//            this.expand();
//            IChartsFaces.invokeDeferredRenders(this.id)
//        } else {
//            this.collapse()
//        }
//    }, expand: function () {
//        this.toggleState(false, "ui-icon-plusthick", "ui-icon-minusthick");
//        if (this.cfg.toggleOrientation === "vertical") {
//            this.slideDown()
//        } else {
//            if (this.cfg.toggleOrientation === "horizontal") {
//                this.slideRight()
//            }
//        }
//    }, collapse: function () {
//        this.toggleState(true, "ui-icon-minusthick", "ui-icon-plusthick");
//        if (this.cfg.toggleOrientation === "vertical") {
//            this.slideUp()
//        } else {
//            if (this.cfg.toggleOrientation === "horizontal") {
//                this.slideLeft()
//            }
//        }
//    }, slideUp: function () {
//        this.content.slideUp(this.cfg.toggleSpeed, "easeInOutCirc")
//    }, slideDown: function () {
//        this.content.slideDown(this.cfg.toggleSpeed, "easeInOutCirc")
//    }, slideLeft: function () {
//        var a = this;
//        this.originalWidth = this.jq.width();
//        this.title.hide();
//        this.toggler.hide();
//        this.content.hide();
//        this.jq.animate({width: "42px"}, this.cfg.toggleSpeed, "easeInOutCirc", function () {
//            a.toggler.show();
//            a.jq.addClass("ui-panel-collapsed-h")
//        })
//    }, slideRight: function () {
//        var a = this, b = this.originalWidth || "100%";
//        this.toggler.hide();
//        this.jq.animate({width: b}, this.cfg.toggleSpeed, "easeInOutCirc", function () {
//            a.jq.removeClass("ui-panel-collapsed-h");
//            a.title.show();
//            a.toggler.show();
//            a.content.css({visibility: "visible", display: "block", height: "auto"})
//        })
//    }, toggleState: function (c, b, a) {
//        this.toggler.children("span.ui-icon").removeClass(b).addClass(a);
//        this.cfg.collapsed = c;
//        this.toggleStateHolder.val(c);
//        this.fireToggleEvent()
//    }, fireToggleEvent: function () {
//        if (this.cfg.behaviors) {
//            var a = this.cfg.behaviors.toggle;
//            if (a) {
//                a.call(this)
//            }
//        }
//    }, close: function () {
//        if (this.visibleStateHolder) {
//            this.visibleStateHolder.val(false)
//        }
//        var a = this;
//        this.jq.fadeOut(this.cfg.closeSpeed, function (c) {
//            if (a.cfg.behaviors) {
//                var b = a.cfg.behaviors.close;
//                if (b) {
//                    b.call(a)
//                }
//            }
//        })
//    }, show: function () {
//        var a = this;
//        $(this.jqId).fadeIn(this.cfg.closeSpeed, function () {
//            IChartsFaces.invokeDeferredRenders(a.id)
//        });
//        this.visibleStateHolder.val(true)
//    }, bindToggler: function () {
//        var a = this;
//        this.toggler = $(this.jqId + "_toggler");
//        this.toggleStateHolder = $(this.jqId + "_collapsed");
//        this.toggler.click(function () {
//            a.toggle()
//        })
//    }, bindCloser: function () {
//        var a = this;
//        this.closer = $(this.jqId + "_closer");
//        this.visibleStateHolder = $(this.jqId + "_visible");
//        this.closer.click(function (b) {
//            a.close();
//            b.preventDefault()
//        })
//    }});
//var nobj = 3;
//function simpleStringify(object) {
//    var simpleObject = {};
//    for (var prop in object) {
//        /*
//         if (!object.hasOwnProperty(prop)) {
//         //console.log("==> property own");
//         //continue;
//         nobj--;
//         if (nobj === 0) {
//         nobj++;
//         continue;
//         }
//         simpleObject[prop] = simpleStringify(object[prop].__proto__);
//         }
//         */
//        if ((prop === 'chart') || (prop === 'series')) {
//            continue;
//        } else if (typeof (object[prop]) === 'object') {
//            console.log("==> property is object on size = " + nobj);
//            nobj--;
//            if (nobj === 0) {
//                nobj++;
//                continue;
//            }
//            simpleObject[prop] = simpleStringify(object[prop]);
//
//        } else if (typeof (object[prop]) === 'function') {
//            console.log("==> property is function");
//            continue;
//        } else {
//            console.log(prop + " add");
//            simpleObject[prop] = object[prop];
//        }
//    }
//    return JSON.stringify(simpleObject); // returns cleaned up JSON
//}
//;
//IChartsFaces.Charts = IChartsFaces.widget.BaseWidget.extend();
//IChartsFaces.Charts.Serie = IChartsFaces.Charts.extend({
//    init: function (a) {
//        //this._super(a);
//        this.serie = a;
//    },
//    toString: function () {
//        //console.log(simpleStringify(this.serie));
//        var obj = "{"
//                + "animate:" + this.serie.animate + ","
//                + "animationTimeout:" + this.serie.animationTimeout + ","
//                + "barW:" + this.serie.barW + ","
//                + "borderWidth:" + this.serie.borderWidth + ","
//                //Skip chart
//                + "checkboxOffset:" + this.serie.checkboxOffset + ","
//                + "closestPointRange:" + this.serie.closestPointRange + ","
//                + "closestPointRangePx:" + this.serie.closestPointRangePx + ","
//                + "color:" + this.serie.color + ","
//                + "colorCounter:" + this.serie.colorCounter + ","
//                + "colorIndex:" + this.serie.colorIndex + ","
//                + "columnIndex:" + this.serie.columnIndex + ","
//                + "columnMetrics:{offset:" + this.serie.columnMetrics.offset + ",width:" + this.serie.columnMetrics.width + "},"
//                + "cropStart:" + this.serie.cropStart + ","
//                + "cropped:" + this.serie.cropped + ","
//                + "data:[";
//        var i = 0;
//        for (; this.serie.data[i]; ) {
//            if (i > 0) {
//                obj += ",";
//            }
//            obj += simpleStringify(this.serie.data[i]);
//            i++;
//        }
//        obj += "],"
//                + "dataLabelsGroup:{" + simpleStringify(this.serie.dataLabelsGroup) + "},"
//                + "dataMax:" + this.serie.dataMax + ","
//                + "dataMin:" + this.serie.dataMin + ","
//                + "dense:" + this.serie.dense + ","
//                // Skip group
//                + "hasRendered:" + this.serie.hasRendered + ","
//                + "dataMin:" + this.serie.colorIndex + ","
//                + "dense:" + this.serie.columnIndex + ","
//                // skip hcEvents,
//                + "index:" + this.serie.index + ","
//                // skip invertGroups
//                + "isDirty:" + this.serie.isDirty + ","
//                + "isDirtyData:" + this.serie.isDirtyData + ","
//                // Skip legendGroup
//                + "legendItem:{" + simpleStringify(this.serie.legendItem) + "},"
//                + "legendSymbol:{" + simpleStringify(this.serie.legendSymbol) + "},"
//                // Skip linkedSerie
//                + "markerGroup:{" + simpleStringify(this.serie.markerGroup) + "},"
//                + "name:" + this.serie.name + ","
//                + "options:{" + simpleStringify(this.serie.options) + "},"
//                + "pointInterval:" + this.serie.pointInterval + ","
//                + "pointXOffset:" + this.serie.pointXOffset + ","
//                // Skip poiints
//                + "processedXData:{" + simpleStringify(this.serie.processedXData) + "},"
//                + "processedYData:{" + simpleStringify(this.serie.processedYData) + "},"
//                + "selected:" + this.serie.selected + ","
//                + "selectedSerieId:" + this.serie.selectedSerieId + ","
//                + "sharedClipKey:" + this.serie.sharedClipKey + ","
//                + "state:" + this.serie.state + ","
//                + "tooltipOptions:{" + simpleStringify(this.serie.tooltipOptions) + "},"
//                + "translatedThreshold:" + this.serie.translatedThreshold + ","
//                + "userOptions:{" + simpleStringify(this.serie.userOptions) + "},"
//                + "visible:" + this.serie.visible + ","
//                // Skip xAxis
//                + "xData:{" + simpleStringify(this.serie.xData) + "},"
//                + "xIncrement:" + this.serie.xIncrement + ","
//                // Skip yAxis
//                + "yData:{" + simpleStringify(this.serie.yData) + "},"
//                // Skip zoneAxis
//                // Skip zones
//                + "_hasTracking:" + this.serie._hasTracking + ","
//                + "_i:" + this.serie._i + ","
//                + "_legendItemPos:{" + simpleStringify(this.serie._legendItemPos) + "},"
//                + "__proto__:{" + simpleStringify(this.serie.__proto__) + "}"
//                + "}";
//        console.log(obj);
//        return obj;
//    }
//});
//IChartsFaces.widget.Chart = IChartsFaces.widget.BaseWidget.extend({
//
//    init: function (a) {
//        this._super(a);
//        this.panel = $(this.jqId + "-parent");
//        this.container = $(this.jqId);
//        this.selectedSerieIdHolder = $(this.jqId + "_selectedSerieId");
//        this.selectedSerieHolder = $(this.jqId + "_selectedSerie");
//        var chart = $(this.jqId).highcharts();
//        this.ichart = chart;
//        // Bind all event
//        this.bindEvents();
//    },
//    bindEvents: function () {
//        this.bindChartClick();
//    },
//    /// 
//    /// EVENT
//    bindChartClick: function () {
//        var a = this;
//        console.log(this.ichart);
//        if (this.ichart) {
//            var ser = 0;
//            for (; this.ichart.series[ser]; ) {
//                var serie = this.ichart.series[ser];
//                serie.selectedSerieId = ser;
//                Highcharts.addEvent(serie, 'click', function (serie) {
//                    //this.update({color: color});
//                    // Save in js
//                    this.selected = true;
//                    var serieId = this.selectedSerieId;
//                    // Serie Id Holder
//                    a.selectedSerieIdHolder.val(serieId);
//                    a.cfg.selectedSerieId = serieId;
//                    // Serie Holder
//                    console.log(this);
//                    a.selectedSerieHolder.val(new IChartsFaces.Charts.Serie(this));
//                    a.cfg.selectedSerie = this;
//                    //console.log(this);
//                    a.fireSerieEvent();
//                });
//                ser++;
//            }
//        }
//    },
//    /// 
//    /// EVENT
//    fireSerieEvent: function () {
//        console.log("active serie :" + this.cfg.selectedSerieId);
//        if (this.cfg.behaviors) {
//            var a = this.cfg.behaviors.serieclick;
//            if (a) {
//                a.call(this);
//            }
//        }
//    },
//    /// 
//    /// EVENT
//
//});