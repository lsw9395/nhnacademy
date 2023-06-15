<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/06
  Time: 6:30 PM
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
<span><fmt:message key="totalvisitorcount"/>: ${counter}</span>
    <div style="float: right;">
        <form method='post' action='/logout.do'>
            <button type="submit"><fmt:message key="logout"/></button>
        </form>
    </div>
<div><fmt:message key="logincount"/>: ${login_counter}</div>
    <h1><fmt:message key="userlist"/></h1>
    <table>
        <thead>
        <tr>
            <c:choose>
                <c:when test="${admin!=null}">
                    <th style="width: 20%;"><fmt:message key="id"/></th>
                    <th style="width: 20%;"><fmt:message key="pwd"/></th>
                </c:when>
            </c:choose>
            <th style="width: 20%;"><fmt:message key="username"/></th>
            <th style="width: 20%;"><fmt:message key="profile"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <c:choose>
                    <c:when test="${admin!=null}">
                <td>${user.getId()}</td>
                <td>${user.getPassword()}</td>
                    </c:when>
                </c:choose>
                <td><c:url var="view_link" value="/userview.do">
                <c:param name="id" value="${user.getId()}"/>
            </c:url><a href=${view_link}>${user.getName()}</a></td>
                <c:choose>
                    <c:when test="${empty user.getProfileFileName()}">
                        <td>null</td>
                    </c:when>
                    <c:otherwise>
                       <td><c:url var="image_link" value="/fileimage.do">
                           <c:param name="fileName" value="${user.getProfileFileName()}"/>
                       </c:url><img src=${image_link}></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fmt:bundle>
</body>
</html>
