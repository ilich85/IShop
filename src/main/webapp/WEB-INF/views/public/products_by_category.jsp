<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/products.css">
    <title>Products</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<c:choose>
    <c:when test="${productList.size()==0}">
        <p>В данной категории нет продуктов</p>
    </c:when>
    <c:otherwise>
        <div class="products">
            <c:forEach items="${productList}" var="product">
                <div class="product">
                    <div class="product-name">${product.productName}</div>
                    <form action="${pageContext.request.contextPath}/product-in-detail" method="get">
                        <input type="image" class="product-image" name="idProduct" value="${product.idProduct}"
                               src="${pageContext.request.contextPath}/load?id=${product.imageId}" title="Подробнее"/>
                    </form>
                    Цена: ${product.price}
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>