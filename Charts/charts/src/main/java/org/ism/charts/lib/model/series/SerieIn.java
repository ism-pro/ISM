/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.series;

import java.util.ArrayList;
import org.ism.charts.lib.model.properties.States;
import java.util.List;
import org.ism.charts.lib.model.axis.Point;
import org.ism.charts.lib.model.properties.ChartType;
import org.ism.charts.lib.model.properties.DashStyle;
import org.ism.charts.lib.model.properties.DataLabels;
import org.ism.charts.lib.model.properties.Events;

/**
 * <h1>SerieIn</h1><br>
 * SerieIn class is convenient model for plotOptions model and Multiple serie
 * model
 *
 * @author r.hendrick
 *
 */
public class SerieIn {

    /**
     * A line series. If the type option is not specified, it is inherited from
     * chart.type.
     *
     * For options that apply to multiple series, it is recommended to add them
     * to the pointOptions.series options structure. To apply to all series of
     * this specific type, apply it to plotOptions.line.
     */
    private ChartType type = ChartType.LINE;
    
    
    /**
     * Allow this series' points to be selected by clicking on the markers, bars
     * or pie slices. Defaults to false. Try it:
     *
     * Line, column, pie
     */
    private Boolean allowPointSelect = null;
    /**
     * Enable or disable the initial animation when a series is displayed. The
     * animation can also be set as a configuration object. Please note that
     * this option only applies to the initial animation of the series itself.
     * For other animations, see chart.animation and the animation parameter
     * under the API methods.	The following properties are supported:duration
     * The duration of the animation in milliseconds. easing A string reference
     * to an easing function set on the Math object. See the easing demo. Due to
     * poor performance, animation is disabled in old IE browsers for column
     * charts and polar charts.
     *
     * Defaults to true.
     */
    private Boolean animation = null;
    /**
     * For some series, there is a limit that shuts down initial animation by
     * default when the total number of points in the chart is too high. For
     * example, for a column chart and its derivatives, animation doesn't run if
     * there is more than 250 points totally. To disable this cap, set
     * animationLimit to Infinity.
     */
    private Integer animationLimit = null;

    /**
     * className: StringSince 5.0.0 A class name to apply to the series'
     * graphical elements.
     */
    private String className = null;

    /**
     * colorIndex: NumberSince 5.0.0 Styled mode only. A specific color index to
     * use for the series, so its graphic representations are given the class
     * name highcharts-color- {n}.
     *
     * Defaults to undefined.
     */
    private Integer colorIndex = null;

    /**
     * color: Color The main color or the series. In line type series it applies
     * to the line and the point markers unless otherwise specified. In bar type
     * series it applies to the bars unless a color is specified per point. The
     * default value is pulled from the options.colors array.
     *
     * In styled mode, the series color can be set with the .highcharts-series,
     * .highcharts-color-{n}, .highcharts-{type}-series or
     * .highcharts-series-{n} class, or individual classes given by the
     * className option.
     *
     * Try it:
     *
     * General plot option, one specific series, area color
     */
    private String color = null;

    /**
     * Not specify from example pie
     */
    private Boolean colorByPoint = null;
    /**
     * connectEnds: BooleanSince 2.3.0 Polar charts only. Whether to connect the
     * ends of a line series plot across the extremes. Defaults to true. Try it:
     * Do not connect
     */
    private Boolean connectEnds = null;

    /**
     * connectNulls: Boolean Whether to connect a graph line across null points.
     * Defaults to false. Try it: False by default, true
     */
    private Boolean connectNulls = null;

    /**
     * cropThreshold: NumberSince 2.2 When the series contains less points than
     * the crop threshold, all points are drawn, even if the points fall outside
     * the visible plot area at the current zoom. The advantage of drawing all
     * points (including markers and columns), is that animation is performed on
     * updates. On the other hand, when the series contains more points than the
     * crop threshold, the series datas is cropped to only contain points that
     * fall within the plot area. The advantage of cropping away invisible
     * points is to increase performance on large series. Defaults to 300.
     */
    private Integer cropThreshold = null;

