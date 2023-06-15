<%@ page import="com.nhnacademy.nhnmart.util.CookieUtils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: lsw9395
  Date: 2023-04-04
  Time: 오후 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>식품매대 준비</title>
    <meta charset='utf-8'/>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Stylish&display=swap');
        div{
            position: absolute;
            left: 40%;
            top: 40%;
            width: 300px;
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
    <fmt:setLocale value="${locale}"/>
    <fmt:bundle basename="message">
    <div>
        <a href="/foods.do"><fmt:message key="foodlist"/></a>
    </div>
    </fmt:bundle>
</body>
</html>
