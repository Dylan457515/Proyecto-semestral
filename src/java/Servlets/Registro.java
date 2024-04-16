/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.RolTipo;
import Clases.Usuario;
import Clases.Paciente;
import Clases.controladora;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


/**
 *
 * @author dylan
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {
    

    controladora control =new controladora();
    
    RolTipo tipo = new RolTipo();
            
            
            
    Usuario usuN= new Usuario();
    
    Paciente pacN = new Paciente();
            
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
            out.println("<title>Servlet Registro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet El usuario es " +usuN.getNombre()+ "</h1>");
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
            
            
          // Obtener parámetros de la solicitud
            String nom = request.getParameter("RegNombre");

            String pass = request.getParameter("RegContraseña");
            
            String fechaN = request.getParameter("RegFechaNacimiento");
            // Convertir la fecha de nacimiento de String a java.sql.Date
            java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaN);
            usuN.setFechanacimiento(fechaNacimiento);
            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();
            Date fecha1 = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());

            
            
          // Establecer el número y tipo de rol
            tipo.setNrorol(5); // Establece el número de rol
            tipo.setTipoderol("Paciente"); // Establece el tipo de rol
            
             // Establecer el tipo de usuario
            usuN.setTipousuario(tipo); // Establece el tipo de usuario
            
           // Establecer el nombre, contraseña, fecha de ingreso y fecha de modificación en el usuario
            usuN.setNombre(nom);
            usuN.setPassword(pass);
            usuN.setFechaIngreso(fecha1);
            usuN.setFechamodificacion(fecha1);
            // Establecer el nombre y la fecha de nacimiento en el paciente
            pacN.setNombre(nom);
            pacN.setFechadenacimiento(fechaNacimiento);
            // Crear el usuario y el paciente
            control.crearUsuario(usuN);
            // Traer el último paciente
            usuN = control.traerUltimoUsuario();
            
            pacN.setIDusuario(usuN.getIDusuario());
            
            control.crearPaciente(pacN);
            


            
            pacN = control.traerUltimoPaciente();
            // Crear una nueva sesión y establecer los atributos de usuario y paciente
            HttpSession miregistro = request.getSession();
            miregistro.setAttribute("usuario", usuN);
            miregistro.setAttribute("paciente", pacN);
        // Redirigir a la página de ID de usuario
            response.sendRedirect("Menu_Paciente.jsp");
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
