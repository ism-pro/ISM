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
@Table(name = "ques_groupe", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qg_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesGroupe.findAll", query = "SELECT q FROM QuesGroupe q")
    , @NamedQuery(name = "QuesGroupe.findByQgId", query = "SELECT q FROM QuesGroupe q WHERE q.qgId = :qgId")
    , @NamedQuery(name = "QuesGroupe.findByQgGroupe", query = "SELECT q FROM QuesGroupe q WHERE q.qgGroupe = :qgGroupe")
    , @NamedQuery(name = "QuesGroupe.findByQgDesignation", query = "SELECT q FROM QuesGroupe q WHERE q.qgDesignation = :qgDesignation")
    , @NamedQuery(name = "QuesGroupe.findByQgFormulation", query = "SELECT q FROM QuesGroupe q WHERE q.qgFormulation = :qgFormulation")
    , @NamedQuery(name = "QuesGroupe.findByQgDescription", query = "SELECT q FROM QuesGroupe q WHERE q.qgDescription = :qgDescription")
    , @NamedQuery(name = "QuesGroupe.findByQgDeleted", query = "SELECT q FROM QuesGroupe q WHERE q.qgDeleted = :qgDeleted")
    , @NamedQuery(name = "QuesGroupe.findByQgCreated", query = "SELECT q FROM QuesGroupe q WHERE q.qgCreated = :qgCreated")
    , @NamedQuery(name = "QuesGroupe.findByQgChanged", query = "SELECT q FROM QuesGroupe q WHERE q.qgChanged = :qgChanged")})
public class QuesGroupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qg_id", nullable = false)
    private Integer qgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "qg_groupe", nullable = false, length = 45)
    private String qgGroupe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "qg_designation", nullable = false, length = 255)
    private String qgDesignation;
    @Size(max = 512)
    @Column(name = "qg_formulation", length = 512)
    private String qgFormulation;
    @Size(max = 512)
    @Column(name = "qg_description", length = 512)
    private String qgDescription;
    @Column(name = "qg_deleted")
    private Boolean qgDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qg_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qgCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qg_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qgChanged;
    @OneToMany(mappedBy = "qlGroupe")
    private Collection<QuesList> quesListCollection;

    public QuesGroupe() {
    }

    public QuesGroupe(Integer qgId) {
        this.qgId = qgId;
    }

    public QuesGroupe(Integer qgId, String qgGroupe, String qgDesignation, Date qgCreated, Date qgChanged) {
        this.qgId = qgId;
        this.qgGroupe = qgGroupe;
        this.qgDesignation = qgDesignation;
        this.qgCreated = qgCreated;
        this.qgChanged = qgChanged;
    }

    public Integer getQgId() {
        return qgId;
    }

    public void setQgId(Integer qgId) {
        this.qgId = qgId;
    }

    public String getQgGroupe() {
        return qgGroupe;
    }

    public void setQgGroupe(String qgGroupe) {
        this.qgGroupe = qgGroupe;
    }

    public String getQgDesignation() {
        return qgDesignation;
    }

    public void setQgDesignation(String qgDesignation) {
        this.qgDesignation = qgDesignation;
    }

    public String getQgFormulation() {
        return qgFormulation;
    }

    public void setQgFormulation(String qgFormulation) {
        this.qgFormulation = qgFormulation;
    }

    public String getQgDescription() {
        return qgDescription;
    }

    public void setQgDescription(String qgDescription) {
        this.qgDescription = qgDescription;
    }

    public Boolean getQgDeleted() {
        return qgDeleted;
    }

    public void setQgDeleted(Boolean qgDeleted) {
        this.qgDeleted = qgDeleted;
    }

    public Date getQgCreated() {
        return qgCreated;
    }

    public void setQgCreated(Date qgCreated) {
        this.qgCreated = qgCreated;
    }

    public Date getQgChanged() {
        return qgChanged;
    }

    public void setQgChanged(Date qgChanged) {
        this.qgChanged = qgChanged;
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
        hash += (qgId != null ? qgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesGroupe)) {
            return false;
        }
        QuesGroupe other = (QuesGroupe) object;
        if ((this.qgId == null && other.qgId != null) || (this.qgId != null && !this.qgId.equals(other.qgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesGroupe[ qgId=" + qgId + " ]";
    }
    
}
