<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/17
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href ="/resources/style2.css"/>
</head>
<body>
<c:if test="${not empty user}">
    <div>
        <span>ID: ${user.userId}</span>
        <span>이름: ${user.userName}</span>
        <form method="post" action="/logout.do"><button type="submit">로그아웃</button></form>
    </div>
</c:if>
</body>
</html>
