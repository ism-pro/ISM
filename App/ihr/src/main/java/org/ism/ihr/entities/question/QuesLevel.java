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
@Table(name = "ques_level", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ql_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesLevel.findAll", query = "SELECT q FROM QuesLevel q")
    , @NamedQuery(name = "QuesLevel.findByQlId", query = "SELECT q FROM QuesLevel q WHERE q.qlId = :qlId")
    , @NamedQuery(name = "QuesLevel.findByQlLevel", query = "SELECT q FROM QuesLevel q WHERE q.qlLevel = :qlLevel")
    , @NamedQuery(name = "QuesLevel.findByQlDesignation", query = "SELECT q FROM QuesLevel q WHERE q.qlDesignation = :qlDesignation")
    , @NamedQuery(name = "QuesLevel.findByQlDescription", query = "SELECT q FROM QuesLevel q WHERE q.qlDescription = :qlDescription")
    , @NamedQuery(name = "QuesLevel.findByQlDeleted", query = "SELECT q FROM QuesLevel q WHERE q.qlDeleted = :qlDeleted")
    , @NamedQuery(name = "QuesLevel.findByQlCreated", query = "SELECT q FROM QuesLevel q WHERE q.qlCreated = :qlCreated")
    , @NamedQuery(name = "QuesLevel.findByQlChanged", query = "SELECT q FROM QuesLevel q WHERE q.qlChanged = :qlChanged")})
public class QuesLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ql_id", nullable = false)
    private Integer qlId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ql_level", nullable = false, length = 45)
    private String qlLevel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ql_designation", nullable = false, length = 255)
    private String qlDesignation;
    @Size(max = 512)
    @Column(name = "ql_description", length = 512)
    private String qlDescription;
    @Column(name = "ql_deleted")
    private Boolean qlDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ql_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qlCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ql_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qlChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qpdLevel")
    private Collection<QuesPlandef> quesPlandefCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qlLevel")
    private Collection<QuesList> quesListCollection;

    public QuesLevel() {
    }

    public QuesLevel(Integer qlId) {
        this.qlId = qlId;
    }

    public QuesLevel(Integer qlId, String qlLevel, String qlDesignation, Date qlCreated, Date qlChanged) {
        this.qlId = qlId;
        this.qlLevel = qlLevel;
        this.qlDesignation = qlDesignation;
        this.qlCreated = qlCreated;
        this.qlChanged = qlChanged;
    }

    public Integer getQlId() {
        return qlId;
    }

    public void setQlId(Integer qlId) {
        this.qlId = qlId;
    }

    public String getQlLevel() {
        return qlLevel;
    }

    public void setQlLevel(String qlLevel) {
        this.qlLevel = qlLevel;
    }

    public String getQlDesignation() {
        return qlDesignation;
    }

    public void setQlDesignation(String qlDesignation) {
        this.qlDesignation = qlDesignation;
    }

    public String getQlDescription() {
        return qlDescription;
    }

    public void setQlDescription(String qlDescription) {
        this.qlDescription = qlDescription;
    }

    public Boolean getQlDeleted() {
        return qlDeleted;
    }

    public void setQlDeleted(Boolean qlDeleted) {
        this.qlDeleted = qlDeleted;
    }

    public Date getQlCreated() {
        return qlCreated;
    }

    public void setQlCreated(Date qlCreated) {
        this.qlCreated = qlCreated;
    }

    public Date getQlChanged() {
        return qlChanged;
    }

    public void setQlChanged(Date qlChanged) {
        this.qlChanged = qlChanged;
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
        hash += (qlId != null ? qlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesLevel)) {
            return false;
        }
        QuesLevel other = (QuesLevel) object;
        if ((this.qlId == null && other.qlId != null) || (this.qlId != null && !this.qlId.equals(other.qlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesLevel[ qlId=" + qlId + " ]";
    }
    
}
