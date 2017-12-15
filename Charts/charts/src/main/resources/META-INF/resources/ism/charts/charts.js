/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fake(){
    console.log("Fake ");
}
    /*
$(function () {
    var a;

    var processed_json = new Array();
    $.getJSON('http://localhost:8080/charts_demo/data.json', function (data) {
        // Populate series
        for (i = 0; i < data.length; i++) {
            processed_json.push([data[i].key, data[i].value]);
        }

        // draw chart
        $('#container').highcharts({
            chart: {
                type: "column"
            },
            title: {
                text: "Student data"
            },
            xAxis: {
                type: 'category',
                allowDecimals: false,
                title: {
                    text: ""
                }
            },
            yAxis: {
                title: {
                    text: "Scores"
                }
            },
            series: [{
                    name: 'Subjects',
                    data: processed_json
                }]
        });
    });
   
});
 */





/*
 
 var chart1 = new Highcharts.Chart({
 chart: {
 renderTo: 'ctFruit',
 type: 'bar'
 },
 title: {
 text: 'Fruit Consumption'
 },
 xAxis: {
 categories: ['Apples', 'Bananas', 'Oranges']
 },
 yAxis: {
 title: {
 text: 'Fruit eaten'
 }
 },
 series: [{
 name: 'Jane',
 data: [1, 0, 4]
 }, {
 name: 'John',
 data: [5, 7, 3]
 }]
 });
 
 */
/*
 function renderChart(divId, chartType, chartTitle, chartData, categories) {
 var options = createOption(divId, chartType, chartTitle, categories);
 options.series = $.parseJSON(chartData);
 //chart.xAxis[0].setCategories($.parseJSON(categories));
 var chart = new Highcharts.Chart(options);
 }
 
 function createOption(divId, chartType, chartTitle, categories) {
 var options = {
 colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
 chart: {
 backgroundColor: {
 linearGradient: [0, 0, 500, 500],
 stops: [
 [0, 'rgb(255, 255, 255)'],
 [1, 'rgb(240, 240, 255)']
 ]
 },
 borderWidth: 2,
 plotBackgroundColor: 'rgba(255, 255, 255, .9)',
 plotShadow: true,
 plotBorderWidth: 1,
 renderTo: divId,
 type: chartType
 },
 title: {
 text: 'JVM Heap Memory Usage Chart',
 style: {
 color: '#000',
 font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
 }
 },
 subtitle: {
 text: 'Source: http://java-bytes.blogspot.com',
 style: {
 color: '#666666',
 font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
 }
 },
 xAxis: {
 ridLineWidth: 1,
 lineColor: '#000',
 tickColor: '#000',
 categories: $.parseJSON(categories),
 labels: {
 style: {
 color: '#000',
 font: '11px Trebuchet MS, Verdana, sans-serif'
 },
 formatter: function () {
 return this.value;
 }
 },
 title: {
 style: {
 color: '#333',
 fontWeight: 'bold',
 fontSize: '12px',
 fontFamily: 'Trebuchet MS, Verdana, sans-serif'
 
 }
 }
 },
 yAxis: {
 minorTickInterval: 'auto',
 lineColor: '#000',
 lineWidth: 1,
 tickWidth: 1,
 tickColor: '#000',
 title: {
 style: {
 color: '#333',
 fontWeight: 'bold',
 fontSize: '12px',
 fontFamily: 'Trebuchet MS, Verdana, sans-serif'
 },
 text: 'Heap Memory Usage'
 },
 labels: {
 formatter: function () {
 return this.value + 'MB';
 },
 style: {
 color: '#000',
 font: '11px Trebuchet MS, Verdana, sans-serif'
 }
 }
 },
 plotOptions: {
 area: {
 stacking: 'normal',
 lineColor: '#666666',
 lineWidth: 1,
 marker: {
 lineWidth: 1,
 lineColor: '#666666'
 }
 }
 },
 tooltip: {
 formatter: function () {
 return '' + this.x + ': ' + Highcharts.numberFormat(this.y, 0, ',') + ' MB';
 }
 },
 legend: {
 itemStyle: {
 font: '9pt Trebuchet MS, Verdana, sans-serif',
 color: 'black'
 
 },
 itemHoverStyle: {
 color: '#039'
 },
 itemHiddenStyle: {
 color: 'gray'
 }
 },
 labels: {
 style: {
 color: '#99b'
 }
 },
 series: []
 };
 
 return options;
 };
 
 
 
 
 
 $(function () { 
 var myChart = Highcharts.chart('container', {
 chart: {
 type: 'bar'
 },
 title: {
 text: 'Fruit Consumption'
 },
 xAxis: {
 categories: ['Apples', 'Bananas', 'Oranges']
 },
 yAxis: {
 title: {
 text: 'Fruit eaten'
 }
 },
 series: [{
 name: 'Jane',
 data: [1, 0, 4]
 }, {
 name: 'John',
 data: [5, 7, 3]
 }]
 });
 });
 
 
 
 $(function () {
 $('#piDemo').highcharts({
 title: {
 text: 'Monthly Average Temperature',
 x: -20 //center
 },
 subtitle: {
 text: 'Source: WorldClimate.com',
 x: -20
 },
 xAxis: {
 categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
 },
 yAxis: {
 title: {
 text: 'Temperature (�C)'
 },
 plotLines: [{
 value: 0,
 width: 1,
 color: '#808080'
 }]
 },
 tooltip: {
 valueSuffix: '�C'
 },
 legend: {
 layout: 'vertical',
 align: 'right',
 verticalAlign: 'middle',
 borderWidth: 0
 },
 series: [{
 name: 'Tokyo',
 data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
 }, {
 name: 'New York',
 data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
 }, {
 name: 'Berlin',
 data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
 }, {
 name: 'London',
 data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
 }]
 });
 });
 
 */