    /**
     * cursor: String You can set the cursor to "pointer" if you have click
     * events attached to the series, to signal to the user that the points and
     * lines can be clicked. Try it: Pointer cursor on line graph, on columns,
     * on scatter markers See also: In styled mode, the series cursor can be set
     * with the same classes as listed under series.color.
     */
    private String cursor = null;

    /**
     * dashStyle: StringSince 2.1 A name for the dash style to use for the
     * graph. Applies only to series type having a graph, like line, spline,
     * area and scatter in case it has a lineWidth. The value for the dashStyle
     * include: Solid ShortDash ShortDot ShortDashDot ShortDashDotDot Dot Dash
     * LongDash DashDot LongDashDot LongDashDotDot Defaults to Solid. Try it:
     * Possible values demonstrated, chart suitable for printing in black and
     * white See also: In styled mode, the stroke dash-array can be set with the
     * same classes as listed under series.color.
     */
    private DashStyle dashStyle = null;

    /**
     * series<line>.datas An array of datas points for the series. For the line
     * series type, points can be given in the following ways: An array of
     * numerical values. In this case, the numerical values will be interpreted
     * as y options. The x values will be automatically calculated, either
     * starting at 0 and incremented by 1, or from pointStart and pointInterval
     * given in the series options. If the axis has categories, these will be
     * used. Example: datas: [0, 5, 3, 5] An array of arrays with 2 values. In
     * this case, the values correspond to x,y. If the first value is a string,
     * it is applied as the name of the point, and the x value is inferred.
     *
     * datas: [ [0, 1], [1, 2], [2, 8] ] An array of objects with named values.
     * The objects are point configuration objects as seen below. If the total
     * number of datas points exceeds the series' turboThreshold, this option is
     * not available.
     *
     * datas: [{ x: 1, y: 10, name: "Point2", color: "#00FF00" }, { x: 1, y: 6,
     * name: "Point1", color: "#FF00FF" }] Try it:
     *
     * Numerical values Arrays of numeric x and y Arrays of datetime x and y
     * Arrays of point.name and y Config objects
     */
    private List<Data> datas = null;

    /**
     * dataLabels Options for the series datas labels, appearing next to each
     * datas point.
     *
     * In styled mode, the datas labels can be styled wtih the
     * .highcharts-datas-label-box and .highcharts-datas-label class names (see
     * example).
     */
    private DataLabels dataLabels = null;

    /**
     * description: StringSince 5.0.0 Requires Accessibility module
     *
     * A description of the series to add to the screen reader information about
     * the series.
     *
     * Defaults to undefined. Try it: Accessible stock chart
     */
    private String description = null;

    /**
     * enableMouseTracking: Boolean Enable or disable the mouse tracking for a
     * specific series. This includes point tooltips and click events on graphs
     * and points. For large datasets it improves performance. Defaults to true.
     * Try it: No mouse tracking
     */
    private Boolean enableMouseTracking = null;

    /**
     * events
     */
    private Events events = null;

    /**
     * getExtremesFromAll: BooleanSince 4.1.6 Whether to use the Y extremes of
     * the total chart width or only the zoomed area when zooming in on parts of
     * the X axis. By default, the Y axis adjusts to the min and max of the
     * visible datas. Cartesian series only. Defaults to false.
     */
    private Boolean getExtremesFromAll = null;

    /**
     * id: StringSince 1.2.0 An id for the series. This can be used after render
     * time to get a pointer to the series object through chart.get(). Try it:
     * Get series by id
     */
    private String id = null;

    /**
     * index: NumberSince 2.3.0 The index of the series in the chart, affecting
     * the internal index in the chart.series array, the visible Z index as well
     * as the order in the legend.
     */
    private Integer index = null;

