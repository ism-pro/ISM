/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.axis;

import java.util.ArrayList;
import java.util.List;
import org.ism.charts.lib.model.properties.Align;
import org.ism.charts.lib.model.properties.DashStyle;

/**
 * yAxis The Y axis or value axis. Normally this is the vertical axis, though if
 * the chart is inverted this is the horizontal axis. In case of multiple axes,
 * the yAxis node is an array of configuration objects.
 *
 * See the Axis object for programmatic access to the axis.
 *
 * @author r.hendrick
 */
public class YAxis {

    /**
     * allowDecimals: Boolean <br>
     * Since 2.0 Whether to allow decimals in this axis' ticks. When counting
     * integers, like persons or hits on a web page, decimals should be avoided
     * in the labels. Defaults to true. Try it: True by default (unwanted for
     * this type of data), false See also: minTickInterval
     */
    private Boolean allowDecimals = true;

    /**
     * alternateGridColor: Color When using an alternate grid color, a band is
     * painted across the plot area between every other grid line. Try it:
     * Alternate grid color on the Y axis
     */
    private String alternateGridColor = null;

    /**
     * angle: NumberSince 4.2.7 In a polar chart, this is the angle of the Y
     * axis in degrees, where 0 is up and 90 is right. The angle determines the
     * position of the axis line and the labels, though the coordinate system is
     * unaffected. Defaults to 0. Try it: Dual axis polar chart
     */
    private Integer angle = null;

    /**
     * breaks: ArraySince 4.1.0 An array defining breaks in the axis, the
     * sections defined will be left out and all the points shifted closer to
     * each other. Requires that the broken-axis.js module is loaded. Try it:
     * Simple break, advanced with callback
     */
    private Breaks breaks = null;

    /**
     * categories: Array\<String\>
     * If categories are present for the xAxis, names are used instead of
     * numbers for that axis. Since Highcharts 3.0, categories can also be
     * extracted by giving each point a name and setting axis type to category.
     * However, if you have multiple series, best practice remains defining the
     * categories array.
     *
     * Example:
     *
     * categories: ['Apples', 'Bananas', 'Oranges'] Defaults to null Try it:
     * With and without categories
     */
    List<String> categories = null;

    /**
     * ceiling: Number<br>
     * Since 4.0 The highest allowed value for automatically computed axis
     * extremes. Try it: Floor and ceiling See also: floor
     */
    private Integer ceiling = null;

    /**
     * className: String<br>
     * Since 5.0.0 A class name that opens for styling the axis by CSS,
     * especially in Highcharts styled mode. The class name is applied to group
     * elements for the grid, axis elements and labels. Try it: Multiple axes
     * with separate styling.
     */
    private String className = null;

    /**
     * crosshair: Boolean|ObjectSince 4.1 Configure a crosshair that follows
     * either the mouse pointer or the hovered point.
     *
     * In styled mode, the crosshairs are styled in the .highcharts-crosshair,
     * .highcharts-crosshair-thin or .highcharts-xaxis-category classes.
     *
     * Defaults to false. Try it: Crosshair on both axes
     */
    private Crosshair crosshair = null;

    /**
     * dateTimeLabelFormats: Object For a datetime axis, the scale will
     * automatically adjust to the appropriate unit. This member gives the
     * default string representations used for each unit. For intermediate
     * values, different units may be used, for example the day unit can be used
     * on midnight and hour unit be used for intermediate values on the same
     * axis. For an overview of the replacement codes, see dateFormat. Defaults
     * to:<br>
     * { <br>
     * millisecond: '%H:%M:%S.%L',<br>
     * second: '%H:%M:%S',<br>
     * minute: '%H:%M',<br>
     * hour: '%H:%M',<br>
     * day: '%e. %b',<br>
     * week: '%e. %b',<br>
     * month: '%b \'%y',<br>
     * year: '%Y'<br>
     * }<br>
     * Try it: Different day format on X axis
     */
    private String dateTimeLabelFormats = null;//"{millisecond:'%H:%M:%S.%L',second:'%H:%M:%S',minute:'%H:%M',hour:'%H:%M',day:'%e. %b',week:'%e. %b',month:'%b \\'%y',year:'%Y'}";

