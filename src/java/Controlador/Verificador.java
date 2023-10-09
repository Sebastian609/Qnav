/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Administrador;
import Modelo.Pasajero;
import Modelo.Persona;
import Modelo.Ruta;
import ModeloDAO.AdministradorDAO;
import ModeloDAO.PasajeroDAO;
import ModeloDAO.RutaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebav
 */
@WebServlet(name = "Verificador", urlPatterns = {"/Verificador"})
public class Verificador extends HttpServlet {

    String pasajeroValidado = "ruta.jsp";
    String administradorValido = "tabla-pasajeros.jsp";
    String invalido = "index.jsp";
    List<Ruta> rutas = new ArrayList<>();
    PasajeroDAO dao = new PasajeroDAO();
    AdministradorDAO daoAdmin = new AdministradorDAO();
    RutaDAO daoRuta = new RutaDAO();

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
            out.println("<title>Servlet Verificador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Verificador at " + request.getContextPath() + "</h1>");
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

        String acceso = "";
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String tipoUsuario = request.getParameter("tipo_usuario");
        System.out.println("tipo de user : " + tipoUsuario);
        System.out.println("email : " + correo);
        System.out.println("password : " + contrasena);
        switch (tipoUsuario) {
            case "pasajero":
                Pasajero pas = dao.verificarCredenciales(correo, contrasena);
                if (pas != null) {
                    rutas = daoRuta.recuperarRutas();
                    acceso = pasajeroValidado;
                    request.setAttribute("miObjeto", pas);
                    System.out.println("contraseña " + contrasena);
                    pas.setContraseña(contrasena);
                    request.setAttribute("Tarjetas", pas.getTarjetasPasajero());
                    request.setAttribute("Rutas", rutas);

                } else {
                    acceso = invalido;
                }
                break;

            case "administrador": {
                Administrador admin = daoAdmin.verificarCredenciales(correo, contrasena);
                if (admin != null) {
                    acceso=administradorValido;
                     List<Pasajero> pasajeros = dao.listar();
                acceso = administradorValido;
                request.setAttribute("miObjeto", admin);
                request.setAttribute("pasajeros", pasajeros);
                }
               
            }
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
