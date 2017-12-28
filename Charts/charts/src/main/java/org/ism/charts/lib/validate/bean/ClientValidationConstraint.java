/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.validate.bean;

import java.util.Map;
import javax.validation.metadata.ConstraintDescriptor;

/**
 *
 * @author r.hendrick
 */
public interface ClientValidationConstraint {

    public static final String ATTR_MESSAGE = "message";

    public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor);

    public String getValidatorId();
}
