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
package org.ism.charts.lib.component.chart;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.application.ResourceDependencies;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIPanel;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.FacesEvent;
import org.ism.charts.lib.model.ChartModel;

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
@ResourceDependencies({ ///////  CSS
    // JQuery
    // -
    // Chart

    //@ResourceDependency(library = "ism", name = "charts/charts.min.css"),

    ////////  JS
    // JQuery Dynamically load
    //@ResourceDependency(library = "vendor", name = "jquery/3.1.1/js/jquery.min.js"),
    // Highcharts.js Dynamically load due to JQuery
    //@ResourceDependency(library = "vendor", name = "highcharts/highcharts.js"),
    //@ResourceDependency(library = "vendor", name = "highcharts/modules/exporting.js")
    // Charts
    //@ResourceDependency(library = "", name = "ism/charts/charts.js")
    
    @ResourceDependency(library = "ism", name = "icharts/components.css"),
    @ResourceDependency(library = "ism", name = "charts/charts.css"),
//    @ResourceDependency(library = "ism", name = "jquery/jquery.js"),
//    @ResourceDependency(library = "ism", name = "jquery/jquery-plugins.js"),
    @ResourceDependency(library = "ism", name = "icharts/core.js"),
    @ResourceDependency(library = "ism", name = "icharts/components.js"),
    @ResourceDependency(library = "ism", name = "charts/charts.js")
})
@FacesComponent(value = Chart.COMPONENT_TYPE)
public class Chart extends UIPanel implements javax.faces.component.behavior.ClientBehaviorHolder {

    public static final String COMPONENT_TYPE = "org.ism.component.Chart";
    public static final String COMPONENT_FAMILY = "org.ism.component";
    public static final String RENDERER_TYPE = "org.ism.renderKit.Chart";

    protected enum PropertyKeys {
        exporting, debug, model, enabledJQuery, responsive, style, styleClass, type, widgetVar, zoomType;

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

    public Chart() {
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

    public java.lang.String getWidgetVar() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    /**
     * Deprecated : not working
     *
     * @param _widgetVar is the primefaces
     */
    public void setWidgetVar(java.lang.String _widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
    }

    /**
     * <h3>Description</h3>
     * Type allow to specify kind of chart to be render. Type is one of Line,
     * Pie,
     *
     * <h3>Note :</h3>
     * Defining this properety have priority on model type chart.
     *
     * @return type
     */
    public java.lang.String getType() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.type, null);
    }

    /**
     * <h3>Description</h3>
     * Type allow to specify kind of chart to be render. Type is one of Line,
     * Pie,
     *
     * <h3>Note :</h3>
     * Defining this properety have priority on model type chart.
     *
     * @param _type is one of line, pie,
     */
    public void setType(java.lang.String _type) {
        getStateHelper().put(PropertyKeys.type, _type);
    }

    public ChartModel getModel() {
        return (ChartModel) getStateHelper().eval(PropertyKeys.model, null);
    }

    public void setModel(ChartModel model) {
        getStateHelper().put(PropertyKeys.model, model);
    }

    public java.lang.String getStyle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.style, null);
    }

    public void setStyle(java.lang.String _style) {
        getStateHelper().put(PropertyKeys.style, _style);
    }

    public java.lang.String getStyleClass() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    public void setStyleClass(java.lang.String _styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }

    public boolean isResponsive() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.responsive, false);
    }

    public void setResponsive(boolean _responsive) {
        getStateHelper().put(PropertyKeys.responsive, _responsive);
    }

    public boolean isExporting() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.exporting, false);
    }

    public void setExporting(boolean _exporting) {
        getStateHelper().put(PropertyKeys.exporting, _exporting);
    }

    /**
     * When enable this property allow to display the render mecanisme for the
     * chart to render.
     *
     * @return true if debug macanisme is activated
     */
    public boolean isDebug() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.debug, false);
    }

    /**
     * When enable this property allow to display the render mecanisme for the
     * chart to render.
     *
     * @param _debug true for renderer
     */
    public void setDebug(boolean _debug) {
        getStateHelper().put(PropertyKeys.debug, _debug);
    }

    /**
     * When enable this property allow to display the render mecanisme for the
     * chart to render.
     *
     * @return true if debug macanisme is activated
     */
    public boolean getEnabledJQuery() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.enabledJQuery, false);
    }

    /**
     * When enable this property allow to add JQuery librarie this in case of 
     * no conflit if already loaded. default value is true
     *
     * @param enabledJQuery true for renderer
     */
    public void setEnabledJQuery(boolean enabledJQuery) {
        getStateHelper().put(PropertyKeys.enabledJQuery, enabledJQuery);
    }
    
    
    public java.lang.String getZoomType() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.zoomType, null);
    }

    public void setZoomType(java.lang.String _zoomType) {
        getStateHelper().put(PropertyKeys.zoomType, _zoomType);
    }

    /**
     * Returns the saved state for this component.
     *
     * @param context the Faces context
     * @return Array object of saved value
     */
    @Override
    public Object saveState(FacesContext context) {
        /*
        Object values[] = new Object[5];
        values[0] = super.saveState(context);
        values[1] = _styleClass;
        values[2] = _itemStyleClass;
        values[3] = _itemHeaderStyleClass;
        values[4] = _itemContentStyleClass;
         */
        return getStateHelper().saveState(context);
    }

    /**
     * Restores the state of this component.
     *
     * @param context the Faces context
     * @param state the saved state
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        /*
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        _styleClass = (String) values[1];
        _itemStyleClass = (String) values[2];
        _itemHeaderStyleClass = (String) values[3];
        _itemContentStyleClass = (String) values[4];
         */
        getStateHelper().restoreState(context, state);
    }

    private final static String DEFAULT_EVENT = "itemSelect";

    private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList(DEFAULT_EVENT));

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
        if (event instanceof AjaxBehaviorEvent) {
            BehaviorEvent behaviorEvent = (AjaxBehaviorEvent) event;
            Map<String, String> map = getFacesContext().getExternalContext().getRequestParameterMap();
            int itemIndex = Integer.parseInt(map.get("itemIndex"));
            int seriesIndex = Integer.parseInt(map.get("seriesIndex"));

            //ItemSelectEvent itemSelectEvent = new ItemSelectEvent(this, behaviorEvent.getBehavior(), itemIndex, seriesIndex);
            //super.queueEvent(itemSelectEvent);
        }
    }

}
