/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.cache;

/**
 *
 * @author r.hendrick
 */
public interface CacheProvider {

    public Object get(String region, String key);

    public void put(String region, String key, Object object);

    public void remove(String region, String key);

    public void clear();
}
