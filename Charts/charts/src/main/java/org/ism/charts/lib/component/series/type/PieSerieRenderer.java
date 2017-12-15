/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.series.type;

import java.util.List;
import org.ism.charts.lib.component.series.DataRenderer;
import org.ism.charts.lib.component.series.SerieInRenderer;
import org.ism.charts.lib.model.properties.ChartType;
import org.ism.charts.lib.model.series.Data;
import org.ism.charts.lib.model.series.type.PieSerie;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class PieSerieRenderer extends SerieInRenderer {

    static public String renderer(PieSerie pieSerieSet, Boolean first) {
        String series = "";
        // Exclude Null pointer
        if (pieSerieSet == null) {
            //Util.out("Alarm *** PieSerieRenderer : ", "pieSerieSet is null, return empty string");
            return series;
        }

        series += open(true);

        series += allowPointSelect(pieSerieSet.getAllowPointSelect());
        series += animation(pieSerieSet.getAnimation());
        series += animationLimit(pieSerieSet.getAnimationLimit());
        series += className(pieSerieSet.getClassName());
        series += color(pieSerieSet.getColor());
        series += colorByPoint(pieSerieSet.getColorByPoint());
        series += connectEnds(pieSerieSet.getConnectEnds());
        series += connectNulls(pieSerieSet.getConnectNulls());
        series += cropThreshold(pieSerieSet.getCropThreshold());
        series += cursor(pieSerieSet.getCursor());
        series += dashStyle(pieSerieSet.getDashStyle());
        series += datas(pieSerieSet.getData());
        series += dataLabels(pieSerieSet.getDataLabels(), isFirst(series));
        series += description(pieSerieSet.getDescription());
        series += enableMouseTracking(pieSerieSet.getEnableMouseTracking());
        series += events(pieSerieSet.getEvents(), isFirst(series));
        series += getExtremesFromAll(pieSerieSet.getGetExtremesFromAll());
        series += id(pieSerieSet.getId());
        series += index(pieSerieSet.getIndex());
        series += keys(pieSerieSet.getKeys());
        series += legendIndex(pieSerieSet.getLegendIndex());
        series += lineWidth(pieSerieSet.getLineWidth());
        series += linecap(pieSerieSet.getLinecap());
        series += linkedTo(pieSerieSet.getLinkedTo());
        series += marker(pieSerieSet.getMarker(), isFirst(series));
        series += name(pieSerieSet.getName());
        series += negativeColor(pieSerieSet.getNegativeColor());
        series += point(pieSerieSet.getPoint(), isFirst(series));
        series += pointInterval(pieSerieSet.getPointInterval());
        series += pointIntervalUnit(pieSerieSet.getPointIntervalUnit());
        series += pointPlacement(pieSerieSet.getPointPlacement());
        series += pointStart(pieSerieSet.getPointStart());
        series += selected(pieSerieSet.getSelected());
        series += shadow(pieSerieSet.getShadow());
        series += showCheckbox(pieSerieSet.getShowCheckbox());
        series += showInLegend(pieSerieSet.getShowInLegend());
        series += softThreshold(pieSerieSet.getSoftThreshold());
        series += stack(pieSerieSet.getStack());
        series += stacking(pieSerieSet.getStacking());
        series += states(pieSerieSet.getStates(), isFirst(series));
        series += step(pieSerieSet.getStep());
        series += stickyTracking(pieSerieSet.getStickyTracking());
        series += threshold(pieSerieSet.getThreshold());
        series += tooltip(pieSerieSet.getTooltip(), isFirst(series));
        series += turboThreshold(pieSerieSet.getTurboThreshold());
        series += visible(pieSerieSet.getVisible());
        series += xAxis(pieSerieSet.getxAxis());
        series += yAxis(pieSerieSet.getyAxis());
        series += zIndex(pieSerieSet.getzIndex());
        series += zoneAxis(pieSerieSet.getZoneAxis());
        series += zones(pieSerieSet.getZones(), isFirst(series));

        series = close(series);
        return series;
    }

    static public String renderer(PieSerie pieSerieSet) {
        return renderer(pieSerieSet, Boolean.FALSE);
    }

    static public String open(Boolean first) {
        return first ? "{" : ",{";
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
        series += "}";
        return series;
    }

    static public Boolean isFirst(String str) {
        return str.endsWith(",") || str.endsWith("{");
    }

    static public String datas(List<Data> datas) {
        if (datas == null) {
            Util.out("PieSerie data is null !");
            return "";
        }

        String str = "data:[";
        for (Data d : datas) {
            String tmp = DataRenderer.renderer(d, true);
            str += tmp + (!tmp.isEmpty() ? "," : "");
        }
        str = str.substring(0, str.length() - 1);
        str += "],";
        return str;
    }

//    static public String data(Data data, Boolean first) {
//        if (data != null) {
//            String tmp = DataRenderer.renderer(data, first);
//            return tmp + (!tmp.isEmpty() ? "," : "");
//        }
//        return "";
//    }
}
