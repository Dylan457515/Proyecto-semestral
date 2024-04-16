<%-- 
    Document   : registro_personal
    Created on : 13 nov de 2023, 18:19:26
    Author     : dylan
--%>

<%@page import="java.util.List"%>
<%@page import="Clases.RolTipo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Personal</title>
    <link rel="stylesheet" type="text/css" href="Registro_personal.css">
</head>
<body>
    <h1>Registrar Personal</h1>
    <form action="Registro_Personal" method="POST">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>

        <label for="apellido_paterno">Apellido Paterno:</label>
        <input type="text" id="apellido_paterno" name="apellido_paterno" required><br><br>

        <label for="apellido_materno">Apellido Materno:</label>
        <input type="text" id="apellido_materno" name="apellido_materno" required><br><br>
        
        <label for="Direccion">Direccion:</label>
        <input type="text" id="Direccion" name="Direccion" required><br><br>

        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required><br><br>        
        

        <label for="Telefono">Telefono:</label>
        <input type="text" id="Telefono" name="Telefono" required><br><br>


        
        <% 
        
            List<RolTipo> listaRoles = (List) request.getSession().getAttribute("ListaRoles");

        %>
        
        <label for="rol">Rol en el Hospital:</label>
        <select id="rol" name="tiporol">
            <option value="" >Selecciona...</option>
            <% for(RolTipo rol : listaRoles) { %>
                <option value="<%= rol.getNrorol()%>"><%= rol.getTipoderol()%></option>
            <% } %>
        </select><br><br><br>

        
        
        
        <input type="submit" value="Registrar Personal">
    </form>
</body>
</html>