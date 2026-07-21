 package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection connection;
    Statement statement;
    public conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root","12345");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
/* 
package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    public Connection connection; // isko public rakhein taaki baaki classes access kar sakein
    public Statement statement;

    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            
            String url = "jdbc:mysql://b7dps2eqrcjjyhnp9gts-mysql.services.clever-cloud.com:3306/b7dps2eqrcjjyhnp9gts";
            String user = "uhcj6tyski7puv3g";
            String pass = "N7AScDhzITqjhdlCisHI";
            
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // <-- Constructor yahan khatam ho gaya

    // close() method ab constructor se bahar aur bilkul sahi jagah par hai
    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/