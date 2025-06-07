<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Crear Evento</title>
        <link rel="stylesheet" href="estilos/estilo.css">
    </head>
    <body>
        <h2>Crear Nuevo Evento</h2>

        <s:form action="crearEvento" method="POST" cssClass="card-form">
            <s:textfield name="titulo" label="Título" required="true"/>
            <s:textarea name="descripcion" label="Descripción" required="true"/>
            <s:textfield name="ubicacion" label="Ubicación" required="true"/>
            <s:textfield name="fechaInicio" label="Fecha Inicio (yyyy-MM-dd)" required="true"/>
            <s:textfield name="fechaFin" label="Fecha Fin (yyyy-MM-dd)" required="true"/>
            <s:textfield name="plazas" label="Plazas" required="true"/>

            <s:submit value="Crear Evento"/>
        </s:form>

        <s:actionerror/>
        <s:actionmessage/>
    </body>
</html>
