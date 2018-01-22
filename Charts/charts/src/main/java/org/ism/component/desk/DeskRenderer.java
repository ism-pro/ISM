/*
 *
 */
package org.ism.component.desk;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import org.ism.component.tabview.Tab;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

@FacesRenderer(componentFamily = Desk.COMPONENT_FAMILY,
        rendererType = Desk.RENDERER_TYPE)
public class DeskRenderer extends CoreRenderer {

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
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        Desk desk = (Desk) component;

        encodeMarkup(context, desk);
        encodeScript(context, desk);
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

        // NAV DESK
        writer.startElement("nav", desk);
        writer.writeAttribute("id", clientId, "id");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
        writer.writeAttribute("class", styleClass, "styleClass");

        //=== === SIDE MENU
        writer.startElement("ul", null);
        writer.writeAttribute("id", clientId + "_sidemenu", "id");
        writer.writeAttribute("class", Desk.DESK_SIDEMENU, "class");
        encodeNavigator(context, desk); // ENCODOE NAVIGATOR
        encodeMenus(context, desk);     // Encode Menus
        writer.endElement("ul");
        //=== === END SIDE MENU

        //=== === Start Content 
        writer.startElement("div", null);
        writer.writeAttribute("id", clientId + "_contentmenu", "id");
        writer.writeAttribute("class", Desk.DESK_CONTENT_MENU, null);
        encodeContentBar(context, desk);
        encodeContentMenus(context, desk);
        writer.endElement("div");
        //=== ===  END Content

        writer.endElement("nav");   // END DECK COMPONENT

