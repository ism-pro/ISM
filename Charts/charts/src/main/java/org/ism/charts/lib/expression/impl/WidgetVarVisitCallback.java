/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression.impl;

import javax.faces.component.UIComponent;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import org.ism.charts.lib.component.api.Widget;

/**
 *
 * @author r.hendrick
 */
public class WidgetVarVisitCallback implements VisitCallback {

    private final String widgetVar;

    private UIComponent component;

    public WidgetVarVisitCallback(String widgetVar) {
        this.widgetVar = widgetVar;

        this.component = null;
    }

    @Override
    public VisitResult visit(VisitContext context, UIComponent target) {

        if (target instanceof Widget) {
            if (widgetVar.equalsIgnoreCase(((Widget) target).resolveWidgetVar())) {
                component = target;
                return VisitResult.COMPLETE;
            }
        }

        return VisitResult.ACCEPT;
    }

    public UIComponent getComponent() {
        return component;
    }

}
