/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.expression.ClientIdSearchExpressionResolver;
import org.ism.charts.lib.expression.SearchExpressionResolver;

/**
 * {@link SearchExpressionResolver} for the jQuery selectors (PFS).
 */
public class JQuerySelectorExpressionResolver implements SearchExpressionResolver, ClientIdSearchExpressionResolver {

    @Override
    public UIComponent resolveComponent(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {
        throw new FacesException("jQuery selectors aka PFS are not supported on the server side... expression \"" + expression
                + "\" referenced from \"" + source.getClientId(context) + "\".");
    }

    @Override
    public String resolveClientIds(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {
        // just return the complete expression, the client side will take care of it
        // e.g. @(#myButton)
        return expression;
    }
}
