package org.ism.view.process.ctrl;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.lang.StringEscapeUtils;
import org.ism.jsf.process.ctrl.AnalyseAllowedController;
import org.ism.jsf.process.ctrl.AnalyseDataController;
import org.ism.jsf.process.ctrl.AnalysePointController;
import org.ism.jsf.process.ctrl.AnalyseTypeController;

import org.ism.entities.process.Equipement;
import org.ism.entities.process.ctrl.AnalyseAllowed;
import org.ism.entities.process.ctrl.AnalysePoint;
import org.ism.jsf.util.JsfUtil;
import org.ism.model.ChartModel;
import org.ism.model.axis.PlotLines;
import org.ism.model.details.SubTitle;
import org.ism.model.details.Title;
import org.ism.model.properties.ChartType;
import org.ism.model.properties.DataLabels;
import org.ism.model.series.Data;
import org.ism.model.series.PlotOptions;
import org.ism.model.series.Series;
import org.ism.model.series.ToolTip;
import org.ism.model.series.type.LineSerie;
import org.ism.entities.process.ctrl.AnalyseData;
import org.primefaces.model.LazyDataModel;

/**
 * Manage statistics from resulting data analysis on each sample point. <br >
 * It representing the analysis class for rendering chart in a convenient way.
 *
 * It can generate chart models when @see Class#handleAnalyseSearch is
 * requested.
 *
 * @author r.hendrick
 */
@ManagedBean(name = "viewAnalyseChart")
@SessionScoped
public class ViewAnalyseChart implements Serializable {

    public class ViewAnalyseChartSelect {

        private Equipement equipement = null;
        private List<AnalyseAllowed> alloweds = new ArrayList<>();
        private List<AnalysePoint> points = null;

        public Equipement getEquipement() {
            return equipement;
        }

        public void setEquipement(Equipement equipement) {
            this.equipement = equipement;
        }

        public List<AnalyseAllowed> getAlloweds() {
            if (alloweds == null) {
                alloweds = new ArrayList<>();
            }
            return alloweds;
        }

        public void setAlloweds(List<AnalyseAllowed> alloweds) {
            getAlloweds();
            this.alloweds = alloweds;
        }

        public List<AnalysePoint> getPoints() {
            return points;
        }

        public void setPoints(List<AnalysePoint> points) {
            this.points = points;
        }

    }

    /**
     * Injection of AnalyseAllowedController. It allows to get information and
     * process somes actions to is database.
     */
    @Inject
    AnalyseAllowedController analyseAllowedController;
    @Inject
    AnalyseDataController analyseDataController;
    @Inject
    AnalyseTypeController analyseTypeController;
    @Inject
    AnalysePointController analysePointController;

    /**
     * Specify the selection of all request<br >
     * It is andle equipement, sample point and analyse allowed
     */
    private ViewAnalyseChartSelect selected = null;
    /**
     * Specify the available sample point.<br>
     * It is use to display content of select component and changed while
     * equipement is selected
     */
    private List<AnalysePoint> requestPoints;
    /**
     * Specify the available analyse allowed <br>
     * It is use to display content of select component and changed while sample
     * point is changed
     */
    private List<AnalyseAllowed> analyseAlloweds = new ArrayList<>();
    /**
     * Specify the date from which the analyse has to be made <br>
     * by default this is the first day of the actual month.
     */
    private Date dateFrom;
    /**
     * Specify the date to which the analyse has to be made <br >
     * by default this is the actual day
     */
    private Date dateTo;

    /**
     * Contains all the charts model
     */
    List<ChartModel> models = new ArrayList<>();
    /**
     * Specify current tab to display. <br > Usefull when it is necessary to
     * switch between request and result directly
     */
    private Integer activeIndex = 0;

