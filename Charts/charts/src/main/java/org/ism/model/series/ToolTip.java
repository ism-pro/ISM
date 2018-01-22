/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.series;

/**
 * <h1>ToolTip</h1><br>
 * ToolTip tooltip: ObjectSince 2.3 A configuration object for the tooltip
 * rendering of each single series. Properties are inherited from tooltip, but
 * only the following properties can be defined on a series level.
 *
 * @author r.hendrick
 *
 */
public class ToolTip {

    /**
     * Enable or disable animation of the tooltip. In slow legacy IE browsers
     * the animation is disabled by default. Defaults to true.
     */
    private Boolean animation = null;

    /**
     * The background color or gradient for the tooltip.
     *
     * In styled mode, the stroke width is set in the .highcharts-tooltip-box
     * class.
     *
     * Defaults to rgba(247,247,247,0.85). Try it:
     *
     * Yellowish background. Gradient. Tooltip in styled mode.
     *
     */
    private String backgroundColor = null;

    /**
     * The color of the tooltip border. When null, the border takes the color of
     * the corresponding series or point. Defaults to null. Try it:
     *
     * Follow series by default, black border
     *
     */
    private String borderColor = null;

    /**
     * The radius of the rounded border corners. Defaults to 3. Try it:
     *
     * 5px by default, square borders
     *
     */
    private Integer borderRadius = null;

    /**
     * The pixel width of the tooltip border.
     *
     * In styled mode, the stroke width is set in the .highcharts-tooltip-box
     * class.
     *
     * Defaults to 1. Try it:
     *
     * 2px by default. No border (shadow only).	Tooltip in styled mode.
     *
     */
    private Integer borderWidth = null;

    /**
     * dateTimeLabelFormats: Object For series on a datetime axes, the date
     * format in the tooltip's header will by default be guessed based on the
     * closest data points. This member gives the default string representations
     * used for each unit. For an overview of the replacement codes, see
     * dateFormat.
     *
     * Defaults to:
     *
     * {
     * millisecond:"%A, %b %e, %H:%M:%S.%L", second:"%A, %b %e, %H:%M:%S",
     * minute:"%A, %b %e, %H:%M", hour:"%A, %b %e, %H:%M", day:"%A, %b %e, %Y",
     * week:"Week from %A, %b %e, %Y", month:"%B %Y", year:"%Y" } See also:
     * xAxis.dateTimeLabelFormats
     *
     */
    private String dateTimeLableFormats = null;
    /**
     * Enable or disable the tooltip. Defaults to true. Try it:
     *
     * Disabled, disable tooltip and show values on chart instead.
     *
     */
    private Boolean enabled = null;

    /**
     * Whether the tooltip should follow the mouse as it moves across columns,
     * pie slices and other point types with an extent. By default it behaves
     * this way for scatter, bubble and pie series by override in the
     * plotOptions for those series types.
     *
     * For touch moves to behave the same way, followTouchMove must be true
     * also.
     *
     * Defaults to false.
     *
     */
    private Boolean followPointer = null;

    /**
     * Whether the tooltip should follow the finger as it moves on a touch
     * device. If chart.zoomType is set, it will override followTouchMove.
     * Defaults to true.
     *
     */
    private Boolean followTouchMove = null;

    /**
     * A string to append to the tooltip format. Defaults to false. Try it:
     *
     * A table for value alignment
     *
     */
    private String footerFormat = null;

    /**
     * The HTML of the tooltip header line. Variables are enclosed by curly
     * brackets. Available variables	are point.key, series.name, series.color
     * and other members from the point and series objects. The point.key
     * variable contains the category name, x value or datetime string depending
     * on the type of axis. For datetime axes, the point.key date format can be
     * set using tooltip.xDateFormat.
     *
     * Defaults to <span style="font-size: 10px">{point.key}</span><br/>
     * Try it:
     *
     * A HTML table in the tooltip
     *
     */
    private String headerFormat = null;

    /**
     * The number of milliseconds to wait until the tooltip is hidden when mouse
     * out from a point or chart. Defaults to 500.
     *
     */
    private Integer hideDelay = null;

    /**
     * Padding inside the tooltip, in pixels. Defaults to 8.
     *
     */
    private Integer padding = null;

    /**
     * The HTML of the point's line in the tooltip. Variables are enclosed by
     * curly brackets. Available variables are point.x, point.y, series.name and
     * series.color and other properties on the same form. Furthermore, point.y
     * can be extended by the tooltip.valuePrefix and tooltip.valueSuffix
     * variables. This can also be overridden for each series, which makes it a
     * good hook for displaying units.
     *
     * In styled mode, the dot is colored by a class name rather than the point
     * color.
     *
     * Defaults to <span style="color:{point.color}">\u25CF</span>
     * {series.name}: <b>{point.y}</b><br/>. Try it:
     *
     * A different point format with value suffix
     *
     */
    private String pointFormat = null;

    /**
     * pointFormatter: FunctionSince 4.1.0 A callback function for formatting
     * the HTML output for a single point in the tooltip. Like the pointFormat
     * string, but with more flexibility. The this keyword refers to the Point
     * object.
     */
    private String pointFormatter = null;

    /**
     * Whether to apply a drop shadow to the tooltip. Defaults to true. Try it:
     *
     * True by default, false
     *
     */
    private Boolean shadow = null;

    /**
     * The name of a symbol to use for the border around the tooltip. In
     * Highcharts 3.x and less, the shape was square. Defaults to callout.
     *
     */
    private String shape = null;

