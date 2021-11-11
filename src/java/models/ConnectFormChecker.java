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
public class ConnectFormChecker {
    /**
     * Attribu alias et password à vérfier.
     */
    private String alias, pwd;
    /**
     * Attribu request à utiliser.
     */
    private HttpServletRequest request;
    /**
     * Message d'erreur à utiliser.
     */
    private Map<String, String> errors;
    /**
     * Constructeurs du vérificateur.
     * @param request 
     */
    public ConnectFormChecker(HttpServletRequest request) {
        this.request = request;
        this.alias = request.getParameter("alias");
        this.pwd = request.getParameter("pwd");
        this.errors = new HashMap<>();
    }
    /**
     * Méthode de vérification.
     * @return 
     */
    public boolean checkForm() {
        boolean isFormOk = true;
        if (this.alias.isEmpty()) {
            isFormOk = false;
            errors.put("aliasErrorMsg", "Entrez votre pseudo");
        }
        if (this.pwd.isEmpty()) {
            isFormOk = false;
            errors.put("pwdErrorMsg", "Entrez votre mot de passe");
        }
        if (this.pwd.length() < 8) {
            isFormOk = false;
            errors.put("pwdErrorMsg", "Mot de passe trop court");
        }
        if(!isFormOk) {
            this.errors.put("errorMsg", "Ce formulaire contient des erreurs");
            this.request.setAttribute("errors", errors);
        }
        return isFormOk;
    }
}