    /**
     * Specify if user want to keep in memory current request during
     * session.<br>by default nothing is keep in memory but if the user check
     * the box then while preparing nothing is going to be init. This allow to
     * keep everything in memory
     *
     */
    private Boolean keepRequest = false;
    /**
     * Correspond to the elapse time used during creation model process.
     */
    private Long elapseTiming;
    /**
     * Specify the progression of creation model
     */
    private Integer modelProgress = 0;

    private LazyDataModel<AnalyseData> lazyDataModel;

    /**
     * Manage all the injection and default value.
     * <br >Default date From is set on begin of the month.
     *
     */
    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (analyseAllowedController == null) {
            analyseAllowedController = (AnalyseAllowedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "analyseAllowedController");
            analyseAllowedController.prepareCreate();
        }

        // Init. Analyse Point Ctrl if not exist
        if (analysePointController == null) {
            analysePointController = (AnalysePointController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "analysePointController");
            analysePointController.prepareCreate();
        }

        if (analyseDataController == null) {
            analyseDataController = (AnalyseDataController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "analyseDataController");
            analyseDataController.prepareCreate();
        }
        //lazyDataModel = new AnalyseDataLazyModel(analyseDataController.getItemsByLastChanged());

        // Init selection case
        selected = new ViewAnalyseChartSelect();

