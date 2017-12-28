/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import javax.faces.component.UIForm;
import org.ism.charts.lib.expression.SearchExpressionResolver;

/**
 * {@link SearchExpressionResolver} for the "@form" keyword.
 */
public class FormExpressionResolver extends AbstractClosestExpressionResolver implements SearchExpressionResolver {

    @Override
    public Class<?> getType() {
        return UIForm.class;
    }
}

