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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "ques_choice", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qc_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesChoice.findAll", query = "SELECT q FROM QuesChoice q")
    , @NamedQuery(name = "QuesChoice.findByQcId", query = "SELECT q FROM QuesChoice q WHERE q.qcId = :qcId")
    , @NamedQuery(name = "QuesChoice.findByQcText", query = "SELECT q FROM QuesChoice q WHERE q.qcText = :qcText")
    , @NamedQuery(name = "QuesChoice.findByQcNumber", query = "SELECT q FROM QuesChoice q WHERE q.qcNumber = :qcNumber")
    , @NamedQuery(name = "QuesChoice.findByQcCorrect", query = "SELECT q FROM QuesChoice q WHERE q.qcCorrect = :qcCorrect")
    , @NamedQuery(name = "QuesChoice.findByQcDescription", query = "SELECT q FROM QuesChoice q WHERE q.qcDescription = :qcDescription")
    , @NamedQuery(name = "QuesChoice.findByQcDeleted", query = "SELECT q FROM QuesChoice q WHERE q.qcDeleted = :qcDeleted")
    , @NamedQuery(name = "QuesChoice.findByQcCreated", query = "SELECT q FROM QuesChoice q WHERE q.qcCreated = :qcCreated")
    , @NamedQuery(name = "QuesChoice.findByQcChanged", query = "SELECT q FROM QuesChoice q WHERE q.qcChanged = :qcChanged")})
public class QuesChoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qc_id", nullable = false)
    private Integer qcId;
    @Size(max = 512)
    @Column(name = "qc_text", length = 512)
    private String qcText;
    @Lob
    @Column(name = "qc_image")
    private byte[] qcImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qc_number", nullable = false)
    private double qcNumber;
    @Column(name = "qc_correct")
    private Boolean qcCorrect;
    @Size(max = 512)
    @Column(name = "qc_description", length = 512)
    private String qcDescription;
    @Column(name = "qc_deleted")
    private Boolean qcDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qc_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qcCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qc_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qcChanged;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qrChoice")
    private Collection<QuesResponse> quesResponseCollection;
    @JoinColumn(name = "qc_list", referencedColumnName = "ql_id", nullable = false)
    @ManyToOne(optional = false)
    private QuesList qcList;

    public QuesChoice() {
    }

    public QuesChoice(Integer qcId) {
        this.qcId = qcId;
    }

    public QuesChoice(Integer qcId, double qcNumber, Date qcCreated, Date qcChanged) {
        this.qcId = qcId;
        this.qcNumber = qcNumber;
        this.qcCreated = qcCreated;
        this.qcChanged = qcChanged;
    }

    public Integer getQcId() {
        return qcId;
    }

    public void setQcId(Integer qcId) {
        this.qcId = qcId;
    }

    public String getQcText() {
        return qcText;
    }

    public void setQcText(String qcText) {
        this.qcText = qcText;
    }

    public byte[] getQcImage() {
        return qcImage;
    }

    public void setQcImage(byte[] qcImage) {
        this.qcImage = qcImage;
    }

    public double getQcNumber() {
        return qcNumber;
    }

    public void setQcNumber(double qcNumber) {
        this.qcNumber = qcNumber;
    }

    public Boolean getQcCorrect() {
        return qcCorrect;
    }

    public void setQcCorrect(Boolean qcCorrect) {
        this.qcCorrect = qcCorrect;
    }

    public String getQcDescription() {
        return qcDescription;
    }

    public void setQcDescription(String qcDescription) {
        this.qcDescription = qcDescription;
    }

    public Boolean getQcDeleted() {
        return qcDeleted;
    }

    public void setQcDeleted(Boolean qcDeleted) {
        this.qcDeleted = qcDeleted;
    }

    public Date getQcCreated() {
        return qcCreated;
    }

    public void setQcCreated(Date qcCreated) {
        this.qcCreated = qcCreated;
    }

    public Date getQcChanged() {
        return qcChanged;
    }

    public void setQcChanged(Date qcChanged) {
        this.qcChanged = qcChanged;
    }

    @XmlTransient
    public Collection<QuesResponse> getQuesResponseCollection() {
        return quesResponseCollection;
    }

    public void setQuesResponseCollection(Collection<QuesResponse> quesResponseCollection) {
        this.quesResponseCollection = quesResponseCollection;
    }

    public QuesList getQcList() {
        return qcList;
    }

    public void setQcList(QuesList qcList) {
        this.qcList = qcList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qcId != null ? qcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesChoice)) {
            return false;
        }
        QuesChoice other = (QuesChoice) object;
        if ((this.qcId == null && other.qcId != null) || (this.qcId != null && !this.qcId.equals(other.qcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesChoice[ qcId=" + qcId + " ]";
    }
    
}
