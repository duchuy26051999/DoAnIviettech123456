<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/13/2020
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="laptop" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- Bootstrap Core CSS -->
    <link href="../../../../resources/static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <!-- Custom CSS -->
    <link href="../../../../resources/static/css/style.css" rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="../../../../resources/static/css/morris.css" type="text/css"/>
    <!-- Graph CSS -->
    <link href="../../../../resources/static/css/font-awesome.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="../../../../resources/static/js/jquery-2.1.4.min.js"></script>
    <!-- //jQuery -->
    <!-- <link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'/>
    <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'> -->
    <!-- lined-icons -->
    <link rel="stylesheet" href="../../../../resources/static/css/icon-font.min.css" type='text/css' />

    <!-- tables -->
    <link rel="stylesheet" type="text/css" href="../../../../resources/static/css/table-style.css" />
    <link rel="stylesheet" type="text/css" href="../../../../resources/static/css/basictable.css" />
    <script type="text/javascript" src="../../../../resources/static/js/jquery.basictable.min.js"></script>

</head>
<body>
<h1>hello Admin ${message}</h1>
<%--<div class="page-container">--%>
<%--    <!--/content-inner-->--%>
<%--    <div class="left-content">--%>
<%--        <div class="mother-grid-inner">--%>
<%--            <!--header start here-->--%>
<%--            <div class="header-main">--%>



<%--            <jsp:include page="../header-admin/header1.jsp"/>--%>

<%--                <div class="clearfix"> </div>--%>
<%--            </div>--%>
<%--            <!--heder end here-->--%>
<%--            <ol class="breadcrumb">--%>
<%--                <li class="breadcrumb-item"><a href="index1.html">Trang chủ</a> <i class="fa fa-angle-right"></i></li>--%>
<%--            </ol>--%>

<%--            <!-- grids -->--%>
<%--            <div class="grids">--%>



<%--                <div class="agile-calendar-grid">--%>
<%--                    <div class="page">--%>

<%--                        <div class="w3l-calendar-left">--%>
<%--                            <div class="calendar-heading">--%>
<%--                                <!-- 									<p>Noi dung</p>--%>
<%--                                 -->--%>
<%--                                <div class="agile-tables">--%>
<%--                                    <div class="w3l-table-info">--%>
<%--                                        <h2>Danh mục chức năng của admin:</h2>--%>
<%--                                        <div class="row">--%>
<%--                                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">--%>

<%--                                            </div>--%>
<%--                                            <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">--%>
<%--                                                <div class="list-group">--%>
<%--                                                    <a href="index1.html" class="list-group-item active">Trang chủ</a>--%>
<%--                                                    <a href="sanpham.html" class="list-group-item">Quản lý sản phẩm</a>--%>
<%--                                                    <a href="sanpham1.html" class="list-group-item">Quản lý loại sản phẩm</a>--%>
<%--                                                    <a href="sanpham2.html" class="list-group-item">Quản lý nhà sản xuất</a>--%>
<%--                                                    <a href="donhang.html" class="list-group-item">Quản lý đơn hàng</a>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">--%>

<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <div class="clearfix"> </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- //grids -->--%>
<%--            <!--photoday-section-->--%>


<%--            <div class="col-sm-4 wthree-crd">--%>
<%--                <div class="card">--%>
<%--                    <div class="card-body">--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="clearfix"></div>--%>

<%--            <!--//photoday-section-->--%>
<%--            <!-- script-for sticky-nav -->--%>
<%--            <script>--%>
<%--                $(document).ready(function() {--%>
<%--                    var navoffeset=$(".header-main").offset().top;--%>
<%--                    $(window).scroll(function(){--%>
<%--                        var scrollpos=$(window).scrollTop();--%>
<%--                        if(scrollpos >=navoffeset){--%>
<%--                            $(".header-main").addClass("fixed");--%>
<%--                        }else{--%>
<%--                            $(".header-main").removeClass("fixed");--%>
<%--                        }--%>
<%--                    });--%>

<%--                });--%>
<%--            </script>--%>
<%--            <!-- /script-for sticky-nav -->--%>
<%--            <!--inner block start here-->--%>
<%--            <div class="inner-block">--%>

<%--            </div>--%>
<%--            <!--inner block end here-->--%>
<%--            <!--copy rights start here-->--%>
<%--            <div class="copyrights">--%>
<%--                <p>© 2018 N5QPT. All Rights Reserved | Design by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>--%>
<%--            </div>--%>
<%--            <!--COPY rights end here-->--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <!--//content-inner-->--%>
<%--    <!--/sidebar-menu-->--%>
<%--    <jsp:include page="../header-admin/header.jsp"/>--%>
<%--    <div class="clearfix"></div>--%>
<%--</div>--%>
<%--<script>--%>
<%--    var toggle = true;--%>

<%--    $(".sidebar-icon").click(function() {--%>
<%--        if (toggle)--%>
<%--        {--%>
<%--            $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");--%>
<%--            $("#menu span").css({"position":"absolute"});--%>
<%--        }--%>
<%--        else--%>
<%--        {--%>
<%--            $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");--%>
<%--            setTimeout(function() {--%>
<%--                $("#menu span").css({"position":"relative"});--%>
<%--            }, 400);--%>
<%--        }--%>

<%--        toggle = !toggle;--%>
<%--    });--%>
<%--</script>--%>
<%--<!--js -->--%>
<%--<script src="../../../../resources/static/js/jquery.nicescroll.js"></script>--%>
<%--<script src="../../../../resources/static/js/scripts.js"></script>--%>
<%--<!-- Bootstrap Core JavaScript -->--%>
<%--<script src="../../../../resources/static/js/bootstrap.min.js"></script>--%>
<%--<!-- /Bootstrap Core JavaScript -->--%>
<%--<!-- morris JavaScript -->--%>
<%--<script src="../../../../resources/static/js/raphael-min.js"></script>--%>

</body>
</html>
