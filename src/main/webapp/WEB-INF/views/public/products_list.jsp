<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/products-list.css">
    <title>Products</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>
    Продукты:
</h3>
<div class="products">
    <c:forEach items="${productsList}" var="product">
        <div class="product">
            <div class="product-name">${product.productName}</div>
            <form method="get" action="${pageContext.request.contextPath}/product-in-detail">
                <input type="image" class="product-image" name="idProduct" value="${product.idProduct}"
                       src="${pageContext.request.contextPath}/load?id=${product.imageId}" title="Подробнее"/>
            </form>
            Цена : ${product.price}
        </div>
    </c:forEach>
</div>
</body>
</html>