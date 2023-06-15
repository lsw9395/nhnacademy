<%@ page import="com.nhnacademy.nhnmart.object.Food" %>
<%@ page import="com.nhnacademy.nhnmart.object.Basket" %>
<%@ page import="com.nhnacademy.nhnmart.util.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: lsw9395
  Date: 2023-04-04
  Time: 오후 8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>장바구니</title>
    <meta charset='UTF-8'/>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Stylish&display=swap');
        *{
            font-family: 'Stylish',sans-serif;
            font-size: 30px;
        }
        table {
            margin: 0 auto;
            width: 800px;
            border-collapse: collapse;
            border:1px #ccc solid;
        }
        table tr>td,th{
            padding:5px;
            border:1px #ccc solid;
        }
        h1{
            text-align: center;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<h1><fmt:message key="cartlist"/> </h1>
<table>
    <thead>
    <tr>
        <th style="width: 35%" ><fmt:message key="productname"/></th>
        <th style="width: 35%" ><fmt:message key="productprice"/></th>
        <th style="width: 30%"><fmt:message key="productquantity"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="food" items="${foods}">
        <tr>
            <td style="text-align: center">${food.getName()}</td>
            <td style="text-align: center">
                <fmt:formatNumber value="${food.getPrice()}" pattern="###,###,###,###" />
            </td>
            <td style="text-align: center">${food.getQuantity()}</td>
        </tr>
    </c:forEach>
    </tbody>
    <h1><fmt:message key="totalprice"/> <fmt:formatNumber value="${total_price}" pattern="###,###,###,###" /></h1>
    <h1><a href="index.do"><fmt:message key="gomain"/></a></h1>
</table>
</fmt:bundle>
</body>
</html>
