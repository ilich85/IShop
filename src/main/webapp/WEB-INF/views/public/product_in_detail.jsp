<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="../../../resources/css/product-in-detail.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="hidden-update" id="hidden-update">
    <br/>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/update-comment">
        <input type="hidden" name="productId" value="${productInfo.idProduct}">
        <textarea id="text" name="commentText" cols="20" rows="5"></textarea> <br/>
        <button id="id" name="idComment">Обновить</button>
    </form>
</div>
<div class="product-name"><p>${productInfo.productName}</p></div>
<div class="product-price"><p>Цена : ${productInfo.price}</p></div>
<div class="product-description">Описание : ${productInfo.description}</div>
<c:choose>
    <c:when test="${productInfo.quantity>0}">
        <div class="available"><p>Доступно : ${productInfo.quantity}</p></div>
        <form class="product-info" action="${pageContext.request.contextPath}/add-to-basket" method="post">
            <div class="quantity"> Количество:
                <input type="number" name="quantity"
                       min="1" max="${productInfo.quantity}" value="1" class="product-quantity"/>
                <button name="idProduct" value="${productInfo.idProduct}">Добавить в корзину</button>
            </div>
        </form>
    </c:when>
    <c:otherwise>
        <div class="available"><p>Товар закончился.</p></div>
    </c:otherwise>
</c:choose>
<img class="product-image" src="${pageContext.request.contextPath}/load?id=${productInfo.imageId}"
     width="200" height="200"/>

<div class="comments">
    <c:choose>
        <c:when test="${productInfo.comments.size()==0}">
            Комментарии пока никто не оставил.
        </c:when>
        <c:otherwise>
            <c:forEach items="${productInfo.comments}" var="comment">
                <div class="comment">
                    <div class="comment-text">${comment.commentText}</div>
                    <div class="comment-date">${comment.commentDate}</div>
                    <div class="user">${comment.userName}</div>
                </div>
                <c:if test="${comment.userName==currentUser}">
                    <button onclick="show(${comment.idComment},'${comment.commentText}')">Обновить отзыв</button>
                    <form method="post" action="${pageContext.request.contextPath}/delete-comment">
                        <input type="hidden" name="productId" value="${productInfo.idProduct}">
                        <button name="idComment" value="${comment.idComment}">Удалить отзыв</button>
                    </form>
                </c:if>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<div class="comment-add">
    <form method="post" action="${pageContext.request.contextPath}/add-comment">
        <textarea name="commentText" placeholder="Введите текст" cols="20" rows="5"></textarea>
        <br/>
        <br/>
        <button name="productId" value="${productInfo.idProduct}">Добавить отзыв</button>
    </form>
</div>
<script src="../../../resources/js/show.js"></script>
</body>
</html>