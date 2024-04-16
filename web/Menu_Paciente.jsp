<%-- 
    Document   : Menu_Paciente
    Created on : 15 nov de 2023, 18:21:11
    Author     : dylan
--%>

<%@page import="Clases.Paciente"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Menu proncipal</title>
    <link rel="stylesheet" href="menus.css">
    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
    />
    <link rel="stylesheet" href="https://fontawesome.com/icons/suitcase-medical?f=classic&s=solid">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet"/>
</head>
 <% 
            Usuario UsuN = (Usuario) request.getSession().getAttribute("usuario");
            Paciente PasN = (Paciente)request.getSession().getAttribute("paciente");
        %>
<body>
    <nav>
        <input type="checkbox" id="check">
        <label for="check" class="checkbtn">
            <i class="fas fa-bars">

            </i>
        </label>
        <a href="#" class="enlace">
            <!-- <img src="logo,png" alt="" class="logo"> -->
        </a>
        <ul>
            <li><a class="active" href="#">Inicio</a></li>
            <li><a class="active" href="#">Perfil de Paciente</a></li>
            

            
            <li>
                
                <form class="formulario" action="Mostrar_formulario_Paciente" method="POST" id="Mostrar_Ultimo_Registro">
                    <a href="otroServlet?usuarioId=<%= UsuN.getIDusuario()%>">ID:<%= UsuN.getIDusuario()%></a>
                    <% if (PasN != null) { %>
                <a href="otroServlet?pacienteId=<%= PasN.getIDPaciente()%>">ID paciente:<%= PasN.getIDPaciente()%></a>
                <% } %>
                    <input type="hidden" name="usuarioId" value="<%= UsuN.getIDusuario()%>">
                    <% if (PasN != null) { %>
                    <input type="hidden" name="pacienteId" value="<%= PasN.getIDPaciente()%>">
                    <% } %>
                    <a class="active" href="#"  onclick="document.getElementById('Mostrar_Ultimo_Registro').submit();" > Ultimo Registro</a>
                </form>
            </li>
            

            <li><a href="index.jsp">cerrar sesion</a></li>
        </ul><br><br><br><br><br>
        
        
    </nav>
        
        
        
        
    <script src="script.js"></script>

   
    <section></section>
</body>
</html>

