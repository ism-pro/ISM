/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model;

import java.util.ArrayList;
import org.ism.charts.lib.model.axis.XAxis;
import java.util.Iterator;
import java.util.List;
import org.ism.charts.lib.model.axis.YAxis;
import org.ism.charts.lib.model.properties.ChartType;
import org.ism.charts.lib.model.properties.DataLabels;
import org.ism.charts.lib.model.series.Legend;
import org.ism.charts.lib.model.series.PlotOptions;
import org.ism.charts.lib.model.series.Series;
import org.ism.charts.lib.model.series.ToolTip;

/**
 *
 * @author r.hendrick
 */
public class ChartModel {

    private ChartSet chart = new ChartSet();
    private ChartTitle title = null;
    private ChartTitle subTitle = null;
    //private ChartToolTip tooltip = null;
    //private ChartLegend legend = null;
    //private ChartPlotOptions plotOptions = null;
    //private List<ChartSerie> series = new ArrayList<>();
    //private ChartSerieMarker marker = null;

    // Nouvelle formule
    private PlotOptions plotOptions = null;
    Legend legend = null;
    ToolTip toolTip = null;
    private XAxis xAxis = null;
    private YAxis yAxis = null;
    Series series = null;

  
    /**
     * <h3>renderTitle</h3>
     *
     * Allow to convert Title object to highchart formated Note start with
     * ",Title"
     *
     * @return mapped for encoded script
     */
    public String renderTitle() {
        String strTitle = "";
        if (title == null) {
            return "";
        }
        if (!title.isUnused()) {
            return "";
        }

        strTitle = ",title:{";
        if (title.getAlign() != null) {
            strTitle += "align:" + title.getAlign().name() + ",";
        }
        if (title.getFloating() != null) {
            strTitle += "floating:" + title.getAlign().name() + ",";
        }
        if (title.getMargin() != null) {
            strTitle += "margin:" + title.getMargin().toString() + ",";
        }
        if (title.getStyle() != null) {
            strTitle += "style:" + title.getStyle() + ",";
        }
        if (title.getText() != null) {
            strTitle += "text:'" + title.getText() + "',";
        }
        if (title.getUseHTML() != null) {
            strTitle += "useHTML:" + title.getUseHTML().toString() + ",";
        }
        if (title.getVerticalAlign() != null) {
            strTitle += "verticalAlign:" + title.getVerticalAlign().name() + ",";
        }
        if (title.getWidthAdjust() != null) {
            strTitle += "widthAdjust:" + title.getWidthAdjust().toString() + ",";
        }
        if (title.getX() != null) {
            strTitle += "x:" + title.getX().toString() + ",";
        }
        if (title.getY() != null) {
            strTitle += "y:" + title.getY().toString() + ",";
        }

        // Remove last ","
        strTitle = strTitle.substring(0, strTitle.length() - 1);
        // close
        strTitle += "}";

        return strTitle;
    }

    /**
     * <h3>renderSubTitle</h3>
     *
     * Allow to convert Title object to highchart formated Note start with
     * ",subtitle"
     *
     * @return mapped for encoded script
     */
    public String renderSubTitle() {
        String strSubTitle = "";
        if (subTitle == null) {
            return "";
        }
        if (!subTitle.isUnused()) {
            return "";
        }

        strSubTitle = ",subtitle:{";
        if (subTitle.getAlign() != null) {
            strSubTitle += "align:" + subTitle.getAlign().name() + ",";
        }
        if (subTitle.getFloating() != null) {
            strSubTitle += "floating:" + subTitle.getAlign().name() + ",";
        }
        if (subTitle.getMargin() != null) {
            strSubTitle += "margin:" + subTitle.getMargin().toString() + ",";
        }
        if (subTitle.getStyle() != null) {
            strSubTitle += "style:" + subTitle.getStyle() + ",";
        }
        if (subTitle.getText() != null) {
            strSubTitle += "text:'" + subTitle.getText() + "',";
        }
        if (subTitle.getUseHTML() != null) {
            strSubTitle += "useHTML:" + subTitle.getUseHTML().toString() + ",";
        }
        if (subTitle.getVerticalAlign() != null) {
            strSubTitle += "verticalAlign:" + subTitle.getVerticalAlign().name() + ",";
        }
        if (subTitle.getWidthAdjust() != null) {
            strSubTitle += "widthAdjust:" + subTitle.getWidthAdjust().toString() + ",";
        }
        if (subTitle.getX() != null) {
            strSubTitle += "x:" + subTitle.getX().toString() + ",";
        }
        if (subTitle.getY() != null) {
            strSubTitle += "y:" + subTitle.getY().toString() + ",";
        }

        // Remove last ","
        strSubTitle = strSubTitle.substring(0, strSubTitle.length() - 1);
        // close
        strSubTitle += "}";

        return strSubTitle;
    }


