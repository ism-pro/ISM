/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.view.process.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import org.ism.jsf.process.ctrl.AnalysePointController;
import org.ism.jsf.process.ctrl.AnalyseTypeController;
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
     * @param typeList
     * @param pointList 
     */
    private void manageTransfertFromPickList(List<AnalyseType> typeList, List<AnalysePoint> pointList) {
        // Finish running if none of the two list is defined
        if (typeList.isEmpty() && pointList.isEmpty()) {
            return;
        }
        
        Integer aaCount = analyseAllowedController.getCount();

        // Append new Item to list
        
        if (displayMode == 1) {
            
            for (AnalyseType at : typeList) {
                AnalyseAllowed aa = new AnalyseAllowed(aaCount+preset.size(), false, 0, false, 0, false, 0, false, 0, false, null, null);
                aa.setAaPoint(selected.getAaPoint());
                aa.setAaType(at);
                preset.add(aa);
            }
        } else {
            for (AnalysePoint ap : pointList) {
                AnalyseAllowed aa = new AnalyseAllowed(aaCount+preset.size(), false, 0, false, 0, false, 0, false, 0, false, null, null);
                aa.setAaId(aaCount+preset.size());
                aa.setAaPoint(ap);
                aa.setAaType(selected.getAaType());
                preset.add(aa);
            }
        }
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
        analyseTypeSource = analyseTypeController.getItems();
        analyseTypeTarget = new ArrayList<>();
        if (point != null) {
            // Recherche des données affectée et non affectée
            List<AnalyseAllowed> lstPA = analyseAllowedController.getItemsByPoint(point);
            preset = lstPA;

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
     * handleAnalyseTypeChanged allow to react over a Type selection while
     * displayMode is 2
     *
     * @param event corresponding event when valuage changed occured
     */
    public void handleAnalyseTypeChanged(ValueChangeEvent event) {
        AnalyseType type = (AnalyseType) event.getNewValue();
        getSelected().setAaType(type);
        analysePointSource = analysePointController.getItems();
        analysePointTarget = new ArrayList<>();
        if (type != null) {
            // Recherche des données affectée et non affectée
            List<AnalyseAllowed> lstPA = analyseAllowedController.getItemsByType(type);
            preset = lstPA;
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
     * 
     * @param event 
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
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void handleAnalysePointUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void handleAnalysePointReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    /**
     * Managing TYPE
     *
     * @param event
     */
    public void handleAnalyseTypeTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        List<AnalyseType> typeList = new ArrayList<>();
        for (Object item : event.getItems()) {
            builder.append(((AnalyseType) item).toString()).append("<br />");
            typeList.add((AnalyseType) item);
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);

        /// Managing transfert Type
        manageTransfertFromPickList(typeList, null);
    }

    public void handleAnalyseTypeSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void handlevUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void handleAnalyseTypeReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public void handleTableSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Table Selected Row", null));
    }

    public void handleTableUnSelect(UnselectEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Table Selected Row", null));
    }

    /* ========================================================================
   
    =========================================================================*/
    /**
     * Create
     */
    public void create() {

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

    /* ========================================================================
   
    =========================================================================*/
    /**
     * *
     *
     * @return selected
     */
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

    ////////////////////////////////////////////////////////////////////////////
    /// Manage Injection
    ///
    ////////////////////////////////////////////////////////////////////////////
    public void setAnalysePointController(AnalysePointController analysePointController) {
        this.analysePointController = analysePointController;
    }

    public void setAnalyseTypeController(AnalyseTypeController analyseTypeController) {
        this.analyseTypeController = analyseTypeController;
    }

    public void setAnalyseAllowedController(AnalyseAllowedController analyseAllowedController) {
        this.analyseAllowedController = analyseAllowedController;
    }

    public void setStaffAuthController(StaffAuthController staffAuthController) {
        this.staffAuthController = staffAuthController;
    }
}
