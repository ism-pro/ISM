/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.axis;

import org.ism.charts.lib.model.axis.Breaks;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class BreaksRenderer {

    static public String renderer(Breaks breaksSet, Boolean first) {
        String breaks = "";
        // Exclude Null pointer
        if (breaksSet == null) {
            //Util.out("Alarm *** BreaksRenderer : ", "breaksSet is null, return empty string");
            return breaks;
        }

        breaks += open(first);
        breaks += breakSize(breaksSet.getBreakSize());
        breaks += from(breaksSet.getFrom());
        breaks += repeat(breaksSet.getRepeat());
        breaks += to(breaksSet.getTo());

        breaks = close(breaks);
        return breaks;
    }

    static public String renderer(Breaks breaksSet) {
        return renderer(breaksSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "breaks:{" : ",breaks:{";
    }

    static private String close(String breaks) {
        if (breaks.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        breaks = breaks.substring(0, breaks.length() - 1);
        breaks += "}";
        return breaks;
    }

    static private String breakSize(Integer breakSize) {
        if (breakSize != null && breakSize != 0) {
            return "breakSize:" + breakSize + ",";
        }
        return "";
    }

    static private String from(Integer from) {
        if (from != null) {
            return "from:" + from + ",";
        }
        return "";
    }

    static private String repeat(Integer repeat) {
        if (repeat != null && repeat != 0) {
            return "repeat:" + repeat + ",";
        }
        return "";
    }

    static private String to(Integer to) {
        if (to != null) {
            return "to:" + to + ",";
        }
        return "";
    }

}
