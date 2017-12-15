/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.axis;

import org.ism.charts.lib.model.properties.DashStyle;

/**
 *
 * An array of objects representing plot lines on the X axis
 *
 * @author r.hendrick
 */
public class PlotLines {

    /**
     * className: StringSince 5.0.0 A custom class name, in addition to the
     * default highcharts-plot-line, to apply to each individual line.
     */
    private String className = null;

    /**
     * color: Color The color of the line. Try it:
     *
     * A red line from X axis
     */
    private String color = null;

    /**
     * dashStyle: StringSince 1.2 The dashing or dot style for the plot line.
     * For possible values see this overview. Defaults to Solid. Try it:
     *
     * Dash and dot pattern
     */
    private DashStyle dashStyle = null;

    // Event
    /**
     * id: String An id used for identifying the plot line in
     * Axis.removePlotLine. Try it:
     *
     * Remove plot line by id
     */
    private String id = null;

    /**
     * label Text labels for the plot bands
     */
    private Label label = null;

    /**
     * value: Number The position of the line in axis units. Try it:
     *
     * Between two categories on X axis
     */
    private Integer value = null;

    /**
     * width: Number The width or thickness of the plot line. Try it:
     *
     * 2px wide line from X axis
     */
    private Integer width = null;

    /**
     * zIndex: NumberSince 1.2 The z index of the plot line within the chart.
     * Try it:
     *
     * Behind plot lines by default, above plot lines, above plot lines and
     * series.
     */
    private Integer zIndex = null;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public DashStyle getDashStyle() {
        return dashStyle;
    }

    public void setDashStyle(DashStyle dashStyle) {
        this.dashStyle = dashStyle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
