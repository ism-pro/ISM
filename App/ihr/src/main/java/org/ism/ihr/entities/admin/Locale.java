/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.admin;

import org.ism.ihr.entities.map.Country;
import org.ism.ihr.entities.map.Continent;
import org.ism.ihr.entities.map.Town;
import org.ism.ihr.entities.map.Cities;
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
@Table(catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"l_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locale.findAll", query = "SELECT l FROM Locale l")
    , @NamedQuery(name = "Locale.findByLId", query = "SELECT l FROM Locale l WHERE l.lId = :lId")
    , @NamedQuery(name = "Locale.findByLLocale", query = "SELECT l FROM Locale l WHERE l.lLocale = :lLocale")
    , @NamedQuery(name = "Locale.findByLDesignation", query = "SELECT l FROM Locale l WHERE l.lDesignation = :lDesignation")
    , @NamedQuery(name = "Locale.findByLDeleted", query = "SELECT l FROM Locale l WHERE l.lDeleted = :lDeleted")
    , @NamedQuery(name = "Locale.findByLCreated", query = "SELECT l FROM Locale l WHERE l.lCreated = :lCreated")
    , @NamedQuery(name = "Locale.findByLChanged", query = "SELECT l FROM Locale l WHERE l.lChanged = :lChanged")})
public class Locale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "l_id", nullable = false)
    private Integer lId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "l_locale", nullable = false, length = 10)
    private String lLocale;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "l_designation", nullable = false, length = 255)
    private String lDesignation;
    @Column(name = "l_deleted")
    private Boolean lDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "l_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "l_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cLocale")
    private Collection<Continent> continentCollection;
    @OneToMany(mappedBy = "cLocale")
    private Collection<Country> countryCollection;
    @OneToMany(mappedBy = "cLocale")
    private Collection<Cities> citiesCollection;
    @OneToMany(mappedBy = "tLocale")
    private Collection<Town> townCollection;

    public Locale() {
    }

    public Locale(Integer lId) {
        this.lId = lId;
    }

    public Locale(Integer lId, String lLocale, String lDesignation, Date lCreated, Date lChanged) {
        this.lId = lId;
        this.lLocale = lLocale;
        this.lDesignation = lDesignation;
        this.lCreated = lCreated;
        this.lChanged = lChanged;
    }

    public Integer getLId() {
        return lId;
    }

    public void setLId(Integer lId) {
        this.lId = lId;
    }

    public String getLLocale() {
        return lLocale;
    }

    public void setLLocale(String lLocale) {
        this.lLocale = lLocale;
    }

    public String getLDesignation() {
        return lDesignation;
    }

    public void setLDesignation(String lDesignation) {
        this.lDesignation = lDesignation;
    }

    public Boolean getLDeleted() {
        return lDeleted;
    }

    public void setLDeleted(Boolean lDeleted) {
        this.lDeleted = lDeleted;
    }

    public Date getLCreated() {
        return lCreated;
    }

    public void setLCreated(Date lCreated) {
        this.lCreated = lCreated;
    }

    public Date getLChanged() {
        return lChanged;
    }

    public void setLChanged(Date lChanged) {
        this.lChanged = lChanged;
    }

    @XmlTransient
    public Collection<Continent> getContinentCollection() {
        return continentCollection;
    }

    public void setContinentCollection(Collection<Continent> continentCollection) {
        this.continentCollection = continentCollection;
    }

    @XmlTransient
    public Collection<Country> getCountryCollection() {
        return countryCollection;
    }

    public void setCountryCollection(Collection<Country> countryCollection) {
        this.countryCollection = countryCollection;
    }

    @XmlTransient
    public Collection<Cities> getCitiesCollection() {
        return citiesCollection;
    }

    public void setCitiesCollection(Collection<Cities> citiesCollection) {
        this.citiesCollection = citiesCollection;
    }

    @XmlTransient
    public Collection<Town> getTownCollection() {
        return townCollection;
    }

    public void setTownCollection(Collection<Town> townCollection) {
        this.townCollection = townCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lId != null ? lId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locale)) {
            return false;
        }
        Locale other = (Locale) object;
        if ((this.lId == null && other.lId != null) || (this.lId != null && !this.lId.equals(other.lId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.Locale[ lId=" + lId + " ]";
    }
    
}
