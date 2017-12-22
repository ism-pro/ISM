/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fake() {
    console.log("Fake ");
}
function toStringISO(text) {
    var a = "";
    a = text;
    a = a.replace('!', '\041').replace('"', '\042').replace('#', '\043').replace('$', '\044').replace('%', '\045').replace('&', '\046').replace("'", '\047').replace('<', '\074').replace('=', '\075').replace('>', '\076').replace('^', '\136').replace('`', '\140').replace('~', '\176').replace('€', '\200').replace('', '\201').replace('‚', '\202').replace('ƒ', '\203').replace('„', '\204').replace('…', '\205').replace('ˆ', '\210').replace('‰', '\211').replace('', '\215').replace('Ž', '\216').replace('', '\217').replace('', '\220').replace('‘', '\221').replace('’', '\222').replace('“', '\223').replace('”', '\224').replace('•', '\225').replace('–', '\226').replace('—', '\227').replace('˜', '\230').replace('™', '\231').replace('š', '\232').replace('›', '\233').replace('œ', '\234').replace('', '\235').replace('ž', '\236').replace('Ÿ', '\237').replace(' ', '\240').replace('¡', '\241').replace('¢', '\242').replace('£', '\243').replace('¤', '\244').replace('¥', '\245').replace('¦', '\246').replace('§', '\247').replace('¨', '\250').replace('©', '\251').replace('ª', '\252').replace('«', '\253').replace('¬', '\254').replace('­', '\255').replace('®', '\256').replace('¯', '\257').replace('°', '\260').replace('±', '\261').replace('²', '\262').replace('³', '\263').replace('´', '\264').replace('µ', '\265').replace('¶', '\266').replace('·', '\267').replace('¸', '\270').replace('¹', '\271').replace('º', '\272').replace('»', '\273').replace('¼', '\274').replace('½', '\275').replace('¾', '\276').replace('¿', '\277').replace('Ç', '\307').replace('È', '\310').replace('É', '\311').replace('Ê', '\312').replace('Ë', '\313').replace('Ì', '\314').replace('Í', '\315').replace('Î', '\316').replace('Ï', '\317').replace('Ò', '\322').replace('Ó', '\323').replace('Ô', '\324').replace('Õ', '\325').replace('Ö', '\326').replace('×', '\327').replace('Ù', '\331').replace('Ú', '\332').replace('Û', '\333').replace('Ü', '\334').replace('à', '\340').replace('á', '\341').replace('â', '\342').replace('ã', '\343').replace('ä', '\344').replace('å', '\345').replace('æ', '\346').replace('ç', '\347').replace('è', '\350').replace('é', '\351').replace('ê', '\352').replace('ë', '\353').replace('ì', '\354').replace('í', '\355').replace('î', '\356').replace('ï', '\357').replace('ô', '\364').replace('ö', '\366').replace('ù', '\371').replace('ú', '\372').replace('û', '\373').replace('ü', '\374');

    return a;
}
function tsi(text) {
    return toStringISO(text);
}
;
$(function () {

    var fr = {
        /**
         * contextButtonTitle: StringSince 3.0
         * Exporting module menu. The tooltip title for the context menu holding print and export menu items.
         * Defaults to Chart context menu.
         */
        contextButtonTitle: "Options du graphique",
        /**
         * decimalPoint: StringSince 1.2.2
         * The default decimal point used in the Highcharts.numberFormat method unless otherwise specified in the function arguments.
         *
         * Defaults to ..
         */
        decimalPoint: ",",
        downloadCSV: tsi("Telecharger CSV"),
        downloadJPEG: tsi("Telecharger JPEG image"),
        downloadPDF: tsi("Telecharger PDF"),
        downloadPNG: tsi("Telecharger PNG image"),
        downloadSVG: tsi("Telecharger SVG vecteur Image"),
        downloadXLS: tsi("Telecharger XLS"),
        
        /**
         * 
         */
        loading: "Chargement...",
        months: [
            'Janvier', tsi('Février'), 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', tsi('Août'), 'Septembre', 'Octobre', 'Novembre', tsi('Décembre')],
        
        printChart: "Imprimer",
        rangeSelectorFrom: "De",
        rangeSelectorTo: tsi("à"),
        rangeSelectorZoom: "Zommer",
        
        /**
         * The text for the label appearing when a chart is zoomed.
         * Defaults to Reset zoom.
         */
        resetZoom: tsi("Dézoomer"),
        /**
         * he tooltip title for the label appearing when a chart is zoomed.
         * Defaults to Reset zoom level 1:1.
         */
        resetZoomTitle: tsi("Dézoomer à l'echelle [1:1]"),
        /**
         * 
         */
        shortMonths: ["Jan", tsi("Fév"), "Mar", "Avr", "Mai", "Jui", "Juil", tsi("Aoû"), "Sept", "Oct", "Nov", tsi("Déc")],
        shortWeekdays: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'],
        viewData: 'Visualiser la table de donnée',
        /**
         * Exporting module only. The text for the menu item to print the chart.
         * Defaults to Print chart.
         */
        weekdays: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi']
    };
    Highcharts.setOptions({lang: fr});
});



