<%-- 
    Document   : personal
    Created on : 13 nov de 2023, 18:16:04
    Author     : dylan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Personal</title>

    <link rel="stylesheet" type="text/css" href="styles_personal.css">
</head>
<body>
    <div class="container">
        <div class="box" id="registrar-personal">
            <form class="formulario" action="Registro_Personal" method="GET" id="Formulario_Registro_Personal_Medico">
                <h2>Registrar Personal</h2>
                <p>Haga clic para registrar nuevo personal.</p>

                <a href="#" onclick="document.getElementById('Formulario_Registro_Personal_Medico').submit();">Registrar</a>
            </form>
            
            
        </div>

        <div class="box" id="ver-personal">
            <h2>Personal</h2>
            <p>Haga clic para ver el personal existente.</p>
            <a href="personal_ya_exixtente.jsp">Ver</a>
        </div>
    </div>
</body>
</html>
