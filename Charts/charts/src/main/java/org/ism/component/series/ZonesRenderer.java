/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.series;


import org.ism.model.properties.DashStyle;
import org.ism.model.series.Zones;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class ZonesRenderer {

    static public String renderer(Zones zonesSet, Boolean first) {
        String zones = "";
        // Exclude Null pointer
        if (zonesSet == null) {
            //Util.out("Alarm *** ZonesRenderer : ", "zonesSet is null, return empty string");
            return zones;
        }

        zones += open(first);
        zones += className(zonesSet.getClassName());
        zones += color(zonesSet.getColor());
        zones += dashStyle(zonesSet.getDashStyle());
        zones += fillColor(zonesSet.getFillColor());
        zones += value(zonesSet.getValue());

        zones = close(zones);
        return zones;
    }

    static public String renderer(Zones zonesSet) {
        return renderer(zonesSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "zones:{" : ",zones:{";
    }

    static private String close(String zones) {
        if (zones.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        zones = zones.substring(0, zones.length() - 1);
        zones += "}";
        return zones;
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

    static private String dashStyle(DashStyle dashStyle) {
        if (dashStyle != null) {
            return "dashStyle:'" + dashStyle + "',";
        }
        return "";
    }

    static private String fillColor(String fillColor) {
        if (fillColor != null) {
            return "fillColor:'" + fillColor + "',";
        }
        return "";
    }

    static private String value(Integer value) {
        if (value != null) {
            return "value:" + value + ",";
        }
        return "";
    }

}
