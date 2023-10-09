/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUD;
import Modelo.Pasajero;
import Modelo.Persona;
import Modelo.Tarjeta;
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
public class TarjetaDAO {

    Date fechaActual = new Date();
    String fechaFormateada = "";

    public List<Tarjeta> recuperarTarjetas(Persona per) {
        Connection con = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();

        try {
            String sql = "SELECT * FROM tarjeta WHERE Nro_Doc_Pasajero = ?";
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getNroDoc());
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setId(rs.getString("ID_Tarjeta"));
                tarjeta.setNroDoc(rs.getString("Nro_Doc_Pasajero"));
                tarjeta.setSaldo(rs.getDouble("Saldo_Tarjeta"));
                tarjeta.setFecha_compra(rs.getDate("Fecha_Compra_Tarjeta"));
                tarjetas.add(tarjeta);
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
        return tarjetas;

    }

    public String recuperarPk(String Pk, String tabla, String Formato) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Date fechaActual = new Date();
        String maxPKStr;
        String nuevoPKStr = null;
        String fechaFormateada = null;
        try {
            // Establecer una conexión a la base de datos
            Conexion cn = new Conexion();
            con = cn.getConnection();
            // Obtener la fecha actual

            // Formatear la fecha como una cadena en el formato deseado (por ejemplo, "yyyy-MM-dd")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaFormateada = sdf.format(fechaActual);

            // Definir la consulta SQL para obtener el valor máximo actual de la PK
            String maxSql = "SELECT MAX(" + Pk + ") FROM " + tabla + ";";

            // Preparar la consulta para obtener el valor máximo
            ps = con.prepareStatement(maxSql);

            // Ejecutar la consulta y obtener el resultado
            rs = ps.executeQuery();

            // Obtener el valor máximo actual
            int maxPK = 0;
            if (rs.next()) {
                maxPKStr = rs.getString(1);
                if (maxPKStr != null) {
                    maxPK = Integer.parseInt(maxPKStr.substring(1)); // Extraer el número y convertirlo a entero
                }
            }

            // Incrementar el valor máximo
            int nuevoPK = maxPK + 1;

            // Formatear el nuevo PK en el formato "T000X"
            nuevoPKStr = String.format(Formato + "%04d", nuevoPK);
            System.out.println("insertando " + nuevoPKStr + " " + fechaFormateada);
            // Definir la consulta SQL para insertar un nuevo registro con el nuevo PK

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y los recursos
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
        return nuevoPKStr;
    }

   public boolean recargar(String tarjeta, String saldo) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
   
    System.out.println("recargando a = " + tarjeta);
 
    System.out.println(fechaFormateada);

    try {
        Conexion cn = new Conexion();
        con = cn.getConnection(); // Inicializa la conexión

        String sqlActualizarSaldo = "UPDATE tarjeta SET Saldo_Tarjeta = Saldo_Tarjeta + ? WHERE ID_Tarjeta = ?";
        ps = con.prepareStatement(sqlActualizarSaldo);
        ps.setString(1, saldo);
        ps.setString(2, tarjeta);
        ps.executeUpdate();
        System.out.println("Recargando....");
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
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
}


