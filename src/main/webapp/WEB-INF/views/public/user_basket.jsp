<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Basket</title>
    <link rel="stylesheet" href="../../../resources/css/basket.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1><p>Корзина</p></h1>
<c:choose>
    <c:when test="${basket.details.size()==0}">
        <h3><p>Ваша корзина пуста</p></h3>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <td></td>
                <td>Product Name:</td>
                <td>Price:</td>
                <td>Amount:</td>
                <td>Quantity:</td>
                <td>Remove:</td>
            </tr>
            <c:forEach items="${basket.details}" var="details">
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/product-in-detail" method="get">
                            <input type="image" class="product-image" name="idProduct"
                                   value="${details.product.idProduct}"
                                   src="${pageContext.request.contextPath}/load?id=${details.product.imageId}"
                                   title="Подробнее"/>
                        </form>
                    </td>
                    <td>${details.product.productName}</td>
                    <td>${details.product.price}</td>
                    <td>${details.amount}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/update-quantity" method="post">
                            <input min="1" max="${details.quantity+details.product.quantity}"
                                   type="number" onchange="submit()" name="quantity" value="${details.quantity}"/>
                            <input type="hidden" name="idProduct" value="${details.product.idProduct}"/>
                            <input type="hidden" name="basketDetails" value="${details.idBasketDetails}">
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/remove-from-basket" method="post">
                            <input type="hidden" name="quantity" value="${details.quantity}">
                            <input type="image" class="image" name="idProduct" value="${details.product.idProduct}"
                                   src="../../../resources/images/delete.png" class="delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div>
            Итого: ${basket.total}
        </div>
        <spring:form action="/add-order" method="post">
            <button name="total" value="${basket.total}">Сделать заказ</button>
        </spring:form>
    </c:otherwise>
</c:choose>
</body>
</html>