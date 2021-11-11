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
import models.ConnectFormChecker;
import models.Event;
import models.FlashMessage;
import models.User;

/**
 *
 * @author  Groupe 1
 */
@WebServlet(name = "Connect", urlPatterns = {"/connect"})
public class Connect extends HttpServlet {

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
        // Recuperer la session
        HttpSession session = request.getSession();
        // Recuperer les message flash
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        if (session.getAttribute("user") != null) {
            flash.add("warning", "Attention, vous êtes déjà connécté.");
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }
        flash.add("ok", "Veuillez vous connecter.");
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
        // Recuperer la session
        HttpSession session = request.getSession();
        // Recuperer les flash message
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        // Recuperer le validateur
        ConnectFormChecker checker = new ConnectFormChecker(request);
        if (checker.checkForm()) {
            User user = User.isValid(request.getParameter("alias"), request.getParameter("pwd"));
            if (user != null) {
                //Créer ou recuperer une session pour l'utilisateur
                session.setAttribute("user", user);
                //Récupérer la liste des events de la BDD
                session.setAttribute("events", Event.eventToArray());
                flash.add("ok", "Vous êtes connecté.");
                //Envoyer vers la page de profil.
                if (user.isAdmin()) {
                    response.sendRedirect(request.getContextPath() + "/listAubergeAdmin");
                } else {
                    response.sendRedirect(request.getContextPath() + "/listAubergeUser");
                }
                return;
            } else {
                flash.add("nok", "Cet utilisateur n'est pas reconnu");
            }
        }
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/connect.jsp")
                .forward(request, response);
    }
}
