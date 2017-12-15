/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.charts.lib.component.axis;

import java.util.List;
import org.ism.charts.lib.model.axis.AxisTitle;
import org.ism.charts.lib.model.axis.AxisType;
import org.ism.charts.lib.model.axis.Breaks;
import org.ism.charts.lib.model.axis.Crosshair;
import org.ism.charts.lib.model.axis.Labels;
import org.ism.charts.lib.model.axis.PlotBands;
import org.ism.charts.lib.model.axis.PlotLines;
import org.ism.charts.lib.model.axis.StackLabels;
import org.ism.charts.lib.model.axis.YAxis;
import org.ism.charts.lib.model.properties.Align;
import org.ism.charts.lib.model.properties.DashStyle;
import org.ism.charts.lib.util.Util;

/**
 *
 * @author r.hendrick
 */
public class YAxisRenderer {

    /**
     * Render YAxis starting with "," or not depending on first
     *
     * @param yAxisModel model which define YAxis
     * @param first allow to prepend or not with ","
     * @return Corresponding string of javascript
     */
    static public String renderer(YAxis yAxisModel, Boolean first) {
        String yAxis = "";
        // Exclude Null pointer
        if (yAxisModel == null) {
            //Util.out("Alarm *** YAxisRenderer : ", "yAxisModel is null, return empty string");
            return yAxis;
        }

        yAxis += open(first);
        yAxis += allowDecimals(yAxisModel.getAllowDecimals());
        yAxis += alternateGridColor(yAxisModel.getAlternateGridColor());
        yAxis += angle(yAxisModel.getAngle());
        yAxis += breaks(yAxisModel.getBreaks(), isFirst(yAxis));
        yAxis += categories(yAxisModel.getCategories());
        yAxis += ceiling(yAxisModel.getCeiling());
        yAxis += className(yAxisModel.getClassName());
        yAxis += crosshair(yAxisModel.getCrosshair(), isFirst(yAxis));
        yAxis += dateTimeLabelFormats(yAxisModel.getDateTimeLabelFormats());
        yAxis += description(yAxisModel.getDescription());
        yAxis += endOnTick(yAxisModel.getEndOnTick());
        yAxis += floor(yAxisModel.getFloor());
        yAxis += gridLineColor(yAxisModel.getGridLineColor());
        yAxis += gridLineDashStyle(yAxisModel.getGridLineDashStyle());
        yAxis += gridLineInterpolation(yAxisModel.getGridLineInterpolation());
        yAxis += gridLineWidth(yAxisModel.getGridLineWidth());
        yAxis += gridZIndex(yAxisModel.getGridZIndex());
        yAxis += id(yAxisModel.getId());
        yAxis += labels(yAxisModel.getLabels(), isFirst(yAxis));
        yAxis += lineColor(yAxisModel.getLineColor());
        yAxis += lineWidth(yAxisModel.getLineWidth());
        yAxis += linkedTo(yAxisModel.getLinkedTo());
        yAxis += max(yAxisModel.getMax());
        yAxis += maxColor(yAxisModel.getMaxColor());
        yAxis += maxPadding(yAxisModel.getMaxPadding());
        yAxis += min(yAxisModel.getMin());
        yAxis += minColor(yAxisModel.getMinColor());
        yAxis += minPadding(yAxisModel.getMinPadding());
        yAxis += minRange(yAxisModel.getMinRange());
        yAxis += minTickInterval(yAxisModel.getMinTickInterval());
        yAxis += minorGridLineColor(yAxisModel.getMinorGridLineColor());
        yAxis += minorGridLineDashStyle(yAxisModel.getMinorGridLineDashStyle());
        yAxis += minorGridLineWidth(yAxisModel.getMinorGridLineWidth());
        yAxis += minorTickColor(yAxisModel.getMinorTickColor());
        yAxis += minorTickInterval(yAxisModel.getMinorTickInterval());
        yAxis += minorTickLength(yAxisModel.getMinorTickLength());
        yAxis += minorTickPosition(yAxisModel.getMinorTickPosition());
        yAxis += minorTickWidth(yAxisModel.getMinorTickWidth());
        yAxis += offset(yAxisModel.getOffset());
        yAxis += opposite(yAxisModel.getOpposite());
        yAxis += plotBands(yAxisModel.getPlotBands(), isFirst(yAxis));
        yAxis += plotLines(yAxisModel.getPlotLines(), isFirst(yAxis));
        yAxis += reversed(yAxisModel.getReversed());
        yAxis += reversedStacks(yAxisModel.getReversedStacks());
        yAxis += showEmpty(yAxisModel.getShowEmpty());
        yAxis += showFirstLabel(yAxisModel.getShowFirstLabel());
        yAxis += showLastLabel(yAxisModel.getShowLastLabel());
        yAxis += softMax(yAxisModel.getSoftMax());
        yAxis += softMin(yAxisModel.getSoftMin());
        yAxis += stackLabels(yAxisModel.getStackLabels(), isFirst(yAxis));
        yAxis += startOfWeek(yAxisModel.getStartOfWeek());
        yAxis += startOnTick(yAxisModel.getStartOnTick());
        yAxis += tickAmount(yAxisModel.getTickAmount());
        yAxis += tickColor(yAxisModel.getTickColor());
        yAxis += tickInterval(yAxisModel.getTickInterval());
        yAxis += tickLength(yAxisModel.getTickLength());
        yAxis += tickPixelInterval(yAxisModel.getTickPixelInterval());
        yAxis += tickPosition(yAxisModel.getTickPosition());
        yAxis += tickPositions(yAxisModel.getTickPositions());
        yAxis += tickWidth(yAxisModel.getTickWidth());
        yAxis += tickmarkPlacement(yAxisModel.getTickmarkPlacement());
        yAxis += title(yAxisModel.getTitle(), isFirst(yAxis));
        yAxis += type(yAxisModel.getType());
        yAxis += uniqueNames(yAxisModel.getUniqueNames());
        yAxis += units(yAxisModel.getUnits());
        yAxis += visible(yAxisModel.getVisible());

        yAxis = close(yAxis);
        return yAxis;
    }

