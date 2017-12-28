/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.metadata;

import java.util.Set;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.el.ValueReference;
import javax.faces.context.FacesContext;
import javax.validation.Validator;
import javax.validation.groups.Default;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;
import org.ism.charts.lib.context.RequestContext;
import org.ism.charts.lib.el.ValueExpressionAnalyzer;

/**
 *
 * @author r.hendrick
 */
public class BeanValidationMetadataExtractor {

    public static Set<ConstraintDescriptor<?>> extractAllConstraintDescriptors(FacesContext context, RequestContext requestContext, ValueExpression ve) {

        PropertyDescriptor propertyDescriptor = extractPropertyDescriptor(context, requestContext, ve);

        if (propertyDescriptor != null) {
            return propertyDescriptor.getConstraintDescriptors();
        }

        return null;
    }

    public static Set<ConstraintDescriptor<?>> extractDefaultConstraintDescriptors(FacesContext context, RequestContext requestContext, ValueExpression ve) {

        return extractConstraintDescriptors(context, requestContext, ve, Default.class);
    }

    public static Set<ConstraintDescriptor<?>> extractConstraintDescriptors(FacesContext context, RequestContext requestContext, ValueExpression ve, Class... groups) {

        PropertyDescriptor propertyDescriptor = extractPropertyDescriptor(context, requestContext, ve);

        if (propertyDescriptor != null) {
            return propertyDescriptor.findConstraints().unorderedAndMatchingGroups(groups).getConstraintDescriptors();
        }

        return null;
    }

    public static PropertyDescriptor extractPropertyDescriptor(FacesContext context, RequestContext requestContext, ValueExpression ve) {

        if (ve != null) {
            ELContext elContext = context.getELContext();
            ValueReference vr = ValueExpressionAnalyzer.getReference(elContext, ve);

            if (vr != null) {
                Validator validator = requestContext.getApplicationContext().getValidator();
                Object base = vr.getBase();
                Object property = vr.getProperty();

                if (base != null && property != null) {
                    BeanDescriptor beanDescriptor = validator.getConstraintsForClass(base.getClass());

                    if (beanDescriptor != null) {
                        return beanDescriptor.getConstraintsForProperty(property.toString());
                    }
                }
            }
        }

        return null;
    }
}
