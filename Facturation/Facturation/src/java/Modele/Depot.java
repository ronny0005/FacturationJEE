package Modele;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Closeable;
import java.io.Serializable;

/**
 * Created by T.Ron on 18/03/2016.
 */
@ManagedBean(name="depotbean")
@SessionScoped
public class Depot {

    private int id;
    private String nom;

    public Depot(String nom) {
        this.nom = nom;
    }

    public Depot(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Depot(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}