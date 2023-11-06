/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.GenericDao;
import Modelo.Pasajero;
import Modelo.Persona;
import Modelo.Tarjeta;
import Modelo.Transportista;
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
public class TransportistaDAO extends Utilities.Util implements GenericDao<Object, Object>
{
    Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
    public List<Transportista> recuperarTransportistas(String id) {
       
        List<Transportista> transportista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuario WHERE ID_EMPRESA = ?";
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                Transportista ts = new Transportista();
                ts.setId(rs.getString("ID_USUARIO"));
               
            
                ts.setNroDoc(rs.getString("NRO_DOC"));
                ts.setNombres(rs.getString("NOMBRES"));
                ts.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                ts.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                ts.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                ts.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                ts.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                ts.setContraseña(rs.getString("CONTRASEÑA"));
                ts.setTelefono(rs.getInt("TELEFONO"));
                ts.setLicencia(rs.getString("LICENCIA"));
                
                transportista.add(ts);
                
                
                
            }
            System.out.println("-TARJETAS RECUPERADAS "+transportista.size());
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
        return transportista;

    }

    @Override
    public boolean create(Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object read(Object id) {
        Transportista ts = null;
        
       

        try {
            String sql = "SELECT * FROM usuario WHERE ID_USUARIO = '" + id+"'";
            System.out.println("verificando crendeciales");
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                
                ts = new Transportista();
                System.out.println("valido");
                ts.setId(rs.getString("ID_USUARIO"));
                ts.setNroDoc(rs.getString("NRO_DOC"));
                ts.setNombres(rs.getString("NOMBRES"));
                ts.setLicencia(rs.getString("LICENCIA"));
                ts.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                ts.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                ts.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                ts.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                ts.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                ts.setContraseña(rs.getString("CONTRASEÑA"));
                ts.setTelefono(rs.getInt("TELEFONO"));
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

        return ts; // Dev
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String recuperarPk(String Pk, String tabla, String Formato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
