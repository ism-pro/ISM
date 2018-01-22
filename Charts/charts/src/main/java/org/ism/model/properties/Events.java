/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.properties;

/**
 * <h1>Events</h1><br>
 * Events class Events for each single point
 *
 * @author r.hendrick
 *
 */
public class Events {

    /**
     * afterAnimate: FunctionSince 4.0 Fires after the series has finished its
     * initial animation, or in case animation is disabled, immediately as the
     * series is displayed. The this keyword refers to the Series object. Try
     * it: Show label after animate
     */
    private String afterAnimate = null;

    /**
     * checkboxClick: FunctionSince 1.2.0 Fires when the checkbox next to the
     * series' name in the legend is clicked. One parameter, event, is passed to
     * the function. The state of the checkbox is found by event.checked. The
     * checked item is found by event.item. Return false to prevent the default
     * action which is to toggle the select state of the series. The this
     * keyword refers to the Series object. Try it: Alert checkbox status
     */
    private String checkboxClick = null;

    /**
     * click: Function Fires when a point is clicked. One parameter, event, is
     * passed to the function. This contains common event information based on
     * jQuery or MooTools depending on which library is used as the base for
     * Highcharts. If the series.allowPointSelect option is true, the default
     * action for the point's click event is to toggle the point's select state.
     * Returning false cancels this action.
     *
     * The this keyword refers to the Point object. Try it: Click marker to
     * alert values, click column, go to URL
     */
    private String click = null;

    /**
     * hide: FunctionSince 1.2.0 Fires when the series is hidden after chart
     * generation time, either by clicking the legend item or by calling
     * .hide(). The this keyword refers to the Series object. Try it: Alert when
     * the series is hidden by clicking the legend item
     */
    private String hide = null;

    /**
     * legendItemClick: Function Fires when the legend item belonging to the
     * series is clicked. One parameter, event, is passed to the function. The
     * default action is to toggle the visibility of the series. This can be
     * prevented by returning false or calling event.preventDefault(). The this
     * keyword refers to the Series object. Try it: Confirm hiding and showing
     */
    private String legendItemClick = null;

    /**
     * mouseOut: <br>
     * Function Fires when the mouse leaves the graph. One parameter, event, is
     * passed to the function. This contains common event information based on
     * jQuery or MooTools depending on which library is used as the base for
     * Highcharts. If the stickyTracking option is true, mouseOut doesn't happen
     * before the mouse enters another graph or leaves the plot area. The this
     * keyword refers to the Series object. Try it: Log mouse over and out with
     * sticky tracking by default, without sticky tracking
     * <br>
     * Function Fires when the mouse leaves the area close to the point. One
     * parameter, event, is passed to the function. This contains common event
     * information based on jQuery or MooTools depending on which library is
     * used as the base for Highcharts. The this keyword refers to the Point
     * object. Try it: Show values in the chart's corner on mouse over
     */
    private String mouseOut = null;

    /**
     * mouseOver: Function Fires when the mouse enters the area close to the
     * point. One parameter, event, is passed to the function. This contains
     * common event information based on jQuery or MooTools depending on which
     * library is used as the base for Highcharts. The this keyword refers to
     * the Point object. Try it: Show values in the chart's corner on mouse over
     */
    private String mouseOver = null;

    /**
     * remove: FunctionSince 1.2.0 Fires when the point is removed using the
     * .remove() method. One parameter, event, is passed to the function.
     * Returning false cancels the operation. The this keyword refers to the
     * Point object. Try it: Remove point and confirm
     */
    private String remove = null;

    /**
     * select: FunctionSince 1.2.0 Fires when the point is selected either
     * programmatically or following a click on the point. One parameter, event,
     * is passed to the function. Returning false cancels the operation. The
     * this keyword refers to the Point object. Try it: Report the last selected
     * point
     */
    private String select = null;

    /**
     * show: FunctionSince 1.2.0 Fires when the series is shown after chart
     * generation time, either by clicking the legend item or by calling
     * .show(). The this keyword refers to the Series object. Try it: Alert when
     * the series is shown by clicking the legend item.
     */
    private String show = null;

    /**
     * unselect: FunctionSince 1.2.0 Fires when the point is unselected either
     * programmatically or following a click on the point. One parameter, event,
     * is passed to the function. Returning false cancels the operation. The
     * this keyword refers to the Point object. Try it: Report the last
     * unselected point
     */
    private String unselect = null;

    /**
     * update: FunctionSince 1.2.0 Fires when the point is updated
     * programmatically through the .update() method. One parameter, event, is
     * passed to the function. The new point options can be accessed through
     * event.options. Returning false cancels the operation. The this keyword
     * refers to the Point object. Try it: Confirm point updating
     */
    private String update = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public String getAfterAnimate() {
        return afterAnimate;
    }

    public void setAfterAnimate(String afterAnimate) {
        this.afterAnimate = afterAnimate;
    }

    public String getCheckboxClick() {
        return checkboxClick;
    }

    public void setCheckboxClick(String checkboxClick) {
        this.checkboxClick = checkboxClick;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public String getLegendItemClick() {
        return legendItemClick;
    }

    public void setLegendItemClick(String legendItemClick) {
        this.legendItemClick = legendItemClick;
    }

    public String getMouseOut() {
        return mouseOut;
    }

    public void setMouseOut(String mouseOut) {
        this.mouseOut = mouseOut;
    }

    public String getMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(String mouseOver) {
        this.mouseOver = mouseOver;
    }

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getUnselect() {
        return unselect;
    }

    public void setUnselect(String unselect) {
        this.unselect = unselect;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
    
}
