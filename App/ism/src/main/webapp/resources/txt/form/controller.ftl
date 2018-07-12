<#if comment>

TEMPLATE DESCRIPTION:

This is Java template for 'JSF Pages From Entity Beans' controller class. Templating
is performed using FreeMaker (http://freemarker.org/) - see its documentation
for full syntax. Variables available for templating are:

controllerClassName - controller class name (type: String)
controllerPackageName - controller package name (type: String)
entityClassName - entity class name without package (type: String)
importEntityFullClassName - whether to import entityFullClassName or not
entityFullClassName - fully qualified entity class name (type: String)
ejbClassName - EJB class name (type: String)
importEjbFullClassName - whether to import ejbFullClassName or not
ejbFullClassName - fully qualified EJB class name (type: String)
managedBeanName - name of managed bean (type: String)
keyEmbedded - is entity primary key is an embeddable class (type: Boolean)
keyType - fully qualified class name of entity primary key
keyBody - body of Controller.Converter.getKey() method
keyStringBody - body of Controller.Converter.getStringKey() method
keyGetter - entity getter method returning primaty key instance
keySetter - entity setter method to set primary key instance
embeddedIdFields - contains information about embedded primary IDs
cdiEnabled - project contains beans.xml, so Named beans can be used
bundle - name of the variable defined in the JSF config file for the resource bundle (type: String)

This template is accessible via top level menu Tools->Templates and can
be found in category JavaServer Faces->JSF from Entity.

</#if>
package ${controllerPackageName};

<#if importEntityFullClassName?? && importEntityFullClassName == true>
import ${entityFullClassName};
</#if>
import ${controllerPackageName}.util.JsfUtil;
import ${controllerPackageName}.util.JsfUtil.PersistAction;
<#if importEjbFullClassName?? && importEjbFullClassName == true>
    <#if ejbClassName??>
import ${ejbFullClassName};
    <#elseif jpaControllerClassName??>
import ${jpaControllerFullClassName};
    </#if>
</#if>

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;



@ManagedBean(name="${managedBeanName}")
@SessionScoped
public class ${controllerClassName} implements Serializable {

@EJB private ${ejbFullClassName} ejbFacade;
private List<${entityClassName}> items = null;
private ${entityClassName} selected;
private Boolean isReleaseSelected;              //!< Spécifie si oui ou non l'élément selection doit rester en mémoire après création
private Boolean isOnMultiCreation;     

    //!< Spécifie si le mode de création multiple est activé
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
    private ${entityClassName}LazyModel lazyModel;

    



        public ${controllerClassName}() {
        }

        @PostConstruct
        protected void initialize() {
        isReleaseSelected = true;   //!< by default, after a crud event select element is release (null)
        isOnMultiCreation = false;  //!< Par défaut, la création multiple n'est pas permise
        // STRING PARSE
        String src_01 = "src_xx";
        String src_02 = "src_xx";
        String src_03 = "src_xx";
        String src_04 = "src_xx";
        String src_05 = "src_xx";
        String src_06 = "src_xx";
        String src_07 = "src_xx";
        String src_08 = "src_xx";
        String src_09 = "src_xx";
        String src_10 = "src_xx";
        String src_11 = "src_xx";
        String src_12 = "src_xx";
        String src_13 = "src_xx";
        String src_14 = "src_xx";
        String src_15 = "src_xx";
        String src_16 = "src_xx";
        String src_17 = "src_xx";
        String src_18 = "src_xx";
        String src_19 = "src_xx";
        String src_20 = "src_xx";
        String src_21 = "src_xx";
        String src_22 = "src_xx";
        String src_23 = "src_xx";
        String src_24 = "src_xx";
        String src_25 = "src_xx";
        String src_26 = "src_xx";
        String src_27 = "src_xx";
        String src_28 = "src_xx";
        String src_29 = "src_xx";
        String src_30 = "src_xx";


        // Setup initial visibility
        headerTextMap = new HashMap<Integer, String>();
        headerTextMap.put(0, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("CtrlShort"));
        headerTextMap.put(1, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_01));
        headerTextMap.put(2, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_02));
        headerTextMap.put(3, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_03));
        headerTextMap.put(4, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_04));
        headerTextMap.put(5, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_05));
        headerTextMap.put(6, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_06));
        headerTextMap.put(7, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_07));
        headerTextMap.put(8, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_08));
        headerTextMap.put(9, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_09));
        headerTextMap.put(10, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_10));
        headerTextMap.put(11, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_11));
        headerTextMap.put(12, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_12));
        headerTextMap.put(13, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_13));
        headerTextMap.put(14, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_14));
        headerTextMap.put(15, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_15));
        headerTextMap.put(16, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_16));
        headerTextMap.put(17, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_17));
        headerTextMap.put(18, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_18));
        headerTextMap.put(19, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_19));
        headerTextMap.put(20, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_20));
        headerTextMap.put(11, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_21));
        headerTextMap.put(12, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_22));
        headerTextMap.put(13, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_23));
        headerTextMap.put(14, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_24));
        headerTextMap.put(15, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_25));
        headerTextMap.put(16, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_26));
        headerTextMap.put(17, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_27));
        headerTextMap.put(18, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_28));
        headerTextMap.put(19, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_29));
        headerTextMap.put(20, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_30));

        visibleColMap = new HashMap<String, Boolean>();
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("CtrlShort"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_01), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_02), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_03), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_04), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_05), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_06), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_07), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_08), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_09), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_10), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_11), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_12), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_13), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_14), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_15), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_16), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_17), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_18), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_19), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_20), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_21), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_22), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_23), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_24), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_25), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_26), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_27), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_28), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_29), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString(src_30), false);

        // Initialize lazy model
        lazyModel = new ${entityClassName}LazyModel(ejbFacade);

        }

