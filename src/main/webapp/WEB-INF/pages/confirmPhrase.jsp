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

        <form method="post" action="/admin/confirmPhrase">
            <h1 class="auth">Confirm order</h1>
            <div class="field">
                <label for="phrase_text">User_email: </label>
                <input readonly id="phrase_text" name="phrase_text" value="${sessionScope.get("phrase_text")}"  type="text" required>
            </div>
            <div class="field">
            <label for="crud_action">crud action: </label>
            <input readonly id="crud_action" name="crud_action" value="${sessionScope.get("crud_action")}"  type="text" required>
            </div>

            <div class="field">
                <label for="author_name">author_name: </label>
                <input readonly id="author_name" name="author_name" value="${sessionScope.get("author_name")}"  type="text" required>
            </div>
            <div class="field">
                <label for="author_surname">author_surname: </label>
                <input readonly id="author_surname" name="author_surname" value="${sessionScope.get("author_surname")}"  type="text" required>
            </div>

            <input type="number" hidden name="temp_phrase_id" value="${sessionScope.get("temp_phrase_id")}"/>
            <input type="submit" name="confirm" value="confirm">
            <input type="submit" name="reject" value="reject">
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
            <h6>Info:</h6>
            <textarea cols="20" rows="5" readonly></textarea>
        </aside>
    </div>    
</body>
</html>