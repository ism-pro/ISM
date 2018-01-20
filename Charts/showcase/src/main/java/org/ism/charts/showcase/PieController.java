/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.showcase;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.ism.model.properties.ChartType;
import org.ism.model.ChartModel;
import org.ism.model.details.Title;
import org.ism.model.properties.Align;
import org.ism.model.properties.DataLabels;
import org.ism.model.properties.Style;
import org.ism.model.series.Data;
import org.ism.model.series.Legend;
import org.ism.model.series.PlotOptions;
import org.ism.model.series.Series;
import org.ism.model.series.ToolTip;
import org.ism.model.series.type.PieSerie;

/**
 *
 * @author r.hendrick
 */
@ManagedBean(name = "pieController")
@SessionScoped
public class PieController implements Serializable {

    private ChartModel pieModel;

    @PostConstruct
    public void init() {
        createBasicPieModels();
    }

    private void createBasicPieModels() {
        ChartModel model = new ChartModel();
        model.getChart().setType(ChartType.PIE);
        model.getChart().setPlotShadow(false);
        
        // Setup
        model.setTitle(new Title("Browser market shares January, 2015 to May, 2015"));

        // Setup Tooltip
        model.setToolTip(new ToolTip());
        model.getToolTip().setPointFormat("{series.name}: <b>{point.percentage:.1f}%</b>");

        //Plot Options
        model.setPlotOptions(new PlotOptions());
        model.getPlotOptions().setType(ChartType.PIE);
        model.getPlotOptions().setAllowPointSelect(true);
        model.getPlotOptions().setCursor("pointer");
        model.getPlotOptions().setDataLabels(new DataLabels());
        model.getPlotOptions().getDataLabels().setEnabled(true);
        model.getPlotOptions().getDataLabels().setFormat("<b>{point.name}</b>: {point.percentage:.1f} %");
        model.getPlotOptions().getDataLabels().setStyle(new Style("(Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'"));

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
        Data mie = new Data("Microsoft Internet Explorer", 56.33);
        Data chr = new Data("Chrome", 24.03);
        chr.setSliced(true);
        chr.setSelected(true);
        Data fir = new Data("Firefox", 10.38);
        Data saf = new Data("Safari", 4.77);
        Data ope = new Data("Opera", 0.91);
        Data oth = new Data("Proprietary or Undetectable", 0.2);

        model.setSeries(new Series());
        model.getSeries().setPieSerie(new PieSerie());
        model.getSeries().getPieSerie().setName("Brands");
        model.getSeries().getPieSerie().setColorByPoint(true);
        model.getSeries().getPieSerie().setData(new ArrayList<>());
        model.getSeries().getPieSerie().getData().add(mie);
        model.getSeries().getPieSerie().getData().add(chr);
        model.getSeries().getPieSerie().getData().add(fir);
        model.getSeries().getPieSerie().getData().add(saf);
        model.getSeries().getPieSerie().getData().add(ope);
        model.getSeries().getPieSerie().getData().add(oth);



        pieModel = model;
    }

    public ChartModel getBasicBar() {
        return pieModel;
    }

    public void setBasicBar(ChartModel basicBar) {
        this.pieModel = basicBar;
    }

}
