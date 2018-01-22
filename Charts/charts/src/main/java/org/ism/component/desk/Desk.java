package org.ism.component.desk;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.FacesEvent;
import org.ism.component.tabview.Tab;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.Constants;

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
    @ResourceDependency(library = "primefaces", name = "components.css")
    ,@ResourceDependency(library = "webjars", name = "font-awesome/4.7.0/css/font-awesome.min-jsf.css")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/css/bootstrap.min-jsf.css")
    ,@ResourceDependency(library = "ism", name = "desk/desk.min.css")

    ,@ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
    ,@ResourceDependency(library = "primefaces", name = "jquery/jquery-plugins.js")
    ,@ResourceDependency(library = "primefaces", name = "core.js")
    ,@ResourceDependency(library = "primefaces", name = "components.js")
    ,@ResourceDependency(library = "webjars", name = "popper.js/1.12.9/dist/umd/popper.min.js")
    ,@ResourceDependency(library = "webjars", name = "bootstrap/4.0.0-beta.2/js/bootstrap.bundle.min.js")
    ,@ResourceDependency(library = "ism", name = "desk/desk.js")
})

@FacesComponent(value = Desk.COMPONENT_TYPE)
public class Desk extends UIPanel implements org.primefaces.component.api.Widget, javax.faces.component.behavior.ClientBehaviorHolder, org.primefaces.component.api.PrimeClientBehaviorHolder {

    public static final String COMPONENT_TYPE = "org.ism.component.Desk";
    public static final String COMPONENT_FAMILY = "org.ism.component";
    public static final String RENDERER_TYPE = "org.ism.component.DeskRenderer";

    // Encode-Decode
    public static final String PARAM_MENU = "_menu";
    public static final String PARAM_COLLAPSE = "_collapsed";
    public static final String PARAM_VISIBLE = "_visible";

    // CSS
    public static final String DESK = "c-desk"; //
    public static final String DESK_SIDEMENU = "c-sidemenu";
    public static final String DESK_MENU = "c-menu";
    public static final String DESK_MENU_NAV = "c-menunav";
    public static final String DESK_MENU_ACTIVE = "c-active";

