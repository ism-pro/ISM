/*
 *
 */
package org.ism.charts.lib.component.desk;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
//import javax.faces.component.behavior.ClientBehavior;
//import javax.faces.component.behavior.ClientBehaviorContext;
//import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import org.ism.charts.lib.component.chart.Chart;
import org.ism.charts.lib.component.tabview.Tab;
import org.ism.charts.lib.renderKit.CoreRenderer;
import org.ism.charts.lib.util.Util;
//import org.ism.jsfc.component.desk.Desk;
//import org.ism.jsfc.component.tab.Tab;
//import org.ism.jsfc.event.desk.ActiveIndexChanged;
//import org.ism.jsfc.renderKit.CoreRenderer;
//import org.ism.jsfc.util.Util;

@FacesRenderer(componentFamily = Desk.COMPONENT_FAMILY,
        rendererType = Desk.RENDERER_TYPE)
public class Deskkit extends CoreRenderer {

    /**
     * <p>
     * Decode the incoming request parameters</p>
     *
     * @param context <code>FaceContext</code> current context
     * @param component  <code>UIComponent</code> current component
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        // Get Component and Id
        Desk desk = (Desk) component;
        String clientId = desk.getClientId();

        // Get Parameters
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        //Restore Menu
        String menuParam = params.get(clientId + Desk.PARAM_MENU);
        if (menuParam != null) {
            desk.setMenu(Integer.valueOf(menuParam));
        }

        //Restore toggle state
        String collapsedParam = params.get(clientId + "_collapsed");
        if (collapsedParam != null) {
            desk.setCollapsed(Boolean.valueOf(collapsedParam));
        }

        //Restore visibility state
        String visibleParam = params.get(clientId + "_visible");
        if (visibleParam != null) {
            desk.setVisible(Boolean.valueOf(visibleParam));
        }

        decodeBehaviors(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        Chart chart = (Chart) component;

        encodeMarkup(context, chart);
        encodeScript(context, chart);
    }

    protected void encodeMarkup(FacesContext context, UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        // Can't start rendering if not a renderer
        if (!component.isRendered()) {
            System.out.println(this.getClass().getName() + " is not a renderer");
            return;
        }

        // Get Parameters
        Desk desk = (Desk) component;
        String clientId = desk.getClientId();
        String style = desk.getStyle() != null ? desk.getStyle() : null;
        String styleClass = desk.getStyleClass() != null
                ? Desk.DESK + " " + desk.getStyleClass()
                : Desk.DESK;

        // Get Writer
        ResponseWriter writer = context.getResponseWriter();

        // Main GROUP
        writer.startElement("nav", desk);
        writer.writeAttribute("id", clientId, "id");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
        writer.writeAttribute("class", styleClass, "styleClass");

        // SIDE MENU
        writer.startElement("ul", null);
        writer.writeAttribute("id", clientId + "_sidemenu", "id");
        writer.writeAttribute("class", "c-sidemenu", "class");

        // NAV MENU
        writer.startElement("li", null);
        writer.writeAttribute("id", clientId + "_naviconbtn", "id");
        writer.writeAttribute("class", "c-menu c-item c-menunav", "class");

        writer.startElement("a", null);
        writer.writeAttribute("id", clientId + "_naviconlnk", "id");
        writer.writeAttribute("href", "#", "href");

        writer.startElement("i", null);
        writer.writeAttribute("class", "fa fa-navicon fa-1x", "class");
        writer.writeAttribute("aria-hidden", "true", "aria-hidden");

        writer.endElement("i");
        writer.endElement("a");
        writer.endElement("li");

        // Loop over desk children
        List children = component.getChildren();
        Integer menuCounter = 0;
//        String formClientId = findFormClientId(context, component);
//        String showOneClientId = component.getClientId();

        for (Iterator iter = children.iterator(); iter.hasNext();) {
            UIComponent child = (UIComponent) iter.next();
            if (child instanceof Tab) {
                Tab tab = (Tab) child;
                menuCounter++;
                //String id = clientId + "_nav_" + menuCounter;
                String id = tab.getClientId();

                // MENU
                writer.startElement("li", null);
                writer.writeAttribute("id", id, "id");
                writer.writeAttribute("class", "c-menu " + (desk.getMenu().equals(menuCounter) ? Desk.DESK_ACTIVETAB : ""), "class");

                writer.startElement("a", null);
                writer.writeAttribute("id", clientId + "_navlnk_" + menuCounter, "id");
                //writer.writeAttribute("href", "#", "href");

                writer.startElement("i", null);
                writer.writeAttribute("class", tab.getFaIcon(), "class");
                writer.writeAttribute("aria-hidden", "true", "aria-hidden");

                writer.endElement("i");
                writer.endElement("a");
                writer.endElement("li");

            }

        }

        writer.endElement("ul");   // END DECK COMPONENT
        writer.endElement("nav");   // END DECK COMPONENT

        // Managing state Holder
        encodeStateHolder(context, desk, clientId + Desk.PARAM_MENU, String.valueOf(desk.getMenu()));
        if (desk.isToggleable()) {
            encodeStateHolder(context, panel, clientId + "_collapsed", String.valueOf(collapsed));
        }

        if (desk.isClosable()) {
            encodeStateHolder(context, panel, clientId + "_visible", String.valueOf(visible));
        }

        encodeStateHolder(context, desk, clientId + "_selectedSerieId", String.valueOf(chart.getSelectedSerieId()));
        encodeStateHolder(context, desk, clientId + "_selectedSerieOptions", String.valueOf(chart.getSelectedSerieOptions()));

    }

    /**
     * <p>
     * Convenient method to start rendering script attached to the file
     * </p>
     *
     * @param context main context
     * @param component current component
     * @throws IOException exception
     *
     * @since 1612.16
     */
    protected void encodeScript(FacesContext context, UIComponent component) throws IOException {
        // Get Writer
        ResponseWriter writer = context.getResponseWriter();
        // Get Parameters
        Desk desk = (Desk) component;
        String clientId = desk.getClientId(context);

        UIComponent form = Util.closestForm(context, desk);
        if (form == null) {
            throw new FacesException("Desk : \"" + clientId + "\" must be inside a form element");
        }

        startScript(writer, clientId);
        writer.write("$(function() {");
        writer.write("ISMJsfc.cw('Desk','" + desk.resolveWidgetVar() + "',{");
        writer.write("id:'" + clientId + "'");
        writer.write(",currentMenu: " + desk.getMenu());

        // Client Behavior
        encodeClientBehaviors(context, desk);

        // close CW
        writer.write("});");
        // Close JQuery
        writer.write("});");

        endScript(writer);
    }

    protected void encodeStateHolder(FacesContext context, Desk desk, String idName, String value) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("input", null);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("id", idName, null);
        writer.writeAttribute("name", idName, null);
        writer.writeAttribute("value", value, null);
        writer.endElement("input");
    }

}
