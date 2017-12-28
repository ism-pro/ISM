/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression;

/**
 *
 * @author r.hendrick
 */
public class SearchExpressionHint {

    public static final int NONE = 0x0;

    /**
     * Checks if the {@link UIComponent} has a renderer or not. This check is currently only useful for the update attributes, as a component without renderer
     * can't be updated.
     */
    public static final int VALIDATE_RENDERER = 0x1;

    public static final int IGNORE_NO_RESULT = 0x2;

    public static final int PARENT_FALLBACK = 0x4;

    public static final int SKIP_UNRENDERED = 0x8;
}
