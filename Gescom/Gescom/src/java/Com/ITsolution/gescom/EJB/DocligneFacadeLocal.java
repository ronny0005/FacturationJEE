/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Docligne;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SERGE
 */
@Local
public interface DocligneFacadeLocal {

    void create(Docligne docligne);

    void edit(Docligne docligne);

    void remove(Docligne docligne);

    Docligne find(Object id);

    List<Docligne> findAll();

    List<Docligne> findRange(int[] range);

    int count();
    
}
