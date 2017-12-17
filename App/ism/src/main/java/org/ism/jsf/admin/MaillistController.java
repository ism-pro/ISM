package org.ism.jsf.admin;

import org.ism.entities.admin.Maillist;
import org.ism.jsf.util.JsfUtil;
import org.ism.jsf.util.JsfUtil.PersistAction;
import org.ism.sessions.admin.MaillistFacade;

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
import javax.faces.component.UIComponent;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.ism.entities.admin.Company;
import org.ism.entities.app.IsmMailtype;
import org.ism.entities.app.IsmNcrstate;
import org.ism.entities.smq.Processus;
import org.ism.jsf.app.IsmMailtypeController;
import org.ism.jsf.app.IsmNcrstateController;
import org.ism.services.TableSet;
import org.primefaces.model.SortMeta;

@ManagedBean(name = "maillistController")
@SessionScoped
public class MaillistController implements Serializable {

    @EJB
    private org.ism.sessions.admin.MaillistFacade ejbFacade;
    private List<Maillist> items = null;
    private Maillist selected;
    private Boolean isReleaseSelected;              //!< Spécifie si oui ou non l'élément selection doit rester en mémoire après création
    private Boolean isOnMultiCreation;              //!< Spécifie si le mode de création multiple est activé

    private Map<Integer, String> headerTextMap;     //!< map header in order to manage reodering
    private Map<String, Boolean> visibleColMap;     //!< Allow to keep 

    private List<Maillist> itemsByLast;  // Specify current items list
    private List<Maillist> itemsFiltered;// Specify current filtered

    /**
     * Multi Sort Meta save table sorting
     */
    private List<SortMeta> multiSortMeta = null;
    /**
     * Filters save table filters
     */
    private Map<String, Object> filters = null;

    /**
     * Define table setups include with controller
     */
    private TableSet tableSet = new TableSet();

    /**
     * Define lazy model to load progressively data
     */
    //private MaillistLazyModel lazyModel;
    
    @ManagedProperty(value = "#{ismNcrstateController}")
    IsmNcrstateController ismNcrstateController;
    
    @ManagedProperty(value = "#{ismMailtypeController}")
    IsmMailtypeController ismMailtypeController;
    
    /**
     * Type standard
     */
    public static Integer MTYPE_STD = 1 ; 
    /**
     * Type RECLAMMATION
     */
    public static Integer MTYPE_RECLAM = 2 ; 

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Constructor
    // 
    //
    // /////////////////////////////////////////////////////////////////////////
    public MaillistController() {
    }

    @PostConstruct
    protected void initialize() {
        isReleaseSelected = true;   //!< by default, after a crud event select element is release (null)
        isOnMultiCreation = false;  //!< Par défaut, la création multiple n'est pas permise

        // Init. list
        itemsByLast = getItemsByLastChanged();

        // STRING PARSE
        String src_01 = "CtrlShort";
        String src_02 = "CtrlShort";
        String src_03 = "CtrlShort";
        String src_04 = "CtrlShort";
        String src_05 = "CtrlShort";
        String src_06 = "CtrlShort";
        String src_07 = "CtrlShort";
        String src_08 = "CtrlShort";
        String src_09 = "CtrlShort";
        String src_10 = "CtrlShort";
        String src_11 = "CtrlShort";
        String src_12 = "CtrlShort";
        String src_13 = "CtrlShort";
        String src_14 = "CtrlShort";
        String src_15 = "CtrlShort";
        String src_16 = "CtrlShort";
        String src_17 = "CtrlShort";
        String src_18 = "CtrlShort";
        String src_19 = "CtrlShort";
        String src_20 = "CtrlShort";

        // Setup initial visibility
        headerTextMap = new HashMap<>();
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

        visibleColMap = new HashMap<>();
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

    }

    private MaillistFacade getFacade() {
        return ejbFacade;
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Container
    // 
    //
    // /////////////////////////////////////////////////////////////////////////
    public Maillist prepareCreate() {
        selected = new Maillist();
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
                getString("MaillistReleaseSelectedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistReleaseSelectedDetail"));
    }

    /**
     * Allow to toggle from on creation mode to multicreation mode
     */
    public void toggleMultiCreation() {
        isOnMultiCreation = !isOnMultiCreation;
        JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistToggleMultiCreationSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistToggleMultiCreationDetail") + isOnMultiCreation);
    }

