/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java2s.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="essay")
@SessionScoped
public class Essay implements Serializable{
    private static final long serialVersionUID = 1L;
    String bookNo;
    String productName;
    BigDecimal price;
    int qty;
    String ref;
    String codebarre;
    boolean editable;

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Essay(String bookNo, String productName, BigDecimal price, int qty) {
        this.bookNo = bookNo;
        this.productName = productName;
        this.price = price;
        this.qty = qty;
    }
    public  ArrayList<Essay> liste = new ArrayList<>(Arrays.asList(new Essay("1", "CSS", new BigDecimal("123"), 1),
                                new Essay("2", "HTML", new BigDecimal("321"), 2),
                                new Essay("3", "SQL", new BigDecimal("12333"), 8),
                                new Essay("4", "Javascript", new BigDecimal("1233"), 3),
                                new Essay("5", "Web", new BigDecimal("123"), 10),
                                new Essay ("55","jquery",new BigDecimal("252"),45)
  ));

    public ArrayList<Essay> getListe() {
        return liste;
    }

    public void setListe(ArrayList<Essay> liste) {
        this.liste = liste;
    }
  
    
  public String addAction() {
    Essay essay = new Essay(this.bookNo, this.productName, 
      this.price, this.qty);
    liste.add(essay);
    return null;
  }
  public String deleteAction(Essay essay) {
    liste.remove(essay);
    return null;
  }
  public String saveAction() {
    //get all existing value but set "editable" to false 
    for (Essay essay : liste){
      essay.setEditable(false);
    }
    //return to current page
    return null;
  }
  public String editAction(Essay essay) {
    essay.setEditable(true);
    return null;
  }

    public Essay() {
    }
    
}
