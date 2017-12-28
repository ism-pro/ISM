/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.menu;

import java.util.List;

/**
 *
 * @author r.hendrick
 */
public interface MenuGroup extends MenuElement {

    public int getElementsCount();

    public List getElements();
}
