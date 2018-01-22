/*
 * Generated, Do Not Modify
 */
 /*
 * Copyright 2009-2013 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ism.component.crop;

import javax.faces.context.FacesContext;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import org.primefaces.util.ComponentUtils;

/**
 * <h3>chart component</h3>
 * <p>
 * Define de chart markedup which allow to renderer a component chart. <br >
 * The component chart can be define in two different ways :
 * <ul>
 * <li>One is to specify each attributes
 * <li>The other is to give the chart model attribute
 * </ul>
 * Note : When concurent attributes are define in specific attribute and in the
 * chart model, the priority goes to the specific attribute.
 * </p>
 * <p>
 * The component chart is entierly base Highchart which will process chart
 * rendering based on similar classes.
 *
 * @see http://api.highcharts.com/highcharts/
 *
 * @author r.hendrick
 */
@ResourceDependencies({
    @ResourceDependency(library = "primefaces", name = "components.css")
    ,@ResourceDependency(library = "webjars", name = "font-awesome/4.7.0/css/font-awesome.min-jsf.css")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/css/bootstrap.min-jsf.css")
    ,@ResourceDependency(library = "webjars", name = "cropper/3.1.3/dist/cropper.min.css")
    ,@ResourceDependency(library = "ism", name = "crop/cropAvatar.css", target = "head")

    ,@ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
    ,@ResourceDependency(library = "primefaces", name = "core.js")
    ,@ResourceDependency(library = "primefaces", name = "components.js")
    ,@ResourceDependency(library = "webjars", name = "popper.js/1.12.9/dist/umd/popper.min.js")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/js/bootstrap.bundle.min.js")
    ,@ResourceDependency(library = "webjars", name = "cropper/3.1.3/dist/cropper.min.js")
    ,@ResourceDependency(library = "ism", name = "crop/cropAvatar.js", target = "head")
})
@FacesComponent(value = CropAvatar.COMPONENT_TYPE)
public class CropAvatar extends UIInput implements org.primefaces.component.api.Widget {

    public static final String COMPONENT_TYPE = "org.ism.component.CropAvatar";
    public static final String COMPONENT_FAMILY = "org.ism.component";
    public static final String RENDERER_TYPE = "org.ism.component.CropAvatarRenderer";

    protected enum PropertyKeys {

        alt,
        applyLabel,
        applyTitle,
        aspectRatio,
        avatarTitle,
        backgroundColor,
        backgroundOpacity,
        boxHeight,
        boxWidth,
        chooseLabel,
        closable,
        closeTitle,
        header,
        image,
        initialCoords,
        minSize,
        maxSize,
        style,
        styleClass,
        widgetVar;

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

    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// Component setup
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public CropAvatar() {
        setRendererType(RENDERER_TYPE);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    /**
     * <p>
     * Specify that after encode begin has finish, encodeChildren have to start.
     * It indicates that this renderer is responsible for rendering any child
     * components
     * </p>
     *
     * @return default return true because there is child
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// Property
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public java.lang.String getAlt() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.alt, "Avatar");
    }

    public void setAlt(java.lang.String _alt) {
        getStateHelper().put(PropertyKeys.alt, _alt);
    }

    public java.lang.String getApplyLabel() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.applyLabel, "Appliquer");
    }

    public void setApplyLabel(java.lang.String applyLabel) {
        getStateHelper().put(PropertyKeys.applyLabel, applyLabel);
    }

    public java.lang.String getApplyTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.applyTitle, "Valider le redimensionnement");
    }

    public void setApplyTitle(java.lang.String applyTitle) {
        getStateHelper().put(PropertyKeys.applyTitle, applyTitle);
    }

    public double getAspectRatio() {
        return (java.lang.Double) getStateHelper().eval(PropertyKeys.aspectRatio, java.lang.Double.MIN_VALUE);
    }

    public void setAspectRatio(double _aspectRatio) {
        getStateHelper().put(PropertyKeys.aspectRatio, _aspectRatio);
    }

    public java.lang.String getAvatarTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.avatarTitle, "Cliquer pour modifier");
    }

    public void setAvatarTitle(java.lang.String avatarTitle) {
        getStateHelper().put(PropertyKeys.avatarTitle, avatarTitle);
    }

    public java.lang.String getBackgroundColor() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.backgroundColor, null);
    }

    public void setBackgroundColor(java.lang.String _backgroundColor) {
        getStateHelper().put(PropertyKeys.backgroundColor, _backgroundColor);
    }

    public double getBackgroundOpacity() {
        return (java.lang.Double) getStateHelper().eval(PropertyKeys.backgroundOpacity, 0.6);
    }

    public void setBackgroundOpacity(double _backgroundOpacity) {
        getStateHelper().put(PropertyKeys.backgroundOpacity, _backgroundOpacity);
    }

    public int getBoxHeight() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.boxHeight, 0);
    }

    public void setBoxHeight(int _boxHeight) {
        getStateHelper().put(PropertyKeys.boxHeight, _boxHeight);
    }

    public int getBoxWidth() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.boxWidth, 0);
    }

    public void setBoxWidth(int _boxWidth) {
        getStateHelper().put(PropertyKeys.boxWidth, _boxWidth);
    }

    public java.lang.String getChooseLabel() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.chooseLabel, "Charger");
    }

    public void setChooseLabel(java.lang.String chooseLabel) {
        getStateHelper().put(PropertyKeys.chooseLabel, chooseLabel);
    }

    public java.lang.Boolean isClosable() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.closable, true);
    }

    public void setClosable(java.lang.Boolean closable) {
        getStateHelper().put(PropertyKeys.closable, closable);
    }

    public java.lang.String getCloseTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.closeTitle, "Fermer/Quitter");
    }

    public void setCloseTitle(java.lang.String closeTitle) {
        getStateHelper().put(PropertyKeys.closeTitle, closeTitle);
    }

    public java.lang.String getHeader() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.header, "Crop Avatar");
    }

    public void setHeader(java.lang.String header) {
        getStateHelper().put(PropertyKeys.header, header);
    }

    public java.lang.String getImage() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.image, null);
    }

    public void setImage(java.lang.String _image) {
        getStateHelper().put(PropertyKeys.image, _image);
    }

    public java.lang.String getInitialCoords() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.initialCoords, null);
    }

    public void setInitialCoords(java.lang.String _initialCoords) {
        getStateHelper().put(PropertyKeys.initialCoords, _initialCoords);
    }

    public java.lang.String getMinSize() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.minSize, null);
    }

    public void setMinSize(java.lang.String _minSize) {
        getStateHelper().put(PropertyKeys.minSize, _minSize);
    }

    public java.lang.String getMaxSize() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.maxSize, null);
    }

    public void setMaxSize(java.lang.String _maxSize) {
        getStateHelper().put(PropertyKeys.maxSize, _maxSize);
    }

    public java.lang.String getStyle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.style, null);
    }

    public void setStyle(java.lang.String style) {
        getStateHelper().put(PropertyKeys.style, style);
    }

    public java.lang.String getStyleClass() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    public void setStyleClass(java.lang.String _styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }

    public java.lang.String getWidgetVar() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(java.lang.String _widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
    }

    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// 
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    @Override
    public String resolveWidgetVar() {
        return ComponentUtils.resolveWidgetVar(getFacesContext(), this);
    }
}
