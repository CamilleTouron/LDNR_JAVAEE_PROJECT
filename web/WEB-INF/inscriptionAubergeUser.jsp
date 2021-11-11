<%-- 
    Document   : listAuberge
    Created on : 4 nov. 2021, 11:21:09
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/rsc/css/inside.css"/>">
        <title>Auberges</title>
    </head>
    <body>
        <%@include file="jspf/header.jspf" %>
        <div>
            <div>
                <!-- Un évènement --> 
                <div>
                    <div>
                        <h1>${auberge.event.name}</h1>
                        <h2>${auberge.event.date}</h2>
                    </div>
                    <div>
                        <!-- Récupération de ce qu'amènent les autres -->
                        <table>
                            <thead>
                                <tr>
                                    <th>Nom :</th>
                                    <th>Nombre de convives :</th>
                                    <th>Nombre d'entrées :</th>
                                    <th>Nombre de parts :</th>
                                    <th>Nombre de plats :</th>
                                    <th>Nombre de parts :</th>
                                    <th>Nombre de desserts :</th>
                                    <th>Nombre de parts :</th>
                                    <th>Nombre de boisson :</th>
                                    <th>Nombre de parts :</th>
                                    <th>Commentaires :</th>
                                </tr>

                            </thead>
                            <tbody>
                                <c:forEach items="${auberge.participants}" var="participant">
                                    <tr>
                                        <td>${participant.user.alias}</td>
                                        <td>${participant.convives}</td>
                                        <td>${participant.entree.name}</td>
                                        <td>${participant.entree.qty}</td>
                                        <td>${participant.plat.name}</td>
                                        <td>${participant.plat.qty}</td>
                                        <td>${participant.dessert.name}</td>
                                        <td>${participant.dessert.qty}</td>
                                        <td>${participant.boisson.name}</td>
                                        <td>${participant.boisson.qty}</td>
                                        <td>${participant.comment}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <th colspan="2">Nombres de participant total :</th>
                                    <th colspan="2">Nombres de part d'entrée total :</th>
                                    <th colspan="2">Nombres de part de plat total :</th>
                                    <th colspan="2">Nombres de part de dessert total :</th>
                                    <th colspan="2">Nombres de part de boisson total :</th>
                                    <th></th>
                                </tr>
                                <tr>
                                    <td colspan="2">${auberge.nbConvives}</td>
                                    <td colspan="2">${auberge.partEntree}</td>
                                    <td colspan="2">${auberge.partPlat}</td>
                                    <td colspan="2">${auberge.partDessert}</td>
                                    <td colspan="2">${auberge.partBoisson}</td>
                                    <td colspan="2"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <div>
                            <c:choose>
                                <c:when test="${sessionScope.user.exist(auberge.event.idEvent) == false}">
                                    <fieldset class="FieldsetIAU">
                                        <legend>Participer</legend>
                                        <div>
                                            <form action="<c:url value="/Participer"/>" method="POST">
                                                <p>Bienvenue ${sessionScope.user.alias}</p>

                                                <div>
                                                    <label for="convives">Nombre d'invités ? :</label>
                                                    <input type='number' name="convives" id="convives" min="1" required>
                                                </div>

                                                <div>
                                                    <label for="entree">Quelle entrée ? :</label>
                                                    <input type='text' name="entree" id="entree" required>
                                                </div>
                                                <div>
                                                    <label for="partEntree">Nombre de part.s (entrée) ? :</label>
                                                    <input type='number' name="partEntree" id="partEntree" required>
                                                </div>
                                                <div>
                                                    <label for="plat">Quel plat ? :</label>
                                                    <input type='text' name="plat" id="plat" required>
                                                </div>
                                                <div>
                                                    <label for="partPlat">Nombre de part.s (plat) ? :</label>
                                                    <input type='number' name="partPlat" id="partPlat"required>
                                                </div>
                                                <div>
                                                    <label for="dessert">Quel dessert ? :</label>
                                                    <input type='text' name="dessert" id="dessert" required>
                                                </div>
                                                <div>
                                                    <label for="partDessert">Nombre de part.s (dessert) ? :</label>
                                                    <input type='number' name="partDessert" id="partDessert" required>
                                                </div>
                                                <div>
                                                    <label for="boisson">Quelle boisson ? :</label>
                                                    <input type='text' name="boisson" id="boisson" required>
                                                </div>
                                                <div>
                                                    <label for="partBoisson">Nombre de part.s (boisson) ? :</label>
                                                    <input type='number' name="partBoisson" id="partBoisson" required>
                                                </div>
                                                <div>
                                                    <label for="commentaire">Commentaire.s ? :</label>
                                                    <textarea rows="3" cols="28" name='commentaire' id="commentaire" placeholder="Commentaires...." required></textarea>
                                                </div>
                                                <div>
                                                    <button type="submit">Participer</button>
                                                    <button type="reset">Annuler</button>
                                                </div>
                                            </form>
                                        </div>
                                    </fieldset>
                                </c:when>
                                <c:otherwise>
                                    <fieldset>
                                        <legend>Modifier</legend>
                                        <div>
                                            <form action="<c:url value="/modifier"/>" method="POST">
                                                <p>Bienvenue ${sessionScope.user.alias}</p>

                                                <div>
                                                    <label for="convives">Nombre d'invités ? :</label>
                                                    <input type='number' name="convives" id="convives" min="1" required>
                                                </div>

                                                <div>
                                                    <label for="entree">Quelle entrée ? :</label>
                                                    <input type='text' name="entree" id="entree" required>
                                                </div>
                                                <div>
                                                    <label for="partEntree">Nombre de part.s (entrée) ? :</label>
                                                    <input type='number' name="partEntree" id="partEntree" required>
                                                </div>
                                                <div>
                                                    <label for="plat">Quel plat ? :</label>
                                                    <input type='text' name="plat" id="plat">
                                                </div>
                                                <div>
                                                    <label for="partPlat">Nombre de part.s (plat) ? :</label>
                                                    <input type='number' name="partPlat" id="partPlat" required>
                                                </div>
                                                <div>
                                                    <label for="dessert">Quel dessert ? :</label>
                                                    <input type='text' name="dessert" id="dessert" required>
                                                </div>
                                                <div>
                                                    <label for="partDessert">Nombre de part.s (dessert) ? :</label>
                                                    <input type='number' name="partDessert" id="partDessert" required>
                                                </div>
                                                <div>
                                                    <label for="boisson">Quelle boisson ? :</label>
                                                    <input type='text' name="boisson" id="boisson" required>
                                                </div>
                                                <div>
                                                    <label for="partBoisson">Nombre de part.s (boisson) ? :</label>
                                                    <input type='number' name="partBoisson" id="partBoisson" required>
                                                </div>
                                                <div>
                                                    <label for="commentaire">Commentaire.s :</label>
                                                    <textarea rows="5" cols="30" name='commentaire' id="commentaire" placeholder="Commentaires...." required></textarea>
                                                </div>
                                                <div>
                                                    <button type="submit">Modifier</button>

                                                    <button type="reset">Annuler</button>
                                                </div>
                                            </form>
                                            <button><a href="Remove">Retirer ma participation</a></button>
                                        </div>
                                    </fieldset>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <%@include file="/WEB-INF/jspf/footer.jspf" %>  
    </body>
</html>