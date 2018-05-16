<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
${category.nameCategory}
${category.idCategory}
Количество товаров : ${category.countOfProducts}
<img class="image" src="${pageContext.request.contextPath}/load?id=${category.imageId}"/>
<div class="update">
    <form action="${pageContext.request.contextPath}/delete-category" method="post">
        <button name="categoryName" value="${category.nameCategory}">Удалить категорию</button>
    </form>
    <form action="${pageContext.request.contextPath}/update-category" method="post">
        <input type="hidden" name="categoryId" value="${category.idCategory}"/>
        <input type="text" name="newName" value="${category.nameCategory}">
        <button>Изменить название</button>
    </form>
    <form action="${pageContext.request.contextPath}/add-category-image" method="get">
        <input type="hidden" name="categoryId" value="${category.idCategory}"/>
        <button>Изменить изображение</button>
    </form>
</div>
</body>
</html>