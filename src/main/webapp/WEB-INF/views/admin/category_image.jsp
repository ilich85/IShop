<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Image</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<form method="POST" action="${pageContext.request.contextPath}/add-category-image" enctype="multipart/form-data">
    ${category.nameCategory}
    <img src="${pageContext.request.contextPath}/load?id=${category.imageId}"/>
    <br/>
    <br/>
    <input type="file" name="file"><br/>
    <button name="categoryId" value="${category.idCategory}">Загрузить</button>
</form>
${size}
</body>
</html>