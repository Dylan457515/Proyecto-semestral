<%-- 
    Document   : Menu_secretario
    Created on : 12 nov de 2023, 17:34:43
    Author     : dylan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu proncipal</title>
    <link rel="stylesheet" href="menus.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
    />
    <link rel="stylesheet" href="https://fontawesome.com/icons/suitcase-medical?f=classic&s=solid">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet"/>

</head>
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
            <li><a class="active" href="#">Perfil de Secretario</a></li>
            <li><a href="Registro_de_citas.jsp">Registrar cita</a></li>
            <li><a href="#">Asignar citas</a></li>
            
            <li><a href="#">Ayuda</a></li>
            <li><a href="index.jsp">cerrar sesion</a></li>
        </ul>



        <div class="wrapper">
            <div class="container">
                <i class="fas fa-smile-beam"></i>
                <span class="num" data-val="340">000</span>
                <span class="text">Clientes atendidos</span>
            </div>
            <div class="container">
                <i class="fa-solid fa-notes-medical"></i>
                <span class="num" data-val="400">050</span>
                <span class="text">Citas por asignar</span>
            </div>
            <div class="container">
                <i class="fa-solid fa-file-medical"></i>
                <span class="num" data-val="225">000</span>
                <span class="text">Citas programadas</span>
            </div>
        </div>
    <!-- Script -->
    <script src="script.js"></script>

    </nav>
    <section></section>
</body>
</html>
