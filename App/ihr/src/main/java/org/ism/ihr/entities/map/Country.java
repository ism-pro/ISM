/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.map;

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
import org.ism.ihr.entities.contact.Candidate;
import org.ism.ihr.entities.admin.Locale;
import org.ism.ihr.entities.school.School;

/**
 *
 * @author r.hendrick
 */
@Entity
@Table(catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"c_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
    , @NamedQuery(name = "Country.findByCId", query = "SELECT c FROM Country c WHERE c.cId = :cId")
    , @NamedQuery(name = "Country.findByCCountry", query = "SELECT c FROM Country c WHERE c.cCountry = :cCountry")
    , @NamedQuery(name = "Country.findByCDesignation", query = "SELECT c FROM Country c WHERE c.cDesignation = :cDesignation")
    , @NamedQuery(name = "Country.findByCGeoname", query = "SELECT c FROM Country c WHERE c.cGeoname = :cGeoname")
    , @NamedQuery(name = "Country.findByCisinEU", query = "SELECT c FROM Country c WHERE c.cisinEU = :cisinEU")
    , @NamedQuery(name = "Country.findByCDeleted", query = "SELECT c FROM Country c WHERE c.cDeleted = :cDeleted")
    , @NamedQuery(name = "Country.findByCCreated", query = "SELECT c FROM Country c WHERE c.cCreated = :cCreated")
    , @NamedQuery(name = "Country.findByCChanged", query = "SELECT c FROM Country c WHERE c.cChanged = :cChanged")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "c_id", nullable = false)
    private Integer cId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "c_country", nullable = false, length = 45)
    private String cCountry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "c_designation", nullable = false, length = 255)
    private String cDesignation;
    @Column(name = "c_geoname")
    private Integer cGeoname;
    @Column(name = "c_is_in_EU")
    private Boolean cisinEU;
    @Column(name = "c_deleted")
    private Boolean cDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cChanged;
    @JoinColumn(name = "c_continent", referencedColumnName = "c_continent")
    @ManyToOne
    private Continent cContinent;
    @JoinColumn(name = "c_locale", referencedColumnName = "l_locale")
    @ManyToOne
    private Locale cLocale;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cCountry")
    private Collection<Candidate> candidateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sCountry")
    private Collection<School> schoolCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cCountry")
    private Collection<Cities> citiesCollection;

    public Country() {
    }

    public Country(Integer cId) {
        this.cId = cId;
    }

    public Country(Integer cId, String cCountry, String cDesignation, Date cCreated, Date cChanged) {
        this.cId = cId;
        this.cCountry = cCountry;
        this.cDesignation = cDesignation;
        this.cCreated = cCreated;
        this.cChanged = cChanged;
    }

    public Integer getCId() {
        return cId;
    }

    public void setCId(Integer cId) {
        this.cId = cId;
    }

    public String getCCountry() {
        return cCountry;
    }

    public void setCCountry(String cCountry) {
        this.cCountry = cCountry;
    }

    public String getCDesignation() {
        return cDesignation;
    }

    public void setCDesignation(String cDesignation) {
        this.cDesignation = cDesignation;
    }

    public Integer getCGeoname() {
        return cGeoname;
    }

    public void setCGeoname(Integer cGeoname) {
        this.cGeoname = cGeoname;
    }

    public Boolean getCisinEU() {
        return cisinEU;
    }

    public void setCisinEU(Boolean cisinEU) {
        this.cisinEU = cisinEU;
    }

    public Boolean getCDeleted() {
        return cDeleted;
    }

    public void setCDeleted(Boolean cDeleted) {
        this.cDeleted = cDeleted;
    }

    public Date getCCreated() {
        return cCreated;
    }

    public void setCCreated(Date cCreated) {
        this.cCreated = cCreated;
    }

    public Date getCChanged() {
        return cChanged;
    }

    public void setCChanged(Date cChanged) {
        this.cChanged = cChanged;
    }

    public Continent getCContinent() {
        return cContinent;
    }

    public void setCContinent(Continent cContinent) {
        this.cContinent = cContinent;
    }

    public Locale getCLocale() {
        return cLocale;
    }

    public void setCLocale(Locale cLocale) {
        this.cLocale = cLocale;
    }

    @XmlTransient
    public Collection<Candidate> getCandidateCollection() {
        return candidateCollection;
    }

    public void setCandidateCollection(Collection<Candidate> candidateCollection) {
        this.candidateCollection = candidateCollection;
    }

    @XmlTransient
    public Collection<School> getSchoolCollection() {
        return schoolCollection;
    }

    public void setSchoolCollection(Collection<School> schoolCollection) {
        this.schoolCollection = schoolCollection;
    }

    @XmlTransient
    public Collection<Cities> getCitiesCollection() {
        return citiesCollection;
    }

    public void setCitiesCollection(Collection<Cities> citiesCollection) {
        this.citiesCollection = citiesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cId != null ? cId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.cId == null && other.cId != null) || (this.cId != null && !this.cId.equals(other.cId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.Country[ cId=" + cId + " ]";
    }
    
}
