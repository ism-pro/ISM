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
public class ToggleSelectEvent extends AbstractAjaxBehaviorEvent {

    private boolean selected;

    public ToggleSelectEvent(UIComponent component, Behavior behavior, boolean selected) {
        super(component, behavior);
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
