/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.ism.ihr.entities.Pays;

/**
 *
 * @author r.hendrick
 */
@Stateless
public class PaysFacade extends AbstractFacade<Pays> {

    @PersistenceContext(unitName = "org.ism_ihr_war_1807.04PU")
    private EntityManager em;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    private final String SELECTALLBYLASTCHANGED = "Pays.selectAllByLastChange";     // query = "SELECT n FROM NonConformiteFrequency n ORDER BY n.ncfChanged DESC"
    private final String FIND_BY_CODE = "Pays.findByPPays";        // query = "SELECT n FROM NonConformiteFrequency n WHERE n.ncfFrequency = :ncfFrequency"
    private final String FIND_BY_DESIGNATION = "Pays.findByPDesignation";      // query = "SELECT n FROM NonConformiteFrequency n WHERE n.ncfDesignation = :ncfDesignation
    
    
    

    public PaysFacade() {
        super(Pays.class);
    }

 public List<Pays> findAllByLastChanged() {
        em.flush();
        Query q = em.createNamedQuery(SELECTALLBYLASTCHANGED);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        int count = q.getResultList().size();
        if (count > 0) {
            return q.getResultList();
        }
        return null;
    }

    public List<Pays> findByCode(String code) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_CODE).setParameter("ncfFrequency", code);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        int count = q.getResultList().size();
        if (count > 0) {
            return q.getResultList();
        }
        return null;
    }

    public List<Pays> findByDesignation(String designation) {
        em.flush();
        Query q = em.createNamedQuery(FIND_BY_DESIGNATION).setParameter("ncfDesignation", designation);
        q.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        int count = q.getResultList().size();
        if (count > 0) {
            return q.getResultList();
        }
        return null;
    }

    
}
