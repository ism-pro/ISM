/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.event;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import org.ism.charts.lib.model.menu.MenuItem;

/**
 *
 * @author r.hendrick
 */
public class MenuActionEvent extends ActionEvent {

    private MenuItem menuItem;

    public MenuActionEvent(UIComponent component, MenuItem menuItem) {
        super(component);
        this.menuItem = menuItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