    /**
     * When the tooltip is shared, the entire plot area will capture mouse
     * movement or touch events. Tooltip texts for series types with ordered
     * data (not pie, scatter, flags etc) will be shown in a single bubble. This
     * is recommended for single series charts and for tablet/mobile optimized
     * charts.
     *
     * See also tooltip.split, that is better suited for charts with many
     * series, especially line-type series.
     *
     * Defaults to false. Try it:
     *
     * False by default, true, true with x axis crosshair, true with mixed
     * series types.
     *
     */
    private Boolean shared = null;

    /**
     * Proximity snap for graphs or single points. Does not apply to bars,
     * columns and pie slices. It defaults to 10 for mouse-powered devices and
     * 25 for touch devices. Note that since Highcharts 4.1 the whole plot area
     * by default captures pointer events in order to show the tooltip, so for
     * tooltip.snap to make sense, stickyTracking must be false. Try it:
     *
     * 10 px by default, 50 px on graph
     *
     */
    private Integer snap = null;

    /**
     * split: BooleanSince 5.0.0 Split the tooltip into one label per series,
     * with the header close to the axis. This is recommended over shared
     * tooltips for charts with multiple line series, generally making them
     * easier to read. Defaults to false. Try it:
     *
     * Split tooltip
     *
     */
    private Boolean split = null;

    /**
     * CSS styles for the tooltip. The tooltip can also be styled through the
     * CSS class .highcharts-tooltip. Defaults to { "color": "#333333",
     * "cursor": "default", "fontSize": "12px", "pointerEvents": "none",
     * "whiteSpace": "nowrap" }. Try it:
     *
     * Greater padding, bold text
     *
     */
    private String style = null;

    /**
     * useHTML: BooleanSince 2.2 Use HTML to render the contents of the tooltip
     * instead of SVG. Using HTML allows advanced formatting like tables and
     * images in the tooltip. It is also recommended for rtl languages as it
     * works around rtl bugs in early Firefox. Defaults to false. Try it:
     *
     * A table for value alignment. Full HTML tooltip.
     *
     */
    private Boolean useHTML = null;

    /**
     * valueDecimals: NumberSince 2.2 How many decimals to show in each series'
     * y value. This is overridable in each series' tooltip options object. The
     * default is to preserve all decimals. Try it:
     *
     * Set decimals, prefix and suffix for the value
     *
     */
    private Integer valueDecimals = null;

    /**
     * A string to prepend to each series' y value. Overridable in each series'
     * tooltip options object. Try it:
     *
     * Set decimals, prefix and suffix for the value
     *
     */
    private String valuePrefix = null;

    /**
     * A string to append to each series' y value. Overridable in each series'
     * tooltip options object. Try it:
     *
     * Set decimals, prefix and suffix for the value
     *
     */
    private String valueSuffix = null;

    /**
     * The format for the date in the tooltip header if the X axis is a datetime
     * axis. The default is a best guess based on the smallest distance between
     * points in the chart. Try it:
     *
     * A different format
     *
     */
    private String xDateFormat = null;


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public Boolean getAnimation() {
        return animation;
    }

    public void setAnimation(Boolean animation) {
        this.animation = animation;
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

    public String getDateTimeLableFormats() {
        return dateTimeLableFormats;
    }

    public void setDateTimeLableFormats(String dateTimeLableFormats) {
        this.dateTimeLableFormats = dateTimeLableFormats;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getFollowPointer() {
        return followPointer;
    }

    public void setFollowPointer(Boolean followPointer) {
        this.followPointer = followPointer;
    }

    public Boolean getFollowTouchMove() {
        return followTouchMove;
    }

    public void setFollowTouchMove(Boolean followTouchMove) {
        this.followTouchMove = followTouchMove;
    }

    public String getFooterFormat() {
        return footerFormat;
    }

    public void setFooterFormat(String footerFormat) {
        this.footerFormat = footerFormat;
    }

    public String getHeaderFormat() {
        return headerFormat;
    }

    public void setHeaderFormat(String headerFormat) {
        this.headerFormat = headerFormat;
    }

    public Integer getHideDelay() {
        return hideDelay;
    }

    public void setHideDelay(Integer hideDelay) {
        this.hideDelay = hideDelay;
    }

    public Integer getPadding() {
        return padding;
    }

    public void setPadding(Integer padding) {
        this.padding = padding;
    }

    public String getPointFormat() {
        return pointFormat;
    }

    public void setPointFormat(String pointFormat) {
        this.pointFormat = pointFormat;
    }

    public String getPointFormatter() {
        return pointFormatter;
    }

    public void setPointFormatter(String pointFormatter) {
        this.pointFormatter = pointFormatter;
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

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Integer getSnap() {
        return snap;
    }

    public void setSnap(Integer snap) {
        this.snap = snap;
    }

    public Boolean getSplit() {
        return split;
    }

    public void setSplit(Boolean split) {
        this.split = split;
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

    public Integer getValueDecimals() {
        return valueDecimals;
    }

    public void setValueDecimals(Integer valueDecimals) {
        this.valueDecimals = valueDecimals;
    }

    public String getValuePrefix() {
        return valuePrefix;
    }

    public void setValuePrefix(String valuePrefix) {
        this.valuePrefix = valuePrefix;
    }

    public String getValueSuffix() {
        return valueSuffix;
    }

    public void setValueSuffix(String valueSuffix) {
        this.valueSuffix = valueSuffix;
    }

    public String getxDateFormat() {
        return xDateFormat;
    }

    public void setxDateFormat(String xDateFormat) {
        this.xDateFormat = xDateFormat;
    }
    
    
    
}