    /**
     * Render YAxis starting default with ","
     *
     * @param yAxisModel model which define YAxis
     * @return Corresponding string of javascript
     */
    static public String renderer(YAxis yAxisModel) {
        return renderer(yAxisModel, Boolean.FALSE);
    }

    static private String open(Boolean first) {
        return first ? "yAxis:{" : ",yAxis:{";
    }

    static private String close(String yAxis) {
        if (yAxis.length() <= open(Boolean.FALSE).length()) {
            return "";
        }
        // Remove last ","
        yAxis = yAxis.substring(0, yAxis.length() - 1);
        yAxis += "}";
        return yAxis;
    }

    static private Boolean isFirst(String yAxis) {
        return yAxis.endsWith(",") || yAxis.endsWith("{") || yAxis.endsWith("[");
    }

    /// ////////////////////////////////////////////////////////////////////////
    /// 
    /// ////////////////////////////////////////////////////////////////////////
    static private String allowDecimals(Boolean allowDecimals) {
        if (allowDecimals != null && allowDecimals != true) {
            return "allowDecimals:" + allowDecimals + ",";
        }
        return "";
    }

    static private String alternateGridColor(String alternateGridColor) {
        if (alternateGridColor != null && alternateGridColor.trim().length() > 0) {
            return "alternateGridColor:'" + alternateGridColor + "',";
        }
        return "";
    }

    static private String angle(Integer angle) {
        if (angle != null) {
            return "angle:" + angle + ",";
        }
        return "";
    }

