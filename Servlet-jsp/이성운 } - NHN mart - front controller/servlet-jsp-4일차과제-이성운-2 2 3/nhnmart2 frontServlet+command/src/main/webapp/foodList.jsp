<%@ page import="com.nhnacademy.nhnmart.util.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: lsw9395
  Date: 2023-04-04
  Time: 오후 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>식품매대</title>
    <meta charset='UTF-8'/>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Stylish&display=swap');
        *{
            font-family: 'Stylish',sans-serif;
        }
        form{
            text-align: center;
        }
        div{
            text-align: center;
            font-size: 40px;
        }
        h1{
            text-align: center;
        }
        #quantity{
            height: 30px;
            width: 70px;
            font-size: 30px;
        }
        #btn{
            height: 50px;
            width: 150px;
            font-size: 25px;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
    <h1><fmt:message key="foodlist"/></h1>
    <form action='/cart.do' method='post'>
            <c:forEach var="food" items="${foods}">
                <div>
                    ${food.getName()}(${food.getPrice()}원): 수량: ${food.getQuantity()}개
                        <input type='hidden' id='name' name='name' value='${food.getName()}'>
                        <input type='hidden' id='price' name='price' value='${food.getPrice()}'>
                        <input type='number' id='quantity' name='quantity' value='0' min='0' max="${food.getQuantity()}">
                </div>
            </c:forEach>
        <button id='btn' type='submit'><fmt:message key="inputcart"/> </button>
    </form>
</fmt:bundle>
</body>
</html>
