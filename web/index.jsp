<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/rsc/css/style.css"/>">
        <title>Accueil</title>
    </head>

    <body>
        <div class="wrapper">
            <div class="wrapper-half left"></div>

            <div class="wrapper-half right">
                <header>
                    <%@include file="/WEB-INF/jspf/header.jspf" %>
                </header>

                <fieldset>
                    <legend>Bienvenue</legend>
                    <h2>Rendez-vous pour un repas  </h2>
                    
                    <p>BookHostal est une application destinée à organiser des repas de type "Auberge Espagnole".<br><br>Lorsque vous vous serez inscrit pour rejoindre notre communauté, il vous suffira de vous connecter afin d'avoir accès à la liste complète des évènements à venir et auxquels vous pourrez participer.<br><br>Une interface intuitive vous permettra ensuite de vous inscrire à l'un des repas proposés, d'indiquer le nombre de convives qui vous accompagneront, et de donner tous les détails sur ce que vous comptez apporter après avoir vu ce qu'apporteront les autres.<br><br>Une fois votre inscription enregistrée, il ne vous restera plus qu'à venir le jour prévu avec votre appétit et votre bonne humeur.
                    </p>

                    <ul class="actions">
                        <li><a href="connect"><input type="submit" value="Se Connecter" /></a></li>
                        <li><a href="subscribe" ><input type="submit" value="S'inscrire" /></a></li>
                    </ul>
                </fieldset>

                <footer>
                    <%@include file="/WEB-INF/jspf/footer.jspf" %>  
                </footer>

            </div>

        </div>
    </body>

</html>