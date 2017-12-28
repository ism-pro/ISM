/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.api;

/**
 *
 * @author r.hendrick
 */
public interface Confirmable {

    public boolean requiresConfirmation();

    public void setConfirmationScript(String script);

    public String getConfirmationScript();
}
