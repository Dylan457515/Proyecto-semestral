<%-- 
    Document   : tista_de_citas
    Created on : 15 nov de 2023, 23:33:17
    Author     : dylan
--%>

<%@page import="Clases.Doctores"%>
<%@page import="Clases.Paciente"%>
<%@page import="java.util.List"%>
<%@page import="Clases.Formulario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="font-size: 16px;" lang="es"><head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Sample Headline">
    <meta name="description" content="">
    <title>Página 2</title>
    <link rel="stylesheet" href="extra.css" media="screen">
<link rel="stylesheet" href="tista_de_citas.css" media="screen">
    <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 5.21.10, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    
    
    
    <script type="application/ld+json">{

}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="Página 2">
    <meta property="og:type" content="website">
  <meta data-intl-tel-input-cdn-path="intlTelInput/"></head>
  <body data-path-to-root="./" data-include-products="true" class="u-body u-xl-mode" data-lang="es">
    <section class="u-align-center u-clearfix u-image u-shading u-section-1" src="" id="sec-3679" data-image-width="1280" data-image-height="800">
      <div class="u-clearfix u-sheet u-sheet-1">
        <h2 class="u-text u-text-default u-text-1">Ultimas citas</h2>
        <a href="Menu_Paciente.jsp" class="u-btn u-btn-round u-button-style u-hover-palette-1-light-1 u-palette-1-base u-radius-6 u-btn-1">Menu</a>
        <div class="custom-expanded u-table u-table-responsive u-table-1">
          <table class="u-table-entity">
            <colgroup>
              <col width="17.8%">
              <col width="16.6%">
              <col width="16.6%">
              <col width="15.4%">
              <col width="18.19999999999999%">
              <col width="16.599999999999987%">
            </colgroup>
            <thead class="u-palette-4-base u-table-header u-table-header-1">
              <tr style="height: 37px;">
                <th class="u-border-1 u-border-palette-4-base u-table-cell">Nombre</th>
                <th class="u-border-1 u-border-palette-4-base u-table-cell">Doctor</th>
                <th class="u-border-1 u-border-palette-4-base u-table-cell"> Fecha</th>
                <th class="u-border-1 u-border-palette-4-base u-table-cell"> Tratamiento</th>
                <th class="u-border-1 u-border-palette-4-base u-table-cell"> Diagnostico</th>
                <th class="u-border-1 u-border-palette-4-base u-table-cell"> Notas</th>
              </tr>
            </thead>
            
            <% 
        
            List<Formulario> listaFormulario = (List) request.getSession().getAttribute("ListaFormulario");
            List<Doctores> listaDoctores = (List) request.getSession().getAttribute("ListaDoctores");
            Paciente Paciente = (Paciente) request.getSession().getAttribute("PasN");

            %>
            <%for(Formulario formu:listaFormulario){  
                if(formu.getIDPaciente().equals(Paciente)){



            %>
            <tbody class="u-table-body">
              <tr style="height: 91px;">
                <td class="u-border-1 u-border-grey-30 u-first-column u-grey-5 u-table-cell u-table-cell-7"><%= Paciente.getNombre()%></td>
                <td class="u-border-1 u-border-grey-30 u-table-cell">
                <%
                    for(Doctores doctor:listaDoctores){
                        if(formu.getIDDoctor().equals(doctor)){
                            out.print(doctor.getNombre() + " ");
                        }
                    }
                        
                    
                %>
                </td>

                <%
                    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = formatter.format(formu.getFechaingreso());
                %>
                <td class="u-border-1 u-border-grey-30 u-table-cell"><%= formattedDate %></td>

                <td class="u-border-1 u-border-grey-30 u-table-cell"><%= formu.getTratamiento() %></td>
                <td class="u-border-1 u-border-grey-30 u-table-cell"><%= formu.getDiagnostico() %></td>
                <td class="u-border-1 u-border-grey-30 u-table-cell"><%= formu.getNotas() %></td>
              </tr>
              
              <%  }
                }%>
              
              
              
              
              <tr style="height: 110px;"></tr>
              <tr style="height: 110px;"></tr>
              <tr style="height: 110px;"></tr>
            </tbody>
          </table>
        </div>
      </div>
    </section>
    

  
</body></html>