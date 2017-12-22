/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.details;

import org.ism.charts.lib.model.properties.Align;

/**
 * The chart's main title
 *
 * @author r.hendrick
 */
public class SubTitle {

    /**
     * align: StringSince 2.0 The horizontal alignment of the title. Can be one
     * of "left", "center" and "right". Defaults to center. Try it:
     *
     * Aligned to the plot area (x = 70px = margin left - spacing left)
     */
    private Align align = null;

    /**
     * floating: BooleanSince 2.1 When the title is floating, the plot area will
     * not move to make space for it. Defaults to false. Try it:
     *
     * False by default, true - title on top of the plot area.
     */
    private Boolean floating = null;

    /**
     * style: CSSObject CSS styles for the title. Use this for font styling, but
     * use align, x and y for text alignment.
     *
     * In styled mode, the title style is given in the .highcharts-title class.
     *
     * Defaults to { "color": "#333333", "fontSize": "18px" }. Try it:
     *
     * Custom color and weight. Styled mode.
     */
    private String style = null;

    /**
     * text: String The title of the chart. To disable the title, set the text
     * to null. Defaults to Chart title. Try it:
     *
     * Custom title
     */
    private String text = null;

    /**
     * useHTML: Boolean Whether to use HTML to render the text. Defaults to
     * false.
     */
    private Boolean useHTML = null;

    /**
     * verticalAlign: StringSince 2.1 The vertical alignment of the title. Can
     * be one of "top", "middle" and "bottom". When a value is given, the title
     * behaves as if floating were true. Try it:
     *
     * Chart title in bottom right corner
     */
    private Align verticalAlign = null;

    /**
     * widthAdjust: NumberSince 4.2.5 Adjustment made to the title width,
     * normally to reserve space for the exporting burger menu. Defaults to -44.
     * Try it:
     *
     * Wider menu, greater padding
     */
    private Integer widthAdjust = null;

    /**
     * x: NumberSince 2.0 The x position of the title relative to the alignment
     * within chart.spacingLeft and chart.spacingRight. Defaults to 0. Try it:
     *
     * Aligned to the plot area (x = 70px = margin left - spacing left)
     */
    private Integer x = null;

    /**
     * y: NumberSince 2.0 The y position of the title relative to the alignment
     * within chart.spacingTop and chart.spacingBottom. By default it depends on
     * the font size. Try it:
     *
     * Title inside the plot area
     */
    private Integer y = null;

    

    public SubTitle(String title) {
        this.text = title;
    }

    public SubTitle(String title, String style) {
        this.text = title;
        this.style = style;
    }

    public SubTitle(String title, Integer x, Integer y) {
        this.text = title;
        this.x = x;
        this.y = y;
    }

    public SubTitle(String title, String style, Integer x, Integer y) {
        this.text = title;
        this.style = style;
        this.x = x;
        this.y = y;
    }

    public SubTitle(String title, Boolean isHTML) {
        this.text = title;
        this.useHTML = isHTML;
    }

    public SubTitle(String title, Boolean isHTML, Integer x, Integer y) {
        this.text = title;
        this.useHTML = isHTML;
        this.x = x;
        this.y = y;
    }


    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public Boolean getFloating() {
        return floating;
    }

    public void setFloating(Boolean floating) {
        this.floating = floating;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Integer getWidthAdjust() {
        return widthAdjust;
    }

    public void setWidthAdjust(Integer widthAdjust) {
        this.widthAdjust = widthAdjust;
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