    /**
     * description: String<br>
     * Since 5.0.0 Requires Accessibility module
     *
     * Description of the axis to screen reader users. Defaults to undefined.
     * Try it: Accessible complex chart
     */
    private String description = null;

    /**
     * endOnTick: Boolean<br>
     * Since 1.2.0 Whether to force the axis to end on a tick. Use this option
     * with the maxPadding option to control the axis end. Defaults to true. Try
     * it: True by default and false for Y axis, false for logarithmic Y axis
     */
    private Boolean endOnTick = true;

    /**
     * floor: Number<br>
     * Since 4.0 The lowest allowed value for automatically computed axis
     * extremes. Defaults to null. <br>
     * Try it: Floor and ceiling See also: ceiling
     */
    private Integer floor = null;

    /**
     * gridLineColor: Color <br>
     * Color of the grid lines extending the ticks across the plot area.
     *
     * In styled mode, the stroke is given in the .highcharts-grid-line class.
     *
     * Defaults to #e6e6e6. Try it: Green lines. Styled mode.
     */
    private String gridLineColor = "#e6e6e6";

    /**
     * gridLineDashStyle: String <br>
     * Since 1.2 The dash or dot style of the grid lines. For possible values,
     * see this demonstration. <br>
     * Defaults to Solid. <br>
     * Try it: Long dashes
     */
    private DashStyle gridLineDashStyle = DashStyle.SOLID;

    /**
     * gridLineInterpolation: String Polar charts only. Whether the grid lines
     * should draw as a polygon with straight lines between categories, or as
     * circles. Can be either circle or polygon. Defaults to null. Try it:
     * Polygon grid lines, circle and polygon
     */
    private String gridLineInterpolation = null;

    /**
     * gridLineWidth: Number <br>
     * The width of the grid lines extending the ticks across the plot area.
     * Defaults to 0. <br>
     * Try it: <br>
     * 2px lines
     */
    private Integer gridLineWidth = 1;

    /**
     * gridZIndex: Number <br>
     * The Z index of the grid lines. Defaults to 1. <br>
     * Try it: <br>
     * A Z index of 4 renders the grid above the graph
     */
    private Integer gridZIndex = 1;

    /**
     * id: String <br>Since 1.2.0 An id for the axis. This can be used after
     * render time to get a pointer to the axis object through chart.get().
     * <br>
     * Try it: <br>
     * Get the object
     */
    private String id = null;

    /**
     * The axis labels show the number or category for each tick.
     */
    private Labels labels = null;

    /**
     * lineColor: Color <br>
     * The color of the line marking the axis itself. <br>
     *
     * In styled mode, the line stroke is given in the .highcharts-axis-line or
     * .highcharts-xaxis-line class.
     * <br>
     * Defaults to #ccd6eb. <br>
     * Try it: <br>
     * A red line on Y axis. Axes in styled mode.
     */
    private String lineColor = "#ccd6eb";

    /**
     * lineWidth: Number <br>
     * The width of the line marking the axis itself. Defaults to 1.
     * <br>
     * Try it: <br>
     * A 1px line on Y axis
     */
    private Integer lineWidth = 0;

    /**
     * linkedTo: Number Since 2.0.2<br>
     * Index of another axis that this axis is linked to. When an axis is linked
     * to a master axis, it will take the same extremes as the master, but as
     * assigned by min or max or by setExtremes. It can be used to show
     * additional info, or to ease reading the chart by duplicating the scales.
     * <br>
     * Try it:<br>
     * Different string formats of the same date, Y values on both sides
     */
    private Integer linkedTo = null;

    /**
     * max: Number The maximum value of the axis. If null, the max value is
     * automatically calculated. If the endOnTick option is true, the max value
     * might be rounded up.
     *
     * If a tickAmount is set, the axis may be extended beyond the set max in
     * order to reach the given number of ticks. The same may happen in a chart
     * with multiple axes, determined by chart.alignTicks, where a tickAmount is
     * applied internally.
     *
     * Try it: Y axis max of 200, Y axis max on logarithmic axis
     */
    private Integer max = null;

    /**
     * maxColor: ColorSince 4.0 Solid gauge only. Unless stops are set, the
     * color to represent the maximum value of the Y axis. Defaults to #003399.
     * Try it: Min and max colors
     */
    private String maxColor = "#003399";

