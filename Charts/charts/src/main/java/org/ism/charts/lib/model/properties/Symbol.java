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
public enum Symbol {

    CIRCLE("circle"),
    SQUARE("square"),
    DIAMOND("diamond"),
    TRIANGLE("triangle"),
    TRIANGLE_DOWN("triangle-down");

    private String text;

    Symbol(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
