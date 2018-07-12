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
@Table(name = "ques_categorie", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qc_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesCategorie.findAll", query = "SELECT q FROM QuesCategorie q")
    , @NamedQuery(name = "QuesCategorie.findByQcId", query = "SELECT q FROM QuesCategorie q WHERE q.qcId = :qcId")
    , @NamedQuery(name = "QuesCategorie.findByQcCategorie", query = "SELECT q FROM QuesCategorie q WHERE q.qcCategorie = :qcCategorie")
    , @NamedQuery(name = "QuesCategorie.findByQcDesignation", query = "SELECT q FROM QuesCategorie q WHERE q.qcDesignation = :qcDesignation")
    , @NamedQuery(name = "QuesCategorie.findByQcDescription", query = "SELECT q FROM QuesCategorie q WHERE q.qcDescription = :qcDescription")
    , @NamedQuery(name = "QuesCategorie.findByQcDeleted", query = "SELECT q FROM QuesCategorie q WHERE q.qcDeleted = :qcDeleted")
    , @NamedQuery(name = "QuesCategorie.findByQcCreated", query = "SELECT q FROM QuesCategorie q WHERE q.qcCreated = :qcCreated")
    , @NamedQuery(name = "QuesCategorie.findByQcChanged", query = "SELECT q FROM QuesCategorie q WHERE q.qcChanged = :qcChanged")})
public class QuesCategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qc_id", nullable = false)
    private Integer qcId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "qc_categorie", nullable = false, length = 45)
    private String qcCategorie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "qc_designation", nullable = false, length = 255)
    private String qcDesignation;
    @Size(max = 512)
    @Column(name = "qc_description", length = 512)
    private String qcDescription;
    @Column(name = "qc_deleted")
    private Boolean qcDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qc_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qcCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qc_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qcChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qpdCategorie")
    private Collection<QuesPlandef> quesPlandefCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qlCategorie")
    private Collection<QuesList> quesListCollection;

    public QuesCategorie() {
    }

    public QuesCategorie(Integer qcId) {
        this.qcId = qcId;
    }

    public QuesCategorie(Integer qcId, String qcCategorie, String qcDesignation, Date qcCreated, Date qcChanged) {
        this.qcId = qcId;
        this.qcCategorie = qcCategorie;
        this.qcDesignation = qcDesignation;
        this.qcCreated = qcCreated;
        this.qcChanged = qcChanged;
    }

    public Integer getQcId() {
        return qcId;
    }

    public void setQcId(Integer qcId) {
        this.qcId = qcId;
    }

    public String getQcCategorie() {
        return qcCategorie;
    }

    public void setQcCategorie(String qcCategorie) {
        this.qcCategorie = qcCategorie;
    }

    public String getQcDesignation() {
        return qcDesignation;
    }

    public void setQcDesignation(String qcDesignation) {
        this.qcDesignation = qcDesignation;
    }

    public String getQcDescription() {
        return qcDescription;
    }

    public void setQcDescription(String qcDescription) {
        this.qcDescription = qcDescription;
    }

    public Boolean getQcDeleted() {
        return qcDeleted;
    }

    public void setQcDeleted(Boolean qcDeleted) {
        this.qcDeleted = qcDeleted;
    }

    public Date getQcCreated() {
        return qcCreated;
    }

    public void setQcCreated(Date qcCreated) {
        this.qcCreated = qcCreated;
    }

    public Date getQcChanged() {
        return qcChanged;
    }

    public void setQcChanged(Date qcChanged) {
        this.qcChanged = qcChanged;
    }

    @XmlTransient
    public Collection<QuesPlandef> getQuesPlandefCollection() {
        return quesPlandefCollection;
    }

    public void setQuesPlandefCollection(Collection<QuesPlandef> quesPlandefCollection) {
        this.quesPlandefCollection = quesPlandefCollection;
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
        hash += (qcId != null ? qcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesCategorie)) {
            return false;
        }
        QuesCategorie other = (QuesCategorie) object;
        if ((this.qcId == null && other.qcId != null) || (this.qcId != null && !this.qcId.equals(other.qcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesCategorie[ qcId=" + qcId + " ]";
    }
    
}
