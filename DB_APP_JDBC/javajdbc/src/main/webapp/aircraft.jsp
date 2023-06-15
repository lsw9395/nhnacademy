<%@ page import="com.example.javajdbc.DbUtils" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.javajdbc.AircraftData" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/05/03
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String no = request.getParameter("passenger");
    String i = request.getParameter("aircraftNo");
%>
<%!
    private static List<AircraftData> aircraftDataList = new ArrayList<>();
    public static List<AircraftData> getDate(String i,String no){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try{
            DataSource dataSource = DbUtils.getDataSource();
            conn = dataSource.getConnection();
            String sqlQuery = "SELECT a.AircraftNo,Kindofaircraft,Airline,Deparetures,Arrival,FlightDate " +
                            "From Passenger as p left join reservation as r on p.passengerNo = r.passengerNo inner join Flight as f on f.FlightNo = r.FlightNo inner join Aircraft as a on a.AircraftNo = f.AircraftNo"
                            +" WHERE a.AircraftNo = ? and p.PassengerNo = ?";
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1,i);
            statement.setString(2,no);
            result = statement.executeQuery();
            while(result.next()){
                aircraftDataList.add(new AircraftData(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6)));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                result.close();
                statement.close();
                conn.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    return aircraftDataList;
}

%>
<html>
<head>
    <link rel="stylesheet" href ="style.css"/>
    <title>비행기 정보</title>
</head>
<body>
    <h1>비행기 정보</h1>
<table>
    <tr>
        <td>비행기 번호</td>
        <td>비행기 종류</td>
        <td>항공사</td>
        <td>출발지</td>
        <td>도착지</td>
        <td>출발 시간</td>
    </tr>
    <%List<AircraftData> aircrafts = new ArrayList<>(); aircrafts = getDate(i,no);%>
    <%for(AircraftData ad:aircrafts){%>
    <tr>
        <td><%=ad.getAircraftNo()%></td>
        <td><%=ad.getKindOfAircraft()%></td>
        <td><%=ad.getAirline()%></td>
        <td><%=ad.getDeparetures()%></td>
        <td><%=ad.getArrival()%></td>
        <td><%=ad.getFlightDate()%></td>
    </tr>
    <%}%>
</table>
<%aircraftDataList.clear();%>
</body>
</html>































