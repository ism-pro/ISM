/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.properties;

/**
 * <h1>Halo</h1><br>
 * Halo classOptions for the halo appearing around the hovered point in
 * line-type series as well as outside the hovered slice in pie charts. By
 * default the halo is filled by the current point or series color with an
 * opacity of 0.25. The halo can be disabled by setting the halo option to
 * false.
 *
 * In styled mode, the halo is styled with the .highcharts-halo class, with
 * colors inherited from .highcharts-color-{n}.
 *
 * Try it: Halo options
 *
 * @author r.hendrick
 *
 */
public class Halo {

    /**
     * attributes: ObjectSince 4.0 A collection of SVG attributes to override
     * the appearance of the halo, for example fill, stroke and stroke-width.
     */
    private String attributes = null;

    /**
     * opacity: NumberSince 4.0 Opacity for the halo unless a specific fill is
     * overridden using the attributes setting. Note that Highcharts is only
     * able to apply opacity to colors of hex or rgb(a) formats. Defaults to
     * 0.25.
     */
    private Double opacity = null;

    /**
     * size: NumberSince 4.0 The pixel size of the halo. For point markers this
     * is the radius of the halo. For pie slices it is the width of the halo
     * outside the slice. For bubbles it defaults to 5 and is the width of the
     * halo outside the bubble. Defaults to 10.
     */
    private Integer size = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Double getOpacity() {
        return opacity;
    }

    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
