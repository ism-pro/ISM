/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.series;


import com.google.gson.Gson;
import org.ism.model.series.type.BarSerie;
import org.ism.model.series.type.LineSerie;
import org.ism.model.series.type.PieSerie;
import org.ism.util.Util;

/**
 * <h1>Series</h1><br>
 * Series class
 *
 * @author r.hendrick
 *
 */
public class Series {

    private LineSerie lineSerie = null;
    private BarSerie barSerie = null;
    private PieSerie pieSerie = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public LineSerie getLineSerie() {
        return lineSerie;
    }

    public void setLineSerie(LineSerie lineSerie) {
        this.lineSerie = lineSerie;
    }

    public BarSerie getBarSerie() {
        return barSerie;
    }

    public void setBarSerie(BarSerie barSerie) {
        this.barSerie = barSerie;
    }

    public PieSerie getPieSerie() {
        return pieSerie;
    }

    public void setPieSerie(PieSerie pieSerie) {
        this.pieSerie = pieSerie;
    }



}
