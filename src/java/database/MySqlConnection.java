/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groupe 1
 */
public final class MySqlConnection {
    /**
     * Attribu de instance de la connexion.
     */
    private static Connection instance;
    /**
     * Constructeur qui ne sert à rien.
     */
    private MySqlConnection() {

    }

    /**
     * Retourne une connexion a la base de donnée ou null.
     *
     * @return l'instance
     */
    public static Connection getInstance() {
        //Si elle n'existe pas je la créer :
        ConnectionProperties prop=new ConnectionProperties();
        if (instance == null) {
            String url = "jdbc:mysql://" + prop.host + ":" + prop.port + "/" + prop.database;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                instance = DriverManager.getConnection(url, prop.userName, prop.password);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);//Présicer dans la doc qu'est ce que l'erreur 1.
            }
        }
        //Je la retourne :
        return instance;
    }
}
