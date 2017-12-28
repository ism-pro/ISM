/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.validate.bean;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.FacesWrapper;
import javax.validation.ConstraintTarget;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.validation.metadata.ConstraintDescriptor;

/**
 *
 * @author r.hendrick
 */
public class ConstraintDescriptorWrapper<T extends Annotation> implements ConstraintDescriptor<T>, FacesWrapper<ConstraintDescriptor<T>> {

    protected final ConstraintDescriptor<T> wrapped;

    public ConstraintDescriptorWrapper(ConstraintDescriptor<?> wrapped) {
        this.wrapped = (ConstraintDescriptor<T>) wrapped;
    }

    @Override
    public T getAnnotation() {
        return wrapped.getAnnotation();
    }

    @Override
    public Set<Class<?>> getGroups() {
        return wrapped.getGroups();
    }

    @Override
    public Set<Class<? extends Payload>> getPayload() {
        return wrapped.getPayload();
    }

    @Override
    public List<Class<? extends ConstraintValidator<T, ?>>> getConstraintValidatorClasses() {
        return wrapped.getConstraintValidatorClasses();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return wrapped.getAttributes();
    }

    @Override
    public Set<ConstraintDescriptor<?>> getComposingConstraints() {
        return wrapped.getComposingConstraints();
    }

    @Override
    public boolean isReportAsSingleViolation() {
        return wrapped.isReportAsSingleViolation();
    }

    @Override
    public ConstraintDescriptor<T> getWrapped() {
        return wrapped;
    }

    //@Override - BV 1.1
    public String getMessageTemplate() {
        return wrapped.getMessageTemplate();
    }

    //@Override - BV 1.1
    public ConstraintTarget getValidationAppliesTo() {
        return wrapped.getValidationAppliesTo();
    }
}
