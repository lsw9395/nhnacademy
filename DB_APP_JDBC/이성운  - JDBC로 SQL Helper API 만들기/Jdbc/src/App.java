import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class App {
    public static void main(String[] args) {
        PassengerList list = new PassengerList();
        List<Passenger> passengers = list.getData();
        for(Passenger p : passengers){
            System.out.println(p);
        }
        List<Passenger> passengers2 = new ArrayList<>();
        SQLHelper helper = new SQLHelper();
        ResultSet result = helper.executeReader("SELECT p.PassengerNo, PassengerName, Grade, Age From Passenger as p left join reservation as r on p.passengerNo = r.passengerNo");
        try {
            while(result.next()){
                passengers2.add(new Passenger(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for(Passenger p : passengers2){
            System.out.println(p);
        }

        List<Object> passengers3 = new ArrayList<>();
        passengers3 = helper.executeScalar("SELECT p.PassengerNo, PassengerName, Grade, Age From Passenger as p left join reservation as r on p.passengerNo = r.passengerNo");
        for(Object p : passengers3){
            System.out.println(p);
        }
    }
}
