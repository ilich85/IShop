<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/admin-style.css">
    <title>Work with products</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<a href="${pageContext.request.contextPath}/create-product">Добавить продукт</a>
<br/>
<br/>
Продукты:
<table border="1">
    <tr>
        <td></td>
        <td>Название</td>
        <td>Цена</td>
        <td>Количество</td>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>
                <form method="get" action="${pageContext.request.contextPath}/update-product">
                    <input type="image" class="image" name="idProduct" value="${product.idProduct}"
                           src="${pageContext.request.contextPath}/load?id=${product.imageId}"/>
                </form>
            </td>
            <td>
                <div>${product.productName}</div>
            </td>
            <td>
                <div>${product.price}</div>
            </td>
            <td>
                <div>${product.quantity}</div>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>