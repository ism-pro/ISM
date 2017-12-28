/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.api;

import java.util.Collection;

/**
 * InputHolder is implemented by components that have both obstrusive and
 * non-obstrusive client behavior events
 */
public interface MixedClientBehaviorHolder {

    public Collection<String> getUnobstrusiveEventNames();
}
