/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.properties;

import org.ism.model.properties.Hover;
import org.ism.model.properties.Select;
import org.ism.model.properties.States;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class StatesRenderer {

    static public String renderer(States statesSet, Boolean first) {
        String states = "";
        // Exclude Null pointer
        if (statesSet == null) {
            //Util.out("Alarm *** StatesRenderer : ", "statesSet is null, return empty string");
            return states;
        }

        states += open(first);
        states += hover(statesSet.getHover(), isFirst(states));
        states += select(statesSet.getSelect(), isFirst(states));

        states = close(states);
        return states;
    }

    static public String renderer(States statesSet) {
        return renderer(statesSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "states:{" : ",states:{";
    }

    static private String close(String states) {
        if (states.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        states = states.substring(0, states.length() - 1);
        states += "}";
        return states;
    }

    static private Boolean isFirst(String states) {
        return states.endsWith(",");
    }

    static private String hover(Hover hover, Boolean first) {
        if (hover != null) {
            String tmp = HoverRenderer.renderer(hover, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String select(Select select, Boolean first) {
        if (select != null) {
            String tmp = SelectRenderer.renderer(select, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

}
