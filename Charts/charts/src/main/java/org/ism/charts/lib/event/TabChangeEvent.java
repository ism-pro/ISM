/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.event;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import org.ism.charts.lib.component.tabview.Tab;

/**
 *
 * @author r.hendrick
 */
public class TabChangeEvent extends AbstractAjaxBehaviorEvent implements TabEvent {

    private Tab tab;
    private Object data;

    public TabChangeEvent(UIComponent component, Behavior behavior, Tab tab) {
        super(component, behavior);
        this.tab = tab;
    }

    @Override
    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
