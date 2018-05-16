<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/categories.css">
    <title>Categories</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="categories">
    <c:forEach items="${categoriesList}" var="category">
        <div class="category">
            <div class="category-name"> ${category.nameCategory}</div>
            <form action="${pageContext.request.contextPath}/products-by-category" method="get">
                <input type="image" class="category-image" name="categoryName" value="${category.nameCategory}"
                       src="${pageContext.request.contextPath}/load?id=${category.imageId}" title="К товарам"/>
            </form>
            Количество товаров : ${category.countOfProducts}
        </div>
    </c:forEach>
</div>
</body>
</html>