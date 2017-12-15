/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.series;

import org.ism.charts.lib.model.properties.ChartType;
import org.ism.charts.lib.model.series.type.LineSerie;
import org.ism.charts.lib.model.series.PlotOptions;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class PlotOptionsRenderer extends SerieInRenderer {

    static public String renderer(PlotOptions plotOptionsSet, Boolean first) {
        String plotOptions = "";
        
        // Exclude Null pointer
        if (plotOptionsSet == null) {
            //Util.out("Alarm *** PlotOptionsRenderer : ", "plotOptionsSet is null, return empty string");
            return plotOptions;
        }

        plotOptions += open(first, plotOptionsSet.getType());

        plotOptions += allowPointSelect(plotOptionsSet.getAllowPointSelect());
        plotOptions += animation(plotOptionsSet.getAnimation());
        plotOptions += animationLimit(plotOptionsSet.getAnimationLimit());
        plotOptions += className(plotOptionsSet.getClassName());
        plotOptions += color(plotOptionsSet.getColor());
        plotOptions += colorByPoint(plotOptionsSet.getColorByPoint());
        plotOptions += connectEnds(plotOptionsSet.getConnectEnds());
        plotOptions += connectNulls(plotOptionsSet.getConnectNulls());
        plotOptions += cropThreshold(plotOptionsSet.getCropThreshold());
        plotOptions += cursor(plotOptionsSet.getCursor());
        plotOptions += dashStyle(plotOptionsSet.getDashStyle());
        plotOptions += datas(plotOptionsSet.getData());
        plotOptions += dataLabels(plotOptionsSet.getDataLabels(), isFirst(plotOptions));
        plotOptions += description(plotOptionsSet.getDescription());
        plotOptions += enableMouseTracking(plotOptionsSet.getEnableMouseTracking());
        plotOptions += events(plotOptionsSet.getEvents(), isFirst(plotOptions));
        plotOptions += getExtremesFromAll(plotOptionsSet.getGetExtremesFromAll());
        plotOptions += id(plotOptionsSet.getId());
        plotOptions += index(plotOptionsSet.getIndex());
        plotOptions += keys(plotOptionsSet.getKeys());
        plotOptions += legendIndex(plotOptionsSet.getLegendIndex());
        plotOptions += lineWidth(plotOptionsSet.getLineWidth());
        plotOptions += linecap(plotOptionsSet.getLinecap());
        plotOptions += linkedTo(plotOptionsSet.getLinkedTo());
        plotOptions += marker(plotOptionsSet.getMarker(), isFirst(plotOptions));
        plotOptions += name(plotOptionsSet.getName());
        plotOptions += negativeColor(plotOptionsSet.getNegativeColor());
        plotOptions += point(plotOptionsSet.getPoint(), isFirst(plotOptions));
        plotOptions += pointInterval(plotOptionsSet.getPointInterval());
        plotOptions += pointIntervalUnit(plotOptionsSet.getPointIntervalUnit());
        plotOptions += pointPlacement(plotOptionsSet.getPointPlacement());
        plotOptions += pointStart(plotOptionsSet.getPointStart());
        plotOptions += selected(plotOptionsSet.getSelected());
        plotOptions += shadow(plotOptionsSet.getShadow());
        plotOptions += showCheckbox(plotOptionsSet.getShowCheckbox());
        plotOptions += showInLegend(plotOptionsSet.getShowInLegend());
        plotOptions += softThreshold(plotOptionsSet.getSoftThreshold());
        plotOptions += stack(plotOptionsSet.getStack());
        plotOptions += stacking(plotOptionsSet.getStacking());
        plotOptions += states(plotOptionsSet.getStates(), isFirst(plotOptions));
        plotOptions += step(plotOptionsSet.getStep());
        plotOptions += stickyTracking(plotOptionsSet.getStickyTracking());
        plotOptions += threshold(plotOptionsSet.getThreshold());
        plotOptions += tooltip(plotOptionsSet.getTooltip(), isFirst(plotOptions));
        plotOptions += turboThreshold(plotOptionsSet.getTurboThreshold());
        plotOptions += visible(plotOptionsSet.getVisible());
        plotOptions += xAxis(plotOptionsSet.getxAxis());
        plotOptions += yAxis(plotOptionsSet.getyAxis());
        plotOptions += zIndex(plotOptionsSet.getzIndex());
        plotOptions += zoneAxis(plotOptionsSet.getZoneAxis());
        plotOptions += zones(plotOptionsSet.getZones(), isFirst(plotOptions));

        plotOptions = close(plotOptions);
        return plotOptions;
    }

    static public String renderer(PlotOptions plotOptionsSet) {
        return renderer(plotOptionsSet, Boolean.FALSE);
    }

    static public String open(Boolean first, ChartType type) {
        return first ? ("plotOptions:{" + type + ":{") : (",plotOptions:{" + type + ":{");
    }

    public static String close(String plotOptions) {
        if (plotOptions.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        plotOptions = plotOptions.substring(0, plotOptions.length() - 1);
        plotOptions += "}}";
        return plotOptions;
    }

    public static Boolean isFirst(String str) {
        return str.endsWith(",") || str.endsWith("{");
    }

}
