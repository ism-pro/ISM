/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


PrimeFaces.widget.Chart = PrimeFaces.widget.BaseWidget.extend({

    init: function (a) {
        this._super(a);
        this.panel = $(this.jqId + "-parent");
        this.container = $(this.jqId);
        this.selectedSerieIdHolder = $(this.jqId + "_selectedSerieId");
        this.selectedSerieOptionsHolder = $(this.jqId + "_selectedSerieOptions");
        console.log($(this.jqId))
        var chart = $(this.jqId).highcharts();
        this.ichart = chart;
        // Bind all event
        this.bindEvents();
        console.log("Chart start");
    },
    bindEvents: function () {
        this.bindSerieClick();
    },
    /// 
    /// EVENT
    bindSerieClick: function () {
        var a = this;
        //console.log(this.ichart);
        if (this.ichart) {
            var ser = 0;
            for (; this.ichart.series[ser]; ) {
                var serie = this.ichart.series[ser];
                serie.selectedSerieId = ser;
                Highcharts.addEvent(serie, 'click', function (serie) {
                    // Save in js
                    this.selected = true;
                    var serieId = this.selectedSerieId;
                    // Serie Id Holder
                    a.selectedSerieIdHolder.val(serieId);
                    a.cfg.selectedSerieId = serieId;
                    // Serie Holder
                    a.selectedSerieOptionsHolder.val(JSON.stringify(this.options));
                    a.cfg.selectedSerie = this;
                    // Fire Serie Click Event
                    a.fireSerieClickEvent();
                });
                ser++;
            }
        }
    },
    /// 
    /// EVENT
    fireSerieClickEvent: function () {
        if (this.cfg.behaviors) {
            var a = this.cfg.behaviors.serieclick;
            if (a) {
                a.call(this);
            }
        }
    }
    /// 
    /// EVENT

});






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
//        /*if ((prop === 'chart') || (prop === 'series')) {
//         continue;
//         } else*/ if (typeof (object[prop]) === 'object') {
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
//    nobj++;
//    return JSON.stringify(simpleObject); // returns cleaned up JSON
//}
//;


//IChartsFaces.widget.Serie = IChartsFaces.widget.BaseWidget.extend({
//    init: function (a) {
//        //this._super(a);
//        this.serie = a;
//    },
//    toString: function () {
//        //console.log(simpleStringify(this.serie));
//        /*
//         var obj = "{"
//         + "animate:" + this.serie.animate + ","
//         + "animationTimeout:" + this.serie.animationTimeout + ","
//         + "barW:" + this.serie.barW + ","
//         + "borderWidth:" + this.serie.borderWidth + ","
//         //Skip chart
//         "checkboxOffset:" + this.serie.checkboxOffset + ","
//         + "closestPointRange:" + this.serie.closestPointRange + ","
//         + "closestPointRangePx:" + this.serie.closestPointRangePx + ","
//         + "color:" + this.serie.color + ","
//         + "colorCounter:" + this.serie.colorCounter + ","
//         + "colorIndex:" + this.serie.colorIndex + ","
//         + "columnIndex:" + this.serie.columnIndex + ","
//         //+ "columnMetrics:{offset:" + this.serie.columnMetrics.offset + ",width:" + this.serie.columnMetrics.width + "},"
//         + "cropStart:" + this.serie.cropStart + ","
//         + "cropped:" + this.serie.cropped + ","
//         + "data:[";
//         var i = 0;
//         for (; this.serie.data[i]; ) {
//         if (i > 0) {
//         obj += ",";
//         }
//         obj += simpleStringify(this.serie.data[i]);
//         i++;
//         }
//         obj += "],"
//         + "dataLabelsGroup:{" + simpleStringify(this.serie.dataLabelsGroup) + "},"
//         + "dataMax:" + this.serie.dataMax + ","
//         + "dataMin:" + this.serie.dataMin + ","
//         + "dense:" + this.serie.dense + ","
//         // Skip group
//         + "hasRendered:" + this.serie.hasRendered + ","
//         + "dataMin:" + this.serie.colorIndex + ","
//         + "dense:" + this.serie.columnIndex + ","
//         // skip hcEvents,
//         + "index:" + this.serie.index + ","
//         // skip invertGroups
//         + "isDirty:" + this.serie.isDirty + ","
//         + "isDirtyData:" + this.serie.isDirtyData + ","
//         // Skip legendGroup
//         + "legendItem:{" + simpleStringify(this.serie.legendItem) + "},"
//         + "legendSymbol:{" + simpleStringify(this.serie.legendSymbol) + "},"
//         // Skip linkedSerie
//         + "markerGroup:{" + simpleStringify(this.serie.markerGroup) + "},"
//         + "name:" + this.serie.name + ","
//         + "options:{" + simpleStringify(this.serie.options) + "},"
//         + "pointInterval:" + this.serie.pointInterval + ","
//         + "pointXOffset:" + this.serie.pointXOffset + ","
//         // Skip poiints
//         + "processedXData:{" + simpleStringify(this.serie.processedXData) + "},"
//         + "processedYData:{" + simpleStringify(this.serie.processedYData) + "},"
//         + "selected:" + this.serie.selected + ","
//         + "selectedSerieId:" + this.serie.selectedSerieId + ","
//         + "sharedClipKey:" + this.serie.sharedClipKey + ","
//         + "state:" + this.serie.state + ","
//         + "tooltipOptions:{" + simpleStringify(this.serie.tooltipOptions) + "},"
//         + "translatedThreshold:" + this.serie.translatedThreshold + ","
//         + "userOptions:{" + simpleStringify(this.serie.userOptions) + "},"
//         + "visible:" + this.serie.visible + ","
//         // Skip xAxis
//         + "xData:{" + simpleStringify(this.serie.xData) + "},"
//         + "xIncrement:" + this.serie.xIncrement + ","
//         // Skip yAxis
//         + "yData:{" + simpleStringify(this.serie.yData) + "},"
//         // Skip zoneAxis
//         // Skip zones
//         + "_hasTracking:" + this.serie._hasTracking + ","
//         + "_i:" + this.serie._i + ","
//         + "_legendItemPos:{" + simpleStringify(this.serie._legendItemPos) + "},"
//         + "__proto__:{" + simpleStringify(this.serie.__proto__) + "}"
//         + "}";*/
//        //console.log(this.serie.options);
//        //var obj = simpleStringify(this.serie.options);
//        var obj = JSON.stringify(this.serie.options);
//        //console.log(obj);
//        return obj;
//    }
//});