<#if ejbClassName??>
            private ${ejbClassName} getFacade() {
            return ejbFacade;
            }
<#elseif jpaControllerClassName??>
                private ${jpaControllerClassName} getJpaController() {
                if (jpaController == null) {
<#if isInjected?? && isInjected==true>
                jpaController = new ${jpaControllerClassName}(utx, emf);
<#else>
                jpaController = new ${jpaControllerClassName}(Persistence.createEntityManagerFactory(<#if persistenceUnitName??>"${persistenceUnitName}"</#if>));
</#if>
                }
                return jpaController;
                }
</#if>

                /// ////////////////////////////////////////////////////////////////////////
                ///
                /// BASE OPTIONS
                ///
                /// ////////////////////////////////////////////////////////////////////////
                public ${entityClassName} prepareCreate() {
                selected = new ${entityClassName}();
                return selected;
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
                getString("${entityClassName}ReleaseSelectedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}ReleaseSelectedDetail"));
                }

                /**
                * Allow to toggle from on creation mode to multicreation mode
                */
                public void toggleMultiCreation() {
                isOnMultiCreation = !isOnMultiCreation;
                JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}ToggleMultiCreationSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}ToggleMultiCreationDetail") + isOnMultiCreation);
                }

                /**
                * Facke togle event : this is useful to use with
                */
                public void toggleMultiCreationFake() {
                /*isOnMultiCreation = !isOnMultiCreation;*/
                JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}ToggleMultiCreationSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}ToggleMultiCreationDetail") + isOnMultiCreation);
                }

                
    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// TABLE OPTIONS
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public void handleColumnToggle(ToggleEvent e) {
                visibleColMap.replace(headerTextMap.get((Integer) e.getData()),
                e.getVisibility() == Visibility.VISIBLE);

                JsfUtil.addSuccessMessage("${entityClassName} : Toggle Column",
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
                JsfUtil.addSuccessMessage("${entityClassName} : Reorder Column",
                "Columns : <br>" + columns);

                }

                
    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// CRUD OPTIONS
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public void create() {
                // Set time on creation action
                selected.set${entityClassName?substring(0,3)}Changed(new Date());
                selected.set${entityClassName?substring(0,3)}Created(new Date());

                persist(PersistAction.CREATE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}PersistenceCreatedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}PersistenceCreatedDetail")
                + selected.get${entityClassName?substring(0,3)}${entityClassName}() + " <br > " + selected.get${entityClassName?substring(0,3)}Designation());

                if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
                if (isReleaseSelected) {
                selected = null;
                }
                if (isOnMultiCreation) {
                selected = new ${entityClassName}();

                } else {
                JsfUtil.out("is not on multicreation");
                List<${entityClassName}> v_${entityClassName} = getFacade().findAll();
                selected = v_${entityClassName}.get(v_${entityClassName}.size() - 1);
                }
                }
                }



                public void createUnReleasded() {
                isReleaseSelected = false;
                create();
                }

                public void update() {
                // Set time on creation action
                selected.set${entityClassName?substring(0,3)}Changed(new Date());

                persist(PersistAction.UPDATE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}PersistenceUpdatedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}PersistenceUpdatedDetail")
                + selected.get${entityClassName?substring(0,3)}${entityClassName}() + " <br > " + selected.get${entityClassName?substring(0,3)}Designation());
                }

                public void destroy() {
                persist(PersistAction.DELETE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}PersistenceDeletedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("${entityClassName}PersistenceDeletedDetail")
                + selected.get${entityClassName?substring(0,3)}${entityClassName}() + " <br > " + selected.get${entityClassName?substring(0,3)}Designation());
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

                
    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// JPA OPTIONS
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public ${entityClassName} get${entityClassName}(java.lang.Integer id) {
                return getFacade().find(id);
                }

                public List<${entityClassName}> getItems() {
                items = getFacade().findAll();
                return items;
                }

                public List<${entityClassName}> getItemsByLastChanged() {
                items = getFacade().findAllByLastChanged();
                return items;
                }

                public List<${entityClassName}> getItemsBy${entityClassName}(String _${entityClassName}) {
                return getFacade().findByCode(_${entityClassName});
                }

                public List<${entityClassName}> getItemsByDesignation(String designation) {
                return getFacade().findByDesignation(designation);
                }

                public List<${entityClassName}> getItemsAvailableSelectMany() {
                return getFacade().findAll();
                }

                public List<${entityClassName}> getItemsAvailableSelectOne() {
                return getFacade().findAll();
                }

                
    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// GETTER / SETTER
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public ${entityClassName} getSelected() {
                if (selected == null) {
                selected = new ${entityClassName}();
                }
                return selected;
                }

                public void setSelected(${entityClassName} selected) {
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


    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// INJECTION
    ///
    /// ////////////////////////////////////////////////////////////////////////
    




    /// ////////////////////////////////////////////////////////////////////////
    //
    /// LAZY
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public ${entityClassName}LazyModel getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(${entityClassName}LazyModel lazyModel) {
        this.lazyModel = lazyModel;
    }







    /// ////////////////////////////////////////////////////////////////////////
    //
    /// CONVERTER
    ///
    /// ////////////////////////////////////////////////////////////////////////
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.ism.entities.smq.${entityClassName};

import org.ism.jsf.smq.${entityClassName}Controller;
import org.ism.jsf.util.JsfUtil;

/**
 * <h1>${entityClassName}Converter</h1><br>
 * ${entityClassName}Converter class
 *
 * @author r.hendrick
 *
 */
@ManagedBean
@SessionScoped
@FacesConverter(value = "docExplorerConverter")
public class ${entityClassName}Converter implements Converter, Serializable {
    
    @ManagedProperty(value = "#{${entityClassName}Controller}")
    ${entityClassName}Controller ${entityClassName}Controller;
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        
        
        try {
            Integer.valueOf(value);
        } catch (NumberFormatException ex) {
            JsfUtil.out("${entityClassName}Converter :  Impossible de convertir la valeur " + value + " en entier ! Erreur : " + ex.getLocalizedMessage());
            return null;
        }

        return docExplorerController.get${entityClassName}(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ${entityClassName}) {
            ${entityClassName} o = (${entityClassName}) object;
            return getStringKey(o.getDcId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DocExplorer.class.getName()});
            return null;
        }
    }
    
    
    public void set${entityClassName}Controller(${entityClassName}Controller docExplorerController){
        this.docExplorerController = docExplorerController;
    }

}



    /// ////////////////////////////////////////////////////////////////////////
    //
    /// LAZY
    ///
    /// ////////////////////////////////////////////////////////////////////////
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ${entityFullClassName};
import ${ejbFullClassName};
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * <h1>${entityClassName}LazyModel</h1>
 *
 * @author r.hendrick
 */
