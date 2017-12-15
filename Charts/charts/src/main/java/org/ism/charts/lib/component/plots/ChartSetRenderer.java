/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.plots;

import org.ism.charts.lib.util.Util;
import org.ism.charts.lib.model.ChartSet;
import org.ism.charts.lib.model.properties.ChartType;
import org.ism.charts.lib.model.properties.ResetZoomButton;


/**
 * <h1>ChartSetRenderer</h1>
 *
 * <h2>Description</h2>
 *
 *
 * @author r.hendrick
 */
public class ChartSetRenderer {

    /**
     * <h3>renderer</h3>
     * This is the main method which allow to convert a ChartSet model to a
     * hightchart encoded script
     *
     * This method return empty string if no data set
     *
     * @param chart is model of ChartSet allowing to render chart data
     * @param first define if has to start with ","
     * @return The output is empty string for null or nothing used
     */
    static public String renderer(ChartSet chart, Boolean first) {
        String chartSet = "";
        // Exclude Null pointer
        if (chart == null) {
            //Util.out("Alarm *** ChartSetRenderer : ", "chart is null, return empty string");
            return chartSet;
        }

        // Check used
        if (!chart.isUnused() && !chart.getResetZoomButton().isUnused() && chart.getType() == null) {
            //Util.out("Warning *** ChartSetRenderer : ", "chart not used");
            return chartSet;
        }

        chartSet += open(first);
        chartSet += alignTicks(chart.getAlignTricks());
        chartSet += animation(chart.getAnimation());
        chartSet += backgroundColor(chart.getBackgroundColor());
        chartSet += borderColor(chart.getBorderColor());
        chartSet += borderRadius(chart.getBorderRadius());
        chartSet += borderWidth(chart.getBorderWidth());
        chartSet += className(chart.getClassName());
        chartSet += colorCount(chart.getColorCount());
        chartSet += description(chart.getDescription());
        chartSet += height(chart.getHeight());
        chartSet += ignoreHiddenSeries(chart.getIgnoreHiddenSeries());
        chartSet += inverted(chart.getInverted());
        chartSet += marginBottom(chart.getMarginBottom());
        chartSet += marginLeft(chart.getMarginLeft());
        chartSet += marginRight(chart.getMarginRight());
        chartSet += marginTop(chart.getMarginTop());
        chartSet += panKey(chart.getPanKey());
        chartSet += panning(chart.getPanning());
        chartSet += pinchType(chart.getPinchType());
        chartSet += plotBackgrounColor(chart.getPlotBackgroundCorlor());
        chartSet += plotBackgroundImage(chart.getPlotBackgroundImage());
        chartSet += plotBorderColor(chart.getPlotBorderColor());
        chartSet += plotBorderWidth(chart.getPlotBorderWidth());
        chartSet += plotShadow(chart.getPlotShadow());
        chartSet += renderTo(chart.getRenderTo());
        chartSet += type(chart.getType());
        chartSet += zoomType(chart.getZommType());

        chartSet = close(chartSet);
        return chartSet;
    }

    /**
     * Surcharged method to auto set first
     *
     * @param chart chart
     * @return chart
     */
    static public String renderer(ChartSet chart) {
        return renderer(chart, Boolean.TRUE);
    }

    /**
     * <h3>open</h3>
     *
     * @return script to open chart starting with ","
     */
    static private String open(Boolean first) {
        return first ? "chart:{" : ",chart:{";
    }

    /**
     * <h3>alignTicks</h3>
     * Include fonction alignTicks which allow to align two different axis when
     * used
     *
     * @param align true of false
     * @return
     */
    static private String alignTicks(Boolean align) {
        if (align != null) {
            return "alignTicks:" + align + ",";
        }
        return "";
    }

    /**
     * <h3>animation</h3>
     * Include fonction animation which allow to activate or disable animation
     *
     * @param anim enable or disable animation
     * @return script animation
     */
    static private String animation(Boolean anim) {
        if (anim != null) {
            return "animation:" + anim + ",";
        }
        return "";
    }

