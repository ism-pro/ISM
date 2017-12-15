/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.axis;

/**
 * <h1>Breaks</h1><br>
 * An array defining breaks in the axis, the sections defined will be left out
 * and all the points shifted closer to each other. Requires that the
 * broken-axis.js module is loaded. Try it: Simple break, advanced with callback
 *
 * @author r.hendrick
 *
 */
public class Breaks {

    /**
     * breakSize: Number<br>
     * Since 4.1.0 A number indicating how much space should be left between the
     * start and the end of the break. The break size is given in axis units, so
     * for instance on a datetime axis, a break size of 3600000 would indicate
     * the equivalent of an hour. Defaults to 0.
     */
    private Integer breakSize = 0;

    /**
     * from: Number<br>
     * Since 4.1.0 The point where the break starts.
     */
    private Integer from = null;

    /**
     * repeat: Number<br>
     * Since 4.1.0 Defines an interval after which the break appears again. By
     * default the breaks do not repeat. Defaults to 0. Try it: Repeated Break
     */
    private Integer repeat = 0;

    /**
     * to: Number<br>
     * Since 4.1.0 The point where the break ends.
     */
    private Integer to = null;



    ////////////////////////////////////////////////////////////////////////////
    /// Getter and setter
    ///
    ////////////////////////////////////////////////////////////////////////////
    public Integer getBreakSize() {
        return breakSize;
    }

    public void setBreakSize(Integer breakSize) {
        this.breakSize = breakSize;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    
    
    
}
