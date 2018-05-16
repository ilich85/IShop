<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="../../../resources/css/profile.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<br/>
<br/>
<div class="profile-page">
    <a href="${pageContext.request.contextPath}/user-address">Адрес доставки</a>
    <br/>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/user-orders">Мои заказы</a>
    <br/>
    <br/>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/user-delete">
        <button>Удалить страницу</button>
    </form>
</div>
</body>
</html>