<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Crear Publicacion</title>
        <link rel="stylesheet" href="estilos/estilo.css">
    </head>
    <body>
        <h2>Crear Nueva Publicacion</h2>

        <s:form action="crearPublicacion" method="POST" cssClass="card-form">
            <s:textfield name="titulo" label="Título" required="true"/>
            <s:textarea name="contenido" label="Contenido" required="true"/>
            <s:textfield name="fechaPublicacion" label="Fecha de Publicacion(yyyy-MM-dd)" required="true"/>

            <s:submit value="Crear Publicacion"/>
        </s:form>

        <s:actionerror/>
        <s:actionmessage/>
    </body>
</html>
