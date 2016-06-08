/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Artstock;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SERGE
 */
@Local
public interface ArtstockFacadeLocal {

    void create(Artstock artstock);

    void edit(Artstock artstock);

    void remove(Artstock artstock);

    Artstock find(Object id);

    List<Artstock> findAll();

    List<Artstock> findRange(int[] range);

    int count();
    
}
