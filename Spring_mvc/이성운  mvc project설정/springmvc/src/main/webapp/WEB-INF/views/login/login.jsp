<%--
  Created by IntelliJ IDEA.
  User: lsw9395
  Date: 2023-04-05
  Time: 오전 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <meta charset='utf-8'/>
    <link rel="stylesheet" href ="/style2.css"/>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
        <c:choose>
            <c:when test="${admin != null}">
                <div class="div2">
                    <p><a href="/userlist.do"><fmt:message key="admin_win"/></a></p>
                    <p><a href="/postlist.do"><fmt:message key="post_list"/></a></p>
                    <form method='post' action='/logout.do'>
                        <button type="submit"><fmt:message key="logout"/></button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    <a href="/postlist.do"><fmt:message key="post_list"/></a>
                    <form method='post' action='/logout.do'>
                        <button type="submit"><fmt:message key="logout"/></button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
</fmt:bundle>
</body>
</html>
