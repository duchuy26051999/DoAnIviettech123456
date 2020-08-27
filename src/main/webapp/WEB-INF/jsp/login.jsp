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
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
    <!-- bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="themes/css/bootstrappage.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min.css">
    <!-- <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min2.css"> -->
    <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min3.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
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
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
<jsp:include page="header/header.jsp"/>
<div id="wrapper" class="container">
    <jsp:include page="header/header-section.jsp"/>
    <section  class="homepage-slider" id="home-slider">
        <div class="flexslider">
            <ul class="slides">
                <li>
                    <img src="themes/images/carousel/slider_3.jpg" alt="anh bia 1" style="width: 100%;height: 300px"/>
                </li>
                <li>
                    <img src="themes/images/carousel/slider_8.jpg" alt="anh bia 2" style="width: 100%;height: 300px"/>
                </li>
                <li>
                    <img src="themes/images/carousel/slider_9.jpg" alt="anh bia 3" style="width: 100%;height: 300px"/>
                </li>
                <li>
                    <img src="themes/images/carousel/slider_6.jpg" alt="anh bia 4" style="width: 100%;height: 300px" />
                </li>
                <li>
                    <img src="themes/images/carousel/slider_7.jpg" alt="anh bia 5" style="width: 100%;height: 300px" />
                </li>
            </ul>
        </div>
    </section>
    <br/>
    <br/>
    </section>
    <section class="main-content">
        <div class="row">
            <h4 class="title" align="center" style="width: 99%;margin-left: 20px;font-size: 20px"><span class="text"><strong>Đăng nhập</strong></span></h4>
            <div class="span5" style="width: 99%;margin-left: 21px;">
                <div style="width: 400px;margin-left: 380px;background: #00bcd4;text-align: center;min-height: 280px;margin-bottom: 10px;border-radius: 50px">

                <div style="color: red">
                    <c:if test="${error == true}">
                        Login Failed!!!<br />
                        Reason :
                        <c:if test="${sessionScope!= null and sessionScope['SPRING_SECURITY_LAST_EXCEPTION'] != null}">
                            <label>${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</label>
                        </c:if>
                    </c:if>
                </div>
                <form action="<c:url value="j_spring_security_check"/>" method="post">
                    <fieldset style="margin-top: 10px">
                        <div class="control-group">
                            <label class="control-label" style="margin-top: 25px;font-size: 15px">Tài khoản</label>
                            <div class="controls">
                                <input type="text" name="username" placeholder="Nhập Username" class="input-xlarge" required="true"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" style="font-size: 15px">Mật khẩu</label>
                            <div class="controls">

                                <input type="password" name="password" placeholder="Nhập Password" path="passWord" class="input-xlarge" required="true"/>
                            </div>
                        </div>
                        <label>
                            <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Nhớ mật khẩu của tôi
                        </label>
                        <div class="control-group">
                            <input tabindex="3" class="btn btn-primary large" type="submit" id="submit" value="Đăng nhập">
                            <p class="reset">Đăng ký <a tabindex="4" href="/register" title="Bạn chưa có tài khỏa đăng nhập">tài khoản?</a></p>
                        </div>
                    </fieldset>
                </form>
            </div>
            </div>

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
<script src="themes/js/common.js"></script>
<script src="themes/js/jquery.flexslider-min.js"></script>
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
<script type="text/javascript">
    var attempt=3;
    // function validate(){
    //     var username=document.getElementById("username").value;
    //     var password=document.getElementById("password").value;
    //     if(username=="DHP" && password=="DHP")
    //     {
    //         alert("Login successfully!");
    //         window.location="/index.html";
    //         return false;
    //     }
    //     else{
    //         attempt--;
    //         alert("Fail to login");
    //     }
    //     if(attempt==0){
    //         document.getElementById("username").disabled=true;
    //         document.getElementById("password").disabled=true;
    //         document.getElementById("submit").disabled=true;
    //         return false;
    //     }
    // }
    function validate1(){
        var username=document.getElementById("username1").value;
        var password=document.getElementById("password1").value;
        var email=document.getElementById("email1").value;
        if(username=="DHP" && password=="DHP" && email=="123@gmail.com")
        {
            alert("Create account successfully!");
            window.location="index.html";
            return false;
        }
        else{
            attempt--;
            alert("Fail to login");
        }
        if(attempt==0){
            document.getElementById("username1").disabled=true;
            document.getElementById("password1").disabled=true;
            document.getElementById("email1").disabled=true;
            document.getElementById("submit1").disabled=true;
            return false;
        }
    }
</script>
</body>
</html>