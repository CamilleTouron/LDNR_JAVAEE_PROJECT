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
import models.SubscribeFormChecker;
import models.User;

/**
 *
 * @author Groupe 1
 */
@WebServlet(name = "Subscribe", urlPatterns = {"/subscribe"})
public class Subscribe extends HttpServlet {

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
        // On récupere la session
        HttpSession session = request.getSession();
        // On demande l'affichage du formulaire => appel de la vue        
        // Un attribut de requête dont la clé est "msg" et la valeur est "Coucou"
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        if (session.getAttribute("user") != null) {
            flash.add("warning", "Attention, vous êtes déjà connécté.");
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }
        flash.add("ok", "Veuillez vous inscrire.");
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/subscribe.jsp")
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
        // J'instancie un objet métier pour vérifier le formulaire
        SubscribeFormChecker checker = new SubscribeFormChecker(request);
        // Recuperer la session
        HttpSession session = request.getSession();
        // Recuperer les emssage Flash 
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        // Selon la réponse donnée par l'objet métier
        // je redirige vers une vue ou une autre
        if (checker.checkForm()) {
            try {
                User user = User.createUser(request.getParameter("alias"), request.getParameter("pwd"), request.getParameter("contact"));
                response.sendRedirect(
                        request.getServletContext().getContextPath() + "/connect"
                );
                flash.add("ok", "Inscription réalisée avec succés.");
                return;
            } catch (Exception ex) {
                request.setAttribute("errorMsg", ex.getMessage());
                request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/subscribe.jsp")
                        .forward(request, response);
            }
        } else {
            flash.add("nok", "Inscription échouée.");
            request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/subscribe.jsp")
                    .forward(request, response);
        }
    }
}
