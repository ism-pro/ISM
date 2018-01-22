/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.series.type;

import org.ism.model.series.SerieIn;

/**
 * <h1>PieSerie</h1><br>
 *
 * PieSerie class extends SerieIn model. Line Serie predifine some setup in
 * order to be faster for model specification
 *
 * @author r.hendrick
 *
 */
public class PieSerie extends SerieIn {

    /**
     * 
     * @param pieSerie
     * @return 
     */
    static public SerieIn toSerieIn(PieSerie pieSerie){
        SerieIn serieIn = pieSerie;
        return serieIn;
    }
}
