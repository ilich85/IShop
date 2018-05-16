<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <link rel="stylesheet" href="../../../resources/css/products-list.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<c:choose>
    <c:when test="${searchResult.size()==0}">
        <h3>По вашему запросу ничего не найдено</h3>
    </c:when>
    <c:otherwise>
        <div class="products">
            <c:forEach items="${searchResult}" var="product">
                <div class="product">
                    <div class="product-name">${product.productName}</div>
                    <form action="${pageContext.request.contextPath}/product-in-detail" method="get">
                        <input type="image" class="product-image" name="idProduct" value="${product.idProduct}"
                               src="${pageContext.request.contextPath}/load?id=${product.imageId}" title="Подробнее"/>
                    </form>
                        ${product.price}
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>