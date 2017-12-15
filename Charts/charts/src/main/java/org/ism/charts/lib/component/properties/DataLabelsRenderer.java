/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.properties;

import org.ism.charts.lib.model.properties.Align;
import org.ism.charts.lib.model.properties.DataLabels;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class DataLabelsRenderer {

    static public String renderer(DataLabels dataLabelsSet, Boolean first) {
        String dataLabels = "";
        // Exclude Null pointer
        if (dataLabelsSet == null) {
            //Util.out("Alarm *** DataLabelsRenderer : ", "dataLabelsSet is null, return empty string");
            return dataLabels;
        }

        dataLabels += open(first);
        dataLabels += align(dataLabelsSet.getAlign());
        dataLabels += allowOverlap(dataLabelsSet.getAllowOverlap());
        dataLabels += backgroundColor(dataLabelsSet.getBackgroundColor());
        dataLabels += borderColor(dataLabelsSet.getBorderColor());
        dataLabels += borderRadius(dataLabelsSet.getBorderRadius());
        dataLabels += borderWidth(dataLabelsSet.getBorderWidth());
        dataLabels += className(dataLabelsSet.getClassName());
        dataLabels += color(dataLabelsSet.getColor());
        dataLabels += connectorColor(dataLabelsSet.getConnectorColor());
        dataLabels += connectorPadding(dataLabelsSet.getConnectorPadding());
        dataLabels += connectorWidth(dataLabelsSet.getConnectorWidth());
        dataLabels += crop(dataLabelsSet.getCrop());
        dataLabels += defer(dataLabelsSet.getDefer());
        dataLabels += distance(dataLabelsSet.getDistance());
        dataLabels += enabled(dataLabelsSet.getEnabled());
        dataLabels += format(dataLabelsSet.getFormat());
        dataLabels += formatter(dataLabelsSet.getFormatter());
        dataLabels += inside(dataLabelsSet.getInside());
        dataLabels += overflow(dataLabelsSet.getOverflow());
        dataLabels += padding(dataLabelsSet.getPadding());
        dataLabels += reserveSpace(dataLabelsSet.getReserveSpace());
        dataLabels += rotation(dataLabelsSet.getRotation());
        dataLabels += shadow(dataLabelsSet.getShadow());
        dataLabels += shape(dataLabelsSet.getShape());
        dataLabels += softConnector(dataLabelsSet.getSoftConnector());
        dataLabels += style(dataLabelsSet.getStyle());
        dataLabels += useHTML(dataLabelsSet.getUseHTML());
        dataLabels += verticalAlign(dataLabelsSet.getVerticalAlign());
        dataLabels += x(dataLabelsSet.getX());
        dataLabels += y(dataLabelsSet.getY());
        dataLabels += zIndex(dataLabelsSet.getzIndex());

        dataLabels = close(dataLabels);
        return dataLabels;
    }

    static public String renderer(DataLabels dataLabelsSet) {
        return renderer(dataLabelsSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "dataLabels:{" : ",dataLabels:{";
    }

    static private String close(String dataLabels) {
        if (dataLabels.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        dataLabels = dataLabels.substring(0, dataLabels.length() - 1);
        dataLabels += "}";
        return dataLabels;
    }

    static private Boolean isFirst(String point) {
        return point.endsWith(",");
    }

    static private String align(Align align) {
        if (align != null) {
            return "align:'" + align + "',";
        }
        return "";
    }

    static private String allowOverlap(Boolean allowOverlap) {
        if (allowOverlap != null) {
            return "allowOverlap:" + allowOverlap + ",";
        }
        return "";
    }

    static private String backgroundColor(String backgroundColor) {
        if (backgroundColor != null) {
            return "backgroundColor:'" + backgroundColor + "',";
        }
        return "";
    }

    static private String borderColor(String borderColor) {
        if (borderColor != null) {
            return "borderColor:'" + borderColor + "',";
        }
        return "";
    }

    static private String borderRadius(Integer borderRadius) {
        if (borderRadius != null) {
            return "borderRadius:" + borderRadius + ",";
        }
        return "";
    }

    static private String borderWidth(Integer borderWidth) {
        if (borderWidth != null) {
            return "borderWidth:" + borderWidth + ",";
        }
        return "";
    }

    static private String className(String className) {
        if (className != null) {
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

    static private String connectorColor(String connectorColor) {
        if (connectorColor != null) {
            return "connectorColor:'" + connectorColor + "',";
        }
        return "";
    }

    static private String connectorPadding(Integer connectorPadding) {
        if (connectorPadding != null) {
            return "connectorPadding:" + connectorPadding + ",";
        }
        return "";
    }

    static private String connectorWidth(Integer connectorWidth) {
        if (connectorWidth != null) {
            return "connectorWidth:" + connectorWidth + ",";
        }
        return "";
    }

    static private String crop(Boolean crop) {
        if (crop != null) {
            return "crop:" + crop + ",";
        }
        return "";
    }

    static private String defer(Boolean defer) {
        if (defer != null) {
            return "defer:" + defer + ",";
        }
        return "";
    }

    static private String distance(Integer distance) {
        if (distance != null) {
            return "distance:" + distance + ",";
        }
        return "";
    }

    static private String enabled(Boolean enabled) {
        if (enabled != null) {
            return "enabled:" + enabled + ",";
        }
        return "";
    }

    static private String format(String format) {
        if (format != null) {
            return "format:'" + format + "',";
        }
        return "";
    }

    static private String formatter(String formatter) {
        if (formatter != null) {
            return "formatter:'" + formatter + "',";
        }
        return "";
    }

    static private String inside(Boolean inside) {
        if (inside != null) {
            return "inside:" + inside + ",";
        }
        return "";
    }

    static private String overflow(String overflow) {
        if (overflow != null) {
            return "overflow:'" + overflow + "',";
        }
        return "";
    }

    static private String padding(Integer padding) {
        if (padding != null) {
            return "padding:" + padding + ",";
        }
        return "";
    }

    static private String reserveSpace(Boolean reserveSpace) {
        if (reserveSpace != null) {
            return "reserveSpace:" + reserveSpace + ",";
        }
        return "";
    }

    static private String rotation(Integer rotation) {
        if (rotation != null) {
            return "rotation:" + rotation + ",";
        }
        return "";
    }

    static private String shadow(Boolean shadow) {
        if (shadow != null) {
            return "shadow:" + shadow + ",";
        }
        return "";
    }

    static private String shape(String shape) {
        if (shape != null) {
            return "shape:'" + shape + "',";
        }
        return "";
    }

    static private String softConnector(Boolean softConnector) {
        if (softConnector != null) {
            return "softConnector:" + softConnector + ",";
        }
        return "";
    }

    static private String style(String style) {
        if (style != null) {
            return "style:" + style + ",";
        }
        return "";
    }

    static private String useHTML(Boolean useHTML) {
        if (useHTML != null) {
            return "useHTML:" + useHTML + ",";
        }
        return "";
    }

    static private String verticalAlign(Align verticalAlign) {
        if (verticalAlign != null) {
            return "verticalAlign:'" + verticalAlign + "',";
        }
        return "";
    }

    static private String x(Integer x) {
        if (x != null) {
            return "x:" + x + ",";
        }
        return "";
    }

    static private String y(Integer y) {
        if (y != null) {
            return "y:" + y + ",";
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
