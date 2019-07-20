<%@ page import="java.util.*" import="entity.Car" %>
<%--
  Created by IntelliJ IDEA.
  User: GOLD
  Date: 2019/6/8
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>购物车</title>
    <link href="css/myCar.css" type="text/css" rel="stylesheet"/>
    <link href="css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/myCar.js"></script>
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
<div id="conten " class="public-container">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="shopping">
        <form action="ServletOrder" method="post" name="myform">
            <tr>
                <td class="title_2" colspan="2"><h4>商品列表</h4></td>
                <td class="title_3"><h4>价格</h4></td>
                <td class="title_4"><h4>数量</h4></td>
                <td class="title_5"><h4>操作</h4></td>
            </tr>
            <tr>
                <td colspan="8" class="line"></td>
            </tr>
            <script>
                var i = 0;
                var c_id = new Array();
            </script>
            <%
                Object object = request.getAttribute("cars");
                ArrayList<Car> cars = (ArrayList<Car>) object;
                session.setAttribute("car_list", cars);
                session.setAttribute("User_id", u_id);
                double total = 0;
                for (Car car : cars) {
            %>
            <tr>
            <tr>
                <td colspan="8" class="shopInfo">
                    <sapn style="color:red;">店铺：</sapn>
                    <a><%=car.getB_name() %>
                    </a></td>
            </tr>
            <script>
                c_id[i] =<%=car.getC_id()%>;
                i++;
            </script>
            <td class="cart_td_2"><img src="<%=car.getPicture() %>" alt="shopping" style="width: 80px;height: 100px"/>
            </td>
            <td class="cart_td_3">名称：<%=car.getG_name() %><br>类型：<%=car.getG_type() %><br><br>
            </td>
            <td class="cart_td_4"><input id="<%=car.getC_id() %>" type="text" readonly="readonly"
                                         value="<%=car.getPrice() %>"
                                         style="width:50px;border:none;background: #e2f2ff"/>
            </td>
            <td class="cart_td_5"><input readonly="readonly" name="" type="text" value="<%=car.getC_amount() %>"
                                         style="width:30px;border:none; text-align: center; color:yellowgreen;background: #e2f2ff"/>
            </td>
            <td class="cart_td_6"><a href="ServletDelete?Delete=<%=car.getC_id()%>">移除</a></td>
            </tr>
            <%
                    int n = car.getC_amount();
                    double price = car.getPrice() * n;
                    total += price;
                }
            %>
            <tr>
                <td colspan="5" class="shopend"><span style="font-size:20px;display: block;margin-top: 100px">商品总价（不含运费）：<%=total %>元<br/>
                    <input type="submit" value="提交订单" style="width: 70px;height: 40px"/>
                </td>
            </tr>
        </form>
    </table>
</div>
</body>
</html>
