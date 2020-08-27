<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/14/2020
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
        <html>
<head>
    <title>Title</title>
</head>
<body>
<div id="top-bar" class="container"  style="background: #5bc0de;">
    <div class="row">
        <div class="span4" style="max-width: 270px">
            <form action="${pageContext.request.contextPath}/search" method="post" class="search_form">
                <div class="input-group">
                    <input type="text" style="width: 180px" name="search" class="search-query form-control input-group" Placeholder="Tìm kiếm...">
                    <span class="input-group-btn">
								<button class="btn btn-primary rounded-circle btnpro" type="submit" id="btn-search">
									<span class="fa fa-search"></span>
								</button>
							</span>

                </div>
            </form>
        </div>
        <div class="span8" style="width: 870px;font-size: 12px">
            <div class="account pull-right">
                <ul class="user-menu">
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/home" />">Trang chủ</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/user/myBid" />">Nhật ký đấu giá</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/user/myAuction"/>">Phiên Đấu giá</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/user/listGood"/>">Kho Hàng</a></li>
                        </sec:authorize>

                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/staff/home" />">Admin Home</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/user/creditCard" />">Thẻ tín dụng</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_STAFF')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/staff/home" />">Staff Home</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/user/myOrders" />">Đơn hàng</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li style="padding: 5px 6px"><a href="<c:url value="/user/formNewAuction" />">Tạo phiên đấu giá</a></li>
                        </sec:authorize>

                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <li style="padding: 5px 6px"><a href="<c:url value="/login" />">Đăng nhập</a></li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <li style="padding: 5px 6px"><a href="<c:url value="/register" />">Đăng ký</a></li>
                    </sec:authorize>

                    <nav id="menu" class="pull-right">
                        <ul>
                            <li style="color:  #fffa90">${hello} ${accountName}
                                <ul style="min-width: auto">
                                    <li style="width: 120px"><a href="<c:url value="/user/profile"/>">Trang cá nhân</a></li>
                                    <li style="width: 120px"><a href="<c:url value="/user/changePasswordForm"/>">Đổi mật khẩu</a></li>
                                    <li style="width: 120px"><a href="<c:url value="/logout"/>">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>

                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