    public boolean registrarTransaccion(String tipoPago, String tarjeta, String monto) {

        System.out.println("registrando recargrga");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fechaFormateada = sdf.format(fechaActual);
        String id_recarga = recuperarPk("ID_Recarga", "recargas", "R");
        System.out.println("id recargatra " + id_recarga);
        try {
            Conexion cn = new Conexion();
            System.out.println(id_recarga);
            System.out.println(tarjeta);
            System.out.println(tipoPago);
            System.out.println(fechaFormateada);
            String sql = "INSERT INTO recargas(ID_Recarga, ID_Tarjeta,ID_Tipo_Pago,Fechar_Transaccion,monto) VALUES (?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, id_recarga);
            ps.setString(2, tarjeta);
            ps.setString(3, tipoPago);
            ps.setString(4, fechaFormateada);
            ps.setString(5, monto);

            ps.executeUpdate();
            System.out.println("consulta: " + sql);

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
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
        return false;
    }

   public boolean consumir(String tarjeta, String idRuta) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    double saldoGastar = 0.0;

    try {
        Conexion cn = new Conexion();
        con = cn.getConnection();

        // Consulta SQL para obtener la tarifa de la ruta
        String sqlTarifaRuta = "SELECT Tarifa_Ruta FROM ruta WHERE ID_Ruta = ?";
        ps = con.prepareStatement(sqlTarifaRuta);
        ps.setString(1, idRuta);
        rs = ps.executeQuery();

        if (rs.next()) {
            double tarifaRuta = rs.getDouble("Tarifa_Ruta");

            // Consulta SQL para obtener el saldo actual de la tarjeta
            String sqlSaldoActual = "SELECT Saldo_Tarjeta FROM tarjeta WHERE ID_Tarjeta = ?";
            ps = con.prepareStatement(sqlSaldoActual);
            ps.setString(1, tarjeta);
            rs = ps.executeQuery();

            if (rs.next()) {
                double saldoActual = rs.getDouble("Saldo_Tarjeta");

                // Verificar si la operación resultaría en un saldo negativo
                if (saldoActual < tarifaRuta) {
                    System.out.println("Operación cancelada: Saldo resultante sería negativo.");
                    return false;
                }

                saldoGastar = tarifaRuta; // Establecer el saldo a gastar como la tarifa de la ruta

                // Consulta SQL para actualizar el saldo
                String sqlActualizarSaldo = "UPDATE tarjeta SET Saldo_Tarjeta = Saldo_Tarjeta - ? WHERE ID_Tarjeta = ?";
                ps = con.prepareStatement(sqlActualizarSaldo);
                ps.setDouble(1, saldoGastar);
                ps.setString(2, tarjeta);
                ps.executeUpdate();
                System.out.println("Recargando....");
                return true;
            } else {
                System.out.println("Tarjeta no encontrada.");
                return false;
            }
        } else {
            System.out.println("Ruta no encontrada.");
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
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
}

    public void registrarConsumo(String tarjeta, String Placa, String Ruta) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String Pk = recuperarPk("ID_Consumo", "consumo", "C");
        try {

            Conexion cn = new Conexion();
            String sql = "INSERT INTO `consumo` (`ID_Consumo`, `ID_Tarjeta`, `Placa_Bus`, `Monto_Consumo`, `Fecha_Consumo`) "
                    + "VALUES (?, ?,?, (SELECT Tarifa_ruta FROM `ruta` WHERE ID_RUTA = ?), ?)";
            con = cn.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaFormateada = sdf.format(fechaActual);

            ps = con.prepareStatement(sql);

            ps.setString(1, Pk);
            ps.setString(2, tarjeta);
            ps.setString(3, Placa);
            ps.setString(4, Ruta);
            ps.setString(5, fechaFormateada);

            ps.executeUpdate();
            System.out.println("registrando consumo....");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
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
    }
    
    public boolean comprarTarjeta(String doc) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Date fechaActual = new Date();
        String maxPKStr;
        String nuevoPKStr = recuperarPk("ID_Tarjeta", "tarjeta", "T");
        String fechaFormateada = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaFormateada = sdf.format(fechaActual);
            Conexion cn = new Conexion();
            con = cn.getConnection();
            String insertSql = "INSERT INTO TARJETA (ID_Tarjeta, Nro_Doc_Pasajero, Saldo_Tarjeta, Fecha_Compra_Tarjeta) VALUES (?, ?, ?, ?)";

            // Preparar la consulta para insertar el nuevo registro
            ps = con.prepareStatement(insertSql);

            // Asignar valores a los parámetros en la consulta
            ps.setString(1, nuevoPKStr);          // Asignar nuevoPKStr al primer parámetro (ID_Tarjeta)
            ps.setString(2, doc);     // Asignar el número de documento del pasajero
            ps.setDouble(3, 0.00);                // Asignar el saldo inicial como 0.00
            ps.setString(4, fechaFormateada);     // Asignar fechaFormateada al cuarto parámetro (Fecha_Compra_Tarjeta)

            // Ejecutar la consulta de inserción
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Nuevo registro insertado con éxito.");
                Tarjeta tar = new Tarjeta(0, nuevoPKStr, doc, fechaActual);
                

            } else {
                System.out.println("No se pudo insertar el nuevo registro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y los recursos
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

}
