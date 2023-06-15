<%@ page import="com.example.javajdbc.Passenger" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="com.example.javajdbc.FlightData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.example.javajdbc.DbUtils" %><%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/05/02
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String i =request.getParameter("passenger");%>
<%!
    private List<FlightData> flightDataList = new ArrayList<>();
    public static void loadDriver(String driverName){
        try {
            Class.forName(driverName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<FlightData> getData(String i){
        DataSource dataSource = DbUtils.getDataSource();
        Connection myConnection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            myConnection = dataSource.getConnection();
            String sqlQuery = "SELECT AircraftNo,reservedData, Deparetures, Arrival, Price, FlightDate From Passenger as p left join reservation as r on p.passengerNo = r.passengerNo" +
                    " inner join Flight as f on f.FlightNo = r.FlightNo where p.passengerNo = ?";
            statement= myConnection.prepareStatement(sqlQuery);
            statement.setString(1,i);
            result = statement.executeQuery();
            while(result.next()){
                flightDataList.add(new FlightData(result.getInt(1),result.getString(2), result.getString(3), result.getString(4),result.getInt(5),result.getString(6)));
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
        return flightDataList;
    }
%>
<html>
<head>
    <link rel="stylesheet" href ="style.css"/>
    <title>예약정보</title>
</head>
<%List<FlightData> flightDataList1 = new ArrayList<>(); flightDataList1=getData(i);%>
<body>
<h1>예약정보</h1>
    <table>
        <tr>
            <td>예약 비행기 번호</td>
            <td>예약 날짜</td>
            <td>출발지</td>
            <td>도착지</td>
            <td>가격</td>
            <td>출발 상세 시간</td>
        </tr>
        <% for(FlightData f : flightDataList1){%>
        <tr>
            <td><a href="aircraft.jsp?passenger=<%=i%>&aircraftNo=<%=f.getAircraftNo()%>"> <%=f.getAircraftNo()%></></td>
            <td><%=f.getReservedData()%></td>
            <td><%=f.getDeparetures()%></td>
            <td><%=f.getArrival()%></td>
            <td><%=f.getPrice()%></td>
            <td><%=f.getFlightDate()%></td>
        </tr>
        <%}%>
    </table>
    <%flightDataList.clear();%>
</body>
</html>