    /**
     * keys: Array<String>Since 4.1.6 An array specifying which option maps to
     * which key in the datas point array. This makes it convenient to work with
     * unstructured datas arrays from different sources. Try it: An extended
     * datas array with keys See also: series.datas
     */
    private List<String> keys = null;

    /**
     * legendIndex: Number The sequential index of the series in the legend. Try
     * it: Legend in opposite order . See also: legend.reversed,
     * yAxis.reversedStacks
     */
    private Integer legendIndex = null;

    /**
     * lineWidth: Number Pixel with of the graph line. Defaults to 2. Try it:
     * 5px on all series, on one single series See also: In styled mode, the
     * line stroke-width can be set with the .highcharts-graph class name.
     */
    private Integer lineWidth = null;

    /**
     * linecap: String The line cap used for line ends and line joins on the
     * graph. Defaults to round.
     */
    private String linecap = null;

    /**
     * linkedTo: StringSince 3.0 The id of another series to link to.
     * Additionally, the value can be ":previous" to link to the previous
     * series. When two series are linked, only the first one appears in the
     * legend. Toggling the visibility of this also toggles the linked series.
     * Try it: Linked series
     */
    private Integer linkedTo = null;

    /**
     * marker Options for the point markers of line-like series. Properties like
     * fillColor, lineColor and lineWidth define the visual appearance of the
     * markers. Other series types, like column series, don't have markers, but
     * have visual options on the series level instead.
     *
     * In styled mode, the markers can be styled with the .highcharts-point,
     * .highcharts-point-hover and .highcharts-point-select class names.
     */
    private Marker marker = null;

    /**
     * name: String The name of the series as shown in the legend, tooltip etc.
     * Try it: Series name
     */
    private String name = null;

    /**
     * negativeColor: ColorSince 3.0 The color for the parts of the graph or
     * points that are below the threshold. Defaults to null. Try it: Spline,
     * area and column - arearange. See also: In styled mode, a negative color
     * is applied by setting this option to true combined with the
     * .highcharts-negative class name (view live demo).
     */
    private String negativeColor = null;

    /**
     * point Properties for each single point
     */
    private Point point = null;

    /**
     * pointInterval: Number If no x values are given for the points in a
     * series, pointInterval defines the interval of the x values. For example,
     * if a series contains one value every decade starting from year 0, set
     * pointInterval to 10.
     *
     * Since Highcharts 4.1, it can be combined with pointIntervalUnit to draw
     * irregular intervals.
     *
     * Defaults to 1. Try it: Datetime X axis
     */
    private Integer pointInterval = null;

    /**
     * pointIntervalUnit: StringSince 4.1.0 On datetime series, this allows for
     * setting the pointInterval to irregular time units, day, month and year. A
     * day is usually the same as 24 hours, but pointIntervalUnit also takes the
     * DST crossover into consideration when dealing with local time. Combine
     * this option with pointInterval to draw weeks, quarters, 6 months, 10
     * years etc. Try it: One point a month
     */
    private String pointIntervalUnit = null;

    /**
     * pointPlacement: String|NumberSince 2.3.0 Possible values: null, "on",
     * "between".
     *
     * In a column chart, when pointPlacement is "on", the point will not create
     * any padding of the X axis. In a polar column chart this means that the
     * first column points directly north. If the pointPlacement is "between",
     * the columns will be laid out between ticks. This is useful for example
     * for visualising an amount between two points in time or in a certain
     * sector of a polar chart.
     *
     * Since Highcharts 3.0.2, the point placement can also be numeric, where 0
     * is on the axis value, -0.5 is between this value and the previous, and
     * 0.5 is between this value and the next. Unlike the textual options,
     * numeric point placement options won't affect axis padding.
     *
     * Note that pointPlacement needs a pointRange to work. For column series
     * this is computed, but for line-type series it needs to be set.
     *
     * Defaults to null in cartesian charts, "between" in polar charts.
     *
     * Try it: Between in a column chart, numeric placement for custom layout.
     * See also: xAxis.tickmarkPlacement
     */
    private String pointPlacement = null;

