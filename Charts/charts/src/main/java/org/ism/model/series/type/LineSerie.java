/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.series.type;

import org.ism.model.series.SerieIn;

/**
 * <h1>LineSerie</h1><br>
 *
 * LineSerie class extends SerieIn model. Line Serie predifine some setup in
 * order to be faster for model specification
 *
 * @author r.hendrick
 *
 */
public class LineSerie extends SerieIn {

    /**
     * 
     * @param lineSerie
     * @return 
     */
    static public SerieIn toSerieIn(LineSerie lineSerie){
        SerieIn serieIn = lineSerie;
        return serieIn;
    }
}
