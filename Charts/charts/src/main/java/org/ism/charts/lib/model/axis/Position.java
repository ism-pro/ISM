/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.axis;

import org.ism.charts.lib.model.properties.Align;

/**
 * <h1>Position</h1>
 *
 * <h2>Description</h2>
 * The position of the button. Try it:
 *
 * Above the plot area
 *
 * @author r.hendrick
 */
public class Position {

    /**
     * The heatmap series type. This series type is available both in Highcharts
     * and Highmaps.
     */
    private Align align = null;

    /**
     * The vertical alignment of the button. Defaults to top.
     */
    private Align verticalAlign = null;

    /**
     * The horizontal offset of the button Defaults to -10.
     */
    private Integer x = null;

    /**
     * The vertical offset of the button. Defaults to 10.bbbbbbbxx
     */
    private Integer y = null;

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

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
        unused = align != null;
    }

    public Align getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(Align verticalAlign) {
        this.verticalAlign = verticalAlign;
        unused = verticalAlign != null;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
        unused = x != null;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
        unused = y != null;
    }

}
