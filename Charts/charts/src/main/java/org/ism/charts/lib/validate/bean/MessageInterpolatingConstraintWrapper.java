/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.validate.bean;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import javax.validation.MessageInterpolator;
import javax.validation.metadata.ConstraintDescriptor;

/**
 *
 * @author r.hendrick
 */
public class MessageInterpolatingConstraintWrapper extends ConstraintDescriptorWrapper<Annotation> {

    private final MessageInterpolator interpolator;
    private final MessageInterpolator.Context context;

    public MessageInterpolatingConstraintWrapper(MessageInterpolator interpolator, ConstraintDescriptor<?> constraintDescriptor) {
        super(constraintDescriptor);
        this.interpolator = interpolator;
        this.context = new ContextImpl(constraintDescriptor, null);
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = super.getAttributes();

        // wrap it - could be unmodifiable
        attributes = new HashMap<String, Object>(attributes);

        Object message = attributes.remove(ClientValidationConstraint.ATTR_MESSAGE);
        if (message != null) {
            if (message instanceof String) {
                message = interpolator.interpolate((String) message, getContext());
            }
            attributes.put(ClientValidationConstraint.ATTR_MESSAGE, message);
        }
        return attributes;
    }

    private MessageInterpolator.Context getContext() {
        return context;
    }

    public class ContextImpl implements MessageInterpolator.Context {

        private final ConstraintDescriptor<?> constraintDescriptor;
        private final Object validatedValue;

        public ContextImpl(ConstraintDescriptor<?> constraintDescriptor, Object validatedValue) {
            this.constraintDescriptor = constraintDescriptor;
            this.validatedValue = validatedValue;
        }

        @Override
        public ConstraintDescriptor<?> getConstraintDescriptor() {
            return constraintDescriptor;
        }

        @Override
        public Object getValidatedValue() {
            return validatedValue;
        }

        //@Override - BV 1.1
        @Override
        public <T> T unwrap(Class<T> type) {
            return null;
        }
    }
}
