/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.event;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import org.ism.model.cropper.CroppedImage;
import org.ism.model.json.Options;
import org.primefaces.event.AbstractAjaxBehaviorEvent;

/**
 *
 * @author r.hendrick
 */
public class CroppedEvent extends AbstractAjaxBehaviorEvent {

    private CroppedImage croppedImage;
    private Integer index;

    public CroppedEvent(UIComponent component, Behavior behavior, CroppedImage croppedImage) {
        super(component, behavior);
        this.croppedImage = croppedImage;
    }

    public CroppedEvent(UIComponent component, Behavior behavior, CroppedImage croppedImage, Integer index) {
        super(component, behavior);
        this.index = index;
        this.croppedImage = croppedImage;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}
