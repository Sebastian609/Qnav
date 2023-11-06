
package Config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    public Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/querynav","root","");            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("no se conecto a bd");
          System.out.println(e);    
            
        }
        
    }
    public Connection getConnection(){
        return con;
    }
    
       
}
