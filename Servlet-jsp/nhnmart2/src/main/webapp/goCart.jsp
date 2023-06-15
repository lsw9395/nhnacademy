<%@ page import="com.nhnacademy.nhnmart.util.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: lsw9395
  Date: 2023-04-05
  Time: 오전 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8"/>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Stylish&display=swap');
        *{
            font-family: 'Stylish', sans-serif;
        }
        div{
            position: absolute;
            left: 40%;
            top: 40%;
            width: 400px;
            height: 80px;
            margin: 0 auto;
            z-index:9999;
            border-color: black;
            border-radius: 20px;
            border-style: solid;
            text-align: center;
            padding-top: 20px;
            font-size: 50px;
            font-family: 'Stylish',sans-serif;
        }
    </style>
</head>
<body>
<%
    String locale = CookieUtils.getCookie(request,"locale").getValue();
%>
    <fmt:setLocale value="<%=locale%>"/>
    <fmt:bundle basename="message">
    <div>
        <a href="/cart"><fmt:message key="cartlist"/></a>
    </div>
</fmt:bundle>
</body>
</html>
