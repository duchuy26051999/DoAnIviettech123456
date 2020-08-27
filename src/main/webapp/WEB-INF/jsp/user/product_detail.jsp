<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/13/2020
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Auction Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->

    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/bootstrap/css/font-awesome.min.css">
    <link href="${pageContext.servletContext.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/themes/css/bootstrappage.css" rel="stylesheet"/>

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/themes/css/font-awesome.min.css">
    <link href="${pageContext.servletContext.contextPath}/themes/css/main.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/themes/css/jquery.fancybox.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/themes/css/font-awesome.min.css">
    <!-- <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min2.css"> -->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/themes/css/font-awesome.min3.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
    <!-- scripts -->
    <script src="${pageContext.servletContext.contextPath}/themes/js/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/themes/js/superfish.js"></script>
    <script src="${pageContext.servletContext.contextPath}/themes/js/jquery.scrolltotop.js"></script>
    <script src="${pageContext.servletContext.contextPath}/themes/js/jquery.fancybox.js"></script>
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <style>
        #clock{
            position: relative;
            display: block;
            text-align: center;
            margin: 10px 0;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.4em;
        }
        #clock #time
        {
            display: flex;
        }
        #clock #time div
        {
            position: relative;
            margin: 0 5px;
        }
        #time div span
        {
            position: relative;
            display: block;
            width: 100px;
            height: 50px;
            background: #2196f3;
            color: #fff;
            font-weight: 300;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 3em;
            z-index: 10;
            box-shadow: 0 0 0 1px rgba(0, 0, 0, .2);
        }
        #time div span:nth-child(2)
        {
            height: 30px;
            font-size: 0.7em;
            letter-spacing: 0.2em;
            font-weight: 500;
            z-index: 9;
            box-shadow: none;
            background: #127fd6;
            text-transform: uppercase;
        }
        #time div:last-child span
        {
            background: #ff006a;
        }
        #time div:last-child span
        {
            background: #ff006a;
        }
        #time div:last-child span:nth-child(2)
        {
            background: #ec0062;
        }
        #auction table{
            width: 100%;
            border-collapse: collapse;
        }
        #auction table td, table th{
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }
        #auction table th{
            font-size: 1em;
            background-color: #c2d7e4;
            color: #2a3342;
        }
        #auction table tbody tr:nth-child(even){
            background-color: #f5f5f5;
        }
    </style>
