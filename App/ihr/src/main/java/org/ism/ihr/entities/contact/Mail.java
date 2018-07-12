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
    @UniqueConstraint(columnNames = {"m_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mail.findAll", query = "SELECT m FROM Mail m")
    , @NamedQuery(name = "Mail.findByMId", query = "SELECT m FROM Mail m WHERE m.mId = :mId")
    , @NamedQuery(name = "Mail.findByMMail", query = "SELECT m FROM Mail m WHERE m.mMail = :mMail")
    , @NamedQuery(name = "Mail.findByMDesignation", query = "SELECT m FROM Mail m WHERE m.mDesignation = :mDesignation")
    , @NamedQuery(name = "Mail.findByMMain", query = "SELECT m FROM Mail m WHERE m.mMain = :mMain")
    , @NamedQuery(name = "Mail.findByMDeleted", query = "SELECT m FROM Mail m WHERE m.mDeleted = :mDeleted")
    , @NamedQuery(name = "Mail.findByMCreated", query = "SELECT m FROM Mail m WHERE m.mCreated = :mCreated")
    , @NamedQuery(name = "Mail.findByMChanged", query = "SELECT m FROM Mail m WHERE m.mChanged = :mChanged")})
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "m_id", nullable = false)
    private Integer mId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "m_mail", nullable = false, length = 45)
    private String mMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "m_designation", nullable = false, length = 255)
    private String mDesignation;
    @Column(name = "m_main")
    private Boolean mMain;
    @Column(name = "m_deleted")
    private Boolean mDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "m_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date mCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "m_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date mChanged;
    @JoinColumn(name = "m_purpose", referencedColumnName = "cp_purpose")
    @ManyToOne
    private ContPurpose mPurpose;

    public Mail() {
    }

    public Mail(Integer mId) {
        this.mId = mId;
    }

    public Mail(Integer mId, String mMail, String mDesignation, Date mCreated, Date mChanged) {
        this.mId = mId;
        this.mMail = mMail;
        this.mDesignation = mDesignation;
        this.mCreated = mCreated;
        this.mChanged = mChanged;
    }

    public Integer getMId() {
        return mId;
    }

    public void setMId(Integer mId) {
        this.mId = mId;
    }

    public String getMMail() {
        return mMail;
    }

    public void setMMail(String mMail) {
        this.mMail = mMail;
    }

    public String getMDesignation() {
        return mDesignation;
    }

    public void setMDesignation(String mDesignation) {
        this.mDesignation = mDesignation;
    }

    public Boolean getMMain() {
        return mMain;
    }

    public void setMMain(Boolean mMain) {
        this.mMain = mMain;
    }

    public Boolean getMDeleted() {
        return mDeleted;
    }

    public void setMDeleted(Boolean mDeleted) {
        this.mDeleted = mDeleted;
    }

    public Date getMCreated() {
        return mCreated;
    }

    public void setMCreated(Date mCreated) {
        this.mCreated = mCreated;
    }

    public Date getMChanged() {
        return mChanged;
    }

    public void setMChanged(Date mChanged) {
        this.mChanged = mChanged;
    }

    public ContPurpose getMPurpose() {
        return mPurpose;
    }

    public void setMPurpose(ContPurpose mPurpose) {
        this.mPurpose = mPurpose;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mId != null ? mId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mail)) {
            return false;
        }
        Mail other = (Mail) object;
        if ((this.mId == null && other.mId != null) || (this.mId != null && !this.mId.equals(other.mId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.Mail[ mId=" + mId + " ]";
    }
    
}
