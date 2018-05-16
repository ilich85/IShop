<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/header.css">
</head>
<body>
<div class="header">
    <div class="main-menu">
        <br/>
        <a href="${pageContext.request.contextPath}/main-page">Главная</a>
    </div>
    <div class="main-menu">
        <br/>
        <a href="${pageContext.request.contextPath}/list-categories">Категории</a>
    </div>
    <div class="main-menu">
        <br/>
        <a href="${pageContext.request.contextPath}/list-products">Товары</a>
    </div>
    <div class="main-menu">
        <br/>
        <a href="${pageContext.request.contextPath}/contacts">Контакты</a>
    </div>
    <div class="profile"><a href="${pageContext.request.contextPath}/user-profile">Профиль</a></div>
    <div class="search-form">
        <form method="get" action="${pageContext.request.contextPath}/search">
            <br/>
            <input name="product-search" placeholder="Ввведите название"/>
            <input type="submit" value="Поиск"/>
        </form>
    </div>
    <div class="username">Привет, ${cookie.get("currentUser").value}</div>
    <form action="${pageContext.request.contextPath}/view-basket" method="get">
        <input type="image" src="../../../resources/images/basket.png" class="basket"/>
    </form>
    <form action="${pageContext.request.contextPath}/user-exit" method="post">
        <button class="exit">exit</button>
    </form>
</div>
</body>
</html>