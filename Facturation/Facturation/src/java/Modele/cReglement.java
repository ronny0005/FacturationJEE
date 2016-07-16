package Modele;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Closeable;
import java.io.Serializable;

/**
 * Created by T.Ron on 18/03/2016.
 */
@ManagedBean(name="creglementbean")
@SessionScoped
public class cReglement {

    private int cbMarq;

    public cReglement(int cbMarq) {
        this.cbMarq = cbMarq;
    }

    public int getCbMarq() {
        return cbMarq;
    }

    public void setCbMarq(int cbMarq) {
        this.cbMarq = cbMarq;
    }
}
