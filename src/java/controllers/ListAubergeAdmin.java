/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Event;
import models.FlashMessage;
import models.User;
/**
 * 
 * @author Groupe 1
 */
@WebServlet(name = "ListAubergeAdmin", urlPatterns = {"/listAubergeAdmin"})
public class ListAubergeAdmin extends HttpServlet {

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
            
        }
    }

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
        // On recupere la session en cours
        HttpSession session = request.getSession();
        // On recupere nos flash message
        FlashMessage flash = (FlashMessage)session.getAttribute("flash");
        // On set l'attribut avec la liste d'event
        session.setAttribute("event", Event.eventToArray());
        if(session.getAttribute("user")!=null){
            User user= (User)session.getAttribute("user");
            if(user.isAdmin()){
                flash.add("ok", "Voici la liste des auberges.");
            getServletContext()
                .getRequestDispatcher("/WEB-INF/listAubergeAdmin.jsp")
                .forward(request, response);
            return;
            }
        }
        flash.add("nok","Veuillez vous connecter.");
        getServletContext()
                .getRequestDispatcher("/WEB-INF/connect.jsp")
                .forward(request, response);
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


}
