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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "ques_list", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ql_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesList.findAll", query = "SELECT q FROM QuesList q")
    , @NamedQuery(name = "QuesList.findByQlId", query = "SELECT q FROM QuesList q WHERE q.qlId = :qlId")
    , @NamedQuery(name = "QuesList.findByQlDesignation", query = "SELECT q FROM QuesList q WHERE q.qlDesignation = :qlDesignation")
    , @NamedQuery(name = "QuesList.findByQlPoint", query = "SELECT q FROM QuesList q WHERE q.qlPoint = :qlPoint")
    , @NamedQuery(name = "QuesList.findByQlPointPrecision", query = "SELECT q FROM QuesList q WHERE q.qlPointPrecision = :qlPointPrecision")
    , @NamedQuery(name = "QuesList.findByQlDuration", query = "SELECT q FROM QuesList q WHERE q.qlDuration = :qlDuration")
    , @NamedQuery(name = "QuesList.findByQlDescription", query = "SELECT q FROM QuesList q WHERE q.qlDescription = :qlDescription")
    , @NamedQuery(name = "QuesList.findByQlDeleted", query = "SELECT q FROM QuesList q WHERE q.qlDeleted = :qlDeleted")
    , @NamedQuery(name = "QuesList.findByQlCreated", query = "SELECT q FROM QuesList q WHERE q.qlCreated = :qlCreated")
    , @NamedQuery(name = "QuesList.findByQlChanged", query = "SELECT q FROM QuesList q WHERE q.qlChanged = :qlChanged")})
public class QuesList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ql_id", nullable = false)
    private Integer qlId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ql_designation", nullable = false, length = 255)
    private String qlDesignation;
    @Lob
    @Column(name = "ql_image")
    private byte[] qlImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ql_point", nullable = false)
    private double qlPoint;
    @Column(name = "ql_point_precision")
    private Boolean qlPointPrecision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ql_duration", nullable = false)
    private int qlDuration;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qrList")
    private Collection<QuesResponse> quesResponseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qcList")
    private Collection<QuesChoice> quesChoiceCollection;
    @JoinColumn(name = "ql_categorie", referencedColumnName = "qc_categorie", nullable = false)
    @ManyToOne(optional = false)
    private QuesCategorie qlCategorie;
    @JoinColumn(name = "ql_groupe", referencedColumnName = "qg_groupe")
    @ManyToOne
    private QuesGroupe qlGroupe;
    @JoinColumn(name = "ql_level", referencedColumnName = "ql_level", nullable = false)
    @ManyToOne(optional = false)
    private QuesLevel qlLevel;
    @JoinColumn(name = "ql_multiple", referencedColumnName = "qm_multiple", nullable = false)
    @ManyToOne(optional = false)
    private QuesMultiple qlMultiple;
    @JoinColumn(name = "ql_type", referencedColumnName = "qt_type", nullable = false)
    @ManyToOne(optional = false)
    private QuesType qlType;

    public QuesList() {
    }

    public QuesList(Integer qlId) {
        this.qlId = qlId;
    }

    public QuesList(Integer qlId, String qlDesignation, double qlPoint, int qlDuration, Date qlCreated, Date qlChanged) {
        this.qlId = qlId;
        this.qlDesignation = qlDesignation;
        this.qlPoint = qlPoint;
        this.qlDuration = qlDuration;
        this.qlCreated = qlCreated;
        this.qlChanged = qlChanged;
    }

    public Integer getQlId() {
        return qlId;
    }

    public void setQlId(Integer qlId) {
        this.qlId = qlId;
    }

    public String getQlDesignation() {
        return qlDesignation;
    }

    public void setQlDesignation(String qlDesignation) {
        this.qlDesignation = qlDesignation;
    }

    public byte[] getQlImage() {
        return qlImage;
    }

    public void setQlImage(byte[] qlImage) {
        this.qlImage = qlImage;
    }

    public double getQlPoint() {
        return qlPoint;
    }

    public void setQlPoint(double qlPoint) {
        this.qlPoint = qlPoint;
    }

    public Boolean getQlPointPrecision() {
        return qlPointPrecision;
    }

    public void setQlPointPrecision(Boolean qlPointPrecision) {
        this.qlPointPrecision = qlPointPrecision;
    }

    public int getQlDuration() {
        return qlDuration;
    }

    public void setQlDuration(int qlDuration) {
        this.qlDuration = qlDuration;
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
    public Collection<QuesResponse> getQuesResponseCollection() {
        return quesResponseCollection;
    }

    public void setQuesResponseCollection(Collection<QuesResponse> quesResponseCollection) {
        this.quesResponseCollection = quesResponseCollection;
    }

    @XmlTransient
    public Collection<QuesChoice> getQuesChoiceCollection() {
        return quesChoiceCollection;
    }

    public void setQuesChoiceCollection(Collection<QuesChoice> quesChoiceCollection) {
        this.quesChoiceCollection = quesChoiceCollection;
    }

    public QuesCategorie getQlCategorie() {
        return qlCategorie;
    }

    public void setQlCategorie(QuesCategorie qlCategorie) {
        this.qlCategorie = qlCategorie;
    }

    public QuesGroupe getQlGroupe() {
        return qlGroupe;
    }

    public void setQlGroupe(QuesGroupe qlGroupe) {
        this.qlGroupe = qlGroupe;
    }

    public QuesLevel getQlLevel() {
        return qlLevel;
    }

    public void setQlLevel(QuesLevel qlLevel) {
        this.qlLevel = qlLevel;
    }

    public QuesMultiple getQlMultiple() {
        return qlMultiple;
    }

    public void setQlMultiple(QuesMultiple qlMultiple) {
        this.qlMultiple = qlMultiple;
    }

    public QuesType getQlType() {
        return qlType;
    }

    public void setQlType(QuesType qlType) {
        this.qlType = qlType;
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
        if (!(object instanceof QuesList)) {
            return false;
        }
        QuesList other = (QuesList) object;
        if ((this.qlId == null && other.qlId != null) || (this.qlId != null && !this.qlId.equals(other.qlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesList[ qlId=" + qlId + " ]";
    }
    
}
