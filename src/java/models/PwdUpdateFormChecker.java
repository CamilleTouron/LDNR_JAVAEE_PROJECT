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
 * @author Groupe 1
 */
public class PwdUpdateFormChecker {
    /**
     * Attribus password et nouveau password à vérifier.
     */
    private String pwd, newPwd;
    /**
     * Message d'erreur.
     */
    private Map<String, String> errors;
    /**
     * Récupérer les messages d'erreurs.
     * @return 
     */
    public Map<String, String> getErrors() {
        return errors;
    }
    /**
     * Constructeur du vérificateur.
     * @param request 
     */
    public PwdUpdateFormChecker(HttpServletRequest request) {
        this.pwd = request.getParameter("pwd");
        this.newPwd = request.getParameter("newPwd");
        this.errors = new HashMap<>();
    }
    /**
     * Méthode de vérification du formulaire de changement de mot de passe.
     * @return 
     */
    public boolean isValid() {
        boolean isValid = true;
        if (this.pwd.isEmpty()) {
            isValid = false;
            errors.put("pwdErrorMsg", "Entrez votre mot de passe");
        }
        if (this.pwd.length() < 8) {
            isValid = false;
            errors.put("pwdErrorMsg", "Mot de passe trop court");
        }
        if (this.newPwd.isEmpty()) {
            isValid = false;
            errors.put("newPwdErrorMsg", "Entrez votre nouveau mot de passe");
        }
        if (this.newPwd.length() < 8) {
            isValid = false;
            errors.put("newPwdErrorMsg", "Nouveau mot de passe trop court");
        }
        if (this.newPwd.equals(this.pwd)) {
            isValid = false;
            errors.put("newPwdErrorMsg", "Le nouveau mot de passe doit être différent de l'ancien mot de passe.");
        }
        if (!isValid) {
            errors.put("errorMsgPwd", "Il y a des erreurs dans votre formulaire");
        }
        return isValid;
    }
}
