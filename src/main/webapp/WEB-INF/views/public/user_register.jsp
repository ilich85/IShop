<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="../../../resources/css/auth-reg.css">
</head>
<body>
<script src="../../../resources/js/validate.js"></script>
<div class="register">
    <br/>
    <spring:form method="post" modelAttribute="newUser" action="user-create">
        Имя пользователя: <br/>
        <spring:input id="login" path="userName"/>
        <br/>
        Пароль: <br/>
        <spring:input id="password" type="password" path="password"/>
        <br/>
        Email: <br/>
        <spring:input id="mail" path="email"/>
        <br/>
        <br/>
        <spring:button class="buttons">Зарегистрироваться</spring:button>
        <script>inputValidation()</script>
    </spring:form>
    <a href="${pageContext.request.contextPath}/index">Войти</a>
    <br/>
    ${createUserResult}
</div>
</body>
</html>