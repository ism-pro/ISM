/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.validate.bean;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.Digits;
import javax.validation.metadata.ConstraintDescriptor;
import org.ism.charts.lib.util.HTML;

/**
 *
 * @author r.hendrick
 */
public class DigitsClientValidationConstraint implements ClientValidationConstraint {

    private static final String MESSAGE_METADATA = "data-p-digits-msg";
    private static final String MESSAGE_ID = "{javax.validation.constraints.Digits.message}";

    @Override
    public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
        Map<String, Object> metadata = new HashMap<String, Object>();
        Map attrs = constraintDescriptor.getAttributes();
        Object message = attrs.get(ATTR_MESSAGE);

        metadata.put(HTML.VALIDATION_METADATA.DIGITS_INTEGER, attrs.get("integer"));
        metadata.put(HTML.VALIDATION_METADATA.DIGITS_FRACTION, attrs.get("fraction"));

        if (!message.equals(MESSAGE_ID)) {
            metadata.put(MESSAGE_METADATA, message);
        }

        return metadata;
    }

    @Override
    public String getValidatorId() {
        return Digits.class.getSimpleName();
    }
}
