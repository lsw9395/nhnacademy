
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLHelper{
    public static final String dataBaseUrl = "jdbc:mysql://localhost/module06";
    public static final String driverName = "com.mysql.cj.jdbc.Driver";
    public static final String userName = "root";
    public static final String userPwd = "1234";

    public static ResultSet executeReader(String sqlQuery){
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            con = DriverManager.getConnection(dataBaseUrl, userName, userPwd);
            statement = con.prepareStatement(sqlQuery);
            result = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                con.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static ResultSet executeReader(String sqlQuery, String... param){
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        try {
            con = DriverManager.getConnection(dataBaseUrl, userName, userPwd);
            statement = con.prepareStatement(sqlQuery);
            for(int i = 1 ; i<=param.length;i++){
                statement.setString(i, param[i-1]);
            }
            result = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                con.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static void executeWriter(String sqlQuery){
        executeReader(sqlQuery);
    }
    public static List<Object> executeScalar(String sqlQuery){
        ResultSet result = executeReader(sqlQuery);
        List<Object> data = new ArrayList<>();
        try {
            ResultSetMetaData rsmd = result.getMetaData();
            int col = rsmd.getColumnCount();
            for(int i = 1; i<=col; i++){
                data.add(result.getObject(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }
    public static List<Object> executeScalar(String sqlQuery,String... param){
        ResultSet result = executeReader(sqlQuery, param);
        List<Object> data = new ArrayList<>();
        try {
            ResultSetMetaData rsmd = result.getMetaData();
            int col = rsmd.getColumnCount();
            for(int i = 1; i<=col; i++){
                data.add(result.getObject(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }
    public static int ExecuteNonQuery(String sqlQuery){
        ResultSet result = executeReader(sqlQuery);
        int rowCount = 0;
        try {
            result.last();
            rowCount = result.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowCount;
    }
    public static int ExecuteNonQuery(String sqlQuery,String... param){
        ResultSet result = executeReader(sqlQuery,param);
        int rowCount = 0;
        try {
            result.last();
            rowCount = result.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowCount;
    }
}