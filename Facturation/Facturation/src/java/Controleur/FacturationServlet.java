/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.ArticleServeur;
import Modele.Facture;
import Modele.Parametre;
import fonction.outils;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class FacturationServlet extends HttpServlet {

    ArrayList<String> lv;
    String client;
    String designation;
    String qte;

    ArrayList<ArticleServeur> liste_article;
    ArrayList<String> lstr;
    ArrayList<String> lart;

    Boolean modif = false;
    int position_modif = 0;
    outils ou;
    String total;
    Integer id_facture;
    ArrayList<String> lclient = new ArrayList<String>();
    Parametre parametre;
    Facture facture;

    public void verouille(){
        if(!facture.getNouveau()) {
//            client.setEnabled(false);
//            designation.setEnabled(false);
//            qte.setEnabled(false);
//            caisse.setEnabled(false);
//            date.setEnabled(false);
        }
    }

    public void ajoutListe() {
        for (int i = 0; i < facture.getListe_article().size(); i++) {
            DecimalFormat decim = new DecimalFormat("#.##");
            double px_achat = (facture.getListe_article().get(i).getAr_prixven() * facture.getListe_article().get(i).getQte_vendue());
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("First Line", facture.getListe_article().get(i).getAr_design());
            datum.put("Second Line", "Qte : " + ((int) facture.getListe_article().get(i).getQte_vendue())
                    + " Prix de vente : " + decim.format(px_achat));
        }
        
    }

    public void initialise() {
        if (!facture.getNouveau());
            //client.setText(facture.getId_client().getIntitule());
    }

    public void clear() {
        //qte.setText("");
        //designation.setText("");
    }

    public String calculPrix() {
        double total_tva = 0, total_precompte = 0, total_marge = 0, total_ht = 0, total_ttc;
        for (int i = 0; i < facture.getListe_article().size(); i++) {
            ArticleServeur article = facture.getListe_article().get(i);
            double prix = Math.round(article.getAr_prixven() * article.getQte_vendue());
            total_tva += Math.round(prix * article.getTaxe1() / 100);
            total_precompte += Math.round(prix * article.getTaxe2() / 100);
            total_marge += Math.round(article.getQte_vendue() * article.getTaxe3());
            total_ht += prix;
        }

        DecimalFormat decim = new DecimalFormat("#");
        total_ttc = total_ht + total_tva + total_precompte + total_marge;
        return "Total TTC : " + decim.format(total_ttc);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Facturation.jsp").forward( request, response );
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
