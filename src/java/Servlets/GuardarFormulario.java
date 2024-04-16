/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.Doctores;
import Clases.Formulario;
import Clases.HabitacionTipo;
import Clases.Paciente;
import Clases.Usuario;
import Clases.controladora;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dylan
 */
public class GuardarFormulario extends HttpServlet {
    controladora control =new controladora();
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
            out.println("<title>Servlet GuardarFormulario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GuardarFormulario at " + request.getContextPath() + "</h1>");
            
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
        
        HttpSession session = request.getSession();
        Usuario UsuN = (Usuario) session.getAttribute("usuario");
        Paciente PasN = (Paciente) session.getAttribute("paciente");

        List<Formulario> Lista_Formulario = control.traerListaFormularios();
        System.out.println(PasN.getNombre());
        
        HttpSession Salida = request.getSession();
        Salida.setAttribute("UsuN", UsuN);
        Salida.setAttribute("PasN", PasN);
        Salida.setAttribute("ListaFormulario", Lista_Formulario);
        
        response.sendRedirect("lista_de_citas.jsp");
        
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
        // Crear nuevas instancias de Formulario, HabitacionTipo y Paciente
        Formulario forN = new Formulario();
        HabitacionTipo habitacionT = new HabitacionTipo();
        Paciente pacienteA = new Paciente();
                // Obtener parámetros de la solicitud
        String direc = request.getParameter("direccion");
        String sex = request.getParameter("sexo");
        String tel = request.getParameter("telefono");
                // Convertir el número de teléfono de String a Integer
        Integer telInt = null; // Declaración antes del bloque try-catch
        try {
            telInt = Integer.parseInt(tel);
        } catch (NumberFormatException e) {
            // Manejar la excepción si el número de teléfono no es un entero válido
        }
        // Establecer el número de teléfono en el formulario
        forN.setTelefono(telInt);
        
        // Obtener más parámetros de la solicitud
        String TipoAbitacion = request.getParameter("Tipo_de_abitacion");
        String Abitacio = request.getParameter("habitacion");
        // Convertir el número de habitación de String a Integer
        Integer Abit = null; // Declaración antes del bloque try-catch
        try {
            Abit = Integer.parseInt(Abitacio);
        } catch (NumberFormatException e) {
            // Manejar la excepción si el número de habitación no es un entero válido
        }
        // Ahora puedes usar 'telInt' aquí
        forN.setHabitacion(Abit);
        
        // Obtener la fecha de ingreso de la solicitud y establecer la hora de ingreso a la hora actual
        String FechaI = request.getParameter("fechaIngreso");
        
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date HoraActual = new Date();
        forN.setHoraingreso(HoraActual);

        
        String idPaciente = request.getParameter("idPaciente");
        int idP = 0; 
        try {
            idP = Integer.parseInt(idPaciente);
        } catch (NumberFormatException e) {
            // Maneja la excepción aquí
        }
         
        Paciente paciente = control.traerPaciente(idP);
        
        
        String idDoctor = request.getParameter("idDoctor");
        int idD = 0; 
        try {
            idD = Integer.parseInt(idDoctor);
        } catch (NumberFormatException e) {
            // Maneja la excepción aquí
        }
        Doctores doctor = control.traerDoctor(idD);
        String NomDoc = doctor.getNombre();
        forN.setNombre(NomDoc);
        
        String pagoTotal = request.getParameter("pagoTotal");
        int pagoT = 0; 
        try {
            pagoT = Integer.parseInt(pagoTotal);
        } catch (NumberFormatException e) {
            // Maneja la excepción aquí
        }
        
        String fechaAlta = request.getParameter("fechaAlta");
        java.sql.Date fechaAl = java.sql.Date.valueOf(fechaAlta);
        forN.setFechaalta(fechaAl);
        
        
        String motivos = request.getParameter("motivos");
        
        forN.setMotivo(motivos);
        
        String diagnostico = request.getParameter("diagnostico");
        forN.setDiagnostico(diagnostico);
        String tratamiento = request.getParameter("tratamiento");
        forN.setTratamiento(tratamiento);
        String not = request.getParameter("notas");
        forN.setNotas(not);

        
        // Establecer los parámetros restantes en el formulario
        forN.setDireccion(direc);
        forN.setSexo(sex);
        forN.setTelefono(telInt);
        forN.setTipohabitacion(habitacionT);
         // Establecer el tipo de habitación basado en la entrada
        switch (TipoAbitacion) {
            case "Economica":
                habitacionT.setNrohabitacion(1);
                habitacionT.setTipodehabitacion("Economica");
                break;
            case "No_economica":
                habitacionT.setNrohabitacion(2);
                habitacionT.setTipodehabitacion("No Economica");
                break;
            default:
                throw new AssertionError();
        }
        // Establecer la fecha de ingreso a la fecha actual
        LocalDate fechaActual = LocalDate.now();
        Date fechaAct = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());

        forN.setFechaingreso(fechaAct);
        
        forN.setIDPaciente(paciente);
        
        forN.setIDDoctor(doctor);
        
        forN.setTotalpagar(pagoT);
        // Pasar el formulario a un método de control para ser procesado o almacenado
        control.crearFormulario(forN);
        response.sendRedirect("doctorForm.jsp");
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