    /**
     * <h3>backgroundColor</h3>
     * Include fonction backgroundColor which setup backgroundColor
     *
     * @param color hexadecimal string color like #FFFEEE
     * @return script backgroundColor
     */
    static private String backgroundColor(String color) {
        if (color != null) {
            return "backgroundColor:'" + color + "',";
        }
        return "";
    }

    /**
     * <h3>borderColor</h3>
     * Include fonction borderColor which setup borderColor
     *
     * @param color hexadecimal string color like #335cad
     * @return script borderColor
     */
    static private String borderColor(String color) {
        if (color != null) {
            return "borderColor:'" + color + "',";
        }
        return "";
    }

    /**
     * <h3>borderRadius</h3>
     * Include fonction borderRadius which setup the corner
     *
     * @param radius integer value of the corner
     * @return script borderRadius
     */
    static private String borderRadius(Integer radius) {
        if (radius != null) {
            return "borderRadius:" + radius + ",";
        }
        return "";
    }

    /**
     * <h3>borderWidth</h3>
     * Include fonction borderWidth which setup the border width
     *
     * @param width integer value of the width
     * @return script width
     */
    static private String borderWidth(Integer width) {
        if (width != null) {
            return "borderWidth:" + width + ",";
        }
        return "";
    }

    /**
     * <h3>className</h3>
     * Include fonction className which setup the class name
     *
     * @param style style class
     * @return script class
     */
    static private String className(String style) {
        if (style != null) {
            return "className:'" + style + "',";
        }
        return "";
    }

    /**
     * <h3>colorCount</h3>
     * Include fonction colorCount which setup
     *
     * @param count number of color define
     * @return script the color
     */
    static private String colorCount(Integer count) {
        if (count != null) {
            return "colorCount:" + count + ",";
        }
        return "";
    }

    /**
     * <h3>description</h3>
     * Include fonction description which setup
     *
     * @param description a description
     * @return script the description
     */
    static private String description(String descript) {
        if (descript != null) {
            return "description:" + descript + ",";
        }
        return "";
    }

    /**
     * height: Number An explicit height for the chart. By default (when null)
     * the height is calculated from the offset height of the containing
     * element, or 400 pixels if the containing element's height is 0. Defaults
     * to null. Try it:
     *
     * 500px height
     *
     * @param h height
     * @return script the height
     */
    static private String height(Integer h) {
        if (h != null) {
            return "height:" + h + ",";
        }
        return "";
    }

    /**
     * If true, the axes will scale to the remaining visible series once one
     * series is hidden. If false, hiding and showing a series will not affect
     * the axes or the other series. For stacks, once one series within the
     * stack is hidden, the rest of the stack will close in around it even if
     * the axis is not affected. Defaults to true. Try it:
     *
     * True by default false, true with stack
     *
     * @param ignore ignore
     * @return script the ingrore hidden series
     */
    static private String ignoreHiddenSeries(Boolean ignore) {
        if (ignore != null) {
            return "ignoreHiddenSeries:" + ignore + ",";
        }
        return "";
    }

    /**
     * Whether to invert the axes so that the x axis is vertical and y axis is
     * horizontal. When true, the x axis is reversed by default. If a bar series
     * is present in the chart, it will be inverted automatically.
     *
     * Inverting the chart doesn't have an effect if there are no cartesian
     * series in the chart, or if the chart is polar.
     *
     * Defaults to false. Try it:
     *
     * Inverted line
     *
     * @param invert inverted
     * @return script the inverted
     */
    static private String inverted(Boolean invert) {
        if (invert != null) {
            return "inverted:" + invert + ",";
        }
        return "";
    }

    /**
     * The margin between the bottom outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingBottom. Try it:
     *
     * 100px bottom margin
     *
     * @param margin margin
     * @return script the margin bottom
     */
    static private String marginBottom(Integer margin) {
        if (margin != null) {
            return "marginBottom:" + margin + ",";
        }
        return "";
    }

