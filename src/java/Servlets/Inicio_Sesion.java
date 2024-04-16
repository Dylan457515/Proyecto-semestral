/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.Paciente;
import Clases.RolTipo;
import Clases.Usuario;
import Clases.controladora;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dylan
 */
public class Inicio_Sesion extends HttpServlet {
    Usuario usuA= new Usuario();
    Paciente pacA = new Paciente();
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
            out.println("<title>Servlet Inicio_Sesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Inicio_Sesion at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        // Crear nuevas instancias de controladora y RolTipo
        controladora control =new controladora();
        RolTipo tipoPaciente = new RolTipo();
        RolTipo tipoDoctor = new RolTipo();
        RolTipo tipoAdmin = new RolTipo();
        RolTipo tipoSecretario = new RolTipo();
        
        Paciente pacA = new Paciente();
        
        
        // Establecer los números y tipos de roles
        tipoPaciente.setNrorol(5); // Establece el número de rol
        tipoPaciente.setTipoderol("Paciente"); // Establece el tipo de rol
        tipoDoctor.setNrorol(6); // Establece el número de rol
        tipoDoctor.setTipoderol("Doctor"); // Establece el tipo de rol
        tipoAdmin.setNrorol(3); // Establece el número de rol
        tipoAdmin.setTipoderol("Administrador"); // Establece el tipo de rol
        tipoSecretario.setNrorol(7); // Establece el número de rol
        tipoSecretario.setTipoderol("Secretario"); // Establece el tipo de rol
         // Obtener parámetros de la solicitud
        String idStr = request.getParameter("txtID");
        int id = Integer.parseInt(idStr);

        String pass1 = request.getParameter("txtpassword");

         // Traer el usuario con el ID proporcionado
        Usuario usuario_inicio = control.traerUsuario(id);
        
        List<Paciente> Lista_Pacientes = control.traerListaPacientes();
        // Comprobar si el usuario existe y si la contraseña es correcta
        if(usuario_inicio != null){
            if(usuario_inicio.getPassword().equals(pass1)){
                // Comprobar el tipo de usuario y redirigir a la página correspondiente
                if(usuario_inicio.getTipousuario().equals(tipoPaciente)){
                    
                    System.out.println("es un paciente");
                    
                   for (Paciente paciente : Lista_Pacientes) {
                        // Comprueba si el atributo del paciente coincide con el valor buscado
                        if (paciente.getIDusuario().intValue() == id) {
                            // Si se encuentra una coincidencia, haz algo con el paciente
                            pacA = paciente;
                            break;
                        }
                    }
                    
                    
                    
                    usuA = control.traerUsuario(id);
                    
                    HttpSession miregistro = request.getSession();
                    miregistro.setAttribute("usuario", usuA);
                    miregistro.setAttribute("paciente", pacA);
                    // Redirigir a la página de ID de usuario
                    response.sendRedirect("Menu_Paciente.jsp");

                }else if(usuario_inicio.getTipousuario().equals(tipoAdmin)){
                    
                    System.out.println("es un Admin");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Menu_Arministrador.jsp");
                    dispatcher.forward(request, response);
                    
                }else if(usuario_inicio.getTipousuario().equals(tipoSecretario)){
                    
                    System.out.println("es un Secretario");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Menu_secretario.jsp");
                    dispatcher.forward(request, response);

                    
                }else{
                    
                    System.out.println("es un Doctor");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Menu_doctor.jsp");
                    dispatcher.forward(request, response);
                    
                }
            }else{
                // Si la contraseña no es correcta, redirigir a la página de inicio
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        }else{
            // Si el usuario no existe, redirigir a la página de inicio
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
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
