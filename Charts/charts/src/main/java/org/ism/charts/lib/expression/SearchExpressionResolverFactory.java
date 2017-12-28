/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression;

import java.util.HashMap;
import javax.faces.FacesException;
import org.ism.charts.lib.expression.impl.AllExpressionResolver;
import org.ism.charts.lib.expression.impl.ChildExpressionResolver;
import org.ism.charts.lib.expression.impl.CompositeExpressionResolver;
import org.ism.charts.lib.expression.impl.FindComponentExpressionResolver;
import org.ism.charts.lib.expression.impl.FormExpressionResolver;
import org.ism.charts.lib.expression.impl.IdExpressionResolver;
import org.ism.charts.lib.expression.impl.JQuerySelectorExpressionResolver;
import org.ism.charts.lib.expression.impl.NamingContainerExpressionResolver;
import org.ism.charts.lib.expression.impl.NextExpressionResolver;
import org.ism.charts.lib.expression.impl.NoneExpressionResolver;
import org.ism.charts.lib.expression.impl.ParentExpressionResolver;
import org.ism.charts.lib.expression.impl.PreviousExpressionResolver;
import org.ism.charts.lib.expression.impl.RootExpressionResolver;
import org.ism.charts.lib.expression.impl.RowExpressionResolver;
import org.ism.charts.lib.expression.impl.ThisExpressionResolver;
import org.ism.charts.lib.expression.impl.WidgetVarExpressionResolver;

/**
 *
 * @author r.hendrick
 */
public class SearchExpressionResolverFactory {

    private static final HashMap<String, SearchExpressionResolver> RESOLVER_MAPPING = new HashMap<String, SearchExpressionResolver>();

    private static final FindComponentExpressionResolver FIND_COMPONENT_EXPRESSION_RESOLVER = new FindComponentExpressionResolver();

    static {
        RESOLVER_MAPPING.put(SearchExpressionConstants.THIS_KEYWORD, new ThisExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.COMPOSITE_KEYWORD, new CompositeExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.PARENT_KEYWORD, new ParentExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.FORM_KEYWORD, new FormExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.ALL_KEYWORD, new AllExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.NAMINGCONTAINER_KEYWORD, new NamingContainerExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.NONE_KEYWORD, new NoneExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.NEXT_KEYWORD, new NextExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.PREVIOUS_KEYWORD, new PreviousExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.CHILD_KEYWORD, new ChildExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.WIDGETVAR_KEYWORD, new WidgetVarExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.KEYWORD_PREFIX, new JQuerySelectorExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.ROW_KEYWORD, new RowExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.ID_KEYWORD, new IdExpressionResolver());
        RESOLVER_MAPPING.put(SearchExpressionConstants.ROOT_KEYWORD, new RootExpressionResolver());
    }

    /**
     * Finds a {@link SearchExpressionResolver} for the given expression.
     *
     * @param expression The search expression.
     * @return The {@link SearchExpressionResolver}.
     */
    public static SearchExpressionResolver findResolver(final String expression) {
        SearchExpressionResolver resolver = null;

        if (expression.startsWith(SearchExpressionConstants.KEYWORD_PREFIX)) {
            // check if it's an expression with parameter
            int parenthesisPosition = expression.indexOf('(');
            if (parenthesisPosition > 0) {
                String expressionWithoutParam = expression.substring(0, parenthesisPosition);
                resolver = RESOLVER_MAPPING.get(expressionWithoutParam);
            } else {
                resolver = RESOLVER_MAPPING.get(expression);
            }
        } else {
            // if it's not a keyword, just delegate it to JSF
            resolver = FIND_COMPONENT_EXPRESSION_RESOLVER;
        }

        if (resolver == null) {
            throw new FacesException("No SearchExpressionResolver available for expression \"" + expression + "\"");
        }

        return resolver;
    }

    public static void registerResolver(final String keyword, final SearchExpressionResolver resolver) {
        RESOLVER_MAPPING.put(keyword, resolver);
    }

    public static SearchExpressionResolver removeResolver(final String keyword) {
        return RESOLVER_MAPPING.remove(keyword);
    }

    /**
     * Prevent instantiation.
     */
    private SearchExpressionResolverFactory() {

    }
}
