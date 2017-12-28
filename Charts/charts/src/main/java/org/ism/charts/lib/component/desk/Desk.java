package org.ism.charts.lib.component.desk;

import org.ism.charts.lib.component.desk.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.el.MethodExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.FacesEvent;
import org.ism.charts.lib.event.CloseEvent;
import org.ism.charts.lib.event.SelectEvent;
import org.ism.charts.lib.event.SerieClickEvent;
import org.ism.charts.lib.event.ToggleEvent;
import org.ism.charts.lib.event.UnselectEvent;
import org.ism.charts.lib.model.Visibility;
import org.ism.charts.lib.model.json.Options;
import org.ism.charts.lib.util.Constants;

/**
 * <p>
 * Main entry point of ribbon component. InputDate is based on UIComponentBase
 * Based on
 * <a href="https://eonasdan.github.io/bootstrap-datetimepicker/">
 * bootstrap-datetimepicker </a>
 * </p>
 *
 * @author r.hendrick
 */
@ResourceDependencies({
    ///////  CSS
    @ResourceDependency(library = "webjars", name = "font-awesome/4.7.0/css/font-awesome.min-jsf.css")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/css/bootstrap.min-jsf.css")
    ,@ResourceDependency(library = "ism", name = "desk/desk.min.css")
    ,
    ////////  JS
    @ResourceDependency(library = "webjars", name = "popper.js/1.12.9/dist/umd/popper.min.js")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/js/bootstrap.bundle.min.js")
    ,@ResourceDependency(library = "ism", name = "desk/desk.min.js")
})

@FacesComponent(value = Desk.COMPONENT_TYPE)
public class Desk extends UIPanel implements org.ism.charts.lib.component.api.Widget, javax.faces.component.behavior.ClientBehaviorHolder, org.ism.charts.lib.component.api.IChartsClientBehaviorHolder {

    public static final String COMPONENT_TYPE = "org.ism.component.Desk";
    public static final String COMPONENT_FAMILY = "org.ism.component";
    public static final String RENDERER_TYPE = "org.ism.renderKit.Desk";

    // Encode-Decode
    public static final String PARAM_MENU = "_menu";
    public static final String PARAM_COLLAPSE = "_collapsed";
    public static final String PARAM_VISIBLE = "_visible";

    // CSS
    public static final String DESK = "c-desk w3-collapse w3-animate-left w3-white";
    public static final String DESK_SIDEMENU = "c-sidemenu";
    public static final String DESK_ACTIVETAB = "c-active";

    public static final String DECK_ITEM = "desk-item";
    public static final String DECK_ITEM_HEADER = "desk-item-header";
    public static final String DECK_ITEM_CONTENT = "desk-item-content";

    public static final String ID_HEADER = "_header";
    public static final String ID_CONTENT = "_content";

    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// Properties
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    protected enum PropertyKeys {
        closable,
        menu,
        style,
        styleClass,
        itemStyleClass,
        itemHeaderStyleClass,
        itemContentStyleClass,
        toggleable,
        collapsed,
        visible,
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
    public Desk() {
        setRendererType(RENDERER_TYPE); // renderer itself
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
    /// Getter / Setters
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public java.lang.Boolean isClosable() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.closable, false);
    }

    public void setClosable(java.lang.Boolean closable) {
        getStateHelper().put(PropertyKeys.closable, closable);
    }

    public java.lang.Boolean isCollapsed() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.collapsed, false);
    }

    public void setCollapsed(java.lang.Boolean collapsed) {
        getStateHelper().put(PropertyKeys.collapsed, collapsed);
    }

    public java.lang.Integer getMenu() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.menu, 0);
    }

    public void setMenu(java.lang.Integer menu) {
        getStateHelper().put(PropertyKeys.menu, menu);
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

    public void setStyleClass(java.lang.String styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, styleClass);
    }

    public java.lang.String getItemStyleClass() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.itemStyleClass, null);
    }

    public void setItemStyleClass(java.lang.String itemStyleClass) {
        getStateHelper().put(PropertyKeys.itemStyleClass, itemStyleClass);
    }

    public java.lang.String getItemHeaderStyleClass() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.itemHeaderStyleClass, null);
    }

    public void setItemHeaderStyleClass(java.lang.String itemHeaderStyleClass) {
        getStateHelper().put(PropertyKeys.itemHeaderStyleClass, itemHeaderStyleClass);
    }

    public java.lang.String getItemContentStyleClass() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.itemContentStyleClass, null);
    }

    public void setItemContentStyleClass(java.lang.String itemContentStyleClass) {
        getStateHelper().put(PropertyKeys.itemContentStyleClass, itemContentStyleClass);
    }

    public java.lang.Boolean isToggleable() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.toggleable, false);
    }

    public void setToggleable(java.lang.Boolean toggleable) {
        getStateHelper().put(PropertyKeys.toggleable, toggleable);
    }

    public boolean isVisible() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.visible, true);
    }

    public void setVisible(boolean _visible) {
        getStateHelper().put(PropertyKeys.visible, _visible);
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
    /// State Helper
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    @Override
    public Object saveState(FacesContext context) {
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
        getStateHelper().restoreState(context, state);
    }

    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// EVENTS
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    private static final Map<String, Class<? extends BehaviorEvent>> BEHAVIOR_EVENT_MAPPING = Collections.unmodifiableMap(new HashMap<String, Class<? extends BehaviorEvent>>() {
        {
            put("toggle", ToggleEvent.class);
            put("close", CloseEvent.class);
        }
    });

    private final static String DEFAULT_EVENT = "toggle, close";
    private static final Collection<String> EVENT_NAMES = BEHAVIOR_EVENT_MAPPING.keySet();

    @Override
    public Map<String, Class<? extends BehaviorEvent>> getBehaviorEventMapping() {
        return BEHAVIOR_EVENT_MAPPING;
    }

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

            if (eventName.equals("toggle")) {
                boolean collapsed = Boolean.valueOf(params.get(clientId + "_collapsed"));
                Visibility visibility = collapsed ? Visibility.HIDDEN : Visibility.VISIBLE;

                super.queueEvent(new ToggleEvent(this, behaviorEvent.getBehavior(), visibility));

            } else if (eventName.equals("close")) {
                super.queueEvent(new CloseEvent(this, behaviorEvent.getBehavior()));
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
        FacesContext context = getFacesContext();
        String userWidgetVar = (String) getAttributes().get("widgetVar");

        if (userWidgetVar != null) {
            return userWidgetVar;
        } else {
            return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
        }
    }
}
