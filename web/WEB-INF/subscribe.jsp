<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/rsc/css/style.css"/>">
        <title>Inscription</title>
    </head>

    <body>
        <div class="wrapper">
            <div class="wrapper-half left"></div>

            <div class="wrapper-half right">
                <header>
                    <%@include file="jspf/header.jspf" %>
                </header>
                <c:if test="${!empty requestScope.errorMsg}">
                    <div class="flash nok">${requestScope.errorMsg}</div>
                </c:if>
                <c:if test="${!empty requestScope.errors.errorMsg}">
                    <div class="flash nok">${requestScope.errors.errorMsg}</div>
                </c:if>

                <form action="<c:url value="/subscribe"/>" method="POST">
                    <fieldset>
                        <legend>Inscription</legend>
                        <p>Inscrivez-vous !</p>
                        <div class="field">
                            <label for="alias">Pseudo <span class="mandatory">*</span></label>
                            <input type="text" id="alias" name="alias" 
                                   value="<c:out value='${param.alias}'/>" 
                                   size="20" maxlength="200" 
                                   required/>
                            <c:if test="${!empty requestScope.errors.aliasErrorMsg}">
                                <span class="error">${requestScope.errors.aliasErrorMsg}</span>
                            </c:if>
                        </div>
                        <div class="field">
                            <label for="contact">Contact<span class="mandatory">*</span></label>
                            <input type="contact" id="contact" name="contact" 
                                   value="<c:out value='${param.contact}'/>" 
                                   size="20" maxlength="60"
                                   required/>
                            <c:if test="${!empty requestScope.errors.contactErrorMsg}">
                                <span class="error">${requestScope.errors.contactErrorMsg}</span>
                            </c:if>
                        </div>
                        <div class="field">
                            <label for="pwd">Mot de passe <span class="mandatory">*</span></label>
                            <input type="password" id="pwd" name="pwd" 
                                   value="" size="20" maxlength="20" 
                                   required/>
                            <c:if test="${!empty requestScope.errors.pwdErrorMsg}">
                                <span class="error">${requestScope.errors.pwdErrorMsg}</span>
                            </c:if>
                        </div>
                        <div class="field">
                            <label for="confirm">Confirmation <span class="mandatory">*</span></label>
                            <input type="password" id="confirm" name="confirm" 
                                   value="" size="20" maxlength="20" 
                                   required/>
                            <c:if test="${!empty requestScope.errors.confirmErrorMsg}">
                                <span class="error">${requestScope.errors.confirmErrorMsg}</span>
                            </c:if>
                        </div>
                        <p>Les champs marqués d'un <span class="mandatory">*</span> sont obligatoires.</p>

                        <div>
                            <input type="checkbox" id="cdu" name="accpt" checked>

                            <label for="accpt">J'accepte les conditions d'utilisation de ce site.</label>
                        </div> 

                        <ul class="actions">
                            <li><input type="submit" value="Inscription" class="special" /></li>
                            <li><input type="reset" value="Effacer" /></li>
                        </ul>



                    </fieldset>
                </form>

                <footer>
                    <%@include file="/WEB-INF/jspf/footer.jspf" %>  
                </footer>

            </div>

        </div>
    </body>

</html>