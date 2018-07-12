/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.contact;

import org.ism.ihr.entities.map.Continent;
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
@Table(name = "pho_prefixe", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"p_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhoPrefixe.findAll", query = "SELECT p FROM PhoPrefixe p")
    , @NamedQuery(name = "PhoPrefixe.findByPId", query = "SELECT p FROM PhoPrefixe p WHERE p.pId = :pId")
    , @NamedQuery(name = "PhoPrefixe.findByPPrefixe", query = "SELECT p FROM PhoPrefixe p WHERE p.pPrefixe = :pPrefixe")
    , @NamedQuery(name = "PhoPrefixe.findByPDesignation", query = "SELECT p FROM PhoPrefixe p WHERE p.pDesignation = :pDesignation")
    , @NamedQuery(name = "PhoPrefixe.findByPRegion", query = "SELECT p FROM PhoPrefixe p WHERE p.pRegion = :pRegion")
    , @NamedQuery(name = "PhoPrefixe.findByPDeleted", query = "SELECT p FROM PhoPrefixe p WHERE p.pDeleted = :pDeleted")
    , @NamedQuery(name = "PhoPrefixe.findByPCreated", query = "SELECT p FROM PhoPrefixe p WHERE p.pCreated = :pCreated")
    , @NamedQuery(name = "PhoPrefixe.findByPChanged", query = "SELECT p FROM PhoPrefixe p WHERE p.pChanged = :pChanged")})
public class PhoPrefixe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "p_id", nullable = false)
    private Integer pId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "p_prefixe", nullable = false, length = 45)
    private String pPrefixe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "p_designation", nullable = false, length = 255)
    private String pDesignation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "p_region", nullable = false, length = 255)
    private String pRegion;
    @Column(name = "p_deleted")
    private Boolean pDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pChanged;
    @JoinColumn(name = "p_continent", referencedColumnName = "c_continent")
    @ManyToOne
    private Continent pContinent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pPrefixe")
    private Collection<Phone> phoneCollection;

    public PhoPrefixe() {
    }

    public PhoPrefixe(Integer pId) {
        this.pId = pId;
    }

    public PhoPrefixe(Integer pId, String pPrefixe, String pDesignation, String pRegion, Date pCreated, Date pChanged) {
        this.pId = pId;
        this.pPrefixe = pPrefixe;
        this.pDesignation = pDesignation;
        this.pRegion = pRegion;
        this.pCreated = pCreated;
        this.pChanged = pChanged;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getPPrefixe() {
        return pPrefixe;
    }

    public void setPPrefixe(String pPrefixe) {
        this.pPrefixe = pPrefixe;
    }

    public String getPDesignation() {
        return pDesignation;
    }

    public void setPDesignation(String pDesignation) {
        this.pDesignation = pDesignation;
    }

    public String getPRegion() {
        return pRegion;
    }

    public void setPRegion(String pRegion) {
        this.pRegion = pRegion;
    }

    public Boolean getPDeleted() {
        return pDeleted;
    }

    public void setPDeleted(Boolean pDeleted) {
        this.pDeleted = pDeleted;
    }

    public Date getPCreated() {
        return pCreated;
    }

    public void setPCreated(Date pCreated) {
        this.pCreated = pCreated;
    }

    public Date getPChanged() {
        return pChanged;
    }

    public void setPChanged(Date pChanged) {
        this.pChanged = pChanged;
    }

    public Continent getPContinent() {
        return pContinent;
    }

    public void setPContinent(Continent pContinent) {
        this.pContinent = pContinent;
    }

    @XmlTransient
    public Collection<Phone> getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(Collection<Phone> phoneCollection) {
        this.phoneCollection = phoneCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pId != null ? pId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhoPrefixe)) {
            return false;
        }
        PhoPrefixe other = (PhoPrefixe) object;
        if ((this.pId == null && other.pId != null) || (this.pId != null && !this.pId.equals(other.pId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.PhoPrefixe[ pId=" + pId + " ]";
    }
    
}
