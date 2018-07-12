/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.contact;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author r.hendrick
 */
@Entity
@Table(name = "cont_purpose", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cp_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContPurpose.findAll", query = "SELECT c FROM ContPurpose c")
    , @NamedQuery(name = "ContPurpose.findByCpId", query = "SELECT c FROM ContPurpose c WHERE c.cpId = :cpId")
    , @NamedQuery(name = "ContPurpose.findByCpPurpose", query = "SELECT c FROM ContPurpose c WHERE c.cpPurpose = :cpPurpose")
    , @NamedQuery(name = "ContPurpose.findByCpDesignation", query = "SELECT c FROM ContPurpose c WHERE c.cpDesignation = :cpDesignation")
    , @NamedQuery(name = "ContPurpose.findByCpDeleted", query = "SELECT c FROM ContPurpose c WHERE c.cpDeleted = :cpDeleted")
    , @NamedQuery(name = "ContPurpose.findByCpCreated", query = "SELECT c FROM ContPurpose c WHERE c.cpCreated = :cpCreated")
    , @NamedQuery(name = "ContPurpose.findByCpChanged", query = "SELECT c FROM ContPurpose c WHERE c.cpChanged = :cpChanged")})
public class ContPurpose implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cp_id", nullable = false)
    private Integer cpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cp_purpose", nullable = false, length = 45)
    private String cpPurpose;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cp_designation", nullable = false, length = 255)
    private String cpDesignation;
    @Column(name = "cp_deleted")
    private Boolean cpDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cp_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cpCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cp_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cpChanged;
    @OneToMany(mappedBy = "mPurpose")
    private Collection<Mail> mailCollection;
    @OneToMany(mappedBy = "pPurpose")
    private Collection<Phone> phoneCollection;

    public ContPurpose() {
    }

    public ContPurpose(Integer cpId) {
        this.cpId = cpId;
    }

    public ContPurpose(Integer cpId, String cpPurpose, String cpDesignation, Date cpCreated, Date cpChanged) {
        this.cpId = cpId;
        this.cpPurpose = cpPurpose;
        this.cpDesignation = cpDesignation;
        this.cpCreated = cpCreated;
        this.cpChanged = cpChanged;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String getCpPurpose() {
        return cpPurpose;
    }

    public void setCpPurpose(String cpPurpose) {
        this.cpPurpose = cpPurpose;
    }

    public String getCpDesignation() {
        return cpDesignation;
    }

    public void setCpDesignation(String cpDesignation) {
        this.cpDesignation = cpDesignation;
    }

    public Boolean getCpDeleted() {
        return cpDeleted;
    }

    public void setCpDeleted(Boolean cpDeleted) {
        this.cpDeleted = cpDeleted;
    }

    public Date getCpCreated() {
        return cpCreated;
    }

    public void setCpCreated(Date cpCreated) {
        this.cpCreated = cpCreated;
    }

    public Date getCpChanged() {
        return cpChanged;
    }

    public void setCpChanged(Date cpChanged) {
        this.cpChanged = cpChanged;
    }

    @XmlTransient
    public Collection<Mail> getMailCollection() {
        return mailCollection;
    }

    public void setMailCollection(Collection<Mail> mailCollection) {
        this.mailCollection = mailCollection;
    }

    @XmlTransient
    public Collection<Phone> getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(Collection<Phone> phoneCollection) {
        this.phoneCollection = phoneCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpId != null ? cpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContPurpose)) {
            return false;
        }
        ContPurpose other = (ContPurpose) object;
        if ((this.cpId == null && other.cpId != null) || (this.cpId != null && !this.cpId.equals(other.cpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.ContPurpose[ cpId=" + cpId + " ]";
    }
    
}
