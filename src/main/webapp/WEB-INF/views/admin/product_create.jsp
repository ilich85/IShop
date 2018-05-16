<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="../../../resources/css/admin-product.css">
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<div class="add-product">
    <spring:form method="post" modelAttribute="newProduct" action="/create-product">
        Название:<br/><spring:input path="productName"/><br/>
        Описание:<br/><spring:input path="description"/><br/>
        Количество:<br/><spring:input path="quantity"/><br/>
        <label>Категория:<br/>
            <select name="categoryName">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.nameCategory}">${category.nameCategory}</option>
                </c:forEach>
            </select>
        </label><br/>
        Цена:<br/><spring:input path="price"/><br/>
        <spring:button>Добавить продукт</spring:button>
    </spring:form>
</div>
</body>
</html>