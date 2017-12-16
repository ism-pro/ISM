/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.sessions.smq;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.ism.entities.admin.Company;
import org.ism.entities.smq.DocExplorer;
import org.ism.entities.smq.DocType;
import org.ism.entities.smq.Processus;
import org.ism.sessions.AbstractFacade;

/**
 *
 * @author r.hendrick
 */
@Stateless
public class DocExplorerFacade extends AbstractFacade<DocExplorer> {

    @PersistenceContext(unitName = "ISM_PU")
    private EntityManager em;

    private final String SELECTALLBYLASTCHANGED = "DocExplorer.selectAllByLastChange";  // query = "SELECT d FROM DocExplorer d ORDER BY d.dcChanged DESC"
    private final String FIND_BY_CODE = "DocExplorer.findByDcVersion";             // query = "SELECT d FROM DocExplorer d WHERE WHERE d.dcVersion = :dcVersion"
    private final String FIND_BY_DESIGNATION = "DocExplorer.findByDcDesignation";    // query = "SELECT d FROM DocExplorer d WHERE d.dcDesignation = :dcDesignation"
    private final String FIND_BY_PROCESSUS = "DocExplorer.findByProcessus";
    private final String FIND_BY_PROCESSUS_AND_TYPE = "DocExplorer.findByProcessusAndType";
    private final String FIND_BY_CODE_OF_COMPANY = "DocExplorer.findByDcVersionOfCompany";
    private final String FIND_BY_DESIGNATION_OF_COMPANY = "DocExplorer.findByDcDesignationOfCompany";
    private final String FIND_BY_LINK = "DocExplorer.findByDcLink";
    private final String FIND_BY_LINK_OF_COMPANY = "DocExplorer.findByDcLinkOfCompany";

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocExplorerFacade() {
        super(DocExplorer.class);
    }

    public List<DocExplorer> findAllByLastChanged() {
        em.flush();
        Query q = em.createNamedQuery(SELECTALLBYLASTCHANGED);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        int count = q.getResultList().size();
        if (count > 0) {
            return q.getResultList();
        }
        return null;
    }

    public List<DocExplorer> findByCode(String code) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_CODE).setParameter("dcVersion", code);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        int count = q.getResultList().size();
        if (count > 0) {
            return q.getResultList();
        }
        return null;
    }

    public List<DocExplorer> findByDesignation(String designation) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_DESIGNATION).setParameter("dcDesignation", designation);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        int count = q.getResultList().size();
        if (count > 0) {
            return q.getResultList();
        }
        return null;
    }

    public List<DocExplorer> findByProcessus(Processus processus) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_PROCESSUS).setParameter("dcProcessus", processus);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        int count = q.getResultList().size();
        if (count > 0) {
            return q.getResultList();
        }
        return null;
    }

    public List<DocExplorer> findByProcessusAndType(Processus processus, DocType docType) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_PROCESSUS_AND_TYPE)
                .setParameter("dcProcessus", processus)
                .setParameter("dcType", docType);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        int count = q.getResultList().size();
        if (count > 0) {
            return q.getResultList();
        }
        return null;
    }

    public List<DocExplorer> findByCode(String code, Company company) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_CODE_OF_COMPANY)
                .setParameter("dcVersion", code)
                .setParameter("dcCompany", company);
        return q.getResultList();
    }

    public List<DocExplorer> findByDesignation(String designation, Company company) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_DESIGNATION_OF_COMPANY)
                .setParameter("dcDesignation", designation)
                .setParameter("dcCompany", company);
        return q.getResultList();
    }

    public List<DocExplorer> findByLink(String link) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_LINK).setParameter("dcLink", link);
        return q.getResultList();
    }

    public List<DocExplorer> findByLink(String link, Company company) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_LINK_OF_COMPANY)
                .setParameter("dcLink", link)
                .setParameter("dcCompany", company);
        return q.getResultList();
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Criteria
    //
    //
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Filtring allow to append condition to a predicate depending on field.
     * Main purpose of filtring is to implement entities conditions.
     *
     * <br> DocExplorerField_Id=N°
     * <br> DocExplorerField_Name=Designation
     * <br> DocExplorerField_Version=Numéro
     * <br> DocExplorerField_Commentaire=Commentaire
     * <br> DocExplorerField_Link=Lien
     * <br> DocExplorerField_DateApprouved=Date approbation
     * <br> DocExplorerField_Timestamp=Modif.
     * <br> DocExplorerField_DocType=Type
     * <br> DocExplorerField_Processus=Processus
     * <br> DocExplorerField_Activated=Activé
     * <br> DocExplorerField_Created=Création
     * <br> DocExplorerField_Company=Société
     *
     * @param cb is a criteria builder allowing to concat in this case
     * @param rt is the main data
     * @param filter couple of field filter and value of filter
     * @return a predicate of filter
     */
    @Override
    public Predicate filtring(CriteriaBuilder cb, Root<DocExplorer> rt, Map.Entry<String, Object> filter) {
        Expression<String> expr;
        final String field = filter.getKey();
        final String value = filter.getValue().toString();
        Predicate p = null;
        switch (field) {
            case "dcProcessus":
                p = likeFieldComposite(cb, rt, field, "pProcessus", "pDesignation", "pId", value);
                break;
            case "dcType":
                p = likeFieldComposite(cb, rt, field, "dctType", "dctDesignation", "dctId", value);
                break;
            case "dcCompany":
                p = likeFieldComposite(cb, rt, field, "cCompany", "cDesignation", "cId", value);
                break;
            default:
                expr = rt.get(field);
                if (filter.getValue() instanceof Boolean) {
                    p = cb.equal(expr, value);
                } else if (value.contains("start") && value.contains("end")) {
                    p = betweenFieldDate(cb, rt, field, value);
                } else {
                    p = cb.like(expr, value);
                }
                break;
        }
        return p;
    }
}
