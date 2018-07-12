/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.question;

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
@Table(name = "ques_type", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qt_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesType.findAll", query = "SELECT q FROM QuesType q")
    , @NamedQuery(name = "QuesType.findByQtId", query = "SELECT q FROM QuesType q WHERE q.qtId = :qtId")
    , @NamedQuery(name = "QuesType.findByQtType", query = "SELECT q FROM QuesType q WHERE q.qtType = :qtType")
    , @NamedQuery(name = "QuesType.findByQtDesignation", query = "SELECT q FROM QuesType q WHERE q.qtDesignation = :qtDesignation")
    , @NamedQuery(name = "QuesType.findByQtDescription", query = "SELECT q FROM QuesType q WHERE q.qtDescription = :qtDescription")
    , @NamedQuery(name = "QuesType.findByQtDeleted", query = "SELECT q FROM QuesType q WHERE q.qtDeleted = :qtDeleted")
    , @NamedQuery(name = "QuesType.findByQtCreated", query = "SELECT q FROM QuesType q WHERE q.qtCreated = :qtCreated")
    , @NamedQuery(name = "QuesType.findByQtChanged", query = "SELECT q FROM QuesType q WHERE q.qtChanged = :qtChanged")})
public class QuesType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qt_id", nullable = false)
    private Integer qtId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "qt_type", nullable = false, length = 45)
    private String qtType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "qt_designation", nullable = false, length = 255)
    private String qtDesignation;
    @Size(max = 512)
    @Column(name = "qt_description", length = 512)
    private String qtDescription;
    @Column(name = "qt_deleted")
    private Boolean qtDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qt_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qtCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qt_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qtChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qlType")
    private Collection<QuesList> quesListCollection;

    public QuesType() {
    }

    public QuesType(Integer qtId) {
        this.qtId = qtId;
    }

    public QuesType(Integer qtId, String qtType, String qtDesignation, Date qtCreated, Date qtChanged) {
        this.qtId = qtId;
        this.qtType = qtType;
        this.qtDesignation = qtDesignation;
        this.qtCreated = qtCreated;
        this.qtChanged = qtChanged;
    }

    public Integer getQtId() {
        return qtId;
    }

    public void setQtId(Integer qtId) {
        this.qtId = qtId;
    }

    public String getQtType() {
        return qtType;
    }

    public void setQtType(String qtType) {
        this.qtType = qtType;
    }

    public String getQtDesignation() {
        return qtDesignation;
    }

    public void setQtDesignation(String qtDesignation) {
        this.qtDesignation = qtDesignation;
    }

    public String getQtDescription() {
        return qtDescription;
    }

    public void setQtDescription(String qtDescription) {
        this.qtDescription = qtDescription;
    }

    public Boolean getQtDeleted() {
        return qtDeleted;
    }

    public void setQtDeleted(Boolean qtDeleted) {
        this.qtDeleted = qtDeleted;
    }

    public Date getQtCreated() {
        return qtCreated;
    }

    public void setQtCreated(Date qtCreated) {
        this.qtCreated = qtCreated;
    }

    public Date getQtChanged() {
        return qtChanged;
    }

    public void setQtChanged(Date qtChanged) {
        this.qtChanged = qtChanged;
    }

    @XmlTransient
    public Collection<QuesList> getQuesListCollection() {
        return quesListCollection;
    }

    public void setQuesListCollection(Collection<QuesList> quesListCollection) {
        this.quesListCollection = quesListCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qtId != null ? qtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesType)) {
            return false;
        }
        QuesType other = (QuesType) object;
        if ((this.qtId == null && other.qtId != null) || (this.qtId != null && !this.qtId.equals(other.qtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesType[ qtId=" + qtId + " ]";
    }
    
}
