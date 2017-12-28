/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import javax.faces.component.NamingContainer;
import org.ism.charts.lib.expression.SearchExpressionResolver;

/**
 *
 * @author r.hendrick
 * {@link SearchExpressionResolver} for the "@namingcontainer" keyword.
 */
public class NamingContainerExpressionResolver extends AbstractClosestExpressionResolver implements SearchExpressionResolver {

    @Override
    public Class<?> getType() {
        return NamingContainer.class;
    }
}