</head>
<body onload="start()">
<jsp:include page="../header/header.jsp"/>
<div id="wrapper" class="container">
    <jsp:include page="../header/header-section.jsp"/>
    <section class="header_text sub">
        <img class="pageBanner" src="${pageContext.servletContext.contextPath}/themes/images/carousel/slider_8.jpg" alt="New products" style="width: 100%;height: 250px" >
        <h4><span>Chi tiết sản phẩm</span></h4>
    </section>
    <section class="main-content">
        <div class="row">
            <div class="span7" style="width: 100%">
                <div class="row" style="width: 80%;margin-left: 150px">

                    <div class="span4">
                        <a href="${pageContext.servletContext.contextPath}/${auctionDetails.goods.imageList.get(0).url}" class="thumbnail" data-fancybox-group="group1" title="Description 1"><img alt="" src="${pageContext.servletContext.contextPath}/getImage/${auctionDetails.goods.imageList.get(0).url}"></a>
                        <ul class="thumbnails small">
                            <c:forEach var="image" items="${imageList}">
                            <li class="span1">
                                <a href="${pageContext.request.contextPath}/getImage/${image.url}" class="thumbnail" data-fancybox-group="group1" title="Description 2"><img src="${pageContext.request.contextPath}/getImage/${image.url}" alt=""></a>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="span5">
                        <address style="font-size: 16px">
                            <strong>Tên sản phẩm:</strong> <span>${auctionDetails.goods.goodName}</span><br>
                            <strong>Mô tả:</strong> <span>${auctionDetails.goods.goodDescription}</span><br>
                            <%--                            <strong>Tình trạng:</strong> <span>Hết hàng</span><br>--%>
                            <%--                            <strong>Loại:</strong> <span>Văn phòng</span><br>--%>
                            <%--                            <strong>Lượt xem:</strong> <span>21932</span><br>--%>
                            <%--                            <strong>Đã bán:</strong> <span>2154</span><br>--%>
                        </address>
                        <%--                        <h4><strong>Giá: 7.900.000đ</strong></h4>--%>
                        <!-- thoi gian -->
                        <script language="javascript">

                            var h = ${auctionDetails.hours}; // Giờ
                            var m = ${auctionDetails.minutes}; // Phút
                            var s = ${auctionDetails.seconds}; // Giây
                            var timeout = null; // Timeout
                            function start()
                            {
                                if (s === -1){
                                    m -= 1;
                                    s = 59;
                                }
                                if (m === -1){
                                    h -= 1;
                                    m = 59;
                                }

                                if (h == -1){
                                    clearTimeout(timeout);
                                    stop();
                                    return false;
                                }
                                /*BƯỚC 1: HIỂN THỊ ĐỒNG HỒ*/
                                document.getElementById('h').innerText = h.toString();
                                document.getElementById('m').innerText = m.toString();
                                document.getElementById('s').innerText = s.toString();
                                /*BƯỚC 1: GIẢM PHÚT XUỐNG 1 GIÂY VÀ GỌI LẠI SAU 1 GIÂY */
                                timeout = setTimeout(function(){
                                    s--;
                                    start();
                                }, 1000);
                                setTimeout( click, 0);
                            }

                            function stop(){
                                var tb = 'Kết thúc...';
                                document.getElementById('tb').innerText = tb.toString();
                            }

                        </script>
                        <div id="clock">
                            <div id="time">
                                <div><span id="h">00</span><span>Giờ</span></div>
                                <div><span id="m">00</span><span>Phút</span></div>
                                <div><span id="s">00</span><span>Giây</span></div>
                            </div>
                        </div>
                        <div style="color: red; font-size: 18px"><span id="tb"></span></div>

                    </div>
                    <div class="span5">
                        <form action="${pageContext.request.contextPath}/makeABid/${auctionDetails.id}" class="form-inline" method="post">
                            <p>&nbsp</p>
                            <p style="color: #00aced">Giá thầu lớn nhất:  ${auctionDetails.maxBid} VNĐ</p>
                            <a href="/minusBid/${auctionDetails.id}/${price}"><input type="button" name="remove" value="-" style="width: 25px;height: 29px;font-size: 20px;border-radius: 5px"/></a>
                            <input type="text" name="bidPrice" style="width: 170px;height: 20px" size="50" value="${formatPrice} VND" align="center" readonly="true" />
                            <input type="hidden" name="bid" value="${price}"/>
                            <a href="/plusBid/${auctionDetails.id}/${price}"><input type="button" name="p" value="+" style="width: 25px;height: 29px;font-size: 20px;border-radius: 5px"/></a>

                            <button class="btn btn-inverse" type="submit">Đấu giá</button>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="span9" style="font-size: 15px;width: 95%">
                        <ul class="nav nav-tabs" id="myTab">

                            <li class=""><a href="#auction">Bảng đấu giá</a></li>
                            <li class="active"><a href="#home">Miêu tả</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="auction">
                                <table>
                                    <thead>
                                    <th>Khách hàng</th>
                                    <th>Thời gian đấu giá</th>
                                    <th>Giá thầu</th>
                                    <th>Trạng thái</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="bid" items="${bid}">
                                        <tr>
                                            <td>${bid.customer.customerName}</td>
                                            <td>${bid.formatDate()}</td>
                                            <td>${bid.formatPrice()} VNĐ</td>
                                            <td>${bid.bidStatus}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane" id="home">
                                ${auctionDetails.goods.goodDescription}

                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
        <hr width="100%">
<%----%>
        <h4 class="title" align="center" style="font-size: 19px"><span class="text"><strong>Bình luận</strong></span></h4>
        <div class="commentUser" style="width: 100%;">
            <div class="comment">
                <c:forEach var="comment" items="${listComment}">
                <div>

                   <span style="font-size: 18px;color: #2a3342" id="customerName"><b>${comment.customer.customerName}</b> : ${comment.message} </span><br>
                <div style="font-size: 9.5px"><span id="timeComment">${comment.formatDatePost()}</span></div>
                </c:forEach>
            </div>
            <br>
            <mvc:form modelAttribute="comment" method="post" action="/postMessage/${auctionDetails.id}">
                <fieldset>
                    <div class="clearfix">
                        <label style="font-size: 14px"><span>Nhập bình luận : </span></label>
                        <div class="input">
                            <mvc:textarea cssStyle="width: 450px" path="message" tabindex="3" id="message" name="body" rows="6" placeholder="Viết gì đó ... "></mvc:textarea>
                        </div>
                    </div>

                    <div class="actions">
                        <button tabindex="3" type="submit" class="btn btn-primary">Bình luận</button>
                    </div>
                </fieldset>
            </mvc:form>
        </div>
<%--        --%>
    </section>
<%----%>
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
<script>
    $(function () {
        $('#myTab a:first').tab('show');
        $('#myTab a').click(function (e) {
            e.preventDefault();
            $(this).tab('show');
        })
    })
    $(document).ready(function() {
        $('.thumbnail').fancybox({
            openEffect  : 'none',
            closeEffect : 'none'
        });

        $('#myCarousel-2').carousel({
            interval: 2500
        });
    });
</script>
</body>
</html>