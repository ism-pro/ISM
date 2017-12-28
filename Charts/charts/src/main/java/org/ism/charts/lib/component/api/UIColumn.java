/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.api;

import java.io.IOException;
import java.util.List;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ism.charts.lib.component.celleditor.CellEditor;

/**
 *
 * @author r.hendrick
 */
public interface UIColumn {

    public ValueExpression getValueExpression(String property);

    public String getContainerClientId(FacesContext context);

    public String getColumnKey();

    public String getClientId();

    public String getClientId(FacesContext context);

    public String getSelectionMode();

    public boolean isResizable();

    public String getStyle();

    public String getStyleClass();

    public int getRowspan();

    public int getColspan();

    public String getFilterPosition();

    public UIComponent getFacet(String facet);

    public Object getFilterBy();

    public Object getFilterValue();

    public String getHeaderText();

    public String getFooterText();

    public String getFilterStyleClass();

    public String getFilterStyle();

    public String getFilterMatchMode();

    public int getFilterMaxLength();

    public Object getFilterOptions();

    public CellEditor getCellEditor();

    public boolean isDynamic();

    public MethodExpression getSortFunction();

    public Object getSortBy();

    public List<UIComponent> getChildren();

    public boolean isExportable();

    public boolean isRendered();

    public void encodeAll(FacesContext context) throws IOException;

    public void renderChildren(FacesContext context) throws IOException;

    public String getWidth();

    public boolean isToggleable();

    public MethodExpression getFilterFunction();

    public String getField();

    public int getPriority();

    public boolean isSortable();

    public boolean isFilterable();

    public boolean isVisible();

    public boolean isSelectRow();

    public String getAriaHeaderText();

    public MethodExpression getExportFunction();

    public boolean isGroupRow();
}
