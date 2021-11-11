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
import models.FlashMessage;
import models.PwdUpdateFormChecker;
import models.User;

/**
 *
 * @author Groupe 1
 */
@WebServlet(name = "ProfileChangePwd", urlPatterns = {"/ProfileChangePwd"})
public class ProfileChangePwd extends HttpServlet {

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
        //vérifier que l'user est connecter
        HttpSession session = request.getSession(false);
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        if (session.getAttribute("user") == null) {
            response.sendRedirect(
                    request.getServletContext().getContextPath() + "/connect"
            );
            flash.add("nok", "Pour changer de mot de passe la connexion est recquise.");
        } else {
            PwdUpdateFormChecker checker = new PwdUpdateFormChecker(request);

            if (checker.isValid()) {
                try {
                    User user = (User) session.getAttribute("user");
                    user.updatePwd(request.getParameter("newPwd"), request.getParameter("pwd"));
                    session.removeAttribute("user");
                    request.setAttribute("errors", checker.getErrors());
                    flash.add("ok", "Changement de mot de passe réalisé avec succées, veuillez vous reconnecté avec le nouveau mot de passe.");
                    response.sendRedirect(
                            request.getServletContext().getContextPath() + "/connect"
                    );
                } catch (Exception ex) {
                    flash.add("nok", "Impossible de changer le mot de passe.");
                    response.sendRedirect(
                            request.getServletContext().getContextPath() + "/connect"
                    );
                    //Si cela ne marche pas on affiche un message d'erreur :
                    request.setAttribute("error", ex.getMessage());
                }
            } else {
                request.setAttribute("errors", checker.getErrors());
                this.getServletContext()
                        .getRequestDispatcher("/WEB-INF/profile.jsp")
                        .forward(request, response);
                flash.add("nok", "Impossible de changer le mot de passe.");
            }
        }
    }
}
