/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.event;

import org.ism.charts.lib.component.tabview.Tab;

/**
 *
 * @author r.hendrick
 */
public interface TabEvent {

    public Tab getTab();

    public Object getData();
}
