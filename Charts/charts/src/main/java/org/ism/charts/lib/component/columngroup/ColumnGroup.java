/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.columngroup;

import javax.faces.application.ResourceDependencies;
import javax.faces.component.UIComponentBase;

/**
 *
 * @author r.hendrick
 */
@ResourceDependencies({})
public class ColumnGroup extends UIComponentBase {

    public static final String COMPONENT_TYPE = "org.ism.component.ColumnGroup";
    public static final String COMPONENT_FAMILY = "org.ism.component";

    public enum PropertyKeys {

        type;

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

    public ColumnGroup() {
        setRendererType(null);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public java.lang.String getType() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.type, null);
    }

    public void setType(java.lang.String _type) {
        getStateHelper().put(PropertyKeys.type, _type);
    }

}
