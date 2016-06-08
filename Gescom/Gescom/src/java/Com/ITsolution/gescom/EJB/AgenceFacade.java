/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Agence;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SERGE
 */
@Stateless
public class AgenceFacade extends AbstractFacade<Agence> implements AgenceFacadeLocal {

    @PersistenceContext(unitName = "GescomPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgenceFacade() {
        super(Agence.class);
    }
   public List<Agence> listeAgen(){
        return this.em.createNamedQuery("Agence.findByVue", Agence.class).getResultList();
    }
    public void deleteAgence( int id){
         em.createNativeQuery("Agence.agenceupdate ",Agence.class );
    }
  
}
