<%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/04/09
  Time: 12:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>

<table>
    <tbody>
    <tr>
        <th>status_code</th>
        <td>${status_code}</td>
    </tr>
    <tr>
        <th>message</th>
        <td>${message}</td>
    </tr>
    <tr>
        <th>request_uri</th>
        <td>${request_uri}</td>
    </tr>
    </tbody>
</table>
</body>
</html>

