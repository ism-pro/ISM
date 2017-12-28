/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.expression.ClientIdSearchExpressionResolver;
import org.ism.charts.lib.expression.SearchExpressionConstants;
import org.ism.charts.lib.expression.SearchExpressionResolver;

/**
 *
 * @author r.hendrick {@link SearchExpressionResolver} for the "@none" keyword.
 */
public class NoneExpressionResolver implements SearchExpressionResolver, ClientIdSearchExpressionResolver {

    @Override
    public UIComponent resolveComponent(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {
        return null;
    }

    @Override
    public String resolveClientIds(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {
        return SearchExpressionConstants.NONE_KEYWORD;
    }
}
