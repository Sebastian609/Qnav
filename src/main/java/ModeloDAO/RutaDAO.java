/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.GenericDao;
import Modelo.Paradero;
import Modelo.Pasajero;
import Modelo.Ruta;
import Modelo.Tarjeta;
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
public class RutaDAO implements GenericDao<Object, Object> {

    BusDAO busesDAO = new BusDAO();
    ParaderoDAO paraderoDAO = new ParaderoDAO();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

   

    @Override
    public boolean create(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object read(Object id) {
        Ruta ruta = null;
        
        
        try {
            String sql = "SELECT * FROM ruta WHERE ID_RUTA = " + id;
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                ruta = new Ruta();
                ruta.setId(rs.getString("ID_RUTA"));
                ruta.setNombre(rs.getString("NOMBRE_RUTA"));
                ruta.setTarifa(rs.getDouble("TARIFA_RUTA"));
                ruta.setColor(rs.getString("COLOR_RUTA"));
               
                ruta.setParaderos(paraderoDAO.getAll(ruta.getId()) );
                
                
                

            } else {
                System.out.println("Credenciales inválidas.");
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

        return ruta; // Dev
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
         List<Object> rutas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ruta";
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Ruta ruta = new Ruta();
                ruta.setId(rs.getString("ID_Ruta"));
                ruta.setNombre(rs.getString("Nombre_Ruta"));
                ruta.setTarifa(rs.getDouble("Tarifa_ruta"));
                ruta.setColor(rs.getString("Color_Ruta"));
                ruta.setEstado(rs.getBoolean("Activo"));
               

                rutas.add(ruta);
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
        return rutas;
    }

    @Override
    public String recuperarPk(String Pk, String tabla, String Formato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