    /**
     * Facke togle event : this is useful to use with
     */
    public void toggleMultiCreationFake() {
        /*isOnMultiCreation = !isOnMultiCreation;*/
        JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistToggleMultiCreationSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistToggleMultiCreationDetail") + isOnMultiCreation);
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // HANDLE TABLE OPERATIONS
    // 
    //
    // /////////////////////////////////////////////////////////////////////////
    public void handleColumnToggle(ToggleEvent e) {
        visibleColMap.replace(headerTextMap.get((Integer) e.getData()),
                e.getVisibility() == Visibility.VISIBLE);

        JsfUtil.addSuccessMessage("Maillist : Toggle Column",
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
        JsfUtil.addSuccessMessage("Maillist : Reorder Column",
                "Columns : <br>" + columns);

    }

    public void handleTableChanges() {
        itemsByLast = getItemsByLastChanged();
    }

    public void handleDestroy() {
        destroy();
        itemsByLast = getItemsByLastChanged();
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // TABLE CRUD
    // 
    //
    // /////////////////////////////////////////////////////////////////////////
    public void create() {
        // Set time on creation action
        selected.setMlChanged(new Date());
        selected.setMlCreated(new Date());

        persist(PersistAction.CREATE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistPersistenceCreatedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistPersistenceCreatedDetail")
                + "Groupe : " + selected.getMlGroupe() + " / Event : " + ismNcrstateController.getItemsByState(selected.getMlEvent()).getStatename() + " / Processus " + selected.getMlProcessus());

        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            if (isReleaseSelected) {
                selected = null;
            }
            if (isOnMultiCreation) {
                selected = new Maillist();

            } else {
//                JsfUtil.out("is not on multicreation");
//                List<Maillist> v_Maillist = getFacade().findAll();
//                selected = v_Maillist.get(v_Maillist.size() - 1);
            }
        }
    }

    public void createUnReleasded() {
        isReleaseSelected = false;
        create();
    }

    public void update() {
        // Set time on creation action
        selected.setMlChanged(new Date());

        persist(PersistAction.UPDATE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistPersistenceUpdatedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistPersistenceUpdatedDetail")
                + "Groupe : " + selected.getMlGroupe() + " / Event : " + ismNcrstateController.getItemsByState(selected.getMlEvent()).getStatename() + " / Processus " + selected.getMlProcessus());
    }

    public void destroy() {
        persist(PersistAction.DELETE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistPersistenceDeletedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                getString("MaillistPersistenceDeletedDetail")
                + "Groupe : " + selected.getMlGroupe() + " / Event : " + ismNcrstateController.getItemsByState(selected.getMlEvent()).getStatename() + " / Processus " + selected.getMlProcessus());
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            selected = null;
        }
    }
    
    /**
     * Allow to destroy all the mail list affected to a specific processus
     * @param processus is the processus to destroy
     */
    public void destroyAllByProcessus(Processus processus) {
        List<Maillist> mails =  ejbFacade.findByProcessus(processus);
        for(Maillist mail : mails){
            selected = mail;
            destroy();
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

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // JPA CONTAINER
    // 
    //
    // /////////////////////////////////////////////////////////////////////////
    public Maillist getMaillist(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Maillist> getItems() {
        items = getFacade().findAll();
        return items;
    }

    public List<Maillist> getItemsByLastChanged() {
        items = getFacade().findAllByLastChanged();
        return items;
    }

    public List<Maillist> getItemsByMaillist(String _Maillist) {
        return getFacade().findByCode(_Maillist);
    }
    
    public List<Maillist> getItemsByProcessus(Processus processus) {
        return getFacade().findByProcessus(processus);
    }

    public List<Maillist> getItemsByDesignation(String designation) {
        return getFacade().findByDesignation(designation);
    }
    
    
    public Maillist getItemsBy(String mlGroupe, String mlEvent, IsmMailtype ismMailtype, Processus processus, Company company) {
        return  getFacade().findUnique(mlGroupe, mlEvent, ismMailtype, processus, company);
    }
    
    /**
     * Permet de retrouver la liste de message correspondant
     * @param mlGroupe catégorie de liste
     * @param mlEvent A : create, B : wait solution, C: processing, D : finished, E: Canceled
     * @param type 1: standard, 2: réclam
     * @param processus concerné affectant également la société
     * @return la premier valeur si retrouvé sinon null
     */
    public Maillist getItemsBy(String mlGroupe, String mlEvent, Integer type, Processus processus) {
        return  getFacade().findUnique(mlGroupe, mlEvent, ismMailtypeController.getIsmMailtype(type), processus, processus.getPCompany());
    }

    public List<Maillist> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Maillist> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Getter / Setter
    // 
    //
    // /////////////////////////////////////////////////////////////////////////
    public Maillist getSelected() {
        if (selected == null) {
            selected = new Maillist();
        }
        return selected;
    }

    public void setSelected(Maillist selected) {
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

    public List<Maillist> getItemsByLast() {
        return itemsByLast;
    }

    public void setItemsByLast(List<Maillist> itemsByLast) {
        this.itemsByLast = itemsByLast;
    }

    public List<Maillist> getItemsFiltered() {
        return itemsFiltered;
    }

    public void setItemsFiltered(List<Maillist> itemsFiltered) {
        this.itemsFiltered = itemsFiltered;
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // INJECTION
    // 
    //
    // /////////////////////////////////////////////////////////////////////////
    public void setIsmNcrstateController(IsmNcrstateController ismNcrstateController) {
        this.ismNcrstateController = ismNcrstateController;
    }

    public void setIsmMailtypeController(IsmMailtypeController ismMailtypeController) {
        this.ismMailtypeController = ismMailtypeController;
    }
    
    

    
    
    

}