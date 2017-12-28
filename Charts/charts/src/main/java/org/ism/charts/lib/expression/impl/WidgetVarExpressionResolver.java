/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.expression.ClientIdSearchExpressionResolver;
import org.ism.charts.lib.expression.SearchExpressionResolver;
import org.ism.charts.lib.expression.SearchExpressionUtils;

/**
 * {@link SearchExpressionResolver} for the "@widgetVar" keyword.
 */
public class WidgetVarExpressionResolver implements SearchExpressionResolver, ClientIdSearchExpressionResolver {

    private static final Pattern PATTERN = Pattern.compile("@widgetVar\\((\\w+)\\)");

    @Override
    public UIComponent resolveComponent(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {

        try {
            Matcher matcher = PATTERN.matcher(expression);

            if (matcher.matches()) {

                WidgetVarVisitCallback visitCallback = new WidgetVarVisitCallback(matcher.group(1));
                context.getViewRoot().visitTree(
                        SearchExpressionUtils.createVisitContext(context, options),
                        visitCallback);

                return visitCallback.getComponent();

            } else {
                throw new FacesException("Expression does not match following pattern @widgetVar(var). Expression: \"" + expression + "\"");
            }

        } catch (Exception e) {
            throw new FacesException("Expression does not match following pattern @widgetVar(var). Expression: \"" + expression + "\"", e);
        }
    }

    @Override
    public String resolveClientIds(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {
        // just return the complete expression, the client side will take care of it
        // e.g. @widgetVar(myWidget)
        return expression;
    }

}
