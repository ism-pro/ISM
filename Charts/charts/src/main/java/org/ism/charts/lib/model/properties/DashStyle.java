/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.properties;

/**
 *
 * @author r.hendrick
 */
public enum DashStyle {

    SOLID("solid"),
    SHORTDASH("ShortDash"),
    SHORTDOT("ShortDot"),
    SHORTDASHDOT("ShortDashDot"),
    SHORTDASHDOTDOT("ShortDashDotDot"),
    DOT("Dot"),
    DASH("Dash"),
    LONGDASH("LongDash"),
    DASHDOT("DashDot"),
    LONGDASHDOT("LongDashDot"),
    LONGDASHDOTDOT("LongDashDotDot");

    private String text;
    
    DashStyle(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
