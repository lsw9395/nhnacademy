<%@ page import="com.example.javajdbc.Passenger" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<%!
    List<Passenger> passengerList = new ArrayList<>();
    String driverName = "com.mysql.cj.jdbc.Driver";
    String dataBaseUrl = "jdbc:mysql://localhost:3306/module06";
    String userName = "root";
    String userPwd = "1234";
    public static void loadDriver(String driverName){
        try {
            Class.forName(driverName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Passenger> getData(){
        loadDriver(driverName);
        Connection myConnection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            myConnection = DriverManager.getConnection(dataBaseUrl, userName, userPwd);
            String sqlQuery = "SELECT PassengerNo, PassengerName, Grade, Age From Passenger";
            statement= myConnection.prepareStatement(sqlQuery);
            result = statement.executeQuery();
            while(result.next()){
                this.passengerList.add(new Passenger(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                result.close();
                statement.close();
                myConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return passengerList;
    }

%>
<html>
<head>
    <title>Passenger_info</title>
    <link rel="stylesheet" href ="style.css"/>
</head>
<body>
<h1>승객의 정보</h1>
<%List<Passenger> passengers = new ArrayList<>();%>
<%passengers = getData();%>
<table>
    <tr>
        <td>번호</td>
        <td>이름</td>
        <td>등급</td>
        <td>나이</td>
    </tr>
    <% for(Passenger p : passengers){%>
    <tr>
        <td><%=p.getPassengerNo()%></td>
        <td><a href="info.jsp?passenger=<%=p.getPassengerNo()%>"><%=p.getPassengerName()%></a></td>
        <td><%=p.getGrade()%></td>
        <td><%=p.getAge()%></td>
    </tr>
    <%}%>
</table>
<h1><a href="member.jsp?page=1">멤버 페이징 확인하러 가기</a> </h1>
    <%passengers.clear();%>
</body>
</html>