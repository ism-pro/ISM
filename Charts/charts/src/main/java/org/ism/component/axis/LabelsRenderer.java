/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.axis;

import java.util.List;
import org.ism.model.axis.Labels;
import org.ism.model.properties.Align;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class LabelsRenderer {

    static public String renderer(Labels labelsSet, Boolean first) {
        String labels = "";
        // Exclude Null pointer
        if (labelsSet == null) {
            //Util.out("Alarm *** LabelsRenderer : ", "labelsSet is null, return empty string");
            return labels;
        }

        labels += open(first);
        labels += align(labelsSet.getAlign());
        labels += autoRotation(labelsSet.getAutoRotation());
        labels += autoRotationLimit(labelsSet.getAutoRotationLimit());
        labels += distance(labelsSet.getDistance());
        labels += enabled(labelsSet.getEnabled());
        labels += format(labelsSet.getFormat());
        labels += formatter(labelsSet.getFormatter());
        labels += padding(labelsSet.getPadding());
        labels += reserveSpace(labelsSet.getReserveSpace());
        labels += rotation(labelsSet.getRotation());
        labels += staggerLines(labelsSet.getStaggerLines());
        labels += step(labelsSet.getStep());
        labels += style(labelsSet.getStyle());
        labels += useHTML(labelsSet.getUseHTML());
        labels += x(labelsSet.getX());
        labels += y(labelsSet.getY());
        labels += zIndex(labelsSet.getzIndex());

        labels = close(labels);
        return labels;
    }

    static public String renderer(Labels labelsSet) {
        return renderer(labelsSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "labels:{" : ",labels:{";
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

    static private String autoRotation(List<Integer> autoRotation) {
        if (autoRotation != null && !autoRotation.isEmpty()) {
            String str = "autoRotation:[" + autoRotation.get(0);
            for (int i = 1; i < autoRotation.size(); i++) {
                str += "," + autoRotation.get(i);
            }
            str += "],";
            return str;
        }
        return "";
    }

    static private String autoRotationLimit(Integer autoRotationLimit) {
        if (autoRotationLimit != null && autoRotationLimit != 80) {
            return "autoRotationLimit:" + autoRotationLimit + ",";
        }
        return "";
    }

    static private String distance(Integer distance) {
        if (distance != null && distance != 15) {
            return "distance:" + distance + ",";
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

    static private String padding(Integer padding) {
        if (padding != null && padding != 5) {
            return "padding:" + padding + ",";
        }
        return "";
    }

    static private String reserveSpace(Boolean reserveSpace) {
        if (reserveSpace != null && reserveSpace != true) {
            return "reserveSpace:" + reserveSpace + ",";
        }
        return "";
    }

    static private String rotation(Integer rotation) {
        if (rotation != null && rotation != 0) {
            return "rotation:" + rotation + ",";
        }
        return "";
    }

    static private String staggerLines(Integer staggerLines) {
        if (staggerLines != null) {
            return "staggerLines:" + staggerLines + ",";
        }
        return "";
    }

    static private String step(Integer step) {
        if (step != null) {
            return "step:" + step + ",";
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

    static private String useHTML(Boolean useHTML) {
        if (useHTML != null && useHTML != false) {
            return "useHTML:" + useHTML + ",";
        }
        return "";
    }

    static private String x(Integer x) {
        if (x != null && x != 0) {
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
        if (zIndex != null && zIndex != 7) {
            return "zIndex:" + zIndex + ",";
        }
        return "";
    }

}
