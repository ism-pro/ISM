/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.contact;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author r.hendrick
 */
@Entity
@Table(catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"p_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
    , @NamedQuery(name = "Phone.findByPId", query = "SELECT p FROM Phone p WHERE p.pId = :pId")
    , @NamedQuery(name = "Phone.findByPPhone", query = "SELECT p FROM Phone p WHERE p.pPhone = :pPhone")
    , @NamedQuery(name = "Phone.findByPDesignation", query = "SELECT p FROM Phone p WHERE p.pDesignation = :pDesignation")
    , @NamedQuery(name = "Phone.findByPMain", query = "SELECT p FROM Phone p WHERE p.pMain = :pMain")
    , @NamedQuery(name = "Phone.findByPDeleted", query = "SELECT p FROM Phone p WHERE p.pDeleted = :pDeleted")
    , @NamedQuery(name = "Phone.findByPCreated", query = "SELECT p FROM Phone p WHERE p.pCreated = :pCreated")
    , @NamedQuery(name = "Phone.findByPChanged", query = "SELECT p FROM Phone p WHERE p.pChanged = :pChanged")})
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "p_id", nullable = false)
    private Integer pId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "p_phone", nullable = false, length = 45)
    private String pPhone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "p_designation", nullable = false, length = 255)
    private String pDesignation;
    @Column(name = "p_main")
    private Boolean pMain;
    @Column(name = "p_deleted")
    private Boolean pDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pChanged;
    @JoinColumn(name = "p_prefixe", referencedColumnName = "p_id", nullable = false)
    @ManyToOne(optional = false)
    private PhoPrefixe pPrefixe;
    @JoinColumn(name = "p_purpose", referencedColumnName = "cp_purpose")
    @ManyToOne
    private ContPurpose pPurpose;

    public Phone() {
    }

    public Phone(Integer pId) {
        this.pId = pId;
    }

    public Phone(Integer pId, String pPhone, String pDesignation, Date pCreated, Date pChanged) {
        this.pId = pId;
        this.pPhone = pPhone;
        this.pDesignation = pDesignation;
        this.pCreated = pCreated;
        this.pChanged = pChanged;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getPPhone() {
        return pPhone;
    }

    public void setPPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public String getPDesignation() {
        return pDesignation;
    }

    public void setPDesignation(String pDesignation) {
        this.pDesignation = pDesignation;
    }

    public Boolean getPMain() {
        return pMain;
    }

    public void setPMain(Boolean pMain) {
        this.pMain = pMain;
    }

    public Boolean getPDeleted() {
        return pDeleted;
    }

    public void setPDeleted(Boolean pDeleted) {
        this.pDeleted = pDeleted;
    }

    public Date getPCreated() {
        return pCreated;
    }

    public void setPCreated(Date pCreated) {
        this.pCreated = pCreated;
    }

    public Date getPChanged() {
        return pChanged;
    }

    public void setPChanged(Date pChanged) {
        this.pChanged = pChanged;
    }

    public PhoPrefixe getPPrefixe() {
        return pPrefixe;
    }

    public void setPPrefixe(PhoPrefixe pPrefixe) {
        this.pPrefixe = pPrefixe;
    }

    public ContPurpose getPPurpose() {
        return pPurpose;
    }

    public void setPPurpose(ContPurpose pPurpose) {
        this.pPurpose = pPurpose;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pId != null ? pId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phone)) {
            return false;
        }
        Phone other = (Phone) object;
        if ((this.pId == null && other.pId != null) || (this.pId != null && !this.pId.equals(other.pId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.Phone[ pId=" + pId + " ]";
    }
    
}
