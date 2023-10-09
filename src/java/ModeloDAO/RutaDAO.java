/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
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
public class RutaDAO {
    BusDAO busesDAO = new BusDAO();

    public List<Ruta> recuperarRutas() {
        Connection con = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ruta> rutas = new ArrayList<>();

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
                ruta.setTarifa(rs.getString("Tarifa_ruta"));
                ruta.setColor(rs.getString("Color_Ruta"));
                ruta.setFlota(busesDAO.recuperarBuses(ruta.getId()));

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
}
