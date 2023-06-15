<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/05
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css" />
</head>

<body>
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
    <c:forEach  var="student" items="${studentList}">
        <tr>
            <td>${student.getId()}</td>
            <td>${student.getName()}</td>
            <td>${student.getGender()}</td>
            <td>${student.getAge()}</td>
            <td><c:url var="view_url" value="/student/view.do">
                <c:param name="id" value="${student.getId()}"/>
                </c:url>
                <a href="${view_url}">조회</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>