package org.ism.jsf.smq;

import org.ism.entities.smq.DocExplorer;
import org.ism.jsf.util.JsfUtil;
import org.ism.jsf.util.JsfUtil.PersistAction;
import org.ism.sessions.smq.DocExplorerFacade;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.ism.entities.admin.Company;
import org.ism.entities.smq.DocType;
import org.ism.entities.smq.Processus;
import org.ism.lazy.smq.DocExplorerLazyModel;
import org.ism.services.TableSet;
import org.primefaces.event.data.FilterEvent;
import org.primefaces.event.data.SortEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@ManagedBean(name = "docExplorerController")
@SessionScoped
public class DocExplorerController implements Serializable {

    @EJB
    private org.ism.sessions.smq.DocExplorerFacade ejbFacade;
    private List<DocExplorer> items = null;
    private DocExplorer selected;
    private DocExplorer edited;
    private Boolean isReleaseSelected;              //!< Spécifie si oui ou non l'élément selection doit rester en mémoire après création
    private Boolean isOnMultiCreation;              //!< Spécifie si le mode de création multiple est activé

    private Map<Integer, String> headerTextMap;     //!< map header in order to manage reodering
    private Map<String, Boolean> visibleColMap;     //!< Allow to keep 

    /**
     * Multi Sort Meta save table sorting
     */
    private List<SortMeta> multiSortMeta = null;
    /**
     * filters save table filters
     */
    private Map<String, Object> filters = null;

    /**
     * Define table setups include with Analyse data controller
     */
    private TableSet tableSet = new TableSet();

    /**
     * Define lazy model to load progressively data
     */
    private DocExplorerLazyModel lazyModel;

    public DocExplorerController() {
    }

