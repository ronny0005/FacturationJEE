/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.ITsolution.gescom.JPA;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SERGE
 */
@Entity
@Table(name = "DOCENTETE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docentete.findAll", query = "SELECT d FROM Docentete d"),
    @NamedQuery(name = "Docentete.findByDeRef", query = "SELECT d FROM Docentete d WHERE d.deRef = :deRef"),
    @NamedQuery(name = "Docentete.findByDEdomaine", query = "SELECT d FROM Docentete d WHERE d.dEdomaine = :dEdomaine"),
    @NamedQuery(name = "Docentete.findByDEtype", query = "SELECT d FROM Docentete d WHERE d.dEtype = :dEtype"),
    @NamedQuery(name = "Docentete.findByDEdate", query = "SELECT d FROM Docentete d WHERE d.dEdate = :dEdate"),
    @NamedQuery(name = "Docentete.findByDEcategorieComptable", query = "SELECT d FROM Docentete d WHERE d.dEcategorieComptable = :dEcategorieComptable"),
    @NamedQuery(name = "Docentete.findByDEmontantDoc", query = "SELECT d FROM Docentete d WHERE d.dEmontantDoc = :dEmontantDoc"),
    @NamedQuery(name = "Docentete.findByUtilisateur", query = "SELECT d FROM Docentete d WHERE d.utilisateur = :utilisateur"),
    @NamedQuery(name = "Docentete.findByVue", query = "SELECT d FROM Docentete d WHERE d.vue = :vue")})
public class Docentete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DE_REF")
    private String deRef;
    @Column(name = "DE_domaine")
    private Integer dEdomaine;
    @Column(name = "DE_type")
    private Integer dEtype;
    @Column(name = "DE_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dEdate;
    @Size(max = 50)
    @Column(name = "DE_categorieComptable")
    private String dEcategorieComptable;
    @Column(name = "DE_montant_Doc")
    private Integer dEmontantDoc;
    @Size(max = 50)
    @Column(name = "utilisateur")
    private String utilisateur;
    @Column(name = "vue")
    private Integer vue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deRef", fetch = FetchType.EAGER)
    private List<Docligne> docligneList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deRef", fetch = FetchType.EAGER)
    private List<Reglement> reglementList;
    @JoinColumn(name = "CJ_ID", referencedColumnName = "CJ_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Codejournal cjId;
    @JoinColumn(name = "CO_ID", referencedColumnName = "CO_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Collaborateur coId;
    @JoinColumn(name = "CG_ID", referencedColumnName = "CG_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Comptgeneral cgId;
    @JoinColumn(name = "DP_ID", referencedColumnName = "DP_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Depot dpId;
    @JoinColumn(name = "TR_ID", referencedColumnName = "TR_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tiers trId;

    public Docentete() {
    }

    public Docentete(String deRef) {
        this.deRef = deRef;
    }

    public String getDeRef() {
        return deRef;
    }

    public void setDeRef(String deRef) {
        this.deRef = deRef;
    }

    public Integer getDEdomaine() {
        return dEdomaine;
    }

    public void setDEdomaine(Integer dEdomaine) {
        this.dEdomaine = dEdomaine;
    }

    public Integer getDEtype() {
        return dEtype;
    }

    public void setDEtype(Integer dEtype) {
        this.dEtype = dEtype;
    }

    public Date getDEdate() {
        return dEdate;
    }

    public void setDEdate(Date dEdate) {
        this.dEdate = dEdate;
    }

    public String getDEcategorieComptable() {
        return dEcategorieComptable;
    }

    public void setDEcategorieComptable(String dEcategorieComptable) {
        this.dEcategorieComptable = dEcategorieComptable;
    }

    public Integer getDEmontantDoc() {
        return dEmontantDoc;
    }

    public void setDEmontantDoc(Integer dEmontantDoc) {
        this.dEmontantDoc = dEmontantDoc;
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
    public List<Docligne> getDocligneList() {
        return docligneList;
    }

    public void setDocligneList(List<Docligne> docligneList) {
        this.docligneList = docligneList;
    }

    @XmlTransient
    public List<Reglement> getReglementList() {
        return reglementList;
    }

    public void setReglementList(List<Reglement> reglementList) {
        this.reglementList = reglementList;
    }

    public Codejournal getCjId() {
        return cjId;
    }

    public void setCjId(Codejournal cjId) {
        this.cjId = cjId;
    }

    public Collaborateur getCoId() {
        return coId;
    }

    public void setCoId(Collaborateur coId) {
        this.coId = coId;
    }

    public Comptgeneral getCgId() {
        return cgId;
    }

    public void setCgId(Comptgeneral cgId) {
        this.cgId = cgId;
    }

    public Depot getDpId() {
        return dpId;
    }

    public void setDpId(Depot dpId) {
        this.dpId = dpId;
    }

    public Tiers getTrId() {
        return trId;
    }

    public void setTrId(Tiers trId) {
        this.trId = trId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deRef != null ? deRef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docentete)) {
            return false;
        }
        Docentete other = (Docentete) object;
        if ((this.deRef == null && other.deRef != null) || (this.deRef != null && !this.deRef.equals(other.deRef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Com.ITsolution.JPA.Docentete[ deRef=" + deRef + " ]";
    }
    
}
