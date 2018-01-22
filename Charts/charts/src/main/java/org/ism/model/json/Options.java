/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.json;

import java.util.List;
import org.ism.model.axis.Point;
import org.ism.model.properties.Animation;
import org.ism.model.properties.DataLabels;
import org.ism.model.properties.Events;
import org.ism.model.properties.States;
import org.ism.model.series.Data;
import org.ism.model.series.Marker;

/**
 *
 * @author r.hendrick
 */
public class Options {

    private Integer lineWidth;
    private Boolean allowPointSelect;
    private Boolean showCheckbox;
    private Animation animation;
    private Events event;
    private Marker marker;
    private Point point;
    private DataLabels dataLabels;
    private Integer cropThreshold;
    private Integer pointRange;
    private Boolean softThreshold;
    private States states;
    private Boolean stickyTracking;
    private Integer turboThreshold;
    private String findNearestPointBy;
    private List<Double> data;
    private String name;

    ///
    ///
    ///
    ///
    ///
    public Integer getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Integer lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Boolean getAllowPointSelect() {
        return allowPointSelect;
    }

    public void setAllowPointSelect(Boolean allowPointSelect) {
        this.allowPointSelect = allowPointSelect;
    }

    public Boolean getShowCheckbox() {
        return showCheckbox;
    }

    public void setShowCheckbox(Boolean showCheckbox) {
        this.showCheckbox = showCheckbox;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public DataLabels getDataLabels() {
        return dataLabels;
    }

    public void setDataLabels(DataLabels dataLabels) {
        this.dataLabels = dataLabels;
    }

    public Integer getCropThreshold() {
        return cropThreshold;
    }

    public void setCropThreshold(Integer cropThreshold) {
        this.cropThreshold = cropThreshold;
    }

    public Integer getPointRange() {
        return pointRange;
    }

    public void setPointRange(Integer pointRange) {
        this.pointRange = pointRange;
    }

    public Boolean getSoftThreshold() {
        return softThreshold;
    }

    public void setSoftThreshold(Boolean softThreshold) {
        this.softThreshold = softThreshold;
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    public Boolean getStickyTracking() {
        return stickyTracking;
    }

    public void setStickyTracking(Boolean stickyTracking) {
        this.stickyTracking = stickyTracking;
    }

    public Integer getTurboThreshold() {
        return turboThreshold;
    }

    public void setTurboThreshold(Integer turboThreshold) {
        this.turboThreshold = turboThreshold;
    }

    public String getFindNearestPointBy() {
        return findNearestPointBy;
    }

    public void setFindNearestPointBy(String findNearestPointBy) {
        this.findNearestPointBy = findNearestPointBy;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
