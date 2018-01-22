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
public class Style {

    public String fontSize;
    public String fontWeight;
    public String color;
    public String textOutline;
    
    public Style(String color){
        this.color = color;
    }

    @Override
    public String toString() {
        return ""
                + (fontSize == null ? "" : "fontSize:\"" + fontSize + "\",")
                + (fontSize == null ? "" : "fontWeight:\"" + fontWeight + "\",")
                + (fontSize == null ? "" : "color:\"" + color + "\",")
                + (fontSize == null ? "" : "textOutline:\"" + textOutline + "\",");

    }
}
