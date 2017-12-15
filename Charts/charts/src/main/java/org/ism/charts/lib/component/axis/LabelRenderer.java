/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.axis;

import org.ism.charts.lib.model.axis.Label;
import org.ism.charts.lib.model.properties.Align;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class LabelRenderer {

    static public String renderer(Label labelSet, Boolean first) {
        String labels = "";
        // Exclude Null pointer
        if (labelSet == null) {
            //Util.out("Alarm *** LabelRenderer : ", "labelSet is null, return empty string");
            return labels;
        }

        labels += open(first);
        labels += align(labelSet.getAlign());
        labels += rotation(labelSet.getRotation());
        labels += style(labelSet.getStyle());
        labels += text(labelSet.getText());
        labels += textAlign(labelSet.getTextAlign());
        labels += useHTML(labelSet.getUseHTML());
        labels += verticalAlign(labelSet.getVerticalAlign());
        labels += x(labelSet.getX());
        labels += y(labelSet.getY());

        labels = close(labels);
        return labels;
    }

    static public String renderer(Label labelSet) {
        return renderer(labelSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "label:{" : ",label:{";
    }

    static private String close(String labels) {
        if (labels.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        labels = labels.substring(0, labels.length() - 1);
        labels += "}";
        return labels;
    }

    static private String align(Align align) {
        if (align != null) {
            return "align:'" + align + "',";
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
        if (style != null && style.trim().length() >= 0) {
            return "style:'" + style + "',";
        }
        return "";
    }

    static private String text(String text) {
        if (text != null && text.trim().length() >= 0) {
            return "text:'" + text + "',";
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
        if (verticalAlign != null && verticalAlign != Align.TOP) {
            return "textAlign:'" + verticalAlign + "',";
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
