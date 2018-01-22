/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.axis;


import org.ism.model.axis.StackLabels;
import org.ism.model.properties.Align;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class StackLabelsRenderer {

    static public String renderer(StackLabels stackLablesSet, Boolean first) {
        String stackLabels = "";
        // Exclude Null pointer
        if (stackLablesSet == null) {
            //Util.out("Alarm *** LabelsRenderer : ", "stackLablesSet is null, return empty string");
            return stackLabels;
        }

        stackLabels += open(first);
        stackLabels += align(stackLablesSet.getAlign());
        stackLabels += enabled(stackLablesSet.getEnabled());
        stackLabels += format(stackLablesSet.getFormat());
        stackLabels += formatter(stackLablesSet.getFormatter());
        stackLabels += rotation(stackLablesSet.getRotation());
        stackLabels += style(stackLablesSet.getStyle());
        stackLabels += textAlign(stackLablesSet.getTextAlign());
        stackLabels += useHTML(stackLablesSet.getUseHTML());
        stackLabels += verticalAlign(stackLablesSet.getVerticalAlign());
        stackLabels += x(stackLablesSet.getX());
        stackLabels += y(stackLablesSet.getY());

        stackLabels = close(stackLabels);
        return stackLabels;
    }

    static public String renderer(StackLabels stackLablesSet) {
        return renderer(stackLablesSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "stackLabels:{" : ",stackLabels:{";
    }

    static private String close(String stackLabels) {
        if (stackLabels.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        stackLabels = stackLabels.substring(0, stackLabels.length() - 1);
        stackLabels += "}";
        return stackLabels;
    }

    static private String align(Align align) {
        if (align != null) {
            return "align:'" + align + "',";
        }
        return "";
    }

    static private String enabled(Boolean enabled) {
        if (enabled != null && enabled != true) {
            return "enabled:" + enabled + ",";
        }
        return "";
    }

    static private String format(String format) {
        //if (format != null && format.trim().length() >= 0 && !format.matches("{value}")) {
        if (format != null && format.trim().length() >= 0) {
            return "format:'" + format + "',";
        }
        return "";
    }

    static private String formatter(String formatter) {
        //if (formatter != null && formatter.trim().length() >= 0 && !formatter.matches("function(){return this.value;}")) {
        if (formatter != null && formatter.trim().length() >= 0) {
            return "formatter:'" + formatter + "',";
        }
        return "";
    }

    static private String rotation(Integer rotation) {
        if (rotation != null && rotation != 0) {
            return "rotation:" + rotation + ",";
        }
        return "";
    }

    static private String style(String style) {
        //if (style != null && style.trim().length() >= 0 && !style.matches("{\"color\":\"#666666\",\"cursor\":\"default\",\"fontSize\":\"11px\"}")) {
        if (style != null && style.trim().length() >= 0) {
            return "style:'" + style + "',";
        }
        return "";
    }

    static private String textAlign(Align textAlign) {
        if (textAlign != null) {
            return "textAlign:'" + textAlign + "',";
        }
        return "";
    }

    static private String useHTML(Boolean useHTML) {
        if (useHTML != null && useHTML != false) {
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

}
