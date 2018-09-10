<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8" />
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" media="screen" href="../../css/main.css" />
    
	<link rel="stylesheet" href="../../bootstrap/bootstrap.min.css" />
	<script src="../../bootstrap/bootstrap.js"></script>
</head>
<body>
    <div class="container">
        <form method="post" action="/register">
            <h1 class="auth">Registration</h1>
            <div class="field">
                <label for="firstName">First name: </label>
                <input id="firstName" name="userName" type="text" required>
            </div>
            <div class="field">
                <label for="secondName">Second name: </label>
                <input id="secondName" name="userSurname" type="text" required>
            </div>
            <div class="field">
                <label for="email">E-mail: </label>
                <input id="email" name="login_email" type="email" required>
            </div>
            <div class="field">
                <label for="password">Password: </label>
                <input id="password" name="login_password" type="password" required>
            </div>
            <input type="submit" value="Enter">
        </form>
    </div>
    <div class="dropdown choose-country">
        <c:forEach items="${SUPPORTED_LOCALES}" var="value">
            <c:if test="${value eq sessionScope['locale']}">
                <a class="#" data-toggle="dropdown" href="#"> ${value.language}</a>
            </c:if>
        </c:forEach>
        <ul class="dropdown-menu" role="menu">
            <c:forEach items="${SUPPORTED_LOCALES}" var="value">
                <li role="menuitem"><a href="?lang=${value.language}"> ${value.language}</a></li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>