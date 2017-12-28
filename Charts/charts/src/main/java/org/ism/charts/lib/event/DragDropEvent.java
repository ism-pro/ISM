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
public class DragDropEvent extends AbstractAjaxBehaviorEvent {

    private String dragId;

    private String dropId;

    private Object data;

    public DragDropEvent(UIComponent component, Behavior behavior, String dragId, String dropId) {
        super(component, behavior);
        this.dragId = dragId;
        this.dropId = dropId;
    }

    public DragDropEvent(UIComponent component, Behavior behavior, String dragId, String dropId, Object data) {
        super(component, behavior);
        this.dragId = dragId;
        this.dropId = dropId;
        this.data = data;
    }

    public String getDragId() {
        return dragId;
    }

    public String getDropId() {
        return dropId;
    }

    public Object getData() {
        return data;
    }
}
