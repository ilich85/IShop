<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/product-in-detail.css">
    <title>categories</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
${result}
<div class="categories">
    <c:forEach items="${categoriesList}" var="category">
        <div class="category">
            <form action="${pageContext.request.contextPath}/update-category" method="get">
                <input type="image" name="idCategory" value="${category.idCategory}"
                       src="${pageContext.request.contextPath}/load?id=${category.imageId}" class="category-image"/>
                    ${category.nameCategory}
                Количество товаров : ${category.countOfProducts}
            </form>
        </div>
    </c:forEach>
    <form action="${pageContext.request.contextPath}/create-category" method="post">
        <input name="categoryName"/>
        <button>Создать категорию</button>
    </form>
</div>
</body>
</html>