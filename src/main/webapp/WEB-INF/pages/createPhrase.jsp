<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Add activity</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
    
	<link rel="stylesheet" href="/bootstrap/bootstrap.min.css" />
	<script src="/bootstrap/bootstrap.js"></script>
</head>
<body>
    <div class="container">

        <form method="post" action="/user/createPhrase">
            <h1 class="auth">Create phrase</h1>
            <div class="field">
                <label for="phrase_text">Phrase text: </label>
                <input id="phrase_text" name="phrase_text" type="text" required>
            </div>
            <div class="field">

                <%--<select id="bot_id" name="bot_id">
                    <c:forEach items="${botList}" var="bot">
                        <option value="${bot}">
                                ${bot}
                        </option>
                    </c:forEach>
                </select>--%>

                <select name='bot'>
                    <option value="${selected}" selected>${selected}</option>
                    <c:forEach items="${botList}" var="bot">
                        <c:if test="${bot != selected}">
                            <option value="${bot}">${bot}</option>
                        </c:if>
                    </c:forEach>
                </select>

            </div>
            <input type="submit" value="create" onclick="">
        </form>
        <aside>
            <div>
                <nav class="navigation">
                    <form method="get" action="/userPage">
                        <input type="submit" value="home">
                    </form>
                    <form method="get" action="/logout">
                        <input type="submit" value="log out">
                    </form>
                </nav>
                <select class="language">
                    <option>Українська</option>
                    <option>Російська</option>
                    <option selected>English</option>
                </select>
            </div>
        </aside>
    </div>    
</body>
</html>