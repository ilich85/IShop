<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/address.css">
    <title>Delivery Address</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<c:choose>
    <c:when test="${userAddress.idAddress==0}">
        <div class="address">
            <br/>
            <br/>
            <spring:form method="post" modelAttribute="newAddress" action="/set-delivery-address">
                Имя: <spring:input path="firstName"/> <br/>
                Фамилия: <spring:input path="lastName"/> <br/>
                Страна: <spring:input path="country"/> <br/>
                Город: <spring:input path="city"/> <br/>
                Улица: <spring:input path="street"/> <br/>
                Номер дома: <spring:input path="house"/> <br/>
                Квартира: <spring:input path="flat"/> <br/>
                <spring:button>Добавить адрес доставки</spring:button>
            </spring:form>
        </div>
    </c:when>
    <c:otherwise>
        <div class="address">
            Имя: ${userAddress.firstName}<br/>
            Фамилия: ${userAddress.lastName}<br/>
            Страна: ${userAddress.country}<br/>
            Город: ${userAddress.city}<br/>
            Улица: ${userAddress.street}<br/>
            Номер дома: ${userAddress.house}<br/>
            <c:if test="${userAddress.flat!=0}">
                Квартира: ${userAddress.flat}<br/>
            </c:if>
            <script>function show() {
                document.getElementById('hidden-update').style.display = 'block';
            }
            </script>
            <button onclick="show()">Обновить адрес</button>
        </div>
        <div class="hidden-update" id="hidden-update">
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <form method="post" action="${pageContext.request.contextPath}/update-delivery-address">
                Имя: <input name="firstName" value="${userAddress.firstName}"/> <br/>
                Фамилия: <input name="lastName" value="${userAddress.lastName}"/> <br/>
                Страна: <input name="country" value="${userAddress.country}"/> <br/>
                Город: <input name="city" value="${userAddress.city}"/> <br/>
                Улица: <input name="street" value="${userAddress.street}"/> <br/>
                Номер дома:<input name="house" value="${userAddress.house}"/> <br/>
                Квартира: <input name="flat" value="${userAddress.flat}"/> <br/>
                <button>Обновить адрес</button>
            </form>
            <form method="get" action="${pageContext.request.contextPath}/user-address">
                <button>Отмена</button>
            </form>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>