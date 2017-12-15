/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.series;

import org.ism.charts.lib.model.series.ToolTip;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class ToolTipRenderer {

    static public String renderer(ToolTip toolTipSet, Boolean first) {
        String tooltip = "";
        // Exclude Null pointer
        if (toolTipSet == null) {
            //Util.out("Alarm *** ToolTipTenderer : ", "toolTipSet is null, return empty string");
            return tooltip;
        }

        tooltip += open(first);
        tooltip += animation(toolTipSet.getAnimation());
        tooltip += backgroundColor(toolTipSet.getBackgroundColor());
        tooltip += borderColor(toolTipSet.getBorderColor());
        tooltip += borderRadius(toolTipSet.getBorderRadius());
        tooltip += borderWidth(toolTipSet.getBorderWidth());
        tooltip += dateTimeLableFormats(toolTipSet.getDateTimeLableFormats());
        tooltip += enabled(toolTipSet.getEnabled());
        tooltip += followPointer(toolTipSet.getFollowPointer());
        tooltip += followTouchMove(toolTipSet.getFollowTouchMove());
        tooltip += footerFormat(toolTipSet.getFooterFormat());
        tooltip += headerFormat(toolTipSet.getHeaderFormat());
        tooltip += hideDelay(toolTipSet.getHideDelay());
        tooltip += padding(toolTipSet.getPadding());
        tooltip += pointFormat(toolTipSet.getPointFormat());
        tooltip += pointFormatter(toolTipSet.getPointFormatter());
        tooltip += shadow(toolTipSet.getShadow());
        tooltip += shape(toolTipSet.getShape());
        tooltip += shared(toolTipSet.getShared());
        tooltip += snap(toolTipSet.getSnap());
        tooltip += split(toolTipSet.getSplit());
        tooltip += style(toolTipSet.getStyle());
        tooltip += useHTML(toolTipSet.getUseHTML());
        tooltip += valueDecimals(toolTipSet.getValueDecimals());
        tooltip += valuePrefix(toolTipSet.getValuePrefix());
        tooltip += valueSuffix(toolTipSet.getValueSuffix());
        tooltip += xDateFormat(toolTipSet.getxDateFormat());
        tooltip = close(tooltip);
        return tooltip;
    }

    static public String renderer(ToolTip toolTipSet) {
        return renderer(toolTipSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "tooltip:{" : ",tooltip:{";
    }

    static private String close(String tooltip) {
        if (tooltip.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        tooltip = tooltip.substring(0, tooltip.length() - 1);
        tooltip += "}";
        return tooltip;
    }

    static private String animation(Boolean animation) {
        if (animation != null) {
            return "animation:" + animation + ",";
        }
        return "";
    }

    static private String backgroundColor(String backgroundColor) {
        if (backgroundColor != null) {
            return "backgroundColor:'" + backgroundColor + "',";
        }
        return "";
    }

    static private String borderColor(String borderColor) {
        if (borderColor != null) {
            return "borderColor:'" + borderColor + "',";
        }
        return "";
    }

    static private String borderRadius(Integer borderRadius) {
        if (borderRadius != null) {
            return "borderRadius:" + borderRadius + ",";
        }
        return "";
    }

    static private String borderWidth(Integer borderWidth) {
        if (borderWidth != null) {
            return "borderWidth:" + borderWidth + ",";
        }
        return "";
    }

    static private String dateTimeLableFormats(String dateTimeLableFormats) {
        if (dateTimeLableFormats != null) {
            return "dateTimeLableFormats:'" + dateTimeLableFormats + "',";
        }
        return "";
    }

    static private String enabled(Boolean enabled) {
        if (enabled != null) {
            return "enabled:" + enabled + ",";
        }
        return "";
    }

    static private String followPointer(Boolean followPointer) {
        if (followPointer != null) {
            return "followPointer:" + followPointer + ",";
        }
        return "";
    }

    static private String followTouchMove(Boolean followTouchMove) {
        if (followTouchMove != null) {
            return "followTouchMove:" + followTouchMove + ",";
        }
        return "";
    }

    static private String footerFormat(String footerFormat) {
        if (footerFormat != null) {
            return "footerFormat:'" + footerFormat + "',";
        }
        return "";
    }

    static private String headerFormat(String headerFormat) {
        if (headerFormat != null) {
            return "headerFormat:'" + headerFormat + "',";
        }
        return "";
    }

    static private String hideDelay(Integer hideDelay) {
        if (hideDelay != null) {
            return "hideDelay:" + hideDelay + ",";
        }
        return "";
    }

    static private String padding(Integer padding) {
        if (padding != null) {
            return "padding:" + padding + ",";
        }
        return "";
    }

    static private String pointFormat(String pointFormat) {
        if (pointFormat != null) {
            return "pointFormat:'" + pointFormat + "',";
        }
        return "";
    }

    static private String pointFormatter(String pointFormatter) {
        if (pointFormatter != null) {
            return "pointFormatter:'" + pointFormatter + "',";
        }
        return "";
    }

    static private String shadow(Boolean shadow) {
        if (shadow != null) {
            return "shadow:" + shadow + ",";
        }
        return "";
    }

    static private String shape(String shape) {
        if (shape != null) {
            return "shape:'" + shape + "',";
        }
        return "";
    }

    static private String shared(Boolean shared) {
        if (shared != null) {
            return "shared:" + shared + ",";
        }
        return "";
    }

    static private String snap(Integer snap) {
        if (snap != null) {
            return "snap:" + snap + ",";
        }
        return "";
    }

    static private String split(Boolean split) {
        if (split != null) {
            return "split:" + split + ",";
        }
        return "";
    }

    static private String style(String style) {
        if (style != null) {
            return "style:'" + style + "',";
        }
        return "";
    }

    static private String useHTML(Boolean useHTML) {
        if (useHTML != null) {
            return "useHTML:" + useHTML + ",";
        }
        return "";
    }

    static private String valueDecimals(Integer valueDecimals) {
        if (valueDecimals != null) {
            return "valueDecimals:" + valueDecimals + ",";
        }
        return "";
    }

    static private String valuePrefix(String valuePrefix) {
        if (valuePrefix != null) {
            return "valuePrefix:'" + valuePrefix + "',";
        }
        return "";
    }

    static private String valueSuffix(String valueSuffix) {
        if (valueSuffix != null) {
            return "valueSuffix:'" + valueSuffix + "',";
        }
        return "";
    }

    static private String xDateFormat(String xDateFormat) {
        if (xDateFormat != null) {
            return "xDateFormat:'" + xDateFormat + "',";
        }
        return "";
    }

}