    /**
     * The margin between the left outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingLeft. Try it:
     *
     * 150px left margin
     *
     * @param margin margin left
     * @return script the margin Left
     */
    static private String marginLeft(Integer margin) {
        if (margin != null) {
            return "marginLeft:" + margin + ",";
        }
        return "";
    }

    /**
     * The margin between the right outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingRight. Try it:
     *
     * 100px right margin
     *
     * @param margin margin
     * @return script the margin Right
     */
    static private String marginRight(Integer margin) {
        if (margin != null) {
            return "marginRight:" + margin + ",";
        }
        return "";
    }

    /**
     * The margin between the top outer edge of the chart and the plot area. Use
     * this to set a fixed pixel value for the margin as opposed to the default
     * dynamic margin. See also spacingTop. Try it:
     *
     * 100px top margin
     *
     * @param margin margin
     * @return script the marginTop
     */
    static private String marginTop(Integer margin) {
        if (margin != null) {
            return "marginTop:" + margin + ",";
        }
        return "";
    }

    /**
     * Allows setting a key to switch between zooming and panning. Try it:
     *
     * Hold down shift to pan
     *
     * @param pankey pankey
     * @return script the panKey
     */
    static private String panKey(String pankey) {
        if (pankey != null) {
            return "panKey:" + pankey + ",";
        }
        return "";
    }

    /**
     * Allow panning in a chart. Best used with panKey to combine zooming and
     * panning. Defaults to false. Try it:
     *
     * Zooming and panning
     *
     * @param pan panning
     * @return script the panning
     */
    static private String panning(Boolean pan) {
        if (pan != null) {
            return "panning:" + pan + ",";
        }
        return "";
    }

    /**
     * Equivalent to zoomType, but for multitouch gestures only. By default, the
     * pinchType is the same as the zoomType setting. However, pinching can be
     * enabled separately in some cases, for example in stock charts where a
     * mouse drag pans the chart, while pinching is enabled. Defaults to null.
     *
     * @param pinch pinch
     * @return script the pinch
     */
    static private String pinchType(String pinch) {
        if (pinch != null) {
            return "pinchType:" + pinch + ",";
        }
        return "";
    }

    /**
     * <h3>plotBackgrounColor</h3>
     * Include fonction plotBackgrounColor which setup
     *
     * @param color a description
     * @return script the plotBackGroundColor
     */
    static private String plotBackgrounColor(String color) {
        if (color != null) {
            return "plotBackgrounColor:" + color + ",";
        } else if (color == null) {
            return "plotBackgrounColor:null,";
        }
        return "";
    }

    /**
     * The URL for an image to use as the plot background. To set an image as
     * the background for the entire chart, set a CSS background image to the
     * container element. Note that for the image to be applied to exported
     * charts, its URL needs to be accessible by the export server. Try it:
     *
     * Skies See also:
     *
     * In styled mode, a plot background image can be set with the
     * .highcharts-plot-background class and a custom pattern.
     *
     * @param backgroundImage the background image
     * @return script the plotBackgroundImage
     */
    static private String plotBackgroundImage(String backgroundImage) {
        if (backgroundImage != null) {
            return "plotBackgroundImage:'" + backgroundImage + "',";
        }
        return "";
    }

    /**
     * The color of the inner chart or plot area border. Defaults to #cccccc.
     * Try it:
     *
     * Blue border See also:
     *
     * In styled mode, a plot border stroke can be set with the
     * .highcharts-plot-border class.
     *
     * @param borderColor border color
     * @return script the plotBorderColor
     */
    static private String plotBorderColor(String borderColor) {
        if (borderColor != null) {
            return "plotBorderColor:'" + borderColor + "',";
        }
        return "";
    }

