/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.properties;

/**
 * <h1>DataLabels</h1>
 *
 * <h2>Description</h2>
 * Options for the series data labels, appearing next to each data point.
 *
 * In styled mode, the data labels can be styled wtih the
 * .highcharts-data-label-box and .highcharts-data-label class names (see
 * example).
 *
 *
 * @author r.hendrick
 */
public class DataLabels {

    /**
     * align: String The alignment of the data label compared to the point. If
     * right, the right side of the label should be touching the point. For
     * points with an extent, like columns, the alignments also dictates how to
     * align it inside the box, as given with the inside option. Can be one of
     * "left", "center" or "right". Defaults to center. Try it: Left aligned
     */
    private String align = null;

    /**
     * allowOverlap: BooleanSince 4.1.0 Whether to allow data labels to overlap.
     * To make the labels less sensitive for overlapping, the dataLabels.padding
     * can be set to 0. Defaults to false. Try it: Don't allow overlap
     */
    private Boolean allowOverlap = null;

    /**
     * backgroundColor: ColorSince 2.2.1 The background color or gradient for
     * the data label. Defaults to undefined. Try it:
     *
     * Data labels box options
     */
    private String backgroundColor = null;

    /**
     * borderColor: ColorSince 2.2.1 The border color for the data label.
     * Defaults to undefined. Try it:
     *
     * Data labels box options
     */
    private String borderColor = null;

    /**
     * borderRadius: NumberSince 2.2.1 The border radius in pixels for the data
     * label. Defaults to 0. Try it:
     *
     * Data labels box options
     */
    private Integer borderRadius = null;

    /**
     * borderWidth: NumberSince 2.2.1 The border width in pixels for the data
     * label. Defaults to 0. Try it:
     *
     * Data labels box options
     */
    private Integer borderWidth = null;

    /**
     * className: StringSince 5.0.0 A class name for the data label.
     * Particularly in styled mode, this can be used to give each series' or
     * point's data label unique styling. In addition to this option, a default
     * color class name is added so that we can give the labels a contrast text
     * shadow. Try it:
     *
     * Styling by CSS.
     */
    private String className = null;

    /**
     * color: Color The text color for the data labels. Defaults to null. Try
     * it:
     *
     * Red data labels
     */
    private String color = null;

    /**
     * connectorColor: StringSince 2.1 The color of the line connecting the data
     * label to the pie slice. The default color is the same as the point's
     * color.
     *
     * In styled mode, the connector stroke is given in the
     * .highcharts-data-label-connector class.
     *
     * Defaults to {point.color}. Try it:
     *
     * Blue connectors. Styled connectors.
     */
    private String connectorColor = null;

    /**
     * connectorPadding: NumberSince 2.1 The distance from the data label to the
     * connector. Defaults to 5. Try it:
     *
     * No padding
     */
    private Integer connectorPadding = null;

    /**
     * connectorWidth: NumberSince 2.1 The width of the line connecting the data
     * label to the pie slice.
     *
     * In styled mode, the connector stroke width is given in the
     * .highcharts-data-label-connector class.
     *
     * Defaults to 1. Try it:
     *
     * Disable the connector. Styled connectors.
     */
    private Integer connectorWidth = null;

    /**
     * crop: BooleanSince 2.3.3 Whether to hide data labels that are outside the
     * plot area. By default, the data label is moved inside the plot area
     * according to the overflow option. Defaults to true.
     */
    private Boolean crop = null;

    /**
     * defer: BooleanSince 4.0 Whether to defer displaying the data labels until
     * the initial series animation has finished. Defaults to true.
     */
    private Boolean defer = null;

    /**
     * distance: NumberSince 2.1 The distance of the data label from the pie's
     * edge. Negative numbers put the data label on top of the pie slices.
     * Connectors are only shown for data labels outside the pie. Defaults to
     * 30. Try it:
     *
     * Data labels on top of the pie
     */
    private Integer distance = null;

    /**
     * enabled: BooleanSince 2.1 Enable or disable the data labels. Defaults to
     * true.
     */
    private Boolean enabled = null;         //true

    /**
     * format: StringSince 3.0 A format string for the data label. Available
     * variables are the same as for formatter. Defaults to {y}. Try it:
     *
     * Add a unit
     */
    private String format = null;

    /**
     * formatter: Function Callback JavaScript function to format the data
     * label. Note that if a format is defined, the format takes precedence and
     * the formatter is ignored. Available data are: this.percentage	Stacked
     * series and pies only. The point's percentage of the total. this.point	The
     * point object. The point name, if defined, is available through
     * this.point.name. this.series:	The series object. The series name is
     * available through this.series.name. this.total	Stacked series only. The
     * total value at this point's x value. this.x:	The x value. this.y:	The y
     * value.
     */
    private String formatter = null;         //undefined
    /**
     * inside: BooleanSince 3.0 For points with an extent, like columns, whether
     * to align the data label inside the box or to the actual value point.
     * Defaults to false in most cases, true in stacked columns.
     */
    private Boolean inside = null;

