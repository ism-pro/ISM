/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.properties;

/**
 * <h1>Select</h1><br>
 * Select class
 *
 * @author r.hendrick
 *
 */
public class Select {

    /**
     * enabled: Boolean Enable or disable the point marker. Defaults to true.
     * Try it: Disabled hover state
     */
    private Boolean enabled = null;

    /**
     * fillColor: Color The fill color of the marker in hover state.
     */
    private String fillColor = null;

    /**
     * lineColor: Color The color of the point marker's outline. When null, the
     * series' or point's color is used. Defaults to #ffffff. Try it: White fill
     * color, black line color
     */
    private String lineColor = null;

    /**
     * lineWidth: Number The width of the point marker's outline. Defaults to 0.
     * Try it: 3px line width
     */
    private Integer lineWidth = null;

    /**
     * radius: Number The radius of the point marker. In hover state, it
     * defaults to the normal state's radius + 2 as per the radiusPlus option.
     * Try it: 10px radius
     */
    private Integer radius = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
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

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

}
