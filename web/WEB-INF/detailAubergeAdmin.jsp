<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${auberge.event.name}</title>
    </head>
    <body>
        <div>        
            <%@include file="jspf/header.jspf" %>
        </div>

        <div>
            <div>
                <p>${auberge.event.name}</p>
                <p>${auberge.event.date}</p>
            </div>
            <div>
                <div class="event">
                    <table>
                        <thead>
                            <tr>
                                <th class="thTailleGrande">Pseudo</th>
                                <th class="thTaillePetite">Convives</th>
                                <th class="thTailleGrande">Entree</th>
                                <th class="thTaillePetite">Part</th>
                                <th class="thTailleGrande">Plat</th>
                                <th class="thTaillePetite">Part</th>
                                <th class="thTailleGrande">Dessert</th>
                                <th class="thTaillePetite">Part</th>
                                <th class="thTailleGrande">Boisson</th>
                                <th class="thTaillePetite">Part</th>
                                <th class="thTailleGrande">Commentaire</th>
                                <th class="thTailleGrande">Contact</th>
                            </tr>
                        </thead>
                        <tbody class="listeEvent">
                            <c:forEach items ="${auberge.participants}" var="participant">
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
                                    <td>${participant.user.contact}</td>
                                </tr>
                            </c:forEach>  
                            <tr>
                                <th class="thTailleGrande" colspan="2">Nombres de participant total :</th>
                                <th class="thTailleGrande" colspan="2">Nombres de part d'entrée total :</th>
                                <th class="thTailleGrande" colspan="2">Nombres de part de plat total :</th>
                                <th class="thTailleGrande" colspan="2">Nombres de part de dessert total :</th>
                                <th class="thTailleGrande" colspan="2">Nombres de part de boisson total :</th>
                                <th class="thTailleGrande" colspan="2"></th>
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
            </div>

        </div>
    </body>
</html>
