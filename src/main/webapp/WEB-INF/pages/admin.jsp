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
            <form method="get" action="/">
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
                    <td>id</td>
                    <td>text</td>
                    <td>author name</td>
                    <td>author surname</td>
                    <td>crudAction</td>
                    <td></td>
                </tr>
                <c:forEach items="${extend_phrases}" var="phrase">
                    <tr>
                        <td>${phrase.extendId}</td>
                        <td>${phrase.text}</td>
                        <td>${phrase.author.name}</td>
                        <td>${phrase.author.surname}</td>
                        <td>${phrase.crudAction}</td>
                        <td>
                            <form method="get" action="/admin/confirmPhrase">
                                <button class="btn-primary" id="temp_phrase_id" name="temp_phrase_id" value="${phrase.extendId}">show</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <m:display paginParamName="page" totalPages="${pages}"/>
        </div>
        <div class="rightBlock">
            <div class="buttons">
                <form method="get" action="/phrases_all">
                    <input type="submit" value="go to all phrases">
                </form>
                <form method="get" action="/bots_all">
                    <input type="submit" value="go to all bots">
                </form>
            </div>
        </div>
    </section>
</div>
</body>

</html>