/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.axis;

import org.ism.model.properties.Align;

/**
 * <h1>Labels</h1><br>
 * Labels class The axis labels show the number or category for each tick.
 *
 *
 * @author r.hendrick
 *
 */
public class StackLabels {

    /**
     * align: String <br>
     * What part of the string the given position is anchored to. If left, the
     * left side of the string is at the axis position. Can be one of "left",
     * "center" or "right". Defaults to an intelligent guess based on which side
     * of the chart the axis is on and the rotation of the label. Try it: Align
     * left and, right on X axis. Understand alignment to boxes, lines and
     * points
     */
    private Align align = null;

    /**
     * enabled: Boolean Enable or disable the axis labels. Defaults to true. Try
     * it: X axis labels disabled
     */
    private Boolean enabled = true;

    /**
     * format: StringSince 3.0 A format string for the axis label. Defaults to
     * {value}. Try it: Add units to Y axis label
     */
    private String format = "{value}";

    /**
     * formatter: Function Callback JavaScript function to format the label. The
     * value is given by this.value. Additional properties for this are axis,
     * chart, isFirst and isLast. The value of the default label formatter can
     * be retrieved by calling this.axis.defaultLabelFormatter.call(this) within
     * the function.
     *
     * Defaults to:
     *
     * function() { return this.value; } Try it: Linked category names. Modified
     * numeric labels.
     */
    private String formatter = "function(){return this.value;}";

    /**
     * rotation: Number Rotation of the labels in degrees. Defaults to 0. Try
     * it: X axis labels rotated 90°
     */
    private Integer rotation = 0;

    /**
     * style: CSSObject CSS styles for the label. Use whiteSpace: 'nowrap' to
     * prevent wrapping of category labels. Use textOverflow: 'none' to prevent
     * ellipsis (dots).
     *
     * In styled mode, the labels are styled with the .highcharts-axis-labels
     * class.
     *
     * Defaults to { "color": "#666666", "cursor": "default", "fontSize": "11px"
     * }. Try it: Red X axis labels
     */
    private String style = null;

    /**
     * textAlign: StringSince 2.1.5 The text alignment for the label. While
     * align determines where the texts anchor point is placed with regards to
     * the stack, textAlign determines how the text is aligned against its
     * anchor point. Possible values are "left", "center" and "right". The
     * default value is calculated at runtime and depends on orientation and
     * whether the stack is positive or negative. Try it: Label in center
     * position but text-aligned left
     */
    private Align textAlign = null;

    /**
     * useHTML: Boolean Whether to use HTML to render the labels. Defaults to
     * false.
     */
    private Boolean useHTML = false;

    /**
     * verticalAlign: StringSince 2.1.5 Defines the vertical alignment of the
     * stack total label. Can be one of "top", "middle" or "bottom". The default
     * value is calculated at runtime and depends on orientation and whether the
     * stack is positive or negative. Try it: "Vertically aligned top",
     * "Vertically aligned middle", "Vertically aligned bottom"
     */
    private Align verticalAlign = null;

    /**
     * x: Number The x position offset of the label relative to the tick
     * position on the axis. Defaults to 0. Try it: Y axis labels placed on grid
     * lines
     */
    private Integer x = null;

    /**
     * y: Number The y position offset of the label relative to the tick
     * position on the axis. The default makes it adapt to the font size on
     * bottom axis. Defaults to null. Try it: Y axis labels placed on grid lines
     */
    private Integer y = null;


    
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // Getter / Setter
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Align getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(Align textAlign) {
        this.textAlign = textAlign;
    }

    public Boolean getUseHTML() {
        return useHTML;
    }

    public void setUseHTML(Boolean useHTML) {
        this.useHTML = useHTML;
    }

    public Align getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(Align verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
    
  
}
