/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.celleditor;

import javax.faces.application.ResourceDependencies;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

/**
 *
 * @author r.hendrick
 */
@ResourceDependencies({})
public class CellEditor extends UIComponentBase {

    public static final String COMPONENT_TYPE = "org.ism.component.CellEditor";
    public static final String COMPONENT_FAMILY = "org.ism.component";
    public static final String RENDERER_TYPE = "org.ism.renderKit.CellEditorRenderer";
    public static final String DEFAULT_RENDERER = "org.ism.renderKit.CellEditorRenderer";

    public enum PropertyKeys {
        ;

		String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
        }

        @Override
        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }

    public CellEditor() {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    @Override
    public void processDecodes(FacesContext context) {
        if (isEditRequest(context)) {
            super.processDecodes(context);
        }
    }

    @Override
    public void processValidators(FacesContext context) {
        if (isEditRequest(context)) {
            super.processValidators(context);
        }
    }

    @Override
    public void processUpdates(FacesContext context) {
        if (isEditRequest(context)) {
            super.processUpdates(context);
        }
    }

    public boolean isEditRequest(FacesContext context) {
        return context.getExternalContext().getRequestParameterMap().containsKey(this.getClientId(context));
    }

    private UIComponent parentTable = null;

    public UIComponent getParentTable(FacesContext context) {
        if (parentTable == null) {
            UIComponent parent = this.getParent();

            while (parent != null) {
//                if(parent instanceof DataTable || parent instanceof TreeTable) {
//                    parentTable = parent;
//                    break;
//                }

                parent = parent.getParent();
            }
        }

        return parentTable;
    }

}
