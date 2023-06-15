<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/07
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<h1><fmt:message key="post"/></h1>
<c:choose>
    <c:when test="${not empty writer}">
        <span><c:url var="update_link" value="/postupdate.do">
            <c:param name="postid" value="${post.getId()}"/>
        </c:url><a href="${update_link}"><fmt:message key="modify"/></a></span>
        <span><c:url var="delete_link" value="/postdelete.do">
            <c:param name="postid" value="${post.getId()}"/>
        </c:url><a href="${delete_link}"><fmt:message key="delete"/></a></span>
    </c:when>
</c:choose>
<span><a href="/postlist.do"><fmt:message key="post_list"/></a></span>
<table>
    <tbody>
        <tr>
            <td style="width: 20%; font-weight: bold;"><fmt:message key="title"/></td>
            <td>${post.getTitle()}</td>
        </tr>
        <tr>
            <td style="width: 20%; font-weight: bold;"><fmt:message key="writer"/></td>
            <td><c:url var="view_link" value="/userview.do">
                <c:param name="id" value="${post.getWriterUserId()}"/>
            </c:url><a href=${view_link}>${user.getName()}</a></td>
        </tr>
        <tr>
            <td colspan="2" style="height: 500px;">${post.getContent()}</td>
        </tr>
    </tbody>
</table>
</fmt:bundle>
</body>
</html>
