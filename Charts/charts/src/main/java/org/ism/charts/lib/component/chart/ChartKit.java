package org.ism.charts.lib.component.chart;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import org.ism.charts.lib.component.axis.XAxisRenderer;
import org.ism.charts.lib.component.axis.YAxisRenderer;
import org.ism.charts.lib.component.details.SubTitleRenderer;
import org.ism.charts.lib.component.details.TitleRenderer;
import org.ism.charts.lib.component.plots.ChartSetRenderer;
import org.ism.charts.lib.component.series.LegendRenderer;
import org.ism.charts.lib.component.series.PlotOptionsRenderer;
import org.ism.charts.lib.component.series.SeriesRenderer;
import org.ism.charts.lib.component.series.ToolTipRenderer;
import org.ism.charts.lib.model.ChartModel;
import org.ism.charts.lib.model.json.Options;
import org.ism.charts.lib.model.properties.ChartType;
import org.ism.charts.lib.renderKit.CoreRenderer;
import org.ism.charts.lib.util.WidgetBuilder;

@FacesRenderer(componentFamily = Chart.COMPONENT_FAMILY,
        rendererType = Chart.RENDERER_TYPE)
public class ChartKit extends org.primefaces.renderkit.CoreRenderer {

    /**
     * <p>
     * Decode the incoming request parameters</p>
     *
     * @param context <code>FaceContext</code> current context
     * @param component  <code>UIComponent</code> current component
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        Chart chart = (Chart) component;
        String clientId = chart.getClientId(context);
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        String selectedSerieIdParam = params.get(clientId + "_selectedSerieId");
        if (selectedSerieIdParam != null) {
            if (!selectedSerieIdParam.trim().replace("null", "").isEmpty()) {
                chart.setSelectedSerieId(Integer.valueOf(selectedSerieIdParam));
            }
        }

        //Restore toggle state
        String selectedSerieParam = params.get(clientId + "_selectedSerieOptions");
        if (selectedSerieIdParam != null) {
            //chart.getModel().getSeries().valueOf(selectedSerieParam);
            chart.setSelectedSerieOptions(unparseObj(chart, selectedSerieParam));
        }

        //Restore visibility state
        String visibleParam = params.get(clientId + "_visible");
        if (visibleParam != null) {
            //chart.setVisible(Boolean.valueOf(visibleParam));
        }

        decodeBehaviors(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        Chart chart = (Chart) component;

        encodeMarkup(context, chart);
        encodeScript(context, chart);
    }

    protected void encodeMarkup(FacesContext context, UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }

        // Can't start rendering if not a renderer
        if (!component.isRendered()) {
            System.out.println(this.getClass().getName() + " is not a renderer");
            return;
        }

        // Get Parameters
        Chart chart = (Chart) component;
        String style = chart.getStyle();
        String styleClass = chart.getStyleClass();
        String clientId = chart.getClientId(context);

        // Get Writer
        ResponseWriter writer = context.getResponseWriter();

        //
        // Managing main panel
        writer.startElement("div", null);
        writer.writeAttribute("id", clientId + "-panel", null);
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
        writer.writeAttribute("class", Chart.CHART_PANEL, "class");

        //
        // Managing chart container
        writer.startElement("div", null);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("class", Chart.CHART_CONTAINER + " " + (styleClass != null ? styleClass : ""), "class");
        writer.endElement("div");

        // End chart panel
        writer.endElement("div");

        // 
        encodeStateHolder(context, chart, clientId + "_selectedSerieId", String.valueOf(chart.getSelectedSerieId()));
        encodeStateHolder(context, chart, clientId + "_selectedSerieOptions", String.valueOf(chart.getSelectedSerieOptions()));
    }

    /**
     *
     * @param context <code>FaceContext</code> current context
     * @param chart  <code>UIComponent</code> current component
     * @throws IOException exception
     */
    protected void encodeScript(FacesContext context, Chart chart) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        String clientId = chart.getClientId(context);

        ChartModel model = chart.getModel();
        if (model == null) {
            return;
        }

        // Manage Prioriority Options on Property Options
        String type = chart.getType() != null ? chart.getType() : model.getChart().getType().toString();
        model.getChart().setType(ChartType.convert(type));
        model.getChart().setRenderTo(clientId);
        model.getChart().setZommType(chart.getZoomType() != null ? chart.getZoomType() : model.getChart().getZommType());

        String script = "var options={";
        script += ChartSetRenderer.renderer(model.getChart());
        script += TitleRenderer.renderer(model.getTitle());
        script += SubTitleRenderer.renderer(model.getSubTitle());
        script += XAxisRenderer.renderer(model.getxAxis());
        script += YAxisRenderer.renderer(model.getyAxis());
        script += ToolTipRenderer.renderer(model.getToolTip());
        script += PlotOptionsRenderer.renderer(model.getPlotOptions());
        script += LegendRenderer.renderer(model.getLegend());
        script += SeriesRenderer.renderer(model.getSeries());
        script += "};";
        script += "var chart_" + clientId.replace(":", "_") + " = new Highcharts.Chart(options);";

        if (chart.isDebug()) {
            System.out.println(script);
        }

        writer.startElement("script", null);
        writer.write("$(document).ready(function() {");
        // script code
        writer.write(script);
        writer.write("});");
        writer.endElement("script");
//        

//        WidgetBuilder wb = new WidgetBuilder(context);
//        writer.write("IChartsFaces.cw(\"");
//        writer.write("Chart");
//        writer.write("\",\"");
//        writer.write(chart.resolveWidgetVar());
//        writer.write("\",{");
//        writer.write("id:\"");
//        writer.write(clientId);
//        writer.write("\"");
//        encodeClientBehaviors(context, chart);
//        writer.write("}");
//        writer.write(");");
//        writer.write("});");
//        writer.endElement("script");
        org.primefaces.util.WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("Chart", chart.resolveWidgetVar(), clientId);
        encodeClientBehaviors(context, chart);
        wb.finish();
    }

    protected void encodeStateHolder(FacesContext context, Chart chart, String name, String value) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("input", null);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("id", name, null);
        writer.writeAttribute("name", name, null);
        writer.writeAttribute("value", value, null);
        writer.endElement("input");
    }

    /**
     * Unparse content object
     *
     * @param selectedSerie JSON Object
     */
    public Options unparseObj(Chart chart, String selectedSerie) {
        Gson g = new Gson();
        //Util.out("Serie : " + selectedSerie);
        Options options = g.fromJson(selectedSerie, Options.class);
        return options;
    }
}
