<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/13/2020
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="laptop" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <!-- Custom CSS -->
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/morris.css" type="text/css"/>
    <!-- Graph CSS -->
    <link href="${pageContext.servletContext.contextPath}/css/font-awesome.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="${pageContext.servletContext.contextPath}/js/jquery-2.1.4.min.js"></script>
    <!-- //jQuery -->
    <!-- <link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'/>
    <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'> -->
    <!-- lined-icons -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/icon-font.min.css" type='text/css' />

    <!-- tables -->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/table-style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/basictable.css" />
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.basictable.min.js"></script>
    <style>
        .row h4{
            text-align: center;
            color: #9d0000;
        }
    </style>
</head>
<body>
<div class="page-container">
    <!--/content-inner-->
    <div class="left-content">
        <div class="mother-grid-inner">
            <!--header start here-->
            <div class="header-main">



                <jsp:include page="../header-staff/header1.jsp"/>

                <div class="clearfix"> </div>
            </div>
            <!--heder end here-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/staff/home">Trang chủ</a></li>
            </ol>

            <!-- grids -->
            <div class="grids">



                <div class="agile-calendar-grid">
                    <div class="page">

                        <div class="w3l-calendar-left">
                            <div class="calendar-heading">
                                <!-- 									<p>Noi dung</p>
                                 -->
                                <div class="agile-tables">
                                    <div class="w3l-table-info">
                                        <div class="row">
                                            <h4>${mes}</h4>
                                            <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">

                                            </div>
                                            <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
                                                <mvc:form modelAttribute="accountStaff" action="${pageContext.request.contextPath}/staff/saveStaffAccount" method="post">
                                                    <div class="form-group">
                                                        <label for="ma">Username</label>
                                                        <mvc:input path="userName" class="form-control" required="true" placeholder="Nhập username"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="ten">Password</label>
                                                        <mvc:password path="passWord" class="form-control" required="true" placeholder="Nhập password"/>
                                                    </div>
                                                    <button type="submit" class="btn btn-default">Thêm tài khoản</button>
                                                </mvc:form>
                                            </div>
                                            <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">

                                            </div>
                                        </div>
                                    </div>

                                    <!-- Modal -->
                                    <div class="modal fade" id="myModal" role="dialog">
                                        <div class="modal-dialog">

                                            <!-- Modal content-->
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Cập nhật thông tin sản phẩm</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Mời bạn nhập thông tin sản phẩm: </p>
                                                    <form action="">
                                                        <div class="form-group">
                                                            <label for="ma">Mã sản phẩm:</label>
                                                            <input type="text" class="form-control" id="ma">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="ten">Tên sản phẩm:</label>
                                                            <input type="text" class="form-control" id="ten">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="comment">Mô tả:</label>
                                                            <textarea class="form-control" rows="5" id="comment"></textarea>
                                                        </div>

                                                        <div class="form-group">
                                                            <label for="loai">Loại:</label>
                                                            <input type="textarea" class="form-control" id="loai">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="nsx">Nhà sản xuất:</label>
                                                            <input type="text" class="form-control" id="nsx">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="gia">Giá bán:</label>
                                                            <input type="textarea" class="form-control" id="gia">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="xuatxu">Xuất xứ:</label>
                                                            <input type="textarea" class="form-control" id="xuatxu">
                                                        </div>
                                                        <button type="submit" class="btn btn-default">Cập nhật</button>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <!-- end mymodal -->
                                    <!-- sanpham -->
                                    <!-- Modal -->
                                    <div class="modal fade" id="myModal1" role="dialog">
                                        <div class="modal-dialog">

                                            <!-- Modal content-->
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Bạn có chắc chắn muốn xóa hay không?</h4>
                                                </div>
                                                <div class="modal-body">

                                                    <form action="">
                                                        <button type="submit" class="btn btn-default" style="margin-left: 43%;">Xóa
                                                        </button>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <!-- end modal sanpham -->
                                </div>
                            </div>
                        </div>

                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>
            <!-- //grids -->
            <!--photoday-section-->


            <div class="col-sm-4 wthree-crd">
                <div class="card">
                    <div class="card-body">

                    </div>
                </div>
            </div>

            <div class="clearfix"></div>

            <!--//photoday-section-->
            <!-- script-for sticky-nav -->
            <script>
                $(document).ready(function() {
                    var navoffeset=$(".header-main").offset().top;
                    $(window).scroll(function(){
                        var scrollpos=$(window).scrollTop();
                        if(scrollpos >=navoffeset){
                            $(".header-main").addClass("fixed");
                        }else{
                            $(".header-main").removeClass("fixed");
                        }
                    });

                });
            </script>
            <!-- /script-for sticky-nav -->
            <!--inner block start here-->
            <div class="inner-block">

            </div>
            <!--inner block end here-->
            <!--copy rights start here-->
            <div class="copyrights">
                <p>© 2018 N5QPT. All Rights Reserved  Design by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
            </div>
            <!--COPY rights end here-->
        </div>
    </div>
    <!--//content-inner-->
    <!--/sidebar-menu-->
    <jsp:include page="../header-staff/header.jsp"/>
    <div class="clearfix"></div>
</div>
<script>
    var toggle = true;

    $(".sidebar-icon").click(function() {
        if (toggle)
        {
            $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
            $("#menu span").css({"position":"absolute"});
        }
        else
        {
            $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
            setTimeout(function() {
                $("#menu span").css({"position":"relative"});
            }, 400);
        }

        toggle = !toggle;
    });
</script>
<!--js -->
<script src="${pageContext.servletContext.contextPath}/js/jquery.nicescroll.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
<!-- /Bootstrap Core JavaScript -->
<!-- morris JavaScript -->
<script src="${pageContext.servletContext.contextPath}/js/raphael-min.js"></script>

</body>
</html>