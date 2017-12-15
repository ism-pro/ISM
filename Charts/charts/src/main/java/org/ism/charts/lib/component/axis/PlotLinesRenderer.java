/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.axis;

import org.ism.charts.lib.model.axis.Label;
import org.ism.charts.lib.model.axis.PlotLines;
import org.ism.charts.lib.model.properties.DashStyle;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class PlotLinesRenderer {

    static public String renderer(PlotLines plotLinesSet, Boolean first) {
        String plotLines = "";
        // Exclude Null pointer
        if (plotLinesSet == null) {
            //Util.out("Alarm *** PlotLinesRanderer : ", "plotLinesSet is null, return empty string");
            return plotLines;
        }

        plotLines += open(first);
        plotLines += className(plotLinesSet.getClassName());
        plotLines += color(plotLinesSet.getColor());
        plotLines += dashStyle(plotLinesSet.getDashStyle());
        plotLines += id(plotLinesSet.getId());
        plotLines += label(plotLinesSet.getLabel(), isFirst(plotLines));
        plotLines += value(plotLinesSet.getValue());
        plotLines += width(plotLinesSet.getWidth());
        plotLines += zIndex(plotLinesSet.getzIndex());

        plotLines = close(plotLines);
        return plotLines;
    }

    static public String renderer(PlotLines plotLinesSet) {
        return renderer(plotLinesSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "plotLines:[{" : ",plotLines:[{";
    }

    static private String close(String plotLines) {
        if (plotLines.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        plotLines = plotLines.substring(0, plotLines.length() - 1);
        plotLines += "}]";
        return plotLines;
    }

    static private Boolean isFirst(String plotLines) {
        return plotLines.endsWith(",") || plotLines.endsWith("{") || plotLines.endsWith("[");
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

    static private String dashStyle(DashStyle dashStyle) {
        if (dashStyle != null && dashStyle != DashStyle.SOLID) {
            return "dashStyle:'" + dashStyle + "',";
        }
        return "";
    }

    static private String id(String id) {
        if (id != null && id.trim().length() >= 0) {
            return "id:'" + id + "',";
        }
        return "";
    }

    static private String label(Label label, Boolean first) {
        if (label != null) {
            String tmp = LabelRenderer.renderer(label, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String value(Integer value) {
        if (value != null) {
            return "value:" + value + ",";
        }
        return "";
    }

    static private String width(Integer width) {
        if (width != null) {
            return "width:" + width + ",";
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
