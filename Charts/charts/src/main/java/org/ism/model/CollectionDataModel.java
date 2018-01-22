/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model;

import java.util.Collection;
import javax.faces.model.DataModel;
import javax.faces.model.DataModelEvent;
import javax.faces.model.DataModelListener;

/**
 *
 * @author r.hendrick
 * @param <E> Element
 */
public class CollectionDataModel<E> extends DataModel<E> {

    private int index = -1;
    private Collection<E> wrapped;
    private E[] wrappedArray;

    public CollectionDataModel() {
        this(null);
    }

    public CollectionDataModel(Collection<E> collection) {
        super();
        setWrappedData(collection);
    }

    @Override
    public int getRowCount() {
        if (wrappedArray == null) {
            return -1;
        }

        return wrappedArray.length;
    }

    @Override
    public E getRowData() {
        if (wrappedArray == null) {
            return null;
        } else if (!isRowAvailable()) {
            throw new IllegalArgumentException("No next row available!");
        }

        return wrappedArray[index];
    }

    @Override
    public int getRowIndex() {
        return index;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex < -1) {
            throw new IllegalArgumentException();
        }

        int oldIndex = index;
        index = rowIndex;

        if (wrappedArray == null) {
            return;
        }

        DataModelListener[] listeners = getDataModelListeners();
        if (oldIndex != index && listeners != null) {

            Object rowData = null;
            if (isRowAvailable()) {
                rowData = getRowData();
            }

            DataModelEvent event = new DataModelEvent(this, index, rowData);
            for (DataModelListener listener : listeners) {
                if (listener != null) {
                    listener.rowSelected(event);
                }
            }
        }

    }

    @Override
    public Object getWrappedData() {
        return wrapped;
    }

    @Override
    public void setWrappedData(Object data) {
        if (data == null) {
            wrapped = null;
            wrappedArray = null;
            setRowIndex(-1);
        } else {
            wrapped = (Collection<E>) data;
            wrappedArray = (E[]) new Object[wrapped.size()];
            wrapped.toArray(wrappedArray);
            setRowIndex(0);
        }
    }

    @Override
    public boolean isRowAvailable() {
        if (wrappedArray == null) {
            return false;
        } else if (index >= 0 && index < wrappedArray.length) {
            return true;
        }

        return false;
    }
}
