package vtpd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bartek
 */
public class DBConnect {
    
    public Connection getConnection() throws ClassNotFoundException, SQLException{       
           Class.forName("com.mysql.cj.jdbc.Driver");
           return DriverManager.getConnection("jdbc:mysql://localhost:3306/baza?useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam&useSSL=false","user","admin"); 
     }
}
