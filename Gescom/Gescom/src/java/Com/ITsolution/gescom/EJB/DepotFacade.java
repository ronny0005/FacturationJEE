/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Depot;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SERGE
 */
@Stateless
public class DepotFacade extends AbstractFacade<Depot> implements DepotFacadeLocal {

    @PersistenceContext(unitName = "GescomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepotFacade() {
        super(Depot.class);
    }
    
}