    static private String breaks(Breaks breaks, Boolean first) {
        if (breaks != null) {
            String tmp = BreaksRenderer.renderer(breaks, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String categories(List<String> categories) {
        if (categories != null && !categories.isEmpty()) {
            String str = "categories:['" + categories.get(0) + "'";
            for (int i = 1; i < categories.size(); i++) {
                str += ",'" + categories.get(i) + "'";
            }
            str += "],";
            return str;
        }
        return "";
    }

    static private String ceiling(Integer ceiling) {
        if (ceiling != null) {
            return "ceiling:" + ceiling + ",";
        }
        return "";
    }

    static private String className(String className) {
        if (className != null && className.trim().length() > 0) {
            return "className:'" + className + "',";
        }
        return "";
    }

    static private String crosshair(Crosshair crosshair, Boolean first) {
        if (crosshair != null) {
            String tmp = CrosshairRenderer.renderer(crosshair, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String dateTimeLabelFormats(String dateTimeLabelFormats) {
        if (dateTimeLabelFormats != null && dateTimeLabelFormats.trim().length() > 0) {
            return "dateTimeLabelFormats:\"" + dateTimeLabelFormats + "\",";
        }
        return "";
    }

    static private String description(String description) {
        if (description != null && description.trim().length() > 0) {
            return "description:'" + description + "',";
        }
        return "";
    }

    static private String endOnTick(Boolean endOnTick) {
        if (endOnTick != null && endOnTick != true) {
            return "endOnTick:" + endOnTick + ",";
        }
        return "";
    }

    static private String floor(Integer floor) {
        if (floor != null) {
            return "floor:" + floor + ",";
        }
        return "";
    }

    static private String gridLineColor(String gridLineColor) {
        if (gridLineColor != null && gridLineColor.trim().length() > 0 && !gridLineColor.matches("#e6e6e6")) {
            return "gridLineColor:'" + gridLineColor + "',";
        }
        return "";
    }

    static private String gridLineDashStyle(DashStyle gridLineDashStyle) {
        if (gridLineDashStyle != null && gridLineDashStyle != DashStyle.SOLID) {
            return "gridLineDashStyle:'" + gridLineDashStyle + "',";
        }
        return "";
    }

    static private String gridLineInterpolation(String gridLineInterpolation) {
        if (gridLineInterpolation != null && gridLineInterpolation.trim().length() > 0) {
            return "gridLineInterpolation:'" + gridLineInterpolation + "',";
        }
        return "";
    }

    static private String gridLineWidth(Integer gridLineWidth) {
        if (gridLineWidth != null && gridLineWidth != 1) {
            return "gridLineWidth:" + gridLineWidth + ",";
        }
        return "";
    }

    static private String gridZIndex(Integer gridZIndex) {
        if (gridZIndex != null && gridZIndex != 1) {
            return "gridZIndex:" + gridZIndex + ",";
        }
        return "";
    }

    static private String id(String id) {
        if (id != null && id.trim().length() > 0) {
            return "id:'" + id + "',";
        }
        return "";
    }

    static private String labels(Labels labels, Boolean first) {
        if (labels != null) {
            return LabelsRenderer.renderer(labels, first) + ",";
        }
        return "";
    }

    static private String lineColor(String lineColor) {
        if (lineColor != null && lineColor.trim().length() > 0 && !lineColor.matches("#ccd6eb")) {
            return "lineColor:'" + lineColor + "',";
        }
        return "";
    }

    static private String lineWidth(Integer lineWidth) {
        if (lineWidth != null && lineWidth != 0) {
            return "lineWidth:" + lineWidth + ",";
        }
        return "";
    }

    static private String linkedTo(Integer linkedTo) {
        if (linkedTo != null) {
            return "linkedTo:" + linkedTo + ",";
        }
        return "";
    }

    static private String max(Integer max) {
        if (max != null) {
            return "max:" + max + ",";
        }
        return "";
    }

    static private String maxColor(String maxColor) {
        if (maxColor != null && maxColor.trim().length() > 0 && !maxColor.contains("003399")) {
            return "maxColor:'" + maxColor + "',";
        }
        return "";
    }

    static private String maxPadding(Double maxPadding) {
        if (maxPadding != null && maxPadding != 0.01) {
            return "maxPadding:" + maxPadding + ",";
        }
        return "";
    }

    static private String min(Integer min) {
        if (min != null) {
            return "min:" + min + ",";
        }
        return "";
    }

    static private String minColor(String minColor) {
        if (minColor != null && minColor.trim().length() > 0 && !minColor.contains("e6ebf5")) {
            return "minColor:'" + minColor + "',";
        }
        return "";
    }

    static private String minPadding(Double minPadding) {
        if (minPadding != null && minPadding != 0.05) {
            return "minPadding:" + minPadding + ",";
        }
        return "";
    }

    static private String minRange(Integer minRange) {
        if (minRange != null) {
            return "minRange:" + minRange + ",";
        }
        return "";
    }

    static private String minTickInterval(Integer minTickInterval) {
        if (minTickInterval != null) {
            return "minTickInterval:" + minTickInterval + ",";
        }
        return "";
    }

    static private String minorGridLineColor(String minorGridLineColor) {
        if (minorGridLineColor != null && minorGridLineColor.trim().length() > 0 && !minorGridLineColor.matches("#f2f2f2")) {
            return "minorGridLineColor:'" + minorGridLineColor + "',";
        }
        return "";
    }

    static private String minorGridLineDashStyle(DashStyle minorGridLineDashStyle) {
        if (minorGridLineDashStyle != null && minorGridLineDashStyle != DashStyle.SOLID) {
            return "minorGridLineDashStyle:'" + minorGridLineDashStyle + "',";
        }
        return "";
    }

    static private String minorGridLineWidth(Integer minorGridLineWidth) {
        if (minorGridLineWidth != null && minorGridLineWidth != 1) {
            return "minorGridLineWidth:" + minorGridLineWidth + ",";
        }
        return "";
    }

    static private String minorTickColor(String minorTickColor) {
        if (minorTickColor != null && minorTickColor.trim().length() > 0 && !minorTickColor.matches("#999999")) {
            return "minorTickColor:'" + minorTickColor + "',";
        }
        return "";
    }

    static private String minorTickInterval(String minorTickInterval) {
        if (minorTickInterval != null && minorTickInterval.trim().length() > 0) {
            return "minorTickInterval:'" + minorTickInterval + "',";
        }
        return "";
    }

    static private String minorTickLength(Integer minorTickLength) {
        if (minorTickLength != null && minorTickLength != 2) {
            return "minorTickLength:" + minorTickLength + ",";
        }
        return "";
    }

    static private String minorTickPosition(Align minorTickPosition) {
        if (minorTickPosition != null && minorTickPosition != Align.OUTSIDE) {
            return "minorTickPosition:'" + minorTickPosition + "',";
        }
        return "";
    }

    static private String minorTickWidth(Integer minorTickWidth) {
        if (minorTickWidth != null && minorTickWidth != 0) {
            return "minorTickWidth:" + minorTickWidth + ",";
        }
        return "";
    }

    static private String offset(Integer offset) {
        if (offset != null && offset != 0) {
            return "offset:" + offset + ",";
        }
        return "";
    }

    static private String opposite(Boolean opposite) {
        if (opposite != null && opposite != false) {
            return "opposite:" + opposite + ",";
        }
        return "";
    }

    static private String plotBands(PlotBands plotBands, Boolean first) {
        if (plotBands != null) {
            String tmp = PlotBandsRenderer.renderer(plotBands, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String plotLines(PlotLines plotLines, Boolean first) {
        if (plotLines != null) {
            String tmp = PlotLinesRenderer.renderer(plotLines, first);
            return tmp + (!tmp.isEmpty() ? ","  : "");
        }
        return "";
    }

    static private String reversed(Boolean reversed) {
        if (reversed != null && reversed != false) {
            return "reversed:" + reversed + ",";
        }
        return "";
    }

    static private String reversedStacks(Boolean reversedStacks) {
        if (reversedStacks != null && reversedStacks != true) {
            return "reversedStacks:" + reversedStacks + ",";
        }
        return "";
    }

    static private String showEmpty(Boolean showEmpty) {
        if (showEmpty != null && showEmpty != true) {
            return "showEmpty:" + showEmpty + ",";
        }
        return "";
    }

    static private String showFirstLabel(Boolean showFirstLabel) {
        if (showFirstLabel != null && showFirstLabel != true) {
            return "showFirstLabel:" + showFirstLabel + ",";
        }
        return "";
    }

    static private String showLastLabel(Boolean showLastLabel) {
        if (showLastLabel != null && showLastLabel != true) {
            return "showLastLabel:" + showLastLabel + ",";
        }
        return "";
    }

    static private String softMax(Integer softMax) {
        if (softMax != null) {
            return "softMax:" + softMax + ",";
        }
        return "";
    }

    static private String softMin(Integer softMin) {
        if (softMin != null) {
            return "softMin:" + softMin + ",";
        }
        return "";
    }

    static private String stackLabels(StackLabels stackLabels, Boolean first) {
        if (stackLabels != null) {
            String tmp = StackLabelsRenderer.renderer(stackLabels, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String startOfWeek(Integer startOfWeek) {
        if (startOfWeek != null && startOfWeek != 1) {
            return "startOfWeek:" + startOfWeek + ",";
        }
        return "";
    }

    static private String startOnTick(Boolean startOnTick) {
        if (startOnTick != null && startOnTick != false) {
            return "startOnTick:" + startOnTick + ",";
        }
        return "";
    }

    static private String tickAmount(Integer tickAmount) {
        if (tickAmount != null) {
            return "tickAmount:" + tickAmount + ",";
        }
        return "";
    }

    static private String tickColor(String tickColor) {
        if (tickColor != null && tickColor.trim().length() > 0 && !tickColor.matches("#ccd6eb")) {
            return "tickColor:'" + tickColor + "',";
        }
        return "";
    }

    static private String tickInterval(Integer tickInterval) {
        if (tickInterval != null) {
            return "tickInterval:" + tickInterval + ",";
        }
        return "";
    }

    static private String tickLength(Integer tickLength) {
        if (tickLength != null && tickLength != 10) {
            return "tickLength:" + tickLength + ",";
        }
        return "";
    }

    static private String tickPixelInterval(Integer tickPixelInterval) {
        if (tickPixelInterval != null) {
            return "tickPixelInterval:" + tickPixelInterval + ",";
        }
        return "";
    }

    static private String tickPosition(Align tickPosition) {
        if (tickPosition != null && tickPosition != Align.OUTSIDE) {
            return "tickPosition:'" + tickPosition + "',";
        }
        return "";
    }

    static private String tickPositions(List<Integer> tickPositions) {
        if (tickPositions != null && !tickPositions.isEmpty()) {
            String str = "tickPositions:['" + tickPositions.get(0) + "";
            for (int i = 1; i < tickPositions.size(); i++) {
                str += "," + tickPositions.get(0) + "";
            }
            str += "],";
            return str;
        }
        return "";
    }

    static private String tickWidth(Integer tickWidth) {
        if (tickWidth != null && tickWidth != 0) {
            return "tickWidth:" + tickWidth + ",";
        }
        return "";
    }

    static private String tickmarkPlacement(Align tickmarkPlacement) {
        if (tickmarkPlacement != null) {
            return "tickmarkPlacement:'" + tickmarkPlacement + "',";
        }
        return "";
    }

    static private String title(AxisTitle title, Boolean first) {
        if (title != null) {
            String tmp = AxisTitleRenderer.renderer(title, first);
            return tmp + (!tmp.isEmpty() ? "," : "");
        }
        return "";
    }

    static private String type(AxisType type) {
        if (type != null && type != AxisType.LINEAR) {
            return "type:'" + type + "',";
        }
        return "";
    }

    static private String uniqueNames(Boolean uniqueNames) {
        if (uniqueNames != null && uniqueNames != true) {
            return "uniqueNames:" + uniqueNames + ",";
        }
        return "";
    }

    static private String units(String units) {
        if (units != null && units.trim().length() > 0) {
            return "units:\"" + units + "\",";
        }
        return "";
    }

    static private String visible(Boolean visible) {
        if (visible != null && visible != true) {
            return "visible:" + visible + ",";
        }
        return "";
    }

}
