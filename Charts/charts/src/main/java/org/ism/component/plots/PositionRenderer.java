/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.plots;

import org.ism.util.Util;
import org.ism.model.properties.Align;
import org.ism.model.axis.Position;


/**
 * <h1>PositionRenderer</h1>
 *
 * <h2>Description</h2>
 *
 *
 * @author r.hendrick
 */
public class PositionRenderer {

    /**
     * <h3>renderer</h3>
     * This is the main method which allow to convert a PositionRenderer model
     * to a hightchart encoded script
     *
     * This method return empty string if no data set
     *
     * @param position is model of position allowing to render it data
     * @param first define if has to start with ","
     * @return The output is empty string for null or nothing used
     */
    static public String renderer(Position position, Boolean first) {
        String pos = "";
        // Exclude Null pointer
        if (position == null) {
            //Util.out("Alarm *** Position : ", "position is null, return empty string");
            return pos;
        }

        // Check used
        if (!position.isUnused()) {
            //Util.out("Warning *** Position : ", "position not used");
            return pos;
        }

        pos += open(first);
        pos += align(position.getAlign());
        pos += verticalAlign(position.getVerticalAlign());
        pos += x(position.getX());
        pos += y(position.getY());
        pos = close(pos);
        return pos;
    }

    /**
     * Surcharged method to auto set first
     *
     * @param position position
     * @return script encode of position
     */
    static public String renderer(Position position) {
        return renderer(position, Boolean.TRUE);
    }

    /**
     * <h3>open</h3>
     *
     * @return script to open resetZoomButton starting with ","
     */
    static private String open(Boolean first) {
        return first ? "position:{" : ",position:{";
    }

    
    /**
     * The heatmap series type. This series type is available both in Highcharts
     * and Highmaps.

     * @param alignement horizontal alignement
     * @return script encoded for align
     */
    static private String align(Align alignement) {
        if (alignement != null) {
            return "align:'" + alignement.toString() + "',";
        }
        return "";
    }

    /**
     * The vertical alignment of the button. Defaults to top.

     * @param vertical vertical align
     * @return encoded script for vertical align
     */
    static private String verticalAlign(Align vertical) {
        if (vertical != null) {
            return "verticalAlign:'" + vertical.toString() + "',";
        }
        return "";
    }

    /**
     * The horizontal offset of the button Defaults to -10.
     * 
     * @param pos_x x position
     * @return encoded script for x position
     */
    static private String x(Integer pos_x) {
        if (pos_x != null) {
            return "x:" + pos_x + ",";
        }
        return "";
    }

    /**
     * The vertical offset of the button. Defaults to 10.bbbbbbbxx
     * 
     * @param pos_y y position
     * @return encoded y position
     */
    static private String y(Integer pos_y) {
        if (pos_y != null) {
            return "y:" + pos_y + ",";
        }
        return "";
    }

    /**
     * <h3>close</h3>
     *
     * @return script to close position
     */
    static private String close(String position) {
        position = position.substring(0, position.length() - 1);
        position += "}";
        return position;
    }

}
