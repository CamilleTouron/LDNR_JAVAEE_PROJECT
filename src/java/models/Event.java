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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stag
 */
public class Event {
    /**
     * Attribu id d'un event.
     */
    private int idEvent;
    /**
     * Attribu date d'un event.
     */
    private String date;
    /**
     * Attribu name d'un event.
     */
    private String name;
    /**
     * Attribu nombre de participants total d'un event.
     */
    private int nbParticipantsTotal;
    /**
     * Attribu qui représente si l'event est desactiver ou pas.
     */
    private boolean desactiver;
    /**
     * Méthode de récupération du nombre de participants total de l'event.
     * @return 
     */
    public int getNbParticipantsTotal() {
        return nbParticipantsTotal;
    }
    /**
     * Méthode qui récupére si l'event est desactiver ou pas.
     * @return 
     */
    public boolean isDesactiver() {
        return desactiver;
    }
    /**
     * Méthode que attribu le nombre de participant total d'un event.
     * @param nbParticipantsTotal 
     */
    public void setNbParticipantsTotal(int nbParticipantsTotal) {
        this.nbParticipantsTotal = nbParticipantsTotal;
        // TODO 1 RAJOUTER LE CODE QUI MODIFIE LA BASE DE DONNEE : 
        // rajouter à la bdd la colonne nbParticipantsTotal
        // c'est à dire mets à jour le nombre de participant total de l'envent
    }
    /**
     * Méthode de récuperation de l'id de l'event.
     * @return 
     */
    public int getIdEvent() {
        return idEvent;
    }
    /**
     * Méthode de récupération de la date de l'event.
     * @return 
     */
    public String getDate() {
        return date;
    }
    /**
     * Méthode de récupération du nom de l'event.
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * Méthode de l'attribu desactiver de l'event.
     * @return 
     */
    public boolean getDesactiver() {
        return desactiver;
    }
    /**
     * Méthode d'attribution de la valeur de l'id de l'event.
     * @param idEvent
     */
    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
    /**
     * Méthode d'attribution de la valeur date de l'event.
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * Méthode d'attribution de la valeur nom de l'event.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Méthode d'attribution de la valeur desactiver de l'event.
     * @param desactiver
     */
    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }

    /**
     * Méthode qui servira pour le html listAubergeUser/Admin
     *
     * @return
     */
    public static ArrayList<Event> eventToArray() {
        //Déclarer un buffer qu'on retournera
        ArrayList<Event> listOfEvents = new ArrayList<>();
        //Appeler la connexion
        Connection conn = MySqlConnection.getInstance();
        //Ecrire la requete et la stocker dans un string :
        String req = "SELECT * FROM events";

        try {
            //Faire une pré- requete :
            PreparedStatement pstmt = conn.prepareStatement(req);
            //Executer la prerequete.
            ResultSet rs = pstmt.executeQuery();
            rs = pstmt.getResultSet();
            //Si il existe une premiere ligne + je me possitionne dessus
            if (rs.first()) {
                //Je cherche les attribus d'event et les mets dans listOfEvents
                do {
                    Event e = new Event();
                    e.date = rs.getString("date");
                    e.name = rs.getString("name");
                    if (rs.getInt("desactiver") == 1) {
                        e.desactiver = true;
                    } else {
                        e.desactiver = false;
                    }
                    e.idEvent = rs.getInt("idEvent");
                    e.nbParticipantsTotal = rs.getInt("nbParticipantsTotal");
                    listOfEvents.add(e);
                } while (rs.next());
            }
        } catch (SQLException ex) {

        }
        return listOfEvents;
    }

    /**
     * Méthode qui retourn true si un evenement exist et sinon false.
     *
     * @param name
     * @param date
     * @return
     */
    public boolean eventExist(String name, String date) {
        boolean exist = false;
        //Appeler la connexion :
        Connection conn = MySqlConnection.getInstance();
        //Ecrire la requete et la stocker dans un string :
        String req = "SELECT * WHERE `events`.`name` = ? AND `events`.`date` = ?";
        try {
            //Faire une pré- requete :
            PreparedStatement pstmt = conn.prepareStatement(req);
            //Remplacer les ?
            pstmt.setString(1, name);
            pstmt.setString(2, date);
            try {
                //Executer la prerequete.
                int qty = pstmt.executeUpdate();
                ResultSet keys = pstmt.getResultSet();
                if (keys.first()) {//théoriquement qu'une ligne.
                    exist = true;
                }
            } catch (SQLException sQLException) {
                exist = false;
            }
        } catch (SQLException ex) {
            exist = false;
        }
        return exist;
    }
    /**
     * Méthode qui créer un event.
     * @param user
     * @param date
     * @param name
     * @return
     * @throws Exception 
     */
    public static boolean createEvent(User user, String date, String name) throws Exception {
        //Vérifier que le user en cours et bien l'admin.
        int desactiver = 0;
        int nbParticipantsTotal = 0;
        if (!user.isAdmin()) {
            throw new Exception("Vous n'êtes pas l'admin.");
        }
        //Créer un buffer event :
        Event event = new Event();
        if (!event.eventExist(name, date)) {
            //TODO 2

            //Appeler la connexion :
            Connection conn = MySqlConnection.getInstance();
            //Ecrire une requete stockée dans un string :
            String req = "INSERT INTO events (date ,name, desactiver, nbParticipantsTotal) VALUES (?,?,?,?)";

            //Faire une pre-requete grace à la requete et la connexion qui genere la
            //clé primaire :
            //Dans un try-catch, 
            // 1 -Remplacer les ? par les valeurs passé en parametres :
            // 2 -Dans un autre try-catch excecuter la prérequete :
            // 3 -Définir les attribus du buffer event :
            //      a -La date :
            //      b -Le nom :
            //      c -Le "desactiver", par défaut il est actif à la création:
            //      d -Le nb de participant initialement à 0:
            //      e -La clé primaire qu'il faut chercher grace à la prerequete et 
            //         un ResultSet puis dans un if voir si elle existe si oui 
            //         l'attribuer à idEvent du buffer event :
            // 4 -Fermer le catch en jetant une exception :
            //Retourner l'event créer :
            try {
                PreparedStatement pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, date);

                pstmt.setString(2, name);

                pstmt.setInt(3, desactiver);

                pstmt.setInt(4, nbParticipantsTotal);

                try {
                    int qty = pstmt.executeUpdate();

                    return true;
                } catch (SQLException sQLException) {
                    throw new Exception("ERREUR CREATION EVENT");
                }
            } catch (SQLException ex) {
                throw new Exception("ERREUR CREATION EVENT");
            }
        } else {
            return false;
        }
    }

    /**
     * CETTE METHODE VA ETRE UTILISER PAR L'ADMIN ! Méthode qui active un event.
     *
     * @param user qui sera le user en cours.
     * @param id de l'auberge
     * @throws Exception si l'user en cours n'est pas l'admin.
     */
    public static void activate(User user, String id) throws Exception {
        //Vérifier que le user en cours et bien l'admin.
        if (!user.isAdmin()) {
            throw new Exception("Vous n'êtes pas l'admin.");
        }
        //Appeler la connexion :
        Connection conn = MySqlConnection.getInstance();
        //Ecrire la requete et la stocker dans un string :
        String req = "UPDATE `events` SET `desactiver` = ? WHERE `events`.`idEvent` = ? ";
        try {
            //Faire une pré- requete :
            PreparedStatement pstmt = conn.prepareStatement(req);
            //Mettre desactiver à 0 c'est a dire event actif.
            pstmt.setString(1, "0");
            //Chercher par le nom de l'event.
            pstmt.setInt(2, Integer.parseInt(id));
            try {
                //Executer la prerequete.
                int qty = pstmt.executeUpdate();
            } catch (SQLException sQLException) {
            }
        } catch (SQLException ex) {
            throw new Exception("Impossible d'activer.");
        }
    }

    /**
     * CETTE METHODE VA ETRE UTILISER PAR L'ADMIN ! Méthode qui desactive un
     * event.
     *
     * @param user qui sera le user en cours.
     * @param id de l'auberge
     * @throws Exception si l'user en cours n'est pas l'admin.
     */
    public static void desactivate(User user, String id) throws Exception {
        //Vérifier que le user en cours et bien l'admin.
        if (!user.isAdmin()) {
            throw new Exception("Vous n'êtes pas l'admin.");
        }
        //Appeler la connexion :
        Connection conn = MySqlConnection.getInstance();
        //Ecrire la requete et la stocker dans un string :
        String req = "UPDATE `events` SET `desactiver` = ? WHERE `events`.`idEvent` = ? ";
        try {
            //Faire une pré- requete :
            PreparedStatement pstmt = conn.prepareStatement(req);
            //Mettre desactiver à 1 c'est a dire event non actif.
            pstmt.setString(1, "1");
            //Chercher par le nom de l'event.
            pstmt.setInt(2, Integer.parseInt(id));
            try {
                //Executer la prerequete.
                int qty = pstmt.executeUpdate();
            } catch (SQLException sQLException) {
            }
        } catch (SQLException ex) {
            throw new Exception("Impossible de desactiver.");
        }
    }

    /**
     * Methode qui sers a compter le nombre de ligne total dans un event
     *
     * @return nbLigne le nombre de ligne total de idEvent
     */
    private static int countParticipants(int idEvent) {
        int nbLigne = 0;
        // Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        //Faire la string req
        String req = "SELECT COUNT(*) FROM `participants` WHERE idEvent=? ";
        // Remplacer le ? par l'idEvent

        try {
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, idEvent);

            ResultSet rs = pstmt.executeQuery();
            rs = pstmt.getResultSet();
            // Si il existe une premiere ligne je me possitionne
            if (rs.first()) {
                nbLigne = rs.getInt("COUNT(*)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbLigne;
    }

    /**
     * Methode qui sers a compter le nombre de convives + participant total d'un
     * event.
     *
     * @return nbParticipantTotal le nombre de participant total de idEvent
     */
    private static int sumNbParticipantsTotal(int idEvent) {
        // Déclaration des variables 
        int nbParticipants = 0;
        int nbConvives = 0;
        int nbParticipantsTotal = 0;
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
                nbConvives = rs.getInt("SUM(convives)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);

        }
        // Recuperer le resultat de countParticipant 
        nbParticipants = countParticipants(idEvent);
        // nbParticipantTotal = nbParticipantTotal + fonction count Part
        nbParticipantsTotal = nbParticipants + nbConvives;
        // le retourner
        return nbParticipantsTotal;
    }

    /**
     *
     * Methode qui sers a mettre a jour le nombre total de participant d'un
     * event.
     *
     * @param idEvent a mettre a jour
     */
    public static void updateNbParticipantTotal(int idEvent) {
        // Buffer pour aller chercher la fonction nbPartTotal        
        int nbParticipantsTotal = sumNbParticipantsTotal(idEvent);
        // Faire la connexion 
        Connection conn = MySqlConnection.getInstance();
        // Faire la string req 
        String req = "UPDATE `events` SET `nbParticipantsTotal` = ? WHERE `events`.`idEvent` = ? ";
        // Faire la prerequette 
        try {
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, nbParticipantsTotal);
            pstmt.setInt(2, idEvent);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode pour chercher une auberge par id, Qui servira à afficher dans la
     * vue le détail d'une auberge.
     *
     * @param idEvent
     * @return auberge
     */
    public static Event getEventById(int idEvent) {
        //Faire un buffer event
        Event event = new Event();
        event.setIdEvent(idEvent);

        //Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        //Ecrire la requete et la stocker dans un string :
        String req = "SELECT * FROM events WHERE idEvent=?";
        try {
            //Faire une pré- requete :
            PreparedStatement pstmt = conn.prepareStatement(req);
            //Remplacer les ?
            pstmt.setInt(1, idEvent);
            try {
                //Executer la prerequete.               
                ResultSet rs = pstmt.executeQuery();
                rs = pstmt.getResultSet();
                //Si il existe une premiere ligne + je me possitionne dessus
                if (rs.first()) {
                    event.setName(rs.getString("name"));
                    event.setDate(rs.getString("date"));
                    event.setNbParticipantsTotal(rs.getInt("nbParticipantsTotal"));
                }
            } catch (SQLException sQLException) {
            }
        } catch (SQLException ex) {
        }
        return event;
    }
}
