/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
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
@WebServlet(name = "Activate", urlPatterns = {"/Activate"})
public class Activate extends HttpServlet {


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
        this.doPost(request, response);
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
        String buffer = (String) request.getParameter("id");
        System.out.println(buffer);
        HttpSession session = request.getSession();
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        try {
            //Appeler la méthode activer de event
            Event.activate((User) session.getAttribute("user"), request.getParameter("id"));
           // On retire l'attribut
            session.removeAttribute("events");
            // Puis on le remet afin de repartir sur la la liste d'event
            session.setAttribute("events",Event.eventToArray());
            // Notre message flash
            flash.add("ok", "Auberge activée.");
            getServletContext().getRequestDispatcher("/WEB-INF/listAubergeAdmin.jsp").forward(request, response);
        } catch (Exception ex) {
            flash.add("nok", "Impossible de desactiver l'auberge.");
            getServletContext().getRequestDispatcher("/WEB-INF/listAubergeAdmin.jsp").forward(request, response);
        }    
    }
}
