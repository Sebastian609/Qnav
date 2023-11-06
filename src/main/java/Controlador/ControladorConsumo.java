/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Pasajero;
import Modelo.Tarjeta;
import ModeloDAO.TarjetaDAO;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sebav
 */
@WebServlet(name = "ControladorConsumo", urlPatterns = {"/ControladorConsumo"})
public class ControladorConsumo extends HttpServlet {

    String pasajeroValidado = "Verificador?correo=quispe@gmail.com&contrasena=123&tipo_usuario=pasajero";
    TarjetaDAO dao = new TarjetaDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        reader.close();

        // Obtener los datos JSON del cuerpo de la solicitud
        String jsonString = sb.toString();

        try {
            // Parsear el JSON para obtener los valores específicos
            JSONObject json = new JSONObject(jsonString);
            String tarjetaId = json.getString("tarjetaId");
            String rutaId = json.getString("rutaId");
            String busId = json.getString("busId");
            String pasajeroId = json.getString("pasajeroId");
            String mensaje;

            // Realizar acciones con los datos recibidos
            // Por ejemplo:
            System.out.println("Tarjeta ID: " + tarjetaId);
            System.out.println("Ruta ID: " + rutaId);
            System.out.println("Bus ID: " + busId);
            System.out.println("Pasajero ID: " + pasajeroId);

            Pasajero pasajero = new Pasajero(pasajeroId);
            Tarjeta tarjeta = pasajero.recuperarTarjeta(tarjetaId);
           
            if(tarjeta.consumir(rutaId, busId))
            {
                mensaje = "1"; 
                System.out.println(tarjeta.getSaldo() + " es el nuevo saldo de la tarjeta " + tarjeta.getId());
                
            }
            else
            {
                mensaje = "2"; 
            }
           
            HttpSession session = request.getSession();
            session.setAttribute("user", pasajero);

            // Puedes realizar procesamientos adicionales con estos datos.
            // Enviar una respuesta de éxito al cliente
            

            // Establece el tipo de contenido y el encabezado de la respuesta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Construye un objeto JSON con el mensaje
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("message", mensaje);

            // Escribe el objeto JSON como respuesta
            response.getWriter().write(jsonResponse.toString());

        } catch (JSONException e) {
            // Manejar cualquier error que surja al procesar el JSON
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error al procesar los datos: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
