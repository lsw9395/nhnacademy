import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PassengerList {
    private static List<Passenger> passengerList = new ArrayList<>();
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String dataBaseUrl = "jdbc:mysql://localhost:3306/module06";
    private static final String userName = "root";
    private static final String userPwd = "1234";
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
            String sqlQuery = "SELECT p.PassengerNo, PassengerName, Grade, Age From Passenger as p left join reservation as r on p.passengerNo = r.passengerNo";
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
}
