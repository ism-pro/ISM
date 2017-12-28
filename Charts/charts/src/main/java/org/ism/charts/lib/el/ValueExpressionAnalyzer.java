/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.el;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.el.ValueReference;
import javax.faces.el.CompositeComponentExpressionHolder;
import org.ism.charts.lib.context.RequestContext;

/**
 *
 * @author r.hendrick
 */
public class ValueExpressionAnalyzer {

    public static ValueReference getReference(ELContext elContext, ValueExpression expression) {

        if (expression == null) {
            return null;
        }

        ValueReference reference = toValueReference(expression, elContext);

        // check for a CC expression
        if (reference != null && isCompositeComponentReference(reference)) {
            ValueExpression unwrapped = unwrapCompositeComponentReference(reference);

            // check for nested CC expressions
            if (unwrapped != null) {
                ValueReference unwrappedRef = toValueReference(unwrapped, elContext);
                if (isCompositeComponentReference(unwrappedRef)) {
                    return getReference(elContext, unwrapped);
                } else {
                    return unwrappedRef;
                }
            }
        }

        return reference;
    }

    public static ValueExpression getExpression(ELContext elContext, ValueExpression expression) {

        if (expression == null) {
            return null;
        }

        // Unwrapping is required e.g. for p:graphicImage to support nested expressions in composites
        // The unwrapping requires EL 2.2
        if (RequestContext.getCurrentInstance().getApplicationContext().getConfig().isAtLeastEL22()) {
            ValueReference reference = toValueReference(expression, elContext);

            // check for a CC expression
            if (reference != null && isCompositeComponentReference(reference)) {
                ValueExpression unwrapped = unwrapCompositeComponentReference(reference);

                if (unwrapped != null) {
                    // check for nested CC expressions
                    if (isCompositeComponentReference(toValueReference(unwrapped, elContext))) {
                        return getExpression(elContext, unwrapped);
                    } else {
                        return unwrapped;
                    }
                }
            }
        }

        return expression;
    }

    public static boolean isCompositeComponentReference(ValueReference vr) {
        return vr != null && vr.getBase() != null && vr.getBase() instanceof CompositeComponentExpressionHolder;
    }

    public static ValueExpression unwrapCompositeComponentReference(ValueReference vr) {
        return ((CompositeComponentExpressionHolder) vr.getBase()).getExpression((String) vr.getProperty());
    }

    public static ValueReference intercept(ELContext elContext, ValueExpression expression) {

        if (expression == null) {
            return null;
        }

        InterceptingResolver resolver = new InterceptingResolver(elContext.getELResolver());
        ELContext interceptingContext = new InterceptingContext(elContext, resolver);

        // #getType throws a PropertyNotFoundException when a sub-expression is null
        expression.getType(interceptingContext);

        // intercept EL calls
        expression.getValue(interceptingContext);

        return resolver.getValueReference();
    }

    public static ValueReference toValueReference(ValueExpression ve, ELContext elContext) {
        ValueReference reference = ve.getValueReference(elContext);

        // e.g. TagValueExpression returns null
        if (reference == null) {
            reference = intercept(elContext, ve);
        }

        return reference;
    }
}