    /**
     * maxPadding: NumberSince 1.2.0 Padding of the max value relative to the
     * length of the axis. A padding of 0.05 will make a 100px axis 5px longer.
     * This is useful when you don't want the highest data value to appear on
     * the edge of the plot area. When the axis' max option is set or a max
     * extreme is set using axis.setExtremes(), the maxPadding will be ignored.
     * Defaults to 0.01. Try it: Max padding of 0.25 on y axis
     */
    private Double maxPadding = 0.01;

    /**
     * min: Number The minimum value of the axis. If null the min value is
     * automatically calculated. If the startOnTick option is true, the min
     * value might be rounded down. Try it: Y axis min of -50 with startOnTick
     * to false, -50 with startOnTick true by default
     */
    private Integer min = null;

    /**
     * minColor: ColorSince 4.0 Solid gauge only. Unless stops are set, the
     * color to represent the minimum value of the Y axis. Defaults to #e6ebf5.
     * Try it: Min and max color
     */
    private String minColor = "#e6ebf5";

    /**
     * minPadding: NumberSince 1.2.0 Padding of the min value relative to the
     * length of the axis. A padding of 0.05 will make a 100px axis 5px longer.
     * This is useful when you don't want the lowest data value to appear on the
     * edge of the plot area. When the axis' min option is set or a min extreme
     * is set using axis.setExtremes(), the minPadding will be ignored. Defaults
     * to 0.01. Try it: Min padding of 0.2
     */
    private Double minPadding = 0.05;

    /**
     * minRange: Number The minimum range to display on this axis. The entire
     * axis will not be allowed to span over a smaller interval than this. For
     * example, for a datetime axis the main unit is milliseconds. If minRange
     * is set to 3600000, you can't zoom in more than to one hour.
     *
     * The default minRange for the x axis is five times the smallest interval
     * between any of the data points.
     *
     * On a logarithmic axis, the unit for the minimum range is the power. So a
     * minRange of 1 means that the axis can be zoomed to 10-100, 100-1000,
     * 1000-10000 etc.
     *
     * Note that the minPadding, maxPadding, startOnTick and endOnTick settings
     * also affect how the extremes of the axis are computed.
     *
     * Try it: Minimum range of 5
     */
    private Integer minRange = null;

    /**
     * minTickInterval: NumberSince 2.3.0 The minimum tick interval allowed in
     * axis values. For example on zooming in on an axis with daily data, this
     * can be used to prevent the axis from showing hours. Defaults to the
     * closest distance between two points on the axis.
     */
    private Integer minTickInterval = null;

    /**
     * minorGridLineColor: Color Color of the minor, secondary grid lines.
     *
     * In styled mode, the stroke width is given in the
     * .highcharts-minor-grid-line class.
     *
     * Defaults to #f2f2f2. Try it: Bright grey lines from Y axis. Styled mode.
     */
    private String minorGridLineColor = "#f2f2f2";

    /**
     * minorGridLineDashStyle: StringSince 1.2 The dash or dot style of the
     * minor grid lines. For possible values, see this demonstration. Defaults
     * to Solid. Try it: Long dashes on minor grid lines
     */
    private DashStyle minorGridLineDashStyle = DashStyle.SOLID;

    /**
     * minorGridLineWidth: Number Width of the minor, secondary grid lines.
     *
     * In styled mode, the stroke width is given in the .highcharts-grid-line
     * class.
     *
     * Defaults to 1. Try it: 2px lines from Y axis . Styled mode.
     */
    private Integer minorGridLineWidth = 1;

    /**
     * minorTickColor: Color Color for the minor tick marks. Defaults to
     * #999999. Try it: Black tick marks on Y axis
     */
    private String minorTickColor = "#999999";