    /**
     * <h3>plotBorderWidth</h3>
     * Include fonction plotBorderWidth which setup
     *
     * @param plotBorderWidth the border width
     * @return script the plotBorderWidth
     */
    static private String plotBorderWidth(Integer width) {
        if (width != null) {
            return "plotBorderWidth:" + width + ",";
        } else if (width == null) {
            return "plotBorderWidth:null,";
        }
        return "";
    }

    /**
     * <h3>plotShadow</h3>
     * Include fonction plotShadow which setup
     *
     * @param plotShadow a plotShadow
     * @return script the plotShadow
     */
    static private String plotShadow(Boolean shadow) {
        if (shadow != null) {
            return "plotShadow:" + shadow + ",";
        }
        return "";
    }

    /**
     * When true, cartesian charts like line, spline, area and column are
     * transformed into the polar coordinate system. Requires
     * highcharts-more.js. Defaults to false. Try it:
     *
     * Polar line, polar area, polar column, combined series types, spider
     * chart, wind rose.
     *
     * @param pol define polor Line, area, column, combine
     * @return encoded script of polar
     */
    static private String polar(Boolean pol) {
        if (pol != null) {
            return "polar:" + pol + ",";
        }
        return "";
    }

    /**
     * reflow: BooleanSince 2.1 Whether to reflow the chart to fit the width of
     * the container div on resizing the window. Defaults to true. Try it:
     *
     * Move the bar between the JavaScript frame and the Preview frame to see
     * the effect: True by default, false.
     *
     * @param flow define if reflow
     * @return encode script for reflow
     */
    static private String reflow(Boolean flow) {
        if (flow != null) {
            return "reflow:" + flow + ",";
        }
        return "";
    }

    /**
     * <h3>renderTo</h3>
     * Include fonction renderTo chart
     *
     * @param render the div id on which to render chart
     * @return script of the render
     */
    static private String renderTo(String render) {
        if (render != null) {
            return "renderTo:'" + render + "',";
        }
        return "";
    }

    /**
     * The button that appears after a selection zoom, allowing the user to
     * reset zoom.
     *
     * @param resetZoomButton setup for resetZoomButton
     * @return encoded script for Reset Zoom Button
     */
    static private String resetZoomButton(ResetZoomButton resetZoomButton) {
        if (resetZoomButton != null) {
            return ResetZoomButtonRenderer.renderer(resetZoomButton, false);
        }
        return "";
    }

    /**
     * The background color of the marker square when selecting (zooming in on)
     * an area of the chart. Defaults to rgba(51,92,173,0.25). See also:
     *
     * In styled mode, the selection marker fill is set with the
     * .highcharts-selection-marker class.
     *
     * @param selectMarkerFill selection marker fill
     * @return encoded script for selection marker fill
     */
    static private String selectionMarkerFill(String selectMarkerFill) {
        if (selectMarkerFill != null) {
            return "selectionMarkerFill:'" + selectMarkerFill + "',";
        }
        return "";
    }

    /**
     * Whether to apply a drop shadow to the outer chart area. Requires that
     * backgroundColor be set. Since 2.3 the shadow can be an object
     * configuration containing color, offsetX, offsetY, opacity and width.
     * Defaults to false. Try it:
     *
     *
     * @param shadowOn enable shadow
     * @return encoded script for shadow
     */
    static private String shadow(Boolean shadowOn) {
        if (shadowOn != null) {
            return "shadow:" + shadowOn + ",";
        }
        return "";
    }

    /**
     * Whether to show the axes initially. This only applies to empty charts
     * where series are added dynamically, as axes are automatically added to
     * cartesian series. Defaults to false. Try it:
     *
     * False by default, true
     *
     * @param show show axes
     * @return encoded script for show axes
     */
    static private String showAxes(Boolean show) {
        if (show != null) {
            return "showAxes:" + show + ",";
        }
        return "";
    }

