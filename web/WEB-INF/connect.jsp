<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/rsc/css/style.css"/>">
        <title>Connexion</title>
    </head>

    <body>
        <div class="wrapper">
            <div class="wrapper-half left"></div>

            <div class="wrapper-half right">
                <header>
                    <%@include file="/WEB-INF/jspf/header.jspf" %>
                     <c:if test="${!empty requestScope.errorMsg}">
                    <div class="flash nok">${requestScope.errorMsg}</div>
                </c:if>
                <c:if test="${!empty requestScope.errors.errorMsg}">
                    <div class="flash nok">${requestScope.errors.errorMsg}</div>
                </c:if>
                </header>
               

                <fieldset>
                    <legend>Connexion</legend>
                    <h2>Rendez-vous pour un repas  </h2>

                    <form method="post" action="<c:url value="/connect"/>">
                        <div class="field">
                            <label for="alias">Votre pseudo</label>
                                <input id="alias" name="alias" type="text">
                                <c:if test="${!empty requestScope.errors.aliasErrorMsg}">
                                    <span class="error">${requestScope.errors.aliasErrorMsg}</span>
                                </c:if>
                        </div>

                        <div class="field">
                            <label for="pwd">Votre mot de passe</label>
                                <input id="pwd" name="pwd" type="password">
                                <c:if test="${!empty requestScope.errors.pwdErrorMsg}">
                                    <span class="error">${requestScope.errors.pwdErrorMsg}</span>
                                </c:if>
                        </div>
                        
                        <ul class="actions">
                            <li><input type="submit" value="Se connecter" class="special" /></li>
                            <li><input type="reset" value="Annuler" /></li>
                        </ul>
                    </form>



                </fieldset>

                <footer>
                    <%@include file="/WEB-INF/jspf/footer.jspf" %>  
                </footer>

            </div>

        </div>
    </body>

</html>