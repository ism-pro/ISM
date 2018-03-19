/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.showcase.charts.showcase.desk;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.ism.component.tabview.Tab;
import org.ism.event.SerieClickEvent;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author r.hendrick
 */
@ManagedBean(name = "deskWindowController")
@SessionScoped
public class DeskWindowController implements Serializable {

    private Integer menu = 2;

    @PostConstruct
    public void init() {
    }

    //
    //
    //
    //
    //
    public void handleSerieClick(SerieClickEvent event) {
//        Integer index = event.getIndex();
//        options =  event.getOptions();
//        outputMessage = "Selected serei id  = " + index;
    }
    
    public void handleTabChange(TabChangeEvent event){
        Tab tab = (Tab) event.getTab();
        
    }
    
    /**
     * Update current menu
     * @param event 
     */
    public void handleMenuChange(ItemSelectEvent event){
        menu = (Integer) event.getItemIndex();
    }

    //
    //
    //
    //
    //
    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

}