        // Init request
        requestPoints = analysePointController.getItems();
        dateFrom = new Date();
        LocalDateTime ldtFrom = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1, 0, 0, 0);
        dateFrom = Date.from(ldtFrom.atZone(ZoneId.systemDefault()).toInstant());
        dateTo = new Date();
        keepRequest = false;
        modelProgress = 0;
    }

    /**
     * Manage preparation of the ViewAnalyseChart before using it.
     * <br>Usually call when before showing page via a button on listener.
     */
    public void prepareCreate() {
        if (!keepRequest) {
            selected = new ViewAnalyseChartSelect();
            analyseAlloweds = new ArrayList<>();
            requestPoints = analysePointController.getItems();
            activeIndex = 0;
            models = new ArrayList<>();
            modelProgress = 0;
        }
    }

    /**
     * Manage selections action hover the equipement component selection. When
     * nothing is set from the equipement selector, allow the sample point are
     * available. Otherwise only corresponding sample point link to selected
     * equipement are available.
     */
    public void handleEquipementSelect() {
        if (selected.equipement == null) {
            requestPoints = analysePointController.getItems();
        } else {
            requestPoints
                    = analysePointController.getItemsByEquipement(selected.equipement);
        }
    }

    /**
     * Manage selections action hover the sample point component selection. When
     * nothing is set from the sample point the selection of analysis allowed is
     * fullfill with allow possible analysis. Otherwise only allowed analysis
     * define on a sample point going to be available.
     */
    public void handlePointSelect() {
        if (selected.points == null) {
            analyseAlloweds = analyseAllowedController.getItems();
        } else if (selected.points.isEmpty()) {
            analyseAlloweds = analyseAllowedController.getItems();
        } else {
            analyseAlloweds = new ArrayList<>();
            selected.points.stream().forEach((point) -> {
                List<AnalyseAllowed> aAlloweds = analyseAllowedController.getItemsByPoint(point);
                if (aAlloweds != null) {
                    if (!aAlloweds.isEmpty()) {
                        analyseAlloweds.addAll(aAlloweds);
                    }
                }
            });
        }

    }

    /**
     * Generate all chart models specify by allowed analysis
     * (selected.alloweds). Each chart are generate step by step and put in the
     * models array. <br />
     * Chart models is allways initialize. This mean if nothing was allowed
     * chart models is going to be empty. <br />
     * All chart model arre generate in the same way. Base chart plot is line.
     *
     *
     */
    private void createModels() {
        // Initialize elapse time
        elapseTiming = System.currentTimeMillis();
        // Initialize the model
        models = new ArrayList<>();

        // If no sample point and analysis was allowed then creation finished
        if (selected.alloweds == null) {
            elapseTiming = System.currentTimeMillis() - elapseTiming;
            return;
        } else if (selected.alloweds.isEmpty()) {
            elapseTiming = System.currentTimeMillis() - elapseTiming;
            return;
        }

        // init. Progress status
        int progress = 0;
        Integer fullsize = selected.alloweds.size();
        modelProgress = 0;

        // Loop hover selected chart
        Iterator<AnalyseAllowed> iterAnalyseAllowed = selected.alloweds.iterator();
        while (iterAnalyseAllowed.hasNext()) {
            AnalyseAllowed currentAnalyse = ((AnalyseAllowed) iterAnalyseAllowed.next());
            models.add(createModel(currentAnalyse));

            progress++;
            modelProgress = 100 * (progress / fullsize);

        }

        elapseTiming = System.currentTimeMillis() - elapseTiming;

    }

    private ChartModel createModel(AnalyseAllowed currentAnalyse) {
        ChartModel model = new ChartModel();
        model.getChart().setType(ChartType.LINE);
        model.getChart().setPlotBackgroundCorlor(null);
        model.getChart().setPlotBorderWidth(null);
        model.getChart().setPlotShadow(false);
        model.getChart().setZommType("x");

        // Seutp Title
        model.setTitle(new Title(StringEscapeUtils.escapeJavaScript(currentAnalyse.getAaPoint().getApPoint() + " - "
                + currentAnalyse.getAaPoint().getApDesignation())));
        model.getTitle().setX(-20);

        // Setup SubTitle
        model.setSubTitle(new SubTitle(StringEscapeUtils.escapeJavaScript("["
                + currentAnalyse.getAaType().getAtType()
                + " - "
                + currentAnalyse.getAaType().getAtDesignation() + "]")));
        model.getSubTitle().setX(-20);
        model.getSubTitle().setStyle("\"color:blue;\"");

        // Setup XAxis
        model.getxAxis().setCategories(
                analyseDataController.getItemsByPointTypeSampleTimeRangeSampleDate(
                        currentAnalyse.getAaPoint(), currentAnalyse.getAaType(),
                        dateFrom, dateTo, ""));
        model.getxAxis().getTitle().setText(StringEscapeUtils.escapeJavaScript(ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("AnalyseDataField_adsampleTime")));

        // Setup YAxis
        model.getyAxis().getTitle().setText(StringEscapeUtils.escapeJavaScript(currentAnalyse.getAaType().getAtUnite().getUDesignation()));
        model.getyAxis().setPlotLines(new PlotLines());
        model.getyAxis().getPlotLines().setValue(0);
        model.getyAxis().getPlotLines().setWidth(1);
        model.getyAxis().getPlotLines().setColor("#808080");

        // Setup Tooltip
        model.setToolTip(new ToolTip());
        model.getToolTip().setValueSuffix(StringEscapeUtils.escapeJavaScript(currentAnalyse.getAaType().getAtUnite().getUUnite()));

        // Setup Legend
//        model.setLegend(new Legend());
//        model.getLegend().setLayout(Align.VERTICAL);
//        model.getLegend().setAlign(Align.RIGHT);
//        model.getLegend().setVerticalAlign(Align.MIDDLE);
//        model.getLegend().setBorderWidth(0);

        // Mot Options
        model.setPlotOptions(new PlotOptions());
        model.getPlotOptions().setDataLabels(new DataLabels());
        model.getPlotOptions().getDataLabels().setEnabled(true);
        model.getPlotOptions().setEnableMouseTracking(true);

        // Managed serie
        model.setSeries(new Series());
        model.getSeries().setLineSerie(new LineSerie());
        model.getSeries().getLineSerie().setData(new ArrayList<Data>());

        // Manage serie title
        String graphTitle = StringEscapeUtils.escapeJava(currentAnalyse.getAaType().getAtType() + " - " + currentAnalyse.getAaType().getAtDesignation());
        String hhTitle = StringEscapeUtils.escapeJava(ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("AnalyseAllowedField_aalimitHH"));
        String hTitle = StringEscapeUtils.escapeJava(ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("AnalyseAllowedField_aalimitH"));
        String lTitle = StringEscapeUtils.escapeJava(ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("AnalyseAllowedField_aalimitB"));
        String llTitle = StringEscapeUtils.escapeJava(ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("AnalyseAllowedField_aalimitBB"));

        // Graph Serie
        List<?> datas = analyseDataController
                .getItemsByPointTypeSampleTimeRangeD(currentAnalyse.getAaPoint(), currentAnalyse.getAaType(),
                        dateFrom, dateTo);
        if (datas != null) {
            model.getSeries().getLineSerie().getData().add(new Data(graphTitle, datas));
        }

        // HH Serie
        datas = analyseDataController.getItemsByPointTypeSampleTimeRangeLimite(
                currentAnalyse.getAaPoint(), currentAnalyse.getAaType(),
                dateFrom, dateTo, "HH");
        if (datas != null) {
            model.getSeries().getLineSerie().getData().add(new Data(hhTitle, datas));
        }

        // H Serie
        datas = analyseDataController.getItemsByPointTypeSampleTimeRangeLimite(
                currentAnalyse.getAaPoint(), currentAnalyse.getAaType(),
                dateFrom, dateTo, "H");
        if (datas != null) {
            model.getSeries().getLineSerie().getData().add(new Data(hTitle, datas));
        }

        // L Serie
        datas = analyseDataController.getItemsByPointTypeSampleTimeRangeLimite(
                currentAnalyse.getAaPoint(), currentAnalyse.getAaType(),
                dateFrom, dateTo, "B");
        if (datas != null) {
            model.getSeries().getLineSerie().getData().add(new Data(lTitle, datas));
        }

        // LL Serie
        datas = analyseDataController.getItemsByPointTypeSampleTimeRangeLimite(
                currentAnalyse.getAaPoint(), currentAnalyse.getAaType(),
                dateFrom, dateTo, "BB");
        if (datas != null) {
            model.getSeries().getLineSerie().getData().add(new Data(llTitle, datas));
        }
        activeIndex = 1;

        return model;
    }

    /**
     * Convenient method tacking care of the request defined.
     * <br> Method will create model with specify data using createModels
     * method.
     *
     * @see ViewAnalyseChart#createModels()
     */
    public void handleSearchRequest() {
        createModels();
    }

    public List<ChartModel> getModels() {
        return models;
    }

    public void setModels(List<ChartModel> models) {
        this.models = models;
    }

    public List<AnalyseAllowed> getAnalyseAlloweds() {
        return analyseAlloweds;
    }

    public void setAnalyseAlloweds(List<AnalyseAllowed> analyseAlloweds) {
        this.analyseAlloweds = analyseAlloweds;
    }

    public ViewAnalyseChartSelect getSelected() {
        return selected;
    }

    public void setSelected(ViewAnalyseChartSelect selected) {
        this.selected = selected;
    }

    public List<AnalysePoint> getRequestPoints() {
        return requestPoints;
    }

    public void setRequestPoints(List<AnalysePoint> requestPoints) {
        this.requestPoints = requestPoints;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }

    public Boolean getKeepRequest() {
        return keepRequest;
    }

    public void setKeepRequest(Boolean keepRequest) {
        this.keepRequest = keepRequest;
    }

    public Long getElapseTiming() {
        return elapseTiming;
    }

    public void setElapseTiming(Long elapseTiming) {
        this.elapseTiming = elapseTiming;
    }

    public Integer getModelProgress() {
        return modelProgress;
    }

    public void setModelProgress(Integer modelProgress) {
        this.modelProgress = modelProgress;
    }

    public LazyDataModel<AnalyseData> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<AnalyseData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

}
