<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/13/2020
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Credit Card</title>
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
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <style>
        table{
            width: 100%;
            border-collapse: collapse;
        }
        table td, table th{
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }
        table th{
            font-size: 1em;
            background-color: #a6a5a7;
        }
        table tbody tr:nth-child(even){
            background-color: #f5f5f5;
        }
        form strong{
            float: left;
        }
        form strong label{
            font-size: 13px;
            color: #2b2b2b;
        }
        .pagination li{
            float: left;
            list-style-type: none;
        }
        .pagination li a input{
            height: 32px;
            font-size: 14px;
            background: #dad55e;
        }

    </style>
</head>
<body>
<jsp:include page="../header/header.jsp"/>
<div id="wrapper" class="container">
    <jsp:include page="../header/header-section.jsp"/>
    <section  class="homepage-slider" id="home-slider">
        <div class="flexslider">
            <ul class="slides">
                <li>
                    <img src="${pageContext.request.contextPath}/themes/images/carousel/slider_3.jpg" alt="anh bia 1" style="width: 100%;height: 300px"/>
                    <%--                    <div class="intro">--%>
                    <%--                        <h1>Laptop mua online</h1>--%>
                    <%--                        <p><span>Giảm thêm đến 1.000.000đ</span></p>--%>
                    <%--                        <p><span>Áp dụng kèm theo khuyến mãi trả thẳng khác</span></p>--%>
                    <%--                    </div>--%>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/themes/images/carousel/slider_8.jpg" alt="anh bia 2" style="width: 100%;height: 300px"/>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/themes/images/carousel/slider_9.jpg" alt="anh bia 3" style="width: 100%;height: 300px"/>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/themes/images/carousel/slider_6.jpg" alt="anh bia 4" style="width: 100%;height: 300px" />
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/themes/images/carousel/slider_7.jpg" alt="anh bia 5" style="width: 100%;height: 300px" />
                </li>
            </ul>
        </div>
    </section>
    <br/>
    <br/>
    </section>
    <section class="main-content">
        <div style="margin-top: -40px;">
            <h4 class="title" align="center" style="font-size: 17px"><span class="text"><strong>Thông tin thẻ tín dụng</strong></span></h4>
            <div style="font-size: 16px;width: 300px;margin-left: 470px">
                <strong>Tên khách hàng:</strong> <span>${credit.customer.customerName}</span><br>
                <strong>Số tài khoản  :</strong> <span>${credit.cardNumber}</span><br>
                <strong>Số dư      :</strong> <span>${credit.formatAmount()} VNĐ</span><br>
            </div>
            <h4 class="title" align="center" style="font-size: 19px;margin-top: 20px"><span class="text"><strong>Nhật ký giao dịch</strong></span></h4>
            <p style="color: red" align="center">${message}</p>
            <form action="${pageContext.request.contextPath}/user/myCredit/search/1" method="post" style="margin: 0px">
                <strong id="start" style="margin-left: 280px"><label>Ngày bắt đầu</label>
                    <input type="datetime-local" name="dateStart" required/></strong>
                <strong id="end" style="margin-left: 20px"><label>Ngày kết thúc</label>
                    <input type="datetime-local" name="dateEnd" required/></strong>
                <strong style="margin-left: 10px;margin-top: 25px;">
                    <input type="submit" value="Tìm kiếm" style="height: 30px;font-size: 14px; background: #fbb450">
                </strong>
            </form>
            <strong style="margin-left: 10px;"><a href="/user/myCredit"><input type="button" value="Xem tất cả" style="margin-top: 25px;height: 30px;font-size: 14px;background: #fbb450"></a></strong>
            <table width="100%" border="1px solid #7f7f7f" style="text-align: center">
                <thead style="background: #c7c4c4">
                <tr style="height: 40px">
                    <th>Mã giao dịch</th>
                    <th>Ngày giao dịch</th>
                    <th>Nội dung</th>
                    <th>Số tiền giao dịch</th>
                    <th>Số dư</th>
                    <th>Loại giao dịch</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="transaction" items="${transactionLog.pageList}">
                    <tr>
                        <td>${transaction.id}</td>
                        <td>${transaction.formatDate()}</td>
                        <td>${transaction.content}</td>
                        <td>${transaction.formatAmount()} VNĐ</td>
                        <td>${transaction.formatBalance()} VNĐ</td>
                        <td>${transaction.logType}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div style="margin-bottom: 70px">
            <ul class="pagination">
                <li class="${currentIndex == 1}? 'page-item disabled' : 'page-item'">
                    <a class="page-link" href="${pageContext.request.contextPath}/user/myCredit/page/1"><input type="button" value="First"></a>
                </li>
                <li class="${currentIndex == 1}? 'page-item disabled': 'page-item' ">
                    <a class="page-link" aria-label="Previous"
                       href="${pageContext.request.contextPath}${baseUrl}${currentIndex - 1}"
                       title='Go to previous page'><input type="button" value="<<"></a>
                </li>
                <li class="${item == currentIndex}? 'page-item active' :'page-item'">
                    <c:forEach var="i" begin="${beginIndex}" end="${totalPageCount}" step="1">
                        <a class="page-link" href="${pageContext.request.contextPath}${baseUrl}${i}">
                            <input type="button" value="${i}"></a>
                    </c:forEach>
                </li>
                <li class="${currentIndex == totalPageCount}? 'page-item disabled': 'page-item'">
                    <a class="page-link" aria-label="Next"
                       href="${pageContext.request.contextPath}${baseUrl}${currentIndex + 1}" title='Go to next page'>
                        <input type="button" value=">>"> </a>

                </li>
                <li class="${pageContext.request.contextPath}${currentIndex == totalPageCount}? 'page-item disabled':'page-item'">
                    <a class="page-link" href="${pageContext.request.contextPath}${baseUrl}${totalPageCount}"><input type="button" value="Last"></a>
                </li>
            </ul>
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
<script src="${pageContext.servletContext.contextPath}/themes/js/common.js"></script>
<script src="${pageContext.servletContext.contextPath}/themes/js/jquery.flexslider-min.js"></script>
<script type="text/javascript">
    $(function() {
        $(document).ready(function() {
            $('.flexslider').flexslider({
                animation: "fade",
                slideshowSpeed: 4000,
                animationSpeed: 600,
                controlNav: false,
                directionNav: true,
                controlsContainer: ".flex-container" // the container that holds the flexslider
            });
        });
    });
    $(document).ready(function() {
        $('#checkout').click(function (e) {
            document.location.href = "checkout.html";
        })
    });
</script>
</body>
</html>