/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.series;

import org.ism.charts.lib.component.axis.AxisTitleRenderer;
import org.ism.charts.lib.component.properties.NavigationRenderer;
import org.ism.charts.lib.model.axis.AxisTitle;
import org.ism.charts.lib.model.properties.Align;
import org.ism.charts.lib.model.properties.Navigation;
import org.ism.charts.lib.model.series.Legend;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class LegendRenderer {

    static public String renderer(Legend legendSet, Boolean first) {
        String legend = "";
        // Exclude Null pointer
        if (legendSet == null) {
            //Util.out("Alarm *** LegendRenderer : ", "legendSet is null, return empty string");
            return legend;
        }

        legend += open(first);
        legend += align(legendSet.getAlign());
        legend += backgroundColor(legendSet.getBackgroundColor());
        legend += borderColor(legendSet.getBorderColor());
        legend += borderRadius(legendSet.getBorderRadius());
        legend += borderWidth(legendSet.getBorderWidth());
        legend += floating(legendSet.getFloating());
        legend += itemDistance(legendSet.getItemDistance());
        legend += itemHiddenStyle(legendSet.getItemHiddenStyle());
        legend += itemHoverStyle(legendSet.getItemHoverStyle());
        legend += itemMarginBottom(legendSet.getItemMarginBottom());
        legend += itemMarginTop(legendSet.getItemMarginTop());
        legend += itemStyle(legendSet.getItemStyle());
        legend += itemWidth(legendSet.getItemWidth());
        legend += labelFormat(legendSet.getLabelFormat());
        legend += labelFormatter(legendSet.getLabelFormatter());
        legend += layout(legendSet.getLayout());
        legend += lineHeight(legendSet.getLineHeight());
        legend += margin(legendSet.getMargin());
        legend += maxHeight(legendSet.getMaxHeight());
        legend += navigation(legendSet.getNavigation(), isFirst(legend));
        legend += padding(legendSet.getPadding());
        legend += reversed(legendSet.getReversed());
        legend += rtl(legendSet.getRtl());
        legend += shadow(legendSet.getShadow());
        legend += symbolHeight(legendSet.getSymbolHeight());
        legend += symbolPadding(legendSet.getSymbolPadding());
        legend += symbolRadius(legendSet.getSymbolRadius());
        legend += symbolWidth(legendSet.getSymbolWidth());
        legend += title(legendSet.getTitle(), isFirst(legend));
        legend += useHTML(legendSet.getUseHTML());
        legend += verticalAlign(legendSet.getVerticalAlign());
        legend += width(legendSet.getWidth());
        legend += x(legendSet.getX());
        legend += y(legendSet.getY());

        legend = close(legend);
        return legend;
    }

    static public String renderer(Legend legendSet) {
        return renderer(legendSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "legend:{" : ",legend:{";
    }

    static private String close(String legend) {
        if (legend.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        legend = legend.substring(0, legend.length() - 1);
        legend += "}";
        return legend;
    }

    static private Boolean isFirst(String states) {
        return states.endsWith(",");
    }

    static private String align(Align align) {
        if (align != null) {
            return "align:'" + align + "',";
        }
        return "";
    }

    static private String backgroundColor(String backgroundColor) {
        if (backgroundColor != null) {
            return "backgroundColor:" + backgroundColor + ",";
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

    static private String enabled(Boolean enabled) {
        if (enabled != null) {
            return "enabled:" + enabled + ",";
        }
        return "";
    }

    static private String floating(Boolean floating) {
        if (floating != null) {
            return "floating:" + floating + ",";
        }
        return "";
    }

    static private String itemDistance(Integer itemDistance) {
        if (itemDistance != null) {
            return "itemDistance:" + itemDistance + ",";
        }
        return "";
    }

    static private String itemHiddenStyle(String itemHiddenStyle) {
        if (itemHiddenStyle != null) {
            return "itemHiddenStyle:'" + itemHiddenStyle + "',";
        }
        return "";
    }

    static private String itemHoverStyle(String itemHoverStyle) {
        if (itemHoverStyle != null) {
            return "itemHoverStyle:'" + itemHoverStyle + "',";
        }
        return "";
    }

    static private String itemMarginBottom(Integer itemMarginBottom) {
        if (itemMarginBottom != null) {
            return "itemMarginBottom:" + itemMarginBottom + ",";
        }
        return "";
    }

    static private String itemMarginTop(Integer itemMarginTop) {
        if (itemMarginTop != null) {
            return "itemMarginTop:" + itemMarginTop + ",";
        }
        return "";
    }

    static private String itemStyle(String itemStyle) {
        if (itemStyle != null) {
            return "itemStyle:'" + itemStyle + "',";
        }
        return "";
    }

    static private String itemWidth(Integer itemWidth) {
        if (itemWidth != null) {
            return "itemWidth:" + itemWidth + ",";
        }
        return "";
    }

    static private String labelFormat(String labelFormat) {
        if (labelFormat != null) {
            return "labelFormat:'" + labelFormat + "',";
        }
        return "";
    }

    static private String labelFormatter(String labelFormatter) {
        if (labelFormatter != null) {
            return "labelFormatter:'" + labelFormatter + "',";
        }
        return "";
    }

    static private String layout(Align layout) {
        if (layout != null) {
            return "layout:'" + layout + "',";
        }
        return "";
    }

    static private String lineHeight(Integer lineHeight) {
        if (lineHeight != null) {
            return "lineHeight:" + lineHeight + ",";
        }
        return "";
    }

    static private String margin(Integer margin) {
        if (margin != null) {
            return "margin:" + margin + ",";
        }
        return "";
    }

    static private String maxHeight(Integer maxHeight) {
        if (maxHeight != null) {
            return "maxHeight:" + maxHeight + ",";
        }
        return "";
    }

    static private String navigation(Navigation navigation, Boolean first) {
        if (navigation != null) {
            String tmp = NavigationRenderer.renderer(navigation, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String padding(Integer padding) {
        if (padding != null) {
            return "padding:" + padding + ",";
        }
        return "";
    }

    static private String reversed(Boolean reversed) {
        if (reversed != null) {
            return "reversed:" + reversed + ",";
        }
        return "";
    }

    static private String rtl(Boolean rtl) {
        if (rtl != null) {
            return "rtl:" + rtl + ",";
        }
        return "";
    }

    static private String shadow(Boolean shadow) {
        if (shadow != null) {
            return "shadow:" + shadow + ",";
        }
        return "";
    }

    static private String symbolHeight(Integer symbolHeight) {
        if (symbolHeight != null) {
            return "symbolHeight:" + symbolHeight + ",";
        }
        return "";
    }

    static private String symbolPadding(Integer symbolPadding) {
        if (symbolPadding != null) {
            return "symbolPadding:" + symbolPadding + ",";
        }
        return "";
    }

    static private String symbolRadius(Integer symbolRadius) {
        if (symbolRadius != null) {
            return "symbolRadius:" + symbolRadius + ",";
        }
        return "";
    }

    static private String symbolWidth(Integer symbolWidth) {
        if (symbolWidth != null) {
            return "symbolWidth:" + symbolWidth + ",";
        }
        return "";
    }

    static private String title(AxisTitle title, Boolean first) {
        if (title != null) {
            String tmp = AxisTitleRenderer.renderer(title, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String useHTML(Boolean useHTML) {
        if (useHTML != null) {
            return "useHTML:" + useHTML + ",";
        }
        return "";
    }

    static private String verticalAlign(Align verticalAlign) {
        if (verticalAlign != null) {
            return "verticalAlign:'" + verticalAlign + "',";
        }
        return "";
    }

    static private String width(Integer width) {
        if (width != null) {
            return "width:" + width + ",";
        }
        return "";
    }

    static private String x(Integer x) {
        if (x != null) {
            return "x:" + x + ",";
        }
        return "";
    }

    static private String y(Integer y) {
        if (y != null) {
            return "y:" + y + ",";
        }
        return "";
    }

}
