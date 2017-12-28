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
public class SelectEvent extends AbstractAjaxBehaviorEvent {

    private Object object;
    private boolean metaKey;
    private boolean ctrlKey;

    public SelectEvent(UIComponent component, Behavior behavior, Object object) {
        super(component, behavior);
        this.object = object;
    }

    public SelectEvent(UIComponent component, Behavior behavior, Object object, boolean metaKey, boolean ctrlKey) {
        super(component, behavior);
        this.object = object;
        this.metaKey = metaKey;
        this.ctrlKey = ctrlKey;
    }

    public Object getObject() {
        return object;
    }

    public boolean isMetaKey() {
        return metaKey;
    }

    public boolean isCtrlKey() {
        return ctrlKey;
    }
}