        // Managing state Holder
        encodeStateHolder(context, desk, clientId + Desk.PARAM_MENU, String.valueOf(desk.getMenu()));
    }

    protected void encodeNavigator(FacesContext context, Desk desk) throws IOException {

        // Get Parameters
        String clientId = desk.getClientId();
        String Nav_Title = "Réduire/Agrandir";

        // Get Writer
        ResponseWriter writer = context.getResponseWriter();

        // NAVIGATION BUTTON
        writer.startElement("li", null);
        writer.writeAttribute("id", clientId + "_naviconbtn", "id");
        writer.writeAttribute("class", Desk.DESK_MENU_NAV, "class");
        if (desk.isNavigatorTitled()) {
            writer.writeAttribute("data-toggle", "tooltip", null);
            writer.writeAttribute("data-placement", "right", null);
            writer.writeAttribute("title", desk.getNavigatorTitle() == null ? Nav_Title : desk.getNavigatorTitle(), null);
        }

        // Encoding LINK A
        writer.startElement("a", null);
        writer.writeAttribute("id", clientId + "_naviconlnk", "id");
        //writer.writeAttribute("href", "#", "href");

        writer.startElement("i", null);
        writer.writeAttribute("class", desk.getIcon() == null ? "fa fa-navicon fa-2x" : desk.getIcon(), "class");
        writer.writeAttribute("aria-hidden", "true", "aria-hidden");

        writer.endElement("i");
        writer.endElement("a");
        writer.endElement("li");
    }

    protected void encodeMenus(FacesContext context, Desk desk) throws IOException {
        // Get Parameters
        String clientId = desk.getClientId();
        // Get Writer
        ResponseWriter writer = context.getResponseWriter();

        // LOOP OVER CHILDREEN TO CREATE MENU
        List children = desk.getChildren();
        Integer menuCounter = 0;

        for (Iterator iter = children.iterator(); iter.hasNext();) {
            UIComponent child = (UIComponent) iter.next();
            if (child instanceof Tab) {
                Tab tab = (Tab) child;
                menuCounter++;
                String id = tab.getClientId() + "_nav_" + menuCounter;

                // MENU
                writer.startElement("li", null);
                writer.writeAttribute("id", id, "id");
                writer.writeAttribute("class", Desk.DESK_MENU + (desk.getMenu().equals(menuCounter) ? " " + Desk.DESK_MENU_ACTIVE : ""), "class");
                writer.writeAttribute("data-menu", menuCounter, null);
                tab.setTitle(tab.getTitle() != null ? tab.getTitle() : "");
                writer.writeAttribute("data-title", tab.getTitle(), null);
                if (tab.getTitletip() != null) {
                    writer.writeAttribute("data-toggle", "tooltip", null);
                    writer.writeAttribute("data-placement", "right", null);
                    writer.writeAttribute("title", tab.getTitletip(), null);
                }

                // Encoding LINK A
                writer.startElement("a", null);
                writer.writeAttribute("id", clientId + "_navlnk_" + menuCounter, "id");

                writer.startElement("i", null);
                writer.writeAttribute("class", tab.getFaIcon() == null ? "fa fa-navicon fa-2x" : tab.getFaIcon(), "class");
                writer.writeAttribute("aria-hidden", "true", "aria-hidden");

                writer.endElement("i");
                writer.endElement("a");
                writer.endElement("li");
            }
        }
    }

    protected void encodeContentBar(FacesContext context, Desk desk) throws IOException {

        // Get Parameters
        String clientId = desk.getClientId();

        // Get Writer
        ResponseWriter writer = context.getResponseWriter();

        // Do not rendered if disabled 
        if (!desk.isHeadered()) {
            return;
        }

        //  BUTTON
        writer.startElement("div", null);
        writer.writeAttribute("id", clientId + "_content_bar", "id");
        writer.writeAttribute("class", Desk.DESK_CONTENT_BAR, "class");

        // + Start encode content bar title
        writer.startElement("div", null);
        writer.writeAttribute("class", Desk.DESK_CONTENT_BARTITLE, "class");
        writer.startElement("span", null);
        writer.writeAttribute("class", Desk.DESK_CONTENT_BAR_TITLE, "class");
        writer.writeText(((Tab) desk.getChildren().get(desk.getMenu()-1)).getTitle(), null);
        writer.endElement("span");
        writer.endElement("div");

        // + Start encode content bar btns
        writer.startElement("div", null);
        writer.writeAttribute("class", Desk.DESK_CONTENT_BAR_BTNS, "class");
        // Encoding Closer
        if (desk.isClosable()) {
            writer.startElement("a", null);
            writer.writeAttribute("id", clientId + "_desk_closer", "id");
            writer.writeAttribute("class", "c-btn-close btn-light c-btn-circle " + Desk.DESK_CONTENT_BAR_BTN, "class");
            if (desk.getCloseTitle() != null) {
                writer.writeAttribute("data-toggle", "tooltip", null);
                writer.writeAttribute("data-placement", "bottom", null);
                writer.writeAttribute("title", desk.getCloseTitle() == null ? "Fermer le menu" : desk.getCloseTitle(), null);
            }
            writer.startElement("i", null);
            writer.writeAttribute("class", "fa fa-close fa-1x", "class");
            writer.writeAttribute("aria-hidden", "true", "aria-hidden");
            writer.endElement("i");
            writer.endElement("a");
        }
        // Encoding Collapser
        if (desk.isCollapsable()) {
            writer.startElement("a", null);
            writer.writeAttribute("id", clientId + "_desk_collapser", "id");
            writer.writeAttribute("class", "c-btn-collapse-fixed btn-light c-btn-circle " + Desk.DESK_CONTENT_BAR_BTN, "class");
            if (desk.getCollapseTitle() != null) {
                writer.writeAttribute("data-toggle", "tooltip", null);
                writer.writeAttribute("data-placement", "bottom", null);
                writer.writeAttribute("title", desk.getCollapseTitle() == null ? "Fermer le menu" : desk.getCollapseTitle(), null);
            }
            writer.startElement("i", null);
            writer.writeAttribute("class", "fa fa-thumb-tack fa-1x", "class");
            writer.writeAttribute("aria-hidden", "true", "aria-hidden");
            writer.endElement("i");
            writer.endElement("a");
        }
        writer.endElement("div");
        writer.endElement("div");

    }

    protected void encodeContentMenus(FacesContext context, Desk desk) throws IOException {
        // Get Parameters
        String clientId = desk.getClientId();
        // Get Writer
        ResponseWriter writer = context.getResponseWriter();

        // LOOP OVER CHILDREEN TO CREATE MENU
        List children = desk.getChildren();
        Integer menuCounter = 0;

        for (Iterator iter = children.iterator(); iter.hasNext();) {
            UIComponent child = (UIComponent) iter.next();
            if (child instanceof Tab) {
                Tab tab = (Tab) child;
                menuCounter++;
                String id = tab.getClientId() + "_nav_" + menuCounter + "_content";

                // MENU
                writer.startElement("div", null);
                writer.writeAttribute("id", id , "id");
                writer.writeAttribute("class", Desk.DESK_CONTENT_DEF, "class");
                writer.writeAttribute("data-menu", menuCounter, null);

                tab.encodeChildren(context);

                writer.endElement("div");
            }
        }
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

        WidgetBuilder wb = new WidgetBuilder(context);
        wb.init("Desk", desk.resolveWidgetVar(), clientId);

        wb.attr("closable", desk.isClosable());
        wb.attr("closeSpeed", desk.getCloseSpeed());
        wb.attr("collapsable", desk.isCollapsable());
        wb.attr("collapsed", desk.isCollapsed());
        wb.attr("collapseSpeed", desk.getCollapseSpeed());
        wb.attr("headered", desk.isHeadered());
        wb.attr("menu", desk.getMenu());
        wb.attr("navigatorTitled", desk.isNavigatorTitled());
        
        encodeClientBehaviors(context, desk);
        wb.finish();
    }

    protected void encodeStateHolder(FacesContext context, Desk desk, String stateHolderId, String activeMenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("input", null);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("id", stateHolderId, null);
        writer.writeAttribute("name", stateHolderId, null);
        writer.writeAttribute("value", activeMenu, null);
        writer.writeAttribute("autocomplete", "off", null);
        writer.endElement("input");
    }

}
