/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Collaborateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SERGE
 */
@Local
public interface CollaborateurFacadeLocal {

    void create(Collaborateur collaborateur);

    void edit(Collaborateur collaborateur);

    void remove(Collaborateur collaborateur);

    Collaborateur find(Object id);

    List<Collaborateur> findAll();

    List<Collaborateur> findRange(int[] range);

    int count();
    
}
