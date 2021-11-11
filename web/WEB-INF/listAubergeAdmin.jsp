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
        <link rel="stylesheet" href="<c:url value="/rsc/css/index.css"/>">
        <title>Auberges</title>
    </head>
    <body>
        <%@include file="jspf/header.jspf" %>
        <div>
            <fieldset class="FieldsetLAA">
                <legend>Ajouter une auberge</legend>
                <form action="<c:url value="/addEvent"/>" method="POST">
                    <div>
                        <label for="name">Nom</label>
                        <input type="text" name="name" id="name" required>
                    </div>
                    <div>
                        <label for="date">Date</label>
                        <input type="date" name="date" id="date" required>
                    </div>
                    <div>
                        <button type="submit">Ajouter l'auberge</button>
                    </div>
                </form>
            </fieldset><br>
            <div>
                <!-- Liste des auberges --> 
                <div class="liste">
                    <div class="event">
                        <table>
                            <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Date</th>
                                    <th>Nombre de participants</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.events}" var="event">
                                    <tr>
                                        <td>${event.name}</td>
                                        <td>${event.date}</td>
                                        <td>${event.nbParticipantsTotal}</td>
                                        <td><c:choose>
                                                <c:when test="${event.desactiver == false}">
                                                    <button><a href="Desactivate?id=${event.idEvent}">Desactiver</a></button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button><a href="Activate?id=${event.idEvent}">Activer</a></button>
                                                </c:otherwise>
                                            </c:choose>
                                                <c:if test="${event.nbParticipantsTotal != 0}">
                                                <button><a href="detailaubergeadmin?id=${event.idEvent}">Voir</a></button>

                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>  
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>  
    </body>
</html>
