/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groupe 1
 */
public class Participant {
    /**
     * Attribu user d'un participant.
     */
    private User user;
    /**
     * Attribu nombre de convives d'un participant.
     */
    private int convives;
    /**
     * Attribu plats d'un participant.
     */
    Dish boisson, plat, entree, dessert;
    /**
     * Attribu comment d'un participant.
     */
    private String comment;
    
    /**
     * Constructeur d'un participant.
     * @param user 
     */
    public Participant(User user) {
        this.user = user;
    }
    /**
     * Méthode d'attribution de valeur de l'attribu user de participant.
     * @param user 
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Méthode d'attribution de valeur de l'attribu du plat boisson de participant. 
     * @param boisson
     */
    public void setBoisson(Dish boisson) {
        this.boisson = boisson;
    }
    /**
     * Méthode d'attribution de valeur de l'attribu plat plat de participant. 
     * @param plat
     */
    public void setPlat(Dish plat) {
        this.plat = plat;
    }
    /**
     * Méthode d'attribution de valeur de l'attribu plat entree de participant. 
     * @param entree
     */
    public void setEntree(Dish entree) {
        this.entree = entree;
    }
    /**
     * Méthode d'attribution de valeur de l'attribu plat dessert de participant. 
     * @param dessert
     */
    public void setDessert(Dish dessert) {
        this.dessert = dessert;
    }
    /**
     * Méthode de récupération de l'attribu user de participant.
     * @return 
     */
    public User getUser() {
        return user;
    }
    /**
     * Méthode de récupération de l'attribu convives de participant.
     * @return 
     */
    public int getConvives() {
        return convives;
    }
    /**
     * Méthode de récupération de l'attribu comment de participant.
     * @return 
     */
    public String getComment() {
        return comment;
    }
    /**
     * Méthode d'attribution de valeur de l'attribu convives de participant. 
     * @param convives
     */
    public void setConvives(int convives) {
        this.convives = convives;
    }
    /**
     * Méthode d'attribution de valeur de l'attribu comment de participant. 
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * Méthode de récupération de l'attribu plat boisson de participant.
     * @return 
     */
    public Dish getBoisson() {
        return boisson;
    }
    /**
     * Méthode de récupération de l'attribu plat plat de participant.
     * @return 
     */
    public Dish getPlat() {
        return plat;
    }
    /**
     * Méthode de récupération de l'attribu plat entree de participant.
     * @return 
     */
    public Dish getEntree() {
        return entree;
    }
    /**
     * Méthode de récupération de l'attribu plat dessert de participant.
     * @return 
     */
    public Dish getDessert() {
        return dessert;
    }


    /**
     * Méthode qui ajoute un participants à la BDD.
     *
     * @param p
     * @param idEvent
     */
    public static void addParticipant(Participant p, int idEvent) {
        //TODO 2
        //Verifier si le prticipant est déjà ajouter avec exist()
        //if (p.exist(p.getUser(),idEvent)) 
        if (!p.getUser().exist(idEvent)) {
            Connection conn = MySqlConnection.getInstance();
            //Ecrire la requete dans un string avec INSERT voir requete sur le jdb
            String req = "INSERT INTO participants VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(req);
                //Remplacer les ? par les attribus du p
                pstmt.setInt(1, idEvent);
                pstmt.setInt(2, p.getUser().getId());
                pstmt.setInt(3, p.convives);
                pstmt.setString(4, p.entree.getName());
                pstmt.setInt(5, p.entree.getQty());
                pstmt.setString(6, p.plat.getName());
                pstmt.setInt(7, p.plat.getQty());
                pstmt.setString(8, p.dessert.getName());
                pstmt.setInt(9, p.dessert.getQty());
                pstmt.setString(10, p.boisson.getName());
                pstmt.setInt(11, p.boisson.getQty());
                pstmt.setString(12, p.comment);
                int qty = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Participant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Méthode qui modifie un participant à partir d'un participant p.
     *
     * @param p
     * @param idEvent
     */
    public static void modifyParticipant(Participant p, int idEvent) {
        //TODO 4 ATTENTION A MODIFIER ET NON RAJOUTER

        //Faire la connexion
        Connection conn = MySqlConnection.getInstance();

        //Ecrire la requete dans un string voir requete sur le jdb
        String req = "UPDATE `participants` SET `convives` =  ? , `entree` =  ? , `partEntree` =  ? , `plat` =  ?  , `partPlat` = ?, `dessert` =  ?  , `partDessert` =  ? , `boisson` =  ? , `partBoisson` =  ? , `commentaire` =  ?  WHERE `idEvent` =  ? AND `idUser` =  ? ";

        try {
            PreparedStatement pstmt = conn.prepareStatement(req);
            //Remplacer les ? par les attribus du participant

            pstmt.setInt(1, p.convives);
            pstmt.setString(2, p.entree.getName());
            pstmt.setInt(3, p.entree.getQty());
            pstmt.setString(4, p.plat.getName());
            pstmt.setInt(5, p.plat.getQty());
            pstmt.setString(6, p.dessert.getName());
            pstmt.setInt(7, p.dessert.getQty());
            pstmt.setString(8, p.boisson.getName());
            pstmt.setInt(9, p.boisson.getQty());
            pstmt.setString(10, p.comment);
            pstmt.setInt(11, idEvent);
            pstmt.setInt(12, p.getUser().getId());

            //Executer la prerequette
            try {
                int qty = pstmt.executeUpdate();
            } catch (SQLException SqlException) {
                Logger.getLogger(Participant.class.getName()).log(Level.SEVERE, null, SqlException);
            }

            //JETER DES EXCEPTIONS SI CELA NE FONCTIONNE PAS
        } catch (SQLException ex) {
            Logger.getLogger(Participant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode qui supprime un participant à partir d'un id.
     *
     * @param idEvent
     * @param idUser
     */
    public static void deleteParticipant(int idEvent, int idUser) throws Exception {
        //Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        //Ecrire la requete dans un string voir requete sur le jdb
        String req = "DELETE FROM participants WHERE idEvent=? AND idUser=? ";
        try {
            //Faire la prerequete
            PreparedStatement pstmt = conn.prepareStatement(req);
            //Remplacer les ? par les attribus du p
            pstmt.setInt(1, idEvent);
            pstmt.setInt(2, idUser);
            //Executer la prerequette
            int qty = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Impossible de supprimer le participant.");
        }
    }

}
