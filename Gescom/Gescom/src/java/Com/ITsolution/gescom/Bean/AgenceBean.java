/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.Bean;

import Com.ITsolution.gescom.EJB.AgenceFacadeLocal;
import Com.ITsolution.gescom.JPA.Agence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author SERGE
 */
 @ManagedBean
 @RequestScoped
public class AgenceBean  implements Serializable {

    @EJB
    private AgenceFacadeLocal agenceFacade;
    
    public Agence agence;
    public int paramAgence;

    public int getParamAgence() {
        return paramAgence;
    }

    public void setParamAgence(int paramAgence) {
        this.paramAgence = paramAgence;
    }
    
    public AgenceBean(){
        agence = new Agence();
    }

     

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
     
    public List<SelectItem> getMaList() {
             List<Agence> agences= agenceFacade.findAll();
             List<SelectItem> items=new ArrayList<>();
             SelectItem item;
             for (Agence a: agences ){
                 item = new SelectItem(a, a.getAGnom());
                     items.add(item);
                     
                     
                     }
             return    items; 
             
               
        
	}
    public List<Agence> listeAgence(){
        return agenceFacade.listeAgen();
    }
     
    public void enregistrerAgence(){
        agence.setAgId(0);
        agence.setVue(1);
        agence.setAGdatecreation( new Date());
        agenceFacade.create(agence);
        this.getAgence();
        FacesMessage message = new FacesMessage( "L'agence a été crée avec succès !" );
        FacesContext.getCurrentInstance().addMessage( null, message);
    }
    public void modifierAgence(){
        agence.setAgId(paramAgence);
        agenceFacade.edit(agence);
        this.getAgence();
        FacesMessage message = new FacesMessage( "modification éffectuée!" );
        FacesContext.getCurrentInstance().addMessage( null, message);
    }
    public void supprimerAgence(){
        
        agenceFacade.remove(agence);
        this.getAgence();
        FacesMessage message = new FacesMessage( "suppression éffectuée!" );
        FacesContext.getCurrentInstance().addMessage( null, message);
    }
    
    
    public Agence  param_modif_agence(){
      agence= (Agence) agenceFacade.find(paramAgence);
      return agence;
    }
    
}
