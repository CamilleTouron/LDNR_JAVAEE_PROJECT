/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Groupe 1
 */
public class Message {
    /**
     * Attribu clé d'un message.
     */
    private final String key;
    /**
     * Attribu valeur d'un message.
     */
    private final String value;
    /**
     * Constructeur d'un message.
     * @param key
     * @param value 
     */
    public Message(String key, String value) {
        this.key = key;
        this.value = value;
    }
    /**
     * Méthode de récupération de la clé d'un message.
     * @return 
     */
    public String getKey() {
        return key;
    }
    /**
     * Méthode de récupération de la valeur d'un message.
     * @return 
     */
    public String getValue() {
        return value;
    }
}
