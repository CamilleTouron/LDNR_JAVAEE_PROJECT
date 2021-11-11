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
import models.ContactlUpdateFormChecker;
import models.FlashMessage;
import models.User;

/**
 *
 * @author Groupe 1
 */
@WebServlet(name = "ProfileChangeContact", urlPatterns = {"/ProfileChangeContact"})
public class ProfileChangeContact extends HttpServlet {

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
        // On recupe la session en cours
        HttpSession session = request.getSession(false);
        // On recupere nos flash message
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        // Vérifier que l'user est connecter

        if (session.getAttribute("user") == null) {
            response.sendRedirect(
                    request.getServletContext().getContextPath() + "/connect"
            );
            flash.add("nok", "Pour changer le contact la connexion est recquise.");
        } else {
            //recupérer les parametres et vérifier si ils sont justes ( >8 et non identiques)
            ContactlUpdateFormChecker checker = new ContactlUpdateFormChecker(request);
            if (checker.isValid()) {
                //appeler le user pour update le mot de passe
                try {
                    //Récupérer l'attribu alias de la session qui est créer 
                    //dans le Connext.java lors de la connexion :
                    User user = (User) session.getAttribute("user");
                    //Utiliser user pour changer le mot de passe :
                    user.updateContact(request.getParameter("newContact"), request.getParameter("contact"));
                    //Le deconnecter de la session
                    session.removeAttribute("user");
                    //L'envoyer sur la page de connexion 
                    request.setAttribute("errors", checker.getErrors());
                    flash.add("ok", "Changement de contact réalisé avec succées, veuillez vous reconnecté..");
                    this.getServletContext()
                            .getRequestDispatcher("/WEB-INF/connect.jsp")
                            .forward(request, response);
                } catch (Exception ex) {
                    flash.add("nok", "Impossible de changer le contact.");
                    //Si cela ne marche pas on affiche un message d'erreur :
                    request.setAttribute("error", ex.getMessage());
                }
            } else {
                request.setAttribute("errors", checker.getErrors());
                this.getServletContext()
                        .getRequestDispatcher("/WEB-INF/profile.jsp")
                        .forward(request, response);
                flash.add("nok", "Impossible de changer le contact.");
            }
        }
    }
}
