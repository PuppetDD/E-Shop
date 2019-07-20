<%@ page import="java.util.ArrayList" import="entity.*" %>
<%--
  Created by IntelliJ IDEA.
  User: GOLD
  Date: 2019/6/8
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>订单</title>
    <meta http-equiv="Content-Type" content="text/html; utf-8">
    <link href="css/zhifu.css" type="text/css" rel="stylesheet"/>
    <link href="css/myCar.css" type="text/css" rel="stylesheet"/>
    <link href="css/style.css" type="text/css" rel="stylesheet"/>
    <link href="css/reset.css" type="text/css" rel="stylesheet"/>
</head>
<body>
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
            <%if (name == "null") {%>
            <li class="item"><a href="login.jsp" target="_parent"><span class="header-username-color">登录</span></a></li>
            <li class="item"><a href="register.jsp" target="_parent"><span class="header-username-color">注册</span></a>
            </li>
            <%} %>
            <%if (name != "null") {%>
            <li class="item"><a href=""><span class="header-username-color"><%=name %></span></a></li>
            <li class="item"><a href="ServletCar"><span class="header-username-color">购物车</span></a></li>
            <li class="item"><a href="ServletOrders?u_id="<%=u_id%>><span class="header-username-color">订单</span></a>
            </li>
            <li class="item"><a href="ServletSign" method="get"><span class="header-username-color">退出</span></a></li>
            <%} %>
        </ul>
    </div>
</div>
<div class="public-container">
    <h3 style="border-bottom: red solid 10px;">订单详情</h3>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="shopping">
        <tr>
            <td class="title_1"><h4>订单编号</h4></td>
            <td class="title_2" style="text-align:center;width:600px;"><h4>商品</h4></td>
            <td class="title_3"><h4>价格</h4></td>
            <td class="title_4"><h4>数量</h4></td>
            <td class="title_5"><h4>支付状态</h4></td>
        </tr>
        <%
            Object object1 = request.getAttribute("orders");
            ArrayList<Order> o_list = (ArrayList<Order>) object1;
            for (Order o : o_list) {
                System.out.println(o.getG_name());
        %>
        <tr>
            <td class="cart_td_3"><%=o.getO_id()%>
            </td>
            <td class="cart_td_3"><%=o.getG_name()%>
            </td>
            <td class="cart_td_4"><span style="text-align: center;margin-left: 20px"><%=o.getO_price()%> </span></td>
            <td class="cart_td_5"><span style="text-align: center;"><%=o.getO_amount()%></span></td>
            <td class="cart_td_5"><span style="text-align: center;margin-left: 5px"><%=o.getStatus()%></span></td>
            <td class="cart_td_6"><a href="ServletOrdersDelete?OrdersDelete=<%=o.getO_id()%>"><span
                    style="font-size:12px">删除</span></a></td>
        </tr>
        <%}%>
    </table>
</div>
</body>


</html>