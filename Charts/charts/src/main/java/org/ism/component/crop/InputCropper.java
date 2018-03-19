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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.FacesEvent;
import org.ism.event.CropErrorEvent;
import org.ism.event.CroppedDataEvent;
import org.ism.event.CroppedEvent;
import org.ism.model.cropper.CropError;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.Constants;

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
    ,@ResourceDependency(library = "vendor", name = "bootstrap-touchspin/jquery.bootstrap-touchspin.min.css")
    ,@ResourceDependency(library = "vendor", name = "bootstrap-toggle/css/bootstrap-toggle.min.css")
    ,@ResourceDependency(library = "ism", name = "crop/inputCropper.css", target = "head")

    ,@ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
    ,@ResourceDependency(library = "primefaces", name = "core.js")
    ,@ResourceDependency(library = "primefaces", name = "components.js")
    ,@ResourceDependency(library = "webjars", name = "popper.js/1.12.9/dist/umd/popper.min.js")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/js/bootstrap.bundle.min.js")
    ,@ResourceDependency(library = "webjars", name = "cropper/3.1.3/dist/cropper.min.js")
    ,@ResourceDependency(library = "vendor", name = "bootstrap-touchspin/jquery.bootstrap-touchspin.min.js")
    ,@ResourceDependency(library = "vendor", name = "bootstrap-toggle/js/bootstrap-toggle.min.js")
    ,@ResourceDependency(library = "ism", name = "crop/inputCropper.js", target = "head")
})
@FacesComponent(value = InputCropper.COMPONENT_TYPE)
public class InputCropper extends UIInput implements org.primefaces.component.api.Widget, javax.faces.component.behavior.ClientBehaviorHolder, org.primefaces.component.api.PrimeClientBehaviorHolder {

    public static final String COMPONENT_TYPE = "org.ism.component.InputCropper";
    public static final String COMPONENT_FAMILY = "org.ism.component";
    public static final String RENDERER_TYPE = "org.ism.component.InputCropperRenderer";

    protected enum PropertyKeys {

        alt,
        applyLabel,
        applyTitle,
        aspectRatio,
        backgroundColor,
        backgroundOpacity,
        boxHeight,
        boxWidth,
        chooseLabel,
        closable,
        closeTitle,
        cropError,
        dragMode,
        For,
        header,
        image,
        imageCropped,
        initialCoords,
        inputTitle,
        loadingGif,
        minSize,
        maxSize,
        rotation,
        style,
        styleClass,
        uploadUrl,
        widgetVar,
        zoom,
        zoomX,
        zoomY,;

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
    public InputCropper() {
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
        return (java.lang.String) getStateHelper().eval(PropertyKeys.alt, "Charger une image");
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

    public String getAspectRatio() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.aspectRatio, "free");
    }

    public void setAspectRatio(String _aspectRatio) {
        getStateHelper().put(PropertyKeys.aspectRatio, _aspectRatio);
    }

