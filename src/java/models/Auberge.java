/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groupe 1
 */
public class Auberge {

    /**
     * Attribu id.
     */
    private Integer idAuberge;
    /**
     * Attribu event.
     */
    private Event event;
    /**
     * Attribu liste de participants.
     */
    private List<Participant> participants;
    /**
     * Attribu d'info pour affichage dynamique.
     */
    private int partEntree, partPlat, partDessert, partBoisson, nbConvives;

    /**
     * Récupérer le nombre de part d'entrée pour l'affichage dynamique.
     *
     * @return le nombre de part d'entree de l'evement.
     */
    public int getPartEntree() {
        return partEntree;
    }

    /**
     * Attribu le nombre de part d'entrée.
     *
     * @param partEntree
     */
    public void setPartEntree(int partEntree) {
        this.partEntree = partEntree;
    }

    /**
     * Récupérer le nombre de part de plat pour l'affichage dynamique.
     *
     * @return le nombre de part de plat de l'evement.
     */
    public int getPartPlat() {
        return partPlat;
    }

    /**
     * Attribu le nombre de part de plat.
     *
     * @param partPlat
     */
    public void setPartPlat(int partPlat) {
        this.partPlat = partPlat;
    }

    /**
     * Récupérer le nombre de part de dessert pour l'affichage dynamique.
     *
     * @return le nombre de part de dessert de l'evement.
     */
    public int getPartDessert() {
        return partDessert;
    }

    /**
     * Attribu le nombre de part de dessert.
     *
     * @param PartDessert
     */
    public void setPartDessert(int PartDessert) {
        this.partDessert = PartDessert;
    }

    /**
     * Récupérer le nombre de part de boisson pour l'affichage dynamique.
     *
     * @return le nombre de part de boisson de l'evement.
     */
    public int getPartBoisson() {
        return partBoisson;
    }

    /**
     * Attribu le nombre de part de boisson.
     *
     * @param PartBoisson
     */
    public void setPartBoisson(int PartBoisson) {
        this.partBoisson = PartBoisson;
    }

    /**
     * Récupérer le nombre de convives pour l'affichage dynamique.
     *
     * @return le nombre de convives de l'evement.
     */
    public int getNbConvives() {
        return nbConvives;
    }

    /**
     * Attribu le nombre de nb convives.
     *
     * @param nbConvives
     */
    public void setNbConvives(int nbConvives) {
        this.nbConvives = nbConvives;
    }

    /**
     * Récupérer l'id de l'auberge.
     *
     * @return le nombre de convives de l'evement.
     */
    public Integer getIdAuberge() {
        return idAuberge;
    }

    /**
     * Récupérer l'id de l'auberge.
     *
     * @return l'evement.
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Récupérer la liste des participants de l'auberge.
     *
     * @return l'evement.
     */
    public List<Participant> getParticipants() {
        return participants;
    }
    /**
     * Attribuer l'event de l'auberge.
     *
     * @param event
     */
    public void setEvent(Event event) {
        this.event = event;
    }
    /**
     * Attribuer la liste des participants à l'auberge.
     *
     * @param participants
     */
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    /**
     * Active une auberge.
     *
     * @param user
     * @param name
     * @throws Exception
     */
    public void activate(User user, String name) throws Exception {
        Event.activate(user, name);
    }

    /**
     * Desactive une auberge
     *
     * @param user
     * @param name
     * @throws Exception
     */
    public void desactivate(User user, String name) throws Exception {
        Event.activate(user, name);
    }

    /**
     * Ajouter un participant
     *
     * @param p
     * @param idEvent
     * @throws java.lang.Exception
     */
    public void addParticipant(Participant p, int idEvent) throws Exception {
        Participant.addParticipant(p, idEvent);
        this.participants.add(p);
    }

    /**
     * Méthode pour modifier un participant à partir d'un participant p.
     *
     * @param p
     * @param idEvent
     * @throws java.lang.Exception
     */
    public void modifyParticipant(Participant p, int idEvent) throws Exception {
        Participant.modifyParticipant(p, idEvent);
    }

