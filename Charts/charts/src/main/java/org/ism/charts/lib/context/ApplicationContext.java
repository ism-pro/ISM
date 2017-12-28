/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.context;

import java.util.Map;
import javax.faces.context.FacesContext;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.ism.charts.lib.cache.CacheProvider;
import org.ism.charts.lib.config.IChartsConfiguration;

/**
 * A {@link ApplicationContext} is a contextual store, similar to
 * {@link RequestContext}. Only one {@link ApplicationContext} should be
 * available in the application.
 *
 * It can be accessed via:
 * <blockquote>
 * RequestContext.getCurrentInstance().getApplicationContext();
 * </blockquote>
 */
public abstract class ApplicationContext {

    public static final String INSTANCE_KEY = ApplicationContext.class.getName();

    public static ApplicationContext getCurrentInstance() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        return (ApplicationContext) facesContext.getExternalContext().getApplicationMap().get(INSTANCE_KEY);
    }

    public static void setCurrentInstance(final ApplicationContext context, final FacesContext facesContext) {
        facesContext.getExternalContext().getApplicationMap().put(INSTANCE_KEY, context);
    }

    public abstract IChartsConfiguration getConfig();

    public abstract ValidatorFactory getValidatorFactory();

    public abstract CacheProvider getCacheProvider();

    public abstract Map<Class<?>, Map<String, Object>> getEnumCacheMap();

    public abstract Map<Class<?>, Map<String, Object>> getConstantsCacheMap();

    public abstract Validator getValidator();

    public abstract void release();
}
