<%@ page import="entity.Goods" import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  entity.User: GOLD
  Date: 2019/5/30
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>E-Shop</title>
    <link rel="stylesheet" href="css/banner.css"/>
    <link rel="stylesheet" href="css/reset.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="css/serch.css"/>
    <link rel="stylesheet" href="css/goods.css"/>
    <link rel="stylesheet" href="css/list-header.css"/>
    <script type="text/javascript" src="js/register.js"></script>
    <script type="text/javascript" src="js/banner.js"></script>
</head>
<body>
<script type="application/x-javascript">
    var error = "<%=request.getParameter("error")%>";
    if (error == "yes") {
        alert("账号或密码错误!");
    }
</script>
<%
    String name = null;
    String u_id = null;
    if (session.getAttribute("User") != null) {
        name = session.getAttribute("User").toString();
        u_id = session.getAttribute("User_id").toString();
    }
%>
<div class="header">
    <div class="public-container clearfloat">
        <div class="header-logo" onclick="window.open('ServletIndex','_self')">
        </div>
        <ul class="header-username clearfloat">
            <%if (name == null) {%>
            <li class="item"><a href="login.jsp" target="_parent"><span class="header-username-color">登录</span></a></li>
            <li class="item"><a href="register.jsp" target="_parent"><span class="header-username-color">注册</span></a>
            </li>
            <%}%>
            <%if (name != null) {%>
            <li class="item"><a href="ServletSelf"><span class="header-username-color"><%=name %></span></a></li>
            <li class="item"><a href="ServletCar"><span class="header-username-color">购物车</span></a></li>
            <li class="item"><a href="ServletOrders?u_id="<%=u_id%>><span class="header-username-color">订单</span></a>
            </li>
            <li class="item"><a href="ServletSign" method="get"><span class="header-username-color">退出</span></a></li>
            <%} %>
        </ul>
    </div>
</div>

<div class="banner-header">
    <div class="public-container clearfloat ">
        <div class="navbar-btn " id="navbar-btn1"><a href="ServletIndex?style=1"><span
                class="navbar-btn-style">首页</span></a>
        </div>
        <div class="navbar-btn " id="navbar-btn2"><a href="ServletIndex?style=2"><span
                class="navbar-btn-style">日用品</span></a>
        </div>
        <div class="navbar-btn " id="navbar-btn3"><a href="ServletIndex?style=3"><span
                class="navbar-btn-style">电子科技</span></a>
        </div>
        <div class="navbar-btn " id="navbar-btn4"><a href="ServletIndex?style=4"><span
                class="navbar-btn-style">零食小吃</span></a>
        </div>
        <div class="container">
            <form action="ServletIndex" method="post">
                <input type="text" class="search" name="key">
                <input type="submit" class="btn" value="">
            </form>
        </div>
    </div>
</div>

<div class="index-list">
    <div class="list-information public-container1">
        <%
            Object object1 = request.getAttribute("g_list");
            ArrayList<Goods> g_list = (ArrayList<Goods>) object1;
            for (Goods g : g_list) {
        %>
        <div class="product">
            <div class="product_image">
                <img class="product_image1" src="<%=g.getPicture() %>" alt="loading">
            </div>
            <div class="product_content">
                <div class="product_title">
                    <a href="ServletDetails?g_id=<%=g.getG_id()%>">名称：<%=g.getG_name()%>
                    </a>
                </div>
                <div class="product_price">价格：<span class="price-color"><strong><%=g.getG_price() %></strong></span>元
                </div>
            </div>
        </div>
        <%}%>
        <div class="clearfloat2"></div>
    </div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<%if (request.getParameter("styleshow").equals("1") || request.getParameter("styleshow") == null) {%>
<script>changecolor();</script>
<%} %>
<%if (request.getParameter("styleshow").equals("2")) {%>
<script>changecolor2();</script>
<%} %>
<%if (request.getParameter("styleshow").equals("3")) {%>
<script>changecolor3();</script>
<%} %>
<%if (request.getParameter("styleshow").equals("4")) {%>
<script>changecolor4();</script>
<%} %>

</body>
</html>
