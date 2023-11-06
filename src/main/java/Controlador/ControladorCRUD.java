/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Pasajero;
import ModeloDAO.PasajeroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sebav
 */
@WebServlet(name = "ControladorCRUD", urlPatterns = {"/ControladorCRUD"})
public class ControladorCRUD extends HttpServlet {

    PasajeroDAO pasajeroDao = new PasajeroDAO();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    String listar = "tabla-pasajeros.jsp";
    String acceso = "";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorCRUD</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorCRUD at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String accion = request.getParameter("accion");
        String nroDoc = request.getParameter("id");
        System.out.println("accediedno controlador " + nroDoc + accion);
           String nuevoNroDoc = request.getParameter("nuevoNroDoc");
        String nuevosNombres = request.getParameter("nuevosNombres");
        String nuevoApellidoPaterno = request.getParameter("nuevoApellidoPaterno");
        String nuevoApellidoMaterno = request.getParameter("nuevoApellidoMaterno");
        String nuevoCorreo = request.getParameter("nuevoCorreo");
        String nuevaContrasena = request.getParameter("nuevaContrasena");
        String nuevaFechaNacimiento = request.getParameter("nuevaFechaNacimiento");
        String nuevaFechaRegistro = request.getParameter("nuevaFechaRegistro");
        if(nuevaContrasena==null)
        {
            nuevaContrasena="123";
        }
            
            

        switch (accion) {
            case "editar":
                acceso = listar;

                System.out.println("FECHA" + nuevaFechaRegistro);
                System.out.println("nombres " + nuevosNombres + nuevaContrasena);
                String nuevoTelefono = request.getParameter("nuevoTelefono");
                 {
                    try {
                        pasajeroDao.edit(new Pasajero(null,null, null, nuevoNroDoc, nuevosNombres, nuevoApellidoPaterno, nuevoApellidoMaterno, formato.parse(nuevaFechaNacimiento), formato.parse(nuevaFechaRegistro), nuevoCorreo, nuevaContrasena, Integer.parseInt(nuevoTelefono)));
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladorCRUD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;


            case "agregar":

                break;
            case "borrar":
                pasajeroDao.eliminar(nroDoc);
                acceso = "tabla-pasajeros.jsp";
                break;

            case "listar":

                break;

            default:
                throw new AssertionError();
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
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
