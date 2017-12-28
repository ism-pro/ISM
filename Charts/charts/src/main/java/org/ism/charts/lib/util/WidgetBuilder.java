/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.util;

import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author r.hendrick
 */
public class WidgetBuilder {

    protected boolean endFunction = false;
    protected FacesContext context;

    public WidgetBuilder(FacesContext context) {
        this.context = context;
    }

    /**
     *
     * @param widgetClass Constructor name of the widget
     * @param widgetVar Name of the client side widget
     * @param id Client id of the component
     * @param endFunction If the init script is wrapped by a method and if the
     * endFunction parentheses should be rendered.
     * @throws IOException
     * @return The current instance.
     */
    protected WidgetBuilder init(String widgetClass, String widgetVar, String id, boolean endFunction) throws IOException {
        this.endFunction = endFunction;

        context.getResponseWriter().write("IChartsFaces.cw(\"");
        context.getResponseWriter().write(widgetClass);
        context.getResponseWriter().write("\",\"");
        context.getResponseWriter().write(widgetVar);
        context.getResponseWriter().write("\",{");
        context.getResponseWriter().write("id:\"");
        context.getResponseWriter().write(id);
        context.getResponseWriter().write("\"");

        return this;
    }

    public WidgetBuilder init(String widgetClass, String widgetVar, String id) throws IOException {
        this.renderScriptBlock(id);
        this.init(widgetClass, widgetVar, id, false);

        return this;
    }

    public WidgetBuilder initWithDomReady(String widgetClass, String widgetVar, String id) throws IOException {

        this.renderScriptBlock(id);
        context.getResponseWriter().write("$(function(){");
        this.init(widgetClass, widgetVar, id, true);

        return this;
    }

    public WidgetBuilder initWithWindowLoad(String widgetClass, String widgetVar, String id) throws IOException {

        this.renderScriptBlock(id);
        context.getResponseWriter().write("$(window).load(function(){");
        this.init(widgetClass, widgetVar, id, true);

        return this;
    }

    public WidgetBuilder initWithComponentLoad(String widgetClass, String widgetVar, String id, String targetId) throws IOException {

        this.renderScriptBlock(id);
        context.getResponseWriter().write("$(IChartsFaces.escapeClientId(\"" + targetId + "\")).load(function(){");
        this.init(widgetClass, widgetVar, id, true);

        return this;
    }

    private void renderScriptBlock(String id) throws IOException {
        context.getResponseWriter().startElement("script", null);
        context.getResponseWriter().writeAttribute("id", id + "_s", null);
        context.getResponseWriter().writeAttribute("type", "text/javascript", null);
    }

    public WidgetBuilder attr(String name, String value) throws IOException {
        if (value != null) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":\"");
            context.getResponseWriter().write(value);
            context.getResponseWriter().write("\"");
        }

        return this;
    }

    public WidgetBuilder nativeAttr(String name, String value) throws IOException {
        if (value != null) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(value);
        }

        return this;
    }

    public WidgetBuilder nativeAttr(String name, String value, String defaultValue) throws IOException {
        if (value != null && !value.equals(defaultValue)) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(value);
        }

        return this;
    }

    public WidgetBuilder attr(String name, Boolean value) throws IOException {
        if (value != null) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(Boolean.toString(value));
        }

        return this;
    }

    public WidgetBuilder attr(String name, Number value) throws IOException {
        if (value != null) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(value.toString());
        }

        return this;
    }

    public WidgetBuilder attr(String name, String value, String defaultValue) throws IOException {
        if (value != null && !value.equals(defaultValue)) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":\"");
            context.getResponseWriter().write(value);
            context.getResponseWriter().write("\"");
        }

        return this;
    }

    public WidgetBuilder attr(String name, double value, double defaultValue) throws IOException {
        if (value != defaultValue) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(Double.toString(value));
        }

        return this;
    }

    public WidgetBuilder attr(String name, int value, int defaultValue) throws IOException {
        if (value != defaultValue) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(Integer.toString(value));
        }

        return this;
    }

    public WidgetBuilder attr(String name, boolean value, boolean defaultValue) throws IOException {
        if (value != defaultValue) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(Boolean.toString(value));
        }

        return this;
    }

    public WidgetBuilder callback(String name, String signature, String callback) throws IOException {
        if (callback != null) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(signature);
            context.getResponseWriter().write("{");
            context.getResponseWriter().write(callback);
            context.getResponseWriter().write("}");
        }
        return this;
    }

    public WidgetBuilder returnCallback(String name, String signature, String callback) throws IOException {
        if (callback != null) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(signature);
            context.getResponseWriter().write("{return ");
            context.getResponseWriter().write(callback);
            context.getResponseWriter().write("}");
        }
        return this;
    }

    public WidgetBuilder callback(String name, String callback) throws IOException {
        if (callback != null) {
            context.getResponseWriter().write(",");
            context.getResponseWriter().write(name);
            context.getResponseWriter().write(":");
            context.getResponseWriter().write(callback);
        }
        return this;
    }

    public WidgetBuilder append(String str) throws IOException {
        context.getResponseWriter().write(str);

        return this;
    }

    public WidgetBuilder append(char chr) throws IOException {
        context.getResponseWriter().write(chr);

        return this;
    }

    public WidgetBuilder append(Number number) throws IOException {
        context.getResponseWriter().write(number.toString());

        return this;
    }

    /**
     * Close only the object but keep open the jquery block
     *
     * @since 1712.20
     * @throws IOException exception 
     */
    public void closeFaces() throws IOException {
        context.getResponseWriter().write("}");
        context.getResponseWriter().write(");");
    }

    /**
     * Ferme le bloque jquery à utiliser en combinaison avec close
     * @since 1712.20
     * @throws IOException exception
     */
    public void terminate() throws IOException {
        if (endFunction) {
            context.getResponseWriter().write("});");
        }
        context.getResponseWriter().endElement("script");
    }

    public void finish() throws IOException {
        context.getResponseWriter().write("}");
        context.getResponseWriter().write(");");
        if (endFunction) {
            context.getResponseWriter().write("});");
        }
        context.getResponseWriter().endElement("script");
    }
}
