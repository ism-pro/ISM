/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.properties;


import org.ism.model.properties.Halo;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class HaloRenderer {

    static public String renderer(Halo haloSet, Boolean first) {
        String halo = "";
        // Exclude Null pointer
        if (haloSet == null) {
            //Util.out("Alarm *** HaloRenderer : ", "haloSet is null, return empty string");
            return halo;
        }

        halo += open(first);
        halo += attributes(haloSet.getAttributes());
        halo += opacity(haloSet.getOpacity());
        halo += size(haloSet.getSize());

        halo = close(halo);
        return halo;
    }

    static public String renderer(Halo haloSet) {
        return renderer(haloSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "halo:{" : ",halo:{";
    }

    static private String close(String halo) {
        if (halo.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        halo = halo.substring(0, halo.length() - 1);
        halo += "}";
        return halo;
    }

    static private String attributes(String attributes) {
        if (attributes != null) {
            return "attributes:'" + attributes + "',";
        }
        return "";
    }

    static private String opacity(Double opacity) {
        if (opacity != null) {
            return "opacity:" + opacity + ",";
        }
        return "";
    }

    static private String size(Integer size) {
        if (size != null) {
            return "size:" + size + ",";
        }
        return "";
    }

}
