<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/header.css">
</head>
<body>
<div class="header">
    <div class="main-menu">
        <br/>
        <a href="${pageContext.request.contextPath}/categories">Категории</a>
    </div>
    <div class="main-menu">
        <br/>
        <a href="${pageContext.request.contextPath}/products">Продукты</a>
    </div>
    <form action="${pageContext.request.contextPath}/admin-exit" method="post">
        <button class="exit">exit</button>
    </form>
    <c:if test="${cookie.containsValue('currentAdmin')=='superadmin'}">
        <a href="${pageContext.request.contextPath}/work-with-admins" class="admins">admins</a>
    </c:if>
</div>
</body>
</html>