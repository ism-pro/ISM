/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.axis;

import org.ism.model.axis.Crosshair;
import org.ism.model.properties.DashStyle;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class CrosshairRenderer {

    static public String renderer(Crosshair crosshairSet, Boolean first) {
        String crosshair = "";
        // Exclude Null pointer
        if (crosshairSet == null) {
            //Util.out("Alarm *** CrosshairRenderer : ", "crosshairSet is null, return empty string");
            return crosshair;
        }

        crosshair += open(first);
        crosshair += className(crosshairSet.getClassName());
        crosshair += color(crosshairSet.getColor());
        crosshair += dashStyle(crosshairSet.getDashStyle());
        crosshair += snap(crosshairSet.getSnap());
        crosshair += width(crosshairSet.getWidth());
        crosshair += zIndex(crosshairSet.getzIndex());

        crosshair = close(crosshair);
        return crosshair;
    }

    static public String renderer(Crosshair crosshairSet) {
        return renderer(crosshairSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "crosshair:{" : ",crosshair:{";
    }

    static private String close(String crosshair) {
        if (crosshair.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        crosshair = crosshair.substring(0, crosshair.length() - 1);
        crosshair += "}";
        return crosshair;
    }

    static private String className(String className) {
        if (className != null && className.trim().length() >= 0) {
            return "className:'" + className + "',";
        }
        return "";
    }

    static private String color(String color) {
        if (color != null && !color.matches("#cccccc")) {
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

    static private String snap(Boolean snap) {
        if (snap != null && snap != true) {
            return "snap:" + snap + ",";
        }
        return "";
    }

    static private String width(Integer width) {
        if (width != null && width != 1) {
            return "width:" + width + ",";
        }
        return "";
    }

    static private String zIndex(Integer zIndex) {
        if (zIndex != null && zIndex != 2) {
            return "zIndex:" + zIndex + ",";
        }
        return "";
    }

}
