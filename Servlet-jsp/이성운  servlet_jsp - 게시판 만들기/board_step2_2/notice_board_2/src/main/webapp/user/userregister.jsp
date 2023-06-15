<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/07
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href ="/style.css"/>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<c:choose>
  <c:when test="${empty user}">
    <form method="post" action="/userregister.do" enctype="multipart/form-data">
  </c:when>
  <c:otherwise>
    <form method="post" action="/userupdate.do" enctype="multipart/form-data">
  </c:otherwise>
</c:choose>

<table>
  <tbody>
  <tr>

    <td style="width: 30%;"><fmt:message key="id"/></td>
    <td><input type="text" id="id" name="id"  ${not empty user ? 'disabled': 'required'} value="${user.getId()}"></td>
    <c:choose>
      <c:when test="${not empty user}">
        <input type="hidden" id="id2" name="id2" value="${user.getId()}">
      </c:when>
    </c:choose>
  </tr>
  <tr>
    <td><fmt:message key="pwd"/></td>
    <td><input type="text" id="password" name="password" required value="${user.getPassword()}"></td>
  </tr>
  <tr>
    <td><fmt:message key="username"/></td>
    <td><input type="text" id="name" name="name" required value="${user.getName()}"></td>
  </tr>
  <tr>
        <td><fmt:message key="profile"/></td>
        <td><input type="file" id="profile" name="profile" required></td>
  </tr>
  </tbody>
</table>
  <c:choose>
    <c:when test="${empty user}">
      <button type="submit"><fmt:message key="register"/></button>
    </c:when>
    <c:otherwise>
      <button type="submit"><fmt:message key="modify"/></button>
    </c:otherwise>
  </c:choose>
</form>
</fmt:bundle>
</body>
</html>
