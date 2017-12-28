/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.expression;

import javax.faces.FacesException;

/**
 *
 * @author r.hendrick
 */
public class ComponentNotFoundException extends FacesException {

    public ComponentNotFoundException() {
        super();
    }

    public ComponentNotFoundException(String message) {
        super(message);
    }

    public ComponentNotFoundException(Throwable cause) {
        super(cause);
    }

    public ComponentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
