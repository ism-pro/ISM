/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model;

import org.ism.model.properties.ChartType;
import org.ism.model.properties.ResetZoomButton;

/**
 * Options regarding the chart area and plot area as well as general chart
 * options.
 *
 * @author r.hendrick
 */
public class ChartSet {

    /**
     * When using multiple axis, the ticks of two or more opposite axes will
     * automatically be aligned by adding ticks to the axis or axes with the
     * least ticks, as if tickAmount were specified.
     *
     * This can be prevented by setting alignTicks to false. If the grid lines
     * look messy, it's a good idea to hide them for the secondary axis by
     * setting gridLineWidth to 0.
     *
     * Defaults to true. Try it:
     *
     * True by default, false.
     */
    private Boolean alignTricks = null;
    /**
     * *
     * Set the overall animation for all chart updating. Animation can be
     * disabled throughout the chart by setting it to false here. It can be
     * overridden for each individual API method as a function parameter. The
     * only animation not affected by this option is the initial series
     * animation, see plotOptions.series.animation.
     *
     * The animation can either be set as a boolean or a configuration object.
     * If true, it will use the 'swing' jQuery easing and a duration of 500 ms.
     * If used as a configuration object, the following properties are
     * supported:
     *
     * duration The duration of the animation in milliseconds. easing A string
     * reference to an easing function set on the Math object. See the easing
     * demo. Defaults to true. Try it:
     *
     * Updating with no animation, with a longer duration, with a jQuery UI
     * easing.
     */
    private Boolean animation = null;

    /**
     * The background color or gradient for the outer chart area. Defaults to
     * #FFFFFF. Try it:
     *
     * Color, gradient See also:
     *
     * In styled mode, the background is set with the .highcharts-background
     * class.
     */
    private String backgroundColor = null;

    /**
     * The color of the outer chart border. Defaults to #335cad. Try it:
     *
     * Brown border See also:
     *
     * In styled mode, the stroke is set with the .highcharts-background class.
     */
    private String borderColor = null;

    /**
     * The corner radius of the outer chart border. Defaults to 0. Try it:
     *
     * 20px radius
     */
    private Integer borderRadius = null;

    /**
     * The pixel width of the outer chart border. Defaults to 0. Try it:
     *
     * 5px border See also:
     *
     * In styled mode, the stroke is set with the .highcharts-background class.
     */
    private Integer borderWidth = null;

    /**
     * A CSS class name to apply to the charts container div, allowing unique
     * CSS styling for each chart.
     */
    private String className = null;

    /**
     * In styled mode, this sets how many colors the class names should rotate
     * between. With ten colors, series (or points) are given class names like
     * highcharts-color-0, highcharts-color-0 [...] highcharts-color-9. The
     * equivalent in non-styled mode is to set colors using the colors setting.
     * Defaults to 10.
     */
    private Integer colorCount = null;

    /**
     * Deprecated
     *
     * Alias of type. Defaults to line. Try it:
     *
     * Bar
     */
    private String defaultSeriesType = null;

    /**
     * A text description of the chart.
     *
     * If the Accessibility module is loaded, this is included by default as a
     * long description of the chart and its contents in the hidden screen
     * reader information region.
     *
     * Defaults to undefined. Try it:
     *
     * Accessible line chart See also:
     *
     * typeDescription
     */
    private String description = null;

    //EVENT A CREE
    /**
     * height: Number An explicit height for the chart. By default (when null)
     * the height is calculated from the offset height of the containing
     * element, or 400 pixels if the containing element's height is 0. Defaults
     * to null. Try it:
     *
     * 500px height
     */
    private Integer height = null;

    /**
     * If true, the axes will scale to the remaining visible series once one
     * series is hidden. If false, hiding and showing a series will not affect
     * the axes or the other series. For stacks, once one series within the
     * stack is hidden, the rest of the stack will close in around it even if
     * the axis is not affected. Defaults to true. Try it:
     *
     * True by default false, true with stack
     */
    private Boolean ignoreHiddenSeries = null;

    /**
     * Whether to invert the axes so that the x axis is vertical and y axis is
     * horizontal. When true, the x axis is reversed by default. If a bar series
     * is present in the chart, it will be inverted automatically.
     *
     * Inverting the chart doesn't have an effect if there are no cartesian
     * series in the chart, or if the chart is polar.
     *
     * Defaults to false. Try it:
     *
     * Inverted line
     */
    private Boolean inverted = null;

    /**
     * The margin between the bottom outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingBottom. Try it:
     *
     * 100px bottom margin
     */
    private Integer marginBottom = null;

