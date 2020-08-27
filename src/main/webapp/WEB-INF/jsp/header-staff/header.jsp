<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/14/2020
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="sidebar-menu">
    <header class="logo1">
        <a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a>
    </header>
    <div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
    <div class="menu">
        <ul id="menu" >
            <li><a href="/staff/home"><i class="fa fa-tachometer"></i> <span>Trang chủ</span><div class="clearfix"></div></a></li>

            <li id="menu-academico" ><a href="#"><i class="fa fa-list-ul" aria-hidden="true"></i><span>Thông tin đấu giá</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
                <ul id="menu-academico-sub" >
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/manageAuction">Quản lý Auction</a></li>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/manageGood">Quản lý hàng hóa</a></li>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/manageImage">Quản lý ảnh</a></li>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/manageCategory">Quản lý thể loại</a></li>
                    <%--                    <li id="menu-academico-avaliacoes" ><a href="sanpham2.html">Nhà sản xuất</a></li>--%>
                </ul>
            </li>

            <li id="menu-academico" ><a href="#"><i class="fa fa-file-text-o"></i>  <span>Thông tin khách hàng</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
                <ul id="menu-academico-sub" >
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/manageCustomer">Quản lý khách hàng</a></li>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/viewAccount">Quản lý tài khoản</a></li>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/depositAmount">Nạp tiền khách hàng</a></li>
                    <%--                    <li id="menu-academico-avaliacoes" ><a href="sanpham2.html">Nhà sản xuất</a></li>--%>
                </ul>
            </li>

            <li id="menu-academico" ><a href="#"><i class="fa fa-list-ul" aria-hidden="true"></i><span>Thông tin nhân viên</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
                <ul id="menu-academico-sub" >
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/manageStaff">Quản lý nhân viên</a></li>
                </ul>
            </li>

            <li id="menu-academico" ><a href="#"><i class="fa fa-envelope nav_icon"></i><span>Báo cáo thống kê</span><span class="fa fa-angle-right" style="float: right"><div class="clearfix"></div></a>
                <ul id="menu-academico-sub">
<%--                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/reportAuction">Thống kê Auction</a></li>--%>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/reportAuctionSuccess">Thống kê Auction thành công</a></li>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/reportAuctionFailure">Thống kê Auction thất bại</a></li>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/commission">Thống kê tiền hoa hồng</a></li>
                    <li id="menu-academico-avaliacoes" ><a href="${pageContext.request.contextPath}/staff/depositCustomer">Thống kê tiền nạp khách hàng</a></li>
                </ul>
            </li>

        </ul>
    </div>
</div>
</body>
</html>
