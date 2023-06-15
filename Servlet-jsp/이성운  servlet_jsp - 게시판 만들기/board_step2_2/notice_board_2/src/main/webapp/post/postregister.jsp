<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/07
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<c:choose>
    <c:when test="${empty post}">
        <form method="post" action="/register.do">
    </c:when>
    <c:otherwise>
        <form method="post" action="/postupdate.do">
    </c:otherwise>
</c:choose>
        <table>
            <tbody>
            <tr>
                <td style="width: 30%; font-size: 40px;"><fmt:message key="title"/></td>
                <td>
                    <input type="text" id="title" name="title" required value="${post.getTitle()}">
                </td>
            </tr>
            <tr>
                <td colspan="2" style="font-size: 40px;"><fmt:message key="content"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <textarea cols="100" rows="10" id="content" name="content" required>${post.getContent()}</textarea>
                </td>

            </tr>
            </tbody>
        </table>
        <input type="hidden" id="id" name="id" value="${post.getId()}"/>
        <c:choose>
            <c:when test="${empty post}">
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
