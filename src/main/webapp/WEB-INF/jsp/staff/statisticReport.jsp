<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/13/2020
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Deposit credit</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
    <!-- bootstrap -->
    <link href="${pageContext.servletContext.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/themes/css/bootstrappage.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/themes/css/font-awesome.min.css">
    <!-- <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min2.css"> -->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/themes/css/font-awesome.min3.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
    <!-- global styles -->
    <link href="${pageContext.servletContext.contextPath}/themes/css/flexslider.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/themes/css/main.css" rel="stylesheet"/>

    <!-- scripts -->
    <script src="${pageContext.servletContext.contextPath}/themes/js/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/themes/js/superfish.js"></script>
    <script src="${pageContext.servletContext.contextPath}/themes/js/jquery.scrolltotop.js"></script>
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <style>
        .form{
            width: 100%;
            min-height: 180px;
            max-height: 200px;
            background: #c4e3f3;
            margin-top: -45px;
        }
        .form form{
            margin-left: 300px;
        }
        .form form input{
            font-size: 15px;
        }
        #textSearch{
            width: 255px;
        }
        #search{
            height: 30px;
            margin-top: -10px;
            background: #fbb450;
            color: #353f40;
        }
        #deposit{
            height: 30px;
            margin-top: -10px;
            color: #353f40;
            background: #fbb450;
        }
        table{
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        table td, table th{
            padding: 12px 15px;
            border: 1px solid #7f7f7f;
            text-align: center;
        }
        table th{
            font-size: 1em;
            background-color: #c7ddef;
        }
        table tbody tr:nth-child(even){
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div id="wrapper" class="container">
    <jsp:include page="../header/header-sectionStaff.jsp"/>
    <section class="main-content">
        <div>
            <p>${message}</p>
            <h4 class="title"><span class="text"><strong>List</strong> Credit Card</span></h4><br>
            <div class="form">
                <h4 align="center">Search credit</h4>
                <p style="color:red;" align="center">${message}</p>
                <form action="${pageContext.request.contextPath}/staff/searchCredit" method="post" style="margin-left: 430px;float: left">

                    <input type="text" id="textSearch" name="codeSearch" placeholder="Enter customer name or card number"/>
                    <input id="search" type="submit" name="put" value="Search">
                </form>
                <button style="margin-left: 5px;height: 31px;width: 65px; color: #0e0e0e; background: #fbb450; ">

                    <a href="/staff/depositAmount">List All</a></button>
                <hr width="100%"/>
                <h4 align="center" style="margin-top:-10px">Deposit amount credit</h4>
                <p style="color:red;" align="center">${message1}</p>
                <form action="/staff/deposit" method="post">

                    <input type="text" name="cardNumber" placeholder="Enter Card Number"/>
                    <input type="text" name="amount" placeholder="Enter amount">VND</input>
                    <input id="deposit" type="submit" name="deposit" value="Deposit amount">
                </form>
            </div>

            <table>
                <thead style="background: #c7c4c4">
                <tr style="height: 40px">
                    <th width="30%">Card Number</th>
                    <th width="40%">Customer Name</th>
                    <th width="30%">Amount</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="credit" items="${listCredit}">
                    <tr>
                        <td>${credit.cardNumber}</td>
                        <td>${credit.customer.customerName}</td>
                        <td>${credit.formatAmount()} VNĐ</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
    <section style="background-color: #857474;" id="footer-bar">
        <div class="row">
            <div class="span3">
                <h4>Điều Hướng</h4>
                <ul class="nav">
                    <li><a href="/">Trang chủ</a></li>
                    <li><a href="/contact">Liên hệ</a></li>
                    <li><a href="/user/formRegisterAuction">Đăn ký tài khoản</a></li>
                    <li><a href="/login">Đăng nhập</a></li>
                </ul>
            </div>
            <div class="span4">
                <h4>Tài khoản của tôi</h4>
                <ul class="nav">
                    <li><a href="/user/creditCard">Thẻ tín dụng của tôi</a></li>
                    <li><a href="/user/listGood">Danh mục hàng hóa</a></li>
                    <li><a href="/user/myAuction">Phiên đấu giá</a></li>
                    <li><a href="/user/myOrders">Đơn hàng thành công</a></li>
                    <li><a href="/user/bidLog">Lịch sử đấu giá</a></li>
                </ul>
            </div>
            <div class="span5">
                <p class="logo"><!-- <img src="themes/images/logo.png" class="site_logo" alt=""> --><h4>AUCTION SYSTEM</h4></p>
                <p style="color: white;">Website chúng tôi chuyên cung cấp các dịch vụ uy tín và mới nhất trên thị trường hiện nay để phục vụ cho mọi khách hàng có nhu cầu mua và bán thông qua hình thức đấu giá trực tuyến
                    <br/>Hãy ghé thăm trang web của chúng tôi và đừng bỏ lỡ các dịch vụ mới nhé!</p>
                <div style="margin-left: 60px;">
                    <a href="#" class="button">
                        <i class="fab fa-facebook-f fa-lg"></i>
                    </a>
                    <a href="#" class="button">
                        <i class="fab fa-twitter fa-lg"></i>
                    </a>
                    <a href="#" class="button">
                        <i class="fab fa-instagram fa-lg"></i>
                    </a>
                </div>

            </div>
        </div>
    </section>

    <section id="copyright">
        <span style="text-align: center">AUCTION SYSTEM THỰC HIỆN BỞI SỸ HOÀN & ĐỨC HUY</span>
    </section>
</div>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/themes/js/common.js"></script>
<script src="${pageContext.servletContext.contextPath}/themes/js/jquery.flexslider-min.js"></script>
</body>
</html>

