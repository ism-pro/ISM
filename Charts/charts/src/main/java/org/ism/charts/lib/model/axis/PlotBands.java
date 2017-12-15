/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.axis;

/**
 * <h1>PlotBands</h1><br>
 * PlotBands class An array of colored bands stretching across the plot area
 * marking an interval on the axis.
 *
 * In a gauge, a plot band on the Y axis (value axis) will stretch along the
 * perimeter of the gauge.
 *
 * In styled mode, the plot bands are styled by the .highcharts-plot-band class
 * in addition to the className option.
 *
 * @author r.hendrick
 *
 */
public class PlotBands {

    /**
     * borderColor: Color Border color for the plot band. Also requires
     * borderWidth to be set. Defaults to null.
     */
    private String borderColor = null;

    /**
     * borderWidth: Number Border width for the plot band. Also requires
     * borderColor to be set. Defaults to 0.
     */
    private Integer borderWidth = 0;

    /**
     * className: StringSince 5.0.0 A custom class name, in addition to the
     * default highcharts-plot-band, to apply to each individual band.
     */
    private String className = null;

    /**
     * color: Color The color of the plot band. Try it: Color band
     */
    private String color = null;

    /**
     * from: Number The start position of the plot band in axis units. Try it:
     * Datetime axis, categorized axis,
     */
    private Integer from = null;

    /**
     * id: String An id used for identifying the plot band in
     * Axis.removePlotBand. Try it: Remove plot band by id
     */
    private String id = null;

    /**
     * label Text labels for the plot bands
     */
    private Label label = null;

    /**
     * to: Number The end position of the plot band in axis units. Try it:
     * Datetime axis, categorized axis,
     */
    private Integer to = null;

    /**
     * zIndex: NumberSince 1.2 The z index of the plot band within the chart,
     * relative to other elements. Using the same z index as another element may
     * give unpredictable results, as the last rendered element will be on top.
     * Values from 0 to 20 make sense. Try it: Behind plot lines by default,
     * above plot lines, above plot lines and series.
     */
    private Integer zIndex = null;

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

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

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Label getLabel() {
        if(label==null)
            label = new Label();
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

    
    
    
}
