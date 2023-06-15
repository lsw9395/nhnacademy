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
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <style>
    form{
      position: absolute;
      top: 35%;
      left: 35%;
      width: 30%;
      height: 30%;
      text-align: center;
      padding-top: 60px;
      box-sizing: border-box;
    }
    input{
      width: 60%;
      height: 50px;
      font-size: 25px;
      border-radius: 20px;
    }
    button{
      width: 30%;
      height: 30px;
      font-size: 20px;
      border-radius: 20px;
    }
  </style>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="message">
<form method="post" action="/login.do">
  <p><input type="text" name="userId" placeholder="아이디: lee" required/></p>
  <p><input type="password" name="userPassword" placeholder="비밀번호: 1234" required/></p>
  <p><button type="submit"><fmt:message key="login"/></button></p>
  <p><a href="/userregister.do"><fmt:message key="sign_up"/></a> </p>
  <span><a href="/change_lang.do?lang=en">English(영어)</a></span>
  <span><a href="/change_lang.do?lang=ko">한국어(Korean)</a></span>
</form>
</fmt:bundle>
</body>
</html>