public class ${entityClassName}LazyModel extends LazyDataModel<${entityClassName}> implements Serializable {

    private ${entityClassName}Facade ejbFacade;

    /**
     * data source save current data value of the page
     */
    private List<${entityClassName}> datasource;
    private List<SortMeta> multiSortMeta = null;
    private Map<String, Object> filters = null;
    
    public static final String DEFAULT_SORT_ORDER = "dcChanged";

    /**
     * Retained filtre value of table
     */
    private List<${entityClassName}> filtredValue;

    public ${entityClassName}LazyModel() {
    }

    /**
     * Default lazy model constructor
     *
     * @param facade facade to make request
     */
    public ${entityClassName}LazyModel(${entityClassName}Facade facade) {
        ejbFacade = facade;
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Loading request
    //
    //
    // /////////////////////////////////////////////////////////////////////////
    /**
     * This load method intend to load data only with one sort order specify on
     * one field. <br>
     * Data will be load from facade from specified first page calculate over
     * page row size
     *
     * @param first specified from which row to start getting items
     * @param pageSize specified numbers of items to read which correspond to
     * page size
     * @param sortField field to be sorted
     * @param sortOrder sort order value
     * @param filters is a map defining a couple of field and criteria of
     * filters
     * @return list of analyse data corresponding to defined criteria
     *
     */
    @Override
    public List<${entityClassName}> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        // Create sort order Map
        //Map<String, Object> 
        return ejbFacade.findByCriterias(first, pageSize, null, filters);

    }

