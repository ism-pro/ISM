/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.properties;

import org.ism.charts.lib.model.properties.Align;
import org.ism.charts.lib.model.properties.Navigation;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class NavigationRenderer {

    static public String renderer(Navigation navigationSet, Boolean first) {
        String navigation = "";
        // Exclude Null pointer
        if (navigationSet == null) {
            //Util.out("Alarm *** NavigationRenderer : ", "navigationSet is null, return empty string");
            return navigation;
        }

        navigation += open(first);
        navigation += activeColor(navigationSet.getActiveColor());
        navigation += animation(navigationSet.getAnimation());
        navigation += arrowSize(navigationSet.getArrowSize());
        navigation += enabled(navigationSet.getEnabled());
        navigation += inactiveColor(navigationSet.getInactiveColor());
        navigation += style(navigationSet.getStyle());

        navigation = close(navigation);
        return navigation;
    }

    static public String renderer(Navigation navigationSet) {
        return renderer(navigationSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "navigation:{" : ",navigation:{";
    }

    static private String close(String navigation) {
        if (navigation.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        navigation = navigation.substring(0, navigation.length() - 1);
        navigation += "}";
        return navigation;
    }

    static private Boolean isFirst(String point) {
        return point.endsWith(",");
    }

    static private String activeColor(String activeColor) {
        if (activeColor != null) {
            return "activeColor:'" + activeColor + "',";
        }
        return "";
    }

    static private String animation(Boolean animation) {
        if (animation != null) {
            return "animation:" + animation + ",";
        }
        return "";
    }

    static private String arrowSize(Integer arrowSize) {
        if (arrowSize != null) {
            return "arrowSize:" + arrowSize + ",";
        }
        return "";
    }

    static private String enabled(Boolean enabled) {
        if (enabled != null) {
            return "enabled:" + enabled + ",";
        }
        return "";
    }

    static private String inactiveColor(String inactiveColor) {
        if (inactiveColor != null) {
            return "inactiveColor:'" + inactiveColor + "',";
        }
        return "";
    }

    static private String style(String style) {
        if (style != null) {
            return "style:'" + style + "',";
        }
        return "";
    }

}