    public static final String DESK_CONTENT_MENU = "c-contentmenu";
    public static final String DESK_CONTENT_BAR = "c-content-bar";
    public static final String DESK_CONTENT_BARTITLE = "c-content-barTitle";
    public static final String DESK_CONTENT_BAR_TITLE = "c-content-bar-title";
    public static final String DESK_CONTENT_BAR_BTNS = "c-content-barBtns";
    public static final String DESK_CONTENT_BAR_BTN = "c-content-bar-btn";
    public static final String DESK_CONTENT_DEF = "c-menu-def";

//    public static final String DECK_ITEM = "desk-item";
//    public static final String DECK_ITEM_HEADER = "desk-item-header";
//    public static final String DECK_ITEM_CONTENT = "desk-item-content";
//
//    public static final String ID_HEADER = "_header";
//    public static final String ID_CONTENT = "_content";
    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// Properties
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    protected enum PropertyKeys {
        closable,
        closeSpeed,
        closeTitle,
        collapsed,
        collapsable,
        collapseSpeed,
        collapseTitle,
        headered,
        menu,
        icon,
        navigatorTitle,
        navigatorTitled,
        style,
        styleClass,
        itemStyleClass,
        itemHeaderStyleClass,
        itemContentStyleClass,
        toggleable,
        toggleTitle,
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
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.closable, true);
    }

    public void setClosable(java.lang.Boolean closable) {
        getStateHelper().put(PropertyKeys.closable, closable);
    }

    public java.lang.Integer getCloseSpeed() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.closeSpeed, 1000);
    }

    public void setCloseSpeed(java.lang.Integer closeSpeed) {
        getStateHelper().put(PropertyKeys.closeSpeed, closeSpeed);
    }

    public java.lang.String getCloseTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.closeTitle, null);
    }

    public void setCloseTitle(java.lang.String closeTitle) {
        getStateHelper().put(PropertyKeys.closeTitle, closeTitle);
    }

    public java.lang.Boolean isCollapsable() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.collapsable, true);
    }

    public void setCollapsable(java.lang.Boolean collapsable) {
        getStateHelper().put(PropertyKeys.collapsable, collapsable);
    }

    public java.lang.Boolean isCollapsed() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.collapsed, false);
    }

    public void setCollapsed(java.lang.Boolean collapsed) {
        getStateHelper().put(PropertyKeys.collapsed, collapsed);
    }

    public java.lang.Integer getCollapseSpeed() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.collapseSpeed, 150);
    }

    public void setCollapseSpeed(java.lang.Integer collapseSpeed) {
        getStateHelper().put(PropertyKeys.collapseSpeed, collapseSpeed);
    }

    public java.lang.String getCollapseTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.collapseTitle, null);
    }

    public void setCollapseTitle(java.lang.String collapseTitle) {
        getStateHelper().put(PropertyKeys.collapseTitle, collapseTitle);
    }

    public java.lang.Boolean isHeadered() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.headered, true);
    }

    public void setHeadered(java.lang.Boolean headered) {
        getStateHelper().put(PropertyKeys.headered, headered);
    }

    public java.lang.Integer getMenu() {
        return (java.lang.Integer) getStateHelper().eval(PropertyKeys.menu, 0);
    }

    public void setMenu(java.lang.Integer menu) {
        getStateHelper().put(PropertyKeys.menu, menu);
    }

    public java.lang.String getIcon() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.icon, null);
    }

    public void setIcon(java.lang.String icon) {
        getStateHelper().put(PropertyKeys.icon, icon);
    }

    public java.lang.String getNavigatorTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.navigatorTitle, null);
    }

    public void setNavigatorTitle(java.lang.String navigatorTitle) {
        getStateHelper().put(PropertyKeys.navigatorTitle, navigatorTitle);
    }

    public java.lang.Boolean isNavigatorTitled() {
        return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.navigatorTitled, true);
    }

    public void setNavigatorTitled(java.lang.Boolean navigatorTitled) {
        getStateHelper().put(PropertyKeys.navigatorTitled, navigatorTitled);
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

    public java.lang.String getToggleTitle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.toggleTitle, null);
    }

    public void setToggleTitle(java.lang.String toggleTitle) {
        getStateHelper().put(PropertyKeys.toggleTitle, toggleTitle);
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
        //return getStateHelper().saveState(context);
        Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = getMenu();
        return (values);
    }

    /**
     * Restores the state of this component.
     *
     * @param context the Faces context
     * @param state the saved state
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        //getStateHelper().restoreState(context, state);
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setMenu((Integer) values[1]);
    }
    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// EVENTS
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////
    private final static String DEFAULT_EVENT = "itemSelect, tabChange, toggle, close";
    private static final Map<String, Class<? extends BehaviorEvent>> BEHAVIOR_EVENT_MAPPING = Collections.unmodifiableMap(new HashMap<String, Class<? extends BehaviorEvent>>() {
        {
            put("itemSelect", ItemSelectEvent.class);
            put("tabChange", TabChangeEvent.class);
            put("toggle", ToggleEvent.class);
            put("close", CloseEvent.class);
        }
    });

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

            if (eventName.equals("itemSelect")) {
                Integer active = this.getMenu();
                super.queueEvent(new ItemSelectEvent(this, behaviorEvent.getBehavior(), active, 1));

            } else if (eventName.equals("tabChange")) {
                Integer active = this.getMenu();
                Tab tab = (Tab) this.getChildren().get(active);
                super.queueEvent(new TabChangeEvent(this, behaviorEvent.getBehavior(), tab));

            } else if (eventName.equals("toggle")) {
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
        return ComponentUtils.resolveWidgetVar(getFacesContext(), this);
    }
}
