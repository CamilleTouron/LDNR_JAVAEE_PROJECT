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

/**
 *
 * @author Groupe 1
 */
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {

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
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        // Verifier que l'utilisateur est bien connecter.
        if (session.getAttribute("user") != null) {
            //flash.add("ok", "Vous êtes connecté.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
        } else {
            flash.add("nok", "Vous avez été déconnécté.");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
