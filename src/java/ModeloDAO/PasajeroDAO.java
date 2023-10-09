/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUD;
import Modelo.Pasajero;
import Modelo.Persona;
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
public class PasajeroDAO implements CRUD {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Pasajero p = new Pasajero();

    @Override
    public List listar() {
        ArrayList<Pasajero> list = new ArrayList<>();
        String sql = "select * from pasajeros";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pasajero pas = new Pasajero();
                pas.setNroDoc(rs.getString("Nro_Doc_Pasajero"));
                pas.setNombres(rs.getString("Nombres_Pasajero"));
                pas.setApellidoPaterno(rs.getString("Apellido_Paterno_Pasajero"));
                pas.setApellidoMaterno(rs.getString("Apellido_Materno_Pasajero"));
                pas.setFechaNacimiento(rs.getDate("Fecha_Nacimiento_Pasajero"));
                pas.setFechaRegistro(rs.getDate("Fecha_Registro_Pasajero"));
                pas.setCorreo(rs.getString("Correo_Pasajero"));
                pas.setContraseña(rs.getString("Contraseña_Pasajero"));
                pas.setTelefono(rs.getInt("Telefono_Pasajero"));
                list.add(pas);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Pasajero list(String id) {
        String sql = "select * from pasajeros where Nro_Doc_Pasajero=" + id;
        Pasajero pas = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pas = new Pasajero();
                pas.setNroDoc(rs.getString("Nro_Doc_Pasajero"));
                pas.setNombres(rs.getString("Nombres_Pasajero"));
                pas.setApellidoPaterno(rs.getString("Apellido_Paterno_Pasajero"));
                pas.setApellidoMaterno(rs.getString("Apellido_Materno_Pasajero"));
                pas.setFechaNacimiento(rs.getDate("Fecha_Nacimiento_Pasajero"));
                pas.setFechaRegistro(rs.getDate("Fecha_Registro_Pasajero"));
                pas.setCorreo(rs.getString("Correo_Pasajero"));
                pas.setContraseña(rs.getString("Contraseña_Pasajero"));
                pas.setTelefono(rs.getInt("Telefono_Pasajero"));

            }
        } catch (Exception e) {
        }

        return pas;
    }

    @Override

    public boolean edit(Persona pasajero) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        

        try {
        Conexion cn = new Conexion();
        con = cn.getConnection(); 
        String sql = "UPDATE pasajeros SET Nombres_Pasajero=?, Apellido_Paterno_Pasajero=?, "
                + "Apellido_Materno_Pasajero=?, Fecha_Nacimiento_Pasajero=?, "
                + "Fecha_Registro_Pasajero=?, Correo_Pasajero=?, Contraseña_Pasajero=?, "
                + "Telefono_Pasajero=? WHERE Nro_Doc_Pasajero=?";
             ps = con.prepareStatement(sql);
            ps.setString(1, pasajero.getNombres());
            ps.setString(2, pasajero.getApellidoPaterno());
            ps.setString(3, pasajero.getApellidoMaterno());
            ps.setDate(4, new java.sql.Date(pasajero.getFechaNacimiento().getTime()));
            ps.setDate(5, new java.sql.Date(pasajero.getFechaRegistro().getTime()));
            ps.setString(6, pasajero.getCorreo());
            ps.setString(7, pasajero.getContraseña());
            ps.setInt(8, pasajero.getTelefono());
            ps.setString(9, pasajero.getNroDoc());

            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "delete from pasajeros where Nro_Doc_Pasajero=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean add(Persona per) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Pasajero verificarCredenciales(String correo, String contrasena) {
        Pasajero pasajero = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TarjetaDAO daoTarjeta = new TarjetaDAO();

        try {
            String sql = "SELECT * FROM pasajeros WHERE Correo_Pasajero = ? AND Contraseña_Pasajero = ?";
            System.out.println("verificando crendeciales");
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(   1, correo);
            ps.setString(2, contrasena);

            rs = ps.executeQuery();

            if (rs.next()) {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                pasajero = new Pasajero();
                System.out.println("valido");
                pasajero.setNroDoc(rs.getString("Nro_Doc_Pasajero"));
                pasajero.setNombres(rs.getString("Nombres_Pasajero"));
                pasajero.setApellidoPaterno(rs.getString("Apellido_Paterno_Pasajero"));
                pasajero.setApellidoMaterno(rs.getString("Apellido_Materno_Pasajero"));
                pasajero.setFechaNacimiento(rs.getDate("Fecha_Nacimiento_Pasajero"));
                pasajero.setFechaRegistro(rs.getDate("Fecha_Registro_Pasajero"));
                pasajero.setCorreo(rs.getString("Correo_Pasajero"));
                pasajero.setContraseña(rs.getString("Contraseña_Pasajero"));
                pasajero.setTelefono(rs.getInt("Telefono_Pasajero"));
                pasajero.setTarjetasPasajero(daoTarjeta.recuperarTarjetas(pasajero));
                System.out.println("recuperando tarjetas");
                
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

        return pasajero; // Devuelve el objeto Pasajero si las credenciales son válidas, o null si no lo son
    }

   
}
