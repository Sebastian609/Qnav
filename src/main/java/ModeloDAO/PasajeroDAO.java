/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUD;
import Intefaces.GenericDao;
import Modelo.Pasajero;
import Modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sebav
 */
public class PasajeroDAO implements GenericDao<Object, Object> {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Pasajero p = new Pasajero();

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

    public boolean add(Persona per) {
        Pasajero pasajero = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TarjetaDAO daoTarjeta = new TarjetaDAO();

        try {
            String sql = "INSERT INTO `usuario`(`ID_USUARIO`, `NRO_DOC`, `NOMBRES`, `APELLIDO_PATERNO`, `APELLIDO_MATERNO`, `FECHA_NACIMIENTO`, `FECHA_REGISTRO`, `CORREO`, `CONTRASEÑA`, `TELEFONO`, `ID_Rol`, `ACTIVO`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Rl-001',1)";
            System.out.println("verificando crendeciales");
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getId());
            ps.setString(2, per.getNroDoc());
            ps.setString(3, per.getNombres());
            ps.setString(4, per.getApellidoPaterno());
            ps.setString(5, per.getApellidoMaterno());
            ps.setDate(6, (java.sql.Date) per.getFechaNacimiento());
            ps.setDate(7, (java.sql.Date) per.getFechaRegistro());
            ps.setString(8, per.getCorreo());
            ps.setString(9, per.getContraseña());
            ps.setInt(10, per.getTelefono());

            ps.executeQuery();

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

        return true;
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
            ps.setString(1, correo);
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

    @Override


    public boolean create(Object entity) {
        Pasajero pasajero = (Pasajero) entity;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO usuario(ID_USUARIO, NRO_DOC, NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, FECHA_NACIMIENTO, FECHA_REGISTRO, CORREO, CONTRASEÑA, TELEFONO, ID_Rol, ACTIVO) VALUES (?,?,?,?,?,?,?,?,?,?,'Rl-001',1)";
            con = (new Conexion()).getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, pasajero.getId());
            ps.setString(2, pasajero.getNroDoc());
            ps.setString(3, pasajero.getNombres());
            ps.setString(4, pasajero.getApellidoPaterno());
            ps.setString(5, pasajero.getApellidoMaterno());
            ps.setDate(6, new java.sql.Date(pasajero.getFechaNacimiento().getTime()));

            // Obtener la fecha y hora actual y convertirla a Timestamp
            java.util.Date fechaActual = new java.util.Date();
            java.sql.Timestamp fechaRegistro = new java.sql.Timestamp(fechaActual.getTime());
            ps.setTimestamp(7, fechaRegistro);

            ps.setString(8, pasajero.getCorreo());
            ps.setString(9, pasajero.getContraseña());
            ps.setInt(10, pasajero.getTelefono());
            ps.executeUpdate();

            return true; // Retorna verdadero si la inserción fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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

        return false; // Retorna falso si hay algún problema con la inserción
    }

    public Object inicioSesion(String correo, String contraseña) {

        Pasajero pasajero = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TarjetaDAO daoTarjeta = new TarjetaDAO();

        try {
            String sql = "SELECT * FROM USUARIO WHERE correo = '" + correo + "' AND contraseña = '" + contraseña + "'";
            System.out.println("verificando crendeciales");
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                pasajero = new Pasajero();
                System.out.println("valido");

                pasajero.setId(rs.getString("ID_USUARIO"));
                System.out.println("id = " + pasajero.getId());
                pasajero.setNroDoc(rs.getString("NRO_DOC"));
                pasajero.setNombres(rs.getString("NOMBRES"));
                pasajero.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                pasajero.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                pasajero.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                pasajero.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                pasajero.setCorreo(rs.getString("CORREO"));
                pasajero.setContraseña(rs.getString("CONTRASEÑA"));
                pasajero.setTelefono(rs.getInt("TELEFONO"));
                pasajero.setTarjetasPasajero(daoTarjeta.recuperarTarjetas(pasajero));

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

        return pasajero; // Dev

    }

    public boolean verificarCorreoExistente(String correo, String DNI ) {
        String consulta = "SELECT ID_USUARIO FROM usuario WHERE Correo = ? OR NRO_DOC = ?";
        boolean existe = false;
        Connection conn = null; // Declarar la conexión fuera del bloque try

        try {
            Conexion cn = new Conexion();
            conn = cn.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(consulta);
            pstmt.setString(1, correo);
            pstmt.setString(2, DNI);
            ResultSet rs = pstmt.executeQuery();

            existe = rs.next(); // Devuelve true si encuentra un registro con el correo

        }    catch (SQLException e) {
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

        return existe;
    }
    
    public Pasajero obtenerTitular(String id)
    {
        Pasajero pasajero = null;
        TarjetaDAO daoTarjeta = new TarjetaDAO();

        try {
            String sql = "SELECT * FROM USUARIO WHERE ID_EMPRESA ='"+id+"'";
            System.out.println("verificando crendeciales");
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                pasajero = new Pasajero();
                pasajero.setId(rs.getString("ID_USUARIO"));
    
                pasajero.setNroDoc(rs.getString("NRO_DOC"));
                pasajero.setNombres(rs.getString("NOMBRES"));
                pasajero.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                pasajero.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                pasajero.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                pasajero.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                pasajero.setCorreo(rs.getString("CORREO"));
                pasajero.setContraseña(rs.getString("CONTRASEÑA"));
                pasajero.setTelefono(rs.getInt("TELEFONO"));
                pasajero.setTarjetasPasajero(daoTarjeta.recuperarTarjetas(pasajero));

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

        return pasajero; // Dev
    }

    @Override
    public Object read(Object id) {

        Pasajero pasajero = null;
        TarjetaDAO daoTarjeta = new TarjetaDAO();

        try {
            String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO ='"+id+"'";
            System.out.println("verificando crendeciales");
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                pasajero = new Pasajero();
                pasajero.setId(rs.getString("ID_USUARIO"));
    
                pasajero.setNroDoc(rs.getString("NRO_DOC"));
                pasajero.setNombres(rs.getString("NOMBRES"));
                pasajero.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                pasajero.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                pasajero.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
                pasajero.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                pasajero.setCorreo(rs.getString("CORREO"));
                pasajero.setContraseña(rs.getString("CONTRASEÑA"));
                pasajero.setTelefono(rs.getInt("TELEFONO"));
                pasajero.setTarjetasPasajero(daoTarjeta.recuperarTarjetas(pasajero));

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

        return pasajero; // Dev
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
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement("SELECT MAX(" + Pk + ") FROM " + tabla + ";"); ResultSet rs = ps.executeQuery()) {

            Date fechaActual = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = sdf.format(fechaActual);

            String maxPKStr;
            String nuevoPKStr = null;

            int maxPK = 0;
            if (rs.next()) {
                maxPKStr = rs.getString(1);
                if (maxPKStr != null && maxPKStr.length() > 5) {
                    maxPK = Integer.parseInt(maxPKStr.substring(3)); // Extraer el número y convertirlo a entero
                }
            }

            int nuevoPK = maxPK + 1;
            nuevoPKStr = String.format(Formato + "%03d", nuevoPK);

            // Aquí podrías usar el nuevoPKStr para cualquier inserción o uso posterior en la base de datos.
            return nuevoPKStr;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de error básico, puedes personalizarlo para tu aplicación.
            return null; // Retorna null en caso de error.
        }
    }

}
