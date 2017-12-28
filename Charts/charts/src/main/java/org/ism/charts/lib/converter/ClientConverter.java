/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.converter;

import java.util.Map;

/**
 *
 * @author r.hendrick
 */
public interface ClientConverter {

    public Map<String, Object> getMetadata();

    public String getConverterId();
}
