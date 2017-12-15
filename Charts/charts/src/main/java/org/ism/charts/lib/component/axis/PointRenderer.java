/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.axis;

import org.ism.charts.lib.component.properties.EventsRenderer;
import org.ism.charts.lib.model.axis.Point;
import org.ism.charts.lib.model.properties.Events;
import org.ism.charts.lib.util.Util;

/**
 * <h1>PointRenderer</h1><br>
 * PointRenderer class
 *
 * @author r.hendrick
 *
 */
public class PointRenderer {

    static public String renderer(Point pointSet, Boolean first) {
        String point = "";
        // Exclude Null pointer
        if (pointSet == null) {
            //Util.out("Alarm *** PointRenderer : ", "pointSet is null, return empty string");
            return point;
        }

        point += open(first);
        point += events(pointSet.getEvents(), isFirst(point));
        point += x(pointSet.getX());
        point += y(pointSet.getY());

        point = close(point);
        return point;
    }

    static public String renderer(Point pointSet) {
        return renderer(pointSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "point:{" : ",point:{";
    }

    static private String close(String point) {
        if (point.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        point = point.substring(0, point.length() - 1);
        point += "}";
        return point;
    }

    static private Boolean isFirst(String point) {
        return point.endsWith(",");
    }

    static private String events(Events events, Boolean first) {
        if (events != null) {
            String tmp = EventsRenderer.renderer(events, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
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
