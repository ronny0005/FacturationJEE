/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Caisse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SERGE
 */
@Stateless
public class CaisseFacade extends AbstractFacade<Caisse> implements CaisseFacadeLocal {

    @PersistenceContext(unitName = "GescomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaisseFacade() {
        super(Caisse.class);
    }
    
}
