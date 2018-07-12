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
import org.ism.ihr.entities.admin.Locale;
import org.ism.ihr.entities.contact.PhoPrefixe;

/**
 *
 * @author r.hendrick
 */
@Entity
@Table(catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"c_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Continent.findAll", query = "SELECT c FROM Continent c")
    , @NamedQuery(name = "Continent.findByCId", query = "SELECT c FROM Continent c WHERE c.cId = :cId")
    , @NamedQuery(name = "Continent.findByCContinent", query = "SELECT c FROM Continent c WHERE c.cContinent = :cContinent")
    , @NamedQuery(name = "Continent.findByCDesignation", query = "SELECT c FROM Continent c WHERE c.cDesignation = :cDesignation")
    , @NamedQuery(name = "Continent.findByCDeleted", query = "SELECT c FROM Continent c WHERE c.cDeleted = :cDeleted")
    , @NamedQuery(name = "Continent.findByCCreated", query = "SELECT c FROM Continent c WHERE c.cCreated = :cCreated")
    , @NamedQuery(name = "Continent.findByCChanged", query = "SELECT c FROM Continent c WHERE c.cChanged = :cChanged")})
public class Continent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "c_id", nullable = false)
    private Integer cId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "c_continent", nullable = false, length = 45)
    private String cContinent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "c_designation", nullable = false, length = 255)
    private String cDesignation;
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
    @JoinColumn(name = "c_locale", referencedColumnName = "l_locale", nullable = false)
    @ManyToOne(optional = false)
    private Locale cLocale;
    @OneToMany(mappedBy = "cContinent")
    private Collection<Country> countryCollection;
    @OneToMany(mappedBy = "pContinent")
    private Collection<PhoPrefixe> phoPrefixeCollection;

    public Continent() {
    }

    public Continent(Integer cId) {
        this.cId = cId;
    }

    public Continent(Integer cId, String cContinent, String cDesignation, Date cCreated, Date cChanged) {
        this.cId = cId;
        this.cContinent = cContinent;
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

    public String getCContinent() {
        return cContinent;
    }

    public void setCContinent(String cContinent) {
        this.cContinent = cContinent;
    }

    public String getCDesignation() {
        return cDesignation;
    }

    public void setCDesignation(String cDesignation) {
        this.cDesignation = cDesignation;
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

    public Locale getCLocale() {
        return cLocale;
    }

    public void setCLocale(Locale cLocale) {
        this.cLocale = cLocale;
    }

    @XmlTransient
    public Collection<Country> getCountryCollection() {
        return countryCollection;
    }

    public void setCountryCollection(Collection<Country> countryCollection) {
        this.countryCollection = countryCollection;
    }

    @XmlTransient
    public Collection<PhoPrefixe> getPhoPrefixeCollection() {
        return phoPrefixeCollection;
    }

    public void setPhoPrefixeCollection(Collection<PhoPrefixe> phoPrefixeCollection) {
        this.phoPrefixeCollection = phoPrefixeCollection;
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
        if (!(object instanceof Continent)) {
            return false;
        }
        Continent other = (Continent) object;
        if ((this.cId == null && other.cId != null) || (this.cId != null && !this.cId.equals(other.cId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.Continent[ cId=" + cId + " ]";
    }
    
}
