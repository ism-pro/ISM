/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.properties;

import org.ism.charts.lib.model.properties.Halo;
import org.ism.charts.lib.model.properties.Hover;

/**
 *
 * @author r.hendrick
 */
public class HoverRenderer {

    static public String renderer(Hover hoverSet, Boolean first) {
        String hover = "";
        // Exclude Null pointer
        if (hoverSet == null) {
            //Util.out("Alarm *** HoverRenderer : ", "hoverSet is null, return empty string");
            return hover;
        }

        hover += open(first);
        hover += enabled(hoverSet.getEnabled());
        hover += fillColor(hoverSet.getFillColor());
        hover += halo(hoverSet.getHalo(), isFirst(hover));
        hover += lineColor(hoverSet.getLineColor());
        hover += lineWidth(hoverSet.getLineWidth());
        hover += lineWidthPlus(hoverSet.getLineWidthPlus());
        hover += radius(hoverSet.getRadius());
        hover += radiusPlus(hoverSet.getRadiusPlus());

        hover = close(hover);
        return hover;
    }

    static public String renderer(Hover hoverSet) {
        return renderer(hoverSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "hover:{" : ",hover:{";
    }

    static private String close(String hover) {
        if (hover.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        hover = hover.substring(0, hover.length() - 1);
        hover += "}";
        return hover;
    }

    static private Boolean isFirst(String hover) {
        return hover.endsWith(",");
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

    static private String halo(Halo halo, Boolean first) {
        if (halo != null) {
            String tmp = HaloRenderer.renderer(halo, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
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

    static private String lineWidthPlus(Integer lineWidthPlus) {
        if (lineWidthPlus != null) {
            return "lineWidthPlus:" + lineWidthPlus + ",";
        }
        return "";
    }

    static private String radius(Integer radius) {
        if (radius != null) {
            return "radius:" + radius + ",";
        }
        return "";
    }

    static private String radiusPlus(Integer radiusPlus) {
        if (radiusPlus != null) {
            return "radiusPlus:" + radiusPlus + ",";
        }
        return "";
    }

}
