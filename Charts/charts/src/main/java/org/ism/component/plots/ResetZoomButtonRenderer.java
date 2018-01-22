/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.plots;

import org.ism.model.axis.Position;
import org.ism.model.properties.ResetZoomButton;
import org.ism.util.Util;


/**
 * <h1>ResetZoomButtonRenderer</h1>
 *
 * <h2>Description</h2>
 *
 *
 * @author r.hendrick
 */
public class ResetZoomButtonRenderer {

    /**
     * <h3>renderer</h3>
     * This is the main method which allow to convert a ResetZoomButtonRenderer
     * model to a hightchart encoded script
     *
     * This method return empty string if no data set
     *
     * @param resetZoomButton is model of resetZoomButton allowing to render it data
     * @param first define if has to start with ","
     * @return The output is empty string for null or nothing used
     */
    static public String renderer(ResetZoomButton resetZoomButton, Boolean first) {
        String rzb = "";
        // Exclude Null pointer
        if (resetZoomButton == null) {
            //Util.out("Alarm *** ResetZoomButtonRenderer : ", "resetZoomButton is null, return empty string");
            return rzb;
        }

        // Check used
        if (!resetZoomButton.isUnused() && !resetZoomButton.getPosition().isUnused()) {
            //Util.out("Warning *** ResetZoomButtonRenderer : ", "resetZoomButton not used");
            return rzb;
        }

        rzb += open(first);
        rzb += position(resetZoomButton.getPosition());
        rzb += relativeTo(resetZoomButton.getRelativeTo());
        rzb += theme(resetZoomButton.getTheme());
        rzb = close(rzb);
        return rzb;
    }

    /**
     * Surcharged method to auto set first
     *
     * @param resetZoomButton reset zoom button
     * @return script encode of resetZoomButton
     */
    static public String renderer(ResetZoomButton resetZoomButton) {
        return renderer(resetZoomButton, Boolean.TRUE);
    }

    /**
     * <h3>open</h3>
     *
     * @return script to open resetZoomButton starting with ","
     */
    static private String open(Boolean first) {
        return first ? "resetZoomButton:{" : ",resetZoomButton:{";
    }

    /**
     * The position of the button. Try it:
     *
     * Above the plot area
     */
    static private String position(Position position) {
        if (position != null) {
            return PositionRenderer.renderer(position);
        }
        return "";
    }

    /**
     * What frame the button should be placed related to. Can be either "plot"
     * or "chart". Defaults to plot. Try it:
     *
     * Relative to the chart
     */
    static private String relativeTo(String relative) {
        if (relative != null) {
            return "relativeTo:'" + relative + "',";
        }
        return "";
    }

    /**
     * theme: ObjectSince 2.2 A collection of attributes for the button. The
     * object takes SVG attributes like fill, stroke, stroke-width or r, the
     * border radius. The theme also supports style, a collection of CSS
     * properties for the text. Equivalent attributes for the hover state are
     * given in theme.states.hover. Try it:
     *
     * Theming the button
     */
    static private String theme(String th) {
        if (th != null) {
            return "theme:'" + th + "',";
        }
        return "";
    }

    /**
     * <h3>close</h3>
     *
     * @return script to close resetZoomButton
     */
    static private String close(String rzb) {
        rzb = rzb.substring(0, rzb.length() - 1);
        rzb += "}";
        return rzb;
    }

}
