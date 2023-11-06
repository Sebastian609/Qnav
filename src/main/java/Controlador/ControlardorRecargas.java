/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Pasajero;
import Modelo.Tarjeta;
import ModeloDAO.PasajeroDAO;
import ModeloDAO.TarjetaDAO;
import com.google.gson.Gson;
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

/**
 *
 * @author sebav
 */
@WebServlet(name = "ControlardorRecargas", urlPatterns = {"/ControlardorRecargas"})
public class ControlardorRecargas extends HttpServlet {
    String pasajeroValidado="recarga.jsp";
    TarjetaDAO dao=new TarjetaDAO();
 
    
   
   
   

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            System.out.println("recargando S/.");
    
        }
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
        
        String tarjetaId = request.getParameter("tarjetaId");
        String saldo = (request.getParameter("saldo"));
        String tipoPago = request.getParameter("tipoPago");
    
         if(dao.recargar(tarjetaId, saldo))
                {
                    dao.registrarTransaccion(tipoPago, tarjetaId,saldo);
                }
         RequestDispatcher vista=request.getRequestDispatcher(pasajeroValidado);
        vista.forward(request, response);
        
        
        
        
     
        
        
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

        // Analizar los datos recibidos (JSON) usando Gson
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(sb.toString(), JsonObject.class);

        // Obtener los valores individuales de los datos recibidos
        String tipoPago = data.get("tipoPago").getAsString();
        String tarjetaId = data.get("tarjeta").getAsString();
       
         String pasajeroId = data.get("pasajeroId").getAsString();
        Double montoRecarga = data.get("montoRecarga").getAsDouble();
        System.out.println(pasajeroId);
        String mensaje = "Recibido";
       // Establece el tipo de contenido y el encabezado de la respuesta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Construye un objeto JSON con el mensaje
            JsonObject jsonResponse = new JsonObject();
             Pasajero pasajero = new Pasajero(pasajeroId);
       Tarjeta tarjeta = pasajero.recuperarTarjeta(tarjetaId);
       tarjeta.recargar(montoRecarga,tipoPago);
       System.out.println(tarjeta.getSaldo() + " es el nuevo saldo de la tarjeta " + tarjeta.getId());
            jsonResponse.addProperty("message", mensaje);

            // Escribe el objeto JSON como respuesta
                HttpSession session = request.getSession();
            session.setAttribute("user", pasajero);
            response.getWriter().write(jsonResponse.toString());
    
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
