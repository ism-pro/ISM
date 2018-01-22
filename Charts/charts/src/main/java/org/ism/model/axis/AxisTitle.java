/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.axis;

/**
 *
 * @author r.hendrick
 */
public class AxisTitle {

    public static final String LOW = "low";
    public static final String MIDDLE = "middle";
    public static final String HIGH = "high";
    /**
     * align: String Alignment of the title relative to the axis values.
     * Possible values are "low", "middle" or "high". Defaults to middle. Try
     * it:
     *
     * X axis title aligned "low", "middle" by default, "high", place the Y axis
     * title on top of the axis
     */
    private String align = AxisTitle.MIDDLE;

    /**
     * margin: Number The pixel distance between the axis labels or line and the
     * title. Defaults to 0 for horizontal axes, 10 for vertical Try it:
     *
     * Y axis title margin of 60
     */
    private Integer margin = 0;

    /**
     * The distance of the axis title from the axis line. By default, this
     * distance is computed from the offset width of the labels, the labels'
     * distance from the axis and the title's margin. However when the offset
     * option is set, it overrides all this. Try it:
     *
     * Place the axis title on top of the axis
     */
    private Integer offset = null;

    /**
     * rotation: Number The rotation of the text in degrees. 0 is horizontal,
     * 270 is vertical reading from bottom to top. Defaults to 0. Try it:
     *
     * Horizontal Y axis title
     */
    private Integer rotation = null;

    /**
     * style: CSSObject CSS styles for the title. When titles are rotated they
     * are rendered using vector graphic techniques and not all styles are
     * applicable.
     *
     * In styled mode, the stroke width is given in the .highcharts-axis-title
     * class.
     *
     * Defaults to {"color":"#666666"}. Try it:
     *
     * Red Y axis title. Styled mode.
     */
    private String style = "{\"color\":\"#666666\"}";

    /**
     * text: String The actual text of the axis title. It can contain basic HTML
     * text markup like <b>, <i> and spans with style. Try it:
     *
     * Custom HTML title for X axis
     */
    private String text = null;

    /**
     * x: NumberSince 4.1.6 Horizontal pixel offset of the title position.
     * Defaults to 0.
     */
    private Integer x = 0;

    /**
     * y: Number Vertical pixel offset of the title position.
     */
    private Integer y = null;

    
    
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // Getter and Setter
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    
    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
