/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.axis;

/**
 *
 * @author r.hendrick
 */
public enum AxisType {
    LINEAR("linear"),
    LOGARITHMIC("logarithmic"), 
    DATETIME("datetime"),
    CATEGORY("category")
    ;
    
    
    private String text;
    
    AxisType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