    /**
     * Méthode pour supprimer un participant grace à un id.
     *
     * @param idEvent
     * @param idUser
     * @throws java.lang.Exception
     */
    public void deleteParticipant(int idEvent, int idUser) throws Exception {
        Participant.deleteParticipant(idEvent, idUser);
    }

    /**
     * Méthode pour chercher une auberge par id, Qui servira à afficher dans la
     * vue le détail d'une auberge.
     *
     * @param e
     * @return auberge
     */
    public static Auberge getAubergeById(Event e) {
        //Faire un buffer Auberge
        Auberge auberge = new Auberge();
        List<Participant> listOfParticipants = new ArrayList<>();
        //Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        //Ecrire la requete et la stocker dans un string :
        String req = "SELECT * FROM (events e INNER JOIN participants p ON e.idEvent=p.idEvent)INNER JOIN users u ON u.idUser = p.idUser WHERE e.idEvent=?";
        try {
            //Faire une pré- requete :
            PreparedStatement pstmt = conn.prepareStatement(req);
            //Remplacer les ?
            pstmt.setInt(1, e.getIdEvent());
            try {
                //Executer la prerequete.               
                ResultSet rs = pstmt.executeQuery();
                rs = pstmt.getResultSet();
                //Si il existe une premiere ligne + je me possitionne dessus
                if (rs.first()) {
                    //Je cherche les attribus d'event et les mets dans listOfEvents
                    //Set l'event de l'auberge
                    e.setDate(rs.getString("date"));
                    e.setName(rs.getString("name"));
                    e.setNbParticipantsTotal(rs.getInt("nbParticipantsTotal"));
                    if (rs.getInt("desactiver") == 1) {
                        e.setDesactiver(true);
                    } else {
                        e.setDesactiver(true);
                    }
                    //Set la liste des participant
                    do {
                        Participant p;
                        //Récupérer le user
                        User u = new User();
                        u.setAlias(rs.getString("alias"));
                        u.setContact(rs.getString("contact"));
                        u.setIdUser(rs.getInt("idUser"));
                        u.setPassword("");
                        p = new Participant(u);
                        p.setConvives(rs.getInt("convives"));
                        Dish d = new Dish(rs.getString("boisson"), rs.getInt("partBoisson"));
                        p.setBoisson(d);
                        d = new Dish(rs.getString("plat"), rs.getInt("partPlat"));
                        p.setPlat(d);
                        d = new Dish(rs.getString("dessert"), rs.getInt("partDessert"));
                        p.setDessert(d);
                        d = new Dish(rs.getString("entree"), rs.getInt("partEntree"));
                        p.setEntree(d);
                        p.setComment(rs.getString("commentaire"));
                        listOfParticipants.add(p);
                    } while (rs.next());
                    //Rajouter la liste des participants à l'auberge.
                }
                auberge.setEvent(e);
                auberge.setParticipants(listOfParticipants);

            } catch (SQLException sQLException) {
            }
        } catch (SQLException ex) {
        }
        return auberge;
    }

