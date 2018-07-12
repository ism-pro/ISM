/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.entities.contact;

import org.ism.ihr.entities.admin.CivilState;
import org.ism.ihr.entities.map.Country;
import org.ism.ihr.entities.map.Town;
import org.ism.ihr.entities.map.Cities;
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
import org.ism.ihr.entities.school.SchDegree;
import org.ism.ihr.entities.school.SchQualification;
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
    @NamedQuery(name = "Candidate.findAll", query = "SELECT c FROM Candidate c")
    , @NamedQuery(name = "Candidate.findByCId", query = "SELECT c FROM Candidate c WHERE c.cId = :cId")
    , @NamedQuery(name = "Candidate.findByCLastname", query = "SELECT c FROM Candidate c WHERE c.cLastname = :cLastname")
    , @NamedQuery(name = "Candidate.findByCFirstname", query = "SELECT c FROM Candidate c WHERE c.cFirstname = :cFirstname")
    , @NamedQuery(name = "Candidate.findByCPostname", query = "SELECT c FROM Candidate c WHERE c.cPostname = :cPostname")
    , @NamedQuery(name = "Candidate.findByCGenre", query = "SELECT c FROM Candidate c WHERE c.cGenre = :cGenre")
    , @NamedQuery(name = "Candidate.findByCBirth", query = "SELECT c FROM Candidate c WHERE c.cBirth = :cBirth")
    , @NamedQuery(name = "Candidate.findByCaddrRoad", query = "SELECT c FROM Candidate c WHERE c.caddrRoad = :caddrRoad")
    , @NamedQuery(name = "Candidate.findByCaddrNumber", query = "SELECT c FROM Candidate c WHERE c.caddrNumber = :caddrNumber")
    , @NamedQuery(name = "Candidate.findByCPhone1", query = "SELECT c FROM Candidate c WHERE c.cPhone1 = :cPhone1")
    , @NamedQuery(name = "Candidate.findByCPhone2", query = "SELECT c FROM Candidate c WHERE c.cPhone2 = :cPhone2")
    , @NamedQuery(name = "Candidate.findByCPhone3", query = "SELECT c FROM Candidate c WHERE c.cPhone3 = :cPhone3")
    , @NamedQuery(name = "Candidate.findByCMail1", query = "SELECT c FROM Candidate c WHERE c.cMail1 = :cMail1")
    , @NamedQuery(name = "Candidate.findByCMail2", query = "SELECT c FROM Candidate c WHERE c.cMail2 = :cMail2")
    , @NamedQuery(name = "Candidate.findByCMail3", query = "SELECT c FROM Candidate c WHERE c.cMail3 = :cMail3")
    , @NamedQuery(name = "Candidate.findByCfirstJob", query = "SELECT c FROM Candidate c WHERE c.cfirstJob = :cfirstJob")
    , @NamedQuery(name = "Candidate.findByCCv", query = "SELECT c FROM Candidate c WHERE c.cCv = :cCv")
    , @NamedQuery(name = "Candidate.findByCmotivLetter", query = "SELECT c FROM Candidate c WHERE c.cmotivLetter = :cmotivLetter")
    , @NamedQuery(name = "Candidate.findByCDeleted", query = "SELECT c FROM Candidate c WHERE c.cDeleted = :cDeleted")
    , @NamedQuery(name = "Candidate.findByCCreated", query = "SELECT c FROM Candidate c WHERE c.cCreated = :cCreated")
    , @NamedQuery(name = "Candidate.findByCChanged", query = "SELECT c FROM Candidate c WHERE c.cChanged = :cChanged")})
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "c_id", nullable = false)
    private Integer cId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "c_lastname", nullable = false, length = 255)
    private String cLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "c_firstname", nullable = false, length = 255)
    private String cFirstname;
    @Size(max = 255)
    @Column(name = "c_postname", length = 255)
    private String cPostname;
    @Column(name = "c_genre")
    private Boolean cGenre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_birth", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cBirth;
    @Size(max = 255)
    @Column(name = "c_addrRoad", length = 255)
    private String caddrRoad;
    @Size(max = 45)
    @Column(name = "c_addrNumber", length = 45)
    private String caddrNumber;
    @Size(max = 45)
    @Column(name = "c_phone1", length = 45)
    private String cPhone1;
    @Size(max = 45)
    @Column(name = "c_phone2", length = 45)
    private String cPhone2;
    @Size(max = 45)
    @Column(name = "c_phone3", length = 45)
    private String cPhone3;
    @Size(max = 128)
    @Column(name = "c_mail1", length = 128)
    private String cMail1;
    @Size(max = 128)
    @Column(name = "c_mail2", length = 128)
    private String cMail2;
    @Size(max = 128)
    @Column(name = "c_mail3", length = 128)
    private String cMail3;
    @Column(name = "c_firstJob")
    private Boolean cfirstJob;
    @Lob
    @Column(name = "c_photo")
    private byte[] cPhoto;
    @Size(max = 512)
    @Column(name = "c_cv", length = 512)
    private String cCv;
    @Size(max = 512)
    @Column(name = "c_motivLetter", length = 512)
    private String cmotivLetter;
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
    @JoinColumn(name = "c_city", referencedColumnName = "c_city", nullable = false)
    @ManyToOne(optional = false)
    private Cities cCity;
    @JoinColumn(name = "c_civilState", referencedColumnName = "cs_state", nullable = false)
    @ManyToOne(optional = false)
    private CivilState ccivilState;
    @JoinColumn(name = "c_country", referencedColumnName = "c_country", nullable = false)
    @ManyToOne(optional = false)
    private Country cCountry;
    @JoinColumn(name = "c_degree", referencedColumnName = "sd_degree", nullable = false)
    @ManyToOne(optional = false)
    private SchDegree cDegree;
    @JoinColumn(name = "c_qualification", referencedColumnName = "sq_qualification")
    @ManyToOne
    private SchQualification cQualification;
    @JoinColumn(name = "c_school", referencedColumnName = "s_school", nullable = false)
    @ManyToOne(optional = false)
    private School cSchool;
    @JoinColumn(name = "c_town", referencedColumnName = "t_town", nullable = false)
    @ManyToOne(optional = false)
    private Town cTown;

    public Candidate() {
    }

    public Candidate(Integer cId) {
        this.cId = cId;
    }

    public Candidate(Integer cId, String cLastname, String cFirstname, Date cBirth, Date cCreated, Date cChanged) {
        this.cId = cId;
        this.cLastname = cLastname;
        this.cFirstname = cFirstname;
        this.cBirth = cBirth;
        this.cCreated = cCreated;
        this.cChanged = cChanged;
    }

    public Integer getCId() {
        return cId;
    }

    public void setCId(Integer cId) {
        this.cId = cId;
    }

    public String getCLastname() {
        return cLastname;
    }

    public void setCLastname(String cLastname) {
        this.cLastname = cLastname;
    }

    public String getCFirstname() {
        return cFirstname;
    }

    public void setCFirstname(String cFirstname) {
        this.cFirstname = cFirstname;
    }

    public String getCPostname() {
        return cPostname;
    }

    public void setCPostname(String cPostname) {
        this.cPostname = cPostname;
    }

    public Boolean getCGenre() {
        return cGenre;
    }

    public void setCGenre(Boolean cGenre) {
        this.cGenre = cGenre;
    }

    public Date getCBirth() {
        return cBirth;
    }

    public void setCBirth(Date cBirth) {
        this.cBirth = cBirth;
    }

    public String getCaddrRoad() {
        return caddrRoad;
    }

    public void setCaddrRoad(String caddrRoad) {
        this.caddrRoad = caddrRoad;
    }

    public String getCaddrNumber() {
        return caddrNumber;
    }

    public void setCaddrNumber(String caddrNumber) {
        this.caddrNumber = caddrNumber;
    }

    public String getCPhone1() {
        return cPhone1;
    }

    public void setCPhone1(String cPhone1) {
        this.cPhone1 = cPhone1;
    }

    public String getCPhone2() {
        return cPhone2;
    }

    public void setCPhone2(String cPhone2) {
        this.cPhone2 = cPhone2;
    }

    public String getCPhone3() {
        return cPhone3;
    }

    public void setCPhone3(String cPhone3) {
        this.cPhone3 = cPhone3;
    }

    public String getCMail1() {
        return cMail1;
    }

    public void setCMail1(String cMail1) {
        this.cMail1 = cMail1;
    }

    public String getCMail2() {
        return cMail2;
    }

    public void setCMail2(String cMail2) {
        this.cMail2 = cMail2;
    }

    public String getCMail3() {
        return cMail3;
    }

    public void setCMail3(String cMail3) {
        this.cMail3 = cMail3;
    }

    public Boolean getCfirstJob() {
        return cfirstJob;
    }

    public void setCfirstJob(Boolean cfirstJob) {
        this.cfirstJob = cfirstJob;
    }

    public byte[] getCPhoto() {
        return cPhoto;
    }

    public void setCPhoto(byte[] cPhoto) {
        this.cPhoto = cPhoto;
    }

    public String getCCv() {
        return cCv;
    }

    public void setCCv(String cCv) {
        this.cCv = cCv;
    }

    public String getCmotivLetter() {
        return cmotivLetter;
    }

    public void setCmotivLetter(String cmotivLetter) {
        this.cmotivLetter = cmotivLetter;
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

    public Cities getCCity() {
        return cCity;
    }

    public void setCCity(Cities cCity) {
        this.cCity = cCity;
    }

    public CivilState getCcivilState() {
        return ccivilState;
    }

    public void setCcivilState(CivilState ccivilState) {
        this.ccivilState = ccivilState;
    }

    public Country getCCountry() {
        return cCountry;
    }

    public void setCCountry(Country cCountry) {
        this.cCountry = cCountry;
    }

    public SchDegree getCDegree() {
        return cDegree;
    }

    public void setCDegree(SchDegree cDegree) {
        this.cDegree = cDegree;
    }

    public SchQualification getCQualification() {
        return cQualification;
    }

    public void setCQualification(SchQualification cQualification) {
        this.cQualification = cQualification;
    }

    public School getCSchool() {
        return cSchool;
    }

    public void setCSchool(School cSchool) {
        this.cSchool = cSchool;
    }

    public Town getCTown() {
        return cTown;
    }

    public void setCTown(Town cTown) {
        this.cTown = cTown;
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
        if (!(object instanceof Candidate)) {
            return false;
        }
        Candidate other = (Candidate) object;
        if ((this.cId == null && other.cId != null) || (this.cId != null && !this.cId.equals(other.cId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ism.ihr.entities.Candidate[ cId=" + cId + " ]";
    }
    
}
