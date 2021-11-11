/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stag
 */
public class ContactlUpdateFormChecker {
    /**
     * Attribu contact et newContact à vérifier.
     */
    private String contact, newContact;
    /**
     * Attribu de messages d'erreur.
     */
    private Map<String, String> errors;
    /**
     * Méthode de récupération des messages d'erreurs.
     * @return 
     */
    public Map<String, String> getErrors() {
        return errors;
    }
    /**
     * Contructeur du vérificateur.
     * @param request 
     */
    public ContactlUpdateFormChecker(HttpServletRequest request) {
        this.contact = request.getParameter("contact");
        this.newContact = request.getParameter("newContact");
        this.errors = new HashMap<>();
    }
    /**
     * Méthode de vérification du format du contact.
     * @param contact
     * @return true si un des formats valides sinon false.
     */
    public static boolean isContactValid(String contact){
        // Soit c'est un email.
        if (contact.contains("@")){
            return true;
        }//Soit c'est un numéro de téléphone.
        else if (contact.matches("[0-9]+")){
            return true;
        }
        //Retourne faux si c'est auccun des deux.
        return false;
    }
    /**
     * Méthode de vérification de formulaire de changement de contact.
     * @return 
     */
    public boolean isValid() {
        boolean isValid = true;
        if (this.contact.isEmpty()) {
            isValid = false;
            errors.put("contactErrorMsg", "Entrez votre contact.");
        }
        if (!isContactValid(contact)) {
            isValid = false;
            errors.put("contactErrorMsg", "Contact invalide.");
        }
        if (this.newContact.isEmpty()) {
            isValid = false;
            errors.put("newContactErrorMsg", "Entrez votre nouveau contact.");
        }
        if (!isContactValid(newContact)) {
            isValid = false;
            errors.put("newContactErrorMsg", "Nouveau contact invalide.");
        }
        if (this.newContact.equals(this.contact)) {
            isValid = false;
            errors.put("newContactErrorMsg", "Le nouvel contact doit être différent de l'ancien contact.");
        }
        if (!isValid) {
            errors.put("errorMsg", "Il y a des erreurs dans votre formulaire");
        }
        return isValid;
    }
}
