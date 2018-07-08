/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.ihr.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.ism.ihr.entities.ScolaNiveau;

/**
 *
 * @author r.hendrick
 */
@Stateless
public class ScolaNiveauFacade extends AbstractFacade<ScolaNiveau> {

    @PersistenceContext(unitName = "org.ism_ihr_war_1807.04PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScolaNiveauFacade() {
        super(ScolaNiveau.class);
    }
    
}