    /**
     * pointStart: Number If no x values are given for the points in a series,
     * pointStart defines on what value to start. For example, if a series
     * contains one yearly value starting from 1945, set pointStart to 1945.
     * Defaults to 0. Try it: Linear, datetime X axis
     */
    private Integer pointStart = null;

    /**
     * selected: BooleanSince 1.2.0 Whether to select the series initially. If
     * showCheckbox is true, the checkbox next to the series name will be
     * checked for a selected series. Defaults to false. Try it: One out of two
     * series selected
     */
    private Boolean selected = null;

    /**
     * shadow: Boolean|Object Whether to apply a drop shadow to the graph line.
     * Since 2.3 the shadow can be an object configuration containing color,
     * offsetX, offsetY, opacity and width. Defaults to false. Try it: Shadow
     * enabled
     */
    private Boolean shadow = null;

    /**
     * showCheckbox: BooleanSince 1.2.0 If true, a checkbox is displayed next to
     * the legend item to allow selecting the series. The state of the checkbox
     * is determined by the selected option. Defaults to false. Try it: Show
     * select box
     */
    private Boolean showCheckbox = null;

    /**
     * showInLegend: Boolean Whether to display this particular series or series
     * type in the legend. The default value is true for standalone series,
     * false for linked series. Defaults to true. Try it: One series in the
     * legend, one hidden
     */
    private Boolean showInLegend = null;

    /**
     * softThreshold: BooleanSince 4.1.9 When this is true, the series will not
     * cause the Y axis to cross the zero plane (or threshold option) unless the
     * datas actually crosses the plane.
     *
     * For example, if softThreshold is false, a series of 0, 1, 2, 3 will make
     * the Y axis show negative values according to the minPadding option. If
     * softThreshold is true, the Y axis starts at 0.
     *
     * Defaults to true.
     */
    private Boolean softThreshold = null;

    /**
     * stack: StringSince 2.1 This option allows grouping series in a stacked
     * chart. The stack option can be a string or a number or anything else, as
     * long as the grouped series' stack options match each other. Try it:
     * Stacked and grouped columns
     */
    private String stack = null;

    /**
     * stacking: String Whether to stack the values of each series on top of
     * each other. Possible values are null to disable, "normal" to stack by
     * value or "percent". Try it: Line, column, bar, area with "normal"
     * stacking. Line, column, bar, area with "percent" stacking. See also:
     * yAxis.reversedStacks
     */
    private String stacking = null;

    /**
     * states: plotOptions.series.states A wrapper object for all the series
     * options in specific states.
     */
    private States states = null;

    /**
     * step: StringSince 1.2.5 Whether to apply steps to the line. Possible
     * values are left, center and right. Prior to 2.3.5, only left was
     * supported. Defaults to false. Try it: Different step line options
     */
    private Boolean step = null;

    /**
     * stickyTracking: BooleanSince 2.0 Sticky tracking of mouse events. When
     * true, the mouseOut event on a series isn't triggered until the mouse
     * moves over another series, or out of the plot area. When false, the
     * mouseOut event on a series is triggered when the mouse leaves the area
     * around the series' graph or markers. This also implies the tooltip. When
     * stickyTracking is false and tooltip.shared is false, the tooltip will be
     * hidden when moving the mouse between series. Defaults to true for line
     * and area type series, but to false for columns, pies etc. Defaults to
     * true. Try it: True by default, false
     */
    private Boolean stickyTracking = null;

    /**
     * threshold: NumberSince 3.0 The threshold, also called zero level or base
     * level. For line type series this is only used in conjunction with
     * negativeColor. Defaults to 0. See also: softThreshold.
     */
    private Integer threshold = null;

    /**
     * tooltip: ObjectSince 2.3 A configuration object for the tooltip rendering
     * of each single series. Properties are inherited from tooltip, but only
     * the following properties can be defined on a series level.
     */
    private ToolTip tooltip = null;

