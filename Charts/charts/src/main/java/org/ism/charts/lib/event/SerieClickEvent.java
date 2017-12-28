/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.event;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import org.ism.charts.lib.model.json.Options;

/**
 *
 * @author r.hendrick
 */
public class SerieClickEvent extends AbstractAjaxBehaviorEvent {

    private Options options;
    private Integer index;

    public SerieClickEvent(UIComponent component, Behavior behavior, Options options) {
        super(component, behavior);
        this.options = options;
    }

    public SerieClickEvent(UIComponent component, Behavior behavior, Options options, Integer index) {
        super(component, behavior);
        this.index = index;
        this.options = options;
    }

    public Options getOptions() {
        return options;
    }
    
    public Object getObject(){
        return options;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
    
    

}
