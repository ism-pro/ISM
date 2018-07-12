/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.question;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author r.hendrick
 */
@Entity
@Table(name = "ques_response", catalog = "ihr", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"qr_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuesResponse.findAll", query = "SELECT q FROM QuesResponse q")
    , @NamedQuery(name = "QuesResponse.findByQrId", query = "SELECT q FROM QuesResponse q WHERE q.qrId = :qrId")
    , @NamedQuery(name = "QuesResponse.findByQrQuestionnaire", query = "SELECT q FROM QuesResponse q WHERE q.qrQuestionnaire = :qrQuestionnaire")
    , @NamedQuery(name = "QuesResponse.findByQrText", query = "SELECT q FROM QuesResponse q WHERE q.qrText = :qrText")
    , @NamedQuery(name = "QuesResponse.findByQrNumber", query = "SELECT q FROM QuesResponse q WHERE q.qrNumber = :qrNumber")
    , @NamedQuery(name = "QuesResponse.findByQrScore", query = "SELECT q FROM QuesResponse q WHERE q.qrScore = :qrScore")
    , @NamedQuery(name = "QuesResponse.findByQrIsdatediff", query = "SELECT q FROM QuesResponse q WHERE q.qrIsdatediff = :qrIsdatediff")
    , @NamedQuery(name = "QuesResponse.findByQrDate", query = "SELECT q FROM QuesResponse q WHERE q.qrDate = :qrDate")
    , @NamedQuery(name = "QuesResponse.findByQrDeleted", query = "SELECT q FROM QuesResponse q WHERE q.qrDeleted = :qrDeleted")
    , @NamedQuery(name = "QuesResponse.findByQrCreated", query = "SELECT q FROM QuesResponse q WHERE q.qrCreated = :qrCreated")
    , @NamedQuery(name = "QuesResponse.findByQrChanged", query = "SELECT q FROM QuesResponse q WHERE q.qrChanged = :qrChanged")})
public class QuesResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qr_id", nullable = false)
    private Integer qrId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qr_questionnaire", nullable = false)
    private int qrQuestionnaire;
    @Size(max = 512)
    @Column(name = "qr_text", length = 512)
    private String qrText;
    @Lob
    @Column(name = "qr_image")
    private byte[] qrImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qr_number", nullable = false)
    private double qrNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qr_score", nullable = false)
    private double qrScore;
    @Column(name = "qr_isdatediff")
    private Boolean qrIsdatediff;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qr_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qrDate;
    @Column(name = "qr_deleted")
    private Boolean qrDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qr_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qrCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qr_changed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date qrChanged;
    @JoinColumn(name = "qr_choice", referencedColumnName = "qc_id", nullable = false)
    @ManyToOne(optional = false)
    private QuesChoice qrChoice;
    @JoinColumn(name = "qr_list", referencedColumnName = "ql_id", nullable = false)
    @ManyToOne(optional = false)
    private QuesList qrList;

    public QuesResponse() {
    }

    public QuesResponse(Integer qrId) {
        this.qrId = qrId;
    }

    public QuesResponse(Integer qrId, int qrQuestionnaire, double qrNumber, double qrScore, Date qrDate, Date qrCreated, Date qrChanged) {
        this.qrId = qrId;
        this.qrQuestionnaire = qrQuestionnaire;
        this.qrNumber = qrNumber;
        this.qrScore = qrScore;
        this.qrDate = qrDate;
        this.qrCreated = qrCreated;
        this.qrChanged = qrChanged;
    }

    public Integer getQrId() {
        return qrId;
    }

    public void setQrId(Integer qrId) {
        this.qrId = qrId;
    }

    public int getQrQuestionnaire() {
        return qrQuestionnaire;
    }

    public void setQrQuestionnaire(int qrQuestionnaire) {
        this.qrQuestionnaire = qrQuestionnaire;
    }

    public String getQrText() {
        return qrText;
    }

    public void setQrText(String qrText) {
        this.qrText = qrText;
    }

    public byte[] getQrImage() {
        return qrImage;
    }

    public void setQrImage(byte[] qrImage) {
        this.qrImage = qrImage;
    }

    public double getQrNumber() {
        return qrNumber;
    }

    public void setQrNumber(double qrNumber) {
        this.qrNumber = qrNumber;
    }

    public double getQrScore() {
        return qrScore;
    }

    public void setQrScore(double qrScore) {
        this.qrScore = qrScore;
    }

    public Boolean getQrIsdatediff() {
        return qrIsdatediff;
    }

    public void setQrIsdatediff(Boolean qrIsdatediff) {
        this.qrIsdatediff = qrIsdatediff;
    }

    public Date getQrDate() {
        return qrDate;
    }

    public void setQrDate(Date qrDate) {
        this.qrDate = qrDate;
    }

    public Boolean getQrDeleted() {
        return qrDeleted;
    }

    public void setQrDeleted(Boolean qrDeleted) {
        this.qrDeleted = qrDeleted;
    }

    public Date getQrCreated() {
        return qrCreated;
    }

    public void setQrCreated(Date qrCreated) {
        this.qrCreated = qrCreated;
    }

    public Date getQrChanged() {
        return qrChanged;
    }

    public void setQrChanged(Date qrChanged) {
        this.qrChanged = qrChanged;
    }

    public QuesChoice getQrChoice() {
        return qrChoice;
    }

    public void setQrChoice(QuesChoice qrChoice) {
        this.qrChoice = qrChoice;
    }

    public QuesList getQrList() {
        return qrList;
    }

    public void setQrList(QuesList qrList) {
        this.qrList = qrList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qrId != null ? qrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuesResponse)) {
            return false;
        }
        QuesResponse other = (QuesResponse) object;
        if ((this.qrId == null && other.qrId != null) || (this.qrId != null && !this.qrId.equals(other.qrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.QuesResponse[ qrId=" + qrId + " ]";
    }
    
}
