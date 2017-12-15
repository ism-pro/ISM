/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.axis;

import org.ism.charts.lib.model.axis.AxisTitle;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class AxisTitleRenderer {

    /**
     * <h3>renderer</h3>
     * This is the main method which allow to convert a AxisTitle model to a
     * hightaxisTitleChart encoded script
     *
     * This method return empty string if no data set
     *
     * @param axisTitleSet is model of AxisTitle allowing to render axisTitleSet
     * data
     * @param first define if has to start with ","
     * @return The output is empty string for null or nothing used
     */
    static public String renderer(AxisTitle axisTitleSet, Boolean first) {
        String axisTitle = "";
        // Exclude Null pointer
        if (axisTitleSet == null) {
            //Util.out("Alarm *** AxisTitleRenderer : ", "axisTitleChart is null, return empty string");
            return axisTitle;
        }

        axisTitle += open(first);
        axisTitle += align(axisTitleSet.getAlign());
        axisTitle += margin(axisTitleSet.getMargin());
        axisTitle += offset(axisTitleSet.getOffset());
        axisTitle += rotation(axisTitleSet.getRotation());
        axisTitle += style(axisTitleSet.getStyle());
        axisTitle += text(axisTitleSet.getText());
        axisTitle += x(axisTitleSet.getX());
        axisTitle += y(axisTitleSet.getY());

        axisTitle = close(axisTitle);
        return axisTitle;
    }
    
    /**
     * Surcharge of renderer to auto set not first
     * @param axisTitleSet AxisTitle contain information to be renderer
     * @return 
     */
    static public String renderer(AxisTitle axisTitleSet) {
        return renderer(axisTitleSet, Boolean.FALSE);
    }

    
    
    
    
    /**
     * <h3>open</h3>
     *
     * @return script to open chart starting with ","
     */
    static private String open(Boolean first) {
        return first ? "title:{" : ",title:{";
    }

    /**
     * <h3>close</h3>
     *
     * @return script to close chart starting with
     */
    static private String close(String axisTitle) {
        if(axisTitle.length()<=open(Boolean.FALSE).length()){
            return "";
        }
        // Remove last ","
        axisTitle = axisTitle.substring(0, axisTitle.length() - 1);
        axisTitle += "}";
        return axisTitle;
    }


    static private String align(String align) {
        if (align != null && !align.matches(AxisTitle.MIDDLE)) {
            return "align:'" + align + "',";
        }
        return "";
    }

    static private String margin(Integer margin) {
        if (margin != null && margin>0) {
            return "margin:" + margin + ",";
        }
        return "";
    }
    
    static private String offset(Integer offset) {
        if (offset != null && offset>=0) {
            return "offset:" + offset + ",";
        }
        return "";
    }
    
    static private String rotation(Integer rotation) {
        if (rotation != null && rotation!=0) {
            return "rotation:" + rotation + ",";
        }
        return "";
    }
    
    static private String style(String style) {
        //if (style != null && !style.matches("{\"color\":\"#666666\"}")) {
        if (style != null && !style.contains("666666")) {
            return "style:" + style + ",";
        }
        return "";
    }
    
    static private String text(String text) {
        if (text != null && text.trim().length()>0) {
            return "text:'" + text + "',";
        }
        return "";
    }
    
    static private String x(Integer x) {
        if (x != null && x!=0) {
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
