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
@WebServlet(name = "Remove", urlPatterns = {"/Remove"})
public class Remove extends HttpServlet {

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
        //Récupérer la session en cours
        HttpSession session = request.getSession();
        //Récupérer les flashmessages
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        //Récupérer l'auberge en cours
        Auberge auberge = (Auberge) session.getAttribute("auberge");
        //Récupérer le user en cours de session
        User user = (User) session.getAttribute("user");
        Event event = Event.getEventById(auberge.getEvent().getIdEvent());
        if (user != null) {

            //Récupérer l'id du User à supprimer
            int idUser = user.getId();
            try {
                //Appeler la méthode deleteParticipant de l'auberge en cours
                auberge.deleteParticipant(event.getIdEvent(), idUser);
                Event.updateNbParticipantTotal(event.getIdEvent());
                session.removeAttribute("events");
                session.setAttribute("events", Event.eventToArray());
                //Réinitialiser l'auberge en cours de session
                Auberge a = Auberge.getAubergeById(event);
                a.setInfos(event.getIdEvent());
                session.setAttribute("auberge", a);
                flash.add("ok", "Participation supprimée.");
                getServletContext().getRequestDispatcher("/WEB-INF/inscriptionAubergeUser.jsp").forward(request, response);
            } catch (IOException | ServletException ex) {
                flash.add("nok", "Impossible de supprimer la participation.");
                getServletContext().getRequestDispatcher("/WEB-INF/inscriptionAubergeUser.jsp").forward(request, response);
            } catch (Exception ex) {
                flash.add("nok", "Impossible de supprimer la participation.");
                getServletContext().getRequestDispatcher("/WEB-INF/inscriptionAubergeUser.jsp").forward(request, response);
            }
        } else {
            flash.add("nok", "Vous n'êtes pas connecté vous devez vous connecter.");
            response.sendRedirect(request.getContextPath() + "/connect");
        }
    }
}
