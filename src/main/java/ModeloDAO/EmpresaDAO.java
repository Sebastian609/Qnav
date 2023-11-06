/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.GenericDao;
import Modelo.Bus;
import Modelo.Empresa;
import Modelo.Pasajero;
import Modelo.Ruta;
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
public class EmpresaDAO implements GenericDao<Object, Object> {

    PasajeroDAO pasajeroDAO = new PasajeroDAO();
    BusDAO busDAO = new BusDAO();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public boolean create(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object read(Object id) {
        Empresa empresa = null;

        TarjetaDAO daoTarjeta = new TarjetaDAO();

        try {
            String sql = "SELECT * FROM EMPRESA WHERE ID_EMPRESA = '" + id + "'";
            System.out.println("verificando crendeciales");
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                // Las credenciales son válidas, crea un objeto Pasajero y configúralo
                empresa = new Empresa();
                System.out.println("valido");
                empresa.setId(rs.getString("ID_EMPRESA"));
                empresa.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                empresa.setNombre(rs.getString("NOMBRE_COMERCIAL"));
                empresa.setRUC(rs.getString("RUC"));
                empresa.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                empresa.setEstado(rs.getBoolean("ACTIVO"));
                empresa.setTitular((Pasajero) pasajeroDAO.obtenerTitular((empresa.getId())));
                empresa.setFlota(busDAO.recuperarBuses(empresa.getId()));
                System.out.println("recuperando ");

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

        return empresa; // Dev
    }

    @Override
    public boolean update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public List<Empresa> obtenerTodos() {
        List<Object> objects = getAll(); // Assuming `super.getAll()` retrieves a `List<Object>` from the database

        List<Empresa> empresas = new ArrayList<>();

        for (Object obj : objects) {
            if (obj instanceof Empresa) {
                empresas.add((Empresa) obj);
            }
        }

        return empresas;
    }
    @Override
    public List<Object> getAll() {
        List<Object> list = null;
        Empresa empresa = null;
        try {
            list = new ArrayList<>();
            String sql = "SELECT * FROM empresa";
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                empresa = new Empresa();
              
                empresa.setId(rs.getString("ID_EMPRESA"));
                empresa.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                empresa.setNombre(rs.getString("NOMBRE_COMERCIAL"));
                empresa.setRUC(rs.getString("RUC"));
                empresa.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                empresa.setEstado(rs.getBoolean("ACTIVO"));
                empresa.setTitular((Pasajero) pasajeroDAO.obtenerTitular((empresa.getId())));
                empresa.setFlota(busDAO.recuperarBuses(empresa.getId()));
                list.add(empresa);

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
