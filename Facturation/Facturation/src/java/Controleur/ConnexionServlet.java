/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.ArticleServeur;
import Modele.Client;
import Modele.CompteA;
import Modele.Depot;
import Modele.Facture;
import Modele.Parametre;
import Modele.QteStock;
import Modele.Vehicule;
import fonction.outils;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class ConnexionServlet extends HttpServlet {

    ArrayList<Facture> liste_facture = new ArrayList<Facture>();
    ArrayList<Facture> liste_recouvrement = new ArrayList<Facture>();
    ArrayList<Client> liste_client;
    ArrayList<Vehicule> liste_vehicule;
    ArrayList<ArticleServeur> liste_article;
    ArrayList<CompteA> liste_cr;
    outils ou;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConnexionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConnexionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward( request, response );
        String login = request.getParameter("login");
        String mdp = request.getParameter("motdepasse");
        System.out.println(login +" login ");
        System.out.println(mdp +" mdp ");
        ArrayList<Depot> list = ou.listeDepotServeur();
            Parametre parametre=null;
                if(!login.toString().isEmpty() && !mdp.toString().isEmpty()) {
//                        ou.demarreBase(getApplicationContext());
                    parametre = ou.connexion(login.toString(), mdp.toString());
          //
           //         System.out.println(parametre);
                    // ou.data.insertParametre(parametre);
                }else {
                    //Toast.makeText(MainActivity.this, "Veuillez saisir le login et mot de passe",Toast.LENGTH_SHORT).show();
                }
            if(parametre != null){
                liste_client = ou.listeClientServeur(ou.getVille(parametre.getDo_souche(),parametre.getCt_num()));
                for(int i=0;i<liste_client.size();i++) {
          //          ou.data.insertClient(liste_client.get(i));
                }
                liste_article = ou.listeArticleDispo(String.valueOf(parametre.getDe_no()));
                for(int i=0;i<liste_article.size();i++) {
         //         ou.data.insertArticle(liste_article.get(i));
                    QteStock qte = new QteStock();
                    qte.setAS_QteSto(String.valueOf(ou.articleDisponibleServeur(liste_article.get(i).getAr_ref(),Integer.valueOf(parametre.getDe_no()))));
                    qte.setAR_Ref(liste_article.get(i).getAr_ref());
                    qte.setDE_No(String.valueOf(parametre.getDe_no()));
                    qte.setAS_MontSto("");
         //           ou.data.insertStock(qte);
                }
                liste_vehicule = ou.listeVehiculeServeur();
                liste_cr = ou.listePlanCR();
                DateFormat format = new SimpleDateFormat("yyyy-dd-mm", Locale.FRENCH);
                liste_facture= ou.listeFacture(parametre.getCo_no(),
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"0",ou.getVille(parametre.getDo_souche(),parametre.getCt_num()) );
            
           }
        
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
