/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.properties;

import org.ism.charts.lib.model.properties.Animation;




/**
 *
 * @author r.hendrick
 */
public class AnimationRenderer {

    static public String renderer(Animation animationSet, Boolean first) {
        String animation = "";
        // Exclude Null pointer
        if (animationSet == null) {
            //Util.out("Alarm *** AnimationRenderer : ", "animationSet is null, return empty string");
            return animation;
        }

        animation += open(first);
        animation += duration(animationSet.getDuration());

        animation = close(animation);
        return animation;
    }

    static public String renderer(Animation animationSet) {
        return renderer(animationSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "animation:{" : ",animation:{";
    }

    static private String close(String animation) {
        if (animation.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        animation = animation.substring(0, animation.length() - 1);
        animation += "}";
        return animation;
    }

    static private String duration(Integer duration) {
        if (duration != null) {
            return "duration:" + duration + ",";
        }
        return "";
    }

}
