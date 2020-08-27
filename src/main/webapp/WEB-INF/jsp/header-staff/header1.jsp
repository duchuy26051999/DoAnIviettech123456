<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/14/2020
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="profile_details w3l">
    <ul>
        <li class="dropdown profile_details_drop">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <div class="profile_img">
                    <span class="prfil-img"><img src="${pageContext.servletContext.contextPath}/images/in4.jpg" alt=""> </span>
                    <div class="user-name">
<%--                        <p>Thông tin</p>--%>
                        <span>${accountName}</span>
                    </div>
                    <i class="fa fa-angle-down"></i>
                    <i class="fa fa-angle-up"></i>
                    <div class="clearfix"></div>
                </div>
            </a>
            <ul class="dropdown-menu drp-mnu">
<%--                <li> <a href="#"><i class="fa fa-cog"></i> Cài đặt</a> </li>--%>
<%--                <li> <a href="#"><i class="fa fa-user"></i> Thông tin</a> </li>--%>
                <li> <a href="${pageContext.request.contextPath}/"><i class="fa fa-sign-out"></i>Trang chính</a> </li>
            </ul>
        </li>
    </ul>
</div>
</body>
</html>
