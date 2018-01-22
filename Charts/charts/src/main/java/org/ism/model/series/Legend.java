/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.series;

import org.ism.model.axis.AxisTitle;
import org.ism.model.properties.Align;
import org.ism.model.properties.Navigation;

/**
 * <h1>Legend</h1><br>
 * Legend class
 *
 * @author r.hendrick
 *
 */
public class Legend {

    /**
     * The horizontal alignment of the legend box within the chart area. Valid
     * values are left, center and right.
     *
     * In the case that the legend is aligned in a corner position, the layout
     * option will determine whether to place it above/below or on the side of
     * the plot area.
     *
     * Defaults to center. Try it:
     *
     * Legend at the right of the chart
     *
     */
    private Align align = null;

    /**
     * The background color of the legend. Try it:
     *
     * Yellowish background See also:
     *
     * In styled mode, the legend background fill can be applied with the
     * .highcharts-legend-box class.
     */
    private String backgroundColor = null;

    /**
     * The color of the drawn border around the legend. Defaults to #999999. Try
     * it:
     *
     * Brown border See also:
     *
     * In styled mode, the legend border stroke can be applied with the
     * .highcharts-legend-box class.
     */
    private String borderColor = null;

    /**
     * The border corner radius of the legend. Defaults to 0. Try it:
     *
     * Square by default, 5px rounded
     *
     */
    private Integer borderRadius = null;

    /**
     * The width of the drawn border around the legend. Defaults to 0. Try it:
     *
     * 2px border width See also:
     *
     * In styled mode, the legend border stroke width can be applied with the
     * .highcharts-legend-box class.
     *
     */
    private Integer borderWidth = null;

    /**
     * Enable or disable the legend. Defaults to true. Try it:
     *
     * Legend disabled
     *
     */
    private Boolean enabled = null;

    /**
     * When the legend is floating, the plot area ignores it and is allowed to
     * be placed below it. Defaults to false. Try it:
     *
     * False by default, true.
     */
    private Boolean floating = null;

    /**
     * In a legend with horizontal layout, the itemDistance defines the pixel
     * distance between each item. Defaults to 20. Try it:
     *
     * 50px item distance
     */
    private Integer itemDistance = null;

    /**
     * CSS styles for each legend item when the corresponding series or point is
     * hidden. Only a subset of CSS is supported, notably those options related
     * to text. Properties are inherited from style unless overridden here.
     * Defaults to { "color": "#cccccc" }. Try it:
     *
     * Darker gray color See also:
     *
     * In styled mode, the hidden legend items can be styled with the
     * .highcharts-legend-item-hidden class.
     */
    private String itemHiddenStyle = null;

    /**
     * CSS styles for each legend item in hover mode. Only a subset of CSS is
     * supported, notably those options related to text. Properties are
     * inherited from style unless overridden here. Defaults to { "color":
     * "#000000" }. Try it:
     *
     * Red on hover See also:
     *
     * In styled mode, the hovered legend items can be styled with the
     * .highcharts-legend-item:hover pesudo-class.
     */
    private String itemHoverStyle = null;

    /**
     * The pixel bottom margin for each legend item. Defaults to 0. Try it:
     *
     * Padding and item margins demonstrated
     */
    private Integer itemMarginBottom = null;

    /**
     * The pixel top margin for each legend item. Defaults to 0. Try it:
     *
     * Padding and item margins demonstrated
     */
    private Integer itemMarginTop = null;

    /**
     * CSS styles for each legend item. Only a subset of CSS is supported,
     * notably those options related to text. Defaults to { "color": "#333333",
     * "cursor": "pointer", "fontSize": "12px", "fontWeight": "bold" }. Try it:
     *
     * Bold black text See also:
     *
     * In styled mode, the legend items can be styled with the
     * .highcharts-legend-item class.
     */
    private String itemStyle = null;

    /**
     * The width for each legend item. This is useful in a horizontal layout
     * with many items when you want the items to align vertically. . Try it:
     *
     * Null by default, 80 for aligned legend items
     */
    private Integer itemWidth = null;

