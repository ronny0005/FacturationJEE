/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.Bean;

import Com.ITsolution.gescom.EJB.CollaborateurFacadeLocal;
import Com.ITsolution.gescom.JPA.Collaborateur;
import java.util.logging.Logger;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author SERGE
 */
@ManagedBean
@RequestScoped
public class CollaborateurBean  implements Serializable{

    @EJB
    private CollaborateurFacadeLocal collaborateurFacade;
    public Collaborateur collaborateur;
    
    public CollaborateurBean(){
    collaborateur =new Collaborateur();
        
    }

    public CollaborateurFacadeLocal getCollaborateurFacade() {
        return collaborateurFacade;
    }

    public void setCollaborateurFacade(CollaborateurFacadeLocal collaborateurFacade) {
        this.collaborateurFacade = collaborateurFacade;
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(Collaborateur collaborateur) {
        this.collaborateur = collaborateur;
    }
    
    public void enregistrer() {
       
        collaborateur.setCoId(0);   
        Logger log= Logger.getGlobal();
        log.info(collaborateur.toString());
      //System.out.println(collaborateur);
        collaborateurFacade.create(collaborateur);
        this.getCollaborateur();

    } 
   
}
