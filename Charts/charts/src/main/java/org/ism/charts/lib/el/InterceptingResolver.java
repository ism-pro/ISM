/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.el;

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ValueReference;

/**
 *
 * @author r.hendrick
 */
public class InterceptingResolver extends ELResolver {

    private final ELResolver delegate;
    private ValueReference valueReference;

    public InterceptingResolver(ELResolver delegate) {
        this.delegate = delegate;
    }

    public ValueReference getValueReference() {
        return valueReference;
    }

    // Capture the base and property rather than write the value
    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {
        // currently not used -> see #7114
        if (base != null && property != null) {
            context.setPropertyResolved(true);
            valueReference = new ValueReference(base, property.toString());
        }
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        context.setPropertyResolved(true);
        valueReference = new ValueReference(base, property.toString());

        return delegate.getValue(context, base, property);
    }

    // The rest of the methods simply delegate to the existing context
    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        return delegate.getType(context, base, property);
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        return delegate.isReadOnly(context, base, property);
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return delegate.getFeatureDescriptors(context, base);
    }

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return delegate.getCommonPropertyType(context, base);
    }

    // @Override
    // < EL 2.2 compatibility
    public Object invoke(ELContext context,
            Object base,
            Object method,
            Class<?>[] paramTypes,
            Object[] params) {
        return delegate.invoke(context, base, method, paramTypes, params);
    }
}
