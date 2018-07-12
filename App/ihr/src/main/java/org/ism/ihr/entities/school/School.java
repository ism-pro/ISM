/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.school;

import org.ism.ihr.entities.contact.Candidate;
import org.ism.ihr.entities.map.Country;
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
@Table(catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"s_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s")
    , @NamedQuery(name = "School.findBySId", query = "SELECT s FROM School s WHERE s.sId = :sId")
    , @NamedQuery(name = "School.findBySSchool", query = "SELECT s FROM School s WHERE s.sSchool = :sSchool")
    , @NamedQuery(name = "School.findBySDesignation", query = "SELECT s FROM School s WHERE s.sDesignation = :sDesignation")
    , @NamedQuery(name = "School.findBySDescription", query = "SELECT s FROM School s WHERE s.sDescription = :sDescription")
    , @NamedQuery(name = "School.findBySSiteweb", query = "SELECT s FROM School s WHERE s.sSiteweb = :sSiteweb")
    , @NamedQuery(name = "School.findBySMails", query = "SELECT s FROM School s WHERE s.sMails = :sMails")
    , @NamedQuery(name = "School.findBySPhones", query = "SELECT s FROM School s WHERE s.sPhones = :sPhones")
    , @NamedQuery(name = "School.findBySDeleted", query = "SELECT s FROM School s WHERE s.sDeleted = :sDeleted")
    , @NamedQuery(name = "School.findBySCreated", query = "SELECT s FROM School s WHERE s.sCreated = :sCreated")
    , @NamedQuery(name = "School.findBySChanged", query = "SELECT s FROM School s WHERE s.sChanged = :sChanged")})
public class School implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "s_id", nullable = false)
    private Integer sId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "s_school", nullable = false, length = 45)
    private String sSchool;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "s_designation", nullable = false, length = 255)
    private String sDesignation;
    @Size(max = 255)
    @Column(name = "s_description", length = 255)
    private String sDescription;
    @Size(max = 255)
    @Column(name = "s_siteweb", length = 255)
    private String sSiteweb;
    @Size(max = 45)
    @Column(name = "s_mails", length = 45)
    private String sMails;
    @Size(max = 45)
    @Column(name = "s_phones", length = 45)
    private String sPhones;
    @Column(name = "s_deleted")
    private Boolean sDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "s_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "s_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cSchool")
    private Collection<Candidate> candidateCollection;
    @JoinColumn(name = "s_city", referencedColumnName = "c_city")
    @ManyToOne
    private Cities sCity;
    @JoinColumn(name = "s_country", referencedColumnName = "c_continent", nullable = false)
    @ManyToOne(optional = false)
    private Country sCountry;
    @JoinColumn(name = "s_town", referencedColumnName = "t_town")
    @ManyToOne
    private Town sTown;

    public School() {
    }

    public School(Integer sId) {
        this.sId = sId;
    }

    public School(Integer sId, String sSchool, String sDesignation, Date sCreated, Date sChanged) {
        this.sId = sId;
        this.sSchool = sSchool;
        this.sDesignation = sDesignation;
        this.sCreated = sCreated;
        this.sChanged = sChanged;
    }

    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public String getSSchool() {
        return sSchool;
    }

    public void setSSchool(String sSchool) {
        this.sSchool = sSchool;
    }

    public String getSDesignation() {
        return sDesignation;
    }

    public void setSDesignation(String sDesignation) {
        this.sDesignation = sDesignation;
    }

    public String getSDescription() {
        return sDescription;
    }

    public void setSDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public String getSSiteweb() {
        return sSiteweb;
    }

    public void setSSiteweb(String sSiteweb) {
        this.sSiteweb = sSiteweb;
    }

    public String getSMails() {
        return sMails;
    }

    public void setSMails(String sMails) {
        this.sMails = sMails;
    }

    public String getSPhones() {
        return sPhones;
    }

    public void setSPhones(String sPhones) {
        this.sPhones = sPhones;
    }

    public Boolean getSDeleted() {
        return sDeleted;
    }

    public void setSDeleted(Boolean sDeleted) {
        this.sDeleted = sDeleted;
    }

    public Date getSCreated() {
        return sCreated;
    }

    public void setSCreated(Date sCreated) {
        this.sCreated = sCreated;
    }

    public Date getSChanged() {
        return sChanged;
    }

    public void setSChanged(Date sChanged) {
        this.sChanged = sChanged;
    }

    @XmlTransient
    public Collection<Candidate> getCandidateCollection() {
        return candidateCollection;
    }

    public void setCandidateCollection(Collection<Candidate> candidateCollection) {
        this.candidateCollection = candidateCollection;
    }

    public Cities getSCity() {
        return sCity;
    }

    public void setSCity(Cities sCity) {
        this.sCity = sCity;
    }

    public Country getSCountry() {
        return sCountry;
    }

    public void setSCountry(Country sCountry) {
        this.sCountry = sCountry;
    }

    public Town getSTown() {
        return sTown;
    }

    public void setSTown(Town sTown) {
        this.sTown = sTown;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sId != null ? sId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.sId == null && other.sId != null) || (this.sId != null && !this.sId.equals(other.sId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.School[ sId=" + sId + " ]";
    }
    
}
