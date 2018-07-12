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
    @UniqueConstraint(columnNames = {"t_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Town.findAll", query = "SELECT t FROM Town t")
    , @NamedQuery(name = "Town.findByTId", query = "SELECT t FROM Town t WHERE t.tId = :tId")
    , @NamedQuery(name = "Town.findByTTown", query = "SELECT t FROM Town t WHERE t.tTown = :tTown")
    , @NamedQuery(name = "Town.findByTDesignation", query = "SELECT t FROM Town t WHERE t.tDesignation = :tDesignation")
    , @NamedQuery(name = "Town.findByTCode", query = "SELECT t FROM Town t WHERE t.tCode = :tCode")
    , @NamedQuery(name = "Town.findByTDeleted", query = "SELECT t FROM Town t WHERE t.tDeleted = :tDeleted")
    , @NamedQuery(name = "Town.findByTCreated", query = "SELECT t FROM Town t WHERE t.tCreated = :tCreated")
    , @NamedQuery(name = "Town.findByTChanged", query = "SELECT t FROM Town t WHERE t.tChanged = :tChanged")})
public class Town implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "t_id", nullable = false)
    private Integer tId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "t_town", nullable = false, length = 45)
    private String tTown;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "t_designation", nullable = false, length = 255)
    private String tDesignation;
    @Size(max = 25)
    @Column(name = "t_code", length = 25)
    private String tCode;
    @Column(name = "t_deleted")
    private Boolean tDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "t_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "t_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cTown")
    private Collection<Candidate> candidateCollection;
    @OneToMany(mappedBy = "sTown")
    private Collection<School> schoolCollection;
    @JoinColumn(name = "t_city", referencedColumnName = "c_city", nullable = false)
    @ManyToOne(optional = false)
    private Cities tCity;
    @JoinColumn(name = "t_locale", referencedColumnName = "l_locale")
    @ManyToOne
    private Locale tLocale;

    public Town() {
    }

    public Town(Integer tId) {
        this.tId = tId;
    }

    public Town(Integer tId, String tTown, String tDesignation, Date tCreated, Date tChanged) {
        this.tId = tId;
        this.tTown = tTown;
        this.tDesignation = tDesignation;
        this.tCreated = tCreated;
        this.tChanged = tChanged;
    }

    public Integer getTId() {
        return tId;
    }

    public void setTId(Integer tId) {
        this.tId = tId;
    }

    public String getTTown() {
        return tTown;
    }

    public void setTTown(String tTown) {
        this.tTown = tTown;
    }

    public String getTDesignation() {
        return tDesignation;
    }

    public void setTDesignation(String tDesignation) {
        this.tDesignation = tDesignation;
    }

    public String getTCode() {
        return tCode;
    }

    public void setTCode(String tCode) {
        this.tCode = tCode;
    }

    public Boolean getTDeleted() {
        return tDeleted;
    }

    public void setTDeleted(Boolean tDeleted) {
        this.tDeleted = tDeleted;
    }

    public Date getTCreated() {
        return tCreated;
    }

    public void setTCreated(Date tCreated) {
        this.tCreated = tCreated;
    }

    public Date getTChanged() {
        return tChanged;
    }

    public void setTChanged(Date tChanged) {
        this.tChanged = tChanged;
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

    public Cities getTCity() {
        return tCity;
    }

    public void setTCity(Cities tCity) {
        this.tCity = tCity;
    }

    public Locale getTLocale() {
        return tLocale;
    }

    public void setTLocale(Locale tLocale) {
        this.tLocale = tLocale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tId != null ? tId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Town)) {
            return false;
        }
        Town other = (Town) object;
        if ((this.tId == null && other.tId != null) || (this.tId != null && !this.tId.equals(other.tId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.Town[ tId=" + tId + " ]";
    }
    
}
