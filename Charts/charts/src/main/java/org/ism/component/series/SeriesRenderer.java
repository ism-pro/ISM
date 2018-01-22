/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.series;

import org.ism.component.series.type.LineSerieRenderer;
import java.util.List;
import org.ism.component.series.type.BarSerieRenderer;
import org.ism.component.series.type.PieSerieRenderer;
import org.ism.model.series.Data;
import org.ism.model.series.type.LineSerie;
import org.ism.model.series.Series;
import org.ism.model.series.type.BarSerie;
import org.ism.model.series.type.PieSerie;
import org.ism.util.Util;

/**
 * <h1>SeriesRenderer</h1><br>
 * SeriesRenderer class
 *
 * @author r.hendrick
 *
 */
public class SeriesRenderer {

    static public String renderer(Series seriesSet, Boolean first) {
        String series = "";
        // Exclude Null pointer
        if (seriesSet == null) {
            //Util.out("Alarm *** SeriesRenderer : ", "seriesSet is null, return empty string");
            return series;
        }

        series += open(first);
        series += lineSerie(seriesSet.getLineSerie(), isFirst(series));
        series += barSerie(seriesSet.getBarSerie(), isFirst(series));
        series += pieSerie(seriesSet.getPieSerie(), isFirst(series));

        series = close(series);
        return series;
    }

    static public String renderer(Series seriesSet) {
        return renderer(seriesSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "series:[" : ",series:[";
    }

    static private String close(String series) {
        if (series.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        series = series.substring(0, series.length() - 1);
        series += "]";
        return series;
    }

    static private Boolean isFirst(String states) {
        return states.endsWith(",");
    }

    static private String lineSerie(LineSerie lineSerie, Boolean first) {
        if (lineSerie != null) {
            String tmp = LineSerieRenderer.renderer(lineSerie, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }
    
    
    static private String barSerie(BarSerie barSerie, Boolean first) {
        if (barSerie != null) {
            String tmp = BarSerieRenderer.renderer(barSerie, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String pieSerie(PieSerie pieSerie, Boolean first) {
        if (pieSerie != null) {
            String tmp = PieSerieRenderer.renderer(pieSerie, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }
}