    /**
     * A format string for each legend label. Available variables relates to
     * properties on the series, or the point in case of pies. Defaults to
     * {name}.
     */
    private String labelFormat = null;

    /**
     * labelFormatter: Function Callback function to format each of the series'
     * labels. The this keyword refers to the series object, or the point object
     * in case of pie charts. By default the series or point name is printed.
     * Try it: Add text
     */
    private String labelFormatter = null;

    /**
     * Line height for the legend items. Deprecated as of 2.1. Instead, the line
     * height for each item can be set using itemStyle.lineHeight, and the
     * padding between items using itemMarginTop and itemMarginBottom. Defaults
     * to 16. Try it:
     *
     * Setting padding.
     */
    private Align layout = null;

    /**
     * Line height for the legend items. Deprecated as of 2.1. Instead, the line
     * height for each item can be set using itemStyle.lineHeight, and the
     * padding between items using itemMarginTop and itemMarginBottom. Defaults
     * to 16. Try it:
     *
     * Setting padding.
     */
    private Integer lineHeight = null;

    /**
     * If the plot area sized is calculated automatically and the legend is not
     * floating, the legend margin is the space between the legend and the axis
     * labels or plot area. Defaults to 12. Try it:
     *
     * 12 pixels by default, 30 pixels.
     */
    private Integer margin = null;

    /**
     * Maximum pixel height for the legend. When the maximum height is extended,
     * navigation will show.
     */
    private Integer maxHeight = null;

    /**
     * navigation Options for the paging or navigation appearing when the legend
     * is overflown. Navigation works well on screen, but not in static exported
     * images. One way of working around that is to increase the chart height in
     * export.
     */
    private Navigation navigation = null;

    /**
     * The inner padding of the legend box. Defaults to 8. Try it:
     *
     * Padding and item margins demonstrated
     */
    private Integer padding = null;

    /**
     * Whether to reverse the order of the legend items compared to the order of
     * the series or points as defined in the configuration object. Defaults to
     * false. Try it:
     *
     * Stacked bar with reversed legend See also:
     *
     * yAxis.reversedStacks, series.legendIndex
     */
    private Boolean reversed = null;

    /**
     * Whether to show the symbol on the right side of the text rather than the
     * left side. This is common in Arabic and Hebraic. Defaults to false. Try
     * it:
     *
     * Symbol to the right
     */
    private Boolean rtl = null;

    /**
     * Whether to apply a drop shadow to the legend. A backgroundColor also
     * needs to be applied for this to take effect. Since 2.3 the shadow can be
     * an object configuration containing color, offsetX, offsetY, opacity and
     * width. Defaults to false. Try it:
     *
     * White background and drop shadow
     */
    private Boolean shadow = null;

    /**
     * The pixel height of the symbol for series types that use a rectangle in
     * the legend. Defaults to the font size of legend items.
     */
    private Integer symbolHeight = null;

    /**
     * The pixel padding between the legend item symbol and the legend item
     * text. Defaults to 5. Try it:
     *
     * Greater symbol width and padding
     */
    private Integer symbolPadding = null;

    /**
     * The border radius of the symbol for series types that use a rectangle in
     * the legend. Defaults to 0. Try it:
     *
     * Round symbols
     */
    private Integer symbolRadius = null;

    /**
     * The pixel width of the legend item symbol. Defaults to 16. Try it:
     *
     * Greater symbol width and padding
     */
    private Integer symbolWidth = null;

    /**
     * A title to be added on top of the legend. Try it:
     *
     * Legend title
     *
     * Generic CSS styles for the legend title. Defaults to
     * {"fontWeight":"bold"}. See also:
     *
     * In styled mode, the legend title is styled with the
     * .highcharts-legend-title class.
     */
    private AxisTitle title = null;

    /**
     * useHTML: Boolean Whether to use HTML to render the legend item texts.
     * Prior to 4.1.7, when using HTML, legend.navigation was disabled.
     *
     * Defaults to false.
     */
    private Boolean useHTML = null;

