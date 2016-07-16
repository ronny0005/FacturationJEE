package Modele;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * @author www.objis.com
 */

@ManagedBean(name="articlebean")
@SessionScoped
public class Article {

	@ManagedProperty(value="DUPOND")
	private String nom;
	
	@ManagedProperty(value="Michel")
	private String prenom;
	
	private String datedenaissance;
	
	@ManagedProperty(value="homme")
	private String genre;
	
	@ManagedProperty(value="participant@objis.com")
	private String email;
	
	private String niveaudeservice;
	
	// Constructeur
	
	public Article() {
		super();
	}
	
	// getters et setters
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDatedenaissance() {
		return datedenaissance;
	}
	public void setDatedenaissance(String datedenaissance) {
		this.datedenaissance = datedenaissance;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNiveaudeservice() {
		return niveaudeservice;
	}
	public void setNiveaudeservice(String niveaudeservice) {
		this.niveaudeservice = niveaudeservice;
	}

	
}
