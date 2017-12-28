/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.validate.bean;

import java.util.List;
import java.util.Map;

/**
 *
 * @author r.hendrick
 */
public class BeanValidationMetadata {

    private Map<String, Object> attributes;
    private List<String> validatorIds;

    public BeanValidationMetadata() {
    }

    public BeanValidationMetadata(Map<String, Object> attributes, List<String> validatorIds) {
        this.attributes = attributes;
        this.validatorIds = validatorIds;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<String> getValidatorIds() {
        return validatorIds;
    }

    public void setValidatorIds(List<String> validatorIds) {
        this.validatorIds = validatorIds;
    }
}
