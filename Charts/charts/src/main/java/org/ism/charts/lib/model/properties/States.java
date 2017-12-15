/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.properties;

/**
 * <h1>States</h1><br>
 * States class
 *
 * @author r.hendrick
 *
 */
public class States {

    /**
     * On over
     */
    private Hover hover = null;

    /**
     * select The appearance of the point marker when selected. In order to
     * allow a point to be selected, set the series.allowPointSelect option to
     * true.
     */
    private Select select = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /// Getter/Setter
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public Hover getHover() {
        return hover;
    }

    public void setHover(Hover hover) {
        this.hover = hover;
    }

    public Select getSelect() {
        return select;
    }

    public void setSelect(Select select) {
        this.select = select;
    }

}
