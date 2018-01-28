/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.event;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import org.ism.model.cropper.CropError;
import org.primefaces.event.AbstractAjaxBehaviorEvent;

/**
 *
 * @author r.hendrick
 */
public class CropErrorEvent extends AbstractAjaxBehaviorEvent {

    private CropError cropError;
    private Integer index;

    public CropErrorEvent(UIComponent component, Behavior behavior, CropError cropError) {
        super(component, behavior);
        this.cropError = cropError;
    }

    public CropErrorEvent(UIComponent component, Behavior behavior, CropError cropError, Integer index) {
        super(component, behavior);
        this.index = index;
        this.cropError = cropError;
    }

    public CropError getCropError() {
        return cropError;
    }

    public void setCropError(CropError cropError) {
        this.cropError = cropError;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}
