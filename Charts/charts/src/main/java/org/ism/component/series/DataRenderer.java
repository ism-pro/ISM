/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.component.series;

import java.util.ArrayList;
import java.util.List;
import org.ism.component.properties.DataLabelsRenderer;
import org.ism.component.properties.EventsRenderer;
import org.ism.model.properties.DataLabels;
import org.ism.model.properties.Events;
import org.ism.model.series.Data;
import org.ism.model.series.Marker;
import org.ism.util.Util;

/**
 *
 * @author r.hendrick
 */
public class DataRenderer {

    static public String renderer(Data dataSet, Boolean first) {
        String data = "";
        // Exclude Null pointer
        if (dataSet == null) {
            //Util.out("Alarm *** DataRenderer : ", "dataSet is null, return empty string");
            return data;
        }

        data += open(first);
        data += className(dataSet.getClassName());
        data += color(dataSet.getColor());
        data += colorIndex(dataSet.getColorIndex());
        data += datas(dataSet.getDatas());
        data += dataLabels(dataSet.getDataLabels(), isFirst(data));
        data += description(dataSet.getDescription());
        data += drilldown(dataSet.getDrilldown());
        data += events(dataSet.getEvents(), isFirst(data));
        data += id(dataSet.getId());
        data += labelrank(dataSet.getLabelrank());
        data += legendIndex(dataSet.getLegendIndex());
        data += marker(dataSet.getMarker(), isFirst(data));
        data += name(dataSet.getName());
        data += selected(dataSet.getSelected());
        data += sliced(dataSet.getSliced());
        data += x(dataSet.getX());
        data += y(dataSet.getY());

        data = close(data);
        return data;
    }

    static public String renderer(Data dataSet) {
        return renderer(dataSet, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "{" : ",{";
    }

    static private String close(String data) {
        if (data.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        data = data.substring(0, data.length() - 1);
        data += "}";
        return data;
    }

    static private Boolean isFirst(String states) {
        return states.endsWith(",");
    }

    static private String className(String className) {
        if (className != null) {
            return "className:'" + className + "',";
        }
        return "";
    }

    static private String color(String color) {
        if (color != null) {
            return "color:'" + color + "',";
        }
        return "";
    }

    static private String colorIndex(Integer colorIndex) {
        if (colorIndex != null) {
            return "colorIndex:" + colorIndex + ",";
        }
        return "";
    }

    static private String datas(List<?> datas) {
        if (datas == null || datas.isEmpty()) {
            return "";
        }
        if (datas.get(0) instanceof Double) {
            String str = "data:[" + (Double) datas.get(0);
            for (int i = 1; i < datas.size(); i++) {
                str += "," + (Double) datas.get(i);
            }
            str += "],";
            return str;
        } else if (datas.get(0) instanceof Integer) {
            String str = "data:[" + (Integer) datas.get(0);
            for (int i = 1; i < datas.size(); i++) {
                str += "," + (Integer) datas.get(i);
            }
            str += "],";
            return str;
        } else if (datas.get(0) instanceof ArrayList){
            return datas((List<Object>) datas.get(0));
        }else {
            Util.out("DataRenderer >> unknow data type");
        }
        return "";
    }

    static private String dataLabels(DataLabels dataLabels, Boolean first) {
        if (dataLabels != null) {
            String tmp = DataLabelsRenderer.renderer(dataLabels, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String description(String description) {
        if (description != null) {
            return "description:'" + description + "',";
        }
        return "";
    }

    static private String drilldown(String drilldown) {
        if (drilldown != null) {
            return "drilldown:'" + drilldown + "',";
        }
        return "";
    }

    static private String events(Events events, Boolean first) {
        if (events != null) {
            String tmp = EventsRenderer.renderer(events, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String id(String id) {
        if (id != null) {
            return "id:'" + id + "',";
        }
        return "";
    }

    static private String labelrank(Integer labelrank) {
        if (labelrank != null) {
            return "labelrank:" + labelrank + ",";
        }
        return "";
    }

    static private String legendIndex(Integer legendIndex) {
        if (legendIndex != null) {
            return "legendIndex:" + legendIndex + ",";
        }
        return "";
    }

    static private String marker(Marker marker, Boolean first) {
        if (marker != null) {
            String tmp = MarkerRenderer.renderer(marker, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String name(String name) {
        if (name != null) {
            return "name:'" + name + "',";
        }
        return "";
    }

    static private String selected(Boolean selected) {
        if (selected != null) {
            return "selected:" + selected + ",";
        }
        return "";
    }

    static private String sliced(Boolean sliced) {
        if (sliced != null) {
            return "sliced:" + sliced + ",";
        }
        return "";
    }

    static private String x(Integer x) {
        if (x != null) {
            return "x:" + x + ",";
        }
        return "";
    }

    static private String y(Object y) {
        if (y != null) {
            return "y:" + y + ",";
        }
        return "";
    }

}
