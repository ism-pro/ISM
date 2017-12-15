/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.series;

import org.ism.charts.lib.model.properties.States;
import org.ism.charts.lib.model.properties.Symbol;

/**
 * <h1>Marker</h1><br>
 * Marker class
 *
 * @author r.hendrick
 *
 */
public class Marker {

    /**
     * Enable or disable the point marker. If null, the markers are hidden when
     * the data is dense, and shown for more widespread data points. Defaults to
     * null. Try it:
     *
     * Disabled markers, disabled in normal state but enabled on hover
     */
    private Boolean enable = null;
    /**
     * The fill color of the point marker. When null, the series' or point's
     * color is used. Try it:
     *
     * White fill
     */
    private String fillColor = null;
    /**
     * Image markers only. Set the image width explicitly. When using this
     * option, a width must also be set. Defaults to null. Try it:
     *
     * Fixed width and height
     */
    private Integer height = null;
    /**
     * The color of the point marker's outline. When null, the series' or
     * point's color is used. Defaults to #ffffff. Try it:
     *
     * Inherit from series color (null)
     */
    private String lineColor = null;
    /**
     *
     */
    private Integer lineWidth = null;
    /**
     * radius: Number The radius of the point marker. Defaults to 4. Try it:
     *
     * Bigger markers
     */
    private Integer radius = null;

    /**
     * Define State
     */
    private States states = null;

    /**
     * A predefined shape or symbol for the marker. When null, the symbol is
     * pulled from options.symbols. Other possible values are "circle",
     * "square", "diamond", "triangle" and "triangle-down".
     *
     * Additionally, the URL to a graphic can be given on this form:
     * "url(graphic.png)". Note that for the image to be applied to exported
     * charts, its URL needs to be accessible by the export server.
     *
     * Custom callbacks for symbol path generation can also be added to
     * Highcharts.SVGRenderer.prototype.symbols. The callback is then used by
     * its method name, as shown in the demo.
     *
     * Try it:
     *
     * Predefined, graphic and custom markers
     */
    private Symbol symbol = null;

    /**
     * Image markers only. Set the image width explicitly. When using this
     * option, a height must also be set. Defaults to null. Try it:
     *
     * Fixed width and height
     */
    private Integer width = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}
