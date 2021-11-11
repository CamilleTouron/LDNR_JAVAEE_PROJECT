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
import models.Auberge;
import models.Event;
import models.FlashMessage;
import models.User;

/**
 *
 * @author Groupe 1
 */
@WebServlet(name = "DetailAubergeAdmin", urlPatterns = {"/detailaubergeadmin"})
public class DetailAubergeAdmin extends HttpServlet {

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
        // On recupere dans un string l'id de l'event en cours
        String idEvent = (String) request.getParameter("id");
        // On recupere la session en cours
        HttpSession session = request.getSession();
        // On recupere les flash messages
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        // On recupere l'event entier avec l'id
        Event event = Event.getEventById(Integer.parseInt(idEvent));

        // Appeler la méthode getAubergebyId     
        Auberge auberge = Auberge.getAubergeById(event);
        auberge.setInfos(event.getIdEvent());

        session.setAttribute("auberge", auberge);
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            // Si c'est bien l'admin on entre dans le if
            if (user.isAdmin()) {
                flash.add("ok", "Voici le details de l'auberge " + auberge
                        .getEvent().getName() + ".");
                getServletContext()
                        .getRequestDispatcher("/WEB-INF/detailAubergeAdmin.jsp")
                        .forward(request, response);
                // Si pas admin retour page de co ( flash msg pas admin ) 
            } else {
                flash.add("nok", "Vous n'êtes pas admin.");
                session.removeAttribute("user");
                getServletContext()
                        .getRequestDispatcher("/WEB-INF/connect.jsp")
                        .forward(request, response);
            }
            // si pas de session retour a la page connection (flash msg pas co)
        } else {
            flash.add("nok", "Vous n'êtes pas connecté.");
            session.removeAttribute("user");
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/connect.jsp")
                    .forward(request, response);
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
    }

}
