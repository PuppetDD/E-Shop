<%--
  Created by IntelliJ IDEA.
  User: GOLD
  Date: 2019/6/3
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Register</title>
    <meta charset="utf-8">
    <link href="css/style1.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
</head>
<body>
<div class="main">
    <form action="ServletRegister" method="post">
        <ul class="left-form">
            <h2>New Account:</h2>
            <li>
                <input type="text" name="User_id" placeholder="Account" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="text" name="User_name" placeholder="Username" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="text" name="User_number" placeholder="Phone number" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="text" name="User_mail" placeholder="Email" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="password" name="User_password" placeholder="password" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="password" name="User_password" placeholder="password" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="text" name="User_address" placeholder="Address" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="radio" name="User_sex" value="m" checked="checked"/>Man   
                <input type="radio" name="User_sex" value="w"/>Women
                <div class="clear"></div>
            </li>
            <input type="submit" value="Create Account">
            <div class="clear"></div>
        </ul>
        <div class="clear"></div>
    </form>
</div>
</body>
</html>
