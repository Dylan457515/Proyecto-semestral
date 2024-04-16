<%-- 
    Document   : Id_usuario
    Created on : 13 nov de 2023, 09:11:02
    Author     : dylan
--%>

<%@page import="Clases.Paciente"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ID</title>
        
        
        
    </head>
    <body>
        <% 
            Usuario UsuN = (Usuario) request.getSession().getAttribute("usuario");
            Paciente PasN = (Paciente)request.getSession().getAttribute("paciente");
        %>
        <h1>ID Paciente:<%= PasN.getIDPaciente()%>    </h1>
        <h1>ID usuario: <%= UsuN.getIDusuario()%></h1>
        
        
    </body>
</html>
