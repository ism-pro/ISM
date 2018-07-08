/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author r.hendrick
 */
@Entity
@Table(name = "scola_niveau", catalog = "ihr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScolaNiveau.findAll", query = "SELECT s FROM ScolaNiveau s")
    , @NamedQuery(name = "ScolaNiveau.findBySnId", query = "SELECT s FROM ScolaNiveau s WHERE s.snId = :snId")
    , @NamedQuery(name = "ScolaNiveau.findBySnNiveau", query = "SELECT s FROM ScolaNiveau s WHERE s.snNiveau = :snNiveau")
    , @NamedQuery(name = "ScolaNiveau.findBySnDesignation", query = "SELECT s FROM ScolaNiveau s WHERE s.snDesignation = :snDesignation")
    , @NamedQuery(name = "ScolaNiveau.findBySnDeleted", query = "SELECT s FROM ScolaNiveau s WHERE s.snDeleted = :snDeleted")
    , @NamedQuery(name = "ScolaNiveau.findBySnCreated", query = "SELECT s FROM ScolaNiveau s WHERE s.snCreated = :snCreated")
    , @NamedQuery(name = "ScolaNiveau.findBySnChanged", query = "SELECT s FROM ScolaNiveau s WHERE s.snChanged = :snChanged")})
public class ScolaNiveau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sn_id")
    private Integer snId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sn_niveau")
    private String snNiveau;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sn_designation")
    private String snDesignation;
    @Column(name = "sn_deleted")
    private Boolean snDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sn_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date snCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sn_changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date snChanged;

    public ScolaNiveau() {
    }

    public ScolaNiveau(Integer snId) {
        this.snId = snId;
    }

    public ScolaNiveau(Integer snId, String snNiveau, String snDesignation, Date snCreated, Date snChanged) {
        this.snId = snId;
        this.snNiveau = snNiveau;
        this.snDesignation = snDesignation;
        this.snCreated = snCreated;
        this.snChanged = snChanged;
    }

    public Integer getSnId() {
        return snId;
    }

    public void setSnId(Integer snId) {
        this.snId = snId;
    }

    public String getSnNiveau() {
        return snNiveau;
    }

    public void setSnNiveau(String snNiveau) {
        this.snNiveau = snNiveau;
    }

    public String getSnDesignation() {
        return snDesignation;
    }

    public void setSnDesignation(String snDesignation) {
        this.snDesignation = snDesignation;
    }

    public Boolean getSnDeleted() {
        return snDeleted;
    }

    public void setSnDeleted(Boolean snDeleted) {
        this.snDeleted = snDeleted;
    }

    public Date getSnCreated() {
        return snCreated;
    }

    public void setSnCreated(Date snCreated) {
        this.snCreated = snCreated;
    }

    public Date getSnChanged() {
        return snChanged;
    }

    public void setSnChanged(Date snChanged) {
        this.snChanged = snChanged;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (snId != null ? snId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScolaNiveau)) {
            return false;
        }
        ScolaNiveau other = (ScolaNiveau) object;
        if ((this.snId == null && other.snId != null) || (this.snId != null && !this.snId.equals(other.snId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return snNiveau + " - " + snDesignation + " [" + snId + "]";
    }
    
}
