/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import static Config.crearUsuario.generarCodigoAleatorio;
import static Config.crearUsuario.validarCodigo;
import Modelo.Pasajero;
import ModeloDAO.PasajeroDAO;
import WebSockets.AlertasWebSocket;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sebav
 */
@WebServlet(name = "ControladorRegistro", urlPatterns = {"/ControladorRegistro"})
public class ControladorRegistro extends HttpServlet {

    private static final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LONGITUD_CODIGO = 6;
    private static final PasajeroDAO dao = new PasajeroDAO();
    
    public static String generarCodigoAleatorio() {
        SecureRandom aleatorio = new SecureRandom();
        StringBuilder codigoGenerado = new StringBuilder(LONGITUD_CODIGO);

        for (int i = 0; i < LONGITUD_CODIGO; i++) {
            int indice = aleatorio.nextInt(CARACTERES_PERMITIDOS.length());
            codigoGenerado.append(CARACTERES_PERMITIDOS.charAt(indice));
        }

        return codigoGenerado.toString();
    }

    public static boolean validarCodigo(String codigoGenerado, String codigoIngresado) {
        return codigoGenerado.equals(codigoIngresado);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar los datos del formulario
        
        String correo = request.getParameter("correo");
        String dni = request.getParameter("dni");
      

        if (dao.verificarCorreoExistente(correo, dni) == false) 
        {
            String cod = generarCodigoAleatorio();
            System.out.println(cod);
            response.getWriter().write(cod);
            
            
            
        } else {
            response.getWriter().write("0");
             
            
        }

        // Realizar la lógica de tu aplicación con estos datos, como almacenar en una base de datos, validaciones, etc.
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
