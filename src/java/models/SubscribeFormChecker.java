/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Groupe 1
 */
public class SubscribeFormChecker {
    /**
     * Attribu à vérifier.
     */
    private String alias, contact, pwd, confirm;
    /**
     * Messages d'erreurs.
     */
    private Map<String, String> errors;
    /**
     * Requete.
     */
    private HttpServletRequest request;

    /**
     * Constructeur
     *
     * @param request La requête issue du formulaire
     */
    public SubscribeFormChecker(HttpServletRequest request) {
        // Récupération des données du formulaire
        // et affichage pour debug
        this.request = request;
        alias = request.getParameter("alias");
        contact = request.getParameter("contact");
        pwd = request.getParameter("pwd");
        confirm = request.getParameter("confirm");
        errors = new HashMap<>();
    }
    /**
     * Méthode de vérification du format du contact.
     * @param contact
     * @return 
     */
    public static boolean isContactValid(String contact){
        //Vérification format pour un email.
        if (contact.contains("@")){
            return true;
        }//Vérification format pour un numéro.
        else if (contact.matches("[0-9]+")){
            return true;
        }
        return false;
    }

    /**
     * Vérifie les données du formulaire d'inscription.
     *
     * @return true si formulaire valide, false sinon
     */
    public boolean checkForm() {
        // Vérification des données du formulaire
        boolean isFormOk = true;
        // Le pseudo ne doit pas être vide
        if (alias.isEmpty()) {
            isFormOk = false;
            errors.put("aliasErrorMsg", "Entrez un pseudo");
        } else if (alias.length() < 3) {
            isFormOk = false;
            errors.put("aliasErrorMsg", "Pseudo trop court");
        }
        // L'adresse e-mail est valide
        if (!isContactValid(contact)) {
            isFormOk = false;
            errors.put("contactErrorMsg", "Ceci n'est pas un contact valide");
        }
        // Le mot de passe doit faire au moins 8 caractères
        if (pwd.length() < 8) {
            isFormOk = false;
            errors.put("pwdErrorMsg", "Mot de passe trop court");
        }
        // Le mot de confirmation doit être égal au mot de passe
        if (!pwd.equals(confirm)) {
            isFormOk = false;
            errors.put("confirmErrorMsg", "Les mots de passe ne correspondent pas");
        }
        if (!isFormOk) {
            errors.put("errorMsg", "Il y a des erreurs dans votre formulaire");
            request.setAttribute("errors", errors);
        }
        return isFormOk;
    }
}
