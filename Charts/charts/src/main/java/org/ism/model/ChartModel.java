/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model;

import org.ism.model.details.Title;
import org.ism.model.axis.XAxis;
import org.ism.model.axis.YAxis;
import org.ism.model.details.SubTitle;
import org.ism.model.series.Legend;
import org.ism.model.series.PlotOptions;
import org.ism.model.series.Series;
import org.ism.model.series.ToolTip;

/**
 *
 * @author r.hendrick
 */
public class ChartModel {

    private ChartSet chart = new ChartSet();

    // Nouvelle formule
    private PlotOptions plotOptions = null;
    Legend legend = null;
    ToolTip toolTip = null;
    private XAxis xAxis = null;
    private YAxis yAxis = null;
    Series series = null;
    private Title title = null;
    private SubTitle subTitle = null;

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

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public SubTitle getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(SubTitle subTitle) {
        this.subTitle = subTitle;
    }

    public ChartSet getChart() {
        if (chart == null) {
            chart = new ChartSet();
        }
        return chart;
    }

    public void setChart(ChartSet chart) {
        this.chart = chart;
    }

}
