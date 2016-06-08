/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.EJB;

import Com.ITsolution.gescom.JPA.Codejournal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SERGE
 */
@Local
public interface CodejournalFacadeLocal {

    void create(Codejournal codejournal);

    void edit(Codejournal codejournal);

    void remove(Codejournal codejournal);

    Codejournal find(Object id);

    List<Codejournal> findAll();

    List<Codejournal> findRange(int[] range);

    int count();
    
}
