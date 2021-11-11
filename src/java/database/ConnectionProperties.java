/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Groupe 1
 */
public class ConnectionProperties extends Properties {
    /**
     * Attribu host de la propriété de la connexion.
     */
    String host ;
    /**
     * Attribu port de la propriété de la la connexion.
     */
    String port ;
    /**
     * Attribu database de la propriété de la la connexion.
     */
    String database ;
    /**
     * Attribu userName de la propriété de la la connexion.
     */
    String userName ;
    /**
     * Attribu password de la propriété de la la connexion.
     */
    String password ;
    /**
     * Constructeur des propriétés de la connexion.
     */
    public ConnectionProperties() {
        super();
        setProperties();
    }
    /**
     * Fonction qui établie les propriétés de la connexion.
     */
    private void setProperties(){
        //Récupérer via un flux les propriété sur un fichier de propriétés.
        try {
            this.load(ConnectionProperties.class.getResourceAsStream("bookhostage.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ConnectionProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        host = this.getProperty("host");
        port = this.getProperty("port");
        database = this.getProperty("database");
        userName = this.getProperty("user");
        password = this.getProperty("password");
    }
    /**
     * Récupérer l'attribu host des propriétés de la connexion.
     * @return host
     */
    public String getHost() {
        return host;
    }
    /**
     * Récupérer l'attribu port des propriétés de la connexion.
     * @return port
     */
    public String getPort() {
        return port;
    }
    /**
     * Récupérer l'attribu database des propriétés de la connexion.
     * @return database
     */
    public String getDatabase() {
        return database;
    }
    /**
     * Récupérer l'attribu username des propriétés de la connexion.
     * @return username
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Récupérer l'attribu password des propriétés de la connexion.
     * @return password
     */
    public String getPassword() {
        return password;
    }   
}
