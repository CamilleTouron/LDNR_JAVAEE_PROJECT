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

/**
 *
 * @author Groupe 1
 */
@WebServlet(name = "ListAubergeUser", urlPatterns = {"/listAubergeUser"})
public class ListAubergeUser extends HttpServlet {

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
        // On recupere la session en cours
        HttpSession session = request.getSession();
        // On recupere nos message flash
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        if (session.getAttribute("user") != null) {
            session.setAttribute("events", Event.eventToArray());
            flash.add("ok", "Voici la liste des auberges.");
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/listAubergeUser.jsp")
                    .forward(request, response);
            return;
        } else {
            flash.add("nok", "Veuillez vous connecter.");
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
