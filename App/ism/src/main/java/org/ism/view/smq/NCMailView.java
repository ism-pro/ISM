/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.view.smq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.ism.domain.mail.MailDefiner;
import org.ism.entities.admin.Maillist;
import org.ism.entities.hr.Staff;
import org.ism.entities.hr.StaffGroups;
import org.ism.entities.smq.Processus;
import org.ism.jsf.admin.MailaddressController;
import org.ism.jsf.admin.MaillistController;
import org.ism.jsf.app.IsmMailtypeController;
import org.ism.jsf.hr.StaffAuthController;
import org.ism.jsf.hr.StaffController;
import org.ism.jsf.hr.StaffGroupsController;
import org.ism.jsf.smq.ProcessusController;
import org.ism.jsf.smq.nc.NonConformiteRequestController;
import org.ism.jsf.util.JsfUtil;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

/**
 * NonConformitesMailConfig class
 *
 * @author r.hendrick
 */
@ManagedBean(name = "ncMailView")
@SessionScoped
public class NCMailView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Mail list contains all defined groupe with event and processus concern
     */
    @ManagedProperty(value = "#{maillistController}")
    private MaillistController maillistController;

    /**
     * Mail Address contains all possible linked to mail list
     */
    @ManagedProperty(value = "#{mailaddressController}")
    private MailaddressController mailaddressController;

    /**
     * Mail Address contains all possible linked to mail list
     */
    @ManagedProperty(value = "#{ismMailtypeController}")
    private IsmMailtypeController ismMailtypeController;

    /**
     * Contains list of available processus
     */
    @ManagedProperty(value = "#{processusController}")
    private ProcessusController processusController;

    /**
     * Contains list of available staff
     */
    @ManagedProperty(value = "#{staffController}")
    private StaffController staffController;

    /**
     * Contains list of staff Groups
     */
    @ManagedProperty(value = "#{staffGroupsController}")
    private StaffGroupsController staffGroupsController;

    /**
     * Contains current user information
     */
    @ManagedProperty(value = "#{staffAuthController}")
    private StaffAuthController staffAuthController;

    /**
     * Specifiy the current selected processus
     */
    private Processus selectedProcessus;

    /**
     * Represent a list of staff
     */
    MailDefiner mailCreate;
    MailDefiner mailWaiting;
    MailDefiner mailProcessing;
    MailDefiner mailFinished;
    MailDefiner mailCanceled;

    private String MLGROUPE = NonConformiteRequestController.MLGROUPE;
    private String A = NonConformiteRequestController.onCreated;
    private String B = NonConformiteRequestController.onWaitingSolution;
    private String C = NonConformiteRequestController.onProgressed;
    private String D = NonConformiteRequestController.onFinished;
    private String E = NonConformiteRequestController.onCanceled;

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Container
    // 
    //
    // /////////////////////////////////////////////////////////////////////////
    @PostConstruct
    private void init() {
        mailCreate = new MailDefiner();
        mailWaiting = new MailDefiner();
        mailProcessing = new MailDefiner();
        mailFinished = new MailDefiner();
        mailCanceled = new MailDefiner();
    }

    /**
     * Reset content of mail actualy set
     */
    public void prepareMail() {
        mailCreate = new MailDefiner();
        mailWaiting = new MailDefiner();
        mailProcessing = new MailDefiner();
        mailFinished = new MailDefiner();
        mailCanceled = new MailDefiner();
    }

    /**
     * Process change while processus is change form one to an other
     *
     * @param event current item select event
     */
    public void handleProcessusChanged(SelectEvent event) {
        SelectOneMenu som = (SelectOneMenu) event.getSource();

        if (som.getValue() == null) {
            JsfUtil.addErrorMessage("Aucun processus définit ! Aucune action...");
            return;
        }

        // Initialisaze all mail
        prepareMail();

        // Read processus
        Processus processus = (Processus) som.getValue();

        // Load Mail definer
        parseMaillist(mailCreate, maillistController.getItemsBy(MLGROUPE, A, ismMailtypeController.getIsmMailtype(1), processus, staffAuthController.getCompany()));
        parseMaillist(mailCreate, maillistController.getItemsBy(MLGROUPE, A, ismMailtypeController.getIsmMailtype(2), processus, staffAuthController.getCompany()));

        parseMaillist(mailWaiting, maillistController.getItemsBy(MLGROUPE, B, ismMailtypeController.getIsmMailtype(1), processus, staffAuthController.getCompany()));
        parseMaillist(mailWaiting, maillistController.getItemsBy(MLGROUPE, B, ismMailtypeController.getIsmMailtype(2), processus, staffAuthController.getCompany()));

        parseMaillist(mailProcessing, maillistController.getItemsBy(MLGROUPE, C, ismMailtypeController.getIsmMailtype(1), processus, staffAuthController.getCompany()));
        parseMaillist(mailProcessing, maillistController.getItemsBy(MLGROUPE, C, ismMailtypeController.getIsmMailtype(2), processus, staffAuthController.getCompany()));

        parseMaillist(mailFinished, maillistController.getItemsBy(MLGROUPE, D, ismMailtypeController.getIsmMailtype(1), processus, staffAuthController.getCompany()));
        parseMaillist(mailFinished, maillistController.getItemsBy(MLGROUPE, D, ismMailtypeController.getIsmMailtype(2), processus, staffAuthController.getCompany()));

        parseMaillist(mailCanceled, maillistController.getItemsBy(MLGROUPE, E, ismMailtypeController.getIsmMailtype(1), processus, staffAuthController.getCompany()));
        parseMaillist(mailCanceled, maillistController.getItemsBy(MLGROUPE, E, ismMailtypeController.getIsmMailtype(2), processus, staffAuthController.getCompany()));

    }

    /**
     * Complete staff allow to help user on completion by providing list of
     * available data
     *
     * @param query starting of seaching staff begining by firstname lastname
     * middlename [staff]
     * @return list of available staff starting with query with a result of
     * maximum 10 staff
     */
    public List<Staff> completeStaff(String query) {
        return staffController.staffStartingWith(query, 10);
    }

    private void persit(String mlEvent, MailDefiner mailDefiner) {
        // Create mail list
        Maillist mlist = new Maillist();
        mlist.setMlCompany(staffAuthController.getCompany());
        mlist.setMlProcessus(selectedProcessus);
        mlist.setMlGroupe(MLGROUPE);
        mlist.setMlDeleted(Boolean.FALSE);
        mlist.setMlEvent(mlEvent);
        if (mailDefiner.isStandardDefined()) {
            mlist.setMlType(ismMailtypeController.getIsmMailtype(1)); // ism mail type 1: standard / 2 : réclamation
            mlist.setMlTos(mailDefiner.tosAsString());
            mlist.setMlCcs(mailDefiner.ccsAsString());
            mlist.setMlCcis(mailDefiner.ccisAsString());
            maillistController.setSelected(mlist);
            maillistController.create();
        }
        if (mailDefiner.isReclamDefined()) {
            mlist.setMlType(ismMailtypeController.getIsmMailtype(2)); // ism mail type 1: standard / 2 : réclamation
            mlist.setMlTos(mailDefiner.tosReclamAsString());
            mlist.setMlCcs(mailDefiner.ccsReclamAsString());
            mlist.setMlCcis(mailDefiner.ccisReclamAsString());
            maillistController.setSelected(mlist);
            maillistController.create();
        }

    }

    /**
     *
     */
    public void save() {
        // First delete all existing setup of this processus
        maillistController.destroyAllByProcessus(selectedProcessus);

        // Create new definition of this processus
        persit(A, mailCreate);
        persit(B, mailWaiting);
        persit(C, mailProcessing);
        persit(D, mailFinished);
        persit(E, mailCanceled);
    }

    /**
     * Convert a defined string containing email of staff to a staff list
     *
     * @param strStaffs a list a staff email separate by ";"
     * @return list of recognized staffs
     */
    public List<Staff> asStaffs(String strStaffs) {
        List<Staff> staffs = new ArrayList<>();
        if (strStaffs == null) {
            return staffs;
        }
        if (strStaffs.trim().isEmpty()) {
            return staffs;
        }

        String a[] = strStaffs.trim().split(";");
        for (String mail : a) {
            List<StaffGroups> s = staffGroupsController.getItemsByMaillistCompany(mail, staffAuthController.getCompany());
            if (s != null) {
                if (!staffs.contains(s.get(0).getStgStaff())) {
                    staffs.add(s.get(0).getStgStaff());
                }
            }
        }
        return staffs;
    }

    private void parseMaillist(MailDefiner mailDefiner, Maillist maillist) {
        if (null == maillist) {
            //JsfUtil.out("MailDefiner : Unable to parse maillist");
        } else {
            switch (maillist.getMlType().getId()) {
                case 1:
                    mailDefiner.setTos(asStaffs(maillist.getMlTos()));
                    mailDefiner.setCcs(asStaffs(maillist.getMlCcs()));
                    mailDefiner.setCcis(asStaffs(maillist.getMlCcis()));
                    break;
                case 2:
                    mailDefiner.setRtos(asStaffs(maillist.getMlTos()));
                    mailDefiner.setRccs(asStaffs(maillist.getMlCcs()));
                    mailDefiner.setRccis(asStaffs(maillist.getMlCcis()));
                    break;
                default:
                    //JsfUtil.out("MailDefiner : Unable to parse maillist");
                    break;
            }
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Getter / Setter
    //
    //
    // /////////////////////////////////////////////////////////////////////////
    public Processus getSelectedProcessus() {
        return selectedProcessus;
    }

    public void setSelectedProcessus(Processus selectedProcessus) {
        this.selectedProcessus = selectedProcessus;
    }

    public MailDefiner getMailCreate() {
        return mailCreate;
    }

    public void setMailCreate(MailDefiner mailCreate) {
        this.mailCreate = mailCreate;
    }

    public MailDefiner getMailWaiting() {
        return mailWaiting;
    }

    public void setMailWaiting(MailDefiner mailWaiting) {
        this.mailWaiting = mailWaiting;
    }

    public MailDefiner getMailProcessing() {
        return mailProcessing;
    }

    public void setMailProcessing(MailDefiner mailProcessing) {
        this.mailProcessing = mailProcessing;
    }

    public MailDefiner getMailFinished() {
        return mailFinished;
    }

    public void setMailFinished(MailDefiner mailFinished) {
        this.mailFinished = mailFinished;
    }

    public MailDefiner getMailCanceled() {
        return mailCanceled;
    }

    public void setMailCanceled(MailDefiner mailCanceled) {
        this.mailCanceled = mailCanceled;
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // MANAGED INJECTION
    //
    //
    // /////////////////////////////////////////////////////////////////////////
//    public void setCompanyController(CompanyController companyController) {
//        this.companyController = companyController;
//    }
    public void setMaillistController(MaillistController maillistController) {
        this.maillistController = maillistController;
    }

    public void setProcessusController(ProcessusController processusController) {
        this.processusController = processusController;
    }

    public void setMailaddressController(MailaddressController mailaddressController) {
        this.mailaddressController = mailaddressController;
    }

    public void setStaffController(StaffController staffController) {
        this.staffController = staffController;
    }

    public void setStaffGroupsController(StaffGroupsController staffGroupsController) {
        this.staffGroupsController = staffGroupsController;
    }

    public void setStaffAuthController(StaffAuthController staffAuthController) {
        this.staffAuthController = staffAuthController;
    }

    public void setIsmMailtypeController(IsmMailtypeController ismMailtypeController) {
        this.ismMailtypeController = ismMailtypeController;
    }

}
