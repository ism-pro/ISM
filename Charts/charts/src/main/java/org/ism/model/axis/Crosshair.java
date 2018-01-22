/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.axis;

import org.ism.model.properties.DashStyle;

/**
 * <h1>Crosshair</h1><br>
 * Configure a crosshair that follows either the mouse pointer or the hovered
 * point.
 *
 * In styled mode, the crosshairs are styled in the .highcharts-crosshair,
 * .highcharts-crosshair-thin or .highcharts-xaxis-category classes.
 *
 * Try it: Crosshair on both axes
 *
 * @author r.hendrick
 *
 */
public class Crosshair {

    /**
     * className: StringSince 5.0.0 A class name for the crosshair, especially
     * as a hook for styling.
     */
    private String className = null;

    /**
     * color: ColorSince 4.1 The color of the crosshair. Defaults to #cccccc for
     * numeric and datetime axes, and rgba(204,214,235,0.25) for category axes,
     * where the crosshair by default highlights the whole category. Try it:
     * Customized crosshairs.
     */
    private String color = "#cccccc";

    /**
     * dashStyle: StringSince 4.1 The dash style for the crosshair. See
     * series.dashStyle for possible values. Defaults to Solid. Try it: Dotted
     * crosshair
     */
    private DashStyle dashStyle = DashStyle.SOLID;

    /**
     * snap: BooleanSince 4.1 Whether the crosshair should snap to the point or
     * follow the pointer independent of points. Defaults to true. Try it: True
     * by default
     */
    private Boolean snap = true;

    /**
     * width: NumberSince 4.1 The pixel width of the crosshair. Defaults to 1
     * for numeric or datetime axes, and for one category width for category
     * axes. Try it: Customized crosshairs.
     */
    private Integer width = 1;

    /**
     * zIndex: NumberSince 4.1 The Z index of the crosshair. Higher Z indices
     * allow drawing the crosshair on top of the series or behind the grid
     * lines. Defaults to 2.
     */
    private Integer zIndex = 2;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String Color) {
        this.color = Color;
    }

    public DashStyle getDashStyle() {
        return dashStyle;
    }

    public void setDashStyle(DashStyle dashStyle) {
        this.dashStyle = dashStyle;
    }

    public Boolean getSnap() {
        return snap;
    }

    public void setSnap(Boolean snap) {
        this.snap = snap;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }
    
    
    
    
}
