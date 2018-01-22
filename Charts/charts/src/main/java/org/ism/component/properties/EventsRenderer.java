/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.properties;

import org.ism.model.properties.Events;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class EventsRenderer {

    static public String renderer(Events eventsSet, Boolean first) {
        String events = "";
        // Exclude Null pointer
        if (eventsSet == null) {
            //Util.out("Alarm *** HaloRenderer : ", "eventsSet is null, return empty string");
            return events;
        }

        events += open(first);
        events += afterAnimate(eventsSet.getAfterAnimate());
        events += checkboxClick(eventsSet.getCheckboxClick());
        events += click(eventsSet.getClick());
        events += hide(eventsSet.getHide());
        events += legendItemClick(eventsSet.getLegendItemClick());
        events += mouseOut(eventsSet.getMouseOut());
        events += mouseOver(eventsSet.getMouseOver());
        events += remove(eventsSet.getRemove());
        events += select(eventsSet.getSelect());
        events += show(eventsSet.getShow());
        events += unselect(eventsSet.getUnselect());
        events += update(eventsSet.getUpdate());

        events = close(events);
        return events;
    }

    static public String renderer(Events eventsSet) {
        return renderer(eventsSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "events:{" : ",events:{";
    }

    static private String close(String events) {
        if (events.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        events = events.substring(0, events.length() - 1);
        events += "}";
        return events;
    }

    static private String afterAnimate(String afterAnimate) {
        if (afterAnimate != null) {
            return "afterAnimate:" + afterAnimate + ",";
        }
        return "";
    }

    static private String checkboxClick(String checkboxClick) {
        if (checkboxClick != null) {
            return "checkboxClick:" + checkboxClick + ",";
        }
        return "";
    }

    static private String click(String click) {
        if (click != null) {
            return "click:" + click + ",";
        }
        return "";
    }

    static private String hide(String hide) {
        if (hide != null) {
            return "hide:" + hide + ",";
        }
        return "";
    }

    static private String legendItemClick(String legendItemClick) {
        if (legendItemClick != null) {
            return "legendItemClick:" + legendItemClick + ",";
        }
        return "";
    }

    static private String mouseOut(String mouseOut) {
        if (mouseOut != null) {
            return "mouseOut:" + mouseOut + ",";
        }
        return "";
    }

    static private String mouseOver(String mouseOver) {
        if (mouseOver != null) {
            return "mouseOver:" + mouseOver + ",";
        }
        return "";
    }

    static private String remove(String remove) {
        if (remove != null) {
            return "remove:" + remove + ",";
        }
        return "";
    }

    static private String select(String select) {
        if (select != null) {
            return "select:" + select + ",";
        }
        return "";
    }

    static private String show(String show) {
        if (show != null) {
            return "show:" + show + ",";
        }
        return "";
    }

    static private String unselect(String unselect) {
        if (unselect != null) {
            return "unselect:" + unselect + ",";
        }
        return "";
    }

    static private String update(String update) {
        if (update != null) {
            return "update:" + update + ",";
        }
        return "";
    }

}