    public java.lang.String getAvatarTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.inputTitle, "Cliquer pour modifier");
    }

    public void setAvatarTitle(java.lang.String inputTitle) {
        getStateHelper().put(PropertyKeys.inputTitle, inputTitle);
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

    public CropError getCropError() {
        return (CropError) getStateHelper().eval(PropertyKeys.cropError, null);
    }

    public void setCropError(CropError cropError) {
        getStateHelper().put(PropertyKeys.cropError, cropError);
    }

    public String getDragMode() {
        return (String) getStateHelper().eval(PropertyKeys.dragMode, "crop");
    }

    public void setDragMode(String dragMode) {
        getStateHelper().put(PropertyKeys.dragMode, dragMode);
    }

    public java.lang.String getFor() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.For, null);
    }

    public void setFor(java.lang.String For) {
        getStateHelper().put(PropertyKeys.For, For);
    }

    public java.lang.String getHeader() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.header, "Title");
    }

    public void setHeader(java.lang.String header) {
        getStateHelper().put(PropertyKeys.header, header);
    }

    public org.ism.model.cropper.CroppedImage getImageCropped() {
        return (org.ism.model.cropper.CroppedImage) getStateHelper().eval(PropertyKeys.imageCropped, null);
    }

    public void setImageCropped(org.ism.model.cropper.CroppedImage imageCropped) {
        getStateHelper().put(PropertyKeys.imageCropped, imageCropped);
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

    public java.lang.String getLoadingGif() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.loadingGif, "/ism/crop/loading.gif");
    }

    public void setLoadingGif(java.lang.String loadingGif) {
        getStateHelper().put(PropertyKeys.loadingGif, loadingGif);
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

    public Double getRotation() {
        return (Double) getStateHelper().eval(PropertyKeys.rotation, 180d);
    }

    public void setRotation(Double rotation) {
        getStateHelper().put(PropertyKeys.rotation, rotation);
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
    
    public java.lang.String getUploadUrl() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.uploadUrl, "/");
    }

    public void setUploadUrl(java.lang.String uploadUrl) {
        getStateHelper().put(PropertyKeys.uploadUrl, uploadUrl);
    }

    public java.lang.String getWidgetVar() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(java.lang.String _widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
    }

    public Double getZoom() {
        return (Double) getStateHelper().eval(PropertyKeys.zoom, 1d);
    }

    public void setZoom(Double zoom) {
        getStateHelper().put(PropertyKeys.rotation, zoom);
    }

    public Double getZoomX() {
        return (Double) getStateHelper().eval(PropertyKeys.zoomX, 1d);
    }

    public void setZoomX(Double zoomX) {
        getStateHelper().put(PropertyKeys.zoomX, zoomX);
    }

    public Double getZoomY() {
        return (Double) getStateHelper().eval(PropertyKeys.zoomY, 1d);
    }

    public void setZoomY(Double zoomY) {
        getStateHelper().put(PropertyKeys.zoomY, zoomY);
    }

    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// 
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    private static final Map<String, Class<? extends BehaviorEvent>> BEHAVIOR_EVENT_MAPPING = Collections.unmodifiableMap(new HashMap<String, Class<? extends BehaviorEvent>>() {
        {
            put("cropped", CroppedEvent.class);
            put("cropError", CropErrorEvent.class);
            put("croppedData", CroppedDataEvent.class);
            put("cropStart", SelectEvent.class);
            put("cropEnd", UnselectEvent.class);
            put("crop", CloseEvent.class);
        }
    });

    private static final Collection<String> EVENT_NAMES = BEHAVIOR_EVENT_MAPPING.keySet();

    @Override
    public Map<String, Class<? extends BehaviorEvent>> getBehaviorEventMapping() {
        return BEHAVIOR_EVENT_MAPPING;
    }

    private final static String DEFAULT_EVENT = "cropped, cropError, croppedData, cropStart, cropEnd, crop";

    @Override
    public Collection<String> getEventNames() {
        return EVENT_NAMES;
    }

    @Override
    public String getDefaultEventName() {
        return DEFAULT_EVENT;
    }

    @Override
    public void queueEvent(FacesEvent event) {
        FacesContext context = getFacesContext();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String eventName = params.get(Constants.RequestParams.PARTIAL_BEHAVIOR_EVENT_PARAM);
        String clientId = this.getClientId(context);

        if (isSelfRequest(context)) {
            AjaxBehaviorEvent behaviorEvent = (AjaxBehaviorEvent) event;

            if (eventName.equals("cropped")) {
                CroppedEvent evt = new CroppedEvent(this, behaviorEvent.getBehavior(), getImageCropped(), null);
                super.queueEvent(evt);
            } else if (eventName.equals("cropError")) {
                CropErrorEvent evt = new CropErrorEvent(this, behaviorEvent.getBehavior(), getCropError(), null);
                super.queueEvent(evt);
            } else if (eventName.equals("croppedData")) {
                CroppedDataEvent evt = new CroppedDataEvent(this, behaviorEvent.getBehavior(), null, null);
                super.queueEvent(evt);
            }
        } else {
            super.queueEvent(event);
        }
    }

    @Override
    public void processDecodes(FacesContext context) {
        if (isSelfRequest(context)) {
            this.decode(context);
        } else {
            super.processDecodes(context);
        }
    }

    @Override
    public void processValidators(FacesContext context) {
        if (!isSelfRequest(context)) {
            super.processValidators(context);
        }
    }

    private boolean isSelfRequest(FacesContext context) {
        return this.getClientId(context).equals(context.getExternalContext().getRequestParameterMap().get(Constants.RequestParams.PARTIAL_SOURCE_PARAM));
    }

    @Override
    public String resolveWidgetVar() {
        return ComponentUtils.resolveWidgetVar(getFacesContext(), this);
    }
}
