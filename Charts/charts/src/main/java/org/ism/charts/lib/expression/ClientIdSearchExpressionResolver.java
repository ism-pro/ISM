/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Interface for resolvers, to resolve the component clientId by a expression.
 * This can be used to improve performance when the clientIds are somehow
 * already available for a expression.
 */
public interface ClientIdSearchExpressionResolver {

    /**
     * Resolves one or multiple clientId's for the given expression string.
     *
     * @param context The {@link FacesContext}.
     * @param source The source component. E.g. a button.
     * @param last The last resolved component in the chain. If it's not a
     * nested expression, it's the same as the source component.
     * @param expression The search expression.
     * @param options The options.
     *
     * @return The resolved clientId's or <code>null</code>.
     */
    String resolveClientIds(FacesContext context, UIComponent source, UIComponent last, String expression, int options);
}
