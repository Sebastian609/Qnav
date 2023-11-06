/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sebav
 */
public class Util
{
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
