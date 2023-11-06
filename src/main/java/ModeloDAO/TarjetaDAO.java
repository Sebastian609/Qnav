/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.GenericDao;
import Modelo.Bus;
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
public class TarjetaDAO  implements GenericDao<Object, Object>  {

    Date fechaActual = new Date();
    String fechaFormateada = "";
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Tarjeta> recuperarTarjetas(Persona per) {
       
        List<Tarjeta> tarjetas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tarjeta WHERE ID_USUARIO = ?";
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getId());
            System.out.println("- BUSCANDO TARJETAS DE : " +per.getNombres() + " " + per.getApellidoPaterno());
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setId(rs.getString("ID_TARJETA"));
                tarjeta.setIdDueño(rs.getString("ID_USUARIO"));
                tarjeta.setSaldo(rs.getDouble("SALDO_TARJETA"));
                tarjeta.setFecha_compra(rs.getDate("FECHA_COMPRA_TARJETA"));
                tarjetas.add(tarjeta);
                System.out.println("-TARJETA " + tarjeta.getId() +"("+tarjeta.getSaldo()+")");
                
                
            }
            System.out.println("-TARJETAS RECUPERADAS "+tarjetas.size());
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
    try (Connection con = new Conexion().getConnection();
         PreparedStatement ps = con.prepareStatement("SELECT MAX(" + Pk + ") FROM " + tabla + ";");
         ResultSet rs = ps.executeQuery()) {


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
        if(rs==null)
        {
            nuevoPKStr=Formato+"000";
        }

        return nuevoPKStr;
    } catch (SQLException e) {
        e.printStackTrace(); // Manejo de error básico, puedes personalizarlo para tu aplicación.
        return null; // Retorna null en caso de error.
    }
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
    public boolean registrarRecarga(Tarjeta tarjeta,Double monto, String tipoPago)
    {
        
        

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        String id_recarga = recuperarPk("ID_RECARGA", "RECARGA", "RC-");
        System.out.println("registrando recarga " + id_recarga);
        
        try {
            Conexion cn = new Conexion();
            System.out.println(id_recarga);
            System.out.println(tarjeta);
            System.out.println(tipoPago);
            System.out.println(fechaFormateada);
            String sql = "INSERT INTO recarga (ID_RECARGA,ID_TARJETA,ID_TIPO_PAGO,FECHA_TRANSACCION,MONTO) VALUES (?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, id_recarga);
            ps.setString(2, tarjeta.getId());
            ps.setString(3, tipoPago);
            java.util.Date fechaActual = new java.util.Date();
            java.sql.Timestamp fechaRegistro = new java.sql.Timestamp(fechaActual.getTime());
            ps.setTimestamp(4, fechaRegistro);
           
            ps.setDouble(5, monto);

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

    public boolean registrarTransaccion(String tipoPago, String tarjeta, String monto) {

        System.out.println("registrando recarga");

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
    public boolean registrarConsumo(Tarjeta tarjeta, Bus bus)
    {
        String Pk = recuperarPk("ID_CONSUMO", "consumo", "CS-");
        try {

            Conexion cn = new Conexion();
            String sql = "INSERT INTO `consumo` (`ID_CONSUMO`, `ID_BUS`,`ID_TARJETA`,  `MONTO_CONSUMO`, `FECHA_CONSUMO`) "
                    + "VALUES (?, ?,?, (SELECT TARIFA_RUTA FROM `ruta` WHERE ID_RUTA = ?), ?)";
            con = cn.getConnection();
           

            ps = con.prepareStatement(sql);

            ps.setString(1, Pk);
            ps.setString(2, bus.getId());
            ps.setString(3, tarjeta.getId());
            ps.setString(4, bus.getRuta().getId());
            java.util.Date fechaActual = new java.util.Date();
            java.sql.Timestamp fechaRegistro = new java.sql.Timestamp(fechaActual.getTime());
            ps.setTimestamp(5, fechaRegistro);

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
        return false;
    }

    public void registrarConsumo(String tarjeta, String Placa, String Ruta) {
       

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

    @Override
    public boolean create(Object entity) {
        Tarjeta tarjeta = (Tarjeta) entity;
       

        try {
            String sql = "INSERT INTO `tarjeta`(`ID_TARJETA`, `ID_USUARIO`, `SALDO_TARJETA`, `FECHA_COMPRA_TARJETA`, `ACTIVO`) VALUES (?,?,0.00,?,1)";
            con = (new Conexion()).getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, tarjeta.getId());
            ps.setString(2, tarjeta.getIdDueño());
            // Obtener la fecha y hora actual y convertirla a Timestamp
            ps.setTimestamp(3, new java.sql.Timestamp(tarjeta.getFecha_compra().getTime())); // Guarda la fecha actual como un timestamp
         
            // Obtener la fecha y hora actual y convertirla a Timestamp
          
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

    @Override
    public Object read(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Object entity) {
               Tarjeta tarjeta = (Tarjeta) entity;
       

        try {
            String sql = "UPDATE `tarjeta` SET `SALDO_TARJETA`= ? WHERE ID_TARJETA = ?";
            con = (new Conexion()).getConnection();
            ps = con.prepareStatement(sql);

            ps.setDouble(1, tarjeta.getSaldo());
            ps.setString(2, tarjeta.getId());
            // Obtener la fecha y hora actual y convertirla a Timestamp
         
          
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

    @Override
    public boolean delete(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> getAll() {
        
        return null;
        
    }

}