    /**
     * The margin between the left outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingLeft. Try it:
     *
     * 150px left margin
     */
    private Integer marginLeft = null;

    /**
     * The margin between the right outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingRight. Try it:
     *
     * 100px right margin
     */
    private Integer marginRight = null;

    /**
     * The margin between the top outer edge of the chart and the plot area. Use
     * this to set a fixed pixel value for the margin as opposed to the default
     * dynamic margin. See also spacingTop. Try it:
     *
     * 100px top margin
     */
    private Integer marginTop = null;

    /**
     * Allows setting a key to switch between zooming and panning. Try it:
     *
     * Hold down shift to pan
     *
     */
    private String panKey = null;

    /**
     * Allow panning in a chart. Best used with panKey to combine zooming and
     * panning. Defaults to false. Try it:
     *
     * Zooming and panning
     */
    private Boolean panning = null;

    /**
     * Equivalent to zoomType, but for multitouch gestures only. By default, the
     * pinchType is the same as the zoomType setting. However, pinching can be
     * enabled separately in some cases, for example in stock charts where a
     * mouse drag pans the chart, while pinching is enabled. Defaults to null.
     */
    private String pinchType = null;

    /**
     * The background color or gradient for the plot area. Try it:
     *
     * Color, gradient See also:
     *
     * In styled mode, the plot background is set with the
     * .highcharts-plot-background class.
     */
    private String plotBackgroundCorlor = null;

    /**
     * The URL for an image to use as the plot background. To set an image as
     * the background for the entire chart, set a CSS background image to the
     * container element. Note that for the image to be applied to exported
     * charts, its URL needs to be accessible by the export server. Try it:
     *
     * Skies See also:
     *
     * In styled mode, a plot background image can be set with the
     * .highcharts-plot-background class and a custom pattern.
     */
    private String plotBackgroundImage = null;

    /**
     * The color of the inner chart or plot area border. Defaults to #cccccc.
     * Try it:
     *
     * Blue border See also:
     *
     * In styled mode, a plot border stroke can be set with the
     * .highcharts-plot-border class.
     */
    private String plotBorderColor = null;

    /**
     * The pixel width of the plot area border. Defaults to 0. Try it:
     *
     * 1px border
     */
    private Integer plotBorderWidth = null;

    /**
     * Whether to apply a drop shadow to the plot area. Requires that
     * plotBackgroundColor be set. Since 2.3 the shadow can be an object
     * configuration containing color, offsetX, offsetY, opacity and width.
     * Defaults to false. Try it:
     *
     * Plot shadow
     */
    private Boolean plotShadow = null;

    /**
     * When true, cartesian charts like line, spline, area and column are
     * transformed into the polar coordinate system. Requires
     * highcharts-more.js. Defaults to false. Try it:
     *
     * Polar line, polar area, polar column, combined series types, spider
     * chart, wind rose.
     */
    private Boolean polar = null;

    /**
     * reflow: BooleanSince 2.1 Whether to reflow the chart to fit the width of
     * the container div on resizing the window. Defaults to true. Try it:
     *
     * Move the bar between the JavaScript frame and the Preview frame to see
     * the effect: True by default, false.
     */
    private Boolean reflow = null;

    /**
     * The HTML element where the chart will be rendered. If it is a string, the
     * element by that id is used. The HTML element can also be passed by direct
     * reference.
     *
     * When using the Highcharts.chart constructor, the renderTo is given as a
     * parameter, and can be skipped in the options set.
     *
     * Try it:
     *
     * String, object reference, object reference through jQuery,
     */
    private String renderTo = null;

    /**
     * The button that appears after a selection zoom, allowing the user to
     * reset zoom.
     */
    private ResetZoomButton resetZoomButton  = null;

    /**
     * The background color of the marker square when selecting (zooming in on)
     * an area of the chart. Defaults to rgba(51,92,173,0.25). See also:
     *
     * In styled mode, the selection marker fill is set with the
     * .highcharts-selection-marker class.
     */
    private String selectionMarkerFill = null;

    /**
     * Whether to apply a drop shadow to the outer chart area. Requires that
     * backgroundColor be set. Since 2.3 the shadow can be an object
     * configuration containing color, offsetX, offsetY, opacity and width.
     * Defaults to false. Try it:
     *
     * Shadow
     */
    private Boolean shadow = null;

    /**
     * Whether to show the axes initially. This only applies to empty charts
     * where series are added dynamically, as axes are automatically added to
     * cartesian series. Defaults to false. Try it:
     *
     * False by default, true
     */
    private Boolean showAxes = null;

