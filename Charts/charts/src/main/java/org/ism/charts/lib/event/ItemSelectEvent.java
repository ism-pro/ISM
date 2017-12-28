/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.event;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;

/**
 *
 * @author r.hendrick
 */
public class ItemSelectEvent extends AbstractAjaxBehaviorEvent {

    private int itemIndex;

    private int seriesIndex;

    public ItemSelectEvent(UIComponent source, Behavior behavior, int itemIndex, int seriesIndex) {
        super(source, behavior);
        this.itemIndex = itemIndex;
        this.seriesIndex = seriesIndex;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public int getSeriesIndex() {
        return seriesIndex;
    }
}
