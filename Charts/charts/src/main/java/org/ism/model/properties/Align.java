/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.properties;

/**
 *
 * @author r.hendrick
 */
public enum Align {
    
    UNDEFIN("undefin"),
    HLEFT("left"), 
    CENTER("center"),
    RIGHT("right"),
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom"),
    HORIZONTAL("horizontal"),
    VERTICAL("vertical"),
    INSIDE("inside"),
    OUTSIDE("oustside"),
    BETWEEN("between"),
    ON("on")
    ;
    
    
    private String text;
    
    Align(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