    /**
     * The space between the bottom edge of the chart and the content (plot
     * area, axis title and labels, title, subtitle or legend in top position).
     *
     * Defaults to 15. Try it:
     *
     * Spacing bottom set to 100.
     */
    private Integer spacingBottom = null;

    /**
     * The space between the left edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     *
     * Defaults to 10. Try it:
     *
     * Spacing left set to 100
     */
    private Integer spacingLeft = null;

    /**
     * The space between the right edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     *
     * Defaults to 10. Try it:
     *
     * Spacing set to 100, legend in right position with default spacing
     */
    private Integer spacingRight = null;

    /**
     * The space between the top edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     *
     * Defaults to 10. Try it:
     *
     * A top spacing of 100, floating chart title makes the plot area align to
     * the default spacingTop of 10..
     */
    private Integer spacingTop = null;

    /**
     * Additional CSS styles to apply inline to the container div. Note that
     * since the default font styles are applied in the renderer, it is ignorant
     * of the individual chart options and must be set globally. Defaults to
     * {"fontFamily":"\"Lucida Grande\", \"Lucida Sans Unicode\", Verdana,
     * Arial, Helvetica, sans-serif","fontSize":"12px"}. Try it:
     *
     * Using a serif type font. Styled mode with relative font sizes. See also:
     *
     * In styled mode, general chart styles can be set with the .highcharts-root
     * class.
     */
    private String style = null;

    /**
     * The default series type for the chart. Can be any of the chart types
     * listed under plotOptions. Defaults to line. Try it:
     *
     * Bar
     */
    private ChartType type = null;

    /**
     * A text description of the chart type.
     *
     * If the Accessibility module is loaded, this will be included in the
     * description of the chart in the screen reader information region.
     *
     * Highcharts will by default attempt to guess the chart type, but for more
     * complex charts it is recommended to specify this property for clarity.
     *
     * Defaults to undefined. Try it:
     *
     * Accessible complex chart
     */
    private String typeDescription = null;

    /**
     * An explicit width for the chart. By default (when null) the width is
     * calculated from the offset width of the containing element. Defaults to
     * null. Try it:
     *
     * 800px wide
     */
    private Integer width = null;

    /**
     * Decides in what dimensions the user can zoom by dragging the mouse. Can
     * be one of x, y or xy. Try it:
     *
     * None by default, x, y, xy See also:
     *
     * panKey
     */
    private String zommType = null;

    /**
     * Boolean used to determine if when of the setter method was used that way,
     * we know if a parameter has to be write down
     */
    private Boolean unused = true;

    /**
     *
     * <
     * h1>isUnused</h1>
     * Standard method to describe if class was used by set or get method
     *
     * @return unused to know if change was made
     */
    public boolean isUnused() {
        return unused;
    }

    public Boolean getAlignTricks() {
        return alignTricks;
    }

    public void setAlignTricks(Boolean alignTricks) {
        this.alignTricks = alignTricks;
        unused = alignTricks != null;
    }

    public Boolean getAnimation() {
        return animation;
    }

    public void setAnimation(Boolean animation) {
        this.animation = animation;
        unused = animation != null;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        unused = backgroundColor != null;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
        unused = borderColor != null;
    }

    public Integer getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(Integer borderRadius) {
        this.borderRadius = borderRadius;
        unused = borderRadius != null;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
        unused = borderWidth != null;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        unused = className != null;
    }

    public Integer getColorCount() {
        return colorCount;
    }

    public void setColorCount(Integer colorCount) {
        this.colorCount = colorCount;
        unused = colorCount != null;
    }

    public String getDefaultSeriesType() {
        return defaultSeriesType;
    }

    public void setDefaultSeriesType(String defaultSeriesType) {
        this.defaultSeriesType = defaultSeriesType;
        unused = defaultSeriesType != null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        unused = description != null;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
        unused = height != null;
    }

    public Boolean getIgnoreHiddenSeries() {
        return ignoreHiddenSeries;
    }

    public void setIgnoreHiddenSeries(Boolean ignoreHiddenSeries) {
        this.ignoreHiddenSeries = ignoreHiddenSeries;
        unused = ignoreHiddenSeries != null;
    }

