/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.behavior.ajax;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.MethodNotFoundException;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

/**
 *
 * @author r.hendrick
 */
public class AjaxBehaviorListenerImpl implements AjaxBehaviorListener, Serializable {

    private static final Logger LOG = Logger.getLogger(AjaxBehaviorListenerImpl.class.getName());

    private MethodExpression listener;
    private MethodExpression listenerWithArg;
    private MethodExpression listenerWithCustomArg;

    // required by serialization
    public AjaxBehaviorListenerImpl() {
    }

    public AjaxBehaviorListenerImpl(MethodExpression listener, MethodExpression listenerWithArg) {
        this(listener, listenerWithArg, null);
    }

    public AjaxBehaviorListenerImpl(MethodExpression listener, MethodExpression listenerWithArg, MethodExpression listenerWithCustomArg) {
        this.listener = listener;
        this.listenerWithArg = listenerWithArg;
        this.listenerWithCustomArg = listenerWithCustomArg;
    }

    @Override
    public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
        FacesContext context = FacesContext.getCurrentInstance();
        final ELContext elContext = context.getELContext();

        if (LOG.isLoggable(Level.FINE)) {
            LOG.log(Level.FINE, "Try to invoke listener: {0}", listener.getExpressionString());
        }

        try {
            listener.invoke(elContext, new Object[]{});
        } catch (MethodNotFoundException | IllegalArgumentException | ArrayIndexOutOfBoundsException mnfe) {
            processArgListener(context, elContext, event);
        }
    }

    private void processArgListener(FacesContext context, ELContext elContext, AjaxBehaviorEvent event) throws AbortProcessingException {
        if (LOG.isLoggable(Level.FINE)) {
            LOG.log(Level.FINE, "Try to invoke listenerWithArg: {0}", listenerWithArg.getExpressionString());
        }

        try {
            listenerWithArg.invoke(elContext, new Object[]{event});
        } catch (MethodNotFoundException | IllegalArgumentException mnfe) {
            processCustomArgListener(context, elContext, event);
        }
    }

    private void processCustomArgListener(FacesContext context, ELContext elContext, AjaxBehaviorEvent event) throws AbortProcessingException {

        if (listenerWithCustomArg == null) {

            MethodExpression argListener = context.getApplication().getExpressionFactory().
                    createMethodExpression(elContext, listener.getExpressionString(), Void.class, new Class[]{event.getClass()});

            if (LOG.isLoggable(Level.FINE)) {
                LOG.log(Level.FINE, "Try to invoke customListener: {0}", argListener.getExpressionString());
            }

            argListener.invoke(elContext, new Object[]{event});
        } else {
            if (LOG.isLoggable(Level.FINE)) {
                LOG.log(Level.FINE, "Try to invoke customListener: {0}", listenerWithCustomArg.getExpressionString());
            }

            listenerWithCustomArg.invoke(elContext, new Object[]{event});
        }
    }
}
