/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Docentete;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SERGE
 */
@Local
public interface DocenteteFacadeLocal {

    void create(Docentete docentete);

    void edit(Docentete docentete);

    void remove(Docentete docentete);

    Docentete find(Object id);

    List<Docentete> findAll();

    List<Docentete> findRange(int[] range);

    int count();
    
}
