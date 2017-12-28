/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.api;

import java.util.Map;
import javax.faces.event.BehaviorEvent;

/**
 *
 * @author r.hendrick
 */
public interface IChartsClientBehaviorHolder {

    Map<String, Class<? extends BehaviorEvent>> getBehaviorEventMapping();
}
