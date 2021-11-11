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
@WebServlet(name = "Modifier", urlPatterns = {"/modifier"})
public class Modifier extends HttpServlet {

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

        // récupérer la session
        HttpSession session = request.getSession();
        // récupérer attribut auberge de la session (user, alias participant)
        Auberge auberge = (Auberge) session.getAttribute("auberge");
        // récupérer flashMessage
        FlashMessage flash = (FlashMessage) session.getAttribute("flash");
        // créer participant 
        //  on récupère l'user
        User user = (User) session.getAttribute("user");
        Event event = Event.getEventById(auberge.getEvent().getIdEvent());
        if (user != null) {

            Participant p = new Participant(user);
            // récupérer dans requete les infos modifiées et ajouter dans participants
            p.setConvives(Integer.parseInt(request.getParameter("convives")));
            p.setEntree(new Dish(request.getParameter("entree"), Integer.parseInt(request.getParameter("partEntree"))));
            p.setPlat(new Dish(request.getParameter("plat"), Integer.parseInt(request.getParameter("partPlat"))));
            p.setBoisson(new Dish(request.getParameter("boisson"), Integer.parseInt(request.getParameter("partBoisson"))));
            p.setDessert(new Dish(request.getParameter("dessert"), Integer.parseInt(request.getParameter("partDessert"))));
            p.setComment(request.getParameter("commentaire"));

            try {
                auberge.modifyParticipant(p, auberge.getEvent().getIdEvent());
                // Appeler la fonction de mise à jour du nombre de participants total d'un event
                Event.updateNbParticipantTotal(auberge.getEvent().getIdEvent());
                // On retire nos attribut
                session.removeAttribute("events");
                // Puis on le remet sur la liste d'event
                session.setAttribute("events", Event.eventToArray());
                //Remove l'auberge de la session en cours
                session.removeAttribute("auberge");
                //AddAttribute auberge  la session en utilisant getaubergebyid en passant en parametre l'id du buffer auberge qu'on a créer
                session.setAttribute("auberge", Auberge.getAubergeById(event));
                flash.add("ok", "Votre participation a bien été modifié.");
            } catch (Exception ex) {
                flash.add("nok", "Votre participation n'a pas été modifié");
                Logger.getLogger(Modifier.class.getName()).log(Level.SEVERE, null, ex);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/inscriptionAubergeUser.jsp").forward(request, response);
        } else {
            flash.add("nok", "Vous n'êtes pas connecté vous devez vous connecter.");
            response.sendRedirect(request.getContextPath() + "/connect");
            //else, le rediriger vers connect.jsp
        }
    }

}
