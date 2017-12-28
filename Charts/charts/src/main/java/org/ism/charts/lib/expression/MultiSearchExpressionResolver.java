/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author r.hendrick
 */
public interface MultiSearchExpressionResolver {

    /**
     * Resolves a list of {@link UIComponent} for the last or source
     * {@link UIComponent} and for the given expression string.
     *
     * @param context The {@link FacesContext}.
     * @param source The source component. E.g. a button.
     * @param last The last resolved component in the chain. If it's not a
     * nested expression, it's the same as the source component.
     * @param expression The search expression.
     * @param components The component list to add the resolved
     * {@link UIComponent}s.
     * @param options The options.
     */
    void resolveComponents(FacesContext context, UIComponent source, UIComponent last, String expression, List<UIComponent> components, int options);

}
