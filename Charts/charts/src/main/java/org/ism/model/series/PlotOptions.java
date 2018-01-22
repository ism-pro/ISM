/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.series;

/**
 *
 * <h2>Description</h2>
 * The plotOptions is a wrapper object for config objects for each series type.
 * The config objects for each series can also be overridden for each series
 * item as given in the series array.
 *
 * Configuration options for the series are given in three levels. Options for
 * all series in a chart are given in the plotOptions.series object. Then
 * options for all series of a specific type are given in the plotOptions of
 * that type, for example plotOptions.line. Next, options for one single series
 * are given in the series array.
 *
 *
 * @author r.hendrick
 */
public class PlotOptions extends SerieIn {

    /**
     *
     * @param plotOptions
     * @return
     */
    static public SerieIn toSerieIn(PlotOptions plotOptions) {
        SerieIn serieIn = plotOptions;
        return serieIn;
    }
}
