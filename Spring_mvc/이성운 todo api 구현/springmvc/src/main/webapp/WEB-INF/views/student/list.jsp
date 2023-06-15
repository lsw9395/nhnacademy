<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/05
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/resources/style4.css" />
</head>

<body>
<jsp:include page="/WEB-INF/views/login/loginInfo.jsp" />
<h1>학생 리스트</h1>
<p><a href="/student/register.do" >학생(등록)</a></p>
<table>
    <thead>
    <tr>
        <th style="width: 20%;">아이디</th>
        <th style="width: 20%;">이름</th>
        <th style="width: 20%;">성별</th>
        <th style="width: 20%;">나이</th>
        <th style="width: 20%;">cmd</th>
    </tr>
    </thead>
    <tbody>
        <tr th:each="item : ${studentList}" >
            <td th:text="${item.id}"></td>
            <td th:text="${item.name}"></td>
            <td th:text="${#nhnacademy.gender(item.gender)}"></td>
            <td th:text="${item.age}"></td>
            <td>
                <a th:href="@{/student/view.do(id=${item.id})}">조회</a>
            </td>
        </tr>
    </tbody>
</table>
</body>
</html>