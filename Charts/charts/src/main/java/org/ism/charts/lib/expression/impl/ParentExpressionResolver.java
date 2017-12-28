/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.expression.SearchExpressionResolver;

/**
 * {@link SearchExpressionResolver} for the "@parent" keyword.
 */
public class ParentExpressionResolver implements SearchExpressionResolver {

    @Override
    public UIComponent resolveComponent(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {
        return last.getParent();
    }
}
