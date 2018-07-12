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
@Table(name = "ques_plandef", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qpd_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesPlandef.findAll", query = "SELECT q FROM QuesPlandef q")
    , @NamedQuery(name = "QuesPlandef.findByQpdId", query = "SELECT q FROM QuesPlandef q WHERE q.qpdId = :qpdId")
    , @NamedQuery(name = "QuesPlandef.findByQpdPointPrecision", query = "SELECT q FROM QuesPlandef q WHERE q.qpdPointPrecision = :qpdPointPrecision")
    , @NamedQuery(name = "QuesPlandef.findByQpdNumber", query = "SELECT q FROM QuesPlandef q WHERE q.qpdNumber = :qpdNumber")
    , @NamedQuery(name = "QuesPlandef.findByQpdDescription", query = "SELECT q FROM QuesPlandef q WHERE q.qpdDescription = :qpdDescription")
    , @NamedQuery(name = "QuesPlandef.findByQpdDeleted", query = "SELECT q FROM QuesPlandef q WHERE q.qpdDeleted = :qpdDeleted")
    , @NamedQuery(name = "QuesPlandef.findByQpdCreated", query = "SELECT q FROM QuesPlandef q WHERE q.qpdCreated = :qpdCreated")
    , @NamedQuery(name = "QuesPlandef.findByQpdChanged", query = "SELECT q FROM QuesPlandef q WHERE q.qpdChanged = :qpdChanged")})
public class QuesPlandef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qpd_id", nullable = false)
    private Integer qpdId;
    @Column(name = "qpd_point_precision")
    private Boolean qpdPointPrecision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qpd_number", nullable = false)
    private int qpdNumber;
    @Size(max = 512)
    @Column(name = "qpd_description", length = 512)
    private String qpdDescription;
    @Column(name = "qpd_deleted")
    private Boolean qpdDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qpd_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qpdCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qpd_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qpdChanged;
    @JoinColumn(name = "qpd_categorie", referencedColumnName = "qc_categorie", nullable = false)
    @ManyToOne(optional = false)
    private QuesCategorie qpdCategorie;
    @JoinColumn(name = "qpd_level", referencedColumnName = "ql_level", nullable = false)
    @ManyToOne(optional = false)
    private QuesLevel qpdLevel;
    @JoinColumn(name = "qpd_part", referencedColumnName = "qpp_id", nullable = false)
    @ManyToOne(optional = false)
    private QuesPlanpart qpdPart;
    @JoinColumn(name = "qpd_plan", referencedColumnName = "qp_id", nullable = false)
    @ManyToOne(optional = false)
    private QuesPlan qpdPlan;

    public QuesPlandef() {
    }

    public QuesPlandef(Integer qpdId) {
        this.qpdId = qpdId;
    }

    public QuesPlandef(Integer qpdId, int qpdNumber, Date qpdCreated, Date qpdChanged) {
        this.qpdId = qpdId;
        this.qpdNumber = qpdNumber;
        this.qpdCreated = qpdCreated;
        this.qpdChanged = qpdChanged;
    }

    public Integer getQpdId() {
        return qpdId;
    }

    public void setQpdId(Integer qpdId) {
        this.qpdId = qpdId;
    }

    public Boolean getQpdPointPrecision() {
        return qpdPointPrecision;
    }

    public void setQpdPointPrecision(Boolean qpdPointPrecision) {
        this.qpdPointPrecision = qpdPointPrecision;
    }

    public int getQpdNumber() {
        return qpdNumber;
    }

    public void setQpdNumber(int qpdNumber) {
        this.qpdNumber = qpdNumber;
    }

    public String getQpdDescription() {
        return qpdDescription;
    }

    public void setQpdDescription(String qpdDescription) {
        this.qpdDescription = qpdDescription;
    }

    public Boolean getQpdDeleted() {
        return qpdDeleted;
    }

    public void setQpdDeleted(Boolean qpdDeleted) {
        this.qpdDeleted = qpdDeleted;
    }

    public Date getQpdCreated() {
        return qpdCreated;
    }

    public void setQpdCreated(Date qpdCreated) {
        this.qpdCreated = qpdCreated;
    }

    public Date getQpdChanged() {
        return qpdChanged;
    }

    public void setQpdChanged(Date qpdChanged) {
        this.qpdChanged = qpdChanged;
    }

    public QuesCategorie getQpdCategorie() {
        return qpdCategorie;
    }

    public void setQpdCategorie(QuesCategorie qpdCategorie) {
        this.qpdCategorie = qpdCategorie;
    }

    public QuesLevel getQpdLevel() {
        return qpdLevel;
    }

    public void setQpdLevel(QuesLevel qpdLevel) {
        this.qpdLevel = qpdLevel;
    }

    public QuesPlanpart getQpdPart() {
        return qpdPart;
    }

    public void setQpdPart(QuesPlanpart qpdPart) {
        this.qpdPart = qpdPart;
    }

    public QuesPlan getQpdPlan() {
        return qpdPlan;
    }

    public void setQpdPlan(QuesPlan qpdPlan) {
        this.qpdPlan = qpdPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qpdId != null ? qpdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesPlandef)) {
            return false;
        }
        QuesPlandef other = (QuesPlandef) object;
        if ((this.qpdId == null && other.qpdId != null) || (this.qpdId != null && !this.qpdId.equals(other.qpdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesPlandef[ qpdId=" + qpdId + " ]";
    }
    
}
