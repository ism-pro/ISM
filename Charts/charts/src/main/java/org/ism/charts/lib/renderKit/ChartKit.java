package org.ism.charts.lib.renderKit;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import org.ism.charts.lib.component.axis.XAxisRenderer;
import org.ism.charts.lib.component.axis.YAxisRenderer;
import org.ism.charts.lib.component.chart.Chart;
import org.ism.charts.lib.component.details.SubTitleRenderer;
import org.ism.charts.lib.component.details.TitleRenderer;
import org.ism.charts.lib.component.plots.ChartSetRenderer;
import org.ism.charts.lib.component.series.LegendRenderer;
import org.ism.charts.lib.component.series.PlotOptionsRenderer;
import org.ism.charts.lib.component.series.SeriesRenderer;
import org.ism.charts.lib.component.series.ToolTipRenderer;
import org.ism.charts.lib.model.ChartModel;
import org.ism.charts.lib.model.properties.ChartType;

@FacesRenderer(componentFamily = Chart.COMPONENT_FAMILY,
        rendererType = Chart.RENDERER_TYPE)
public class ChartKit extends CoreRenderer {

    /**
     * <p>
     * Decode the incoming request parameters</p>
     *
     * @param context <code>FaceContext</code> current context
     * @param component  <code>UIComponent</code> current component
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        super.decode(context, component);
    }

    /**
     * <p>
     * Encode the beginning of this component.</p>
     *
     * @param context <code>FacesContext</code>for the current request
     * @param component <code>UIComponent</code> current component
     * @throws IOException if an I/O error occurs during rendering
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        // Write resources
        super.encodeBegin(context, component);
        encodeResources(context, component);

        // Can't start rendering if not a renderer
        if (!component.isRendered()) {
            System.out.println(this.getClass().getName() + " is not a renderer");
            return;
        }

        // Get Writer
        ResponseWriter writer = context.getResponseWriter();

        // Get Parameters
        Chart chart = (Chart) component;
        String style = chart.getStyle();
        String styleClass = chart.getStyleClass();

        writer.startElement("div", null);
        writer.writeAttribute("id", chart.getClientId(context), null);
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }
        writer.endElement("div");

    }

    /**
     * <p>
     * Encode the children of this component.</p>
     *
     * @param context <code>FacesContext</code>for the current request
     * @param component  <code>UIComponent</code> current component
     * @throws IOException if an I/O error occurs during rendering
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if ((context == null) || (component == null)) {
            throw new NullPointerException();
        }
        // Can't start rendering if not a renderer
        if (!component.isRendered()) {
            return;
        }
    }

    /**
     * <p>
     * Encode the ending of this component.</p>
     *
     * @param context <code>FacesContext</code>for the current request
     * @param component  <code>UIComponent</code> current component
     * @throws IOException if an I/O error occurs during rendering
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        encodeScript(context, component);
    }

    /**
     * <p>
     * Method manually encoded resources. In particular, it add specified
     * functionnally used. The main highchart library is already include while
     * calling this component as well as requeried library JQuery and specic
     * resources of the component.
     * </p>
     *
     * @param context <code>FaceContext</code> current context
     * @param component  <code>UIComponent</code> current component
     * @throws IOException exception
     */
    @Override
    protected void encodeResources(FacesContext context, UIComponent component) throws IOException {
        Chart chart =(Chart) component;
        // Style
        //writeStyleResource(context, "/projsf-ch3/showOneDeck.css");

        // Script      
        if (chart.getEnabledJQuery()) {
            writeScriptResource(context, context.getExternalContext().getRequestServletPath() + "/vendor/jquery/3.1.1/js/jquery.min.js");
        }
        writeScriptResource(context, context.getExternalContext().getRequestServletPath() + "/vendor/highcharts/6.0.4/code/highcharts.js");
        if (chart.isExporting()) {
            writeScriptResource(context, context.getExternalContext().getRequestServletPath() + "/vendor/highcharts/6.0.4/code/modules/exporting.js");
        }

    }

    /**
     *
     * @param context <code>FaceContext</code> current context
     * @param component  <code>UIComponent</code> current component
     * @throws IOException exception
     */
    protected void encodeScript(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        Chart chart = (Chart) component;
        String clientId = chart.getClientId(context);

        ChartModel model = chart.getModel();
        if (model == null) {
            return;
        }

        String type = chart.getType() != null ? chart.getType() : model.getChart().getType().toString();
        model.getChart().setType(ChartType.convert(type));
        model.getChart().setRenderTo(clientId);

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

    }

}
