/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.properties;

/**
 * <h1>Navigation</h1><br>
 * Navigation class Options for the paging or navigation appearing when the
 * legend is overflown. Navigation works well on screen, but not in static
 * exported images. One way of working around that is to increase the chart
 * height in export.
 *
 * @author r.hendrick
 *
 */
public class Navigation {

    /**
     * activeColor: ColorSince 2.2.4 The color for the active up or down arrow
     * in the legend page navigation. Defaults to #003399. Try it: Legend page
     * navigation demonstrated See also: In styled mode, the active arrow be
     * styled with the .highcharts-legend-nav-active class.
     */
    private String activeColor = null;

    /**
     * animation: Boolean|ObjectSince 2.2.4 How to animate the pages when
     * navigating up or down. A value of true applies the default navigation
     * given in the chart.animation option. Additional options can be given as
     * an object containing values for easing and duration. . Defaults to true.
     * Try it: Legend page navigation demonstrated
     */
    private Boolean animation = null;

    /**
     * arrowSize: NumberSince 2.2.4 The pixel size of the up and down arrows in
     * the legend paging navigation. . Defaults to 12. Try it: Legend page
     * navigation demonstrated
     */
    private Integer arrowSize = null;

    /**
     * enabled: BooleanSince 4.2.4 Whether to enable the legend navigation. In
     * most cases, disabling the navigation results in an unwanted overflow.
     * Defaults to true.
     */
    private Boolean enabled = null;

    /**
     * inactiveColor: ColorSince 2.2.4 The color of the inactive up or down
     * arrow in the legend page navigation. . Defaults to #cccccc. Try it:
     * Legend page navigation demonstrated See also: In styled mode, the
     * inactive arrow be styled with the .highcharts-legend-nav-inactive class.
     */
    private String inactiveColor = null;

    /**
     * style: CSSObjectSince 2.2.4 Text styles for the legend page navigation.
     * Try it: Legend page navigation demonstrated See also: In styled mode, the
     * navigation items are styled with the .highcharts-legend-navigation class.
     */
    private String style = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public String getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(String activeColor) {
        this.activeColor = activeColor;
    }

    public Boolean getAnimation() {
        return animation;
    }

    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }

    public Integer getArrowSize() {
        return arrowSize;
    }

    public void setArrowSize(Integer arrowSize) {
        this.arrowSize = arrowSize;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getInactiveColor() {
        return inactiveColor;
    }

    public void setInactiveColor(String inactiveColor) {
        this.inactiveColor = inactiveColor;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}
