/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.series.type;

import org.ism.component.series.SerieInRenderer;
import org.ism.component.series.SeriesRenderer;
import static org.ism.component.series.SerieInRenderer.datas;
import org.ism.model.properties.ChartType;
import org.ism.model.series.type.LineSerie;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class LineSerieRenderer extends SerieInRenderer {

    static public String renderer(LineSerie lineSerieSet, Boolean first) {
        String series = "";
        // Exclude Null pointer
        if (lineSerieSet == null) {
            //Util.out("Alarm *** LineSerieRenderer : ", "lineSerieSet is null, return empty string");
            return series;
        }
        
        series += datas(lineSerieSet.getData());


        series = close(series);
        return series;
    }

    static public String renderer(LineSerie lineSerieSet) {
        return renderer(lineSerieSet, Boolean.FALSE);
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
