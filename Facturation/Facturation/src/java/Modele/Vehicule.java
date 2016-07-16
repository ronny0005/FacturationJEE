package Modele;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Closeable;
import java.io.Serializable;

/**
 * Created by T.Ron on 18/03/2016.
 */
@ManagedBean(name="vehiculebean")
@SessionScoped
public class Vehicule implements Serializable {
    private int id;
    private String ca_num;
    private String ca_intitule;
    private int n_analytique;

    public Vehicule(String ca_num, String ca_intitule, int n_analytique) {
        this.ca_num = ca_num;
        this.ca_intitule = ca_intitule;
        this.n_analytique = n_analytique;
    }

    public String getCa_num() {
        return ca_num;
    }

    public void setCa_num(String ca_num) {
        this.ca_num = ca_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCa_intitule() {
        return ca_intitule;
    }

    public void setCa_intitule(String ca_intitule) {
        this.ca_intitule = ca_intitule;
    }

    public int getN_analytique() {
        return n_analytique;
    }

    public void setN_analytique(int n_analytique) {
        this.n_analytique = n_analytique;
    }
}
