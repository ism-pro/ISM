/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.properties;

import org.ism.charts.lib.model.axis.Position;

/**
 * <h1>ResetZoomButton</h1>
 *
 * <h2>Description</h2>
 *
 *
 * @author r.hendrick
 */
public class ResetZoomButton {

    /**
     * The position of the button. Try it:
     *
     * Above the plot area
     */
    private Position position = null;

    /**
     * What frame the button should be placed related to. Can be either "plot"
     * or "chart". Defaults to plot. Try it:
     *
     * Relative to the chart
     */
    private String relativeTo = null;

    /**
     * theme: ObjectSince 2.2 A collection of attributes for the button. The
     * object takes SVG attributes like fill, stroke, stroke-width or r, the
     * border radius. The theme also supports style, a collection of CSS
     * properties for the text. Equivalent attributes for the hover state are
     * given in theme.states.hover. Try it:
     *
     * Theming the button
     */
    private String theme = null;

    /**
     * Boolean used to determine if when of the setter method was used that way,
     * we know if a parameter has to be write down
     */
    private Boolean unused = true;

    /**
     *
     * <
     * h1>isUnused</h1>
     * Standard method to describe if class was used by set or get method
     *
     * @return unused to know if change was made
     */
    public boolean isUnused() {
        return unused;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getRelativeTo() {
        return relativeTo;
    }

    public void setRelativeTo(String relativeTo) {
        this.relativeTo = relativeTo;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
}
