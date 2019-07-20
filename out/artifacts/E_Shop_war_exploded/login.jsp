<%--
  Created by IntelliJ IDEA.
  User: GOLD
  Date: 2019/6/3
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <link href="css/style1.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
</head>
<body>
<div class="main">
    <form action="ServletSign" method="post">
        <ul class="left-form">
            <h3>Login</h3>
            <div>
                <li><input type="text" name="User_id" placeholder="Username" required/>
                    <div class="clear"></div>
                </li>
                <li><input type="password" name="User_password" placeholder="Password" required/>
                    <div class="clear"></div>
                </li>
                <input type="submit" value="Login">
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </ul>
        <div class="clear"></div>
    </form>
</div>
</body>
</html>
