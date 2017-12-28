/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.behavior.ajax;

import java.util.Collection;
import javax.faces.component.ActionSource;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.faces.render.ClientBehaviorRenderer;
import javax.faces.render.FacesBehaviorRenderer;
import org.ism.charts.lib.component.api.ClientBehaviorRenderingMode;
import org.ism.charts.lib.expression.SearchExpressionFacade;
import org.ism.charts.lib.util.AjaxRequestBuilder;

/**
 *
 * @author r.hendrick
 */
@FacesBehaviorRenderer(rendererType = AjaxBehavior.RENDERER_TYPE)
public class AjaxBehaviorRenderer extends ClientBehaviorRenderer {

    @Override
    public void decode(FacesContext context, UIComponent component, ClientBehavior behavior) {
        AjaxBehavior ajaxBehavior = (AjaxBehavior) behavior;

        if (!ajaxBehavior.isDisabled()) {
            AjaxBehaviorEvent event = new AjaxBehaviorEvent(component, behavior);

            PhaseId phaseId = isImmediate(component, ajaxBehavior) ? PhaseId.APPLY_REQUEST_VALUES : PhaseId.INVOKE_APPLICATION;

            event.setPhaseId(phaseId);

            component.queueEvent(event);
        }
    }

    @Override
    public String getScript(ClientBehaviorContext behaviorContext, ClientBehavior behavior) {
        AjaxBehavior ajaxBehavior = (AjaxBehavior) behavior;
        if (ajaxBehavior.isDisabled()) {
            return null;
        }

        UIComponent component = behaviorContext.getComponent();

        ClientBehaviorRenderingMode renderingMode = ClientBehaviorRenderingMode.OBSTRUSIVE;

        Collection<ClientBehaviorContext.Parameter> behaviorParameters = behaviorContext.getParameters();
        if (behaviorParameters != null && !behaviorParameters.isEmpty()) {
            for (ClientBehaviorContext.Parameter behaviorParameter : behaviorParameters) {
                if (behaviorParameter.getValue() != null && behaviorParameter.getValue() instanceof ClientBehaviorRenderingMode) {
                    renderingMode = (ClientBehaviorRenderingMode) behaviorParameter.getValue();
                    break;
                }
            }
        }

        String source = behaviorContext.getSourceId();
        String process = ajaxBehavior.getProcess();
        if (process == null) {
            process = "@this";
        }

        //AjaxRequestBuilder builder = RequestContext.getCurrentInstance().getAjaxRequestBuilder();
        AjaxRequestBuilder builder = new AjaxRequestBuilder(FacesContext.getCurrentInstance());
        
        String request = builder.init()
                .source(source)
                .event(behaviorContext.getEventName())
                .form(SearchExpressionFacade.resolveClientId(behaviorContext.getFacesContext(), component, ajaxBehavior.getForm()))
                .process(component, process)
                .update(component, ajaxBehavior.getUpdate())
                .async(ajaxBehavior.isAsync())
                .global(ajaxBehavior.isGlobal())
                .delay(ajaxBehavior.getDelay())
                .timeout(ajaxBehavior.getTimeout())
                .partialSubmit(ajaxBehavior.isPartialSubmit(), ajaxBehavior.isPartialSubmitSet(), ajaxBehavior.getPartialSubmitFilter())
                .resetValues(ajaxBehavior.isResetValues(), ajaxBehavior.isResetValuesSet())
                .ignoreAutoUpdate(ajaxBehavior.isIgnoreAutoUpdate())
                .skipChildren(ajaxBehavior.isSkipChildren())
                .onstart(ajaxBehavior.getOnstart())
                .onerror(ajaxBehavior.getOnerror())
                .onsuccess(ajaxBehavior.getOnsuccess())
                .oncomplete(ajaxBehavior.getOncomplete())
                .params(component)
                .buildBehavior(renderingMode);

        return request;
    }

    private boolean isImmediate(UIComponent component, AjaxBehavior ajaxBehavior) {
        boolean immediate = false;

        if (ajaxBehavior.isImmediateSet()) {
            immediate = ajaxBehavior.isImmediate();
        } else if (component instanceof EditableValueHolder) {
            immediate = ((EditableValueHolder) component).isImmediate();
        } else if (component instanceof ActionSource) {
            immediate = ((ActionSource) component).isImmediate();
        }

        return immediate;
    }
}
