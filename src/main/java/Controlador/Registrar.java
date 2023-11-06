/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Pasajero;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sebav
 */
@WebServlet(name = "Registrar", urlPatterns = {"/Registrar"})
public class Registrar extends HttpServlet {

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
          request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
        int telf = 0;
        String nombre = request.getParameter("nombre").toUpperCase();
        String apellidoPaterno = request.getParameter("apellidoPaterno").toUpperCase();
        String apellidoMaterno = request.getParameter("apellidoMaterno").toUpperCase();
        String correo = request.getParameter("correo").toUpperCase();
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String contrasena = request.getParameter("contrasena");
        
        
        try {
             telf = Integer.parseInt(telefono);
            // Usa 'numeroComoEntero' como un entero en tu código
        } catch (NumberFormatException e) {
            // Manejo de errores si el String no se puede convertir a un entero
        }

        // Por ejemplo, imprimir los valores en la consola
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido Paterno: " + apellidoPaterno);
        System.out.println("Apellido Materno: " + apellidoMaterno);
        System.out.println("Correo: " + correo);
        System.out.println("DNI: " + dni);
        System.out.println("Fecha de Nacimiento: " + fechaNacimiento);
        System.out.println("Contraseña: " + contrasena);
        
         Date fechaActual = new Date();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String fechaFormateada = formatoFecha.format(fechaActual);
                System.out.println("Fecha y hora actual: " + fechaFormateada);

                Date fechaNacimientoParsed = null;
                try {
                    fechaNacimientoParsed = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);
                    System.out.println("Fecha de nacimiento: " + new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimientoParsed));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Pasajero pasajero = new Pasajero(dni, nombre, apellidoPaterno, apellidoMaterno, fechaNacimientoParsed, fechaActual, correo, contrasena, telf);
                System.out.println(pasajero.getId());
                if(pasajero.getId()!=null)
                {
                    pasajero.guardarEnBD();
                    response.getWriter().write("1");
                    System.out.println("usuario guradado");
                }
                else
                {
                     pasajero.guardarEnBD();
                    response.getWriter().write("0");
                    System.out.println("usuario no guradado");
                    System.out.println("datos de pasajero nuloss");
                }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
