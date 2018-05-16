<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../resources/css/admins.css">
    <title>Admins</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<br/>
<div class="add-admin">
${result}
    <br/>
    <br/>
    <spring:form method="post" modelAttribute="newAdmin" action="/admin-create">
        Логин:
        <br/>
        <spring:input path="adminName"/>
        <br/>
        Пароль:
        <br/>
        <spring:input path="password"/>
        <br/>
        <spring:button>Добавить админа</spring:button>
    </spring:form>
</div>
<div class="admins-list">
    <table border="1">
        <tr>
            <td>Логин</td>
            <td>Удалить</td>
        </tr>
        <c:forEach items="${adminsList}" var="admin">
            <tr>
                <td>${admin.adminName}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin-remove" method="post">
                        <input type="image" class="delete-image" name="adminName" value="${admin.adminName}"
                               src="../../../resources/images/delete.png"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>