//IChartsFaces.widget.Desk  = IChartsFaces.widget.BaseWidget.extend({
//    ///
//    /// Init
//    init: function (a) {
//        this._super(a);
//        this.menu = $(this.jqId + "_menu");
//        this.collapsed = $(this.jqId + "_collapsed");
//        this.closable = $(this.jqId + "_closable");
//        
//        // Render
//        this.desktop = $(".ic-desktop");
//        this.row = $(".ic-desktop.ic-row");
//        this.row.header = $(".ic-desktop.ic-row.ic-header");
//        this.row.content = $(".ic-desktop.ic-row.ic-content");
//        this.row.footer = $(".ic-desktop.ic-row.ic-footer");
//        this.menu = $(".ic-desktop.ic-row.ic-content.ic-menu");
//        this.menu.left = $(".ic-desktop.ic-row.ic-content.ic-menu.ic-left");
//        this.menu.right = $(".ic-desktop.ic-row.ic-content.ic-menu.ic-right");
//        this.area = $(".ic-desktop.ic-row.ic-content.ic-area");
//        
//        console.log("Init Desktop");
//        this.buildDesk();
//    },
//    
//    ///
//    /// Build
//    buildDesk: function(){
//        // Init desktop
//        this.desktop.addClass("container-fluid");
//        
//        // Init rows
//        this.desktopRow.addClass("row");
//        
//        
//        // Init 
//    },
//    
//    //==========================================================================
//    // BINDING
//    //==========================================================================
//    bindMenuEvents: function () {
//        var a = this;
//        this.bindMenuHover(this.rowSelector);
//        this.tbody.off("click.dataTable", this.rowSelector).on("click.dataTable", this.rowSelector, null, function (b) {
//            a.onRowClick(b, $(this));
//        });
//        if (this.hasBehavior("rowDblselect")) {
//            this.tbody.off("dblclick.dataTable", this.rowSelector).on("dblclick.dataTable", this.rowSelector, null, function (b) {
//                a.onRowDblclick(b, $(this));
//            });
//        }
//    },
//    onMenuClick: function (e, d, a) {
//
//    }
//});
//$(function () {
//
//    /**
//     * Gère l'affichage du desk complet ou réduit seulement au 
//     * menu
//     */
//    $('.c-menunav').click(function () {
//        var deskwidth = $('.c-desk').width();
//        var sideMenuWidth = $('.c-desk .c-sidemenu').width();
//        //console.log("Desk with :" + deskwidth + " sidemenu with : " + sideMenuWidth);
//        if (deskwidth <= sideMenuWidth+1 && deskwidth >= sideMenuWidth-1) {
//            // agrandit
//            var newWidth = 200;
//            $('.c-desk').css('overflow', 'auto');
//            $('.c-desk').width(newWidth);
//            if ($('.c-deskBody')) {
//                $('.c-deskBody').css('margin-left', newWidth);
//            }
//
//        } else {
//            // reduce to auto size
//            var sideMenuWidth = $('.c-desk .c-sidemenu').width();
//            $('.c-desk').css('width', 'auto');
//            $('.c-desk').css('overflow', 'hidden');
//            if ($('.c-deskBody')) {
//                $('.c-deskBody').css('margin-left', sideMenuWidth);
//            }
//        }
//    });
//});
