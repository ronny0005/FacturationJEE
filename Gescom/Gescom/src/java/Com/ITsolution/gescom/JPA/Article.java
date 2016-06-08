/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.JPA;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SERGE
 */
@Entity
@Table(name = "ARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByArRef", query = "SELECT a FROM Article a WHERE a.arRef = :arRef"),
    @NamedQuery(name = "Article.findByARcodeBarre", query = "SELECT a FROM Article a WHERE a.aRcodeBarre = :aRcodeBarre"),
    @NamedQuery(name = "Article.findByARdesignation", query = "SELECT a FROM Article a WHERE a.aRdesignation = :aRdesignation"),
    @NamedQuery(name = "Article.findByARprixAchat", query = "SELECT a FROM Article a WHERE a.aRprixAchat = :aRprixAchat"),
    @NamedQuery(name = "Article.findByARprixVente", query = "SELECT a FROM Article a WHERE a.aRprixVente = :aRprixVente"),
    @NamedQuery(name = "Article.findByARstockAlert", query = "SELECT a FROM Article a WHERE a.aRstockAlert = :aRstockAlert"),
    @NamedQuery(name = "Article.findByUtilisateur", query = "SELECT a FROM Article a WHERE a.utilisateur = :utilisateur"),
    @NamedQuery(name = "Article.findByVue", query = "SELECT a FROM Article a WHERE a.vue = :vue")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "AR_REF")
    private String arRef;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AR_codeBarre")
    private String aRcodeBarre;
    @Size(max = 50)
    @Column(name = "AR_designation")
    private String aRdesignation;
    @Column(name = "AR_prixAchat")
    private Integer aRprixAchat;
    @Column(name = "AR_prixVente")
    private Integer aRprixVente;
    @Column(name = "AR_stockAlert")
    private Integer aRstockAlert;
    @Size(max = 50)
    @Column(name = "utilisateur")
    private String utilisateur;
    @Column(name = "vue")
    private Integer vue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arRef", fetch = FetchType.EAGER)
    private List<Artstock> artstockList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arRef", fetch = FetchType.EAGER)
    private List<Docligne> docligneList;
    @JoinColumn(name = "FA_ID", referencedColumnName = "FA_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Famille faId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arRef", fetch = FetchType.EAGER)
    private List<TierArticle> tierArticleList;

    public Article() {
    }

    public Article(String arRef) {
        this.arRef = arRef;
    }

    public Article(String arRef, String aRcodeBarre) {
        this.arRef = arRef;
        this.aRcodeBarre = aRcodeBarre;
    }

    public String getArRef() {
        return arRef;
    }

    public void setArRef(String arRef) {
        this.arRef = arRef;
    }

    public String getARcodeBarre() {
        return aRcodeBarre;
    }

    public void setARcodeBarre(String aRcodeBarre) {
        this.aRcodeBarre = aRcodeBarre;
    }

    public String getARdesignation() {
        return aRdesignation;
    }

    public void setARdesignation(String aRdesignation) {
        this.aRdesignation = aRdesignation;
    }

    public Integer getARprixAchat() {
        return aRprixAchat;
    }

    public void setARprixAchat(Integer aRprixAchat) {
        this.aRprixAchat = aRprixAchat;
    }

    public Integer getARprixVente() {
        return aRprixVente;
    }

    public void setARprixVente(Integer aRprixVente) {
        this.aRprixVente = aRprixVente;
    }

    public Integer getARstockAlert() {
        return aRstockAlert;
    }

    public void setARstockAlert(Integer aRstockAlert) {
        this.aRstockAlert = aRstockAlert;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getVue() {
        return vue;
    }

    public void setVue(Integer vue) {
        this.vue = vue;
    }

    @XmlTransient
    public List<Artstock> getArtstockList() {
        return artstockList;
    }

    public void setArtstockList(List<Artstock> artstockList) {
        this.artstockList = artstockList;
    }

    @XmlTransient
    public List<Docligne> getDocligneList() {
        return docligneList;
    }

    public void setDocligneList(List<Docligne> docligneList) {
        this.docligneList = docligneList;
    }

    public Famille getFaId() {
        return faId;
    }

    public void setFaId(Famille faId) {
        this.faId = faId;
    }

    @XmlTransient
    public List<TierArticle> getTierArticleList() {
        return tierArticleList;
    }

    public void setTierArticleList(List<TierArticle> tierArticleList) {
        this.tierArticleList = tierArticleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arRef != null ? arRef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.arRef == null && other.arRef != null) || (this.arRef != null && !this.arRef.equals(other.arRef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Com.ITsolution.JPA.Article[ arRef=" + arRef + " ]";
    }
    
}
