/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.axis;

import org.ism.charts.lib.model.axis.Label;
import org.ism.charts.lib.model.axis.PlotBands;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class PlotBandsRenderer {

    static public String renderer(PlotBands plotBandsSet, Boolean first) {
        String plotBands = "";
        // Exclude Null pointer
        if (plotBandsSet == null) {
            //Util.out("Alarm *** PlotBandsRanderer : ", "plotBandsSet is null, return empty string");
            return plotBands;
        }

        plotBands += open(first);
        plotBands += borderColor(plotBandsSet.getBorderColor());
        plotBands += borderWidth(plotBandsSet.getBorderWidth());
        plotBands += className(plotBandsSet.getClassName());
        plotBands += color(plotBandsSet.getColor());
        plotBands += from(plotBandsSet.getFrom());
        plotBands += id(plotBandsSet.getId());
        plotBands += label(plotBandsSet.getLabel());
        plotBands += to(plotBandsSet.getTo());
        plotBands += zIndex(plotBandsSet.getzIndex());

        plotBands = close(plotBands);
        return plotBands;
    }

    static public String renderer(PlotBands plotBandsSet) {
        return renderer(plotBandsSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "plotBands:{" : ",plotBands:{";
    }

    static private String close(String plotBands) {
        if (plotBands.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        plotBands = plotBands.substring(0, plotBands.length() - 1);
        plotBands += "}";
        return plotBands;
    }

    static private String borderColor(String borderColor) {
        if (borderColor != null && borderColor.trim().length() >= 0) {
            return "borderColor:'" + borderColor + "',";
        }
        return "";
    }

    static private String borderWidth(Integer borderWidth) {
        if (borderWidth != null && borderWidth != 0) {
            return "borderWidth:" + borderWidth + ",";
        }
        return "";
    }

    static private String className(String className) {
        if (className != null && className.trim().length() >= 0) {
            return "className:'" + className + "',";
        }
        return "";
    }

    static private String color(String color) {
        if (color != null) {
            return "color:'" + color + "',";
        }
        return "";
    }

    static private String from(Integer from) {
        if (from != null) {
            return "from:" + from + ",";
        }
        return "";
    }

    static private String id(String id) {
        if (id != null && id.trim().length() >= 0) {
            return "id:'" + id + "',";
        }
        return "";
    }

    static private String label(Label label) {
        if (label != null) {
            return LabelRenderer.renderer(label);
        }
        return "";
    }

    static private String to(Integer to) {
        if (to != null) {
            return "to:" + to + ",";
        }
        return "";
    }

    static private String zIndex(Integer zIndex) {
        if (zIndex != null) {
            return "zIndex:" + zIndex + ",";
        }
        return "";
    }

}
