/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.showcase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.ism.charts.lib.model.properties.ChartType;
import org.ism.charts.lib.model.ChartModel;
import org.ism.charts.lib.model.axis.AxisTitle;
import org.ism.charts.lib.model.properties.Align;
import org.ism.charts.lib.model.properties.DataLabels;
import org.ism.charts.lib.model.series.Data;
import org.ism.charts.lib.model.series.Legend;
import org.ism.charts.lib.model.series.PlotOptions;
import org.ism.charts.lib.model.series.Series;
import org.ism.charts.lib.model.series.ToolTip;
import org.ism.charts.lib.model.series.type.BarSerie;

/**
 *
 * @author r.hendrick
 */
@ManagedBean(name = "bar")
public class Bar implements Serializable {

    private ChartModel basicBar;

    @PostConstruct
    public void init() {
        createBasicBarModels();
    }

    private void createBasicBarModels() {
        ChartModel model = new ChartModel();
        model.getChart().setType(ChartType.BAR);
        // Seutp Title
        model.getTitle().setText("Historic World Population by Region");
        //model.getTitle().setX(-20);
        // Setup SubTitle
        model.getSubTitle().setText("Source: <a href=\"https://en.wikipedia.org/wiki/World_population\">Wikipedia.org</a>");
        //model.getSubTitle().setX(-20);
        
        // Setup xAxis
        List<String> xAxis = new ArrayList<String>();
        Collections.addAll(xAxis, "Africa", "America", "Asia", "Europe", "Oceania");
        model.getxAxis().setCategories(xAxis);
        model.getxAxis().setTitle(null);

        // Setup yAxis
        model.getyAxis().setMin(0);
        model.getyAxis().getTitle().setAlign(AxisTitle.HIGH);
        model.getyAxis().getTitle().setText("Population (millions)");

        // Setup Tooltip
        model.setToolTip(new ToolTip());
        model.getToolTip().setValueSuffix(" millions");

        //Plot Options
        model.setPlotOptions(new PlotOptions());
        model.getPlotOptions().setType(ChartType.BAR);
        model.getPlotOptions().setDataLabels(new DataLabels());
        model.getPlotOptions().getDataLabels().setEnabled(true);

        // Setup Legend
        model.setLegend(new Legend());
        model.getLegend().setLayout(Align.VERTICAL);
        model.getLegend().setAlign(Align.RIGHT);
        model.getLegend().setVerticalAlign(Align.TOP);
        model.getLegend().setX(-40);
        model.getLegend().setY(80);
        model.getLegend().setFloating(true);
        model.getLegend().setBorderWidth(1);
        model.getLegend().setBackgroundColor("((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF')");
        model.getLegend().setShadow(true);

        // Series
        List<Object> year1800 = new ArrayList<>();
        Collections.addAll(year1800, 107, 31, 635, 203, 2);
        List<Object> year1900 = new ArrayList<>();
        Collections.addAll(year1900, 133, 156, 947, 408, 6);
        List<Object> year2012 = new ArrayList<>();
        Collections.addAll(year2012, 1052, 954, 4250, 740, 38);

        model.setSeries(new Series());
        model.getSeries().setBarSerie(new BarSerie());
        model.getSeries().getBarSerie().setData(new ArrayList<>());
        model.getSeries().getBarSerie().getData().add(new Data("Year 1800", year1800));
        model.getSeries().getBarSerie().getData().add(new Data("Year 1900", year1900));
        model.getSeries().getBarSerie().getData().add(new Data("Year 2012", year2012));

        basicBar = model;
    }

    public ChartModel getBasicBar() {
        return basicBar;
    }

    public void setBasicBar(ChartModel basicBar) {
        this.basicBar = basicBar;
    }

}
