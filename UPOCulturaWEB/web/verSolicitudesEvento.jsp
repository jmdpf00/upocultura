<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Solicitudes del Evento</title>
        <link rel="stylesheet" href="estilos/estilo.css">
    </head>
    <body>
        <h2>Solicitudes del Evento ID: <s:property value="id" /></h2>

        <table border="1">
            <thead>
                <tr>
                    <th>Usuario</th>
                    <th>Fecha Solicitud</th>
                    <th>Estado</th>
                    <th>Asignar Tarea</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="solicitudes" var="solicitud">
                    <s:if test="#solicitud.estado == 'pendiente'">
                        <tr>
                            <td><s:property value="#solicitud.usuario.nombre" /></td>
                            <td><s:property value="#solicitud.fechaSolicitud" /></td>
                            <td><s:property value="#solicitud.estado" /></td>
                            <td>
                                <s:form action="asignarTarea" method="POST" cssClass="card-form">
                                    <s:hidden name="idSolicitud" value="%{#solicitud.id}" />
                                    <s:select name="idTarea" list="tareas" 
                                              listKey="id" listValue="descripcion" />
                                    <s:submit value="Asignar" />
                                </s:form>
                            </td>
                        </tr>
                    </s:if>
                </s:iterator>
            </tbody>
        </table>

        <s:form action="principal">
            <s:submit value="Volver al inicio" />
        </s:form>
    </body>
</html>

