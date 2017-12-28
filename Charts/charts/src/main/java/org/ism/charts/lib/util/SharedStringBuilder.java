/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.util;

import javax.faces.context.FacesContext;

/**
 *
 * @author r.hendrick
 */
public class SharedStringBuilder {

    /**
	 * Get a shared {@link StringBuilder} instance.
	 * This is required as e.g. 100 e.g. {@link org.primefaces.expression.SearchExpressionFacade#resolveClientId} calls would create 
	 * 300 {@link StringBuilder} instances!
	 *
	 * @param context The {@link FacesContext}
	 * @param key The key for the {@link FacesContext} attributes.
	 * @return The shared {@link StringBuilder} instance
	 */
	public static StringBuilder get(FacesContext context, String key) {
		StringBuilder builder = (StringBuilder) context.getAttributes().get(key);

		if (builder == null) {
			builder = new StringBuilder();
			context.getAttributes().put(key, builder);
		}
        else {
			builder.setLength(0);
		}

		return builder;
	}
    
    /**
	 * Get a shared {@link StringBuilder} instance.
	 * This is required as e.g. 100 e.g. {@link org.primefaces.expression.SearchExpressionFacade#resolveClientId} calls would create 
	 * 300 {@link StringBuilder} instances!
	 *
	 * @param key The key for the {@link FacesContext} attributes.
	 * @return The shared {@link StringBuilder} instance
	 */
	public static StringBuilder get(String key) {
        return get(FacesContext.getCurrentInstance(), key);
	}
}

