<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Crear Tarea</title>
        <link rel="stylesheet" href="estilos/estilo.css">
    </head>
    <body>
        <h2>Crear Nueva Tarea</h2>

        <s:form action="crearTarea" method="POST" cssClass="card-form">
            <s:textarea name="descripcion" label="Descripcion Tarea" required="true"/>
            <s:textfield name="horaInicio" label="Hora de Inicio (HH:mm)" required="true"/>
            <s:textfield name="horaFin" label="Hora de Fin (HH:mm)" required="true"/>

            <s:submit value="Crear Tarea"/>
        </s:form>

        <s:actionerror/>
        <s:actionmessage/>
    </body>
</html>
