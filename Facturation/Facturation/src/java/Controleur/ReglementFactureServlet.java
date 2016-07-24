/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import fonction.outils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author T.Ron
 */
public class ReglementFactureServlet extends HttpServlet {

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
            out.println("<title>Servlet ReglementFactureServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReglementFactureServlet at " + request.getContextPath() + "</h1>");
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
        int mtt_regl = Integer.parseInt(request.getParameter("mtt_regl"));
        String cr_no = request.getParameter("cr_no");
        int total_ttc = Integer.parseInt(request.getParameter("total_ttc"));
        int avance = Integer.parseInt(request.getParameter("avance"));
        String entete = request.getParameter("entete");
        
        int total = mtt_regl - (total_ttc- avance);
        String dr="0";
        if(total>=0) {
            dr = "1";
            mtt_regl=total_ttc- avance;
        }
        outils.addEcheance(cr_no, String.valueOf(mtt_regl), entete, dr);
        if(avance==0){
        //    cr=ou.reglerEntete(fact.getEntete(), fact.getRef(), String.valueOf(mtt_regl));
        }else {
            if (cr == 0) {
                //cr = outils.addReglement(fact.getEn), "RGT" /*+ fact.getId_client().getIntitule()*/, String.valueOf(mtt_regl),String.valueOf(parametre.getCo_no()));
            }
        }
        for(int i=0;i<lst_client.size();i++) {

            if (lst_client.get(i).getIntitule().equals(client.getText().toString())) {
                valideFacture(lst_client.get(i).getNum());
            }
        }
        System.out.println("total "+total);
        if(total<=0){
            total = 0;
            mtt.setEnabled(true);
            mtt.setText(""+total);
            lst_fact.setEnabled(true);
            outils.updateRgImpute(String.valueOf(cr.getCbMarq()));
            cr=null;
        }
        AfficheFacture();
        if(total>0) {
            lst_fact.setEnabled(true);
            mtt.setEnabled(false);
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
