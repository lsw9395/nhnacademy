<%@ page import="com.example.javajdbc.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.example.javajdbc.DbUtils2" %><%--
  Created by IntelliJ IDEA.
  User: iseong-un
  Date: 2023/05/03
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    int pages = Integer.parseInt(request.getParameter("page"));
%>
<%!
    private static List<Member> memberList = new ArrayList<>();

    public static List<Member> getData(int page){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        DataSource dataSource = DbUtils2.getDataSource();
        int perpage = 20;
        int offset = (page-1) * 20;
        try{
            conn = dataSource.getConnection();
            String sqlQuery = "SELECT id, name, city from Members limit ?, ?";
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, offset);
            statement.setInt(2, perpage);
            result = statement.executeQuery();
            System.out.println(page);
            while(result.next()){
                memberList.add(new Member(result.getInt(1),result.getString(2),result.getString(3)));
            }
        } catch (Exception e){
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
        return memberList;
    }
    public static int getTotalPage(){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        DataSource dataSource = DbUtils2.getDataSource();
        int totalPage = 0;
        try{
            conn = dataSource.getConnection();
            String sqlQuery = "SELECT count(*) from members";
            statement = conn.prepareStatement(sqlQuery);
            result = statement.executeQuery();

            while(result.next()){
                totalPage = result.getInt(1);
            }
        } catch (Exception e){
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
        return totalPage;
    }
%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href ="style.css"/>
</head>
<%List<Member> members; members = getData(pages);%>
<%int totalPages = (int)Math.ceil(getTotalPage()/(double)20);
    int startPage = ((pages-1)/20) *20 +1;
    int endPage = Math.min(startPage + 19, totalPages);
%>
<body>
    <table>
        <tr>
            <td>아이디</td>
            <td>이름</td>
            <td>도시</td>
        </tr>
        <%for(Member m : members){%>
        <tr>
            <td><%=m.getId()%></td>
            <td><%=m.getName()%></td>
            <td><%=m.getCity()%></td>
        </tr>
        <%}%>
    </table>
    <% if(startPage >1) {%>
    <a href="?page=<%=startPage-1%>">이전</a>
    <% }%>
    <%for(int i = startPage ; i <=endPage; i++){%>
    <span><a href="member.jsp?page=<%=i%>"><%=i%></a> </span>
    <%}%>
<% if(endPage < totalPages) {%>
    <a href="?page=<%=endPage +1%>">다음</a>
<%}%>
</body>
<%memberList.clear();%>
</html>
