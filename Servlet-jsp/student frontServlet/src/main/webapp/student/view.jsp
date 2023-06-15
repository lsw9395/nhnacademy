<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/05
  Time: 2:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
    <h1>${student.getName()}의 학생 정보</h1>
    <tbody>
        <tr>
            <td style="width: 30%">아이디</td>
            <td>${student.getId()}</td>
        </tr>
        <tr>
            <td>이름</td>
            <td>${student.getName()}</td>
        </tr>
        <tr>
            <td>이름</td>
            <td>${student.getGender()}</td>
        </tr>
        <tr>
            <td>나이</td>
            <td>${student.getAge()}</td>
        </tr>
        <tr>
            <td>등록일</td>
            <td>${cfmt:formatDate(student.getCreatedAt(), 'yyyy-MM-dd HH:mm:ss')}</td>
        </tr>
    </tbody>
</table>
<ul>
    <li><button><a href="/student/list.do">리스트</a></button></li>
    <li>
        <c:url var="update_link" value="/student/update.do">
            <c:param name="id" value="${student.getId()}"/>
        </c:url>
        <button><a href="${update_link}">수정</a></button>
    </li>
    <li>
        <form method="post" action="/student/delete.do">
            <input type="hidden" name="id" value="${student.getId()}" />
            <button type="submit">삭제</button>
        </form>
    </li>

 </ul>

</body>
</html>