/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.model.menu;

import java.util.List;
import java.util.Map;
import org.ism.charts.lib.component.api.Confirmable;

/**
 *
 * @author r.hendrick
 */
public interface MenuItem extends MenuElement, Confirmable {

    public String getIcon();

    public String getIconPos();

    public String getTitle();

    public boolean shouldRenderChildren();

    public boolean isDisabled();

    public String getOnclick();

    public String getStyle();

    public String getStyleClass();

    public String getUrl();

    public String getTarget();

    public String getOutcome();

    public String getFragment();

    public boolean isIncludeViewParams();

    public boolean isAjax();

    public Object getValue();

    public void setStyleClass(String styleClass);

    public Map<String, List<String>> getParams();

    public void setParam(String key, Object value);

    public boolean isDynamic();

    public String getCommand();

    public boolean isImmediate();

    public String getClientId();

    public String getContainerStyle();

    public String getContainerStyleClass();

    public boolean isEscape();

    public String getRel();

}
