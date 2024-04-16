/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.Doctores;
import Clases.RolTipo;
import Clases.Usuario;
import Clases.controladora;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dylan
 */
public class Registro_Personal extends HttpServlet {
    controladora control =new controladora();
    
    RolTipo tipoT = new RolTipo();
    
    Doctores docN = new Doctores();
    
    Usuario usuN= new Usuario();
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
            out.println("<title>Servlet Registro_Personal</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registro_Personal at " + request.getContextPath() + "</h1>");
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
        List<RolTipo> Lista_Roles = new ArrayList<>();
        
        Lista_Roles = control.traerRolTipo();
        
        HttpSession Salida = request.getSession();
        Salida.setAttribute("ListaRoles", Lista_Roles);
        
        response.sendRedirect("registro_personal.jsp");
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
    String nom = request.getParameter("nombre");
    // Establecer el nombre en el doctor y el usuario
    docN.setNombre(nom);
    usuN.setNombre(nom);

    // Continuar obteniendo parámetros y estableciéndolos en el doctor y el usuario
    String apP = request.getParameter("apellido_paterno");
    docN.setApPaterno(apP);
    String apM = request.getParameter("apellido_materno");
    docN.setApMaterno(apM);
    String direc = request.getParameter("Direccion");
    docN.setDireccion(direc);
    String pass = request.getParameter("password");
    usuN.setPassword(pass);
    String tel = request.getParameter("Telefono");
    int telefono = Integer.parseInt(tel);
    docN.setTelefono(telefono);

    // Obtener el tipo de rol de la solicitud y traer el RolTipo correspondiente
    String tipR = request.getParameter("tiporol");
    int tipoRol = Integer.parseInt(tipR);
    RolTipo RolSelec = control.traerUnRolTipo(tipoRol);

    // Establecer la especialidad en el doctor y el tipo de usuario en el usuario
    docN.setEspeialidad(RolSelec);
    usuN.setTipousuario(RolSelec);

    // Obtener la fecha actual
    LocalDate fechaActual = LocalDate.now();
    Date fechaAct = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());

    // Establecer la fecha de nacimiento, la fecha de ingreso y la fecha de modificación en el usuario
    usuN.setFechanacimiento(fechaAct);
    usuN.setFechaIngreso(fechaAct);
    usuN.setFechamodificacion(fechaAct);

    // Crear el doctor y el usuario
    control.crearDoctor(docN);
    control.crearUsuario(usuN);
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
