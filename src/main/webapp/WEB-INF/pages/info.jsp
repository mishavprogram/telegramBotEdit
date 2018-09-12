<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Success</title>
	<link rel="stylesheet" href="../../bootstrap/bootstrap.min.css" />
	<script src="../../bootstrap/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="../../css/main.css" />
</head>
<body>

    <div class="container">
        <div>
            <h1 class="greeting"><c:out value="${sessionScope.get(\"infoTitle\")} ${sessionScope.get(\"info_message\")}!"/></h1>
            <h1 class="greeting"><c:out value="${sessionScope.get(\"userName\")} ${sessionScope.get(\"userSurname\")}!"/></h1>
            <form>
            <input type="submit" value="go back" onclick="history.back()"/>
            </form>
            <form method="get" action="/">
            <input type="submit" value="go home"/>
            </form>

        </div>
        <select class="language">
            <option>Українська</option>
            <option>Російська</option>
            <option selected>English</option>
        </select>
    </div>    
</body>
</html>