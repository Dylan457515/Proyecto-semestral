<%-- 
    Document   : doctorForm
    Created on : 5 nov de 2023, 14:26:51
    Author     : dylan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <title>Informe de doctor</title>
        <link rel="stylesheet" href="estilo_formulario.css">
    </head>
    
<body>
    <h1>Informe de doctor</h1>
    <form id="doctorForm"  action="GuardarFormulario" method="POST">
        <div>
            <div>
                <label for="direccion">Dirección:</label>
                    <input type="text" id="direccion" name="direccion"><br><br><br>
                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                    <input type="date" id="fechaNacimiento" name="fechaNacimiento"><br><br><br>
                <label for="sexo">Sexo:</label>
                    <select id="sexo" name="sexo">
                        <option value="" >Selecciona...</option>
                        <option value="masculino">Masculino</option>
                        <option value="femenino">Femenino</option>
                    </select><br><br><br>
            </div>

            <div>

                <label for="telefono">Teléfono:</label>
                    <input type="tel" id="telefono" name="telefono"><br><br><br>
                <label for="direccion">Tipo de abitacion:</label>
                <select id="Tipo_de_abitacion" name="Tipo_de_abitacion">
                        <option value="">Selecciona...</option>
                        <option value="Economica">Economica</option>
                        <option value="No_economica">No economica</option>
                </select><br><br><br>
                <label for="habitacion">Habitación:</label>
                    <input type="text" id="habitacion" name="habitacion"><br><br><br>
                <label for="fechaIngreso">Fecha de Ingreso:</label>
                    <input type="date" id="fechaIngreso" name="fechaIngreso"><br><br><br>
            </div>

            <div>
                <label for="idPaciente">ID de Paciente:</label>
                    <input type="text" id="idPaciente" name="idPaciente"><br><br><br>
                <label for="idDoctor">ID de Doctor:</label>
                    <input type="text" id="idDoctor" name="idDoctor"><br><br><br>
                <label for="pagoTotal">Pago Total:</label>
                    <input type="number" id="pagoTotal" name="pagoTotal"><br><br><br>
                <label for="pagoTotal">Fecha de alta:</label>
                    <input type="date" id="fechaAlta" name="fechaAlta"><br><br><br>
            </div>

        </div>

        <div class="input-group">
            <label for="motivos">Motivos:</label>
            <textarea id="motivos" name="motivos" rows="5" cols="50"></textarea>
        </div>
        <div class="input-group">
            <label for="diagnostico">Diagnóstico:</label>
            <textarea id="diagnostico" name="diagnostico" rows="5" cols="50"></textarea>
        </div>
        <div class="input-group">
            <label for="tratamiento">Tratamiento:</label>
            <textarea id="tratamiento" name="tratamiento" rows="5" cols="50"></textarea>
        </div>
        <div class="input-group">
            <label for="notas">Notas/Observaciones:</label>
            <textarea id="notas" name="notas" rows="5" cols="50"></textarea>
        </div>
        
        <button class="submit" >Enviar</button>
    </form>
    
    <div id="pacientes">

    </div>
    
    
</body>
</html>

