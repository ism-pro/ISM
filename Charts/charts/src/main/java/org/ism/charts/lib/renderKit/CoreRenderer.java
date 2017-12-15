/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.renderKit;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

/**
 * <h1>CoreRenderer</h1>
 *
 * <h2>Description</h2>
 * HtmlRenderer is a base class for all Renderers that output HTML markup.
 *
 *
 * @author r.hendrick
 */
public class CoreRenderer extends Renderer {

    /**
     * You create the keys using the fully qualified class name of the
     * HtmlRenderer class, com.apress.projsf.ch2.render.html.HtmlRenderer, and
     * appending either STYLE_WRITTEN or SCRIPTS_WRITTEN to distinguish between
     * style and script resources
     *
     */
    static private final String STYLE_RESOURCES_KEY
            = CoreRenderer.class.getName() + ".STYLES_WRITTEN";
    /**
     * You create the keys using the fully qualified class name of the
     * HtmlRenderer class, com.apress.projsf.ch2.render.html.HtmlRenderer, and
     * appending either STYLE_WRITTEN or SCRIPTS_WRITTEN to distinguish between
     * style and script resources
     *
     */
    static private final String SCRIPT_RESOURCES_KEY
            = CoreRenderer.class.getName() + ".SCRIPTS_WRITTEN";

    /**
     * Begins the encoded output for this component. "encodeResources" is called
     * automatically during encodeBegin() and can be overridden by your subclass
     *
     * @param context the Faces context
     * @param component the Faces component
     *
     * @throws IOException if an I/O error occurs during rendering
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        // write out resources
        encodeResources(context, component);
    }

    /**
     * <p>
     * Encode the children of this component.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param component <code>UIComponent</code> current component
     * @throws java.io.IOException <code>java.io.IOException</code> for
     * exception
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
                // Can't start rendering if not a renderer
        if (!component.isRendered()) {
            return;
        }
    }

    /**
     * Override hook for subclasses to write out their resources.
     *
     * @param context <code>FacesContext</code> for the current request
     * @param component <code>UIComponent</code> current component
     * @throws java.io.IOException <code>java.io.IOException</code> for
     * exception
     */
    protected void encodeResources(FacesContext context, UIComponent component)
            throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        // empty hook for subclasses to override as needed
    }

    /**
     * Encode script super class for encode script which is call at the of 
     * encoded end associate only for spefic script
     *
     * @param context <code>FacesContext</code> for the current request
     * @param component <code>UIComponent</code> current component
     * @throws java.io.IOException <code>java.io.IOException</code> for
     * exception
     */
    protected void encodeScripts(FacesContext context, UIComponent component)
            throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        // empty hook for subclasses to override as needed
    }

    /**
     * Endding the encoded output for this component.
     *
     * @param context <code>FacesContext</code> for the current request
     * @param component <code>UIComponent</code> current component
     * @throws IOException <code>java.io.IOException</code> for exception
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        // Encode script of the renderer
        encodeScripts(context, component);
    }

    /**
     * Writes a style sheet resource at-most-once within a single RenderResponse
     * phase.
     *
     * @param context <code>FacesContext</code> for the current request
     * @param resourcePath the style sheet resource path
     *
     * @throws IOException if an error occurs during rendering
     */
    protected void writeStyleResource(FacesContext context, String resourcePath)
            throws IOException {
        if ((context == null) || (resourcePath == null)) {
            throw new NullPointerException();
        }
        Set styleResources = getStyleResourcesAlreadyWritten(context);
        // Set.add() returns true only if item was added to the set
        // and returns false if item was already present in the set
        if (styleResources.add(resourcePath)) {
            ViewHandler handler = context.getApplication().getViewHandler();
            String resourceURL = handler.getResourceURL(context, resourcePath);
            ResponseWriter out = context.getResponseWriter();
            out.startElement("style", null);
            out.writeAttribute("type", "text/css", null);
            out.writeText("@import url(" + resourceURL + ");", null);
            out.endElement("style");
        }
    }

    /**
     * Writes a script library resource at-most-once within a single
     * RenderResponse phase.
     *
     * @param context the Faces context
     *
     * @param resourcePath the script library resource path
     *
     * @throws IOException if an error occurs during rendering
     */
    protected void writeScriptResource(FacesContext context, String resourcePath)
            throws IOException {
        if ((context == null) || (resourcePath == null)) {
            throw new NullPointerException();
        }
        Set scriptResources = getScriptResourcesAlreadyWritten(context);
        // Set.add() returns true only if item was added to the set
        // and returns false if item was already present in the set
        if (scriptResources.add(resourcePath)) {
            ViewHandler handler = context.getApplication().getViewHandler();
            String resourceURL = handler.getResourceURL(context, resourcePath);
            ResponseWriter out = context.getResponseWriter();
            out.startElement("script", null);
            out.writeAttribute("type", "text/javascript", null);
            out.writeAttribute("src", resourceURL, null);
            out.endElement("script");
        }
    }

    /**
     * The _getStyleResourceAlreadyWritten() method implements the at-most-once
     * semantics by adding a key�_STYLE_RESOURCE_KEY�with an associated Map to
     * the request scope. This Map is populated by the writeStyleResource()
     * method
     *
     * Implements at-most-once semantics for each style resource on the
     * currently rendering page
     */
    private Set getStyleResourcesAlreadyWritten(FacesContext context) {
        if ((context == null)) {
            throw new NullPointerException();
        }
        ExternalContext external = context.getExternalContext();
        Map requestScope = external.getRequestMap();
        Set written = (Set) requestScope.get(STYLE_RESOURCES_KEY);
        if (written == null) {
            written = new HashSet();
            requestScope.put(STYLE_RESOURCES_KEY, written);
        }
        return written;
    }

    /**
     * <h3>getScriptResourcesAlreadyWritten</h3>
     *
     * a similar method, the _getScriptResourceAlreadyWritten() method, that
     * creates a similar key-Map pair as the previously mentioned
     * _getStyleResourceAlreadyWritten() method and guarantees that script
     * resources are written only once.
     *
     */
    private Set getScriptResourcesAlreadyWritten(FacesContext context) {
        if ((context == null)) {
            throw new NullPointerException();
        }
        ExternalContext external = context.getExternalContext();
        Map requestScope = external.getRequestMap();
        Set written = (Set) requestScope.get(SCRIPT_RESOURCES_KEY);
        if (written == null) {
            written = new HashSet();
            requestScope.put(SCRIPT_RESOURCES_KEY, written);
        }
        return written;
    }

}
