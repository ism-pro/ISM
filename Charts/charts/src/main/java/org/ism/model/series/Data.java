/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.series;

import java.util.List;
import org.ism.model.properties.DataLabels;
import org.ism.model.properties.Events;

/**
 * <h1>Data</h1><br>
 * Data class <br>
 * An array of data points for the series. For the line series type, points can
 * be given in the following ways: An array of numerical values. In this case,
 * the numerical values will be interpreted as y options. The x values will be
 * automatically calculated, either starting at 0 and incremented by 1, or from
 * pointStart and pointInterval given in the series options. If the axis has
 * categories, these will be used. Example:<br>
 * data: [0, 5, 3, 5]<br>
 * An array of arrays with 2 values. In this case, the values correspond to x,y.
 * If the first value is a string, it is applied as the name of the point, and
 * the x value is inferred.<br>
 * <br>
 * data: [ [0, 1], [1, 2], [2, 8] ]<br>
 * An array of objects with named values. The objects are point configuration
 * objects as seen below. If the total number of data points exceeds the series'
 * turboThreshold, this option is not available.
 * <br>
 * data: [<br>{ x: 1, y: 10, name: "Point2", color: "#00FF00" }, <br>{ x: 1, y:
 * 6, name: "Point1", color: "#FF00FF" }<br>]
 * <br>
 * Try it:<br>
 * Numerical values<br>
 * Arrays of numeric x and y<br>
 * Arrays of datetime x and y<br>
 * Arrays of point.name and y<br>
 * Config objects<br>
 *
 * @author r.hendrick
 *
 */
public class Data {

    public final static String TYPE_DATA = "DATA";
    public final static String TYPE_Y = "DATA";
    
    public Data() {

    }

    public Data(String name, List<?> datas) {
        this.name = name;
        this.datas = (List<Object>) datas;
    }

    public Data(String name, Object y) {
        this.name = name;
        this.y = y;
    }
    
    public Data(String name,  List<?>  datas, String type){
        this.name = name;
        if(type.matches("DATA"))
            this.datas = (List<Object>) datas;
        else
            this.y = y;
    }

    /**
     * className: StringSince 5.0.0 An additional, individual class name for the
     * data point's graphic representation.
     */
    private String className = null;

    /**
     * color: Color Individual color for the point. By default the color is
     * pulled from the global colors array. Defaults to undefined. Try it: Mark
     * the highest point
     */
    private String color = null;

    /**
     * colorIndex: NumberSince 5.0.0 Styled mode only. A specific color index to
     * use for the point, so its graphic representations are given the class
     * name highcharts-color-{n}.
     */
    private Integer colorIndex = null;

    /**
     * dataLabels: Object Individual data label for each point. The options are
     * the same as the ones for plotOptions.series.dataLabels Try it: Show a
     * label for the last value
     */
    private DataLabels dataLabels = null;

    /**
     * Datas Is a special owener for y values
     */
    private List<?> datas = null;

    /**
     * description: StringSince 5.0.0 Requires Accessibility module
     *
     * A description of the point to add to the screen reader information about
     * the point.
     *
     * Defaults to undefined. Try it: Accessible map
     */
    private String description = null;

    /**
     * drilldown: StringSince 3.0.8 The id of a series in the drilldown.series
     * array to use for a drilldown for this point. Try it: Basic drilldown
     */
    private String drilldown = null;

    /**
     * Individual point events
     */
    private Events events = null;

    /**
     * id: StringSince 1.2.0 An id for the point. This can be used after render
     * time to get a pointer to the point object through chart.get(). Try it:
     * Remove an id'd point
     */
    private String id = null;

    /**
     * labelrank: Number The rank for this point's data label in case of
     * collision. If two data labels are about to overlap, only the one with the
     * highest labelrank will be drawn.
     */
    private Integer labelrank = null;

    /**
     * legendIndex: Number The sequential index of the data point in the legend.
     */
    private Integer legendIndex = null;

    /**
     * Data Marker
     */
    private Marker marker = null;

    /**
     * name : String The name of the point as shown in the legend , tooltip,
     * dataLabel etc.If the xAxis.type is set to category , and no categories
     * option exists, the category will be pulled from the point.name of the
     * last series defined . For multiple series , best practice however is to
     * define xAxis .categories.Try it : Point names
     */
    private String name = null;

    /**
     * selected: Boolean Whether the data point is selected initially. Defaults
     * to false.
     */
    private Boolean selected = null;

    /**
     * sliced: Boolean Whether to display a slice offset from the center.
     * Defaults to false. Try it: One sliced point
     */
    private Boolean sliced = null;

    /**
     * x: Number The x value of the point. For datetime axes, the X value is the
     * timestamp in milliseconds since 1970.
     */
    private Integer x = null;

    /**
     * y: Number The y value of the point.
     */
    private Object y = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getColorIndex() {
        return colorIndex;
    }

    public void setColorIndex(Integer colorIndex) {
        this.colorIndex = colorIndex;
    }

    public List<?> getDatas() {
        return datas;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }

    public DataLabels getDataLabels() {
        return dataLabels;
    }

    public void setDataLabels(DataLabels dataLabels) {
        this.dataLabels = dataLabels;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDrilldown() {
        return drilldown;
    }

    public void setDrilldown(String drilldown) {
        this.drilldown = drilldown;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLabelrank() {
        return labelrank;
    }

    public void setLabelrank(Integer labelrank) {
        this.labelrank = labelrank;
    }

    public Integer getLegendIndex() {
        return legendIndex;
    }

    public void setLegendIndex(Integer legendIndex) {
        this.legendIndex = legendIndex;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getSliced() {
        return sliced;
    }

    public void setSliced(Boolean sliced) {
        this.sliced = sliced;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Object getY() {
        return y;
    }

    public void setY(Object y) {
        this.y = y;
    }

}
