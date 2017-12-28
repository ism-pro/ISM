/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.api;

/**
 *
 * @author r.hendrick
 */
public class RepeatStatus {

    private static final long serialVersionUID = 1L;

    private final int count;
    private final int index;
    private final boolean first;
    private final boolean last;
    private final Integer begin;
    private final Integer end;
    private final Integer step;

    public RepeatStatus(boolean first, boolean last, int count, int index, Integer begin, Integer end, Integer step) {
        this.count = count;
        this.index = index;
        this.begin = begin;
        this.end = end;
        this.step = step;
        this.first = first;
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isEven() {
        return ((count % 2) == 0);
    }

    public boolean isOdd() {
        return !isEven();
    }

    public Integer getBegin() {
        return (begin == -1) ? null : begin;
    }

    public Integer getEnd() {
        return (end == -1) ? null : end;
    }

    public int getIndex() {
        return index;
    }

    public Integer getStep() {
        return (step == -1) ? null : step;
    }
}
