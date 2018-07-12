/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.school;

import org.ism.ihr.entities.contact.Candidate;
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
@Table(name = "sch_qualification", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sq_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchQualification.findAll", query = "SELECT s FROM SchQualification s")
    , @NamedQuery(name = "SchQualification.findBySqId", query = "SELECT s FROM SchQualification s WHERE s.sqId = :sqId")
    , @NamedQuery(name = "SchQualification.findBySqQualification", query = "SELECT s FROM SchQualification s WHERE s.sqQualification = :sqQualification")
    , @NamedQuery(name = "SchQualification.findBySqDesignation", query = "SELECT s FROM SchQualification s WHERE s.sqDesignation = :sqDesignation")
    , @NamedQuery(name = "SchQualification.findBySqType", query = "SELECT s FROM SchQualification s WHERE s.sqType = :sqType")
    , @NamedQuery(name = "SchQualification.findBySqDeleted", query = "SELECT s FROM SchQualification s WHERE s.sqDeleted = :sqDeleted")
    , @NamedQuery(name = "SchQualification.findBySqCreated", query = "SELECT s FROM SchQualification s WHERE s.sqCreated = :sqCreated")
    , @NamedQuery(name = "SchQualification.findBySqChanged", query = "SELECT s FROM SchQualification s WHERE s.sqChanged = :sqChanged")})
public class SchQualification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sq_id", nullable = false)
    private Integer sqId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sq_qualification", nullable = false, length = 45)
    private String sqQualification;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sq_designation", nullable = false, length = 255)
    private String sqDesignation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sq_type", nullable = false, length = 255)
    private String sqType;
    @Column(name = "sq_deleted")
    private Boolean sqDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sq_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sqCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sq_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sqChanged;
    @OneToMany(mappedBy = "cQualification")
    private Collection<Candidate> candidateCollection;

    public SchQualification() {
    }

    public SchQualification(Integer sqId) {
        this.sqId = sqId;
    }

    public SchQualification(Integer sqId, String sqQualification, String sqDesignation, String sqType, Date sqCreated, Date sqChanged) {
        this.sqId = sqId;
        this.sqQualification = sqQualification;
        this.sqDesignation = sqDesignation;
        this.sqType = sqType;
        this.sqCreated = sqCreated;
        this.sqChanged = sqChanged;
    }

    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
    }

    public String getSqQualification() {
        return sqQualification;
    }

    public void setSqQualification(String sqQualification) {
        this.sqQualification = sqQualification;
    }

    public String getSqDesignation() {
        return sqDesignation;
    }

    public void setSqDesignation(String sqDesignation) {
        this.sqDesignation = sqDesignation;
    }

    public String getSqType() {
        return sqType;
    }

    public void setSqType(String sqType) {
        this.sqType = sqType;
    }

    public Boolean getSqDeleted() {
        return sqDeleted;
    }

    public void setSqDeleted(Boolean sqDeleted) {
        this.sqDeleted = sqDeleted;
    }

    public Date getSqCreated() {
        return sqCreated;
    }

    public void setSqCreated(Date sqCreated) {
        this.sqCreated = sqCreated;
    }

    public Date getSqChanged() {
        return sqChanged;
    }

    public void setSqChanged(Date sqChanged) {
        this.sqChanged = sqChanged;
    }

    @XmlTransient
    public Collection<Candidate> getCandidateCollection() {
        return candidateCollection;
    }

    public void setCandidateCollection(Collection<Candidate> candidateCollection) {
        this.candidateCollection = candidateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sqId != null ? sqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchQualification)) {
            return false;
        }
        SchQualification other = (SchQualification) object;
        if ((this.sqId == null && other.sqId != null) || (this.sqId != null && !this.sqId.equals(other.sqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.SchQualification[ sqId=" + sqId + " ]";
    }
    
}