    /**
     * <h3>renderSerie</h3>
     *
     * Allow to convert serie to highchart formated. Note starte with ,serie:
     * ...
     *
     * @return mapped for encoded script
     */
//    public String renderSerie() {
//        String strSeries = "";
//        if (this.getSeries().isEmpty()) {
//            return strSeries;
//        }
//
//        List<ChartSerie> series = this.getSeries();
//
//        Iterator<ChartSerie> itrSerie = series.iterator();
//        strSeries += ",series:[";
//        while (itrSerie.hasNext()) {
//            ChartSerie serie = itrSerie.next();
//            strSeries += "{";
//            if (serie.getName() != null) {
//                strSeries += "name:'" + serie.getName() + "',";
//            }
//            if (serie.getColorByPoint() != null) {
//                strSeries += "colorByPoint:" + serie.getColorByPoint().toString() + ",";
//            }
//            //
//            // MANAGE MARKER
//            if (serie.getMarker().isUnused()) {
//                strSeries += "marker:{";
//                if (serie.getMarker().getEnable() != null) {
//                    strSeries += "enabled:" + serie.getMarker().getEnable().toString() + ",";
//                }
//                if (serie.getMarker().getFillColor() != null) {
//                    strSeries += "fillColor:\"" + serie.getMarker().getFillColor() + "\",";
//                }
//                if (serie.getMarker().getHeight() != null) {
//                    strSeries += "height:" + serie.getMarker().getHeight().toString() + ",";
//                }
//                if (serie.getMarker().getLineColor() != null) {
//                    strSeries += "lineColor:\"" + serie.getMarker().getLineColor() + "\",";
//                }
//                if (serie.getMarker().getLineWidth() != null) {
//                    strSeries += "lineWidth:" + serie.getMarker().getLineWidth().toString() + ",";
//                }
//                if (serie.getMarker().getRadius() != null) {
//                    strSeries += "radius:" + serie.getMarker().getRadius().toString() + ",";
//                }
//                if (serie.getMarker().getSymbol() != null) {
//                    strSeries += "symbol:'" + serie.getMarker().getSymbol() + "',";
//                }
//                if (serie.getMarker().getWidth() != null) {
//                    strSeries += "width:" + serie.getMarker().getWidth().toString() + ",";
//                }
//                strSeries = strSeries.substring(0, strSeries.length() - 1);
//                strSeries += "},";
//            }
//
//            //
//            // Manage DATA
//            if (serie.getDatas() != null) {
//                strSeries += "data:[";
//                // Insertion de donn�e
//                List<ChartSerieData> datas = serie.getDatas();
//                Iterator<ChartSerieData> itrData = datas.iterator();
//                while (itrData.hasNext()) {
//                    ChartSerieData data = itrData.next();
//                    strSeries += "{";
//                    if (data.getName() != null) {
//                        strSeries += "name:'" + data.getName() + "',";
//                    }
//                    if (data.getY() != null) {
//                        strSeries += "y:" + data.getY() + ",";
//                    }
//                    if (data.getYs() != null) {
//                        strSeries = strSeries.substring(0, strSeries.length() - 1);
//                        Iterator<Double> itrYs = data.getYs().iterator();
//                        while (itrYs.hasNext()) {
//                            strSeries += itrYs.next() + ",";
//                        }
//                    }
//                    if (data.getSliced() != null) {
//                        strSeries += "sliced:" + data.getSliced().toString() + ",";
//                    }
//                    if (data.getSelected() != null) {
//                        strSeries += "selected:" + data.getSelected().toString() + ",";
//                    }
//                    strSeries = strSeries.substring(0, strSeries.length() - 1);
//                    // Managing chartype
//                    if (chart.getType() == ChartType.LINE) {
//                        strSeries += "]},";
//                    } else {
//                        strSeries += "},";
//                    }
//                }
//                if (chart.getType() != ChartType.LINE) {
//                    strSeries = strSeries.substring(0, strSeries.length() - 1);
//                    strSeries += "]";
//                }
//            }
//            if (chart.getType() == ChartType.LINE) {
//                //strSeries = strSeries.substring(0, strSeries.length() - 1);
//            } else {
//                strSeries += "}]";
//            }
//        }
//        if (chart.getType() == ChartType.LINE) {
//            strSeries = strSeries.substring(0, strSeries.length() - 1);
//            strSeries += "]";
//        }
//        return strSeries;
//    }
 