    /**
     * minorTickInterval: String|Number Tick interval in scale units for the
     * minor ticks. On a linear axis, if "auto", the minor tick interval is
     * calculated as a fifth of the tickInterval. If null, minor ticks are not
     * shown.
     *
     * On logarithmic axes, the unit is the power of the value. For example,
     * setting the minorTickInterval to 1 puts one tick on each of 0.1, 1, 10,
     * 100 etc. Setting the minorTickInterval to 0.1 produces 9 ticks between 1
     * and 10, 10 and 100 etc. A minorTickInterval of "auto" on a log axis
     * results in a best guess, attempting to enter approximately 5 minor ticks
     * between each major tick.
     *
     * If user settings dictate minor ticks to become too dense, they don't make
     * sense, and will be ignored to prevent performance problems.
     *
     * On axes using categories, minor ticks are not supported.
     *
     * Try it: Null by default, "auto" on linear Y axis, 5 units on linear Y
     * axis, "auto" on logarithmic Y axis, 0.1 on logarithmic Y axis.
     */
    private String minorTickInterval = null;

    /**
     * minorTickLength: Number The pixel length of the minor tick marks.
     * Defaults to 2. Try it: 10px on Y axis
     */
    private Integer minorTickLength = 2;

    /**
     * minorTickPosition: String The position of the minor tick marks relative
     * to the axis line. Can be one of inside and outside. Defaults to outside.
     * Try it: Outside by default, inside
     */
    private Align minorTickPosition = Align.OUTSIDE;

    /**
     * minorTickWidth: Number The pixel width of the minor tick mark. Defaults
     * to 0. Try it: 3px width
     */
    private Integer minorTickWidth = 0;

    /**
     * offset: Number The distance in pixels from the plot area to the axis
     * line. A positive offset moves the axis with it's line, labels and ticks
     * away from the plot area. This is typically used when two or more axes are
     * displayed on the same side of the plot. With multiple axes the offset is
     * dynamically adjusted to avoid collision, this can be overridden by
     * setting offset explicitly. Defaults to 0. Try it: Y axis offset of 70,
     * Axes positioned in the center of the plot
     */
    private Integer offset = 0;

    /**
     * opposite: Boolean Whether to display the axis on the opposite side of the
     * normal. The normal is on the left side for vertical axes and bottom for
     * horizontal, so the opposite sides will be right and top respectively.
     * This is typically used with dual or multiple axes. Defaults to false. Try
     * it: Secondary Y axis opposite
     */
    private Boolean opposite = false;

    /**
     * An array of colored bands stretching across the plot area marking an
     * interval on the axis.
     *
     * In a gauge, a plot band on the Y axis (value axis) will stretch along the
     * perimeter of the gauge.
     *
     * In styled mode, the plot bands are styled by the .highcharts-plot-band
     * class in addition to the className option.
     */
    private PlotBands plotBands = null;

    /**
     * plotLines: Array<Object>
     * An array of lines stretching across the plot area, marking a specific
     * value on one of the axes.
     *
     * In styled mode, the plot lines are styled by the .highcharts-plot-line
     * class in addition to the className option.
     */
    private PlotLines plotLines = null;

    /**
     * reversed: Boolean Whether to reverse the axis so that the highest number
     * is closest to the origin. If the chart is inverted, the x axis is
     * reversed by default. Defaults to false. Try it: Reversed Y axis
     */
    private Boolean reversed = false;

    /**
     * reversedStacks: BooleanSince 3.0.10 If true, the first series in a stack
     * will be drawn on top in a positive, non-reversed Y axis. If false, the
     * first series is in the base of the stack. Defaults to true. Try it:
     * Non-reversed stacks.
     */
    private Boolean reversedStacks = true;

    /**
     * showEmpty: BooleanSince 1.1 Whether to show the axis line and title when
     * the axis has no data. Defaults to true. Try it: When clicking the legend
     * to hide series, one axis preserves line and title, the other doesn't
     */
    private Boolean showEmpty = true;

    /**
     * showFirstLabel: Boolean Whether to show the first tick label. Defaults to
     * true. Try it: Set to false on X axis
     */
    private Boolean showFirstLabel = true;

    /**
     * showLastLabel: Boolean Whether to show the last tick label. Defaults to
     * true. Try it: Set to true on X axis
     */
    private Boolean showLastLabel = true;

    /**
     * softMax: Number Since 5.0.1 A soft maximum for the axis. If the series
     * data maximum is less than this, the axis will stay at this maximum, but
     * if the series data maximum is higher, the axis will flex to show all
     * data. Try it: Soft min and max
     */
    private Integer softMax = null;

