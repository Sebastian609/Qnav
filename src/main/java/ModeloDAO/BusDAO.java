/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Intefaces.GenericDao;
import Modelo.Bus;
import Modelo.Ruta;
import Modelo.Transportista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utilities.Util;
import java.sql.Array;

/**
 *
 * @author sebav
 */
public class BusDAO extends Util implements GenericDao<Object, Object> {

    Connection con = null;
    PreparedStatement ps = null;
    TransportistaDAO daoTrasportista = new TransportistaDAO();

    ResultSet rs = null;

    public List<Bus> recuperarBuses(String id) {

        List<Bus> buses = new ArrayList<>();

        try {
            String sql = "SELECT *  FROM bus WHERE ID_USUARIO IN (SELECT ID_USUARIO FROM usuario WHERE ID_EMPRESA = ?) ";
            Conexion cn = new Conexion();
            con = cn.getConnection();

            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setId(rs.getString("ID_BUS"));
                bus.setPlaca(rs.getString("PLACA"));    
                bus.setTransportista((Transportista) daoTrasportista.read(rs.getString("ID_USUARIO")));
                bus.setAforo(rs.getInt("AFORO"));
                bus.setEstado(rs.getBoolean("ACTIVO"));
                bus.setRuta(new Ruta(rs.getString("ID_RUTA")));
                buses.add(bus);
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
        return buses;
    }

    @Override
    public boolean create(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object read(Object id) {

        Bus bus = null;
        try {
            String sql = "SELECT * FROM bus where ID_BUS = ?";
            Conexion cn = new Conexion();
            con = cn.getConnection();

            ps = con.prepareStatement(sql);
            ps.setString(1, (String) id);
            System.out.println((String) id);
            rs = ps.executeQuery();
            if (rs.next()) {
                bus = new Bus();
                bus.setId(rs.getString("ID_BUS"));
                bus.setPlaca(rs.getString("PLACA"));
                bus.setTransportista((Transportista) daoTrasportista.read(rs.getString("ID_USUARIO")));
                bus.setRuta(new Ruta(rs.getString("ID_RUTA")));
                bus.setAforo(rs.getInt("AFORO"));
                bus.setEstado(rs.getBoolean("ACTIVO"));

            } else {
                System.out.println("no existe bus con ese id ");
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

        return bus;
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
        List<Object> list = null;
        Bus bus = null;
        try {
            list = new ArrayList<>();
            String sql = "SELECT * FROM bus";
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                bus = new Bus();
                bus.setId(rs.getString("ID_BUS"));
                bus.setPlaca(rs.getString("PLACA"));
                bus.setTransportista((Transportista) daoTrasportista.read(rs.getString("ID_USUARIO")));
                bus.setRuta(new Ruta("ID_RUTA"));
                bus.setAforo(rs.getInt("AFORO"));
                bus.setEstado(rs.getBoolean("ACTIVO"));
                list.add(bus);

            }

            {
                System.out.println("no existe bus con ese id ");
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

    public List<Bus> obtenerTodos() {
        List<Object> objects = getAll(); // Assuming `super.getAll()` retrieves a `List<Object>` from the database

        List<Bus> buses = new ArrayList<>();

        for (Object obj : objects) {
            if (obj instanceof Bus) {
                buses.add((Bus) obj);
            }
        }

        return buses;
    }

    @Override
    public String recuperarPk(String Pk, String tabla, String Formato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