    /**
     * Methode qui sers a compter le nombre de de part d'entree d'un event.
     *
     * @return nbParticipantTotal le nombre de participant total de idEvent
     */
    private int getPartEntree(int idEvent) {
        int nbPartEntree = 0;
        // Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        // Faire la string req
        String req = "SELECT SUM(partEntree) FROM participants WHERE idEvent=? ";
        // Remplacer le ? par l'idEvent
        try {
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, idEvent);
            // Execute querry
            ResultSet rs = pstmt.executeQuery();
            rs = pstmt.getResultSet();
            // Utiliser resultSet pour utiliser le res de psmtmt
            if (rs.first()) {
                // rajoutez le nombre de nbParticipantTotal = res
                nbPartEntree = rs.getInt("SUM(partEntree)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("nbENtree =" + nbPartEntree);
        return nbPartEntree;
    }

    /**
     * Methode qui sers a compter le nombre de de part de plat d'un event.
     *
     * @return nbParticipantTotal le nombre de participant total de idEvent
     */
    private int getPartPlat(int idEvent) {
        int nbPartPlat = 0;
        // Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        // Faire la string req
        String req = "SELECT SUM(partPlat) FROM participants WHERE idEvent=? ";
        // Remplacer le ? par l'idEvent
        try {
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, idEvent);
            // Execute querry
            ResultSet rs = pstmt.executeQuery();
            rs = pstmt.getResultSet();
            // Utiliser resultSet pour utiliser le res de psmtmt
            if (rs.first()) {
                // rajoutez le nombre de nbParticipantTotal = res
                nbPartPlat = rs.getInt("SUM(partPlat)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("nbPlat =" + nbPartPlat);
        return nbPartPlat;
    }

    /**
     * Methode qui sers a compter le nombre de dessert de plat d'un event.
     *
     * @return nbParticipantTotal le nombre de participant total de idEvent
     */
    private int getPartDessert(int idEvent) {
        int nbPartDessert = 0;
        // Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        // Faire la string req
        String req = "SELECT SUM(partDessert) FROM participants WHERE idEvent=? ";
        // Remplacer le ? par l'idEvent
        try {
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, idEvent);
            // Execute querry
            ResultSet rs = pstmt.executeQuery();
            rs = pstmt.getResultSet();
            // Utiliser resultSet pour utiliser le res de psmtmt
            if (rs.first()) {
                // rajoutez le nombre de nbParticipantTotal = res
                nbPartDessert = rs.getInt("SUM(partDessert)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("nbDessert =" + nbPartDessert);
        return nbPartDessert;
    }

    /**
     * Methode qui sers a compter le nombre de boisson de plat d'un event.
     *
     * @return nbParticipantTotal le nombre de participant total de idEvent
     */
    private int getPartBoisson(int idEvent) {
        int nbPartBoisson = 0;
        // Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        // Faire la string req
        String req = "SELECT SUM(partBoisson) FROM participants WHERE idEvent=? ";
        // Remplacer le ? par l'idEvent
        try {
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, idEvent);
            // Execute querry
            ResultSet rs = pstmt.executeQuery();
            rs = pstmt.getResultSet();
            // Utiliser resultSet pour utiliser le res de psmtmt
            if (rs.first()) {
                // rajoutez le nombre de nbParticipantTotal = res
                nbPartBoisson = rs.getInt("SUM(partBoisson)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("nbBoisson =" + nbPartBoisson);
        return nbPartBoisson;
    }

    /* Methode qui sers a compter le nombre de dessert de plat d'un event.
     * 
     * @return nbParticipantTotal le nombre de participant total de idEvent
     */
    private int getPartConvives(int idEvent) {
        int nbPartConvives = 0;
        // Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        // Faire la string req
        String req = "SELECT SUM(convives) FROM participants WHERE idEvent=? ";
        // Remplacer le ? par l'idEvent
        try {
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, idEvent);
            // Execute querry
            ResultSet rs = pstmt.executeQuery();
            rs = pstmt.getResultSet();
            // Utiliser resultSet pour utiliser le res de psmtmt
            if (rs.first()) {
                // rajoutez le nombre de nbParticipantTotal = res
                nbPartConvives = rs.getInt("SUM(convives)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("nbConvives =" + nbPartConvives);
        return nbPartConvives;
    }
    /**
     * Méthode qui set les infos pour l'affichage dynamique.
     * @param idEvent 
     */
    public void setInfos(int idEvent) {
        this.nbConvives = this.getPartConvives(idEvent);
        this.partEntree = this.getPartEntree(idEvent);
        this.partPlat = this.getPartPlat(idEvent);
        this.partDessert = this.getPartDessert(idEvent);
        this.partBoisson = this.getPartBoisson(idEvent);
    }
}