    /**
     * softMin: NumberSince 5.0.1 A soft minimum for the axis. If the series
     * data minimum is greater than this, the axis will stay at this minimum,
     * but if the series data minimum is lower, the axis will flex to show all
     * data. Try it: Soft min and max
     */
    private Integer softMin = null;

    /**
     * stackLabels The stack labels show the total value for each bar in a
     * stacked column or bar chart. The label will be placed on top of positive
     * columns and below negative columns. In case of an inverted column chart
     * or a bar chart the label is placed to the right of positive bars and to
     * the left of negative bars.
     */
    private StackLabels stackLabels = null;

    /**
     * startOfWeek: Number For datetime axes, this decides where to put the tick
     * between weeks. 0 = Sunday, 1 = Monday. Defaults to 1. Try it: Monday by
     * default, Sunday
     */
    private Integer startOfWeek = 1;

    /**
     * startOnTick: Boolean Since 1.2.0 Whether to force the axis to start on a
     * tick. Use this option with the minPadding option to control the axis
     * start. Defaults to false. Try it: False by default, true on X axis
     */
    private Boolean startOnTick = false;

    /**
     * tickAmount: Number Since 4.1.0 The amount of ticks to draw on the axis.
     * This opens up for aligning the ticks of multiple charts or panes within a
     * chart. This option overrides the tickPixelInterval option.
     *
     * This option only has an effect on linear axes. Datetime, logarithmic or
     * category axes are not affected.
     *
     * Try it: 8 ticks on Y axis
     */
    private Integer tickAmount = null;

    /**
     * tickColor: Color Color for the main tick marks.
     *
     * In styled mode, the stroke is given in the .highcharts-tick class.
     *
     * Defaults to #ccd6eb. Try it: Red ticks on X axis. Styled mode.
     */
    private String tickColor = "#ccd6eb";

    /**
     * tickInterval: Number The interval of the tick marks in axis units. When
     * null, the tick interval is computed to approximately follow the
     * tickPixelInterval on linear and datetime axes. On categorized axes, a
     * null tickInterval will default to 1, one category. Note that datetime
     * axes are based on milliseconds, so for example an interval of one day is
     * expressed as 24 * 3600 * 1000.
     *
     * On logarithmic axes, the tickInterval is based on powers, so a
     * tickInterval of 1 means one tick on each of 0.1, 1, 10, 100 etc. A
     * tickInterval of 2 means a tick of 0.1, 10, 1000 etc. A tickInterval of
     * 0.2 puts a tick on 0.1, 0.2, 0.4, 0.6, 0.8, 1, 2, 4, 6, 8, 10, 20, 40
     * etc.
     *
     * If the tickInterval is too dense for labels to be drawn, Highcharts may
     * remove ticks.
     *
     * If the chart has multiple axes, the alignTicks option may interfere with
     * the tickInterval setting.
     *
     * Try it: Tick interval of 5 on a linear axis See also: tickPixelInterval,
     * tickPositions, tickPositioner
     */
    private Integer tickInterval = null;

    /**
     * tickLength: Number The pixel length of the main tick marks. Defaults to
     * 10. Try it: 20 px tick length on the X axis
     */
    private Integer tickLength = 10;

    /**
     * tickPixelInterval: Number If tickInterval is null this option sets the
     * approximate pixel interval of the tick marks. Not applicable to
     * categorized axis.
     *
     * The tick interval is also influenced by the minTickInterval option, that,
     * by default prevents ticks from being denser than the data points.
     *
     * Defaults to 72 for the Y axis and 100 for the X axis.
     *
     * Try it: 50 px on X axis See also: tickInterval, tickPositioner,
     * tickPositions-
     */
    private Integer tickPixelInterval = null;

    /**
     * tickPosition: String The position of the major tick marks relative to the
     * axis line. Can be one of inside and outside. Defaults to outside. Try it:
     * "outside" by default, "inside" on X axis
     */
    private Align tickPosition = Align.OUTSIDE;

    /**
     *
     */
    //private TickPositioner tickPositioner  = null;
    
    /**
     * tickPositions: Array<Number>
     * An array defining where the ticks are laid out on the axis. This
     * overrides the default behaviour of tickPixelInterval and tickInterval.
     * Try it: Demo of tickPositions and tickPositioner See also: tickPositioner
     */
    private List<Integer> tickPositions = null;

