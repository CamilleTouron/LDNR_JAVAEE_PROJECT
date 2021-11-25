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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groupe 1
 */
public class User {
    /**
     * Attribu is d'un user.
     */
    private int idUser;
    /**
     * Attribu alias d'un user.
     */
    private String alias;
    /**
     * Attribu password d'un user.
     */
    private String password;
    /**
     * Attribu contact d'un user.
     */
    private String contact;
    /**
     * Méthode de d'attribution de la valeur id d'un user.
     * @param idUser 
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    /**
     * Méthode d'attribution de la valeur alias d'un user. 
     * @param alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    /**
     * Méthode de d'attribution de la valeur password d'un user.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Méthode de d'attribution de la valeur contact d'un user.
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
    /**
     * Méthode de récupération de la valeur id d'un user.
     * @return 
     */
    public Integer getId() {
        return idUser;
    }
    /**
     * Méthode de récupération de la valeur alias d'un user.
     * @return 
     */
    public String getAlias() {
        return alias;
    }
    /**
     * Méthode de récupération de la valeur password d'un user.
     * @return 
     */
    public String getPassword() {
        return password;
    }
    /**
     * Méthode de récupération de la valeur contact d'un user.
     * @return 
     */
    public String getContact() {
        return contact;
    }
    /**
     * Méthode de création d'un event.
     * @param alias
     * @param pwd
     * @param contact
     * @return
     * @throws Exception 
     */
    public static User createUser(String alias, String pwd, String contact) throws Exception {
        User user = null;
        Connection conn = MySqlConnection.getInstance();
        String req = "INSERT INTO users (alias,password,contact) VALUES (?,?,?)";

        // Outil de hashage
        PasswordAuthentication pa = new PasswordAuthentication();
        // Mot de passe initial & hasher en DB 
        String passwordToHash, hashedPassword;
        // Transfert de variable pour comprendre avec le code du prof
        passwordToHash = pwd;
        // Hashage du mot de passe
        hashedPassword = pa.hash(passwordToHash.toCharArray());

        try {
            PreparedStatement pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, alias);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, contact);

            try {
                int qty = pstmt.executeUpdate();
            } catch (SQLException sQLException) {
            }

            user = new User();
            user.alias = alias;
            user.password = hashedPassword;
            user.contact = contact;
            ResultSet keys = pstmt.getGeneratedKeys();//Table d'id.
            if (keys.first()) {//théoriquement qu'une ligne.
                user.idUser = keys.getInt(1);
            }
        } catch (SQLException ex) {
            throw new Exception("Cet utilisateur existe déjà.");
        }
        return user;
    }

    /**
     * Méthode de vérification de l'existence de l'utilisateur.
     *
     * @param al Le pseudo du user.
     * @param pwd Le mot de passe du user.
     * @return Un user complet (avec id).
     */
    public static User isValid(String al, String pwd) {

        User user = null;
        Connection conn = MySqlConnection.getInstance();
        String req = "SELECT * FROM users WHERE `alias` = ?";
        System.out.println(al);
        // Un outil de hashage
        PasswordAuthentication pa = new PasswordAuthentication();
        // Un booléen pour recevoir le résultat de la comparaison
        boolean isOk = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement(req,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, al);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                // Comparaison des deux mot de passe
                isOk = pa.authenticate(pwd.toCharArray(), rs.getString("password"));

                if (isOk) {
                    user = new User();
                    user.alias = al;
                    user.password = rs.getString("password");
                    user.contact = rs.getString("contact");
                    user.idUser = rs.getInt("idUser");
                } else {
                    System.out.println("COUCOU CAMILLE ET ALAN");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    /**
     * Méthode de modification mot de passe.
     * @param newPwd
     * @param pwd
     * @throws Exception 
     */
    public void updatePwd(String newPwd, String pwd) throws Exception {
        // Notre outil d'hashage
        PasswordAuthentication pa = new PasswordAuthentication();
        // Mot de passe initial & hasher en DB 
        String hashedNewPassword;
        // Verificateur boolean par défaut sur false
        boolean isOk = false;
        // Comparaison des mdp
        isOk = pa.authenticate(pwd.toCharArray(), this.password);

        // Hashage du mot de passe
        hashedNewPassword = pa.hash(newPwd.toCharArray());

        if (isOk == false) {
            throw new Exception("Votre mot de passe est faux. Réessayer.");
        }
        Connection conn = MySqlConnection.getInstance();
        String req = "UPDATE `users` SET `password` = ? WHERE `users`.`alias` = ? ";

        try {
            PreparedStatement pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, hashedNewPassword);
            pstmt.setString(2, alias);

            try {
                int qty = pstmt.executeUpdate();
            } catch (SQLException sQLException) {
            }
            this.password = hashedNewPassword;
        } catch (SQLException ex) {
            throw new Exception("Impossible de changer le mot de passe.");
        }
    }
    /**
     * Méthode de modification du mot de passe.
     * @param newContact
     * @param contact
     * @throws Exception 
     */
    public void updateContact(String newContact, String contact) throws Exception {
        if (!this.contact.equals(contact)) {
            throw new Exception("Votre contact est faux. Réessayer.");
        }
        Connection conn = MySqlConnection.getInstance();
        String req = "UPDATE `users` SET `contact` = ? WHERE `users`.`alias` = ? ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newContact);
            pstmt.setString(2, alias);
            try {
                int qty = pstmt.executeUpdate();
            } catch (SQLException sQLException) {
            }
            this.contact = newContact;
        } catch (SQLException ex) {
            throw new Exception("Impossible de changer le contact.");
        }
    }
    /**
     * Méthode qui détermine si l'user en cours est l'admin.
     * @return true si c'est l'admin et false si ce n'est pas le cas.
     */
    public boolean isAdmin(){
        //L'id de l'admin est 1,on vérifie si c'est le cas pour le user en cours
        if(this.idUser == 1){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Méthode qui cherche si un participants est deja dans la BDD. On comparera
     * les pseudo de user.
     *
     * @param idEvent
     * @return
     */
    public boolean exist(int idEvent) {
        //TODO 3
        boolean exist;
        //Faire la connexion
        Connection conn = MySqlConnection.getInstance();
        //Ecrire la requete dans un string , on cherche si l'id du user est deja 
        //dans la table participants/auberge.
        String req = "SELECT * FROM participants WHERE idUser = ? AND idEvent = ?";
        
        //Faire la prerequete
        //Remplacer les ? par l'attribu id du user passer en parametre
        //Executer la prerequette
        //Penser au try catch si ca marche pas
        try {
            PreparedStatement pstmt = conn.prepareStatement(req,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, this.idUser);
            pstmt.setInt(2, idEvent);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JETER DES EXCEPTIONS SI CELA NE FONCTIONNE PAS
        return false;
    }

}
