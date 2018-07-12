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
@Table(name = "sch_degree", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sd_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchDegree.findAll", query = "SELECT s FROM SchDegree s")
    , @NamedQuery(name = "SchDegree.findBySdId", query = "SELECT s FROM SchDegree s WHERE s.sdId = :sdId")
    , @NamedQuery(name = "SchDegree.findBySdDegree", query = "SELECT s FROM SchDegree s WHERE s.sdDegree = :sdDegree")
    , @NamedQuery(name = "SchDegree.findBySdDesignation", query = "SELECT s FROM SchDegree s WHERE s.sdDesignation = :sdDesignation")
    , @NamedQuery(name = "SchDegree.findBySdDeleted", query = "SELECT s FROM SchDegree s WHERE s.sdDeleted = :sdDeleted")
    , @NamedQuery(name = "SchDegree.findBySdCreated", query = "SELECT s FROM SchDegree s WHERE s.sdCreated = :sdCreated")
    , @NamedQuery(name = "SchDegree.findBySdChanged", query = "SELECT s FROM SchDegree s WHERE s.sdChanged = :sdChanged")})
public class SchDegree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sd_id", nullable = false)
    private Integer sdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sd_degree", nullable = false, length = 45)
    private String sdDegree;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sd_designation", nullable = false, length = 255)
    private String sdDesignation;
    @Column(name = "sd_deleted")
    private Boolean sdDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sd_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sd_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cDegree")
    private Collection<Candidate> candidateCollection;

    public SchDegree() {
    }

    public SchDegree(Integer sdId) {
        this.sdId = sdId;
    }

    public SchDegree(Integer sdId, String sdDegree, String sdDesignation, Date sdCreated, Date sdChanged) {
        this.sdId = sdId;
        this.sdDegree = sdDegree;
        this.sdDesignation = sdDesignation;
        this.sdCreated = sdCreated;
        this.sdChanged = sdChanged;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public String getSdDegree() {
        return sdDegree;
    }

    public void setSdDegree(String sdDegree) {
        this.sdDegree = sdDegree;
    }

    public String getSdDesignation() {
        return sdDesignation;
    }

    public void setSdDesignation(String sdDesignation) {
        this.sdDesignation = sdDesignation;
    }

    public Boolean getSdDeleted() {
        return sdDeleted;
    }

    public void setSdDeleted(Boolean sdDeleted) {
        this.sdDeleted = sdDeleted;
    }

    public Date getSdCreated() {
        return sdCreated;
    }

    public void setSdCreated(Date sdCreated) {
        this.sdCreated = sdCreated;
    }

    public Date getSdChanged() {
        return sdChanged;
    }

    public void setSdChanged(Date sdChanged) {
        this.sdChanged = sdChanged;
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
        hash += (sdId != null ? sdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchDegree)) {
            return false;
        }
        SchDegree other = (SchDegree) object;
        if ((this.sdId == null && other.sdId != null) || (this.sdId != null && !this.sdId.equals(other.sdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.SchDegree[ sdId=" + sdId + " ]";
    }
    
}
