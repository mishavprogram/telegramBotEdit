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
    <title>Authorisation</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />

    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css" />
    <script src="/bootstrap/jquery-3.2.1.min.js"></script>
    <script src="/bootstrap/bootstrap.js"></script>
</head>
<body>

    <div class="container">

        <form method="get" action="/login">
            <input type="submit" value="login">
        </form>

        <form method="get" action="/register">
            <input type="submit" value="register">
        </form>

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
    </div>    
</body>
</html>