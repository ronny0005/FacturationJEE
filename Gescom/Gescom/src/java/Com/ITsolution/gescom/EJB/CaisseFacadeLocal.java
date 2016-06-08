/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Caisse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SERGE
 */
@Local
public interface CaisseFacadeLocal {

    void create(Caisse caisse);

    void edit(Caisse caisse);

    void remove(Caisse caisse);

    Caisse find(Object id);

    List<Caisse> findAll();

    List<Caisse> findRange(int[] range);

    int count();
    
}
