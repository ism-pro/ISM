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
@Table(name = "ques_multiple", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qm_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesMultiple.findAll", query = "SELECT q FROM QuesMultiple q")
    , @NamedQuery(name = "QuesMultiple.findByQmId", query = "SELECT q FROM QuesMultiple q WHERE q.qmId = :qmId")
    , @NamedQuery(name = "QuesMultiple.findByQmMultiple", query = "SELECT q FROM QuesMultiple q WHERE q.qmMultiple = :qmMultiple")
    , @NamedQuery(name = "QuesMultiple.findByQmDesignation", query = "SELECT q FROM QuesMultiple q WHERE q.qmDesignation = :qmDesignation")
    , @NamedQuery(name = "QuesMultiple.findByQmDescription", query = "SELECT q FROM QuesMultiple q WHERE q.qmDescription = :qmDescription")
    , @NamedQuery(name = "QuesMultiple.findByQmDeleted", query = "SELECT q FROM QuesMultiple q WHERE q.qmDeleted = :qmDeleted")
    , @NamedQuery(name = "QuesMultiple.findByQmCreated", query = "SELECT q FROM QuesMultiple q WHERE q.qmCreated = :qmCreated")
    , @NamedQuery(name = "QuesMultiple.findByQmChanged", query = "SELECT q FROM QuesMultiple q WHERE q.qmChanged = :qmChanged")})
public class QuesMultiple implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qm_id", nullable = false)
    private Integer qmId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "qm_multiple", nullable = false, length = 45)
    private String qmMultiple;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "qm_designation", nullable = false, length = 255)
    private String qmDesignation;
    @Size(max = 512)
    @Column(name = "qm_description", length = 512)
    private String qmDescription;
    @Column(name = "qm_deleted")
    private Boolean qmDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qm_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qmCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qm_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qmChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qlMultiple")
    private Collection<QuesList> quesListCollection;

    public QuesMultiple() {
    }

    public QuesMultiple(Integer qmId) {
        this.qmId = qmId;
    }

    public QuesMultiple(Integer qmId, String qmMultiple, String qmDesignation, Date qmCreated, Date qmChanged) {
        this.qmId = qmId;
        this.qmMultiple = qmMultiple;
        this.qmDesignation = qmDesignation;
        this.qmCreated = qmCreated;
        this.qmChanged = qmChanged;
    }

    public Integer getQmId() {
        return qmId;
    }

    public void setQmId(Integer qmId) {
        this.qmId = qmId;
    }

    public String getQmMultiple() {
        return qmMultiple;
    }

    public void setQmMultiple(String qmMultiple) {
        this.qmMultiple = qmMultiple;
    }

    public String getQmDesignation() {
        return qmDesignation;
    }

    public void setQmDesignation(String qmDesignation) {
        this.qmDesignation = qmDesignation;
    }

    public String getQmDescription() {
        return qmDescription;
    }

    public void setQmDescription(String qmDescription) {
        this.qmDescription = qmDescription;
    }

    public Boolean getQmDeleted() {
        return qmDeleted;
    }

    public void setQmDeleted(Boolean qmDeleted) {
        this.qmDeleted = qmDeleted;
    }

    public Date getQmCreated() {
        return qmCreated;
    }

    public void setQmCreated(Date qmCreated) {
        this.qmCreated = qmCreated;
    }

    public Date getQmChanged() {
        return qmChanged;
    }

    public void setQmChanged(Date qmChanged) {
        this.qmChanged = qmChanged;
    }

    @XmlTransient
    public Collection<QuesList> getQuesListCollection() {
        return quesListCollection;
    }

    public void setQuesListCollection(Collection<QuesList> quesListCollection) {
        this.quesListCollection = quesListCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qmId != null ? qmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesMultiple)) {
            return false;
        }
        QuesMultiple other = (QuesMultiple) object;
        if ((this.qmId == null && other.qmId != null) || (this.qmId != null && !this.qmId.equals(other.qmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesMultiple[ qmId=" + qmId + " ]";
    }
    
}
