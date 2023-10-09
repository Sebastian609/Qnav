/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Modelo.Bus;
import Modelo.Ruta;
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
public class BusDAO {
    public List<Bus> recuperarBuses(String idRuta) {
        Connection con = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Bus> buses = new ArrayList<>();

        try {
            String sql = "SELECT * FROM bus where ID_Ruta = ?";
            Conexion cn = new Conexion();
            con = cn.getConnection();
            
            ps = con.prepareStatement(sql);
            ps.setString(1, idRuta);

            rs = ps.executeQuery();
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setPlaca(rs.getString("Placa_Bus"));
                bus.setAforo(rs.getInt("Aforo"));
                bus.setEstado(rs.getBoolean("Estado"));

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
    }}
