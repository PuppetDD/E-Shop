<%@ page import="entity.*" import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: GOLD
  Date: 2019/6/8
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>商品详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="js/details.js"></script>
</head>
<body>
<%
    String name = null;
    String u_id = null;
    if (session.getAttribute("User") != null) {
        name = session.getAttribute("User").toString();
        u_id = session.getAttribute("User_id").toString();
    }
    if (request.getParameter("insert") != null) {
        if (request.getParameter("insert").equals("1")) {
%>
<script type="text/javascript">carinsert();</script>
<%
        }
    }
    if (request.getParameter("insert") != null) {
        if (request.getParameter("insert").equals("0")) {
            //重复商品不能添加
        }
    }
%>
<input id="name" type="text" style="display:none" value="<%=session.getAttribute("User")%>"/><!-- 判断是否登录  购物车 -->
<div class="header">
    <div class="public-container clearfloat">
        <div class="header-logo" onclick="window.open('ServletIndex','_self')">
        </div>
        <ul class="header-username clearfloat">
            <%if (name == null) {%>
            <li class="item"><a href="login.jsp" target="_parent"><span class="header-username-color">登录</span></a></li>
            <li class="item"><a href="register.jsp" target="_parent"><span class="header-username-color">注册</span></a>
            </li>
            <%} %>
            <%if (name != null) {%>
            <li class="item"><a href=""><span class="header-username-color"><%=name %></span></a></li>
            <li class="item"><a href="ServletCar"><span class="header-username-color">购物车</span></a></li>
            <li class="item"><a href="ServletOrders?u_id="<%=u_id%>><span class="header-username-color">订单</span></a>
            </li>
            <li class="item"><a href="ServletSign" method="get"><span class="header-username-color">退出</span></a></li>
            <%} %>
        </ul>
    </div>
</div>
<div class="banner-header">
</div>
<%
    Object object = request.getAttribute("Details");
    ArrayList<Goods> details = (ArrayList<Goods>) object;
    Goods g = details.get(0);
    Car car = new Car();
    car.setG_id(g.getG_id());
    car.setG_name(g.getG_name());
    car.setG_type(g.getG_type());
    car.setB_name(g.getB_name());
    car.setU_id(u_id);
    car.setB_id(g.getB_id());
    car.setPrice(g.getG_price());
    car.setPicture(g.getPicture());
    session.setAttribute("addCar", car);
%>

<div class="public-container2">
    <div class="product1">
        <div class="product_image">
            <img class="product_image1" src="<%=g.getPicture()%>" alt="">
        </div>
        <div class="product_content">
            <ul>
                <li class="product_price">类型：<%=g.getG_type()%>
                </li>
                <li class="product_price">生厂商:<%=g.getManufacturer()%>
                </li>
                <li class="product_price">商家:<%=g.getB_name()%>
                </li>
            </ul>
        </div>
    </div>
    <!--conet -->
    <div class="tb-property">
        <div class="tr-nobdr">
            <h3>名称：<%=g.getG_name() %>
            </h3>
        </div>
        <div class="txt">
            <span class="nowprice">价格：<a><%=g.getG_price() %>元</a></span>
        </div>
        <leabel>商品属性</leabel>
        <form action="ServletAdd" method="post">
            <input type="text" style="display:none" id="flag" name="flag" value="1"/>
            <div id="userGender-input">
                <ul>
                    <li>库存：<%=g.getStock()%>
                    </li>
                </ul>
            </div>
            <div class="gcIpt">
                <span class="guT">数量</span>
                <input id="min" name="" type="button" value="-" onclick="sub();"/>
                <input readonly="readonly" id="text_box" name="c_amount" type="text" value="1"
                       style="width:30px; text-align: center; color: #0F0F0F;"/>
                <input id="add" name="" type="button" value="+" onclick="addN();"/>
            </div>
            <div class="nobdr-btns">
                <button type="submit" class="addcart hu" onclick="checkStatus();"><img src="img/shop.png" width="25"
                                                                                       height="25"/>加入购物车
                </button>
                <button type="submit" class="addcart yh" onclick="checkStatus();choice();"><img src="img/ht.png"
                                                                                                width="25" height="25"/>立即购买
                </button>
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>
