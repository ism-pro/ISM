/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.chart;

import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;

/**
 * <h1>ChartHandler</h1>
 * <p>
 * This class coverts
 * http://www.omnifaces-fans.org/2015/02/jsf-22-writing-custom-component-handler.html
 * </p>
 *
 *
 * @author r.hendrick
 */
public class ChartHandler extends ComponentHandler {

    private static final Logger logger
            = Logger.getLogger(ChartHandler.class.getName());

    public ChartHandler(ComponentConfig config) {
        super(config);
    }

    @Override
    public void onComponentPopulated(FaceletContext ctx, UIComponent c, UIComponent parent) {
        super.onComponentPopulated(ctx, c, parent); 
    }

    @Override
    public void onComponentCreated(FaceletContext ctx, UIComponent c, UIComponent parent) {
        super.onComponentCreated(ctx, c, parent); 
    }

    @Override
    public UIComponent createComponent(FaceletContext ctx) {
        return super.createComponent(ctx); 
    }

}
