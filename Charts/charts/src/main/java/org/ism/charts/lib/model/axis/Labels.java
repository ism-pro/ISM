/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.axis;

import java.util.ArrayList;
import java.util.List;
import org.ism.charts.lib.model.properties.Align;

/**
 * <h1>Labels</h1><br>
 * Labels class The axis labels show the number or category for each tick.
 *
 *
 * @author r.hendrick
 *
 */
public class Labels {

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
     * autoRotation: Array<Number>Since 4.1.0 For horizontal axes, the allowed
     * degrees of label rotation to prevent overlapping labels. If there is
     * enough space, labels are not rotated. As the chart gets narrower, it will
     * start rotating the labels -45 degrees, then remove every second label and
     * try again with rotations 0 and -45 etc. Set it to false to disable
     * rotation, which will cause the labels to word-wrap if possible. Defaults
     * to [-45]. Try it: Default auto rotation of 0 or -45, custom graded auto
     * rotation
     */
    private List<Integer> autoRotation = null;

    /**
     * autoRotationLimit: NumberSince 4.1.5 When each category width is more
     * than this many pixels, we don't apply auto rotation. Instead, we lay out
     * the axis label with word wrap. A lower limit makes sense when the label
     * contains multiple short words that don't extend the available horizontal
     * space for each label. Defaults to 80. Try it: Lower limit
     */
    private Integer autoRotationLimit = 80;

    /**
     * distance: Number Polar charts only. The label's pixel distance from the
     * perimeter of the plot area. Defaults to 15.
     */
    private Integer distance = 15;

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
     * padding: Number The pixel padding for axis labels, to ensure white space
     * between them. Defaults to 5.
     */
    private Integer padding = 5;

    /**
     * reserveSpace: BooleanSince 4.1.10 Whether to reserve space for the
     * labels. This can be turned off when for example the labels are rendered
     * inside the plot area instead of outside. Defaults to true. Try it: No
     * reserved space, labels inside plot.
     */
    private Boolean reserveSpace = true;

    /**
     * rotation: Number Rotation of the labels in degrees. Defaults to 0. Try
     * it: X axis labels rotated 90°
     */
    private Integer rotation = 0;

    /**
     * staggerLines: Number Since 2.1 Horizontal axes only. The number of lines
     * to spread the labels over to make room or tighter labels. . Try it: Show
     * labels over two lines
     */
    private Integer staggerLines = null;

    /**
     * step: NumberSince 2.1 To show only every n'th label on the axis, set the
     * step to n. Setting the step to 2 shows every other label.
     *
     * By default, the step is calculated automatically to avoid overlap. To
     * prevent this, set it to 1. This usually only happens on a category axis,
     * and is often a sign that you have chosen the wrong axis type. Read more
     * at Axis docs => What axis should I use?
     *
     * Try it: Showing only every other axis label on a categorized x axis. Auto
     * steps on a category axis.
     */
    private Integer step = null;

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
    private String style = "{\"color\":\"#666666\",\"cursor\":\"default\",\"fontSize\":\"11px\"}";

    /**
     * useHTML: Boolean Whether to use HTML to render the labels. Defaults to
     * false.
     */
    private Boolean useHTML = false;

    /**
     * x: Number The x position offset of the label relative to the tick
     * position on the axis. Defaults to 0. Try it: Y axis labels placed on grid
     * lines
     */
    private Integer x = 0;

    /**
     * y: Number The y position offset of the label relative to the tick
     * position on the axis. The default makes it adapt to the font size on
     * bottom axis. Defaults to null. Try it: Y axis labels placed on grid lines
     */
    private Integer y = null;

    /**
     * zIndex: Number The Z index for the axis labels. Defaults to 7.
     */
    private Integer zIndex = 7;

    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // Getter / Setter
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    public Align getAlign() {
        if (align == null) {
            align = Align.CENTER;
        }
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public List<Integer> getAutoRotation() {
        if (autoRotation == null) {
            autoRotation = new ArrayList<>();
        }
        return autoRotation;
    }

    public void setAutoRotation(List<Integer> autoRotation) {
        this.autoRotation = autoRotation;
    }

    public Integer getAutoRotationLimit() {
        return autoRotationLimit;
    }

    public void setAutoRotationLimit(Integer autoRotationLimit) {
        this.autoRotationLimit = autoRotationLimit;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public Integer getPadding() {
        return padding;
    }

    public void setPadding(Integer padding) {
        this.padding = padding;
    }

    public Boolean getReserveSpace() {
        return reserveSpace;
    }

    public void setReserveSpace(Boolean reserveSpace) {
        this.reserveSpace = reserveSpace;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    public Integer getStaggerLines() {
        if (staggerLines == null) {
            staggerLines = 0;
        }
        return staggerLines;
    }

    public void setStaggerLines(Integer staggerLines) {
        this.staggerLines = staggerLines;
    }

    public Integer getStep() {
        if (step == null) {
            step = 0;
        }
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Boolean getUseHTML() {
        return useHTML;
    }

    public void setUseHTML(Boolean useHTML) {
        this.useHTML = useHTML;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        if (y == null) {
            y = 0;
        }
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

}