    /**
     * overflow: StringSince 3.0.6 How to handle data labels that flow outside
     * the plot area. The default is justify, which aligns them inside the plot
     * area. For columns and bars, this means it will be moved inside the bar.
     * To display data labels outside the plot area, set crop to false and
     * overflow to "none". Defaults to justify.
     */
    private String overflow = null;

    /**
     * padding: NumberSince 2.2.1 When either the borderWidth or the
     * backgroundColor is set, this	is the padding within the box. Defaults to
     * 5. Try it:
     *
     * Data labels box options
     */
    private Integer padding = null;

    /**
     * reserveSpace: BooleanSince 4.1.10 Whether to reserve space for the
     * labels. This can be turned off when for example the labels are rendered
     * inside the plot area instead of outside. Defaults to true. Try it: No
     * reserved space, labels inside plot.
     */
    private Boolean reserveSpace = null;

    /**
     * rotation: Number Text rotation in degrees. Note that due to a more
     * complex structure, backgrounds, borders and padding will be lost on a
     * rotated data label. Defaults to 0. Try it:
     *
     * Vertical labels
     */
    private Integer rotation = null;

    /**
     * shadow: Boolean|ObjectSince 2.2.1 The shadow of the box. Works best with
     * borderWidth or backgroundColor. Since 2.3 the shadow can be an object
     * configuration containing color, offsetX, offsetY, opacity and width.
     * Defaults to false. Try it:
     *
     * Data labels box options
     */
    private Boolean shadow = null;

    /**
     * shape: StringSince 4.1.2 The name of a symbol to use for the border
     * around the label. Symbols are predefined functions on the Renderer
     * object. Defaults to square. Try it:
     *
     * A callout for annotations
     */
    private String shape = null;

    /**
     * softConnector: BooleanSince 2.1.7 Whether to render the connector as a
     * soft arc or a line with sharp break. Defaults to true. Try it:
     *
     * Soft, non soft connectors.
     */
    private Boolean softConnector = null;

    /**
     * style: CSSObjectSince 4.1.0 Styles for the label. Defaults to {"color":
     * "contrast", "fontSize": "11px", "fontWeight": "bold", "textShadow": "1px
     * 1px contrast, -1px -1px contrast, -1px 1px contrast, 1px -1px contrast"
     * }. Try it:
     *
     * Bold labels
     */
    private Style style = null;

    /**
     * useHTML: Boolean Whether to use HTML to render the labels. Defaults to
     * false.
     */
    private Boolean useHTML = null;

    /**
     * verticalAlign: StringSince 2.3.3 The vertical alignment of a data label.
     * Can be one of top, middle or bottom. The default value depends on the
     * data, for instance in a column chart, the label is above positive values
     * and below negative values.
     */
    private String verticalAlign = null;

    /**
     * x: Number The x position offset of the label relative to the point.
     * Defaults to 0. Try it:
     *
     * Vertical and positioned
     */
    private Integer x = null;

    /**
     * The y position offset of the label relative to the point. Defaults to -6.
     * Try it:
     *
     * Vertical and positioned
     */
    private Integer y = null;

    /**
     * zIndex: NumberSince 2.3.5 The Z index of the data labels. The default Z
     * index puts it above the series. Use a Z index of 2 to display it behind
     * the series. Defaults to 6.
     */
    private Integer zIndex = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public Boolean getAllowOverlap() {
        return allowOverlap;
    }

    public void setAllowOverlap(Boolean allowOverlap) {
        this.allowOverlap = allowOverlap;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public Integer getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(Integer borderRadius) {
        this.borderRadius = borderRadius;
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

    public String getConnectorColor() {
        return connectorColor;
    }

    public void setConnectorColor(String connectorColor) {
        this.connectorColor = connectorColor;
    }

    public Integer getConnectorPadding() {
        return connectorPadding;
    }

    public void setConnectorPadding(Integer connectorPadding) {
        this.connectorPadding = connectorPadding;
    }

    public Integer getConnectorWidth() {
        return connectorWidth;
    }

    public void setConnectorWidth(Integer connectorWidth) {
        this.connectorWidth = connectorWidth;
    }

    public Boolean getCrop() {
        return crop;
    }

    public void setCrop(Boolean crop) {
        this.crop = crop;
    }

    public Boolean getDefer() {
        return defer;
    }

    public void setDefer(Boolean defer) {
        this.defer = defer;
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

    public Boolean getInside() {
        return inside;
    }

    public void setInside(Boolean inside) {
        this.inside = inside;
    }

    public String getOverflow() {
        return overflow;
    }

    public void setOverflow(String overflow) {
        this.overflow = overflow;
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

    public Boolean getShadow() {
        return shadow;
    }

    public void setShadow(Boolean shadow) {
        this.shadow = shadow;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Boolean getSoftConnector() {
        return softConnector;
    }

    public void setSoftConnector(Boolean softConnector) {
        this.softConnector = softConnector;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Boolean getUseHTML() {
        return useHTML;
    }

    public void setUseHTML(Boolean useHTML) {
        this.useHTML = useHTML;
    }

    public String getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(String verticalAlign) {
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

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

}
