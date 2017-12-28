/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.api;

import java.io.Serializable;

/**
 *
 * @author r.hendrick
 */
@SuppressWarnings({"SerializableHasSerializationMethods", "NonSerializableFieldInSerializableClass"})
public class SavedState implements Serializable {

    private static final long serialVersionUID = 4325654657465654768L;

    private Object submittedValue;

    private boolean submitted;

    Object getSubmittedValue() {
        return (this.submittedValue);
    }

    void setSubmittedValue(Object submittedValue) {
        this.submittedValue = submittedValue;
    }

    private boolean valid = true;

    boolean isValid() {
        return (this.valid);
    }

    void setValid(boolean valid) {
        this.valid = valid;
    }

    private Object value;

    Object getValue() {
        return (this.value);
    }

    public void setValue(Object value) {
        this.value = value;
    }

    private boolean localValueSet;

    boolean isLocalValueSet() {
        return (this.localValueSet);
    }

    public void setLocalValueSet(boolean localValueSet) {
        this.localValueSet = localValueSet;
    }

    public boolean getSubmitted() {
        return this.submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}
