/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.axis;

import org.ism.charts.lib.model.properties.Align;

/**
 * <h1>Label</h1><br>
 * Label class Text labels for the plot bands
 *
 * @author r.hendrick
 *
 */
public class Label {

    /**
     * align: StringSince 2.1 Horizontal alignment of the label. Can be one of
     * "left", "center" or "right". Defaults to center. Try it: Aligned to the
     * right
     */
    private Align align = Align.CENTER;

    /**
     * rotation: NumberSince 2.1 Rotation of the text label in degrees .
     * Defaults to 0. Try it: Vertical text
     */
    private Integer rotation = 0;

    /**
     * style: ObjectSince 2.1 CSS styles for the text label.
     *
     * In styled mode, the labels are styled by the .highcharts-plot-band-label
     * class.
     *
     * Try it: Blue and bold label
     */
    private String style = null;

    /**
     * text: StringSince 2.1 The string text itself. A subset of HTML is
     * supported.
     */
    private String text = null;

    /**
     * textAlign: StringSince 2.1 The text alignment for the label. While align
     * determines where the texts anchor point is placed within the plot band,
     * textAlign determines how the text is aligned against its anchor point.
     * Possible values are "left", "center" and "right". Defaults to the same as
     * the align option. Try it: Vertical text in center position but
     * text-aligned left
     */
    private Align textAlign = null;

    /**
     * useHTML: BooleanSince 3.0.3 Whether to use HTML to render the labels.
     * Defaults to false.
     */
    private Boolean useHTML = false;

    /**
     * verticalAlign: StringSince 2.1 Vertical alignment of the label relative
     * to the plot band. Can be one of "top", "middle" or "bottom". Defaults to
     * top. Try it: Vertically centered label
     */
    private Align verticalAlign = Align.TOP;

    /**
     * x: NumberSince 2.1 Horizontal position relative the alignment. Default
     * varies by orientation. Try it: Aligned 10px from the right edge
     */
    private Integer x = null;

    /**
     * y: NumberSince 2.1 Vertical position of the text baseline relative to the
     * alignment. Default varies by orientation. Try it: Label on x axis
     */
    private Integer y = null;

    
    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
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
