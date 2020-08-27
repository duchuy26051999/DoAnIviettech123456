<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/14/2020
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section class="navbar main-menu">
    <div class="navbar-inner main-menu">
        <a href="/" class="logo pull-left"><h4 class="title">AUCTION </h4></a>
        <nav id="menu" class="pull-right">
            <ul>
                <li><a href="#">Danh Mục</a>
                    <ul>
                        <c:forEach var="c" items="${category}">
                            <li><a href="${pageContext.request.contextPath}/searchCategory/${c.category}">${c.category}</a></li>
                        </c:forEach>
<%--                        <li><a href="./products.html">Apple</a></li>--%>
<%--                        <li><a href="./products.html">Dell</a></li>--%>
<%--                        <li><a href="./products.html">Asus</a></li>--%>
<%--                        <li><a href="./products.html">HP</a></li>--%>
<%--                        <li><a href="./products.html">Acer</a></li>--%>
<%--                        <li><a href="./products.html">Lenovo</a></li>--%>
                    </ul>
                </li>
                <li><a href="/contact">Liên hệ</a></li>
<%--                <li><a href="#">News</a></li>--%>
<%--                <li><a href="./products.html">Bán chạy nhất</a></li>--%>
                <li><a href="/prepareAuction">Sản phẩm sắp đấu giá</a></li>
            </ul>
        </nav>
    </div>
</section>
</body>
</html>
