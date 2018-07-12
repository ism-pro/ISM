/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.question;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "ques_plan", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qp_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesPlan.findAll", query = "SELECT q FROM QuesPlan q")
    , @NamedQuery(name = "QuesPlan.findByQpId", query = "SELECT q FROM QuesPlan q WHERE q.qpId = :qpId")
    , @NamedQuery(name = "QuesPlan.findByQpDesignation", query = "SELECT q FROM QuesPlan q WHERE q.qpDesignation = :qpDesignation")
    , @NamedQuery(name = "QuesPlan.findByQpDescription", query = "SELECT q FROM QuesPlan q WHERE q.qpDescription = :qpDescription")
    , @NamedQuery(name = "QuesPlan.findByQpDeleted", query = "SELECT q FROM QuesPlan q WHERE q.qpDeleted = :qpDeleted")
    , @NamedQuery(name = "QuesPlan.findByQpCreated", query = "SELECT q FROM QuesPlan q WHERE q.qpCreated = :qpCreated")
    , @NamedQuery(name = "QuesPlan.findByQpChanged", query = "SELECT q FROM QuesPlan q WHERE q.qpChanged = :qpChanged")})
public class QuesPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qp_id", nullable = false)
    private Integer qpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "qp_designation", nullable = false, length = 255)
    private String qpDesignation;
    @Size(max = 512)
    @Column(name = "qp_description", length = 512)
    private String qpDescription;
    @Column(name = "qp_deleted")
    private Boolean qpDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qp_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qpCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qp_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qpChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qpdPlan")
    private Collection<QuesPlandef> quesPlandefCollection;

    public QuesPlan() {
    }

    public QuesPlan(Integer qpId) {
        this.qpId = qpId;
    }

    public QuesPlan(Integer qpId, String qpDesignation, Date qpCreated, Date qpChanged) {
        this.qpId = qpId;
        this.qpDesignation = qpDesignation;
        this.qpCreated = qpCreated;
        this.qpChanged = qpChanged;
    }

    public Integer getQpId() {
        return qpId;
    }

    public void setQpId(Integer qpId) {
        this.qpId = qpId;
    }

    public String getQpDesignation() {
        return qpDesignation;
    }

    public void setQpDesignation(String qpDesignation) {
        this.qpDesignation = qpDesignation;
    }

    public String getQpDescription() {
        return qpDescription;
    }

    public void setQpDescription(String qpDescription) {
        this.qpDescription = qpDescription;
    }

    public Boolean getQpDeleted() {
        return qpDeleted;
    }

    public void setQpDeleted(Boolean qpDeleted) {
        this.qpDeleted = qpDeleted;
    }

    public Date getQpCreated() {
        return qpCreated;
    }

    public void setQpCreated(Date qpCreated) {
        this.qpCreated = qpCreated;
    }

    public Date getQpChanged() {
        return qpChanged;
    }

    public void setQpChanged(Date qpChanged) {
        this.qpChanged = qpChanged;
    }

    @XmlTransient
    public Collection<QuesPlandef> getQuesPlandefCollection() {
        return quesPlandefCollection;
    }

    public void setQuesPlandefCollection(Collection<QuesPlandef> quesPlandefCollection) {
        this.quesPlandefCollection = quesPlandefCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qpId != null ? qpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesPlan)) {
            return false;
        }
        QuesPlan other = (QuesPlan) object;
        if ((this.qpId == null && other.qpId != null) || (this.qpId != null && !this.qpId.equals(other.qpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesPlan[ qpId=" + qpId + " ]";
    }
    
}
