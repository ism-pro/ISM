/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.expression.SearchExpressionResolver;

/**
 * {@link SearchExpressionResolver} for the "@previous" keyword.
 */
public class PreviousExpressionResolver implements SearchExpressionResolver {

    @Override
    public UIComponent resolveComponent(FacesContext context, UIComponent source, UIComponent last, String expression, int options) {
        UIComponent parent = last.getParent();

        if (parent.getChildCount() > 1) {
            List<UIComponent> children = parent.getChildren();
            int index = children.indexOf(last);

            if (index > 0) {
                int nextIndex = -1;
                do {
                    index--;

                    String className = children.get(index).getClass().getName();
                    if (!className.contains("UIInstructions") && !className.contains("UILeaf")) {
                        nextIndex = index;
                    }
                } while (nextIndex == -1 && index > 0);

                if (nextIndex != -1) {
                    return children.get(nextIndex);
                }
            }
        }

        return null;
    }

}
