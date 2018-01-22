/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.properties;

import org.ism.model.properties.Select;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class SelectRenderer {

    static public String renderer(Select selectSet, Boolean first) {
        String select = "";
        // Exclude Null pointer
        if (selectSet == null) {
            //Util.out("Alarm *** SelectRenderer : ", "selectSet is null, return empty string");
            return select;
        }

        select += open(first);
        select += enabled(selectSet.getEnabled());
        select += fillColor(selectSet.getFillColor());
        select += lineColor(selectSet.getLineColor());
        select += lineWidth(selectSet.getLineWidth());
        select += radius(selectSet.getRadius());

        select = close(select);
        return select;
    }

    static public String renderer(Select selectSet) {
        return renderer(selectSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "select:{" : ",select:{";
    }

    static private String close(String select) {
        if (select.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        select = select.substring(0, select.length() - 1);
        select += "}";
        return select;
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

}
