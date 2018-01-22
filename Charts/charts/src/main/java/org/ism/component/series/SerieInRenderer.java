/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.series;

import java.util.List;
import org.ism.component.axis.PointRenderer;
import org.ism.component.properties.DataLabelsRenderer;
import org.ism.component.properties.EventsRenderer;
import org.ism.component.properties.StatesRenderer;
import org.ism.model.axis.Point;
import org.ism.model.properties.ChartType;
import org.ism.model.properties.DashStyle;
import org.ism.model.properties.DataLabels;
import org.ism.model.properties.Events;
import org.ism.model.properties.States;
import org.ism.model.series.Data;
import org.ism.model.series.Marker;
import org.ism.model.series.SerieIn;
import org.ism.model.series.ToolTip;
import org.ism.model.series.Zones;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class SerieInRenderer {

    
    static public String renderer(SerieIn SerieInSet, Boolean first) {
        String serieIn = "";
        // Exclude Null pointer
        if (SerieInSet == null) {
            //Util.out("Alarm *** SerieInRenderer : ", "SerieInSet is null, return empty string");
            return serieIn;
        }

        //serieIn += open(first);
        //serieIn += open(first, SerieInSet.getType());
        //serieIn += type(SerieInSet.getType());
        serieIn += allowPointSelect(SerieInSet.getAllowPointSelect());
        serieIn += animation(SerieInSet.getAnimation());
        serieIn += animationLimit(SerieInSet.getAnimationLimit());
        serieIn += className(SerieInSet.getClassName());
        serieIn += color(SerieInSet.getColor());
        serieIn += colorByPoint(SerieInSet.getColorByPoint());
        serieIn += connectEnds(SerieInSet.getConnectEnds());
        serieIn += connectNulls(SerieInSet.getConnectNulls());
        serieIn += cropThreshold(SerieInSet.getCropThreshold());
        serieIn += cursor(SerieInSet.getCursor());
        serieIn += dashStyle(SerieInSet.getDashStyle());
        serieIn += datas(SerieInSet.getData());
        serieIn += dataLabels(SerieInSet.getDataLabels(), isFirst(serieIn));
        serieIn += description(SerieInSet.getDescription());
        serieIn += enableMouseTracking(SerieInSet.getEnableMouseTracking());
        serieIn += events(SerieInSet.getEvents(), isFirst(serieIn));
        serieIn += getExtremesFromAll(SerieInSet.getGetExtremesFromAll());
        serieIn += id(SerieInSet.getId());
        serieIn += index(SerieInSet.getIndex());
        serieIn += keys(SerieInSet.getKeys());
        serieIn += legendIndex(SerieInSet.getLegendIndex());
        serieIn += lineWidth(SerieInSet.getLineWidth());
        serieIn += linecap(SerieInSet.getLinecap());
        serieIn += linkedTo(SerieInSet.getLinkedTo());
        serieIn += marker(SerieInSet.getMarker(), isFirst(serieIn));
        serieIn += name(SerieInSet.getName());
        serieIn += negativeColor(SerieInSet.getNegativeColor());
        serieIn += point(SerieInSet.getPoint(), isFirst(serieIn));
        serieIn += pointInterval(SerieInSet.getPointInterval());
        serieIn += pointIntervalUnit(SerieInSet.getPointIntervalUnit());
        serieIn += pointPlacement(SerieInSet.getPointPlacement());
        serieIn += pointStart(SerieInSet.getPointStart());
        serieIn += selected(SerieInSet.getSelected());
        serieIn += shadow(SerieInSet.getShadow());
        serieIn += showCheckbox(SerieInSet.getShowCheckbox());
        serieIn += showInLegend(SerieInSet.getShowInLegend());
        serieIn += softThreshold(SerieInSet.getSoftThreshold());
        serieIn += stack(SerieInSet.getStack());
        serieIn += stacking(SerieInSet.getStacking());
        serieIn += states(SerieInSet.getStates(), isFirst(serieIn));
        serieIn += step(SerieInSet.getStep());
        serieIn += stickyTracking(SerieInSet.getStickyTracking());
        serieIn += threshold(SerieInSet.getThreshold());
        serieIn += tooltip(SerieInSet.getTooltip(), isFirst(serieIn));
        serieIn += turboThreshold(SerieInSet.getTurboThreshold());
        serieIn += visible(SerieInSet.getVisible());
        serieIn += xAxis(SerieInSet.getxAxis());
        serieIn += yAxis(SerieInSet.getyAxis());
        serieIn += zIndex(SerieInSet.getzIndex());
        serieIn += zoneAxis(SerieInSet.getZoneAxis());
        serieIn += zones(SerieInSet.getZones(), isFirst(serieIn));

        serieIn = close(serieIn);
        return serieIn;
    }

    static public String renderer(SerieIn SerieInSet) {
        return renderer(SerieInSet, Boolean.FALSE);
    }

    static public String open(Boolean first) {
        return first ? "line:{" : ",line:{";
    }

    static public String open(Boolean first, ChartType type) {
        return first ? "" + type + ":{" : "," + type + ":{";
    }

    static public String close(String serieIn) {
        if (serieIn.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        serieIn = serieIn.substring(0, serieIn.length() - 1);
        serieIn += "}";
        return serieIn;
    }

    static public Boolean isFirst(String str) {
        return str.endsWith(",") || str.endsWith("{");
    }

    static public String type(ChartType type) {
        if (type != null) {
            return "type:'" + type + "',";
        }
        return "";
    }

    static public String allowPointSelect(Boolean allowPointSelect) {
        if (allowPointSelect != null) {
            return "allowPointSelect:" + allowPointSelect + ",";
        }
        return "";
    }

    static public String animation(Boolean animation) {
        if (animation != null) {
            return "animation:" + animation + ",";
        }
        return "";
    }

    static public String animationLimit(Integer animationLimit) {
        if (animationLimit != null) {
            return "animationLimit:" + animationLimit + ",";
        }
        return "";
    }

    static public String className(String className) {
        if (className != null) {
            return "className:'" + className + "',";
        }
        return "";
    }

    static public String color(String color) {
        if (color != null) {
            return "color:'" + color + "',";
        }
        return "";
    }

    static public String colorByPoint(Boolean colorByPoint) {
        if (colorByPoint != null) {
            return "colorByPoint:" + colorByPoint + ",";
        }
        return "";
    }

    
    static public String connectEnds(Boolean connectEnds) {
        if (connectEnds != null) {
            return "connectEnds:" + connectEnds + ",";
        }
        return "";
    }

    static public String connectNulls(Boolean connectNulls) {
        if (connectNulls != null) {
            return "connectNulls:" + connectNulls + ",";
        }
        return "";
    }

    static public String cropThreshold(Integer cropThreshold) {
        if (cropThreshold != null) {
            return "cropThreshold:" + cropThreshold + ",";
        }
        return "";
    }

    static public String cursor(String cursor) {
        if (cursor != null) {
            return "cursor:'" + cursor + "',";
        }
        return "";
    }

    static public String dashStyle(DashStyle dashStyle) {
        if (dashStyle != null) {
            return "dashStyle:'" + dashStyle + "',";
        }
        return "";
    }

    static public String datas(List<Data> datas) {
        if (datas != null) {
            String str = "";
            for (Data d : datas) {
                String tmp = DataRenderer.renderer(d, true);
                str += tmp + (!tmp.isEmpty() ? "," : "");
            }
            return str;
        }
        return "";
    }

//    static public String data(Data data, Boolean first) {
//        if (data != null) {
//            String tmp = DataRenderer.renderer(data, first);
//            return tmp + (!tmp.isEmpty() ? "," : "");
//        }
//        return "";
//    }

    static public String dataLabels(DataLabels dataLabels, Boolean first) {
        if (dataLabels != null) {
            String tmp = DataLabelsRenderer.renderer(dataLabels, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static public String description(String description) {
        if (description != null) {
            return "description:'" + description + "',";
        }
        return "";
    }

    static public String enableMouseTracking(Boolean enableMouseTracking) {
        if (enableMouseTracking != null) {
            return "enableMouseTracking:" + enableMouseTracking + ",";
        }
        return "";
    }

    static public String events(Events events, Boolean first) {
        if (events != null) {
            String tmp = EventsRenderer.renderer(events, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static public String getExtremesFromAll(Boolean getExtremesFromAll) {
        if (getExtremesFromAll != null) {
            return "getExtremesFromAll:" + getExtremesFromAll + ",";
        }
        return "";
    }

    static public String id(String id) {
        if (id != null) {
            return "id:'" + id + "',";
        }
        return "";
    }

    static public String index(Integer index) {
        if (index != null) {
            return "index:" + index + ",";
        }
        return "";
    }

    static public String keys(List<String> keys) {
        if (keys != null && !keys.isEmpty()) {
            String str = "keys:['" + keys.get(0) + "'";
            for (int i = 1; i < keys.size(); i++) {
                str += ",'" + keys.get(i) + "'";
            }
            str += "],";
            return str;
        }
        return "";
    }

    static public String legendIndex(Integer legendIndex) {
        if (legendIndex != null) {
            return "legendIndex:" + legendIndex + ",";
        }
        return "";
    }

    static public String lineWidth(Integer lineWidth) {
        if (lineWidth != null) {
            return "lineWidth:" + lineWidth + ",";
        }
        return "";
    }

    static public String linecap(String linecap) {
        if (linecap != null) {
            return "linecap:'" + linecap + "',";
        }
        return "";
    }

    static public String linkedTo(Integer linkedTo) {
        if (linkedTo != null) {
            return "linkedTo:" + linkedTo + ",";
        }
        return "";
    }

    static public String marker(Marker marker, Boolean first) {
        if (marker != null) {
            String tmp = MarkerRenderer.renderer(marker, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static public String name(String name) {
        if (name != null) {
            return "name:'" + name + "',";
        }
        return "";
    }

    static public String negativeColor(String negativeColor) {
        if (negativeColor != null) {
            return "negativeColor:'" + negativeColor + "',";
        }
        return "";
    }

    static public String point(Point point, Boolean first) {
        if (point != null) {
            String tmp = PointRenderer.renderer(point, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static public String pointInterval(Integer pointInterval) {
        if (pointInterval != null) {
            return "pointInterval:" + pointInterval + ",";
        }
        return "";
    }

    static public String pointIntervalUnit(String pointIntervalUnit) {
        if (pointIntervalUnit != null) {
            return "pointIntervalUnit:'" + pointIntervalUnit + "',";
        }
        return "";
    }

    static public String pointPlacement(String pointPlacement) {
        if (pointPlacement != null) {
            return "pointPlacement:'" + pointPlacement + "',";
        }
        return "";
    }

    static public String pointStart(Integer pointStart) {
        if (pointStart != null) {
            return "pointStart:" + pointStart + ",";
        }
        return "";
    }

    static public String selected(Boolean selected) {
        if (selected != null) {
            return "selected:" + selected + ",";
        }
        return "";
    }

    static public String shadow(Boolean shadow) {
        if (shadow != null) {
            return "shadow:" + shadow + ",";
        }
        return "";
    }

    static public String showCheckbox(Boolean showCheckbox) {
        if (showCheckbox != null) {
            return "showCheckbox:" + showCheckbox + ",";
        }
        return "";
    }

    static public String showInLegend(Boolean showInLegend) {
        if (showInLegend != null) {
            return "showInLegend:" + showInLegend + ",";
        }
        return "";
    }

    static public String softThreshold(Boolean softThreshold) {
        if (softThreshold != null) {
            return "softThreshold:" + softThreshold + ",";
        }
        return "";
    }

    static public String stack(String stack) {
        if (stack != null) {
            return "stack:'" + stack + "',";
        }
        return "";
    }

    static public String stacking(String stacking) {
        if (stacking != null) {
            return "stacking:'" + stacking + "',";
        }
        return "";
    }

    static public String states(States states, Boolean first) {
        if (states != null) {
            String tmp = StatesRenderer.renderer(states, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static public String step(Boolean step) {
        if (step != null) {
            return "step:" + step + ",";
        }
        return "";
    }

    static public String stickyTracking(Boolean stickyTracking) {
        if (stickyTracking != null) {
            return "stickyTracking:" + stickyTracking + ",";
        }
        return "";
    }

    static public String threshold(Integer threshold) {
        if (threshold != null) {
            return "threshold:" + threshold + ",";
        }
        return "";
    }

    static public String tooltip(ToolTip tooltip, Boolean first) {
        if (tooltip != null) {
            String tmp = ToolTipRenderer.renderer(tooltip, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static public String turboThreshold(Integer turboThreshold) {
        if (turboThreshold != null) {
            return "turboThreshold:" + turboThreshold + ",";
        }
        return "";
    }

    static public String visible(Boolean visible) {
        if (visible != null) {
            return "visible:" + visible + ",";
        }
        return "";
    }

    static public String xAxis(Integer xAxis) {
        if (xAxis != null) {
            return "xAxis:" + xAxis + ",";
        }
        return "";
    }

    static public String yAxis(Integer yAxis) {
        if (yAxis != null) {
            return "yAxis:" + yAxis + ",";
        }
        return "";
    }

    static public String zIndex(Integer zIndex) {
        if (zIndex != null) {
            return "zIndex:" + zIndex + ",";
        }
        return "";
    }

    static public String zoneAxis(String zoneAxis) {
        if (zoneAxis != null) {
            return "zoneAxis:'" + zoneAxis + "',";
        }
        return "";
    }

    static public String zones(Zones zones, Boolean first) {
        if (zones != null) {
            String tmp = ZonesRenderer.renderer(zones, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

}
