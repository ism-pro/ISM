/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.validate.bean;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.Pattern;
import javax.validation.metadata.ConstraintDescriptor;
import org.ism.charts.lib.util.HTML;

/**
 *
 * @author r.hendrick
 */
public class PatternClientValidationConstraint implements ClientValidationConstraint {

    private static final String MESSAGE_METADATA = "data-p-pattern-msg";
    private static final String MESSAGE_ID = "{javax.validation.constraints.Pattern.message}";

    @Override
    public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
        Map<String, Object> metadata = new HashMap<String, Object>();
        Map attrs = constraintDescriptor.getAttributes();
        Object message = attrs.get(ATTR_MESSAGE);

        metadata.put(HTML.VALIDATION_METADATA.PATTERN, attrs.get("regexp"));

        if (!message.equals(MESSAGE_ID)) {
            metadata.put(MESSAGE_METADATA, message);
        }

        return metadata;
    }

    @Override
    public String getValidatorId() {
        return Pattern.class.getSimpleName();
    }

}
