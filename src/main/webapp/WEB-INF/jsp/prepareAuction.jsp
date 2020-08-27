<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/13/2020
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8">
    <meta http-equiv="refresh" content="10">
    <title>Auction Prepare</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
    <!-- bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min.css">
    <!-- <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min2.css"> -->
    <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min3.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
    <link href="themes/css/bootstrappage.css" rel="stylesheet"/>

    <!-- global styles -->
    <link href="themes/css/flexslider.css" rel="stylesheet"/>
    <link href="themes/css/main.css" rel="stylesheet"/>

    <!-- scripts -->
    <script src="themes/js/jquery-1.7.2.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="themes/js/superfish.js"></script>
    <script src="themes/js/jquery.scrolltotop.js"></script>
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <style>
        .span3 img{
            width: 270px;
            height: 270px;
        }
    </style>
</head>
<body onload="start1()">
<jsp:include page="header/header.jsp"/>
<div id="wrapper" class="container">
    <jsp:include page="header/header-section.jsp"/>
    <section  class="homepage-slider" id="home-slider">
        <div class="flexslider">
            <ul class="slides">
                <li>
                    <img src="themes/images/carousel/slider_3.jpg" alt="anh bia 1" style="width: 100%;height: 300px"/>
                    <%--                    <div class="intro">--%>
                    <%--                        <h1>Laptop mua online</h1>--%>
                    <%--                        <p><span>Giảm thêm đến 1.000.000đ</span></p>--%>
                    <%--                        <p><span>Áp dụng kèm theo khuyến mãi trả thẳng khác</span></p>--%>
                    <%--                    </div>--%>
                </li>
                <li>
                    <img src="themes/images/carousel/slider_8.jpg" alt="anh bia 2" style="width: 100%;height: 300px"/>
                </li>
                <li>
                    <img src="themes/images/carousel/slider_7.jpg" alt="anh bia 3" style="width: 100%;height: 300px"/>
                </li>
                <li>
                    <img src="themes/images/carousel/slider_6.jpg" alt="anh bia 4" style="width: 100%;height: 300px" />
                </li>
                <li>
                    <img src="themes/images/carousel/slider_05.jpg" alt="anh bia 5" style="width: 100%;height: 300px" />
                </li>
            </ul>
        </div>
    </section>
    <section class="main-content">
        <div class="row">
            <div class="span12">
                <br/>
                <div class="row">
                    <div class="span12">
                        <h4 class="title">

									<span class="pull-left"><span class="text"><span class="line"><a href="product_detail.html"><strong>Các sản phẩm sắp đấu giá</strong></a></span></span>
									</span>

                        </h4>
                        <div id="myCarousel-2" class="myCarousel carousel slide">
                            <div class="carousel-inner">
                                <div class="active item">
                                    <ul class="thumbnails"style="margin-left: -4px">
                                        <c:forEach var="auction" items="${auctionList1}">
                                            <li class="span3">
                                                <div class="product-box">

                                                    <span class="sale_tag"></span>
                                                    <p><img src="/getImage/${auction.goods.imageList.get(0).url}" alt="" /></p>
                                                    <a href="" class="title">${auction.goods.goodName}</a><br/>
                                                    <a href="" class="bidMax">${auction.maxBid} VNĐ</a><br/>
                                                    <a href="" class="time")>
                                                        <span>Đấu giá sau </span>
                                                        <span id="h${auction.id}">${auction.nextHours}</span> :
                                                        <span id="m${auction.id}">${auction.nextMinutes}</span> :
                                                        <span id="s${auction.id}">${auction.nextSeconds}</span>
                                                        <span id="tb${auction.id}"></span>
                                                    </a>

                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <c:forEach var="i" begin="1" end="${auctionList1.size()}" step="1">
                                        <input type="hidden" id="id${i}" value="${auctionList1.get(i-1).id}">
                                    </c:forEach>
                                </div>
                                <%--                                <!-- -->--%>

                            </div>
                        </div>
                    </div>
                </div>
                <br/>

            </div>

        </div>
    </section>
    <div class="row feature_box"><span id="s${auctionRecently.id}">${auctionRecently.nextSeconds}</span>
        <div class="span4">
            <div class="service">
                <div class="responsive">
                    <img src="themes/images/feature_img_2.png" alt="" />
                    <h4>DỊCH VỤ <strong>TIỆN ÍCH</strong></h4>
                    <p>Các sản phẩm được tham gia đấu giá, thươnng lượng phù hợp giá cả và hợp xu hướng người dùng hiện nay.</p>
                </div>
            </div>
        </div>
        <div class="span4">
            <div class="service">
                <div class="customize">
                    <img src="themes/images/feature_img_1.png" alt="" />
                    <h4>GIAO HÀNG <strong>MIỄN PHÍ</strong></h4>
                    <p>Sản phầm được giao hàng nhanh, gọn bằng nhiều hình thức nhằm đáp ứng tốt yêu cầu của khách hàng.</p>
                </div>
            </div>
        </div>
        <div class="span4">
            <div class="service">
                <div class="support">
                    <img src="themes/images/feature_img_3.png" alt="" />
                    <h4>PHỤC VỤ <strong>24/7</strong></h4>
                    <p>Khách hàng luôn là ưu tiên hàng đầu và được phục vụ 24/7.</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="themes/js/common.js"></script>
<script src="themes/js/jquery.flexslider-min.js"></script>
<script language="javascript"  >

    var timeout = null; // Timeout
    function start1(){
        var i;
        for(i=1; i<=${auctionList1.size()}; i++)
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
                stop1(idString);
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
            start1();
        }, 1000);
        setTimeout( click, 0);

    }
    //});
    function stop1(idString){
        for(i=1; i<=${auctionList1.size()}; i++)
        {
            var tb = 'Bắt đầu...'
            // var idString = "id" + i.toString();
            var autionId=document.getElementById(idString).value;
            var tbString="tb"+autionId.toString();
            document.getElementById(tbString).innerText = tb.toString();
        }
    }
</script>
<script type="text/javascript">
    $(function() {
        $(document).ready(function () {
            $('.flexslider').flexslider({
                animation: "fade",
                slideshowSpeed: 4000,
                animationSpeed: 600,
                controlNav: false,
                directionNav: true,
                controlsContainer: ".flex-container" // the container that holds the flexslider
            });
        })
    });
</script>

</body>
</html>
