package org.ism.jsf.smq.nc;

import java.io.File;
import org.ism.entities.smq.nc.NonConformiteRequest;
import org.ism.jsf.util.JsfUtil;
import org.ism.jsf.util.JsfUtil.PersistAction;
import org.ism.sessions.smq.nc.NonConformiteRequestFacade;
import org.ism.entities.app.IsmNcrstate;

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
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.Part;
import org.ism.entities.admin.Company;
import org.ism.entities.admin.Maillist;
import org.ism.entities.smq.Processus;
import org.ism.entities.smq.nc.NonConformiteActions;
import org.ism.event.CropErrorEvent;
import org.ism.event.CroppedEvent;
import org.ism.jsf.admin.MaillistController;
import org.ism.jsf.admin.MailsenderController;
import org.ism.jsf.app.IsmNcrstateController;
import org.ism.jsf.hr.StaffAuthController;
import org.ism.lazy.smq.nc.NonConformiteRequestLazyModel;
import org.ism.model.cropper.CroppedImage;
import org.ism.services.TableSet;
import org.ism.services.admin.Mail;
import org.ism.services.file.FileService;
import org.primefaces.event.data.FilterEvent;
import org.primefaces.event.data.SortEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@ManagedBean(name = "nonConformiteRequestController")
@SessionScoped
public class NonConformiteRequestController implements Serializable {

    @EJB
    private org.ism.sessions.smq.nc.NonConformiteRequestFacade ejbFacade;

    @ManagedProperty(value = "#{mailsenderController}")
    MailsenderController mailsenderController;

    @ManagedProperty(value = "#{maillistController}")
    MaillistController maillistController;

    @ManagedProperty(value = "#{staffAuthController}")
    StaffAuthController staffAuthController;

    @ManagedProperty(value = "#{ismNcrstateController}")
    IsmNcrstateController ismNcrstateController;

    // Event
    public static final String onCreated = "A", onWaitingSolution = "B", onProgressed = "C", onFinished = "D", onCanceled = "E";

    // Groupe
    public static final String MLGROUPE = "NC";

    private List<NonConformiteRequest> items = null;
    private NonConformiteRequest selected;
    private NonConformiteRequest edited;
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
    private NonConformiteRequestLazyModel lazyModel;

    /**
     *
     */
    private CroppedImage croppedImage;
    private Part uploadedPartFile;
    private File uploadedFile;
    private String uploadedStringFile;

    public NonConformiteRequestController() {
    }