    /**
     * This load method intend to load data only with multi sort order specify
     * on multiple fields. <br>
     * Data will be load from facade from specified first page calculate over
     * page row size
     *
     * @param first specified from which row to start getting items
     * @param pageSize specified numbers of items to read which correspond to
     * page size
     * @param multiSortMeta is a map defining a couple of field sort order way
     * @param filters is a map defining a couple of field and criteria of
     * filters
     * @return list of analyse data corresponding to defined criteria
     */
    @Override
    public List<${entityClassName}> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {

        // Restore filter if required
        if (multiSortMeta == null) {
            if (this.multiSortMeta == null) {
                // Sorting default by date d'échantillonnage
                multiSortMeta = new ArrayList<>();
                SortMeta metaSort = new SortMeta(null, DEFAULT_SORT_ORDER, SortOrder.DESCENDING, null);
                multiSortMeta.add(metaSort);
                this.multiSortMeta = multiSortMeta;
            }
        }else{
            this.multiSortMeta = multiSortMeta;
        }

        if (this.filters != null && this.filters != filters) {
            filters = this.filters;
        }
        // Get data
        datasource = ejbFacade.findByCriterias(first, pageSize, convertSortMeta(this.multiSortMeta), filters);
        // Count rows
        this.setRowCount(ejbFacade.countByCriterias(filters));

        return datasource;
    }

    /**
     * Convert SortMeta is usefuel in order to change from List<SortMeta> to Map
     * key and sortway.
     *
     * @return HashMap of the sortMeta
     */
    private Map<String, String> convertSortMeta(List<SortMeta> multiSortMeta) {
        Map<String, String> sorts = null;
        if (multiSortMeta != null) {
            for (SortMeta e : multiSortMeta) {
                if (sorts == null) {
                    sorts = new HashMap<>();
                }
                sorts.put(e.getSortField(), e.getSortOrder().toString());
            }
        }
        return sorts;
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Getters / Setters
    //
    //
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Read facade used for request
     *
     * @return analyse data facade to make jpa request
     */
    private ${entityClassName}Facade getFacade() {
        return ejbFacade;
    }

    /**
     * Read row data from specified key.
     *
     * @param rowKey the corresponding key of current data
     * @return corresponding data
     */
    @Override
    public ${entityClassName} getRowData(String rowKey) {
        for (${entityClassName} data : datasource) {
            if (data.getDcId().equals(Integer.valueOf(rowKey))) {
                return data;
            }
        }
        return null;
    }

    /**
     * Read row key of current data
     *
     * @param data current data
     * @return corresponding id key
     */
    @Override
    public Object getRowKey(${entityClassName} data) {
        return data.getDcId();
    }

    public List<SortMeta> getMultiSortMeta() {
        return multiSortMeta;
    }

    public void setMultiSortMeta(List<SortMeta> multiSortMeta) {
        this.multiSortMeta = multiSortMeta;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public List<${entityClassName}> getFiltredValue() {
        return filtredValue;
    }

    public void setFiltredValue(List<${entityClassName}> filtredValue) {
        this.filtredValue = filtredValue;
    }

}


    /// ////////////////////////////////////////////////////////////////////////
    //
    /// VALIDATOR
    ///
    /// ////////////////////////////////////////////////////////////////////////
    

}
