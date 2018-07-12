/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.question;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ques_state", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qs_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesState.findAll", query = "SELECT q FROM QuesState q")
    , @NamedQuery(name = "QuesState.findByQsId", query = "SELECT q FROM QuesState q WHERE q.qsId = :qsId")
    , @NamedQuery(name = "QuesState.findByQsDesignation", query = "SELECT q FROM QuesState q WHERE q.qsDesignation = :qsDesignation")
    , @NamedQuery(name = "QuesState.findByQsDescription", query = "SELECT q FROM QuesState q WHERE q.qsDescription = :qsDescription")
    , @NamedQuery(name = "QuesState.findByQsDeleted", query = "SELECT q FROM QuesState q WHERE q.qsDeleted = :qsDeleted")
    , @NamedQuery(name = "QuesState.findByQsCreated", query = "SELECT q FROM QuesState q WHERE q.qsCreated = :qsCreated")
    , @NamedQuery(name = "QuesState.findByQsChanged", query = "SELECT q FROM QuesState q WHERE q.qsChanged = :qsChanged")})
public class QuesState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qs_id", nullable = false)
    private Integer qsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "qs_designation", nullable = false, length = 255)
    private String qsDesignation;
    @Size(max = 512)
    @Column(name = "qs_description", length = 512)
    private String qsDescription;
    @Column(name = "qs_deleted")
    private Boolean qsDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qs_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qsCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qs_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qsChanged;

    public QuesState() {
    }

    public QuesState(Integer qsId) {
        this.qsId = qsId;
    }

    public QuesState(Integer qsId, String qsDesignation, Date qsCreated, Date qsChanged) {
        this.qsId = qsId;
        this.qsDesignation = qsDesignation;
        this.qsCreated = qsCreated;
        this.qsChanged = qsChanged;
    }

    public Integer getQsId() {
        return qsId;
    }

    public void setQsId(Integer qsId) {
        this.qsId = qsId;
    }

    public String getQsDesignation() {
        return qsDesignation;
    }

    public void setQsDesignation(String qsDesignation) {
        this.qsDesignation = qsDesignation;
    }

    public String getQsDescription() {
        return qsDescription;
    }

    public void setQsDescription(String qsDescription) {
        this.qsDescription = qsDescription;
    }

    public Boolean getQsDeleted() {
        return qsDeleted;
    }

    public void setQsDeleted(Boolean qsDeleted) {
        this.qsDeleted = qsDeleted;
    }

    public Date getQsCreated() {
        return qsCreated;
    }

    public void setQsCreated(Date qsCreated) {
        this.qsCreated = qsCreated;
    }

    public Date getQsChanged() {
        return qsChanged;
    }

    public void setQsChanged(Date qsChanged) {
        this.qsChanged = qsChanged;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qsId != null ? qsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesState)) {
            return false;
        }
        QuesState other = (QuesState) object;
        if ((this.qsId == null && other.qsId != null) || (this.qsId != null && !this.qsId.equals(other.qsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesState[ qsId=" + qsId + " ]";
    }
    
}
