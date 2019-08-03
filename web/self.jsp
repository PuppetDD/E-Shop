<%@ page import="entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: GOLD
  Date: 2019/6/11
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Information</title>
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
<%
    Object object = request.getAttribute("self");
    User u = (User) object;
%>
<div class="main">
    <form action="ServletRegister" method="post">
        <ul class="left-form">
            <h2>Your Information:</h2>
            <li>
                <input type="text" name="User_id" value="<%=u.getU_id()%>" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="text" name="User_name" value="<%=u.getU_name()%>" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="text" name="User_number" value="<%=u.getU_number()%>" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="text" name="User_mail" value="<%=u.getU_mail()%>" required/>
                <div class="clear"></div>
            </li>
            <li>
                <input type="text" name="User_address" value="<%=u.getU_address()%>" required/>
                <div class="clear"></div>
            </li>
            <input type="submit" value="Change">
            <div class="clear"></div>
        </ul>
        <div class="clear"></div>
    </form>
</div>
</body>
</html>