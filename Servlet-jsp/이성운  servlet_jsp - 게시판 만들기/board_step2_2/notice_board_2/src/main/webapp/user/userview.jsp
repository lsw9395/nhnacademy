<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/07
  Time: 8:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href ="/style.css"/>
  <style>
    img{
      height: 500px;
      width: 800px;
      object-fit: cover;
    }
  </style>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<h1>${user.getName()}<fmt:message key="user_info"/></h1>
<c:choose>
  <c:when test="${not empty admin}">
    <span><c:url var="update_link" value="/userupdate.do">
      <c:param name="id" value="${user.getId()}"/>
    </c:url><a href="${update_link}"><fmt:message key="modify"/></a></span>
    <span><c:url var="delete_link" value="/userdelete.do">
      <c:param name="id" value="${user.getId()}"/>
    </c:url><a href="${delete_link}"><fmt:message key="delete"/></a></span>
  </c:when>
</c:choose>
<span><a href="/userlist.do"><fmt:message key="userlist"/></a></span>
<span><a href="/postlist.do"><fmt:message key="post_list"/></a></span>
  <table>
    <thead>
      <tr>
        <th><fmt:message key="username"/></th>
        <th><fmt:message key="profile"/></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${user.getName()}</td>
            <c:choose>
            <c:when test="${empty user.getProfileFileName()}">
          <td>asd</td>
          </c:when>
          <c:otherwise>
            <td><c:url var="image_link" value="/fileimage.do">
              <c:param name="fileName" value="${user.getProfileFileName()}"/>
            </c:url><img src=${image_link} ></td>
          </c:otherwise>
          </c:choose>
      </tr>
    </tbody>
  </table>
</fmt:bundle>
</body>
</html>
