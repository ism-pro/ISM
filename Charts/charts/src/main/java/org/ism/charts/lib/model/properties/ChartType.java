/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.properties;

/**
 *
 * @author r.hendrick
 */
public enum ChartType {

    AREA("area"),
    AREA_RANGE("arearange"),
    AREA_SPLINE("areaspline"),
    AREA_SPLINE_RANGE("areasplinerange"),
    BAR("bar"),
    BOX_PLOT("boxplot"),
    BUBBLE("bubble"),
    COLUMN("column"),
    COLUMN_RANGE("columnrange"),
    ERROR_BAR("errorbar"),
    FUNNEL("funnel"),
    GAUGE("gauge"),
    HEATMAP("heatmap"),
    LINE("line"),
    PIE("pie"),
    PLOTOPTIONS("plotoptions");

    private String text;

    ChartType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    /**
     * Convert Type
     *
     * @param type type in string
     * @return the type in ChartTYpe
     */
    static public ChartType convert(String type) {
        switch (type) {
            case "area":
                return AREA;
            case "arearange":
                return AREA_RANGE;
            case "areaspline":
                return AREA_SPLINE;
            case "areasplinerange":
                return AREA_SPLINE_RANGE;
            case "bar":
                return BAR;
            case "boxplot":
                return BOX_PLOT;
            case "bubble":
                return BUBBLE;
            case "column":
                return COLUMN;
            case "columnrange":
                return COLUMN_RANGE;
            case "errorbar":
                return ERROR_BAR;
            case "funnel":
                return FUNNEL;
            case "gauge":
                return GAUGE;
            case "heatmap":
                return HEATMAP;
            case "line":
                return LINE;
            case "pie":
                return PIE;
            case "plotoptions":
                return PLOTOPTIONS;
        }
        return null;
    }
}
