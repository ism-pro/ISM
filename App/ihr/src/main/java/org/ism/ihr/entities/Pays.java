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
@Table(catalog = "ihr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p")
    , @NamedQuery(name = "Pays.findByPId", query = "SELECT p FROM Pays p WHERE p.pId = :pId")
    , @NamedQuery(name = "Pays.findByPPays", query = "SELECT p FROM Pays p WHERE p.pPays = :pPays")
    , @NamedQuery(name = "Pays.findByPDesignation", query = "SELECT p FROM Pays p WHERE p.pDesignation = :pDesignation")
    , @NamedQuery(name = "Pays.findByPDeleted", query = "SELECT p FROM Pays p WHERE p.pDeleted = :pDeleted")
    , @NamedQuery(name = "Pays.findByPCreated", query = "SELECT p FROM Pays p WHERE p.pCreated = :pCreated")
    , @NamedQuery(name = "Pays.findByPChanged", query = "SELECT p FROM Pays p WHERE p.pChanged = :pChanged")
    ,@NamedQuery(name = "Pays.selectAllByLastChange", query = "SELECT p FROM Pays p ORDER BY p.pChanged DESC")
})

public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "p_id")
    private Integer pId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "p_pays")
    private String pPays;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "p_designation")
    private String pDesignation;
    @Column(name = "p_deleted")
    private Boolean pDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "p_changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pChanged;

    public Pays() {
    }

    public Pays(Integer pId) {
        this.pId = pId;
    }

    public Pays(Integer pId, String pPays, String pDesignation, Date pCreated, Date pChanged) {
        this.pId = pId;
        this.pPays = pPays;
        this.pDesignation = pDesignation;
        this.pCreated = pCreated;
        this.pChanged = pChanged;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getPPays() {
        return pPays;
    }

    public void setPPays(String pPays) {
        this.pPays = pPays;
    }

    public String getPDesignation() {
        return pDesignation;
    }

    public void setPDesignation(String pDesignation) {
        this.pDesignation = pDesignation;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pId != null ? pId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.pId == null && other.pId != null) || (this.pId != null && !this.pId.equals(other.pId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pPays + " - " + pDesignation + " [" + pId + " ]";
    }

}
