/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.GenericDao;
import Modelo.Bus;
import Modelo.Paradero;
import Modelo.Ruta;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebav
 */
public class ParaderoDAO implements GenericDao<Object, Object>
{
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public boolean create(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object read(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> getAll() {
        
        return null;
        
    }
    
    public List<Paradero> getAll(String id)
    {
        List<Paradero> list=null;
          
        try {
            list = new ArrayList<>();
            String sql = "SELECT * FROM PARADERO WHERE ID_RUTA = '" + id+"'";
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

              while (rs.next()) 
            {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                Paradero pr = new Paradero();
                pr.setId(rs.getString("ID_PARADERO"));
                pr.setNroOrden(rs.getInt("NRO_ORDEN"));
                pr.setNombre(rs.getString("NOMBRE_PARADERO"));
                pr.setLatitud(rs.getDouble("LATITUD"));
                pr.setLongitud(rs.getDouble("LONGITUD"));
                pr.setEstado(rs.getBoolean("ACTIVO"));
                list.add(pr);
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public String recuperarPk(String Pk, String tabla, String Formato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
