/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.details;

import org.ism.charts.lib.model.details.Title;
import org.ism.charts.lib.model.properties.Align;

/**
 *
 * @author r.hendrick
 */
public class TitleRenderer {

    static public String renderer(Title titleSet, Boolean first) {
        String title = "";
        // Exclude Null pointer
        if (titleSet == null) {
            //Util.out("Alarm *** TitleRenderer : ", "titleSet is null, return empty string");
            return title;
        }

        title += open(first);
        title += align(titleSet.getAlign());
        title += floating(titleSet.getFloating());
        title += margin(titleSet.getMargin());
        title += style(titleSet.getStyle());
        title += text(titleSet.getText());
        title += useHTML(titleSet.getUseHTML());
        title += verticalAlign(titleSet.getVerticalAlign());
        title += widthAdjust(titleSet.getWidthAdjust());
        title += x(titleSet.getX());
        title += y(titleSet.getY());

        title = close(title);
        return title;
    }

    static public String renderer(Title legendSet) {
        return renderer(legendSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "title:{" : ",title:{";
    }

    static private String close(String legend) {
        if (legend.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        legend = legend.substring(0, legend.length() - 1);
        legend += "}";
        return legend;
    }

    static private Boolean isFirst(String states) {
        return states.endsWith(",");
    }

    static private String align(Align align) {
        if (align != null) {
            return "align:'" + align + "',";
        }
        return "";
    }

    static private String floating(Boolean floating) {
        if (floating != null) {
            return "floating:" + floating + ",";
        }
        return "";
    }
    
    static private String margin(Integer margin) {
        if (margin != null) {
            return "margin:" + margin + ",";
        }
        return "";
    }

    static private String style(String style) {
        if (style != null) {
            return "style:'" + style + "',";
        }
        return "";
    }
    
    static private String text(String text) {
        if (text != null) {
            return "text:'" + text + "',";
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

    static private String widthAdjust(Integer widthAdjust) {
        if (widthAdjust != null) {
            return "widthAdjust:" + widthAdjust + ",";
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

}