    @PostConstruct
    protected void initialize() {
        isReleaseSelected = true;   //!< by default, after a crud event select element is release (null)
        isOnMultiCreation = false;  //!< Par défaut, la création multiple n'est pas permise

        headerTextMap = new HashMap<>();
        headerTextMap.put(0, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("CtrlShort"));
        headerTextMap.put(1, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrId"));
        headerTextMap.put(2, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrStaff"));
        headerTextMap.put(3, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrTitle"));
        headerTextMap.put(4, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrProcessus"));
        headerTextMap.put(5, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrState"));
        headerTextMap.put(6, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrNature"));
        headerTextMap.put(7, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrGravity"));
        headerTextMap.put(8, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrFrequency"));
        headerTextMap.put(9, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrOccured"));
        headerTextMap.put(10, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClientname"));
        headerTextMap.put(11, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClientaddress"));
        headerTextMap.put(12, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClientphone"));
        headerTextMap.put(13, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClientemail"));
        headerTextMap.put(14, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClienttype"));
        headerTextMap.put(15, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrCreated"));
        headerTextMap.put(16, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrChanged"));
        headerTextMap.put(17, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrEnddingAction"));
        headerTextMap.put(18, ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrCompany"));

        visibleColMap = new HashMap<>();
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("CtrlShort"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrId"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrStaff"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrTitle"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrProcessus"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrState"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrNature"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrGravity"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrFrequency"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrOccured"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClientname"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClientaddress"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClientphone"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClientemail"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrClienttype"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrCreated"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrChanged"), false);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrEnddingAction"), true);
        visibleColMap.put(ResourceBundle.getBundle(JsfUtil.BUNDLE).getString("NonConformiteRequestField_ncrCompany"), false);

        // Initialize lazy model
        lazyModel = new NonConformiteRequestLazyModel(ejbFacade);
    }

    private NonConformiteRequestFacade getFacade() {
        return ejbFacade;
    }

    /**
     * ************************************************************************
     * CRUD OPTIONS
     *
     * ************************************************************************
     */
    /**
     *
     * @return prepared non conformite request
     */
    public NonConformiteRequest prepareCreate() {
        selected = new NonConformiteRequest();
        return selected;
    }

    public NonConformiteRequest prepareEdit() {
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
                        getString("NonConformiteRequestReleaseSelectedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestReleaseSelectedDetail"));
    }

    /**
     * Allow to toggle from on creation mode to multicreation mode
     */
    public void toggleMultiCreation() {
        isOnMultiCreation = !isOnMultiCreation;
        JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestToggleMultiCreationSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestToggleMultiCreationDetail") + isOnMultiCreation);
    }

    /**
     * Facke togle event : this is useful to use with
     */
    public void toggleMultiCreationFake() {
        /*isOnMultiCreation = !isOnMultiCreation;*/
        JsfUtil.addSuccessMessage(
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestToggleMultiCreationSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestToggleMultiCreationDetail") + isOnMultiCreation);
    }

    /**
     * ************************************************************************
     * TABLE OPTIONS
     *
     * ************************************************************************
     */
    /**
     *
     * @param e toggle event
     */
    public void handleColumnToggle(ToggleEvent e) {
        visibleColMap.replace(headerTextMap.get((Integer) e.getData()),
                e.getVisibility() == Visibility.VISIBLE);

        JsfUtil.addSuccessMessage("NonConformiteRequest : Toggle Column",
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
        JsfUtil.addSuccessMessage("NonConformiteRequest : Reorder Column",
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

    /**
     * Handle Mail On Create : while event creation occure everything is prepare
     * to informe all define receiver that new mail as bean created.
     */
    private void handleMailOnCreate() {
        Maillist ml = maillistController.getItemsBy(MLGROUPE, onCreated, 1, selected.getNcrProcessus());
        Maillist mlr = maillistController.getItemsBy(MLGROUPE, onCreated, 2, selected.getNcrProcessus());
        String currentStaffMail = staffAuthController.getStaff().getStMaillist().trim().isEmpty() ? "" : staffAuthController.getStaff().getStMaillist();

        Boolean isClient = false;
        isClient = (selected.getNcrClientname() == null ? false : !selected.getNcrClientname().trim().isEmpty())
                || (selected.getNcrClientaddress() == null ? false : !selected.getNcrClientaddress().trim().isEmpty())
                || (selected.getNcrClientemail() == null ? false : !selected.getNcrClientemail().trim().isEmpty())
                || (selected.getNcrClientphone() == null ? false : !selected.getNcrClientphone().trim().isEmpty())
                || (selected.getNcrClienttype() == null ? false : !selected.getNcrClienttype().trim().isEmpty());

        // Préparation du message
        String sujet = selected.getNcrId() + " - création - " + selected.getNcrTitle();
        String title = selected.getNcrId() + " : " + selected.getNcrTitle();

        // Préparation mail
        Mail mail = new Mail();
        mail.addTo((isClient == false ? (ml != null ? ";" + ml.getMlTos() : "") : (mlr != null ? ";" + mlr.getMlTos() : "")));
        mail.addCC(currentStaffMail + (isClient == false ? (ml != null ? ";" + ml.getMlCcs() : "") : (mlr != null ? ";" + mlr.getMlCcs() : "")));
        mail.addCCI((isClient == false ? (ml != null ? ";" + ml.getMlCcis() : "") : (mlr != null ? ";" + mlr.getMlCcis() : "")));
        mail.setSubject(sujet);
        mail.setHtmlMultipart(Mail.createMessageNC(title, selected, null));

        mailsenderController.sendMessage(mail);
    }

    private void handleMailOnValidate() {
        Maillist ml = maillistController.getItemsBy(MLGROUPE, onWaitingSolution, 1, selected.getNcrProcessus());
        Maillist mlr = maillistController.getItemsBy(MLGROUPE, onWaitingSolution, 2, selected.getNcrProcessus());
        if (!selected.getNcrApprouved()) {
            ml = maillistController.getItemsBy(MLGROUPE, onCanceled, 1, selected.getNcrProcessus());
            mlr = maillistController.getItemsBy(MLGROUPE, onCanceled, 2, selected.getNcrProcessus());
        }

        String currentStaffMail = staffAuthController.getStaff().getStMaillist().trim().isEmpty() ? "" : staffAuthController.getStaff().getStMaillist();
        String emetteurStaffMail = selected.getNcrStaff().getStMaillist().trim().isEmpty() ? "" : ";" + selected.getNcrStaff().getStMaillist();

        Boolean isClient
                = (selected.getNcrClientname() == null ? false : !selected.getNcrClientname().trim().isEmpty())
                || (selected.getNcrClientaddress() == null ? false : !selected.getNcrClientaddress().trim().isEmpty())
                || (selected.getNcrClientemail() == null ? false : !selected.getNcrClientemail().trim().isEmpty())
                || (selected.getNcrClientphone() == null ? false : !selected.getNcrClientphone().trim().isEmpty())
                || (selected.getNcrClienttype() == null ? false : !selected.getNcrClienttype().trim().isEmpty());

        // Préparation du message
        String sujet = selected.getNcrId() + (selected.getNcrApprouved() == true ? " - En attente de solution - " : " - Annulée - ") + selected.getNcrTitle();
        String title = selected.getNcrId() + " " + (selected.getNcrApprouved() == true ? "<span style=\"color:green\"> - [APPROUVEE] - </span>" : "<span style=\"color:red\"> - [REFUSEE] - </span>") + " : " + selected.getNcrTitle();

        // Préparation mail
        Mail mail = new Mail();
        mail.addTo((isClient == false ? (ml != null ? ";" + ml.getMlTos() : "") : (mlr != null ? ";" + mlr.getMlTos() : "")));
        mail.addCC(currentStaffMail + emetteurStaffMail + ";" + (isClient == false ? (ml != null ? ";" + ml.getMlCcs() : "") : (mlr != null ? ";" + mlr.getMlCcs() : "")));
        mail.addCCI((isClient == false ? (ml != null ? ";" + ml.getMlCcis() : "") : (mlr != null ? ";" + mlr.getMlCcis() : "")));
        mail.setSubject(sujet);
        mail.setHtmlMultipart(Mail.createMessageNC(title, selected, null));

        mailsenderController.sendMessage(mail);
    }

    /**
     *
     * @param state 1: for action apply, 2: for review action
     * @param ncas
     */
    public void handleMailOnActions(Integer state, List<NonConformiteActions> ncas) {
        Maillist ml = maillistController.getItemsBy(MLGROUPE, onProgressed, 1, selected.getNcrProcessus());
        Maillist mlr = maillistController.getItemsBy(MLGROUPE, onProgressed, 2, selected.getNcrProcessus());

        String currentStaffMail = staffAuthController.getStaff().getStMaillist().trim().isEmpty() ? "" : staffAuthController.getStaff().getStMaillist();
        String emetteurStaffMail = selected.getNcrStaff().getStMaillist().trim().isEmpty() ? "" : ";" + selected.getNcrStaff().getStMaillist();

        Boolean isClient
                = (selected.getNcrClientname() == null ? false : !selected.getNcrClientname().trim().isEmpty())
                || (selected.getNcrClientaddress() == null ? false : !selected.getNcrClientaddress().trim().isEmpty())
                || (selected.getNcrClientemail() == null ? false : !selected.getNcrClientemail().trim().isEmpty())
                || (selected.getNcrClientphone() == null ? false : !selected.getNcrClientphone().trim().isEmpty())
                || (selected.getNcrClienttype() == null ? false : !selected.getNcrClienttype().trim().isEmpty());

        // State
        String strState = "";
        switch (state) {
            case 1:
                strState = "(Action)";
                break;
            case 2:
                strState = "(Ajournée)";
                break;
            default:
                strState = "";
                break;

        }

        // Préparation du message
        String sujet = selected.getNcrId() + " - En Cours - " + selected.getNcrTitle();
        String title = selected.getNcrId() + "<span style=\"color:green\"> - [EN COURS " + strState + "] - </span>" + selected.getNcrTitle();

        // Préparation mail
        Mail mail = new Mail();
        mail.addTo((isClient == false ? (ml != null ? ";" + ml.getMlTos() : "") : (mlr != null ? ";" + mlr.getMlTos() : "")));
        mail.addCC(currentStaffMail + emetteurStaffMail + ";" + (isClient == false ? (ml != null ? ";" + ml.getMlCcs() : "") : (mlr != null ? ";" + mlr.getMlCcs() : "")));
        mail.addCCI((isClient == false ? (ml != null ? ";" + ml.getMlCcis() : "") : (mlr != null ? ";" + mlr.getMlCcis() : "")));
        mail.setSubject(sujet);
        mail.setHtmlMultipart(Mail.createMessageNC(title, selected, ncas));

        mailsenderController.sendMessage(mail);
    }

    public void handleMailOnCloture(List<NonConformiteActions> ncas) {
        Maillist ml = maillistController.getItemsBy(MLGROUPE, onFinished, 1, selected.getNcrProcessus());
        Maillist mlr = maillistController.getItemsBy(MLGROUPE, onFinished, 2, selected.getNcrProcessus());

        String currentStaffMail = staffAuthController.getStaff().getStMaillist().trim().isEmpty() ? "" : staffAuthController.getStaff().getStMaillist();
        String emetteurStaffMail = selected.getNcrStaff().getStMaillist().trim().isEmpty() ? "" : ";" + selected.getNcrStaff().getStMaillist();

        Boolean isClient
                = (selected.getNcrClientname() == null ? false : !selected.getNcrClientname().trim().isEmpty())
                || (selected.getNcrClientaddress() == null ? false : !selected.getNcrClientaddress().trim().isEmpty())
                || (selected.getNcrClientemail() == null ? false : !selected.getNcrClientemail().trim().isEmpty())
                || (selected.getNcrClientphone() == null ? false : !selected.getNcrClientphone().trim().isEmpty())
                || (selected.getNcrClienttype() == null ? false : !selected.getNcrClienttype().trim().isEmpty());

        // Préparation du message
        String sujet = selected.getNcrId() + " - CLOTUREE - " + selected.getNcrTitle();
        String title = selected.getNcrId() + "<span style=\"color:green\"> - [CLOTUREE] - </span>" + selected.getNcrTitle();

        // Préparation mail
        Mail mail = new Mail();
        mail.addTo((isClient == false ? (ml != null ? ";" + ml.getMlTos() : "") : (mlr != null ? ";" + mlr.getMlTos() : "")));
        mail.addCC(currentStaffMail + emetteurStaffMail + ";" + (isClient == false ? (ml != null ? ";" + ml.getMlCcs() : "") : (mlr != null ? ";" + mlr.getMlCcs() : "")));
        mail.addCCI((isClient == false ? (ml != null ? ";" + ml.getMlCcis() : "") : (mlr != null ? ";" + mlr.getMlCcis() : "")));
        mail.setSubject(sujet);
        mail.setHtmlMultipart(Mail.createMessageNC(title, selected, ncas));

        mailsenderController.sendMessage(mail);
    }

    public void handleCroppedImage(CroppedEvent event) {

        croppedImage = (CroppedImage) event.getCroppedImage();
        if (croppedImage == null) {
            JsfUtil.addErrorMessage("Aucune image n'a été rognée !");
            return;
        }
        FileService.tmpRemoveOld(croppedImage);
        //FileService.imgCreate(croppedImage);
    }

    public void handleCropError(CropErrorEvent cropErrorEvent) {
        JsfUtil.addErrorMessage("Crop Error", cropErrorEvent.getCropError().getSize().msg);
    }

    /**
     * On creation complete, clean preview file and move it to the permanent
     * directory
     */
    private void handleCreateCroppedImage() {

        if (croppedImage == null) {
            return;
        }

        FileService.tmpMoveSmqNC(croppedImage);
        croppedImage = null;
    }

    private void handleEditCroppedImage(String previewFilename) {

        handleCreateCroppedImage();

        // Suppression du fichier existant avant
        if (previewFilename != null) {
            FileService.deleteImgFromSMQNC(previewFilename);
        }
    }

    /**
     * ************************************************************************
     * CRUD OPTIONS
     *
     * ************************************************************************
     */
    public void create() {
        // Set time on creation action
        selected.setNcrChanged(new Date());
        selected.setNcrCreated(new Date());
        selected.setNcrState(new IsmNcrstate(IsmNcrstate.CREATE_ID));
        if (croppedImage != null) {
            if (selected.getNcrLink() == null || selected.getNcrLink().trim().isEmpty()) {
                selected.setNcrLink(FileService.filenameComplete(croppedImage));
            }
        }
        // Reset Link if undefined
        if(selected.getNcrLink()!=null){
            if(selected.getNcrLink().trim().isEmpty()){
                selected.setNcrLink(null);
            }
        }
        

        persist(PersistAction.CREATE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestPersistenceCreatedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestPersistenceCreatedDetail")
                + selected.getNcrTitle());

        if (!JsfUtil.isValidationFailed()) {
            // Récupère l'élément nouvellement crée
            selected = getLast();
            handleCreateCroppedImage();
            handleMailOnCreate();

            if (isReleaseSelected) {
                selected = null;
            }
            if (isOnMultiCreation) {
                selected = new NonConformiteRequest();
            }
        }
    }

    public void createUnReleasded() {
        isReleaseSelected = false;
        create();
    }

    public void update() {
        // Set time on creation action
        selected.setNcrChanged(new Date());
        String fileToRemove = null;
        if (croppedImage != null) {
            if (selected.getNcrLink() == null || selected.getNcrLink().trim().isEmpty()) {
            } else {
                fileToRemove = selected.getNcrLink();
            }
            selected.setNcrLink(FileService.filenameComplete(croppedImage));
        }

        persist(PersistAction.UPDATE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestPersistenceUpdatedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestPersistenceUpdatedDetail")
                + selected.getNcrTitle());

        if (!JsfUtil.isValidationFailed()) {
            handleEditCroppedImage(fileToRemove);
        }
    }

    public void updateOnValidate() {
        selected.setNcrChanged(new Date());
        selected.setNcrapprouvedDate(new Date());
        if (selected.getNcrApprouved()) {
            selected.setNcrState(new IsmNcrstate(IsmNcrstate.WAITFORSOLUTION_ID));

        } else {
            selected.setNcrState(new IsmNcrstate(IsmNcrstate.CANCEL_ID));
        }
        update();
        handleMailOnValidate();
    }

    public void destroy() {
        NonConformiteRequest ncrs = selected;

        persist(PersistAction.DELETE,
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestPersistenceDeletedSummary"),
                ResourceBundle.getBundle(JsfUtil.BUNDLE).
                        getString("NonConformiteRequestPersistenceDeletedDetail")
                + selected.getNcrTitle());
        if (!JsfUtil.isValidationFailed()) {

            if (ncrs.getNcrLink() == null || ncrs.getNcrLink().trim().isEmpty()) {
            } else {
                FileService.deleteImgFromSMQNC(ncrs.getNcrLink());
            }

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

    /**
     * ************************************************************************
     * JPA
     *
     * ************************************************************************
     */
    /**
     *
     * @param id non conformite request key
     * @return corresponding non conformite request object
     */
    public NonConformiteRequest getNonConformiteRequest(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public NonConformiteRequest getLast() {
        return getFacade().findLast();
    }

    public List<NonConformiteRequest> getItems() {
        items = getFacade().findAll();
        return items;
    }

    public List<NonConformiteRequest> getItemsByLastChanged() {
        items = getFacade().findAllByLastChanged();
        return items;
    }

    public List<NonConformiteRequest> getItemsByCode(String code) {
        return getFacade().findByCode(code);
    }

    public List<NonConformiteRequest> getItemsByCode(String code, Company company) {
        return getFacade().findByCode(Integer.valueOf(code), company);
    }

    public Integer countByProcessus(String processusCode) {
        return getFacade().countByState(processusCode);
    }

    public Integer countByStaff(String staffCode) {
        return getFacade().countByState(staffCode);
    }

    public Integer countByState(String stateCode) {
        return getFacade().countByState(stateCode);
    }

    public List<NonConformiteRequest> getItemsByDesignation(String designation) {
        return getFacade().findByDesignation(designation);
    }

    public List<NonConformiteRequest> getItemsByDesignation(String designation, Company company) {
        return getFacade().findByDesignation(designation, company);
    }

    public List<NonConformiteRequest> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<NonConformiteRequest> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * ************************************************************************
     * GETTER / SETTER
     *
     * ************************************************************************
     */
    /**
     *
     * @return selected non conformite request
     */
    public NonConformiteRequest getSelected() {
        if (selected == null) {
            selected = new NonConformiteRequest();
        }
        return selected;
    }

    public void setSelected(NonConformiteRequest selected) {
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

    /// ////////////////////////////////////////////////////////////////////////
    ///
    /// Contrôle graphique renderer state
    ///
    /// ////////////////////////////////////////////////////////////////////////
    /**
     * *
     *
     * @param from
     * @param to
     * @return
     */
    public Integer getCountItemsCreateInRange(Date from, Date to) {
        List<NonConformiteRequest> l = getFacade().itemsCreateInRange(from, to);
        if (l == null || l.isEmpty()) {
            return 0;
        }
        return l.size();
    }

    public List<NonConformiteRequest> getItemsCreateInRange(Date from, Date to) {
        return getFacade().itemsCreateInRange(from, to);
    }

    public Integer getCountItemsApprouvedInRange(Date from, Date to) {
        List<NonConformiteRequest> l = getFacade().itemsApprouvedInRange(from, to);
        if (l == null || l.isEmpty()) {
            return 0;
        }
        return l.size();
    }

    public List<NonConformiteRequest> getItemsApprouvedInRange(Date from, Date to) {
        return getFacade().itemsApprouvedInRange(from, to);
    }

    /**
     *
     * @param state is one of (A, B, C, D, E) respectively (Créé, en attente de
     * solution, en cours, terminé, annulé)
     * @param from
     * @param to
     * @return
     */
    public Integer getCountItemsStateFromTo(String state, Date from, Date to) {
        List<NonConformiteRequest> l = getFacade().itemsStateInRange(state, from, to);
        if (l == null || l.isEmpty()) {
            return 0;
        }
        return l.size();
    }

    public List<NonConformiteRequest> getItemsStateFromTo(String state, Date from, Date to) {
        return getFacade().itemsStateInRange(state, from, to);
    }

    /**
     *
     * @param state is one of (A, B, C, D, E) respectively (Créé, en attente de
     * solution, en cours, terminé, annulé)
     * @param from
     * @param to
     * @return
     */
    public Integer getCountItemsStateChangeInRange(String state, Date from, Date to) {
        List<NonConformiteRequest> l = getFacade().itemsStateInChangeRange(state, from, to);
        if (l == null || l.isEmpty()) {
            return 0;
        }
        return l.size();
    }

    public List<NonConformiteRequest> getItemsStateChangeInRange(String state, Date from, Date to) {
        return getFacade().itemsStateInChangeRange(state, from, to);
    }

    /**
     * *
     *
     * @param from
     * @param to
     * @param processus
     * @return
     */
    public List<NonConformiteRequest> getItemsCreateInRangeByProcessus(Date from, Date to, Processus processus) {
        return getFacade().itemsCreateInRangeByProcessus(from, to, processus);
    }

    public List<NonConformiteRequest> getItemsApprouvedInRangeByProcessus(Date from, Date to, Processus processus) {
        return getFacade().itemsApprouvedInRangeByProcessus(from, to, processus);
    }

    /**
     *
     * @param state is one of (A, B, C, D, E) respectively (Créé, en attente de
     * solution, en cours, terminé, annulé)
     * @param from
     * @param to
     * @param processus
     * @return
     */
    public List<NonConformiteRequest> getItemsStateFromToByProcessus(String state, Date from, Date to, Processus processus) {
        return getFacade().itemsStateInRangeByProcessus(state, from, to, processus);
    }

    /**
     *
     * @param state is one of (A, B, C, D, E) respectively (Créé, en attente de
     * solution, en cours, terminé, annulé)
     * @param from
     * @param to
     * @param processus
     * @return
     */
    public List<NonConformiteRequest> getItemsStateChangeInRangeByProcessus(String state, Date from, Date to, Processus processus) {
        return getFacade().itemsStateInChangeRangeByProcessus(state, from, to, processus);
    }

    public Integer countProcessusInState(Processus processus, String state) {
        return getFacade().countProcessusInState(processus, state);
    }

    public Part getUploadedPartFile() {
        return uploadedPartFile;
    }

    public void setUploadedPartFile(Part uploadedPartFile) {
        this.uploadedPartFile = uploadedPartFile;
    }

    public File getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(File uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getUploadedStringFile() {
        return uploadedStringFile;
    }

    public void setUploadedStringFile(String uploadedStringFile) {
        this.uploadedStringFile = uploadedStringFile;
    }

    ////////////////////////////////////////////////////////////////////////////
    /// Injection
    ///
    ////////////////////////////////////////////////////////////////////////////
    public void setMailsenderController(MailsenderController mailsenderController) {
        this.mailsenderController = mailsenderController;
    }

    public void setMaillistController(MaillistController maillistController) {
        this.maillistController = maillistController;
    }

    public void setStaffAuthController(StaffAuthController staffAuthController) {
        this.staffAuthController = staffAuthController;
    }

    public void setIsmNcrstateController(IsmNcrstateController ismNcrstateController) {
        this.ismNcrstateController = ismNcrstateController;
    }

    /// ////////////////////////////////////////////////////////////////////////
    //
    /// LAZY
    ///
    /// ////////////////////////////////////////////////////////////////////////
    public NonConformiteRequestLazyModel getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(NonConformiteRequestLazyModel lazyModel) {
        this.lazyModel = lazyModel;
    }

}
