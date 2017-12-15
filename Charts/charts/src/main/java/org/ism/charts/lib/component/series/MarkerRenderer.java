/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.series;

import org.ism.charts.lib.component.properties.StatesRenderer;
import org.ism.charts.lib.model.properties.States;
import org.ism.charts.lib.model.properties.Symbol;
import org.ism.charts.lib.model.series.Marker;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class MarkerRenderer {

    static public String renderer(Marker markerSet, Boolean first) {
        String marker = "";
        // Exclude Null pointer
        if (markerSet == null) {
            //Util.out("Alarm *** MarkerRenderer : ", "markerSet is null, return empty string");
            return marker;
        }

        marker += open(first);
        marker += enabled(markerSet.getEnable());
        marker += fillColor(markerSet.getFillColor());
        marker += height(markerSet.getHeight());
        marker += lineColor(markerSet.getLineColor());
        marker += lineWidth(markerSet.getLineWidth());
        marker += radius(markerSet.getRadius());
        marker += states(markerSet.getStates(), isFirst(marker));
        marker += symbol(markerSet.getSymbol());

        marker = close(marker);
        return marker;
    }

    static public String renderer(Marker markerSet) {
        return renderer(markerSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "marker:{" : ",marker:{";
    }

    static private String close(String marker) {
        if (marker.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        marker = marker.substring(0, marker.length() - 1);
        marker += "}";
        return marker;
    }

    static private Boolean isFirst(String states) {
        return states.endsWith(",");
    }

    static private String enabled(Boolean enabled) {
        if (enabled != null) {
            return "enabled:" + enabled + ",";
        }
        return "";
    }

    static private String fillColor(String fillColor) {
        if (fillColor != null) {
            return "fillColor:'" + fillColor + "',";
        }
        return "";
    }

    static private String height(Integer height) {
        if (height != null) {
            return "height:" + height + ",";
        }
        return "";
    }

    static private String lineColor(String lineColor) {
        if (lineColor != null) {
            return "lineColor:'" + lineColor + "',";
        }
        return "";
    }

    static private String lineWidth(Integer lineWidth) {
        if (lineWidth != null) {
            return "lineWidth:" + lineWidth + ",";
        }
        return "";
    }

    static private String radius(Integer radius) {
        if (radius != null) {
            return "radius:" + radius + ",";
        }
        return "";
    }

    static private String states(States states, Boolean first) {
        if (states != null) {
            String tmp = StatesRenderer.renderer(states, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String symbol(Symbol symbol) {
        if (symbol != null) {
            return "symbol:'" + symbol + "',";
        }
        return "";
    }

}
