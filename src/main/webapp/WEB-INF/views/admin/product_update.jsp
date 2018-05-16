<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product update</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<br/>
<br/>
<form method="post" action="${pageContext.request.contextPath}/update-product">
    Название : <input type="text" name="productName" value="${product.productName}"/><br/>
    Категория :
    <select name="categoryName">
        <option selected>${product.categoryName}</option>
        <c:forEach items="${categories}" var="category">
            <option value="${category.nameCategory}">
                    ${category.nameCategory}
            </option>
        </c:forEach>
    </select>
    <br/>
    Цена : <input type="number" name="price" value="${product.price}"/><br/>
    Описание : <textarea name="description">${product.description}</textarea><br/>
    Количество : <input type="text" name="quantity" value="${product.quantity}"/><br/>
    <button name="idProduct" value="${product.idProduct}">Обновить продукт</button>
</form>
<form method="post" action="${pageContext.request.contextPath}/delete-product">
    <button name="productId" value="${product.idProduct}">Удалить продукт</button>
</form>
<br/>
<form method="get" action="${pageContext.request.contextPath}/add-product-image">
    <img src="${pageContext.request.contextPath}/load?id=${product.imageId}" width="300" height="300"/>
    <button name="productId" value="${product.idProduct}">Изменить изображение</button>
</form>
</body>
</html>