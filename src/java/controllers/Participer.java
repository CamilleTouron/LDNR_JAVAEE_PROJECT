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
import models.Auberge;
import models.Dish;
import models.Event;
import models.FlashMessage;
import models.Participant;
import models.User;

/**
 *
 * @author Groupe 1
 */
@WebServlet(name = "Participer", urlPatterns = {"/Participer"})
public class Participer extends HttpServlet {

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
        // La session en cours
        HttpSession session = request.getSession();
        // Flash Message
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        // Auberge en cours
        Auberge auberge = (Auberge) session.getAttribute("auberge");
        // User de la session
        User user = (User) session.getAttribute("user");
        Event event = Event.getEventById(auberge.getEvent().getIdEvent());

        if (user != null) {
            // On recupere l'id event de la session
            //int idEvent = auberge.getEvent().getIdEvent();
            // Id de l'user
            int idUser = user.getId();

            Participant p = new Participant((User) session.getAttribute("user"));
            // récupérer dans requete les infos modifiées et ajouter dans participants
            p.setConvives(Integer.parseInt(request.getParameter("convives")));
            p.setEntree(new Dish(request.getParameter("entree"), Integer.parseInt(request.getParameter("partEntree"))));
            p.setPlat(new Dish(request.getParameter("plat"), Integer.parseInt(request.getParameter("partPlat"))));
            p.setBoisson(new Dish(request.getParameter("boisson"), Integer.parseInt(request.getParameter("partBoisson"))));
            p.setDessert(new Dish(request.getParameter("dessert"), Integer.parseInt(request.getParameter("partDessert"))));
            p.setComment(request.getParameter("commentaire"));

            try {
                // On apelle la méthode addPart de l'auberge en cours
                auberge.addParticipant(p, event.getIdEvent());
                session.removeAttribute("events");
                Event.updateNbParticipantTotal(event.getIdEvent());
                session.setAttribute("events", Event.eventToArray());
                session.removeAttribute("auberge");
                // Appeler la méthode getAubergebyId     
                Auberge a = Auberge.getAubergeById(event);
                a.setInfos(event.getIdEvent());
                session.setAttribute("auberge", a);
                flash.add("ok", "Vous avez bien été ajouté.");

            } catch (Exception ex) {
                flash.add("nok", "Vous n'avez pas été ajouté.");
                Logger.getLogger(Participer.class.getName()).log(Level.SEVERE, null, ex);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/inscriptionAubergeUser.jsp").forward(request, response);
        } else {
            flash.add("nok", "Vous n'êtes pas connecté vous devez vous connecter.");
            response.sendRedirect(request.getContextPath() + "/connect");
            //else, le rediriger vers connect.jsp
        }
    }
}
