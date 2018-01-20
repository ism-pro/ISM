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
import javax.faces.bean.SessionScoped;
import org.ism.event.SerieClickEvent;
import org.ism.model.properties.ChartType;
import org.ism.model.ChartModel;
import org.ism.model.axis.AxisTitle;
import org.ism.model.details.SubTitle;
import org.ism.model.details.Title;
import org.ism.model.properties.Align;
import org.ism.model.properties.DataLabels;
import org.ism.model.series.Data;
import org.ism.model.series.Legend;
import org.ism.model.series.PlotOptions;
import org.ism.model.series.Series;
import org.ism.model.series.ToolTip;
import org.ism.model.series.type.BarSerie;

/**
 *
 * @author r.hendrick
 */
@ManagedBean(name = "barController")
@SessionScoped
public class BarController implements Serializable {

    private ChartModel basicBar;
    
    private String outputMessage = "Empty";

    @PostConstruct
    public void init() {
        createBasicBarModels();
    }

    private void createBasicBarModels() {
        ChartModel model = new ChartModel();
        model.getChart().setType(ChartType.BAR);
        // Seutp Title
        model.setTitle(new Title("Historic World Population by Region"));

        // Setup SubTitle
        model.setSubTitle(new SubTitle("Source: <a href=\"https://en.wikipedia.org/wiki/World_population\">Wikipedia.org</a>"));

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

    //
    //
    //
    //
    //
    public void handleSerieClick(SerieClickEvent event) {
//        Integer selectedSerieId = Integer.valueOf(event.getObject().toString());
  //      outputMessage = "Selected serei id  = " + selectedSerieId ;
    }


    //
    //
    //
    //
    //
    public ChartModel getBasicBar() {
        return basicBar;
    }

    public void setBasicBar(ChartModel basicBar) {
        this.basicBar = basicBar;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    
}
