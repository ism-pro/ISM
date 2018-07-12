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
@Table(name = "ques_planpart", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qpp_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesPlanpart.findAll", query = "SELECT q FROM QuesPlanpart q")
    , @NamedQuery(name = "QuesPlanpart.findByQppId", query = "SELECT q FROM QuesPlanpart q WHERE q.qppId = :qppId")
    , @NamedQuery(name = "QuesPlanpart.findByQppDesignation", query = "SELECT q FROM QuesPlanpart q WHERE q.qppDesignation = :qppDesignation")
    , @NamedQuery(name = "QuesPlanpart.findByQppDescription", query = "SELECT q FROM QuesPlanpart q WHERE q.qppDescription = :qppDescription")
    , @NamedQuery(name = "QuesPlanpart.findByQppDeleted", query = "SELECT q FROM QuesPlanpart q WHERE q.qppDeleted = :qppDeleted")
    , @NamedQuery(name = "QuesPlanpart.findByQppCreated", query = "SELECT q FROM QuesPlanpart q WHERE q.qppCreated = :qppCreated")
    , @NamedQuery(name = "QuesPlanpart.findByQppChanged", query = "SELECT q FROM QuesPlanpart q WHERE q.qppChanged = :qppChanged")})
public class QuesPlanpart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qpp_id", nullable = false)
    private Integer qppId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "qpp_designation", nullable = false, length = 255)
    private String qppDesignation;
    @Size(max = 512)
    @Column(name = "qpp_description", length = 512)
    private String qppDescription;
    @Column(name = "qpp_deleted")
    private Boolean qppDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qpp_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qppCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qpp_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qppChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qpdPart")
    private Collection<QuesPlandef> quesPlandefCollection;

    public QuesPlanpart() {
    }

    public QuesPlanpart(Integer qppId) {
        this.qppId = qppId;
    }

    public QuesPlanpart(Integer qppId, String qppDesignation, Date qppCreated, Date qppChanged) {
        this.qppId = qppId;
        this.qppDesignation = qppDesignation;
        this.qppCreated = qppCreated;
        this.qppChanged = qppChanged;
    }

    public Integer getQppId() {
        return qppId;
    }

    public void setQppId(Integer qppId) {
        this.qppId = qppId;
    }

    public String getQppDesignation() {
        return qppDesignation;
    }

    public void setQppDesignation(String qppDesignation) {
        this.qppDesignation = qppDesignation;
    }

    public String getQppDescription() {
        return qppDescription;
    }

    public void setQppDescription(String qppDescription) {
        this.qppDescription = qppDescription;
    }

    public Boolean getQppDeleted() {
        return qppDeleted;
    }

    public void setQppDeleted(Boolean qppDeleted) {
        this.qppDeleted = qppDeleted;
    }

    public Date getQppCreated() {
        return qppCreated;
    }

    public void setQppCreated(Date qppCreated) {
        this.qppCreated = qppCreated;
    }

    public Date getQppChanged() {
        return qppChanged;
    }

    public void setQppChanged(Date qppChanged) {
        this.qppChanged = qppChanged;
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
        hash += (qppId != null ? qppId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesPlanpart)) {
            return false;
        }
        QuesPlanpart other = (QuesPlanpart) object;
        if ((this.qppId == null && other.qppId != null) || (this.qppId != null && !this.qppId.equals(other.qppId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesPlanpart[ qppId=" + qppId + " ]";
    }
    
}
