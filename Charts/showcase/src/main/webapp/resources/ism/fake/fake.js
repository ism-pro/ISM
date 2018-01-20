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

//
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

//Primefaces.widget.Chart = Primefaces.widget.BaseWidget.extend({
//
//    init: function (a) {
//        this._super(a);
//        this.panel = $(this.jqId + "-parent");
//        this.container = $(this.jqId);
//        this.selectedSerieIdHolder = $(this.jqId + "_selectedSerieId");
//        this.selectedSerieOptionsHolder = $(this.jqId + "_selectedSerieOptions");
//        var chart = $(this.jqId).highcharts();
//        this.ichart = chart;
//        // Bind all event
//        this.bindEvents();
//        //console.log("Chart start");
//    },
//    bindEvents: function () {
//        this.bindSerieClick();
//    },
//    /// 
//    /// EVENT
//    bindSerieClick: function () {
//        var a = this;
//        //console.log(this.ichart);
//        if (this.ichart) {
//            var ser = 0;
//            for (; this.ichart.series[ser]; ) {
//                var serie = this.ichart.series[ser];
//                serie.selectedSerieId = ser;
//                Highcharts.addEvent(serie, 'click', function (serie) {
//                    // Save in js
//                    this.selected = true;
//                    var serieId = this.selectedSerieId;
//                    // Serie Id Holder
//                    a.selectedSerieIdHolder.val(serieId);
//                    a.cfg.selectedSerieId = serieId;
//                    // Serie Holder
//                    a.selectedSerieOptionsHolder.val(JSON.stringify(this.options));
//                    a.cfg.selectedSerie = this;
//                    // Fire Serie Click Event
//                    a.fireSerieClickEvent();
//                });
//                ser++;
//            }
//        }
//    },
//    /// 
//    /// EVENT
//    fireSerieClickEvent: function () {
//        if (this.cfg.behaviors) {
//            var a = this.cfg.behaviors.serieclick;
//            if (a) {
//                a.call(this);
//            }
//        }
//    }
//    /// 
//    /// EVENT
//
//});
//IChartsFaces.widget.Desktop  = IChartsFaces.widget.BaseWidget.extend({
//    ///
//    /// Init
//    init: function (a) {
//        //this._super(a);
////        this.menu = $(this.jqId + "_menu");
////        this.collapsed = $(this.jqId + "_collapsed");
////        this.closable = $(this.jqId + "_closable");
//        this.cfg = {};
//
//        // 
//        this.fullscreen = this.cfg.fullscreen || true;
//        
//        // Render
//        this.desktop = $(".ic-desktop");
//        this.row = $(".ic-row");
//        this.row.header = $(".ic-desktop.ic-row.ic-header");
//        this.row.content = $(".ic-desktop.ic-row.ic-content");
//        this.row.footer = $(".ic-desktop.ic-row.ic-footer");
//        this.menu = $(".ic-content .ic-menu");
//        this.menu.left = $(".ic-desktop.ic-row.ic-content.ic-menu.ic-left");
//        this.menu.right = $(".ic-desktop.ic-row.ic-content.ic-menu.ic-right");
//        this.area = $(".ic-content .ic-area");
//        
//        this.buildDesk();
//        
//    },
//    
//    ///
//    /// Build
//    buildDesk: function(){
//        // Init desktop
//        this.desktop.addClass("container-fluid");
//        if(this.fullscreen){
//            this.desktop.addClass("ic-full-height");
//        }
//        
//        // Init rows
//        this.row.addClass("row");
//        
//        // Init menu
//        this.menu.addClass("col-sm-2");
//        if(this.fullscreen){
//            this.menu.addClass("ic-full-height");
//        }
//       
//        // Area
//        this.area.addClass("col-sm-10");
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
//
//
//
//$(function () {
//     
//    $(document).ready(function(){
//       // var desktop = new IChartsFaces.widget.Desktop();
//    });
//});
//




