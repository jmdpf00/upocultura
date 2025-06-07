<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Modificar Evento</title>
        <link rel="stylesheet" href="estilos/estilo.css">
    </head>
    <body>

        <h2>Modificar Evento</h2>
        <h3>Introduce los nuevos datos para modificar este evento</h3>
        <s:form action="principal">
            <s:submit value="Volver a la pagina principal"/>
        </s:form>
        <s:form action="modificarEvento" cssClass="card-form">
            <s:hidden name="evento.id" value="%{evento.id}" />       
            <s:textfield name="evento.titulo" label="Título" />
            <s:textarea name="evento.descripcion" label="Descripción" />
            <s:textfield name="evento.ubicacion" label="Ubicación" />
            <s:textfield name="evento.fechaInicio" label="Fecha de Inicio" />
            <s:textfield name="evento.fechaFin" label="Fecha de Fin" />
            <s:textfield name="evento.plazas" label="Plazas" />
            <br/><br/>
            <s:submit value="Guardar cambios" />
        </s:form>
    </body>
</html>

