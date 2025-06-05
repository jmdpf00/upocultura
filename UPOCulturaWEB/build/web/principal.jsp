<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Eventos - UpoCultura</title>
        
    </head>
    <body>
        <h2>Listado de Eventos</h2>
        <%-- Mostrar botón solo si el usuario es organizador --%>
        <s:if test="#session.usuario.tipo == 'organizador'">
            <s:form action="crearEventoForm">
                <s:submit value="Crear nuevo evento" />
            </s:form>
        </s:if>
        <table border="1">
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Descripción</th>
                    <th>Ubicación</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Plazas</th>
                    <s:if test="#session.usuario.tipo == 'organizador'">
                        <th>Acciones</th>
                    </s:if>

            </thead>
            <tbody>
                <s:iterator value="listaEventos">
                    <tr>
                        <td><s:property value="titulo" /></td>
                        <td><s:property value="descripcion" /></td>
                        <td><s:property value="ubicacion" /></td>
                        <td><s:property value="fechaInicio" /></td>
                        <td><s:property value="fechaFin" /></td>
                        <td><s:property value="plazas" /></td>
                        <s:if test="#session.usuario.tipo == 'organizador'">
                            <td>
                                <s:form action="modificarEventoForm" method="POST" theme="simple">
                                    <s:hidden name="id" value="%{id}" />
                                    <s:submit value="Modificar" />
                                </s:form>
                                <s:form action="eliminarEvento" method="POST" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este evento?');" theme="simple">
                                    <s:hidden name="id" value="%{id}" />
                                    <s:submit value="Eliminar" />
                                </s:form>
                            </td>
                        </s:if>
                    </tr>
                </s:iterator>
            </tbody>
        </table>

        <br/>
        <h2>Listado de Publicaciones</h2>
        <%-- Mostrar botón solo si el usuario es organizador --%>
        <s:if test="#session.usuario.tipo == 'organizador'">
            <s:form action="crearPublicacionForm">
                <s:submit value="Crear nueva publicacion" />
            </s:form>
        </s:if>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Contenido</th>
                    <th>Fecha Publicacion</th>
            </thead>
            <tbody>
                <s:iterator value="listaPublicaciones">
                    <tr>
                        <td><s:property value="titulo" /></td>
                        <td><s:property value="contenido" /></td>
                        <td><s:property value="fechaPublicacion" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        
        <s:form action="logout">
            <s:submit value="Cerrar Sesión"/>
        </s:form>
    </body>
</html>

