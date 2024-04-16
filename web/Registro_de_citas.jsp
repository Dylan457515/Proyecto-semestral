<%-- 
    Document   : Registro_de_citas
    Created on : 14 nov de 2023, 15:46:10
    Author     : dylan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Cita</title>
    <link rel="stylesheet" type="text/css" href="Stilo_registro_cita.css">
</head>
<body>
    <div class="background-image">
        <h1>Registrar Cita</h1>
        <form action="Registrar_Cita" method="POST">
            <label for="id">ID del Paciente:</label>
            <input type="text" id="idPaciente:" name="idPaciente:" required><br><br>
            
            <label for="id">ID del Doctor:</label>
            <input type="text" id="idDoctor:" name="idDoctor:" required><br><br>

            <label for="nombre">Nombre del Paciente:</label>
            <input type="text" id="nombre" name="nombre" required><br><br>

            <label for="fecha">Fecha de la Cita:</label>
            <input type="date" id="fecha" name="fecha" required><br><br>

            <label for="hora">Hora de la Cita:</label>
            <input type="time" id="hora" name="hora" required><br><br>

            <input type="submit" value="Registrar Cita">
        </form>
    </div>
</body>
</html>
