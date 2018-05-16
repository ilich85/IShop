<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin auth</title>
    <link rel="stylesheet" href="../../../resources/css/auth-reg.css">
</head>
<body>
<div class="auth">
    <br/>
    <br/>
    <spring:form method="post" modelAttribute="admin" action="/admin-in">
        Логин: <br/>
        <spring:input path="adminName"/>
        <br/>
        Пароль: <br/>
        <spring:input type="password" path="password"/>
        <br/>
        <br/>
        <spring:button>Войти</spring:button>
    </spring:form>
    ${checkResult}
</div>
</body>
</html>