    /**
     * The vertical alignment of the legend box. Can be one of top, middle or
     * bottom. Vertical position can be further determined by the y option.
     *
     * In the case that the legend is aligned in a corner position, the layout
     * option will determine whether to place it above/below or on the side of
     * the plot area.
     *
     * Defaults to bottom. Try it:
     *
     * Legend 100px from the top of the chart
     */
    private Align verticalAlign = null;

    /**
     * The width of the legend box. Try it:
     *
     * Aligned to the plot area
     */
    private Integer width = null;

    /**
     * The x offset of the legend relative to its horizontal alignment align
     * within chart.spacingLeft and chart.spacingRight. Negative x moves it to
     * the left, positive x moves it to the right. Defaults to 0. Try it:
     *
     * Aligned to the plot area
     */
    private Integer x = null;

    /**
     * The vertical offset of the legend relative to it's vertical alignment
     * verticalAlign within chart.spacingTop and chart.spacingBottom. Negative y
     * moves it up, positive y moves it down. Defaults to 0. Try it:
     *
     * Legend 100px from the top of the chart
     */
    private Integer y = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getFloating() {
        return floating;
    }

    public void setFloating(Boolean floating) {
        this.floating = floating;
    }

    public Integer getItemDistance() {
        return itemDistance;
    }

    public void setItemDistance(Integer itemDistance) {
        this.itemDistance = itemDistance;
    }

    public String getItemHiddenStyle() {
        return itemHiddenStyle;
    }

    public void setItemHiddenStyle(String itemHiddenStyle) {
        this.itemHiddenStyle = itemHiddenStyle;
    }

    public String getItemHoverStyle() {
        return itemHoverStyle;
    }

    public void setItemHoverStyle(String itemHoverStyle) {
        this.itemHoverStyle = itemHoverStyle;
    }

    public Integer getItemMarginBottom() {
        return itemMarginBottom;
    }

    public void setItemMarginBottom(Integer itemMarginBottom) {
        this.itemMarginBottom = itemMarginBottom;
    }

    public Integer getItemMarginTop() {
        return itemMarginTop;
    }

    public void setItemMarginTop(Integer itemMarginTop) {
        this.itemMarginTop = itemMarginTop;
    }

    public String getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(String itemStyle) {
        this.itemStyle = itemStyle;
    }

    public Integer getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(Integer itemWidth) {
        this.itemWidth = itemWidth;
    }

    public String getLabelFormat() {
        return labelFormat;
    }

    public void setLabelFormat(String labelFormat) {
        this.labelFormat = labelFormat;
    }

    public String getLabelFormatter() {
        return labelFormatter;
    }

    public void setLabelFormatter(String labelFormatter) {
        this.labelFormatter = labelFormatter;
    }

    public Align getLayout() {
        return layout;
    }

    public void setLayout(Align layout) {
        this.layout = layout;
    }

    public Integer getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(Integer lineHeight) {
        this.lineHeight = lineHeight;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public Integer getPadding() {
        return padding;
    }

    public void setPadding(Integer padding) {
        this.padding = padding;
    }

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    public Boolean getRtl() {
        return rtl;
    }

    public void setRtl(Boolean rtl) {
        this.rtl = rtl;
    }

    public Boolean getShadow() {
        return shadow;
    }

    public void setShadow(Boolean shadow) {
        this.shadow = shadow;
    }

    public Integer getSymbolHeight() {
        return symbolHeight;
    }

    public void setSymbolHeight(Integer symbolHeight) {
        this.symbolHeight = symbolHeight;
    }

    public Integer getSymbolPadding() {
        return symbolPadding;
    }

    public void setSymbolPadding(Integer symbolPadding) {
        this.symbolPadding = symbolPadding;
    }

    public Integer getSymbolRadius() {
        return symbolRadius;
    }

    public void setSymbolRadius(Integer symbolRadius) {
        this.symbolRadius = symbolRadius;
    }

    public Integer getSymbolWidth() {
        return symbolWidth;
    }

    public void setSymbolWidth(Integer symbolWidth) {
        this.symbolWidth = symbolWidth;
    }

    public AxisTitle getTitle() {
        return title;
    }

    public void setTitle(AxisTitle title) {
        this.title = title;
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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
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
