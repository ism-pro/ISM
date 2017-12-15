/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.properties;

/**
 * <h1>Zone</h1>
 *
 * <h2>Description</h2>
 * An array defining zones within a series. Zones can be applied to the X axis,
 * Y axis or Z axis for bubbles, according to the zoneAxis option.
 *
 * In styled mode, the color zones are styled with the .highcharts-zone-{n}
 * class, or custom classed from the className option (view live demo).
 *
 * Try it:
 *
 * Color zones
 *
 *
 * @author r.hendrick
 */
public class Zone {

    /**
     * className: StringSince 5.0.0 Styled mode only. A custom class name for
     * the zone. Try it:
     *
     * Zones styled by class name.
     */
    private String className = null;

    /**
     * color: ColorSince 4.1.0 Defines the color of the series. See also:
     *
     * series color
     */
    private String color = null;

    /**
     * dashStyle: StringSince 4.1.0 A name for the dash style to use for the
     * graph. Try it:
     *
     * Dashed line indicates prognosis See also:
     *
     * series.dashStyle
     */
    private DashStyle dashStyle = null;

    /**
     * fillColor: ColorSince 4.1.0 Defines the fill color for the series (in
     * area type series) See also:
     *
     * fillColor
     */
    private String fillColor = null;

    /**
     * value: NumberSince 4.1.0 The value up to where the zone extends, if
     * undefined the zones stretches to the last value in the series. Defaults
     * to undefined.
     */
    private Integer value = null;

    /**
     * Boolean used to determine if when of the setter method was used that way,
     * we know if a parameter has to be write down
     */
    private Boolean unused = true;

    /***
     * <h1>isUnused</h1>
     * Standard method to describe if class was used by set or get method
     * 
     * @return unused to know if change was made
     */
    public boolean isUnused() {
        return unused;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        unused = className != null;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        unused = color != null;
    }

    public DashStyle getDashStyle() {
        return dashStyle;
    }

    public void setDashStyle(DashStyle dashStyle) {
        this.dashStyle = dashStyle;
        unused = dashStyle != null;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
        unused = fillColor != null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
        unused = value != null;
    }

}
