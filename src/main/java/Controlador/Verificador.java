/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Bus;
import Modelo.Empresa;
import Modelo.Pasajero;
import ModeloDAO.BusDAO;
import ModeloDAO.EmpresaDAO;
import WebSockets.AlertasWebSocket;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author sebav
 */
@WebServlet(name = "Verificador", urlPatterns = {"/Verificador"})
public class Verificador extends HttpServlet {
    EmpresaDAO dao = new EmpresaDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

        //RECOPILAMOS LOS DATOS DEL FOMRULARIO SERIALIZADO
        String correo = request.getParameter("correo").toUpperCase();
        String contrasena = request.getParameter("contrasena");
        String tipoUsuario = request.getParameter("tipoUsuario");
        //CREAMOS UN USUARIO Y VALIDAMOS
        switch (tipoUsuario) {
            case "pasajero":
                Pasajero pas = new Pasajero(correo, contrasena);
                if (pas.getId() != null) 
                {
                    response.getWriter().write("1");
                    
                    List<Empresa> empresas = dao.obtenerTodos();
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("user", pas);
                    session.setAttribute("empresas", empresas);
                   
                } 
                else 
                {
                    response.getWriter().write("0");
                    System.out.println("no existe");
                }
                break;

            case "administrador": {

            }
            break;
            default:
                throw new AssertionError();
        }

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
