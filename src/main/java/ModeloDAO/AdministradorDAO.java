/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUD;
import Modelo.Administrador;

import Modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sebav
 */
public class AdministradorDAO implements CRUD
{

    @Override
    public List listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Persona list(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Persona per) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean edit(Persona per) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Administrador verificarCredenciales(String correo, String contrasena) {
        Administrador admin = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        

        try {
            String sql = "SELECT * FROM admin WHERE Correo_Admin = ? AND Contraseña_Admin = ?";
            System.out.println("verificando crendeciales");
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(   1, correo);
            ps.setString(2, contrasena);

            rs = ps.executeQuery();

            if (rs.next()) {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                admin = new Administrador();
                System.out.println("valido");
                admin.setNroDoc(rs.getString("Nro_Doc_Admin"));
                admin.setNombres(rs.getString("Nombres_Admin"));
                admin.setApellidoPaterno(rs.getString("Apellido_Paterno_Admin"));
                admin.setApellidoMaterno(rs.getString("Apellido_Materno_Admin"));
                admin.setFechaNacimiento(rs.getDate("Fecha_Nacimiento_Admin"));
                admin.setFechaRegistro(rs.getDate("Fecha_Registro_Admin"));
                admin.setCorreo(rs.getString("Correo_Admin"));
                admin.setContraseña(rs.getString("Contraseña_Admin"));
                admin.setTelefono(rs.getInt("Telefono_Admin"));
                
                
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

        return admin; 
    }
    
}