    @PostConstruct
    protected void initialize() {
        isReleaseSelected = true;   //!< by default, after a crud event select element is release (null)
        isOnMultiCreation = false;  //!< Par défaut, la création multiple n'est pas permise
        // Setup initial visibility
        /*
        DocExplorerField_Id=N°
        DocExplorerField_Processus=Processus
        DocExplorerField_DocType=Type
        DocExplorerField_Name=Designation
        DocExplorerField_Version=Version
        DocExplorerField_Commentaire=Commentaire
        DocExplorerField_Link=Lien document
        DocExplorerField_DateApprouved=Date approbation
        DocExplorerField_Activated=Activé
        DocExplorerField_Created=Création
        DocExplorerField_Timestamp=Modif.
        DocExplorerField_Company=Société
         */
        headerTextMap = new HashMap<>();
        headerTextMap.put(0, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("CtrlShort"));
        headerTextMap.put(1, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Id"));
        headerTextMap.put(2, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Processus"));
        headerTextMap.put(3, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_DocType"));
        headerTextMap.put(4, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Name"));
        headerTextMap.put(5, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Version"));
        headerTextMap.put(6, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Commentaire"));
        headerTextMap.put(7, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Link"));
        headerTextMap.put(8, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_DateApprouved"));
        headerTextMap.put(9, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Activated"));
        headerTextMap.put(10, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Created"));
        headerTextMap.put(11, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Timestamp"));
        headerTextMap.put(12, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Company"));

        visibleColMap = new HashMap<>();
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("CtrlShort"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Id"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Processus"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_DocType"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Name"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Version"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Commentaire"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Link"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_DateApprouved"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Activated"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Created"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Timestamp"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("DocExplorerField_Company"), false);

        // Initialize lazy model
        lazyModel = new DocExplorerLazyModel(ejbFacade);
    }

    private DocExplorerFacade getFacade() {
        return ejbFacade;
    }

    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// BASE OPTIONS
    ///
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    public DocExplorer prepareCreate() {
        selected = new DocExplorer();
        return selected;
    }

    public DocExplorer prepareEdit() {
        edited = selected;
        return edited;
    }

    /**
     * This method is useful to release actual selected ! That way nothing is
     * selected
     */
    public void releaseSelected() {
        isReleaseSelected = true;
        selected = null;
        JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerReleaseSelectedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerReleaseSelectedDetail"));
    }

    /**
     * Allow to toggle from on creation mode to multicreation mode
     */
    public void toggleMultiCreation() {
        isOnMultiCreation = !isOnMultiCreation;
        JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerToggleMultiCreationSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerToggleMultiCreationDetail") + isOnMultiCreation);
    }

    /**
     * Facke togle event : this is useful to use with
     */
    public void toggleMultiCreationFake() {
        /*isOnMultiCreation = !isOnMultiCreation;*/
        JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerToggleMultiCreationSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerToggleMultiCreationDetail") + isOnMultiCreation);
    }

        /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// TABLE OPTIONS
    ///
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param e toggle event
     */
    public void handleColumnToggle(ToggleEvent e) {
        visibleColMap.replace(headerTextMap.get((Integer) e.getData()),
                e.getVisibility() == Visibility.VISIBLE);

        JsfUtil.addSuccessMessage("DocExplorer : Toggle Column",
                "Column n° " + e.getData() + " is now " + e.getVisibility());

    }

    public void handleColumnReorder(javax.faces.event.AjaxBehaviorEvent e) {
        DataTable table = (DataTable) e.getSource();
        String columns = "";
        int i = 0;
        for (UIColumn column : table.getColumns()) {
            UIComponent uic = (UIComponent) column;
            String headerText = (String) uic.getAttributes().get((Object) "headerText");
            Boolean visible = (Boolean) uic.getAttributes().get((Object) "visible");
            headerTextMap.replace(i, headerText);
            visibleColMap.replace(headerText, visible);
            columns += headerText + "(" + visible + ") <br >";
            i++;
        }
        JsfUtil.addSuccessMessage("DocExplorer : Reorder Column",
                "Columns : <br>" + columns);
    }

    /**
     * Handle Filter Date Range allow in a filter to manage a range sélection
     *
     * @param value a value is the corresponding filter
     * @param filter a object filtred
     * @param locale a local
     * @return true if ok
     * @throws ParseException an error
     */
    public boolean handleFilterDateRange(Object value, Object filter, Locale locale) throws ParseException {
        return JsfUtil.dateRangeIn(value, filter, locale);
    }

    public void handleTableChanges() {

    }

    /**
     * Handle filtering is used to save state of filters and restore it to lazy
     * model if it is different from lazy
     *
     * @param event filter event
     */
    public void handleFiltering(FilterEvent event) {
        filters = event.getFilters();
        lazyModel.setFilters(filters);
    }

    /**
     * Handle sorting is used to save state of sort in the lazy model to allow
     * restore while staying in the same stage.
     *
     * @param event while sorting
     */
    public void handleSorting(SortEvent event) {

        SortMeta sortMeta = new SortMeta(event.getSortColumn(),
                event.getSortColumn().getField(),
                event.isAscending() ? SortOrder.ASCENDING : SortOrder.DESCENDING,
                null);
        if (!multiSortMeta.contains(sortMeta)) {
            multiSortMeta.add(sortMeta);
        } else {
            // reorder
            multiSortMeta.remove(sortMeta);
            multiSortMeta.add(sortMeta);
        }
        lazyModel.setMultiSortMeta(multiSortMeta);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///
    /// EXPORTER
    ///
    ////////////////////////////////////////////////////////////////////////////
    public void handlePostProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            header.getCell(i).setCellStyle(cellStyle);
            sheet.autoSizeColumn(i);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    ///
    /// CRUD OPTIONS
    ///
    ////////////////////////////////////////////////////////////////////////////
    public void create() {
        // Set time on creation action
        selected.setDcChanged(new Date());
        selected.setDcCreated(new Date());

        persist(PersistAction.CREATE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerPersistenceCreatedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerPersistenceCreatedDetail")
                + selected.getDcVersion() + " <br > " + selected.getDcDesignation());

        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            if (isReleaseSelected) {
                selected = null;
            }
            if (isOnMultiCreation) {
                selected = new DocExplorer();

            } else {
                JsfUtil.out("is not on multicreation");
                List<DocExplorer> docExplorer = getFacade().findAll();
                selected = docExplorer.get(docExplorer.size() - 1);
            }
        }
    }

    public void createUnReleasded() {
        isReleaseSelected = false;
        create();
    }

    public void update() {
        // Set time on creation action
        selected.setDcChanged(new Date());

        persist(PersistAction.UPDATE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerPersistenceUpdatedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerPersistenceUpdatedDetail")
                + selected.getDcVersion() + " <br > " + selected.getDcDesignation());
    }

    public void destroy() {
        persist(PersistAction.DELETE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerPersistenceDeletedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("DocExplorerPersistenceDeletedDetail")
                + selected.getDcVersion() + " <br > " + selected.getDcDesignation());
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            selected = null;
        }
    }

    private void persist(PersistAction persistAction, String summary, String detail) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(summary, detail);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(summary, msg);
                } else {
                    JsfUtil.addErrorMessage(ex, summary,
                            ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, summary, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("PersistenceErrorOccured"));
            }
        }
    }

    private void persist(PersistAction persistAction, String detail) {
        persist(persistAction, detail, detail);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///
    /// JPA
    ///
    ////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param id doc explorer key
     * @return corresponding doc explorer object
     */
    public DocExplorer getDocExplorer(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<DocExplorer> getItems() {
        items = getFacade().findAll();
        return items;
    }

    public List<DocExplorer> getItemsByLastChanged() {
        items = getFacade().findAllByLastChanged();
        return items;
    }

    public List<DocExplorer> getItemsByCode(String code) {
        return getFacade().findByCode(code);
    }

    public List<DocExplorer> getItemsByCode(String code, Company company) {
        return getFacade().findByCode(code, company);
    }

    public List<DocExplorer> getItemsByDesignation(String designation) {
        return getFacade().findByDesignation(designation);
    }

    public List<DocExplorer> getItemsByDesignation(String designation, Company company) {
        return getFacade().findByDesignation(designation, company);
    }

    public List<DocExplorer> getItemsByLink(String link) {
        return getFacade().findByLink(link);
    }

    public List<DocExplorer> getItemsByLink(String link, Company company) {
        return getFacade().findByLink(link, company);
    }

    public List<DocExplorer> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DocExplorer> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<DocExplorer> getItemsByProcessus(Processus processus) {
        return getFacade().findByProcessus(processus);
    }

    public List<DocExplorer> getItemsByProcessusAndType(Processus processus, DocType docType) {
        return getFacade().findByProcessusAndType(processus, docType);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///
    /// GETTER / SETTER
    ///
    ////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @return selected doc explorer
     */
    public DocExplorer getSelected() {
        if (selected == null) {
            selected = new DocExplorer();
        }
        return selected;
    }

    public void setSelected(DocExplorer selected) {
        this.selected = selected;
    }

    public Boolean getIsReleaseSelected() {
        return isReleaseSelected;
    }

    public void setIsReleaseSelected(Boolean isReleaseSelected) {
        this.isReleaseSelected = isReleaseSelected;
    }

    public Boolean getIsOnMultiCreation() {
        return isOnMultiCreation;
    }

    public void setIsOnMultiCreation(Boolean isOnMultiCreation) {
        this.isOnMultiCreation = isOnMultiCreation;
    }

    public Map<String, Boolean> getVisibleColMap() {
        return visibleColMap;
    }

    public void setVisibleColMap(Map<String, Boolean> visibleColMap) {
        this.visibleColMap = visibleColMap;
    }

    public Boolean getIsVisibleColKey(String key) {
        return this.visibleColMap.get(key);
    }

    public TableSet getTableSet() {
        return tableSet;
    }

    public void setTableSet(TableSet tableSet) {
        this.tableSet = tableSet;
    }

    ////////////////////////////////////////////////////////////////////////////
    /// Manage Injection
    ///
    ////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////
    //
    /// LAZY
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public DocExplorerLazyModel getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(DocExplorerLazyModel lazyModel) {
        this.lazyModel = lazyModel;
    }
}
