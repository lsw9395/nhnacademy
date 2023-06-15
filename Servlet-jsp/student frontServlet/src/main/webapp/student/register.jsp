<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/05
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="/style.css" />
</head>
<body>
  <c:choose>
      <c:when test="${empty student}">
        <form method="post" action="/student/register.do">
      </c:when>
      <c:otherwise>
        <form method="post" action="/student/update.do">
      </c:otherwise>
  </c:choose>
    <table>
      <tbody>
        <tr>
          <td style="width: 30%;">ID</td>
          <td><input type="text" id="id" name="id"required  value="${student.getId()}"></td>
        </tr>
      <tr>
        <td>이름</td>
        <td><input type="text" id="name" name="name" required value="${student.getName()}"></td>
      </tr>
        <tr>
          <td>성별</td>
          <td>
            <input type="radio" name="group" value="M" ${student.getGender() eq 'M' ? 'checked' : ' '}>남
            <input type="radio" name="group" value="F" ${student.getGender() eq 'F' ? 'checked' : ' '}>여
          </td>
        </tr>
        <tr>
          <td>나이</td>
          <td><input type="number" id="age" name="age" required value="${student.getAge()}"></td>
        </tr>
      </tbody>
    </table>
        <c:choose>
          <c:when test="${empty student}">
            <button type="submit">등록</button>
          </c:when>
          <c:otherwise>
            <button type="submit">수정</button>
          </c:otherwise>
        </c:choose>
  </form>
</body>
</html>
