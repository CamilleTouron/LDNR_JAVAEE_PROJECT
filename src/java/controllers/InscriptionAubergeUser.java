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
import models.FlashMessage;
import models.Auberge;
import models.Event;
import models.User;

/**
 * 
 * @author Groupe 1
 */
@WebServlet(name = "InscriptionAubergeUser", urlPatterns = {"/inscriptionAubergeUser"})
public class InscriptionAubergeUser extends HttpServlet {

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
        // On récupere dans un string l'id de l'event en cours
        String idEvent = (String) request.getParameter("id");
        // On recupere la session en cours
        HttpSession session = request.getSession();
        // On recupere les flash message
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        // On recupere User de la session
        User user = (User) session.getAttribute("user");
        // On recupere l'event entier avec son id
        Event event = Event.getEventById(Integer.parseInt(idEvent));
        //
        if (user != null) {
            if (!user.isAdmin()) {
                try {
                    Auberge auberge = Auberge.getAubergeById(event);
                    auberge.setInfos(Integer.parseInt(idEvent));
                    //Appeler la méthode desactiver de event
                    session.setAttribute("auberge", auberge);
                    flash.add("ok", "Auberge trouvée");
                    getServletContext().getRequestDispatcher("/WEB-INF/inscriptionAubergeUser.jsp").forward(request, response);
                } catch (IOException | NumberFormatException | ServletException ex) {
                    flash.add("nok", "Impossible de trouver l'auberge.");
                    getServletContext().getRequestDispatcher("/WEB-INF/listAubergeUser.jsp").forward(request, response);
                }
            } else {
                flash.add("nok", "Admin, vous n'avez pas accès à cette page.");
                getServletContext().getRequestDispatcher("/WEB-INF/connect.jsp").forward(request, response);
            }
        }else {
                flash.add("nok", "Veuillez vous connecter, pour accéder à cette page.");
                getServletContext().getRequestDispatcher("/WEB-INF/connect.jsp").forward(request, response);
            }
    }
}
