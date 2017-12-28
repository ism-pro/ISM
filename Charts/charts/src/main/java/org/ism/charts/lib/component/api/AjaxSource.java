/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.api;

/**
 * AjaxSource is the contract that needs to be implemented by components that
 * fully implement all configuration options of PrimeFaces PPR
 */
public interface AjaxSource {

    public String getOnstart();

    public String getOncomplete();

    public String getOnsuccess();

    public String getOnerror();

    public String getUpdate();

    public String getProcess();

    public boolean isGlobal();

    public boolean isAsync();

    public boolean isPartialSubmit();

    public boolean isPartialSubmitSet();

    public String getPartialSubmitFilter();

    public boolean isResetValues();

    public boolean isResetValuesSet();

    public boolean isIgnoreAutoUpdate();

    public boolean isAjaxified();

    public String getDelay();

    public int getTimeout();

    public String getForm();
}
