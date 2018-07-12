/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.map;

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
    @NamedQuery(name = "Cities.findAll", query = "SELECT c FROM Cities c")
    , @NamedQuery(name = "Cities.findByCId", query = "SELECT c FROM Cities c WHERE c.cId = :cId")
    , @NamedQuery(name = "Cities.findByCCity", query = "SELECT c FROM Cities c WHERE c.cCity = :cCity")
    , @NamedQuery(name = "Cities.findByCDesignation", query = "SELECT c FROM Cities c WHERE c.cDesignation = :cDesignation")
    , @NamedQuery(name = "Cities.findByCGeoname", query = "SELECT c FROM Cities c WHERE c.cGeoname = :cGeoname")
    , @NamedQuery(name = "Cities.findByCProvince", query = "SELECT c FROM Cities c WHERE c.cProvince = :cProvince")
    , @NamedQuery(name = "Cities.findByCDeleted", query = "SELECT c FROM Cities c WHERE c.cDeleted = :cDeleted")
    , @NamedQuery(name = "Cities.findByCCreated", query = "SELECT c FROM Cities c WHERE c.cCreated = :cCreated")
    , @NamedQuery(name = "Cities.findByCChanged", query = "SELECT c FROM Cities c WHERE c.cChanged = :cChanged")})
public class Cities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "c_id", nullable = false)
    private Integer cId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "c_city", nullable = false, length = 45)
    private String cCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "c_designation", nullable = false, length = 255)
    private String cDesignation;
    @Column(name = "c_geoname")
    private Integer cGeoname;
    @Size(max = 45)
    @Column(name = "c_province", length = 45)
    private String cProvince;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cCity")
    private Collection<Candidate> candidateCollection;
    @OneToMany(mappedBy = "sCity")
    private Collection<School> schoolCollection;
    @JoinColumn(name = "c_country", referencedColumnName = "c_country", nullable = false)
    @ManyToOne(optional = false)
    private Country cCountry;
    @JoinColumn(name = "c_locale", referencedColumnName = "l_locale")
    @ManyToOne
    private Locale cLocale;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tCity")
    private Collection<Town> townCollection;

    public Cities() {
    }

    public Cities(Integer cId) {
        this.cId = cId;
    }

    public Cities(Integer cId, String cCity, String cDesignation, Date cCreated, Date cChanged) {
        this.cId = cId;
        this.cCity = cCity;
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

    public String getCCity() {
        return cCity;
    }

    public void setCCity(String cCity) {
        this.cCity = cCity;
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

    public String getCProvince() {
        return cProvince;
    }

    public void setCProvince(String cProvince) {
        this.cProvince = cProvince;
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

    public Country getCCountry() {
        return cCountry;
    }

    public void setCCountry(Country cCountry) {
        this.cCountry = cCountry;
    }

    public Locale getCLocale() {
        return cLocale;
    }

    public void setCLocale(Locale cLocale) {
        this.cLocale = cLocale;
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
        hash += (cId != null ? cId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cities)) {
            return false;
        }
        Cities other = (Cities) object;
        if ((this.cId == null && other.cId != null) || (this.cId != null && !this.cId.equals(other.cId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.Cities[ cId=" + cId + " ]";
    }
    
}
