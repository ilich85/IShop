<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Image</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<br/>
<br/>
<form method="POST" action="${pageContext.request.contextPath}/add-product-image" enctype="multipart/form-data">
    Название : ${product.productName}<br/>
    Цена : ${product.price}<br/>
    Описание : ${product.description}<br/>
    Количество : ${product.quantity}<br/>
    <img src="${pageContext.request.contextPath}/load?id=${product.imageId}"/>
    <br/>
    <br/>
    <input type="file" name="file"><br/>
    <button name="productId" value="${product.idProduct}">Загрузить</button>
</form>
${size}
</body>
</html>