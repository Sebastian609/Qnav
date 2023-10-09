
package Config;

import Modelo.Pasajero;
import java.sql.*;

public class Conexion {
    Connection con;
    public Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/registro","root","");            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("no se conecto a bd");
        }
        System.out.println("conectado");
    }
    public Connection getConnection(){
        return con;
    }
    
       
}
