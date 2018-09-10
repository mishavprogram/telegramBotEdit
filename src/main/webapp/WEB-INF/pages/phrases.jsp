<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="m" uri="/WEB-INF/taglib/Paginator.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <meta charset="utf-8" />
    <title>Admin page</title>
    
	<link rel="stylesheet" href="/bootstrap/bootstrap.min.css" />
	<script src="/bootstrap/jquery-3.2.1.min.js"></script>
    <script src="/bootstrap/bootstrap.js"></script>
    <script src="/js/selectRow.js"></script>
    
    <link rel="stylesheet" type="text/css" media="screen" href="/css/user.css" />
</head>

<body>
<div class="container">
    <header>
        <h1 class="greeting"><fmt:message key="hello" bundle="${msg}"/>, <c:out value="${sessionScope.get(\"userName\")} ${sessionScope.get(\"userSurname\")}!"/></h1>
        <nav class="navigation">
            <form method="get" action="/user">
                <input type="submit" value="<fmt:message key="home" bundle="${msg}"/>">
            </form>
            <form method="get" action="/logout">
                <input type="submit" value="<fmt:message key="logout" bundle="${msg}"/>">
            </form>
        </nav>
        <select class="language">
            <option>Українська</option>
            <option>Російська</option>
            <option selected>English</option>
        </select>
    </header>

    <section class="mainSection">
        <div class="tableBlock">
            <table class="table">
                <tr>
                    <td>text</td>
                    <td>author name</td>
                    <td>author surname</td>
                </tr>
                <c:forEach items="${phrases}" var="phrase">
                    <tr>
                        <td>${phrase.text}</td>
                        <td>${phrase.author.name}</td>
                        <td>${phrase.author.surname}</td>
                        <td></td>
                        <td><form method="get" action="/"><button class="btn-primary">show</button></form></td>
                    </tr>
                </c:forEach>
            </table>
            <m:display paginParamName="page" totalPages="${pages}"/>
        </div>
        <div class="rightBlock">

        </div>
    </section>
</div>
</body>

</html>