    /**
     * The space between the bottom edge of the chart and the content (plot
     * area, axis title and labels, title, subtitle or legend in top position).
     *
     * Defaults to 15. Try it:
     *
     * Spacing bottom set to 100.
     * 
     * @param spacin spacing bottom
     * @return encoded script for spacing bottom
     */
    static private String spacingBottom(Integer spacin) {
        if (spacin != null) {
            return "spacingBottom:" + spacin + ",";
        }
        return "";
    }

    /**
     * The space between the left edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     *
     * Defaults to 10. Try it:
     *
     * Spacing left set to 100
     * 
     * @param spacing spacing left
     * @return encoded script for spacinf left
     */
    static private String spacingLeft(Integer spacing) {
        if (spacing != null) {
            return "spacingLeft:" + spacing + ",";
        }
        return "";
    }

    /**
     * The space between the right edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     *
     * Defaults to 10. Try it:
     *
     * Spacing set to 100, legend in right position with default spacing
     * 
     * @param spacing spacing right
     * @return encoded script for spacing right
     */
    static private String spacingRight(Integer spacing) {
        if (spacing != null) {
            return "spacingRight:" + spacing + ",";
        }
        return "";
    }

    /**
     * The space between the top edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     *
     * Defaults to 10. Try it:
     *
     * A top spacing of 100, floating chart title makes the plot area align to
     * the default spacingTop of 10..
     * 
     * @param spacing spacing top
     * @return encoded spacing top
     */
    static private String spacingTop(Integer spacing) {
        if (spacing != null) {
            return "spacingTop:" + spacing + ",";
        }
        return "";
    }

    /**
     * Additional CSS styles to apply inline to the container div. Note that
     * since the default font styles are applied in the renderer, it is ignorant
     * of the individual chart options and must be set globally. Defaults to
     * {"fontFamily":"\"Lucida Grande\", \"Lucida Sans Unicode\", Verdana,
     * Arial, Helvetica, sans-serif","fontSize":"12px"}. Try it:
     *
     * Using a serif type font. Styled mode with relative font sizes. See also:
     *
     * In styled mode, general chart styles can be set with the .highcharts-root
     * class.
     * 
     * @param styling
     * @return 
     */
    static private String style(String styling) {
        if (styling != null) {
            return "style:" + styling + ",";
        }
        return "";
    }

    /**
     * <h3>type</h3>
     * Include fonction type to chart
     *
     * @param chartType type of the line
     * @return script of type line
     */
    static private String type(ChartType chartType) {
        if (chartType.toString() != null) {
            return "type:'" + chartType.toString() + "',";
        }
        return "";
    }

    /**
     * A text description of the chart type.
     *
     * If the Accessibility module is loaded, this will be included in the
     * description of the chart in the screen reader information region.
     *
     * Highcharts will by default attempt to guess the chart type, but for more
     * complex charts it is recommended to specify this property for clarity.
     *
     * Defaults to undefined. Try it:
     *
     * Accessible complex chart
     * 
     * @param descript type description
     * @return encoded script for type description
     */
    static private String typeDescription(String descript) {
        if (descript != null) {
            return "typeDescription:'" + descript + "',";
        }
        return "";
    }

    /**
     * An explicit width for the chart. By default (when null) the width is
     * calculated from the offset width of the containing element. Defaults to
     * null. Try it:
     *
     * 800px wide
     * 
     * @param w with
     * @return encoded script width
     */
    static private String width(Integer w) {
        if (w != null) {
            return "width:" + w + ",";
        }
        return "";
    }

    /**
     * <h3>zoomType</h3>
     * Include fonction zoomType which setup x, y, xy zoom type
     *
     * @param type is the type of zomm which is one of x, y, xy
     * @return script of zoom type
     */
    static private String zoomType(String type) {
        if (type != null) {
            return "zoomType:'" + type + "',";
        }
        return "";
    }

    /**
     * <h3>close</h3>
     *
     * @return script to close chart starting with
     */
    static private String close(String chartSet) {
        chartSet = chartSet.substring(0, chartSet.length() - 1);
        chartSet += "}";
        return chartSet;
    }

}
