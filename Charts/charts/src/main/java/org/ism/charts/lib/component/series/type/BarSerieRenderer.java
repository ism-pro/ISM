/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.series.type;

import org.ism.charts.lib.component.series.SerieInRenderer;
import org.ism.charts.lib.component.series.SeriesRenderer;
import static org.ism.charts.lib.component.series.SerieInRenderer.datas;
import org.ism.charts.lib.model.properties.ChartType;
import org.ism.charts.lib.model.series.type.BarSerie;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class BarSerieRenderer extends SerieInRenderer {

    static public String renderer(BarSerie barSerieSet, Boolean first) {
        String series = "";
        // Exclude Null pointer
        if (barSerieSet == null) {
            //Util.out("Alarm *** BarSerieRenderer : ", "barSerieSet is null, return empty string");
            return series;
        }
        
        series += datas(barSerieSet.getData());


        series = close(series);
        return series;
    }

    static public String renderer(BarSerie barSerieSet) {
        return renderer(barSerieSet, Boolean.FALSE);
    }

    static public String open(Boolean first) {
        return "";//first ? "line:{" : ",line:{";
    }

    static public String open(Boolean first, ChartType type) {
        return first ? "" + type + ":{" : "," + type + ":{";
    }

    static public String close(String series) {
        if (series.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        series = series.substring(0, series.length() - 1);
        //series += "}";
        return series;
    }

    static public Boolean isFirst(String str) {
        return str.endsWith(",") || str.endsWith("{");
    }



}