    /**
     * turboThreshold: NumberSince 2.2 When a series contains a datas array that
     * is longer than this, only one dimensional arrays of numbers, or two
     * dimensional arrays with x and y values are allowed. Also, only the first
     * point is tested, and the rest are assumed to be the same format. This
     * saves expensive datas checking and indexing in long series. Set it to 0
     * disable. Defaults to 1000.
     */
    private Integer turboThreshold = null;

    /**
     * visible: Boolean Set the initial visibility of the series. Defaults to
     * true. Try it: Two series, one hidden and one visible
     */
    private Boolean visible = null;

    /**
     * xAxis: Number|String When using dual or multiple x axes, this number
     * defines which xAxis the particular series is connected to. It refers to
     * either the axis id or the index of the axis in the xAxis array, with 0
     * being the first. Defaults to 0.
     */
    private Integer xAxis = null;

    /**
     * xIncrement
     */
    public Integer xIncrement = null;
    /**
     * yAxis: Number|String When using dual or multiple y axes, this number
     * defines which yAxis the particular series is connected to. It refers to
     * either the axis id or the index of the axis in the yAxis array, with 0
     * being the first. Defaults to 0. Try it: Apply the column series to the
     * secondary Y axis
     */
    private Integer yAxis = null;

    /**
     * zIndex: Number Define the visual z index of the series. Try it: With no z
     * index, the series defined last are on top, with a z index, the series
     * with the highest z index is on top.
     */
    private Integer zIndex = null;

    /**
     * zoneAxis: StringSince 4.1.0 Defines the Axis on which the zones are
     * applied. Defaults to y. Try it: Zones on the X-Axis See also: zones
     */
    private String zoneAxis = null;

    /**
     * zones: ArraySince 4.1.0 An array defining zones within a series. Zones
     * can be applied to the X axis, Y axis or Z axis for bubbles, according to
     * the zoneAxis option.
     *
     * In styled mode, the color zones are styled with the .highcharts-zone-{n}
     * class, or custom classed from the className option (view live demo).
     *
     * Try it: Color zones See also: zoneAxis
     */
    private Zones zones = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public ChartType getType() {
        return type;
    }

    public void setType(ChartType type) {
        this.type = type;
    }

    public Boolean getAllowPointSelect() {
        return allowPointSelect;
    }

    public void setAllowPointSelect(Boolean allowPointSelect) {
        this.allowPointSelect = allowPointSelect;
    }

