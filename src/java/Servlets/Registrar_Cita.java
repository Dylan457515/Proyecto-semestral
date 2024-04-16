/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.CitasMedicas;
import Clases.Doctores;
import Clases.Paciente;
import Clases.controladora;
import java.sql.Time;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author dylan
 */
public class Registrar_Cita extends HttpServlet {
    CitasMedicas citaM = new CitasMedicas();

    controladora control = new controladora();
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
            out.println("<title>Servlet Registrar_Cita</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> sita registrada corectamente</h1>");
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
        
        // Obtener el ID del paciente de la solicitud y convertirlo a un entero
        String idPacienteStr = request.getParameter("idPaciente:");
        try {
            int idPaciente = Integer.parseInt(idPacienteStr);
            // Traer el paciente con el ID proporcionado y establecerlo en la cita médica
            Paciente pasA = control.traerPaciente(idPaciente);
            citaM.setIDPaciente(pasA);
        } catch (NumberFormatException e) {
            // Manejar la excepción si el ID del paciente no es un entero válido
        }
        
        // Obtener el ID del doctor de la solicitud y convertirlo a un entero
        String idDoctorStr = request.getParameter("idDoctor:");
        try {
            int idDoctor = Integer.parseInt(idDoctorStr);
            // Traer el doctor con el ID proporcionado y establecerlo en la cita médica
            Doctores docA = control.traerDoctor(idDoctor);
            citaM.setIDDoctor(docA);
        } catch (NumberFormatException e) {
            // Manejar la excepción si el ID del doctor no es un entero válido
        }
    
        // Obtener la fecha de la solicitud y convertirla a java.sql.Date
        String fecha = request.getParameter("fecha");
        java.sql.Date fechaDat = java.sql.Date.valueOf(fecha);
        citaM.setFechaprogramada(fechaDat);
        
        // Obtener la hora de la solicitud y convertirla a Time
        String horaStr = request.getParameter("hora");
        try {
            Time hora = Time.valueOf(horaStr + ":00"); // Añade segundos si es necesario
            citaM.setHoraprogramada(hora);
        } catch (IllegalArgumentException e) {
            // Manejar la excepción si la hora no es válida
        }
        
        // Obtener la fecha actual y establecerla como la fecha de registro en la cita médica
        LocalDate fechaActual = LocalDate.now();
        Date fechaReg = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
        citaM.setFecharegistro(fechaReg);
        
        // Crear la cita médica
        control.crearCitasMedicas(citaM);
        
        // Procesar la solicitud y la respuesta
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
