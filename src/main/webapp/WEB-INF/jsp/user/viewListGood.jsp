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
    <title>List goods of customer</title>
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
            background-color: #a6a5a7;
        }
        #auction table tbody tr:nth-child(even){
            background-color: #f5f5f5;
        }
        #bid table{
            width: 100%;
            border-collapse: collapse;
        }
        #bid table td, table th{
            padding: 12px 15px;
            border: 1px solid #ddd;
        }
        #bid table th{
            font-size: 1em;
            background-color: #a6a5a7;
        }
        #bid table tbody tr:nth-child(even){
            background-color: #f5f5f5;
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
    <%--    <h4><span>Cập nhật thông tin</span></h4>--%>
    </section>
    <section class="main-content">
        <div>
            <%--            <p align="center">${message}</p>--%>
            <h4 class="title" align="center" style="margin-top: -25px"><span class="text"><strong>Danh sách</strong> các mặt hàng của bạn</span></h4><br>
            <p style="color: red;margin-bottom: 45px" align="center">${message}</p>
            <div>
                <form action="${pageContext.request.contextPath}/user/myGoodsList/searchGoods/1" method="post" style="margin-left: 5px;margin-top: -45px">
                    <strong id="start" style="margin-left: 400px;float: left"><label>Nhập tên sản phẩm</label>
                        <input type="text" name="goodsName" required placeholder="Nhập tên hàng để tìm kiếm"/></strong>
                    <%--                    <strong id="end" style="margin-left: 20px;float: left"><label>Ngày kết thúc</label>--%>
                    <%--                        <input type="datetime-local" name="dateEnd" required/></strong>--%>
                    <strong style="margin-left: 10px;margin-top: 25px;float: left">
                        <input type="submit" value="Tìm kiếm" style="height: 30px;font-size: 14px; background: #fbb450">
                    </strong>
                </form>
                <strong style="margin-left: 10px;margin-top: 25px;float: left">
                    <a href="/user/listGood"><input type="submit" value="Xem tất cả" style="height: 30px;font-size: 14px; background: #fbb450"></a>
                </strong>
            </div>
            <div class="tab-pane" id="home">
                <div class="tab-pane active" id="bid">
                    <table>
                        <thead style="background: #c7c4c4">
                        <tr style="height: 40px">
                            <th width="15%">Tên sản phẩm</th>
                            <th width="37%">Mô tả</th>
                            <th width="10%">Thẻ loại</th>
                            <th width="10%">Ảnh chính</th>
                            <th width="10%">Trạng thái</th>
                            <th width="10%">Cập nhật</th>
                            <th width="8%">Xóa</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="good" items="${good.pageList}">
                            <tr>
                                <td>${good.goodName}</td>
                                <td>${good.goodDescription}</td>
                                <td>${good.category.category}</td>
                                <td><img src="${pageContext.request.contextPath}/getImage/${good.getUrlImage()}" width="75px" height="60pxpx"></td>
                                <td>${good.status}</td>
                                <td><a href="${pageContext.request.contextPath}/user/updateGood/${good.id}">Update</a></td>
                                <td><a href="${pageContext.request.contextPath}/user/deleteGood/${good.id}">Delete</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div>
                    <ul class="pagination">
                        <li class="${currentIndex == 1}? 'page-item disabled' : 'page-item'">
                            <a class="page-link" href="${pageContext.request.contextPath}/user/myListDemo/page/1"><input type="button" value="First"></a>
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
            </div>
                <br/>
            <button type="button" style="background: #f2f2f2; margin-left: 280px;height: 35px;font-size: 15px;margin-top: 20px">
                <a href="/user/formNewGood">Tạo mới hàng hóa</a></button>
            <button type="button" style="background: #f2f2f2; margin-left: 30px;height: 35px;font-size: 15px;margin-top: 20px">
                <a href="/user/formNewAuction">Tạo phiên đâu giá</a></button>
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

