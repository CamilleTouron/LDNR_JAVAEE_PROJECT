/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@WebServlet(name = "AddEvent", urlPatterns = {"/addEvent"})
public class AddEvent extends HttpServlet {

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
        //Récupérer la session en cours
        HttpSession session = request.getSession();
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        User user = (User) session.getAttribute("user");
        //Vérifier si le user de la session en cours existe
        if (user != null) {
            //Vérifier si le user est admin
            if (user.isAdmin()) {
                try {
                    //Ajouter l'event, appeller createEvent(request.getParameter("date"),
                    //request.getParameter("date"));
                    Event.createEvent(user, (String) request.getParameter("date"),
                            (String) request.getParameter("name"));
                    
                    //Supprimer la liste des events courants
                    session.removeAttribute("events");
                    
                    //Récupérer la liste des events de la BDD
                    session.setAttribute("events", Event.eventToArray());
                    // Message Flash 
                    flash.add("ok", "Vous avez bien crée l'auberge " + (String) request.getParameter("name") + " à la date du "
                            + (String) request.getParameter("date") + ".");
                    
                    this.getServletContext()
                            .getRequestDispatcher("/WEB-INF/listAubergeAdmin.jsp")
                            .forward(request, response);
                    
                } catch (Exception ex) {
                    Logger.getLogger(AddEvent.class.getName()).log(Level.SEVERE, null, ex);
                    flash.add("nok", "IMPOSSIBLE DE CREER L'AUBERGE " + (String) request.getParameter("name"));
                    response.sendRedirect(request.getContextPath() + "/connect");
                }
            } else {
                session.removeAttribute("user");
                flash.add("nok", "Vous n'êtes pas l'admin vous devez vous connecter.");
                response.sendRedirect(request.getContextPath() + "/connect");
                //else, le rediriger vers connect.jsp
            }
        } else {
            flash.add("nok", "Vous n'êtes pas connecté vous devez vous connecter.");
            response.sendRedirect(request.getContextPath() + "/connect");
            //else, le rediriger vers connect.jsp
        }
    }
}