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
import org.ism.model.axis.PlotLines;
import org.ism.model.details.SubTitle;
import org.ism.model.details.Title;
import org.ism.model.json.Options;
import org.ism.model.properties.Align;
import org.ism.model.properties.DataLabels;
import org.ism.model.series.Data;
import org.ism.model.series.Legend;
import org.ism.model.series.type.LineSerie;
import org.ism.model.series.PlotOptions;
import org.ism.model.series.Series;
import org.ism.model.series.ToolTip;

/**
 *
 * @author r.hendrick
 */
@ManagedBean(name = "lineController")
@SessionScoped
public class LineController implements Serializable {

    private ChartModel basicLineModel;
    private ChartModel withDataLabel;

    private String outputMessage = "Empty";
    private Options options;

    @PostConstruct
    public void init() {
        createBasicLineModel();
        createWidthDataLabel();
    }

    /**
     *
     */
    private void createBasicLineModel() {
        ChartModel model = new ChartModel();
        model.getChart().setType(ChartType.LINE);
        // Seutp Title
        model.setTitle(new Title("Monthly Average Temperature"));
        model.getTitle().setX(-20);
        // Setup SubTitle
        model.setSubTitle(new SubTitle("Source: WorldClimate.com"));
        model.getSubTitle().setX(-20);
        // Setup xAxis
        List<String> xAxis = new ArrayList<>();
        Collections.addAll(xAxis, "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        model.getxAxis().setCategories(xAxis);
        // Setup yAxis
        model.getyAxis().getTitle().setText("Temperature (°C)");
        model.getyAxis().setPlotLines(new PlotLines());
        model.getyAxis().getPlotLines().setValue(0);
        model.getyAxis().getPlotLines().setWidth(1);
        model.getyAxis().getPlotLines().setColor("#808080");

        // Setup Tooltip
        model.setToolTip(new ToolTip());
        model.getToolTip().setValueSuffix("°C");

        // Setup Legend
        model.setLegend(new Legend());
        model.getLegend().setLayout(Align.VERTICAL);
        model.getLegend().setAlign(Align.RIGHT);
        model.getLegend().setVerticalAlign(Align.MIDDLE);
        model.getLegend().setBorderWidth(0);
        
        // Setup PlotOptions
        model.setPlotOptions(new PlotOptions());
        //model.getPlotOptions().setAllowPointSelect(true);
        model.getPlotOptions().setLineWidth(1);
        

        // Series
        List<Object> lTokyo = new ArrayList<>();
        Collections.addAll(lTokyo, 7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6);
        List<Object> lNewYorck = new ArrayList<>();
        Collections.addAll(lNewYorck, -0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5);
        List<Object> lBerlin = new ArrayList<>();
        Collections.addAll(lBerlin, -0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0);
        List<Object> lLondon = new ArrayList<>();
        Collections.addAll(lLondon, 3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8);

        model.setSeries(new Series());
        model.getSeries().setLineSerie(new LineSerie());
        model.getSeries().getLineSerie().setData(new ArrayList<>());
        model.getSeries().getLineSerie().getData().add(new Data("Tokyo", lTokyo));
        model.getSeries().getLineSerie().getData().add(new Data("New York", lNewYorck));
        model.getSeries().getLineSerie().getData().add(new Data("Berlin", lBerlin));
        model.getSeries().getLineSerie().getData().add(new Data("London", lLondon));

        basicLineModel = model;
    }

    private void createWidthDataLabel() {
        ChartModel model = new ChartModel();
        model.getChart().setType(ChartType.LINE);
        // Seutp Title
        model.setTitle(new Title("Monthly Average Temperature"));
        model.getTitle().setX(-20);
        // Setup SubTitle
        model.setSubTitle(new SubTitle("Source: WorldClimate.com"));
        model.getSubTitle().setX(-20);
        // Setup xAxis
        List<String> xAxis = new ArrayList<>();
        Collections.addAll(xAxis, "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        model.getxAxis().setCategories(xAxis);

        // Setup yAxis
        model.getyAxis().getTitle().setText("Temperature (°C)");
        model.getyAxis().setPlotLines(new PlotLines());
        model.getyAxis().getPlotLines().setValue(0);
        model.getyAxis().getPlotLines().setWidth(1);
        model.getyAxis().getPlotLines().setColor("#808080");
        // Setup Tooltip
//        model.setToolTip(new ToolTip());
//        model.getToolTip().setValueSuffix("°C");
//        // Setup Legend
//        model.setLegend(new Legend());
//        model.getLegend().setLayout(Align.VERTICAL);
//        model.getLegend().setAlign(Align.RIGHT);
//        model.getLegend().setVerticalAlign(Align.MIDDLE);
//        model.getLegend().setBorderWidth(0);

        // Plot Options
        model.setPlotOptions(new PlotOptions());
        model.getPlotOptions().setDataLabels(new DataLabels());
        model.getPlotOptions().getDataLabels().setEnabled(true);
        model.getPlotOptions().setEnableMouseTracking(true);

        // Series
        List<Object> lTokyo = new ArrayList<>();
        Collections.addAll(lTokyo, 7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6);
        List<Object> lLondon = new ArrayList<>();
        Collections.addAll(lLondon, 3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8);

        model.setSeries(new Series());
        model.getSeries().setLineSerie(new LineSerie());
        model.getSeries().getLineSerie().setData(new ArrayList<Data>());
        model.getSeries().getLineSerie().getData().add(new Data("Tokyo", lTokyo));
        model.getSeries().getLineSerie().getData().add(new Data("London", lLondon));

        withDataLabel = model;
    }

    //
    //
    //
    //
    //
    public void handleSerieClick(SerieClickEvent event) {
        Integer index = event.getIndex();
        options =  event.getOptions();
        outputMessage = "Selected serei id  = " + index;
    }

    //
    //
    //
    //
    //
    public ChartModel getBasicLineModel() {
        return basicLineModel;
    }

    public ChartModel getWithDataLabel() {
        return withDataLabel;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
    
    

}