    public ChartSet getChart() {
        if (chart == null) {
            chart = new ChartSet();
        }
        return chart;
    }

    public void setChart(ChartSet chart) {
        this.chart = chart;
    }

    public ChartTitle getTitle() {
        if (title == null) {
            title = new ChartTitle("Title");
        }
        return title;
    }

    public void setTitle(ChartTitle title) {
        getTitle();
        this.title = title;
    }

    public ChartTitle getSubTitle() {
        if (subTitle == null) {
            subTitle = new ChartTitle("subTitle", "{\"color\":\"#666666\"}");
        }
        return subTitle;
    }

    public void setSubTitle(ChartTitle subTitle) {
        getSubTitle();
        this.subTitle = subTitle;
    }

//    public ChartToolTip getTooltip() {
//        if (tooltip == null) {
//            tooltip = new ChartToolTip();
//        }
//        return tooltip;
//    }
//    public ChartLegend getLegend() {
//        if (legend == null) {
//            legend = new ChartLegend();
//        }
//        return legend;
//    }
//
//    public void setLegend(ChartLegend legend) {
//        getLegend();
//        this.legend = legend;
//    }
//    public void setTooltip(ChartToolTip tooltip) {
//        getTooltip();
//        this.tooltip = tooltip;
//    }
//    public ChartPlotOptions getPlotOptions() {
//        if (plotOptions == null) {
//            plotOptions = new ChartPlotOptions();
//        }
//        return plotOptions;
//    }
//
//    public void setPlotOptions(ChartPlotOptions plotOptions) {
//        getPlotOptions();
//        this.plotOptions = plotOptions;
//    }
    public XAxis getxAxis() {
        if (xAxis == null) {
            xAxis = new XAxis();
        }
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        getxAxis();
        this.xAxis = xAxis;
    }

    public YAxis getyAxis() {
        if (yAxis == null) {
            yAxis = new YAxis();
        }
        return yAxis;
    }

    public void setyAxis(YAxis yAxis) {
        getyAxis();
        this.yAxis = yAxis;
    }

//    public List<ChartSerie> getSeries() {
//        return series;
//    }
//
//    public void setSeries(List<ChartSerie> series) {
//        this.series = series;
//    }
    /**
     * General marker not apply to any series. Need to use method markerApplyTo
     * to set marker to a serie, or markerApply to set marker to ll series
     *
     * @return marker
     */
//    public ChartSerieMarker getMarker() {
//        if (marker == null) {
//            marker = new ChartSerieMarker();
//        }
//        return marker;
//    }
//
//    /**
//     * Affect marekr by new value
//     *
//     * @param marker
//     */
//    public void setMarker(ChartSerieMarker marker) {
//        this.marker = marker;
//    }

    public PlotOptions getPlotOptions() {
        return plotOptions;
    }

    public void setPlotOptions(PlotOptions plotOptions) {
        this.plotOptions = plotOptions;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public ToolTip getToolTip() {
        return toolTip;
    }

    public void setToolTip(ToolTip toolTip) {
        this.toolTip = toolTip;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

}
