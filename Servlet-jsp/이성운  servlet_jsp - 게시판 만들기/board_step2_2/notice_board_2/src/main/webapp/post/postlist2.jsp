<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/09
  Time: 1:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href ="/style.css"/>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
  <h1><fmt:message key="post"/></h1>
  <span><a href="/register.do" ><fmt:message key="postregister"/></a></span>
  <div style="float: right">
    <form method='post' action='/logout.do'>
      <button type="submit"><fmt:message key="logout"/></button>
    </form>
  </div>
  <table>
    <thead>
    <tr>
      <th style="width: 15%;"><fmt:message key="postnum"/></th>
      <th><fmt:message key="title"/></th>
      <th style="width: 18%;"><fmt:message key="username"/></th>
      <th style="width: 10%;"><fmt:message key="posttime"/></th>
      <th style="width: 8%;"><fmt:message key="viewcount"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${pages.getList()}">
      <tr>
        <td>${post.getId()}</td>
        <td><c:url var="view_link" value="/postview.do">
          <c:param name="postid" value="${post.getId()}"/>
        </c:url><a href=${view_link}>${post.getTitle()}</a> </td>
        <td><c:url var="view_link" value="/userview.do">
          <c:param name="id" value="${post.getWriterUserId()}"/>
        </c:url><a href=${view_link}>${post.getWriterUserId()}</a></td>
        <td>${cfmt:formatDate(post.getWriteTime(),'yyyy-MM-dd HH:mm:ss')}</td>
        <td>${post.getViewCount()}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <c:forEach var="page" begin="1" end="${pages.getTotalPageCount()}">
    <span><c:url var="post_link" value="/postlist.do">
      <c:param name="page" value="${page}"/>
    </c:url><a href=${post_link}>${page}</a></span>
  </c:forEach>
</fmt:bundle>
</body>
</html>