    public Boolean getAnimation() {
        return animation;
    }

    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }

    public Integer getAnimationLimit() {
        return animationLimit;
    }

    public void setAnimationLimit(Integer animationLimit) {
        this.animationLimit = animationLimit;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getColorIndex() {
        return colorIndex;
    }

    public void setColorIndex(Integer colorIndex) {
        this.colorIndex = colorIndex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getColorByPoint() {
        return colorByPoint;
    }

    public void setColorByPoint(Boolean colorByPoint) {
        this.colorByPoint = colorByPoint;
    }

    public Boolean getConnectEnds() {
        return connectEnds;
    }

    public void setConnectEnds(Boolean connectEnds) {
        this.connectEnds = connectEnds;
    }

    public Boolean getConnectNulls() {
        return connectNulls;
    }

    public void setConnectNulls(Boolean connectNulls) {
        this.connectNulls = connectNulls;
    }

    public Integer getCropThreshold() {
        return cropThreshold;
    }

    public void setCropThreshold(Integer cropThreshold) {
        this.cropThreshold = cropThreshold;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public DashStyle getDashStyle() {
        return dashStyle;
    }

    public void setDashStyle(DashStyle dashStyle) {
        this.dashStyle = dashStyle;
    }

    public List<Data> getData() {
        return datas;
    }

    public void setData(List<Data> datas) {
        this.datas = datas;
    }

    public DataLabels getDataLabels() {
        return dataLabels;
    }

    public void setDataLabels(DataLabels dataLabels) {
        this.dataLabels = dataLabels;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnableMouseTracking() {
        return enableMouseTracking;
    }

    public void setEnableMouseTracking(Boolean enableMouseTracking) {
        this.enableMouseTracking = enableMouseTracking;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Boolean getGetExtremesFromAll() {
        return getExtremesFromAll;
    }

    public void setGetExtremesFromAll(Boolean getExtremesFromAll) {
        this.getExtremesFromAll = getExtremesFromAll;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public Integer getLegendIndex() {
        return legendIndex;
    }

    public void setLegendIndex(Integer legendIndex) {
        this.legendIndex = legendIndex;
    }

    public Integer getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Integer lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getLinecap() {
        return linecap;
    }

    public void setLinecap(String linecap) {
        this.linecap = linecap;
    }

    public Integer getLinkedTo() {
        return linkedTo;
    }

    public void setLinkedTo(Integer linkedTo) {
        this.linkedTo = linkedTo;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNegativeColor() {
        return negativeColor;
    }

    public void setNegativeColor(String negativeColor) {
        this.negativeColor = negativeColor;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Integer getPointInterval() {
        return pointInterval;
    }

    public void setPointInterval(Integer pointInterval) {
        this.pointInterval = pointInterval;
    }

    public String getPointIntervalUnit() {
        return pointIntervalUnit;
    }

    public void setPointIntervalUnit(String pointIntervalUnit) {
        this.pointIntervalUnit = pointIntervalUnit;
    }

    public String getPointPlacement() {
        return pointPlacement;
    }

    public void setPointPlacement(String pointPlacement) {
        this.pointPlacement = pointPlacement;
    }

    public Integer getPointStart() {
        return pointStart;
    }

    public void setPointStart(Integer pointStart) {
        this.pointStart = pointStart;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getShadow() {
        return shadow;
    }

    public void setShadow(Boolean shadow) {
        this.shadow = shadow;
    }

    public Boolean getShowCheckbox() {
        return showCheckbox;
    }

    public void setShowCheckbox(Boolean showCheckbox) {
        this.showCheckbox = showCheckbox;
    }

    public Boolean getShowInLegend() {
        return showInLegend;
    }

    public void setShowInLegend(Boolean showInLegend) {
        this.showInLegend = showInLegend;
    }

    public Boolean getSoftThreshold() {
        return softThreshold;
    }

    public void setSoftThreshold(Boolean softThreshold) {
        this.softThreshold = softThreshold;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getStacking() {
        return stacking;
    }

    public void setStacking(String stacking) {
        this.stacking = stacking;
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    public Boolean getStep() {
        return step;
    }

    public void setStep(Boolean step) {
        this.step = step;
    }

    public Boolean getStickyTracking() {
        return stickyTracking;
    }

    public void setStickyTracking(Boolean stickyTracking) {
        this.stickyTracking = stickyTracking;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public ToolTip getTooltip() {
        return tooltip;
    }

    public void setTooltip(ToolTip tooltip) {
        this.tooltip = tooltip;
    }

    public Integer getTurboThreshold() {
        return turboThreshold;
    }

    public void setTurboThreshold(Integer turboThreshold) {
        this.turboThreshold = turboThreshold;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getxAxis() {
        return xAxis;
    }

    public void setxAxis(Integer xAxis) {
        this.xAxis = xAxis;
    }

    public Integer getxIncrement() {
        return xIncrement;
    }

    public void setxIncrement(Integer xIncrement) {
        this.xIncrement = xIncrement;
    }

    public Integer getyAxis() {
        return yAxis;
    }

    public void setyAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

    public String getZoneAxis() {
        return zoneAxis;
    }

    public void setZoneAxis(String zoneAxis) {
        this.zoneAxis = zoneAxis;
    }

    public Zones getZones() {
        return zones;
    }

    public void setZones(Zones zones) {
        this.zones = zones;
    }

}