    public Boolean getInverted() {
        return inverted;
    }

    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
        unused = inverted != null;
    }

    public Integer getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(Integer marginBottom) {
        this.marginBottom = marginBottom;
        unused = marginBottom != null;
    }

    public Integer getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(Integer marginLeft) {
        this.marginLeft = marginLeft;
        unused = marginLeft != null;
    }

    public Integer getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(Integer marginRight) {
        this.marginRight = marginRight;
        unused = marginRight != null;
    }

    public Integer getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(Integer marginTop) {
        this.marginTop = marginTop;
        unused = marginTop != null;
    }

    public String getPanKey() {
        return panKey;
    }

    public void setPanKey(String panKey) {
        this.panKey = panKey;
        unused = panKey != null;
    }

    public Boolean getPanning() {
        return panning;
    }

    public void setPanning(Boolean panning) {
        this.panning = panning;
        unused = panning != null;
    }

    public String getPinchType() {
        return pinchType;
    }

    public void setPinchType(String pinchType) {
        this.pinchType = pinchType;
        unused = pinchType != null;
    }

    public String getPlotBackgroundCorlor() {
        return plotBackgroundCorlor;
    }

    public void setPlotBackgroundCorlor(String plotBackgroundCorlor) {
        this.plotBackgroundCorlor = plotBackgroundCorlor;
        unused = plotBackgroundCorlor != null;
    }

    public String getPlotBackgroundImage() {
        return plotBackgroundImage;
    }

    public void setPlotBackgroundImage(String plotBackgroundImage) {
        this.plotBackgroundImage = plotBackgroundImage;
        unused = plotBackgroundImage != null;
    }

    public String getPlotBorderColor() {
        return plotBorderColor;
    }

    public void setPlotBorderColor(String plotBorderColor) {
        this.plotBorderColor = plotBorderColor;
        unused = plotBorderColor != null;
    }

    public Integer getPlotBorderWidth() {
        return plotBorderWidth;
    }

    public void setPlotBorderWidth(Integer plotBorderWidth) {
        this.plotBorderWidth = plotBorderWidth;
        unused = plotBorderWidth != null;
    }

    public Boolean getPlotShadow() {
        return plotShadow;
    }

    public void setPlotShadow(Boolean plotShadow) {
        this.plotShadow = plotShadow;
        unused = plotShadow != null;
    }

    public Boolean getPolar() {
        return polar;
    }

    public void setPolar(Boolean polar) {
        this.polar = polar;
        unused = polar != null;
    }

    public Boolean getReflow() {
        return reflow;
    }

    public void setReflow(Boolean reflow) {
        this.reflow = reflow;
        unused = reflow != null;
    }

    public String getRenderTo() {
        return renderTo;
    }

    public void setRenderTo(String renderTo) {
        this.renderTo = renderTo;
        unused = renderTo != null;
    }

    public String getSelectionMarkerFill() {
        return selectionMarkerFill;
    }

    public void setSelectionMarkerFill(String selectionMarkerFill) {
        this.selectionMarkerFill = selectionMarkerFill;
        unused = selectionMarkerFill != null;
    }

    public Boolean getShadow() {
        return shadow;
    }

    public void setShadow(Boolean shadow) {
        this.shadow = shadow;
        unused = shadow != null;
    }

    public Boolean getShowAxes() {
        return showAxes;
    }

    public void setShowAxes(Boolean showAxes) {
        this.showAxes = showAxes;
        unused = showAxes != null;
    }

    public Integer getSpacingBottom() {
        return spacingBottom;
    }

    public void setSpacingBottom(Integer spacingBottom) {
        this.spacingBottom = spacingBottom;
        unused = spacingBottom != null;
    }

    public Integer getSpacingLeft() {
        return spacingLeft;
    }

    public void setSpacingLeft(Integer spacingLeft) {
        this.spacingLeft = spacingLeft;
        unused = spacingLeft != null;
    }

    public Integer getSpacingRight() {
        return spacingRight;
    }

    public void setSpacingRight(Integer spacingRight) {
        this.spacingRight = spacingRight;
        unused = spacingRight != null;
    }

    public Integer getSpacingTop() {
        return spacingTop;
    }

    public void setSpacingTop(Integer spacingTop) {
        this.spacingTop = spacingTop;
        unused = spacingTop != null;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
        unused = style != null;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
        unused = typeDescription != null;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
        unused = width != null;
    }

    public String getZommType() {
        return zommType;
    }

    public void setZommType(String zommType) {
        this.zommType = zommType;
        unused = zommType != null;
    }

    public ChartType getType() {
        return type;
    }

    public void setType(ChartType type) {
        this.type = type;
        unused = type != null;
    }

    public ResetZoomButton getResetZoomButton() {
        if(resetZoomButton==null){  resetZoomButton = new ResetZoomButton(); }
        return resetZoomButton;
    }

    public void setResetZoomButton(ResetZoomButton resetZoomButton) {
        getResetZoomButton();
        this.resetZoomButton = resetZoomButton;
        unused = resetZoomButton != null;
    }

    
}
