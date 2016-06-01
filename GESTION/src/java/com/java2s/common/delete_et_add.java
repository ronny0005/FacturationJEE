package com.java2s.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name="book")
@SessionScoped
public class delete_et_add implements Serializable{
  private static final long serialVersionUID = 1L;
  String bookNo;
  String productName;
  BigDecimal price;
  int qty;
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

  private static final ArrayList<Book> bookList =  
    new ArrayList<>(Arrays.asList(
                                new Book("1", "CSS", new BigDecimal("123.12"), 1),
                                new Book("2", "HTML", new BigDecimal("321.12"), 2),
                                new Book("3", "SQL", new BigDecimal("12333.33"), 8),
                                new Book("4", "Javascript", new BigDecimal("1233.33"), 3),
                                new Book("5", "Web", new BigDecimal("123.22"), 10)
  ));
   
  public ArrayList<Book> getBookList() {
    return bookList;
  }
  public String addAction() {
    Book book = new Book(this.bookNo, this.productName, 
      this.price, this.qty);
    bookList.add(book);
    return null;
  }
  public String deleteAction(Book book) {
    bookList.remove(book);
    return null;
  }
  public String saveAction() {
    //get all existing value but set "editable" to false 
    for (Book book : bookList){
      book.setEditable(false);
    }
    //return to current page
    return null;
  }
  public String editAction(Book book) {
    book.setEditable(true);
    return null;
  }

    public static class Book{
      
      String ref;
      String codebarre;
      BigDecimal price;
      int qty;
      boolean editable;

        public boolean isEditable() {
            return editable;
        }

        public void setEditable(boolean editable) {
            this.editable = editable;
        }
    
      public Book(String bookNo, String productName, 
          BigDecimal price, int qty) {
        this.ref = bookNo;
        this.codebarre = productName;
        this.price = price;
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
    }

}