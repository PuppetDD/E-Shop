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
    <title>订单提交</title>
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
    <form action="ServletOrdersUpdate" method="post">
        <h3>收获地址</h3>
        <ul class="ul-list">
            <%
                Object object = request.getAttribute("user_address");
                ArrayList<User> users = (ArrayList<User>) object;
                User u = users.get(0);
            %>
            <li class="list"><input type="radio" name="address" checked="checked" value=<%=u.getU_id() %>/>
                <div class="address">
                    <ul class="ul-address">
                        <li><em style="color:saddlebrown;">收货人：</em><%=u.getU_name()%>
                        </li>
                        <li><em style="color:saddlebrown;">收获地址:</em><%=u.getU_address() %>
                        </li>
                        <li><em style="color:saddlebrown;">电话:</em><%=u.getU_number() %>
                        </li>
                    </ul>
                </div>
            </li>
        </ul>
        <h3 style="border-bottom: red solid 10px;">商品</h3>
        <br>
        <%
            Object object2 = request.getAttribute("car_list");
            ArrayList<Car> c_list = (ArrayList<Car>) object2;
            session.setAttribute("updateOrders", c_list);
            double total = 0;
            for (Car c : c_list) {
                System.out.println("图片:" + c.getPicture());
        %>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="shopping">
            <tr>
                <td class="cart_td_2"><img src="<%=c.getPicture() %>" alt="shopping"
                                           style="width:50px;border:none;background: #e2f2ff"/>
                </td>
                <td class="cart_td_3">商品名：<%=c.getG_name()%>
                </td>
                <td class="cart_td_4">价格：<input name="price" type="text" readonly="readonly" value="<%=c.getPrice() %>"
                                                style="width:50px;border:none;background: #e2f2ff"/>
                </td>
                <td class="cart_td_5">数量：<%=c.getC_amount() %>
                </td>
            </tr>
        </table>
        <%
                total += c.getPrice() * c.getC_amount();
            }%>
        <br><br>
        <h3>请选择支付方式</h3>
        <ul class="ul-list1">
            <li class="list"><input type="radio" name="method" value="支付宝" checked="checked"/><img src="img/alipay.jpg "
                                                                                                   style="width: 100px;height: 50px;margin-right: 20px">
            </li>
            <li class="list"><input type="radio" name="method" value="微信支付"/><img src="img/weixin.jpg "
                                                                                  style="width: 100px;height: 50px;margin-right: 20px">
            </li>
        </ul>
        <div style="float: right;width: 200px;font-size: 18px">
            <td> 应支付：<input name="price" type="text" style="width:40px;border:none;"/><%=total %>元</td>
            <input type="submit" value="确认订单" style="background: red;width: 100px;height: 40px;margin-left: 100px"/>
        </div>
    </form>
</div>
</body>
</html>
