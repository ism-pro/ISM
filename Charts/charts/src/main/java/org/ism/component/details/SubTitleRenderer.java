/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.details;

import org.ism.model.details.SubTitle;
import org.ism.model.properties.Align;

/**
 *
 * @author r.hendrick
 */
public class SubTitleRenderer {

    static public String renderer(SubTitle subTitleSet, Boolean first) {
        String subTitle = "";
        // Exclude Null pointer
        if (subTitleSet == null) {
            //Util.out("Alarm *** SubTitleRenderer : ", "subTitleSet is null, return empty string");
            return subTitle;
        }

        subTitle += open(first);
        subTitle += align(subTitleSet.getAlign());
        subTitle += floating(subTitleSet.getFloating());
        subTitle += style(subTitleSet.getStyle());
        subTitle += text(subTitleSet.getText());
        subTitle += useHTML(subTitleSet.getUseHTML());
        subTitle += verticalAlign(subTitleSet.getVerticalAlign());
        subTitle += widthAdjust(subTitleSet.getWidthAdjust());
        subTitle += x(subTitleSet.getX());
        subTitle += y(subTitleSet.getY());

        subTitle = close(subTitle);
        return subTitle;
    }

    static public String renderer(SubTitle legendSet) {
        return renderer(legendSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "subtitle:{" : ",subtitle:{";
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
