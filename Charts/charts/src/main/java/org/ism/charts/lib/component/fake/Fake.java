/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.fake;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIPanel;

/**
 *
 * @author r.hendrick
 */
@ResourceDependencies({
    ///////  CSS

    @ResourceDependency(library = "ism", name = "icharts/components.min.css")
    ,@ResourceDependency(library = "webjars", name = "font-awesome/4.7.0/css/font-awesome.min-jsf.css")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/css/bootstrap.min-jsf.css")
    ,@ResourceDependency(library = "ism", name = "fake/fake.css")
    ,
    ////////  JS
    
    @ResourceDependency(library = "ism", name = "icharts/core.js")
    ,@ResourceDependency(library = "ism", name = "icharts/components.js")
    ,@ResourceDependency(library = "webjars", name = "popper.js/1.12.9/dist/umd/popper.min.js")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/js/bootstrap.bundle.min.js")
    ,@ResourceDependency(library = "ism", name = "fake/fake.js")
})

@FacesComponent(value = Fake.COMPONENT_TYPE)
public class Fake extends UIPanel {

    public static final String COMPONENT_TYPE = "org.ism.component.Fake";
    public static final String COMPONENT_FAMILY = "org.ism.component";
    public static final String RENDERER_TYPE = "org.ism.renderKit.Fake";

    public enum PropertyKeys {

        ariaLabel,
        closable,
        disabled,
        faIcon,
        title,
        titleStyle,
        titleStyleClass,
        titletip;

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

    public Fake() {
        setRendererType(null);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public java.lang.String getFaIcon() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.faIcon, null);
    }

    public void setFaIcon(java.lang.String faIcon) {
        getStateHelper().put(PropertyKeys.faIcon, faIcon);
    }

    public java.lang.String getTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.title, null);
    }

    public void setTitle(java.lang.String _title) {
        getStateHelper().put(PropertyKeys.title, _title);
    }

    public java.lang.String getTitleStyle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.titleStyle, null);
    }

    public void setTitleStyle(java.lang.String _titleStyle) {
        getStateHelper().put(PropertyKeys.titleStyle, _titleStyle);
    }

    public java.lang.String getTitleStyleClass() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.titleStyleClass, null);
    }

    public void setTitleStyleClass(java.lang.String _titleStyleClass) {
        getStateHelper().put(PropertyKeys.titleStyleClass, _titleStyleClass);
    }

    public boolean isDisabled() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
    }

    public void setDisabled(boolean _disabled) {
        getStateHelper().put(PropertyKeys.disabled, _disabled);
    }

    public boolean isClosable() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.closable, false);
    }

    public void setClosable(boolean _closable) {
        getStateHelper().put(PropertyKeys.closable, _closable);
    }

    public java.lang.String getTitletip() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.titletip, null);
    }

    public void setTitletip(java.lang.String _titletip) {
        getStateHelper().put(PropertyKeys.titletip, _titletip);
    }

    public java.lang.String getAriaLabel() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.ariaLabel, null);
    }

    public void setAriaLabel(java.lang.String _ariaLabel) {
        getStateHelper().put(PropertyKeys.ariaLabel, _ariaLabel);
    }

    public void setLoaded(boolean value) {
        getStateHelper().put("loaded", value);
    }

    public boolean isLoaded() {
        Object value = getStateHelper().get("loaded");

        return (value == null) ? false : (Boolean) value;
    }
}
