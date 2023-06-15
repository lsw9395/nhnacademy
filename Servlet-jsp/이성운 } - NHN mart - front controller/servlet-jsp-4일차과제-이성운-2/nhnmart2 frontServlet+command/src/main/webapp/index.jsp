<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/06
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
  <h1><a href="/init.do"><fmt:message key="init"/> </a></h1>
  <h1><a href="/foods.do"><fmt:message key="foods"/></a></h1>
  <h1><a href="/cart.do"><fmt:message key="cart"/></a></h1>
  <h1><a href="/loginForm.do"><fmt:message key="loginform"/> </a></h1>
  <h1><a href="/login.do"><fmt:message key="login"/> </a></h1>
  <h1><a href="/change-lang?lang=ko"><fmt:message key="ko"/> </a></h1>
  <h1><a href="/change-lang?lang=en"><fmt:message key="en"/> </a></h1>
</fmt:bundle>
</body>
</html>