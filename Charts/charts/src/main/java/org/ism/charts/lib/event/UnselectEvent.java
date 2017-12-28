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
public class UnselectEvent extends AbstractAjaxBehaviorEvent {

    private Object object;

    public UnselectEvent(UIComponent component, Behavior behavior, Object object) {
        super(component, behavior);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
