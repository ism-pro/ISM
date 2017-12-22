/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fake() {
    console.log("Fake ");
}

(function (H) {
    Highcharts.setOptions({
        lang: {
            months: [
                'Janvier', 'Février', 'Mars', 'Avril',
                'Mai', 'Juin', 'Juillet', 'Août',
                'Septembre', 'Octobre', 'Novembre', 'Décembre'
            ],

            /**
             * The text for the label appearing when a chart is zoomed.
             * Defaults to Reset zoom.
             */
            resetZoom: "Dézoomer",
            /**
             * he tooltip title for the label appearing when a chart is zoomed.
             * Defaults to Reset zoom level 1:1.
             */
            resetZoomTitle: "Dézoomer à l'échelle [1:1]",
            /**
             * 
             */
            shortMonths: ["Jan", "Fév", "Mar", "Avr", "Mai", "Jui", "Juil", "Aou", "Sept", "Oct", "Nov", "Déc"],
            /**
             * Exporting module only. The text for the menu item to print the chart.
             * Defaults to Print chart.
             */
            weekdays: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi']
        }
    });

}(Highcharts));



$(function () {
    var a = "";
});






