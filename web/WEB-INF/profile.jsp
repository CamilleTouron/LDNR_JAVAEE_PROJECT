<%-- 
    Document   : profile
    Created on : 28 oct. 2021, 10:08:15
    Author     : stag
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/rsc/css/index.css"/>">
        <title>Profile</title>
    </head>
    <body>
        <header>
            <%@include file="jspf/header.jspf" %>
        </header>
        
        <h1>Votre compte</h1>
        <div>
            Votre pseudo : ${sessionScope.user.alias}
        </div>
        <div>
            Votre contact : ${sessionScope.user.contact}
        </div>
        <div><br>
            <form action="<c:url value="/ProfileChangePwd"/>" method="POST">
                <fieldset class="FSProfile">
                    <legend>Modification du mot de passe</legend>
                    <div>
                        <label for="pwd">Votre ancien mot de passe.</label>
                        <input id="pwd" name="pwd" type="password" value="<c:out value='${param.pwd}'/>" required>
                        <c:if test="${!empty requestScope.errors.pwdErrorMsg}">
                            <span class="error">${requestScope.errors.pwdErrorMsg}</span>
                        </c:if>
                    </div>
                    <div>
                        <label for="newPwd">Votre nouveau mot de passe.</label>
                        <input id="newPwd" name="newPwd" type="password" value="<c:out value='${param.newPwd}'/>" required>
                        <c:if test="${!empty requestScope.errors.newPwdErrorMsg}">
                            <span class="error">${requestScope.errors.newPwdErrorMsg}</span>
                        </c:if>
                    </div>
                    <div>
                        <input type="submit" value="changer">
                        <input type="reset" value="annuler"> 
                        <c:if test="${!empty requestScope.errors.errorMsgPwd}">
                            <span class="error">${requestScope.errors.errorMsgPwd}</span>
                        </c:if>
                        <c:if test="${!empty requestScope.error}">
                            <span class="error">${requestScope.error}</span>
                        </c:if>
                    </div>
                </fieldset>
            </form>
        </div>
        <div><br>
            <form action="<c:url value="/ProfileChangeContact"/>" method="POST">
                <fieldset class="FSProfile">
                    <legend>Modification du téléphone ou mail</legend>
                    <div>
                        <label for="contact">Votre ancien téléphone ou mail.</label>
                        <input id="contact" name="contact" type="text" value="<c:out value='${param.contact}'/>" required>
                        <c:if test="${!empty requestScope.errors.contactErrorMsg}">
                            <span class="error">${requestScope.errors.contactErrorMsg}</span>
                        </c:if>
                    </div>
                    <div>
                        <label for="newContact">Votre nouveau contact.</label>
                        <input id="newContact" name="newContact" type="text" value="<c:out value='${param.newContact}'/>" required>
                        <c:if test="${!empty requestScope.errors.newContactErrorMsg}">
                            <span class="error">${requestScope.errors.newContactErrorMsg}</span>
                        </c:if>
                    </div>
                    <div>
                        <input type="submit" value="changer">
                        <input type="reset" value="annuler"> 
                        <c:if test="${!empty requestScope.errors.errorMsg}">
                            <span class="error">${requestScope.errors.errorMsg}</span>
                        </c:if>
                        <c:if test="${!empty requestScope.error}">
                            <span class="error">${requestScope.error}</span>
                        </c:if>
                    </div>
                </fieldset>
            </form>
        </div>
        <%@include file="jspf/footer.jspf" %>
    </body>
</html>
