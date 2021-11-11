/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Groupe 1
 */
public class FlashMessage implements Iterator<Message>{
    /**
     * Attribu liste de message de FlashMessage.
     */
    ArrayList<Message> messages = new ArrayList<>();
    /**
     * Méthode qui rajoute un message à la liste des messages.
     * @param success
     * @param message 
     */
    public void add(String success,String message){
        messages.add(new Message(success,message));
    }
    /**
     * Méthode de récupération de la liste des messages.
     * @return 
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }
    /**
     * Méthode qui affiche le prochain message.
     * @return 
     */
    @Override
    public boolean hasNext() {
        return messages.size()>0;
    }
    /**
     * Méthode qui supprime le message afficher.
     * @return 
     */
    @Override
    public Message next() {
        return messages.remove(0);
    }
    
}
