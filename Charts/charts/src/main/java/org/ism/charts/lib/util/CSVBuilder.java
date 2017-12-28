/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.expression.SearchExpressionFacade;
import org.ism.charts.lib.expression.SearchExpressionHint;

/**
 * Helper to generate javascript code of a client side validation*
 */
public class CSVBuilder {

    protected StringBuilder buffer;
    protected FacesContext context;

    public CSVBuilder(FacesContext context) {
        this.context = context;
        this.buffer = new StringBuilder();
    }

    public CSVBuilder init() {
        buffer.append("if(PrimeFaces.vb({");
        return this;
    }

    public CSVBuilder source(String source) {
        if (source == null || source.equals("this")) {
            buffer.append("s:").append("this");
        } else {
            buffer.append("s:").append("'").append(source).append("'");
        }

        return this;
    }

    public CSVBuilder ajax(boolean value) {
        if (value) {
            buffer.append(",a:").append("true");
        }

        return this;
    }

    public CSVBuilder process(UIComponent component, String expressions) {
        if (expressions != null && expressions.trim().length() > 0) {
            String resolvedExpressions = SearchExpressionFacade.resolveClientIds(context, component, expressions);
            buffer.append(",p:'").append(resolvedExpressions).append("'");
        }

        return this;
    }

    public CSVBuilder update(UIComponent component, String expressions) {
        if (expressions != null && expressions.trim().length() > 0) {
            String resolvedExpressions = SearchExpressionFacade.resolveClientIds(
                    context, component, expressions, SearchExpressionHint.VALIDATE_RENDERER);
            buffer.append(",u:'").append(resolvedExpressions).append("'");
        }

        return this;
    }

    public CSVBuilder command(String command) {
        buffer.append("})){").append(command).append("}");

        return this;
    }

    public String build() {
        buffer.append("else{return false;}");

        String request = buffer.toString();

        reset();

        return request;
    }

    public void reset() {
        buffer.setLength(0);
    }
}
