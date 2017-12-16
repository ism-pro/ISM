/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.sessions.app;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.ism.entities.app.IsmNcrstate;
import org.ism.sessions.AbstractFacade;

/**
 *
 * @author r.hendrick
 */
@Stateless
public class IsmNcrstateFacade extends AbstractFacade<IsmNcrstate> {

    @PersistenceContext(unitName = "ISM_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IsmNcrstateFacade() {
        super(IsmNcrstate.class);
    }

    public IsmNcrstate findState(String state) {
        em.flush();
        Query q = em.createNamedQuery("IsmNcrstate.findByIstate")
                .setParameter("istate", state);
        List l = q.getResultList();
        if(l==null) return null;
        if(l.isEmpty()) return null;
        return (IsmNcrstate) l.get(0);
    }

}
