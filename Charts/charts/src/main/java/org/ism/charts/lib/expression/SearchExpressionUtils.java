/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression;

import javax.faces.component.visit.VisitContext;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.util.ComponentUtils;

/**
 *
 * @author r.hendrick
 */
public class SearchExpressionUtils {

    public static VisitContext createVisitContext(FacesContext context, int hints) {
        if (isHintSet(hints, SearchExpressionHint.SKIP_UNRENDERED)) {
            return VisitContext.createVisitContext(context, null, ComponentUtils.VISIT_HINTS_SKIP_UNRENDERED);
        }

        return VisitContext.createVisitContext(context);
    }

    public static boolean isHintSet(int hints, int hint) {
        return (hints & hint) != 0;
    }
}
