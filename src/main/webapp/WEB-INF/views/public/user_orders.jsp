<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="../../../resources/css/orders.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<br/>
<br/>
<c:choose>
    <c:when test="${orders.size()==0}">
        <h3>Вы еще не сделали покупок</h3>
    </c:when>
    <c:otherwise>
        <div class="orders">
            <c:forEach items="${orders}" var="order">
                <div class="order">
                    <div class="order-date">date: ${order.orderDate}</div>
                    <div class="details">
                        <c:forEach items="${order.details}" var="detail">
                            <form action="${pageContext.request.contextPath}/product-in-detail" method="get">
                                <input type="hidden" name="idProduct" value="${detail.product.idProduct}">
                                <input type="image"
                                       src="${pageContext.request.contextPath}/load?id=${detail.product.imageId}"
                                       width="60" height="60"/>
                            </form>
                            Наименование: ${detail.product.productName}<br/>
                            Количество: ${detail.quantity}<br/>
                            Сумма: ${detail.amount}<br/>
                        </c:forEach>
                    </div>
                </div>
                <div class="order-total">Всего: ${order.total}</div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>