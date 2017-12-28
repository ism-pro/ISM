/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.event;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.FacesListener;

/**
 *
 * @author r.hendrick
 */
public abstract class AbstractAjaxBehaviorEvent extends AjaxBehaviorEvent {

    public AbstractAjaxBehaviorEvent(UIComponent component, Behavior behavior) {
        super(component, behavior);
    }

    @Override
    public boolean isAppropriateListener(FacesListener facesListener) {
        return (facesListener instanceof AjaxBehaviorListener);
    }

    @Override
    public void processListener(FacesListener facesListener) {
        if (facesListener instanceof AjaxBehaviorListener) {
            ((AjaxBehaviorListener) facesListener).processAjaxBehavior(this);
        }
    }
}
