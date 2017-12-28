/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.event;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import org.ism.charts.lib.model.Visibility;

/**
 *
 * @author r.hendrick
 */
public class ToggleEvent extends AbstractAjaxBehaviorEvent {

    /**
     * Visibility status
     */
    private Visibility visibility;

    /**
     * Related data if available
     */
    private Object data;

    public ToggleEvent(UIComponent component, Behavior behavior, Visibility visibility) {
        super(component, behavior);
        this.visibility = visibility;
    }

    public ToggleEvent(UIComponent component, Behavior behavior, Visibility visibility, Object data) {
        super(component, behavior);
        this.visibility = visibility;
        this.data = data;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public Object getData() {
        return data;
    }
}
