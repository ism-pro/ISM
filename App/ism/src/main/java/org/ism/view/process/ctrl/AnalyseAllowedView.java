/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.view.process.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.ism.entities.process.ctrl.AnalyseAllowed;
import org.ism.entities.process.ctrl.AnalysePoint;
import org.ism.entities.process.ctrl.AnalyseType;
import org.ism.jsf.hr.StaffAuthController;
import org.ism.jsf.process.ctrl.AnalyseAllowedController;
import org.ism.jsf.process.ctrl.AnalyseDataController;
import org.ism.jsf.process.ctrl.AnalysePointController;
import org.ism.jsf.process.ctrl.AnalyseTypeController;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author r.hendrick
 */
@ManagedBean(name = "analyseAllowedView")
@SessionScoped
public class AnalyseAllowedView implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{analysePointController}")
    private AnalysePointController analysePointController;

    @ManagedProperty(value = "#{analyseTypeController}")
    private AnalyseTypeController analyseTypeController;

    @ManagedProperty(value = "#{analyseAllowedController}")
    private AnalyseAllowedController analyseAllowedController;

    @ManagedProperty(value = "#{analyseDataController}")
    private AnalyseDataController analyseDataController;

    @ManagedProperty(value = "#{staffAuthController}")
    private StaffAuthController staffAuthController;

    /**
     * Current analyse allowed use for the controller view
     */
    private AnalyseAllowed selected = null;

    /**
     * displayMode allow to specify which mode is to be set for display users
     * <br>
     * 1 by document affect staff, <br>
     * 2 by staff affect document,<br>
     * <br>
     * Default is 1.
     *
     */
    private Integer displayMode = 1;

    /**
     * Staff Sources contains all the unaffected staff for a document
     */
    List<AnalysePoint> analysePointSource = new ArrayList<>();
    /**
     * Staff Target contains all the affected staff for a document
     */
    List<AnalysePoint> analysePointTarget = new ArrayList<>();
    /**
     * Document Sources contains all the unaffected staff for a document
     */
    List<AnalyseType> analyseTypeSource = new ArrayList<>();
    /**
     * Document Target contains all the affected staff for a document
     */
    List<AnalyseType> analyseTypeTarget = new ArrayList<>();

    /**
     * StaffModel is
     */
    private DualListModel<AnalysePoint> analysePointModel;
    /**
     * DocumentModel is
     */
    private DualListModel<AnalyseType> analyseTypeModel;
    /**
     * Progress value interac in some long process with bean in order to informe
     * user
     */
    private Integer progressValue = 0;

    private List<AnalyseAllowed> preset = new ArrayList<>();
    /**
     * Preset Index Marker contains corresponding index change from preset table
     * allowing update only on line changed
     */
    private List<AnalyseAllowed> presetMarker = new ArrayList<>();
    private List<AnalyseAllowed> selections = new ArrayList<>();

    /**
     * Convenient action to do fist call for initialisation of component
     */
    @PostConstruct
    public void init() {
        // Initialize sources
        analysePointSource = analysePointController.getItems();
        analyseTypeSource = analyseTypeController.getItems();

        // Initialise model
        analysePointModel = new DualListModel<>(analysePointSource, analysePointTarget);
        analyseTypeModel = new DualListModel<>(analyseTypeSource, analyseTypeTarget);
    }

    /**
     * Manage transfert from pick list depending on display mode
     *
     * @param typeList list of analyse type
     * @param pointList list of sample point
     */
    private void manageTransfertFromPickList(List<AnalyseType> typeList, List<AnalysePoint> pointList) {
        // Finish running if none of the two list is defined
        if(typeList==null && pointList==null)   return;

        Integer aaCount = analyseAllowedController.getNextKey() + 500;

        // Mode Point d'échantillonnage
        if (displayMode == 1) {
            Boolean isAdd = false; // Correspond à une suppression
            if (analyseTypeModel.getTarget() != null) {
                if (analyseTypeModel.getTarget().size() > preset.size()) { // add
                    isAdd = true; // Correspond à un ajout 
                }
            }
            if (isAdd) { // En cas d'ajout
                for (AnalyseType at : typeList) {
                    AnalyseAllowed aa = new AnalyseAllowed(aaCount + preset.size(), false, 0, false, 0, false, 0, false, 0, false, null, null);
                    aa.setAaPoint(selected.getAaPoint());
                    aa.setAaType(at);
                    aa.setAaObservation("");
                    aa.setAaCompany(staffAuthController.getCompany());
                    // restaure des liaison existante
                    List<AnalyseAllowed> laa = analyseAllowedController.getItemsByPointType(selected.getAaPoint(), at);
                    if (laa != null && !laa.isEmpty()) {
                        aa = laa.get(0);
                    }
                    preset.add(aa);
                }
            } else { // En cas de suppression
                List<Integer> index;
                for (AnalyseType at : typeList) {
                    for (AnalyseAllowed aa : preset) {
                        if (Objects.equals(at.getAtId(), aa.getAaType().getAtId())) {
                            preset.remove(aa);
                            if (presetMarker.contains(aa)) {
                                presetMarker.remove(aa);
                            }
                            break;
                        }
                    }
                }
            }
        } else {
            Boolean isAdd = false; // Correspond à une suppression
            if (analysePointModel.getTarget() != null) {
                if (analysePointModel.getTarget().size() > preset.size()) { // add
                    isAdd = true; // Correspond à un ajout 
                }
            }
            if (isAdd) { // En cas d'ajout
                for (AnalysePoint ap : pointList) {
                    AnalyseAllowed aa = new AnalyseAllowed(aaCount + preset.size(), false, 0, false, 0, false, 0, false, 0, false, null, null);
                    aa.setAaPoint(ap);
                    aa.setAaType(selected.getAaType());
                    aa.setAaObservation("");
                    aa.setAaCompany(staffAuthController.getCompany());
                    // restaure des liaison existante
                    List<AnalyseAllowed> laa = analyseAllowedController.getItemsByPointType(ap, selected.getAaType());
                    if (laa != null && !laa.isEmpty()) {
                        aa = laa.get(0);
                    }
                    preset.add(aa);
                }
            } else { // En cas de suppression
                List<Integer> index;
                for (AnalysePoint ap : pointList) {
                    for (AnalyseAllowed aa : preset) {
                        if (Objects.equals(aa.getAaPoint().getApId(), ap.getApId())) {
                            preset.remove(aa);
                            if (presetMarker.contains(aa)) {
                                presetMarker.remove(aa);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * On display mode change the preset, the presetMarker have to be initialize
     * as well as the corresponding picklist
     */
    public void onDisplayModeChanged(){
        selected.setAaPoint(null);
        selected.setAaType(null);
        preset = new ArrayList<>();
        presetMarker = new ArrayList<>();
    }
    
    /**
     * On analyse point changed method know what to do when selected point item
     * change. <br>
     * In particular, initialize source, target, preset, presetMarker
     */
    private void onAnalysePointChanged() {
        analyseTypeSource = analyseTypeController.getItems();
        analyseTypeTarget = new ArrayList<>();
        if (getSelected().getAaPoint() != null) {
            // Recherche des données affectée et non affectée
            List<AnalyseAllowed> lstPA = analyseAllowedController.getItemsByPoint(getSelected().getAaPoint());
            preset = lstPA;
            presetMarker = new ArrayList<>();

            if (lstPA != null) {
                for (AnalyseAllowed pa : lstPA) {
                    analyseTypeTarget.add(pa.getAaType());
                }
                analyseTypeSource.removeAll(analyseTypeTarget);
            }
        }
        analyseTypeModel = new DualListModel<>(analyseTypeSource, analyseTypeTarget);

    }

    /**
     * Handle Point change allow to react over a Point sample selection while
     * displayMode is 1
     *
     * @param event corresponding event when valuage changed occured
     */
    public void handleAnalysePointChanged(ValueChangeEvent event) {
        AnalysePoint point = (AnalysePoint) event.getNewValue();
        getSelected().setAaPoint(point);
        onAnalysePointChanged();
    }

    /**
     * On analyse type changed method know what to do when selected type item
     * change. <br>
     * In particular, initialize source, target, preset, presetMarker
     */
    private void onAnalyseTypeChanged() {
        analysePointSource = analysePointController.getItems();
        analysePointTarget = new ArrayList<>();
        if (getSelected().getAaType() != null) {
            // Recherche des données affectée et non affectée
            List<AnalyseAllowed> lstPA = analyseAllowedController.getItemsByType(getSelected().getAaType());
            preset = lstPA;
            presetMarker = new ArrayList<>();

            if (lstPA != null) {
                for (AnalyseAllowed pa : lstPA) {
                    analysePointTarget.add(pa.getAaPoint());
                }
                analysePointSource.removeAll(analysePointTarget);
            }
        }
        analysePointModel = new DualListModel<>(analysePointSource, analysePointTarget);
    }

    /**
     * handleAnalyseTypeChanged allow to react over a Type selection while
     * displayMode is 2
     *
     * @param event corresponding event when valuage changed occured
     */
    public void handleAnalyseTypeChanged(ValueChangeEvent event) {
        AnalyseType type = (AnalyseType) event.getNewValue();
        getSelected().setAaType(type);
        onAnalyseTypeChanged();
    }

    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 
    // Gestion par Point d'échantillonage
    // 
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Handle analyse point transfert event
     *
     * @param event contains the transfert object
     */
    public void handleAnalysePointTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        List<AnalysePoint> pointList = new ArrayList<>();
        for (Object item : event.getItems()) {
            builder.append(((AnalysePoint) item).toString()).append("<br />");
            pointList.add((AnalysePoint) item);
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);

        /// Manage Transfert
        manageTransfertFromPickList(null, pointList);
    }

    public void handleAnalysePointSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Point échant. sélectionné : ", event.getObject().toString()));
    }

    public void handleAnalysePointUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Point échant. désélectionné : ", event.getObject().toString()));
    }

    public void handleAnalysePointReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liste réordonnée", null));
    }

    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 
    // Gestion par Type d'echantillonage
    // 
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Handle analyse type on transfer event
     *
     * @param event contain type to be transfer
     */
    public void handleAnalyseTypeTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        List<AnalyseType> typeList = new ArrayList<>();
        String transfered = "";
        for (Object item : event.getItems()) {
            builder.append(((AnalyseType) item).toString()).append("<br />");
            typeList.add((AnalyseType) item);
            transfered += " / " + (AnalyseType) item;
        }

        /// Managing transfert Type
        manageTransfertFromPickList(typeList, null);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liste des type d'analyse transférée : ", transfered));

    }

    public void handleAnalyseTypeSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        AnalyseType at = (AnalyseType) event.getObject();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Type analyse sélectionnée : " + at, event.getObject().toString()));
    }

    public void handleAnalyseTypeUnSelect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        AnalyseType at = (AnalyseType) event.getObject();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Type analyse désélectionnée : " + at, event.getObject().toString()));
    }

    public void handleAnalyseTypeReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liste de type d'analyse réordonnée", null));
    }

    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 
    // GESTION DE LA TABLE D'EDITION
    // 
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    public void handleTableSelect(SelectEvent event) {
        AnalyseAllowed aa = (AnalyseAllowed) event.getObject();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyse possible sélectionnée : " + aa, null));
    }

    public void handleTableUnSelect(UnselectEvent event) {
        AnalyseAllowed aa = (AnalyseAllowed) event.getObject();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyse possible désélectionnée : " + aa, null));
    }

    public void onRowEdit(RowEditEvent event) {
        AnalyseAllowed aa = (AnalyseAllowed) event.getObject();
        // Add edited row on presetMarker if already exist in database for update
        if (analyseAllowedController.contains(aa)) {
            presetMarker.add(aa);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyse possible édition ligne : " + aa, null));
    }

    public void onRowCancel(RowEditEvent event) {
        AnalyseAllowed aa = (AnalyseAllowed) event.getObject();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyse possible annulation edition ligne : " + aa, null));
    }

    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 
    // CRUD
    // 
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Create is base on the current existing table which is going to be modify
     *
     * - A list of removed existing link will be create - if link was already
     * used than the link is only delete in software, - otherwise link is remove
     * from allowed link
     *
     * - Each row is going to be check if already exist, if - existing row are
     * going to be modify,
     *
     */
    public void create() {

        // Mode point d'échantillonnage fixe
        if (this.displayMode == 1) {
            // Get list a allowed item to be removed
            List<AnalyseAllowed> listOfLinkToBeRemoved = analyseAllowedController.getItemsByPoint(selected.getAaPoint());
            listOfLinkToBeRemoved.removeAll(preset);

            // Remove items without link and update as delete the one with link
            for (AnalyseAllowed aa : listOfLinkToBeRemoved) {
                analyseAllowedController.setSelected(aa);
                if (!analyseDataController.contains(selected.getAaPoint(), aa.getAaType())) {
                    analyseAllowedController.destroy();
                } else {
                    analyseAllowedController.getSelected().setAaDeleted(true);
                    analyseAllowedController.update();
                }
            }

            // Create new link from the preset table and update the id
            List<AnalyseAllowed> listOfNewLinkToAdd = new ArrayList();
            listOfNewLinkToAdd.addAll(preset);
            listOfNewLinkToAdd.removeAll(analyseAllowedController.getItemsByPoint(selected.getAaPoint()));

            for (AnalyseAllowed aa : listOfNewLinkToAdd) {
                analyseAllowedController.setSelected(aa);
                analyseAllowedController.create();
                // Change the preset id
                for (AnalyseAllowed aap : preset) {
                    if (aap == aa) {
                        aap.setAaId(analyseAllowedController.getItemsByPointType(selected.getAaPoint(), aa.getAaType()).get(0).getAaId());
                    }
                }
            }

            // Now update all the preset with new value
            for (AnalyseAllowed aa : presetMarker) {
                analyseAllowedController.setSelected(aa);
                analyseAllowedController.update();
            }

            // Recall init 
            onAnalysePointChanged();

        } // Mode type échantillonage
        else if (this.displayMode == 2) {
            // Get list a allowed item to be removed
            List<AnalyseAllowed> listOfLinkToBeRemoved = analyseAllowedController.getItemsByType(selected.getAaType());
            listOfLinkToBeRemoved.removeAll(preset);

            // Remove items without link and update as delete the one with link
            for (AnalyseAllowed aa : listOfLinkToBeRemoved) {
                analyseAllowedController.setSelected(aa);
                if (!analyseDataController.contains(aa.getAaPoint(), selected.getAaType())) {
                    analyseAllowedController.destroy();
                } else {
                    analyseAllowedController.getSelected().setAaDeleted(true);
                    analyseAllowedController.update();
                }
            }

            // Create new link from the preset table and update the id
            List<AnalyseAllowed> listOfNewLinkToAdd = new ArrayList();
            listOfNewLinkToAdd.addAll(preset);
            listOfNewLinkToAdd.removeAll(analyseAllowedController.getItemsByType(selected.getAaType()));

            for (AnalyseAllowed aa : listOfNewLinkToAdd) {
                analyseAllowedController.setSelected(aa);
                analyseAllowedController.create();
                // Change the preset id
                for (AnalyseAllowed aap : preset) {
                    if (aap == aa) {
                        aap.setAaId(analyseAllowedController.getItemsByPointType(aa.getAaPoint(), selected.getAaType()).get(0).getAaId());
                    }
                }
            }

            // Now update all the preset with new value
            for (AnalyseAllowed aa : presetMarker) {
                analyseAllowedController.setSelected(aa);
                analyseAllowedController.update();
            }

            // Recall init 
            onAnalyseTypeChanged();
        }
    }

    /**
     * update
     */
    public void update() {

    }

    /**
     * Destroy the user if not appearring in the list
     */
    public void destroy() {

    }

    /**
     * updateChange is convenient method which check taget data to know what to
     * preserve from rights. <br>
     * 1. Depending on display mode do next operation,<br>
     * 2. Remove all the existing access rights,<br>
     * 3. Recreate all new defined rights
     */
    public void updateChange() {
        progressValue = 0;
        List<AnalyseAllowed> lstPA = new ArrayList<>();
        switch (displayMode) {
            case 1: // Case by Point
                AnalysePoint point = selected.getAaPoint();
                lstPA = analyseAllowedController.getItemsByPoint(point);
                // Remove existing content
                if (lstPA != null) {
                    progressValue = 0;
                    for (AnalyseAllowed pa : lstPA) {
                        analyseAllowedController.setSelected(pa);
                        analyseAllowedController.destroy();
                        progressValue += (50 / lstPA.size());
                    }
                }

                progressValue = 50;
                // Recreate target
                analyseTypeSource = analyseTypeModel.getSource();
                analyseTypeTarget = analyseTypeModel.getTarget();

                if (!analyseTypeTarget.isEmpty()) {
                    for (AnalyseType type : analyseTypeTarget) {
                        AnalyseAllowed pa = new AnalyseAllowed();
                        pa.setAaCompany(staffAuthController.getCompany());
                        pa.setAaType(type);
                        pa.setAaPoint(point);
                        analyseAllowedController.setIsOnMultiCreation(Boolean.TRUE);
                        analyseAllowedController.setSelected(pa);
                        analyseAllowedController.create();
                        progressValue += (50 / analyseTypeTarget.size());
                    }
                }
                progressValue = 100;
                break;
            case 2: // Case by document
                AnalyseType type = selected.getAaType();
                lstPA = analyseAllowedController.getItemsByType(type);
                // Remove existing content
                if (lstPA != null) {
                    progressValue = 0;
                    for (AnalyseAllowed pa : lstPA) {
                        analyseAllowedController.setSelected(pa);
                        analyseAllowedController.destroy();
                        progressValue += (50 / lstPA.size());
                    }
                }

                progressValue = 50;
                // Recreate target
                analysePointSource = analysePointModel.getSource();
                analysePointTarget = analysePointModel.getTarget();

                if (!analysePointTarget.isEmpty()) {
                    for (AnalysePoint pointItr : analysePointTarget) {
                        AnalyseAllowed pa = new AnalyseAllowed();
                        pa.setAaCompany(staffAuthController.getCompany());
                        pa.setAaType(type);
                        pa.setAaPoint(pointItr);
                        analyseAllowedController.setIsOnMultiCreation(Boolean.TRUE);
                        analyseAllowedController.setSelected(pa);
                        analyseAllowedController.create();
                        progressValue += (50 / analyseTypeTarget.size());
                    }
                }
                progressValue = 100;
                break;
            default:
                break;
        }
    }

    /**
     * Get corresponding Document Explorer from a str which is suppose to have
     * double [ ] in which is contain a number corresponding to document.
     *
     * @param str contain the number of the document
     * @return document explorer
     */
    private AnalyseType getAnalyseTypeFromStr(String str) {
        String sub = str.split("[")[1].split("]")[0].replace("]", "").trim();
        return analyseTypeController.getAnalyseType(Integer.valueOf(sub));
    }

    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 
    // ASSESSEUR MUTATEUR
    // 
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    public AnalyseAllowed getSelected() {
        if (selected == null) {
            selected = new AnalyseAllowed();
        }
        return selected;
    }

    public void setSelected(AnalyseAllowed processAccess) {
        this.getSelected();
        this.selected = processAccess;
    }

    public Integer getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(Integer displayMode) {
        this.displayMode = displayMode;
    }

    public List<AnalysePoint> getAnalysePointSource() {
        return analysePointSource;
    }

    public void setAnalysePointSource(List<AnalysePoint> analysePointSource) {
        this.analysePointSource = analysePointSource;
    }

    public List<AnalysePoint> getAnalysePointTarget() {
        return analysePointTarget;
    }

    public void setAnalysePointTarget(List<AnalysePoint> analysePointTarget) {
        this.analysePointTarget = analysePointTarget;
    }

    public List<AnalyseType> getAnalyseTypeSource() {
        return analyseTypeSource;
    }

    public void setAnalyseTypeSource(List<AnalyseType> analyseTypeSource) {
        this.analyseTypeSource = analyseTypeSource;
    }

    public List<AnalyseType> getAnalyseTypeTarget() {
        return analyseTypeTarget;
    }

    public void setAnalyseTypeTarget(List<AnalyseType> analyseTypeTarget) {
        this.analyseTypeTarget = analyseTypeTarget;
    }

    public DualListModel<AnalysePoint> getAnalysePointModel() {
        return analysePointModel;
    }

    public void setAnalysePointModel(DualListModel<AnalysePoint> analysePointModel) {
        this.analysePointModel = analysePointModel;
    }

    public DualListModel<AnalyseType> getAnalyseTypeModel() {
        return analyseTypeModel;
    }

    public void setAnalyseTypeModel(DualListModel<AnalyseType> analyseTypeModel) {
        this.analyseTypeModel = analyseTypeModel;
    }

    public Integer getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(Integer progressValue) {
        this.progressValue = progressValue;
    }

    public List<AnalyseAllowed> getPreset() {
        return preset;
    }

    public void setPreset(List<AnalyseAllowed> preset) {
        this.preset = preset;
    }

    public List<AnalyseAllowed> getSelections() {
        return selections;
    }

    public void setSelections(List<AnalyseAllowed> selections) {
        this.selections = selections;
    }

    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // 
    // INJECTION
    // 
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////////////////////////////////////////
    public void setAnalysePointController(AnalysePointController analysePointController) {
        this.analysePointController = analysePointController;
    }

    public void setAnalyseTypeController(AnalyseTypeController analyseTypeController) {
        this.analyseTypeController = analyseTypeController;
    }

    public void setAnalyseAllowedController(AnalyseAllowedController analyseAllowedController) {
        this.analyseAllowedController = analyseAllowedController;
    }

    public void setAnalyseDataController(AnalyseDataController analyseDataController) {
        this.analyseDataController = analyseDataController;
    }

    public void setStaffAuthController(StaffAuthController staffAuthController) {
        this.staffAuthController = staffAuthController;
    }
}