    /**
     * tickWidth: Number <br>
     * tickWidth: Number The pixel width of the major tick marks.
     *
     * In styled mode, the stroke width is given in the .highcharts-tick class.
     *
     * Defaults to 1. Try it: 10 px width. Styled mode.
     */
    private Integer tickWidth = 0;

    /**
     * tickmarkPlacement: String<br>
     * For categorized axes only. If on the tick mark is placed in the center of
     * the category, if between the tick mark is placed between categories. The
     * default is between if the tickInterval is 1, else on. Defaults to null.
     * <br>
     * Try it:<br>
     * "between" by default, "on"
     */
    private Align tickmarkPlacement = null;

    /**
     * Specify axis title
     */
    private AxisTitle title = null;

    /**
     * type: String<br>
     * The type of axis. Can be one of linear, logarithmic, datetime or
     * category. In a datetime axis, the numbers are given in milliseconds, and
     * tick marks are placed on appropriate values like full hours or days. In a
     * category axis, the point names of the chart's series are used for
     * categories, if not a categories array is defined. Defaults to linear. Try
     * it:<br>
     * Linear. <br>
     * Type datetime with regular intervals.<br>
     * Type datetime with irregular intervals.<br>
     * Logarithmic.<br>
     * Logarithmic with minor grid lines.<br>
     * Logarithmic on two axes.<br>
     * Logarithmic with extension to emulate negative values.<br>
     */
    private AxisType type = AxisType.LINEAR;

    /**
     * uniqueNames: Boolean Since 4.2.7 <br>
     * Applies only when the axis type is category. When uniqueNames is true,
     * points are placed on the X axis according to their names. If the same
     * point name is repeated in the same or another series, the point is placed
     * on the same X position as other points of the same name. When uniqueNames
     * is false, the points are laid out in increasing X positions regardless of
     * their names, and the X axis category will take the name of the last point
     * in each position. Defaults to true. Try it: <br>
     * True by default, false
     */
    private Boolean uniqueNames = true;

    /**
     * units: Array Datetime axis only. An array determining what time intervals
     * the ticks are allowed to fall on. Each array item is an array where the
     * first value is the time unit and the second value another array of
     * allowed multiples. Defaults to: units: [[ 'millisecond', // unit name [1,
     * 2, 5, 10, 20, 25, 50, 100, 200, 500] // allowed multiples ], [ 'second',
     * [1, 2, 5, 10, 15, 30] ], [ 'minute', [1, 2, 5, 10, 15, 30] ], [ 'hour',
     * [1, 2, 3, 4, 6, 8, 12] ], [ 'day', [1] ], [ 'week', [1] ], [ 'month', [1,
     * 3, 6] ], [ 'year', null ]]
     */
    private String units = null;
    /**
     * visible: Boolean Since 4.1.9 <br>
     * Whether axis, including axis title, line, ticks and labels, should be
     * visible. Defaults to true. Try it: Toggle axis visibility.
     */
    private Boolean visible = true;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public Boolean getAllowDecimals() {
        return allowDecimals;
    }

    public void setAllowDecimals(Boolean allowDecimals) {
        this.allowDecimals = allowDecimals;
    }

    public String getAlternateGridColor() {
        return alternateGridColor;
    }

