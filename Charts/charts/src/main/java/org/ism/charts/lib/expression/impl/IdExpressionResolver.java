/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.expression.MultiSearchExpressionResolver;
import org.ism.charts.lib.expression.SearchExpressionResolver;
import org.ism.charts.lib.util.ComponentTraversalUtils;

/**
 *
 * @author r.hendrick
 */
public class IdExpressionResolver implements SearchExpressionResolver, MultiSearchExpressionResolver {

    private static final Pattern PATTERN = Pattern.compile("@id\\(([\\w-]+)\\)");

    @Override
    public UIComponent resolveComponent(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {
        throw new FacesException("@id likely returns multiple components, therefore it's not supported in #resolveComponent... expression \"" + expression
                + "\" referenced from \"" + source.getClientId(context) + "\".");
    }

    @Override
    public void resolveComponents(FacesContext context, UIComponent source, UIComponent last, String expression, List<UIComponent> components, int options) {
        ComponentTraversalUtils.withId(
                extractId(expression),
                last,
                components);
    }

    protected String extractId(String expression) {
        try {
            Matcher matcher = PATTERN.matcher(expression);

            if (matcher.matches()) {

                return matcher.group(1);

            } else {
                throw new FacesException("Expression does not match following pattern @id(id). Expression: \"" + expression + "\"");
            }

        } catch (Exception e) {
            throw new FacesException("Expression does not match following pattern @id(id). Expression: \"" + expression + "\"", e);
        }
    }
}
