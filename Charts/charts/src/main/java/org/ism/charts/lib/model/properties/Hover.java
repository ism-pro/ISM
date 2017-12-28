/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.properties;

import org.ism.charts.lib.model.series.Marker;

/**
 * <h1>hover</h1><br>
 * hover class
 *
 * @author r.hendrick
 *
 */
public class Hover {

    /**
     * animation Animation when hovering over the marker.
     */
    private Animation animation = null;

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
     * halo: ObjectSince 4.0 Options for the halo appearing around the hovered
     * point in line-type series as well as outside the hovered slice in pie
     * charts. By default the halo is filled by the current point or series
     * color with an opacity of 0.25. The halo can be disabled by setting the
     * halo option to false.
     *
     * In styled mode, the halo is styled with the .highcharts-halo class, with
     * colors inherited from .highcharts-color-{n}.
     *
     * Try it: Halo options
     */
    private Halo halo = null;

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
     * lineWidthPlus: NumberSince 4.0.3 The additional line width for a hovered
     * point. Defaults to 1. Try it: 2 pixels wider on hover
     */
    private Integer lineWidthPlus = null;

    private Marker marker = null;
    /**
     * radius: Number The radius of the point marker. In hover state, it
     * defaults to the normal state's radius + 2 as per the radiusPlus option.
     * Try it: 10px radius
     */
    private Integer radius = null;

    /**
     * radiusPlus: NumberSince 4.0.3 The number of pixels to increase the radius
     * of the hovered point. Defaults to 2. Try it: 5 pixels greater radius on
     * hover
     */
    private Integer radiusPlus = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

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

    public Halo getHalo() {
        return halo;
    }

    public void setHalo(Halo halo) {
        this.halo = halo;
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

    public Integer getLineWidthPlus() {
        return lineWidthPlus;
    }

    public void setLineWidthPlus(Integer lineWidthPlus) {
        this.lineWidthPlus = lineWidthPlus;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getRadiusPlus() {
        return radiusPlus;
    }

    public void setRadiusPlus(Integer radiusPlus) {
        this.radiusPlus = radiusPlus;
    }

}