    public void setAlternateGridColor(String alternateGridColor) {
        this.alternateGridColor = alternateGridColor;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Breaks getBreaks() {
//        if (breaks == null) {
//            breaks = new Breaks();
//        }
        return breaks;
    }

    public void setBreaks(Breaks breaks) {
        this.breaks = breaks;
    }

    public List<String> getCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getCeiling() {
        return ceiling;
    }

    public void setCeiling(Integer ceiling) {
        this.ceiling = ceiling;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Crosshair getCrosshair() {
//        if (crosshair == null) {
//            crosshair = new Crosshair();
//        }
        return crosshair;
    }

    public void setCrosshair(Crosshair crosshair) {
        this.crosshair = crosshair;
    }

    public String getDateTimeLabelFormats() {
        return dateTimeLabelFormats;
    }

    public void setDateTimeLabelFormats(String dateTimeLabelFormats) {
        this.dateTimeLabelFormats = dateTimeLabelFormats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEndOnTick() {
        return endOnTick;
    }

    public void setEndOnTick(Boolean endOnTick) {
        this.endOnTick = endOnTick;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getGridLineColor() {
        return gridLineColor;
    }

    public void setGridLineColor(String gridLineColor) {
        this.gridLineColor = gridLineColor;
    }

    public DashStyle getGridLineDashStyle() {
        return gridLineDashStyle;
    }

    public void setGridLineDashStyle(DashStyle gridLineDashStyle) {
        this.gridLineDashStyle = gridLineDashStyle;
    }

    public String getGridLineInterpolation() {
        return gridLineInterpolation;
    }

    public void setGridLineInterpolation(String gridLineInterpolation) {
        this.gridLineInterpolation = gridLineInterpolation;
    }

    public Integer getGridLineWidth() {
        return gridLineWidth;
    }

    public void setGridLineWidth(Integer gridLineWidth) {
        this.gridLineWidth = gridLineWidth;
    }

    public Integer getGridZIndex() {
        return gridZIndex;
    }

    public void setGridZIndex(Integer gridZIndex) {
        this.gridZIndex = gridZIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Labels getLabels() {
//        if (labels == null) {
//            labels = new Labels();
//        }
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public Integer getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Integer lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Integer getLinkedTo() {
        return linkedTo;
    }

    public void setLinkedTo(Integer linkedTo) {
        this.linkedTo = linkedTo;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getMaxColor() {
        return maxColor;
    }

    public void setMaxColor(String maxColor) {
        this.maxColor = maxColor;
    }

    public Double getMaxPadding() {
        return maxPadding;
    }

    public void setMaxPadding(Double maxPadding) {
        this.maxPadding = maxPadding;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getMinColor() {
        return minColor;
    }

    public void setMinColor(String minColor) {
        this.minColor = minColor;
    }

    public Double getMinPadding() {
        return minPadding;
    }

    public void setMinPadding(Double minPadding) {
        this.minPadding = minPadding;
    }

    public Integer getMinRange() {
        return minRange;
    }

    public void setMinRange(Integer minRange) {
        this.minRange = minRange;
    }

    public Integer getMinTickInterval() {
        return minTickInterval;
    }

    public void setMinTickInterval(Integer minTickInterval) {
        this.minTickInterval = minTickInterval;
    }

    public String getMinorGridLineColor() {
        return minorGridLineColor;
    }

    public void setMinorGridLineColor(String minorGridLineColor) {
        this.minorGridLineColor = minorGridLineColor;
    }

    public DashStyle getMinorGridLineDashStyle() {
        return minorGridLineDashStyle;
    }

    public void setMinorGridLineDashStyle(DashStyle minorGridLineDashStyle) {
        this.minorGridLineDashStyle = minorGridLineDashStyle;
    }

    public Integer getMinorGridLineWidth() {
        return minorGridLineWidth;
    }

    public void setMinorGridLineWidth(Integer minorGridLineWidth) {
        this.minorGridLineWidth = minorGridLineWidth;
    }

    public String getMinorTickColor() {
        return minorTickColor;
    }

    public void setMinorTickColor(String minorTickColor) {
        this.minorTickColor = minorTickColor;
    }

    public String getMinorTickInterval() {
        return minorTickInterval;
    }

    public void setMinorTickInterval(String minorTickInterval) {
        this.minorTickInterval = minorTickInterval;
    }

    public Integer getMinorTickLength() {
        return minorTickLength;
    }

    public void setMinorTickLength(Integer minorTickLength) {
        this.minorTickLength = minorTickLength;
    }

    public Align getMinorTickPosition() {
        return minorTickPosition;
    }

    public void setMinorTickPosition(Align minorTickPosition) {
        this.minorTickPosition = minorTickPosition;
    }

    public Integer getMinorTickWidth() {
        return minorTickWidth;
    }

    public void setMinorTickWidth(Integer minorTickWidth) {
        this.minorTickWidth = minorTickWidth;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getOpposite() {
        return opposite;
    }

    public void setOpposite(Boolean opposite) {
        this.opposite = opposite;
    }

    public PlotBands getPlotBands() {
//        if (plotBands == null) {
//            plotBands = new PlotBands();
//        }
        return plotBands;
    }

    public void setPlotBands(PlotBands plotBands) {
        this.plotBands = plotBands;
    }

    public PlotLines getPlotLines() {
//        if (plotLines == null) {
//            plotLines = new PlotLines();
//        }
        return plotLines;
    }

    public void setPlotLines(PlotLines plotLines) {
        this.plotLines = plotLines;
    }

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    public Boolean getReversedStacks() {
        return reversedStacks;
    }

    public void setReversedStacks(Boolean reversedStacks) {
        this.reversedStacks = reversedStacks;
    }

    public Boolean getShowEmpty() {
        return showEmpty;
    }

    public void setShowEmpty(Boolean showEmpty) {
        this.showEmpty = showEmpty;
    }

    public Boolean getShowFirstLabel() {
        return showFirstLabel;
    }

    public void setShowFirstLabel(Boolean showFirstLabel) {
        this.showFirstLabel = showFirstLabel;
    }

    public Boolean getShowLastLabel() {
        return showLastLabel;
    }

    public void setShowLastLabel(Boolean showLastLabel) {
        this.showLastLabel = showLastLabel;
    }

    public Integer getSoftMax() {
        return softMax;
    }

    public void setSoftMax(Integer softMax) {
        this.softMax = softMax;
    }

    public Integer getSoftMin() {
        return softMin;
    }

    public void setSoftMin(Integer softMin) {
        this.softMin = softMin;
    }

    public StackLabels getStackLabels() {
        return stackLabels;
    }

    public void setStackLabels(StackLabels stackLabels) {
        this.stackLabels = stackLabels;
    }

    
    
    public Integer getStartOfWeek() {
        return startOfWeek;
    }

    public void setStartOfWeek(Integer startOfWeek) {
        this.startOfWeek = startOfWeek;
    }

    public Boolean getStartOnTick() {
        return startOnTick;
    }

    public void setStartOnTick(Boolean startOnTick) {
        this.startOnTick = startOnTick;
    }

    public Integer getTickAmount() {
        return tickAmount;
    }

    public void setTickAmount(Integer tickAmount) {
        this.tickAmount = tickAmount;
    }

    public String getTickColor() {
        return tickColor;
    }

    public void setTickColor(String tickColor) {
        this.tickColor = tickColor;
    }

    public Integer getTickInterval() {
        return tickInterval;
    }

    public void setTickInterval(Integer tickInterval) {
        this.tickInterval = tickInterval;
    }

    public Integer getTickLength() {
        return tickLength;
    }

    public void setTickLength(Integer tickLength) {
        this.tickLength = tickLength;
    }

    public Integer getTickPixelInterval() {
        return tickPixelInterval;
    }

    public void setTickPixelInterval(Integer tickPixelInterval) {
        this.tickPixelInterval = tickPixelInterval;
    }

    public Align getTickPosition() {
        return tickPosition;
    }

    public void setTickPosition(Align tickPosition) {
        this.tickPosition = tickPosition;
    }

    public List<Integer> getTickPositions() {
        if (tickPositions == null) {
            tickPositions = new ArrayList<>();
        }
        return tickPositions;
    }

    public void setTickPositions(List<Integer> tickPositions) {
        this.tickPositions = tickPositions;
    }

    public Integer getTickWidth() {
        return tickWidth;
    }

    public void setTickWidth(Integer tickWidth) {
        this.tickWidth = tickWidth;
    }

    public Align getTickmarkPlacement() {
        return tickmarkPlacement;
    }

    public void setTickmarkPlacement(Align tickmarkPlacement) {
        this.tickmarkPlacement = tickmarkPlacement;
    }

    public AxisTitle getTitle() {
        if (title == null) {
            title = new AxisTitle();
        }
        return title;
    }

    public void setTitle(AxisTitle title) {
        this.title = title;
    }

    public AxisType getType() {
        return type;
    }

    public void setType(AxisType type) {
        this.type = type;
    }

    public Boolean getUniqueNames() {
        return uniqueNames;
    }

    public void setUniqueNames(Boolean uniqueNames) {
        this.uniqueNames = uniqueNames;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

}
