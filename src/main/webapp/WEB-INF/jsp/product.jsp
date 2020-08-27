<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/13/2020
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Products</title>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/themes/css/jquery-ui.min.css">
    <!-- scripts -->
    <script src="${pageContext.servletContext.contextPath}/themes/js/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/themes/js/superfish.js"></script>
    <script src="${pageContext.servletContext.contextPath}/themes/js/jquery.scrolltotop.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/themes/js/jquery-ui.min.js"></script>
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/respond.min.js"></script>
    <![endif]-->
    <style>
        .span3 img{
            width: 270px;
            height: 270px;
        }
    </style>
</head>
<body onload="start()">
<jsp:include page="header/header.jsp"/>
<div id="wrapper" class="container">
    <jsp:include page="header/header-section.jsp"/>
    <section  class="homepage-slider" id="home-slider">
        <div class="flexslider">
            <ul class="slides">
                <li>
                    <img src="${pageContext.request.contextPath}/themes/images/carousel/slider_3.jpg" alt="anh bia 1" style="width: 100%;height: 300px"/>
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
<%--    <section class="header_text">--%>
<%--        Website chúng tôi chuyên cung cấp các dịch vụ uy tín và mới nhất trên thị trường hiện nay để phục vụ cho mọi khách hàng có nhu cầu mua và bán thông qua hình thức đấu giá trực tuyến--%>
<%--        <br/>Hãy ghé thăm trang web của chúng tôi và đừng bỏ lỡ các dịch vụ mới nhé!--%>
<%--    </section>--%>
    </section>
    <section class="main-content">

        <div class="row">
            <div class="span9" style="width: 100%">
                <h4 class="title" align="center" style="font-size: 19px"><span class="text"><strong>Danh sách</strong> sản phẩm</span></h4>
                <ul class="thumbnails listing-products">
                    <c:forEach var="auction" items="${auctionList}">
                        <li class="span3">
                            <div class="product-box">

                                <span class="sale_tag"></span>
                                <p><a href="${pageContext.request.contextPath}/productDetail/${auction.id}"><img src="/getImage/${auction.goods.imageList.get(0).url}" alt="" /></a></p>
                                <a href="${pageContext.request.contextPath}/productDetail/${auction.id}" class="title">${auction.goods.goodName}</a><br/>
                                <a href="${pageContext.request.contextPath}/productDetail/${auction.id}" class="bidMax">${auction.maxBid} VNĐ</a><br/>
                                <a href="${pageContext.request.contextPath}/productDetail/${auction.id}" class="time")>
                                    <span id="h${auction.id}">${auction.hours}</span> :
                                    <span id="m${auction.id}">${auction.minutes}</span> :
                                    <span id="s${auction.id}">${auction.seconds}</span>
                                    <span id="tb${auction.id}"></span>
                                </a>

                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <c:forEach var="i" begin="1" end="${auctionList.size()}" step="1">
                    <input type="hidden" id="id${i}" value="${auctionList.get(i-1).id}">
                </c:forEach>
            </div>
            <script type="text/javascript">
                $("#menu-filter").accordion();
            </script>
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
</script>
<script language="javascript"  >

    var timeout = null; // Timeout
    function start(){
        var i;
        for(i=1; i<=${auctionList.size()}; i++)
        {
            var idString = "id" + i.toString();
            var autionId=document.getElementById(idString).value;

            var hString="h"+autionId.toString();
            var mString="m"+autionId.toString();
            var sString ="s"+autionId.toString();
            var h = document.getElementById(hString).innerHTML; // Giờ
            var m = document.getElementById(mString).innerHTML; // Phút
            var s = document.getElementById(sString).innerHTML; // Giây
            //var k= {auctionList1.get(i).id};  auctionList1.get(i).id

            s--;
            if (s === -1){
                m -= 1;
                s = 59;
            }
            if (m === -1){
                h -= 1;
                m = 59;
            }

            if (h === -1){
                clearTimeout(timeout);
                stop();
                return false;
            }
            // alert(s);
            /*BƯỚC 1: HIỂN THỊ ĐỒNG HỒ*/
            document.getElementById(hString).innerText = h.toString();
            document.getElementById(mString).innerText = m.toString();
            document.getElementById(sString).innerText = s.toString();
            //alert(s);
        }
        /*BƯỚC 1: GIẢM PHÚT XUỐNG 1 GIÂY VÀ GỌI LẠI SAU 1 GIÂY */
        timeout = setTimeout(function(){
            start();
        }, 1000);
        setTimeout( click, 0);
    }
    //});
    function stop(){
        for(i=1; i<=${auctionList.size()}; i++)
        {
            var tb = 'Kết Thúc...'
            var idString = "id" + i.toString();
            var autionId=document.getElementById(idString).value;
            var tbString="tb"+autionId.toString();
            document.getElementById(tbString).innerText = tb.toString();
        }
    }

</script>
</body>
</html>
