<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="../../../resources/css/auth-reg.css">
</head>
<body>
<script src="../../../resources/js/validate.js"></script>
<div class="auth">
    <br/>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/user-sign-in">
        Имя пользователя: <br/>
        <input type="text" id="login" name="userName"/>
        <br/>
        Пароль: <br/>
        <input type="password" id="password" name="password"/>
        <br/>
        <br/>
        <input type="submit" value="Войти"/>
        <script>inputValidation()</script>
    </form>
    <a href="<c:url value="/user-create"/>">Регистрация</a>
    <br/>
    ${createUserResult}
    ${checkResult}
</div>
</body>
</html>