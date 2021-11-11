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
        <title>Auberges</title>
    </head>
    <body>
        <header>
            <%@include file="jspf/header.jspf" %>
        </header>

        <div>
            <p>
                HostalBook est une application destinée à organiser des repas de type "Auberge Espagnole".<br><br>
                Vous trouverez ici toutes les auberges auxquelles vous pouvez participer.
            <p>
                <!-- Liste des auberges --> 
            <div>
                <div class="event">
                    <table>
                        <thead>
                            <tr>
                                <th class="thLAU">Nom</th>
                                <th class="thLAU">Date</th>
                                <th class="thLAU">Nombre de participants</th>
                                <th class="thLAU">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.events}" var="event">    
                                <c:if test="${event.desactiver == false}">
                                    <tr>
                                        <td class="tdLAU">${event.name}</td>
                                        <td class="tdLAU">${event.date}</td>
                                        <td class="tdLAU">${event.nbParticipantsTotal}</td>
                                        <td class="tdLAU">
                                            <button><a href="inscriptionAubergeUser?id=${event.idEvent}">Participer</a></button>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>   
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>  
    </body>
</html>
