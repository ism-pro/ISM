/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.admin;

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
import org.ism.ihr.entities.contact.Candidate;

/**
 *
 * @author r.hendrick
 */
@Entity
@Table(name = "civil_state", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cs_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CivilState.findAll", query = "SELECT c FROM CivilState c")
    , @NamedQuery(name = "CivilState.findByCsId", query = "SELECT c FROM CivilState c WHERE c.csId = :csId")
    , @NamedQuery(name = "CivilState.findByCsState", query = "SELECT c FROM CivilState c WHERE c.csState = :csState")
    , @NamedQuery(name = "CivilState.findByCsDesignation", query = "SELECT c FROM CivilState c WHERE c.csDesignation = :csDesignation")
    , @NamedQuery(name = "CivilState.findByCsDescription", query = "SELECT c FROM CivilState c WHERE c.csDescription = :csDescription")
    , @NamedQuery(name = "CivilState.findByCsDeleted", query = "SELECT c FROM CivilState c WHERE c.csDeleted = :csDeleted")
    , @NamedQuery(name = "CivilState.findByCsCreated", query = "SELECT c FROM CivilState c WHERE c.csCreated = :csCreated")
    , @NamedQuery(name = "CivilState.findByCsChanged", query = "SELECT c FROM CivilState c WHERE c.csChanged = :csChanged")})
public class CivilState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cs_id", nullable = false)
    private Integer csId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cs_state", nullable = false, length = 45)
    private String csState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cs_designation", nullable = false, length = 255)
    private String csDesignation;
    @Size(max = 512)
    @Column(name = "cs_description", length = 512)
    private String csDescription;
    @Column(name = "cs_deleted")
    private Boolean csDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cs_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date csCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cs_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date csChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ccivilState")
    private Collection<Candidate> candidateCollection;

    public CivilState() {
    }

    public CivilState(Integer csId) {
        this.csId = csId;
    }

    public CivilState(Integer csId, String csState, String csDesignation, Date csCreated, Date csChanged) {
        this.csId = csId;
        this.csState = csState;
        this.csDesignation = csDesignation;
        this.csCreated = csCreated;
        this.csChanged = csChanged;
    }

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public String getCsState() {
        return csState;
    }

    public void setCsState(String csState) {
        this.csState = csState;
    }

    public String getCsDesignation() {
        return csDesignation;
    }

    public void setCsDesignation(String csDesignation) {
        this.csDesignation = csDesignation;
    }

    public String getCsDescription() {
        return csDescription;
    }

    public void setCsDescription(String csDescription) {
        this.csDescription = csDescription;
    }

    public Boolean getCsDeleted() {
        return csDeleted;
    }

    public void setCsDeleted(Boolean csDeleted) {
        this.csDeleted = csDeleted;
    }

    public Date getCsCreated() {
        return csCreated;
    }

    public void setCsCreated(Date csCreated) {
        this.csCreated = csCreated;
    }

    public Date getCsChanged() {
        return csChanged;
    }

    public void setCsChanged(Date csChanged) {
        this.csChanged = csChanged;
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
        hash += (csId != null ? csId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CivilState)) {
            return false;
        }
        CivilState other = (CivilState) object;
        if ((this.csId == null && other.csId != null) || (this.csId != null && !this.csId.equals(other.csId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.CivilState[ csId=" + csId + " ]";
    }
    
}
